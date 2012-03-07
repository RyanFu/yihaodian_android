// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import weibo4andriod.Configuration;

// Referenced classes of package weibo4andriod.http:
//            PostParameter, OAuthToken, BASE64Encoder

public class OAuth
    implements Serializable
{

    public OAuth(String s, String s1)
    {
        consumerKey = "";
        setConsumerKey(s);
        setConsumerSecret(s1);
    }

    public static String constructRequestURL(String s)
    {
        int i = s.indexOf("?");
        String s1;
        int j;
        String s2;
        int k;
        if(-1 != i)
            s1 = s.substring(0, i);
        else
            s1 = s;
        j = s1.indexOf("/", 8);
        s2 = s1.substring(0, j).toLowerCase();
        k = s2.indexOf(":", 8);
        if(-1 == k) goto _L2; else goto _L1
_L1:
        if(!s2.startsWith("http://") || !s2.endsWith(":80")) goto _L4; else goto _L3
_L3:
        s2 = s2.substring(0, k);
_L2:
        return (new StringBuilder()).append(s2).append(s1.substring(j)).toString();
_L4:
        if(s2.startsWith("https://") && s2.endsWith(":443"))
            s2 = s2.substring(0, k);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public static String encode(String s)
    {
        String s1 = null;
        String s2 = URLEncoder.encode(s, "UTF-8");
        s1 = s2;
_L2:
        StringBuffer stringbuffer = new StringBuffer(s1.length());
        int i = 0;
        while(i < s1.length()) 
        {
            char c = s1.charAt(i);
            if(c == '*')
                stringbuffer.append("%2A");
            else
            if(c == '+')
                stringbuffer.append("%20");
            else
            if(c == '%' && i + 1 < s1.length() && s1.charAt(i + 1) == '7' && s1.charAt(i + 2) == 'E')
            {
                stringbuffer.append('~');
                i += 2;
            } else
            {
                stringbuffer.append(c);
            }
            i++;
        }
        return stringbuffer.toString();
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String encodeParameters(List list)
    {
        return encodeParameters(list, "&", false);
    }

    public static String encodeParameters(List list, String s, boolean flag)
    {
        StringBuffer stringbuffer = new StringBuffer();
        PostParameter postparameter;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); stringbuffer.append(encode(postparameter.value)))
        {
            postparameter = (PostParameter)iterator.next();
            if(stringbuffer.length() != 0)
            {
                if(flag)
                    stringbuffer.append("\"");
                stringbuffer.append(s);
            }
            stringbuffer.append(encode(postparameter.name)).append("=");
            if(flag)
                stringbuffer.append("\"");
        }

        if(stringbuffer.length() != 0 && flag)
            stringbuffer.append("\"");
        return stringbuffer.toString();
    }

    private void log(String s)
    {
        if(DEBUG)
            System.out.println((new StringBuilder()).append("[").append(new Date()).append("]").append(s).toString());
    }

    private void log(String s, String s1)
    {
        if(DEBUG)
            log((new StringBuilder()).append(s).append(s1).toString());
    }

    public static String normalizeAuthorizationHeaders(List list)
    {
        Collections.sort(list);
        return encodeParameters(list);
    }

    public static String normalizeRequestParameters(List list)
    {
        Collections.sort(list);
        return encodeParameters(list);
    }

    public static String normalizeRequestParameters(PostParameter apostparameter[])
    {
        return normalizeRequestParameters(toParamList(apostparameter));
    }

    private void parseGetParameters(String s, List list)
    {
        int i;
        String as[];
        i = 0;
        int j = s.indexOf("?");
        if(-1 == j)
            break MISSING_BLOCK_LABEL_126;
        as = s.substring(j + 1).split("&");
        UnsupportedEncodingException unsupportedencodingexception;
        for(int k = as.length; i < k; i++)
        {
            String as1[] = as[i].split("=");
            if(as1.length == 2)
                list.add(new PostParameter(URLDecoder.decode(as1[0], "UTF-8"), URLDecoder.decode(as1[1], "UTF-8")));
            else
                list.add(new PostParameter(URLDecoder.decode(as1[0], "UTF-8"), ""));
            break MISSING_BLOCK_LABEL_127;
        }

        break MISSING_BLOCK_LABEL_126;
        unsupportedencodingexception;
    }

    public static List toParamList(PostParameter apostparameter[])
    {
        ArrayList arraylist = new ArrayList(apostparameter.length);
        arraylist.addAll(Arrays.asList(apostparameter));
        return arraylist;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof OAuth))
        {
            flag = false;
        } else
        {
            OAuth oauth = (OAuth)obj;
            if(consumerKey == null ? oauth.consumerKey != null : !consumerKey.equals(oauth.consumerKey))
                flag = false;
            else
            if(consumerSecret == null ? oauth.consumerSecret != null : !consumerSecret.equals(oauth.consumerSecret))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    String generateAuthorizationHeader(String s, String s1, PostParameter apostparameter[], String s2, String s3, OAuthToken oauthtoken)
    {
        PostParameter apostparameter1[];
        ArrayList arraylist;
        ArrayList arraylist1;
        StringBuffer stringbuffer;
        String s4;
        String s5;
        if(apostparameter == null)
            apostparameter1 = new PostParameter[0];
        else
            apostparameter1 = apostparameter;
        arraylist = new ArrayList(5);
        arraylist.add(new PostParameter("oauth_consumer_key", consumerKey));
        arraylist.add(OAUTH_SIGNATURE_METHOD);
        arraylist.add(new PostParameter("oauth_timestamp", s3));
        arraylist.add(new PostParameter("oauth_nonce", s2));
        arraylist.add(new PostParameter("oauth_version", "1.0"));
        if(oauthtoken != null)
            arraylist.add(new PostParameter("oauth_token", oauthtoken.getToken()));
        arraylist1 = new ArrayList(arraylist.size() + apostparameter1.length);
        arraylist1.addAll(arraylist);
        arraylist1.addAll(toParamList(apostparameter1));
        parseGetParameters(s1, arraylist1);
        stringbuffer = (new StringBuffer(s)).append("&").append(encode(constructRequestURL(s1))).append("&");
        stringbuffer.append(encode(normalizeRequestParameters(arraylist1)));
        s4 = stringbuffer.toString();
        log("OAuth base string:", s4);
        s5 = generateSignature(s4, oauthtoken);
        log("OAuth signature:", s5);
        arraylist.add(new PostParameter("oauth_signature", s5));
        return (new StringBuilder()).append("OAuth ").append(encodeParameters(arraylist, ",", true)).toString();
    }

    String generateAuthorizationHeader(String s, String s1, PostParameter apostparameter[], OAuthToken oauthtoken)
    {
        long l = System.currentTimeMillis() / 1000L;
        return generateAuthorizationHeader(s, s1, apostparameter, String.valueOf(l + (long)RAND.nextInt()), String.valueOf(l), oauthtoken);
    }

    String generateSignature(String s)
    {
        return generateSignature(s, null);
    }

    String generateSignature(String s, OAuthToken oauthtoken)
    {
        byte abyte0[] = null;
        Mac mac = Mac.getInstance("HmacSHA1");
        if(oauthtoken != null) goto _L2; else goto _L1
_L1:
        SecretKeySpec secretkeyspec1 = new SecretKeySpec((new StringBuilder()).append(encode(consumerSecret)).append("&").toString().getBytes(), "HmacSHA1");
_L3:
        byte abyte1[];
        mac.init(secretkeyspec1);
        abyte1 = mac.doFinal(s.getBytes());
        abyte0 = abyte1;
_L4:
        return (new BASE64Encoder()).encode(abyte0);
_L2:
        SecretKeySpec secretkeyspec;
        if(oauthtoken.getSecretKeySpec() == null)
            oauthtoken.setSecretKeySpec(new SecretKeySpec((new StringBuilder()).append(encode(consumerSecret)).append("&").append(encode(oauthtoken.getTokenSecret())).toString().getBytes(), "HmacSHA1"));
        secretkeyspec = oauthtoken.getSecretKeySpec();
        secretkeyspec1 = secretkeyspec;
          goto _L3
        InvalidKeyException invalidkeyexception;
        invalidkeyexception;
        invalidkeyexception.printStackTrace();
          goto _L4
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
          goto _L4
    }

    public int hashCode()
    {
        int i;
        int j;
        int k;
        if(consumerKey != null)
            i = consumerKey.hashCode();
        else
            i = 0;
        j = i * 31;
        if(consumerSecret != null)
            k = consumerSecret.hashCode();
        else
            k = 0;
        return j + k;
    }

    public void setConsumerKey(String s)
    {
        String s1;
        if(s != null)
            s1 = s;
        else
            s1 = "";
        consumerKey = s1;
    }

    public void setConsumerSecret(String s)
    {
        String s1;
        if(s != null)
            s1 = s;
        else
            s1 = "";
        consumerSecret = s1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("OAuth{consumerKey='").append(consumerKey).append('\'').append(", consumerSecret='").append(consumerSecret).append('\'').append('}').toString();
    }

    private static final boolean DEBUG = Configuration.getDebug();
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final PostParameter OAUTH_SIGNATURE_METHOD = new PostParameter("oauth_signature_method", "HMAC-SHA1");
    private static Random RAND = new Random();
    static final long serialVersionUID = 0xc3603b1500a84fe6L;
    private String consumerKey;
    private String consumerSecret;

}
