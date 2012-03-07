// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.*;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, QNameMap, XmlFriendlyReplacer, StaxReader, 
//            StaxWriter, AbstractPullReader

public class StaxDriver extends AbstractXmlDriver
{

    public StaxDriver()
    {
        qnameMap = new QNameMap();
    }

    public StaxDriver(QNameMap qnamemap)
    {
        this(qnamemap, false);
    }

    public StaxDriver(QNameMap qnamemap, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        qnameMap = qnamemap;
    }

    public StaxDriver(QNameMap qnamemap, boolean flag)
    {
        this(qnamemap, new XmlFriendlyReplacer());
        setRepairingNamespace(flag);
    }

    public StaxDriver(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(new QNameMap(), xmlfriendlyreplacer);
    }

    private void loadLibrary()
    {
        if(!libraryPresent)
        {
            try
            {
                Class.forName("javax.xml.stream.XMLStreamReader");
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new IllegalArgumentException("StAX API is not present. Specify another driver. For example: new XStream(new DomDriver())");
            }
            libraryPresent = true;
        }
    }

    protected XMLStreamReader createParser(InputStream inputstream)
        throws XMLStreamException
    {
        return getInputFactory().createXMLStreamReader(inputstream);
    }

    protected XMLStreamReader createParser(Reader reader)
        throws XMLStreamException
    {
        return getInputFactory().createXMLStreamReader(reader);
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        loadLibrary();
        AbstractPullReader abstractpullreader;
        try
        {
            abstractpullreader = createStaxReader(createParser(inputstream));
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return abstractpullreader;
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        loadLibrary();
        AbstractPullReader abstractpullreader;
        try
        {
            abstractpullreader = createStaxReader(createParser(reader));
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return abstractpullreader;
    }

    public AbstractPullReader createStaxReader(XMLStreamReader xmlstreamreader)
    {
        return new StaxReader(qnameMap, xmlstreamreader, xmlFriendlyReplacer());
    }

    public StaxWriter createStaxWriter(XMLStreamWriter xmlstreamwriter)
        throws XMLStreamException
    {
        return createStaxWriter(xmlstreamwriter, true);
    }

    public StaxWriter createStaxWriter(XMLStreamWriter xmlstreamwriter, boolean flag)
        throws XMLStreamException
    {
        return new StaxWriter(qnameMap, xmlstreamwriter, flag, isRepairingNamespace(), xmlFriendlyReplacer());
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        StaxWriter staxwriter;
        try
        {
            staxwriter = createStaxWriter(getOutputFactory().createXMLStreamWriter(outputstream));
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return staxwriter;
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        StaxWriter staxwriter;
        try
        {
            staxwriter = createStaxWriter(getOutputFactory().createXMLStreamWriter(writer));
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return staxwriter;
    }

    public XMLInputFactory getInputFactory()
    {
        if(inputFactory == null)
            inputFactory = XMLInputFactory.newInstance();
        return inputFactory;
    }

    public XMLOutputFactory getOutputFactory()
    {
        if(outputFactory == null)
            outputFactory = XMLOutputFactory.newInstance();
        return outputFactory;
    }

    public QNameMap getQnameMap()
    {
        return qnameMap;
    }

    public boolean isRepairingNamespace()
    {
        return Boolean.TRUE.equals(getOutputFactory().getProperty("javax.xml.stream.isRepairingNamespaces"));
    }

    public void setQnameMap(QNameMap qnamemap)
    {
        qnameMap = qnamemap;
    }

    public void setRepairingNamespace(boolean flag)
    {
        XMLOutputFactory xmloutputfactory = getOutputFactory();
        Boolean boolean1;
        if(flag)
            boolean1 = Boolean.TRUE;
        else
            boolean1 = Boolean.FALSE;
        xmloutputfactory.setProperty("javax.xml.stream.isRepairingNamespaces", boolean1);
    }

    private static boolean libraryPresent;
    private XMLInputFactory inputFactory;
    private XMLOutputFactory outputFactory;
    private QNameMap qnameMap;
}
