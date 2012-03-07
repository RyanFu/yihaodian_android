// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CharArrayConverter
    implements Converter
{

    public CharArrayConverter()
    {
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(class1.isArray() && class1.getComponentType().equals(Character.TYPE))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        hierarchicalstreamwriter.setValue(new String((char[])obj));
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return hierarchicalstreamreader.getValue().toCharArray();
    }
}
