// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.*;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;

public interface ProtocolSocketFactory
{

    public abstract Socket createSocket(String s, int i)
        throws IOException, UnknownHostException;

    public abstract Socket createSocket(String s, int i, InetAddress inetaddress, int j)
        throws IOException, UnknownHostException;

    public abstract Socket createSocket(String s, int i, InetAddress inetaddress, int j, HttpConnectionParams httpconnectionparams)
        throws IOException, UnknownHostException, ConnectTimeoutException;
}
