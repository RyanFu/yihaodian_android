// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;

public class ChunkedOutputStream extends OutputStream
{

    public ChunkedOutputStream(OutputStream outputstream)
        throws IOException
    {
        this(outputstream, 2048);
    }

    public ChunkedOutputStream(OutputStream outputstream, int i)
        throws IOException
    {
        stream = null;
        cachePosition = 0;
        wroteLastChunk = false;
        cache = new byte[i];
        stream = outputstream;
    }

    @Override
	public void close()
        throws IOException
    {
        finish();
        super.close();
    }

    public void finish()
        throws IOException
    {
        if(!wroteLastChunk)
        {
            flushCache();
            writeClosingChunk();
            wroteLastChunk = true;
        }
    }

    @Override
	public void flush()
        throws IOException
    {
        stream.flush();
    }

    protected void flushCache()
        throws IOException
    {
        if(cachePosition > 0)
        {
            byte abyte0[] = EncodingUtil.getAsciiBytes(Integer.toHexString(cachePosition) + "\r\n");
            stream.write(abyte0, 0, abyte0.length);
            stream.write(cache, 0, cachePosition);
            stream.write(ENDCHUNK, 0, ENDCHUNK.length);
            cachePosition = 0;
        }
    }

    protected void flushCacheWithAppend(byte abyte0[], int i, int j)
        throws IOException
    {
        byte abyte1[] = EncodingUtil.getAsciiBytes(Integer.toHexString(j + cachePosition) + "\r\n");
        stream.write(abyte1, 0, abyte1.length);
        stream.write(cache, 0, cachePosition);
        stream.write(abyte0, i, j);
        stream.write(ENDCHUNK, 0, ENDCHUNK.length);
        cachePosition = 0;
    }

    @Override
	public void write(int i)
        throws IOException
    {
        cache[cachePosition] = (byte)i;
        cachePosition = 1 + cachePosition;
        if(cachePosition == cache.length)
            flushCache();
    }

    @Override
	public void write(byte abyte0[])
        throws IOException
    {
        write(abyte0, 0, abyte0.length);
    }

    @Override
	public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        if(j >= cache.length - cachePosition)
        {
            flushCacheWithAppend(abyte0, i, j);
        } else
        {
            System.arraycopy(abyte0, i, cache, cachePosition, j);
            cachePosition = j + cachePosition;
        }
    }

    protected void writeClosingChunk()
        throws IOException
    {
        stream.write(ZERO, 0, ZERO.length);
        stream.write(CRLF, 0, CRLF.length);
        stream.write(ENDCHUNK, 0, ENDCHUNK.length);
    }

    private static final byte CRLF[];
    private static final byte ENDCHUNK[];
    private static final byte ZERO[];
    private byte cache[];
    private int cachePosition;
    private OutputStream stream;
    private boolean wroteLastChunk;

    static 
    {
        byte abyte0[] = new byte[2];
        abyte0[0] = 13;
        abyte0[1] = 10;
        CRLF = abyte0;
        ENDCHUNK = CRLF;
        byte abyte1[] = new byte[1];
        abyte1[0] = 48;
        ZERO = abyte1;
    }
}
