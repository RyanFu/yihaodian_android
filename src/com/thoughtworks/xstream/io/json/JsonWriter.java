// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.json;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.core.util.*;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.io.Writer;

public class JsonWriter
    implements ExtendedHierarchicalStreamWriter
{
    public class Node
    {

        public final Class clazz;
        public boolean fieldAlready;
        public boolean isCollection;
        public final String name;
        private final JsonWriter this$0;

        public Node(String s, Class class1)
        {
            this$0 = JsonWriter.this;
            name = s;
            clazz = class1;
            isCollection = JsonWriter.this.isCollection(class1);
        }
    }


    public JsonWriter(Writer writer1)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer1, ac);
    }

    public JsonWriter(Writer writer1, int i)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer1, ac, "\n", i);
    }

    public JsonWriter(Writer writer1, String s)
    {
        this(writer1, s.toCharArray());
    }

    public JsonWriter(Writer writer1, String s, String s1)
    {
        this(writer1, s.toCharArray(), s1);
    }

    public JsonWriter(Writer writer1, char ac[])
    {
        this(writer1, ac, "\n");
    }

    public JsonWriter(Writer writer1, char ac[], String s)
    {
        this(writer1, ac, s, 0);
    }

    public JsonWriter(Writer writer1, char ac[], String s, int i)
    {
        elementStack = new FastStack(16);
        writer = new QuickWriter(writer1);
        lineIndenter = ac;
        newLine = s;
        mode = i;
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

    private void finishTag()
    {
        if(readyForNewLine)
            endOfLine();
        readyForNewLine = false;
        tagIsEmpty = false;
    }

    private boolean isCollection(Class class1)
    {
        if(class1 == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class2;
        Class class3;
        Class class4;
        if(class$java$util$Collection == null)
        {
            class2 = _mthclass$("java.util.Collection");
            class$java$util$Collection = class2;
        } else
        {
            class2 = class$java$util$Collection;
        }
        if(class2.isAssignableFrom(class1) || class1.isArray()) goto _L4; else goto _L3
_L3:
        if(class$java$util$Map == null)
        {
            class3 = _mthclass$("java.util.Map");
            class$java$util$Map = class3;
        } else
        {
            class3 = class$java$util$Map;
        }
        if(class3.isAssignableFrom(class1)) goto _L4; else goto _L5
_L5:
        if(class$java$util$Map$Entry == null)
        {
            class4 = _mthclass$("java.util.Map$Entry");
            class$java$util$Map$Entry = class4;
        } else
        {
            class4 = class$java$util$Map$Entry;
        }
        if(!class4.isAssignableFrom(class1)) goto _L2; else goto _L4
_L4:
        flag = true;
_L7:
        return flag;
_L2:
        flag = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private boolean needsQuotes(Class class1)
    {
        boolean flag;
        if(class1 == null || !class1.isPrimitive())
            class1 = Primitives.unbox(class1);
        if(class1 == null || class1 == Character.TYPE)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void writeText(String s, Class class1)
    {
        char c;
        if(needsQuotes(class1))
            writer.write("\"");
        Class class2;
        int i;
        int j;
        if(class$java$lang$Character == null)
        {
            class2 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class2;
        } else
        {
            class2 = class$java$lang$Character;
        }
        if((class1 == class2 || class1 == Character.TYPE) && "".equals(s))
            s = "\uFFFD\uFFFD";
        i = s.length();
        j = 0;
        if(j >= i)
            break MISSING_BLOCK_LABEL_217;
        c = s.charAt(j);
        switch(c)
        {
        default:
            if(c > '\037')
            {
                writer.write(c);
            } else
            {
                writer.write("\\u");
                String s1 = "000" + Integer.toHexString(c);
                writer.write(s1.substring(s1.length() - 4));
            }
            break;

        case 34: // '"'
            break; /* Loop/switch isn't completed */

        case 92: // '\\'
            break MISSING_BLOCK_LABEL_149;
        }
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_66;
_L1:
        writer.write("\\\"");
        continue; /* Loop/switch isn't completed */
        writer.write("\\\\");
        if(true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_124;
_L3:
        if(needsQuotes(class1))
            writer.write("\"");
        return;
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        Node node = (Node)elementStack.peek();
        if(node == null || !node.isCollection)
        {
            String s2 = '@' + s;
            Class class1;
            Class class2;
            if(class$java$lang$String == null)
            {
                class1 = _mthclass$("java.lang.String");
                class$java$lang$String = class1;
            } else
            {
                class1 = class$java$lang$String;
            }
            startNode(s2, class1);
            tagIsEmpty = false;
            if(class$java$lang$String == null)
            {
                class2 = _mthclass$("java.lang.String");
                class$java$lang$String = class2;
            } else
            {
                class2 = class$java$lang$String;
            }
            writeText(s1, class2);
            endNode();
        }
    }

    @Override
	public void close()
    {
        writer.close();
    }

    @Override
	public void endNode()
    {
        Node node;
        depth = depth - 1;
        node = (Node)elementStack.pop();
        if(node.clazz == null || !node.isCollection) goto _L2; else goto _L1
_L1:
        if(node.fieldAlready)
            readyForNewLine = true;
        finishTag();
        writer.write("]");
_L4:
        readyForNewLine = true;
        if(depth == 0 && ((1 & mode) == 0 || depth > 0 && !node.isCollection))
        {
            writer.write("}");
            writer.flush();
        }
        return;
_L2:
        if(tagIsEmpty)
        {
            readyForNewLine = false;
            writer.write("{}");
            finishTag();
        } else
        {
            finishTag();
            if(node.fieldAlready)
                writer.write("}");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void endOfLine()
    {
        writer.write(newLine);
        for(int i = 0; i < depth; i++)
            writer.write(lineIndenter);

    }

    @Override
	public void flush()
    {
        writer.flush();
    }

    @Override
	public void setValue(String s)
    {
        Node node = (Node)elementStack.peek();
        if(node != null && node.fieldAlready)
        {
            Class class1;
            Class class2;
            if(class$java$lang$String == null)
            {
                class1 = _mthclass$("java.lang.String");
                class$java$lang$String = class1;
            } else
            {
                class1 = class$java$lang$String;
            }
            startNode("$", class1);
            tagIsEmpty = false;
            if(class$java$lang$String == null)
            {
                class2 = _mthclass$("java.lang.String");
                class$java$lang$String = class2;
            } else
            {
                class2 = class$java$lang$String;
            }
            writeText(s, class2);
            endNode();
        } else
        {
            if((3 & mode) == 3 && depth == 1)
                throw new ConversionException("Single value cannot be JSON root element");
            readyForNewLine = false;
            tagIsEmpty = false;
            finishTag();
            writeText(writer, s);
        }
    }

    @Override
	public void startNode(String s)
    {
        startNode(s, null);
    }

    @Override
	public void startNode(String s, Class class1)
    {
        Node node = (Node)elementStack.peek();
        if(node == null && ((1 & mode) == 0 || depth > 0 && !isCollection(class1)))
            writer.write("{");
        if(node != null && node.fieldAlready)
        {
            writer.write(",");
            readyForNewLine = true;
        }
        tagIsEmpty = false;
        finishTag();
        if(node == null || node.clazz == null || node.clazz != null && !node.isCollection)
        {
            if(node != null && !node.fieldAlready)
            {
                writer.write("{");
                readyForNewLine = true;
                finishTag();
            }
            if((1 & mode) == 0 || depth > 0)
            {
                writer.write("\"");
                writer.write(s);
                writer.write("\": ");
            }
        }
        if(isCollection(class1))
        {
            writer.write("[");
            readyForNewLine = true;
        }
        if(node != null)
            node.fieldAlready = true;
        elementStack.push(new Node(s, class1));
        depth = 1 + depth;
        tagIsEmpty = true;
    }

    @Override
	public HierarchicalStreamWriter underlyingWriter()
    {
        return this;
    }

    protected void writeAttributeValue(QuickWriter quickwriter, String s)
    {
        writeText(s, ((Class) (null)));
    }

    protected void writeText(QuickWriter quickwriter, String s)
    {
        writeText(s, ((Node)elementStack.peek()).clazz);
    }

    public static final int DROP_ROOT_MODE = 1;
    public static final int STRICT_MODE = 2;
    static Class class$java$lang$Character;
    static Class class$java$lang$String;
    static Class class$java$util$Collection;
    static Class class$java$util$Map;
    static Class class$java$util$Map$Entry;
    private int depth;
    private final FastStack elementStack;
    private final char lineIndenter[];
    private int mode;
    private final String newLine;
    private boolean readyForNewLine;
    private boolean tagIsEmpty;
    private final QuickWriter writer;

}
