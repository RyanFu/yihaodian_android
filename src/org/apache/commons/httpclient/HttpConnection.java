// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HostConfiguration, Wire, WireLogOutputStream, HttpParser, 
//            HttpConnectionManager

public class HttpConnection
{

    public HttpConnection(String s, int i)
    {
        this(null, -1, s, null, i, Protocol.getProtocol("http"));
    }

    public HttpConnection(String s, int i, String s1, int j)
    {
        this(s, i, s1, null, j, Protocol.getProtocol("http"));
    }

    public HttpConnection(String s, int i, String s1, int j, Protocol protocol)
    {
        hostName = null;
        portNumber = -1;
        proxyHostName = null;
        proxyPortNumber = -1;
        socket = null;
        inputStream = null;
        outputStream = null;
        lastResponseInputStream = null;
        isOpen = false;
        params = new HttpConnectionParams();
        locked = false;
        usingSecureSocket = false;
        tunnelEstablished = false;
        if(s1 == null)
            throw new IllegalArgumentException("host parameter is null");
        if(protocol == null)
        {
            throw new IllegalArgumentException("protocol is null");
        } else
        {
            proxyHostName = s;
            proxyPortNumber = i;
            hostName = s1;
            portNumber = protocol.resolvePort(j);
            protocolInUse = protocol;
            return;
        }
    }

    public HttpConnection(String s, int i, String s1, String s2, int j, Protocol protocol)
    {
        this(s, i, s1, j, protocol);
    }

    public HttpConnection(String s, int i, Protocol protocol)
    {
        this(null, -1, s, null, i, protocol);
    }

    public HttpConnection(String s, String s1, int i, Protocol protocol)
    {
        this(null, -1, s, s1, i, protocol);
    }

