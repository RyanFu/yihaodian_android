// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import java.lang.ref.*;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.IdleConnectionHandler;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpConnectionManager, HostConfiguration, HttpConnection, ConnectionPoolTimeoutException, 
//            HttpException, SimpleHttpConnectionManager

public class MultiThreadedHttpConnectionManager
    implements HttpConnectionManager
{
    private static class HttpConnectionAdapter extends HttpConnection
    {

        public void close()
        {
            if(hasConnection())
                wrappedConnection.close();
        }

        public boolean closeIfStale()
            throws IOException
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.closeIfStale();
            else
                flag = false;
            return flag;
        }

        public void flushRequestOutputStream()
            throws IOException
        {
            if(hasConnection())
            {
                wrappedConnection.flushRequestOutputStream();
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public String getHost()
        {
            String s;
            if(hasConnection())
                s = wrappedConnection.getHost();
            else
                s = null;
            return s;
        }

        public HttpConnectionManager getHttpConnectionManager()
        {
            HttpConnectionManager httpconnectionmanager;
            if(hasConnection())
                httpconnectionmanager = wrappedConnection.getHttpConnectionManager();
            else
                httpconnectionmanager = null;
            return httpconnectionmanager;
        }

        public InputStream getLastResponseInputStream()
        {
            InputStream inputstream;
            if(hasConnection())
                inputstream = wrappedConnection.getLastResponseInputStream();
            else
                inputstream = null;
            return inputstream;
        }

        public InetAddress getLocalAddress()
        {
            InetAddress inetaddress;
            if(hasConnection())
                inetaddress = wrappedConnection.getLocalAddress();
            else
                inetaddress = null;
            return inetaddress;
        }

        public HttpConnectionParams getParams()
        {
            if(hasConnection())
                return wrappedConnection.getParams();
            else
                throw new IllegalStateException("Connection has been released");
        }

        public int getPort()
        {
            int i;
            if(hasConnection())
                i = wrappedConnection.getPort();
            else
                i = -1;
            return i;
        }

        public Protocol getProtocol()
        {
            Protocol protocol;
            if(hasConnection())
                protocol = wrappedConnection.getProtocol();
            else
                protocol = null;
            return protocol;
        }

        public String getProxyHost()
        {
            String s;
            if(hasConnection())
                s = wrappedConnection.getProxyHost();
            else
                s = null;
            return s;
        }

        public int getProxyPort()
        {
            int i;
            if(hasConnection())
                i = wrappedConnection.getProxyPort();
            else
                i = -1;
            return i;
        }

        public OutputStream getRequestOutputStream()
            throws IOException, IllegalStateException
        {
            OutputStream outputstream;
            if(hasConnection())
                outputstream = wrappedConnection.getRequestOutputStream();
            else
                outputstream = null;
            return outputstream;
        }

        public InputStream getResponseInputStream()
            throws IOException, IllegalStateException
        {
            InputStream inputstream;
            if(hasConnection())
                inputstream = wrappedConnection.getResponseInputStream();
            else
                inputstream = null;
            return inputstream;
        }

        public int getSendBufferSize()
            throws SocketException
        {
            if(hasConnection())
                return wrappedConnection.getSendBufferSize();
            else
                throw new IllegalStateException("Connection has been released");
        }

        public int getSoTimeout()
            throws SocketException
        {
            if(hasConnection())
                return wrappedConnection.getSoTimeout();
            else
                throw new IllegalStateException("Connection has been released");
        }

        public String getVirtualHost()
        {
            if(hasConnection())
                return wrappedConnection.getVirtualHost();
            else
                throw new IllegalStateException("Connection has been released");
        }

        HttpConnection getWrappedConnection()
        {
            return wrappedConnection;
        }

        protected boolean hasConnection()
        {
            boolean flag;
            if(wrappedConnection != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isOpen()
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isOpen();
            else
                flag = false;
            return flag;
        }

        public boolean isProxied()
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isProxied();
            else
                flag = false;
            return flag;
        }

        public boolean isResponseAvailable()
            throws IOException
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isResponseAvailable();
            else
                flag = false;
            return flag;
        }

        public boolean isResponseAvailable(int i)
            throws IOException
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isResponseAvailable(i);
            else
                flag = false;
            return flag;
        }

        public boolean isSecure()
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isSecure();
            else
                flag = false;
            return flag;
        }

        public boolean isStaleCheckingEnabled()
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isStaleCheckingEnabled();
            else
                flag = false;
            return flag;
        }

        public boolean isTransparent()
        {
            boolean flag;
            if(hasConnection())
                flag = wrappedConnection.isTransparent();
            else
                flag = false;
            return flag;
        }

        public void open()
            throws IOException
        {
            if(hasConnection())
            {
                wrappedConnection.open();
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void print(String s)
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.print(s);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void print(String s, String s1)
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.print(s, s1);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void printLine()
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.printLine();
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void printLine(String s)
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.printLine(s);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void printLine(String s, String s1)
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.printLine(s, s1);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public String readLine()
            throws IOException, IllegalStateException
        {
            if(hasConnection())
                return wrappedConnection.readLine();
            else
                throw new IllegalStateException("Connection has been released");
        }

        public String readLine(String s)
            throws IOException, IllegalStateException
        {
            if(hasConnection())
                return wrappedConnection.readLine(s);
            else
                throw new IllegalStateException("Connection has been released");
        }

        public void releaseConnection()
        {
            if(!isLocked() && hasConnection())
            {
                HttpConnection httpconnection = wrappedConnection;
                wrappedConnection = null;
                httpconnection.releaseConnection();
            }
        }

        public void setConnectionTimeout(int i)
        {
            if(hasConnection())
                wrappedConnection.setConnectionTimeout(i);
        }

        public void setHost(String s)
            throws IllegalStateException
        {
            if(hasConnection())
                wrappedConnection.setHost(s);
        }

        public void setHttpConnectionManager(HttpConnectionManager httpconnectionmanager)
        {
            if(hasConnection())
                wrappedConnection.setHttpConnectionManager(httpconnectionmanager);
        }

        public void setLastResponseInputStream(InputStream inputstream)
        {
            if(hasConnection())
                wrappedConnection.setLastResponseInputStream(inputstream);
        }

        public void setLocalAddress(InetAddress inetaddress)
        {
            if(hasConnection())
            {
                wrappedConnection.setLocalAddress(inetaddress);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void setParams(HttpConnectionParams httpconnectionparams)
        {
            if(hasConnection())
            {
                wrappedConnection.setParams(httpconnectionparams);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void setPort(int i)
            throws IllegalStateException
        {
            if(hasConnection())
                wrappedConnection.setPort(i);
        }

        public void setProtocol(Protocol protocol)
        {
            if(hasConnection())
                wrappedConnection.setProtocol(protocol);
        }

        public void setProxyHost(String s)
            throws IllegalStateException
        {
            if(hasConnection())
                wrappedConnection.setProxyHost(s);
        }

        public void setProxyPort(int i)
            throws IllegalStateException
        {
            if(hasConnection())
                wrappedConnection.setProxyPort(i);
        }

        public void setSendBufferSize(int i)
            throws SocketException
        {
            if(hasConnection())
            {
                wrappedConnection.setSendBufferSize(i);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void setSoTimeout(int i)
            throws SocketException, IllegalStateException
        {
            if(hasConnection())
                wrappedConnection.setSoTimeout(i);
        }

        public void setSocketTimeout(int i)
            throws SocketException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.setSocketTimeout(i);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void setStaleCheckingEnabled(boolean flag)
        {
            if(hasConnection())
            {
                wrappedConnection.setStaleCheckingEnabled(flag);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void setVirtualHost(String s)
            throws IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.setVirtualHost(s);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void shutdownOutput()
        {
            if(hasConnection())
                wrappedConnection.shutdownOutput();
        }

        public void tunnelCreated()
            throws IllegalStateException, IOException
        {
            if(hasConnection())
                wrappedConnection.tunnelCreated();
        }

        public void write(byte abyte0[])
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.write(abyte0);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.write(abyte0, i, j);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void writeLine()
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.writeLine();
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        public void writeLine(byte abyte0[])
            throws IOException, IllegalStateException
        {
            if(hasConnection())
            {
                wrappedConnection.writeLine(abyte0);
                return;
            } else
            {
                throw new IllegalStateException("Connection has been released");
            }
        }

        private HttpConnection wrappedConnection;

        public HttpConnectionAdapter(HttpConnection httpconnection)
        {
            super(httpconnection.getHost(), httpconnection.getPort(), httpconnection.getProtocol());
            wrappedConnection = httpconnection;
        }
    }

    private static class HttpConnectionWithReference extends HttpConnection
    {

        public WeakReference reference;

        public HttpConnectionWithReference(HostConfiguration hostconfiguration)
        {
            super(hostconfiguration);
            reference = new WeakReference(this, MultiThreadedHttpConnectionManager.REFERENCE_QUEUE);
        }
    }

    private static class ReferenceQueueThread extends Thread
    {

        private void handleReference(Reference reference)
        {
            ConnectionSource connectionsource;
            synchronized(MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE)
            {
                connectionsource = (ConnectionSource)MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.remove(reference);
            }
            if(connectionsource != null)
            {
                if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                    MultiThreadedHttpConnectionManager.LOG.debug("Connection reclaimed by garbage collector, hostConfig=" + connectionsource.hostConfiguration);
                connectionsource.connectionPool.handleLostConnection(connectionsource.hostConfiguration);
            }
        }

        public void run()
        {
            do
            {
                if(shutdown)
                    return;
                try
                {
                    Reference reference = MultiThreadedHttpConnectionManager.REFERENCE_QUEUE.remove(1000L);
                    if(reference != null)
                        handleReference(reference);
                }
                catch(InterruptedException interruptedexception)
                {
                    MultiThreadedHttpConnectionManager.LOG.debug("ReferenceQueueThread interrupted", interruptedexception);
                }
            } while(true);
        }

        public void shutdown()
        {
            shutdown = true;
        }

        private boolean shutdown;

        public ReferenceQueueThread()
        {
            shutdown = false;
            setDaemon(true);
            setName("MultiThreadedHttpConnectionManager cleanup");
        }
    }

    private static class WaitingThread
    {

        public HostConnectionPool hostConnectionPool;
        public Thread thread;

        private WaitingThread()
        {
        }

    }

    private static class HostConnectionPool
    {

        public LinkedList freeConnections;
        public HostConfiguration hostConfiguration;
        public int numConnections;
        public LinkedList waitingThreads;

        private HostConnectionPool()
        {
            freeConnections = new LinkedList();
            waitingThreads = new LinkedList();
            numConnections = 0;
        }

    }

    private static class ConnectionSource
    {

        public ConnectionPool connectionPool;
        public HostConfiguration hostConfiguration;

        private ConnectionSource()
        {
        }

    }

    private class ConnectionPool
    {

        /**
         * @deprecated Method deleteConnection is deprecated
         */

        private void deleteConnection(HttpConnection httpconnection)
        {
            this;
            JVM INSTR monitorenter ;
            HostConfiguration hostconfiguration = configurationForConnection(httpconnection);
            if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                MultiThreadedHttpConnectionManager.LOG.debug("Reclaiming connection, hostConfig=" + hostconfiguration);
            httpconnection.close();
            HostConnectionPool hostconnectionpool = getHostPool(hostconfiguration);
            hostconnectionpool.freeConnections.remove(httpconnection);
            hostconnectionpool.numConnections = hostconnectionpool.numConnections - 1;
            numConnections = numConnections - 1;
            idleConnectionHandler.remove(httpconnection);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method closeIdleConnections is deprecated
         */

        public void closeIdleConnections(long l)
        {
            this;
            JVM INSTR monitorenter ;
            idleConnectionHandler.closeIdleConnections(l);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method createConnection is deprecated
         */

        public HttpConnection createConnection(HostConfiguration hostconfiguration)
        {
            this;
            JVM INSTR monitorenter ;
            HttpConnectionWithReference httpconnectionwithreference;
            HostConnectionPool hostconnectionpool = getHostPool(hostconfiguration);
            if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                MultiThreadedHttpConnectionManager.LOG.debug("Allocating new connection, hostConfig=" + hostconfiguration);
            httpconnectionwithreference = new HttpConnectionWithReference(hostconfiguration);
            httpconnectionwithreference.getParams().setDefaults(params);
            httpconnectionwithreference.setHttpConnectionManager(MultiThreadedHttpConnectionManager.this);
            numConnections = 1 + numConnections;
            hostconnectionpool.numConnections = 1 + hostconnectionpool.numConnections;
            MultiThreadedHttpConnectionManager.storeReferenceToConnection(httpconnectionwithreference, hostconfiguration, this);
            this;
            JVM INSTR monitorexit ;
            return httpconnectionwithreference;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method deleteClosedConnections is deprecated
         */

        public void deleteClosedConnections()
        {
            this;
            JVM INSTR monitorenter ;
            Iterator iterator = freeConnections.iterator();
_L1:
            boolean flag = iterator.hasNext();
            if(flag)
                break MISSING_BLOCK_LABEL_24;
            this;
            JVM INSTR monitorexit ;
            return;
            HttpConnection httpconnection = (HttpConnection)iterator.next();
            if(!httpconnection.isOpen())
            {
                iterator.remove();
                deleteConnection(httpconnection);
            }
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method deleteLeastUsedConnection is deprecated
         */

        public void deleteLeastUsedConnection()
        {
            this;
            JVM INSTR monitorenter ;
            HttpConnection httpconnection = (HttpConnection)freeConnections.removeFirst();
            if(httpconnection == null) goto _L2; else goto _L1
_L1:
            deleteConnection(httpconnection);
_L4:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            if(!MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) goto _L4; else goto _L3
_L3:
            MultiThreadedHttpConnectionManager.LOG.debug("Attempted to reclaim an unused connection but there were none.");
              goto _L4
            Exception exception;
            exception;
            throw exception;
        }

        public void freeConnection(HttpConnection httpconnection)
        {
            HostConfiguration hostconfiguration;
            hostconfiguration = configurationForConnection(httpconnection);
            if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                MultiThreadedHttpConnectionManager.LOG.debug("Freeing connection, hostConfig=" + hostconfiguration);
            this;
            JVM INSTR monitorenter ;
            if(!MultiThreadedHttpConnectionManager.this.shutdown)
                break MISSING_BLOCK_LABEL_68;
            httpconnection.close();
            this;
            JVM INSTR monitorexit ;
            break MISSING_BLOCK_LABEL_205;
            HostConnectionPool hostconnectionpool = getHostPool(hostconfiguration);
            hostconnectionpool.freeConnections.add(httpconnection);
            if(hostconnectionpool.numConnections == 0)
            {
                MultiThreadedHttpConnectionManager.LOG.error("Host connection pool not found, hostConfig=" + hostconfiguration);
                hostconnectionpool.numConnections = 1;
            }
            freeConnections.add(httpconnection);
            MultiThreadedHttpConnectionManager.removeReferenceToConnection((HttpConnectionWithReference)httpconnection);
            if(numConnections == 0)
            {
                MultiThreadedHttpConnectionManager.LOG.error("Host connection pool not found, hostConfig=" + hostconfiguration);
                numConnections = 1;
            }
            idleConnectionHandler.add(httpconnection);
            notifyWaitingThread(hostconnectionpool);
            this;
            JVM INSTR monitorexit ;
            break MISSING_BLOCK_LABEL_205;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method getFreeConnection is deprecated
         */

        public HttpConnection getFreeConnection(HostConfiguration hostconfiguration)
        {
            this;
            JVM INSTR monitorenter ;
            HttpConnectionWithReference httpconnectionwithreference = null;
            HostConnectionPool hostconnectionpool = getHostPool(hostconfiguration);
            if(hostconnectionpool.freeConnections.size() <= 0) goto _L2; else goto _L1
_L1:
            httpconnectionwithreference = (HttpConnectionWithReference)hostconnectionpool.freeConnections.removeFirst();
            freeConnections.remove(httpconnectionwithreference);
            MultiThreadedHttpConnectionManager.storeReferenceToConnection(httpconnectionwithreference, hostconfiguration, this);
            if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                MultiThreadedHttpConnectionManager.LOG.debug("Getting free connection, hostConfig=" + hostconfiguration);
            idleConnectionHandler.remove(httpconnectionwithreference);
_L4:
            this;
            JVM INSTR monitorexit ;
            return httpconnectionwithreference;
_L2:
            if(!MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) goto _L4; else goto _L3
_L3:
            MultiThreadedHttpConnectionManager.LOG.debug("There were no free connections to get, hostConfig=" + hostconfiguration);
              goto _L4
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method getHostPool is deprecated
         */

        public HostConnectionPool getHostPool(HostConfiguration hostconfiguration)
        {
            this;
            JVM INSTR monitorenter ;
            HostConnectionPool hostconnectionpool;
            MultiThreadedHttpConnectionManager.LOG.trace("enter HttpConnectionManager.ConnectionPool.getHostPool(HostConfiguration)");
            hostconnectionpool = (HostConnectionPool)mapHosts.get(hostconfiguration);
            if(hostconnectionpool == null)
            {
                hostconnectionpool = new HostConnectionPool();
                hostconnectionpool.hostConfiguration = hostconfiguration;
                mapHosts.put(hostconfiguration, hostconnectionpool);
            }
            this;
            JVM INSTR monitorexit ;
            return hostconnectionpool;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method handleLostConnection is deprecated
         */

        public void handleLostConnection(HostConfiguration hostconfiguration)
        {
            this;
            JVM INSTR monitorenter ;
            HostConnectionPool hostconnectionpool = getHostPool(hostconfiguration);
            hostconnectionpool.numConnections = hostconnectionpool.numConnections - 1;
            numConnections = numConnections - 1;
            notifyWaitingThread(hostconfiguration);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method notifyWaitingThread is deprecated
         */

        public void notifyWaitingThread(HostConfiguration hostconfiguration)
        {
            this;
            JVM INSTR monitorenter ;
            notifyWaitingThread(getHostPool(hostconfiguration));
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method notifyWaitingThread is deprecated
         */

        public void notifyWaitingThread(HostConnectionPool hostconnectionpool)
        {
            this;
            JVM INSTR monitorenter ;
            WaitingThread waitingthread = null;
            if(hostconnectionpool.waitingThreads.size() <= 0) goto _L2; else goto _L1
_L1:
            if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                MultiThreadedHttpConnectionManager.LOG.debug("Notifying thread waiting on host pool, hostConfig=" + hostconnectionpool.hostConfiguration);
            waitingthread = (WaitingThread)hostconnectionpool.waitingThreads.removeFirst();
            waitingThreads.remove(waitingthread);
_L5:
            if(waitingthread != null)
                waitingthread.thread.interrupt();
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            if(waitingThreads.size() <= 0) goto _L4; else goto _L3
_L3:
            if(MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
                MultiThreadedHttpConnectionManager.LOG.debug("No-one waiting on host pool, notifying next waiting thread.");
            waitingthread = (WaitingThread)waitingThreads.removeFirst();
            waitingthread.hostConnectionPool.waitingThreads.remove(waitingthread);
              goto _L5
            Exception exception;
            exception;
            throw exception;
_L4:
            if(!MultiThreadedHttpConnectionManager.LOG.isDebugEnabled()) goto _L5; else goto _L6
_L6:
            MultiThreadedHttpConnectionManager.LOG.debug("Notifying no-one, there are no waiting threads");
              goto _L5
        }

        /**
         * @deprecated Method shutdown is deprecated
         */

        public void shutdown()
        {
            this;
            JVM INSTR monitorenter ;
            Iterator iterator = freeConnections.iterator();
_L3:
            if(iterator.hasNext()) goto _L2; else goto _L1
_L1:
            Iterator iterator1;
            MultiThreadedHttpConnectionManager.shutdownCheckedOutConnections(this);
            iterator1 = waitingThreads.iterator();
_L4:
            if(iterator1.hasNext())
                break MISSING_BLOCK_LABEL_89;
            mapHosts.clear();
            idleConnectionHandler.removeAll();
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            HttpConnection httpconnection = (HttpConnection)iterator.next();
            iterator.remove();
            httpconnection.close();
              goto _L3
            Exception exception;
            exception;
            throw exception;
            WaitingThread waitingthread = (WaitingThread)iterator1.next();
            iterator1.remove();
            waitingthread.thread.interrupt();
              goto _L4
        }

        private LinkedList freeConnections;
        private IdleConnectionHandler idleConnectionHandler;
        private final Map mapHosts;
        private int numConnections;
        private final MultiThreadedHttpConnectionManager this$0;
        private LinkedList waitingThreads;




        private ConnectionPool()
        {
            this$0 = MultiThreadedHttpConnectionManager.this;
            freeConnections = new LinkedList();
            waitingThreads = new LinkedList();
            mapHosts = new HashMap();
            idleConnectionHandler = new IdleConnectionHandler();
            numConnections = 0;
        }

    }


    public MultiThreadedHttpConnectionManager()
    {
        params = new HttpConnectionManagerParams();
        shutdown = false;
        connectionPool = new ConnectionPool();
        synchronized(ALL_CONNECTION_MANAGERS)
        {
            ALL_CONNECTION_MANAGERS.put(this, null);
        }
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

    private HostConfiguration configurationForConnection(HttpConnection httpconnection)
    {
        HostConfiguration hostconfiguration = new HostConfiguration();
        hostconfiguration.setHost(httpconnection.getHost(), httpconnection.getPort(), httpconnection.getProtocol());
        if(httpconnection.getLocalAddress() != null)
            hostconfiguration.setLocalAddress(httpconnection.getLocalAddress());
        if(httpconnection.getProxyHost() != null)
            hostconfiguration.setProxy(httpconnection.getProxyHost(), httpconnection.getProxyPort());
        return hostconfiguration;
    }

    private HttpConnection doGetConnection(HostConfiguration hostconfiguration, long l)
        throws ConnectionPoolTimeoutException
    {
        HttpConnection httpconnection;
        int i;
        int j;
        httpconnection = null;
        i = params.getMaxConnectionsPerHost(hostconfiguration);
        j = params.getMaxTotalConnections();
        ConnectionPool connectionpool = connectionPool;
        connectionpool;
        JVM INSTR monitorenter ;
        HostConfiguration hostconfiguration1 = new HostConfiguration(hostconfiguration);
        HostConnectionPool hostconnectionpool = connectionPool.getHostPool(hostconfiguration1);
        if(l <= 0L) goto _L2; else goto _L1
_L1:
        boolean flag = true;
          goto _L3
_L7:
        if(httpconnection == null) goto _L5; else goto _L4
_L4:
        connectionpool;
        JVM INSTR monitorexit ;
        return httpconnection;
_L5:
        if(shutdown)
            throw new IllegalStateException("Connection factory has been shutdown.");
          goto _L6
        Exception exception;
        exception;
_L14:
        connectionpool;
        JVM INSTR monitorexit ;
        throw exception;
_L6:
        if(hostconnectionpool.freeConnections.size() > 0)
        {
            httpconnection = connectionPool.getFreeConnection(hostconfiguration1);
        } else
        {
            if(hostconnectionpool.numConnections >= i || connectionPool.numConnections >= j)
                break MISSING_BLOCK_LABEL_161;
            httpconnection = connectionPool.createConnection(hostconfiguration1);
        }
          goto _L7
        HttpConnection httpconnection1;
        if(hostconnectionpool.numConnections >= i || connectionPool.freeConnections.size() <= 0)
            break MISSING_BLOCK_LABEL_209;
        connectionPool.deleteLeastUsedConnection();
        httpconnection1 = connectionPool.createConnection(hostconfiguration1);
        httpconnection = httpconnection1;
          goto _L7
        long l1;
        if(!flag || l1 > 0L) goto _L9; else goto _L8
_L8:
        WaitingThread waitingthread;
        WaitingThread waitingthread1;
        try
        {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
        }
        catch(InterruptedException interruptedexception1)
        {
            waitingthread1 = waitingthread;
        }
_L16:
        if(!flag)
            break MISSING_BLOCK_LABEL_257;
        long l3 = System.currentTimeMillis();
        long l2;
        l1 -= l3 - l2;
        waitingthread = waitingthread1;
          goto _L7
_L9:
        if(LOG.isDebugEnabled())
            LOG.debug("Unable to get a connection, waiting..., hostConfig=" + hostconfiguration1);
        if(waitingthread != null) goto _L11; else goto _L10
_L10:
        waitingthread1 = new WaitingThread();
        waitingthread1.hostConnectionPool = hostconnectionpool;
        Thread thread = Thread.currentThread();
        waitingthread1.thread = thread;
_L17:
        if(flag)
            l2 = System.currentTimeMillis();
        hostconnectionpool.waitingThreads.addLast(waitingthread1);
        connectionPool.waitingThreads.addLast(waitingthread1);
        connectionPool.wait(l1);
        hostconnectionpool.waitingThreads.remove(waitingthread1);
        connectionPool.waitingThreads.remove(waitingthread1);
        if(!flag) goto _L13; else goto _L12
_L12:
        l1 -= System.currentTimeMillis() - l2;
          goto _L13
_L15:
        Exception exception1;
        if(flag)
        {
            l1 - (System.currentTimeMillis() - l2);
        }
        throw exception1;
        exception;
          goto _L14
        exception1;
          goto _L15
        InterruptedException interruptedexception;
        interruptedexception;
          goto _L16
_L11:
        waitingthread1 = waitingthread;
          goto _L17
_L3:
        l1 = l;
        l2 = 0L;
        waitingthread = null;
          goto _L7
_L2:
        flag = false;
          goto _L3
_L13:
        waitingthread = waitingthread1;
          goto _L7
        exception1;
        waitingthread;
          goto _L15
    }

    private static void removeReferenceToConnection(HttpConnectionWithReference httpconnectionwithreference)
    {
        synchronized(REFERENCE_TO_CONNECTION_SOURCE)
        {
            REFERENCE_TO_CONNECTION_SOURCE.remove(httpconnectionwithreference.reference);
        }
    }

    public static void shutdownAll()
    {
        Map map = REFERENCE_TO_CONNECTION_SOURCE;
        map;
        JVM INSTR monitorenter ;
        WeakHashMap weakhashmap = ALL_CONNECTION_MANAGERS;
        weakhashmap;
        JVM INSTR monitorenter ;
        Iterator iterator = ALL_CONNECTION_MANAGERS.keySet().iterator();
_L1:
        if(iterator.hasNext())
            break MISSING_BLOCK_LABEL_64;
        if(REFERENCE_QUEUE_THREAD != null)
        {
            REFERENCE_QUEUE_THREAD.shutdown();
            REFERENCE_QUEUE_THREAD = null;
        }
        REFERENCE_TO_CONNECTION_SOURCE.clear();
        map;
        JVM INSTR monitorexit ;
        return;
        MultiThreadedHttpConnectionManager multithreadedhttpconnectionmanager = (MultiThreadedHttpConnectionManager)iterator.next();
        iterator.remove();
        multithreadedhttpconnectionmanager.shutdown();
          goto _L1
        Exception exception1;
        exception1;
        weakhashmap;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static void shutdownCheckedOutConnections(ConnectionPool connectionpool)
    {
        ArrayList arraylist = new ArrayList();
        Map map = REFERENCE_TO_CONNECTION_SOURCE;
        map;
        JVM INSTR monitorenter ;
        Iterator iterator = REFERENCE_TO_CONNECTION_SOURCE.keySet().iterator();
_L3:
        if(iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Iterator iterator1 = arraylist.iterator();
_L4:
        if(!iterator1.hasNext())
            return;
        break MISSING_BLOCK_LABEL_127;
_L2:
        Reference reference = (Reference)iterator.next();
        if(((ConnectionSource)REFERENCE_TO_CONNECTION_SOURCE.get(reference)).connectionPool == connectionpool)
        {
            iterator.remove();
            HttpConnection httpconnection = (HttpConnection)reference.get();
            if(httpconnection != null)
                arraylist.add(httpconnection);
        }
          goto _L3
        Exception exception;
        exception;
        throw exception;
        HttpConnection httpconnection1 = (HttpConnection)iterator1.next();
        httpconnection1.close();
        httpconnection1.setHttpConnectionManager(null);
        httpconnection1.releaseConnection();
          goto _L4
    }

    private static void storeReferenceToConnection(HttpConnectionWithReference httpconnectionwithreference, HostConfiguration hostconfiguration, ConnectionPool connectionpool)
    {
        ConnectionSource connectionsource = new ConnectionSource();
        connectionsource.connectionPool = connectionpool;
        connectionsource.hostConfiguration = hostconfiguration;
        synchronized(REFERENCE_TO_CONNECTION_SOURCE)
        {
            if(REFERENCE_QUEUE_THREAD == null)
            {
                REFERENCE_QUEUE_THREAD = new ReferenceQueueThread();
                REFERENCE_QUEUE_THREAD.start();
            }
            REFERENCE_TO_CONNECTION_SOURCE.put(httpconnectionwithreference.reference, connectionsource);
        }
    }

    public void closeIdleConnections(long l)
    {
        connectionPool.closeIdleConnections(l);
    }

    public void deleteClosedConnections()
    {
        connectionPool.deleteClosedConnections();
    }

    public HttpConnection getConnection(HostConfiguration hostconfiguration)
    {
_L2:
        HttpConnection httpconnection = getConnectionWithTimeout(hostconfiguration, 0L);
        return httpconnection;
        ConnectionPoolTimeoutException connectionpooltimeoutexception;
        connectionpooltimeoutexception;
        LOG.debug("Unexpected exception while waiting for connection", connectionpooltimeoutexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public HttpConnection getConnection(HostConfiguration hostconfiguration, long l)
        throws HttpException
    {
        LOG.trace("enter HttpConnectionManager.getConnection(HostConfiguration, long)");
        HttpConnection httpconnection;
        try
        {
            httpconnection = getConnectionWithTimeout(hostconfiguration, l);
        }
        catch(ConnectionPoolTimeoutException connectionpooltimeoutexception)
        {
            throw new HttpException(connectionpooltimeoutexception.getMessage());
        }
        return httpconnection;
    }

    public HttpConnection getConnectionWithTimeout(HostConfiguration hostconfiguration, long l)
        throws ConnectionPoolTimeoutException
    {
        LOG.trace("enter HttpConnectionManager.getConnectionWithTimeout(HostConfiguration, long)");
        if(hostconfiguration == null)
            throw new IllegalArgumentException("hostConfiguration is null");
        if(LOG.isDebugEnabled())
            LOG.debug("HttpConnectionManager.getConnection:  config = " + hostconfiguration + ", timeout = " + l);
        return new HttpConnectionAdapter(doGetConnection(hostconfiguration, l));
    }

    public int getConnectionsInPool()
    {
        int i;
        synchronized(connectionPool)
        {
            i = connectionPool.numConnections;
        }
        return i;
    }

    public int getConnectionsInPool(HostConfiguration hostconfiguration)
    {
        int i;
        synchronized(connectionPool)
        {
            i = connectionPool.getHostPool(hostconfiguration).numConnections;
        }
        return i;
    }

    public int getConnectionsInUse()
    {
        return getConnectionsInPool();
    }

    public int getConnectionsInUse(HostConfiguration hostconfiguration)
    {
        return getConnectionsInPool(hostconfiguration);
    }

    public int getMaxConnectionsPerHost()
    {
        return params.getDefaultMaxConnectionsPerHost();
    }

    public int getMaxTotalConnections()
    {
        return params.getMaxTotalConnections();
    }

    public HttpConnectionManagerParams getParams()
    {
        return params;
    }

    public boolean isConnectionStaleCheckingEnabled()
    {
        return params.isStaleCheckingEnabled();
    }

    public void releaseConnection(HttpConnection httpconnection)
    {
        LOG.trace("enter HttpConnectionManager.releaseConnection(HttpConnection)");
        if(httpconnection instanceof HttpConnectionAdapter)
            httpconnection = ((HttpConnectionAdapter)httpconnection).getWrappedConnection();
        SimpleHttpConnectionManager.finishLastResponse(httpconnection);
        connectionPool.freeConnection(httpconnection);
    }

    public void setConnectionStaleCheckingEnabled(boolean flag)
    {
        params.setStaleCheckingEnabled(flag);
    }

    public void setMaxConnectionsPerHost(int i)
    {
        params.setDefaultMaxConnectionsPerHost(i);
    }

    public void setMaxTotalConnections(int i)
    {
        params.getMaxTotalConnections();
    }

    public void setParams(HttpConnectionManagerParams httpconnectionmanagerparams)
    {
        if(httpconnectionmanagerparams == null)
        {
            throw new IllegalArgumentException("Parameters may not be null");
        } else
        {
            params = httpconnectionmanagerparams;
            return;
        }
    }

    /**
     * @deprecated Method shutdown is deprecated
     */

    public void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        synchronized(connectionPool)
        {
            if(!shutdown)
            {
                shutdown = true;
                connectionPool.shutdown();
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        connectionpool;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static WeakHashMap ALL_CONNECTION_MANAGERS = new WeakHashMap();
    public static final int DEFAULT_MAX_HOST_CONNECTIONS = 2;
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
    private static final Log LOG;
    private static final ReferenceQueue REFERENCE_QUEUE = new ReferenceQueue();
    private static ReferenceQueueThread REFERENCE_QUEUE_THREAD;
    private static final Map REFERENCE_TO_CONNECTION_SOURCE = new HashMap();
    static Class class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager;
    private ConnectionPool connectionPool;
    private HttpConnectionManagerParams params;
    private boolean shutdown;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.MultiThreadedHttpConnectionManager");
            class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager;
        }
        LOG = LogFactory.getLog(class1);
    }









}
