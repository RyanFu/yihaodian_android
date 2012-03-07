// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import java.util.List;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentWriter, XmlFriendlyReplacer

public class JDomWriter extends AbstractDocumentWriter
{

    public JDomWriter()
    {
        this(((JDOMFactory) (new DefaultJDOMFactory())));
    }

    public JDomWriter(Element element)
    {
        this(element, ((JDOMFactory) (new DefaultJDOMFactory())));
    }

    public JDomWriter(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(element, ((JDOMFactory) (new DefaultJDOMFactory())), xmlfriendlyreplacer);
    }

    public JDomWriter(Element element, JDOMFactory jdomfactory)
    {
        this(element, jdomfactory, new XmlFriendlyReplacer());
    }

    public JDomWriter(Element element, JDOMFactory jdomfactory, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
        documentFactory = jdomfactory;
    }

    public JDomWriter(JDOMFactory jdomfactory)
    {
        this(((Element) (null)), jdomfactory);
    }

    public JDomWriter(JDOMFactory jdomfactory, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(null, jdomfactory, xmlfriendlyreplacer);
    }

    private Element top()
    {
        return (Element)getCurrent();
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        top().setAttribute(documentFactory.attribute(escapeXmlName(s), s1));
    }

    @Override
	protected Object createNode(String s)
    {
        Element element = documentFactory.element(escapeXmlName(s));
        Element element1 = top();
        if(element1 != null)
            element1.addContent(element);
        return element;
    }

    public List getResult()
    {
        return getTopLevelNodes();
    }

    @Override
	public void setValue(String s)
    {
        top().addContent(documentFactory.text(s));
    }

    private final JDOMFactory documentFactory;
}
