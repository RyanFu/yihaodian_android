// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.core.Page;

// Referenced classes of package com.thestore.net:
//            Coupon

public class CouponAsyncTask extends AsyncTask
{

    public CouponAsyncTask(Handler handler, int i)
    {
        result = 99;
        couponCode = "";
        coupon = null;
        couponList = null;
        myHandler = handler;
        type = i;
    }

    public CouponAsyncTask(String s, Handler handler, int i)
    {
        result = 99;
        couponCode = "";
        coupon = null;
        couponList = null;
        couponCode = s;
        myHandler = handler;
        type = i;
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        type;
        JVM INSTR tableswitch 2131296334 2131296335: default 105
    //                   2131296334 28
    //                   2131296335 65;
           goto _L1 _L2 _L3
_L2:
        Object obj;
        coupon = new Coupon(couponCode);
        result = coupon.saveCouponToSessionOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L3:
        coupon = new Coupon();
        couponList = coupon.getMyCouponList();
        obj = couponList;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.printStackTrace();
        obj = null;
        break; /* Loop/switch isn't completed */
_L1:
        obj = null;
        return obj;
    }

    @Override
	protected void onPostExecute(Object obj)
    {
        Message message;
        message = myHandler.obtainMessage();
        message.what = type;
        type;
        JVM INSTR tableswitch 2131296334 2131296335: default 44
    //                   2131296334 54
    //                   2131296335 68;
           goto _L1 _L2 _L3
_L1:
        myHandler.sendMessage(message);
        return;
_L2:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L3:
        message.obj = couponList;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private Coupon coupon;
    private String couponCode;
    private Page couponList;
    private Handler myHandler;
    private int result;
    private int type;
}
