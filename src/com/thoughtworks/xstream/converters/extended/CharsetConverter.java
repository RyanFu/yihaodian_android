// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.nio.charset.Charset;

public class CharsetConverter extends AbstractSingleValueConverter
{

    public CharsetConverter()
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
        if(class$java$nio$charset$Charset == null)
        {
            class2 = _mthclass$("java.nio.charset.Charset");
            class$java$nio$charset$Charset = class2;
        } else
        {
            class2 = class$java$nio$charset$Charset;
        }
        return class2.isAssignableFrom(class1);
    }

    @Override
	public Object fromString(String s)
    {
        return Charset.forName(s);
    }

    @Override
	public String toString(Object obj)
    {
        String s;
        if(obj == null)
            s = null;
        else
            s = ((Charset)obj).name();
        return s;
    }

    static Class class$java$nio$charset$Charset;
}
