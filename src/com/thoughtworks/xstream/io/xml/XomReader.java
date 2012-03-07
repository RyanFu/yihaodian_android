// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentReader, XmlFriendlyReplacer

public class XomReader extends AbstractDocumentReader
{

    public XomReader(Document document)
    {
        super(document.getRootElement());
    }

    public XomReader(Document document, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(document.getRootElement(), xmlfriendlyreplacer);
    }

    public XomReader(Element element)
    {
        super(element);
    }

    public XomReader(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
    }

    @Override
	public String getAttribute(int i)
    {
        return currentElement.getAttribute(i).getValue();
    }

    @Override
	public String getAttribute(String s)
    {
        return currentElement.getAttributeValue(s);
    }

    @Override
	public int getAttributeCount()
    {
        return currentElement.getAttributeCount();
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(currentElement.getAttribute(i).getQualifiedName());
    }

    @Override
	protected Object getChild(int i)
    {
        return currentElement.getChildElements().get(i);
    }

    @Override
	protected int getChildCount()
    {
        return currentElement.getChildElements().size();
    }

    @Override
	public String getNodeName()
    {
        return unescapeXmlName(currentElement.getLocalName());
    }

    @Override
	protected Object getParent()
    {
        return currentElement.getParent();
    }

    @Override
	public String getValue()
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = currentElement.getChildCount();
        for(int j = 0; j < i; j++)
        {
            nu.xom.Node node = currentElement.getChild(j);
            if(node instanceof Text)
                stringbuffer.append(((Text)node).getValue());
        }

        return stringbuffer.toString();
    }

    @Override
	protected void reassignCurrentElement(Object obj)
    {
        currentElement = (Element)obj;
    }

    private Element currentElement;
}
