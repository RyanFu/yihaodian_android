// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.StreamException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlWriter, XmlFriendlyReplacer, QNameMap

public class StaxWriter extends AbstractXmlWriter
{

    public StaxWriter(QNameMap qnamemap, XMLStreamWriter xmlstreamwriter)
        throws XMLStreamException
    {
        this(qnamemap, xmlstreamwriter, true, true);
    }

    public StaxWriter(QNameMap qnamemap, XMLStreamWriter xmlstreamwriter, boolean flag, boolean flag1)
        throws XMLStreamException
    {
        this(qnamemap, xmlstreamwriter, flag, flag1, new XmlFriendlyReplacer());
    }

    public StaxWriter(QNameMap qnamemap, XMLStreamWriter xmlstreamwriter, boolean flag, boolean flag1, XmlFriendlyReplacer xmlfriendlyreplacer)
        throws XMLStreamException
    {
        super(xmlfriendlyreplacer);
        qnameMap = qnamemap;
        out = xmlstreamwriter;
        writeEnclosingDocument = flag;
        namespaceRepairingMode = flag1;
        if(flag)
            xmlstreamwriter.writeStartDocument();
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        try
        {
            out.writeAttribute(escapeXmlName(s), s1);
            return;
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
    }

    @Override
	public void close()
    {
        try
        {
            out.close();
            return;
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
    }

    @Override
	public void endNode()
    {
        try
        {
            tagDepth = tagDepth - 1;
            out.writeEndElement();
            if(tagDepth == 0 && writeEnclosingDocument)
                out.writeEndDocument();
            return;
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
    }

    @Override
	public void flush()
    {
        try
        {
            out.flush();
            return;
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
    }

    protected QNameMap getQNameMap()
    {
        return qnameMap;
    }

    protected XMLStreamWriter getXMLStreamWriter()
    {
        return out;
    }

    public boolean isNamespaceRepairingMode()
    {
        return namespaceRepairingMode;
    }

    @Override
	public void setValue(String s)
    {
        try
        {
            out.writeCharacters(s);
            return;
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
    }

    @Override
	public void startNode(String s)
    {
        QName qname;
        String s1;
        String s2;
        qname = qnameMap.getQName(escapeXmlName(s));
        s1 = qname.getPrefix();
        s2 = qname.getNamespaceURI();
        if(s1 == null || s1.length() <= 0) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L13:
        if(s2 == null || s2.length() <= 0) goto _L4; else goto _L3
_L3:
        boolean flag1 = true;
_L14:
        boolean flag2 = false;
        if(!flag1) goto _L6; else goto _L5
_L5:
        if(!flag) goto _L8; else goto _L7
_L7:
        String s4 = out.getNamespaceContext().getNamespaceURI(s1);
        XMLStreamException xmlstreamexception;
        if(s4 == null || !s4.equals(s2))
            flag2 = true;
_L6:
        if(flag)
            out.setPrefix(s1, s2);
        else
        if(flag1 && flag2)
            out.setDefaultNamespace(s2);
        out.writeStartElement(s1, qname.getLocalPart(), s2);
        if(!flag1 || !flag2 || isNamespaceRepairingMode()) goto _L10; else goto _L9
_L9:
        if(!flag) goto _L12; else goto _L11
_L11:
        out.writeNamespace(s1, s2);
_L10:
        tagDepth = 1 + tagDepth;
        return;
_L8:
        String s3 = out.getNamespaceContext().getNamespaceURI("");
        if(s3 == null || !s3.equals(s2))
            break MISSING_BLOCK_LABEL_289;
          goto _L6
_L12:
        try
        {
            out.writeDefaultNamespace(s2);
        }
        // Misplaced declaration of an exception variable
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
          goto _L10
_L2:
        flag = false;
          goto _L13
_L4:
        flag1 = false;
          goto _L14
        flag2 = true;
          goto _L6
    }

    private boolean namespaceRepairingMode;
    private final XMLStreamWriter out;
    private final QNameMap qnameMap;
    private int tagDepth;
    private final boolean writeEnclosingDocument;
}
