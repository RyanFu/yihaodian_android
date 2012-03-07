// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthenticationException, MalformedChallengeException

public interface AuthScheme
{

    public abstract String authenticate(Credentials credentials, String s, String s1)
        throws AuthenticationException;

    public abstract String authenticate(Credentials credentials, HttpMethod httpmethod)
        throws AuthenticationException;

    public abstract String getID();

    public abstract String getParameter(String s);

    public abstract String getRealm();

    public abstract String getSchemeName();

    public abstract boolean isComplete();

    public abstract boolean isConnectionBased();

    public abstract void processChallenge(String s)
        throws MalformedChallengeException;
}
