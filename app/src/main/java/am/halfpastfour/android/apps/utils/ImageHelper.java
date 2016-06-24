package am.halfpastfour.android.apps.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

/**
 * Created by bobkruithof on 27/01/15.
 * Project: Texter
 * Package: am.halfpastfour.android.apps.utils
 */
public class ImageHelper
{
	/**
	 * Create a rounded corner bitmap
	 *
	 * @param bitmap             The bitmap to be used
	 * @param borderRadiusPixels The border radius
	 *
	 * @return bitmap
	 */
	static Bitmap getRoundedCornerBitmap( Bitmap bitmap, int borderRadiusPixels )
	{
		Bitmap output = Bitmap.createBitmap(
			bitmap.getWidth(),
			bitmap.getHeight(),
			Config.ARGB_8888
		);

		Canvas canvas = new Canvas( output );

		final int color   = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect   = new Rect( 0, 0, bitmap.getWidth(), bitmap.getHeight() );
		final RectF rectF = new RectF( rect );

		paint.setAntiAlias( true );
		canvas.drawARGB( 0, 0, 0, 0 );
		paint.setColor( color );
		canvas.drawRoundRect(
			rectF,
			(float) borderRadiusPixels,
			(float) borderRadiusPixels,
			paint
		);

		paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
		canvas.drawBitmap( bitmap, rect, rect, paint );

		return output;
	}
}