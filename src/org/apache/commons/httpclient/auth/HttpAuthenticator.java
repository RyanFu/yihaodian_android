// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthenticationException, AuthScheme, CredentialsNotAvailableException, InvalidCredentialsException, 
//            BasicScheme, MalformedChallengeException, NTLMScheme, AuthChallengeParser, 
//            DigestScheme

public final class HttpAuthenticator
{

    public HttpAuthenticator()
    {
    }

    public static boolean authenticate(AuthScheme authscheme, HttpMethod httpmethod, HttpConnection httpconnection, HttpState httpstate)
        throws AuthenticationException
    {
        LOG.trace("enter HttpAuthenticator.authenticate(AuthScheme, HttpMethod, HttpConnection, HttpState)");
        return doAuthenticate(authscheme, httpmethod, httpconnection, httpstate, false);
    }

    public static boolean authenticateDefault(HttpMethod httpmethod, HttpConnection httpconnection, HttpState httpstate)
        throws AuthenticationException
    {
        LOG.trace("enter HttpAuthenticator.authenticateDefault(HttpMethod, HttpConnection, HttpState)");
        return doAuthenticateDefault(httpmethod, httpconnection, httpstate, false);
    }

    public static boolean authenticateProxy(AuthScheme authscheme, HttpMethod httpmethod, HttpConnection httpconnection, HttpState httpstate)
        throws AuthenticationException
    {
        LOG.trace("enter HttpAuthenticator.authenticateProxy(AuthScheme, HttpMethod, HttpState)");
        return doAuthenticate(authscheme, httpmethod, httpconnection, httpstate, true);
    }

    public static boolean authenticateProxyDefault(HttpMethod httpmethod, HttpConnection httpconnection, HttpState httpstate)
        throws AuthenticationException
    {
        LOG.trace("enter HttpAuthenticator.authenticateProxyDefault(HttpMethod, HttpState)");
        return doAuthenticateDefault(httpmethod, httpconnection, httpstate, true);
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

    private static boolean doAuthenticate(AuthScheme authscheme, HttpMethod httpmethod, HttpConnection httpconnection, HttpState httpstate, boolean flag)
        throws AuthenticationException
    {
        if(authscheme == null)
            throw new IllegalArgumentException("Authentication scheme may not be null");
        if(httpmethod == null)
            throw new IllegalArgumentException("HTTP method may not be null");
        if(httpstate == null)
            throw new IllegalArgumentException("HTTP state may not be null");
        String s = null;
        String s1;
        org.apache.commons.httpclient.Credentials credentials;
        if(httpconnection != null)
            if(flag)
            {
                s = httpconnection.getProxyHost();
            } else
            {
                s = httpmethod.getParams().getVirtualHost();
                if(s == null)
                    s = httpconnection.getHost();
            }
        s1 = authscheme.getRealm();
        if(LOG.isDebugEnabled())
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("Using credentials for ");
            if(s1 == null)
            {
                stringbuffer.append("default");
            } else
            {
                stringbuffer.append('\'');
                stringbuffer.append(s1);
                stringbuffer.append('\'');
            }
            stringbuffer.append(" authentication realm at ");
            stringbuffer.append(s);
            LOG.debug(stringbuffer.toString());
        }
        if(flag)
            credentials = httpstate.getProxyCredentials(s1, s);
        else
            credentials = httpstate.getCredentials(s1, s);
        if(credentials == null)
        {
            StringBuffer stringbuffer1 = new StringBuffer();
            stringbuffer1.append("No credentials available for the ");
            if(s1 == null)
            {
                stringbuffer1.append("default");
            } else
            {
                stringbuffer1.append('\'');
                stringbuffer1.append(s1);
                stringbuffer1.append('\'');
            }
            stringbuffer1.append(" authentication realm at ");
            stringbuffer1.append(s);
            throw new CredentialsNotAvailableException(stringbuffer1.toString());
        }
        String s2 = authscheme.authenticate(credentials, httpmethod);
        boolean flag1;
        if(s2 != null)
        {
            String s3;
            if(flag)
                s3 = "Proxy-Authorization";
            else
                s3 = "Authorization";
            httpmethod.addRequestHeader(new Header(s3, s2, true));
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    private static boolean doAuthenticateDefault(HttpMethod httpmethod, HttpConnection httpconnection, HttpState httpstate, boolean flag)
        throws AuthenticationException
    {
        if(httpmethod == null)
            throw new IllegalArgumentException("HTTP method may not be null");
        if(httpstate == null)
            throw new IllegalArgumentException("HTTP state may not be null");
        String s = null;
        org.apache.commons.httpclient.Credentials credentials;
        boolean flag1;
        if(httpconnection != null)
            if(flag)
                s = httpconnection.getProxyHost();
            else
                s = httpconnection.getHost();
        if(flag)
            credentials = httpstate.getProxyCredentials(null, s);
        else
            credentials = httpstate.getCredentials(null, s);
        if(credentials == null)
        {
            flag1 = false;
        } else
        {
            if(!(credentials instanceof UsernamePasswordCredentials))
                throw new InvalidCredentialsException("Credentials cannot be used for basic authentication: " + credentials.toString());
            String s1 = BasicScheme.authenticate((UsernamePasswordCredentials)credentials, httpmethod.getParams().getCredentialCharset());
            if(s1 != null)
            {
                String s2;
                if(flag)
                    s2 = "Proxy-Authorization";
                else
                    s2 = "Authorization";
                httpmethod.addRequestHeader(new Header(s2, s1, true));
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        }
        return flag1;
    }

    public static AuthScheme selectAuthScheme(Header aheader[])
        throws MalformedChallengeException
    {
        HashMap hashmap;
        int i;
        LOG.trace("enter HttpAuthenticator.selectAuthScheme(Header[])");
        if(aheader == null)
            throw new IllegalArgumentException("Array of challenges may not be null");
        if(aheader.length == 0)
            throw new IllegalArgumentException("Array of challenges may not be empty");
        hashmap = new HashMap(aheader.length);
        i = 0;
_L8:
        if(i < aheader.length) goto _L2; else goto _L1
_L1:
        String s1 = (String)hashmap.get("ntlm");
        if(s1 == null) goto _L4; else goto _L3
_L3:
        Object obj = new NTLMScheme(s1);
_L6:
        return ((AuthScheme) (obj));
_L2:
        String s = aheader[i].getValue();
        hashmap.put(AuthChallengeParser.extractScheme(s), s);
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        String s2 = (String)hashmap.get("digest");
        if(s2 != null)
        {
            obj = new DigestScheme(s2);
            continue; /* Loop/switch isn't completed */
        }
        String s3 = (String)hashmap.get("basic");
        if(s3 == null)
            break; /* Loop/switch isn't completed */
        obj = new BasicScheme(s3);
        if(true) goto _L6; else goto _L5
_L5:
        throw new UnsupportedOperationException("Authentication scheme(s) not supported: " + hashmap.toString());
        if(true) goto _L8; else goto _L7
_L7:
    }

    private static final Log LOG;
    public static final String PROXY_AUTH = "Proxy-Authenticate";
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    public static final String WWW_AUTH = "WWW-Authenticate";
    public static final String WWW_AUTH_RESP = "Authorization";
    static Class class$org$apache$commons$httpclient$auth$HttpAuthenticator;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$auth$HttpAuthenticator == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.auth.HttpAuthenticator");
            class$org$apache$commons$httpclient$auth$HttpAuthenticator = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$auth$HttpAuthenticator;
        }
        LOG = LogFactory.getLog(class1);
    }
}
