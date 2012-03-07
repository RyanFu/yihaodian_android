// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.*;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, XmlFriendlyReplacer, JDomReader, PrettyPrintWriter

public class JDomDriver extends AbstractXmlDriver
{

    public JDomDriver()
    {
        super(new XmlFriendlyReplacer());
    }

    public JDomDriver(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        JDomReader jdomreader;
        try
        {
            jdomreader = new JDomReader((new SAXBuilder()).build(inputstream), xmlFriendlyReplacer());
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        catch(JDOMException jdomexception)
        {
            throw new StreamException(jdomexception);
        }
        return jdomreader;
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        JDomReader jdomreader;
        try
        {
            jdomreader = new JDomReader((new SAXBuilder()).build(reader), xmlFriendlyReplacer());
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        catch(JDOMException jdomexception)
        {
            throw new StreamException(jdomexception);
        }
        return jdomreader;
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        return new PrettyPrintWriter(new OutputStreamWriter(outputstream));
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        return new PrettyPrintWriter(writer, xmlFriendlyReplacer());
    }
}
