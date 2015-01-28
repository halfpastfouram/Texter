package am.halfpastfour.android.apps.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by bobkruithof on 26/01/15.
 */
public class Strings
{
	public static String join( Collection<?> s, String delimiter )
	{
		StringBuilder builder = new StringBuilder();
		Iterator<?> iter = s.iterator();
		while ( iter.hasNext() ) {
			builder.append( iter.next() );
			if ( !iter.hasNext() ) {
				break;
			}
			builder.append( delimiter );
		}
		return builder.toString();
	}
}
