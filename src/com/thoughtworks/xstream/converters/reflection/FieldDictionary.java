// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.core.JVM;
import com.thoughtworks.xstream.core.util.OrderRetainingMap;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            ImmutableFieldKeySorter, FieldKey, FieldKeySorter, ObjectAccessException

public class FieldDictionary
{

    public FieldDictionary()
    {
        this(((new ImmutableFieldKeySorter())));
    }

    public FieldDictionary(FieldKeySorter fieldkeysorter)
    {
        sorter = fieldkeysorter;
        init();
    }

    private Map buildMap(Class class1, boolean flag)
    {
        Class class2 = class1;
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        if(!keyedByFieldNameCache.containsKey(class1))
        {
            arraylist = new ArrayList();
            do
            {
                Class class3;
                if(class$java$lang$Object == null)
                {
                    class3 = _mthclass$("java.lang.Object");
                    class$java$lang$Object = class3;
                } else
                {
                    class3 = class$java$lang$Object;
                }
                if(class3.equals(class2))
                    break MISSING_BLOCK_LABEL_78;
                arraylist.add(0, class2);
                class2 = class2.getSuperclass();
            } while(true);
        }
          goto _L1
        Map map;
        Map map1;
        Iterator iterator;
        map = Collections.EMPTY_MAP;
        map1 = Collections.EMPTY_MAP;
        iterator = arraylist.iterator();
_L7:
        if(!iterator.hasNext()) goto _L1; else goto _L2
_L2:
        Class class4;
        HashMap hashmap;
        OrderRetainingMap orderretainingmap;
        Field afield[];
        class4 = (Class)iterator.next();
        if(!keyedByFieldNameCache.containsKey(class4))
        {
            hashmap = new HashMap(map);
            orderretainingmap = new OrderRetainingMap(map1);
            afield = class4.getDeclaredFields();
            if(JVM.reverseFieldDefinition())
            {
                int k = afield.length >> 1;
                do
                {
                    int l = k - 1;
                    if(k <= 0)
                        break;
                    int i1 = afield.length - l - 1;
                    Field field3 = afield[l];
                    afield[l] = afield[i1];
                    afield[i1] = field3;
                    k = l;
                } while(true);
            }
            break MISSING_BLOCK_LABEL_474;
        }
          goto _L3
_L6:
        int j = afield.length;
        int i;
        if(i >= j) goto _L5; else goto _L4
_L4:
        Field field1 = afield[i];
        FieldKey fieldkey = new FieldKey(field1.getName(), field1.getDeclaringClass(), i);
        field1.setAccessible(true);
        Field field2 = (Field)hashmap.get(field1.getName());
        if(field2 == null || (8 & field2.getModifiers()) != 0 || field2 != null && (8 & field1.getModifiers()) == 0)
            hashmap.put(field1.getName(), field1);
        orderretainingmap.put(fieldkey, field1);
        i++;
          goto _L6
_L5:
        keyedByFieldNameCache.put(class4, hashmap);
        keyedByFieldKeyCache.put(class4, sorter.sort(class1, orderretainingmap));
_L3:
        map = (Map)keyedByFieldNameCache.get(class4);
        map1 = (Map)keyedByFieldKeyCache.get(class4);
          goto _L7
_L1:
        this;
        JVM INSTR monitorexit ;
        Exception exception;
        Object obj;
        if(flag)
            obj = keyedByFieldKeyCache.get(class1);
        else
            obj = keyedByFieldNameCache.get(class1);
        return (Map)obj;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        i = 0;
          goto _L6
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    private void init()
    {
        keyedByFieldNameCache = new WeakHashMap();
        keyedByFieldKeyCache = new WeakHashMap();
        Map map = keyedByFieldNameCache;
        Class class1;
        Map map1;
        Class class2;
        if(class$java$lang$Object == null)
        {
            class1 = _mthclass$("java.lang.Object");
            class$java$lang$Object = class1;
        } else
        {
            class1 = class$java$lang$Object;
        }
        map.put(class1, Collections.EMPTY_MAP);
        map1 = keyedByFieldKeyCache;
        if(class$java$lang$Object == null)
        {
            class2 = _mthclass$("java.lang.Object");
            class$java$lang$Object = class2;
        } else
        {
            class2 = class$java$lang$Object;
        }
        map1.put(class2, Collections.EMPTY_MAP);
    }

    public Field field(Class class1, String s, Class class2)
    {
        boolean flag;
        Map map;
        Object obj;
        Field field1;
        if(class2 != null)
            flag = true;
        else
            flag = false;
        map = buildMap(class1, flag);
        if(class2 != null)
            obj = new FieldKey(s, class2, 0);
        else
            obj = s;
        field1 = (Field)map.get(obj);
        if(field1 == null)
            throw new ObjectAccessException("No such field " + class1.getName() + "." + s);
        else
            return field1;
    }

    public Iterator fieldsFor(Class class1)
    {
        return buildMap(class1, true).values().iterator();
    }

    protected Object readResolve()
    {
        init();
        return this;
    }

    public Iterator serializableFieldsFor(Class class1)
    {
        return fieldsFor(class1);
    }

    static Class class$java$lang$Object;
    private transient Map keyedByFieldKeyCache;
    private transient Map keyedByFieldNameCache;
    private final FieldKeySorter sorter;
}
