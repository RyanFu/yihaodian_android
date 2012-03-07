// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.FastStack;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.core:
//            TreeUnmarshaller

public abstract class AbstractReferenceUnmarshaller extends TreeUnmarshaller
{

    public AbstractReferenceUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper)
    {
        super(obj, hierarchicalstreamreader, converterlookup, mapper);
        values = new HashMap();
        parentStack = new FastStack(16);
    }

    @Override
	protected Object convert(Object obj, Class class1, Converter converter)
    {
        if(parentStack.size() > 0)
        {
            Object obj3 = parentStack.peek();
            if(obj3 != null && !values.containsKey(obj3))
                values.put(obj3, obj);
        }
        String s = getMapper().aliasForSystemAttribute("reference");
        String s1;
        Object obj2;
        if(s == null)
            s1 = null;
        else
            s1 = reader.getAttribute(s);
        if(s1 != null)
        {
            obj2 = values.get(getReferenceKey(s1));
            if(obj2 == null)
            {
                ConversionException conversionexception = new ConversionException("Invalid reference");
                conversionexception.add("reference", s1);
                throw conversionexception;
            }
        } else
        {
            Object obj1 = getCurrentReferenceKey();
            parentStack.push(obj1);
            obj2 = super.convert(obj, class1, converter);
            if(obj1 != null && obj2 != null)
                values.put(obj1, obj2);
            parentStack.popSilently();
        }
        return obj2;
    }

    protected abstract Object getCurrentReferenceKey();

    protected abstract Object getReferenceKey(String s);

    private FastStack parentStack;
    private Map values;
}
