// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.lang.reflect.Array;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.collections:
//            AbstractCollectionConverter

public class ArrayConverter extends AbstractCollectionConverter
{

    public ArrayConverter(Mapper mapper)
    {
        super(mapper);
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return class1.isArray();
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        int i = Array.getLength(obj);
        for(int j = 0; j < i; j++)
            writeItem(Array.get(obj, j), marshallingcontext, hierarchicalstreamwriter);

    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        ArrayList arraylist = new ArrayList();
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            arraylist.add(readItem(hierarchicalstreamreader, unmarshallingcontext, null));
        }

        Object obj = Array.newInstance(unmarshallingcontext.getRequiredType().getComponentType(), arraylist.size());
        int i = 0;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            int j = i + 1;
            Array.set(obj, i, iterator.next());
            i = j;
        }

        return obj;
    }
}
