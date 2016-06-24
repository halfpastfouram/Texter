package am.halfpastfour.texter;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import am.halfpastfour.android.apps.data.SMSConversation;
import am.halfpastfour.texter.lib.Models.MainListViewModel;
import am.halfpastfour.texter.lib.SMSAdapter;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.texter
 */
public class MainActivity extends Activity
{
	private ActionBarDrawerToggle mDrawerToggle;

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

		//String[] mPlanetTitles = new String[]{};
//		DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(
//			R.id .drawer_layout
//		);
//		ListView mDrawerList	= (ListView) findViewById( R.id.left_drawer );

		// Set the adapter for the list view
//		mDrawerList.setAdapter( new ArrayAdapter<>( this,
//			R.layout.drawer_list_item, mPlanetTitles
//		) );

		// Set the list's click listener
//		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

//		mDrawerToggle = new ActionBarDrawerToggle(
//			this,                  /* host Activity */
//			mDrawerLayout,         /* DrawerLayout object */
//			null,  /* nav drawer icon to replace 'Up' caret */
//			R.string.drawer_open,  /* "open drawer" description */
//			R.string.drawer_close /* "close drawer" description */
//		) {
//			/** Called when a drawer has settled in a completely closed state
//			 * . */
//			public void onDrawerClosed( View view )
//			{
//				super.onDrawerClosed( view );
//				ActionBar actionBar = getActionBar();
//				if ( actionBar != null ) {
//					actionBar.setTitle( R.string.app_name );
//				}
//			}
//
//			/** Called when a drawer has settled in a completely open state. */
//			public void onDrawerOpened( View drawerView )
//			{
//				super.onDrawerOpened( drawerView );
//				ActionBar actionBar = getActionBar();
//				if ( actionBar != null ) {
//					actionBar.setTitle( "Menu" );
//				}
//			}
//		};

		// Set the drawer toggle as the DrawerListener
//		mDrawerLayout.setDrawerListener( mDrawerToggle );

		ActionBar actionBar = getActionBar();
		if ( actionBar != null ) {
			actionBar.setDisplayHomeAsUpEnabled( true );
			actionBar.setHomeButtonEnabled( true );
		}

		// Construct the data source
		// @TODO cache conversations
		MainListViewModel          model         = new MainListViewModel();
		ArrayList<SMSConversation> conversations = model
			.getAllSmsConversations( this );

		// Create the adapter to convert the array to views
		SMSAdapter adapter  = new SMSAdapter( this, conversations );
		ListView   listsView = (ListView) findViewById( R.id.listView );
		listsView.setAdapter( adapter );
	}

	@Override
	protected void onPostCreate( Bundle savedInstanceState )
	{
		super.onPostCreate( savedInstanceState );
		// Sync the toggle state after onRestoreInstanceState has occurred.
		// mDrawerToggle.syncState();

		ImageView view = (ImageView) findViewById( android.R.id.home );
		view.setPadding( 7, 0, 0, 0 );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate( R.menu.menu_main, menu );
		return true;
	}

	@Override
	public void onConfigurationChanged( Configuration newConfig )
	{
		super.onConfigurationChanged( newConfig );
//		mDrawerToggle.onConfigurationChanged( newConfig );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
//		if ( mDrawerToggle.onOptionsItemSelected( item ) ) {
//			return true;
//		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected( item );
	}

}
