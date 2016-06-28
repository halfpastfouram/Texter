package am.halfpastfour.android.apps.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import am.halfpastfour.texter.R;
import am.halfpastfour.texter.models.Contact;
import am.halfpastfour.texter.models.SMSConversation;
import am.halfpastfour.texter.ui.ConversationActivity;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class SMSAdapter extends ArrayAdapter<SMSConversation>
{
	private static SMSAdapter instance = null;

	public SMSAdapter( Context context, ArrayList<SMSConversation> smsConversations )
	{
		super( context, 0, smsConversations );
	}

	@Override
	public View getView( final int position, View convertView, ViewGroup parent )
	{
		// Get the data item for this position
		SMSConversation conversation = getItem( position );

		// Check if an existing view is being reused, otherwise inflate the view
		if ( convertView == null ) {
			convertView = LayoutInflater.from( getContext() )
				.inflate( R.layout.fragment_main, parent, false );
		}

		// Lookup view for data population
		String    conversationId = conversation.getThreadId();
		TextView  tvNumber       = (TextView) convertView.findViewById( R.id.tvNumber );
		TextView  tvMessage      = (TextView) convertView.findViewById( R.id.tvMessage );
		ImageView ivContactImage = (ImageView) convertView.findViewById( R.id.image_contact );

		ivContactImage.setMaxWidth( 150 );
		ivContactImage.setMaxHeight( 150 );

		// Get contact name(s) from conversation
		ArrayList<Contact> contacts = conversation.getContacts( getContext() );
		List<String>       names    = new ArrayList<>();

		for ( int i = 0; i < contacts.size(); i++ ) {
			String name = contacts.get( i ).getName();
			if ( !names.contains( name ) ) {
				names.add( name );
			}
		}

		tvNumber.setText( am.halfpastfour.android.apps.utils.Strings.join( names, ", " ) );
		tvMessage.setText( conversation.getSnippet() );

		convertView.setOnClickListener( new View.OnClickListener()
		{
			@Override public void onClick( View view )
			{
				Log.i( "Conversation id:", getItem( position ).getThreadId() );
				Intent intent = new Intent( view.getContext(), ConversationActivity.class );
				Bundle bundle = new Bundle();
				bundle.putString( "threadId", getItem( position ).getThreadId() );
				intent.putExtras( bundle );
				view.getContext().startActivity( intent );
			}
		} );

		return convertView;
	}
}