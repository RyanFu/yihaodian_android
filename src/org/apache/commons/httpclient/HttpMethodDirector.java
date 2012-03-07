// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.apache.commons.httpclient.auth.AuthChallengeException;
import org.apache.commons.httpclient.auth.AuthChallengeParser;
import org.apache.commons.httpclient.auth.AuthChallengeProcessor;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.auth.MalformedChallengeException;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpMethod, HttpConnection, HttpState, Header, 
//            HttpException, ConnectMethod, HttpMethodBase, HostConfiguration, 
//            HttpRecoverableException, MethodRetryHandler, HttpMethodRetryHandler, DefaultHttpMethodRetryHandler, 
//            RedirectException, URIException, NameValuePair, URI, 
//            CircularRedirectException, HttpConnectionManager, Credentials

class HttpMethodDirector
{

    public HttpMethodDirector(HttpConnectionManager httpconnectionmanager, HostConfiguration hostconfiguration, HttpClientParams httpclientparams, HttpState httpstate)
    {
        releaseConnection = false;
        authProcessor = null;
        redirectLocations = null;
        connectionManager = httpconnectionmanager;
        hostConfiguration = hostconfiguration;
        params = httpclientparams;
        state = httpstate;
        authProcessor = new AuthChallengeProcessor(params);
    }

    private void applyConnectionParams(HttpMethod httpmethod)
        throws IOException
    {
        int i = 0;
        Object obj = httpmethod.getParams().getParameter("http.socket.timeout");
        if(obj == null)
            obj = conn.getParams().getParameter("http.socket.timeout");
        if(obj != null)
            i = ((Integer)obj).intValue();
        conn.setSocketTimeout(i);
    }

    private void authenticate(HttpMethod httpmethod)
    {
        if(conn.isProxied() && !conn.isSecure())
            authenticateProxy(httpmethod);
        authenticateHost(httpmethod);
_L1:
        return;
        AuthenticationException authenticationexception;
        authenticationexception;
        LOG.error(authenticationexception.getMessage(), authenticationexception);
          goto _L1
    }

