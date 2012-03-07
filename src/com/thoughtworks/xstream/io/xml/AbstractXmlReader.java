// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            XmlFriendlyReader, XmlFriendlyReplacer

public abstract class AbstractXmlReader
    implements HierarchicalStreamReader, XmlFriendlyReader
{

    protected AbstractXmlReader()
    {
        this(new XmlFriendlyReplacer());
    }

    protected AbstractXmlReader(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        replacer = xmlfriendlyreplacer;
    }

    protected String escapeXmlName(String s)
    {
        return replacer.escapeName(s);
    }

    @Override
	public String unescapeXmlName(String s)
    {
        return replacer.unescapeName(s);
    }

    private XmlFriendlyReplacer replacer;
}
