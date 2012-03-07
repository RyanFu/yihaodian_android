// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class OuterClassMapper extends MapperWrapper
{

    public OuterClassMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public OuterClassMapper(ClassMapper classmapper, String s)
    {
        this(((Mapper) (classmapper)), s);
    }

    public OuterClassMapper(Mapper mapper)
    {
        this(mapper, "outer-class");
    }

    public OuterClassMapper(Mapper mapper, String s)
    {
        super(mapper);
        alias = s;
    }

    @Override
	public String realMember(Class class1, String s)
    {
        String s1;
        if(s.equals(alias))
            s1 = "this$0";
        else
            s1 = super.realMember(class1, s);
        return s1;
    }

    @Override
	public String serializedMember(Class class1, String s)
    {
        String s1;
        if(s.equals("this$0"))
            s1 = alias;
        else
            s1 = super.serializedMember(class1, s);
        return s1;
    }

    private final String alias;
}
