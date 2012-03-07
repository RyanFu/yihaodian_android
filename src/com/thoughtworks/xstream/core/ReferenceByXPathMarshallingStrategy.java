// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            AbstractTreeMarshallingStrategy, ReferenceByXPathMarshaller, ReferenceByXPathUnmarshaller, TreeMarshaller, 
//            TreeUnmarshaller

public class ReferenceByXPathMarshallingStrategy extends AbstractTreeMarshallingStrategy
{

    public ReferenceByXPathMarshallingStrategy()
    {
        this(RELATIVE);
    }

    public ReferenceByXPathMarshallingStrategy(int i)
    {
        mode = i;
    }

    @Override
	protected TreeMarshaller createMarshallingContext(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper)
    {
        return new ReferenceByXPathMarshaller(hierarchicalstreamwriter, converterlookup, mapper, mode);
    }

    @Override
	protected TreeUnmarshaller createUnmarshallingContext(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper)
    {
        return new ReferenceByXPathUnmarshaller(obj, hierarchicalstreamreader, converterlookup, mapper);
    }

    public static int ABSOLUTE = 1;
    public static int RELATIVE = 0;
    private final int mode;

}
