package am.halfpastfour.texter.lib.Models;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import am.halfpastfour.android.apps.data.SMSConversation;
import am.halfpastfour.android.apps.data.SMSData;

import java.util.ArrayList;

/**
 * Created by bobkruithof on 25/01/15.
 */
public class MainListViewModel
{
	public ArrayList<SMSConversation> getAllSmsConversations( Activity context )
	{
		ArrayList<SMSConversation> conversations = new ArrayList<>();
		SMSConversation smsConversation;
		Uri message = Uri.parse( "content://sms/conversations" );
		ContentResolver contentResolver = context.getContentResolver();

		Cursor cursor;
		cursor = contentResolver.query( message, new String[]{
				Telephony.Sms.Conversations.THREAD_ID,
				Telephony.Sms.Conversations.SNIPPET,
				Telephony.Sms.Conversations.MESSAGE_COUNT
		}, null, null, Telephony.Sms.Conversations.DEFAULT_SORT_ORDER );
		context.startManagingCursor( cursor );

		int totalConversations = cursor.getCount();
		if ( cursor.moveToFirst() ) {
			for ( int i = 0; i < totalConversations; i++ ) {
				smsConversation = new SMSConversation( cursor );
				conversations.add( smsConversation );
				cursor.moveToNext();
			}
		}
		return conversations;
	}

	public ArrayList<SMSData> getAllSmsInConversation( Context context, String threadId ) {
		ArrayList<SMSData> messages = new ArrayList<>();
		SMSData message;
		Uri messageUri = Uri.parse( "content://sms/" );
		ContentResolver contentResolver = context.getContentResolver();

		Cursor cursor;
		cursor = contentResolver.query( messageUri, new String[]{
				Telephony.Sms._ID,
				Telephony.Sms.THREAD_ID,
				Telephony.Sms.ADDRESS,
				Telephony.Sms.BODY,
				Telephony.Sms.DATE,
				Telephony.Sms.READ,
				Telephony.Sms.TYPE
		}, null, null, Telephony.Sms.DEFAULT_SORT_ORDER );

		int totalMessages = cursor.getCount();
		if ( cursor.moveToFirst() ) {
			for ( int i = 0; i < totalMessages; i++ ) {
				message = new SMSData( cursor );
				messages.add( message );
				cursor.moveToNext();
			}
		}
		return messages;
	}

}
