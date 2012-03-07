// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            ProtocolException

public class HttpVersion
    implements Comparable
{

    public HttpVersion(int i, int j)
    {
        major = 0;
        minor = 0;
        if(i < 0)
            throw new IllegalArgumentException("HTTP major version number may not be negative");
        major = i;
        if(j < 0)
        {
            throw new IllegalArgumentException("HTTP minor version number may not be negative");
        } else
        {
            minor = j;
            return;
        }
    }

    public static HttpVersion parse(String s)
        throws ProtocolException
    {
        if(s == null)
            throw new IllegalArgumentException("String may not be null");
        if(!s.startsWith("HTTP/"))
            throw new ProtocolException("Invalid HTTP version string: " + s);
        int i = "HTTP/".length();
        int j = s.indexOf(".", i);
        if(j == -1)
            throw new ProtocolException("Invalid HTTP version number: " + s);
        int k;
        int l;
        int i1;
        int j1;
        try
        {
            k = Integer.parseInt(s.substring(i, j));
        }
        catch(NumberFormatException numberformatexception)
        {
            throw new ProtocolException("Invalid HTTP major version number: " + s);
        }
        l = j + 1;
        i1 = s.length();
        try
        {
            j1 = Integer.parseInt(s.substring(l, i1));
        }
        catch(NumberFormatException numberformatexception1)
        {
            throw new ProtocolException("Invalid HTTP minor version number: " + s);
        }
        return new HttpVersion(k, j1);
    }

    @Override
	public int compareTo(Object obj)
    {
        return compareTo((HttpVersion)obj);
    }

    public int compareTo(HttpVersion httpversion)
    {
        if(httpversion == null)
            throw new IllegalArgumentException("Version parameter may not be null");
        int i = getMajor() - httpversion.getMajor();
        if(i == 0)
            i = getMinor() - httpversion.getMinor();
        return i;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof HttpVersion))
            flag = false;
        else
            flag = equals((HttpVersion)obj);
        return flag;
    }

    public boolean equals(HttpVersion httpversion)
    {
        boolean flag;
        if(compareTo(httpversion) == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getMajor()
    {
        return major;
    }

    public int getMinor()
    {
        return minor;
    }

    public boolean greaterEquals(HttpVersion httpversion)
    {
        boolean flag;
        if(compareTo(httpversion) >= 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public int hashCode()
    {
        return 0x186a0 * major + minor;
    }

    public boolean lessEquals(HttpVersion httpversion)
    {
        boolean flag;
        if(compareTo(httpversion) <= 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("HTTP/");
        stringbuffer.append(major);
        stringbuffer.append('.');
        stringbuffer.append(minor);
        return stringbuffer.toString();
    }

    public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);
    public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
    public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);
    private int major;
    private int minor;

}
