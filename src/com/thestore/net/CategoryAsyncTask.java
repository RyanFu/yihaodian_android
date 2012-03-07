// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.core.Page;

// Referenced classes of package com.thestore.net:
//            Category

public class CategoryAsyncTask extends AsyncTask
{

    public CategoryAsyncTask(long l, int i, int j, Handler handler1, int k)
    {
        rootid = l;
        handler = handler1;
        type = k;
        sortType = i;
        page = j;
    }

    public CategoryAsyncTask(long l, Handler handler1, int i)
    {
        rootid = l;
        handler = handler1;
        type = i;
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        type;
        JVM INSTR tableswitch 2131296344 2131296346: default 111
    //                   2131296344 32
    //                   2131296345 63
    //                   2131296346 63;
           goto _L1 _L2 _L3 _L3
_L2:
        Page page1;
        pages = null;
        pages = (new Category(rootid)).getCategoryByRootCategoryId();
        page1 = pages;
        break; /* Loop/switch isn't completed */
_L3:
        productPages = null;
        productPages = (new Category(rootid)).getCategoryProduct(rootid, sortType, page);
        page1 = productPages;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
_L1:
        page1 = null;
        return page1;
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message;
        message = handler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR tableswitch 2131296344 2131296346: default 48
    //                   2131296344 58
    //                   2131296345 69
    //                   2131296346 69;
           goto _L1 _L2 _L3 _L3
_L1:
        handler.sendMessage(message);
        return;
_L2:
        message.obj = pages;
        continue; /* Loop/switch isn't completed */
_L3:
        message.obj = productPages;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private Handler handler;
    private int page;
    private Page pages;
    private Page productPages;
    private long rootid;
    private int sortType;
    private int type;
}
