package am.halfpastfour.android.apps.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;

/**
 * Created by bobkruithof on 24/06/16.
 * Project: Texter
 * Package: am.halfpastfour.texter.lib.Helper
 */

public class Permissions extends ContextWrapper
{
	/**
	 * Stores the given base context in the super class
	 *
	 * @param base The given base context
	 */
	public Permissions( Context base )
	{
		super( base );
	}

	/**
	 * Returns the
	 * @return The activity gotten from the base context
	 */
	private Activity getActivity()
	{
		return (Activity) getBaseContext();
	}

	/**
	 * Check if the given permission is granted
	 *
	 * @param p_permission The requested permission string
	 *
	 * @return Whether or not the given permission is granted
	 */
	public boolean checkPermission( String p_permission )
	{
		return getActivity().checkSelfPermission( p_permission )
			== PackageManager.PERMISSION_GRANTED;
	}

	/**
	 * Check if the given permission is granted
	 *
	 * @param p_permissions The requested permission string array
	 *
	 * @return Whether or not the given permissions are granted
	 */
	public boolean checkPermissions( String[] p_permissions )
	{
		boolean result = true;
		for ( String p_permission : p_permissions ) {
			if ( !checkPermission( p_permission ) ) result = false;
		}
		return result;
	}

	/**
	 * Check if a rationale should be shown for the request of the given permission string
	 *
	 * @param p_permission The requested permission string
	 *
	 * @return Whether or not a rationale should be shown to the user
	 */
	public boolean shouldShowRequestPermissionRationale( String p_permission )
	{
		return getActivity().shouldShowRequestPermissionRationale( p_permission );
	}

	/**
	 * Check if a rationale should be shown for the request of the given permissions string array
	 *
	 * @param p_permissions The requested permission string
	 *
	 * @return Whether or not a rationale should be shown to the user
	 */
	public boolean shouldShowRequestPermissionsRationale( String[] p_permissions )
	{
		boolean result	= false;
		for ( String p_permission : p_permissions ) {
			if( getActivity().shouldShowRequestPermissionRationale( p_permission ) ) result = true;
		}
		return result;
	}

	/**
	 * Request permission based on the given permissions string
	 *
	 * @param p_permission The requested permission string
	 * @param p_requestId A request id to identify the response with
	 */
	public void requestPermission( String p_permission, int p_requestId )
	{
		requestPermissions( new String[]{ p_permission }, p_requestId );
	}

	/**
	 * Request permission based on the given permissions string array
	 *
	 * @param p_permissions The requested permissions string array
	 * @param p_requestId A request id to identify the response with
	 */
	public void requestPermissions( String[] p_permissions, int p_requestId )
	{
		getActivity().requestPermissions( p_permissions, p_requestId );
	}
}
