// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.javabean;

import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;
import com.thoughtworks.xstream.core.util.OrderRetainingMap;
import java.beans.*;
import java.lang.reflect.*;
import java.util.*;

public class BeanProvider
{
    static interface Visitor
    {

        public abstract boolean shouldVisit(String s, Class class1);

        public abstract void visit(String s, Class class1, Class class2, Object obj);
    }


    public BeanProvider()
    {
        this(null);
    }

    public BeanProvider(Comparator comparator)
    {
        propertyNameCache = new WeakHashMap();
        propertyNameComparator = comparator;
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

    private Map getNameMap(Class class1)
    {
        Object obj;
        obj = propertyNameCache.get(class1);
        if(obj != null)
            break MISSING_BLOCK_LABEL_151;
        Class class2;
        if(class$java$lang$Object != null)
            break MISSING_BLOCK_LABEL_111;
        class2 = _mthclass$("java.lang.Object");
        class$java$lang$Object = class2;
_L1:
        BeanInfo beaninfo = Introspector.getBeanInfo(class1, class2);
        obj = new OrderRetainingMap();
        propertyNameCache.put(class1, obj);
        PropertyDescriptor apropertydescriptor[] = beaninfo.getPropertyDescriptors();
        for(int i = 0; i < apropertydescriptor.length; i++)
        {
            PropertyDescriptor propertydescriptor = apropertydescriptor[i];
            ((Map) (obj)).put(propertydescriptor.getName(), propertydescriptor);
        }

        break MISSING_BLOCK_LABEL_151;
        try
        {
            class2 = class$java$lang$Object;
        }
        catch(IntrospectionException introspectionexception)
        {
            throw new ObjectAccessException("Cannot get BeanInfo of type " + class1.getName(), introspectionexception);
        }
          goto _L1
        return ((Map) (obj));
    }

    private PropertyDescriptor getProperty(String s, Class class1)
    {
        return (PropertyDescriptor)getNameMap(class1).get(s);
    }

    private PropertyDescriptor[] getSerializableProperties(Object obj)
    {
        Map map = getNameMap(obj.getClass());
        ArrayList arraylist = new ArrayList(map.size());
        Object obj1 = map.keySet();
        if(propertyNameComparator != null)
        {
            TreeSet treeset = new TreeSet(propertyNameComparator);
            treeset.addAll(((java.util.Collection) (obj1)));
            obj1 = treeset;
        }
        Iterator iterator = ((Set) (obj1)).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            PropertyDescriptor propertydescriptor = (PropertyDescriptor)map.get(iterator.next());
            if(canStreamProperty(propertydescriptor))
                arraylist.add(propertydescriptor);
        } while(true);
        return (PropertyDescriptor[])(PropertyDescriptor[])arraylist.toArray(new PropertyDescriptor[arraylist.size()]);
    }

    public boolean canInstantiate(Class class1)
    {
        boolean flag;
        if(getDefaultConstrutor(class1) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected boolean canStreamProperty(PropertyDescriptor propertydescriptor)
    {
        boolean flag;
        if(propertydescriptor.getReadMethod() != null && propertydescriptor.getWriteMethod() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected Constructor getDefaultConstrutor(Class class1)
    {
        Constructor aconstructor[];
        int i;
        aconstructor = class1.getConstructors();
        i = 0;
_L3:
        Constructor constructor1;
        if(i >= aconstructor.length)
            break MISSING_BLOCK_LABEL_51;
        constructor1 = aconstructor[i];
        if(constructor1.getParameterTypes().length != 0 || !Modifier.isPublic(constructor1.getModifiers())) goto _L2; else goto _L1
_L1:
        Constructor constructor = constructor1;
_L4:
        return constructor;
_L2:
        i++;
          goto _L3
        constructor = null;
          goto _L4
    }

    public Class getPropertyType(Object obj, String s)
    {
        return getProperty(s, obj.getClass()).getPropertyType();
    }

    public Object newInstance(Class class1)
    {
        Object obj;
        try
        {
            obj = getDefaultConstrutor(class1).newInstance(NO_PARAMS);
        }
        catch(InstantiationException instantiationexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            if(invocationtargetexception.getTargetException() instanceof RuntimeException)
                throw (RuntimeException)invocationtargetexception.getTargetException();
            if(invocationtargetexception.getTargetException() instanceof Error)
                throw (Error)invocationtargetexception.getTargetException();
            else
                throw new ObjectAccessException("Constructor for " + class1.getName() + " threw an exception", invocationtargetexception);
        }
        return obj;
    }

    public boolean propertyDefinedInClass(String s, Class class1)
    {
        boolean flag;
        if(getProperty(s, class1) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean propertyWriteable(String s, Class class1)
    {
        boolean flag;
        if(getProperty(s, class1).getWriteMethod() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void visitSerializableProperties(Object obj, Visitor visitor)
    {
        PropertyDescriptor apropertydescriptor[] = getSerializableProperties(obj);
        int i = 0;
        while(i < apropertydescriptor.length) 
        {
            PropertyDescriptor propertydescriptor = apropertydescriptor[i];
            try
            {
                Method method = propertydescriptor.getReadMethod();
                String s = propertydescriptor.getName();
                Class class1 = method.getDeclaringClass();
                if(visitor.shouldVisit(s, class1))
                {
                    Object obj1 = method.invoke(obj, new Object[0]);
                    visitor.visit(s, propertydescriptor.getPropertyType(), class1, obj1);
                }
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw new ObjectAccessException("Could not get property " + obj.getClass() + "." + propertydescriptor.getName(), illegalargumentexception);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new ObjectAccessException("Could not get property " + obj.getClass() + "." + propertydescriptor.getName(), illegalaccessexception);
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                throw new ObjectAccessException("Could not get property " + obj.getClass() + "." + propertydescriptor.getName(), invocationtargetexception);
            }
            i++;
        }
    }

    public void writeProperty(Object obj, String s, Object obj1)
    {
        PropertyDescriptor propertydescriptor = getProperty(s, obj.getClass());
        try
        {
            Method method = propertydescriptor.getWriteMethod();
            Object aobj[] = new Object[1];
            aobj[0] = obj1;
            method.invoke(obj, aobj);
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ObjectAccessException("Could not set property " + obj.getClass() + "." + propertydescriptor.getName(), illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException("Could not set property " + obj.getClass() + "." + propertydescriptor.getName(), illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ObjectAccessException("Could not set property " + obj.getClass() + "." + propertydescriptor.getName(), invocationtargetexception);
        }
    }

    protected static final Object NO_PARAMS[] = new Object[0];
    static Class class$java$lang$Object;
    private final transient Map propertyNameCache;
    private final Comparator propertyNameComparator;

}
