// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.address.GoodReceiverVO;
import com.yihaodian.mobile.vo.coupon.CouponVO;
import com.yihaodian.mobile.vo.order.*;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Order extends ActivityMain
{

    public Order()
    {
        result = -1;
        totalPricetTextView = null;
        deliveryAmountTextView = null;
        accountAmountTextView = null;
        couponAmountTextView = null;
        paymentAccountTextView = null;
        productAmount = "0.00";
        deliverAmount = "0.00";
        accountAmount = "0.00";
        couponAmount = "0.00";
        paymentAccount = "0.00";
        addressTextView = null;
        goodReceiver = "\u65E0";
        productNameTextView = null;
        sendMethodTextView = null;
        orderItemList = null;
        productName = "\u65E0";
        useCouponLayout = null;
        useCouponTextView = null;
        coupon = "\u65E0";
        useInvoiceteLayout = null;
        useInvoiceteTextView = null;
        invoiceStr = "\u65E0";
        createOrderButton = null;
        cancelbButton = null;
        orderVO = null;
        goodReceiverVO = null;
        couponVO = null;
        invoiceVO = null;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                System.out.println(message.what);
                message.what;
                JVM INSTR lookupswitch 4: default 56
            //                           2131296303: 453
            //                           2131296314: 57
            //                           2131296315: 329
            //                           2131296318: 112;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L3:
                orderVO = (OrderVO)message.obj;
                if(orderVO != null)
                {
                    initViews();
                    cancelProgress();
                } else
                {
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L5:
                String as[] = getResources().getStringArray(0x7f06000c);
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    if(result == 1)
                    {
                        Cart.cartTotal = 0L;
                        setCartImage(Long.valueOf(Cart.cartTotal));
                        CartAsyncTask cartasynctask = new CartAsyncTask(myHandler, 0x7f09002f);
                        if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            cartasynctask.cancel(true);
                            cartasynctask.execute(null);
                        } else
                        {
                            cartasynctask.execute(null);
                        }
                    } else
                    if(result == -19)
                    {
                        cancelProgress();
                        showToast("\u8BA2\u5355\u4E0D\u5B58\u5728");
                    } else
                    {
                        cancelProgress();
                        try
                        {
                            showToast(as[-result]);
                        }
                        catch(Exception exception)
                        {
                            util.showNetNull();
                        }
                    }
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L4
_L4:
                cancelProgress();
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    switch(result)
                    {
                    case 0: // '\0'
                    default:
                        util.showNetNull();
                        break;

                    case 1: // '\001'
                        showToast("\u64CD\u4F5C\u6210\u529F");
                        finish();
                        break;

                    case -1: 
                        showToast("\u64CD\u4F5C\u51FA\u9519");
                        break;
                    }
                } else
                {
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L6
_L6:
_L2:
                cancelProgress();
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    switch(result)
                    {
                    default:
                        util.showNetNull();
                        break;

                    case 1: // '\001'
                        (new android.app.AlertDialog.Builder(Order.this)).setTitle("\u4FE1\u606F").setMessage("\u4E0B\u5355\u6210\u529F").setNeutralButton("\u67E5\u770B\u6211\u7684\u8BA2\u5355", new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                util.changeMain("com.thestore.main.MyOrder");
                                finish();
                            }

                            final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        Object();
                    }
                        }
).setNegativeButton("\u6211\u8981\u6652\u5355", new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                if(DBHelper.testNet())
                                {
                                    showProgress();
                                    Intent intent = new Intent("com.thestore.main.ShowOrder");
                                    intent.putExtra("SHOWORDER_ID", "");
                                    intent.putExtra("ORDER_COUNT", orderItemList.size());
                                    intent.putExtra("ORDER_AREA", User.province);
                                    for(int j = 0; j < orderItemList.size(); j++)
                                    {
                                        ProductVO productvo = ((OrderItemVO)orderItemList.get(j)).getProduct();
                                        intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_ID").toString(), productvo.getProductId());
                                        intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_NAME").toString(), productvo.getCnName());
                                        intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_IMAGE").toString(), productvo.getMiniDefaultProductUrl());
                                        intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_PRICE").toString(), productvo.getPrice());
                                    }

                                    startActivity(intent);
                                    finish();
                                } else
                                {
                                    util.showNetNull();
                                }
                            }

                            final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        Object();
                    }
                        }
).create().show();
                        break;

                    case 2131296342: 
                        if(message.obj == null);
                        break;
                    }
                } else
                {
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L7
_L7:
            }

            final Order this$0;

            
            {
                this$0 = Order.this;
                Handler();
            }
        }
