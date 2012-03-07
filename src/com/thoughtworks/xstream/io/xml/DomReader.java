// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentReader, XmlFriendlyReplacer

public class DomReader extends AbstractDocumentReader
{

    public DomReader(Document document)
    {
        this(document.getDocumentElement());
    }

    public DomReader(Document document, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(document.getDocumentElement(), xmlfriendlyreplacer);
    }

    public DomReader(Element element)
    {
        this(element, new XmlFriendlyReplacer());
    }

    public DomReader(Element element, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(element, xmlfriendlyreplacer);
        textBuffer = new StringBuffer();
    }

    @Override
	public String getAttribute(int i)
    {
        return ((Attr)currentElement.getAttributes().item(i)).getValue();
    }

    @Override
	public String getAttribute(String s)
    {
        Attr attr = currentElement.getAttributeNode(s);
        String s1;
        if(attr == null)
            s1 = null;
        else
            s1 = attr.getValue();
        return s1;
    }

    @Override
	public int getAttributeCount()
    {
        return currentElement.getAttributes().getLength();
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(((Attr)currentElement.getAttributes().item(i)).getName());
    }

    @Override
	protected Object getChild(int i)
    {
        return childElements.get(i);
    }

    @Override
	protected int getChildCount()
    {
        return childElements.size();
    }

    @Override
	public String getNodeName()
    {
        return unescapeXmlName(currentElement.getTagName());
    }

    @Override
	protected Object getParent()
    {
        return currentElement.getParentNode();
    }

    @Override
	public String getValue()
    {
        NodeList nodelist = currentElement.getChildNodes();
        textBuffer.setLength(0);
        int i = nodelist.getLength();
        for(int j = 0; j < i; j++)
        {
            org.w3c.dom.Node node = nodelist.item(j);
            if(node instanceof Text)
            {
                Text text = (Text)node;
                textBuffer.append(text.getData());
            }
        }

        return textBuffer.toString();
    }

    @Override
	protected void reassignCurrentElement(Object obj)
    {
        currentElement = (Element)obj;
        NodeList nodelist = currentElement.getChildNodes();
        childElements = new ArrayList();
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            org.w3c.dom.Node node = nodelist.item(i);
            if(node instanceof Element)
                childElements.add(node);
        }

    }

    private List childElements;
    private Element currentElement;
    private StringBuffer textBuffer;
}
