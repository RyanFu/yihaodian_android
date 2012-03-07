// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.*;
import com.thestore.main.ActivityMain;
import com.thestore.net.DBHelper;
import com.thoughtworks.xstream.XStream;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service
{
    class TimeStampAsy extends AsyncTask
    {

        @Override
		protected transient Object doInBackground(Object aobj[])
        {
            long l = 0L;
            long l1;
            DBHelper dbhelper = new DBHelper();
            XStream xstream = DBHelper.getxStream();
            Object aobj1[] = new Object[1];
            aobj1[0] = DBHelper.getTrader();
            l1 = ((Long)xstream.fromXML(dbhelper.getResultString(dbhelper.getHttpResponse("getServerTimeStamp", xstream.toXML(((aobj1))))))).longValue();
            l = l1;
_L2:
            return Long.valueOf(l);
            Exception exception;
            exception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        @Override
		protected void onPostExecute(Object obj)
        {
            isFinish = false;
            Long.parseLong((String)obj);
_L2:
            return;
            Exception exception;
            exception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final MyService this$0;

        TimeStampAsy()
        {
            this$0 = MyService.this;
            super();
        }
    }


    public MyService()
    {
        task = new TimerTask() {

            @Override
			public void run()
            {
                Message message = handler.obtainMessage();
                message.what = 1;
                handler.sendMessage(message);
            }

            final MyService this$0;

            
            {
                this$0 = MyService.this;
                super();
            }
        }
;
        timerTask = new TimerTask() {

            @Override
			public void run()
            {
                Message message = handler.obtainMessage();
                message.what = 0;
                handler.sendMessage(message);
            }

            final MyService this$0;

            
            {
                this$0 = MyService.this;
                super();
            }
        }
;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 0 1: default 28
            //                           0 29
            //                           1 59;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                try
                {
                    if(DBHelper.getTimeOut())
                    {
                        if(ActivityMain.progressDialog != null)
                            ActivityMain.progressDialog.cancel();
                        DBHelper.isTimeOut = false;
                    }
                }
                catch(Exception exception1) { }
                continue; /* Loop/switch isn't completed */
_L3:
                try
                {
                    if(!isFinish)
                    {
                        (new TimeStampAsy()).execute(null);
                        isFinish = true;
                    }
                }
                catch(Exception exception) { }
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MyService this$0;

            
            {
                this$0 = MyService.this;
                super();
            }
        }
;
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
        Timer timer = new Timer();
        Timer timer1 = new Timer();
        timer.schedule(task, 0L, 0x15f90L);
        timer1.schedule(timerTask, 0L, 500L);
    }

    @Override
	public void onDestroy()
    {
        timerTask.cancel();
        task.cancel();
        super.onDestroy();
    }

    @Override
	public void onStart(Intent intent, int i)
    {
        super.onStart(intent, i);
    }

    Handler handler;
    private boolean isFinish;
    TimerTask task;
    TimerTask timerTask;



/*
    static boolean access$002(MyService myservice, boolean flag)
    {
        myservice.isFinish = flag;
        return flag;
    }

*/
}
