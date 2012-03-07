// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.order.InvoiceVO;
import com.yihaodian.mobile.vo.order.OrderVO;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            Order

public class OrderAsyncTask extends AsyncTask
{

    public OrderAsyncTask(Handler handler, int i)
    {
        result = 0;
        order = null;
        orderVO = null;
        invoiceVO = null;
        couponPage = null;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        page = null;
        myHandler = handler;
        type = i;
    }

    public OrderAsyncTask(Handler handler, int i, int j, int k)
    {
        this(handler, i);
        currentPage = j;
        pageSize = k;
    }

    public OrderAsyncTask(InvoiceVO invoicevo, Handler handler, int i)
    {
        result = 0;
        order = null;
        orderVO = null;
        invoiceVO = null;
        couponPage = null;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        page = null;
        invoiceVO = invoicevo;
        myHandler = handler;
        type = i;
    }

    public OrderAsyncTask(Long long1, Handler handler, int i)
    {
        result = 0;
        order = null;
        orderVO = null;
        invoiceVO = null;
        couponPage = null;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        page = null;
        i;
        JVM INSTR tableswitch 2131296313 2131296321: default 100
    //                   2131296313 111
    //                   2131296314 100
    //                   2131296315 100
    //                   2131296316 119
    //                   2131296317 100
    //                   2131296318 100
    //                   2131296319 100
    //                   2131296320 111
    //                   2131296321 111;
           goto _L1 _L2 _L1 _L1 _L3 _L1 _L1 _L1 _L2 _L2
_L1:
        myHandler = handler;
        type = i;
        return;
_L2:
        orderId = long1;
        continue; /* Loop/switch isn't completed */
_L3:
        goodReceiverId = long1;
        if(true) goto _L1; else goto _L4
_L4:
    }

    @Override
	protected transient Object doInBackground(Object aobj[])
    {
        type;
        JVM INSTR lookupswitch 12: default 576
    //                   2131296313: 439
    //                   2131296314: 112
    //                   2131296315: 239
    //                   2131296316: 399
    //                   2131296318: 205
    //                   2131296319: 311
    //                   2131296320: 476
    //                   2131296321: 516
    //                   2131296342: 355
    //                   2131296392: 143
    //                   2131296471: 273
    //                   2131296580: 177;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L3:
        Object obj;
        order = new Order();
        orderVO = order.getSessionOrder();
        obj = orderVO;
        break; /* Loop/switch isn't completed */
_L11:
        order = new Order();
        result = order.createSessionOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L13:
        order = new Order();
        couponPage = order.getMyCouponList();
        obj = null;
        break; /* Loop/switch isn't completed */
_L6:
        order = new Order();
        result = order.submitOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L4:
        order = new Order();
        result = order.removeSessionOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L12:
        order = new Order(invoiceVO);
        result = order.saveInvoiceToSessionOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L7:
        order = null;
        order = new Order(currentPage, pageSize);
        page = order.getMyOrderListByToken();
        obj = page;
        break; /* Loop/switch isn't completed */
_L10:
        order = null;
        order = new Order(currentPage, pageSize);
        page = order.getMyOrderListByToken();
        obj = page;
        break; /* Loop/switch isn't completed */
_L5:
        order = new Order(goodReceiverId, 0x7f09003c);
        result = order.saveGoodReceiverToSessionOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L2:
        order = new Order(orderId, 0x7f090039);
        orderVO = order.getOrderDetailByOrderId();
        obj = orderVO;
        break; /* Loop/switch isn't completed */
_L8:
        order = new Order(orderId, 0x7f090040);
        result = order.cancelOrder();
        obj = Integer.valueOf(result);
        break; /* Loop/switch isn't completed */
_L9:
        Integer integer;
        order = new Order(orderId, 0x7f090041);
        result = order.rebuyOrder();
        integer = Integer.valueOf(result);
        obj = integer;
        break; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        exception.getMessage();
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
        JVM INSTR lookupswitch 11: default 120
    //                   2131296313: 222
    //                   2131296314: 130
    //                   2131296315: 169
    //                   2131296316: 197
    //                   2131296318: 155
    //                   2131296319: 211
    //                   2131296320: 233
    //                   2131296321: 247
    //                   2131296342: 261
    //                   2131296392: 141
    //                   2131296471: 183;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        myHandler.sendMessage(message);
        return;
_L3:
        message.obj = orderVO;
        continue; /* Loop/switch isn't completed */
_L11:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L6:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L4:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L12:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L5:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L7:
        message.obj = page;
        continue; /* Loop/switch isn't completed */
_L2:
        message.obj = orderVO;
        continue; /* Loop/switch isn't completed */
_L8:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L9:
        message.obj = Integer.valueOf(result);
        continue; /* Loop/switch isn't completed */
_L10:
        message.obj = ((OrderVO)page.getObjList().get(0)).getOrderCode();
        if(true) goto _L1; else goto _L13
_L13:
    }

    private Page couponPage;
    private int currentPage;
    private Long goodReceiverId;
    private InvoiceVO invoiceVO;
    private Handler myHandler;
    private Order order;
    private Long orderId;
    private OrderVO orderVO;
    private Page page;
    private int pageSize;
    private int result;
    private int type;
}
