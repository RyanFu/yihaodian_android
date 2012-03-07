// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;


// Referenced classes of package org.apache.commons.httpclient.params:
//            HttpMethodParams, DefaultHttpParams, HttpParams

public class HttpClientParams extends HttpMethodParams
{

    public HttpClientParams()
    {
    }

    public HttpClientParams(HttpParams httpparams)
    {
        super(httpparams);
    }

    public Class getConnectionManagerClass()
    {
        return (Class)getParameter("http.connection-manager.class");
    }

    public long getConnectionManagerTimeout()
    {
        return getLongParameter("http.connection-manager.timeout", 0L);
    }

    public boolean isAuthenticationPreemptive()
    {
        return getBooleanParameter("http.authentication.preemptive", false);
    }

    @Override
	public void makeLenient()
    {
        super.makeLenient();
        setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.FALSE);
    }

    @Override
	public void makeStrict()
    {
        super.makeStrict();
        setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.TRUE);
    }

    public void setAuthenticationPreemptive(boolean flag)
    {
        setBooleanParameter("http.authentication.preemptive", flag);
    }

    public void setConnectionManagerClass(Class class1)
    {
        setParameter("http.connection-manager.class", class1);
    }

    public void setConnectionManagerTimeout(long l)
    {
        setLongParameter("http.connection-manager.timeout", l);
    }

    public static final String ALLOW_CIRCULAR_REDIRECTS = "http.protocol.allow-circular-redirects";
    public static final String CONNECTION_MANAGER_CLASS = "http.connection-manager.class";
    public static final String CONNECTION_MANAGER_TIMEOUT = "http.connection-manager.timeout";
    public static final String MAX_REDIRECTS = "http.protocol.max-redirects";
    public static final String PREEMPTIVE_AUTHENTICATION = "http.authentication.preemptive";
    private static final String PROTOCOL_STRICTNESS_PARAMETERS[];
    public static final String REJECT_RELATIVE_REDIRECT = "http.protocol.reject-relative-redirect";

    static 
    {
        String as[] = new String[2];
        as[0] = "http.protocol.reject-relative-redirect";
        as[1] = "http.protocol.allow-circular-redirects";
        PROTOCOL_STRICTNESS_PARAMETERS = as;
    }
}
