// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.json;

import com.thoughtworks.xstream.io.*;
import java.io.*;

// Referenced classes of package com.thoughtworks.xstream.io.json:
//            JsonWriter

public class JsonHierarchicalStreamDriver
    implements HierarchicalStreamDriver
{

    public JsonHierarchicalStreamDriver()
    {
    }

    @Override
	public HierarchicalStreamReader createReader(InputStream inputstream)
    {
        throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
    }

    @Override
	public HierarchicalStreamReader createReader(Reader reader)
    {
        throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
    }

    @Override
	public HierarchicalStreamWriter createWriter(OutputStream outputstream)
    {
        HierarchicalStreamWriter hierarchicalstreamwriter;
        try
        {
            hierarchicalstreamwriter = createWriter(((new OutputStreamWriter(outputstream, "UTF-8"))));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new StreamException(unsupportedencodingexception);
        }
        return hierarchicalstreamwriter;
    }

    @Override
	public HierarchicalStreamWriter createWriter(Writer writer)
    {
        return new JsonWriter(writer);
    }
}
