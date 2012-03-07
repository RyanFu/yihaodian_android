// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.javabean;

import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.javabean:
//            BeanProperty

public class PropertyDictionary
{
    private static class OrderRetainingMap extends HashMap
    {

        @Override
		public Object put(Object obj, Object obj1)
        {
            valueOrder.add(obj1);
            return super.put(obj, obj1);
        }

        @Override
		public Collection values()
        {
            return Collections.unmodifiableList(valueOrder);
        }

        private List valueOrder;

        private OrderRetainingMap()
        {
            valueOrder = new ArrayList();
        }

    }

    private static class BeanPropertyComparator
        implements Comparator
    {

        @Override
		public int compare(Object obj, Object obj1)
        {
            return ((BeanProperty)obj).getName().compareTo(((BeanProperty)obj1).getName());
        }

        private BeanPropertyComparator()
        {
        }

    }

    private static class PropertyKey
    {

        @Override
		public boolean equals(Object obj)
        {
            boolean flag;
            if(this == obj)
                flag = true;
            else
            if(!(obj instanceof PropertyKey))
            {
                flag = false;
            } else
            {
                PropertyKey propertykey = (PropertyKey)obj;
                if(propertyName == null ? propertykey.propertyName != null : !propertyName.equals(propertykey.propertyName))
                    flag = false;
                else
                if(propertyType == null ? propertykey.propertyType != null : !propertyType.equals(propertykey.propertyType))
                    flag = false;
                else
                    flag = true;
            }
            return flag;
        }

        @Override
		public int hashCode()
        {
            int i;
            int j;
            int k;
            if(propertyName != null)
                i = propertyName.hashCode();
            else
                i = 0;
            j = i * 29;
            if(propertyType != null)
                k = propertyType.hashCode();
            else
                k = 0;
            return j + k;
        }

        @Override
		public String toString()
        {
            return "PropertyKey{propertyName='" + propertyName + "'" + ", propertyType=" + propertyType + "}";
        }

        private String propertyName;
        private Class propertyType;

        public PropertyKey(String s, Class class1)
        {
            propertyName = s;
            propertyType = class1;
        }
    }


    public PropertyDictionary()
    {
    }

    private Map buildMap(Class class1)
    {
        String s = class1.getName();
        if(keyedByPropertyNameCache.containsKey(s)) goto _L2; else goto _L1
_L1:
        Map map = keyedByPropertyNameCache;
        map;
        JVM INSTR monitorenter ;
        if(keyedByPropertyNameCache.containsKey(s)) goto _L4; else goto _L3
_L3:
        HashMap hashmap;
        Method amethod[];
        int i;
        hashmap = new HashMap();
        amethod = class1.getMethods();
        i = 0;
_L15:
        int j = amethod.length;
        if(i >= j) goto _L6; else goto _L5
_L5:
        if(Modifier.isPublic(amethod[i].getModifiers()) && !Modifier.isStatic(amethod[i].getModifiers())) goto _L8; else goto _L7
_L8:
        String s1;
        Class aclass[];
        Class class2;
        s1 = amethod[i].getName();
        aclass = amethod[i].getParameterTypes();
        class2 = amethod[i].getReturnType();
        if(!s1.startsWith("get") && !s1.startsWith("is") || aclass.length != 0 || class2 == Void.TYPE) goto _L10; else goto _L9
_L9:
        if(!s1.startsWith("get")) goto _L12; else goto _L11
_L11:
        String s2 = Introspector.decapitalize(s1.substring(3));
_L13:
        getBeanProperty(hashmap, class1, s2, class2).setGetterMethod(amethod[i]);
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L12:
        s2 = Introspector.decapitalize(s1.substring(2));
        if(true) goto _L13; else goto _L7
_L10:
        if(s1.startsWith("set") && aclass.length == 1 && class2 == Void.TYPE)
            getBeanProperty(hashmap, class1, Introspector.decapitalize(s1.substring(3)), aclass[0]).setSetterMethod(amethod[i]);
          goto _L7
_L6:
        ArrayList arraylist = new ArrayList();
        Iterator iterator = hashmap.values().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            BeanProperty beanproperty1 = (BeanProperty)iterator.next();
            if(beanproperty1.isReadable() && beanproperty1.isWritable())
                arraylist.add(beanproperty1);
        } while(true);
        Collections.sort(arraylist, new BeanPropertyComparator());
        OrderRetainingMap orderretainingmap = new OrderRetainingMap();
        BeanProperty beanproperty;
        for(Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); orderretainingmap.put(beanproperty.getName(), beanproperty))
            beanproperty = (BeanProperty)iterator1.next();

        keyedByPropertyNameCache.put(s, orderretainingmap);
_L4:
        map;
        JVM INSTR monitorexit ;
_L2:
        return (Map)keyedByPropertyNameCache.get(s);
_L7:
        i++;
        if(true) goto _L15; else goto _L14
_L14:
    }

    private BeanProperty getBeanProperty(Map map, Class class1, String s, Class class2)
    {
        PropertyKey propertykey = new PropertyKey(s, class2);
        BeanProperty beanproperty = (BeanProperty)map.get(propertykey);
        if(beanproperty == null)
        {
            beanproperty = new BeanProperty(class1, s, class2);
            map.put(propertykey, beanproperty);
        }
        return beanproperty;
    }

    public BeanProperty property(Class class1, String s)
    {
        BeanProperty beanproperty = (BeanProperty)buildMap(class1).get(s);
        if(beanproperty == null)
            throw new ObjectAccessException("No such property " + class1.getName() + "." + s);
        else
            return beanproperty;
    }

    public Iterator serializablePropertiesFor(Class class1)
    {
        return buildMap(class1).values().iterator();
    }

    private final Map keyedByPropertyNameCache = Collections.synchronizedMap(new HashMap());
}
