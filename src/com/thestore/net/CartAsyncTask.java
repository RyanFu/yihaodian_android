// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.cart.CartVO;

// Referenced classes of package com.thestore.net:
//            Cart

public class CartAsyncTask extends AsyncTask
{

    public CartAsyncTask(long l, int i, Handler handler1, int j)
    {
        this(l, handler1, j);
        updateType = i;
    }

    public CartAsyncTask(long l, Handler handler1, int i)
    {
        this(handler1, i);
        productId = l;
    }

    public CartAsyncTask(long l, Long long1, int i, Handler handler1, int j)
    {
        this(l, i, handler1, j);
        quantity = long1.longValue();
    }

    public CartAsyncTask(long l, Long long1, Handler handler1, int i)
    {
        this(l, handler1, i);
        quantity = long1.longValue();
    }

    public CartAsyncTask(Handler handler1, int i)
    {
        handler = handler1;
        type = i;
    }

    protected transient Integer doInBackground(String as[])
    {
        type;
        JVM INSTR tableswitch 2131296297 2131296303: default 176
    //                   2131296297 48
    //                   2131296298 176
    //                   2131296299 70
    //                   2131296300 176
    //                   2131296301 104
    //                   2131296302 126
    //                   2131296303 152;
           goto _L1 _L2 _L1 _L3 _L1 _L4 _L5 _L6
_L2:
        Integer integer;
        integer = (new Cart(productId, quantity)).getAddCart();
        break; /* Loop/switch isn't completed */
_L3:
        Cart cart = new Cart();
        cartVO = new CartVO();
        cartVO = cart.getCart();
        integer = null;
        break; /* Loop/switch isn't completed */
_L4:
        integer = (new Cart(productId, updateType)).getDelCart();
        break; /* Loop/switch isn't completed */
_L5:
        integer = (new Cart(productId, updateType, quantity)).getUpdateCart();
        break; /* Loop/switch isn't completed */
_L6:
        Integer integer1 = (new Cart()).delAllProduct();
        integer = integer1;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        integer = null;
        break; /* Loop/switch isn't completed */
_L1:
        integer = null;
        return integer;
    }

    @Override
	protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected void onPostExecute(Integer integer)
    {
        Message message;
        message = handler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR tableswitch 2131296299 2131296299: default 40
    //                   2131296299 55;
           goto _L1 _L2
_L1:
        message.obj = integer;
_L4:
        handler.sendMessage(message);
        return;
_L2:
        message.obj = cartVO;
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Integer)obj);
    }

    private CartVO cartVO;
    private Handler handler;
    private long productId;
    private long quantity;
    private int type;
    private int updateType;
}
