// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import java.text.ParseException;
import java.util.Date;

public class ThreadSafeSimpleDateFormat extends com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat
{

    public ThreadSafeSimpleDateFormat(String s, int i, int j)
    {
        super(s, i, j, true);
    }

    @Override
	public String format(Date date)
    {
        return super.format(date);
    }

    @Override
	public Date parse(String s)
        throws ParseException
    {
        return super.parse(s);
    }
}
