// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.*;
import android.widget.Toast;
import com.thestore.net.DBHelper;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package com.thestore.util:
//            Util

public class DownService extends Service
{
    class DownApkAsy extends AsyncTask
    {

        protected transient Boolean doInBackground(String as[])
        {
            boolean flag1 = downFile(DBHelper.url, path);
            boolean flag = flag1;
_L2:
            return Boolean.valueOf(flag);
            IOException ioexception;
            ioexception;
            flag = false;
            if(true) goto _L2; else goto _L1
_L1:
        }

        @Override
		protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected void onPostExecute(Boolean boolean1)
        {
            if(boolean1.booleanValue())
            {
                initNotify(0x7f0a0017, 0x7f0a001a);
                installApp(fileName);
            } else
            {
                initNotify(0x7f0a0018, 0x7f0a0019);
            }
            stopService(new Intent("com.thestore.util.DownService"));
            DBHelper.isUpdata = false;
        }

        @Override
		protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Boolean)obj);
        }

        final DownService this$0;

        DownApkAsy()
        {
            this$0 = DownService.this;
            super();
        }
    }


    public DownService()
    {
    }

    private void initNotify(int i, int j)
    {
        NotificationManager notificationmanager = (NotificationManager)getSystemService("notification");
        Notification notification = new Notification(i, getResources().getString(j), System.currentTimeMillis());
        Intent intent = new Intent("com.thestore.main.InstallApp");
        intent.putExtra("path", fileName);
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
        notification.setLatestEventInfo(this, getResources().getString(i), getResources().getString(j), pendingintent);
        notificationmanager.notify(0x7f0a0137, notification);
    }

    private void isExists(String s)
    {
        fileName = s.substring(s.lastIndexOf("/"), s.length());
        if(util.isExistSdCard())
        {
            new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append(fileName).toString());
            path = (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("").toString();
        }
    }

    public boolean downFile(String s, String s1)
        throws IOException
    {
        URLConnection urlconnection = (new URL(s)).openConnection();
        urlconnection.connect();
        InputStream inputstream = urlconnection.getInputStream();
        boolean flag;
        FileOutputStream fileoutputstream;
        byte abyte0[];
        int i;
        if(urlconnection.getContentLength() <= 0)
            flag = true;
        else
            flag = true;
        if(inputstream == null)
            flag = true;
        fileoutputstream = new FileOutputStream((new StringBuilder()).append(s1).append(fileName).toString());
        abyte0 = new byte[1024];
        i = 0;
        do
        {
            int j = inputstream.read(abyte0);
            if(j == -1)
            {
                try
                {
                    inputstream.close();
                }
                catch(Exception exception)
                {
                    flag = true;
                }
                return flag;
            }
            fileoutputstream.write(abyte0, 0, j);
            i += j;
        } while(true);
    }

    public void installApp(String s)
    {
        String s1;
        Intent intent;
        if(util.isExistSdCard())
            s1 = (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append(s).toString();
        else
            s1 = (new StringBuilder()).append(Environment.getDataDirectory()).append(s).toString();
        intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(s1)), "application/vnd.android.package-archive");
        DBHelper.context.startActivity(intent);
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
    }

    @Override
	public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
	public void onStart(Intent intent, int i)
    {
        super.onStart(intent, i);
        util = new Util(DBHelper.context);
        if(util.isExistSdCard())
        {
            isExists(DBHelper.url);
            Toast.makeText(this, "\u5F00\u59CB\u4E0B\u8F7D", 0).show();
            DBHelper.isUpdata = true;
            (new DownApkAsy()).execute(null);
        } else
        {
            Toast.makeText(this, (new StringBuilder()).append("\u8BF7\u63D2\u5165\u5B58\u50A8\u5361\uFF0C\u624D\u53EF\u4EE5\u4E0B\u8F7D\uFF01").append(path).toString(), 0).show();
        }
    }

    private String fileName;
    private String path;
    private Util util;



}
