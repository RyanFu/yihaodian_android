// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.*;
import java.util.*;

public class GregorianCalendarConverter
    implements Converter
{

    public GregorianCalendarConverter()
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
        if(class$java$util$GregorianCalendar == null)
        {
            class2 = _mthclass$("java.util.GregorianCalendar");
            class$java$util$GregorianCalendar = class2;
        } else
        {
            class2 = class$java$util$GregorianCalendar;
        }
        return class1.equals(class2);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        GregorianCalendar gregoriancalendar = (GregorianCalendar)obj;
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, "time", Long.TYPE);
        hierarchicalstreamwriter.setValue(String.valueOf(gregoriancalendar.getTime().getTime()));
        hierarchicalstreamwriter.endNode();
        Class class1;
        if(class$java$lang$String == null)
        {
            class1 = _mthclass$("java.lang.String");
            class$java$lang$String = class1;
        } else
        {
            class1 = class$java$lang$String;
        }
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, "timezone", class1);
        hierarchicalstreamwriter.setValue(gregoriancalendar.getTimeZone().getID());
        hierarchicalstreamwriter.endNode();
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        hierarchicalstreamreader.moveDown();
        long l = Long.parseLong(hierarchicalstreamreader.getValue());
        hierarchicalstreamreader.moveUp();
        String s;
        GregorianCalendar gregoriancalendar;
        if(hierarchicalstreamreader.hasMoreChildren())
        {
            hierarchicalstreamreader.moveDown();
            s = hierarchicalstreamreader.getValue();
            hierarchicalstreamreader.moveUp();
        } else
        {
            s = TimeZone.getDefault().getID();
        }
        gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTimeZone(TimeZone.getTimeZone(s));
        gregoriancalendar.setTime(new Date(l));
        return gregoriancalendar;
    }

    static Class class$java$lang$String;
    static Class class$java$util$GregorianCalendar;
}
