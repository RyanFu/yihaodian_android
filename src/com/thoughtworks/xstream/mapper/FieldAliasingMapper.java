// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.core.util.FastField;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class FieldAliasingMapper extends MapperWrapper
{

    public FieldAliasingMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public FieldAliasingMapper(Mapper mapper)
    {
        super(mapper);
        fieldToAliasMap = new HashMap();
        aliasToFieldMap = new HashMap();
        fieldsToOmit = new HashSet();
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

    private String getMember(Class class1, String s, Map map)
    {
        String s1 = null;
        Class class2 = class1;
        do
        {
            if(s1 != null)
                break;
            Class class3;
            if(class$java$lang$Object == null)
            {
                class3 = _mthclass$("java.lang.Object");
                class$java$lang$Object = class3;
            } else
            {
                class3 = class$java$lang$Object;
            }
            if(class2 == class3)
                break;
            s1 = (String)map.get(key(class2, s));
            class2 = class2.getSuperclass();
        } while(true);
        return s1;
    }

    private Object key(Class class1, String s)
    {
        return new FastField(class1, s);
    }

    public void addFieldAlias(String s, Class class1, String s1)
    {
        fieldToAliasMap.put(key(class1, s1), s);
        aliasToFieldMap.put(key(class1, s), s1);
    }

    public void omitField(Class class1, String s)
    {
        fieldsToOmit.add(key(class1, s));
    }

    @Override
	public String realMember(Class class1, String s)
    {
        String s1 = getMember(class1, s, aliasToFieldMap);
        String s2;
        if(s1 == null)
            s2 = super.realMember(class1, s);
        else
            s2 = s1;
        return s2;
    }

    @Override
	public String serializedMember(Class class1, String s)
    {
        String s1 = getMember(class1, s, fieldToAliasMap);
        String s2;
        if(s1 == null)
            s2 = super.serializedMember(class1, s);
        else
            s2 = s1;
        return s2;
    }

    @Override
	public boolean shouldSerializeMember(Class class1, String s)
    {
        boolean flag;
        if(!fieldsToOmit.contains(key(class1, s)))
            flag = true;
        else
            flag = false;
        return flag;
    }

    static Class class$java$lang$Object;
    protected final Map aliasToFieldMap;
    protected final Map fieldToAliasMap;
    protected final Set fieldsToOmit;
}
