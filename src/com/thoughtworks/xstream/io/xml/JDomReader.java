// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import java.util.List;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentReader, XmlFriendlyReplacer

public class JDomReader extends AbstractDocumentReader
{

    public JDomReader(Document document)
    {
        super(document.getRootElement());
    }

    public JDomReader(Document document, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(document.getRootElement(), xmlfriendlyreplacer);
    }

    public JDomReader(Element element)
    {
        super(element);
    }

    public JDomReader(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
    }

    @Override
	public String getAttribute(int i)
    {
        return ((Attribute)currentElement.getAttributes().get(i)).getValue();
    }

    @Override
	public String getAttribute(String s)
    {
        return currentElement.getAttributeValue(s);
    }

    @Override
	public int getAttributeCount()
    {
        return currentElement.getAttributes().size();
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(((Attribute)currentElement.getAttributes().get(i)).getQualifiedName());
    }

    @Override
	protected Object getChild(int i)
    {
        return currentElement.getChildren().get(i);
    }

    @Override
	protected int getChildCount()
    {
        return currentElement.getChildren().size();
    }

    @Override
	public String getNodeName()
    {
        return unescapeXmlName(currentElement.getName());
    }

    @Override
	protected Object getParent()
    {
        return currentElement.getParentElement();
    }

    @Override
	public String getValue()
    {
        return currentElement.getText();
    }

    @Override
	protected void reassignCurrentElement(Object obj)
    {
        currentElement = (Element)obj;
    }

    private Element currentElement;
}
