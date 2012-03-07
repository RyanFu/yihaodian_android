// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class DeleteMethod extends HttpMethodBase
{

    public DeleteMethod()
    {
    }

    public DeleteMethod(String s)
    {
        super(s);
    }

    @Override
	public String getName()
    {
        return "DELETE";
    }
}
