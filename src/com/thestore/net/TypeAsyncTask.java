// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.core.Page;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            Type

public class TypeAsyncTask extends AsyncTask
{

    public TypeAsyncTask(Handler handler1, int i)
    {
        type = i;
        handler = handler1;
    }

    public TypeAsyncTask(Handler handler1, int i, int j, int k)
    {
        type = i;
        handler = handler1;
        pageCount = j;
    }

    public TypeAsyncTask(Handler handler1, long l, int i)
    {
        type = i;
        handler = handler1;
        categoryId = l;
    }

    @Override
	protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Integer[])aobj);
    }

    protected transient List doInBackground(Integer ainteger[])
    {
        type;
        JVM INSTR lookupswitch 5: default 169
    //                   2131296304: 56
    //                   2131296305: 70
    //                   2131296306: 140
    //                   2131296337: 89
    //                   2131296338: 113;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        List list;
        list = (new Type()).getHomeHotTop();
        break; /* Loop/switch isn't completed */
_L3:
        page = (new Type()).getHomeHotPage();
        list = null;
        break; /* Loop/switch isn't completed */
_L5:
        page = (new Type(1, 10, null, null)).getPromotionProductPage();
        list = null;
        break; /* Loop/switch isn't completed */
_L6:
        page = (new Type(pageCount, 10, null, null)).getPromotionProductPage();
        list = null;
        break; /* Loop/switch isn't completed */
_L4:
        page = (new Type(categoryId)).getTypeHotPage();
        list = null;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        list = null;
        break; /* Loop/switch isn't completed */
_L1:
        list = null;
        return list;
    }

    @Override
	protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((List)obj);
    }

    protected void onPostExecute(List list)
    {
        Message message;
        message = handler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR lookupswitch 4: default 64
    //                   2131296305: 79
    //                   2131296306: 79
    //                   2131296337: 79
    //                   2131296338: 79;
           goto _L1 _L2 _L2 _L2 _L2
_L1:
        message.obj = list;
_L4:
        handler.sendMessage(message);
        return;
_L2:
        message.obj = page;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private long categoryId;
    private Handler handler;
    private Page page;
    private int pageCount;
    private int type;
}
