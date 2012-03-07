// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;


// Referenced classes of package org.apache.commons.httpclient.params:
//            DefaultHttpParams, HttpParams

public class HostParams extends DefaultHttpParams
{

    public HostParams()
    {
    }

    public HostParams(HttpParams httpparams)
    {
        super(httpparams);
    }

    public String getVirtualHost()
    {
        return (String)getParameter("http.virtual-host");
    }

    public void setVirtualHost(String s)
    {
        setParameter("http.virtual-host", s);
    }

    public static final String DEFAULT_HEADERS = "http.default-headers";
}
