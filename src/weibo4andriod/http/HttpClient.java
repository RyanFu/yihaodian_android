// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartBase;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HostParams;
import weibo4andriod.Configuration;
import weibo4andriod.Weibo;
import weibo4andriod.WeiboException;

// Referenced classes of package weibo4andriod.http:
//            BASE64Encoder, PostParameter, OAuth, OAuthToken, 
//            AccessToken, RequestToken, Response, FileType, 
//            ImageItem

public class HttpClient
    implements Serializable
{
    private static class ByteArrayPart extends PartBase
    {

        protected long lengthOfData()
            throws IOException
        {
            return (long)mData.length;
        }

        protected void sendData(OutputStream outputstream)
            throws IOException
        {
            outputstream.write(mData);
        }

        protected void sendDispositionHeader(OutputStream outputstream)
            throws IOException
        {
            super.sendDispositionHeader(outputstream);
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("; filename=\"").append(mName).append("\"");
            outputstream.write(stringbuilder.toString().getBytes());
        }

        private byte mData[];
        private String mName;

        public ByteArrayPart(byte abyte0[], String s, String s1)
            throws IOException
        {
            super(s, s1, "UTF-8", "binary");
            mName = s;
            mData = abyte0;
        }
    }


    public HttpClient()
    {
        retryCount = Configuration.getRetryCount();
        retryIntervalMillis = 1000 * Configuration.getRetryIntervalSecs();
        userId = Configuration.getUser();
        password = Configuration.getPassword();
        proxyHost = Configuration.getProxyHost();
        proxyPort = Configuration.getProxyPort();
        proxyAuthUser = Configuration.getProxyUser();
        proxyAuthPassword = Configuration.getProxyPassword();
        connectionTimeout = Configuration.getConnectionTimeout();
        readTimeout = Configuration.getReadTimeout();
        requestHeaders = new HashMap();
        oauth = null;
        requestTokenURL = (new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/request_token").toString();
        authorizationURL = (new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/authorize").toString();
        authenticationURL = (new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/authenticate").toString();
        accessTokenURL = (new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/access_token").toString();
        oauthToken = null;
        token = null;
        basic = null;
        setUserAgent(null);
        setOAuthConsumer(null, null);
        setRequestHeader("Accept-Encoding", "gzip");
    }

    public HttpClient(String s, String s1)
    {
        this();
        setUserId(s);
        setPassword(s1);
    }

    private void encodeBasicAuthenticationString()
    {
        if(userId != null && password != null)
        {
            basic = (new StringBuilder()).append("Basic ").append(new String((new BASE64Encoder()).encode((new StringBuilder()).append(userId).append(":").append(password).toString().getBytes()))).toString();
            oauth = null;
        }
    }

    public static String encodeParameters(PostParameter apostparameter[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        while(i < apostparameter.length) 
        {
            if(i != 0)
                stringbuffer.append("&");
            try
            {
                stringbuffer.append(URLEncoder.encode(apostparameter[i].name, "UTF-8")).append("=").append(URLEncoder.encode(apostparameter[i].value, "UTF-8"));
            }
            catch(UnsupportedEncodingException unsupportedencodingexception) { }
            i++;
        }
        return stringbuffer.toString();
    }

    private static String getCause(int i)
    {
        String s = null;
        i;
        JVM INSTR lookupswitch 9: default 84
    //                   304: 88
    //                   400: 112
    //                   401: 119
    //                   403: 126
    //                   404: 133
    //                   406: 140
    //                   500: 147
    //                   502: 154
    //                   503: 161;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        s = "";
_L12:
        return (new StringBuilder()).append(i).append(":").append(s).toString();
_L3:
        s = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "Authentication credentials were missing or incorrect.";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "Returned by the Search API when an invalid format is specified in the request.";
        continue; /* Loop/switch isn't completed */
_L8:
        s = "Something is broken.  Please post to the group so the Weibo team can investigate.";
        continue; /* Loop/switch isn't completed */
_L9:
        s = "Weibo is down or being upgraded.";
        continue; /* Loop/switch isn't completed */
_L10:
        s = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
        if(true) goto _L12; else goto _L11
_L11:
    }

    private HttpURLConnection getConnection(String s)
        throws IOException
    {
        HttpURLConnection httpurlconnection;
        if(proxyHost != null && !proxyHost.equals(""))
        {
            if(proxyAuthUser != null && !proxyAuthUser.equals(""))
            {
                log((new StringBuilder()).append("Proxy AuthUser: ").append(proxyAuthUser).toString());
                log((new StringBuilder()).append("Proxy AuthPassword: ").append(proxyAuthPassword).toString());
                Authenticator.setDefault(new Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        PasswordAuthentication passwordauthentication;
                        if(getRequestorType().equals(java.net.Authenticator.RequestorType.PROXY))
                            passwordauthentication = new PasswordAuthentication(proxyAuthUser, proxyAuthPassword.toCharArray());
                        else
                            passwordauthentication = null;
                        return passwordauthentication;
                    }

                    final HttpClient this$0;

            
            {
                this$0 = HttpClient.this;
                super();
            }
                }
);
            }
            Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, InetSocketAddress.createUnresolved(proxyHost, proxyPort));
            if(DEBUG)
                log((new StringBuilder()).append("Opening proxied connection(").append(proxyHost).append(":").append(proxyPort).append(")").toString());
            httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection(proxy);
        } else
        {
            httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
        }
        if(connectionTimeout > 0 && !isJDK14orEarlier)
            httpurlconnection.setConnectTimeout(connectionTimeout);
        if(readTimeout > 0 && !isJDK14orEarlier)
            httpurlconnection.setReadTimeout(readTimeout);
        return httpurlconnection;
    }

    private static void log(String s)
    {
        if(DEBUG)
            System.out.println((new StringBuilder()).append("[").append(new Date()).append("]").append(s).toString());
    }

    private static void log(String s, String s1)
    {
        if(DEBUG)
            log((new StringBuilder()).append(s).append(s1).toString());
    }

    private void setHeaders(String s, PostParameter apostparameter[], HttpURLConnection httpurlconnection, boolean flag, String s1)
    {
        log("Request: ");
        log((new StringBuilder()).append(s1).append(" ").toString(), s);
        if(flag)
        {
            if(basic == null)
                if(oauth != null);
            Iterator iterator;
            String s2;
            String s3;
            if(oauth != null)
                s3 = oauth.generateAuthorizationHeader(s1, s, apostparameter, oauthToken);
            else
            if(basic != null)
                s3 = basic;
            else
                throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
            httpurlconnection.addRequestProperty("Authorization", s3);
            log((new StringBuilder()).append("Authorization: ").append(s3).toString());
        }
        for(iterator = requestHeaders.keySet().iterator(); iterator.hasNext(); log((new StringBuilder()).append(s2).append(": ").append((String)requestHeaders.get(s2)).toString()))
        {
            s2 = (String)iterator.next();
            httpurlconnection.addRequestProperty(s2, (String)requestHeaders.get(s2));
        }

    }

    public Response delete(String s, boolean flag)
        throws WeiboException
    {
        return httpRequest(s, null, flag, "DELETE");
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof HttpClient))
        {
            flag = false;
        } else
        {
            HttpClient httpclient = (HttpClient)obj;
            if(connectionTimeout != httpclient.connectionTimeout)
                flag = false;
            else
            if(proxyPort != httpclient.proxyPort)
                flag = false;
            else
            if(readTimeout != httpclient.readTimeout)
                flag = false;
            else
            if(retryCount != httpclient.retryCount)
                flag = false;
            else
            if(retryIntervalMillis != httpclient.retryIntervalMillis)
                flag = false;
            else
            if(accessTokenURL == null ? httpclient.accessTokenURL != null : !accessTokenURL.equals(httpclient.accessTokenURL))
                flag = false;
            else
            if(!authenticationURL.equals(httpclient.authenticationURL))
                flag = false;
            else
            if(!authorizationURL.equals(httpclient.authorizationURL))
                flag = false;
            else
            if(basic == null ? httpclient.basic != null : !basic.equals(httpclient.basic))
                flag = false;
            else
            if(oauth == null ? httpclient.oauth != null : !oauth.equals(httpclient.oauth))
                flag = false;
            else
            if(oauthToken == null ? httpclient.oauthToken != null : !oauthToken.equals(httpclient.oauthToken))
                flag = false;
            else
            if(password == null ? httpclient.password != null : !password.equals(httpclient.password))
                flag = false;
            else
            if(proxyAuthPassword == null ? httpclient.proxyAuthPassword != null : !proxyAuthPassword.equals(httpclient.proxyAuthPassword))
                flag = false;
            else
            if(proxyAuthUser == null ? httpclient.proxyAuthUser != null : !proxyAuthUser.equals(httpclient.proxyAuthUser))
                flag = false;
            else
            if(proxyHost == null ? httpclient.proxyHost != null : !proxyHost.equals(httpclient.proxyHost))
                flag = false;
            else
            if(!requestHeaders.equals(httpclient.requestHeaders))
                flag = false;
            else
            if(!requestTokenURL.equals(httpclient.requestTokenURL))
                flag = false;
            else
            if(userId == null ? httpclient.userId != null : !userId.equals(httpclient.userId))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public Response get(String s)
        throws WeiboException
    {
        return httpRequest(s, null, false);
    }

    public Response get(String s, boolean flag)
        throws WeiboException
    {
        return httpRequest(s, null, flag);
    }

    public String getAccessTokenURL()
    {
        return accessTokenURL;
    }

    public String getAuthenticationRL()
    {
        return authenticationURL;
    }

    public String getAuthorizationURL()
    {
        return authorizationURL;
    }

    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }

    public AccessToken getOAuthAccessToken(String s, String s1)
        throws WeiboException
    {
        try
        {
            oauthToken = new OAuthToken(s, s1) {

                final HttpClient this$0;

            
            {
                this$0 = HttpClient.this;
                super(s, s1);
            }
            }
;
            oauthToken = new AccessToken(httpRequest(accessTokenURL, new PostParameter[0], true));
        }
        catch(WeiboException weiboexception)
        {
            throw new WeiboException("The user has not given access to the account.", weiboexception, weiboexception.getStatusCode());
        }
        return (AccessToken)oauthToken;
    }

    public AccessToken getOAuthAccessToken(String s, String s1, String s2)
        throws WeiboException
    {
        try
        {
            oauthToken = new OAuthToken(s, s1) {

                final HttpClient this$0;

            
            {
                this$0 = HttpClient.this;
                super(s, s1);
            }
            }
;
            String s3 = accessTokenURL;
            PostParameter apostparameter[] = new PostParameter[1];
            apostparameter[0] = new PostParameter("oauth_verifier", s2);
            oauthToken = new AccessToken(httpRequest(s3, apostparameter, true));
        }
        catch(WeiboException weiboexception)
        {
            throw new WeiboException("The user has not given access to the account.", weiboexception, weiboexception.getStatusCode());
        }
        return (AccessToken)oauthToken;
    }

    public AccessToken getOAuthAccessToken(RequestToken requesttoken)
        throws WeiboException
    {
        try
        {
            oauthToken = requesttoken;
            oauthToken = new AccessToken(httpRequest(accessTokenURL, new PostParameter[0], true));
        }
        catch(WeiboException weiboexception)
        {
            throw new WeiboException("The user has not given access to the account.", weiboexception, weiboexception.getStatusCode());
        }
        return (AccessToken)oauthToken;
    }

    public AccessToken getOAuthAccessToken(RequestToken requesttoken, String s)
        throws WeiboException
    {
        try
        {
            oauthToken = requesttoken;
            String s1 = accessTokenURL;
            PostParameter apostparameter[] = new PostParameter[1];
            apostparameter[0] = new PostParameter("oauth_verifier", s);
            oauthToken = new AccessToken(httpRequest(s1, apostparameter, true));
        }
        catch(WeiboException weiboexception)
        {
            throw new WeiboException("The user has not given access to the account.", weiboexception, weiboexception.getStatusCode());
        }
        return (AccessToken)oauthToken;
    }

    public RequestToken getOAuthRequestToken()
        throws WeiboException
    {
        oauthToken = new RequestToken(httpRequest(requestTokenURL, null, true), this);
        return (RequestToken)oauthToken;
    }

    public RequestToken getOauthRequestToken(String s)
        throws WeiboException
    {
        String s1 = requestTokenURL;
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("oauth_callback", s);
        oauthToken = new RequestToken(httpRequest(s1, apostparameter, true), this);
        return (RequestToken)oauthToken;
    }

    public String getPassword()
    {
        return password;
    }

    public String getProxyAuthPassword()
    {
        return proxyAuthPassword;
    }

    public String getProxyAuthUser()
    {
        return proxyAuthUser;
    }

    public String getProxyHost()
    {
        return proxyHost;
    }

    public int getProxyPort()
    {
        return proxyPort;
    }

    public int getReadTimeout()
    {
        return readTimeout;
    }

    public String getRequestHeader(String s)
    {
        return (String)requestHeaders.get(s);
    }

    public String getRequestTokenURL()
    {
        return requestTokenURL;
    }

    public String getUserAgent()
    {
        return getRequestHeader("User-Agent");
    }

    public String getUserId()
    {
        return userId;
    }

    public AccessToken getXAuthAccessToken(String s, String s1, String s2)
        throws WeiboException
    {
        String s3 = accessTokenURL;
        PostParameter apostparameter[] = new PostParameter[3];
        apostparameter[0] = new PostParameter("x_auth_username", s);
        apostparameter[1] = new PostParameter("x_auth_password", s1);
        apostparameter[2] = new PostParameter("x_auth_mode", s2);
        oauthToken = new AccessToken(httpRequest(s3, apostparameter, true));
        return (AccessToken)oauthToken;
    }

    public int hashCode()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        if(basic != null)
            i = basic.hashCode();
        else
            i = 0;
        j = 31 * (31 * (i * 31 + retryCount) + retryIntervalMillis);
        if(userId != null)
            k = userId.hashCode();
        else
            k = 0;
        l = 31 * (j + k);
        if(password != null)
            i1 = password.hashCode();
        else
            i1 = 0;
        j1 = 31 * (l + i1);
        if(proxyHost != null)
            k1 = proxyHost.hashCode();
        else
            k1 = 0;
        l1 = 31 * (31 * (j1 + k1) + proxyPort);
        if(proxyAuthUser != null)
            i2 = proxyAuthUser.hashCode();
        else
            i2 = 0;
        j2 = 31 * (l1 + i2);
        if(proxyAuthPassword != null)
            k2 = proxyAuthPassword.hashCode();
        else
            k2 = 0;
        l2 = 31 * (31 * (31 * (31 * (j2 + k2) + connectionTimeout) + readTimeout) + requestHeaders.hashCode());
        if(oauth != null)
            i3 = oauth.hashCode();
        else
            i3 = 0;
        j3 = 31 * (31 * (31 * (31 * (l2 + i3) + requestTokenURL.hashCode()) + authorizationURL.hashCode()) + authenticationURL.hashCode());
        if(accessTokenURL != null)
            k3 = accessTokenURL.hashCode();
        else
            k3 = 0;
        l3 = 31 * (j3 + k3);
        if(oauthToken != null)
            i4 = oauthToken.hashCode();
        else
            i4 = 0;
        return l3 + i4;
    }

    protected Response httpRequest(String s, PostParameter apostparameter[], boolean flag)
        throws WeiboException
    {
        String s1 = "GET";
        PostParameter apostparameter1[];
        if(apostparameter != null)
        {
            s1 = "POST";
            apostparameter1 = new PostParameter[1 + apostparameter.length];
            for(int i = 0; i < apostparameter.length; i++)
                apostparameter1[i] = apostparameter[i];

            apostparameter1[apostparameter.length] = new PostParameter("source", Weibo.CONSUMER_KEY);
        } else
        {
            apostparameter1 = apostparameter;
        }
        return httpRequest(s, apostparameter1, flag, s1);
    }

    public Response httpRequest(String s, PostParameter apostparameter[], boolean flag, String s1)
        throws WeiboException
    {
        int i;
        Response response;
        int j;
        i = 1 + retryCount;
        response = null;
        j = 0;
_L17:
        if(j >= i) goto _L2; else goto _L1
_L1:
        HttpURLConnection httpurlconnection;
        httpurlconnection = getConnection(s);
        httpurlconnection.setDoInput(true);
        setHeaders(s, apostparameter, httpurlconnection, flag, s1);
        if(apostparameter == null && !"POST".equals(s1)) goto _L4; else goto _L3
_L3:
        byte abyte0[];
        OutputStream outputstream1;
        httpurlconnection.setRequestMethod("POST");
        httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpurlconnection.setDoOutput(true);
        String s2 = "";
        if(apostparameter != null)
            s2 = encodeParameters(apostparameter);
        log("Post Params: ", s2);
        abyte0 = s2.getBytes("UTF-8");
        httpurlconnection.setRequestProperty("Content-Length", Integer.toString(abyte0.length));
        outputstream1 = httpurlconnection.getOutputStream();
        OutputStream outputstream = outputstream1;
        outputstream.write(abyte0);
        outputstream.flush();
        outputstream.close();
        OutputStream outputstream2 = outputstream;
_L13:
        Response response5 = new Response(httpurlconnection);
        int j1 = httpurlconnection.getResponseCode();
        if(!DEBUG) goto _L6; else goto _L5
_L5:
        Map map;
        Iterator iterator;
        log("Response: ");
        map = httpurlconnection.getHeaderFields();
        iterator = map.keySet().iterator();
_L9:
        if(!iterator.hasNext()) goto _L6; else goto _L7
_L7:
        String s3;
        Iterator iterator1;
        s3 = (String)iterator.next();
        iterator1 = ((List)map.get(s3)).iterator();
_L12:
        if(!iterator1.hasNext()) goto _L9; else goto _L8
_L8:
        String s4 = (String)iterator1.next();
        if(s3 == null) goto _L11; else goto _L10
_L10:
        log((new StringBuilder()).append(s3).append(": ").append(s4).toString());
          goto _L12
        Exception exception;
        exception;
        int k;
        Response response2;
        outputstream = outputstream2;
        response2 = response5;
        k = j1;
_L21:
        Response response1;
        IOException ioexception;
        int l;
        Response response3;
        Response response4;
        try
        {
            outputstream.close();
        }
        catch(Exception exception1) { }
        throw exception;
        ioexception;
        l = k;
        response3 = response2;
_L20:
        if(j == retryCount)
            throw new WeiboException(ioexception.getMessage(), ioexception, l);
        break MISSING_BLOCK_LABEL_685;
_L4:
label0:
        {
            if(!"DELETE".equals(s1))
                break label0;
            httpurlconnection.setRequestMethod("DELETE");
            outputstream2 = null;
        }
          goto _L13
        httpurlconnection.setRequestMethod("GET");
        outputstream2 = null;
          goto _L13
_L11:
        log(s4);
          goto _L12
_L6:
        if(j1 == 200) goto _L15; else goto _L14
_L14:
        if(j1 < 500 || j == retryCount)
            throw new WeiboException((new StringBuilder()).append(getCause(j1)).append("\n").append(response5.asString()).toString(), j1);
          goto _L16
_L15:
        outputstream2.close();
_L18:
        response1 = response5;
_L22:
        return response1;
_L16:
        outputstream2.close();
_L19:
        response4 = response5;
_L23:
        int i1;
        Exception exception2;
        Exception exception3;
        try
        {
            if(DEBUG && response4 != null)
                response4.asString();
            log((new StringBuilder()).append("Sleeping ").append(retryIntervalMillis).append(" millisecs for next retry.").toString());
            Thread.sleep(retryIntervalMillis);
        }
        catch(InterruptedException interruptedexception) { }
        i1 = j + 1;
        response = response4;
        j = i1;
          goto _L17
        exception2;
          goto _L18
        exception3;
          goto _L19
        ioexception;
        l = j1;
        response3 = response5;
          goto _L20
        exception;
        outputstream = null;
        k = -1;
        response2 = response;
          goto _L21
        exception;
        k = -1;
        response2 = response;
          goto _L21
        exception;
        outputstream = outputstream2;
        response2 = response;
        k = -1;
          goto _L21
        exception;
        outputstream = outputstream2;
        response2 = response5;
        k = -1;
          goto _L21
_L2:
        response1 = response;
          goto _L22
        response4 = response3;
          goto _L23
    }

    public boolean isAuthenticationEnabled()
    {
        boolean flag;
        if(basic != null || oauth != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Response multPartURL(String s, String s1, PostParameter apostparameter[], File file, boolean flag)
        throws WeiboException
    {
        PostMethod postmethod;
        org.apache.commons.httpclient.HttpClient httpclient;
        postmethod = new PostMethod(s1);
        httpclient = new org.apache.commons.httpclient.HttpClient();
        long l;
        ArrayList arraylist;
        l = System.currentTimeMillis();
        Part apart[];
        if(apostparameter == null)
            apart = new Part[1];
        else
            apart = new Part[1 + apostparameter.length];
        if(apostparameter != null)
        {
            int i = apostparameter.length;
            int j = 0;
            for(int k = 0; k < i;)
            {
                PostParameter postparameter = apostparameter[k];
                int i1 = j + 1;
                StringPart stringpart = new StringPart(postparameter.getName(), postparameter.getValue());
                apart[j] = stringpart;
                k++;
                j = i1;
            }

        }
        FilePart filepart = new FilePart(s, file.getName(), file, (new FileType()).getMIMEType(file), "UTF-8");
        filepart.setTransferEncoding("binary");
        apart[apart.length - 1] = filepart;
        postmethod.setRequestEntity(new MultipartRequestEntity(apart, postmethod.getParams()));
        arraylist = new ArrayList();
        if(!flag) goto _L2; else goto _L1
_L1:
        String s2;
        if(basic == null)
            if(oauth != null);
        if(oauth == null)
            break MISSING_BLOCK_LABEL_391;
        s2 = oauth.generateAuthorizationHeader("POST", s1, apostparameter, oauthToken);
_L3:
        arraylist.add(new Header("Authorization", s2));
        log((new StringBuilder()).append("Authorization: ").append(s2).toString());
_L2:
        Response response;
        httpclient.getHostConfiguration().getParams().setParameter("http.default-headers", arraylist);
        httpclient.executeMethod(postmethod);
        response = new Response();
        response.setResponseAsString(postmethod.getResponseBodyAsString());
        response.setStatusCode(postmethod.getStatusCode());
        log((new StringBuilder()).append("multPartURL URL:").append(s1).append(", result:").append(response).append(", time:").append(System.currentTimeMillis() - l).toString());
        postmethod.releaseConnection();
        return response;
        if(basic != null)
            s2 = basic;
        else
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
          goto _L3
        Exception exception1;
        exception1;
        throw new WeiboException(exception1.getMessage(), exception1, -1);
        Exception exception;
        exception;
        postmethod.releaseConnection();
        throw exception;
    }

    public Response multPartURL(String s, PostParameter apostparameter[], ImageItem imageitem, boolean flag)
        throws WeiboException
    {
        PostMethod postmethod = new PostMethod(s);
        org.apache.commons.httpclient.HttpClient httpclient;
        long l;
        ArrayList arraylist;
        httpclient = new org.apache.commons.httpclient.HttpClient();
        l = System.currentTimeMillis();
        Part apart[];
        if(apostparameter == null)
            apart = new Part[1];
        else
            apart = new Part[1 + apostparameter.length];
        if(apostparameter != null)
        {
            int i = apostparameter.length;
            int j = 0;
            for(int k = 0; k < i;)
            {
                PostParameter postparameter = apostparameter[k];
                int i1 = j + 1;
                apart[j] = new StringPart(postparameter.getName(), postparameter.getValue());
                k++;
                j = i1;
            }

            apart[apart.length - 1] = new ByteArrayPart(imageitem.getContent(), imageitem.getName(), imageitem.getImageType());
        }
        postmethod.setRequestEntity(new MultipartRequestEntity(apart, postmethod.getParams()));
        arraylist = new ArrayList();
        if(!flag) goto _L2; else goto _L1
_L1:
        String s1;
        if(basic == null)
            if(oauth != null);
        if(oauth == null)
            break MISSING_BLOCK_LABEL_364;
        s1 = oauth.generateAuthorizationHeader("POST", s, apostparameter, oauthToken);
_L3:
        arraylist.add(new Header("Authorization", s1));
        log((new StringBuilder()).append("Authorization: ").append(s1).toString());
_L2:
        Response response;
        httpclient.getHostConfiguration().getParams().setParameter("http.default-headers", arraylist);
        httpclient.executeMethod(postmethod);
        response = new Response();
        response.setResponseAsString(postmethod.getResponseBodyAsString());
        response.setStatusCode(postmethod.getStatusCode());
        log((new StringBuilder()).append("multPartURL URL:").append(s).append(", result:").append(response).append(", time:").append(System.currentTimeMillis() - l).toString());
        postmethod.releaseConnection();
        return response;
        if(basic != null)
            s1 = basic;
        else
            throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
          goto _L3
        Exception exception1;
        exception1;
        throw new WeiboException(exception1.getMessage(), exception1, -1);
        Exception exception;
        exception;
        postmethod.releaseConnection();
        throw exception;
    }

    public Response post(String s)
        throws WeiboException
    {
        return httpRequest(s, new PostParameter[0], false);
    }

    public Response post(String s, boolean flag)
        throws WeiboException
    {
        return httpRequest(s, new PostParameter[0], flag);
    }

    public Response post(String s, PostParameter apostparameter[])
        throws WeiboException
    {
        return httpRequest(s, apostparameter, false);
    }

    public Response post(String s, PostParameter apostparameter[], boolean flag)
        throws WeiboException
    {
        PostParameter apostparameter1[] = new PostParameter[1 + apostparameter.length];
        for(int i = 0; i < apostparameter.length; i++)
            apostparameter1[i] = apostparameter[i];

        apostparameter1[apostparameter.length] = new PostParameter("source", Weibo.CONSUMER_KEY);
        return httpRequest(s, apostparameter1, flag);
    }

    public void setAccessTokenURL(String s)
    {
        accessTokenURL = s;
    }

    public void setAuthorizationURL(String s)
    {
        authorizationURL = s;
    }

    public void setConnectionTimeout(int i)
    {
        connectionTimeout = Configuration.getConnectionTimeout(i);
    }

    public void setOAuthAccessToken(AccessToken accesstoken)
    {
        oauthToken = accesstoken;
    }

    public void setOAuthConsumer(String s, String s1)
    {
        String s2 = Configuration.getOAuthConsumerKey(s);
        String s3 = Configuration.getOAuthConsumerSecret(s1);
        if(s2 != null && s3 != null && s2.length() != 0 && s3.length() != 0)
            oauth = new OAuth(s2, s3);
    }

    public void setPassword(String s)
    {
        password = s;
        encodeBasicAuthenticationString();
    }

    public void setProxyAuthPassword(String s)
    {
        proxyAuthPassword = Configuration.getProxyPassword(s);
    }

    public void setProxyAuthUser(String s)
    {
        proxyAuthUser = Configuration.getProxyUser(s);
    }

    public void setProxyHost(String s)
    {
        proxyHost = Configuration.getProxyHost(s);
    }

    public void setProxyPort(int i)
    {
        proxyPort = Configuration.getProxyPort(i);
    }

    public void setReadTimeout(int i)
    {
        readTimeout = Configuration.getReadTimeout(i);
    }

    public void setRequestHeader(String s, String s1)
    {
        requestHeaders.put(s, s1);
    }

    public void setRequestTokenURL(String s)
    {
        requestTokenURL = s;
    }

    public void setRetryCount(int i)
    {
        if(i >= 0)
        {
            retryCount = Configuration.getRetryCount(i);
            return;
        } else
        {
            throw new IllegalArgumentException("RetryCount cannot be negative.");
        }
    }

    public void setRetryIntervalSecs(int i)
    {
        if(i >= 0)
        {
            retryIntervalMillis = 1000 * Configuration.getRetryIntervalSecs(i);
            return;
        } else
        {
            throw new IllegalArgumentException("RetryInterval cannot be negative.");
        }
    }

    public RequestToken setToken(String s, String s1)
    {
        token = s;
        oauthToken = new RequestToken(s, s1);
        return (RequestToken)oauthToken;
    }

    public void setUserAgent(String s)
    {
        setRequestHeader("User-Agent", Configuration.getUserAgent(s));
    }

    public void setUserId(String s)
    {
        userId = s;
        encodeBasicAuthenticationString();
    }

    private static final int BAD_GATEWAY = 502;
    private static final int BAD_REQUEST = 400;
    private static final boolean DEBUG = false;
    private static final int FORBIDDEN = 403;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int NOT_ACCEPTABLE = 406;
    private static final int NOT_AUTHORIZED = 401;
    private static final int NOT_FOUND = 404;
    private static final int NOT_MODIFIED = 304;
    private static final int OK = 200;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static boolean isJDK14orEarlier = false;
    private static final long serialVersionUID = 0xb36a81e0d31617cL;
    private String accessTokenURL;
    private String authenticationURL;
    private String authorizationURL;
    private String basic;
    private int connectionTimeout;
    private OAuth oauth;
    private OAuthToken oauthToken;
    private String password;
    private String proxyAuthPassword;
    private String proxyAuthUser;
    private String proxyHost;
    private int proxyPort;
    private int readTimeout;
    private Map requestHeaders;
    private String requestTokenURL;
    private int retryCount;
    private int retryIntervalMillis;
    private String token;
    private String userId;

    static 
    {
        DEBUG = Configuration.getDebug();
        isJDK14orEarlier = false;
        String s = System.getProperty("java.specification.version");
        if(s == null) goto _L2; else goto _L1
_L1:
        if(1.5D <= Double.parseDouble(s)) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L5:
        isJDK14orEarlier = flag;
_L2:
        return;
_L4:
        flag = false;
          goto _L5
        AccessControlException accesscontrolexception;
        accesscontrolexception;
        isJDK14orEarlier = true;
          goto _L2
    }


}
