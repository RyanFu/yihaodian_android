// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import weibo4andriod.WeiboException;

// Referenced classes of package weibo4andriod.http:
//            OAuthToken, HttpClient, Response, AccessToken

public class RequestToken extends OAuthToken
{

    RequestToken(String s, String s1)
    {
        super(s, s1);
    }

    RequestToken(Response response, HttpClient httpclient)
        throws WeiboException
    {
        super(response);
        httpClient = httpclient;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(obj == null || getClass() != obj.getClass())
            flag = false;
        else
        if(!super.equals(obj))
        {
            flag = false;
        } else
        {
            RequestToken requesttoken = (RequestToken)obj;
            if(httpClient == null ? requesttoken.httpClient != null : !httpClient.equals(requesttoken.httpClient))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public AccessToken getAccessToken(String s)
        throws WeiboException
    {
        return httpClient.getOAuthAccessToken(this, s);
    }

    public String getAuthenticationURL()
    {
        return (new StringBuilder()).append(httpClient.getAuthenticationRL()).append("?oauth_token=").append(getToken()).toString();
    }

    public String getAuthorizationURL()
    {
        return (new StringBuilder()).append(httpClient.getAuthorizationURL()).append("?oauth_token=").append(getToken()).toString();
    }

    @Override
	public volatile String getParameter(String s)
    {
        return super.getParameter(s);
    }

    @Override
	public volatile String getToken()
    {
        return super.getToken();
    }

    @Override
	public volatile String getTokenSecret()
    {
        return super.getTokenSecret();
    }

    @Override
	public int hashCode()
    {
        int i = 31 * super.hashCode();
        int j;
        if(httpClient != null)
            j = httpClient.hashCode();
        else
            j = 0;
        return i + j;
    }

    @Override
	public volatile String toString()
    {
        return super.toString();
    }

    private static final long serialVersionUID = 0x8e00b5c8fdac7e00L;
    private HttpClient httpClient;
}
