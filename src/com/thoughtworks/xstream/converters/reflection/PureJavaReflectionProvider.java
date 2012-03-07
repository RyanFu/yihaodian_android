// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import com.thoughtworks.xstream.core.JVM;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            ReflectionProvider, FieldDictionary, ImmutableFieldKeySorter, ObjectAccessException

public class PureJavaReflectionProvider
    implements ReflectionProvider
{

    public PureJavaReflectionProvider()
    {
        this(new FieldDictionary(new ImmutableFieldKeySorter()));
    }

    public PureJavaReflectionProvider(FieldDictionary fielddictionary)
    {
        serializedDataCache = Collections.synchronizedMap(new HashMap());
        fieldDictionary = fielddictionary;
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

    private Object instantiateUsingSerialization(Class class1)
    {
        try
        {
            byte abyte0[];
            if(serializedDataCache.containsKey(class1))
            {
                abyte0 = (byte[])serializedDataCache.get(class1);
            } else
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
                dataoutputstream.writeShort(-21267);
                dataoutputstream.writeShort(5);
                dataoutputstream.writeByte(115);
                dataoutputstream.writeByte(114);
                dataoutputstream.writeUTF(class1.getName());
                dataoutputstream.writeLong(ObjectStreamClass.lookup(class1).getSerialVersionUID());
                dataoutputstream.writeByte(2);
                dataoutputstream.writeShort(0);
                dataoutputstream.writeByte(120);
                dataoutputstream.writeByte(112);
                abyte0 = bytearrayoutputstream.toByteArray();
                serializedDataCache.put(class1, abyte0);
            }
            return (new ObjectInputStream(new ByteArrayInputStream(abyte0))).readObject();
        }
        catch(IOException ioexception)
        {
            throw new ObjectAccessException("Cannot create " + class1.getName() + " by JDK serialization", ioexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new ObjectAccessException("Cannot find class " + classnotfoundexception.getMessage());
        }
    }

    @Override
	public boolean fieldDefinedInClass(String s, Class class1)
    {
        Field field = fieldDictionary.field(class1, s, null);
        if(fieldModifiersSupported(field)) goto _L2; else goto _L1
_L1:
        boolean flag1 = Modifier.isTransient(field.getModifiers());
        if(!flag1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        continue; /* Loop/switch isn't completed */
        ObjectAccessException objectaccessexception;
        objectaccessexception;
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected boolean fieldModifiersSupported(Field field)
    {
        boolean flag;
        if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public Field getField(Class class1, String s)
    {
        return fieldDictionary.field(class1, s, null);
    }

    @Override
	public Class getFieldType(Object obj, String s, Class class1)
    {
        return fieldDictionary.field(obj.getClass(), s, class1).getType();
    }

    @Override
	public Object newInstance(Class class1)
    {
        Constructor aconstructor[];
        int i;
        aconstructor = class1.getDeclaredConstructors();
        i = 0;
_L2:
        Object obj;
        if(i < aconstructor.length)
        {
            if(aconstructor[i].getParameterTypes().length != 0)
                break MISSING_BLOCK_LABEL_294;
            if(!Modifier.isPublic(aconstructor[i].getModifiers()))
                aconstructor[i].setAccessible(true);
            obj = aconstructor[i].newInstance(new Object[0]);
        } else
        {
            Class class2;
            if(class$java$io$Serializable == null)
            {
                class2 = _mthclass$("java.io.Serializable");
                class$java$io$Serializable = class2;
            } else
            {
                class2 = class$java$io$Serializable;
            }
            if(class2.isAssignableFrom(class1))
                obj = instantiateUsingSerialization(class1);
            else
                throw new ObjectAccessException("Cannot construct " + class1.getName() + " as it does not have a no-args constructor");
        }
          goto _L1
        InstantiationException instantiationexception;
        instantiationexception;
        throw new ObjectAccessException("Cannot construct " + class1.getName(), instantiationexception);
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        throw new ObjectAccessException("Cannot construct " + class1.getName(), illegalaccessexception);
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        if(invocationtargetexception.getTargetException() instanceof RuntimeException)
            throw (RuntimeException)invocationtargetexception.getTargetException();
        if(invocationtargetexception.getTargetException() instanceof Error)
            throw (Error)invocationtargetexception.getTargetException();
        else
            throw new ObjectAccessException("Constructor for " + class1.getName() + " threw an exception", invocationtargetexception.getTargetException());
_L1:
        return obj;
        i++;
          goto _L2
    }

    protected Object readResolve()
    {
        serializedDataCache = Collections.synchronizedMap(new HashMap());
        return this;
    }

    public void setFieldDictionary(FieldDictionary fielddictionary)
    {
        fieldDictionary = fielddictionary;
    }

    protected void validateFieldAccess(Field field)
    {
label0:
        {
            if(Modifier.isFinal(field.getModifiers()))
            {
                if(!JVM.is15())
                    break label0;
                field.setAccessible(true);
            }
            return;
        }
        throw new ObjectAccessException("Invalid final field " + field.getDeclaringClass().getName() + "." + field.getName());
    }

    @Override
	public void visitSerializableFields(Object obj, ReflectionProvider.Visitor visitor)
    {
        Iterator iterator = fieldDictionary.fieldsFor(obj.getClass());
        do
        {
            if(!iterator.hasNext())
                break;
            Field field = (Field)iterator.next();
            if(fieldModifiersSupported(field))
            {
                validateFieldAccess(field);
                try
                {
                    Object obj1 = field.get(obj);
                    visitor.visit(field.getName(), field.getType(), field.getDeclaringClass(), obj1);
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), illegalargumentexception);
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                    throw new ObjectAccessException("Could not get field " + field.getClass() + "." + field.getName(), illegalaccessexception);
                }
            }
        } while(true);
    }

    @Override
	public void writeField(Object obj, String s, Object obj1, Class class1)
    {
        Field field = fieldDictionary.field(obj.getClass(), s, class1);
        validateFieldAccess(field);
        try
        {
            field.set(obj, obj1);
            return;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new ObjectAccessException("Could not set field " + obj.getClass() + "." + field.getName(), illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new ObjectAccessException("Could not set field " + obj.getClass() + "." + field.getName(), illegalaccessexception);
        }
    }

    static Class class$java$io$Serializable;
    protected FieldDictionary fieldDictionary;
    private transient Map serializedDataCache;
}
