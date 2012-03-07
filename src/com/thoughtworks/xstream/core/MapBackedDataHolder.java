// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.converters.DataHolder;
import java.util.*;

public class MapBackedDataHolder
    implements DataHolder
{

    public MapBackedDataHolder()
    {
        this(((new HashMap())));
    }

    public MapBackedDataHolder(Map map1)
    {
        map = map1;
    }

    @Override
	public Object get(Object obj)
    {
        return map.get(obj);
    }

    @Override
	public Iterator keys()
    {
        return Collections.unmodifiableCollection(map.keySet()).iterator();
    }

    @Override
	public void put(Object obj, Object obj1)
    {
        map.put(obj, obj1);
    }

    private final Map map;
}
