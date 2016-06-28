package am.halfpastfour.texter.ui;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import am.halfpastfour.android.apps.utils.Permissions;
import am.halfpastfour.android.apps.utils.SMSAdapter;
import am.halfpastfour.texter.R;
import am.halfpastfour.texter.models.SMSConversation;
import am.halfpastfour.texter.models.view.MainListView;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.texter
 */
public class MainActivity extends Activity
{
	private String[] requiredPermissions = {
		Manifest.permission.READ_SMS,
		Manifest.permission.SEND_SMS,
		Manifest.permission.RECEIVE_SMS,
		Manifest.permission.RECEIVE_MMS,
		Manifest.permission.READ_CONTACTS,
		Manifest.permission.READ_EXTERNAL_STORAGE,
		Manifest.permission.WRITE_EXTERNAL_STORAGE
	};

	/**
	 * @param savedInstanceState A saved instance state
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

		for ( String requiredPermission : requiredPermissions ) {
			Log.d(
				"PERMISSION: " + requiredPermission,
				String.valueOf( helper.checkPermission( requiredPermission ) )
			);
		}

		if ( !helper.checkPermissions( requiredPermissions ) ) {
			if ( helper.shouldShowRequestPermissionsRationale( requiredPermissions ) ) {
				// @TODO Open dialog to explain the importance of the permissions
				Toast.makeText( getBaseContext(), "READ TODO", Toast.LENGTH_SHORT ).show();
			}
			helper.requestPermissions( requiredPermissions, 1 );
			buildContent();
		} else {
			buildContent();
		}
	}

	protected void onResume()
	{
		super.onResume();

		Permissions helper = new Permissions( this );
		if ( helper.checkPermissions( requiredPermissions ) ) buildContent();
	}

	private void buildContent()
	{
		// Construct the data source
		// @TODO cache conversations
		MainListView model = new MainListView();
		ArrayList<SMSConversation> conversations = model
			.getAllSmsConversations( this );

		// Create the adapter to convert the array to views
		SMSAdapter adapter   = new SMSAdapter( getBaseContext(), conversations );
		ListView   listsView = (ListView) findViewById( R.id.listView );
		listsView.setAdapter( adapter );
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

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		switch ( item.getItemId() ) {
			case R.id.action_settings: {
				Intent intent = new Intent();
				intent.setClassName( this, "am.halfpastfour.texter.ui.PreferencesActivity" );
				startActivity( intent );
				return true;
			}
		}

		return super.onOptionsItemSelected( item );
	}
}
