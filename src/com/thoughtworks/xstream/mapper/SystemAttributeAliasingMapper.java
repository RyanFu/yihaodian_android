// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            AbstractAttributeAliasingMapper, Mapper

public class SystemAttributeAliasingMapper extends AbstractAttributeAliasingMapper
{

    public SystemAttributeAliasingMapper(Mapper mapper)
    {
        super(mapper);
    }

    @Override
	public String aliasForSystemAttribute(String s)
    {
        String s1 = (String)nameToAlias.get(s);
        if(s1 == null && !nameToAlias.containsKey(s))
        {
            s1 = super.aliasForSystemAttribute(s);
            if(s1 == s)
                s1 = super.aliasForAttribute(s);
        }
        return s1;
    }
}
