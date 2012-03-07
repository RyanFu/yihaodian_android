// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.XmlHeaderAwareReader;
import com.thoughtworks.xstream.io.*;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, XmlFriendlyReplacer, XppReader, PrettyPrintWriter

public class XppDriver extends AbstractXmlDriver
{

    public XppDriver()
    {
        super(new XmlFriendlyReplacer());
    }

    public XppDriver(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
    }

    private void loadLibrary()
    {
        if(!xppLibraryPresent)
        {
            try
            {
                Class.forName("org.xmlpull.mxp1.MXParser", false, getClass().getClassLoader());
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new IllegalArgumentException("XPP3 pull parser library not present. Specify another driver. For example: new XStream(new DomDriver())");
            }
            xppLibraryPresent = true;
        }
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
        loadLibrary();
        return new XppReader(reader, xmlFriendlyReplacer());
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

    private static boolean xppLibraryPresent;
}
