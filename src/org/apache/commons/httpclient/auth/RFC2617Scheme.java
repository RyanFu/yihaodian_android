// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import java.util.Map;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthScheme, MalformedChallengeException, AuthenticationException, AuthChallengeParser

public abstract class RFC2617Scheme
    implements AuthScheme
{

    public RFC2617Scheme()
    {
        params = null;
    }

    public RFC2617Scheme(String s)
        throws MalformedChallengeException
    {
        params = null;
        processChallenge(s);
    }

    @Override
	public abstract String authenticate(Credentials credentials, String s, String s1)
        throws AuthenticationException;

    @Override
	public abstract String authenticate(Credentials credentials, HttpMethod httpmethod)
        throws AuthenticationException;

    @Override
	public String getID()
    {
        return getRealm();
    }

    @Override
	public String getParameter(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Parameter name may not be null");
        String s1;
        if(params == null)
            s1 = null;
        else
            s1 = (String)params.get(s.toLowerCase());
        return s1;
    }

    protected Map getParameters()
    {
        return params;
    }

    @Override
	public String getRealm()
    {
        return getParameter("realm");
    }

    @Override
	public abstract String getSchemeName();

    @Override
	public abstract boolean isComplete();

    @Override
	public abstract boolean isConnectionBased();

    @Override
	public void processChallenge(String s)
        throws MalformedChallengeException
    {
        if(!AuthChallengeParser.extractScheme(s).equalsIgnoreCase(getSchemeName()))
        {
            throw new MalformedChallengeException("Invalid " + getSchemeName() + " challenge: " + s);
        } else
        {
            params = AuthChallengeParser.extractParams(s);
            return;
        }
    }

    private Map params;
}
