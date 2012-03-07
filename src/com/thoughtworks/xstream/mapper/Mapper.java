// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public interface Mapper
{
    public static interface ImplicitCollectionMapping
    {

        public abstract String getFieldName();

        public abstract String getItemFieldName();

        public abstract Class getItemType();
    }

    public static class Null
    {

        public Null()
        {
        }
    }


    public abstract String aliasForAttribute(Class class1, String s);

    public abstract String aliasForAttribute(String s);

    public abstract String aliasForSystemAttribute(String s);

    public abstract String attributeForAlias(Class class1, String s);

    public abstract String attributeForAlias(String s);

    public abstract String attributeForClassDefiningField();

    public abstract String attributeForEnumType();

    public abstract String attributeForImplementationClass();

    public abstract String attributeForReadResolveField();

    public abstract Class defaultImplementationOf(Class class1);

    public abstract SingleValueConverter getConverterFromAttribute(Class class1, String s);

    public abstract SingleValueConverter getConverterFromAttribute(Class class1, String s, Class class2);

    public abstract SingleValueConverter getConverterFromAttribute(String s);

    public abstract SingleValueConverter getConverterFromItemType(Class class1);

    public abstract SingleValueConverter getConverterFromItemType(String s, Class class1);

    public abstract SingleValueConverter getConverterFromItemType(String s, Class class1, Class class2);

    public abstract String getFieldNameForItemTypeAndName(Class class1, Class class2, String s);

    public abstract ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class class1, String s);

    public abstract Class getItemTypeForItemFieldName(Class class1, String s);

    public abstract Converter getLocalConverter(Class class1, String s);

    public abstract boolean isImmutableValueType(Class class1);

    public abstract Mapper lookupMapperOfType(Class class1);

    public abstract Class realClass(String s);

    public abstract String realMember(Class class1, String s);

    public abstract String serializedClass(Class class1);

    public abstract String serializedMember(Class class1, String s);

    public abstract boolean shouldSerializeMember(Class class1, String s);
}
