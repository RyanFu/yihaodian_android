// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.ObjectIdDictionary;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.core:
//            MapBackedDataHolder

public class TreeMarshaller
    implements MarshallingContext
{
    public static class CircularReferenceException extends XStreamException
    {

        public CircularReferenceException()
        {
        }
    }


    public TreeMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, ClassMapper classmapper)
    {
        this(hierarchicalstreamwriter, converterlookup, ((Mapper) (classmapper)));
    }

    public TreeMarshaller(HierarchicalStreamWriter hierarchicalstreamwriter, ConverterLookup converterlookup, Mapper mapper1)
    {
        parentObjects = new ObjectIdDictionary();
        writer = hierarchicalstreamwriter;
        converterLookup = converterlookup;
        mapper = mapper1;
        if(mapper1 instanceof ClassMapper)
            classMapper = (ClassMapper)mapper1;
    }

    private void lazilyCreateDataHolder()
    {
        if(dataHolder == null)
            dataHolder = new MapBackedDataHolder();
    }

    protected void convert(Object obj, Converter converter)
    {
        if(parentObjects.containsId(obj))
        {
            throw new CircularReferenceException();
        } else
        {
            parentObjects.associateId(obj, "");
            converter.marshal(obj, writer, this);
            parentObjects.removeId(obj);
            return;
        }
    }

    @Override
	public void convertAnother(Object obj)
    {
        convertAnother(obj, null);
    }

    @Override
	public void convertAnother(Object obj, Converter converter)
    {
        if(converter == null)
            converter = converterLookup.lookupConverterForType(obj.getClass());
        else
        if(!converter.canConvert(obj.getClass()))
        {
            ConversionException conversionexception = new ConversionException("Explicit selected converter cannot handle item");
            conversionexception.add("item-type", obj.getClass().getName());
            conversionexception.add("converter-type", converter.getClass().getName());
            throw conversionexception;
        }
        convert(obj, converter);
    }

    @Override
	public Object get(Object obj)
    {
        lazilyCreateDataHolder();
        return dataHolder.get(obj);
    }

    protected Mapper getMapper()
    {
        return mapper;
    }

    @Override
	public Iterator keys()
    {
        lazilyCreateDataHolder();
        return dataHolder.keys();
    }

    @Override
	public void put(Object obj, Object obj1)
    {
        lazilyCreateDataHolder();
        dataHolder.put(obj, obj1);
    }

    public void start(Object obj, DataHolder dataholder)
    {
        dataHolder = dataholder;
        if(obj == null)
        {
            writer.startNode(mapper.serializedClass(null));
            writer.endNode();
        } else
        {
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, mapper.serializedClass(obj.getClass()), obj.getClass());
            convertAnother(obj);
            writer.endNode();
        }
    }

    protected ClassMapper classMapper;
    protected ConverterLookup converterLookup;
    private DataHolder dataHolder;
    private Mapper mapper;
    private ObjectIdDictionary parentObjects;
    protected HierarchicalStreamWriter writer;
}
