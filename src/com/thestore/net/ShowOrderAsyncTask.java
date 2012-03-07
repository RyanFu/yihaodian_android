// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.order.OrderVO;
import java.io.*;
import java.util.List;
import org.apache.commons.httpclient.HttpException;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import weibo4andriod.Weibo;
import weibo4andriod.WeiboException;

// Referenced classes of package com.thestore.net:
//            DBHelper, Order

public class ShowOrderAsyncTask extends AsyncTask
{

    public ShowOrderAsyncTask(Handler handler1, int i)
    {
        result = Boolean.valueOf(false);
        handler = handler1;
        type = i;
    }

    public ShowOrderAsyncTask(Handler handler1, int i, int j, int k)
    {
        result = Boolean.valueOf(false);
        handler = handler1;
        type = i;
        curentPage = j;
        page = k;
    }

    public ShowOrderAsyncTask(Handler handler1, int i, String s, String s1)
    {
        result = Boolean.valueOf(false);
        handler = handler1;
        type = i;
        if(i == 1)
        {
            sinaAccount = s;
            pass = s1;
        } else
        {
            user = s;
            order = s1;
        }
    }

    public ShowOrderAsyncTask(Handler handler1, int i, String s, String s1, String s2, String s3, String s4, 
            String s5, String s6)
    {
        result = Boolean.valueOf(false);
        handler = handler1;
        type = i;
        sinaAccount = s;
        pass = s1;
        data = s2;
        user = s3;
        order = s4;
        product_id = s5;
        province_id = s6;
    }

    private HttpGet getHttpGet(String s)
    {
        HttpGet httpget = new HttpGet(s);
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 10000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 15000);
        httpget.setParams(basichttpparams);
        return httpget;
    }

    private HttpPost getHttpPost(String s)
    {
        HttpPost httppost = new HttpPost(s);
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 10000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 15000);
        httppost.setParams(basichttpparams);
        return httppost;
    }

    private String getResultString(HttpResponse httpresponse)
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

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        type;
        JVM INSTR tableswitch 1 7: default 48
    //                   1 52
    //                   2 126
    //                   3 553
    //                   4 48
    //                   5 516
    //                   6 802
    //                   7 826;
           goto _L1 _L2 _L3 _L4 _L1 _L5 _L6 _L7
_L1:
        Object obj = null;
_L10:
        return obj;
_L2:
        Boolean boolean1;
        result = Boolean.valueOf(false);
        Weibo.CONSUMER_KEY = "356866357";
        Weibo.CONSUMER_SECRET = "5905214414e9e8a5aa50cb17bd499210";
        if((new Weibo(sinaAccount, pass)).verifyCredentials() == null)
            break MISSING_BLOCK_LABEL_118;
        result = Boolean.valueOf(true);
        boolean1 = Boolean.valueOf(true);
        obj = boolean1;
        continue; /* Loop/switch isn't completed */
        WeiboException weiboexception1;
        weiboexception1;
        weiboexception1.printStackTrace();
        obj = result;
        continue; /* Loop/switch isn't completed */
_L3:
        HttpPost httppost1;
        DefaultHttpClient defaulthttpclient1;
        result = Boolean.valueOf(false);
        String s4 = (new StringBuilder()).append("http://").append("m.yihaodian.com").append("/interface/show/saveShow.do").toString();
        String s5 = (new StringBuilder()).append(s4).append("?aid=1").toString();
        String s6 = (new StringBuilder()).append(s5).append("&u=").append(user).toString();
        String s7 = (new StringBuilder()).append(s6).append("&oid=").append(order).toString();
        String s8 = (new StringBuilder()).append(s7).append("&pid=").append(product_id).toString();
        String s9 = (new StringBuilder()).append(s8).append("&vid=").append(province_id).toString();
        httppost1 = getHttpPost((new StringBuilder()).append(s9).append("&tid=1").append("&fid=").append(DBHelper.getTrader().getTraderName()).toString());
        defaulthttpclient1 = new DefaultHttpClient(httppost1.getParams());
        WeiboException weiboexception;
        try
        {
            HttpResponse httpresponse1 = defaulthttpclient1.execute(httppost1);
            if(httpresponse1.getStatusLine().getStatusCode() == 200)
            {
                String s10 = getResultString(httpresponse1);
                if(s10 == null)
                    obj = result;
                else
                if(s10.indexOf("<ResultCode>0</ResultCode>") > 0)
                {
                    obj = result;
                } else
                {
                    Weibo.CONSUMER_KEY = "356866357";
                    Weibo.CONSUMER_SECRET = "5905214414e9e8a5aa50cb17bd499210";
                    (new Weibo(sinaAccount, pass)).updateStatus(data);
                    result = Boolean.valueOf(true);
                    obj = result;
                }
                continue; /* Loop/switch isn't completed */
            }
            httppost1.abort();
        }
        catch(HttpException httpexception1)
        {
            httpexception1.printStackTrace();
        }
        catch(IOException ioexception1)
        {
            try
            {
                ioexception1.printStackTrace();
            }
            // Misplaced declaration of an exception variable
            catch(WeiboException weiboexception)
            {
                weiboexception.printStackTrace();
                obj = result;
                continue; /* Loop/switch isn't completed */
            }
        }
        obj = result;
        continue; /* Loop/switch isn't completed */
