// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.enums;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.Fields;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetConverter
    implements Converter
{

    public EnumSetConverter(Mapper mapper1)
    {
        mapper = mapper1;
    }

    private String joinEnumValues(EnumSet enumset)
    {
        boolean flag = false;
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = enumset.iterator();
        while(iterator.hasNext()) 
        {
            Enum enum = (Enum)iterator.next();
            if(flag)
                stringbuffer.append(',');
            else
                flag = true;
            stringbuffer.append(enum.name());
        }
        return stringbuffer.toString();
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return java/util/EnumSet.isAssignableFrom(class1);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        EnumSet enumset = (EnumSet)obj;
        Class class1 = (Class)Fields.read(typeField, enumset);
        String s = mapper.aliasForSystemAttribute("enum-type");
        if(s != null)
            hierarchicalstreamwriter.addAttribute(s, mapper.serializedClass(class1));
        hierarchicalstreamwriter.setValue(joinEnumValues(enumset));
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        String s = mapper.aliasForSystemAttribute("enum-type");
        if(s == null)
            throw new ConversionException("No EnumType specified for EnumSet");
        Class class1 = mapper.realClass(hierarchicalstreamreader.getAttribute(s));
        EnumSet enumset = EnumSet.noneOf(class1);
        String as[] = hierarchicalstreamreader.getValue().split(",");
        for(int i = 0; i < as.length; i++)
        {
            String s1 = as[i];
            if(s1.length() > 0)
                enumset.add(Enum.valueOf(class1, s1));
        }

        return enumset;
    }

    private static final Field typeField;
    private final Mapper mapper;

    static 
    {
        Field field = null;
        Field afield[] = java/util/EnumSet.getDeclaredFields();
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
                    throw new ExceptionInInitializerError("Cannot detect element type of EnumSet");
                } else
                {
                    typeField = field;
                }
            }
            i++;
        } while(true);
    }
}
