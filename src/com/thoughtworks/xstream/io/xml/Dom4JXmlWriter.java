// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.FastStack;
import com.thoughtworks.xstream.io.StreamException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlWriter, XmlFriendlyReplacer

public class Dom4JXmlWriter extends AbstractXmlWriter
{

    public Dom4JXmlWriter(XMLWriter xmlwriter)
    {
        this(xmlwriter, new XmlFriendlyReplacer());
    }

    public Dom4JXmlWriter(XMLWriter xmlwriter, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        writer = xmlwriter;
        elementStack = new FastStack(16);
        attributes = new AttributesImpl();
        try
        {
            xmlwriter.startDocument();
            return;
        }
        catch(SAXException saxexception)
        {
            throw new StreamException(saxexception);
        }
    }

    private void startElement()
        throws SAXException
    {
        if(!started)
        {
            writer.startElement("", "", (String)elementStack.peek(), attributes);
            attributes.clear();
            started = true;
        }
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        attributes.addAttribute("", "", escapeXmlName(s), "string", s1);
    }

    @Override
	public void close()
    {
        try
        {
            writer.endDocument();
            return;
        }
        catch(SAXException saxexception)
        {
            throw new StreamException(saxexception);
        }
    }

    @Override
	public void endNode()
    {
        try
        {
            if(!children)
            {
                DefaultElement defaultelement = new DefaultElement((String)elementStack.pop());
                for(int i = 0; i < attributes.getLength(); i++)
                    defaultelement.addAttribute(attributes.getQName(i), attributes.getValue(i));

                writer.write(defaultelement);
                attributes.clear();
                children = true;
                started = true;
            } else
            {
                startElement();
                writer.endElement("", "", (String)elementStack.pop());
            }
        }
        catch(SAXException saxexception)
        {
            throw new StreamException(saxexception);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    @Override
	public void flush()
    {
    }

    @Override
	public void setValue(String s)
    {
        char ac[] = s.toCharArray();
        if(ac.length > 0)
        {
            try
            {
                startElement();
                writer.characters(ac, 0, ac.length);
            }
            catch(SAXException saxexception)
            {
                throw new StreamException(saxexception);
            }
            children = true;
        }
    }

    @Override
	public void startNode(String s)
    {
        if(elementStack.size() > 0)
        {
            try
            {
                startElement();
            }
            catch(SAXException saxexception)
            {
                throw new StreamException(saxexception);
            }
            started = false;
        }
        elementStack.push(escapeXmlName(s));
        children = false;
    }

    private AttributesImpl attributes;
    private boolean children;
    private final FastStack elementStack;
    private boolean started;
    private final XMLWriter writer;
}
