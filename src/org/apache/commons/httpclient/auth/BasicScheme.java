// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            RFC2617Scheme, MalformedChallengeException, AuthenticationException, InvalidCredentialsException

public class BasicScheme extends RFC2617Scheme
{

    public BasicScheme()
    {
        complete = false;
    }

    public BasicScheme(String s)
        throws MalformedChallengeException
    {
        super(s);
        complete = true;
    }

    public static String authenticate(UsernamePasswordCredentials usernamepasswordcredentials)
    {
        return authenticate(usernamepasswordcredentials, "ISO-8859-1");
    }

    public static String authenticate(UsernamePasswordCredentials usernamepasswordcredentials, String s)
    {
        LOG.trace("enter BasicScheme.authenticate(UsernamePasswordCredentials, String)");
        if(usernamepasswordcredentials == null)
            throw new IllegalArgumentException("Credentials may not be null");
        if(s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("charset may not be null or empty");
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(usernamepasswordcredentials.getUserName());
            stringbuffer.append(":");
            stringbuffer.append(usernamepasswordcredentials.getPassword());
            return "Basic " + EncodingUtil.getAsciiString(Base64.encodeBase64(EncodingUtil.getBytes(stringbuffer.toString(), s)));
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

    @Override
	public String authenticate(Credentials credentials, String s, String s1)
        throws AuthenticationException
    {
        LOG.trace("enter BasicScheme.authenticate(Credentials, String, String)");
        UsernamePasswordCredentials usernamepasswordcredentials;
        try
        {
            usernamepasswordcredentials = (UsernamePasswordCredentials)credentials;
        }
        catch(ClassCastException classcastexception)
        {
            throw new InvalidCredentialsException("Credentials cannot be used for basic authentication: " + credentials.getClass().getName());
        }
        return authenticate(usernamepasswordcredentials);
    }

    @Override
	public String authenticate(Credentials credentials, HttpMethod httpmethod)
        throws AuthenticationException
    {
        LOG.trace("enter BasicScheme.authenticate(Credentials, HttpMethod)");
        if(httpmethod == null)
            throw new IllegalArgumentException("Method may not be null");
        UsernamePasswordCredentials usernamepasswordcredentials;
        try
        {
            usernamepasswordcredentials = (UsernamePasswordCredentials)credentials;
        }
        catch(ClassCastException classcastexception)
        {
            throw new InvalidCredentialsException("Credentials cannot be used for basic authentication: " + credentials.getClass().getName());
        }
        return authenticate(usernamepasswordcredentials, httpmethod.getParams().getCredentialCharset());
    }

    @Override
	public String getSchemeName()
    {
        return "basic";
    }

    @Override
	public boolean isComplete()
    {
        return complete;
    }

    @Override
	public boolean isConnectionBased()
    {
        return false;
    }

    @Override
	public void processChallenge(String s)
        throws MalformedChallengeException
    {
        super.processChallenge(s);
        complete = true;
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$auth$BasicScheme;
    private boolean complete;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$auth$BasicScheme == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.auth.BasicScheme");
            class$org$apache$commons$httpclient$auth$BasicScheme = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$auth$BasicScheme;
        }
        LOG = LogFactory.getLog(class1);
    }
}
