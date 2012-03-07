// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import com.thestore.util.Log;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class StartService extends Service
{

    public StartService()
    {
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
	public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
	public void onCreate()
    {
        super.onCreate();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run()
            {
                Context context;
                Log.debug("StartService", "Start Schedule");
                context = getApplicationContext();
                if(context == null)
                    break MISSING_BLOCK_LABEL_80;
                ConnectivityManager connectivitymanager = (ConnectivityManager)context.getSystemService("connectivity");
                android.net.NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
                connectivitymanager.getNetworkInfo(0);
                if(networkinfo != null && register())
                {
                    Log.debug("StartService", "StopService");
                    timer.cancel();
                    stopSelf();
                }
_L2:
                return;
                Exception exception;
                exception;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final StartService this$0;

            
            {
                this$0 = StartService.this;
                super();
            }
        }
, 0L, 5000L);
        Log.debug("StartService", "onCreate");
        Log.debug("StartService", Environment.getExternalStorageState());
    }

    @Override
	public void onDestroy()
    {
        super.onDestroy();
        Log.debug("sms.service", "sms service shutdown.");
    }

    protected boolean register()
    {
        boolean flag;
label0:
        {
label1:
            {
                if(Environment.getExternalStorageState().equals("mounted"))
                {
                    File file = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator).append("yihaodianmobile").toString());
                    if(file.exists())
                        break label1;
                    TelephonyManager telephonymanager = (TelephonyManager)getSystemService("phone");
                    String s = (new StringBuilder()).append("http://192.168.40.94:8080/tsmm/imeiManagement/register.do").append("?spid=").append("2000").toString();
                    String s1 = (new StringBuilder()).append(s).append("&sid=1").toString();
                    String s2 = (new StringBuilder()).append(s1).append("&pid=1").toString();
                    String s3 = (new StringBuilder()).append(s2).append("&imei=").append(telephonymanager.getDeviceId()).toString();
                    String s4 = (new StringBuilder()).append(s3).append("&p=").append(telephonymanager.getLine1Number()).toString();
                    Log.debug("Register", s4);
                    HttpPost httppost = getHttpPost(s4);
                    DefaultHttpClient defaulthttpclient = new DefaultHttpClient(httppost.getParams());
                    try
                    {
                        HttpResponse httpresponse = defaulthttpclient.execute(httppost);
                        if(httpresponse.getStatusLine().getStatusCode() == 200)
                        {
                            String s5 = getResultString(httpresponse);
                            Log.debug("getResultString", s5);
                            if(s5 == null)
                            {
                                flag = false;
                                break label0;
                            }
                            if(s5.indexOf("<ResultCode>1</ResultCode>") > 0)
                            {
                                file.mkdirs();
                                flag = true;
                                break label0;
                            }
                        } else
                        {
                            httppost.abort();
                        }
                    }
                    catch(Exception exception) { }
                }
                flag = false;
                break label0;
            }
            flag = true;
        }
        return flag;
    }

    private boolean started;
    private Timer timer;

}
