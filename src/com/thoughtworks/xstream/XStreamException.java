// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream;

import com.thoughtworks.xstream.core.BaseException;

public class XStreamException extends BaseException
{

    protected XStreamException()
    {
        this("", null);
    }

    public XStreamException(String s)
    {
        this(s, null);
    }

    public XStreamException(String s, Throwable throwable)
    {
        StringBuffer stringbuffer = (new StringBuffer()).append(s);
        String s1;
        if(throwable == null)
            s1 = "";
        else
            s1 = " : " + throwable.getMessage();
        super(stringbuffer.append(s1).toString());
        cause = throwable;
    }

    public XStreamException(Throwable throwable)
    {
        this("", throwable);
    }

    @Override
	public Throwable getCause()
    {
        return cause;
    }

    private Throwable cause;
}
