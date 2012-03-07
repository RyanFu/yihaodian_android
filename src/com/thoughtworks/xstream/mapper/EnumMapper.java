// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.enums.EnumSingleValueConverter;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, AttributeMapper, Mapper

public class EnumMapper extends MapperWrapper
{

    public EnumMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public EnumMapper(Mapper mapper)
    {
        super(mapper);
        converterLookup = null;
        readResolve();
    }

    public EnumMapper(Mapper mapper, ConverterLookup converterlookup)
    {
        super(mapper);
        converterLookup = converterlookup;
        readResolve();
    }

    private SingleValueConverter getLocalConverter(String s, Class class1, Class class2)
    {
        if(attributeMapper == null || !java/lang/Enum.isAssignableFrom(class1) || !attributeMapper.shouldLookForSingleValueConverter(s, class1, class2))
            break MISSING_BLOCK_LABEL_113;
        Map map = enumConverterMap;
        map;
        JVM INSTR monitorenter ;
        Object obj;
        Object obj1 = enumConverterMap.get(class1);
        if(obj1 == null)
        {
            obj1 = super.getConverterFromItemType(s, class1, class2);
            if(obj1 == null)
                obj1 = new EnumSingleValueConverter(class1);
            enumConverterMap.put(class1, obj1);
        }
        obj = obj1;
        break MISSING_BLOCK_LABEL_116;
        obj = null;
        return ((SingleValueConverter) (obj));
    }

    private Object readResolve()
    {
        enumConverterMap = new WeakHashMap();
        attributeMapper = (AttributeMapper)lookupMapperOfType(com/thoughtworks/xstream/mapper/AttributeMapper);
        return this;
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s, Class class2)
    {
        SingleValueConverter singlevalueconverter = getLocalConverter(s, class2, class1);
        SingleValueConverter singlevalueconverter1;
        if(singlevalueconverter == null)
            singlevalueconverter1 = super.getConverterFromAttribute(class1, s, class2);
        else
            singlevalueconverter1 = singlevalueconverter;
        return singlevalueconverter1;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1, Class class2)
    {
        SingleValueConverter singlevalueconverter = getLocalConverter(s, class1, class2);
        SingleValueConverter singlevalueconverter1;
        if(singlevalueconverter == null)
            singlevalueconverter1 = super.getConverterFromItemType(s, class1, class2);
        else
            singlevalueconverter1 = singlevalueconverter;
        return singlevalueconverter1;
    }

    @Override
	public boolean isImmutableValueType(Class class1)
    {
        boolean flag;
        if(java/lang/Enum.isAssignableFrom(class1) || super.isImmutableValueType(class1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public String serializedClass(Class class1)
    {
        String s;
        if(class1 == null)
            s = super.serializedClass(class1);
        else
        if(java/lang/Enum.isAssignableFrom(class1) && class1.getSuperclass() != java/lang/Enum)
            s = super.serializedClass(class1.getSuperclass());
        else
        if(java/util/EnumSet.isAssignableFrom(class1))
            s = super.serializedClass(java/util/EnumSet);
        else
            s = super.serializedClass(class1);
        return s;
    }

    private transient AttributeMapper attributeMapper;
    private final ConverterLookup converterLookup;
    private transient Map enumConverterMap;
}
