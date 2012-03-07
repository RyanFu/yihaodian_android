// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import java.io.Serializable;
import javax.crypto.spec.SecretKeySpec;
import weibo4andriod.WeiboException;

// Referenced classes of package weibo4andriod.http:
//            Response

abstract class OAuthToken
    implements Serializable
{

    OAuthToken(String s)
    {
        responseStr = null;
        responseStr = s.split("&");
        tokenSecret = getParameter("oauth_token_secret");
        token = getParameter("oauth_token");
    }

    public OAuthToken(String s, String s1)
    {
        responseStr = null;
        token = s;
        tokenSecret = s1;
    }

    OAuthToken(Response response)
        throws WeiboException
    {
        this(response.asString());
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof OAuthToken))
        {
            flag = false;
        } else
        {
            OAuthToken oauthtoken = (OAuthToken)obj;
            if(secretKeySpec == null ? oauthtoken.secretKeySpec != null : !secretKeySpec.equals(oauthtoken.secretKeySpec))
                flag = false;
            else
            if(!token.equals(oauthtoken.token))
                flag = false;
            else
            if(!tokenSecret.equals(oauthtoken.tokenSecret))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public String getParameter(String s)
    {
        String s1 = null;
        String as[] = responseStr;
        int i = as.length;
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    String s2 = as[j];
                    if(!s2.startsWith((new StringBuilder()).append(s).append('=').toString()))
                        break label0;
                    s1 = s2.split("=")[1].trim();
                }
                return s1;
            }
            j++;
        } while(true);
    }

    SecretKeySpec getSecretKeySpec()
    {
        return secretKeySpec;
    }

    public String getToken()
    {
        return token;
    }

    public String getTokenSecret()
    {
        return tokenSecret;
    }

    @Override
	public int hashCode()
    {
        int i = 31 * (31 * token.hashCode() + tokenSecret.hashCode());
        int j;
        if(secretKeySpec != null)
            j = secretKeySpec.hashCode();
        else
            j = 0;
        return i + j;
    }

    void setSecretKeySpec(SecretKeySpec secretkeyspec)
    {
        secretKeySpec = secretkeyspec;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("OAuthToken{token='").append(token).append('\'').append(", tokenSecret='").append(tokenSecret).append('\'').append(", secretKeySpec=").append(secretKeySpec).append('}').toString();
    }

    private static final long serialVersionUID = 0x211c5fbf6dcc863fL;
    String responseStr[];
    private transient SecretKeySpec secretKeySpec;
    private String token;
    private String tokenSecret;
}
