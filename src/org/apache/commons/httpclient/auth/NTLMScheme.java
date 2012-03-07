// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthScheme, MalformedChallengeException, AuthenticationException, NTLM, 
//            InvalidCredentialsException, AuthChallengeParser

public class NTLMScheme
    implements AuthScheme
{

    public NTLMScheme()
    {
        ntlmchallenge = null;
        state = 0;
    }

    public NTLMScheme(String s)
        throws MalformedChallengeException
    {
        ntlmchallenge = null;
        processChallenge(s);
    }

    public static String authenticate(NTCredentials ntcredentials, String s)
        throws AuthenticationException
    {
        LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
        if(ntcredentials == null)
        {
            throw new IllegalArgumentException("Credentials may not be null");
        } else
        {
            String s1 = (new NTLM()).getResponseFor(s, ntcredentials.getUserName(), ntcredentials.getPassword(), ntcredentials.getHost(), ntcredentials.getDomain());
            return "NTLM " + s1;
        }
    }

    public static String authenticate(NTCredentials ntcredentials, String s, String s1)
        throws AuthenticationException
    {
        LOG.trace("enter NTLMScheme.authenticate(NTCredentials, String)");
        if(ntcredentials == null)
        {
            throw new IllegalArgumentException("Credentials may not be null");
        } else
        {
            NTLM ntlm = new NTLM();
            ntlm.setCredentialCharset(s1);
            String s2 = ntlm.getResponseFor(s, ntcredentials.getUserName(), ntcredentials.getPassword(), ntcredentials.getHost(), ntcredentials.getDomain());
            return "NTLM " + s2;
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
        LOG.trace("enter NTLMScheme.authenticate(Credentials, String, String)");
        NTCredentials ntcredentials;
        try
        {
            ntcredentials = (NTCredentials)credentials;
        }
        catch(ClassCastException classcastexception)
        {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
        return authenticate(ntcredentials, ntlmchallenge);
    }

    @Override
	public String authenticate(Credentials credentials, HttpMethod httpmethod)
        throws AuthenticationException
    {
        LOG.trace("enter NTLMScheme.authenticate(Credentials, HttpMethod)");
        if(state == 0)
            throw new IllegalStateException("NTLM authentication process has not been initiated");
        NTCredentials ntcredentials;
        NTLM ntlm;
        String s;
        try
        {
            ntcredentials = (NTCredentials)credentials;
        }
        catch(ClassCastException classcastexception)
        {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
        ntlm = new NTLM();
        ntlm.setCredentialCharset(httpmethod.getParams().getCredentialCharset());
        if(state == 1 || state == 0x7fffffff)
        {
            s = ntlm.getType1Message(ntcredentials.getHost(), ntcredentials.getDomain());
            state = 2;
        } else
        {
            s = ntlm.getType3Message(ntcredentials.getUserName(), ntcredentials.getPassword(), ntcredentials.getHost(), ntcredentials.getDomain(), ntlm.parseType2Message(ntlmchallenge));
            state = 4;
        }
        return "NTLM " + s;
    }

    @Override
	public String getID()
    {
        return ntlmchallenge;
    }

    @Override
	public String getParameter(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Parameter name may not be null");
        else
            return null;
    }

    @Override
	public String getRealm()
    {
        return null;
    }

    @Override
	public String getSchemeName()
    {
        return "ntlm";
    }

    @Override
	public boolean isComplete()
    {
        boolean flag;
        if(state == 4 || state == 0x7fffffff)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public boolean isConnectionBased()
    {
        return true;
    }

    @Override
	public void processChallenge(String s)
        throws MalformedChallengeException
    {
        if(!AuthChallengeParser.extractScheme(s).equalsIgnoreCase(getSchemeName()))
            throw new MalformedChallengeException("Invalid NTLM challenge: " + s);
        int i = s.indexOf(' ');
        if(i != -1)
        {
            ntlmchallenge = s.substring(i, s.length()).trim();
            state = 3;
        } else
        {
            ntlmchallenge = "";
            if(state == 0)
                state = 1;
            else
                state = 0x7fffffff;
        }
    }

    private static final int FAILED = 0x7fffffff;
    private static final int INITIATED = 1;
    private static final Log LOG;
    private static final int TYPE1_MSG_GENERATED = 2;
    private static final int TYPE2_MSG_RECEIVED = 3;
    private static final int TYPE3_MSG_GENERATED = 4;
    private static final int UNINITIATED;
    static Class class$org$apache$commons$httpclient$auth$NTLMScheme;
    private String ntlmchallenge;
    private int state;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$auth$NTLMScheme == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.auth.NTLMScheme");
            class$org$apache$commons$httpclient$auth$NTLMScheme = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$auth$NTLMScheme;
        }
        LOG = LogFactory.getLog(class1);
    }
}
