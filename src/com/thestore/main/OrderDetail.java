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
import com.yihaodian.mobile.vo.order.OrderItemVO;
import com.yihaodian.mobile.vo.order.OrderVO;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class OrderDetail extends ActivityMain
{

    public OrderDetail()
    {
        fromType = -1;
        orderDetailLayout = null;
        productListLayout = null;
        orderCodeTextView = null;
        orderStatusteTextView = null;
        receiveTextView = null;
        deliveryMethodteTextView = null;
        payMethodteTextView = null;
        inflater = null;
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
        orderOPbButton = null;
        showButton = null;
        orderDetailVO = null;
        orderItems = null;
        goodReceiverVO = null;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 4: default 48
            //                           3: 352
            //                           2131296313: 49
            //                           2131296320: 124
            //                           2131296321: 251;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L3:
                if(message.obj != null)
                {
                    orderDetailVO = (OrderVO)message.obj;
                    Intent intent;
                    int i;
                    ProductVO productvo;
                    Integer integer;
                    Intent intent1;
                    Integer integer1;
                    Intent intent2;
                    if(orderDetailVO != null)
                        try
                        {
                            initOrderDetailView();
                        }
                        catch(Exception exception) { }
                    else
                        util.showNetNull();
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    integer1 = (Integer)message.obj;
                    if(integer1.intValue() == 1)
                    {
                        showToast("\u8BA2\u5355\u53D6\u6D88\u6210\u529F\uFF01");
                        intent2 = new Intent("com.thestore.main.MyOrder");
                        intent2.putExtra("FROM_ID", 0x7f09003f);
                        startActivity(intent2);
                        finish();
                    } else
                    if(integer1.intValue() == 0)
                        showToast("\u8BA2\u5355\u53D6\u6D88\u5931\u8D25\uFF01");
                    else
                        util.showNetNull();
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj != null)
                {
                    integer = (Integer)message.obj;
                    if(integer.intValue() == 1)
                    {
                        intent1 = new Intent("com.thestore.main.Cart");
                        startActivity(intent1);
                    } else
                    if(integer.intValue() == 0)
                        showToast("\u64CD\u4F5C\u5931\u8D25\uFF01");
                    else
                        util.showNetNull();
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L2:
                cancelProgress();
                if(message.obj != null)
                {
                    (Boolean)message.obj;
                    if(DBHelper.testNet())
                    {
                        intent = new Intent("com.thestore.main.ShowOrder");
                        intent.putExtra("SHOWORDER_ID", orderDetailVO.getOrderCode());
                        intent.putExtra("ORDER_COUNT", orderItems.size());
                        intent.putExtra("ORDER_AREA", User.province);
                        for(i = 0; i < orderItems.size(); i++)
                        {
                            productvo = ((OrderItemVO)orderItems.get(i)).getProduct();
                            intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(i).append("_ID").toString(), productvo.getProductId());
                            intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(i).append("_NAME").toString(), productvo.getCnName());
                            intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(i).append("_IMAGE").toString(), productvo.getMiniDefaultProductUrl());
                            intent.putExtra((new StringBuilder()).append("ORDER_ITEM_").append(i).append("_PRICE").toString(), productvo.getPrice());
                        }

                        startActivity(intent);
                    } else
                    {
                        util.showNetNull();
                    }
                }
                if(true) goto _L1; else goto _L6
_L6:
            }

            final OrderDetail this$0;

            
            {
                this$0 = OrderDetail.this;
                super();
            }
        }
