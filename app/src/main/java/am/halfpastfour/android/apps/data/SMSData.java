package am.halfpastfour.android.apps.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class SMSData {
	final String TAG	= "SMSData";

	private String _id;
	private String _number;
	private String _body;
	private String _readState;
	private String _time;
	private String _folderName;

	public SMSData( Cursor cursor ) {
		this.setId( cursor.getString( cursor.getColumnIndexOrThrow( "_id" ) ) );
		this.setNumber( cursor.getString( cursor.getColumnIndexOrThrow( "address" ) ) );
		this.setBody( cursor.getString( cursor.getColumnIndexOrThrow( "snippet" ) ) );
		this.setReadState( cursor.getString( cursor.getColumnIndexOrThrow( "read" ) ) );
		this.setTime( cursor.getString( cursor.getColumnIndexOrThrow( "date" ) ) );

		if ( cursor.getString( cursor.getColumnIndexOrThrow( "type" ) ).contains( "1" ) ) {
			this.setFolderName( "index" );
		} else {
			this.setFolderName( "sent" );
		}
	}

	public String getContactName( Context context )
	{
		String[] numbers	= this.getNumber().split( ";" );
		List<String> names	= new ArrayList<>();

		for ( int i = 0; i < numbers.length; i++ ) {
			Uri uri = Uri.withAppendedPath( ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode( this.getNumber() ) );
			Cursor cursor = context.getContentResolver().query( uri, new String[]{ ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null );
			if( cursor.moveToNext() ) {
				String name	= cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.PhoneLookup.DISPLAY_NAME ) );
				if( !names.contains( name ) ) {
					Log.i( TAG + " getContactName", "Adding " + name + " to the list of names" );
					names.add( name );
				}
			} else {
				Log.i( TAG + " getContactName", "Adding " + numbers[ i ] + " to the list of names" );
				names.add( numbers[ i ] );
			}

			cursor.close();
		}

		return am.halfpastfour.android.apps.utils.Strings.join( names, ", " );
	}

	public String getId() {
		return _id;
	}

	public void setId( String id ) {
		this._id = id;
	}

	public String getNumber() {
		return _number;
	}

	public void setNumber( String number ) {
		this._number = number;
	}

	public String getBody() {
		return _body;
	}

	public void setBody( String body ) {
		this._body = body;
	}

	public String getReadState() {
		return _readState;
	}

	public void setReadState( String readState ) {
		this._readState = readState;
	}

	public String getTime() {
		return _time;
	}

	public void setTime( String time ) {
		this._time = time;
	}

	public String getFolderName() {
		return _folderName;
	}

	public void setFolderName( String folderName ) {
		this._folderName = folderName;
	}
}