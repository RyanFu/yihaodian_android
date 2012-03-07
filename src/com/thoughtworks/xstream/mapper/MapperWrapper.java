// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            Mapper

public abstract class MapperWrapper
    implements Mapper
{

    public MapperWrapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public MapperWrapper(Mapper mapper)
    {
        wrapped = mapper;
    }

    @Override
	public String aliasForAttribute(Class class1, String s)
    {
        return wrapped.aliasForAttribute(class1, s);
    }

    @Override
	public String aliasForAttribute(String s)
    {
        return wrapped.aliasForAttribute(s);
    }

    @Override
	public String aliasForSystemAttribute(String s)
    {
        return wrapped.aliasForSystemAttribute(s);
    }

    @Override
	public String attributeForAlias(Class class1, String s)
    {
        return wrapped.attributeForAlias(class1, s);
    }

    @Override
	public String attributeForAlias(String s)
    {
        return wrapped.attributeForAlias(s);
    }

    @Override
	public String attributeForClassDefiningField()
    {
        return wrapped.attributeForClassDefiningField();
    }

    @Override
	public String attributeForEnumType()
    {
        return wrapped.attributeForEnumType();
    }

    @Override
	public String attributeForImplementationClass()
    {
        return wrapped.attributeForImplementationClass();
    }

    @Override
	public String attributeForReadResolveField()
    {
        return wrapped.attributeForReadResolveField();
    }

    @Override
	public Class defaultImplementationOf(Class class1)
    {
        return wrapped.defaultImplementationOf(class1);
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s)
    {
        return wrapped.getConverterFromAttribute(class1, s);
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s, Class class2)
    {
        return wrapped.getConverterFromAttribute(class1, s, class2);
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(String s)
    {
        return wrapped.getConverterFromAttribute(s);
    }

    @Override
	public SingleValueConverter getConverterFromItemType(Class class1)
    {
        return wrapped.getConverterFromItemType(class1);
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1)
    {
        return wrapped.getConverterFromItemType(s, class1);
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1, Class class2)
    {
        return wrapped.getConverterFromItemType(s, class1, class2);
    }

    @Override
	public String getFieldNameForItemTypeAndName(Class class1, Class class2, String s)
    {
        return wrapped.getFieldNameForItemTypeAndName(class1, class2, s);
    }

    @Override
	public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class class1, String s)
    {
        return wrapped.getImplicitCollectionDefForFieldName(class1, s);
    }

    @Override
	public Class getItemTypeForItemFieldName(Class class1, String s)
    {
        return wrapped.getItemTypeForItemFieldName(class1, s);
    }

    @Override
	public Converter getLocalConverter(Class class1, String s)
    {
        return wrapped.getLocalConverter(class1, s);
    }

    @Override
	public boolean isImmutableValueType(Class class1)
    {
        return wrapped.isImmutableValueType(class1);
    }

    @Override
	public Mapper lookupMapperOfType(Class class1)
    {
        Object obj;
        if(class1.isAssignableFrom(getClass()))
            obj = this;
        else
            obj = wrapped.lookupMapperOfType(class1);
        return ((Mapper) (obj));
    }

    @Override
	public Class realClass(String s)
    {
        return wrapped.realClass(s);
    }

    @Override
	public String realMember(Class class1, String s)
    {
        return wrapped.realMember(class1, s);
    }

    @Override
	public String serializedClass(Class class1)
    {
        return wrapped.serializedClass(class1);
    }

    @Override
	public String serializedMember(Class class1, String s)
    {
        return wrapped.serializedMember(class1, s);
    }

    @Override
	public boolean shouldSerializeMember(Class class1, String s)
    {
        return wrapped.shouldSerializeMember(class1, s);
    }

    private final Mapper wrapped;
}
