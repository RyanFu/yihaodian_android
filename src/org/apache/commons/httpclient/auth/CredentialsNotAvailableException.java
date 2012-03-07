// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;


// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthenticationException

public class CredentialsNotAvailableException extends AuthenticationException
{

    public CredentialsNotAvailableException()
    {
    }

    public CredentialsNotAvailableException(String s)
    {
        super(s);
    }

    public CredentialsNotAvailableException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
