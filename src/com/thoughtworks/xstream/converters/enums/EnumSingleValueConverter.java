// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.enums;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class EnumSingleValueConverter extends AbstractSingleValueConverter
{

    public EnumSingleValueConverter(Class class1)
    {
        if(!java/lang/Enum.isAssignableFrom(class1) && class1 != java/lang/Enum)
        {
            throw new IllegalArgumentException("Converter can only handle defined enums");
        } else
        {
            enumType = class1;
            return;
        }
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return enumType.isAssignableFrom(class1);
    }

    @Override
	public Object fromString(String s)
    {
        return Enum.valueOf(enumType, s);
    }

    private final Class enumType;
}
