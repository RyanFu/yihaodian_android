// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.util.Locale;

public class LocaleConverter extends AbstractSingleValueConverter
{

    public LocaleConverter()
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

    private int[] underscorePositions(String s)
    {
        int ai[] = new int[2];
        int i = 0;
        while(i < ai.length) 
        {
            int j;
            if(i == 0)
                j = 0;
            else
                j = ai[i - 1];
            ai[i] = s.indexOf('_', j + 1);
            i++;
        }
        return ai;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$util$Locale == null)
        {
            class2 = _mthclass$("java.util.Locale");
            class$java$util$Locale = class2;
        } else
        {
            class2 = class$java$util$Locale;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        int ai[] = underscorePositions(s);
        String s1;
        String s2;
        String s3;
        if(ai[0] == -1)
        {
            s1 = s;
            s2 = "";
            s3 = "";
        } else
        if(ai[1] == -1)
        {
            s1 = s.substring(0, ai[0]);
            s2 = s.substring(1 + ai[0]);
            s3 = "";
        } else
        {
            s1 = s.substring(0, ai[0]);
            s2 = s.substring(1 + ai[0], ai[1]);
            s3 = s.substring(1 + ai[1]);
        }
        return new Locale(s1, s2, s3);
    }

    static Class class$java$util$Locale;
}
