// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import java.lang.reflect.Proxy;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class DynamicProxyMapper extends MapperWrapper
{
    public static class DynamicProxy
    {

        public DynamicProxy()
        {
        }
    }


    public DynamicProxyMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public DynamicProxyMapper(ClassMapper classmapper, String s)
    {
        this(((Mapper) (classmapper)), s);
    }

    public DynamicProxyMapper(Mapper mapper)
    {
        this(mapper, "dynamic-proxy");
    }

    public DynamicProxyMapper(Mapper mapper, String s)
    {
        super(mapper);
        alias = s;
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

    public String getAlias()
    {
        return alias;
    }

    @Override
	public Class realClass(String s)
    {
        Class class1;
        if(s.equals(alias))
        {
            if(class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.DynamicProxyMapper$DynamicProxy");
                class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy;
            }
        } else
        {
            class1 = super.realClass(s);
        }
        return class1;
    }

    @Override
	public String serializedClass(Class class1)
    {
        String s;
        if(Proxy.isProxyClass(class1))
            s = alias;
        else
            s = super.serializedClass(class1);
        return s;
    }

    public void setAlias(String s)
    {
        alias = s;
    }

    static Class class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy;
    private String alias;
}
