// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.json;

import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxReader;
import java.io.*;
import java.util.HashMap;

// Referenced classes of package com.thoughtworks.xstream.io.json:
//            JettisonStaxWriter

public class JettisonMappedXmlDriver
    implements HierarchicalStreamDriver
{

    public JettisonMappedXmlDriver()
    {
        Configuration configuration = new Configuration(new HashMap());
        mof = new MappedXMLOutputFactory(configuration);
        mif = new MappedXMLInputFactory(configuration);
        convention = new MappedNamespaceConvention(configuration);
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        StaxReader staxreader;
        try
        {
            staxreader = new StaxReader(new QNameMap(), mif.createXMLStreamReader(inputstream));
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return staxreader;
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        StaxReader staxreader;
        try
        {
            staxreader = new StaxReader(new QNameMap(), mif.createXMLStreamReader(reader));
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return staxreader;
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        JettisonStaxWriter jettisonstaxwriter;
        try
        {
            jettisonstaxwriter = new JettisonStaxWriter(new QNameMap(), mof.createXMLStreamWriter(outputstream), convention);
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return jettisonstaxwriter;
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        JettisonStaxWriter jettisonstaxwriter;
        try
        {
            jettisonstaxwriter = new JettisonStaxWriter(new QNameMap(), mof.createXMLStreamWriter(writer), convention);
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        return jettisonstaxwriter;
    }

    private final MappedNamespaceConvention convention;
    private final MappedXMLInputFactory mif;
    private final MappedXMLOutputFactory mof;
}
