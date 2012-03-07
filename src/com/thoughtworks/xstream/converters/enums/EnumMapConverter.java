// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.enums;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.core.util.Fields;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.Field;
import java.util.EnumMap;

public class EnumMapConverter extends MapConverter
{

    public EnumMapConverter(Mapper mapper)
    {
        super(mapper);
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(class1 == java/util/EnumMap)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Class class1 = (Class)Fields.read(typeField, obj);
        String s = mapper().aliasForSystemAttribute("enum-type");
        if(s != null)
            hierarchicalstreamwriter.addAttribute(s, mapper().serializedClass(class1));
        super.marshal(obj, hierarchicalstreamwriter, marshallingcontext);
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        String s = mapper().aliasForSystemAttribute("enum-type");
        if(s == null)
        {
            throw new ConversionException("No EnumType specified for EnumMap");
        } else
        {
            EnumMap enummap = new EnumMap(mapper().realClass(hierarchicalstreamreader.getAttribute(s)));
            populateMap(hierarchicalstreamreader, unmarshallingcontext, enummap);
            return enummap;
        }
    }

    private static final Field typeField;

    static 
    {
        Field field = null;
        Field afield[] = java/util/EnumMap.getDeclaredFields();
        int i = 0;
        do
        {
label0:
            {
                if(i < afield.length)
                {
                    if(afield[i].getType() != java/lang/Class)
                        break label0;
                    field = afield[i];
                    field.setAccessible(true);
                }
                if(field == null)
                {
                    throw new ExceptionInInitializerError("Cannot detect element type of EnumMap");
                } else
                {
                    typeField = field;
                }
            }
            i++;
        } while(true);
    }
}
