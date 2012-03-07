// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            HttpException

public class ProtocolException extends HttpException
{

    public ProtocolException()
    {
    }

    public ProtocolException(String s)
    {
        super(s);
    }

    public ProtocolException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
