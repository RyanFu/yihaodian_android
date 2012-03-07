// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.Serializable;
import org.apache.commons.httpclient.util.LangUtils;

public class NameValuePair
    implements Serializable
{

    public NameValuePair()
    {
        this(null, null);
    }

    public NameValuePair(String s, String s1)
    {
        name = null;
        value = null;
        name = s;
        value = s1;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(this == obj)
            flag = true;
        else
        if(obj instanceof NameValuePair)
        {
            NameValuePair namevaluepair = (NameValuePair)obj;
            if(LangUtils.equals(name, namevaluepair.name) && LangUtils.equals(value, namevaluepair.value))
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(17, name), value);
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setValue(String s)
    {
        value = s;
    }

    @Override
	public String toString()
    {
        return "name=" + name + ", " + "value=" + value;
    }

    private String name;
    private String value;
}
