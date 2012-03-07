// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.text.*;
import java.util.*;

// Referenced classes of package org.apache.commons.httpclient.util:
//            DateParseException

public class DateUtil
{

    private DateUtil()
    {
    }

    public static String formatDate(Date date)
    {
        return formatDate(date, "EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    public static String formatDate(Date date, String s)
    {
        if(date == null)
            throw new IllegalArgumentException("date is null");
        if(s == null)
        {
            throw new IllegalArgumentException("pattern is null");
        } else
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(s, Locale.US);
            simpledateformat.setTimeZone(GMT);
            return simpledateformat.format(date);
        }
    }

    public static Date parseDate(String s)
        throws DateParseException
    {
        return parseDate(s, null, null);
    }

    public static Date parseDate(String s, Collection collection)
        throws DateParseException
    {
        return parseDate(s, collection, null);
    }

    public static Date parseDate(String s, Collection collection, Date date)
        throws DateParseException
    {
        SimpleDateFormat simpledateformat;
        Iterator iterator;
        if(s == null)
            throw new IllegalArgumentException("dateValue is null");
        if(collection == null)
            collection = DEFAULT_PATTERNS;
        if(date == null)
            date = DEFAULT_TWO_DIGIT_YEAR_START;
        if(s.length() > 1 && s.startsWith("'") && s.endsWith("'"))
            s = s.substring(1, s.length() - 1);
        simpledateformat = null;
        iterator = collection.iterator();
_L2:
        if(!iterator.hasNext())
            throw new DateParseException("Unable to parse the date " + s);
        String s1 = (String)iterator.next();
        Date date1;
        if(simpledateformat == null)
        {
            simpledateformat = new SimpleDateFormat(s1, Locale.US);
            simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
            simpledateformat.set2DigitYearStart(date);
        } else
        {
            simpledateformat.applyPattern(s1);
        }
        date1 = simpledateformat.parse(s);
        return date1;
        ParseException parseexception;
        parseexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final Collection DEFAULT_PATTERNS;
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    static 
    {
        String as[] = new String[3];
        as[0] = "EEE MMM d HH:mm:ss yyyy";
        as[1] = "EEEE, dd-MMM-yy HH:mm:ss zzz";
        as[2] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        DEFAULT_PATTERNS = Arrays.asList(as);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 0, 1, 0, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = calendar.getTime();
    }
}
