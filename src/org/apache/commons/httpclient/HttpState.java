// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.util.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            Cookie, Credentials

public class HttpState
{

    public HttpState()
    {
        credMap = new HashMap();
        proxyCred = new HashMap();
        cookies = new ArrayList();
        preemptive = false;
        cookiePolicy = -1;
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

    private static String getCookiesStringRepresentation(List list)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                return stringbuffer.toString();
            Cookie cookie = (Cookie)iterator.next();
            if(stringbuffer.length() > 0)
                stringbuffer.append("#");
            stringbuffer.append(cookie.toExternalForm());
        } while(true);
    }

    private static String getCredentialsStringRepresentation(Map map)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = map.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                return stringbuffer.toString();
            Object obj = iterator.next();
            Credentials credentials = (Credentials)map.get(obj);
            if(stringbuffer.length() > 0)
                stringbuffer.append(", ");
            stringbuffer.append(obj);
            stringbuffer.append("#");
            stringbuffer.append(credentials.toString());
        } while(true);
    }

    private static Credentials matchCredentials(HashMap hashmap, AuthScope authscope)
    {
        Credentials credentials = (Credentials)hashmap.get(authscope);
        if(credentials != null) goto _L2; else goto _L1
_L1:
        int i;
        AuthScope authscope1;
        Iterator iterator;
        i = -1;
        authscope1 = null;
        iterator = hashmap.keySet().iterator();
_L6:
        if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
        if(authscope1 != null)
            credentials = (Credentials)hashmap.get(authscope1);
_L2:
        return credentials;
_L4:
        AuthScope authscope2 = (AuthScope)iterator.next();
        int j = authscope.match(authscope2);
        if(j > i)
        {
            i = j;
            authscope1 = authscope2;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }


// JavaClassFileOutputException: Prev chain is broken

    /**
     * @deprecated Method addCookies is deprecated
     */

    public void addCookies(Cookie acookie[])
    {
        this;
        JVM INSTR monitorenter ;
        LOG.trace("enter HttpState.addCookies(Cookie[])");
        if(acookie == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        int j = acookie.length;
        if(i < j) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        addCookie(acookie[i]);
        i++;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public void clear()
    {
        clearCookies();
        clearCredentials();
        clearProxyCredentials();
    }

    public void clearCookies()
    {
        cookies.clear();
    }

    public void clearCredentials()
    {
        credMap.clear();
    }

    public void clearProxyCredentials()
    {
        proxyCred.clear();
    }

    public int getCookiePolicy()
    {
        return cookiePolicy;
    }

    /**
     * @deprecated Method getCookies is deprecated
     */

    public Cookie[] getCookies()
    {
        this;
        JVM INSTR monitorenter ;
        Cookie acookie[];
        LOG.trace("enter HttpState.getCookies()");
        acookie = (Cookie[])cookies.toArray(new Cookie[cookies.size()]);
        this;
        JVM INSTR monitorexit ;
        return acookie;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getCookies is deprecated
     */

    public Cookie[] getCookies(String s, int i, String s1, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        CookieSpec cookiespec;
        ArrayList arraylist;
        int j;
        int k;
        LOG.trace("enter HttpState.getCookies(String, int, String, boolean)");
        cookiespec = CookiePolicy.getDefaultSpec();
        arraylist = new ArrayList(cookies.size());
        j = 0;
        k = cookies.size();
_L3:
        if(j < k) goto _L2; else goto _L1
_L1:
        Cookie acookie[] = (Cookie[])arraylist.toArray(new Cookie[arraylist.size()]);
        this;
        JVM INSTR monitorexit ;
        return acookie;
_L2:
        Cookie cookie = (Cookie)cookies.get(j);
        if(cookiespec.match(s, i, s1, flag, cookie))
            arraylist.add(cookie);
        j++;
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getCredentials is deprecated
     */

    public Credentials getCredentials(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        Credentials credentials;
        LOG.trace("enter HttpState.getCredentials(String, String");
        credentials = matchCredentials(credMap, new AuthScope(s1, -1, s, AuthScope.ANY_SCHEME));
        this;
        JVM INSTR monitorexit ;
        return credentials;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getCredentials is deprecated
     */

    public Credentials getCredentials(AuthScope authscope)
    {
        this;
        JVM INSTR monitorenter ;
        if(authscope != null)
            break MISSING_BLOCK_LABEL_21;
        throw new IllegalArgumentException("Authentication scope may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Credentials credentials;
        LOG.trace("enter HttpState.getCredentials(AuthScope)");
        credentials = matchCredentials(credMap, authscope);
        this;
        JVM INSTR monitorexit ;
        return credentials;
    }

    /**
     * @deprecated Method getProxyCredentials is deprecated
     */

    public Credentials getProxyCredentials(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        Credentials credentials;
        LOG.trace("enter HttpState.getCredentials(String, String");
        credentials = matchCredentials(proxyCred, new AuthScope(s1, -1, s, AuthScope.ANY_SCHEME));
        this;
        JVM INSTR monitorexit ;
        return credentials;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getProxyCredentials is deprecated
     */

    public Credentials getProxyCredentials(AuthScope authscope)
    {
        this;
        JVM INSTR monitorenter ;
        if(authscope != null)
            break MISSING_BLOCK_LABEL_21;
        throw new IllegalArgumentException("Authentication scope may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Credentials credentials;
        LOG.trace("enter HttpState.getProxyCredentials(AuthScope)");
        credentials = matchCredentials(proxyCred, authscope);
        this;
        JVM INSTR monitorexit ;
        return credentials;
    }

    public boolean isAuthenticationPreemptive()
    {
        return preemptive;
    }

    /**
     * @deprecated Method purgeExpiredCookies is deprecated
     */

    public boolean purgeExpiredCookies()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        LOG.trace("enter HttpState.purgeExpiredCookies()");
        flag = purgeExpiredCookies(new Date());
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method purgeExpiredCookies is deprecated
     */

    public boolean purgeExpiredCookies(Date date)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        Iterator iterator;
        LOG.trace("enter HttpState.purgeExpiredCookies(Date)");
        flag = false;
        iterator = cookies.iterator();
_L4:
        boolean flag1 = iterator.hasNext();
        if(flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        if(!((Cookie)iterator.next()).isExpired(date)) goto _L4; else goto _L3
_L3:
        iterator.remove();
        flag = true;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public void setAuthenticationPreemptive(boolean flag)
    {
        preemptive = flag;
    }

    public void setCookiePolicy(int i)
    {
        cookiePolicy = i;
    }

    /**
     * @deprecated Method setCredentials is deprecated
     */

    public void setCredentials(String s, String s1, Credentials credentials)
    {
        this;
        JVM INSTR monitorenter ;
        LOG.trace("enter HttpState.setCredentials(String, String, Credentials)");
        credMap.put(new AuthScope(s1, -1, s, AuthScope.ANY_SCHEME), credentials);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setCredentials is deprecated
     */

    public void setCredentials(AuthScope authscope, Credentials credentials)
    {
        this;
        JVM INSTR monitorenter ;
        if(authscope != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("Authentication scope may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        LOG.trace("enter HttpState.setCredentials(AuthScope, Credentials)");
        credMap.put(authscope, credentials);
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method setProxyCredentials is deprecated
     */

    public void setProxyCredentials(String s, String s1, Credentials credentials)
    {
        this;
        JVM INSTR monitorenter ;
        LOG.trace("enter HttpState.setProxyCredentials(String, String, Credentials");
        proxyCred.put(new AuthScope(s1, -1, s, AuthScope.ANY_SCHEME), credentials);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setProxyCredentials is deprecated
     */

    public void setProxyCredentials(AuthScope authscope, Credentials credentials)
    {
        this;
        JVM INSTR monitorenter ;
        if(authscope != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("Authentication scope may not be null");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        LOG.trace("enter HttpState.setProxyCredentials(AuthScope, Credentials)");
        proxyCred.put(authscope, credentials);
        this;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method toString is deprecated
     */

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("[");
        stringbuffer.append(getCredentialsStringRepresentation(proxyCred));
        stringbuffer.append(" | ");
        stringbuffer.append(getCredentialsStringRepresentation(credMap));
        stringbuffer.append(" | ");
        stringbuffer.append(getCookiesStringRepresentation(cookies));
        stringbuffer.append("]");
        s = stringbuffer.toString();
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    private static final Log LOG;
    public static final String PREEMPTIVE_DEFAULT = "false";
    public static final String PREEMPTIVE_PROPERTY = "httpclient.authentication.preemptive";
    static Class class$org$apache$commons$httpclient$HttpState;
    private int cookiePolicy;
    private ArrayList cookies;
    private HashMap credMap;
    private boolean preemptive;
    private HashMap proxyCred;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$HttpState == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpState");
            class$org$apache$commons$httpclient$HttpState = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpState;
        }
        LOG = LogFactory.getLog(class1);
    }
}
