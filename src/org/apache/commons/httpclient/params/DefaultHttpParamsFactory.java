// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpVersion;

// Referenced classes of package org.apache.commons.httpclient.params:
//            HttpParamsFactory, HttpClientParams, DefaultHttpParams, HttpMethodParams, 
//            HttpParams

public class DefaultHttpParamsFactory
    implements HttpParamsFactory
{

    public DefaultHttpParamsFactory()
    {
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

    protected HttpParams createParams()
    {
        HttpClientParams httpclientparams;
        String s2;
        httpclientparams = new HttpClientParams(null);
        httpclientparams.setParameter("http.useragent", "Jakarta Commons-HttpClient/3.0");
        httpclientparams.setVersion(HttpVersion.HTTP_1_1);
        Class class1;
        ArrayList arraylist;
        String as[];
        String s;
        String s1;
        String s3;
        String s5;
        String s6;
        if(class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
            class$org$apache$commons$httpclient$SimpleHttpConnectionManager = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
        }
        httpclientparams.setConnectionManagerClass(class1);
        httpclientparams.setCookiePolicy("rfc2109");
        httpclientparams.setHttpElementCharset("US-ASCII");
        httpclientparams.setContentCharset("ISO-8859-1");
        httpclientparams.setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
        arraylist = new ArrayList();
        as = new String[14];
        as[0] = "EEE, dd MMM yyyy HH:mm:ss zzz";
        as[1] = "EEEE, dd-MMM-yy HH:mm:ss zzz";
        as[2] = "EEE MMM d HH:mm:ss yyyy";
        as[3] = "EEE, dd-MMM-yyyy HH:mm:ss z";
        as[4] = "EEE, dd-MMM-yyyy HH-mm-ss z";
        as[5] = "EEE, dd MMM yy HH:mm:ss z";
        as[6] = "EEE dd-MMM-yyyy HH:mm:ss z";
        as[7] = "EEE dd MMM yyyy HH:mm:ss z";
        as[8] = "EEE dd-MMM-yyyy HH-mm-ss z";
        as[9] = "EEE dd-MMM-yy HH:mm:ss z";
        as[10] = "EEE dd MMM yy HH:mm:ss z";
        as[11] = "EEE,dd-MMM-yy HH:mm:ss z";
        as[12] = "EEE,dd-MMM-yyyy HH:mm:ss z";
        as[13] = "EEE, dd-MM-yyyy HH:mm:ss z";
        arraylist.addAll(Arrays.asList(as));
        httpclientparams.setParameter("http.dateparser.patterns", arraylist);
        s = null;
        s6 = System.getProperty("httpclient.useragent");
        s = s6;
_L6:
        if(s != null)
            httpclientparams.setParameter("http.useragent", s);
        s1 = null;
        s5 = System.getProperty("httpclient.authentication.preemptive");
        s1 = s5;
_L4:
        if(s1 != null)
        {
            String s4 = s1.trim().toLowerCase();
            if(s4.equals("true"))
                httpclientparams.setParameter("http.authentication.preemptive", Boolean.TRUE);
            else
            if(s4.equals("false"))
                httpclientparams.setParameter("http.authentication.preemptive", Boolean.FALSE);
        }
        s2 = null;
        s3 = System.getProperty("apache.commons.httpclient.cookiespec");
        s2 = s3;
_L2:
        if(s2 != null)
            if("COMPATIBILITY".equalsIgnoreCase(s2))
                httpclientparams.setCookiePolicy("compatibility");
            else
            if("NETSCAPE_DRAFT".equalsIgnoreCase(s2))
                httpclientparams.setCookiePolicy("netscape");
            else
            if("RFC2109".equalsIgnoreCase(s2))
                httpclientparams.setCookiePolicy("rfc2109");
        return httpclientparams;
        SecurityException securityexception2;
        securityexception2;
        if(true) goto _L2; else goto _L1
_L1:
        SecurityException securityexception1;
        securityexception1;
        if(true) goto _L4; else goto _L3
_L3:
        SecurityException securityexception;
        securityexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    /**
     * @deprecated Method getDefaultParams is deprecated
     */

    public HttpParams getDefaultParams()
    {
        this;
        JVM INSTR monitorenter ;
        HttpParams httpparams;
        if(httpParams == null)
            httpParams = createParams();
        httpparams = httpParams;
        this;
        JVM INSTR monitorexit ;
        return httpparams;
        Exception exception;
        exception;
        throw exception;
    }

    static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
    private HttpParams httpParams;
}
