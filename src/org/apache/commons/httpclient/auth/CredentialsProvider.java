// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            CredentialsNotAvailableException, AuthScheme

public interface CredentialsProvider
{

    public abstract Credentials getCredentials(AuthScheme authscheme, String s, int i, boolean flag)
        throws CredentialsNotAvailableException;

    public static final String PROVIDER = "http.authentication.credential-provider";
}
