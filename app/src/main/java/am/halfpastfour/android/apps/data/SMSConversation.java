package am.halfpastfour.android.apps.data;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

import java.util.ArrayList;

import am.halfpastfour.texter.lib.Models.MainListViewModel;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class SMSConversation {
	private String threadId;
	private String messageCount;
	private String snippet;
	private ArrayList<Contact> contacts;
	private String address;

	/**
	 * Constructor
	 * @param cursor
	 */
	public SMSConversation( Cursor cursor )
	{
		this.setThreadId( cursor.getString( cursor.getColumnIndexOrThrow( Telephony.Sms.Conversations.THREAD_ID ) ) );
		this.setMessageCount( cursor.getString( cursor.getColumnIndexOrThrow( Telephony.Sms.Conversations.MESSAGE_COUNT ) ) );
		this.setSnippet( cursor.getString( cursor.getColumnIndexOrThrow( Telephony.Sms.Conversations.SNIPPET ) ) );
	}

	/**
	 * Get the thread id
	 * @return String
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * Sets the thread id
	 * @param threadId
	 */
	public void setThreadId( String threadId ) {
		this.threadId = threadId;
	}

	/**
	 * Get the message count
	 * @return String
	 */
	public String getMessageCount() {
		return messageCount;
	}

	/**
	 * Sets the message count
	 * @param messageCount
	 */
	public void setMessageCount( String messageCount ) {
		this.messageCount = messageCount;
	}

	/**
	 * Get the snippet
	 * @return String
	 */
	public String getSnippet() {
		return snippet;
	}

	/**
	 * Sets the snippet
	 * @param snippet
	 */
	public void setSnippet( String snippet ) {
		this.snippet = snippet;
	}

	/**
	 * Gets the
	 * @param context
	 * @return
	 */
	public String getAddress( Context context )
	{
		if( this.address == null ) {
			Uri uri = Uri.parse( "content://sms" );
			Cursor cursor = context.getContentResolver().query( uri, null, "thread_id = " + this.getThreadId(), null, null );

			if ( cursor.moveToNext() ) {
				this.address	= cursor.getString( cursor.getColumnIndexOrThrow( Telephony.Sms.ADDRESS ) );
			}
		}
		return this.address;
	}

	/**
	 * @param context
	 * @return ArrayList<Contact>
	 */
	public ArrayList<Contact> getContacts( Context context )
	{
		if( this.contacts == null ) {
			String[]			addresses	= this.getAddress( context ).split( ";" );
			ArrayList<Contact>	contacts	= new ArrayList<>();

			for( int i = 0; i < addresses.length; i++ ) {
				Uri		uri		= Uri.withAppendedPath( ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode( addresses[ i ] ) );
				Cursor	cursor	= context.getContentResolver().query( uri, new String[]{
						ContactsContract.PhoneLookup._ID,
						ContactsContract.PhoneLookup.DISPLAY_NAME,
						ContactsContract.PhoneLookup.NUMBER,
						ContactsContract.PhoneLookup.NORMALIZED_NUMBER,
						ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI,
				}, null, null, null );

				if( cursor.moveToNext() ) {
					Contact contact = new Contact( cursor, addresses[ i ] );
					contacts.add( contact );
				} else {
					// The phone number is not in the contact list
					contacts.add( new Contact( null, addresses[ i ] ) );
				}

				cursor.close();
			}

			this.contacts	= contacts;
		}

		return this.contacts;
	}

	/**
	 * Gets the list of messages for this conversation thread
	 * @param context
	 * @return ArrayList<SMSData>
	 */
	public ArrayList<SMSData> getMessages( Activity context )
	{
		MainListViewModel model = new MainListViewModel();
		return model.getAllSmsInConversation( context, this.getThreadId() );
	}
}
