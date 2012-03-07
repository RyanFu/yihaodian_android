// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.*;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.methods:
//            RequestEntity

public class InputStreamRequestEntity
    implements RequestEntity
{

    public InputStreamRequestEntity(InputStream inputstream)
    {
        this(inputstream, ((String) (null)));
    }

    public InputStreamRequestEntity(InputStream inputstream, long l)
    {
        this(inputstream, l, null);
    }

    public InputStreamRequestEntity(InputStream inputstream, long l, String s)
    {
        buffer = null;
        if(inputstream == null)
        {
            throw new IllegalArgumentException("The content cannot be null");
        } else
        {
            content = inputstream;
            contentLength = l;
            contentType = s;
            return;
        }
    }

    public InputStreamRequestEntity(InputStream inputstream, String s)
    {
        this(inputstream, -2L, s);
    }

    private void bufferContent()
    {
_L2:
        return;
        if(buffer != null || content == null) goto _L2; else goto _L1
_L1:
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[4096];
_L3:
        int i;
        i = content.read(abyte0);
        if(i >= 0)
            break MISSING_BLOCK_LABEL_102;
        buffer = bytearrayoutputstream.toByteArray();
        content = null;
        contentLength = buffer.length;
          goto _L2
        IOException ioexception;
        ioexception;
        LOG.error(ioexception.getMessage(), ioexception);
        buffer = null;
        content = null;
        contentLength = 0L;
          goto _L2
        bytearrayoutputstream.write(abyte0, 0, i);
          goto _L3
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

    public InputStream getContent()
    {
        return content;
    }

    @Override
	public long getContentLength()
    {
        if(contentLength == -2L && buffer == null)
            bufferContent();
        return contentLength;
    }

    @Override
	public String getContentType()
    {
        return contentType;
    }

    @Override
	public boolean isRepeatable()
    {
        boolean flag;
        if(buffer != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void writeRequest(OutputStream outputstream)
        throws IOException
    {
        if(content == null) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int i;
        abyte0 = new byte[4096];
        i = 0;
_L7:
        int j = content.read(abyte0);
        if(j >= 0) goto _L4; else goto _L3
_L3:
        return;
_L4:
        outputstream.write(abyte0, 0, j);
        i += j;
        continue; /* Loop/switch isn't completed */
_L2:
        if(buffer == null)
            break; /* Loop/switch isn't completed */
        outputstream.write(buffer);
        if(true) goto _L3; else goto _L5
_L5:
        throw new IllegalStateException("Content must be set before entity is written");
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static final int CONTENT_LENGTH_AUTO = -2;
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$InputStreamRequestEntity;
    private byte buffer[];
    private InputStream content;
    private long contentLength;
    private String contentType;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$InputStreamRequestEntity == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.InputStreamRequestEntity");
            class$org$apache$commons$httpclient$methods$InputStreamRequestEntity = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$InputStreamRequestEntity;
        }
        LOG = LogFactory.getLog(class1);
    }
}
