// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;


// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class StringBufferConverter extends AbstractSingleValueConverter
{

    public StringBufferConverter()
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
        if(class$java$lang$StringBuffer == null)
        {
            class2 = _mthclass$("java.lang.StringBuffer");
            class$java$lang$StringBuffer = class2;
        } else
        {
            class2 = class$java$lang$StringBuffer;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        return new StringBuffer(s);
    }

    static Class class$java$lang$StringBuffer;
}
