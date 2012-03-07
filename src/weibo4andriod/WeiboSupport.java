// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import weibo4andriod.http.HttpClient;

// Referenced classes of package weibo4andriod:
//            Configuration

class WeiboSupport
{

    WeiboSupport()
    {
        this(null, null);
    }

    WeiboSupport(String s, String s1)
    {
        http = new HttpClient();
        source = Configuration.getSource();
        USE_SSL = Configuration.useSSL();
        setClientVersion(null);
        setClientURL(null);
        setUserId(s);
        setPassword(s1);
    }

    public void forceUsePost(boolean flag)
    {
    }

    public String getClientURL()
    {
        return http.getRequestHeader("X-Weibo-Client-URL");
    }

    public String getClientVersion()
    {
        return http.getRequestHeader("X-Weibo-Client-Version");
    }

    public String getPassword()
    {
        return http.getPassword();
    }

    public String getSource()
    {
        return source;
    }

    public String getUserAgent()
    {
        return http.getUserAgent();
    }

    public String getUserId()
    {
        return http.getUserId();
    }

    public boolean isUsePostForced()
    {
        return false;
    }

    public void setClientURL(String s)
    {
        setRequestHeader("X-Weibo-Client-URL", Configuration.getClientURL(s));
    }

    public void setClientVersion(String s)
    {
        setRequestHeader("X-Weibo-Client-Version", Configuration.getCilentVersion(s));
    }

    public void setHttpConnectionTimeout(int i)
    {
        http.setConnectionTimeout(i);
    }

    public void setHttpProxy(String s, int i)
    {
        http.setProxyHost(s);
        http.setProxyPort(i);
    }

    public void setHttpProxyAuth(String s, String s1)
    {
        http.setProxyAuthUser(s);
        http.setProxyAuthPassword(s1);
    }

    public void setHttpReadTimeout(int i)
    {
        http.setReadTimeout(i);
    }

    /**
     * @deprecated Method setPassword is deprecated
     */

    @Deprecated
	public void setPassword(String s)
    {
        this;
        JVM INSTR monitorenter ;
        http.setPassword(Configuration.getPassword(s));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setRequestHeader(String s, String s1)
    {
        http.setRequestHeader(s, s1);
    }

    public void setRetryCount(int i)
    {
        http.setRetryCount(i);
    }

    public void setRetryIntervalSecs(int i)
    {
        http.setRetryIntervalSecs(i);
    }

    public void setSource(String s)
    {
        source = Configuration.getSource(s);
        setRequestHeader("X-Weibo-Client", source);
    }

    public void setUserAgent(String s)
    {
        http.setUserAgent(s);
    }

    /**
     * @deprecated Method setUserId is deprecated
     */

    @Deprecated
	public void setUserId(String s)
    {
        this;
        JVM INSTR monitorenter ;
        http.setUserId(Configuration.getUser(s));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected final boolean USE_SSL;
    protected HttpClient http;
    protected String source;
}
