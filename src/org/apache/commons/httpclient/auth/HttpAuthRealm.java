// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;


// Referenced classes of package org.apache.commons.httpclient.auth:
//            AuthScope

public class HttpAuthRealm extends AuthScope
{

    public HttpAuthRealm(String s, String s1)
    {
        super(s, -1, s1, AuthScope.ANY_SCHEME);
    }
}
