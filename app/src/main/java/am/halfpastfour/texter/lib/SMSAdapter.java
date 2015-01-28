package am.halfpastfour.texter.lib;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import am.halfpastfour.android.apps.data.Contact;
import am.halfpastfour.android.apps.data.SMSConversation;
import am.halfpastfour.android.apps.data.SMSData;
import am.halfpastfour.texter.R;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class SMSAdapter extends ArrayAdapter<SMSConversation>
{
	public SMSAdapter( Context context, ArrayList<SMSConversation> smsConversations )
	{
		super( context, 0, smsConversations );
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent )
	{
		// Get the data item for this position
		SMSConversation conversation = getItem( position );

		// Check if an existing view is being reused, otherwise inflate the view
		if( convertView == null ) {
			convertView = LayoutInflater.from( getContext() ).inflate( R.layout.fragment_main, parent, false );
		}

		// Lookup view for data population
		TextView tvNumber = (TextView) convertView.findViewById( R.id.tvNumber );
		TextView tvMessage = (TextView) convertView.findViewById( R.id.tvMessage );

		// Get contact name(s) from conversation
		ArrayList<Contact> contacts = conversation.getContacts( getContext() );
		List<String> names	= new ArrayList<>();

		for ( int i = 0; i < contacts.size(); i++ ) {
			String name	= contacts.get( i ).getName();
			if( !names.contains( name ) ) {
				names.add( name );
			}
		}

		tvNumber.setText( am.halfpastfour.android.apps.utils.Strings.join( names, ", " ) );
		tvMessage.setText( conversation.getSnippet() );

		BitmapDrawable resourceImage	= (BitmapDrawable) getContext().getResources().getDrawable( R.drawable.image_newyork_ribbonstore );
		Bitmap bitmap					= ImageHelper.getRoundedCornerBitmap( resourceImage.getBitmap(), 5000 );
		ImageView image					= (ImageView) convertView.findViewById( R.id.image_contact );
		image.setMaxWidth( 150 );
		image.setMaxHeight( 150 );

		image.setImageBitmap( bitmap );

		return convertView;
	}
}
