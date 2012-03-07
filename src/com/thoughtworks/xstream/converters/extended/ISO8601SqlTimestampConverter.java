// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.thoughtworks.xstream.converters.extended:
//            ISO8601DateConverter

public class ISO8601SqlTimestampConverter extends ISO8601DateConverter
{

    public ISO8601SqlTimestampConverter()
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
        int i = s.lastIndexOf('.');
        int j = 0;
        if(i > 0)
        {
            int k;
            for(k = i + 1; Character.isDigit(s.charAt(k)); k++);
            j = Integer.parseInt(s.substring(i + 1, k));
            s = s.substring(0, i) + s.substring(k);
        }
        Timestamp timestamp = new Timestamp(((Date)super.fromString(s)).getTime());
        timestamp.setNanos(j);
        return timestamp;
    }

    @Override
	public String toString(Object obj)
    {
        Timestamp timestamp = (Timestamp)obj;
        String s = super.toString(new Date(1000L * (timestamp.getTime() / 1000L)));
        String s1 = String.valueOf(timestamp.getNanos());
        int i = s.lastIndexOf('.');
        return s.substring(0, i + 1) + "000000000".substring(s1.length()) + s1 + s.substring(i + 4);
    }

    private static final String PADDING = "000000000";
    static Class class$java$sql$Timestamp;
}
