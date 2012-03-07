// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentWriter, XmlFriendlyReplacer

public class DomWriter extends AbstractDocumentWriter
{

    public DomWriter(Document document1)
    {
        this(document1, new XmlFriendlyReplacer());
    }

    public DomWriter(Document document1, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(document1.getDocumentElement(), document1, xmlfriendlyreplacer);
    }

    public DomWriter(Element element)
    {
        this(element, new XmlFriendlyReplacer());
    }

    public DomWriter(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(element.getOwnerDocument(), xmlfriendlyreplacer);
    }

    public DomWriter(Element element, Document document1, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
        document = document1;
        boolean flag;
        if(document1.getDocumentElement() != null)
            flag = true;
        else
            flag = false;
        hasRootElement = flag;
    }

    private Element top()
    {
        return (Element)getCurrent();
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        top().setAttribute(escapeXmlName(s), s1);
    }

    @Override
	protected Object createNode(String s)
    {
        Element element = document.createElement(escapeXmlName(s));
        if(top() == null) goto _L2; else goto _L1
_L1:
        top().appendChild(element);
_L4:
        return element;
_L2:
        if(!hasRootElement)
        {
            document.appendChild(element);
            hasRootElement = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public void setValue(String s)
    {
        top().appendChild(document.createTextNode(s));
    }

    private final Document document;
    private boolean hasRootElement;
}
