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
//            AbstractReferenceMarshaller, SequenceGenerator

public class ReferenceByIdMarshaller extends AbstractReferenceMarshaller
{
    public static interface IDGenerator
    {

        public abstract String next(Object obj);
    }


    public ReferenceByIdMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, ClassMapper classmapper)
    {
        this(hierarchicalstreamwriter, converterlookup, ((Mapper) (classmapper)));
    }

    public ReferenceByIdMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, ClassMapper classmapper, IDGenerator idgenerator)
    {
        this(hierarchicalstreamwriter, converterlookup, ((Mapper) (classmapper)), idgenerator);
    }

    public ReferenceByIdMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper)
    {
        this(hierarchicalstreamwriter, converterlookup, mapper, ((new SequenceGenerator(1))));
    }

    public ReferenceByIdMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper, IDGenerator idgenerator)
    {
        super(hierarchicalstreamwriter, converterlookup, mapper);
        idGenerator = idgenerator;
    }

    @Override
	protected String createReference(Path path, Object obj)
    {
        return obj.toString();
    }

    @Override
	protected Object createReferenceKey(Path path, Object obj)
    {
        return idGenerator.next(obj);
    }

    @Override
	protected void fireValidReference(Object obj)
    {
        String s = getMapper().aliasForSystemAttribute("id");
        if(s != null)
            writer.addAttribute(s, obj.toString());
    }

    private final IDGenerator idGenerator;
}
