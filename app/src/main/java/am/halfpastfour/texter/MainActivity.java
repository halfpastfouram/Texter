package am.halfpastfour.texter;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import am.halfpastfour.android.apps.data.SMSConversation;
import am.halfpastfour.android.apps.data.SMSData;
import am.halfpastfour.texter.lib.Models.MainListViewModel;
import am.halfpastfour.texter.lib.SMSAdapter;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class MainActivity extends ActionBarActivity
{
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		// Construct the data source
		// @TODO cache conversations
		MainListViewModel model = new MainListViewModel();
		ArrayList<SMSConversation> conversations = model.getAllSmsConversations( this );

		// Create the adapter to convert the array to views
		SMSAdapter adapter = new SMSAdapter( this, conversations );
		ListView listView = (ListView) findViewById( R.id.listView );
		listView.setAdapter( adapter );
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if ( id == R.id.action_settings ) {
			return true;
		}

		return super.onOptionsItemSelected( item );
	}

}
