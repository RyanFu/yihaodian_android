// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;


// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthScheme, AuthPolicy

public class AuthState
{

    public AuthState()
    {
        authScheme = null;
        authRequested = false;
        authAttempted = false;
        preemptive = false;
    }

    public AuthScheme getAuthScheme()
    {
        return authScheme;
    }

    public String getRealm()
    {
        String s;
        if(authScheme != null)
            s = authScheme.getRealm();
        else
            s = null;
        return s;
    }

    public void invalidate()
    {
        authScheme = null;
        authRequested = false;
        authAttempted = false;
        preemptive = false;
    }

    public boolean isAuthAttempted()
    {
        return authAttempted;
    }

    public boolean isAuthRequested()
    {
        return authRequested;
    }

    public boolean isPreemptive()
    {
        return preemptive;
    }

    public void setAuthAttempted(boolean flag)
    {
        authAttempted = flag;
    }

    public void setAuthRequested(boolean flag)
    {
        authRequested = flag;
    }

    public void setAuthScheme(AuthScheme authscheme)
    {
        if(authscheme == null)
        {
            invalidate();
        } else
        {
            if(preemptive && !authScheme.getClass().isInstance(authscheme))
            {
                preemptive = false;
                authAttempted = false;
            }
            authScheme = authscheme;
        }
    }

    public void setPreemptive()
    {
        if(!preemptive)
        {
            if(authScheme != null)
                throw new IllegalStateException("Authentication state already initialized");
            authScheme = AuthPolicy.getAuthScheme("basic");
            preemptive = true;
        }
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("Auth state: auth requested [");
        stringbuffer.append(authRequested);
        stringbuffer.append("]; auth attempted [");
        stringbuffer.append(authAttempted);
        if(authScheme != null)
        {
            stringbuffer.append("]; auth scheme [");
            stringbuffer.append(authScheme.getSchemeName());
            stringbuffer.append("]; realm [");
            stringbuffer.append(authScheme.getRealm());
        }
        stringbuffer.append("] preemptive [");
        stringbuffer.append(preemptive);
        stringbuffer.append("]");
        return stringbuffer.toString();
    }

    public static final String PREEMPTIVE_AUTH_SCHEME = "basic";
    private boolean authAttempted;
    private boolean authRequested;
    private AuthScheme authScheme;
    private boolean preemptive;
}
