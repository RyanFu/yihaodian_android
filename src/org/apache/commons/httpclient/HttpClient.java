// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpState, HostConfiguration, HttpConnectionManager, SimpleHttpConnectionManager, 
//            HttpException, HttpMethod, URI, HttpMethodDirector

public class HttpClient
{

    public HttpClient()
    {
        this(new HttpClientParams());
    }

    public HttpClient(HttpConnectionManager httpconnectionmanager)
    {
        this(new HttpClientParams(), httpconnectionmanager);
    }

    public HttpClient(HttpClientParams httpclientparams)
    {
        state = new HttpState();
        params = null;
        hostConfiguration = new HostConfiguration();
        if(httpclientparams == null)
            throw new IllegalArgumentException("Params may not be null");
        params = httpclientparams;
        httpConnectionManager = null;
        Class class1 = httpclientparams.getConnectionManagerClass();
        if(class1 != null)
            try
            {
                httpConnectionManager = (HttpConnectionManager)class1.newInstance();
            }
            catch(Exception exception)
            {
                LOG.warn("Error instantiating connection manager class, defaulting to SimpleHttpConnectionManager", exception);
            }
        if(httpConnectionManager == null)
            httpConnectionManager = new SimpleHttpConnectionManager();
        if(httpConnectionManager != null)
            httpConnectionManager.getParams().setDefaults(params);
    }

    public HttpClient(HttpClientParams httpclientparams, HttpConnectionManager httpconnectionmanager)
    {
        state = new HttpState();
        params = null;
        hostConfiguration = new HostConfiguration();
        if(httpconnectionmanager == null)
            throw new IllegalArgumentException("httpConnectionManager cannot be null");
        if(httpclientparams == null)
            throw new IllegalArgumentException("Params may not be null");
        params = httpclientparams;
        httpConnectionManager = httpconnectionmanager;
        if(httpConnectionManager != null)
            httpConnectionManager.getParams().setDefaults(params);
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

    public int executeMethod(HostConfiguration hostconfiguration, HttpMethod httpmethod)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod)");
        return executeMethod(hostconfiguration, httpmethod, null);
    }

    public int executeMethod(HostConfiguration hostconfiguration, HttpMethod httpmethod, HttpState httpstate)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod,HttpState)");
        if(httpmethod == null)
            throw new IllegalArgumentException("HttpMethod parameter may not be null");
        HostConfiguration hostconfiguration1 = getHostConfiguration();
        if(hostconfiguration == null)
            hostconfiguration = hostconfiguration1;
        URI uri = httpmethod.getURI();
        if(hostconfiguration == hostconfiguration1 || uri.isAbsoluteURI())
        {
            HostConfiguration hostconfiguration2 = new HostConfiguration(hostconfiguration);
            if(uri.isAbsoluteURI())
                hostconfiguration2.setHost(uri);
            hostconfiguration = hostconfiguration2;
        }
        HttpConnectionManager httpconnectionmanager = getHttpConnectionManager();
        HttpClientParams httpclientparams = params;
        HttpState httpstate1;
        if(httpstate == null)
            httpstate1 = getState();
        else
            httpstate1 = httpstate;
        (new HttpMethodDirector(httpconnectionmanager, hostconfiguration, httpclientparams, httpstate1)).executeMethod(httpmethod);
        return httpmethod.getStatusCode();
    }

    public int executeMethod(HttpMethod httpmethod)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpClient.executeMethod(HttpMethod)");
        return executeMethod(null, httpmethod, null);
    }

    public String getHost()
    {
        return hostConfiguration.getHost();
    }

    /**
     * @deprecated Method getHostConfiguration is deprecated
     */

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
     * @deprecated Method getHttpConnectionManager is deprecated
     */

    public HttpConnectionManager getHttpConnectionManager()
    {
        this;
        JVM INSTR monitorenter ;
        HttpConnectionManager httpconnectionmanager = httpConnectionManager;
        this;
        JVM INSTR monitorexit ;
        return httpconnectionmanager;
        Exception exception;
        exception;
        throw exception;
    }

    public HttpClientParams getParams()
    {
        return params;
    }

    public int getPort()
    {
        return hostConfiguration.getPort();
    }

    /**
     * @deprecated Method getState is deprecated
     */

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
     * @deprecated Method isStrictMode is deprecated
     */

    public boolean isStrictMode()
    {
        this;
        JVM INSTR monitorenter ;
        return false;
    }

    /**
     * @deprecated Method setConnectionTimeout is deprecated
     */

    public void setConnectionTimeout(int i)
    {
        this;
        JVM INSTR monitorenter ;
        httpConnectionManager.getParams().setConnectionTimeout(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHostConfiguration is deprecated
     */

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
     * @deprecated Method setHttpConnectionFactoryTimeout is deprecated
     */

    public void setHttpConnectionFactoryTimeout(long l)
    {
        this;
        JVM INSTR monitorenter ;
        params.setConnectionManagerTimeout(l);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHttpConnectionManager is deprecated
     */

    public void setHttpConnectionManager(HttpConnectionManager httpconnectionmanager)
    {
        this;
        JVM INSTR monitorenter ;
        httpConnectionManager = httpconnectionmanager;
        if(httpConnectionManager != null)
            httpConnectionManager.getParams().setDefaults(params);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setParams(HttpClientParams httpclientparams)
    {
        if(httpclientparams == null)
        {
            throw new IllegalArgumentException("Parameters may not be null");
        } else
        {
            params = httpclientparams;
            return;
        }
    }

    /**
     * @deprecated Method setState is deprecated
     */

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

    /**
     * @deprecated Method setStrictMode is deprecated
     */

    public void setStrictMode(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_16;
        params.makeStrict();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        params.makeLenient();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setTimeout is deprecated
     */

    public void setTimeout(int i)
    {
        this;
        JVM INSTR monitorenter ;
        params.setSoTimeout(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpClient;
    private HostConfiguration hostConfiguration;
    private HttpConnectionManager httpConnectionManager;
    private HttpClientParams params;
    private HttpState state;

    static 
    {
        Provider aprovider[];
        int i;
        Class class1;
        int j;
        if(class$org$apache$commons$httpclient$HttpClient == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpClient");
            class$org$apache$commons$httpclient$HttpClient = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpClient;
        }
        LOG = LogFactory.getLog(class1);
        if(!LOG.isDebugEnabled()) goto _L2; else goto _L1
_L1:
        LOG.debug("Java version: " + System.getProperty("java.version"));
        LOG.debug("Java vendor: " + System.getProperty("java.vendor"));
        LOG.debug("Java class path: " + System.getProperty("java.class.path"));
        LOG.debug("Operating system name: " + System.getProperty("os.name"));
        LOG.debug("Operating system architecture: " + System.getProperty("os.arch"));
        LOG.debug("Operating system version: " + System.getProperty("os.version"));
        aprovider = Security.getProviders();
        i = 0;
_L4:
        j = aprovider.length;
        if(i < j) goto _L3; else goto _L2
_L2:
        return;
_L3:
        Provider provider = aprovider[i];
        LOG.debug(provider.getName() + " " + provider.getVersion() + ": " + provider.getInfo());
        i++;
          goto _L4
        SecurityException securityexception;
        securityexception;
          goto _L2
    }
}
