// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractDocumentWriter, XmlFriendlyReplacer

public class Dom4JWriter extends AbstractDocumentWriter
{

    public Dom4JWriter()
    {
        this(new DocumentFactory(), new XmlFriendlyReplacer());
    }

    public Dom4JWriter(Branch branch)
    {
        this(branch, new DocumentFactory(), new XmlFriendlyReplacer());
    }

    public Dom4JWriter(Branch branch, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(branch, new DocumentFactory(), xmlfriendlyreplacer);
    }

    public Dom4JWriter(Branch branch, DocumentFactory documentfactory, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(branch, xmlfriendlyreplacer);
        documentFactory = documentfactory;
    }

    public Dom4JWriter(DocumentFactory documentfactory)
    {
        this(documentfactory, new XmlFriendlyReplacer());
    }

    public Dom4JWriter(DocumentFactory documentfactory, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(null, documentfactory, xmlfriendlyreplacer);
    }

    private Branch top()
    {
        return (Branch)getCurrent();
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        ((Element)top()).addAttribute(escapeXmlName(s), s1);
    }

    @Override
	protected Object createNode(String s)
    {
        Element element = documentFactory.createElement(escapeXmlName(s));
        if(top() != null)
            top().add(element);
        return element;
    }

    @Override
	public void setValue(String s)
    {
        top().setText(s);
    }

    private final DocumentFactory documentFactory;
}
