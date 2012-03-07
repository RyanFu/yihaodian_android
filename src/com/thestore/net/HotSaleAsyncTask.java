// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            HotSale

public class HotSaleAsyncTask extends AsyncTask
{

    public HotSaleAsyncTask(Handler handler, int i)
    {
        type = 0;
        myHandler = null;
        productList = null;
        type = i;
        myHandler = handler;
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        productList = (new HotSale()).getHotRandomProducts();
        List list = productList;
_L2:
        return list;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        list = productList;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        list = productList;
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message = myHandler.obtainMessage();
        message.what = type;
        message.obj = productList;
        myHandler.sendMessage(message);
    }

    private Handler myHandler;
    private List productList;
    private int type;
}
