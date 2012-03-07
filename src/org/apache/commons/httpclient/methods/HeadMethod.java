// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.logging.Log;

public class HeadMethod extends HttpMethodBase
{

    public HeadMethod()
    {
        setFollowRedirects(true);
    }

    public HeadMethod(String s)
    {
        super(s);
        setFollowRedirects(true);
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    public int getBodyCheckTimeout()
    {
        return getParams().getIntParameter("http.protocol.head-body-timeout", -1);
    }

    @Override
	public String getName()
    {
        return "HEAD";
    }

    @Override
	protected void readResponseBody(HttpState httpstate, HttpConnection httpconnection)
        throws HttpException, IOException
    {
        int i;
        LOG.trace("enter HeadMethod.readResponseBody(HttpState, HttpConnection)");
        i = getParams().getIntParameter("http.protocol.head-body-timeout", -1);
        if(i >= 0) goto _L2; else goto _L1
_L1:
        responseBodyConsumed();
_L6:
        return;
_L2:
        if(LOG.isDebugEnabled())
            LOG.debug("Check for non-compliant response body. Timeout in " + i + " ms");
        boolean flag1 = httpconnection.isResponseAvailable(i);
        boolean flag = flag1;
_L4:
        if(!flag)
            continue; /* Loop/switch isn't completed */
        if(getParams().isParameterTrue("http.protocol.reject-head-body"))
            throw new ProtocolException("Body content may not be sent in response to HTTP HEAD request");
        break; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        LOG.debug("An IOException occurred while testing if a response was available, we will assume one is not.", ioexception);
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        LOG.warn("Body content returned in response to HTTP HEAD");
        super.readResponseBody(httpstate, httpconnection);
        if(true) goto _L6; else goto _L5
_L5:
    }

    @Override
	public void recycle()
    {
        super.recycle();
        setFollowRedirects(true);
    }

    public void setBodyCheckTimeout(int i)
    {
        getParams().setIntParameter("http.protocol.head-body-timeout", i);
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$HeadMethod;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$HeadMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.HeadMethod");
            class$org$apache$commons$httpclient$methods$HeadMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$HeadMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