_L5:
        orderid = ((OrderVO)(new Order()).getMyOrderListByToken().getObjList().get(0)).getOrderCode();
        obj = orderid;
        continue; /* Loop/switch isn't completed */
_L4:
        HttpPost httppost;
        DefaultHttpClient defaulthttpclient;
        result = Boolean.valueOf(false);
        String s = (new StringBuilder()).append("http://").append("m.yihaodian.com").append("/interface/show/exists.do").toString();
        String s1 = (new StringBuilder()).append(s).append("?aid=1").toString();
        String s2 = (new StringBuilder()).append(s1).append("&u=").append(user).toString();
        httppost = getHttpPost((new StringBuilder()).append(s2).append("&oid=").append(order).toString());
        defaulthttpclient = new DefaultHttpClient(httppost.getParams());
        HttpResponse httpresponse = defaulthttpclient.execute(httppost);
        if(httpresponse.getStatusLine().getStatusCode() == 200)
        {
            String s3 = getResultString(httpresponse);
            if(s3 == null)
                obj = result;
            else
            if(s3.indexOf("<ResultCode>0</ResultCode>") > 0)
            {
                obj = result;
            } else
            {
                result = Boolean.valueOf(true);
                obj = result;
            }
            continue; /* Loop/switch isn't completed */
        }
        httppost.abort();
_L8:
        obj = result;
        continue; /* Loop/switch isn't completed */
        HttpException httpexception;
        httpexception;
        httpexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        if(true) goto _L8; else goto _L6
_L6:
        try
        {
            Thread.sleep(1000L);
        }
        catch(InterruptedException interruptedexception1)
        {
            interruptedexception1.printStackTrace();
        }
        obj = Boolean.valueOf(true);
        continue; /* Loop/switch isn't completed */
_L7:
        try
        {
            Thread.sleep(100L);
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
        obj = Boolean.valueOf(true);
        if(true) goto _L10; else goto _L9
_L9:
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message;
        message = handler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR tableswitch 1 7: default 64
    //                   1 74
    //                   2 85
    //                   3 96
    //                   4 107
    //                   5 118
    //                   6 129
    //                   7 129;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L7
_L1:
        handler.sendMessage(message);
        return;
_L2:
        message.obj = result;
        continue; /* Loop/switch isn't completed */
_L3:
        message.obj = result;
        continue; /* Loop/switch isn't completed */
_L4:
        message.obj = result;
        continue; /* Loop/switch isn't completed */
_L5:
        message.obj = result;
        continue; /* Loop/switch isn't completed */
_L6:
        message.obj = orderid;
        continue; /* Loop/switch isn't completed */
_L7:
        message.obj = null;
        if(true) goto _L1; else goto _L8
_L8:
    }

    public static final int SHOWORDER_EXISTS = 3;
    public static final int SHOWORDER_GETORDERID = 5;
    public static final int SHOWORDER_SAVESHOW = 4;
    public static final int SHOWORDER_SHOWINFO1 = 6;
    public static final int SHOWORDER_SHOWINFO2 = 7;
    public static final int SINA_SHOWORDER = 2;
    public static final int SINA_USERCHECK = 1;
    int curentPage;
    String data;
    Handler handler;
    String order;
    String orderid;
    int page;
    String pass;
    String product_id;
    String province_id;
    Boolean result;
    String sinaAccount;
    int type;
    String user;
}
