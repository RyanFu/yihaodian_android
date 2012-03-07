// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;


// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class DoubleConverter extends AbstractSingleValueConverter
{

    public DoubleConverter()
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
        if(class1.equals(Double.TYPE)) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class2;
        if(class$java$lang$Double == null)
        {
            class2 = _mthclass$("java.lang.Double");
            class$java$lang$Double = class2;
        } else
        {
            class2 = class$java$lang$Double;
        }
        if(!class1.equals(class2)) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public Object fromString(String s)
    {
        return Double.valueOf(s);
    }

    static Class class$java$lang$Double;
}
