// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;

public class ShowProcessTask extends AsyncTask
{

    public ShowProcessTask(Handler handler1, int i)
    {
        handler = handler1;
        type = i;
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        try
        {
            Thread.sleep(100L);
        }
        catch(InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
        }
        return Integer.valueOf(1);
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message = handler.obtainMessage();
        message.what = type;
        handler.sendMessage(message);
    }

    Handler handler;
    int type;
}
