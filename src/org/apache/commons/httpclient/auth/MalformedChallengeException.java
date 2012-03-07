// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.ProtocolException;

public class MalformedChallengeException extends ProtocolException
{

    public MalformedChallengeException()
    {
    }

    public MalformedChallengeException(String s)
    {
        super(s);
    }

    public MalformedChallengeException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
