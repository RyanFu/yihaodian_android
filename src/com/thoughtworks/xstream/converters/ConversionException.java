// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters;

import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.core.util.OrderRetainingMap;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters:
//            ErrorWriter

public class ConversionException extends XStreamException
    implements ErrorWriter
{

    public ConversionException(String s)
    {
        super(s);
        stuff = new OrderRetainingMap();
    }

    public ConversionException(String s, Throwable throwable)
    {
        super(s, throwable);
        stuff = new OrderRetainingMap();
        if(s != null)
            add("message", s);
        if(throwable != null)
        {
            add("cause-exception", throwable.getClass().getName());
            String s1;
            if(throwable instanceof ConversionException)
                s1 = ((ConversionException)throwable).getShortMessage();
            else
                s1 = throwable.getMessage();
            add("cause-message", s1);
        }
    }

    public ConversionException(Throwable throwable)
    {
        this(throwable.getMessage(), throwable);
    }

    @Override
	public void add(String s, String s1)
    {
        stuff.put(s, s1);
    }

    @Override
	public String get(String s)
    {
        return (String)stuff.get(s);
    }

    @Override
	public String getMessage()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(super.getMessage() != null)
            stringbuffer.append(super.getMessage());
        if(!stringbuffer.toString().endsWith("\n-------------------------------"))
            stringbuffer.append("\n---- Debugging information ----");
        String s1;
        for(Iterator iterator = keys(); iterator.hasNext(); stringbuffer.append(": ").append(s1))
        {
            String s = (String)iterator.next();
            s1 = get(s);
            stringbuffer.append('\n').append(s);
            stringbuffer.append("                    ".substring(Math.min(20, s.length())));
        }

        stringbuffer.append("\n-------------------------------");
        return stringbuffer.toString();
    }

    public String getShortMessage()
    {
        return super.getMessage();
    }

    @Override
	public Iterator keys()
    {
        return stuff.keySet().iterator();
    }

    private static final String SEPARATOR = "\n-------------------------------";
    private Map stuff;
}
