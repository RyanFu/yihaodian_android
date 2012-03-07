// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class JavaClassConverter extends AbstractSingleValueConverter
{

    public JavaClassConverter()
    {
        this(Thread.currentThread().getContextClassLoader());
    }

    public JavaClassConverter(ClassLoader classloader)
    {
        classLoader = classloader;
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

    private Class loadClass(String s)
        throws ClassNotFoundException
    {
        Class class1 = primitiveClassForName(s);
        Class class2;
        if(class1 != null)
        {
            class2 = class1;
        } else
        {
            int i;
            for(i = 0; s.charAt(i) == '['; i++);
            if(i > 0)
            {
                ClassLoader classloader;
                if(s.charAt(i) == 'L')
                {
                    String s1 = s.substring(i + 1, s.length() - 1);
                    classloader = classLoader.loadClass(s1).getClassLoader();
                } else
                {
                    classloader = null;
                }
                class2 = Class.forName(s, false, classloader);
            } else
            {
                class2 = classLoader.loadClass(s);
            }
        }
        return class2;
    }

    private Class primitiveClassForName(String s)
    {
        Class class1;
        if(s.equals("void"))
            class1 = Void.TYPE;
        else
        if(s.equals("boolean"))
            class1 = Boolean.TYPE;
        else
        if(s.equals("byte"))
            class1 = Byte.TYPE;
        else
        if(s.equals("char"))
            class1 = Character.TYPE;
        else
        if(s.equals("short"))
            class1 = Short.TYPE;
        else
        if(s.equals("int"))
            class1 = Integer.TYPE;
        else
        if(s.equals("long"))
            class1 = Long.TYPE;
        else
        if(s.equals("float"))
            class1 = Float.TYPE;
        else
        if(s.equals("double"))
            class1 = Double.TYPE;
        else
            class1 = null;
        return class1;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$lang$Class == null)
        {
            class2 = _mthclass$("java.lang.Class");
            class$java$lang$Class = class2;
        } else
        {
            class2 = class$java$lang$Class;
        }
        return class2.equals(class1);
    }

    @Override
	public Object fromString(String s)
    {
        Class class1;
        try
        {
            class1 = loadClass(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new ConversionException("Cannot load java class " + s, classnotfoundexception);
        }
        return class1;
    }

    @Override
	public String toString(Object obj)
    {
        return ((Class)obj).getName();
    }

    static Class class$java$lang$Class;
    private ClassLoader classLoader;
}
