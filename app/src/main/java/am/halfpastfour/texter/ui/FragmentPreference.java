package am.halfpastfour.texter.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import am.halfpastfour.texter.R;

/**
 * Created by bobkruithof on 24/06/16.
 * Project: Texter
 * Package: am.halfpastfour.texter.ui
 */

public class FragmentPreference extends PreferenceFragment
{
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		addPreferencesFromResource( R.xml.fragment_preferences );

		Preference     textPreference = findPreference( "version" );
		String         versionNumber  = "";
		PackageManager packageManager = getActivity().getPackageManager();
		PackageInfo    packageInfo    = null;

		try {
			packageInfo = packageManager.getPackageInfo( getActivity().getPackageName(), 0 );
		} catch ( PackageManager.NameNotFoundException e ) {
			e.printStackTrace();
		}

		if ( packageInfo != null ) {
			versionNumber = packageInfo.versionName;
		}

		textPreference.setSummary( versionNumber );
	}
}
