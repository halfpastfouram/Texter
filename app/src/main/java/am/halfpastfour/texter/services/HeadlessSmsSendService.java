package am.halfpastfour.texter.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by bobkruithof on 24/06/16.
 * Project: Texter
 * Package: am.halfpastfour.texter
 */
public class HeadlessSmsSendService extends Service
{
	@Nullable @Override public IBinder onBind( Intent intent )
	{
		// @TODO: Implement sending of sms message
		return null;
	}
}
