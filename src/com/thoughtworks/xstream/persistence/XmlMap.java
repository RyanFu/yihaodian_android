// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.persistence:
//            PersistenceStrategy

public class XmlMap extends AbstractMap
{
    class XmlMapEntries extends AbstractSet
    {

        @Override
		public boolean isEmpty()
        {
            return XmlMap.this.isEmpty();
        }

        @Override
		public Iterator iterator()
        {
            return persistenceStrategy.iterator();
        }

        @Override
		public int size()
        {
            return XmlMap.this.size();
        }

        private final XmlMap this$0;

        XmlMapEntries()
        {
            this$0 = XmlMap.this;
        }
    }


    public XmlMap(PersistenceStrategy persistencestrategy)
    {
        persistenceStrategy = persistencestrategy;
    }

    @Override
	public Set entrySet()
    {
        return new XmlMapEntries();
    }

    @Override
	public Object get(Object obj)
    {
        return persistenceStrategy.get(obj);
    }

    @Override
	public Object put(Object obj, Object obj1)
    {
        return persistenceStrategy.put(obj, obj1);
    }

    @Override
	public Object remove(Object obj)
    {
        return persistenceStrategy.remove(obj);
    }

    @Override
	public int size()
    {
        return persistenceStrategy.size();
    }

    private final PersistenceStrategy persistenceStrategy;

}
