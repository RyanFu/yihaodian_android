// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.thoughtworks.xstream.converters.extended:
//            ISO8601GregorianCalendarConverter

public class ISO8601DateConverter extends ISO8601GregorianCalendarConverter
{

    public ISO8601DateConverter()
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
        if(class$java$util$Date == null)
        {
            class2 = _mthclass$("java.util.Date");
            class$java$util$Date = class2;
        } else
        {
            class2 = class$java$util$Date;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        return ((Calendar)super.fromString(s)).getTime();
    }

    @Override
	public String toString(Object obj)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date)obj);
        return super.toString(calendar);
    }

    static Class class$java$util$Date;
}
