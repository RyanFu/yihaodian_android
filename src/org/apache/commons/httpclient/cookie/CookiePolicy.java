// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.cookie;

import java.util.*;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.cookie:
//            CookieSpec, RFC2109Spec, CookieSpecBase, NetscapeDraftSpec

public abstract class CookiePolicy
{

    public CookiePolicy()
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

    public static CookieSpec getCompatibilitySpec()
    {
        return getSpecByPolicy(0);
    }

    public static CookieSpec getCookieSpec(String s)
        throws IllegalStateException
    {
        if(s == null)
            throw new IllegalArgumentException("Id may not be null");
        Class class1 = (Class)SPECS.get(s.toLowerCase());
        if(class1 != null)
        {
            CookieSpec cookiespec;
            try
            {
                cookiespec = (CookieSpec)class1.newInstance();
            }
            catch(Exception exception)
            {
                LOG.error("Error initializing cookie spec: " + s, exception);
                throw new IllegalStateException(s + " cookie spec implemented by " + class1.getName() + " could not be initialized");
            }
            return cookiespec;
        } else
        {
            throw new IllegalStateException("Unsupported cookie spec " + s);
        }
    }

    public static int getDefaultPolicy()
    {
        return defaultPolicy;
    }

    public static CookieSpec getDefaultSpec()
    {
        CookieSpec cookiespec = getCookieSpec("default");
        Object obj = cookiespec;
_L2:
        return ((CookieSpec) (obj));
        IllegalStateException illegalstateexception;
        illegalstateexception;
        LOG.warn("Default cookie policy is not registered");
        obj = new RFC2109Spec();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static CookieSpec getSpecByPolicy(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 34
    //                   1 45
    //                   2 56;
           goto _L1 _L2 _L3 _L4
_L1:
        Object obj = getDefaultSpec();
_L6:
        return ((CookieSpec) (obj));
_L2:
        obj = new CookieSpecBase();
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new NetscapeDraftSpec();
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new RFC2109Spec();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static CookieSpec getSpecByVersion(int i)
    {
        i;
        JVM INSTR tableswitch 0 1: default 24
    //                   0 30
    //                   1 41;
           goto _L1 _L2 _L3
_L1:
        Object obj = getDefaultSpec();
_L5:
        return ((CookieSpec) (obj));
_L2:
        obj = new NetscapeDraftSpec();
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new RFC2109Spec();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static void registerCookieSpec(String s, Class class1)
    {
        if(s == null)
            throw new IllegalArgumentException("Id may not be null");
        if(class1 == null)
        {
            throw new IllegalArgumentException("Cookie spec class may not be null");
        } else
        {
            SPECS.put(s.toLowerCase(), class1);
            return;
        }
    }

    public static void setDefaultPolicy(int i)
    {
        defaultPolicy = i;
    }

    public static void unregisterCookieSpec(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Id may not be null");
        } else
        {
            SPECS.remove(s.toLowerCase());
            return;
        }
    }

    public static final String BROWSER_COMPATIBILITY = "compatibility";
    public static final int COMPATIBILITY = 0;
    public static final String DEFAULT = "default";
    public static final String IGNORE_COOKIES = "ignoreCookies";
    protected static final Log LOG;
    public static final String NETSCAPE = "netscape";
    public static final int NETSCAPE_DRAFT = 1;
    public static final int RFC2109 = 2;
    public static final String RFC_2109 = "rfc2109";
    private static Map SPECS = Collections.synchronizedMap(new HashMap());
    static Class class$org$apache$commons$httpclient$cookie$CookiePolicy;
    static Class class$org$apache$commons$httpclient$cookie$CookieSpecBase;
    static Class class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec;
    static Class class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec;
    static Class class$org$apache$commons$httpclient$cookie$RFC2109Spec;
    private static int defaultPolicy = 2;

    static 
    {
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        Class class5;
        Class class6;
        if(class$org$apache$commons$httpclient$cookie$RFC2109Spec == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.cookie.RFC2109Spec");
            class$org$apache$commons$httpclient$cookie$RFC2109Spec = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$cookie$RFC2109Spec;
        }
        registerCookieSpec("default", class1);
        if(class$org$apache$commons$httpclient$cookie$RFC2109Spec == null)
        {
            class2 = _mthclass$("org.apache.commons.httpclient.cookie.RFC2109Spec");
            class$org$apache$commons$httpclient$cookie$RFC2109Spec = class2;
        } else
        {
            class2 = class$org$apache$commons$httpclient$cookie$RFC2109Spec;
        }
        registerCookieSpec("rfc2109", class2);
        if(class$org$apache$commons$httpclient$cookie$CookieSpecBase == null)
        {
            class3 = _mthclass$("org.apache.commons.httpclient.cookie.CookieSpecBase");
            class$org$apache$commons$httpclient$cookie$CookieSpecBase = class3;
        } else
        {
            class3 = class$org$apache$commons$httpclient$cookie$CookieSpecBase;
        }
        registerCookieSpec("compatibility", class3);
        if(class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec == null)
        {
            class4 = _mthclass$("org.apache.commons.httpclient.cookie.NetscapeDraftSpec");
            class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec = class4;
        } else
        {
            class4 = class$org$apache$commons$httpclient$cookie$NetscapeDraftSpec;
        }
        registerCookieSpec("netscape", class4);
        if(class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec == null)
        {
            class5 = _mthclass$("org.apache.commons.httpclient.cookie.IgnoreCookiesSpec");
            class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec = class5;
        } else
        {
            class5 = class$org$apache$commons$httpclient$cookie$IgnoreCookiesSpec;
        }
        registerCookieSpec("ignoreCookies", class5);
        if(class$org$apache$commons$httpclient$cookie$CookiePolicy == null)
        {
            class6 = _mthclass$("org.apache.commons.httpclient.cookie.CookiePolicy");
            class$org$apache$commons$httpclient$cookie$CookiePolicy = class6;
        } else
        {
            class6 = class$org$apache$commons$httpclient$cookie$CookiePolicy;
        }
        LOG = LogFactory.getLog(class6);
    }
}
