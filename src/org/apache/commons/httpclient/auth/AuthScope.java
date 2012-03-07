// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.util.LangUtils;

public class AuthScope
{

    public AuthScope(String s, int i)
    {
        this(s, i, ANY_REALM, ANY_SCHEME);
    }

    public AuthScope(String s, int i, String s1)
    {
        this(s, i, s1, ANY_SCHEME);
    }

    public AuthScope(String s, int i, String s1, String s2)
    {
        scheme = null;
        realm = null;
        host = null;
        port = -1;
        String s3;
        int j;
        String s4;
        String s5;
        if(s == null)
            s3 = ANY_HOST;
        else
            s3 = s.toLowerCase();
        host = s3;
        if(i < 0)
            j = -1;
        else
            j = i;
        port = j;
        if(s1 == null)
            s4 = ANY_REALM;
        else
            s4 = s1;
        realm = s4;
        if(s2 == null)
            s5 = ANY_SCHEME;
        else
            s5 = s2.toUpperCase();
        scheme = s5;
    }

    public AuthScope(AuthScope authscope)
    {
        scheme = null;
        realm = null;
        host = null;
        port = -1;
        if(authscope == null)
        {
            throw new IllegalArgumentException("Scope may not be null");
        } else
        {
            host = authscope.getHost();
            port = authscope.getPort();
            realm = authscope.getRealm();
            scheme = authscope.getScheme();
            return;
        }
    }

    private static boolean paramsEqual(int i, int j)
    {
        boolean flag;
        if(i == j)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean paramsEqual(String s, String s1)
    {
        boolean flag;
        if(s == null)
        {
            if(s == s1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = s.equals(s1);
        }
        return flag;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(obj == this)
            flag = true;
        else
        if(!(obj instanceof AuthScope))
        {
            flag = super.equals(obj);
        } else
        {
            AuthScope authscope = (AuthScope)obj;
            if(paramsEqual(host, authscope.host) && paramsEqual(port, authscope.port) && paramsEqual(realm, authscope.realm) && paramsEqual(scheme, authscope.scheme))
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    public String getRealm()
    {
        return realm;
    }

    public String getScheme()
    {
        return scheme;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, host), port), realm), scheme);
    }

    public int match(AuthScope authscope)
    {
        int i = 0;
        if(!paramsEqual(scheme, authscope.scheme)) goto _L2; else goto _L1
_L1:
        i = 0 + 1;
_L10:
        if(!paramsEqual(realm, authscope.realm)) goto _L4; else goto _L3
_L3:
        i += 2;
_L13:
        if(!paramsEqual(port, authscope.port)) goto _L6; else goto _L5
_L5:
        i += 4;
_L15:
        if(!paramsEqual(host, authscope.host)) goto _L8; else goto _L7
_L7:
        i += 8;
_L17:
        int j = i;
_L11:
        return j;
_L2:
        if(scheme == ANY_SCHEME || authscope.scheme == ANY_SCHEME) goto _L10; else goto _L9
_L9:
        j = -1;
          goto _L11
_L4:
        if(realm == ANY_REALM || authscope.realm == ANY_REALM) goto _L13; else goto _L12
_L12:
        j = -1;
          goto _L11
_L6:
        if(port == -1 || authscope.port == -1) goto _L15; else goto _L14
_L14:
        j = -1;
          goto _L11
_L8:
        if(host == ANY_HOST || authscope.host == ANY_HOST) goto _L17; else goto _L16
_L16:
        j = -1;
          goto _L11
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(scheme != null)
        {
            stringbuffer.append(scheme.toUpperCase());
            stringbuffer.append(' ');
        }
        if(realm != null)
        {
            stringbuffer.append('\'');
            stringbuffer.append(realm);
            stringbuffer.append('\'');
        } else
        {
            stringbuffer.append("<any realm>");
        }
        if(host != null)
        {
            stringbuffer.append('@');
            stringbuffer.append(host);
            if(port >= 0)
            {
                stringbuffer.append(':');
                stringbuffer.append(port);
            }
        }
        return stringbuffer.toString();
    }

    public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
    public static final String ANY_HOST = null;
    public static final int ANY_PORT = -1;
    public static final String ANY_REALM = null;
    public static final String ANY_SCHEME = null;
    private String host;
    private int port;
    private String realm;
    private String scheme;

}
