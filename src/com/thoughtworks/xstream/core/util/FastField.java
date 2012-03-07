// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;


public final class FastField
{

    public FastField(Class class1, String s)
    {
        name = s;
        declaringClass = class1;
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
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(this == null)
        {
            flag = false;
        } else
        {
            Class class1 = obj.getClass();
            Class class2;
            if(class$com$thoughtworks$xstream$core$util$FastField == null)
            {
                class2 = _mthclass$("com.thoughtworks.xstream.core.util.FastField");
                class$com$thoughtworks$xstream$core$util$FastField = class2;
            } else
            {
                class2 = class$com$thoughtworks$xstream$core$util$FastField;
            }
            if(class1 == class2)
            {
                FastField fastfield = (FastField)obj;
                if(name.equals(fastfield.getName()) && declaringClass.equals(fastfield.getDeclaringClass()))
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
        }
        return flag;
    }

    public Class getDeclaringClass()
    {
        return declaringClass;
    }

    public String getName()
    {
        return name;
    }

    @Override
	public int hashCode()
    {
        return name.hashCode() ^ declaringClass.hashCode();
    }

    @Override
	public String toString()
    {
        return declaringClass.getName() + "[" + name + "]";
    }

    static Class class$com$thoughtworks$xstream$core$util$FastField;
    private final Class declaringClass;
    private final String name;
}
