// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.util.ExceptionUtil;

public class NoHttpResponseException extends IOException
{

    public NoHttpResponseException()
    {
    }

    public NoHttpResponseException(String s)
    {
        super(s);
    }

    public NoHttpResponseException(String s, Throwable throwable)
    {
        super(s);
        ExceptionUtil.initCause(this, throwable);
    }
}
