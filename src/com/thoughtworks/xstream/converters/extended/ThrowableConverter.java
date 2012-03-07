// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ThrowableConverter
    implements Converter
{

    public ThrowableConverter(Converter converter)
    {
        defaultConverter = converter;
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
        if(class$java$lang$Throwable == null)
        {
            class2 = _mthclass$("java.lang.Throwable");
            class$java$lang$Throwable = class2;
        } else
        {
            class2 = class$java$lang$Throwable;
        }
        return class2.isAssignableFrom(class1);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Throwable throwable = (Throwable)obj;
        if(throwable.getCause() == null)
            try
            {
                throwable.initCause(null);
            }
            catch(IllegalStateException illegalstateexception) { }
        throwable.getStackTrace();
        defaultConverter.marshal(throwable, hierarchicalstreamwriter, marshallingcontext);
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return defaultConverter.unmarshal(hierarchicalstreamreader, unmarshallingcontext);
    }

    static Class class$java$lang$Throwable;
    private Converter defaultConverter;
}
