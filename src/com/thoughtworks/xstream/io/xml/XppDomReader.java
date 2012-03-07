// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.xml.xppdom.Xpp3Dom;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentReader, XmlFriendlyReplacer

public class XppDomReader extends AbstractDocumentReader
{

    public XppDomReader(Xpp3Dom xpp3dom)
    {
        super(xpp3dom);
    }

    public XppDomReader(Xpp3Dom xpp3dom, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xpp3dom, xmlfriendlyreplacer);
    }

    @Override
	public String getAttribute(int i)
    {
        return currentElement.getAttribute(currentElement.getAttributeNames()[i]);
    }

    @Override
	public String getAttribute(String s)
    {
        return currentElement.getAttribute(s);
    }

    @Override
	public int getAttributeCount()
    {
        return currentElement.getAttributeNames().length;
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(currentElement.getAttributeNames()[i]);
    }

    @Override
	protected Object getChild(int i)
    {
        return currentElement.getChild(i);
    }

    @Override
	protected int getChildCount()
    {
        return currentElement.getChildCount();
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
        String s = null;
        String s2 = currentElement.getValue();
        s = s2;
_L2:
        String s1;
        if(s == null)
            s1 = "";
        else
            s1 = s;
        return s1;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	protected void reassignCurrentElement(Object obj)
    {
        currentElement = (Xpp3Dom)obj;
    }

    private Xpp3Dom currentElement;
}
