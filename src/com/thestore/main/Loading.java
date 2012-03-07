// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import com.thestore.net.*;
import com.thestore.util.*;
import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.cart.CartVO;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import com.yihaodian.mobile.vo.system.DownloadVO;
import java.io.File;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Home

public class Loading extends ActivityMain
{

    public Loading()
    {
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 7: default 72
            //                           2131296264: 289
            //                           2131296265: 73
            //                           2131296266: 113
            //                           2131296286: 609
            //                           2131296299: 576
            //                           2131296305: 795
            //                           2131296417: 231;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
                return;
_L3:
                Log.d("yihaodian", "Loading \u5931\u8D25");
                textView.setText(0x7f0a003e);
                handler.removeCallbacks(runnable);
                continue; /* Loop/switch isn't completed */
_L4:
                Log.d("yihaodian", "Loading \u521D\u59CB\u5316");
                if("8151389".equals(DBHelper.getTrader().getUnionKey()))
                {
                    textView.setText(0x7f0a0042);
                    initNet();
                } else
                {
                    textView.setText(0x7f0a0046);
                    if(DBHelper.testNet())
                    {
                        (new DownAsy(handler, 0x7f090008)).execute(null);
                    } else
                    {
                        judge();
                        textView.setText(0x7f0a0011);
                        util.showNetNull();
                    }
                }
                continue; /* Loop/switch isn't completed */
_L8:
                Log.d("yihaodian", "Loading \u53D1\u9001UA");
                if(message.obj == null)
                {
                    switch(((Integer)message.obj).intValue())
                    {
                    case 0: // '\0'
                        sendUA();
                        break;
                    }
                    if(false)
                        ;
                }
                continue; /* Loop/switch isn't completed */
_L2:
                Log.d("yihaodian", "Loading \u68C0\u67E5\u66F4\u65B0");
                if(message.obj != null)
                {
                    final DownloadVO downloadVO = (DownloadVO)message.obj;
                    if(downloadVO.getCanUpdate().equals("true"))
                    {
                        AlertDialog alertdialog;
                        if(downloadVO.getForceUpdate() != null && downloadVO.getForceUpdate().equals("true"))
                            alertdialog = (new android.app.AlertDialog.Builder(Loading.this)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(downloadVO.getRemark()).setPositiveButton(getResources().getString(0x7f0a0014), new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int k)
                                {
                                    DBHelper.url = downloadVO.getDownloadUrl();
                                    textView.setText("\u66F4\u65B0\u4E2D,\u8BF7\u7A0D\u5019...");
                                    if(util.isExistSdCard())
                                    {
                                        startService(new Intent("com.thestore.util.DownService"));
                                    } else
                                    {
                                        finish();
                                        util.showToast("\u8BF7\u63D2\u5165\u5B58\u50A8\u5361\uFF0C\u624D\u53EF\u4EE5\u4E0B\u8F7D\uFF01");
                                    }
                                }

                                final _cls3 this$1;
                                final DownloadVO val$downloadVO;

                    
                    {
                        this$1 = _cls3.this;
                        downloadVO = downloadvo;
                        super();
                    }
                            }
).setNegativeButton(getResources().getString(0x7f0a0015), new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int k)
                                {
                                    finish();
                                }

                                final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                            }
).create();
                        else
                            alertdialog = (new android.app.AlertDialog.Builder(Loading.this)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(downloadVO.getRemark()).setPositiveButton(getResources().getString(0x7f0a0014), new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int k)
                                {
                                    DBHelper.url = downloadVO.getDownloadUrl();
                                    if(util.isExistSdCard())
                                    {
                                        startService(new Intent("com.thestore.util.DownService"));
                                        textView.setText(0x7f0a0042);
                                        initNet();
                                    } else
                                    {
                                        textView.setText(0x7f0a0042);
                                        initNet();
                                        util.showToast("\u8BF7\u63D2\u5165\u5B58\u50A8\u5361\uFF0C\u624D\u53EF\u4EE5\u4E0B\u8F7D\uFF01");
                                    }
                                }

                                final _cls3 this$1;
                                final DownloadVO val$downloadVO;

                    
                    {
                        this$1 = _cls3.this;
                        downloadVO = downloadvo;
                        super();
                    }
                            }
).setNegativeButton(getResources().getString(0x7f0a0016), new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int k)
                                {
                                    textView.setText(0x7f0a0042);
                                    initNet();
                                }

                                final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                            }
).create();
                        alertdialog.show();
                    } else
                    {
                        textView.setText(0x7f0a0042);
                        initNet();
                    }
                } else
                {
                    judge();
                    textView.setText(0x7f0a0011);
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L6:
                if(message.obj != null)
                    Cart.cartTotal = ((CartVO)message.obj).getTotalquantity().longValue();
                positive();
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj != null)
                {
                    if(message.obj.equals("-1"))
                        positive();
                    else
                    if(message.obj.equals("-2"))
                    {
                        positive();
                    } else
                    {
                        User.token = (String)message.obj;
                        User.password = getSharedPreferences("user", 0).getString("password", "");
                        if(DBHelper.testNet())
                        {
                            CartAsyncTask cartasynctask = new CartAsyncTask(handler, 0x7f09002b);
                            if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                cartasynctask.cancel(true);
                                cartasynctask.execute(null);
                            } else
                            {
                                cartasynctask.execute(null);
                            }
                        } else
                        {
                            util.showNetNull();
                            positive();
                        }
                    }
                } else
                {
                    util.showNetNull();
                    positive();
                }
                continue; /* Loop/switch isn't completed */
