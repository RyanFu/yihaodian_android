// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;

// Referenced classes of package com.thestore.net:
//            MyMessage

public class MessageAsyncTask extends AsyncTask
{

    public MessageAsyncTask(int i, int j, int k, Handler handler1, int l)
    {
        this(handler1, l);
        messageType = i;
        currentPage = j;
        pageSize = k;
    }

    public MessageAsyncTask(long l, Handler handler1, int i)
    {
        this(handler1, i);
        messageId = l;
    }

    public MessageAsyncTask(Handler handler1, int i)
    {
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
        JVM INSTR tableswitch 2131296281 2131296284: default 117
    //                   2131296281 36
    //                   2131296282 50
    //                   2131296283 76
    //                   2131296284 94;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        Object obj;
        obj = (new MyMessage()).getMessageCount();
        break; /* Loop/switch isn't completed */
_L3:
        obj = (new MyMessage(messageType, currentPage, pageSize)).getMessageList();
        break; /* Loop/switch isn't completed */
_L4:
        obj = (new MyMessage(messageId)).getMessageDel();
        break; /* Loop/switch isn't completed */
_L5:
        com.yihaodian.mobile.vo.message.InnerMessageVO innermessagevo = (new MyMessage(messageId)).getMessageDetail();
        obj = innermessagevo;
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
    private long messageId;
    private int messageType;
    private int pageSize;
    private int type;
}
