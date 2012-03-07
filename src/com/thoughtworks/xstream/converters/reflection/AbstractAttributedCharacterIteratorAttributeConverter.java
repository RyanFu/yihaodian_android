// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            FieldDictionary, ObjectAccessException

public class AbstractAttributedCharacterIteratorAttributeConverter extends AbstractSingleValueConverter
{

    public AbstractAttributedCharacterIteratorAttributeConverter(Class class1)
    {
        type = class1;
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

    private Object readResolve()
    {
        fieldDictionary = new FieldDictionary();
        attributeMap = new HashMap();
        Iterator iterator = fieldDictionary.fieldsFor(type);
        do
        {
            if(!iterator.hasNext())
                break;
            Field field = (Field)iterator.next();
            if(field.getType() == type && Modifier.isStatic(field.getModifiers()))
                try
                {
                    Object obj = field.get(null);
                    attributeMap.put(toString(obj), obj);
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                    throw new ObjectAccessException("Cannot get object of " + field, illegalaccessexception);
                }
        } while(true);
        return this;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(class1 == type)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public Object fromString(String s)
    {
        return attributeMap.get(s);
    }

    @Override
	public String toString(Object obj)
    {
        java.text.AttributedCharacterIterator.Attribute attribute = (java.text.AttributedCharacterIterator.Attribute)obj;
        String s;
        try
        {
            if(!getName.isAccessible())
                getName.setAccessible(true);
            s = (String)getName.invoke(attribute, (Object[])null);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException("Cannot get name of AttributedCharacterIterator.Attribute", illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ObjectAccessException("Cannot get name of AttributedCharacterIterator.Attribute", invocationtargetexception.getTargetException());
        }
        return s;
    }

    static Class class$java$text$AttributedCharacterIterator$Attribute;
    private static final Method getName;
    private transient Map attributeMap;
    private transient FieldDictionary fieldDictionary;
    private final Class type;

    static 
    {
        try
        {
            Class class1;
            if(class$java$text$AttributedCharacterIterator$Attribute == null)
            {
                class1 = _mthclass$("java.text.AttributedCharacterIterator$Attribute");
                class$java$text$AttributedCharacterIterator$Attribute = class1;
            } else
            {
                class1 = class$java$text$AttributedCharacterIterator$Attribute;
            }
            getName = class1.getDeclaredMethod("getName", (Class[])null);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new ExceptionInInitializerError("Missing AttributedCharacterIterator.Attribute.getName()");
        }
    }
}
