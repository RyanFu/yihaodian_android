// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import java.util.*;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            MalformedChallengeException, AuthenticationException, AuthState, AuthScheme, 
//            AuthChallengeException, AuthPolicy

public final class AuthChallengeProcessor
{

    public AuthChallengeProcessor(HttpParams httpparams)
    {
        params = null;
        if(httpparams == null)
        {
            throw new IllegalArgumentException("Parameter collection may not be null");
        } else
        {
            params = httpparams;
            return;
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

    public AuthScheme processChallenge(AuthState authstate, Map map)
        throws MalformedChallengeException, AuthenticationException
    {
        if(authstate == null)
            throw new IllegalArgumentException("Authentication state may not be null");
        if(map == null)
            throw new IllegalArgumentException("Challenge map may not be null");
        if(authstate.isPreemptive() || authstate.getAuthScheme() == null)
            authstate.setAuthScheme(selectAuthScheme(map));
        AuthScheme authscheme = authstate.getAuthScheme();
        String s = authscheme.getSchemeName();
        if(LOG.isDebugEnabled())
            LOG.debug("Using authentication scheme: " + s);
        String s1 = (String)map.get(s.toLowerCase());
        if(s1 == null)
        {
            throw new AuthenticationException(s + " authorization challenge expected, but not found");
        } else
        {
            authscheme.processChallenge(s1);
            LOG.debug("Authorization challenge processed");
            return authscheme;
        }
    }

    public AuthScheme selectAuthScheme(Map map)
        throws AuthChallengeException
    {
        AuthScheme authscheme;
        Iterator iterator;
        if(map == null)
            throw new IllegalArgumentException("Challenge map may not be null");
        Object obj = params.getParameter("http.auth.scheme-priority");
        if(obj == null || ((Collection) (obj)).isEmpty())
            obj = AuthPolicy.getDefaultAuthPrefs();
        if(LOG.isDebugEnabled())
            LOG.debug("Supported authentication schemes in the order of preference: " + obj);
        authscheme = null;
        iterator = ((Collection) (obj)).iterator();
_L2:
        String s;
        if(iterator.hasNext())
        {
label0:
            {
                s = (String)iterator.next();
                if((String)map.get(s.toLowerCase()) == null)
                    break label0;
                if(LOG.isInfoEnabled())
                    LOG.info(s + " authentication scheme selected");
                AuthScheme authscheme1;
                try
                {
                    authscheme1 = AuthPolicy.getAuthScheme(s);
                }
                catch(IllegalStateException illegalstateexception)
                {
                    throw new AuthChallengeException(illegalstateexception.getMessage());
                }
                authscheme = authscheme1;
            }
        }
        if(authscheme == null)
            throw new AuthChallengeException("Unable to respond to any of these challenges: " + map);
        else
            return authscheme;
        if(LOG.isDebugEnabled())
            LOG.debug("Challenge for " + s + " authentication scheme not available");
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
    private HttpParams params;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$auth$AuthChallengeProcessor == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.auth.AuthChallengeProcessor");
            class$org$apache$commons$httpclient$auth$AuthChallengeProcessor = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
        }
        LOG = LogFactory.getLog(class1);
    }
}
