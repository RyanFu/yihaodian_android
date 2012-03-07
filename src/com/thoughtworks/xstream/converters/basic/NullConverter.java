// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.*;

public class NullConverter
    implements Converter
{

    public NullConverter()
    {
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
        if(class1 == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class2;
        if(class$com$thoughtworks$xstream$mapper$Mapper$Null == null)
        {
            class2 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper$Null");
            class$com$thoughtworks$xstream$mapper$Mapper$Null = class2;
        } else
        {
            class2 = class$com$thoughtworks$xstream$mapper$Mapper$Null;
        }
        if(!class2.isAssignableFrom(class1)) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, "null", null);
        hierarchicalstreamwriter.endNode();
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return null;
    }

    static Class class$com$thoughtworks$xstream$mapper$Mapper$Null;
}
