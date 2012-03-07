// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;

import com.thoughtworks.xstream.XStreamException;

public class StreamException extends XStreamException
{

    public StreamException(String s)
    {
        super(s);
    }

    public StreamException(Throwable throwable)
    {
        super(throwable);
    }
}
