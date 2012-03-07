// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.net.InetAddress;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HostParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpHost, ProxyHost, HttpConnection, URIException, 
//            URI

public class HostConfiguration
    implements Cloneable
{

    public HostConfiguration()
    {
        host = null;
        proxyHost = null;
        localAddress = null;
        params = new HostParams();
    }

    public HostConfiguration(HostConfiguration hostconfiguration)
    {
        host = null;
        proxyHost = null;
        localAddress = null;
        params = new HostParams();
        hostconfiguration;
        JVM INSTR monitorenter ;
        if(hostconfiguration.host == null) goto _L2; else goto _L1
_L1:
        host = (HttpHost)hostconfiguration.host.clone();
_L3:
        if(hostconfiguration.proxyHost == null)
            break MISSING_BLOCK_LABEL_123;
        proxyHost = (ProxyHost)hostconfiguration.proxyHost.clone();
_L4:
        localAddress = hostconfiguration.getLocalAddress();
        params = (HostParams)hostconfiguration.getParams().clone();
        hostconfiguration;
        JVM INSTR monitorexit ;
        return;
_L2:
        host = null;
          goto _L3
        CloneNotSupportedException clonenotsupportedexception;
        clonenotsupportedexception;
        throw new IllegalArgumentException("Host configuration could not be cloned");
        Exception exception;
        exception;
        hostconfiguration;
        JVM INSTR monitorexit ;
        throw exception;
        proxyHost = null;
          goto _L4
    }

    public Object clone()
    {
        return new HostConfiguration(this);
    }

    /**
     * @deprecated Method equals is deprecated
     */

    public boolean equals(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = obj instanceof HostConfiguration;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(obj != this) goto _L4; else goto _L3
_L3:
        boolean flag1 = true;
_L6:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L4:
        boolean flag2;
        HostConfiguration hostconfiguration = (HostConfiguration)obj;
        if(!LangUtils.equals(host, hostconfiguration.host) || !LangUtils.equals(proxyHost, hostconfiguration.proxyHost))
            break MISSING_BLOCK_LABEL_85;
        flag2 = LangUtils.equals(localAddress, hostconfiguration.localAddress);
        if(flag2)
        {
            flag1 = true;
            continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        continue; /* Loop/switch isn't completed */
_L2:
        flag1 = false;
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getHost is deprecated
     */

    public String getHost()
    {
        this;
        JVM INSTR monitorenter ;
        if(host == null) goto _L2; else goto _L1
_L1:
        String s1 = host.getHostName();
        String s = s1;
_L4:
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getHostURL is deprecated
     */

    public String getHostURL()
    {
        this;
        JVM INSTR monitorenter ;
        if(host == null)
            throw new IllegalStateException("Host must be set to create a host URL");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        String s = host.toURI();
        this;
        JVM INSTR monitorexit ;
        return s;
    }

    /**
     * @deprecated Method getLocalAddress is deprecated
     */

    public InetAddress getLocalAddress()
    {
        this;
        JVM INSTR monitorenter ;
        InetAddress inetaddress = localAddress;
        this;
        JVM INSTR monitorexit ;
        return inetaddress;
        Exception exception;
        exception;
        throw exception;
    }

    public HostParams getParams()
    {
        return params;
    }

    /**
     * @deprecated Method getPort is deprecated
     */

    public int getPort()
    {
        this;
        JVM INSTR monitorenter ;
        if(host == null) goto _L2; else goto _L1
_L1:
        int j = host.getPort();
        int i = j;
_L4:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        i = -1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getProtocol is deprecated
     */

    public Protocol getProtocol()
    {
        this;
        JVM INSTR monitorenter ;
        if(host == null) goto _L2; else goto _L1
_L1:
        Protocol protocol1 = host.getProtocol();
        Protocol protocol = protocol1;
_L4:
        this;
        JVM INSTR monitorexit ;
        return protocol;
_L2:
        protocol = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getProxyHost is deprecated
     */

    public String getProxyHost()
    {
        this;
        JVM INSTR monitorenter ;
        if(proxyHost == null) goto _L2; else goto _L1
_L1:
        String s1 = proxyHost.getHostName();
        String s = s1;
_L4:
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getProxyPort is deprecated
     */

    public int getProxyPort()
    {
        this;
        JVM INSTR monitorenter ;
        if(proxyHost == null) goto _L2; else goto _L1
_L1:
        int j = proxyHost.getPort();
        int i = j;
_L4:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        i = -1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getVirtualHost is deprecated
     */

    public String getVirtualHost()
    {
        this;
        JVM INSTR monitorenter ;
        String s = params.getVirtualHost();
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method hashCode is deprecated
     */

    public int hashCode()
    {
        this;
        JVM INSTR monitorenter ;
        int i = LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, host), proxyHost), localAddress);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method hostEquals is deprecated
     */

    public boolean hostEquals(HttpConnection httpconnection)
    {
        this;
        JVM INSTR monitorenter ;
        if(httpconnection != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("Connection may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(host == null) goto _L2; else goto _L1
_L1:
        boolean flag1 = host.getHostName().equalsIgnoreCase(httpconnection.getHost());
        if(flag1) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L6:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        InetAddress inetaddress;
        if(host.getPort() != httpconnection.getPort())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(!host.getProtocol().equals(httpconnection.getProtocol()))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(localAddress != null)
        {
            if(!localAddress.equals(httpconnection.getLocalAddress()))
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_138;
        }
        inetaddress = httpconnection.getLocalAddress();
        if(inetaddress != null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        flag = true;
        continue; /* Loop/switch isn't completed */
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    /**
     * @deprecated Method isHostSet is deprecated
     */

    public boolean isHostSet()
    {
        this;
        JVM INSTR monitorenter ;
        HttpHost httphost = host;
        boolean flag;
        if(httphost != null)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method isProxySet is deprecated
     */

    public boolean isProxySet()
    {
        this;
        JVM INSTR monitorenter ;
        ProxyHost proxyhost = proxyHost;
        boolean flag;
        if(proxyhost != null)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method proxyEquals is deprecated
     */

    public boolean proxyEquals(HttpConnection httpconnection)
    {
        this;
        JVM INSTR monitorenter ;
        if(httpconnection != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("Connection may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(proxyHost == null) goto _L2; else goto _L1
_L1:
        if(!proxyHost.getHostName().equalsIgnoreCase(httpconnection.getProxyHost())) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        i = proxyHost.getPort();
        j = httpconnection.getProxyPort();
        if(i != j) goto _L4; else goto _L5
_L5:
        boolean flag = true;
_L7:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L4:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L2:
        String s = httpconnection.getProxyHost();
        if(s == null)
            flag = true;
        else
            flag = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Protocol protocol = Protocol.getProtocol("http");
        setHost(s, protocol.getDefaultPort(), protocol);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(String s, int i)
    {
        this;
        JVM INSTR monitorenter ;
        setHost(s, i, Protocol.getProtocol("http"));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(String s, int i, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        host = new HttpHost(s, i, Protocol.getProtocol(s1));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(String s, int i, Protocol protocol)
    {
        this;
        JVM INSTR monitorenter ;
        if(s != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("host must not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(protocol != null)
            break MISSING_BLOCK_LABEL_37;
        throw new IllegalArgumentException("protocol must not be null");
        host = new HttpHost(s, i, protocol);
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(String s, String s1, int i, Protocol protocol)
    {
        this;
        JVM INSTR monitorenter ;
        setHost(s, i, protocol);
        params.setVirtualHost(s1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(HttpHost httphost)
    {
        this;
        JVM INSTR monitorenter ;
        host = httphost;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setHost is deprecated
     */

    public void setHost(URI uri)
    {
        this;
        JVM INSTR monitorenter ;
        setHost(uri.getHost(), uri.getPort(), uri.getScheme());
        this;
        JVM INSTR monitorexit ;
        return;
        URIException uriexception;
        uriexception;
        throw new IllegalArgumentException(uriexception.toString());
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    /**
     * @deprecated Method setLocalAddress is deprecated
     */

    public void setLocalAddress(InetAddress inetaddress)
    {
        this;
        JVM INSTR monitorenter ;
        localAddress = inetaddress;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setParams(HostParams hostparams)
    {
        if(hostparams == null)
        {
            throw new IllegalArgumentException("Parameters may not be null");
        } else
        {
            params = hostparams;
            return;
        }
    }

    /**
     * @deprecated Method setProxy is deprecated
     */

    public void setProxy(String s, int i)
    {
        this;
        JVM INSTR monitorenter ;
        proxyHost = new ProxyHost(s, i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setProxyHost is deprecated
     */

    public void setProxyHost(ProxyHost proxyhost)
    {
        this;
        JVM INSTR monitorenter ;
        proxyHost = proxyhost;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method toString is deprecated
     */

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = false;
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer(50);
        stringbuffer.append("HostConfiguration[");
        if(host != null)
        {
            flag = true;
            stringbuffer.append("host=").append(host);
        }
        if(proxyHost == null) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        stringbuffer.append(", ");
_L9:
        stringbuffer.append("proxyHost=").append(proxyHost);
_L2:
        if(localAddress == null) goto _L6; else goto _L5
_L5:
        if(!flag) goto _L8; else goto _L7
_L7:
        stringbuffer.append(", ");
_L10:
        stringbuffer.append("localAddress=").append(localAddress);
        if(flag)
            stringbuffer.append(", ");
        stringbuffer.append("params=").append(params);
_L6:
        String s;
        stringbuffer.append("]");
        s = stringbuffer.toString();
        this;
        JVM INSTR monitorexit ;
        return s;
_L4:
        flag = true;
          goto _L9
_L8:
        flag = true;
          goto _L10
        Exception exception;
        exception;
        throw exception;
          goto _L9
    }

    public static final HostConfiguration ANY_HOST_CONFIGURATION = new HostConfiguration();
    private HttpHost host;
    private InetAddress localAddress;
    private HostParams params;
    private ProxyHost proxyHost;

}
