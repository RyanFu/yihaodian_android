// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.core.util.FastField;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, AttributeMapper, Mapper

public class LocalConversionMapper extends MapperWrapper
{

    public LocalConversionMapper(Mapper mapper)
    {
        super(mapper);
        readResolve();
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    private SingleValueConverter getLocalSingleValueConverter(Class class1, String s, Class class2)
    {
        if(attributeMapper == null || !attributeMapper.shouldLookForSingleValueConverter(s, class2, class1)) goto _L2; else goto _L1
_L1:
        Converter converter = getLocalConverter(class1, s);
        if(converter == null || !(converter instanceof SingleValueConverter)) goto _L2; else goto _L3
_L3:
        SingleValueConverter singlevalueconverter = (SingleValueConverter)converter;
_L5:
        return singlevalueconverter;
_L2:
        singlevalueconverter = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private Object readResolve()
    {
        Class class1;
        if(class$com$thoughtworks$xstream$mapper$AttributeMapper == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.mapper.AttributeMapper");
            class$com$thoughtworks$xstream$mapper$AttributeMapper = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$mapper$AttributeMapper;
        }
        attributeMapper = (AttributeMapper)lookupMapperOfType(class1);
        return this;
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s, Class class2)
    {
        SingleValueConverter singlevalueconverter = getLocalSingleValueConverter(class1, s, class2);
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
        SingleValueConverter singlevalueconverter = getLocalSingleValueConverter(class2, s, class1);
        SingleValueConverter singlevalueconverter1;
        if(singlevalueconverter == null)
            singlevalueconverter1 = super.getConverterFromItemType(s, class1, class2);
        else
            singlevalueconverter1 = singlevalueconverter;
        return singlevalueconverter1;
    }

    @Override
	public Converter getLocalConverter(Class class1, String s)
    {
        return (Converter)localConverters.get(new FastField(class1, s));
    }

    public void registerLocalConverter(Class class1, String s, Converter converter)
    {
        localConverters.put(new FastField(class1, s), converter);
    }

    static Class class$com$thoughtworks$xstream$mapper$AttributeMapper;
    private transient AttributeMapper attributeMapper;
    private final Map localConverters = new HashMap();
}
