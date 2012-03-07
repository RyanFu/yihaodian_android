// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import java.util.AbstractList;

// Referenced classes of package com.thoughtworks.xstream.persistence:
//            XmlMap, PersistenceStrategy

public class XmlArrayList extends AbstractList
{

    public XmlArrayList(PersistenceStrategy persistencestrategy)
    {
        map = new XmlMap(persistencestrategy);
    }

    private void rangeCheck(int i)
    {
        int j = size();
        if(i >= j || i < 0)
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + j);
        else
            return;
    }

    @Override
	public void add(int i, Object obj)
    {
        int j = size();
        if(i >= j + 1 || i < 0)
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + j);
        int k;
        int l;
        if(i != j)
            k = i - 1;
        else
            k = i;
        for(l = j; l > k; l--)
            map.put(new Integer(l + 1), map.get(new Integer(l)));

        map.put(new Integer(i), obj);
    }

    @Override
	public Object get(int i)
    {
        rangeCheck(i);
        return map.get(new Integer(i));
    }

    @Override
	public Object remove(int i)
    {
        int j = size();
        rangeCheck(i);
        Object obj = map.get(new Integer(i));
        for(int k = i; k < j - 1; k++)
            map.put(new Integer(k), map.get(new Integer(k + 1)));

        map.remove(new Integer(j - 1));
        return obj;
    }

    @Override
	public Object set(int i, Object obj)
    {
        rangeCheck(i);
        Object obj1 = get(i);
        map.put(new Integer(i), obj);
        return obj1;
    }

    @Override
	public int size()
    {
        return map.size();
    }

    private final XmlMap map;
}
