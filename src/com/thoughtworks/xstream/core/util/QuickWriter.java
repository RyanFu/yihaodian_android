// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import com.thoughtworks.xstream.io.StreamException;
import java.io.IOException;
import java.io.Writer;

public class QuickWriter
{

    public QuickWriter(Writer writer1)
    {
        this(writer1, 1024);
    }

    public QuickWriter(Writer writer1, int i)
    {
        writer = writer1;
        buffer = new char[i];
    }

    private void raw(char ac[])
    {
        try
        {
            writer.write(ac);
            writer.flush();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    public void close()
    {
        try
        {
            writer.write(buffer, 0, pointer);
            pointer = 0;
            writer.close();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    public void flush()
    {
        try
        {
            writer.write(buffer, 0, pointer);
            pointer = 0;
            writer.flush();
            return;
        }
        catch(IOException ioexception)
        {
            throw new StreamException(ioexception);
        }
    }

    public void write(char c)
    {
        if(1 + pointer >= buffer.length)
            flush();
        char ac[] = buffer;
        int i = pointer;
        pointer = i + 1;
        ac[i] = c;
    }

    public void write(String s)
    {
        int i = s.length();
        if(i + pointer < buffer.length) goto _L2; else goto _L1
_L1:
        flush();
        if(i <= buffer.length) goto _L2; else goto _L3
_L3:
        raw(s.toCharArray());
_L5:
        return;
_L2:
        s.getChars(0, i, buffer, pointer);
        pointer = i + pointer;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void write(char ac[])
    {
        int i = ac.length;
        if(i + pointer < buffer.length) goto _L2; else goto _L1
_L1:
        flush();
        if(i <= buffer.length) goto _L2; else goto _L3
_L3:
        raw(ac);
_L5:
        return;
_L2:
        System.arraycopy(ac, 0, buffer, pointer, i);
        pointer = i + pointer;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private char buffer[];
    private int pointer;
    private final Writer writer;
}
