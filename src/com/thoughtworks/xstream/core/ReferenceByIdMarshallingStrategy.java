// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            AbstractTreeMarshallingStrategy, ReferenceByIdMarshaller, ReferenceByIdUnmarshaller, TreeMarshaller, 
//            TreeUnmarshaller

public class ReferenceByIdMarshallingStrategy extends AbstractTreeMarshallingStrategy
{

    public ReferenceByIdMarshallingStrategy()
    {
    }

    @Override
	protected TreeMarshaller createMarshallingContext(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper)
    {
        return new ReferenceByIdMarshaller(hierarchicalstreamwriter, converterlookup, mapper);
    }

    @Override
	protected TreeUnmarshaller createUnmarshallingContext(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper)
    {
        return new ReferenceByIdUnmarshaller(obj, hierarchicalstreamreader, converterlookup, mapper);
    }
}
