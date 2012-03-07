// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            AbstractReflectionConverter, ReflectionProvider

public class ReflectionConverter extends AbstractReflectionConverter
{

    public ReflectionConverter(Mapper mapper, ReflectionProvider reflectionprovider)
    {
        super(mapper, reflectionprovider);
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return true;
    }
}
