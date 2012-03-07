// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            AbstractReferenceUnmarshaller

public class ReferenceByIdUnmarshaller extends AbstractReferenceUnmarshaller
{

    public ReferenceByIdUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, ClassMapper classmapper)
    {
        this(obj, hierarchicalstreamreader, converterlookup, ((Mapper) (classmapper)));
    }

    public ReferenceByIdUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper)
    {
        super(obj, hierarchicalstreamreader, converterlookup, mapper);
    }

    @Override
	protected Object getCurrentReferenceKey()
    {
        String s = getMapper().aliasForSystemAttribute("id");
        String s1;
        if(s == null)
            s1 = null;
        else
            s1 = reader.getAttribute(s);
        return s1;
    }

    @Override
	protected Object getReferenceKey(String s)
    {
        return s;
    }
}
