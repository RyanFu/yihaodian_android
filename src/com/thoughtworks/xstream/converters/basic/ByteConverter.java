// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;


// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class ByteConverter extends AbstractSingleValueConverter
{

    public ByteConverter()
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
        if(class1.equals(Byte.TYPE)) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class2;
        if(class$java$lang$Byte == null)
        {
            class2 = _mthclass$("java.lang.Byte");
            class$java$lang$Byte = class2;
        } else
        {
            class2 = class$java$lang$Byte;
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
        int i = Integer.decode(s).intValue();
        if(i < -128 || i > 255)
            throw new NumberFormatException("For input string: \"" + s + '"');
        else
            return new Byte((byte)i);
    }

    static Class class$java$lang$Byte;
}
