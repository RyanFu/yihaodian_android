// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.*;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            SaxWriter

public class TraxSource extends SAXSource
{

    public TraxSource()
    {
        super(new InputSource());
        xmlReader = null;
        xstream = null;
        source = null;
    }

    public TraxSource(Object obj)
    {
        super(new InputSource());
        xmlReader = null;
        xstream = null;
        source = null;
        setSource(obj);
    }

    public TraxSource(Object obj, XStream xstream1)
    {
        super(new InputSource());
        xmlReader = null;
        xstream = null;
        source = null;
        setSource(obj);
        setXStream(xstream1);
    }

    public TraxSource(List list)
    {
        super(new InputSource());
        xmlReader = null;
        xstream = null;
        source = null;
        setSourceAsList(list);
    }

    public TraxSource(List list, XStream xstream1)
    {
        super(new InputSource());
        xmlReader = null;
        xstream = null;
        source = null;
        setSourceAsList(list);
        setXStream(xstream1);
    }

    private void configureXMLReader()
    {
        if(xmlReader == null)
            break MISSING_BLOCK_LABEL_51;
        if(xstream != null)
            xmlReader.setProperty("http://com.thoughtworks.xstream/sax/property/configured-xstream", xstream);
        if(source != null)
            xmlReader.setProperty("http://com.thoughtworks.xstream/sax/property/source-object-list", source);
        return;
        SAXException saxexception;
        saxexception;
        throw new IllegalArgumentException(saxexception.getMessage());
    }

    private void createXMLReader(XMLReader xmlreader)
    {
        if(xmlreader == null)
            xmlReader = new SaxWriter();
        else
        if(xmlreader instanceof XMLFilter)
        {
            XMLFilter xmlfilter;
            for(xmlfilter = (XMLFilter)xmlreader; xmlfilter.getParent() instanceof XMLFilter; xmlfilter = (XMLFilter)xmlfilter.getParent());
            if(!(xmlfilter.getParent() instanceof SaxWriter))
                xmlfilter.setParent(new SaxWriter());
            xmlReader = xmlreader;
        } else
        {
            throw new UnsupportedOperationException();
        }
        configureXMLReader();
    }

    @Override
	public XMLReader getXMLReader()
    {
        if(xmlReader == null)
            createXMLReader(null);
        return xmlReader;
    }

    @Override
	public void setInputSource(InputSource inputsource)
    {
        throw new UnsupportedOperationException();
    }

    public void setSource(Object obj)
    {
        if(obj == null)
        {
            throw new IllegalArgumentException("obj");
        } else
        {
            ArrayList arraylist = new ArrayList(1);
            arraylist.add(obj);
            setSourceAsList(arraylist);
            return;
        }
    }

    public void setSourceAsList(List list)
    {
        if(list == null || list.isEmpty())
        {
            throw new IllegalArgumentException("list");
        } else
        {
            source = list;
            configureXMLReader();
            return;
        }
    }

    @Override
	public void setXMLReader(XMLReader xmlreader)
    {
        createXMLReader(xmlreader);
    }

    public void setXStream(XStream xstream1)
    {
        if(xstream1 == null)
        {
            throw new IllegalArgumentException("xstream");
        } else
        {
            xstream = xstream1;
            configureXMLReader();
            return;
        }
    }

    public static final String XSTREAM_FEATURE = "http://com.thoughtworks.xstream/XStreamSource/feature";
    private List source;
    private XMLReader xmlReader;
    private XStream xstream;
}
