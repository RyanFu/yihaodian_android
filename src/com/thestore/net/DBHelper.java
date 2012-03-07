// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.yihaodian.mobile.vo.bussiness.Trader;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.*;

// Referenced classes of package com.thestore.net:
//            User

public class DBHelper
{

    public DBHelper()
    {
    }

    private HttpClient getHttpClient()
    {
        if(client == null)
            client = new DefaultHttpClient(httpParams);
        return client;
    }

    public static boolean getTimeOut()
    {
        return isTimeOut;
    }

    public static Trader getTrader()
    {
        Properties properties;
        if(trader == null)
            trader = new Trader();
        trader.setTraderName("androidSystem");
        trader.setTraderPassword("sCarce!8");
        trader.setClientSystem("android");
        trader.setClientVersion("320");
        trader.setProtocol("HTTPXML");
        trader.setInterfaceVersion("1.0");
        trader.setClientAppVersion(version);
        properties = new Properties();
        String s1;
        properties.load(context.getResources().openRawResource(0x7f050000));
        s1 = properties.getProperty("UnionKey");
        String s = s1;
_L2:
        trader.setUnionKey(s);
        return trader;
        android.content.res.Resources.NotFoundException notfoundexception;
        notfoundexception;
        notfoundexception.printStackTrace();
        s = "";
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        s = "";
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static XStream getxStream()
    {
        return xStream;
    }

    public static boolean testNet()
    {
        Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
_L4:
        if(!enumeration.hasMoreElements()) goto _L2; else goto _L1
_L1:
        Enumeration enumeration1 = ((NetworkInterface)enumeration.nextElement()).getInetAddresses();
_L6:
        if(!enumeration1.hasMoreElements()) goto _L4; else goto _L3
_L3:
        boolean flag1 = ((InetAddress)enumeration1.nextElement()).isLoopbackAddress();
        if(flag1) goto _L6; else goto _L5
_L5:
        boolean flag = true;
_L8:
        return flag;
        Exception exception;
        exception;
        flag = false;
        continue; /* Loop/switch isn't completed */
_L2:
        flag = false;
        if(true) goto _L8; else goto _L7
_L7:
    }

    public Bitmap getBitMap(String s)
    {
        URL url1;
        Bitmap bitmap;
        HttpURLConnection httpurlconnection;
        InputStream inputstream;
        Bitmap bitmap1;
        try
        {
            url1 = new URL(s);
        }
        catch(MalformedURLException malformedurlexception)
        {
            malformedurlexception.printStackTrace();
            url1 = null;
        }
        httpurlconnection = (HttpURLConnection)url1.openConnection();
        httpurlconnection.setDoInput(true);
        httpurlconnection.connect();
        inputstream = httpurlconnection.getInputStream();
        bitmap1 = BitmapFactory.decodeStream(inputstream);
        inputstream.close();
        bitmap = bitmap1;
_L2:
        return bitmap;
        Exception exception;
        exception;
        bitmap = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public HttpPost getHttpPost()
    {
        httpPost = new HttpPost("http://interface.m.yihaodian.com/centralmobile/servlet/CentralMobileFacadeServlet");
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        httpPost.setParams(httpParams);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return httpPost;
    }

    public HttpPost getHttpPost(String s, String s1)
    {
        System.out.println((new StringBuilder()).append(s).append(":").append(s1).toString());
        HttpPost httppost = getHttpPost();
        try
        {
            httppost.setEntity(new UrlEncodedFormEntity(setMethodParamList(s, s1), "Utf-8"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
        }
        return httppost;
    }

    public HttpResponse getHttpResponse(String s, String s1)
    {
        return getHttpResponse(getHttpPost(s, s1));
    }

    public HttpResponse getHttpResponse(HttpPost httppost)
    {
        HttpClient httpclient = getHttpClient();
        httpclient;
        JVM INSTR monitorenter ;
        httpResponse = httpclient.execute(httppost);
        if(httpResponse.getStatusLine().getStatusCode() != 200) goto _L2; else goto _L1
_L1:
        isTimeOut = false;
_L4:
        return httpResponse;
_L2:
        httppost.abort();
        isError = true;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        try
        {
            throw exception1;
        }
        catch(SocketTimeoutException sockettimeoutexception)
        {
            isTimeOut = true;
        }
        catch(Exception exception)
        {
            isTimeOut = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Object getListFromXML(String s)
    {
        return xStream.fromXML(s);
    }

    public Long getMCSITE_ID()
    {
        return MCSITE_ID;
    }

    public String getMethodBody(Object aobj[])
    {
        return xStream.toXML(((Object) (aobj)));
    }

    public String getResultString(HttpResponse httpresponse)
    {
        String s = "";
        InputStream inputstream;
        BufferedReader bufferedreader;
        String s3;
        inputstream = httpresponse.getEntity().getContent();
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        s3 = bufferedreader.readLine();
        String s4;
        String s5;
        s4 = s;
        s5 = s3;
_L2:
        if(s5 == null)
            break; /* Loop/switch isn't completed */
        String s6 = (new StringBuilder()).append(s4).append(s5).append("\n").toString();
        s = s6;
        String s7 = bufferedreader.readLine();
        s4 = s;
        s5 = s7;
        if(true) goto _L2; else goto _L1
_L1:
        bufferedreader.close();
        inputstream.close();
        String s2 = s4;
_L4:
        if(isError && s2.contains("\u7528\u6237Token\u8FC7\u671F,\u8BF7\u91CD\u65B0\u767B\u5F55"))
            User.token = null;
        if(isError && s2.contains("Trader\u51FA\u9519"))
            isTimeOut = true;
        return s2;
        Exception exception;
        exception;
        String s1;
        Exception exception1;
        s1 = s;
        exception1 = exception;
_L5:
        exception1.printStackTrace();
        s2 = s1;
        if(true) goto _L4; else goto _L3
_L3:
        exception1;
        s1 = s4;
          goto _L5
    }

    public List setMethodParamList(String s, String s1)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new BasicNameValuePair("methodName", s));
        arraylist.add(new BasicNameValuePair("methodBody", s1));
        return arraylist;
    }

    public static String BRAND;
    public static String IMEI;
    private static final Long MCSITE_ID = Long.valueOf(1L);
    public static String MODEL;
    public static String PHONE;
    private static final String SERVLET_URL = "http://interface.m.yihaodian.com/centralmobile/servlet/CentralMobileFacadeServlet";
    private static final String TRADER_NAME = "androidSystem";
    private static final String TRADER_PASSWORD = "sCarce!8";
    public static Context context;
    public static boolean isTimeOut;
    public static boolean isUpdata;
    private static Trader trader = null;
    public static String url;
    public static String version;
    private static XStream xStream = new XStream(new DomDriver());
    private HttpClient client;
    HttpParams httpParams;
    private HttpPost httpPost;
    private HttpResponse httpResponse;
    private boolean isError;

}
