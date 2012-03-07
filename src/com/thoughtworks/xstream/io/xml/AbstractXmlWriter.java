// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            XmlFriendlyWriter, XmlFriendlyReplacer

public abstract class AbstractXmlWriter
    implements ExtendedHierarchicalStreamWriter, XmlFriendlyWriter
{

    protected AbstractXmlWriter()
    {
        this(new XmlFriendlyReplacer());
    }

    protected AbstractXmlWriter(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        replacer = xmlfriendlyreplacer;
    }

    @Override
	public String escapeXmlName(String s)
    {
        return replacer.escapeName(s);
    }

    @Override
	public void startNode(String s, Class class1)
    {
        startNode(s);
    }

    @Override
	public HierarchicalStreamWriter underlyingWriter()
    {
        return this;
    }

    private XmlFriendlyReplacer replacer;
}
