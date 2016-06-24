package am.halfpastfour.android.apps.data;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bobkruithof on 01/02/15.
 * Package: am.halfpastfour.android.apps.data
 */
abstract public class ContentProvider extends android.content.ContentProvider
{
	private SQLiteDatabase smsDb;

	private static final String TAG                = "am.halfpast.android.apps.data"
		+ ".ContentProvider";
	private static final String DATABASE_NAME      = "texter-sms.db";
	private static final int    DATABASE_VERSION   = 1;
	private static final String CONVERSATION_TABLE = "conversation";
}
