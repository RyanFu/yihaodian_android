// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods.multipart;

import java.io.*;

// Referenced classes of package org.apache.commons.httpclient.methods.multipart:
//            PartSource

public class FilePartSource
    implements PartSource
{

    public FilePartSource(File file1)
        throws FileNotFoundException
    {
        file = null;
        fileName = null;
        file = file1;
        if(file1 != null)
        {
            if(!file1.isFile())
                throw new FileNotFoundException("File is not a normal file.");
            if(!file1.canRead())
                throw new FileNotFoundException("File is not readable.");
            fileName = file1.getName();
        }
    }

    public FilePartSource(String s, File file1)
        throws FileNotFoundException
    {
        this(file1);
        if(s != null)
            fileName = s;
    }

    @Override
	public InputStream createInputStream()
        throws IOException
    {
        Object obj;
        if(file != null)
            obj = new FileInputStream(file);
        else
            obj = new ByteArrayInputStream(new byte[0]);
        return ((InputStream) (obj));
    }

    @Override
	public String getFileName()
    {
        String s;
        if(fileName == null)
            s = "noname";
        else
            s = fileName;
        return s;
    }

    @Override
	public long getLength()
    {
        long l;
        if(file != null)
            l = file.length();
        else
            l = 0L;
        return l;
    }

    private File file;
    private String fileName;
}
