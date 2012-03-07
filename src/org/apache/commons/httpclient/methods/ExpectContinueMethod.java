// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.logging.Log;

public abstract class ExpectContinueMethod extends HttpMethodBase
{

    public ExpectContinueMethod()
    {
    }

    public ExpectContinueMethod(String s)
    {
        super(s);
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

    @Override
	protected void addRequestHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        boolean flag;
        LOG.trace("enter ExpectContinueMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(httpstate, httpconnection);
        if(getRequestHeader("Expect") != null)
            flag = true;
        else
            flag = false;
        if(!getParams().isParameterTrue("http.protocol.expect-continue") || !getEffectiveVersion().greaterEquals(HttpVersion.HTTP_1_1) || !hasRequestContent()) goto _L2; else goto _L1
_L1:
        if(!flag)
            setRequestHeader("Expect", "100-continue");
_L4:
        return;
_L2:
        if(flag)
            removeRequestHeader("Expect");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean getUseExpectHeader()
    {
        return getParams().getBooleanParameter("http.protocol.expect-continue", false);
    }

    protected abstract boolean hasRequestContent();

    public void setUseExpectHeader(boolean flag)
    {
        getParams().setBooleanParameter("http.protocol.expect-continue", flag);
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$ExpectContinueMethod;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$ExpectContinueMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.ExpectContinueMethod");
            class$org$apache$commons$httpclient$methods$ExpectContinueMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$ExpectContinueMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
