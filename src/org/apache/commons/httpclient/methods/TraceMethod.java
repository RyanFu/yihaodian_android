// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class TraceMethod extends HttpMethodBase
{

    public TraceMethod(String s)
    {
        super(s);
        setFollowRedirects(false);
    }

    @Override
	public String getName()
    {
        return "TRACE";
    }

    @Override
	public void recycle()
    {
        super.recycle();
        setFollowRedirects(false);
    }
}
