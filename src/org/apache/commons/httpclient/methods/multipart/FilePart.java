// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods.multipart;

import java.io.*;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.methods.multipart:
//            PartBase, FilePartSource, PartSource, Part

public class FilePart extends PartBase
{

    public FilePart(String s, File file)
        throws FileNotFoundException
    {
        this(s, ((new FilePartSource(file))), null, null);
    }

    public FilePart(String s, File file, String s1, String s2)
        throws FileNotFoundException
    {
        this(s, ((new FilePartSource(file))), s1, s2);
    }

    public FilePart(String s, String s1, File file)
        throws FileNotFoundException
    {
        this(s, ((new FilePartSource(s1, file))), null, null);
    }

    public FilePart(String s, String s1, File file, String s2, String s3)
        throws FileNotFoundException
    {
        this(s, ((new FilePartSource(s1, file))), s2, s3);
    }

    public FilePart(String s, PartSource partsource)
    {
        this(s, partsource, null, null);
    }

    public FilePart(String s, PartSource partsource, String s1, String s2)
    {
        String s3;
        String s4;
        if(s1 == null)
            s3 = "application/octet-stream";
        else
            s3 = s1;
        if(s2 == null)
            s4 = "ISO-8859-1";
        else
            s4 = s2;
        super(s, s3, s4, "binary");
        if(partsource == null)
        {
            throw new IllegalArgumentException("Source may not be null");
        } else
        {
            source = partsource;
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

    protected PartSource getSource()
    {
        LOG.trace("enter getSource()");
        return source;
    }

    @Override
	protected long lengthOfData()
        throws IOException
    {
        LOG.trace("enter lengthOfData()");
        return source.getLength();
    }

    @Override
	protected void sendData(OutputStream outputstream)
        throws IOException
    {
        LOG.trace("enter sendData(OutputStream out)");
        if(lengthOfData() != 0L) goto _L2; else goto _L1
_L1:
        LOG.debug("No data to send.");
_L5:
        return;
_L2:
        byte abyte0[];
        InputStream inputstream;
        abyte0 = new byte[4096];
        inputstream = source.createInputStream();
_L3:
        int i = inputstream.read(abyte0);
        if(i < 0)
        {
            inputstream.close();
            continue; /* Loop/switch isn't completed */
        }
        outputstream.write(abyte0, 0, i);
          goto _L3
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	protected void sendDispositionHeader(OutputStream outputstream)
        throws IOException
    {
        LOG.trace("enter sendDispositionHeader(OutputStream out)");
        super.sendDispositionHeader(outputstream);
        String s = source.getFileName();
        if(s != null)
        {
            outputstream.write(FILE_NAME_BYTES);
            outputstream.write(Part.QUOTE_BYTES);
            outputstream.write(EncodingUtil.getAsciiBytes(s));
            outputstream.write(Part.QUOTE_BYTES);
        }
    }

    public static final String DEFAULT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    protected static final String FILE_NAME = "; filename=";
    private static final byte FILE_NAME_BYTES[] = EncodingUtil.getAsciiBytes("; filename=");
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$multipart$FilePart;
    private PartSource source;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$multipart$FilePart == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.multipart.FilePart");
            class$org$apache$commons$httpclient$methods$multipart$FilePart = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$multipart$FilePart;
        }
        LOG = LogFactory.getLog(class1);
    }
}
