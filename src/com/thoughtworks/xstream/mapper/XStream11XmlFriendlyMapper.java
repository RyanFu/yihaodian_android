// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;


// Referenced classes of package com.thoughtworks.xstream.mapper:
//            AbstractXmlFriendlyMapper, Mapper

public class XStream11XmlFriendlyMapper extends AbstractXmlFriendlyMapper
{

    public XStream11XmlFriendlyMapper(Mapper mapper)
    {
        super(mapper);
    }

    public String mapNameFromXML(String s)
    {
        return unescapeFieldName(s);
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
}
