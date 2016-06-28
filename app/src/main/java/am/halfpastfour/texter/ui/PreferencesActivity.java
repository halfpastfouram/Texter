package am.halfpastfour.texter.ui;

/**
 * Created by bobkruithof on 24/06/16.
 * Project: Texter
 * Package: am.halfpastfour.texter.ui
 */

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

import java.util.List;

import am.halfpastfour.texter.R;
import am.halfpastfour.texter.models.Settings;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class PreferencesActivity extends PreferenceActivity
{
	@Override
	public void onBuildHeaders( List<Header> target )
	{
		loadHeadersFromResource( R.xml.header_preferences, target );
	}

	@Override
	protected boolean isValidFragment( String fragmentName )
	{
		return FragmentPreference.class.getName().equals( fragmentName );
	}

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setupActionBar();
		prepareLayout();
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch( item.getItemId() ) {
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.
			case android.R.id.home:
				navigateUpFromSameTask( this );
				return true;
		}
		return super.onOptionsItemSelected( item );
	}

	private void prepareLayout() {
		setTitle( "__Settings" );
	}

	/**
	 * Set up the {@link android.app.ActionBar}
	 */
	private void setupActionBar() {
		// Show the Up button in the action bar.
		ActionBar actionBar = getActionBar();
		if( actionBar != null ) actionBar.setDisplayHomeAsUpEnabled( true );
	}
}

