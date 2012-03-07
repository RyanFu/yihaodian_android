// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.core.Page;

// Referenced classes of package com.thestore.net:
//            Favorite

public class FavoriteAsyncTask extends AsyncTask
{

    public FavoriteAsyncTask(int i, int j, Handler handler1, int k)
    {
        this(0L, handler1, k);
        currentPage = i;
        pageSize = j;
    }

    public FavoriteAsyncTask(long l, Handler handler1, int i)
    {
        productId = l;
        handler = handler1;
        type = i;
    }

    @Override
	protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Object doInBackground(String as[])
    {
        type;
        JVM INSTR tableswitch 2131296308 2131296310: default 103
    //                   2131296308 32
    //                   2131296309 50
    //                   2131296310 80;
           goto _L1 _L2 _L3 _L4
_L2:
        Object obj;
        obj = (new Favorite(productId)).getAddFavorite();
        break; /* Loop/switch isn't completed */
_L3:
        page = (new Favorite(currentPage, pageSize)).getListFavorite();
        obj = page;
        break; /* Loop/switch isn't completed */
_L4:
        Integer integer = (new Favorite(productId)).getDelFavorite();
        obj = integer;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
_L1:
        obj = null;
        return obj;
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message = handler.obtainMessage();
        message.what = type;
        message.obj = obj;
        handler.sendMessage(message);
    }

    private int currentPage;
    private Handler handler;
    private Page page;
    private int pageSize;
    private long productId;
    private int type;
}
