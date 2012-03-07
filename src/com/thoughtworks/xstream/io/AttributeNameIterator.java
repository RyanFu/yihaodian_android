// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;

import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.io:
//            HierarchicalStreamReader

public class AttributeNameIterator
    implements Iterator
{

    public AttributeNameIterator(HierarchicalStreamReader hierarchicalstreamreader)
    {
        reader = hierarchicalstreamreader;
        count = hierarchicalstreamreader.getAttributeCount();
    }

    @Override
	public boolean hasNext()
    {
        boolean flag;
        if(current < count)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public Object next()
    {
        HierarchicalStreamReader hierarchicalstreamreader = reader;
        int i = current;
        current = i + 1;
        return hierarchicalstreamreader.getAttributeName(i);
    }

    @Override
	public void remove()
    {
        throw new UnsupportedOperationException();
    }

    private final int count;
    private int current;
    private final HierarchicalStreamReader reader;
}
