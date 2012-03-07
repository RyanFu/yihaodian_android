// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.json;

import com.thoughtworks.xstream.io.xml.*;
import java.util.ArrayList;
import javax.xml.namespace.QName;

public class JettisonStaxWriter extends StaxWriter
{

    public JettisonStaxWriter(QNameMap qnamemap, XMLStreamWriter xmlstreamwriter, MappedNamespaceConvention mappednamespaceconvention)
        throws XMLStreamException
    {
        super(qnamemap, xmlstreamwriter);
        convention = mappednamespaceconvention;
    }

    public JettisonStaxWriter(QNameMap qnamemap, XMLStreamWriter xmlstreamwriter, boolean flag, boolean flag1, XmlFriendlyReplacer xmlfriendlyreplacer, MappedNamespaceConvention mappednamespaceconvention)
        throws XMLStreamException
    {
        super(qnamemap, xmlstreamwriter, flag, flag1, xmlfriendlyreplacer);
        convention = mappednamespaceconvention;
    }

    public JettisonStaxWriter(QNameMap qnamemap, XMLStreamWriter xmlstreamwriter, boolean flag, boolean flag1, MappedNamespaceConvention mappednamespaceconvention)
        throws XMLStreamException
    {
        super(qnamemap, xmlstreamwriter, flag, flag1);
        convention = mappednamespaceconvention;
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    @Override
	public void startNode(String s, Class class1)
    {
label0:
        {
            XMLStreamWriter xmlstreamwriter = getXMLStreamWriter();
            if(class1 == null || !(xmlstreamwriter instanceof AbstractXMLStreamWriter))
                break label0;
            Class class2;
            if(class$java$util$Collection == null)
            {
                class2 = _mthclass$("java.util.Collection");
                class$java$util$Collection = class2;
            } else
            {
                class2 = class$java$util$Collection;
            }
            if(!class2.isAssignableFrom(class1))
            {
                QName qname;
                String s1;
                String s2;
                String s3;
                Class class3;
                if(class$java$util$Map == null)
                {
                    class3 = _mthclass$("java.util.Map");
                    class$java$util$Map = class3;
                } else
                {
                    class3 = class$java$util$Map;
                }
                if(!class3.isAssignableFrom(class1) && !class1.isArray())
                    break label0;
            }
            qname = getQNameMap().getQName(escapeXmlName(s));
            s1 = qname.getPrefix();
            s2 = qname.getNamespaceURI();
            s3 = convention.createKey(s1, s2, qname.getLocalPart());
            if(!((AbstractXMLStreamWriter)xmlstreamwriter).getSerializedAsArrays().contains(s3))
                ((AbstractXMLStreamWriter)xmlstreamwriter).seriliazeAsArray(s3);
        }
        startNode(s);
    }

    static Class class$java$util$Collection;
    static Class class$java$util$Map;
    private final MappedNamespaceConvention convention;
}
