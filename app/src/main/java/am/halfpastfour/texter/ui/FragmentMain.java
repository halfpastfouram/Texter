package am.halfpastfour.texter.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import am.halfpastfour.texter.R;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.texter
 */
public class FragmentMain extends ListFragment
{
	/**
	 * @param inflater The inflater
	 * @param container The container
	 * @param savedInstanceState The saved instance state
	 *
	 * @return Whether or not the inflater has been succesfully inflated
	 */
	@Override public View onCreateView(
		LayoutInflater inflater,
		ViewGroup container,
		Bundle savedInstanceState
	) {
		Log.i( "Fragment Main:", "onCreateView fired" );
		super.onCreateView( inflater, container, savedInstanceState );

		return inflater.inflate(
			R.layout.fragment_main,
			container,
			false
		);
	}

	/**
	 *
	 * @param savedInstanceState The saved instance
	 */
	public void onCreate( Bundle savedInstanceState )
	{
		Log.i( "Fragment Main:", "onCreate fired" );
		super.onCreate( savedInstanceState );
	}

	@Override public boolean onOptionsItemSelected( MenuItem item )
	{
		Log.i( "Fragment Main:", "onOptionsItemSelected fired" );
		return super.onOptionsItemSelected( item );
	}

	@Override public void onListItemClick( ListView l, View v, int position, long id )
	{
		Log.i( "Fragment Main:", "onListItemClick fired" );
		super.onListItemClick( l, v, position, id );
	}

	@Override public boolean onContextItemSelected( MenuItem item )
	{
		Log.i( "Fragment Main:", "onContextItemSelected fired" );
		return super.onContextItemSelected( item );
	}
}
