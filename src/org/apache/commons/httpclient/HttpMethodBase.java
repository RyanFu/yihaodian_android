// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import java.util.Collection;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.cookie.MalformedCookieException;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpMethod, HeaderGroup, URIException, URI, 
//            HttpException, ProtocolException, HttpConnection, HttpState, 
//            HttpVersion, Wire, WireLogInputStream, StatusLine, 
//            NameValuePair, Header, ChunkedInputStream, AutoCloseInputStream, 
//            ContentLengthInputStream, HeaderElement, HostConfiguration, HttpHost, 
//            HttpParser, NoHttpResponseException, MethodRetryHandler

public abstract class HttpMethodBase
    implements HttpMethod
{

    public HttpMethodBase()
    {
        requestHeaders = new HeaderGroup();
        statusLine = null;
        responseHeaders = new HeaderGroup();
        responseTrailerHeaders = new HeaderGroup();
        path = null;
        queryString = null;
        responseStream = null;
        responseConnection = null;
        responseBody = null;
        followRedirects = false;
        doAuthentication = true;
        params = new HttpMethodParams();
        hostAuthState = new AuthState();
        proxyAuthState = new AuthState();
        used = false;
        recoverableExceptionCount = 0;
        httphost = null;
        connectionCloseForced = false;
        effectiveVersion = null;
        aborted = false;
        requestSent = false;
        cookiespec = null;
    }

    public HttpMethodBase(String s)
        throws IllegalArgumentException, IllegalStateException
    {
        requestHeaders = new HeaderGroup();
        statusLine = null;
        responseHeaders = new HeaderGroup();
        responseTrailerHeaders = new HeaderGroup();
        path = null;
        queryString = null;
        responseStream = null;
        responseConnection = null;
        responseBody = null;
        followRedirects = false;
        doAuthentication = true;
        params = new HttpMethodParams();
        hostAuthState = new AuthState();
        proxyAuthState = new AuthState();
        used = false;
        recoverableExceptionCount = 0;
        httphost = null;
        connectionCloseForced = false;
        effectiveVersion = null;
        aborted = false;
        requestSent = false;
        cookiespec = null;
        if(s == null)
            break MISSING_BLOCK_LABEL_220;
        if(s.equals(""))
            break MISSING_BLOCK_LABEL_220;
_L1:
        setURI(new URI(s, true));
        return;
        URIException uriexception;
        uriexception;
        throw new IllegalArgumentException("Invalid uri '" + s + "': " + uriexception.getMessage());
        s = "/";
          goto _L1
    }

    private static boolean canResponseHaveBody(int i)
    {
        LOG.trace("enter HttpMethodBase.canResponseHaveBody(int)");
        boolean flag = true;
        if(i >= 100 && i <= 199 || i == 204 || i == 304)
            flag = false;
        return flag;
    }

    private void checkExecuteConditions(HttpState httpstate, HttpConnection httpconnection)
        throws HttpException
    {
        if(httpstate == null)
            throw new IllegalArgumentException("HttpState parameter may not be null");
        if(httpconnection == null)
            throw new IllegalArgumentException("HttpConnection parameter may not be null");
        if(aborted)
            throw new IllegalStateException("Method has been aborted");
        if(!validate())
            throw new ProtocolException("HttpMethodBase object not valid");
        else
            return;
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

    private void ensureConnectionRelease()
    {
        if(responseConnection != null)
        {
            responseConnection.releaseConnection();
            responseConnection = null;
        }
    }

    protected static String generateRequestLine(HttpConnection httpconnection, String s, String s1, String s2, String s3)
    {
        LOG.trace("enter HttpMethodBase.generateRequestLine(HttpConnection, String, String, String, String)");
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(s);
        stringbuffer.append(" ");
        if(!httpconnection.isTransparent())
        {
            Protocol protocol = httpconnection.getProtocol();
            stringbuffer.append(protocol.getScheme().toLowerCase());
            stringbuffer.append("://");
            stringbuffer.append(httpconnection.getHost());
            if(httpconnection.getPort() != -1 && httpconnection.getPort() != protocol.getDefaultPort())
            {
                stringbuffer.append(":");
                stringbuffer.append(httpconnection.getPort());
            }
        }
        if(s1 == null)
        {
            stringbuffer.append("/");
        } else
        {
            if(!httpconnection.isTransparent() && !s1.startsWith("/"))
                stringbuffer.append("/");
            stringbuffer.append(s1);
        }
        if(s2 != null)
        {
            if(s2.indexOf("?") != 0)
                stringbuffer.append("?");
            stringbuffer.append(s2);
        }
        stringbuffer.append(" ");
        stringbuffer.append(s3);
        stringbuffer.append("\r\n");
        return stringbuffer.toString();
    }

    private CookieSpec getCookieSpec(HttpState httpstate)
    {
        if(cookiespec == null)
        {
            int i = httpstate.getCookiePolicy();
            if(i == -1)
                cookiespec = CookiePolicy.getCookieSpec(params.getCookiePolicy());
            else
                cookiespec = CookiePolicy.getSpecByPolicy(i);
            cookiespec.setValidDateFormats((Collection)params.getParameter("http.dateparser.patterns"));
        }
        return cookiespec;
    }

    private String getRequestLine(HttpConnection httpconnection)
    {
        return generateRequestLine(httpconnection, getName(), getPath(), getQueryString(), effectiveVersion.toString());
    }

    private InputStream readResponseBody(HttpConnection httpconnection)
        throws HttpException, IOException
    {
        LOG.trace("enter HttpMethodBase.readResponseBody(HttpConnection)");
        responseBody = null;
        Object obj = httpconnection.getResponseInputStream();
        if(Wire.CONTENT_WIRE.enabled())
        {
            Wire wire = Wire.CONTENT_WIRE;
            WireLogInputStream wireloginputstream = new WireLogInputStream(((InputStream) (obj)), wire);
            obj = wireloginputstream;
        }
        boolean flag = canResponseHaveBody(statusLine.getStatusCode());
        Object obj1 = null;
        Header header = responseHeaders.getFirstHeader("Transfer-Encoding");
        Object obj2;
        Object obj3;
        if(header != null)
        {
            String s1 = header.getValue();
            if(!"chunked".equalsIgnoreCase(s1) && !"identity".equalsIgnoreCase(s1) && LOG.isWarnEnabled())
                LOG.warn("Unsupported transfer encoding: " + s1);
            HeaderElement aheaderelement[] = header.getElements();
            int i = aheaderelement.length;
            _cls1 _lcls1;
            if(i > 0 && "chunked".equalsIgnoreCase(aheaderelement[i - 1].getName()))
            {
                if(httpconnection.isResponseAvailable(httpconnection.getParams().getSoTimeout()))
                {
                    obj1 = new ChunkedInputStream(((InputStream) (obj)), this);
                } else
                {
                    if(getParams().isParameterTrue("http.protocol.strict-transfer-encoding"))
                        throw new ProtocolException("Chunk-encoded body declared but not sent");
                    LOG.warn("Chunk-encoded body missing");
                }
            } else
            {
                LOG.info("Response content is not chunk-encoded");
                setConnectionCloseForced(true);
                obj1 = obj;
            }
        } else
        {
            long l = getResponseContentLength();
            if(l == -1L)
            {
                if(flag && effectiveVersion.greaterEquals(HttpVersion.HTTP_1_1))
                {
                    Header header1 = responseHeaders.getFirstHeader("Connection");
                    String s = null;
                    if(header1 != null)
                        s = header1.getValue();
                    if(!"close".equalsIgnoreCase(s))
                    {
                        LOG.info("Response content length is not known");
                        setConnectionCloseForced(true);
                    }
                }
                obj1 = obj;
            } else
            {
                obj1 = new ContentLengthInputStream(((InputStream) (obj)), l);
            }
        }
        if(!flag)
            obj2 = null;
        else
            obj2 = obj1;
        if(obj2 != null)
        {
            _lcls1 = new _cls1();
            obj3 = new AutoCloseInputStream(((InputStream) (obj2)), _lcls1);
        } else
        {
            obj3 = obj2;
        }
        return ((InputStream) (obj3));
    }

    private boolean responseAvailable()
    {
        boolean flag;
        if(responseBody != null || responseStream != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void abort()
    {
        if(!aborted) goto _L2; else goto _L1
_L1:
        return;
_L2:
        aborted = true;
        HttpConnection httpconnection = responseConnection;
        if(httpconnection != null)
            httpconnection.close();
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void addCookieRequestHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.addCookieRequestHeader(HttpState, HttpConnection)");
        Header aheader[] = getRequestHeaderGroup().getHeaders("Cookie");
        int i = 0;
        do
        {
            if(i >= aheader.length)
            {
                CookieSpec cookiespec1 = getCookieSpec(httpstate);
                String s = params.getVirtualHost();
                if(s == null)
                    s = httpconnection.getHost();
                Cookie acookie[] = cookiespec1.match(s, httpconnection.getPort(), getPath(), httpconnection.isSecure(), httpstate.getCookies());
                Header header;
                if(acookie != null && acookie.length > 0)
                    if(getParams().isParameterTrue("http.protocol.single-cookie-header"))
                    {
                        String s2 = cookiespec1.formatCookies(acookie);
                        getRequestHeaderGroup().addHeader(new Header("Cookie", s2, true));
                    } else
                    {
                        int j = 0;
                        while(j < acookie.length) 
                        {
                            String s1 = cookiespec1.formatCookie(acookie[j]);
                            getRequestHeaderGroup().addHeader(new Header("Cookie", s1, true));
                            j++;
                        }
                    }
                return;
            }
            header = aheader[i];
            if(header.isAutogenerated())
                getRequestHeaderGroup().removeHeader(header);
            i++;
        } while(true);
    }

    protected void addHostRequestHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.addHostRequestHeader(HttpState, HttpConnection)");
        String s = params.getVirtualHost();
        int i;
        if(s != null)
            LOG.debug("Using virtual host name: " + s);
        else
            s = httpconnection.getHost();
        i = httpconnection.getPort();
        if(LOG.isDebugEnabled())
            LOG.debug("Adding Host request header");
        if(httpconnection.getProtocol().getDefaultPort() != i)
            s = s + ":" + i;
        setRequestHeader("Host", s);
    }

    protected void addProxyConnectionHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.addProxyConnectionHeader(HttpState, HttpConnection)");
        if(!httpconnection.isTransparent() && getRequestHeader("Proxy-Connection") == null)
            addRequestHeader("Proxy-Connection", "Keep-Alive");
    }

    public void addRequestHeader(String s, String s1)
    {
        addRequestHeader(new Header(s, s1));
    }

    public void addRequestHeader(Header header)
    {
        LOG.trace("HttpMethodBase.addRequestHeader(Header)");
        if(header == null)
            LOG.debug("null header value ignored");
        else
            getRequestHeaderGroup().addHeader(header);
    }

    protected void addRequestHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.addRequestHeaders(HttpState, HttpConnection)");
        addUserAgentRequestHeader(httpstate, httpconnection);
        addHostRequestHeader(httpstate, httpconnection);
        addCookieRequestHeader(httpstate, httpconnection);
        addProxyConnectionHeader(httpstate, httpconnection);
    }

    public void addResponseFooter(Header header)
    {
        getResponseTrailerHeaderGroup().addHeader(header);
    }

    protected void addUserAgentRequestHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.addUserAgentRequestHeaders(HttpState, HttpConnection)");
        if(getRequestHeader("User-Agent") == null)
        {
            String s = (String)getParams().getParameter("http.useragent");
            if(s == null)
                s = "Jakarta Commons-HttpClient";
            setRequestHeader("User-Agent", s);
        }
    }

    protected void checkNotUsed()
        throws IllegalStateException
    {
        if(used)
            throw new IllegalStateException("Already used.");
        else
            return;
    }

    protected void checkUsed()
        throws IllegalStateException
    {
        if(!used)
            throw new IllegalStateException("Not Used.");
        else
            return;
    }

    public int execute(HttpState httpstate, HttpConnection httpconnection)
        throws HttpException, IOException
    {
        LOG.trace("enter HttpMethodBase.execute(HttpState, HttpConnection)");
        responseConnection = httpconnection;
        checkExecuteConditions(httpstate, httpconnection);
        statusLine = null;
        connectionCloseForced = false;
        httpconnection.setLastResponseInputStream(null);
        if(effectiveVersion == null)
            effectiveVersion = params.getVersion();
        writeRequest(httpstate, httpconnection);
        requestSent = true;
        readResponse(httpstate, httpconnection);
        used = true;
        return statusLine.getStatusCode();
    }

    void fakeResponse(StatusLine statusline, HeaderGroup headergroup, InputStream inputstream)
    {
        used = true;
        statusLine = statusline;
        responseHeaders = headergroup;
        responseBody = null;
        responseStream = inputstream;
    }

    public String getAuthenticationRealm()
    {
        return hostAuthState.getRealm();
    }

    protected String getContentCharSet(Header header)
    {
        LOG.trace("enter getContentCharSet( Header contentheader )");
        String s = null;
        if(header != null)
        {
            HeaderElement aheaderelement[] = header.getElements();
            if(aheaderelement.length == 1)
            {
                NameValuePair namevaluepair = aheaderelement[0].getParameterByName("charset");
                if(namevaluepair != null)
                    s = namevaluepair.getValue();
            }
        }
        if(s == null)
        {
            s = getParams().getContentCharset();
            if(LOG.isDebugEnabled())
                LOG.debug("Default charset used: " + s);
        }
        return s;
    }

    public boolean getDoAuthentication()
    {
        return doAuthentication;
    }

    public HttpVersion getEffectiveVersion()
    {
        return effectiveVersion;
    }

    public boolean getFollowRedirects()
    {
        return followRedirects;
    }

    public AuthState getHostAuthState()
    {
        return hostAuthState;
    }

    public HostConfiguration getHostConfiguration()
    {
        HostConfiguration hostconfiguration = new HostConfiguration();
        hostconfiguration.setHost(httphost);
        return hostconfiguration;
    }

    public MethodRetryHandler getMethodRetryHandler()
    {
        return methodRetryHandler;
    }

    public abstract String getName();

    public HttpMethodParams getParams()
    {
        return params;
    }

    public String getPath()
    {
        String s;
        if(path == null || path.equals(""))
            s = "/";
        else
            s = path;
        return s;
    }

    public AuthState getProxyAuthState()
    {
        return proxyAuthState;
    }

    public String getProxyAuthenticationRealm()
    {
        return proxyAuthState.getRealm();
    }

    public String getQueryString()
    {
        return queryString;
    }

    public int getRecoverableExceptionCount()
    {
        return recoverableExceptionCount;
    }

    public String getRequestCharSet()
    {
        return getContentCharSet(getRequestHeader("Content-Type"));
    }

    public Header getRequestHeader(String s)
    {
        Header header;
        if(s == null)
            header = null;
        else
            header = getRequestHeaderGroup().getCondensedHeader(s);
        return header;
    }

    protected HeaderGroup getRequestHeaderGroup()
    {
        return requestHeaders;
    }

    public Header[] getRequestHeaders()
    {
        return getRequestHeaderGroup().getAllHeaders();
    }

    public Header[] getRequestHeaders(String s)
    {
        return getRequestHeaderGroup().getHeaders(s);
    }

    public byte[] getResponseBody()
        throws IOException
    {
        if(responseBody != null) goto _L2; else goto _L1
_L1:
        InputStream inputstream = getResponseBodyAsStream();
        if(inputstream == null) goto _L2; else goto _L3
_L3:
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        int k;
        long l = getResponseContentLength();
        if(l > 0x7fffffffL)
            throw new IOException("Content too large to be buffered: " + l + " bytes");
        int i = getParams().getIntParameter("http.method.response.buffer.warnlimit", 0x100000);
        if(l == -1L || l > (long)i)
            LOG.warn("Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.");
        LOG.debug("Buffering response body");
        int j;
        if(l > 0L)
            j = (int)l;
        else
            j = 4096;
        bytearrayoutputstream = new ByteArrayOutputStream(j);
        abyte0 = new byte[4096];
_L7:
        k = inputstream.read(abyte0);
        if(k > 0) goto _L5; else goto _L4
_L4:
        bytearrayoutputstream.close();
        setResponseStream(null);
        responseBody = bytearrayoutputstream.toByteArray();
_L2:
        return responseBody;
_L5:
        bytearrayoutputstream.write(abyte0, 0, k);
        if(true) goto _L7; else goto _L6
_L6:
    }

    public InputStream getResponseBodyAsStream()
        throws IOException
    {
        Object obj;
        if(responseStream != null)
            obj = responseStream;
        else
        if(responseBody != null)
        {
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(responseBody);
            LOG.debug("re-creating response stream from byte array");
            obj = bytearrayinputstream;
        } else
        {
            obj = null;
        }
        return ((InputStream) (obj));
    }

    public String getResponseBodyAsString()
        throws IOException
    {
        byte abyte0[] = null;
        if(responseAvailable())
            abyte0 = getResponseBody();
        String s;
        if(abyte0 != null)
            s = EncodingUtil.getString(abyte0, getResponseCharSet());
        else
            s = null;
        return s;
    }

    public String getResponseCharSet()
    {
        return getContentCharSet(getResponseHeader("Content-Type"));
    }

    public long getResponseContentLength()
    {
        Header aheader[] = getResponseHeaderGroup().getHeaders("Content-Length");
        if(aheader.length != 0) goto _L2; else goto _L1
_L1:
        long l1 = -1L;
_L3:
        return l1;
_L2:
        int i;
        if(aheader.length > 1)
            LOG.warn("Multiple content-length headers detected");
        i = aheader.length - 1;
_L4:
label0:
        {
            if(i >= 0)
                break label0;
            l1 = -1L;
        }
          goto _L3
        Header header = aheader[i];
        long l = Long.parseLong(header.getValue());
        l1 = l;
          goto _L3
        NumberFormatException numberformatexception;
        numberformatexception;
        if(LOG.isWarnEnabled())
            LOG.warn("Invalid content-length value: " + numberformatexception.getMessage());
        i--;
          goto _L4
    }

    public Header getResponseFooter(String s)
    {
        Header header;
        if(s == null)
            header = null;
        else
            header = getResponseTrailerHeaderGroup().getCondensedHeader(s);
        return header;
    }

    public Header[] getResponseFooters()
    {
        return getResponseTrailerHeaderGroup().getAllHeaders();
    }

    public Header getResponseHeader(String s)
    {
        Header header;
        if(s == null)
            header = null;
        else
            header = getResponseHeaderGroup().getCondensedHeader(s);
        return header;
    }

    protected HeaderGroup getResponseHeaderGroup()
    {
        return responseHeaders;
    }

    public Header[] getResponseHeaders()
    {
        return getResponseHeaderGroup().getAllHeaders();
    }

    public Header[] getResponseHeaders(String s)
    {
        return getResponseHeaderGroup().getHeaders(s);
    }

    protected InputStream getResponseStream()
    {
        return responseStream;
    }

    protected HeaderGroup getResponseTrailerHeaderGroup()
    {
        return responseTrailerHeaders;
    }

    public int getStatusCode()
    {
        return statusLine.getStatusCode();
    }

    public StatusLine getStatusLine()
    {
        return statusLine;
    }

    public String getStatusText()
    {
        return statusLine.getReasonPhrase();
    }

    public URI getURI()
        throws URIException
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(httphost != null)
        {
            stringbuffer.append(httphost.getProtocol().getScheme());
            stringbuffer.append("://");
            stringbuffer.append(httphost.getHostName());
            int i = httphost.getPort();
            if(i != -1 && i != httphost.getProtocol().getDefaultPort())
            {
                stringbuffer.append(":");
                stringbuffer.append(i);
            }
        }
        stringbuffer.append(path);
        if(queryString != null)
        {
            stringbuffer.append('?');
            stringbuffer.append(queryString);
        }
        return new URI(stringbuffer.toString(), true);
    }

    public boolean hasBeenUsed()
    {
        return used;
    }

    public boolean isAborted()
    {
        return aborted;
    }

    protected boolean isConnectionCloseForced()
    {
        return connectionCloseForced;
    }

    public boolean isHttp11()
    {
        return params.getVersion().equals(HttpVersion.HTTP_1_1);
    }

    public boolean isRequestSent()
    {
        return requestSent;
    }

    public boolean isStrictMode()
    {
        return false;
    }

    protected void processResponseBody(HttpState httpstate, HttpConnection httpconnection)
    {
    }

    protected void processResponseHeaders(HttpState httpstate, HttpConnection httpconnection)
    {
        Header aheader[];
        CookieSpec cookiespec1;
        String s;
        int i;
        LOG.trace("enter HttpMethodBase.processResponseHeaders(HttpState, HttpConnection)");
        aheader = getResponseHeaderGroup().getHeaders("set-cookie2");
        if(aheader.length == 0)
            aheader = getResponseHeaderGroup().getHeaders("set-cookie");
        cookiespec1 = getCookieSpec(httpstate);
        s = params.getVirtualHost();
        if(s == null)
            s = httpconnection.getHost();
        i = 0;
_L3:
        Header header;
        Cookie acookie[];
        int j = aheader.length;
        if(i >= j)
            return;
        header = aheader[i];
        acookie = null;
        Cookie acookie1[] = cookiespec1.parse(s, httpconnection.getPort(), getPath(), httpconnection.isSecure(), header);
        acookie = acookie1;
_L4:
        if(acookie == null) goto _L2; else goto _L1
_L1:
        int k = 0;
_L5:
        int l = acookie.length;
        if(k < l)
            break MISSING_BLOCK_LABEL_206;
_L2:
        i++;
          goto _L3
        MalformedCookieException malformedcookieexception;
        malformedcookieexception;
        if(LOG.isWarnEnabled())
            LOG.warn("Invalid cookie header: \"" + header.getValue() + "\". " + malformedcookieexception.getMessage());
          goto _L4
        Cookie cookie = acookie[k];
        try
        {
            int i1 = httpconnection.getPort();
            String s1 = getPath();
            boolean flag = httpconnection.isSecure();
            cookiespec1.validate(s, i1, s1, flag, cookie);
            httpstate.addCookie(cookie);
            if(LOG.isDebugEnabled())
                LOG.debug("Cookie accepted: \"" + cookiespec1.formatCookie(cookie) + "\"");
        }
        catch(MalformedCookieException malformedcookieexception1)
        {
            if(LOG.isWarnEnabled())
                LOG.warn("Cookie rejected: \"" + cookiespec1.formatCookie(cookie) + "\". " + malformedcookieexception1.getMessage());
        }
        k++;
          goto _L5
    }

    protected void processStatusLine(HttpState httpstate, HttpConnection httpconnection)
    {
    }

    protected void readResponse(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.readResponse(HttpState, HttpConnection)");
        do
        {
            int i;
            do
            {
                if(statusLine != null)
                {
                    readResponseBody(httpstate, httpconnection);
                    processResponseBody(httpstate, httpconnection);
                    return;
                }
                readStatusLine(httpstate, httpconnection);
                processStatusLine(httpstate, httpconnection);
                readResponseHeaders(httpstate, httpconnection);
                processResponseHeaders(httpstate, httpconnection);
                i = statusLine.getStatusCode();
            } while(i < 100 || i >= 200);
            if(LOG.isInfoEnabled())
                LOG.info("Discarding unexpected response: " + statusLine.toString());
            statusLine = null;
        } while(true);
    }

    protected void readResponseBody(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.readResponseBody(HttpState, HttpConnection)");
        InputStream inputstream = readResponseBody(httpconnection);
        if(inputstream == null)
        {
            responseBodyConsumed();
        } else
        {
            httpconnection.setLastResponseInputStream(inputstream);
            setResponseStream(inputstream);
        }
    }

    protected void readResponseHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        Header aheader[];
        LOG.trace("enter HttpMethodBase.readResponseHeaders(HttpState,HttpConnection)");
        getResponseHeaderGroup().clear();
        aheader = HttpParser.parseHeaders(httpconnection.getResponseInputStream(), getParams().getHttpElementCharset());
        if(!Wire.HEADER_WIRE.enabled()) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i < aheader.length) goto _L3; else goto _L2
