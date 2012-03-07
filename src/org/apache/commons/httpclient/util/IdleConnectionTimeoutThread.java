// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.util.*;
import org.apache.commons.httpclient.HttpConnectionManager;

public class IdleConnectionTimeoutThread extends Thread
{

    public IdleConnectionTimeoutThread()
    {
        connectionManagers = new ArrayList();
        shutdown = false;
        timeoutInterval = 1000L;
        connectionTimeout = 3000L;
        setDaemon(true);
    }

    /**
     * @deprecated Method addConnectionManager is deprecated
     */

    @Deprecated
	public void addConnectionManager(HttpConnectionManager httpconnectionmanager)
    {
        this;
        JVM INSTR monitorenter ;
        if(shutdown)
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        connectionManagers.add(httpconnectionmanager);
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method removeConnectionManager is deprecated
     */

    @Deprecated
	public void removeConnectionManager(HttpConnectionManager httpconnectionmanager)
    {
        this;
        JVM INSTR monitorenter ;
        if(shutdown)
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        connectionManagers.remove(httpconnectionmanager);
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method run is deprecated
     */

    @Deprecated
	@Override
	public void run()
    {
        this;
        JVM INSTR monitorenter ;
_L3:
        if(!shutdown)
            break MISSING_BLOCK_LABEL_21;
        connectionManagers.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Iterator iterator = connectionManagers.iterator();
_L1:
        boolean flag = iterator.hasNext();
        if(flag)
            break MISSING_BLOCK_LABEL_58;
        try
        {
            wait(timeoutInterval);
        }
        catch(InterruptedException interruptedexception) { }
        continue; /* Loop/switch isn't completed */
        ((HttpConnectionManager)iterator.next()).closeIdleConnections(connectionTimeout);
          goto _L1
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L3; else goto _L2
_L2:
    }

    /**
     * @deprecated Method setConnectionTimeout is deprecated
     */

    @Deprecated
	public void setConnectionTimeout(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(shutdown)
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        connectionTimeout = l;
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method setTimeoutInterval is deprecated
     */

    @Deprecated
	public void setTimeoutInterval(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if(shutdown)
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        break MISSING_BLOCK_LABEL_24;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        timeoutInterval = l;
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method shutdown is deprecated
     */

    @Deprecated
	public void shutdown()
    {
        this;
        JVM INSTR monitorenter ;
        shutdown = true;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private List connectionManagers;
    private long connectionTimeout;
    private boolean shutdown;
    private long timeoutInterval;
}
