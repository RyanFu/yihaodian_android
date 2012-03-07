// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;


// Referenced classes of package org.apache.commons.httpclient.methods:
//            EntityEnclosingMethod

public class PutMethod extends EntityEnclosingMethod
{

    public PutMethod()
    {
    }

    public PutMethod(String s)
    {
        super(s);
    }

    @Override
	public String getName()
    {
        return "PUT";
    }
}
