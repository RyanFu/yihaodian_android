// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.path.Path;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            AbstractReferenceMarshaller, ReferenceByXPathMarshallingStrategy

public class ReferenceByXPathMarshaller extends AbstractReferenceMarshaller
{

    public ReferenceByXPathMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, ClassMapper classmapper)
    {
        this(hierarchicalstreamwriter, converterlookup, ((classmapper)), ReferenceByXPathMarshallingStrategy.RELATIVE);
    }

    public ReferenceByXPathMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper, int i)
    {
        super(hierarchicalstreamwriter, converterlookup, mapper);
        mode = i;
    }

    @Override
	protected String createReference(Path path, Object obj)
    {
        Object obj1;
        if(mode == ReferenceByXPathMarshallingStrategy.RELATIVE)
            obj1 = path.relativeTo((Path)obj);
        else
            obj1 = obj;
        return obj1.toString();
    }

    @Override
	protected Object createReferenceKey(Path path, Object obj)
    {
        return path;
    }

    @Override
	protected void fireValidReference(Object obj)
    {
    }

    private final int mode;
}
