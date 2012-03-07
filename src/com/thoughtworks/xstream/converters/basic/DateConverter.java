// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class DateConverter extends AbstractSingleValueConverter
{

    public DateConverter()
    {
        this(false);
    }

    public DateConverter(String s, String as[])
    {
        this(s, as, false);
    }

    public DateConverter(String s, String as[], boolean flag)
    {
        defaultFormat = new ThreadSafeSimpleDateFormat(s, 4, 20, flag);
        acceptableFormats = new ThreadSafeSimpleDateFormat[as.length];
        for(int i = 0; i < as.length; i++)
            acceptableFormats[i] = new ThreadSafeSimpleDateFormat(as[i], 1, 20, flag);

    }

    public DateConverter(boolean flag)
    {
        String as[] = new String[4];
        as[0] = "yyyy-MM-dd HH:mm:ss.S a";
        as[1] = "yyyy-MM-dd HH:mm:ssz";
        as[2] = "yyyy-MM-dd HH:mm:ss z";
        as[3] = "yyyy-MM-dd HH:mm:ssa";
        this("yyyy-MM-dd HH:mm:ss.S z", as, flag);
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
        Date date2 = defaultFormat.parse(s);
        Date date1 = date2;
_L4:
        return date1;
        ParseException parseexception;
        parseexception;
        int i = 0;
_L2:
        if(i >= acceptableFormats.length)
            break; /* Loop/switch isn't completed */
        Date date = acceptableFormats[i].parse(s);
        date1 = date;
        continue; /* Loop/switch isn't completed */
        ParseException parseexception1;
        parseexception1;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        throw new ConversionException("Cannot parse date " + s);
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public String toString(Object obj)
    {
        return defaultFormat.format((Date)obj);
    }

    static Class class$java$util$Date;
    private final ThreadSafeSimpleDateFormat acceptableFormats[];
    private final ThreadSafeSimpleDateFormat defaultFormat;
}
