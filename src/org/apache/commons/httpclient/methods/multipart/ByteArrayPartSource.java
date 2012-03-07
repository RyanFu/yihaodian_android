// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods.multipart;

import java.io.*;

// Referenced classes of package org.apache.commons.httpclient.methods.multipart:
//            PartSource

public class ByteArrayPartSource
    implements PartSource
{

    public ByteArrayPartSource(String s, byte abyte0[])
    {
        fileName = s;
        bytes = abyte0;
    }

    @Override
	public InputStream createInputStream()
        throws IOException
    {
        return new ByteArrayInputStream(bytes);
    }

    @Override
	public String getFileName()
    {
        return fileName;
    }

    @Override
	public long getLength()
    {
        return bytes.length;
    }

    private byte bytes[];
    private String fileName;
}
