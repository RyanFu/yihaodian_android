// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlWriter, XmlFriendlyReplacer

public final class SaxWriter extends AbstractXmlWriter
    implements XMLReader
{

    public SaxWriter()
    {
        this(true);
    }

    public SaxWriter(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(true, xmlfriendlyreplacer);
    }

    public SaxWriter(boolean flag)
    {
        this(flag, new XmlFriendlyReplacer());
    }

    public SaxWriter(boolean flag, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        entityResolver = null;
        dtdHandler = null;
        contentHandler = null;
        errorHandler = null;
        features = new HashMap();
        properties = new HashMap();
        depth = 0;
        elementStack = new LinkedList();
        buffer = new char[128];
        startTagInProgress = false;
        attributeList = new AttributesImpl();
        includeEnclosingDocument = flag;
    }

    private void endDocument(boolean flag)
        throws SAXException
    {
        if(depth == 0 || depth == 1 && flag)
        {
            contentHandler.endDocument();
            depth = 0;
        }
    }

    private void flushStartTag()
        throws SAXException
    {
        if(startTagInProgress)
        {
            String s = (String)elementStack.get(0);
            contentHandler.startElement("", s, s, attributeList);
            attributeList.clear();
            startTagInProgress = false;
        }
    }

    private void parse()
        throws SAXException
    {
        XStream xstream = (XStream)properties.get("http://com.thoughtworks.xstream/sax/property/configured-xstream");
        if(xstream == null)
            xstream = new XStream();
        List list = (List)properties.get("http://com.thoughtworks.xstream/sax/property/source-object-list");
        if(list == null || list.isEmpty())
            throw new SAXException("Missing or empty source object list. Setting property \"http://com.thoughtworks.xstream/sax/property/source-object-list\" is mandatory");
        try
        {
            startDocument(true);
            for(Iterator iterator = list.iterator(); iterator.hasNext(); xstream.marshal(iterator.next(), this));
        }
        catch(StreamException streamexception)
        {
            if(streamexception.getCause() instanceof SAXException)
                throw (SAXException)streamexception.getCause();
            else
                throw new SAXException(streamexception);
        }
        endDocument(true);
        return;
    }

    private void startDocument(boolean flag)
        throws SAXException
    {
        if(depth == 0)
        {
            contentHandler.startDocument();
            if(flag)
                depth = 1 + depth;
        }
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        if(startTagInProgress)
        {
            String s2 = escapeXmlName(s);
            attributeList.addAttribute("", s2, s2, "CDATA", s1);
            return;
        } else
        {
            throw new StreamException(new IllegalStateException("No startElement being processed"));
        }
    }

    @Override
	public void close()
    {
    }

    @Override
	public void endNode()
    {
        try
        {
            flushStartTag();
            String s = (String)elementStack.remove(0);
            contentHandler.endElement("", s, s);
            depth = depth - 1;
            if(depth == 0 && includeEnclosingDocument)
                endDocument(false);
            return;
        }
        catch(SAXException saxexception)
        {
            throw new StreamException(saxexception);
        }
    }

    @Override
	public void flush()
    {
    }

    @Override
	public ContentHandler getContentHandler()
    {
        return contentHandler;
    }

    @Override
	public DTDHandler getDTDHandler()
    {
        return dtdHandler;
    }

    @Override
	public EntityResolver getEntityResolver()
    {
        return entityResolver;
    }

    @Override
	public ErrorHandler getErrorHandler()
    {
        return errorHandler;
    }

    @Override
	public boolean getFeature(String s)
        throws SAXNotRecognizedException
    {
        if(s.equals("http://xml.org/sax/features/namespaces") || s.equals("http://xml.org/sax/features/namespace-prefixes"))
        {
            Boolean boolean1 = (Boolean)features.get(s);
            if(boolean1 == null)
                boolean1 = Boolean.FALSE;
            return boolean1.booleanValue();
        } else
        {
            throw new SAXNotRecognizedException(s);
        }
    }

    @Override
	public Object getProperty(String s)
        throws SAXNotRecognizedException
    {
        if(s.equals("http://com.thoughtworks.xstream/sax/property/configured-xstream") || s.equals("http://com.thoughtworks.xstream/sax/property/source-object-list"))
            return properties.get(s);
        else
            throw new SAXNotRecognizedException(s);
    }

    @Override
	public void parse(String s)
        throws SAXException
    {
        parse();
    }

    @Override
	public void parse(InputSource inputsource)
        throws SAXException
    {
        parse();
    }

    @Override
	public void setContentHandler(ContentHandler contenthandler)
    {
        if(contenthandler == null)
        {
            throw new NullPointerException("handler");
        } else
        {
            contentHandler = contenthandler;
            return;
        }
    }

    @Override
	public void setDTDHandler(DTDHandler dtdhandler)
    {
        if(dtdhandler == null)
        {
            throw new NullPointerException("handler");
        } else
        {
            dtdHandler = dtdhandler;
            return;
        }
    }

    @Override
	public void setEntityResolver(EntityResolver entityresolver)
    {
        if(entityresolver == null)
        {
            throw new NullPointerException("resolver");
        } else
        {
            entityResolver = entityresolver;
            return;
        }
    }

    @Override
	public void setErrorHandler(ErrorHandler errorhandler)
    {
        if(errorhandler == null)
        {
            throw new NullPointerException("handler");
        } else
        {
            errorHandler = errorhandler;
            return;
        }
    }

    @Override
	public void setFeature(String s, boolean flag)
        throws SAXNotRecognizedException
    {
        if(s.equals("http://xml.org/sax/features/namespaces") || s.equals("http://xml.org/sax/features/namespace-prefixes"))
        {
            Map map = features;
            Boolean boolean1;
            if(flag)
                boolean1 = Boolean.TRUE;
            else
                boolean1 = Boolean.FALSE;
            map.put(s, boolean1);
            return;
        } else
        {
            throw new SAXNotRecognizedException(s);
        }
    }

    @Override
	public void setProperty(String s, Object obj)
        throws SAXNotRecognizedException, SAXNotSupportedException
    {
label0:
        {
label1:
            {
                if(s.equals("http://com.thoughtworks.xstream/sax/property/configured-xstream"))
                {
                    if(!(obj instanceof XStream))
                        throw new SAXNotSupportedException("Value for property \"http://com.thoughtworks.xstream/sax/property/configured-xstream\" must be a non-null XStream object");
                } else
                {
                    if(!s.equals("http://com.thoughtworks.xstream/sax/property/source-object-list"))
                        break label0;
                    if(!(obj instanceof List))
                        break label1;
                    List list = (List)obj;
                    if(list.isEmpty())
                        throw new SAXNotSupportedException("Value for property \"http://com.thoughtworks.xstream/sax/property/source-object-list\" shall not be an empty list");
                    obj = Collections.unmodifiableList(new ArrayList(list));
                }
                properties.put(s, obj);
                return;
            }
            throw new SAXNotSupportedException("Value for property \"http://com.thoughtworks.xstream/sax/property/source-object-list\" must be a non-null List object");
        }
        throw new SAXNotRecognizedException(s);
    }

    @Override
	public void setValue(String s)
    {
        try
        {
            flushStartTag();
            int i = s.length();
            if(i > buffer.length)
                buffer = new char[i];
            s.getChars(0, i, buffer, 0);
            contentHandler.characters(buffer, 0, i);
            return;
        }
        catch(SAXException saxexception)
        {
            throw new StreamException(saxexception);
        }
    }

    @Override
	public void startNode(String s)
    {
        if(depth == 0) goto _L2; else goto _L1
_L1:
        flushStartTag();
_L4:
        elementStack.add(0, escapeXmlName(s));
        startTagInProgress = true;
        depth = 1 + depth;
        return;
_L2:
        if(includeEnclosingDocument)
            startDocument(false);
        if(true) goto _L4; else goto _L3
_L3:
        SAXException saxexception;
        saxexception;
        throw new StreamException(saxexception);
    }

    public static final String CONFIGURED_XSTREAM_PROPERTY = "http://com.thoughtworks.xstream/sax/property/configured-xstream";
    public static final String SOURCE_OBJECT_LIST_PROPERTY = "http://com.thoughtworks.xstream/sax/property/source-object-list";
    private final AttributesImpl attributeList;
    private char buffer[];
    private ContentHandler contentHandler;
    private int depth;
    private DTDHandler dtdHandler;
    private List elementStack;
    private EntityResolver entityResolver;
    private ErrorHandler errorHandler;
    private Map features;
    private final boolean includeEnclosingDocument;
    private final Map properties;
    private boolean startTagInProgress;
}
