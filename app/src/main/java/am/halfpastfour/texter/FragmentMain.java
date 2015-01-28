package am.halfpastfour.texter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import am.halfpastfour.texter.lib.ImageHelper;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.data
 */
public class FragmentMain extends ListFragment
{
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		this.onCreateView( inflater, container, savedInstanceState );

		View view						= inflater.inflate( R.layout.fragment_main, container, false );
		BitmapDrawable resourceImage	= (BitmapDrawable) getResources().getDrawable( R.drawable.image_newyork_ribbonstore );
		Bitmap bitmap					= ImageHelper.getRoundedCornerBitmap( resourceImage.getBitmap(), 200 );
		ImageView image					= (ImageView) view.findViewById( R.id.image_contact );

		image.setImageBitmap( bitmap );
		return view;
	}
}
