// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.lang.reflect.Field;

public class Fields
{

    public Fields()
    {
    }

    public static Field find(Class class1, String s)
    {
        Field field;
        try
        {
            field = class1.getDeclaredField(s);
            field.setAccessible(true);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            throw new IllegalArgumentException("Could not access " + class1.getName() + "." + s + " field: " + nosuchfieldexception.getMessage());
        }
        return field;
    }

    public static Object read(Field field, Object obj)
    {
        Object obj1;
        try
        {
            obj1 = field.get(obj);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException("Could not read " + field.getType().getName() + "." + field.getName() + " field");
        }
        return obj1;
    }

    public static void write(Field field, Object obj, Object obj1)
    {
        try
        {
            field.set(obj, obj1);
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException("Could not write " + field.getType().getName() + "." + field.getName() + " field");
        }
    }
}
