// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class ImmutableTypesMapper extends MapperWrapper
{

    public ImmutableTypesMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public ImmutableTypesMapper(Mapper mapper)
    {
        super(mapper);
        immutableTypes = new HashSet();
    }

    public void addImmutableType(Class class1)
    {
        immutableTypes.add(class1);
    }

    @Override
	public boolean isImmutableValueType(Class class1)
    {
        boolean flag;
        if(immutableTypes.contains(class1))
            flag = true;
        else
            flag = super.isImmutableValueType(class1);
        return flag;
    }

    private final Set immutableTypes;
}
