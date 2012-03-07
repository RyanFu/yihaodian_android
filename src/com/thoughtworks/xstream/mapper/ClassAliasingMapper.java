// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class ClassAliasingMapper extends MapperWrapper
{

    public ClassAliasingMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public ClassAliasingMapper(Mapper mapper)
    {
        super(mapper);
        typeToName = new HashMap();
        classToName = new HashMap();
        nameToType = new HashMap();
    }

    private Class primitiveClassNamed(String s)
    {
        Class class1;
        if(s.equals("void"))
            class1 = Void.TYPE;
        else
        if(s.equals("boolean"))
            class1 = Boolean.TYPE;
        else
        if(s.equals("byte"))
            class1 = Byte.TYPE;
        else
        if(s.equals("char"))
            class1 = Character.TYPE;
        else
        if(s.equals("short"))
            class1 = Short.TYPE;
        else
        if(s.equals("int"))
            class1 = Integer.TYPE;
        else
        if(s.equals("long"))
            class1 = Long.TYPE;
        else
        if(s.equals("float"))
            class1 = Float.TYPE;
        else
        if(s.equals("double"))
            class1 = Double.TYPE;
        else
            class1 = null;
        return class1;
    }

    private Object readResolve()
    {
        nameToType = new HashMap();
        Object obj;
        for(Iterator iterator = classToName.keySet().iterator(); iterator.hasNext(); nameToType.put(classToName.get(obj), obj))
            obj = iterator.next();

        Class class1;
        for(Iterator iterator1 = typeToName.keySet().iterator(); iterator1.hasNext(); nameToType.put(typeToName.get(class1), class1.getName()))
            class1 = (Class)iterator1.next();

        return this;
    }

    public void addClassAlias(String s, Class class1)
    {
        nameToType.put(s, class1.getName());
        classToName.put(class1.getName(), s);
    }

    public void addClassAttributeAlias(String s, Class class1)
    {
        addClassAlias(s, class1);
    }

    public void addTypeAlias(String s, Class class1)
    {
        nameToType.put(s, class1.getName());
        typeToName.put(class1, s);
    }

    public boolean aliasIsAttribute(String s)
    {
        return nameToType.containsKey(s);
    }

    public boolean itemTypeAsAttribute(Class class1)
    {
        return classToName.containsKey(class1);
    }

    @Override
	public Class realClass(String s)
    {
        String s1 = (String)nameToType.get(s);
        if(s1 == null) goto _L2; else goto _L1
_L1:
        Class class2 = primitiveClassNamed(s1);
        if(class2 == null) goto _L4; else goto _L3
_L3:
        Class class1 = class2;
_L6:
        return class1;
_L4:
        s = s1;
_L2:
        class1 = super.realClass(s);
        if(true) goto _L6; else goto _L5
_L5:
    }

    @Override
	public String serializedClass(Class class1)
    {
        String s = (String)classToName.get(class1.getName());
        if(s == null) goto _L2; else goto _L1
_L1:
        String s1 = s;
_L4:
        return s1;
_L2:
        for(Iterator iterator = typeToName.keySet().iterator(); iterator.hasNext();)
        {
            Class class2 = (Class)iterator.next();
            if(class2.isAssignableFrom(class1))
            {
                s1 = (String)typeToName.get(class2);
                continue; /* Loop/switch isn't completed */
            }
        }

        s1 = super.serializedClass(class1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private final Map classToName;
    private transient Map nameToType;
    private final Map typeToName;
}
