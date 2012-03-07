// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            ConnectTimeoutException

public class ConnectionPoolTimeoutException extends ConnectTimeoutException
{

    public ConnectionPoolTimeoutException()
    {
    }

    public ConnectionPoolTimeoutException(String s)
    {
        super(s);
    }

    public ConnectionPoolTimeoutException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
