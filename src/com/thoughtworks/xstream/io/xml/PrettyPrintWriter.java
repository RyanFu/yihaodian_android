// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.FastStack;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.StreamException;
import java.io.Writer;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlWriter, XmlFriendlyReplacer

public class PrettyPrintWriter extends AbstractXmlWriter
{

    public PrettyPrintWriter(Writer writer1)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer1, ac);
    }

    public PrettyPrintWriter(Writer writer1, int i)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer1, i, ac);
    }

    public PrettyPrintWriter(Writer writer1, int i, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer1, i, ac, xmlfriendlyreplacer);
    }

    public PrettyPrintWriter(Writer writer1, int i, String s)
    {
        this(writer1, i, s.toCharArray());
    }

    public PrettyPrintWriter(Writer writer1, int i, char ac[])
    {
        this(writer1, i, ac, new XmlFriendlyReplacer());
    }

    public PrettyPrintWriter(Writer writer1, int i, char ac[], XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(writer1, i, ac, xmlfriendlyreplacer, "\n");
    }

    private PrettyPrintWriter(Writer writer1, int i, char ac[], XmlFriendlyReplacer xmlfriendlyreplacer, String s)
    {
        super(xmlfriendlyreplacer);
        elementStack = new FastStack(16);
        writer = new QuickWriter(writer1);
        lineIndenter = ac;
        newLine = s;
        mode = i;
        if(i < XML_QUIRKS || i > XML_1_1)
            throw new IllegalArgumentException("Not a valid XML mode");
        else
            return;
    }

    public PrettyPrintWriter(Writer writer1, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer1, ac, "\n", xmlfriendlyreplacer);
    }

    public PrettyPrintWriter(Writer writer1, String s)
    {
        this(writer1, s.toCharArray());
    }

    public PrettyPrintWriter(Writer writer1, String s, String s1)
    {
        this(writer1, s.toCharArray(), s1);
    }

    public PrettyPrintWriter(Writer writer1, char ac[])
    {
        this(writer1, ac, "\n");
    }

    public PrettyPrintWriter(Writer writer1, char ac[], String s)
    {
        this(writer1, ac, s, new XmlFriendlyReplacer());
    }

    public PrettyPrintWriter(Writer writer1, char ac[], String s, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        this(writer1, XML_QUIRKS, ac, xmlfriendlyreplacer, s);
    }

    private void finishTag()
    {
        if(tagInProgress)
            writer.write('>');
        tagInProgress = false;
        if(readyForNewLine)
            endOfLine();
        readyForNewLine = false;
        tagIsEmpty = false;
    }

    private void writeText(String s)
    {
        int i;
        int j;
        i = s.length();
        j = 0;
_L11:
        char c;
        if(j >= i)
            break MISSING_BLOCK_LABEL_492;
        c = s.charAt(j);
        c;
        JVM INSTR lookupswitch 9: default 104
    //                   0: 180
    //                   9: 294
    //                   10: 294
    //                   13: 281
    //                   34: 255
    //                   38: 216
    //                   39: 268
    //                   60: 229
    //                   62: 242;
           goto _L1 _L2 _L3 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L2:
        if(mode != XML_QUIRKS)
            break; /* Loop/switch isn't completed */
        writer.write(NULL);
_L12:
        j++;
        if(true) goto _L11; else goto _L10
_L10:
        throw new StreamException("Invalid character 0x0 in XML stream");
_L6:
        writer.write(AMP);
          goto _L12
_L8:
        writer.write(LT);
          goto _L12
_L9:
        writer.write(GT);
          goto _L12
_L5:
        writer.write(QUOT);
          goto _L12
_L7:
        writer.write(APOS);
          goto _L12
_L4:
        writer.write(CR);
          goto _L12
_L3:
        writer.write(c);
          goto _L12
_L1:
        if(!Character.isDefined(c) || Character.isISOControl(c))
            break MISSING_BLOCK_LABEL_318;
        if(mode != XML_QUIRKS && c > '\uD7FF' && c < '\uE000')
            throw new StreamException("Invalid character 0x" + Integer.toHexString(c) + " in XML stream");
        writer.write(c);
        break; /* Loop/switch isn't completed */
        if(mode == XML_1_0 && (c < '\t' || c == '\013' || c == '\f' || c == '\016' || c == '\017'))
            throw new StreamException("Invalid character 0x" + Integer.toHexString(c) + " in XML 1.0 stream");
        if(mode != XML_QUIRKS && (c == '\uFFFE' || c == '\uFFFF'))
            throw new StreamException("Invalid character 0x" + Integer.toHexString(c) + " in XML stream");
        writer.write("&#x");
        writer.write(Integer.toHexString(c));
        writer.write(';');
          goto _L12
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        writer.write(' ');
        writer.write(escapeXmlName(s));
        writer.write('=');
        writer.write('"');
        writeAttributeValue(writer, s1);
        writer.write('"');
    }

    @Override
	public void close()
    {
        writer.close();
    }

    @Override
	public void endNode()
    {
        depth = depth - 1;
        if(tagIsEmpty)
        {
            writer.write('/');
            readyForNewLine = false;
            finishTag();
            elementStack.popSilently();
        } else
        {
            finishTag();
            writer.write(CLOSE);
            writer.write((String)elementStack.pop());
            writer.write('>');
        }
        readyForNewLine = true;
        if(depth == 0)
            writer.flush();
    }

    protected void endOfLine()
    {
        writer.write(getNewLine());
        for(int i = 0; i < depth; i++)
            writer.write(lineIndenter);

    }

    @Override
	public void flush()
    {
        writer.flush();
    }

    protected String getNewLine()
    {
        return newLine;
    }

    @Override
	public void setValue(String s)
    {
        readyForNewLine = false;
        tagIsEmpty = false;
        finishTag();
        writeText(writer, s);
    }

    @Override
	public void startNode(String s)
    {
        String s1 = escapeXmlName(s);
        tagIsEmpty = false;
        finishTag();
        writer.write('<');
        writer.write(s1);
        elementStack.push(s1);
        tagInProgress = true;
        depth = 1 + depth;
        readyForNewLine = true;
        tagIsEmpty = true;
    }

    @Override
	public void startNode(String s, Class class1)
    {
        startNode(s);
    }

    protected void writeAttributeValue(QuickWriter quickwriter, String s)
    {
        writeText(s);
    }

    protected void writeText(QuickWriter quickwriter, String s)
    {
        writeText(s);
    }

    private static final char AMP[] = "&amp;".toCharArray();
    private static final char APOS[] = "&apos;".toCharArray();
    private static final char CLOSE[] = "</".toCharArray();
    private static final char CR[] = "&#xd;".toCharArray();
    private static final char GT[] = "&gt;".toCharArray();
    private static final char LT[] = "&lt;".toCharArray();
    private static final char NULL[] = "&#x0;".toCharArray();
    private static final char QUOT[] = "&quot;".toCharArray();
    public static int XML_1_0 = 0;
    public static int XML_1_1 = 1;
    public static int XML_QUIRKS = -1;
    protected int depth;
    private final FastStack elementStack;
    private final char lineIndenter[];
    private final int mode;
    private String newLine;
    private boolean readyForNewLine;
    private boolean tagInProgress;
    private boolean tagIsEmpty;
    private final QuickWriter writer;

}
