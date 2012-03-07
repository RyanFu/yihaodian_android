// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import java.io.Writer;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            PrettyPrintWriter, XmlFriendlyReplacer

public class CompactWriter extends PrettyPrintWriter
{

    public CompactWriter(Writer writer)
    {
        super(writer);
    }

    public CompactWriter(Writer writer, int i)
    {
        super(writer, i);
    }

    public CompactWriter(Writer writer, int i, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(writer, i, xmlfriendlyreplacer);
    }

    public CompactWriter(Writer writer, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(writer, xmlfriendlyreplacer);
    }

    @Override
	protected void endOfLine()
    {
    }
}
