// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;

// Referenced classes of package com.thestore.net:
//            Feekback

public class FeekbackAsyncTask extends AsyncTask
{

    public FeekbackAsyncTask(String s, Handler handler, int i)
    {
        feekbackContent = "";
        type = 0;
        result = 0;
        myHandler = null;
        feekbackContent = s;
        type = i;
        myHandler = handler;
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        Integer integer1;
        result = (new Feekback(feekbackContent)).addFeedback();
        integer1 = Integer.valueOf(result);
        Integer integer = integer1;
_L2:
        return integer;
        Exception exception;
        exception;
        exception.printStackTrace();
        integer = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message = myHandler.obtainMessage();
        message.what = type;
        message.obj = Integer.valueOf(result);
        myHandler.sendMessage(message);
    }

    private String feekbackContent;
    private Handler myHandler;
    private int result;
    private int type;
}
