// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.*;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, XmlFriendlyReplacer, Dom4JReader, Dom4JXmlWriter

public class Dom4JDriver extends AbstractXmlDriver
{

    public Dom4JDriver()
    {
        this(new DocumentFactory(), OutputFormat.createPrettyPrint());
        outputFormat.setTrimText(false);
    }

    public Dom4JDriver(DocumentFactory documentfactory, OutputFormat outputformat)
    {
        this(documentfactory, outputformat, new XmlFriendlyReplacer());
    }

    public Dom4JDriver(DocumentFactory documentfactory, OutputFormat outputformat, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        documentFactory = documentfactory;
        outputFormat = outputformat;
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        Dom4JReader dom4jreader;
        try
        {
            dom4jreader = new Dom4JReader((new SAXReader()).read(inputstream), xmlFriendlyReplacer());
        }
        catch(DocumentException documentexception)
        {
            throw new StreamException(documentexception);
        }
        return dom4jreader;
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        Dom4JReader dom4jreader;
        try
        {
            dom4jreader = new Dom4JReader((new SAXReader()).read(reader), xmlFriendlyReplacer());
        }
        catch(DocumentException documentexception)
        {
            throw new StreamException(documentexception);
        }
        return dom4jreader;
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        return createWriter(((new OutputStreamWriter(outputstream))));
    }

    @Override
	public HierarchicalStreamWriter createWriter(final Writer final_writer1)
    {
        HierarchicalStreamWriter ahierarchicalstreamwriter[] = new HierarchicalStreamWriter[1];
        ahierarchicalstreamwriter[0] = new Dom4JXmlWriter(new XMLWriter(new _cls1(ahierarchicalstreamwriter), outputFormat), xmlFriendlyReplacer());
        return ahierarchicalstreamwriter[0];
    }

    public DocumentFactory getDocumentFactory()
    {
        return documentFactory;
    }

    public OutputFormat getOutputFormat()
    {
        return outputFormat;
    }

    public void setDocumentFactory(DocumentFactory documentfactory)
    {
        documentFactory = documentfactory;
    }

    public void setOutputFormat(OutputFormat outputformat)
    {
        outputFormat = outputformat;
    }

    private DocumentFactory documentFactory;
    private OutputFormat outputFormat;

    private class _cls1 extends FilterWriter
    {

        @Override
		public void close()
        {
            writer[0].close();
        }

        private final Dom4JDriver this$0;
        private final HierarchicalStreamWriter val$writer[];

        _cls1(HierarchicalStreamWriter ahierarchicalstreamwriter[])
        {
            super(final_writer1);
            this$0 = Dom4JDriver.this;
            writer = ahierarchicalstreamwriter;
        }
    }

}
