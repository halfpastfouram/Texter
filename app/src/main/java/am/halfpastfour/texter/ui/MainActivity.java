package am.halfpastfour.texter.ui;

import java.util.ArrayList;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import am.halfpastfour.texter.R;
import am.halfpastfour.texter.models.SMSConversation;
import am.halfpastfour.android.apps.utils.Permissions;
import am.halfpastfour.texter.models.view.MainListView;
import am.halfpastfour.android.apps.utils.SMSAdapter;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.texter
 */
public class MainActivity extends Activity
{
	/**
	 * @param savedInstanceState A saved instance state
	 *
	 * @TODO: Fix exception when resuming the program.
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		ActionBar actionBar = getActionBar();
		if ( actionBar != null ) {
			//actionBar.setDisplayHomeAsUpEnabled( true );
			actionBar.setHomeButtonEnabled( true );
		}

		Permissions helper = new Permissions( this );

		Log.d(
			"PERMISSION: READ_SMS",
			String.valueOf( helper.checkPermission( Manifest.permission.READ_SMS ) )
		);
		if( helper.checkPermission( Manifest.permission.READ_SMS ) ) {
			// Construct the data source
			// @TODO cache conversations
			MainListView model = new MainListView();
			ArrayList<SMSConversation> conversations = model
				.getAllSmsConversations( this );

			// Create the adapter to convert the array to views
			SMSAdapter adapter   = new SMSAdapter( this, conversations );
			ListView   listsView = (ListView) findViewById( R.id.listView );
			listsView.setAdapter( adapter );
		} else {
			helper.requestPermission( Manifest.permission.READ_SMS );
		}
	}

	@Override
	protected void onPostCreate( Bundle savedInstanceState )
	{
		super.onPostCreate( savedInstanceState );

		// Set the home image
		ImageView view = (ImageView) findViewById( android.R.id.home );
		view.setPadding( 7, 0, 0, 0 );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.menu_main, menu );
		return true;
	}

	/**
	 * Onclick event for clicking on a list item
	 * @param view
	 */
	public void listItemOnClick( View view )
	{
		String id = String.valueOf( view.getId() );
		Log.i( "MainActivity", "View id: " + id );
	}
}
