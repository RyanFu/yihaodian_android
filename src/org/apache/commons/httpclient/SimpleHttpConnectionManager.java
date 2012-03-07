// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpConnectionManager, HttpConnection, HostConfiguration

public class SimpleHttpConnectionManager
    implements HttpConnectionManager
{

    public SimpleHttpConnectionManager()
    {
        params = new HttpConnectionManagerParams();
        idleStartTime = 0x7fffffffffffffffL;
        inUse = false;
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

    static void finishLastResponse(HttpConnection httpconnection)
    {
        InputStream inputstream;
        inputstream = httpconnection.getLastResponseInputStream();
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_18;
        httpconnection.setLastResponseInputStream(null);
        inputstream.close();
_L1:
        return;
        IOException ioexception;
        ioexception;
        httpconnection.close();
          goto _L1
    }

    @Override
	public void closeIdleConnections(long l)
    {
        long l1 = System.currentTimeMillis() - l;
        if(idleStartTime <= l1)
            httpConnection.close();
    }

    @Override
	public HttpConnection getConnection(HostConfiguration hostconfiguration)
    {
        return getConnection(hostconfiguration, 0L);
    }

    @Override
	public HttpConnection getConnection(HostConfiguration hostconfiguration, long l)
    {
        return getConnectionWithTimeout(hostconfiguration, l);
    }

    @Override
	public HttpConnection getConnectionWithTimeout(HostConfiguration hostconfiguration, long l)
    {
        if(httpConnection == null)
        {
            httpConnection = new HttpConnection(hostconfiguration);
            httpConnection.setHttpConnectionManager(this);
            httpConnection.getParams().setDefaults(params);
        } else
        if(!hostconfiguration.hostEquals(httpConnection) || !hostconfiguration.proxyEquals(httpConnection))
        {
            if(httpConnection.isOpen())
                httpConnection.close();
            httpConnection.setHost(hostconfiguration.getHost());
            httpConnection.setPort(hostconfiguration.getPort());
            httpConnection.setProtocol(hostconfiguration.getProtocol());
            httpConnection.setLocalAddress(hostconfiguration.getLocalAddress());
            httpConnection.setProxyHost(hostconfiguration.getProxyHost());
            httpConnection.setProxyPort(hostconfiguration.getProxyPort());
        } else
        {
            finishLastResponse(httpConnection);
        }
        idleStartTime = 0x7fffffffffffffffL;
        if(inUse)
            LOG.warn("SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.");
        inUse = true;
        return httpConnection;
    }

    @Override
	public HttpConnectionManagerParams getParams()
    {
        return params;
    }

    public boolean isConnectionStaleCheckingEnabled()
    {
        return params.isStaleCheckingEnabled();
    }

    @Override
	public void releaseConnection(HttpConnection httpconnection)
    {
        if(httpconnection != httpConnection)
        {
            throw new IllegalStateException("Unexpected release of an unknown connection.");
        } else
        {
            finishLastResponse(httpConnection);
            inUse = false;
            idleStartTime = System.currentTimeMillis();
            return;
        }
    }

    public void setConnectionStaleCheckingEnabled(boolean flag)
    {
        params.setStaleCheckingEnabled(flag);
    }

    @Override
	public void setParams(HttpConnectionManagerParams httpconnectionmanagerparams)
    {
        if(httpconnectionmanagerparams == null)
        {
            throw new IllegalArgumentException("Parameters may not be null");
        } else
        {
            params = httpconnectionmanagerparams;
            return;
        }
    }

    private static final Log LOG;
    private static final String MISUSE_MESSAGE = "SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.";
    static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
    protected HttpConnection httpConnection;
    private long idleStartTime;
    private volatile boolean inUse;
    private HttpConnectionManagerParams params;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
            class$org$apache$commons$httpclient$SimpleHttpConnectionManager = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
        }
        LOG = LogFactory.getLog(class1);
    }
}
