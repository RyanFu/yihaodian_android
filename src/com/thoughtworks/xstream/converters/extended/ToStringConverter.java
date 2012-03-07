// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ToStringConverter extends AbstractSingleValueConverter
{

    public ToStringConverter(Class class1)
        throws NoSuchMethodException
    {
        clazz = class1;
        Class aclass[] = new Class[1];
        Class class2;
        if(class$java$lang$String == null)
        {
            class2 = _mthclass$("java.lang.String");
            class$java$lang$String = class2;
        } else
        {
            class2 = class$java$lang$String;
        }
        aclass[0] = class2;
        ctor = class1.getConstructor(aclass);
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

    @Override
	public boolean canConvert(Class class1)
    {
        return class1.equals(clazz);
    }

    @Override
	public Object fromString(String s)
    {
        Object obj;
        try
        {
            Constructor constructor = ctor;
            Object aobj[] = new Object[1];
            aobj[0] = s;
            obj = constructor.newInstance(aobj);
        }
        catch(InstantiationException instantiationexception)
        {
            throw new ConversionException("Unable to instantiate single String param constructor", instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ConversionException("Unable to access single String param constructor", illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ConversionException("Unable to target single String param constructor", invocationtargetexception.getTargetException());
        }
        return obj;
    }

    @Override
	public String toString(Object obj)
    {
        String s;
        if(obj == null)
            s = null;
        else
            s = obj.toString();
        return s;
    }

    static Class class$java$lang$String;
    private final Class clazz;
    private final Constructor ctor;
}
