// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.enums;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class EnumConverter
    implements Converter
{

    public EnumConverter()
    {
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(class1.isEnum() || java/lang/Enum.isAssignableFrom(class1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        hierarchicalstreamwriter.setValue(((Enum)obj).name());
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Class class1 = unmarshallingcontext.getRequiredType();
        if(class1.getSuperclass() != java/lang/Enum)
            class1 = class1.getSuperclass();
        return Enum.valueOf(class1, hierarchicalstreamreader.getValue());
    }
}
