// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.XmlHeaderAwareReader;
import com.thoughtworks.xstream.io.*;
import com.thoughtworks.xstream.io.xml.xppdom.Xpp3DomBuilder;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, XmlFriendlyReplacer, XppDomReader, PrettyPrintWriter

public class XppDomDriver extends AbstractXmlDriver
{

    public XppDomDriver()
    {
        super(new XmlFriendlyReplacer());
    }

    public XppDomDriver(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        HierarchicalStreamReader hierarchicalstreamreader;
        try
        {
            hierarchicalstreamreader = createReader(((new XmlHeaderAwareReader(inputstream))));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new StreamException(unsupportedencodingexception);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        return hierarchicalstreamreader;
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        XppDomReader xppdomreader;
        try
        {
            xppdomreader = new XppDomReader(Xpp3DomBuilder.build(reader), xmlFriendlyReplacer());
        }
        catch(Exception exception)
        {
            throw new StreamException(exception);
        }
        return xppdomreader;
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        return createWriter(((new OutputStreamWriter(outputstream))));
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        return new PrettyPrintWriter(writer, xmlFriendlyReplacer());
    }
}
