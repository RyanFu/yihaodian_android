// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods.multipart;


// Referenced classes of package org.apache.commons.httpclient.methods.multipart:
//            Part

public abstract class PartBase extends Part
{

    public PartBase(String s, String s1, String s2, String s3)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Name must not be null");
        } else
        {
            name = s;
            contentType = s1;
            charSet = s2;
            transferEncoding = s3;
            return;
        }
    }

    @Override
	public String getCharSet()
    {
        return charSet;
    }

    @Override
	public String getContentType()
    {
        return contentType;
    }

    @Override
	public String getName()
    {
        return name;
    }

    @Override
	public String getTransferEncoding()
    {
        return transferEncoding;
    }

    public void setCharSet(String s)
    {
        charSet = s;
    }

    public void setContentType(String s)
    {
        contentType = s;
    }

    public void setName(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Name must not be null");
        } else
        {
            name = s;
            return;
        }
    }

    public void setTransferEncoding(String s)
    {
        transferEncoding = s;
    }

    private String charSet;
    private String contentType;
    private String name;
    private String transferEncoding;
}
