// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import java.lang.reflect.Field;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            ReflectionProvider

public class ReflectionProviderWrapper
    implements ReflectionProvider
{

    public ReflectionProviderWrapper(ReflectionProvider reflectionprovider)
    {
        wrapped = reflectionprovider;
    }

    @Override
	public boolean fieldDefinedInClass(String s, Class class1)
    {
        return wrapped.fieldDefinedInClass(s, class1);
    }

    @Override
	public Field getField(Class class1, String s)
    {
        return wrapped.getField(class1, s);
    }

    @Override
	public Class getFieldType(Object obj, String s, Class class1)
    {
        return wrapped.getFieldType(obj, s, class1);
    }

    @Override
	public Object newInstance(Class class1)
    {
        return wrapped.newInstance(class1);
    }

    @Override
	public void visitSerializableFields(Object obj, ReflectionProvider.Visitor visitor)
    {
        wrapped.visitSerializableFields(obj, visitor);
    }

    @Override
	public void writeField(Object obj, String s, Object obj1, Class class1)
    {
        wrapped.writeField(obj, s, obj1, class1);
    }

    protected final ReflectionProvider wrapped;
}
