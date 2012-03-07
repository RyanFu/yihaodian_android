// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.persistence:
//            XmlMap, PersistenceStrategy

public class XmlSet extends AbstractSet
{

    public XmlSet(PersistenceStrategy persistencestrategy)
    {
        map = new XmlMap(persistencestrategy);
    }

    private Long findEmptyKey()
    {
        long l;
        for(l = System.currentTimeMillis(); map.containsKey(new Long(l)); l++);
        return new Long(l);
    }

    @Override
	public boolean add(Object obj)
    {
        boolean flag;
        if(map.containsValue(obj))
        {
            flag = false;
        } else
        {
            map.put(findEmptyKey(), obj);
            flag = true;
        }
        return flag;
    }

    @Override
	public Iterator iterator()
    {
        return map.values().iterator();
    }

    @Override
	public int size()
    {
        return map.size();
    }

    private final XmlMap map;
}
