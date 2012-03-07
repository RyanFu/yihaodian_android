// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.converters.ErrorWriter;
import com.thoughtworks.xstream.io.StreamException;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractPullReader, XmlFriendlyReplacer, QNameMap

public class StaxReader extends AbstractPullReader
{

    public StaxReader(QNameMap qnamemap, XMLStreamReader xmlstreamreader)
    {
        this(qnamemap, xmlstreamreader, new XmlFriendlyReplacer());
    }

    public StaxReader(QNameMap qnamemap, XMLStreamReader xmlstreamreader, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        qnameMap = qnamemap;
        in = xmlstreamreader;
        moveDown();
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
        errorwriter.add("line number", String.valueOf(in.getLocation().getLineNumber()));
    }

    @Override
	public void close()
    {
        try
        {
            in.close();
            return;
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
    }

    @Override
	public String getAttribute(int i)
    {
        return in.getAttributeValue(i);
    }

    @Override
	public String getAttribute(String s)
    {
        return in.getAttributeValue(null, s);
    }

    @Override
	public int getAttributeCount()
    {
        return in.getAttributeCount();
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(in.getAttributeLocalName(i));
    }

    @Override
	protected String pullElementName()
    {
        javax.xml.namespace.QName qname = in.getName();
        return qnameMap.getJavaClassName(qname);
    }

    @Override
	protected int pullNextEvent()
    {
        int i;
        int j;
        try
        {
            i = in.next();
        }
        catch(XMLStreamException xmlstreamexception)
        {
            throw new StreamException(xmlstreamexception);
        }
        i;
        JVM INSTR tableswitch 1 8: default 56
    //                   1 60
    //                   2 65
    //                   3 56
    //                   4 70
    //                   5 75
    //                   6 56
    //                   7 60
    //                   8 65;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L1 _L2 _L3
_L1:
        j = 0;
_L7:
        return j;
_L2:
        j = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        j = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 3;
        continue; /* Loop/switch isn't completed */
_L5:
        j = 4;
        if(true) goto _L7; else goto _L6
_L6:
    }

    @Override
	protected String pullText()
    {
        return in.getText();
    }

    private final XMLStreamReader in;
    private final QNameMap qnameMap;
}
