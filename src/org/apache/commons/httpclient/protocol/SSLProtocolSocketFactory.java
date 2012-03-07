// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.*;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;

// Referenced classes of package org.apache.commons.httpclient.protocol:
//            SecureProtocolSocketFactory, ReflectionSocketFactory, ControllerThreadSocketFactory

public class SSLProtocolSocketFactory
    implements SecureProtocolSocketFactory
{

    public SSLProtocolSocketFactory()
    {
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

    static SSLProtocolSocketFactory getSocketFactory()
    {
        return factory;
    }

    @Override
	public Socket createSocket(String s, int i)
        throws IOException, UnknownHostException
    {
        return SSLSocketFactory.getDefault().createSocket(s, i);
    }

    @Override
	public Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException
    {
        return SSLSocketFactory.getDefault().createSocket(s, i, inetaddress, j);
    }

    @Override
	public Socket createSocket(String s, int i, InetAddress inetaddress, int j, HttpConnectionParams httpconnectionparams)
        throws IOException, UnknownHostException, ConnectTimeoutException
    {
        if(httpconnectionparams == null)
            throw new IllegalArgumentException("Parameters may not be null");
        int k = httpconnectionparams.getConnectionTimeout();
        Socket socket1;
        if(k == 0)
        {
            socket1 = createSocket(s, i, inetaddress, j);
        } else
        {
            Socket socket = ReflectionSocketFactory.createSocket("javax.net.ssl.SSLSocketFactory", s, i, inetaddress, j, k);
            if(socket == null)
                socket = ControllerThreadSocketFactory.createSocket(this, s, i, inetaddress, j, k);
            socket1 = socket;
        }
        return socket1;
    }

    @Override
	public Socket createSocket(Socket socket, String s, int i, boolean flag)
        throws IOException, UnknownHostException
    {
        return ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(socket, s, i, flag);
    }

    @Override
	public boolean equals(Object obj)
    {
        if(obj == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class1 = obj.getClass();
        Class class2;
        if(class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory == null)
        {
            class2 = _mthclass$("org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory");
            class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory = class2;
        } else
        {
            class2 = class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
        }
        if(!class1.equals(class2)) goto _L2; else goto _L3
_L3:
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public int hashCode()
    {
        Class class1;
        if(class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory");
            class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
        }
        return class1.hashCode();
    }

    static Class class$org$apache$commons$httpclient$protocol$SSLProtocolSocketFactory;
    private static final SSLProtocolSocketFactory factory = new SSLProtocolSocketFactory();

}