_L2:
        getResponseHeaderGroup().setHeaders(aheader);
        return;
_L3:
        Wire.HEADER_WIRE.input(aheader[i].toExternalForm());
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected void readStatusLine(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.readStatusLine(HttpState, HttpConnection)");
        int i = getParams().getIntParameter("http.protocol.status-line-garbage-limit", 0x7fffffff);
        int j = 0;
        do
        {
            String s = httpconnection.readLine(getParams().getHttpElementCharset());
            if(s == null && j == 0)
                throw new NoHttpResponseException("The server " + httpconnection.getHost() + " failed to respond");
            if(Wire.HEADER_WIRE.enabled())
                Wire.HEADER_WIRE.input(s + "\r\n");
            if(s != null && StatusLine.startsWithHTTP(s))
            {
                statusLine = new StatusLine(s);
                String s1 = statusLine.getHttpVersion();
                if(getParams().isParameterFalse("http.protocol.unambiguous-statusline") && s1.equals("HTTP"))
                {
                    getParams().setVersion(HttpVersion.HTTP_1_0);
                    if(LOG.isWarnEnabled())
                        LOG.warn("Ambiguous status line (HTTP protocol version missing):" + statusLine.toString());
                } else
                {
                    effectiveVersion = HttpVersion.parse(s1);
                }
                return;
            }
            if(s == null || j >= i)
                throw new ProtocolException("The server " + httpconnection.getHost() + " failed to respond with a valid HTTP response");
            j++;
        } while(true);
    }

    public void recycle()
    {
        LOG.trace("enter HttpMethodBase.recycle()");
        releaseConnection();
        path = null;
        followRedirects = false;
        doAuthentication = true;
        queryString = null;
        getRequestHeaderGroup().clear();
        getResponseHeaderGroup().clear();
        getResponseTrailerHeaderGroup().clear();
        statusLine = null;
        effectiveVersion = null;
        aborted = false;
        used = false;
        params = new HttpMethodParams();
        responseBody = null;
        recoverableExceptionCount = 0;
        connectionCloseForced = false;
        hostAuthState.invalidate();
        proxyAuthState.invalidate();
        cookiespec = null;
        requestSent = false;
    }

    public void releaseConnection()
    {
        InputStream inputstream = responseStream;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_16;
        Exception exception;
        try
        {
            responseStream.close();
        }
        catch(IOException ioexception) { }
        ensureConnectionRelease();
        return;
        exception;
        ensureConnectionRelease();
        throw exception;
    }

    public void removeRequestHeader(String s)
    {
        Header aheader[] = getRequestHeaderGroup().getHeaders(s);
        int i = 0;
        do
        {
            if(i >= aheader.length)
                return;
            getRequestHeaderGroup().removeHeader(aheader[i]);
            i++;
        } while(true);
    }

    public void removeRequestHeader(Header header)
    {
        if(header != null)
            getRequestHeaderGroup().removeHeader(header);
    }

    protected void responseBodyConsumed()
    {
        responseStream = null;
        if(responseConnection == null) goto _L2; else goto _L1
_L1:
        responseConnection.setLastResponseInputStream(null);
        if(!shouldCloseConnection(responseConnection)) goto _L4; else goto _L3
_L3:
        responseConnection.close();
_L2:
        connectionCloseForced = false;
        ensureConnectionRelease();
        return;
_L4:
        try
        {
            if(responseConnection.isResponseAvailable())
            {
                if(getParams().isParameterTrue("http.protocol.warn-extra-input"))
                    LOG.warn("Extra response data detected - closing connection");
                responseConnection.close();
            }
        }
        catch(IOException ioexception)
        {
            LOG.warn(ioexception.getMessage());
            responseConnection.close();
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void setConnectionCloseForced(boolean flag)
    {
        if(LOG.isDebugEnabled())
            LOG.debug("Force-close connection: " + flag);
        connectionCloseForced = flag;
    }

    public void setDoAuthentication(boolean flag)
    {
        doAuthentication = flag;
    }

    public void setFollowRedirects(boolean flag)
    {
        followRedirects = flag;
    }

    public void setHostConfiguration(HostConfiguration hostconfiguration)
    {
        if(hostconfiguration != null)
            httphost = new HttpHost(hostconfiguration.getHost(), hostconfiguration.getPort(), hostconfiguration.getProtocol());
        else
            httphost = null;
    }

    public void setHttp11(boolean flag)
    {
        if(flag)
            params.setVersion(HttpVersion.HTTP_1_1);
        else
            params.setVersion(HttpVersion.HTTP_1_0);
    }

    public void setMethodRetryHandler(MethodRetryHandler methodretryhandler)
    {
        methodRetryHandler = methodretryhandler;
    }

    public void setParams(HttpMethodParams httpmethodparams)
    {
        if(httpmethodparams == null)
        {
            throw new IllegalArgumentException("Parameters may not be null");
        } else
        {
            params = httpmethodparams;
            return;
        }
    }

    public void setPath(String s)
    {
        path = s;
    }

    public void setQueryString(String s)
    {
        queryString = s;
    }

    public void setQueryString(NameValuePair anamevaluepair[])
    {
        LOG.trace("enter HttpMethodBase.setQueryString(NameValuePair[])");
        queryString = EncodingUtil.formUrlEncode(anamevaluepair, "UTF-8");
    }

    public void setRequestHeader(String s, String s1)
    {
        setRequestHeader(new Header(s, s1));
    }

    public void setRequestHeader(Header header)
    {
        Header aheader[] = getRequestHeaderGroup().getHeaders(header.getName());
        int i = 0;
        do
        {
            if(i >= aheader.length)
            {
                getRequestHeaderGroup().addHeader(header);
                return;
            }
            getRequestHeaderGroup().removeHeader(aheader[i]);
            i++;
        } while(true);
    }

    protected void setResponseStream(InputStream inputstream)
    {
        responseStream = inputstream;
    }

    public void setStrictMode(boolean flag)
    {
        if(flag)
            params.makeStrict();
        else
            params.makeLenient();
    }

    public void setURI(URI uri)
        throws URIException
    {
        if(uri.isAbsoluteURI())
            httphost = new HttpHost(uri);
        String s;
        if(uri.getPath() == null)
            s = "/";
        else
            s = uri.getEscapedPath();
        setPath(s);
        setQueryString(uri.getEscapedQuery());
    }

    protected boolean shouldCloseConnection(HttpConnection httpconnection)
    {
        if(!isConnectionCloseForced()) goto _L2; else goto _L1
_L1:
        boolean flag;
        LOG.debug("Should force-close connection.");
        flag = true;
_L4:
        return flag;
_L2:
        Header header = null;
        if(!httpconnection.isTransparent())
            header = responseHeaders.getFirstHeader("proxy-connection");
        if(header == null)
            header = responseHeaders.getFirstHeader("connection");
        if(header == null)
            header = requestHeaders.getFirstHeader("connection");
        if(header != null)
        {
            if(header.getValue().equalsIgnoreCase("close"))
            {
                if(LOG.isDebugEnabled())
                    LOG.debug("Should close connection in response to directive: " + header.getValue());
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            if(header.getValue().equalsIgnoreCase("keep-alive"))
            {
                if(LOG.isDebugEnabled())
                    LOG.debug("Should NOT close connection in response to directive: " + header.getValue());
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(LOG.isDebugEnabled())
                LOG.debug("Unknown directive: " + header.toExternalForm());
        }
        LOG.debug("Resorting to protocol version default close connection policy");
        if(!effectiveVersion.greaterEquals(HttpVersion.HTTP_1_1))
            break; /* Loop/switch isn't completed */
        if(LOG.isDebugEnabled())
            LOG.debug("Should NOT close connection, using " + effectiveVersion.toString());
_L6:
        flag = effectiveVersion.lessEquals(HttpVersion.HTTP_1_0);
        if(true) goto _L4; else goto _L3
_L3:
        if(!LOG.isDebugEnabled()) goto _L6; else goto _L5
_L5:
        LOG.debug("Should close connection, using " + effectiveVersion.toString());
          goto _L6
    }

    public boolean validate()
    {
        return true;
    }

    protected void writeRequest(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        HttpVersion httpversion;
        String s;
        LOG.trace("enter HttpMethodBase.writeRequest(HttpState, HttpConnection)");
        writeRequestLine(httpstate, httpconnection);
        writeRequestHeaders(httpstate, httpconnection);
        httpconnection.writeLine();
        if(Wire.HEADER_WIRE.enabled())
            Wire.HEADER_WIRE.output("\r\n");
        httpversion = getParams().getVersion();
        Header header = getRequestHeader("Expect");
        s = null;
        if(header != null)
            s = header.getValue();
        if(s == null || s.compareToIgnoreCase("100-continue") != 0) goto _L2; else goto _L1
_L1:
        if(!httpversion.greaterEquals(HttpVersion.HTTP_1_1)) goto _L4; else goto _L3
_L3:
        int i;
        httpconnection.flushRequestOutputStream();
        i = httpconnection.getParams().getSoTimeout();
        httpconnection.setSocketTimeout(3000);
        readStatusLine(httpstate, httpconnection);
        processStatusLine(httpstate, httpconnection);
        readResponseHeaders(httpstate, httpconnection);
        processResponseHeaders(httpstate, httpconnection);
        if(statusLine.getStatusCode() != 100)
            break MISSING_BLOCK_LABEL_259;
        statusLine = null;
        LOG.debug("OK to continue received");
_L5:
        httpconnection.setSocketTimeout(i);
_L2:
        writeRequestBody(httpstate, httpconnection);
        httpconnection.flushRequestOutputStream();
_L6:
        return;
        InterruptedIOException interruptedioexception;
        interruptedioexception;
        if(!ExceptionUtil.isSocketTimeoutException(interruptedioexception))
            throw interruptedioexception;
        break MISSING_BLOCK_LABEL_217;
        Exception exception;
        exception;
        httpconnection.setSocketTimeout(i);
        throw exception;
        removeRequestHeader("Expect");
        LOG.info("100 (continue) read timeout. Resume sending the request");
          goto _L5
_L4:
        removeRequestHeader("Expect");
        LOG.info("'Expect: 100-continue' handshake is only supported by HTTP/1.1 or higher");
          goto _L2
        httpconnection.setSocketTimeout(i);
          goto _L6
    }

    protected boolean writeRequestBody(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        return true;
    }

    protected void writeRequestHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.writeRequestHeaders(HttpState,HttpConnection)");
        addRequestHeaders(httpstate, httpconnection);
        String s = getParams().getHttpElementCharset();
        Header aheader[] = getRequestHeaders();
        int i = 0;
        do
        {
            if(i >= aheader.length)
                return;
            String s1 = aheader[i].toExternalForm();
            if(Wire.HEADER_WIRE.enabled())
                Wire.HEADER_WIRE.output(s1);
            httpconnection.print(s1, s);
            i++;
        } while(true);
    }

    protected void writeRequestLine(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter HttpMethodBase.writeRequestLine(HttpState, HttpConnection)");
        String s = getRequestLine(httpconnection);
        if(Wire.HEADER_WIRE.enabled())
            Wire.HEADER_WIRE.output(s);
        httpconnection.print(s, getParams().getHttpElementCharset());
    }

    private static final int DEFAULT_INITIAL_BUFFER_SIZE = 4096;
    private static final Log LOG;
    private static final int RESPONSE_WAIT_TIME_MS = 3000;
    static Class class$org$apache$commons$httpclient$HttpMethodBase;
    private transient boolean aborted;
    private boolean connectionCloseForced;
    private CookieSpec cookiespec;
    private boolean doAuthentication;
    private HttpVersion effectiveVersion;
    private boolean followRedirects;
    private AuthState hostAuthState;
    private HttpHost httphost;
    private MethodRetryHandler methodRetryHandler;
    private HttpMethodParams params;
    private String path;
    private AuthState proxyAuthState;
    private String queryString;
    private int recoverableExceptionCount;
    private HeaderGroup requestHeaders;
    private boolean requestSent;
    private byte responseBody[];
    private HttpConnection responseConnection;
    private HeaderGroup responseHeaders;
    private InputStream responseStream;
    private HeaderGroup responseTrailerHeaders;
    private StatusLine statusLine;
    private boolean used;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$HttpMethodBase == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpMethodBase");
            class$org$apache$commons$httpclient$HttpMethodBase = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpMethodBase;
        }
        LOG = LogFactory.getLog(class1);
    }

    private class _cls1
        implements ResponseConsumedWatcher
    {

        public void responseConsumed()
        {
            responseBodyConsumed();
        }

        private final HttpMethodBase this$0;

        _cls1()
        {
            this$0 = HttpMethodBase.this;
        }
    }

}
