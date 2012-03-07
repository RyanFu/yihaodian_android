// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, XmlFriendlyReplacer, DomReader, PrettyPrintWriter

public class DomDriver extends AbstractXmlDriver
{

    public DomDriver()
    {
        this(null);
    }

    public DomDriver(String s)
    {
        this(s, new XmlFriendlyReplacer());
    }

    public DomDriver(String s, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        encoding = s;
    }

    private HierarchicalStreamReader createReader(InputSource inputsource)
    {
        DomReader domreader;
        try
        {
            DocumentBuilder documentbuilder = documentBuilderFactory.newDocumentBuilder();
            if(encoding != null)
                inputsource.setEncoding(encoding);
            domreader = new DomReader(documentbuilder.parse(inputsource), xmlFriendlyReplacer());
        }
        catch(FactoryConfigurationError factoryconfigurationerror)
        {
            throw new StreamException(factoryconfigurationerror);
        }
        catch(ParserConfigurationException parserconfigurationexception)
        {
            throw new StreamException(parserconfigurationexception);
        }
        catch(SAXException saxexception)
        {
            throw new StreamException(saxexception);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        return domreader;
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        return createReader(new InputSource(inputstream));
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        return createReader(new InputSource(reader));
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        try
        {
            OutputStreamWriter outputstreamwriter;
            if(encoding != null)
                outputstreamwriter = new OutputStreamWriter(outputstream, encoding);
            else
                outputstreamwriter = new OutputStreamWriter(outputstream);
            return createWriter(((outputstreamwriter)));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new StreamException(unsupportedencodingexception);
        }
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        return new PrettyPrintWriter(writer, xmlFriendlyReplacer());
    }

    private final DocumentBuilderFactory documentBuilderFactory;
    private final String encoding;
}
