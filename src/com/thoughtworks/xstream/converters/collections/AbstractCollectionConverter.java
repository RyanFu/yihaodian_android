// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.HierarchicalStreams;
import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.mapper.Mapper;

public abstract class AbstractCollectionConverter
    implements Converter
{

    public AbstractCollectionConverter(Mapper mapper1)
    {
        mapper = mapper1;
    }

    @Override
	public abstract boolean canConvert(Class class1);

    protected Object createCollection(Class class1)
    {
        Class class2 = mapper().defaultImplementationOf(class1);
        Object obj;
        try
        {
            obj = class2.newInstance();
        }
        catch(InstantiationException instantiationexception)
        {
            throw new ConversionException("Cannot instantiate " + class2.getName(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ConversionException("Cannot instantiate " + class2.getName(), illegalaccessexception);
        }
        return obj;
    }

    protected Mapper mapper()
    {
        return mapper;
    }

    @Override
	public abstract void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext);

    protected Object readItem(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext, Object obj)
    {
        return unmarshallingcontext.convertAnother(obj, HierarchicalStreams.readClassType(hierarchicalstreamreader, mapper()));
    }

    @Override
	public abstract Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext);

    protected void writeItem(Object obj, MarshallingContext marshallingcontext, HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        if(obj == null)
        {
            hierarchicalstreamwriter.startNode(mapper().serializedClass(null));
            hierarchicalstreamwriter.endNode();
        } else
        {
            ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, mapper().serializedClass(obj.getClass()), obj.getClass());
            marshallingcontext.convertAnother(obj);
            hierarchicalstreamwriter.endNode();
        }
    }

    private final Mapper mapper;
}