    private void authenticateHost(HttpMethod httpmethod)
        throws AuthenticationException
    {
        if(cleanAuthHeaders(httpmethod, "Authorization")) goto _L2; else goto _L1
_L1:
        return;
_L2:
        AuthState authstate = httpmethod.getHostAuthState();
        AuthScheme authscheme = authstate.getAuthScheme();
        if(authscheme != null && (authstate.isAuthRequested() || !authscheme.isConnectionBased()))
        {
            String s = httpmethod.getParams().getVirtualHost();
            if(s == null)
                s = conn.getHost();
            AuthScope authscope = new AuthScope(s, conn.getPort(), authscheme.getRealm(), authscheme.getSchemeName());
            if(LOG.isDebugEnabled())
                LOG.debug("Authenticating with " + authscope);
            Credentials credentials = state.getCredentials(authscope);
            if(credentials != null)
            {
                String s1 = authscheme.authenticate(credentials, httpmethod);
                if(s1 != null)
                    httpmethod.addRequestHeader(new Header("Authorization", s1, true));
            } else
            if(LOG.isWarnEnabled())
            {
                LOG.warn("Required credentials not available for " + authscope);
                if(httpmethod.getHostAuthState().isPreemptive())
                    LOG.warn("Preemptive authentication requested but no default credentials available");
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void authenticateProxy(HttpMethod httpmethod)
        throws AuthenticationException
    {
        if(cleanAuthHeaders(httpmethod, "Proxy-Authorization")) goto _L2; else goto _L1
_L1:
        return;
_L2:
        AuthState authstate = httpmethod.getProxyAuthState();
        AuthScheme authscheme = authstate.getAuthScheme();
        if(authscheme != null && (authstate.isAuthRequested() || !authscheme.isConnectionBased()))
        {
            AuthScope authscope = new AuthScope(conn.getProxyHost(), conn.getProxyPort(), authscheme.getRealm(), authscheme.getSchemeName());
            if(LOG.isDebugEnabled())
                LOG.debug("Authenticating with " + authscope);
            Credentials credentials = state.getProxyCredentials(authscope);
            if(credentials != null)
            {
                String s = authscheme.authenticate(credentials, httpmethod);
                if(s != null)
                    httpmethod.addRequestHeader(new Header("Proxy-Authorization", s, true));
            } else
            if(LOG.isWarnEnabled())
            {
                LOG.warn("Required proxy credentials not available for " + authscope);
                if(httpmethod.getProxyAuthState().isPreemptive())
                    LOG.warn("Preemptive authentication requested but no default proxy credentials available");
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
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

    private boolean cleanAuthHeaders(HttpMethod httpmethod, String s)
    {
        Header aheader[] = httpmethod.getRequestHeaders(s);
        boolean flag = true;
        int i = 0;
        do
        {
            if(i >= aheader.length)
                return flag;
            Header header = aheader[i];
            if(header.isAutogenerated())
                httpmethod.removeRequestHeader(header);
            else
                flag = false;
            i++;
        } while(true);
    }

    private boolean executeConnect()
        throws IOException, HttpException
    {
        connectMethod = new ConnectMethod();
        connectMethod.getParams().setDefaults(hostConfiguration.getParams());
        do
        {
            do
            {
                if(!conn.isOpen())
                    conn.open();
                if(params.isAuthenticationPreemptive() || state.isAuthenticationPreemptive())
                {
                    LOG.debug("Preemptively sending default basic credentials");
                    connectMethod.getProxyAuthState().setPreemptive();
                    connectMethod.getProxyAuthState().setAuthAttempted(true);
                }
                int i;
                boolean flag;
                AuthState authstate;
                boolean flag1;
                try
                {
                    authenticateProxy(connectMethod);
                }
                catch(AuthenticationException authenticationexception)
                {
                    LOG.error(authenticationexception.getMessage(), authenticationexception);
                }
                applyConnectionParams(connectMethod);
                connectMethod.execute(state, conn);
                i = connectMethod.getStatusCode();
                flag = false;
                authstate = connectMethod.getProxyAuthState();
                if(i == 407)
                    flag1 = true;
                else
                    flag1 = false;
                authstate.setAuthRequested(flag1);
                if(authstate.isAuthRequested() && processAuthenticationResponse(connectMethod))
                    flag = true;
                if(!flag)
                {
                    boolean flag2;
                    if(i >= 200 && i < 300)
                    {
                        conn.tunnelCreated();
                        connectMethod = null;
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    return flag2;
                }
            } while(connectMethod.getResponseBodyAsStream() == null);
            connectMethod.getResponseBodyAsStream().close();
        } while(true);
    }

    private void executeWithRetry(HttpMethod httpmethod)
        throws IOException, HttpException
    {
        int i = 0;
_L2:
        i++;
        if(LOG.isTraceEnabled())
            LOG.trace("Attempt number " + i + " to process request");
        if(conn.getParams().isStaleCheckingEnabled())
            conn.closeIfStale();
        if(!conn.isOpen())
        {
            conn.open();
            if(conn.isProxied() && conn.isSecure() && !(httpmethod instanceof ConnectMethod) && !executeConnect())
                break; /* Loop/switch isn't completed */
        }
        applyConnectionParams(httpmethod);
        httpmethod.execute(state, conn);
        break; /* Loop/switch isn't completed */
        HttpException httpexception;
        httpexception;
        IOException ioexception;
        try
        {
            throw httpexception;
        }
        catch(IOException ioexception1)
        {
            if(conn.isOpen())
            {
                LOG.debug("Closing the connection.");
                conn.close();
            }
            releaseConnection = true;
            throw ioexception1;
        }
        catch(RuntimeException runtimeexception)
        {
            if(conn.isOpen)
            {
                LOG.debug("Closing the connection.");
                conn.close();
            }
            releaseConnection = true;
            throw runtimeexception;
        }
        ioexception;
        LOG.debug("Closing the connection.");
        conn.close();
        if(httpmethod instanceof HttpMethodBase)
        {
            MethodRetryHandler methodretryhandler = ((HttpMethodBase)httpmethod).getMethodRetryHandler();
            if(methodretryhandler != null && !methodretryhandler.retryMethod(httpmethod, conn, new HttpRecoverableException(ioexception.getMessage()), i, httpmethod.isRequestSent()))
            {
                LOG.debug("Method retry handler returned false. Automatic recovery will not be attempted");
                throw ioexception;
            }
        }
        Object obj = (HttpMethodRetryHandler)httpmethod.getParams().getParameter("http.method.retry-handler");
        if(obj == null)
            obj = new DefaultHttpMethodRetryHandler();
        if(!((HttpMethodRetryHandler) (obj)).retryMethod(httpmethod, ioexception, i))
        {
            LOG.debug("Method retry handler returned false. Automatic recovery will not be attempted");
            throw ioexception;
        }
        if(LOG.isInfoEnabled())
            LOG.info("I/O exception (" + ioexception.getClass().getName() + ") caught when processing request: " + ioexception.getMessage());
        if(LOG.isDebugEnabled())
            LOG.debug(ioexception.getMessage(), ioexception);
        LOG.info("Retrying request");
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void fakeResponse(HttpMethod httpmethod)
        throws IOException, HttpException
    {
        LOG.debug("CONNECT failed, fake the response for the original method");
        if(httpmethod instanceof HttpMethodBase)
        {
            ((HttpMethodBase)httpmethod).fakeResponse(connectMethod.getStatusLine(), connectMethod.getResponseHeaderGroup(), connectMethod.getResponseBodyAsStream());
            httpmethod.getProxyAuthState().setAuthScheme(connectMethod.getProxyAuthState().getAuthScheme());
            connectMethod = null;
        } else
        {
            releaseConnection = true;
            LOG.warn("Unable to fake response on method as it is not derived from HttpMethodBase.");
        }
    }

    private boolean isAuthenticationNeeded(HttpMethod httpmethod)
    {
        AuthState authstate = httpmethod.getHostAuthState();
        boolean flag;
        AuthState authstate1;
        boolean flag1;
        boolean flag2;
        if(httpmethod.getStatusCode() == 401)
            flag = true;
        else
            flag = false;
        authstate.setAuthRequested(flag);
        authstate1 = httpmethod.getProxyAuthState();
        if(httpmethod.getStatusCode() == 407)
            flag1 = true;
        else
            flag1 = false;
        authstate1.setAuthRequested(flag1);
        if(httpmethod.getHostAuthState().isAuthRequested() || httpmethod.getProxyAuthState().isAuthRequested())
        {
            LOG.debug("Authorization required");
            if(httpmethod.getDoAuthentication())
            {
                flag2 = true;
            } else
            {
                LOG.info("Authentication requested but doAuthentication is disabled");
                flag2 = false;
            }
        } else
        {
            flag2 = false;
        }
        return flag2;
    }

    private boolean isRedirectNeeded(HttpMethod httpmethod)
    {
        httpmethod.getStatusCode();
        JVM INSTR tableswitch 301 307: default 48
    //                   301 52
    //                   302 52
    //                   303 52
    //                   304 48
    //                   305 48
    //                   306 48
    //                   307 52;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L2
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        LOG.debug("Redirect required");
        if(httpmethod.getFollowRedirects())
        {
            flag = true;
        } else
        {
            LOG.info("Redirect requested but followRedirects is disabled");
            flag = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean processAuthenticationResponse(HttpMethod httpmethod)
    {
        LOG.trace("enter HttpMethodBase.processAuthenticationResponse(HttpState, HttpConnection)");
        httpmethod.getStatusCode();
        JVM INSTR lookupswitch 2: default 96
    //                   401: 44
    //                   407: 53;
           goto _L1 _L2 _L3
_L2:
        boolean flag;
        flag = processWWWAuthChallenge(httpmethod);
        break; /* Loop/switch isn't completed */
_L3:
        boolean flag1 = processProxyAuthChallenge(httpmethod);
        flag = flag1;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if(LOG.isErrorEnabled())
            LOG.error(exception.getMessage(), exception);
        flag = false;
        break; /* Loop/switch isn't completed */
_L1:
        flag = false;
        return flag;
    }

    private boolean processProxyAuthChallenge(HttpMethod httpmethod)
        throws MalformedChallengeException, AuthenticationException
    {
        AuthState authstate;
        Map map;
        authstate = httpmethod.getProxyAuthState();
        map = AuthChallengeParser.parseChallenges(httpmethod.getResponseHeaders("Proxy-Authenticate"));
        if(!map.isEmpty()) goto _L2; else goto _L1
_L1:
        boolean flag;
        LOG.debug("Proxy authentication challenge(s) not found");
        flag = false;
_L4:
        return flag;
_L2:
        AuthScheme authscheme = null;
        AuthScheme authscheme1 = authProcessor.processChallenge(authstate, map);
        authscheme = authscheme1;
_L5:
        AuthChallengeException authchallengeexception;
        if(authscheme == null)
        {
            flag = false;
        } else
        {
            AuthScope authscope = new AuthScope(conn.getProxyHost(), conn.getProxyPort(), authscheme.getRealm(), authscheme.getSchemeName());
            if(LOG.isDebugEnabled())
                LOG.debug("Proxy authentication scope: " + authscope);
            if(authstate.isAuthAttempted() && authscheme.isComplete())
            {
                if(promptForProxyCredentials(authscheme, httpmethod.getParams(), authscope) == null)
                {
                    if(LOG.isInfoEnabled())
                        LOG.info("Failure authenticating with " + authscope);
                    flag = false;
                } else
                {
                    flag = true;
                }
            } else
            {
                authstate.setAuthAttempted(true);
                Credentials credentials = state.getProxyCredentials(authscope);
                if(credentials == null)
                    credentials = promptForProxyCredentials(authscheme, httpmethod.getParams(), authscope);
                if(credentials == null)
                {
                    if(LOG.isInfoEnabled())
                        LOG.info("No credentials available for " + authscope);
                    flag = false;
                } else
                {
                    flag = true;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        authchallengeexception;
        if(LOG.isWarnEnabled())
            LOG.warn(authchallengeexception.getMessage());
          goto _L5
    }

    private boolean processRedirectResponse(HttpMethod httpmethod)
        throws RedirectException
    {
        Header header = httpmethod.getResponseHeader("location");
        if(header != null) goto _L2; else goto _L1
_L1:
        boolean flag;
        LOG.error("Received redirect response " + httpmethod.getStatusCode() + " but no location header");
        flag = false;
_L3:
        return flag;
_L2:
        String s;
        s = header.getValue();
        if(LOG.isDebugEnabled())
            LOG.debug("Redirect requested to location '" + s + "'");
        URI uri = new URI(conn.getProtocol().getScheme(), null, conn.getHost(), conn.getPort(), httpmethod.getPath());
        URI uri1 = new URI(s, true);
label0:
        {
            if(!uri1.isRelativeURI())
                break MISSING_BLOCK_LABEL_500;
            if(!params.isParameterTrue("http.protocol.reject-relative-redirect"))
                break label0;
            LOG.warn("Relative redirect location '" + s + "' not allowed");
            flag = false;
        }
          goto _L3
        URI uri2;
        LOG.debug("Redirect URI is not absolute - parsing as relative");
        uri2 = new URI(uri, uri1);
_L7:
        httpmethod.setURI(uri2);
        hostConfiguration.setHost(uri2);
        if(!params.isParameterFalse("http.protocol.allow-circular-redirects")) goto _L5; else goto _L4
_L4:
        if(redirectLocations == null)
            redirectLocations = new HashSet();
        redirectLocations.add(uri);
        if(uri2.hasQuery())
            uri2.setQuery(null);
        if(redirectLocations.contains(uri2))
            throw new CircularRedirectException("Circular redirect to '" + uri2 + "'");
          goto _L5
        URIException uriexception3;
        uriexception3;
_L6:
        LOG.warn("Redirected location '" + s + "' is malformed");
        flag = false;
          goto _L3
        URIException uriexception2;
        uriexception2;
        flag = false;
          goto _L3
_L5:
        if(LOG.isDebugEnabled())
            LOG.debug("Redirecting from '" + uri.getEscapedURI() + "' to '" + uri2.getEscapedURI());
        httpmethod.getHostAuthState().invalidate();
        flag = true;
          goto _L3
        URIException uriexception1;
        uriexception1;
          goto _L6
        URIException uriexception;
        uriexception;
          goto _L6
        uri2 = uri1;
          goto _L7
    }

    private boolean processWWWAuthChallenge(HttpMethod httpmethod)
        throws MalformedChallengeException, AuthenticationException
    {
        AuthState authstate;
        Map map;
        authstate = httpmethod.getHostAuthState();
        map = AuthChallengeParser.parseChallenges(httpmethod.getResponseHeaders("WWW-Authenticate"));
        if(!map.isEmpty()) goto _L2; else goto _L1
_L1:
        boolean flag;
        LOG.debug("Authentication challenge(s) not found");
        flag = false;
_L4:
        return flag;
_L2:
        AuthScheme authscheme = null;
        AuthScheme authscheme1 = authProcessor.processChallenge(authstate, map);
        authscheme = authscheme1;
_L5:
        AuthChallengeException authchallengeexception;
        if(authscheme == null)
        {
            flag = false;
        } else
        {
            String s = httpmethod.getParams().getVirtualHost();
            if(s == null)
                s = conn.getHost();
            AuthScope authscope = new AuthScope(s, conn.getPort(), authscheme.getRealm(), authscheme.getSchemeName());
            if(LOG.isDebugEnabled())
                LOG.debug("Authentication scope: " + authscope);
            if(authstate.isAuthAttempted() && authscheme.isComplete())
            {
                if(promptForCredentials(authscheme, httpmethod.getParams(), authscope) == null)
                {
                    if(LOG.isInfoEnabled())
                        LOG.info("Failure authenticating with " + authscope);
                    flag = false;
                } else
                {
                    flag = true;
                }
            } else
            {
                authstate.setAuthAttempted(true);
                Credentials credentials = state.getCredentials(authscope);
                if(credentials == null)
                    credentials = promptForCredentials(authscheme, httpmethod.getParams(), authscope);
                if(credentials == null)
                {
                    if(LOG.isInfoEnabled())
                        LOG.info("No credentials available for " + authscope);
                    flag = false;
                } else
                {
                    flag = true;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        authchallengeexception;
        if(LOG.isWarnEnabled())
            LOG.warn(authchallengeexception.getMessage());
          goto _L5
    }

    private Credentials promptForCredentials(AuthScheme authscheme, HttpParams httpparams, AuthScope authscope)
    {
        Credentials credentials;
        CredentialsProvider credentialsprovider;
        LOG.debug("Credentials required");
        credentials = null;
        credentialsprovider = (CredentialsProvider)httpparams.getParameter("http.authentication.credential-provider");
        if(credentialsprovider == null)
            break MISSING_BLOCK_LABEL_131;
        Credentials credentials1 = credentialsprovider.getCredentials(authscheme, authscope.getHost(), authscope.getPort(), false);
        credentials = credentials1;
_L1:
        if(credentials != null)
        {
            state.setCredentials(authscope, credentials);
            if(LOG.isDebugEnabled())
                LOG.debug(authscope + " new credentials given");
        }
_L2:
        return credentials;
        CredentialsNotAvailableException credentialsnotavailableexception;
        credentialsnotavailableexception;
        LOG.warn(credentialsnotavailableexception.getMessage());
          goto _L1
        LOG.debug("Credentials provider not available");
          goto _L2
    }

    private Credentials promptForProxyCredentials(AuthScheme authscheme, HttpParams httpparams, AuthScope authscope)
    {
        Credentials credentials;
        CredentialsProvider credentialsprovider;
        LOG.debug("Proxy credentials required");
        credentials = null;
        credentialsprovider = (CredentialsProvider)httpparams.getParameter("http.authentication.credential-provider");
        if(credentialsprovider == null)
            break MISSING_BLOCK_LABEL_131;
        Credentials credentials1 = credentialsprovider.getCredentials(authscheme, authscope.getHost(), authscope.getPort(), true);
        credentials = credentials1;
_L1:
        if(credentials != null)
        {
            state.setProxyCredentials(authscope, credentials);
            if(LOG.isDebugEnabled())
                LOG.debug(authscope + " new credentials given");
        }
_L2:
        return credentials;
        CredentialsNotAvailableException credentialsnotavailableexception;
        credentialsnotavailableexception;
        LOG.warn(credentialsnotavailableexception.getMessage());
          goto _L1
        LOG.debug("Proxy credentials provider not available");
          goto _L2
    }

    public void executeMethod(HttpMethod httpmethod)
        throws IOException, HttpException
    {
        Collection collection;
        if(httpmethod == null)
            throw new IllegalArgumentException("Method may not be null");
        hostConfiguration.getParams().setDefaults(params);
        httpmethod.getParams().setDefaults(hostConfiguration.getParams());
        collection = (Collection)hostConfiguration.getParams().getParameter("http.default-headers");
        if(collection == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = collection.iterator();
_L6:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        int i;
        int j;
        i = params.getIntParameter("http.protocol.max-redirects", 100);
        j = 0;
_L9:
        if(conn != null && !hostConfiguration.hostEquals(conn))
        {
            conn.setLocked(false);
            conn.releaseConnection();
            conn = null;
        }
        if(conn == null)
        {
            conn = connectionManager.getConnectionWithTimeout(hostConfiguration, params.getConnectionManagerTimeout());
            conn.setLocked(true);
            if(params.isAuthenticationPreemptive() || state.isAuthenticationPreemptive())
            {
                LOG.debug("Preemptively sending default basic credentials");
                httpmethod.getHostAuthState().setPreemptive();
                httpmethod.getHostAuthState().setAuthAttempted(true);
                if(conn.isProxied() && !conn.isSecure())
                {
                    httpmethod.getProxyAuthState().setPreemptive();
                    httpmethod.getProxyAuthState().setAuthAttempted(true);
                }
            }
        }
        authenticate(httpmethod);
        executeWithRetry(httpmethod);
        if(connectMethod == null) goto _L5; else goto _L4
_L4:
        fakeResponse(httpmethod);
_L8:
        if(conn != null)
            conn.setLocked(false);
        if((releaseConnection || httpmethod.getResponseBodyAsStream() == null) && conn != null)
            conn.releaseConnection();
        return;
_L3:
        httpmethod.addRequestHeader((Header)iterator.next());
          goto _L6
_L5:
        boolean flag = false;
        if(!isRedirectNeeded(httpmethod) || !processRedirectResponse(httpmethod))
            break MISSING_BLOCK_LABEL_534;
        flag = true;
        if(++j >= i)
        {
            LOG.error("Narrowly avoided an infinite loop in execute");
            throw new RedirectException("Maximum redirects (" + i + ") exceeded");
        }
        break MISSING_BLOCK_LABEL_483;
        Exception exception;
        exception;
        if(conn != null)
            conn.setLocked(false);
        if((releaseConnection || httpmethod.getResponseBodyAsStream() == null) && conn != null)
            conn.releaseConnection();
        throw exception;
        if(LOG.isDebugEnabled())
            LOG.debug("Execute redirect " + j + " of " + i);
        if(isAuthenticationNeeded(httpmethod) && processAuthenticationResponse(httpmethod))
        {
            LOG.debug("Retry authentication");
            flag = true;
        }
        if(!flag) goto _L8; else goto _L7
_L7:
        if(httpmethod.getResponseBodyAsStream() != null)
            httpmethod.getResponseBodyAsStream().close();
          goto _L9
    }

    public HttpConnectionManager getConnectionManager()
    {
        return connectionManager;
    }

    public HostConfiguration getHostConfiguration()
    {
        return hostConfiguration;
    }

    public HttpParams getParams()
    {
        return params;
    }

    public HttpState getState()
    {
        return state;
    }

    private static final Log LOG;
    public static final String PROXY_AUTH_CHALLENGE = "Proxy-Authenticate";
    public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
    public static final String WWW_AUTH_CHALLENGE = "WWW-Authenticate";
    public static final String WWW_AUTH_RESP = "Authorization";
    static Class class$org$apache$commons$httpclient$HttpMethodDirector;
    private AuthChallengeProcessor authProcessor;
    private HttpConnection conn;
    private ConnectMethod connectMethod;
    private HttpConnectionManager connectionManager;
    private HostConfiguration hostConfiguration;
    private HttpClientParams params;
    private Set redirectLocations;
    private boolean releaseConnection;
    private HttpState state;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$HttpMethodDirector == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpMethodDirector");
            class$org$apache$commons$httpclient$HttpMethodDirector = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpMethodDirector;
        }
        LOG = LogFactory.getLog(class1);
    }
}
