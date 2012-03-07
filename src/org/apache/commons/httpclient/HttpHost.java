// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.LangUtils;

// Referenced classes of package org.apache.commons.httpclient:
//            URIException, URI

public class HttpHost
    implements Cloneable
{

    public HttpHost(String s)
    {
        this(s, -1, Protocol.getProtocol("http"));
    }

    public HttpHost(String s, int i)
    {
        this(s, i, Protocol.getProtocol("http"));
    }

    public HttpHost(String s, int i, Protocol protocol1)
    {
        hostname = null;
        port = -1;
        protocol = null;
        if(s == null)
            throw new IllegalArgumentException("Host name may not be null");
        if(protocol1 == null)
            throw new IllegalArgumentException("Protocol may not be null");
        hostname = s;
        protocol = protocol1;
        if(i >= 0)
            port = i;
        else
            port = protocol.getDefaultPort();
    }

    public HttpHost(HttpHost httphost)
    {
        hostname = null;
        port = -1;
        protocol = null;
        hostname = httphost.hostname;
        port = httphost.port;
        protocol = httphost.protocol;
    }

    public HttpHost(URI uri)
        throws URIException
    {
        this(uri.getHost(), uri.getPort(), Protocol.getProtocol(uri.getScheme()));
    }

    @Override
	public Object clone()
    {
        return new HttpHost(this);
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof HttpHost)
        {
            if(obj == this)
            {
                flag = true;
            } else
            {
                HttpHost httphost = (HttpHost)obj;
                if(!hostname.equalsIgnoreCase(httphost.hostname))
                    flag = false;
                else
                if(port != httphost.port)
                    flag = false;
                else
                if(!protocol.equals(httphost.protocol))
                    flag = false;
                else
                    flag = true;
            }
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String getHostName()
    {
        return hostname;
    }

    public int getPort()
    {
        return port;
    }

    public Protocol getProtocol()
    {
        return protocol;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, hostname), port), protocol);
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(50);
        stringbuffer.append(toURI());
        return stringbuffer.toString();
    }

    public String toURI()
    {
        StringBuffer stringbuffer = new StringBuffer(50);
        if(protocol != null)
        {
            stringbuffer.append(protocol.getScheme());
            stringbuffer.append("://");
        }
        stringbuffer.append(hostname);
        if(port != protocol.getDefaultPort())
        {
            stringbuffer.append(':');
            stringbuffer.append(port);
        }
        return stringbuffer.toString();
    }

    private String hostname;
    private int port;
    private Protocol protocol;
}
