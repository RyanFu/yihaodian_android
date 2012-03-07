// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.ObjectIdDictionary;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.path.*;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            TreeMarshaller

public abstract class AbstractReferenceMarshaller extends TreeMarshaller
{
    public static class ReferencedImplicitElementException extends ConversionException
    {

        public ReferencedImplicitElementException(Object obj, Path path)
        {
            super("Cannot reference implicit element");
            add("implicit-element", obj.toString());
            add("referencing-element", path.toString());
        }

        public ReferencedImplicitElementException(String s)
        {
            super(s);
        }
    }


    public AbstractReferenceMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper)
    {
        super(hierarchicalstreamwriter, converterlookup, mapper);
        references = new ObjectIdDictionary();
        implicitElements = new ObjectIdDictionary();
        pathTracker = new PathTracker();
        writer = new PathTrackingWriter(hierarchicalstreamwriter, pathTracker);
    }

    @Override
	public void convert(Object obj, Converter converter)
    {
        if(!getMapper().isImmutableValueType(obj.getClass())) goto _L2; else goto _L1
_L1:
        converter.marshal(obj, writer, this);
_L4:
        return;
_L2:
        Path path = pathTracker.getPath();
        Object obj1 = references.lookupId(obj);
        if(obj1 != null)
        {
            String s = getMapper().aliasForSystemAttribute("reference");
            if(s != null)
                writer.addAttribute(s, createReference(path, obj1));
        } else
        {
            if(implicitElements.lookupId(obj) != null)
                throw new ReferencedImplicitElementException(obj, path);
            Object obj2 = createReferenceKey(path, obj);
            if(lastPath == null || !path.isAncestor(lastPath))
            {
                fireValidReference(obj2);
                lastPath = path;
                references.associateId(obj, obj2);
            } else
            {
                implicitElements.associateId(obj, obj2);
            }
            converter.marshal(obj, writer, this);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected abstract String createReference(Path path, Object obj);

    protected abstract Object createReferenceKey(Path path, Object obj);

    protected abstract void fireValidReference(Object obj);

    private ObjectIdDictionary implicitElements;
    private Path lastPath;
    private PathTracker pathTracker;
    private ObjectIdDictionary references;
}
