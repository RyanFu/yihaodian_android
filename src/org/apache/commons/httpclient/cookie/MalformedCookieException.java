// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.cookie;

import org.apache.commons.httpclient.ProtocolException;

public class MalformedCookieException extends ProtocolException
{

    public MalformedCookieException()
    {
    }

    public MalformedCookieException(String s)
    {
        super(s);
    }

    public MalformedCookieException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
