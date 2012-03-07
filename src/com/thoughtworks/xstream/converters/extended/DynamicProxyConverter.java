// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyConverter
    implements Converter
{

    public DynamicProxyConverter(Mapper mapper1)
    {
        Class class1;
        if(class$com$thoughtworks$xstream$converters$extended$DynamicProxyConverter == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.converters.extended.DynamicProxyConverter");
            class$com$thoughtworks$xstream$converters$extended$DynamicProxyConverter = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$converters$extended$DynamicProxyConverter;
        }
        this(mapper1, class1.getClassLoader());
    }

    public DynamicProxyConverter(Mapper mapper1, ClassLoader classloader)
    {
        classLoader = classloader;
        mapper = mapper1;
    }

    private void addInterfacesToXml(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        Class aclass[] = obj.getClass().getInterfaces();
        for(int i = 0; i < aclass.length; i++)
        {
            Class class1 = aclass[i];
            hierarchicalstreamwriter.startNode("interface");
            hierarchicalstreamwriter.setValue(mapper.serializedClass(class1));
            hierarchicalstreamwriter.endNode();
        }

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
	public boolean canConvert(Class class1)
    {
        Class class2;
        boolean flag;
        if(class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy == null)
        {
            class2 = _mthclass$("com.thoughtworks.xstream.mapper.DynamicProxyMapper$DynamicProxy");
            class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy = class2;
        } else
        {
            class2 = class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy;
        }
        if(class1.equals(class2) || Proxy.isProxyClass(class1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        InvocationHandler invocationhandler = Proxy.getInvocationHandler(obj);
        addInterfacesToXml(obj, hierarchicalstreamwriter);
        hierarchicalstreamwriter.startNode("handler");
        String s = mapper.aliasForSystemAttribute("class");
        if(s != null)
            hierarchicalstreamwriter.addAttribute(s, mapper.serializedClass(invocationhandler.getClass()));
        marshallingcontext.convertAnother(invocationhandler);
        hierarchicalstreamwriter.endNode();
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        ArrayList arraylist = new ArrayList();
        InvocationHandler invocationhandler = null;
        while(hierarchicalstreamreader.hasMoreChildren()) 
        {
            hierarchicalstreamreader.moveDown();
            String s = hierarchicalstreamreader.getNodeName();
            if(s.equals("interface"))
                arraylist.add(mapper.realClass(hierarchicalstreamreader.getValue()));
            else
            if(s.equals("handler"))
            {
                String s1 = mapper.aliasForSystemAttribute("class");
                if(s1 != null)
                    invocationhandler = (InvocationHandler)unmarshallingcontext.convertAnother(null, mapper.realClass(hierarchicalstreamreader.getAttribute(s1)));
            }
            hierarchicalstreamreader.moveUp();
        }
        if(invocationhandler == null)
        {
            throw new ConversionException("No InvocationHandler specified for dynamic proxy");
        } else
        {
            Class aclass[] = new Class[arraylist.size()];
            arraylist.toArray(aclass);
            return Proxy.newProxyInstance(classLoader, aclass, invocationhandler);
        }
    }

    static Class class$com$thoughtworks$xstream$converters$extended$DynamicProxyConverter;
    static Class class$com$thoughtworks$xstream$mapper$DynamicProxyMapper$DynamicProxy;
    private ClassLoader classLoader;
    private Mapper mapper;
}
