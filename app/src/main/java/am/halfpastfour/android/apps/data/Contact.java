package am.halfpastfour.android.apps.data;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by bobkruithof on 27/01/15.
 * Package: am.halfpastfour.android.apps.data
 */
public class Contact
{
	final String TAG = "Contact";

	private String id;
	private String name;
	private String number;
	private String normalizedNumber;
	private String photoThumbnailUri;
	private String type;

	/**
	 * Constructor
	 *
	 * @param cursor
	 */
	public Contact( Cursor cursor )
	{
		this.construct( cursor, null );
	}

	/**
	 * Constructor
	 *
	 * @param cursor
	 * @param phoneNumber
	 */
	public Contact( Cursor cursor, String phoneNumber )
	{
		this.construct( cursor, phoneNumber );
	}

	/**
	 * Private method for constructing class
	 *
	 * @param cursor
	 * @param phoneNumber
	 */
	private void construct( Cursor cursor, String phoneNumber )
	{
		if ( cursor != null ) {
			this.setId( cursor.getString( cursor.getColumnIndexOrThrow(
				ContactsContract.PhoneLookup._ID ) ) );
			this.setName( cursor.getString( cursor.getColumnIndexOrThrow(
				ContactsContract.PhoneLookup.DISPLAY_NAME ) ) );
			this.setNumber( cursor.getString( cursor.getColumnIndexOrThrow(
				ContactsContract.PhoneLookup.NUMBER ) ) );
			this.setNormalizedNumber( cursor.getString( cursor
				.getColumnIndexOrThrow(
					ContactsContract.PhoneLookup.NORMALIZED_NUMBER ) ) );
			this.setPhotoThumbnailUri( cursor.getString( cursor
				.getColumnIndexOrThrow(
					ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI ) ) );
		}

		this.setNumber( phoneNumber );
	}

	/**
	 * Get the id
	 *
	 * @return String
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id
	 *
	 * @param id
	 */
	public void setId( String id )
	{
		this.id = id;
	}

	/**
	 * Gets the name of the contact.
	 * Returns normalizedNumber or number if name is not available
	 *
	 * @return String
	 */
	public String getName()
	{
		return this.name == "" || this.name == null
			? (
			this.getNormalizedNumber() == ""
				|| this.getNormalizedNumber() == null
				? this.getNumber()
				: this.getNormalizedNumber()
		)
			: this.name;
	}

	/**
	 * Sets the contact's name
	 *
	 * @param name
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	 * Get the contacts phone number
	 *
	 * @return String
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * Sets the contact's phone number
	 *
	 * @param number
	 */
	public void setNumber( String number )
	{
		this.number = number;
	}

	/**
	 * Gets the contact's normalized phone number
	 *
	 * @return String
	 */
	public String getNormalizedNumber()
	{
		return normalizedNumber;
	}

	/**
	 * Sets the contact's normalized phone number
	 *
	 * @param normalizedNumber
	 */
	public void setNormalizedNumber( String normalizedNumber )
	{
		this.normalizedNumber = normalizedNumber;
	}

	/**
	 * Get the contact's photo thumbnail uri
	 *
	 * @return String
	 */
	public String getPhotoThumbnailUri()
	{
		Log.i( TAG, "Returning thumbnail uri " + this.photoThumbnailUri );
		return this.photoThumbnailUri;
	}

	/**
	 * Sets the contact's photo thumbnail uri
	 *
	 * @param photoThumbnailUri
	 */
	public void setPhotoThumbnailUri( String photoThumbnailUri )
	{
		Log.i( TAG, "Setting photo thumbnail uri to " + photoThumbnailUri );
		this.photoThumbnailUri = photoThumbnailUri;
	}

	public Drawable getPhotoThumbnailDrawable( Context context )
	{
		if ( this.getPhotoThumbnailUri() == ""
			|| this.getPhotoThumbnailUri() == null ) {
			return null;
		}

		Drawable drawable = null;

		try {
			Uri uri = Uri.parse( this.getPhotoThumbnailUri() );
			InputStream inputStream = context.getContentResolver()
				.openInputStream( uri );
			drawable = Drawable.createFromStream( inputStream, uri.toString
				() );
		} catch ( Exception exception ) {
			Log.i( TAG, "An exception occurred" );
			exception.printStackTrace();
		}

		return drawable;
	}

	/**
	 * Get the contact type
	 *
	 * @return String
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the contact type
	 *
	 * @param type
	 */
	public void setType( String type )
	{
		this.type = type;
	}
}
