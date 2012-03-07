// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthScheme, MalformedChallengeException, AuthenticationException

public abstract class AuthSchemeBase
    implements AuthScheme
{

    public AuthSchemeBase(String s)
        throws MalformedChallengeException
    {
        challenge = null;
        if(s == null)
        {
            throw new IllegalArgumentException("Challenge may not be null");
        } else
        {
            challenge = s;
            return;
        }
    }

    @Override
	public abstract String authenticate(Credentials credentials, String s, String s1)
        throws AuthenticationException;

    @Override
	public abstract String authenticate(Credentials credentials, HttpMethod httpmethod)
        throws AuthenticationException;

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof AuthSchemeBase)
            flag = challenge.equals(((AuthSchemeBase)obj).challenge);
        else
            flag = super.equals(obj);
        return flag;
    }

    @Override
	public abstract String getID();

    @Override
	public abstract String getParameter(String s);

    @Override
	public abstract String getRealm();

    @Override
	public abstract String getSchemeName();

    @Override
	public int hashCode()
    {
        return challenge.hashCode();
    }

    @Override
	public abstract boolean isComplete();

    @Override
	public abstract boolean isConnectionBased();

    @Override
	public abstract void processChallenge(String s)
        throws MalformedChallengeException;

    @Override
	public String toString()
    {
        return challenge;
    }

    private String challenge;
}
