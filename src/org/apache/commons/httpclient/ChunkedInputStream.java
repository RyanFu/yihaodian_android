// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpException, HttpMethod, HttpParser

public class ChunkedInputStream extends InputStream
{

    public ChunkedInputStream(InputStream inputstream)
        throws IOException
    {
        this(inputstream, null);
    }

    public ChunkedInputStream(InputStream inputstream, HttpMethod httpmethod)
        throws IOException
    {
        bof = true;
        eof = false;
        closed = false;
        method = null;
        if(inputstream == null)
        {
            throw new IllegalArgumentException("InputStream parameter may not be null");
        } else
        {
            in = inputstream;
            method = httpmethod;
            pos = 0;
            return;
        }
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

    static void exhaustInputStream(InputStream inputstream)
        throws IOException
    {
        for(byte abyte0[] = new byte[1024]; inputstream.read(abyte0) >= 0;);
    }

    private static int getChunkSizeFromInputStream(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream;
        int i;
        bytearrayoutputstream = new ByteArrayOutputStream();
        i = 0;
_L13:
        if(i == -1)
        {
            String s = EncodingUtil.getAsciiString(bytearrayoutputstream.toByteArray());
            int k = s.indexOf(';');
            int j;
            String s1;
            int l;
            if(k > 0)
                s1 = s.substring(0, k).trim();
            else
                s1 = s.trim();
            try
            {
                l = Integer.parseInt(s1.trim(), 16);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new IOException("Bad chunk size: " + s1);
            }
            return l;
        }
        j = inputstream.read();
        if(j == -1)
            throw new IOException("chunked stream ended unexpectedly");
        i;
        JVM INSTR tableswitch 0 2: default 116
    //                   0 126
    //                   1 170
    //                   2 192;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new RuntimeException("assertion failed");
_L2:
        j;
        JVM INSTR lookupswitch 2: default 152
    //                   13: 160
    //                   34: 165;
           goto _L5 _L6 _L7
_L5:
        bytearrayoutputstream.write(j);
          goto _L8
_L6:
        i = 1;
          goto _L8
_L7:
        i = 2;
          goto _L5
_L3:
        if(j == 10)
            i = -1;
        else
            throw new IOException("Protocol violation: Unexpected single newline character in chunk size");
          goto _L8
_L4:
        j;
        JVM INSTR lookupswitch 2: default 220
    //                   34: 239
    //                   92: 228;
           goto _L9 _L10 _L11
_L10:
        break; /* Loop/switch isn't completed */
_L9:
        bytearrayoutputstream.write(j);
        break; /* Loop/switch isn't completed */
_L11:
        bytearrayoutputstream.write(inputstream.read());
        break; /* Loop/switch isn't completed */
        i = 0;
        if(true) goto _L9; else goto _L8
_L8:
        if(true) goto _L13; else goto _L12
_L12:
    }

    private void nextChunk()
        throws IOException
    {
        if(!bof)
            readCRLF();
        chunkSize = getChunkSizeFromInputStream(in);
        bof = false;
        pos = 0;
        if(chunkSize == 0)
        {
            eof = true;
            parseTrailerHeaders();
        }
    }

    private void parseTrailerHeaders()
        throws IOException
    {
        Header aheader[];
        int i;
        try
        {
            String s = "US-ASCII";
            if(method != null)
                s = method.getParams().getHttpElementCharset();
            aheader = HttpParser.parseHeaders(in, s);
        }
        catch(HttpException httpexception)
        {
            LOG.error("Error parsing trailer headers", httpexception);
            IOException ioexception = new IOException(httpexception.getMessage());
            ExceptionUtil.initCause(ioexception, httpexception);
            throw ioexception;
        }
        if(method == null) goto _L2; else goto _L1
_L1:
        i = 0;
_L5:
        if(i < aheader.length) goto _L3; else goto _L2
_L2:
        return;
_L3:
        method.addResponseFooter(aheader[i]);
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void readCRLF()
        throws IOException
    {
        int i = in.read();
        int j = in.read();
        if(i != 13 || j != 10)
            throw new IOException("CRLF expected at end of chunk: " + i + "/" + j);
        else
            return;
    }

    @Override
	public void close()
        throws IOException
    {
        if(closed)
            break MISSING_BLOCK_LABEL_28;
        if(!eof)
            exhaustInputStream(this);
        eof = true;
        closed = true;
        return;
        Exception exception;
        exception;
        eof = true;
        closed = true;
        throw exception;
    }

    @Override
	public int read()
        throws IOException
    {
        if(closed)
            throw new IOException("Attempted read from closed stream.");
        if(!eof) goto _L2; else goto _L1
_L1:
        int i = -1;
_L4:
        return i;
_L2:
        if(pos >= chunkSize)
        {
            nextChunk();
            if(eof)
            {
                i = -1;
                continue; /* Loop/switch isn't completed */
            }
        }
        pos = 1 + pos;
        i = in.read();
        if(true) goto _L4; else goto _L3
_L3:
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
        if(!eof) goto _L2; else goto _L1
_L1:
        int i1 = -1;
_L4:
        return i1;
_L2:
        if(pos >= chunkSize)
        {
            nextChunk();
            if(eof)
            {
                i1 = -1;
                continue; /* Loop/switch isn't completed */
            }
        }
        int k = Math.min(j, chunkSize - pos);
        int l = in.read(abyte0, i, k);
        pos = l + pos;
        i1 = l;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$ChunkedInputStream;
    private boolean bof;
    private int chunkSize;
    private boolean closed;
    private boolean eof;
    private InputStream in;
    private HttpMethod method;
    private int pos;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$ChunkedInputStream == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.ChunkedInputStream");
            class$org$apache$commons$httpclient$ChunkedInputStream = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$ChunkedInputStream;
        }
        LOG = LogFactory.getLog(class1);
    }
}
