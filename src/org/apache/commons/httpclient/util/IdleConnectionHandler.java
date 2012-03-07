// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.util.*;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.logging.Log;

public class IdleConnectionHandler
{

    public IdleConnectionHandler()
    {
        connectionToAdded = new HashMap();
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

    public void add(HttpConnection httpconnection)
    {
        Long long1 = new Long(System.currentTimeMillis());
        if(LOG.isDebugEnabled())
            LOG.debug("Adding connection at: " + long1);
        connectionToAdded.put(httpconnection, long1);
    }

    public void closeIdleConnections(long l)
    {
        long l1 = System.currentTimeMillis() - l;
        if(LOG.isDebugEnabled())
            LOG.debug("Checking for connections, idleTimeout: " + l1);
        Iterator iterator = connectionToAdded.keySet().iterator();
        do
        {
            HttpConnection httpconnection;
            Long long1;
            do
            {
                if(!iterator.hasNext())
                    return;
                httpconnection = (HttpConnection)iterator.next();
                long1 = (Long)connectionToAdded.get(httpconnection);
            } while(long1.longValue() > l1);
            if(LOG.isDebugEnabled())
                LOG.debug("Closing connection, connection time: " + long1);
            iterator.remove();
            httpconnection.close();
        } while(true);
    }

    public void remove(HttpConnection httpconnection)
    {
        connectionToAdded.remove(httpconnection);
    }

    public void removeAll()
    {
        connectionToAdded.clear();
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$util$IdleConnectionHandler;
    private Map connectionToAdded;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$util$IdleConnectionHandler == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.util.IdleConnectionHandler");
            class$org$apache$commons$httpclient$util$IdleConnectionHandler = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$util$IdleConnectionHandler;
        }
        LOG = LogFactory.getLog(class1);
    }
}