    public HttpConnection(HostConfiguration hostconfiguration)
    {
        this(hostconfiguration.getProxyHost(), hostconfiguration.getProxyPort(), hostconfiguration.getHost(), hostconfiguration.getPort(), hostconfiguration.getProtocol());
        localAddress = hostconfiguration.getLocalAddress();
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

    protected void assertNotOpen()
        throws IllegalStateException
    {
        if(isOpen)
            throw new IllegalStateException("Connection is open");
        else
            return;
    }

    protected void assertOpen()
        throws IllegalStateException
    {
        if(!isOpen)
            throw new IllegalStateException("Connection is not open");
        else
            return;
    }

    public void close()
    {
        LOG.trace("enter HttpConnection.close()");
        closeSocketAndStreams();
    }

    public boolean closeIfStale()
        throws IOException
    {
        boolean flag;
        if(isOpen && isStale())
        {
            LOG.debug("Connection is stale, closing...");
            close();
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    protected void closeSocketAndStreams()
    {
        LOG.trace("enter HttpConnection.closeSockedAndStreams()");
        isOpen = false;
        lastResponseInputStream = null;
        if(outputStream != null)
        {
            OutputStream outputstream = outputStream;
            outputStream = null;
            Socket socket1;
            InputStream inputstream;
            try
            {
                outputstream.close();
            }
            catch(Exception exception2)
            {
                LOG.debug("Exception caught when closing output", exception2);
            }
        }
        if(inputStream != null)
        {
            inputstream = inputStream;
            inputStream = null;
            try
            {
                inputstream.close();
            }
            catch(Exception exception1)
            {
                LOG.debug("Exception caught when closing input", exception1);
            }
        }
        if(socket != null)
        {
            socket1 = socket;
            socket = null;
            try
            {
                socket1.close();
            }
            catch(Exception exception)
            {
                LOG.debug("Exception caught when closing socket", exception);
            }
        }
        tunnelEstablished = false;
        usingSecureSocket = false;
    }

    public void flushRequestOutputStream()
        throws IOException
    {
        LOG.trace("enter HttpConnection.flushRequestOutputStream()");
        assertOpen();
        outputStream.flush();
    }

    public String getHost()
    {
        return hostName;
    }

    public HttpConnectionManager getHttpConnectionManager()
    {
        return httpConnectionManager;
    }

    public InputStream getLastResponseInputStream()
    {
        return lastResponseInputStream;
    }

    public InetAddress getLocalAddress()
    {
        return localAddress;
    }

    public HttpConnectionParams getParams()
    {
        return params;
    }

    public int getPort()
    {
        int i;
        if(portNumber < 0)
        {
            if(isSecure())
                i = 443;
            else
                i = 80;
        } else
        {
            i = portNumber;
        }
        return i;
    }

    public Protocol getProtocol()
    {
        return protocolInUse;
    }

    public String getProxyHost()
    {
        return proxyHostName;
    }

    public int getProxyPort()
    {
        return proxyPortNumber;
    }

    public OutputStream getRequestOutputStream()
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.getRequestOutputStream()");
        assertOpen();
        Object obj = outputStream;
        if(Wire.CONTENT_WIRE.enabled())
            obj = new WireLogOutputStream(((OutputStream) (obj)), Wire.CONTENT_WIRE);
        return ((OutputStream) (obj));
    }

    public InputStream getResponseInputStream()
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.getResponseInputStream()");
        assertOpen();
        return inputStream;
    }

    public int getSendBufferSize()
        throws SocketException
    {
        int i;
        if(socket == null)
            i = -1;
        else
            i = socket.getSendBufferSize();
        return i;
    }

    public int getSoTimeout()
        throws SocketException
    {
        return params.getSoTimeout();
    }

    protected Socket getSocket()
    {
        return socket;
    }

    public String getVirtualHost()
    {
        return hostName;
    }

    protected boolean isLocked()
    {
        return locked;
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public boolean isProxied()
    {
        boolean flag;
        if(proxyHostName != null && proxyPortNumber > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isResponseAvailable()
        throws IOException
    {
        LOG.trace("enter HttpConnection.isResponseAvailable()");
        boolean flag;
        if(isOpen)
        {
            if(inputStream.available() > 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean isResponseAvailable(int i)
        throws IOException
    {
        boolean flag;
        LOG.trace("enter HttpConnection.isResponseAvailable(int)");
        assertOpen();
        flag = false;
        if(inputStream.available() <= 0) goto _L2; else goto _L1
_L1:
        flag = true;
_L5:
        return flag;
_L2:
        socket.setSoTimeout(i);
        inputStream.mark(1);
        if(inputStream.read() == -1) goto _L4; else goto _L3
_L3:
        inputStream.reset();
        LOG.debug("Input data available");
        flag = true;
_L6:
        socket.setSoTimeout(params.getSoTimeout());
          goto _L5
        IOException ioexception2;
        ioexception2;
        LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", ioexception2);
_L7:
        flag = false;
          goto _L5
_L4:
        LOG.debug("Input data not available");
          goto _L6
        InterruptedIOException interruptedioexception;
        interruptedioexception;
        if(!ExceptionUtil.isSocketTimeoutException(interruptedioexception))
            throw interruptedioexception;
        break MISSING_BLOCK_LABEL_160;
        Exception exception;
        exception;
        IOException ioexception1;
        try
        {
            socket.setSoTimeout(params.getSoTimeout());
        }
        catch(IOException ioexception)
        {
            LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", ioexception);
        }
        throw exception;
        if(LOG.isDebugEnabled())
            LOG.debug("Input data not available after " + i + " ms");
        socket.setSoTimeout(params.getSoTimeout());
          goto _L5
        ioexception1;
        LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", ioexception1);
          goto _L7
    }

    public boolean isSecure()
    {
        return protocolInUse.isSecure();
    }

    protected boolean isStale()
        throws IOException
    {
        boolean flag = true;
        if(!isOpen) goto _L2; else goto _L1
_L1:
        flag = false;
        int i = inputStream.available();
        if(i > 0) goto _L2; else goto _L3
_L3:
        int j;
        socket.setSoTimeout(1);
        inputStream.mark(1);
        j = inputStream.read();
        if(j != -1)
            break MISSING_BLOCK_LABEL_75;
        flag = true;
_L4:
        Exception exception;
        try
        {
            socket.setSoTimeout(params.getSoTimeout());
        }
        catch(InterruptedIOException interruptedioexception)
        {
            if(!ExceptionUtil.isSocketTimeoutException(interruptedioexception))
                throw interruptedioexception;
        }
        catch(IOException ioexception)
        {
            LOG.debug("An error occurred while reading from the socket, is appears to be stale", ioexception);
            flag = true;
        }
_L2:
        return flag;
        inputStream.reset();
          goto _L4
        exception;
        socket.setSoTimeout(params.getSoTimeout());
        throw exception;
    }

    public boolean isStaleCheckingEnabled()
    {
        return params.isStaleCheckingEnabled();
    }

    public boolean isTransparent()
    {
        boolean flag;
        if(!isProxied() || tunnelEstablished)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void open()
        throws IOException
    {
        int i1;
        int j1;
        boolean flag;
        boolean flag1;
        ProtocolSocketFactory protocolsocketfactory1;
        LOG.trace("enter HttpConnection.open()");
        String s;
        int i;
        int j;
        int k;
        int l;
        Socket socket1;
        if(proxyHostName == null)
            s = hostName;
        else
            s = proxyHostName;
        if(proxyHostName == null)
            i = portNumber;
        else
            i = proxyPortNumber;
        assertNotOpen();
        if(LOG.isDebugEnabled())
            LOG.debug("Open connection to " + s + ":" + i);
        if(socket != null) goto _L2; else goto _L1
_L1:
        if(!isSecure() || isProxied()) goto _L4; else goto _L3
_L3:
        flag1 = true;
_L11:
        usingSecureSocket = flag1;
        if(!isSecure() || !isProxied()) goto _L6; else goto _L5
_L5:
        protocolsocketfactory1 = Protocol.getProtocol("http").getSocketFactory();
_L12:
        socket = protocolsocketfactory1.createSocket(s, i, localAddress, 0, params);
_L2:
        socket.setTcpNoDelay(params.getTcpNoDelay());
        socket.setSoTimeout(params.getSoTimeout());
        j = params.getLinger();
        if(j < 0) goto _L8; else goto _L7
_L7:
        socket1 = socket;
        if(j <= 0) goto _L10; else goto _L9
_L9:
        flag = true;
_L13:
        socket1.setSoLinger(flag, j);
_L8:
        k = params.getSendBufferSize();
        if(k >= 0)
            socket.setSendBufferSize(k);
        l = params.getReceiveBufferSize();
        if(l >= 0)
            socket.setReceiveBufferSize(l);
        i1 = socket.getSendBufferSize();
        IOException ioexception;
        ProtocolSocketFactory protocolsocketfactory;
        if(i1 > 2048 || i1 <= 0)
            i1 = 2048;
        j1 = socket.getReceiveBufferSize();
        if(j1 > 2048 || j1 <= 0)
            j1 = 2048;
        inputStream = new BufferedInputStream(socket.getInputStream(), j1);
        outputStream = new BufferedOutputStream(socket.getOutputStream(), i1);
        isOpen = true;
        return;
_L4:
        flag1 = false;
          goto _L11
_L6:
        protocolsocketfactory = protocolInUse.getSocketFactory();
        protocolsocketfactory1 = protocolsocketfactory;
          goto _L12
_L10:
        flag = false;
          goto _L13
        ioexception;
        closeSocketAndStreams();
        throw ioexception;
          goto _L11
    }

    public void print(String s)
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.print(String)");
        write(EncodingUtil.getBytes(s, "ISO-8859-1"));
    }

    public void print(String s, String s1)
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.print(String)");
        write(EncodingUtil.getBytes(s, s1));
    }

    public void printLine()
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.printLine()");
        writeLine();
    }

    public void printLine(String s)
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.printLine(String)");
        writeLine(EncodingUtil.getBytes(s, "ISO-8859-1"));
    }

    public void printLine(String s, String s1)
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.printLine(String)");
        writeLine(EncodingUtil.getBytes(s, s1));
    }

    public String readLine()
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.readLine()");
        assertOpen();
        return HttpParser.readLine(inputStream);
    }

    public String readLine(String s)
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.readLine()");
        assertOpen();
        return HttpParser.readLine(inputStream, s);
    }

    public void releaseConnection()
    {
        LOG.trace("enter HttpConnection.releaseConnection()");
        if(locked)
            LOG.debug("Connection is locked.  Call to releaseConnection() ignored.");
        else
        if(httpConnectionManager != null)
        {
            LOG.debug("Releasing connection back to connection manager.");
            httpConnectionManager.releaseConnection(this);
        } else
        {
            LOG.warn("HttpConnectionManager is null.  Connection cannot be released.");
        }
    }

    public void setConnectionTimeout(int i)
    {
        params.setConnectionTimeout(i);
    }

    public void setHost(String s)
        throws IllegalStateException
    {
        if(s == null)
        {
            throw new IllegalArgumentException("host parameter is null");
        } else
        {
            assertNotOpen();
            hostName = s;
            return;
        }
    }

    public void setHttpConnectionManager(HttpConnectionManager httpconnectionmanager)
    {
        httpConnectionManager = httpconnectionmanager;
    }

    public void setLastResponseInputStream(InputStream inputstream)
    {
        lastResponseInputStream = inputstream;
    }

    public void setLocalAddress(InetAddress inetaddress)
    {
        assertNotOpen();
        localAddress = inetaddress;
    }

    protected void setLocked(boolean flag)
    {
        locked = flag;
    }

    public void setParams(HttpConnectionParams httpconnectionparams)
    {
        if(httpconnectionparams == null)
        {
            throw new IllegalArgumentException("Parameters may not be null");
        } else
        {
            params = httpconnectionparams;
            return;
        }
    }

    public void setPort(int i)
        throws IllegalStateException
    {
        assertNotOpen();
        portNumber = i;
    }

    public void setProtocol(Protocol protocol)
    {
        assertNotOpen();
        if(protocol == null)
        {
            throw new IllegalArgumentException("protocol is null");
        } else
        {
            protocolInUse = protocol;
            return;
        }
    }

    public void setProxyHost(String s)
        throws IllegalStateException
    {
        assertNotOpen();
        proxyHostName = s;
    }

    public void setProxyPort(int i)
        throws IllegalStateException
    {
        assertNotOpen();
        proxyPortNumber = i;
    }

    public void setSendBufferSize(int i)
        throws SocketException
    {
        params.setSendBufferSize(i);
    }

    public void setSoTimeout(int i)
        throws SocketException, IllegalStateException
    {
        params.setSoTimeout(i);
        if(socket != null)
            socket.setSoTimeout(i);
    }

    public void setSocketTimeout(int i)
        throws SocketException, IllegalStateException
    {
        assertOpen();
        if(socket != null)
            socket.setSoTimeout(i);
    }

    public void setStaleCheckingEnabled(boolean flag)
    {
        params.setStaleCheckingEnabled(flag);
    }

    public void setVirtualHost(String s)
        throws IllegalStateException
    {
        assertNotOpen();
    }

    public void shutdownOutput()
    {
        LOG.trace("enter HttpConnection.shutdownOutput()");
        Class aclass[] = new Class[0];
        Method method = socket.getClass().getMethod("shutdownOutput", aclass);
        Object aobj[] = new Object[0];
        method.invoke(socket, aobj);
_L1:
        return;
        Exception exception;
        exception;
        LOG.debug("Unexpected Exception caught", exception);
          goto _L1
    }

    public void tunnelCreated()
        throws IllegalStateException, IOException
    {
        LOG.trace("enter HttpConnection.tunnelCreated()");
        if(!isSecure() || !isProxied())
            throw new IllegalStateException("Connection must be secure and proxied to use this feature");
        if(usingSecureSocket)
            throw new IllegalStateException("Already using a secure socket");
        if(LOG.isDebugEnabled())
            LOG.debug("Secure tunnel to " + hostName + ":" + portNumber);
        socket = ((SecureProtocolSocketFactory)protocolInUse.getSocketFactory()).createSocket(socket, hostName, portNumber, true);
        int i = params.getSendBufferSize();
        if(i >= 0)
            socket.setSendBufferSize(i);
        int j = params.getReceiveBufferSize();
        if(j >= 0)
            socket.setReceiveBufferSize(j);
        int k = socket.getSendBufferSize();
        if(k > 2048)
            k = 2048;
        int l = socket.getReceiveBufferSize();
        if(l > 2048)
            l = 2048;
        inputStream = new BufferedInputStream(socket.getInputStream(), l);
        outputStream = new BufferedOutputStream(socket.getOutputStream(), k);
        usingSecureSocket = true;
        tunnelEstablished = true;
    }

    public void write(byte abyte0[])
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.write(byte[])");
        write(abyte0, 0, abyte0.length);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.write(byte[], int, int)");
        if(i < 0)
            throw new IllegalArgumentException("Array offset may not be negative");
        if(j < 0)
            throw new IllegalArgumentException("Array length may not be negative");
        if(i + j > abyte0.length)
        {
            throw new IllegalArgumentException("Given offset and length exceed the array length");
        } else
        {
            assertOpen();
            outputStream.write(abyte0, i, j);
            return;
        }
    }

    public void writeLine()
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.writeLine()");
        write(CRLF);
    }

    public void writeLine(byte abyte0[])
        throws IOException, IllegalStateException
    {
        LOG.trace("enter HttpConnection.writeLine(byte[])");
        write(abyte0);
        writeLine();
    }

    private static final byte CRLF[];
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpConnection;
    private String hostName;
    private HttpConnectionManager httpConnectionManager;
    private InputStream inputStream;
    protected boolean isOpen;
    private InputStream lastResponseInputStream;
    private InetAddress localAddress;
    private boolean locked;
    private OutputStream outputStream;
    private HttpConnectionParams params;
    private int portNumber;
    private Protocol protocolInUse;
    private String proxyHostName;
    private int proxyPortNumber;
    private Socket socket;
    private boolean tunnelEstablished;
    private boolean usingSecureSocket;

    static 
    {
        byte abyte0[] = new byte[2];
        abyte0[0] = 13;
        abyte0[1] = 10;
        CRLF = abyte0;
        Class class1;
        if(class$org$apache$commons$httpclient$HttpConnection == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpConnection");
            class$org$apache$commons$httpclient$HttpConnection = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpConnection;
        }
        LOG = LogFactory.getLog(class1);
    }
}
