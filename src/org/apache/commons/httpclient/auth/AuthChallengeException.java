// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;


// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthenticationException

public class AuthChallengeException extends AuthenticationException
{

    public AuthChallengeException()
    {
    }

    public AuthChallengeException(String s)
    {
        super(s);
    }

    public AuthChallengeException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
