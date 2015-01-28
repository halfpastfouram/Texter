package am.halfpastfour.android.apps.data;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class Contact
{
	final String TAG	= "Contact";

	private String id;
	private String name;
	private String number;
	private String normalizedNumber;
	private String photoThumbnailUri;
	private String type;

	/**
	 * Constructor
	 * @param cursor
	 */
	public Contact( Cursor cursor )
	{
		this.construct( cursor, null );
	}

	/**
	 * Constructor
	 * @param cursor
	 * @param phoneNumber
	 */
	public Contact( Cursor cursor, String phoneNumber )
	{
		this.construct( cursor, phoneNumber );
	}

	/**
	 * Private method for constructing class
	 * @param cursor
	 * @param phoneNumber
	 */
	private void construct( Cursor cursor, String phoneNumber )
	{
		if ( cursor != null ) {
			this.setId( cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.PhoneLookup._ID ) ) );
			this.setName( cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.PhoneLookup.DISPLAY_NAME ) ) );
			this.setNumber( cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.PhoneLookup.NUMBER ) ) );
			this.setNormalizedNumber( cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.PhoneLookup.NORMALIZED_NUMBER ) ) );
			this.setPhotoThumbnailUri( cursor.getString( cursor.getColumnIndexOrThrow( ContactsContract.PhoneLookup.PHOTO_THUMBNAIL_URI ) ) );
		}

		if ( this.getNumber() == "" || this.getNumber() == null ) {
			this.setNumber( phoneNumber );
		}
	}

	/**
	 * Get the id
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id
	 * @param id
	 */
	public void setId( String id ) {
		Log.i( TAG, "Setting id to " + id );
		this.id = id;
	}

	/**
	 * Gets the name of the contact.
	 * Returns normalizedNumber or number if name is not available
	 * @return String
	 */
	public String getName() {
		return this.name == "" || this.name == null
				? ( this.getNormalizedNumber() == "" || this.getNormalizedNumber() == null ? this.getNumber() : this.getNormalizedNumber() )
				: this.name;
	}

	/**
	 * Sets the contact's name
	 * @param name
	 */
	public void setName( String name ) {
		Log.i( TAG, "Setting name to " + name );
		this.name = name;
	}

	/**
	 * Get the contacts phone number
	 * @return String
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the contact's phone number
	 * @param number
	 */
	public void setNumber( String number ) {
		Log.i( TAG, "Setting number to " + number );
		this.number = number;
	}

	/**
	 * Gets the contact's normalized phone number
	 * @return String
	 */
	public String getNormalizedNumber() {
		return normalizedNumber;
	}

	/**
	 * Sets the contact's normalized phone number
	 * @param normalizedNumber
	 */
	public void setNormalizedNumber( String normalizedNumber ) {
		Log.i( TAG, "Setting normalized number to " + normalizedNumber );
		this.normalizedNumber = normalizedNumber;
	}

	/**
	 * Get the contact's photo thumbnail uri
	 * @return String
	 */
	public String getPhotoThumbnailUri() {
		return this.photoThumbnailUri;
	}

	/**
	 * Sets the contact's photo thumbnail uri
	 * @param setPhotoThumbnailUri
	 */
	public void setPhotoThumbnailUri( String setPhotoThumbnailUri ) {
		Log.i( TAG, "Setting photo thumbnail uri " + setPhotoThumbnailUri );
		this.photoThumbnailUri = setPhotoThumbnailUri;
	}

	/**
	 * Get the contact type
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the contact type
	 * @param type
	 */
	public void setType( String type ) {
		Log.i( TAG, "Setting type to " + type );
		this.type = type;
	}
}
