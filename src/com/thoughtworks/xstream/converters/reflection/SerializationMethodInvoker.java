// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.converters.ConversionException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            ObjectAccessException

public class SerializationMethodInvoker
{

    public SerializationMethodInvoker()
    {
        cache = Collections.synchronizedMap(new HashMap());
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

    private Method getMethod(Class class1, String s, Class aclass[], boolean flag)
    {
        String s2;
        Object obj;
        String s1 = class1.getName();
        StringBuffer stringbuffer = new StringBuffer(7 + (s1.length() + s.length()));
        stringbuffer.append(s1).append('.').append(s).append('.').append(flag).toString();
        s2 = stringbuffer.toString();
        obj = cache.get(s2);
        if(obj == null) goto _L2; else goto _L1
_L1:
        Method method;
        if(obj == NO_METHOD)
            method = null;
        else
            method = (Method)obj;
_L6:
        return method;
_L2:
        if(!flag)
            break MISSING_BLOCK_LABEL_182;
_L4:
        if(class1 == null)
            break; /* Loop/switch isn't completed */
        Method method2;
        method2 = class1.getDeclaredMethod(s, aclass);
        method2.setAccessible(true);
        cache.put(s2, method2);
        method = method2;
        continue; /* Loop/switch isn't completed */
        NoSuchMethodException nosuchmethodexception1;
        nosuchmethodexception1;
        class1 = class1.getSuperclass();
        if(true) goto _L4; else goto _L3
_L3:
        cache.put(s2, NO_METHOD);
        method = null;
        continue; /* Loop/switch isn't completed */
        Method method1;
        method1 = class1.getDeclaredMethod(s, aclass);
        method1.setAccessible(true);
        cache.put(s2, method1);
        method = method1;
        continue; /* Loop/switch isn't completed */
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        cache.put(s2, NO_METHOD);
        method = null;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void callReadObject(Class class1, Object obj, ObjectInputStream objectinputstream)
    {
        try
        {
            Class aclass[] = new Class[1];
            Class class2;
            Method method;
            Object aobj[];
            if(class$java$io$ObjectInputStream == null)
            {
                class2 = _mthclass$("java.io.ObjectInputStream");
                class$java$io$ObjectInputStream = class2;
            } else
            {
                class2 = class$java$io$ObjectInputStream;
            }
            aclass[0] = class2;
            method = getMethod(class1, "readObject", aclass, false);
            aobj = new Object[1];
            aobj[0] = objectinputstream;
            method.invoke(obj, aobj);
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ConversionException("Could not call " + obj.getClass().getName() + ".readObject()", illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ConversionException("Could not call " + obj.getClass().getName() + ".readObject()", invocationtargetexception.getTargetException());
        }
    }

    public Object callReadResolve(Object obj)
    {
        Object obj1;
        if(obj == null)
        {
            obj1 = null;
        } else
        {
            Method method = getMethod(obj.getClass(), "readResolve", null, true);
            if(method != null)
            {
                Object obj2;
                try
                {
                    obj2 = method.invoke(obj, EMPTY_ARGS);
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                    throw new ObjectAccessException("Could not call " + obj.getClass().getName() + ".readResolve()", illegalaccessexception);
                }
                catch(InvocationTargetException invocationtargetexception)
                {
                    throw new ObjectAccessException("Could not call " + obj.getClass().getName() + ".readResolve()", invocationtargetexception.getTargetException());
                }
                obj1 = obj2;
            } else
            {
                obj1 = obj;
            }
        }
        return obj1;
    }

    public void callWriteObject(Class class1, Object obj, ObjectOutputStream objectoutputstream)
    {
        try
        {
            Class aclass[] = new Class[1];
            Class class2;
            Method method;
            Object aobj[];
            if(class$java$io$ObjectOutputStream == null)
            {
                class2 = _mthclass$("java.io.ObjectOutputStream");
                class$java$io$ObjectOutputStream = class2;
            } else
            {
                class2 = class$java$io$ObjectOutputStream;
            }
            aclass[0] = class2;
            method = getMethod(class1, "writeObject", aclass, false);
            aobj = new Object[1];
            aobj[0] = objectoutputstream;
            method.invoke(obj, aobj);
            return;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ConversionException("Could not call " + obj.getClass().getName() + ".writeObject()", illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ConversionException("Could not call " + obj.getClass().getName() + ".writeObject()", invocationtargetexception.getTargetException());
        }
    }

    public Object callWriteReplace(Object obj)
    {
        Object obj1;
        if(obj == null)
        {
            obj1 = null;
        } else
        {
            Method method = getMethod(obj.getClass(), "writeReplace", null, true);
            if(method != null)
            {
                Object obj2;
                try
                {
                    obj2 = method.invoke(obj, new Object[0]);
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                    throw new ObjectAccessException("Could not call " + obj.getClass().getName() + ".writeReplace()", illegalaccessexception);
                }
                catch(InvocationTargetException invocationtargetexception)
                {
                    throw new ObjectAccessException("Could not call " + obj.getClass().getName() + ".writeReplace()", invocationtargetexception.getTargetException());
                }
                obj1 = obj2;
            } else
            {
                obj1 = obj;
            }
        }
        return obj1;
    }

    public boolean supportsReadObject(Class class1, boolean flag)
    {
        Class aclass[] = new Class[1];
        Class class2;
        boolean flag1;
        if(class$java$io$ObjectInputStream == null)
        {
            class2 = _mthclass$("java.io.ObjectInputStream");
            class$java$io$ObjectInputStream = class2;
        } else
        {
            class2 = class$java$io$ObjectInputStream;
        }
        aclass[0] = class2;
        if(getMethod(class1, "readObject", aclass, flag) != null)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
    }

    public boolean supportsWriteObject(Class class1, boolean flag)
    {
        Class aclass[] = new Class[1];
        Class class2;
        boolean flag1;
        if(class$java$io$ObjectOutputStream == null)
        {
            class2 = _mthclass$("java.io.ObjectOutputStream");
            class$java$io$ObjectOutputStream = class2;
        } else
        {
            class2 = class$java$io$ObjectOutputStream;
        }
        aclass[0] = class2;
        if(getMethod(class1, "writeObject", aclass, flag) != null)
            flag1 = true;
        else
            flag1 = false;
        return flag1;
    }

    private static final Object EMPTY_ARGS[] = new Object[0];
    private static final Object NO_METHOD = new Object();
    static Class class$java$io$ObjectInputStream;
    static Class class$java$io$ObjectOutputStream;
    private Map cache;

}
