// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.core.JVM;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.converters.collections:
//            AbstractCollectionConverter

public class CollectionConverter extends AbstractCollectionConverter
{

    public CollectionConverter(Mapper mapper)
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
        Class class4;
        Class class5;
        if(class$java$util$ArrayList == null)
        {
            class2 = _mthclass$("java.util.ArrayList");
            class$java$util$ArrayList = class2;
        } else
        {
            class2 = class$java$util$ArrayList;
        }
        if(class1.equals(class2)) goto _L2; else goto _L1
_L1:
        if(class$java$util$HashSet == null)
        {
            class3 = _mthclass$("java.util.HashSet");
            class$java$util$HashSet = class3;
        } else
        {
            class3 = class$java$util$HashSet;
        }
        if(class1.equals(class3)) goto _L2; else goto _L3
_L3:
        if(class$java$util$LinkedList == null)
        {
            class4 = _mthclass$("java.util.LinkedList");
            class$java$util$LinkedList = class4;
        } else
        {
            class4 = class$java$util$LinkedList;
        }
        if(class1.equals(class4)) goto _L2; else goto _L4
_L4:
        if(class$java$util$Vector == null)
        {
            class5 = _mthclass$("java.util.Vector");
            class$java$util$Vector = class5;
        } else
        {
            class5 = class$java$util$Vector;
        }
        if(!class1.equals(class5) && (!JVM.is14() || !class1.getName().equals("java.util.LinkedHashSet"))) goto _L5; else goto _L2
_L2:
        flag = true;
_L7:
        return flag;
_L5:
        flag = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        for(Iterator iterator = ((Collection)obj).iterator(); iterator.hasNext(); writeItem(iterator.next(), marshallingcontext, hierarchicalstreamwriter));
    }

    protected void populateCollection(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext, Collection collection)
    {
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            collection.add(readItem(hierarchicalstreamreader, unmarshallingcontext, collection));
        }

    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Collection collection = (Collection)createCollection(unmarshallingcontext.getRequiredType());
        populateCollection(hierarchicalstreamreader, unmarshallingcontext, collection);
        return collection;
    }

    static Class class$java$util$ArrayList;
    static Class class$java$util$HashSet;
    static Class class$java$util$LinkedList;
    static Class class$java$util$Vector;
}
