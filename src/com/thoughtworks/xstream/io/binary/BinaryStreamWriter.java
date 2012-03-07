// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.binary;

import com.thoughtworks.xstream.io.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.io.binary:
//            Token

public class BinaryStreamWriter
    implements ExtendedHierarchicalStreamWriter
{
    private class IdRegistry
    {

        public long getId(String s)
        {
            Long long1 = (Long)ids.get(s);
            if(long1 == null)
            {
                long l = 1L + nextId;
                nextId = l;
                long1 = new Long(l);
                ids.put(s, long1);
                write(new MapIdToValue(long1.longValue(), s));
            }
            return long1.longValue();
        }

        private Map ids;
        private long nextId;
        private final BinaryStreamWriter this$0;

        private IdRegistry()
        {
            this$0 = BinaryStreamWriter.this;
            nextId = 0L;
            ids = new HashMap();
        }

    }


    public BinaryStreamWriter(OutputStream outputstream)
    {
        out = new DataOutputStream(outputstream);
    }

    private void write(Token token)
    {
        try
        {
            tokenFormatter.write(out, token);
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        write(new Token.Attribute(idRegistry.getId(s), s1));
    }

    @Override
	public void close()
    {
        try
        {
            out.close();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    @Override
	public void endNode()
    {
        write(new Token.EndNode());
    }

    @Override
	public void flush()
    {
        try
        {
            out.flush();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    @Override
	public void setValue(String s)
    {
        write(new Token.Value(s));
    }

    @Override
	public void startNode(String s)
    {
        write(new Token.StartNode(idRegistry.getId(s)));
    }

    @Override
	public void startNode(String s, Class class1)
    {
        startNode(s);
    }

    @Override
	public HierarchicalStreamWriter underlyingWriter()
    {
        return this;
    }

    private final IdRegistry idRegistry = new IdRegistry();
    private final DataOutputStream out;
    private final Token.Formatter tokenFormatter = new Token.Formatter();

}
