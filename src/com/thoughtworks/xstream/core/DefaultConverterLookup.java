// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.PrioritizedList;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.*;

public class DefaultConverterLookup
    implements ConverterLookup, ConverterRegistry
{

    public DefaultConverterLookup()
    {
        converters = new PrioritizedList();
        typeToConverterMap = Collections.synchronizedMap(new HashMap());
    }

    public DefaultConverterLookup(ClassMapper classmapper)
    {
        converters = new PrioritizedList();
        typeToConverterMap = Collections.synchronizedMap(new HashMap());
    }

    public DefaultConverterLookup(Mapper mapper)
    {
        converters = new PrioritizedList();
        typeToConverterMap = Collections.synchronizedMap(new HashMap());
    }

    private Object readResolve()
    {
        typeToConverterMap = Collections.synchronizedMap(new HashMap());
        return this;
    }

    @Override
	public Converter lookupConverterForType(Class class1)
    {
        Converter converter = (Converter)typeToConverterMap.get(class1);
        if(converter == null) goto _L2; else goto _L1
_L1:
        Converter converter2 = converter;
_L4:
        return converter2;
_L2:
        Iterator iterator = converters.iterator();
        Converter converter1;
        do
        {
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            converter1 = (Converter)iterator.next();
        } while(!converter1.canConvert(class1));
        typeToConverterMap.put(class1, converter1);
        converter2 = converter1;
        if(true) goto _L4; else goto _L3
_L3:
        throw new ConversionException("No converter specified for " + class1);
    }

    @Override
	public void registerConverter(Converter converter, int i)
    {
        converters.add(converter, i);
        Iterator iterator = typeToConverterMap.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(converter.canConvert((Class)iterator.next()))
                iterator.remove();
        } while(true);
    }

    private final PrioritizedList converters;
    private transient Map typeToConverterMap;
}
