// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.FastStack;
import com.thoughtworks.xstream.core.util.HierarchicalStreams;
import com.thoughtworks.xstream.core.util.PrioritizedList;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.core:
//            MapBackedDataHolder

public class TreeUnmarshaller
    implements UnmarshallingContext
{

    public TreeUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, ClassMapper classmapper)
    {
        this(obj, hierarchicalstreamreader, converterlookup, ((Mapper) (classmapper)));
    }

    public TreeUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper1)
    {
        types = new FastStack(16);
        validationList = new PrioritizedList();
        root = obj;
        reader = hierarchicalstreamreader;
        converterLookup = converterlookup;
        mapper = mapper1;
    }

    private void addInformationTo(ErrorWriter errorwriter, Class class1)
    {
        errorwriter.add("class", class1.getName());
        errorwriter.add("required-type", getRequiredType().getName());
        reader.appendErrors(errorwriter);
    }

    private void lazilyCreateDataHolder()
    {
        if(dataHolder == null)
            dataHolder = new MapBackedDataHolder();
    }

    @Override
	public void addCompletionCallback(Runnable runnable, int i)
    {
        validationList.add(runnable, i);
    }

    protected Object convert(Object obj, Class class1, Converter converter)
    {
        Object obj1;
        try
        {
            types.push(class1);
            obj1 = converter.unmarshal(reader, this);
            types.popSilently();
        }
        catch(ConversionException conversionexception1)
        {
            addInformationTo(conversionexception1, class1);
            throw conversionexception1;
        }
        catch(RuntimeException runtimeexception)
        {
            ConversionException conversionexception = new ConversionException(runtimeexception);
            addInformationTo(conversionexception, class1);
            throw conversionexception;
        }
        return obj1;
    }

    @Override
	public Object convertAnother(Object obj, Class class1)
    {
        return convertAnother(obj, class1, null);
    }

    @Override
	public Object convertAnother(Object obj, Class class1, Converter converter)
    {
        Class class2 = mapper.defaultImplementationOf(class1);
        if(converter == null)
            converter = converterLookup.lookupConverterForType(class2);
        else
        if(!converter.canConvert(class2))
        {
            ConversionException conversionexception = new ConversionException("Explicit selected converter cannot handle type");
            conversionexception.add("item-type", class2.getName());
            conversionexception.add("converter-type", converter.getClass().getName());
            throw conversionexception;
        }
        return convert(obj, class2, converter);
    }

    @Override
	public Object currentObject()
    {
        Object obj;
        if(types.size() == 1)
            obj = root;
        else
            obj = null;
        return obj;
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
	public Class getRequiredType()
    {
        return (Class)types.peek();
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

    public Object start(DataHolder dataholder)
    {
        dataHolder = dataholder;
        Object obj = convertAnother(null, HierarchicalStreams.readClassType(reader, mapper));
        for(Iterator iterator = validationList.iterator(); iterator.hasNext(); ((Runnable)iterator.next()).run());
        return obj;
    }

    private ConverterLookup converterLookup;
    private DataHolder dataHolder;
    private Mapper mapper;
    protected HierarchicalStreamReader reader;
    private Object root;
    private FastStack types;
    private final PrioritizedList validationList;
}
