// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.xml.xppdom.Xpp3Dom;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentWriter, XmlFriendlyReplacer

public class XppDomWriter extends AbstractDocumentWriter
{

    public XppDomWriter()
    {
        this(null, new XmlFriendlyReplacer());
    }

    public XppDomWriter(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(null, xmlfriendlyreplacer);
    }

    public XppDomWriter(Xpp3Dom xpp3dom)
    {
        this(xpp3dom, new XmlFriendlyReplacer());
    }

    public XppDomWriter(Xpp3Dom xpp3dom, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xpp3dom, xmlfriendlyreplacer);
    }

    private Xpp3Dom top()
    {
        return (Xpp3Dom)getCurrent();
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        top().setAttribute(escapeXmlName(s), s1);
    }

    @Override
	protected Object createNode(String s)
    {
        Xpp3Dom xpp3dom = new Xpp3Dom(escapeXmlName(s));
        if(top() != null)
            top().addChild(xpp3dom);
        return xpp3dom;
    }

    public Xpp3Dom getConfiguration()
    {
        return (Xpp3Dom)getTopLevelNodes().get(0);
    }

    @Override
	public void setValue(String s)
    {
        top().setValue(s);
    }
}
