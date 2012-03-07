// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import java.util.*;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthScheme

public abstract class AuthPolicy
{

    public AuthPolicy()
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

    /**
     * @deprecated Method getAuthScheme is deprecated
     */

    public static AuthScheme getAuthScheme(String s)
        throws IllegalStateException
    {
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorenter ;
        if(s != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("Id may not be null");
        Exception exception;
        exception;
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
        throw exception;
        Class class1 = (Class)SCHEMES.get(s.toLowerCase());
        if(class1 == null)
            break MISSING_BLOCK_LABEL_124;
        AuthScheme authscheme = (AuthScheme)class1.newInstance();
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
        return authscheme;
        Exception exception1;
        exception1;
        LOG.error("Error initializing authentication scheme: " + s, exception1);
        throw new IllegalStateException(s + " authentication scheme implemented by " + class1.getName() + " could not be initialized");
        throw new IllegalStateException("Unsupported authentication scheme " + s);
    }

    /**
     * @deprecated Method getDefaultAuthPrefs is deprecated
     */

    public static List getDefaultAuthPrefs()
    {
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorenter ;
        List list = (List)SCHEME_LIST.clone();
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
        return list;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method registerAuthScheme is deprecated
     */

    public static void registerAuthScheme(String s, Class class1)
    {
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorenter ;
        if(s != null)
            break MISSING_BLOCK_LABEL_25;
        throw new IllegalArgumentException("Id may not be null");
        Exception exception;
        exception;
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
        throw exception;
        if(class1 != null)
            break MISSING_BLOCK_LABEL_39;
        throw new IllegalArgumentException("Authentication scheme class may not be null");
        SCHEMES.put(s.toLowerCase(), class1);
        SCHEME_LIST.add(s.toLowerCase());
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
    }

    /**
     * @deprecated Method unregisterAuthScheme is deprecated
     */

    public static void unregisterAuthScheme(String s)
    {
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorenter ;
        if(s != null)
            break MISSING_BLOCK_LABEL_23;
        throw new IllegalArgumentException("Id may not be null");
        Exception exception;
        exception;
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
        throw exception;
        SCHEMES.remove(s.toLowerCase());
        SCHEME_LIST.remove(s.toLowerCase());
        org/apache/commons/httpclient/auth/AuthPolicy;
        JVM INSTR monitorexit ;
    }

    public static final String AUTH_SCHEME_PRIORITY = "http.auth.scheme-priority";
    public static final String BASIC = "Basic";
    public static final String DIGEST = "Digest";
    protected static final Log LOG;
    public static final String NTLM = "NTLM";
    private static final HashMap SCHEMES = new HashMap();
    private static final ArrayList SCHEME_LIST = new ArrayList();
    static Class class$org$apache$commons$httpclient$auth$AuthPolicy;
    static Class class$org$apache$commons$httpclient$auth$BasicScheme;
    static Class class$org$apache$commons$httpclient$auth$DigestScheme;
    static Class class$org$apache$commons$httpclient$auth$NTLMScheme;

    static 
    {
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        if(class$org$apache$commons$httpclient$auth$NTLMScheme == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.auth.NTLMScheme");
            class$org$apache$commons$httpclient$auth$NTLMScheme = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$auth$NTLMScheme;
        }
        registerAuthScheme("NTLM", class1);
        if(class$org$apache$commons$httpclient$auth$DigestScheme == null)
        {
            class2 = _mthclass$("org.apache.commons.httpclient.auth.DigestScheme");
            class$org$apache$commons$httpclient$auth$DigestScheme = class2;
        } else
        {
            class2 = class$org$apache$commons$httpclient$auth$DigestScheme;
        }
        registerAuthScheme("Digest", class2);
        if(class$org$apache$commons$httpclient$auth$BasicScheme == null)
        {
            class3 = _mthclass$("org.apache.commons.httpclient.auth.BasicScheme");
            class$org$apache$commons$httpclient$auth$BasicScheme = class3;
        } else
        {
            class3 = class$org$apache$commons$httpclient$auth$BasicScheme;
        }
        registerAuthScheme("Basic", class3);
        if(class$org$apache$commons$httpclient$auth$AuthPolicy == null)
        {
            class4 = _mthclass$("org.apache.commons.httpclient.auth.AuthPolicy");
            class$org$apache$commons$httpclient$auth$AuthPolicy = class4;
        } else
        {
            class4 = class$org$apache$commons$httpclient$auth$AuthPolicy;
        }
        LOG = LogFactory.getLog(class4);
    }
}
