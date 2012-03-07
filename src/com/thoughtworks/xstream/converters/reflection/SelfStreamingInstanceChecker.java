// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class SelfStreamingInstanceChecker
    implements Converter
{

    public SelfStreamingInstanceChecker(Converter converter, Object obj)
    {
        defaultConverter = converter;
        self = obj;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(class1 == self.getClass())
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        if(obj == self)
        {
            throw new ConversionException("Cannot marshal the XStream instance in action");
        } else
        {
            defaultConverter.marshal(obj, hierarchicalstreamwriter, marshallingcontext);
            return;
        }
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return defaultConverter.unmarshal(hierarchicalstreamreader, unmarshallingcontext);
    }

    private Converter defaultConverter;
    private final Object self;
}
