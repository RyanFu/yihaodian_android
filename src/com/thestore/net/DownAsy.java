// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.system.DownloadVO;

// Referenced classes of package com.thestore.net:
//            DBHelper

public class DownAsy extends AsyncTask
{

    public DownAsy(Handler handler1, int i)
    {
        handler = handler1;
        type = i;
    }

    @Override
	protected transient DownloadVO doInBackground(Object aobj[])
    {
        DownloadVO downloadvo = null;
        DownloadVO downloadvo1;
        DBHelper dbhelper = new DBHelper();
        XStream xstream = DBHelper.getxStream();
        Object aobj1[] = new Object[1];
        aobj1[0] = DBHelper.getTrader();
        downloadvo1 = (DownloadVO)xstream.fromXML(dbhelper.getResultString(dbhelper.getHttpResponse("getClientApplicationDownloadUrl", xstream.toXML(((aobj1))))));
        downloadvo = downloadvo1;
_L2:
        return downloadvo;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    protected void onPostExecute(DownloadVO downloadvo)
    {
        Message message = handler.obtainMessage();
        message.what = type;
        message.obj = downloadvo;
        handler.sendMessage(message);
    }

    @Override
	protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((DownloadVO)obj);
    }

    private Handler handler;
    private int type;
}
