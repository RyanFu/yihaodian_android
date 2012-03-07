// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.converters.ConversionException;
import java.util.UUID;

// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class UUIDConverter extends AbstractSingleValueConverter
{

    public UUIDConverter()
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
        if(class$java$util$UUID == null)
        {
            class2 = _mthclass$("java.util.UUID");
            class$java$util$UUID = class2;
        } else
        {
            class2 = class$java$util$UUID;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        UUID uuid;
        try
        {
            uuid = UUID.fromString(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ConversionException("Cannot create UUID instance", illegalargumentexception);
        }
        return uuid;
    }

    static Class class$java$util$UUID;
}
