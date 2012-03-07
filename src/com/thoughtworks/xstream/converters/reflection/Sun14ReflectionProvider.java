// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import java.lang.ref.WeakReference;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            PureJavaReflectionProvider, ObjectAccessException, FieldDictionary

public class Sun14ReflectionProvider extends PureJavaReflectionProvider
{

    public Sun14ReflectionProvider()
    {
        reflectionFactory = ReflectionFactory.getReflectionFactory();
        constructorCache = Collections.synchronizedMap(new WeakHashMap());
    }

    public Sun14ReflectionProvider(FieldDictionary fielddictionary)
    {
        super(fielddictionary);
        reflectionFactory = ReflectionFactory.getReflectionFactory();
        constructorCache = Collections.synchronizedMap(new WeakHashMap());
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

    private Constructor getMungedConstructor(Class class1)
        throws NoSuchMethodException
    {
        WeakReference weakreference = (WeakReference)constructorCache.get(class1);
        Object obj;
        Constructor constructor;
        if(weakreference == null)
            obj = null;
        else
            obj = weakreference.get();
        constructor = (Constructor)obj;
        if(constructor == null)
        {
            ReflectionFactory reflectionfactory = reflectionFactory;
            Class class2;
            if(class$java$lang$Object == null)
            {
                class2 = _mthclass$("java.lang.Object");
                class$java$lang$Object = class2;
            } else
            {
                class2 = class$java$lang$Object;
            }
            constructor = reflectionfactory.newConstructorForSerialization(class1, class2.getDeclaredConstructor(new Class[0]));
            constructorCache.put(class1, new WeakReference(constructor));
        }
        return constructor;
    }

    private void write(Field field, Object obj, Object obj1)
    {
        long l;
        Class class1;
        if(exception != null)
            throw new ObjectAccessException("Could not set field " + obj.getClass() + "." + field.getName(), exception);
        try
        {
            l = unsafe.objectFieldOffset(field);
            class1 = field.getType();
            if(!class1.isPrimitive())
                break MISSING_BLOCK_LABEL_413;
            if(class1.equals(Integer.TYPE))
            {
                unsafe.putInt(obj, l, ((Integer)obj1).intValue());
                break MISSING_BLOCK_LABEL_423;
            }
            if(class1.equals(Long.TYPE))
            {
                unsafe.putLong(obj, l, ((Long)obj1).longValue());
                break MISSING_BLOCK_LABEL_423;
            }
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ObjectAccessException("Could not set field " + obj.getClass() + "." + field.getName(), illegalargumentexception);
        }
        if(class1.equals(Short.TYPE))
            unsafe.putShort(obj, l, ((Short)obj1).shortValue());
        else
        if(class1.equals(Character.TYPE))
            unsafe.putChar(obj, l, ((Character)obj1).charValue());
        else
        if(class1.equals(Byte.TYPE))
            unsafe.putByte(obj, l, ((Byte)obj1).byteValue());
        else
        if(class1.equals(Float.TYPE))
            unsafe.putFloat(obj, l, ((Float)obj1).floatValue());
        else
        if(class1.equals(Double.TYPE))
            unsafe.putDouble(obj, l, ((Double)obj1).doubleValue());
        else
        if(class1.equals(Boolean.TYPE))
            unsafe.putBoolean(obj, l, ((Boolean)obj1).booleanValue());
        else
            throw new ObjectAccessException("Could not set field " + obj.getClass() + "." + field.getName() + ": Unknown type " + class1);
        break MISSING_BLOCK_LABEL_423;
        unsafe.putObject(obj, l, obj1);
    }

    @Override
	public Object newInstance(Class class1)
    {
        Object obj;
        try
        {
            obj = getMungedConstructor(class1).newInstance(new Object[0]);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), nosuchmethodexception);
        }
        catch(SecurityException securityexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), securityexception);
        }
        catch(InstantiationException instantiationexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), instantiationexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), illegalaccessexception);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), illegalargumentexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new ObjectAccessException("Cannot construct " + class1.getName(), invocationtargetexception);
        }
        return obj;
    }

    @Override
	protected Object readResolve()
    {
        super.readResolve();
        constructorCache = Collections.synchronizedMap(new WeakHashMap());
        reflectionFactory = ReflectionFactory.getReflectionFactory();
        return this;
    }

    @Override
	protected void validateFieldAccess(Field field)
    {
    }

    @Override
	public void writeField(Object obj, String s, Object obj1, Class class1)
    {
        write(fieldDictionary.field(obj.getClass(), s, class1), obj, obj1);
    }

    static Class class$java$lang$Object;
    private static final Exception exception;
    private static final Unsafe unsafe;
    private transient Map constructorCache;
    private transient ReflectionFactory reflectionFactory;

    static 
    {
        Unsafe unsafe1 = null;
        Object obj = null;
        try
        {
            Field field = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe1 = (Unsafe)field.get(null);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            obj = classnotfoundexception;
        }
        catch(SecurityException securityexception)
        {
            obj = securityexception;
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            obj = nosuchfieldexception;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            obj = illegalargumentexception;
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            obj = illegalaccessexception;
        }
        exception = ((Exception) (obj));
        unsafe = unsafe1;
    }
}
