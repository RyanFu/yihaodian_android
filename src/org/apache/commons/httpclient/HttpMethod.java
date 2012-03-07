// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.params.HttpMethodParams;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpException, URIException, Header, HttpState, 
//            HttpConnection, HostConfiguration, StatusLine, URI, 
//            NameValuePair

public interface HttpMethod
{

    public abstract void abort();

    public abstract void addRequestHeader(String s, String s1);

    public abstract void addRequestHeader(Header header);

    public abstract void addResponseFooter(Header header);

    public abstract int execute(HttpState httpstate, HttpConnection httpconnection)
        throws HttpException, IOException;

    public abstract boolean getDoAuthentication();

    public abstract boolean getFollowRedirects();

    public abstract AuthState getHostAuthState();

    public abstract HostConfiguration getHostConfiguration();

    public abstract String getName();

    public abstract HttpMethodParams getParams();

    public abstract String getPath();

    public abstract AuthState getProxyAuthState();

    public abstract String getQueryString();

    public abstract Header getRequestHeader(String s);

    public abstract Header[] getRequestHeaders();

    public abstract Header[] getRequestHeaders(String s);

    public abstract byte[] getResponseBody()
        throws IOException;

    public abstract InputStream getResponseBodyAsStream()
        throws IOException;

    public abstract String getResponseBodyAsString()
        throws IOException;

    public abstract Header getResponseFooter(String s);

    public abstract Header[] getResponseFooters();

    public abstract Header getResponseHeader(String s);

    public abstract Header[] getResponseHeaders();

    public abstract Header[] getResponseHeaders(String s);

    public abstract int getStatusCode();

    public abstract StatusLine getStatusLine();

    public abstract String getStatusText();

    public abstract URI getURI()
        throws URIException;

    public abstract boolean hasBeenUsed();

    public abstract boolean isRequestSent();

    public abstract boolean isStrictMode();

    public abstract void recycle();

    public abstract void releaseConnection();

    public abstract void removeRequestHeader(String s);

    public abstract void removeRequestHeader(Header header);

    public abstract void setDoAuthentication(boolean flag);

    public abstract void setFollowRedirects(boolean flag);

    public abstract void setParams(HttpMethodParams httpmethodparams);

    public abstract void setPath(String s);

    public abstract void setQueryString(String s);

    public abstract void setQueryString(NameValuePair anamevaluepair[]);

    public abstract void setRequestHeader(String s, String s1);

    public abstract void setRequestHeader(Header header);

    public abstract void setStrictMode(boolean flag);

    public abstract void setURI(URI uri)
        throws URIException;

    public abstract boolean validate();
}