;
    }

    private void createOrderProductView(OrderItemVO orderitemvo, int i)
    {
        LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f030029, null);
        TextView textview = (TextView)linearlayout.findViewById(0x7f090159);
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f09015a);
        View view = linearlayout.findViewById(0x7f09015b);
        ProductVO productvo = orderitemvo.getProduct();
        textview.setText(productvo.getCnName());
        textview.setTextSize(14F);
        textview.setTextColor(Color.rgb(51, 51, 51));
        textview1.setText((new StringBuilder()).append("\uFFE5").append(productvo.getPrice()).append("   \u6570\u91CF\uFF1A").append(orderitemvo.getBuyQuantity()).toString());
        textview1.setTextSize(12F);
        textview1.setTextColor(Color.rgb(167, 32, 36));
        if(i == orderItems.size() - 1)
            view.setVisibility(8);
        productListLayout.addView(linearlayout);
    }

    private void initOrderDetailView()
    {
        goodReceiverVO = orderDetailVO.getGoodReceiver();
        orderItems = orderDetailVO.getOrderItemList();
        util.setDefaultTitle(0x7f0a0070);
        orderDetailLayout = (LinearLayout)findViewById(0x7f09014a);
        orderDetailLayout.setVisibility(0);
        orderCodeTextView = (TextView)findViewById(0x7f09014b);
        orderCodeTextView.setText((new StringBuilder()).append(getResources().getString(0x7f0a0071)).append(orderDetailVO.getOrderCode()).toString());
        orderStatusteTextView = (TextView)findViewById(0x7f09014c);
        orderStatusteTextView.setText(orderDetailVO.getOrderStatusForString());
        totalPricetTextView = (TextView)findViewById(0x7f09014d);
        deliveryAmountTextView = (TextView)findViewById(0x7f09014e);
        accountAmountTextView = (TextView)findViewById(0x7f09014f);
        couponAmountTextView = (TextView)findViewById(0x7f090150);
        paymentAccountTextView = (TextView)findViewById(0x7f090151);
        if(orderDetailVO.getProductAmount() != null)
            productAmount = (new DecimalFormat("0.00")).format(orderDetailVO.getProductAmount());
        totalPricetTextView.setText((new StringBuilder()).append("\uFFE5").append(productAmount).toString());
        if(orderDetailVO.getDeliveryAmount() != null)
            deliverAmount = (new DecimalFormat("0.00")).format(orderDetailVO.getDeliveryAmount());
        deliveryAmountTextView.setText((new StringBuilder()).append("\uFFE5").append(deliverAmount).toString());
        if(orderDetailVO.getAccountAmount() != null)
            accountAmount = (new DecimalFormat("0.00")).format(orderDetailVO.getAccountAmount());
        accountAmountTextView.setText((new StringBuilder()).append("\uFFE5").append(accountAmount).toString());
        if(orderDetailVO.getCouponAmount() != null)
            couponAmount = (new DecimalFormat("0.00")).format(orderDetailVO.getCouponAmount());
        couponAmountTextView.setText((new StringBuilder()).append("\uFFE5").append(couponAmount).toString());
        if(orderDetailVO.getPaymentAccount() != null)
            paymentAccount = (new DecimalFormat("0.00")).format(orderDetailVO.getOrderAmount());
        paymentAccountTextView.setText((new StringBuilder()).append("\uFFE5").append(paymentAccount).toString());
        productListLayout = (LinearLayout)findViewById(0x7f090152);
        if(orderItems != null && orderItems.size() > 0)
        {
            for(int i = 0; i < orderItems.size(); i++)
                createOrderProductView((OrderItemVO)orderItems.get(i), i);

        }
        orderOPbButton = (Button)findViewById(0x7f090153);
        String s;
        String s1;
        String s2;
        if(orderDetailVO.getOrderStatusForString().equals("\u5F85\u7ED3\u7B97"))
            s = "\u53D6\u6D88\u8BA2\u5355";
        else
            s = "\u91CD\u65B0\u8D2D\u4E70";
        orderOPbButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                String s3;
                String s4;
                if(orderDetailVO.getOrderStatusForString().equals("\u5F85\u7ED3\u7B97"))
                {
                    s3 = "\u53D6\u6D88\u8BA2\u5355\u63D0\u793A";
                    s4 = "\u786E\u5B9A\u8981\u53D6\u6D88\u8BA2\u5355\u5417\uFF1F";
                } else
                {
                    s3 = "\u91CD\u65B0\u8D2D\u4E70\u63D0\u793A";
                    s4 = "\u786E\u5B9A\u8981\u91CD\u65B0\u8D2D\u4E70\u8BE5\u8BA2\u5355\u4E2D\u7684\u5546\u54C1\u5417\uFF1F";
                }
                (new android.app.AlertDialog.Builder(OrderDetail.this)).setTitle(s3).setIcon(0x7f020068).setMessage(s4).setPositiveButton(0x7f0a0116, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        if(User.token != null && DBHelper.testNet())
                        {
                            showProgress();
                            OrderAsyncTask orderasynctask;
                            if(orderDetailVO.getOrderStatusForString().equals("\u5F85\u7ED3\u7B97"))
                                orderasynctask = new OrderAsyncTask(orderDetailVO.getOrderId(), myHandler, 0x7f090040);
                            else
                                orderasynctask = new OrderAsyncTask(orderDetailVO.getOrderId(), myHandler, 0x7f090041);
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
                            util.showNetNull();
                        }
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
).setNegativeButton("\u53D6\u6D88", null).create().show();
            }

            final OrderDetail this$0;

            
            {
                this$0 = OrderDetail.this;
                super();
            }
        }
);
        orderOPbButton.setText(s);
        receiveTextView = (TextView)findViewById(0x7f090154);
        s1 = (new StringBuilder()).append("\u59D3\u540D\uFF1A").append(goodReceiverVO.getReceiveName()).append("\n").append("\u5730\u5740\uFF1A").append(goodReceiverVO.getRecordName()).toString();
        if(goodReceiverVO.getReceiverMobile() != null && goodReceiverVO.getReceiverMobile().length() > 0)
            s1 = (new StringBuilder()).append(s1).append("\n\u624B\u673A\uFF1A").append(goodReceiverVO.getReceiverMobile()).toString();
        if(goodReceiverVO.getReceiverPhone() != null && goodReceiverVO.getReceiverPhone().length() > 0)
            s1 = (new StringBuilder()).append(s1).append("\n\u7535\u8BDD\uFF1A").append(goodReceiverVO.getReceiverPhone()).toString();
        receiveTextView.setText(s1);
        deliveryMethodteTextView = (TextView)findViewById(0x7f090155);
        s2 = (new StringBuilder()).append(orderDetailVO.getDeliveryMethodForString()).append("\n").append("\u9884\u8BA1\u5230\u8D27\u65F6\u95F4\uFF1A").append((new SimpleDateFormat("yyyy-MM-dd")).format(orderDetailVO.getExpectReceiveDateTo())).toString();
        deliveryMethodteTextView.setText(s2);
        payMethodteTextView = (TextView)findViewById(0x7f090156);
        payMethodteTextView.setText(orderDetailVO.getPaymentMethodForString());
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030027);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0070);
        inflater = LayoutInflater.from(this);
        Intent intent = getIntent();
        fromType = intent.getIntExtra("DETAIL_FROM_TYPE", 0);
        long l = intent.getLongExtra("ORDER_ID", 0L);
        if(l > 0L)
        {
            if(User.token != null && DBHelper.testNet())
            {
                showProgress();
                OrderAsyncTask orderasynctask = new OrderAsyncTask(Long.valueOf(l), myHandler, 0x7f090039);
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
                util.showNetNull();
            }
        } else
        {
            finish();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0000, menu);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i != 4) goto _L2; else goto _L1
