// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.util.*;

public class PrioritizedList
{
    private static class PrioritizedItemIterator
        implements Iterator
    {

        @Override
		public boolean hasNext()
        {
            return iterator.hasNext();
        }

        @Override
		public Object next()
        {
            return ((PrioritizedItem)iterator.next()).value;
        }

        @Override
		public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private Iterator iterator;

        public PrioritizedItemIterator(Iterator iterator1)
        {
            iterator = iterator1;
        }
    }

    private static class PrioritizedItem
        implements Comparable
    {

        @Override
		public int compareTo(Object obj)
        {
            PrioritizedItem prioritizeditem = (PrioritizedItem)obj;
            int i;
            if(priority != prioritizeditem.priority)
                i = prioritizeditem.priority - priority;
            else
                i = prioritizeditem.id - id;
            return i;
        }

        @Override
		public boolean equals(Object obj)
        {
            boolean flag;
            if(id == ((PrioritizedItem)obj).id)
                flag = true;
            else
                flag = false;
            return flag;
        }

        final int id;
        final int priority;
        final Object value;

        public PrioritizedItem(Object obj, int i, int j)
        {
            value = obj;
            priority = i;
            id = j;
        }
    }


    public PrioritizedList()
    {
        lowestPriority = 0x7fffffff;
        lastId = 0;
    }

    public void add(Object obj, int i)
    {
        if(lowestPriority > i)
            lowestPriority = i;
        Set set1 = set;
        int j = 1 + lastId;
        lastId = j;
        set1.add(new PrioritizedItem(obj, i, j));
    }

    public Iterator iterator()
    {
        return new PrioritizedItemIterator(set.iterator());
    }

    private int lastId;
    private int lowestPriority;
    private final Set set = new TreeSet();
}
