// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.collections:
//            AbstractCollectionConverter

public class MapConverter extends AbstractCollectionConverter
{

    public MapConverter(Mapper mapper)
    {
        super(mapper);
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
        Class class3;
        if(class$java$util$HashMap == null)
        {
            class2 = _mthclass$("java.util.HashMap");
            class$java$util$HashMap = class2;
        } else
        {
            class2 = class$java$util$HashMap;
        }
        if(class1.equals(class2)) goto _L2; else goto _L1
_L1:
        if(class$java$util$Hashtable == null)
        {
            class3 = _mthclass$("java.util.Hashtable");
            class$java$util$Hashtable = class3;
        } else
        {
            class3 = class$java$util$Hashtable;
        }
        if(!class1.equals(class3) && !class1.getName().equals("java.util.LinkedHashMap") && !class1.getName().equals("sun.font.AttributeMap")) goto _L3; else goto _L2
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
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Iterator iterator = ((Map)obj).entrySet().iterator();
        while(iterator.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Mapper mapper = mapper();
            Class class1;
            String s;
            Class class2;
            if(class$java$util$Map$Entry == null)
            {
                class1 = _mthclass$("java.util.Map$Entry");
                class$java$util$Map$Entry = class1;
            } else
            {
                class1 = class$java$util$Map$Entry;
            }
            s = mapper.serializedClass(class1);
            if(class$java$util$Map$Entry == null)
            {
                class2 = _mthclass$("java.util.Map$Entry");
                class$java$util$Map$Entry = class2;
            } else
            {
                class2 = class$java$util$Map$Entry;
            }
            ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, s, class2);
            writeItem(entry.getKey(), marshallingcontext, hierarchicalstreamwriter);
            writeItem(entry.getValue(), marshallingcontext, hierarchicalstreamwriter);
            hierarchicalstreamwriter.endNode();
        }
    }

    protected void populateMap(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext, Map map)
    {
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            hierarchicalstreamreader.moveDown();
            Object obj = readItem(hierarchicalstreamreader, unmarshallingcontext, map);
            hierarchicalstreamreader.moveUp();
            hierarchicalstreamreader.moveDown();
            Object obj1 = readItem(hierarchicalstreamreader, unmarshallingcontext, map);
            hierarchicalstreamreader.moveUp();
            map.put(obj, obj1);
        }

    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Map map = (Map)createCollection(unmarshallingcontext.getRequiredType());
        populateMap(hierarchicalstreamreader, unmarshallingcontext, map);
        return map;
    }

    static Class class$java$util$HashMap;
    static Class class$java$util$Hashtable;
    static Class class$java$util$Map$Entry;
}
