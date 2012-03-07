// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.MarshallingStrategy;
import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            TreeMarshaller, TreeUnmarshaller, DefaultConverterLookup

public abstract class AbstractTreeMarshallingStrategy
    implements MarshallingStrategy
{

    public AbstractTreeMarshallingStrategy()
    {
    }

    protected abstract TreeMarshaller createMarshallingContext(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper);

    protected abstract TreeUnmarshaller createUnmarshallingContext(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper);

    @Override
	public void marshal(HierarchicalStreamWriter hierarchicalstreamwriter, Object obj, ConverterLookup converterlookup, Mapper mapper, DataHolder dataholder)
    {
        createMarshallingContext(hierarchicalstreamwriter, converterlookup, mapper).start(obj, dataholder);
    }

    @Override
	public void marshal(HierarchicalStreamWriter hierarchicalstreamwriter, Object obj, DefaultConverterLookup defaultconverterlookup, ClassMapper classmapper, DataHolder dataholder)
    {
        marshal(hierarchicalstreamwriter, obj, ((ConverterLookup) (defaultconverterlookup)), ((Mapper) (classmapper)), dataholder);
    }

    @Override
	public Object unmarshal(Object obj, HierarchicalStreamReader hierarchicalstreamreader, DataHolder dataholder, ConverterLookup converterlookup, Mapper mapper)
    {
        return createUnmarshallingContext(obj, hierarchicalstreamreader, converterlookup, mapper).start(dataholder);
    }

    @Override
	public Object unmarshal(Object obj, HierarchicalStreamReader hierarchicalstreamreader, DataHolder dataholder, DefaultConverterLookup defaultconverterlookup, ClassMapper classmapper)
    {
        return unmarshal(obj, hierarchicalstreamreader, dataholder, ((ConverterLookup) (defaultconverterlookup)), ((Mapper) (classmapper)));
    }
}
