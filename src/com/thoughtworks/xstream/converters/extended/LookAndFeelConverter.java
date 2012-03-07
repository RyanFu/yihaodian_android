// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.mapper.Mapper;

public class LookAndFeelConverter extends ReflectionConverter
{

    public LookAndFeelConverter(Mapper mapper, ReflectionProvider reflectionprovider)
    {
        super(mapper, reflectionprovider);
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
        Class class2;
        if(class$javax$swing$LookAndFeel == null)
        {
            class2 = _mthclass$("javax.swing.LookAndFeel");
            class$javax$swing$LookAndFeel = class2;
        } else
        {
            class2 = class$javax$swing$LookAndFeel;
        }
        return class2.isAssignableFrom(class1);
    }

    static Class class$javax$swing$LookAndFeel;
}
