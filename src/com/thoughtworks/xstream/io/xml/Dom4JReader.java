// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.converters.ErrorWriter;
import java.util.List;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentReader, XmlFriendlyReplacer

public class Dom4JReader extends AbstractDocumentReader
{

    public Dom4JReader(Document document)
    {
        this(document.getRootElement());
    }

    public Dom4JReader(Document document, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(document.getRootElement(), xmlfriendlyreplacer);
    }

    public Dom4JReader(Element element)
    {
        this(element, new XmlFriendlyReplacer());
    }

    public Dom4JReader(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
        errorwriter.add("xpath", currentElement.getPath());
    }

    @Override
	public String getAttribute(int i)
    {
        return currentElement.attribute(i).getValue();
    }

    @Override
	public String getAttribute(String s)
    {
        return currentElement.attributeValue(s);
    }

    @Override
	public int getAttributeCount()
    {
        return currentElement.attributeCount();
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(currentElement.attribute(i).getQualifiedName());
    }

    @Override
	protected Object getChild(int i)
    {
        return currentElement.elements().get(i);
    }

    @Override
	protected int getChildCount()
    {
        return currentElement.elements().size();
    }

    @Override
	public String getNodeName()
    {
        return unescapeXmlName(currentElement.getName());
    }

    @Override
	protected Object getParent()
    {
        return currentElement.getParent();
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
