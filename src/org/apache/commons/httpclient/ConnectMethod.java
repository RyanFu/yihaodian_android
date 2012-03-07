// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpMethodBase, HttpException, HttpConnection, NameValuePair, 
//            Header, StatusLine, Wire, HttpMethod, 
//            HttpState

public class ConnectMethod extends HttpMethodBase
{

    public ConnectMethod()
    {
        LOG.trace("enter ConnectMethod()");
    }

    public ConnectMethod(HttpMethod httpmethod)
    {
        LOG.trace("enter ConnectMethod(HttpMethod)");
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
	protected void addCookieRequestHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
    }

    @Override
	protected void addRequestHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter ConnectMethod.addRequestHeaders(HttpState, HttpConnection)");
        addUserAgentRequestHeader(httpstate, httpconnection);
        addHostRequestHeader(httpstate, httpconnection);
        addProxyConnectionHeader(httpstate, httpconnection);
    }

    @Override
	public int execute(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter ConnectMethod.execute(HttpState, HttpConnection)");
        int i = super.execute(httpstate, httpconnection);
        if(LOG.isDebugEnabled())
            LOG.debug("CONNECT status code " + i);
        return i;
    }

    @Override
	public String getName()
    {
        return "CONNECT";
    }

    @Override
	protected boolean shouldCloseConnection(HttpConnection httpconnection)
    {
        boolean flag;
        if(getStatusCode() == 200)
        {
            Header header = null;
            if(!httpconnection.isTransparent())
                header = getResponseHeader("proxy-connection");
            if(header == null)
                header = getResponseHeader("connection");
            if(header != null && header.getValue().equalsIgnoreCase("close") && LOG.isWarnEnabled())
                LOG.warn("Invalid header encountered '" + header.toExternalForm() + "' in response " + getStatusLine().toString());
            flag = false;
        } else
        {
            flag = super.shouldCloseConnection(httpconnection);
        }
        return flag;
    }

    @Override
	protected void writeRequestLine(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        int i = httpconnection.getPort();
        if(i == -1)
            i = httpconnection.getProtocol().getDefaultPort();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(getName());
        stringbuffer.append(' ');
        stringbuffer.append(httpconnection.getHost());
        if(i > -1)
        {
            stringbuffer.append(':');
            stringbuffer.append(i);
        }
        stringbuffer.append(" ");
        stringbuffer.append(getEffectiveVersion());
        String s = stringbuffer.toString();
        httpconnection.printLine(s, getParams().getHttpElementCharset());
        if(Wire.HEADER_WIRE.enabled())
            Wire.HEADER_WIRE.output(s);
    }

    private static final Log LOG;
    public static final String NAME = "CONNECT";
    static Class class$org$apache$commons$httpclient$ConnectMethod;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$ConnectMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.ConnectMethod");
            class$org$apache$commons$httpclient$ConnectMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$ConnectMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
