// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public abstract class AbstractSingleValueConverter
    implements SingleValueConverter
{

    public AbstractSingleValueConverter()
    {
    }

    @Override
	public abstract boolean canConvert(Class class1);

    @Override
	public abstract Object fromString(String s);

    @Override
	public String toString(Object obj)
    {
        String s;
        if(obj == null)
            s = null;
        else
            s = obj.toString();
        return s;
    }
}
