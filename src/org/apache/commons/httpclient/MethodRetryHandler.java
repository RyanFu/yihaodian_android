// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            HttpMethod, HttpConnection, HttpRecoverableException

public interface MethodRetryHandler
{

    public abstract boolean retryMethod(HttpMethod httpmethod, HttpConnection httpconnection, HttpRecoverableException httprecoverableexception, int i, boolean flag);
}
