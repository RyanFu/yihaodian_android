// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.net.Socket;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpParams;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpState, HostConfiguration, HttpException, ConnectMethod, 
//            HttpMethodBase, HttpMethodDirector, HttpConnection, HttpConnectionManager

public class ProxyClient
{
    static class DummyConnectionManager
        implements HttpConnectionManager
    {

        @Override
		public void closeIdleConnections(long l)
        {
        }

        public HttpConnection getConnection()
        {
            return httpConnection;
        }

        @Override
		public HttpConnection getConnection(HostConfiguration hostconfiguration)
        {
            return getConnectionWithTimeout(hostconfiguration, -1L);
        }

        @Override
		public HttpConnection getConnection(HostConfiguration hostconfiguration, long l)
            throws HttpException
        {
            return getConnectionWithTimeout(hostconfiguration, l);
        }

        @Override
		public HttpConnection getConnectionWithTimeout(HostConfiguration hostconfiguration, long l)
        {
            httpConnection = new HttpConnection(hostconfiguration);
            httpConnection.setHttpConnectionManager(this);
            httpConnection.getParams().setDefaults(connectionParams);
            return httpConnection;
        }

        @Override
		public HttpConnectionManagerParams getParams()
        {
            return null;
        }

        @Override
		public void releaseConnection(HttpConnection httpconnection)
        {
        }

        public void setConnectionParams(HttpParams httpparams)
        {
            connectionParams = httpparams;
        }

        @Override
		public void setParams(HttpConnectionManagerParams httpconnectionmanagerparams)
        {
        }

        private HttpParams connectionParams;
        private HttpConnection httpConnection;

        DummyConnectionManager()
        {
        }
    }

    public static class ConnectResponse
    {

        private void setConnectMethod(ConnectMethod connectmethod)
        {
            connectMethod = connectmethod;
        }

        private void setSocket(Socket socket1)
        {
            socket = socket1;
        }

        public ConnectMethod getConnectMethod()
        {
            return connectMethod;
        }

        public Socket getSocket()
        {
            return socket;
        }

        private ConnectMethod connectMethod;
        private Socket socket;



        private ConnectResponse()
        {
        }

    }


    public ProxyClient()
    {
        this(new HttpClientParams());
    }

    public ProxyClient(HttpClientParams httpclientparams)
    {
        state = new HttpState();
        params = null;
        hostConfiguration = new HostConfiguration();
        if(httpclientparams == null)
        {
            throw new IllegalArgumentException("Params may not be null");
        } else
        {
            params = httpclientparams;
            return;
        }
    }

    public ConnectResponse connect()
        throws IOException, HttpException
    {
        if(getHostConfiguration().getProxyHost() == null)
            throw new IllegalStateException("proxy host must be configured");
        if(getHostConfiguration().getHost() == null)
            throw new IllegalStateException("destination host must be configured");
        ConnectMethod connectmethod = new ConnectMethod();
        connectmethod.getParams().setDefaults(getParams());
        DummyConnectionManager dummyconnectionmanager = new DummyConnectionManager();
        dummyconnectionmanager.setConnectionParams(getParams());
        (new HttpMethodDirector(dummyconnectionmanager, getHostConfiguration(), getParams(), getState())).executeMethod(connectmethod);
        ConnectResponse connectresponse = new ConnectResponse();
        connectresponse.setConnectMethod(connectmethod);
        if(connectmethod.getStatusCode() == 200)
            connectresponse.setSocket(dummyconnectionmanager.getConnection().getSocket());
        else
            dummyconnectionmanager.getConnection().close();
        return connectresponse;
    }

    /**
     * @deprecated Method getHostConfiguration is deprecated
     */

    @Deprecated
	public HostConfiguration getHostConfiguration()
    {
        this;
        JVM INSTR monitorenter ;
        HostConfiguration hostconfiguration = hostConfiguration;
        this;
        JVM INSTR monitorexit ;
        return hostconfiguration;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getParams is deprecated
     */

    @Deprecated
	public HttpClientParams getParams()
    {
        this;
        JVM INSTR monitorenter ;
        HttpClientParams httpclientparams = params;
        this;
        JVM INSTR monitorexit ;
        return httpclientparams;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getState is deprecated
     */

    @Deprecated
	public HttpState getState()
    {
        this;
        JVM INSTR monitorenter ;
        HttpState httpstate = state;
        this;
        JVM INSTR monitorexit ;
        return httpstate;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHostConfiguration is deprecated
     */

    @Deprecated
	public void setHostConfiguration(HostConfiguration hostconfiguration)
    {
        this;
        JVM INSTR monitorenter ;
        hostConfiguration = hostconfiguration;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setParams is deprecated
     */

    @Deprecated
	public void setParams(HttpClientParams httpclientparams)
    {
        this;
        JVM INSTR monitorenter ;
        if(httpclientparams != null)
            break MISSING_BLOCK_LABEL_21;
        throw new IllegalArgumentException("Parameters may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        params = httpclientparams;
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method setState is deprecated
     */

    @Deprecated
	public void setState(HttpState httpstate)
    {
        this;
        JVM INSTR monitorenter ;
        state = httpstate;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private HostConfiguration hostConfiguration;
    private HttpClientParams params;
    private HttpState state;
}
