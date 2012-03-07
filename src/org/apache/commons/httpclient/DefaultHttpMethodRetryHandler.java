// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpMethodRetryHandler, HttpMethodBase, NoHttpResponseException, HttpMethod

public class DefaultHttpMethodRetryHandler
    implements HttpMethodRetryHandler
{

    public DefaultHttpMethodRetryHandler()
    {
        this(3, false);
    }

    public DefaultHttpMethodRetryHandler(int i, boolean flag)
    {
        retryCount = i;
        requestSentRetryEnabled = flag;
    }

    public int getRetryCount()
    {
        return retryCount;
    }

    public boolean isRequestSentRetryEnabled()
    {
        return requestSentRetryEnabled;
    }

    @Override
	public boolean retryMethod(HttpMethod httpmethod, IOException ioexception, int i)
    {
        if(httpmethod == null)
            throw new IllegalArgumentException("HTTP method may not be null");
        if(ioexception == null)
            throw new IllegalArgumentException("Exception parameter may not be null");
        boolean flag;
        if((httpmethod instanceof HttpMethodBase) && ((HttpMethodBase)httpmethod).isAborted())
            flag = false;
        else
        if(i > retryCount)
            flag = false;
        else
        if(ioexception instanceof NoHttpResponseException)
            flag = true;
        else
        if(ioexception instanceof InterruptedIOException)
            flag = false;
        else
        if(ioexception instanceof UnknownHostException)
            flag = false;
        else
        if(ioexception instanceof NoRouteToHostException)
            flag = false;
        else
        if(SSL_HANDSHAKE_EXCEPTION != null && SSL_HANDSHAKE_EXCEPTION.isInstance(ioexception))
            flag = false;
        else
        if(!httpmethod.isRequestSent() || requestSentRetryEnabled)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static Class SSL_HANDSHAKE_EXCEPTION = null;
    private boolean requestSentRetryEnabled;
    private int retryCount;

    static 
    {
        SSL_HANDSHAKE_EXCEPTION = Class.forName("javax.net.ssl.SSLHandshakeException");
_L2:
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
