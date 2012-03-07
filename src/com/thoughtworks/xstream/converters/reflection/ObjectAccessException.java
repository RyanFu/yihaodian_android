// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.XStreamException;

public class ObjectAccessException extends XStreamException
{

    public ObjectAccessException(String s)
    {
        super(s);
    }

    public ObjectAccessException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
