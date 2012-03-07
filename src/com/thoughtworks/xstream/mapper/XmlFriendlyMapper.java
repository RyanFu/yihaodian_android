// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            AbstractXmlFriendlyMapper, Mapper

public class XmlFriendlyMapper extends AbstractXmlFriendlyMapper
{

    public XmlFriendlyMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
    }

    public XmlFriendlyMapper(Mapper mapper)
    {
        super(mapper);
    }

    public String mapNameFromXML(String s)
    {
        return unescapeFieldName(s);
    }

    public String mapNameToXML(String s)
    {
        return escapeFieldName(s);
    }

    @Override
	public Class realClass(String s)
    {
        return super.realClass(unescapeClassName(s));
    }

    @Override
	public String realMember(Class class1, String s)
    {
        return unescapeFieldName(super.realMember(class1, s));
    }

    @Override
	public String serializedClass(Class class1)
    {
        return escapeClassName(super.serializedClass(class1));
    }

    @Override
	public String serializedMember(Class class1, String s)
    {
        return escapeFieldName(super.serializedMember(class1, s));
    }
}
