// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.sql.Timestamp;

public class SqlTimestampConverter extends AbstractSingleValueConverter
{

    public SqlTimestampConverter()
    {
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$sql$Timestamp == null)
        {
            class2 = _mthclass$("java.sql.Timestamp");
            class$java$sql$Timestamp = class2;
        } else
        {
            class2 = class$java$sql$Timestamp;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        return Timestamp.valueOf(s);
    }

    static Class class$java$sql$Timestamp;
}
