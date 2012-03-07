// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package org.apache.commons.httpclient:
//            ChunkedInputStream

public class ContentLengthInputStream extends InputStream
{

    public ContentLengthInputStream(InputStream inputstream, int i)
    {
        this(inputstream, i);
    }

    public ContentLengthInputStream(InputStream inputstream, long l)
    {
        pos = 0L;
        closed = false;
        wrappedStream = null;
        wrappedStream = inputstream;
        contentLength = l;
    }

    @Override
	public void close()
        throws IOException
    {
        if(closed)
            break MISSING_BLOCK_LABEL_16;
        ChunkedInputStream.exhaustInputStream(this);
        closed = true;
        return;
        Exception exception;
        exception;
        closed = true;
        throw exception;
    }

    @Override
	public int read()
        throws IOException
    {
        if(closed)
            throw new IOException("Attempted read from closed stream.");
        int i;
        if(pos >= contentLength)
        {
            i = -1;
        } else
        {
            pos = 1L + pos;
            i = wrappedStream.read();
        }
        return i;
    }

    @Override
	public int read(byte abyte0[])
        throws IOException
    {
        return read(abyte0, 0, abyte0.length);
    }

    @Override
	public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if(closed)
            throw new IOException("Attempted read from closed stream.");
        int l;
        if(pos >= contentLength)
        {
            l = -1;
        } else
        {
            if(pos + j > contentLength)
                j = (int)(contentLength - pos);
            int k = wrappedStream.read(abyte0, i, j);
            pos = pos + k;
            l = k;
        }
        return l;
    }

    @Override
	public long skip(long l)
        throws IOException
    {
        long l1 = Math.min(l, contentLength - pos);
        long l2 = wrappedStream.skip(l1);
        if(l2 > 0L)
            pos = l2 + pos;
        return l2;
    }

    private boolean closed;
    private long contentLength;
    private long pos;
    private InputStream wrappedStream;
}
