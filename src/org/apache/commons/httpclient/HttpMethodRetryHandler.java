// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpMethod

public interface HttpMethodRetryHandler
{

    public abstract boolean retryMethod(HttpMethod httpmethod, IOException ioexception, int i);
}
