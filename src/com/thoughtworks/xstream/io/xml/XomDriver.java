// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.*;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlDriver, XmlFriendlyReplacer, XomReader, PrettyPrintWriter

public class XomDriver extends AbstractXmlDriver
{

    public XomDriver()
    {
        this(new Builder());
    }

    public XomDriver(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(new Builder(), xmlfriendlyreplacer);
    }

    public XomDriver(Builder builder1)
    {
        this(builder1, new XmlFriendlyReplacer());
    }

    public XomDriver(Builder builder1, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        builder = builder1;
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        XomReader xomreader;
        try
        {
            xomreader = new XomReader(builder.build(inputstream), xmlFriendlyReplacer());
        }
        catch(ValidityException validityexception)
        {
            throw new StreamException(validityexception);
        }
        catch(ParsingException parsingexception)
        {
            throw new StreamException(parsingexception);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        return xomreader;
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        XomReader xomreader;
        try
        {
            xomreader = new XomReader(builder.build(reader), xmlFriendlyReplacer());
        }
        catch(ValidityException validityexception)
        {
            throw new StreamException(validityexception);
        }
        catch(ParsingException parsingexception)
        {
            throw new StreamException(parsingexception);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        return xomreader;
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        return new PrettyPrintWriter(new OutputStreamWriter(outputstream), xmlFriendlyReplacer());
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        return new PrettyPrintWriter(writer, xmlFriendlyReplacer());
    }

    protected Builder getBuilder()
    {
        return builder;
    }

    private final Builder builder;
}
