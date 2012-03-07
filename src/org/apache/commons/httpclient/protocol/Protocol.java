// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.protocol;

import java.util.*;
import org.apache.commons.httpclient.util.LangUtils;

// Referenced classes of package org.apache.commons.httpclient.protocol:
//            SecureProtocolSocketFactory, DefaultProtocolSocketFactory, SSLProtocolSocketFactory, ProtocolSocketFactory

public class Protocol
{

    public Protocol(String s, ProtocolSocketFactory protocolsocketfactory, int i)
    {
        if(s == null)
            throw new IllegalArgumentException("scheme is null");
        if(protocolsocketfactory == null)
            throw new IllegalArgumentException("socketFactory is null");
        if(i <= 0)
        {
            throw new IllegalArgumentException("port is invalid: " + i);
        } else
        {
            scheme = s;
            socketFactory = protocolsocketfactory;
            defaultPort = i;
            secure = protocolsocketfactory instanceof SecureProtocolSocketFactory;
            return;
        }
    }

    public Protocol(String s, SecureProtocolSocketFactory secureprotocolsocketfactory, int i)
    {
        this(s, ((ProtocolSocketFactory) (secureprotocolsocketfactory)), i);
    }

    public static Protocol getProtocol(String s)
        throws IllegalStateException
    {
        if(s == null)
            throw new IllegalArgumentException("id is null");
        Protocol protocol = (Protocol)PROTOCOLS.get(s);
        if(protocol == null)
            protocol = lazyRegisterProtocol(s);
        return protocol;
    }

    private static Protocol lazyRegisterProtocol(String s)
        throws IllegalStateException
    {
        Protocol protocol1;
        if("http".equals(s))
        {
            Protocol protocol = new Protocol("http", DefaultProtocolSocketFactory.getSocketFactory(), 80);
            registerProtocol("http", protocol);
            protocol1 = protocol;
        } else
        if("https".equals(s))
        {
            Protocol protocol2 = new Protocol("https", SSLProtocolSocketFactory.getSocketFactory(), 443);
            registerProtocol("https", protocol2);
            protocol1 = protocol2;
        } else
        {
            throw new IllegalStateException("unsupported protocol: '" + s + "'");
        }
        return protocol1;
    }

    public static void registerProtocol(String s, Protocol protocol)
    {
        if(s == null)
            throw new IllegalArgumentException("id is null");
        if(protocol == null)
        {
            throw new IllegalArgumentException("protocol is null");
        } else
        {
            PROTOCOLS.put(s, protocol);
            return;
        }
    }

    public static void unregisterProtocol(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("id is null");
        } else
        {
            PROTOCOLS.remove(s);
            return;
        }
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof Protocol)
        {
            Protocol protocol = (Protocol)obj;
            if(defaultPort == protocol.getDefaultPort() && scheme.equalsIgnoreCase(protocol.getScheme()) && secure == protocol.isSecure() && socketFactory.equals(protocol.getSocketFactory()))
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getDefaultPort()
    {
        return defaultPort;
    }

    public String getScheme()
    {
        return scheme;
    }

    public ProtocolSocketFactory getSocketFactory()
    {
        return socketFactory;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, defaultPort), scheme.toLowerCase()), secure), socketFactory);
    }

    public boolean isSecure()
    {
        return secure;
    }

    public int resolvePort(int i)
    {
        int j;
        if(i <= 0)
            j = getDefaultPort();
        else
            j = i;
        return j;
    }

    @Override
	public String toString()
    {
        return scheme + ":" + defaultPort;
    }

    private static final Map PROTOCOLS = Collections.synchronizedMap(new HashMap());
    private int defaultPort;
    private String scheme;
    private boolean secure;
    private ProtocolSocketFactory socketFactory;

}
