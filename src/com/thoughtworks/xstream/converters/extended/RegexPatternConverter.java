// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.regex.Pattern;

public class RegexPatternConverter
    implements Converter
{

    public RegexPatternConverter(Converter converter)
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
        if(class$java$util$regex$Pattern == null)
        {
            class2 = _mthclass$("java.util.regex.Pattern");
            class$java$util$regex$Pattern = class2;
        } else
        {
            class2 = class$java$util$regex$Pattern;
        }
        return class1.equals(class2);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        defaultConverter.marshal(obj, hierarchicalstreamwriter, marshallingcontext);
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Pattern pattern = (Pattern)defaultConverter.unmarshal(hierarchicalstreamreader, unmarshallingcontext);
        return Pattern.compile(pattern.pattern(), pattern.flags());
    }

    static Class class$java$util$regex$Pattern;
    private Converter defaultConverter;
}
