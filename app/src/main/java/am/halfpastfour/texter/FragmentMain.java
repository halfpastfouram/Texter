package am.halfpastfour.texter;

import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ViewGroup;
import android.view.View;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.texter
 */
public class FragmentMain extends ListFragment
{
	@Override
	public View onCreateView(
		LayoutInflater inflater,
		ViewGroup container,
		Bundle savedInstanceState
	) {
		this.onCreateView( inflater, container, savedInstanceState );

		return inflater.inflate(
			R.layout.fragment_main,
			container,
			false
		);
	}
}
