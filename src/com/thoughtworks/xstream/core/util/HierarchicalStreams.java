// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

public class HierarchicalStreams
{

    public HierarchicalStreams()
    {
    }

    public static String readClassAttribute(HierarchicalStreamReader hierarchicalstreamreader, Mapper mapper)
    {
        String s = mapper.aliasForSystemAttribute("resolves-to");
        String s1;
        if(s == null)
            s1 = null;
        else
            s1 = hierarchicalstreamreader.getAttribute(s);
        if(s1 == null)
        {
            String s2 = mapper.aliasForSystemAttribute("class");
            if(s2 != null)
                s1 = hierarchicalstreamreader.getAttribute(s2);
        }
        return s1;
    }

    public static Class readClassType(HierarchicalStreamReader hierarchicalstreamreader, Mapper mapper)
    {
        String s = readClassAttribute(hierarchicalstreamreader, mapper);
        Class class1;
        if(s == null)
            class1 = mapper.realClass(hierarchicalstreamreader.getNodeName());
        else
            class1 = mapper.realClass(s);
        return class1;
    }
}
