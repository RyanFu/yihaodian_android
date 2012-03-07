// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;


public abstract class BaseException extends RuntimeException
{

    protected BaseException(String s)
    {
        super(s);
    }

    @Override
	public abstract Throwable getCause();
}
