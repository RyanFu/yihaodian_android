// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.javabean;

import java.lang.reflect.*;

public class BeanProperty
{

    public BeanProperty(Class class1, String s, Class class2)
    {
        memberClass = class1;
        propertyName = s;
        type = class2;
    }

    public Object get(Object obj)
        throws IllegalArgumentException, IllegalAccessException
    {
        if(!isReadable())
            throw new IllegalStateException("Property " + propertyName + " of " + memberClass + " not readable");
        Object obj1;
        try
        {
            obj1 = getter.invoke(obj, EMPTY_ARGS);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new UndeclaredThrowableException(invocationtargetexception.getTargetException());
        }
        return obj1;
    }

    public Class getBeanClass()
    {
        return memberClass;
    }

    public String getName()
    {
        return propertyName;
    }

    public Class getType()
    {
        return type;
    }

    public boolean isReadable()
    {
        boolean flag;
        if(getter != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWritable()
    {
        boolean flag;
        if(setter != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Object set(Object obj, Object obj1)
        throws IllegalArgumentException, IllegalAccessException
    {
        if(!isWritable())
            throw new IllegalStateException("Property " + propertyName + " of " + memberClass + " not writable");
        Object obj2;
        try
        {
            Method method = setter;
            Object aobj[] = new Object[1];
            aobj[0] = obj1;
            obj2 = method.invoke(obj, aobj);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new UndeclaredThrowableException(invocationtargetexception.getTargetException());
        }
        return obj2;
    }

    public void setGetterMethod(Method method)
    {
        getter = method;
    }

    public void setSetterMethod(Method method)
    {
        setter = method;
    }

    private static final Object EMPTY_ARGS[] = new Object[0];
    protected Method getter;
    private Class memberClass;
    private String propertyName;
    private Method setter;
    private Class type;

}
