// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.lang.ref.WeakReference;
import java.util.*;

public class ObjectIdDictionary
{
    private static class WeakIdWrapper
        implements Wrapper
    {

        @Override
		public boolean equals(Object obj)
        {
            boolean flag;
            if(get() == ((Wrapper)obj).get())
                flag = true;
            else
                flag = false;
            return flag;
        }

        @Override
		public Object get()
        {
            return ref.get();
        }

        @Override
		public int hashCode()
        {
            return hashCode;
        }

        @Override
		public String toString()
        {
            Object obj = get();
            String s;
            if(obj == null)
                s = "(null)";
            else
                s = obj.toString();
            return s;
        }

        private final int hashCode;
        private final WeakReference ref;

        public WeakIdWrapper(Object obj)
        {
            hashCode = System.identityHashCode(obj);
            ref = new WeakReference(obj);
        }
    }

    private static class IdWrapper
        implements Wrapper
    {

        @Override
		public boolean equals(Object obj1)
        {
            boolean flag;
            if(obj == ((Wrapper)obj1).get())
                flag = true;
            else
                flag = false;
            return flag;
        }

        @Override
		public Object get()
        {
            return obj;
        }

        @Override
		public int hashCode()
        {
            return hashCode;
        }

        @Override
		public String toString()
        {
            return obj.toString();
        }

        private final int hashCode;
        private final Object obj;

        public IdWrapper(Object obj1)
        {
            hashCode = System.identityHashCode(obj1);
            obj = obj1;
        }
    }

    private static interface Wrapper
    {

        @Override
		public abstract boolean equals(Object obj);

        public abstract Object get();

        @Override
		public abstract int hashCode();

        @Override
		public abstract String toString();
    }


    public ObjectIdDictionary()
    {
    }

    private void cleanup()
    {
        if(counter > 10000)
        {
            counter = 0;
            Iterator iterator = map.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                if(((WeakIdWrapper)iterator.next()).get() == null)
                    iterator.remove();
            } while(true);
        }
    }

    public void associateId(Object obj, Object obj1)
    {
        map.put(new WeakIdWrapper(obj), obj1);
        counter = 1 + counter;
        cleanup();
    }

    public boolean containsId(Object obj)
    {
        boolean flag = map.containsKey(new IdWrapper(obj));
        counter = 1 + counter;
        return flag;
    }

    public Object lookupId(Object obj)
    {
        Object obj1 = map.get(new IdWrapper(obj));
        counter = 1 + counter;
        return obj1;
    }

    public void removeId(Object obj)
    {
        map.remove(new IdWrapper(obj));
        counter = 1 + counter;
        cleanup();
    }

    public int size()
    {
        return map.size();
    }

    private volatile int counter;
    private final Map map = new HashMap();
}
