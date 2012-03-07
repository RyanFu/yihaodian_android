// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.util.*;

public class OrderRetainingMap extends HashMap
{
    private static class ArraySet extends ArrayList
        implements Set
    {

        private ArraySet()
        {
        }

    }


    public OrderRetainingMap()
    {
        keyOrder = new ArraySet();
        valueOrder = new ArrayList();
    }

    public OrderRetainingMap(Map map)
    {
        keyOrder = new ArraySet();
        valueOrder = new ArrayList();
        java.util.Map.Entry entry;
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); put(entry.getKey(), entry.getValue()))
            entry = (java.util.Map.Entry)iterator.next();

    }

    @Override
	public Set entrySet()
    {
        java.util.Map.Entry aentry[] = new java.util.Map.Entry[size()];
        for(Iterator iterator = super.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            aentry[keyOrder.indexOf(entry.getKey())] = entry;
        }

        ArraySet arrayset = new ArraySet();
        arrayset.addAll(Arrays.asList(aentry));
        return Collections.unmodifiableSet(arrayset);
    }

    @Override
	public Set keySet()
    {
        return Collections.unmodifiableSet(keyOrder);
    }

    @Override
	public Object put(Object obj, Object obj1)
    {
        int i = keyOrder.lastIndexOf(obj);
        if(i < 0)
        {
            keyOrder.add(obj);
            valueOrder.add(obj1);
        } else
        {
            valueOrder.set(i, obj1);
        }
        return super.put(obj, obj1);
    }

    @Override
	public Object remove(Object obj)
    {
        int i = keyOrder.lastIndexOf(obj);
        if(i != 0)
        {
            keyOrder.remove(i);
            valueOrder.remove(i);
        }
        return super.remove(obj);
    }

    @Override
	public Collection values()
    {
        return Collections.unmodifiableList(valueOrder);
    }

    private ArraySet keyOrder;
    private List valueOrder;
}
