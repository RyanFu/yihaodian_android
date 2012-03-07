// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            Search

public class SearchLongTailDao extends AsyncTask
{

    public SearchLongTailDao(String s, Handler handler1)
    {
        keyword = "";
        keyword = s;
        handler = handler1;
    }

    @Override
	protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    protected transient List doInBackground(Object aobj[])
    {
        Search search = new Search(keyword);
        List list1 = search.getKeyword();
        List list = list1;
_L2:
        return list;
        Exception exception;
        exception;
        list = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((List)obj);
    }

    protected void onPostExecute(List list)
    {
        Message message = handler.obtainMessage();
        message.what = 0x7f090054;
        message.obj = list;
        handler.sendMessage(message);
    }

    private Handler handler;
    private String keyword;
}
