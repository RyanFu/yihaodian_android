// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class XmlHeaderAwareReader extends Reader
{

    public XmlHeaderAwareReader(InputStream inputstream)
        throws UnsupportedEncodingException, IOException
    {
        PushbackInputStream apushbackinputstream[] = new PushbackInputStream[1];
        PushbackInputStream pushbackinputstream;
        Map map;
        if(inputstream instanceof PushbackInputStream)
            pushbackinputstream = (PushbackInputStream)inputstream;
        else
            pushbackinputstream = new PushbackInputStream(inputstream, 64);
        apushbackinputstream[0] = pushbackinputstream;
        map = getHeader(apushbackinputstream);
        version = Double.parseDouble((String)map.get("version"));
        reader = new InputStreamReader(apushbackinputstream[0], (String)map.get("encoding"));
    }

    private Map getHeader(PushbackInputStream apushbackinputstream[])
        throws IOException
    {
        HashMap hashmap = new HashMap();
        hashmap.put("encoding", "utf-8");
        hashmap.put("version", "1.0");
        int i = 0;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(64);
        int j = 0;
        char c = '\0';
        StringBuffer stringbuffer = new StringBuffer();
        StringBuffer stringbuffer1 = new StringBuffer();
        boolean flag = false;
        do
        {
            if(j == -1)
                break;
            j = apushbackinputstream[0].read();
            if(j == -1)
                break;
            bytearrayoutputstream.write(j);
            char c1 = (char)j;
            switch(i)
            {
            case 0: // '\0'
                if(!Character.isWhitespace(c1))
                    if(c1 == '<')
                        i = 1;
                    else
                        j = -1;
                break;

            case 1: // '\001'
                if(!Character.isWhitespace(c1))
                {
                    stringbuffer.append(Character.toLowerCase(c1));
                    if(!"?xml".startsWith(stringbuffer.substring(0)))
                        j = -1;
                } else
                if(stringbuffer.toString().equals("?xml"))
                {
                    i = 2;
                    stringbuffer.setLength(0);
                } else
                {
                    j = -1;
                }
                break;

            case 2: // '\002'
                if(!Character.isWhitespace(c1))
                {
                    if(c1 == '=')
                    {
                        i = 3;
                    } else
                    {
                        char c2 = Character.toLowerCase(c1);
                        if(Character.isLetter(c2))
                            stringbuffer.append(c2);
                        else
                            j = -1;
                    }
                } else
                if(stringbuffer.length() > 0)
                    j = -1;
                break;

            case 3: // '\003'
                if(c == 0)
                {
                    if(c1 == '"' || c1 == '\'')
                        c = c1;
                    else
                        j = -1;
                } else
                if(c1 == '\\' && !flag)
                    flag = true;
                else
                if(c1 == c && !flag)
                {
                    c = '\0';
                    i = 2;
                    hashmap.put(stringbuffer.toString(), stringbuffer1.toString());
                    stringbuffer.setLength(0);
                    stringbuffer1.setLength(0);
                } else
                {
                    flag = false;
                    if(c1 != '\n')
                        stringbuffer1.append(c1);
                    else
                        j = -1;
                }
                break;
            }
        } while(true);
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        int k = abyte0.length;
        do
        {
            int l = k - 1;
            if(k <= 0)
                break;
            byte byte0 = abyte0[l];
            try
            {
                apushbackinputstream[0].unread(byte0);
            }
            catch(IOException ioexception)
            {
                PushbackInputStream pushbackinputstream = apushbackinputstream[0];
                l++;
                apushbackinputstream[0] = new PushbackInputStream(pushbackinputstream, l);
            }
            k = l;
        } while(true);
        return hashmap;
    }

    @Override
	public void close()
        throws IOException
    {
        reader.close();
    }

    @Override
	public boolean equals(Object obj)
    {
        return reader.equals(obj);
    }

    public String getEncoding()
    {
        return reader.getEncoding();
    }

    public double getVersion()
    {
        return version;
    }

    @Override
	public int hashCode()
    {
        return reader.hashCode();
    }

    @Override
	public void mark(int i)
        throws IOException
    {
        reader.mark(i);
    }

    @Override
	public boolean markSupported()
    {
        return reader.markSupported();
    }

    @Override
	public int read()
        throws IOException
    {
        return reader.read();
    }

    @Override
	public int read(char ac[])
        throws IOException
    {
        return reader.read(ac);
    }

    @Override
	public int read(char ac[], int i, int j)
        throws IOException
    {
        return reader.read(ac, i, j);
    }

    @Override
	public boolean ready()
        throws IOException
    {
        return reader.ready();
    }

    @Override
	public void reset()
        throws IOException
    {
        reader.reset();
    }

    @Override
	public long skip(long l)
        throws IOException
    {
        return reader.skip(l);
    }

    @Override
	public String toString()
    {
        return reader.toString();
    }

    private static final String KEY_ENCODING = "encoding";
    private static final String KEY_VERSION = "version";
    private static final int STATE_ATTR_NAME = 2;
    private static final int STATE_ATTR_VALUE = 3;
    private static final int STATE_AWAIT_XML_HEADER = 1;
    private static final int STATE_START = 0;
    private static final String XML_TOKEN = "?xml";
    private final InputStreamReader reader;
    private final double version;
}