_L1:
        if(fromType != 0x7f09011a) goto _L4; else goto _L3
_L3:
        util.changeMain("com.thestore.main.Mystore");
        finish();
_L2:
        return super.onKeyDown(i, keyevent);
_L4:
        if(fromType == 0x7f09003f)
        {
            Intent intent = new Intent("com.thestore.main.MyOrder");
            intent.putExtra("FROM_ID", 0x7f09003f);
            startActivity(intent);
            finish();
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296770 2131296770: default 24
    //                   2131296770 26;
           goto _L1 _L2
_L1:
        return true;
_L2:
        finish();
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private String accountAmount;
    private TextView accountAmountTextView;
    private String couponAmount;
    private TextView couponAmountTextView;
    private String deliverAmount;
    private TextView deliveryAmountTextView;
    private TextView deliveryMethodteTextView;
    private int fromType;
    private GoodReceiverVO goodReceiverVO;
    private LayoutInflater inflater;
    private Handler myHandler;
    private TextView orderCodeTextView;
    private LinearLayout orderDetailLayout;
    private OrderVO orderDetailVO;
    private List orderItems;
    private Button orderOPbButton;
    private TextView orderStatusteTextView;
    private TextView payMethodteTextView;
    private String paymentAccount;
    private TextView paymentAccountTextView;
    private String productAmount;
    private LinearLayout productListLayout;
    private TextView receiveTextView;
    private Button showButton;
    private TextView totalPricetTextView;
    private Util util;



/*
    static OrderVO access$002(OrderDetail orderdetail, OrderVO ordervo)
    {
        orderdetail.orderDetailVO = ordervo;
        return ordervo;
    }

*/




}
