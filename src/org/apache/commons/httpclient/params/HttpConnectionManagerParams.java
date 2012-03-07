// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HostConfiguration;

// Referenced classes of package org.apache.commons.httpclient.params:
//            HttpConnectionParams, DefaultHttpParams

public class HttpConnectionManagerParams extends HttpConnectionParams
{

    public HttpConnectionManagerParams()
    {
    }

    public int getDefaultMaxConnectionsPerHost()
    {
        return getMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION);
    }

    public int getMaxConnectionsPerHost(HostConfiguration hostconfiguration)
    {
        Map map = (Map)getParameter("http.connection-manager.max-per-host");
        int i;
        if(map == null)
        {
            i = 2;
        } else
        {
            Integer integer = (Integer)map.get(hostconfiguration);
            if(integer == null && hostconfiguration != HostConfiguration.ANY_HOST_CONFIGURATION)
                i = getMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION);
            else
            if(integer == null)
                i = 2;
            else
                i = integer.intValue();
        }
        return i;
    }

    public int getMaxTotalConnections()
    {
        return getIntParameter("http.connection-manager.max-total", 20);
    }

    public void setDefaultMaxConnectionsPerHost(int i)
    {
        setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, i);
    }

    public void setMaxConnectionsPerHost(HostConfiguration hostconfiguration, int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException("maxHostConnections must be greater than 0");
        Map map = (Map)getParameter("http.connection-manager.max-per-host");
        HashMap hashmap;
        if(map == null)
            hashmap = new HashMap();
        else
            hashmap = new HashMap(map);
        hashmap.put(hostconfiguration, new Integer(i));
        setParameter("http.connection-manager.max-per-host", hashmap);
    }

    public void setMaxTotalConnections(int i)
    {
        setIntParameter("http.connection-manager.max-total", i);
    }

    public static final String MAX_HOST_CONNECTIONS = "http.connection-manager.max-per-host";
    public static final String MAX_TOTAL_CONNECTIONS = "http.connection-manager.max-total";
}
