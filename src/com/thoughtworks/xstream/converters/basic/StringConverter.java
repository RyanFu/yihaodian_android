// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class StringConverter extends AbstractSingleValueConverter
{

    public StringConverter()
    {
        this(Collections.synchronizedMap(new WeakHashMap()));
    }

    public StringConverter(Map map)
    {
        cache = map;
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
        if(class$java$lang$String == null)
        {
            class2 = _mthclass$("java.lang.String");
            class$java$lang$String = class2;
        } else
        {
            class2 = class$java$lang$String;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        WeakReference weakreference = (WeakReference)cache.get(s);
        Object obj;
        String s1;
        if(weakreference == null)
            obj = null;
        else
            obj = weakreference.get();
        s1 = (String)obj;
        if(s1 == null)
        {
            cache.put(s, new WeakReference(s));
            s1 = s;
        }
        return s1;
    }

    static Class class$java$lang$String;
    private final Map cache;
}
