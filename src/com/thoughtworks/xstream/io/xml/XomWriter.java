// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentWriter, XmlFriendlyReplacer

public class XomWriter extends AbstractDocumentWriter
{

    public XomWriter()
    {
        this(null);
    }

    public XomWriter(Element element)
    {
        this(element, new XmlFriendlyReplacer());
    }

    public XomWriter(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
    }

    private Element top()
    {
        return (Element)getCurrent();
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        top().addAttribute(new Attribute(escapeXmlName(s), s1));
    }

    @Override
	protected Object createNode(String s)
    {
        Element element = new Element(escapeXmlName(s));
        if(top() != null)
            top().appendChild(element);
        return element;
    }

    @Override
	public void setValue(String s)
    {
        top().appendChild(s);
    }
}
