// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.protocol;

import java.io.IOException;
import java.net.*;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.util.TimeoutController;

// Referenced classes of package org.apache.commons.httpclient.protocol:
//            ProtocolSocketFactory

public final class ControllerThreadSocketFactory
{
    public static abstract class SocketTask
        implements Runnable
    {

        public abstract void doit()
            throws IOException;

        protected Socket getSocket()
        {
            return socket;
        }

        @Override
		public void run()
        {
            doit();
_L1:
            return;
            IOException ioexception;
            ioexception;
            exception = ioexception;
              goto _L1
        }

        protected void setSocket(Socket socket1)
        {
            socket = socket1;
        }

        private IOException exception;
        private Socket socket;


        public SocketTask()
        {
        }
    }


    private ControllerThreadSocketFactory()
    {
    }

    public static Socket createSocket(SocketTask sockettask, int i)
        throws IOException, UnknownHostException, ConnectTimeoutException
    {
        long l = i;
        Socket socket;
        try
        {
            TimeoutController.execute(sockettask, l);
        }
        catch(org.apache.commons.httpclient.util.TimeoutController.TimeoutException timeoutexception)
        {
            throw new ConnectTimeoutException("The host did not accept the connection within timeout of " + i + " ms");
        }
        socket = sockettask.getSocket();
        if(sockettask.exception != null)
            throw sockettask.exception;
        else
            return socket;
    }

    public static Socket createSocket(final ProtocolSocketFactory socketfactory, final String host, final int port, final InetAddress localAddress, final int localPort, int i)
        throws IOException, UnknownHostException, ConnectTimeoutException
    {
        _cls1 _lcls1 = new _cls1();
        long l = i;
        Socket socket;
        try
        {
            TimeoutController.execute(_lcls1, l);
        }
        catch(org.apache.commons.httpclient.util.TimeoutController.TimeoutException timeoutexception)
        {
            throw new ConnectTimeoutException("The host did not accept the connection within timeout of " + i + " ms");
        }
        socket = _lcls1.getSocket();
        if(((SocketTask) (_lcls1)).exception != null)
            throw ((SocketTask) (_lcls1)).exception;
        else
            return socket;
    }

    private class _cls1 extends SocketTask
    {

        @Override
		public void doit()
            throws IOException
        {
            setSocket(socketfactory.createSocket(host, port, localAddress, localPort));
        }

        private final String val$host;
        private final InetAddress val$localAddress;
        private final int val$localPort;
        private final int val$port;
        private final ProtocolSocketFactory val$socketfactory;

        _cls1()
        {
            socketfactory = protocolsocketfactory;
            host = s;
            port = i;
            localAddress = inetaddress;
            localPort = j;
        }
    }

}
