// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import weibo4andriod.WeiboException;

// Referenced classes of package weibo4andriod.http:
//            OAuthToken, Response

public class AccessToken extends OAuthToken
{

    AccessToken(String s)
    {
        super(s);
        screenName = getParameter("screen_name");
        String s1 = getParameter("user_id");
        if(s1 != null)
            userId = Integer.parseInt(s1);
    }

    public AccessToken(String s, String s1)
    {
        super(s, s1);
    }

    AccessToken(Response response)
        throws WeiboException
    {
        this(response.asString());
    }

    @Override
	public volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    @Override
	public volatile String getParameter(String s)
    {
        return super.getParameter(s);
    }

    public String getScreenName()
    {
        return screenName;
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

    public int getUserId()
    {
        return userId;
    }

    @Override
	public volatile int hashCode()
    {
        return super.hashCode();
    }

    @Override
	public volatile String toString()
    {
        return super.toString();
    }

    private static final long serialVersionUID = 0x8c3247a79636c1cdL;
    private String screenName;
    private int userId;
}
