// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package org.apache.commons.httpclient.methods:
//            RequestEntity

public class ByteArrayRequestEntity
    implements RequestEntity
{

    public ByteArrayRequestEntity(byte abyte0[])
    {
        this(abyte0, null);
    }

    public ByteArrayRequestEntity(byte abyte0[], String s)
    {
        if(abyte0 == null)
        {
            throw new IllegalArgumentException("The content cannot be null");
        } else
        {
            content = abyte0;
            contentType = s;
            return;
        }
    }

    public byte[] getContent()
    {
        return content;
    }

    @Override
	public long getContentLength()
    {
        return content.length;
    }

    @Override
	public String getContentType()
    {
        return contentType;
    }

    @Override
	public boolean isRepeatable()
    {
        return true;
    }

    @Override
	public void writeRequest(OutputStream outputstream)
        throws IOException
    {
        outputstream.write(content);
    }

    private byte content[];
    private String contentType;
}
