// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;


// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class StringBuilderConverter extends AbstractSingleValueConverter
{

    public StringBuilderConverter()
    {
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
        if(class$java$lang$StringBuilder == null)
        {
            class2 = _mthclass$("java.lang.StringBuilder");
            class$java$lang$StringBuilder = class2;
        } else
        {
            class2 = class$java$lang$StringBuilder;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        return new StringBuilder(s);
    }

    static Class class$java$lang$StringBuilder;
}
