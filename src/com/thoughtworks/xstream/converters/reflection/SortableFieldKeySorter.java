// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.core.util.OrderRetainingMap;
import com.thoughtworks.xstream.io.StreamException;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            FieldKeySorter, FieldKey

public class SortableFieldKeySorter
    implements FieldKeySorter
{
    private class FieldComparator
        implements Comparator
    {

        @Override
		public int compare(Object obj, Object obj1)
        {
            FieldKey fieldkey = (FieldKey)obj;
            FieldKey fieldkey1 = (FieldKey)obj1;
            fieldkey.getDeclaringClass();
            return compare(fieldkey.getFieldName(), fieldkey1.getFieldName());
        }

        public int compare(String s, String s1)
        {
            int i = -1;
            int j = -1;
            for(int k = 0; k < fieldOrder.length; k++)
            {
                if(fieldOrder[k].equals(s))
                    i = k;
                if(fieldOrder[k].equals(s1))
                    j = k;
            }

            if(i == -1 || j == -1)
                throw new StreamException("You have not given XStream a list of all fields to be serialized.");
            else
                return i - j;
        }

        private final String fieldOrder[];
        private final SortableFieldKeySorter this$0;

        public FieldComparator(String as[])
        {
            this$0 = SortableFieldKeySorter.this;
            fieldOrder = as;
        }
    }


    public SortableFieldKeySorter()
    {
    }

    public void registerFieldOrder(Class class1, String as[])
    {
        map.put(class1, new FieldComparator(as));
    }

    @Override
	public Map sort(Class class1, Map map1)
    {
        Object obj;
        if(map.containsKey(class1))
        {
            OrderRetainingMap orderretainingmap = new OrderRetainingMap();
            FieldKey afieldkey[] = (FieldKey[])map1.keySet().toArray(new FieldKey[map1.size()]);
            Arrays.sort(afieldkey, (Comparator)map.get(class1));
            for(int i = 0; i < afieldkey.length; i++)
                orderretainingmap.put(afieldkey[i], map1.get(afieldkey[i]));

            obj = orderretainingmap;
        } else
        {
            obj = map1;
        }
        return ((Map) (obj));
    }

    private final Map map = new WeakHashMap();
}