;
    }

    private boolean checkOrder(String s, String s1)
    {
        boolean flag;
        if(!s1.equals("\u65E0") && !s.equals("0.00"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void initViews()
    {
        totalPricetTextView = (TextView)findViewById(0x7f09013b);
        deliveryAmountTextView = (TextView)findViewById(0x7f09013c);
        accountAmountTextView = (TextView)findViewById(0x7f09013d);
        couponAmountTextView = (TextView)findViewById(0x7f09013e);
        paymentAccountTextView = (TextView)findViewById(0x7f09013f);
        if(orderVO.getProductAmount() != null)
            productAmount = (new DecimalFormat("0.00")).format(orderVO.getProductAmount());
        totalPricetTextView.setText((new StringBuilder()).append("\uFFE5").append(productAmount).toString());
        if(orderVO.getDeliveryAmount() != null)
            deliverAmount = (new DecimalFormat("0.00")).format(orderVO.getDeliveryAmount());
        deliveryAmountTextView.setText((new StringBuilder()).append("\uFFE5").append(deliverAmount).toString());
        if(orderVO.getAccountAmount() != null)
            accountAmount = (new DecimalFormat("0.00")).format(orderVO.getAccountAmount());
        accountAmountTextView.setText((new StringBuilder()).append("\uFFE5").append(accountAmount).toString());
        if(orderVO.getCouponAmount() != null)
            couponAmount = (new DecimalFormat("0.00")).format(orderVO.getCouponAmount());
        couponAmountTextView.setText((new StringBuilder()).append("\uFFE5").append(couponAmount).toString());
        if(orderVO.getPaymentAccount() != null)
            paymentAccount = (new DecimalFormat("0.00")).format(orderVO.getOrderAmount());
        paymentAccountTextView.setText((new StringBuilder()).append("\uFFE5").append(paymentAccount).toString());
        addressTextView = (TextView)findViewById(0x7f090140);
        goodReceiverVO = orderVO.getGoodReceiver();
        if(goodReceiverVO != null)
        {
            String s = "";
            if(goodReceiverVO.getReceiverMobile() != null)
                s = (new StringBuilder()).append(s).append("\n").append(goodReceiverVO.getReceiverMobile()).toString();
            if(goodReceiverVO.getReceiverPhone() != null)
                s = (new StringBuilder()).append(s).append("\n").append(goodReceiverVO.getReceiverPhone()).toString();
            goodReceiver = (new StringBuilder()).append(goodReceiverVO.getReceiveName()).append("\n").append(goodReceiverVO.getAddress1()).append("\n").append(goodReceiverVO.getProvinceName()).append("  ").append(goodReceiverVO.getCityName()).append("  ").append(goodReceiverVO.getCountyName()).append("\n").append(goodReceiverVO.getPostCode()).append(s).toString();
        }
        addressTextView.setText(goodReceiver);
        productNameTextView = (TextView)findViewById(0x7f090141);
        orderItemList = orderVO.getOrderItemList();
        if(orderItemList != null)
        {
            int i = 0;
            while(i < orderItemList.size()) 
            {
                if(i == 0)
                    productName = (new StringBuilder()).append(((OrderItemVO)orderItemList.get(i)).getProduct().getCnName()).append(" * ").append(((OrderItemVO)orderItemList.get(i)).getBuyQuantity()).toString();
                else
                    productName = (new StringBuilder()).append(productName).append(((OrderItemVO)orderItemList.get(i)).getProduct().getCnName()).append(" * ").append(((OrderItemVO)orderItemList.get(i)).getBuyQuantity()).toString();
                if(orderItemList.size() > 1 && i != orderItemList.size() - 1)
                    productName = (new StringBuilder()).append(productName).append("\n").toString();
                i++;
            }
        }
        productNameTextView.setText(productName);
        sendMethodTextView = (TextView)findViewById(0x7f090142);
        payTypeTextView = (TextView)findViewById(0x7f090143);
        if(orderVO.getDeliveryMethodForString() != null && orderVO.getDeliveryMethodForString().length() > 0)
            sendMethodTextView.setText(orderVO.getDeliveryMethodForString());
        payTypeTextView.setText(orderVO.getPaymentMethodForString());
        useCouponLayout = (LinearLayout)findViewById(0x7f090144);
        useCouponTextView = (TextView)findViewById(0x7f090145);
        if(orderVO.getCoupon() != null)
        {
            couponVO = orderVO.getCoupon();
            coupon = couponVO.getNumber();
        }
        useCouponTextView.setText(coupon);
        useInvoiceteLayout = (LinearLayout)findViewById(0x7f090146);
        useInvoiceteTextView = (TextView)findViewById(0x7f090147);
        if(orderVO.getInvoiceList() != null && orderVO.getInvoiceList().size() > 0 && orderVO.getInvoiceList().get(0) != null)
        {
            invoiceVO = (InvoiceVO)orderVO.getInvoiceList().get(0);
            invoiceStr = invoiceVO.getTitle();
        }
        useInvoiceteTextView.setText(invoiceStr);
        createOrderButton = (Button)findViewById(0x7f090148);
        cancelbButton = (Button)findViewById(0x7f090149);
        if(!orderVO.getPaymentMethodForString().contains("\u8D27\u5230\u4ED8\u6B3E\u652F\u4ED8"))
        {
            payTypeTextView.setTextColor(0xffff0000);
            createOrderButton.setClickable(false);
            createOrderButton.setEnabled(false);
            createOrderButton.setBackgroundResource(0x7f02001c);
        } else
        {
            payTypeTextView.setTextColor(Color.rgb(115, 77, 1));
            createOrderButton.setClickable(true);
            createOrderButton.setEnabled(true);
            createOrderButton.setBackgroundResource(0x7f02001b);
            createOrderButton.setOnTouchListener(new android.view.View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    motionevent.getAction();
                    JVM INSTR tableswitch 0 3: default 36
                //                               0 38
                //                               1 53
                //                               2 36
                //                               3 53;
                       goto _L1 _L2 _L3 _L1 _L3
_L1:
                    return false;
_L2:
                    createOrderButton.setBackgroundResource(0x7f02001c);
                    continue; /* Loop/switch isn't completed */
_L3:
                    createOrderButton.setBackgroundResource(0x7f02001b);
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final Order this$0;

            
            {
                this$0 = Order.this;
                Object();
            }
            }
);
        }
        setListeners();
    }

    private void setListeners()
    {
        addressTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("com.thestore.main.GoodReceiver");
                if(goodReceiverVO != null)
                    intent.putExtra("GOODRECEIVER_ID", goodReceiverVO.getId());
                startActivity(intent);
            }

            final Order this$0;

            
            {
                this$0 = Order.this;
                Object();
            }
        }
);
        useCouponLayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("com.thestore.main.Coupon");
                if(couponVO != null)
                    intent.putExtra("COUPON_ID", couponVO.getId());
                startActivity(intent);
            }

            final Order this$0;

            
            {
                this$0 = Order.this;
                Object();
            }
        }
);
        useInvoiceteLayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("com.thestore.main.Invoice");
                if(invoiceVO != null)
                {
                    intent.putExtra("INVOICE_TITLE", invoiceVO.getTitle());
                    intent.putExtra("INVOICE_AMOUNT", String.valueOf(invoiceVO.getAmount()));
                    intent.putExtra("INVOICE_CONTENT", invoiceVO.getContent());
                } else
                {
                    intent.putExtra("INVOICE_AMOUNT", String.valueOf(productAmount));
                }
                startActivity(intent);
            }

            final Order this$0;

            
            {
                this$0 = Order.this;
                Object();
            }
        }
);
        createOrderButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(checkOrder(productAmount, goodReceiver))
                {
                    if(User.token != null && DBHelper.testNet())
                    {
                        OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003e);
                        if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            orderasynctask.cancel(true);
                            orderasynctask.execute(null);
                        } else
                        {
                            orderasynctask.execute(null);
                        }
                        showProgress();
                    } else
                    {
                        util.changeMain("com.thestore.main.userland");
                    }
                } else
                {
                    util.showToast("\u9001\u8D27\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A!");
                }
            }

            final Order this$0;

            
            {
                this$0 = Order.this;
                Object();
            }
        }
);
        cancelbButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(User.token != null && DBHelper.testNet())
                {
                    OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003b);
                    if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        orderasynctask.cancel(true);
                        orderasynctask.execute(null);
                    } else
                    {
                        orderasynctask.execute(null);
                    }
                    showProgress();
                } else
                {
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final Order this$0;

            
            {
                this$0 = Order.this;
                Object();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030026);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0063);
        try
        {
            if(User.token != null && DBHelper.testNet())
            {
                showProgress();
                OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003a);
                if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    orderasynctask.cancel(true);
                    orderasynctask.execute(null);
                } else
                {
                    orderasynctask.execute(null);
                }
                break MISSING_BLOCK_LABEL_122;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            break MISSING_BLOCK_LABEL_122;
        }
        util.changeMain("com.thestore.main.userland");
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0003, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 3: default 40
    //                   2131296770: 42
    //                   2131296775: 132
    //                   2131296776: 49;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L4:
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            OrderAsyncTask orderasynctask1 = new OrderAsyncTask(myHandler, 0x7f09003b);
            if(orderasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                orderasynctask1.cancel(true);
                orderasynctask1.execute(null);
            } else
            {
                orderasynctask1.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(checkOrder(productAmount, goodReceiver))
        {
            if(User.token != null && DBHelper.testNet())
            {
                OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003e);
                if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    orderasynctask.cancel(true);
                    orderasynctask.execute(null);
                } else
                {
                    orderasynctask.execute(null);
                }
                showProgress();
            } else
            {
                util.changeMain("com.thestore.main.userland");
            }
        } else
        {
            util.showToast("\u9001\u8D27\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A!");
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(orderVO != null)
            if(!orderVO.getPaymentMethodForString().contains("\u8D27\u5230\u4ED8\u6B3E\u652F\u4ED8"))
                menu.findItem(0x7f090207).setEnabled(false);
            else
                menu.findItem(0x7f090207).setEnabled(true);
        return super.onPrepareOptionsMenu(menu);
    }

    protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003a);
            if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                orderasynctask.cancel(true);
                orderasynctask.execute(null);
            } else
            {
                orderasynctask.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
    }

    private String accountAmount;
    private TextView accountAmountTextView;
    private TextView addressTextView;
    private Button cancelbButton;
    private String coupon;
    private String couponAmount;
    private TextView couponAmountTextView;
    private CouponVO couponVO;
    private Button createOrderButton;
    private String deliverAmount;
    private TextView deliveryAmountTextView;
    private String goodReceiver;
    private GoodReceiverVO goodReceiverVO;
    private String invoiceStr;
    private InvoiceVO invoiceVO;
    private Handler myHandler;
    private List orderItemList;
    private OrderVO orderVO;
    private TextView payTypeTextView;
    private String paymentAccount;
    private TextView paymentAccountTextView;
    private String productAmount;
    private String productName;
    private TextView productNameTextView;
    private int result;
    private TextView sendMethodTextView;
    private TextView totalPricetTextView;
    private LinearLayout useCouponLayout;
    private TextView useCouponTextView;
    private LinearLayout useInvoiceteLayout;
    private TextView useInvoiceteTextView;
    private Util util;






/*
    static int access$1102(Order order, int i)
    {
        order.result = i;
        return i;
    }

*/











/*
    static OrderVO access$902(Order order, OrderVO ordervo)
    {
        order.orderVO = ordervo;
        return ordervo;
    }

*/
}
