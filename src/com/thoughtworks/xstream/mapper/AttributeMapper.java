// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.core.util.Fields;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class AttributeMapper extends MapperWrapper
{

    public AttributeMapper(Mapper mapper)
    {
        this(mapper, null);
    }

    public AttributeMapper(Mapper mapper, ConverterLookup converterlookup)
    {
        super(mapper);
        fieldNameToTypeMap = new HashMap();
        typeSet = new HashSet();
        fieldToUseAsAttribute = new HashSet();
        converterLookup = converterlookup;
    }

    private Field getField(Class class1, String s)
    {
        return Fields.find(class1, s);
    }

    private SingleValueConverter getLocalConverterFromItemType(Class class1)
    {
        com.thoughtworks.xstream.converters.Converter converter = converterLookup.lookupConverterForType(class1);
        SingleValueConverter singlevalueconverter;
        if(converter != null && (converter instanceof SingleValueConverter))
            singlevalueconverter = (SingleValueConverter)converter;
        else
            singlevalueconverter = null;
        return singlevalueconverter;
    }

    public void addAttributeFor(Class class1)
    {
        typeSet.add(class1);
    }

    public void addAttributeFor(Class class1, String s)
    {
        fieldToUseAsAttribute.add(getField(class1, s));
    }

    public void addAttributeFor(String s, Class class1)
    {
        fieldNameToTypeMap.put(s, class1);
    }

    public void addAttributeFor(Field field)
    {
        fieldToUseAsAttribute.add(field);
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s)
    {
        return getConverterFromAttribute(class1, s, getField(class1, s).getType());
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s, Class class2)
    {
        if(!shouldLookForSingleValueConverter(s, class2, class1)) goto _L2; else goto _L1
_L1:
        SingleValueConverter singlevalueconverter1 = getLocalConverterFromItemType(class2);
        if(singlevalueconverter1 == null) goto _L2; else goto _L3
_L3:
        SingleValueConverter singlevalueconverter = singlevalueconverter1;
_L5:
        return singlevalueconverter;
_L2:
        singlevalueconverter = super.getConverterFromAttribute(class1, s, class2);
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(String s)
    {
        SingleValueConverter singlevalueconverter = null;
        Class class1 = (Class)fieldNameToTypeMap.get(s);
        if(class1 != null)
            singlevalueconverter = getLocalConverterFromItemType(class1);
        return singlevalueconverter;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(Class class1)
    {
        SingleValueConverter singlevalueconverter;
        if(typeSet.contains(class1))
            singlevalueconverter = getLocalConverterFromItemType(class1);
        else
            singlevalueconverter = null;
        return singlevalueconverter;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1)
    {
        SingleValueConverter singlevalueconverter;
        if(fieldNameToTypeMap.get(s) == class1)
            singlevalueconverter = getLocalConverterFromItemType(class1);
        else
            singlevalueconverter = null;
        return singlevalueconverter;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1, Class class2)
    {
        if(!shouldLookForSingleValueConverter(s, class1, class2)) goto _L2; else goto _L1
_L1:
        SingleValueConverter singlevalueconverter1 = getLocalConverterFromItemType(class1);
        if(singlevalueconverter1 == null) goto _L2; else goto _L3
_L3:
        SingleValueConverter singlevalueconverter = singlevalueconverter1;
_L5:
        return singlevalueconverter;
_L2:
        singlevalueconverter = super.getConverterFromItemType(s, class1, class2);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void setConverterLookup(ConverterLookup converterlookup)
    {
        converterLookup = converterlookup;
    }

    public boolean shouldLookForSingleValueConverter(String s, Class class1, Class class2)
    {
        Field field = getField(class2, s);
        boolean flag;
        if(fieldToUseAsAttribute.contains(field) || fieldNameToTypeMap.get(s) == class1 || typeSet.contains(class1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private ConverterLookup converterLookup;
    private final Map fieldNameToTypeMap;
    private final Set fieldToUseAsAttribute;
    private final Set typeSet;
}
