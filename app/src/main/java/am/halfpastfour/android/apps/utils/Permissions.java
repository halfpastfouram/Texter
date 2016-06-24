package am.halfpastfour.android.apps.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by bobkruithof on 24/06/16.
 * Project: Texter
 * Package: am.halfpastfour.texter.lib.Helper
 */

public class Permissions extends ContextWrapper
{
	public static final int REQUEST = 1;

	public Permissions( Context base )
	{
		super( base );
	}

	private Activity getActivity()
	{
		return (Activity) getBaseContext();
	}

	public boolean checkPermission( String p_permission )
	{
		return getActivity().checkSelfPermission( p_permission )
			== PackageManager.PERMISSION_GRANTED;
	}

	public void requestPermission( String p_permission )
	{
		if( !checkPermission( p_permission ) ) {
			if( getActivity().shouldShowRequestPermissionRationale( p_permission ) ){
				// Show an explanation tot the user (use Async)
				Toast.makeText( getActivity(), "Rationale", Toast.LENGTH_SHORT ).show();
			}
			// No explanation needed, we can request the permission
			getActivity().requestPermissions( new String[]{ p_permission }, REQUEST );
		}
	}
}
