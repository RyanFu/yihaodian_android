// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.core.util.Fields;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.lang.reflect.Field;
import java.util.*;

public class PropertiesConverter
    implements Converter
{

    public PropertiesConverter()
    {
        this(false);
    }

    public PropertiesConverter(boolean flag)
    {
        sort = flag;
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
        boolean flag;
        if(class$java$util$Properties == null)
        {
            class2 = _mthclass$("java.util.Properties");
            class$java$util$Properties = class2;
        } else
        {
            class2 = class$java$util$Properties;
        }
        if(class2 == class1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Properties properties = (Properties)obj;
        Object obj1;
        Iterator iterator;
        if(sort)
            obj1 = new TreeMap(properties);
        else
            obj1 = properties;
        for(iterator = ((Map) (obj1)).entrySet().iterator(); iterator.hasNext(); hierarchicalstreamwriter.endNode())
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            hierarchicalstreamwriter.startNode("property");
            hierarchicalstreamwriter.addAttribute("name", entry.getKey().toString());
            hierarchicalstreamwriter.addAttribute("value", entry.getValue().toString());
        }

        Properties properties1 = (Properties)Fields.read(defaultsField, properties);
        if(properties1 != null)
        {
            hierarchicalstreamwriter.startNode("defaults");
            marshal(properties1, hierarchicalstreamwriter, marshallingcontext);
            hierarchicalstreamwriter.endNode();
        }
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Properties properties = new Properties();
        while(hierarchicalstreamreader.hasMoreChildren()) 
        {
            hierarchicalstreamreader.moveDown();
            if(hierarchicalstreamreader.getNodeName().equals("defaults"))
            {
                Properties properties1 = (Properties)unmarshal(hierarchicalstreamreader, unmarshallingcontext);
                Fields.write(defaultsField, properties, properties1);
            } else
            {
                properties.setProperty(hierarchicalstreamreader.getAttribute("name"), hierarchicalstreamreader.getAttribute("value"));
            }
            hierarchicalstreamreader.moveUp();
        }
        return properties;
    }

    static Class class$java$util$Properties;
    private static final Field defaultsField;
    private final boolean sort;

    static 
    {
        try
        {
            Class class1;
            if(class$java$util$Properties == null)
            {
                class1 = _mthclass$("java.util.Properties");
                class$java$util$Properties = class1;
            } else
            {
                class1 = class$java$util$Properties;
            }
            defaultsField = Fields.find(class1, "defaults");
        }
        catch(RuntimeException runtimeexception)
        {
            throw new ExceptionInInitializerError("Cannot access defaults field of Properties");
        }
    }
}
