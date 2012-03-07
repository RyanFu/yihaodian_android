// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.logging.Log;

public class GetMethod extends HttpMethodBase
{

    public GetMethod()
    {
        setFollowRedirects(true);
    }

    public GetMethod(String s)
    {
        super(s);
        LOG.trace("enter GetMethod(String)");
        setFollowRedirects(true);
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
	public String getName()
    {
        return "GET";
    }

    @Override
	public void recycle()
    {
        LOG.trace("enter GetMethod.recycle()");
        super.recycle();
        setFollowRedirects(true);
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$GetMethod;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$GetMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.GetMethod");
            class$org$apache$commons$httpclient$methods$GetMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$GetMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