_L7:
                Log.d("yihaodian", "Loading \u4FC3\u9500\u4FE1\u606F");
                if(message.obj != null)
                {
                    Page page = (Page)message.obj;
                    PromationView.list = page.getObjList();
                    if(PromationView.list.size() > 0)
                    {
                        int i = PromationView.list.size();
                        PromationView.count = i;
                        for(int j = 0; j < i; j++)
                        {
                            PromationView.arraString[j] = ((ProductVO)page.getObjList().get(j)).getAdvertisement();
                            if(PromationView.arraString[j].length() > 13)
                                PromationView.arraString[j] = (new StringBuilder()).append(PromationView.arraString[j].substring(0, 13)).append("...").toString();
                        }

                    }
                    SharedPreferences sharedpreferences = getSharedPreferences("user", 0);
                    if(!sharedpreferences.getString("user", "").equals("") && !sharedpreferences.getString("password", "").equals(""))
                    {
                        User.userAccount = sharedpreferences.getString("user", "");
                        UserAsyncTask userasynctask = new UserAsyncTask(sharedpreferences.getString("user", ""), sharedpreferences.getString("password", ""), handler, 0x7f09001e);
                        if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            userasynctask.cancel(true);
                            userasynctask.execute(null);
                        } else
                        {
                            userasynctask.execute(null);
                        }
                    } else
                    {
                        positive();
                    }
                } else
                {
                    judge();
                    textView.setText(0x7f0a0011);
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L9
_L9:
            }

            final Loading this$0;

            
            {
                this$0 = Loading.this;
                super();
            }
        }
;
        runnable = new Runnable() {

            public void run()
            {
                handler.sendEmptyMessageDelayed(0x7f09000a, 500L);
            }

            final Loading this$0;

            
            {
                this$0 = Loading.this;
                super();
            }
        }
;
    }

    private String getUa()
    {
        try
        {
            DBHelper.BRAND = Build.BRAND;
            DBHelper.MODEL = Build.MODEL;
            TelephonyManager telephonymanager = (TelephonyManager)getSystemService("phone");
            DBHelper.PHONE = telephonymanager.getLine1Number();
            DBHelper.IMEI = telephonymanager.getDeviceId();
        }
        catch(Exception exception) { }
        return (new StringBuilder()).append("UA BRAND:").append(DBHelper.BRAND).append(" MODEL:").append(DBHelper.MODEL).append(" PHONE:").append(DBHelper.PHONE).append(" IMEI:").append(DBHelper.IMEI).toString();
    }

    private void init()
    {
        tempString = getResources().getString(0x7f0a003d);
        textView = (TextView)findViewById(0x7f0900d8);
        if(textView != null)
            textView.setText(tempString);
        util = new Util(this);
        (new Thread(runnable)).start();
    }

    private void initNet()
    {
        if(DBHelper.testNet())
        {
            User.province = (new Store(this)).getInt("provinceID", 1);
            TypeAsyncTask typeasynctask = new TypeAsyncTask(handler, 0x7f090031);
            if(typeasynctask.getStatus() != android.os.AsyncTask.Status.RUNNING)
                typeasynctask.execute(null);
        } else
        {
            util.showNetNull();
        }
    }

    private void judge()
    {
        (new android.app.AlertDialog.Builder(this)).setTitle(getResources().getString(0x7f0a0040)).setMessage(0x7f0a0041).setNegativeButton(0x7f0a0044, new android.content.DialogInterface.OnClickListener() {

            @Override
			public void onClick(DialogInterface dialoginterface, int i)
            {
                finish();
            }

            final Loading this$0;

            
            {
                this$0 = Loading.this;
                super();
            }
        }
).setPositiveButton(0x7f0a0045, new android.content.DialogInterface.OnClickListener() {

            @Override
			public void onClick(DialogInterface dialoginterface, int i)
            {
                init();
            }

            final Loading this$0;

            
            {
                this$0 = Loading.this;
                super();
            }
        }
).create().show();
        tempString = getResources().getString(0x7f0a003e);
        handler.sendEmptyMessage(0x7f090009);
    }

    private void positive()
    {
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/Home);
        startActivity(intent);
        finish();
    }

    private void sendUA()
    {
        FeekbackAsyncTask feekbackasynctask = new FeekbackAsyncTask(getUa(), handler, 0x7f0900a1);
        if(feekbackasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
        {
            feekbackasynctask.cancel(true);
            feekbackasynctask.execute(null);
        } else
        {
            feekbackasynctask.execute(null);
        }
    }

    public void installApp(String s)
    {
        String s1;
        Intent intent;
        if(Environment.getExternalStorageDirectory().exists())
            s1 = (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append(s).toString();
        else
            s1 = s;
        intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(s1)), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(0x7f030017);
        DBHelper.context = this;
        try
        {
            DBHelper.version = getPackageManager().getPackageInfo("com.thestore.main", 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
        startService(new Intent("com.thestore.util.MyService"));
        init();
        sendUA();
    }

    @Override
	public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            stopService(new Intent("com.thestore.util.MyService"));
            finish();
            ((ActivityManager)getSystemService("activity")).restartPackage(getPackageName());
        }
        return super.onKeyDown(i, keyevent);
    }

    private Handler handler;
    private Runnable runnable;
    public String tempString;
    private TextView textView;
    private Util util;









}
