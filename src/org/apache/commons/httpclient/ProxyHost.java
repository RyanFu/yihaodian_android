// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.protocol.Protocol;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpHost

public class ProxyHost extends HttpHost
{

    public ProxyHost(String s)
    {
        this(s, -1);
    }

    public ProxyHost(String s, int i)
    {
        super(s, i, Protocol.getProtocol("http"));
    }

    public ProxyHost(ProxyHost proxyhost)
    {
        super(proxyhost);
    }

    @Override
	public Object clone()
    {
        return new ProxyHost(this);
    }
}
