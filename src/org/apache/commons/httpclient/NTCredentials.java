// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.LangUtils;

// Referenced classes of package org.apache.commons.httpclient:
//            UsernamePasswordCredentials

public class NTCredentials extends UsernamePasswordCredentials
{

    public NTCredentials()
    {
    }

    public NTCredentials(String s, String s1, String s2, String s3)
    {
        super(s, s1);
        if(s3 == null)
            throw new IllegalArgumentException("Domain may not be null");
        domain = s3;
        if(s2 == null)
        {
            throw new IllegalArgumentException("Host may not be null");
        } else
        {
            host = s2;
            return;
        }
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(this == obj)
            flag = true;
        else
        if(super.equals(obj) && (obj instanceof NTCredentials))
        {
            NTCredentials ntcredentials = (NTCredentials)obj;
            if(LangUtils.equals(domain, ntcredentials.domain) && LangUtils.equals(host, ntcredentials.host))
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String getDomain()
    {
        return domain;
    }

    public String getHost()
    {
        return host;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(super.hashCode(), host), domain);
    }

    public void setDomain(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Domain may not be null");
        } else
        {
            domain = s;
            return;
        }
    }

    public void setHost(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Host may not be null");
        } else
        {
            host = s;
            return;
        }
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(super.toString());
        stringbuffer.append("@");
        stringbuffer.append(host);
        stringbuffer.append(".");
        stringbuffer.append(domain);
        return stringbuffer.toString();
    }

    private String domain;
    private String host;
}
