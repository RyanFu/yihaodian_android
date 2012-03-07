// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.converters.ErrorWriter;
import com.thoughtworks.xstream.io.StreamException;
import java.io.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractPullReader, XmlFriendlyReplacer

public class XppReader extends AbstractPullReader
{

    public XppReader(Reader reader1)
    {
        this(reader1, new XmlFriendlyReplacer());
    }

    public XppReader(Reader reader1, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        try
        {
            parser = createParser();
            reader = new BufferedReader(reader1);
            parser.setInput(reader);
            moveDown();
            return;
        }
        catch(XmlPullParserException xmlpullparserexception)
        {
            throw new StreamException(xmlpullparserexception);
        }
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
        errorwriter.add("line number", String.valueOf(parser.getLineNumber()));
    }

    @Override
	public void close()
    {
        try
        {
            reader.close();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    protected XmlPullParser createParser()
    {
        return new MXParser();
    }

    @Override
	public String getAttribute(int i)
    {
        return parser.getAttributeValue(i);
    }

    @Override
	public String getAttribute(String s)
    {
        return parser.getAttributeValue(null, escapeXmlName(s));
    }

    @Override
	public int getAttributeCount()
    {
        return parser.getAttributeCount();
    }

    @Override
	public String getAttributeName(int i)
    {
        return unescapeXmlName(parser.getAttributeName(i));
    }

    @Override
	protected String pullElementName()
    {
        return parser.getName();
    }

    @Override
	protected int pullNextEvent()
    {
        int i;
        int j;
        try
        {
            i = parser.next();
        }
        catch(XmlPullParserException xmlpullparserexception)
        {
            throw new StreamException(xmlpullparserexception);
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
        i;
        JVM INSTR tableswitch 0 9: default 64
    //                   0 70
    //                   1 76
    //                   2 70
    //                   3 76
    //                   4 82
    //                   5 64
    //                   6 64
    //                   7 64
    //                   8 64
    //                   9 88;
           goto _L1 _L2 _L3 _L2 _L3 _L4 _L1 _L1 _L1 _L1 _L5
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
        return parser.getText();
    }

    private final XmlPullParser parser;
    private final BufferedReader reader;
}
