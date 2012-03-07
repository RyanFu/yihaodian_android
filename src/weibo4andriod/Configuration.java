// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.*;
import java.security.AccessControlException;
import java.util.Properties;

// Referenced classes of package weibo4andriod:
//            Weibo, Version

public class Configuration
{

    public Configuration()
    {
    }

    public static boolean getBoolean(String s)
    {
        return Boolean.valueOf(getProperty(s)).booleanValue();
    }

    public static String getCilentVersion()
    {
        return getProperty("weibo4j.clientVersion");
    }

    public static String getCilentVersion(String s)
    {
        return getProperty("weibo4j.clientVersion", s);
    }

    public static String getClientURL()
    {
        return getProperty("weibo4j.clientURL");
    }

    public static String getClientURL(String s)
    {
        return getProperty("weibo4j.clientURL", s);
    }

    public static int getConnectionTimeout()
    {
        return getIntProperty("weibo4j.http.connectionTimeout");
    }

    public static int getConnectionTimeout(int i)
    {
        return getIntProperty("weibo4j.http.connectionTimeout", i);
    }

    public static boolean getDebug()
    {
        return getBoolean("weibo4j.debug");
    }

    public static int getIntProperty(String s)
    {
        String s1 = getProperty(s);
        int j = Integer.parseInt(s1);
        int i = j;
_L2:
        return i;
        NumberFormatException numberformatexception;
        numberformatexception;
        i = -1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int getIntProperty(String s, int i)
    {
        String s1 = getProperty(s, String.valueOf(i));
        int k = Integer.parseInt(s1);
        int j = k;
_L2:
        return j;
        NumberFormatException numberformatexception;
        numberformatexception;
        j = -1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static long getLongProperty(String s)
    {
        String s1 = getProperty(s);
        long l1 = Long.parseLong(s1);
        long l = l1;
_L2:
        return l;
        NumberFormatException numberformatexception;
        numberformatexception;
        l = -1L;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static int getNumberOfAsyncThreads()
    {
        return getIntProperty("weibo4j.async.numThreads");
    }

    public static String getOAuthConsumerKey()
    {
        return getProperty("weibo4j.oauth.consumerKey");
    }

    public static String getOAuthConsumerKey(String s)
    {
        return getProperty("weibo4j.oauth.consumerKey", s);
    }

    public static String getOAuthConsumerSecret()
    {
        return getProperty("weibo4j.oauth.consumerSecret");
    }

    public static String getOAuthConsumerSecret(String s)
    {
        return getProperty("weibo4j.oauth.consumerSecret", s);
    }

    public static String getPassword()
    {
        return getProperty("weibo4j.password");
    }

    public static String getPassword(String s)
    {
        return getProperty("weibo4j.password", s);
    }

    public static String getProperty(String s)
    {
        return getProperty(s, null);
    }

    public static String getProperty(String s, String s1)
    {
        String s2;
        String s4;
        s2 = System.getProperty(s, s1);
        if(s2 == null)
            s2 = defaultProperty.getProperty(s);
        if(s2 != null)
            break MISSING_BLOCK_LABEL_64;
        String s3 = defaultProperty.getProperty((new StringBuilder()).append(s).append(".fallback").toString());
        if(s3 == null)
            break MISSING_BLOCK_LABEL_64;
        s4 = System.getProperty(s3);
        s2 = s4;
_L2:
        return replace(s2);
        AccessControlException accesscontrolexception;
        accesscontrolexception;
        s2 = s1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getProxyHost()
    {
        return getProperty("weibo4j.http.proxyHost");
    }

    public static String getProxyHost(String s)
    {
        return getProperty("weibo4j.http.proxyHost", s);
    }

    public static String getProxyPassword()
    {
        return getProperty("weibo4j.http.proxyPassword");
    }

    public static String getProxyPassword(String s)
    {
        return getProperty("weibo4j.http.proxyPassword", s);
    }

    public static int getProxyPort()
    {
        return getIntProperty("weibo4j.http.proxyPort");
    }

    public static int getProxyPort(int i)
    {
        return getIntProperty("weibo4j.http.proxyPort", i);
    }

    public static String getProxyUser()
    {
        return getProperty("weibo4j.http.proxyUser");
    }

    public static String getProxyUser(String s)
    {
        return getProperty("weibo4j.http.proxyUser", s);
    }

    public static int getReadTimeout()
    {
        return getIntProperty("weibo4j.http.readTimeout");
    }

    public static int getReadTimeout(int i)
    {
        return getIntProperty("weibo4j.http.readTimeout", i);
    }

    public static int getRetryCount()
    {
        return getIntProperty("weibo4j.http.retryCount");
    }

    public static int getRetryCount(int i)
    {
        return getIntProperty("weibo4j.http.retryCount", i);
    }

    public static int getRetryIntervalSecs()
    {
        return getIntProperty("weibo4j.http.retryIntervalSecs");
    }

    public static int getRetryIntervalSecs(int i)
    {
        return getIntProperty("weibo4j.http.retryIntervalSecs", i);
    }

    public static String getScheme()
    {
        String s;
        if(useSSL())
            s = "https://";
        else
            s = "http://";
        return s;
    }

    public static String getSource()
    {
        return getProperty("weibo4j.source");
    }

    public static String getSource(String s)
    {
        return getProperty("weibo4j.source", s);
    }

    public static String getUser()
    {
        return getProperty("weibo4j.user");
    }

    public static String getUser(String s)
    {
        return getProperty("weibo4j.user", s);
    }

    public static String getUserAgent()
    {
        return getProperty("weibo4j.http.userAgent");
    }

    public static String getUserAgent(String s)
    {
        return getProperty("weibo4j.http.userAgent", s);
    }

    static void init()
    {
        defaultProperty = new Properties();
        defaultProperty.setProperty("weibo4j.debug", "true");
        defaultProperty.setProperty("weibo4j.source", Weibo.CONSUMER_KEY);
        defaultProperty.setProperty("weibo4j.clientURL", "http://open.t.sina.com.cn/-{weibo4j.clientVersion}.xml");
        defaultProperty.setProperty("weibo4j.http.userAgent", "weibo4j http://open.t.sina.com.cn/ /{weibo4j.clientVersion}");
        defaultProperty.setProperty("weibo4j.http.useSSL", "false");
        defaultProperty.setProperty("weibo4j.http.proxyHost.fallback", "http.proxyHost");
        defaultProperty.setProperty("weibo4j.http.proxyPort.fallback", "http.proxyPort");
        defaultProperty.setProperty("weibo4j.http.connectionTimeout", "20000");
        defaultProperty.setProperty("weibo4j.http.readTimeout", "120000");
        defaultProperty.setProperty("weibo4j.http.retryCount", "3");
        defaultProperty.setProperty("weibo4j.http.retryIntervalSecs", "10");
        defaultProperty.setProperty("weibo4j.async.numThreads", "1");
        defaultProperty.setProperty("weibo4j.clientVersion", Version.getVersion());
        try
        {
            Class.forName("dalvik.system.VMRuntime");
            defaultProperty.setProperty("weibo4j.dalvik", "true");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            defaultProperty.setProperty("weibo4j.dalvik", "false");
        }
        DALVIK = getBoolean("weibo4j.dalvik");
        if(!loadProperties(defaultProperty, (new StringBuilder()).append(".").append(File.separatorChar).append("weibo4j.properties").toString()) && !loadProperties(defaultProperty, weibo4andriod/Configuration.getResourceAsStream((new StringBuilder()).append("/WEB-INF/").append("weibo4j.properties").toString())))
            if(!loadProperties(defaultProperty, weibo4andriod/Configuration.getResourceAsStream((new StringBuilder()).append("/").append("weibo4j.properties").toString())));
    }

    public static boolean isDalvik()
    {
        return DALVIK;
    }

    private static boolean loadProperties(Properties properties, InputStream inputstream)
    {
        properties.load(inputstream);
        boolean flag = true;
_L2:
        return flag;
        Exception exception;
        exception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static boolean loadProperties(Properties properties, String s)
    {
        File file = new File(s);
        if(!file.exists() || !file.isFile()) goto _L2; else goto _L1
_L1:
        properties.load(new FileInputStream(file));
        boolean flag = true;
_L4:
        return flag;
        Exception exception;
        exception;
_L2:
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String replace(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        String s2 = s;
_L4:
        return s2;
_L2:
        String s1;
        int i = s.indexOf("{", 0);
        if(-1 == i)
            break; /* Loop/switch isn't completed */
        int j = s.indexOf("}", i);
        if(j <= i + 1)
            break; /* Loop/switch isn't completed */
        String s3 = s.substring(i + 1, j);
        if(s3.length() <= 0)
            break; /* Loop/switch isn't completed */
        s1 = (new StringBuilder()).append(s.substring(0, i)).append(getProperty(s3)).append(s.substring(j + 1)).toString();
_L5:
        if(s1.equals(s))
            s2 = s;
        else
            s2 = replace(s1);
        if(true) goto _L4; else goto _L3
_L3:
        s1 = s;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public static boolean useSSL()
    {
        return getBoolean("weibo4j.http.useSSL");
    }

    private static boolean DALVIK;
    private static Properties defaultProperty;

    static 
    {
        init();
    }
}
