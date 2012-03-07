// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;


// Referenced classes of package org.apache.commons.httpclient.params:
//            DefaultHttpParams

public class HttpConnectionParams extends DefaultHttpParams
{

    public HttpConnectionParams()
    {
    }

    public int getConnectionTimeout()
    {
        return getIntParameter("http.connection.timeout", 0);
    }

    public int getLinger()
    {
        return getIntParameter("http.socket.linger", -1);
    }

    public int getReceiveBufferSize()
    {
        return getIntParameter("http.socket.receivebuffer", -1);
    }

    public int getSendBufferSize()
    {
        return getIntParameter("http.socket.sendbuffer", -1);
    }

    public int getSoTimeout()
    {
        return getIntParameter("http.socket.timeout", 0);
    }

    public boolean getTcpNoDelay()
    {
        return getBooleanParameter("http.tcp.nodelay", true);
    }

    public boolean isStaleCheckingEnabled()
    {
        return getBooleanParameter("http.connection.stalecheck", true);
    }

    public void setConnectionTimeout(int i)
    {
        setIntParameter("http.connection.timeout", i);
    }

    public void setLinger(int i)
    {
        setIntParameter("http.socket.linger", i);
    }

    public void setReceiveBufferSize(int i)
    {
        setIntParameter("http.socket.receivebuffer", i);
    }

    public void setSendBufferSize(int i)
    {
        setIntParameter("http.socket.sendbuffer", i);
    }

    public void setSoTimeout(int i)
    {
        setIntParameter("http.socket.timeout", i);
    }

    public void setStaleCheckingEnabled(boolean flag)
    {
        setBooleanParameter("http.connection.stalecheck", flag);
    }

    public void setTcpNoDelay(boolean flag)
    {
        setBooleanParameter("http.tcp.nodelay", flag);
    }

    public static final String CONNECTION_TIMEOUT = "http.connection.timeout";
    public static final String SO_LINGER = "http.socket.linger";
    public static final String SO_RCVBUF = "http.socket.receivebuffer";
    public static final String SO_SNDBUF = "http.socket.sendbuffer";
    public static final String SO_TIMEOUT = "http.socket.timeout";
    public static final String STALE_CONNECTION_CHECK = "http.connection.stalecheck";
    public static final String TCP_NODELAY = "http.tcp.nodelay";
}
