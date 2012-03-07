// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.InitializationException;
import com.thoughtworks.xstream.alias.ClassMapper;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class DefaultImplementationsMapper extends MapperWrapper
{

    public DefaultImplementationsMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public DefaultImplementationsMapper(Mapper mapper)
    {
        super(mapper);
        typeToImpl = new HashMap();
        implToType = new HashMap();
        addDefaults();
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

    private Object readResolve()
    {
        implToType = new HashMap();
        Object obj;
        for(Iterator iterator = typeToImpl.keySet().iterator(); iterator.hasNext(); implToType.put(typeToImpl.get(obj), obj))
            obj = iterator.next();

        return this;
    }

    public void addDefaultImplementation(Class class1, Class class2)
    {
        if(class1 != null && class1.isInterface())
        {
            throw new InitializationException("Default implementation is not a concrete class: " + class1.getName());
        } else
        {
            typeToImpl.put(class2, class1);
            implToType.put(class1, class2);
            return;
        }
    }

    protected void addDefaults()
    {
        Class class1;
        Class class2;
        Class class3;
        Class class4;
        Class class5;
        Class class6;
        Class class7;
        Class class8;
        Class class9;
        if(class$com$thoughtworks$xstream$mapper$Mapper$Null == null)
        {
            class1 = _mthclass$("com.thoughtworks.xstream.mapper.Mapper$Null");
            class$com$thoughtworks$xstream$mapper$Mapper$Null = class1;
        } else
        {
            class1 = class$com$thoughtworks$xstream$mapper$Mapper$Null;
        }
        addDefaultImplementation(null, class1);
        if(class$java$lang$Boolean == null)
        {
            class2 = _mthclass$("java.lang.Boolean");
            class$java$lang$Boolean = class2;
        } else
        {
            class2 = class$java$lang$Boolean;
        }
        addDefaultImplementation(class2, Boolean.TYPE);
        if(class$java$lang$Character == null)
        {
            class3 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class3;
        } else
        {
            class3 = class$java$lang$Character;
        }
        addDefaultImplementation(class3, Character.TYPE);
        if(class$java$lang$Integer == null)
        {
            class4 = _mthclass$("java.lang.Integer");
            class$java$lang$Integer = class4;
        } else
        {
            class4 = class$java$lang$Integer;
        }
        addDefaultImplementation(class4, Integer.TYPE);
        if(class$java$lang$Float == null)
        {
            class5 = _mthclass$("java.lang.Float");
            class$java$lang$Float = class5;
        } else
        {
            class5 = class$java$lang$Float;
        }
        addDefaultImplementation(class5, Float.TYPE);
        if(class$java$lang$Double == null)
        {
            class6 = _mthclass$("java.lang.Double");
            class$java$lang$Double = class6;
        } else
        {
            class6 = class$java$lang$Double;
        }
        addDefaultImplementation(class6, Double.TYPE);
        if(class$java$lang$Short == null)
        {
            class7 = _mthclass$("java.lang.Short");
            class$java$lang$Short = class7;
        } else
        {
            class7 = class$java$lang$Short;
        }
        addDefaultImplementation(class7, Short.TYPE);
        if(class$java$lang$Byte == null)
        {
            class8 = _mthclass$("java.lang.Byte");
            class$java$lang$Byte = class8;
        } else
        {
            class8 = class$java$lang$Byte;
        }
        addDefaultImplementation(class8, Byte.TYPE);
        if(class$java$lang$Long == null)
        {
            class9 = _mthclass$("java.lang.Long");
            class$java$lang$Long = class9;
        } else
        {
            class9 = class$java$lang$Long;
        }
        addDefaultImplementation(class9, Long.TYPE);
    }

    @Override
	public Class defaultImplementationOf(Class class1)
    {
        Class class2;
        if(typeToImpl.containsKey(class1))
            class2 = (Class)typeToImpl.get(class1);
        else
            class2 = super.defaultImplementationOf(class1);
        return class2;
    }

    @Override
	public String serializedClass(Class class1)
    {
        Class class2 = (Class)implToType.get(class1);
        String s;
        if(class2 == null)
            s = super.serializedClass(class1);
        else
            s = super.serializedClass(class2);
        return s;
    }

    static Class class$com$thoughtworks$xstream$mapper$Mapper$Null;
    static Class class$java$lang$Boolean;
    static Class class$java$lang$Byte;
    static Class class$java$lang$Character;
    static Class class$java$lang$Double;
    static Class class$java$lang$Float;
    static Class class$java$lang$Integer;
    static Class class$java$lang$Long;
    static Class class$java$lang$Short;
    private transient Map implToType;
    private final Map typeToImpl;
}
