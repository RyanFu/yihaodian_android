// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            AbstractAttributeAliasingMapper, Mapper

public class AttributeAliasingMapper extends AbstractAttributeAliasingMapper
{

    public AttributeAliasingMapper(Mapper mapper)
    {
        super(mapper);
    }

    @Override
	public String aliasForAttribute(String s)
    {
        String s1 = (String)nameToAlias.get(s);
        String s2;
        if(s1 == null)
            s2 = super.aliasForAttribute(s);
        else
            s2 = s1;
        return s2;
    }

    @Override
	public String attributeForAlias(String s)
    {
        String s1 = (String)aliasToName.get(s);
        String s2;
        if(s1 == null)
            s2 = super.attributeForAlias(s);
        else
            s2 = s1;
        return s2;
    }
}
