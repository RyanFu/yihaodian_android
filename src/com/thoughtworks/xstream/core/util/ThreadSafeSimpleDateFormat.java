// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

// Referenced classes of package com.thoughtworks.xstream.core.util:
//            Pool

public class ThreadSafeSimpleDateFormat
{

    public ThreadSafeSimpleDateFormat(String s, int i, int j, final boolean lenient)
    {
        formatString = s;
        pool = new Pool(i, j, new _cls1());
    }

    private DateFormat fetchFromPool()
    {
        TimeZone timezone = TimeZone.getDefault();
        DateFormat dateformat = (DateFormat)pool.fetchFromPool();
        if(!timezone.equals(dateformat.getTimeZone()))
            dateformat.setTimeZone(timezone);
        return dateformat;
    }

    public String format(Date date)
    {
        DateFormat dateformat = fetchFromPool();
        String s = dateformat.format(date);
        pool.putInPool(dateformat);
        return s;
        Exception exception;
        exception;
        pool.putInPool(dateformat);
        throw exception;
    }

    public Date parse(String s)
        throws ParseException
    {
        DateFormat dateformat = fetchFromPool();
        Date date = dateformat.parse(s);
        pool.putInPool(dateformat);
        return date;
        Exception exception;
        exception;
        pool.putInPool(dateformat);
        throw exception;
    }

    private final String formatString;
    private final Pool pool;


    private class _cls1
        implements Pool.Factory
    {

        @Override
		public Object newInstance()
        {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(formatString, Locale.ENGLISH);
            simpledateformat.setLenient(lenient);
            return simpledateformat;
        }

        private final ThreadSafeSimpleDateFormat this$0;
        private final boolean val$lenient;

        _cls1()
        {
            this$0 = ThreadSafeSimpleDateFormat.this;
            lenient = flag;
        }
    }

}
