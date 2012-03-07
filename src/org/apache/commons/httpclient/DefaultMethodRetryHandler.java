// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            MethodRetryHandler, HttpMethod, HttpConnection, HttpRecoverableException

public class DefaultMethodRetryHandler
    implements MethodRetryHandler
{

    public DefaultMethodRetryHandler()
    {
        retryCount = 3;
        requestSentRetryEnabled = false;
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
	public boolean retryMethod(HttpMethod httpmethod, HttpConnection httpconnection, HttpRecoverableException httprecoverableexception, int i, boolean flag)
    {
        boolean flag1;
        if((!flag || requestSentRetryEnabled) && i <= retryCount)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
    }

    public void setRequestSentRetryEnabled(boolean flag)
    {
        requestSentRetryEnabled = flag;
    }

    public void setRetryCount(int i)
    {
        retryCount = i;
    }

    private boolean requestSentRetryEnabled;
    private int retryCount;
}
