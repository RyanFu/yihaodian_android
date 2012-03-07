// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.json;

import java.io.Writer;

// Referenced classes of package com.thoughtworks.xstream.io.json:
//            JsonWriter

public class JsonHierarchicalStreamWriter extends JsonWriter
{

    public JsonHierarchicalStreamWriter(Writer writer)
    {
        char ac[] = new char[2];
        ac[0] = ' ';
        ac[1] = ' ';
        this(writer, ac);
    }

    public JsonHierarchicalStreamWriter(Writer writer, String s)
    {
        this(writer, s.toCharArray());
    }

    public JsonHierarchicalStreamWriter(Writer writer, String s, String s1)
    {
        this(writer, s.toCharArray(), s1);
    }

    public JsonHierarchicalStreamWriter(Writer writer, char ac[])
    {
        this(writer, ac, "\n");
    }

    public JsonHierarchicalStreamWriter(Writer writer, char ac[], String s)
    {
        super(writer, ac, s);
    }
}
