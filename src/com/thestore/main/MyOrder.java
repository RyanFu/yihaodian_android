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
import android.text.Html;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.order.OrderVO;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.thestore.main:
//            ActivityMain, MyStore

public class MyOrder extends ActivityMain
    implements android.view.View.OnClickListener
{

    public MyOrder()
    {
        currentPage = 1;
        pageSize = 10;
        listSize = 0;
        fromId = -1;
        isCancelRefresh = Boolean.valueOf(false);
        myOrderLayout = null;
        inflater = null;
        loadingButton = null;
        orderPage = null;
        orderList = null;
        orderVO = null;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296319 2131296321: default 32
            //                           2131296319 33
            //                           2131296320 281
            //                           2131296321 453;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                myOrderLayout.removeAllViews();
                if(message.obj != null)
                {
                    orderPage = (Page)message.obj;
                    if(orderPage != null && orderPage.getObjList() != null)
                    {
                        if(isCancelRefresh.booleanValue())
                        {
                            isCancelRefresh = Boolean.valueOf(false);
                            orderList.clear();
                            orderList = orderPage.getObjList();
                        } else
                        if(orderList != null)
                        {
                            Iterator iterator = orderPage.getObjList().iterator();
                            while(iterator.hasNext()) 
                            {
                                OrderVO ordervo = (OrderVO)iterator.next();
                                orderList.add(ordervo);
                            }
                        } else
                        {
                            orderList = orderPage.getObjList();
                        }
                        listSize = orderList.size();
                        init();
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L3:
                if(message.obj != null)
                {
                    Integer integer1 = (Integer)message.obj;
                    if(integer1.intValue() == 1)
                    {
                        isCancelRefresh = Boolean.valueOf(true);
                        showToast("\u8BA2\u5355\u53D6\u6D88\u6210\u529F\uFF01");
                        showProgress();
                        OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003f, 1, 10);
                        if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            orderasynctask.cancel(true);
                            orderasynctask.execute(null);
                        } else
                        {
                            orderasynctask.execute(null);
                        }
                    } else
                    if(integer1.intValue() == 0)
                    {
                        cancelProgress();
                        showToast("\u8BA2\u5355\u53D6\u6D88\u5931\u8D25\uFF01");
                    } else
                    {
                        cancelProgress();
                        util.showNetNull();
                    }
                } else
                {
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    Integer integer = (Integer)message.obj;
                    if(integer.intValue() == 1)
                    {
                        Intent intent = new Intent("com.thestore.main.Cart");
                        startActivity(intent);
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
                if(true) goto _L1; else goto _L5
_L5:
            }

            final MyOrder this$0;

            
            {
                this$0 = MyOrder.this;
                super();
            }
        }
;
    }

    private String changeFontStyle(String s, String s1, String s2, Date date)
    {
        String s3 = getResources().getString(0x7f0a0071);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        simpledateformat.setTimeZone(TimeZone.getTimeZone("EST"));
        String s4 = simpledateformat.format(date);
        String s5 = (new StringBuilder()).append("<font color=#333333 size=14>").append(s3).append("</font>").toString();
        String s6 = (new StringBuilder()).append("<font color=#734d01 size=14>").append(s).append("</font><br />").toString();
        String s7 = (new StringBuilder()).append("<font color=#a10000 size=14>\uFFE5").append(s1).append("&nbsp;&nbsp;&nbsp;").append(s2).append("</font><br />").toString();
        String s8 = (new StringBuilder()).append("<font color=#666666 size=12>").append(s4).append("&nbsp;&nbsp;&nbsp;\u4E0B\u5355").append("</font>").toString();
        return (new StringBuilder()).append(s5).append(s6).append(s7).append(s8).toString();
    }

    private void createView(String s, final long orderId, String s1, final String orderStatus, Date date, final int index)
    {
        LinearLayout linearlayout;
        Button button;
        linearlayout = (LinearLayout)inflater.inflate(0x7f03001f, null);
        TextView textview = (TextView)linearlayout.findViewById(0x7f090100);
        textview.setText(Html.fromHtml(changeFontStyle(s, s1, orderStatus, date)));
        textview.setTextColor(Color.rgb(51, 51, 51));
        textview.setLineSpacing(10F, 1.0F);
        textview.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    orderVO = (OrderVO)orderList.get(index);
                    Intent intent = new Intent("com.thestore.main.OrderDetail");
                    intent.putExtra("ORDER_ID", orderVO.getOrderId());
                    intent.putExtra("DETAIL_FROM_TYPE", 0x7f09003f);
                    startActivity(intent);
                    finish();
                } else
                {
                    util.showNetNull();
                }
            }

            final MyOrder this$0;
            final int val$index;

            
            {
                this$0 = MyOrder.this;
                index = i;
                super();
            }
        }
);
        button = (Button)linearlayout.findViewById(0x7f090101);
        if(!orderStatus.equals("\u5F85\u7ED3\u7B97")) goto _L2; else goto _L1
_L1:
        button.setBackgroundResource(0x7f020023);
_L3:
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                String s2;
                String s3;
                if(orderStatus.equals("\u5F85\u7ED3\u7B97"))
                {
                    s2 = "\u53D6\u6D88\u8BA2\u5355\u63D0\u793A";
                    s3 = "\u786E\u5B9A\u8981\u53D6\u6D88\u8BA2\u5355\u5417\uFF1F";
                } else
                {
                    s2 = "\u91CD\u65B0\u8D2D\u4E70\u63D0\u793A";
                    s3 = "\u786E\u5B9A\u8981\u91CD\u65B0\u8D2D\u4E70\u8BE5\u8BA2\u5355\u4E2D\u7684\u5546\u54C1\u5417\uFF1F";
                }
                (new android.app.AlertDialog.Builder(MyOrder.this)).setTitle(s2).setIcon(0x7f020068).setMessage(s3).setPositiveButton(0x7f0a0116, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if(User.token != null && DBHelper.testNet())
                        {
                            showProgress();
                            OrderAsyncTask orderasynctask;
                            if(orderStatus.equals("\u5F85\u7ED3\u7B97"))
                                orderasynctask = new OrderAsyncTask(Long.valueOf(orderId), myHandler, 0x7f090040);
                            else
                                orderasynctask = new OrderAsyncTask(Long.valueOf(orderId), myHandler, 0x7f090041);
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

                    final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                }
).setNegativeButton("\u53D6\u6D88", null).create().show();
            }

            final MyOrder this$0;
            final long val$orderId;
            final String val$orderStatus;

            
            {
                this$0 = MyOrder.this;
                orderStatus = s;
                orderId = l;
                super();
            }
        }
);
_L4:
        myOrderLayout.addView(linearlayout);
        return;
_L2:
        button.setBackgroundResource(0x7f020020);
          goto _L3
        Exception exception;
        exception;
          goto _L4
    }

    private void init()
    {
        if(orderList != null && orderList.size() > 0)
        {
            for(int i = 0; i < orderList.size(); i++)
                createView(((OrderVO)orderList.get(i)).getOrderCode(), ((OrderVO)orderList.get(i)).getOrderId().longValue(), ((OrderVO)orderList.get(i)).getOrderAmount().toString(), ((OrderVO)orderList.get(i)).getOrderStatusForString(), ((OrderVO)orderList.get(i)).getOrderCreateTime(), i);

        }
        if(orderPage.getTotalSize().intValue() > listSize)
        {
            loadingButton.setVisibility(0);
            loadingButton.setText("\u52A0\u8F7D\u66F4\u591A");
            loadingButton.setOnClickListener(this);
        } else
        {
            loadingButton.setVisibility(8);
        }
    }

    private void initMyOrderView()
    {
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003f, currentPage, pageSize);
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

    @Override
	public void onClick(View view)
    {
        if(view.getId() == 0x7f0900fe)
        {
            loadingButton.getScrollY();
            currentPage = 1 + currentPage;
            if(orderPage.getTotalSize().intValue() > listSize)
                if(User.token != null && DBHelper.testNet())
                {
                    showProgress();
                    OrderAsyncTask orderasynctask = new OrderAsyncTask(myHandler, 0x7f09003f, currentPage, pageSize);
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
        super.onClick(view);
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03001e);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a006e);
        fromId = getIntent().getIntExtra("FROM_ID", 0);
        myOrderLayout = (LinearLayout)findViewById(0x7f0900fd);
        myOrderLayout.setPadding(10, 10, 15, 0);
        inflater = LayoutInflater.from(this);
        loadingButton = (Button)findViewById(0x7f0900fe);
        loadingButton.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 60
            //                           1 38
            //                           2 36
            //                           3 38;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L3:
                loadingButton.setTextColor(Color.rgb(51, 51, 51));
                continue; /* Loop/switch isn't completed */
_L2:
                loadingButton.setTextColor(Color.rgb(255, 140, 16));
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MyOrder this$0;

            
            {
                this$0 = MyOrder.this;
                super();
            }
        }
);
        if(fromId == 0x7f09011a)
        {
            fromId = -1;
            orderPage = MyStore.orderPage;
            orderList = orderPage.getObjList();
            listSize = orderList.size();
            init();
        } else
        {
            initMyOrderView();
        }
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0000, menu);
        return true;
    }

    @Override
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

    @Override
	protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private int currentPage;
    private int fromId;
    private LayoutInflater inflater;
    private Boolean isCancelRefresh;
    private int listSize;
    private Button loadingButton;
    private Handler myHandler;
    private LinearLayout myOrderLayout;
    private List orderList;
    private Page orderPage;
    private OrderVO orderVO;
    private int pageSize;
    private Util util;





/*
    static Page access$202(MyOrder myorder, Page page)
    {
        myorder.orderPage = page;
        return page;
    }

*/



/*
    static Boolean access$302(MyOrder myorder, Boolean boolean1)
    {
        myorder.isCancelRefresh = boolean1;
        return boolean1;
    }

*/



/*
    static List access$402(MyOrder myorder, List list)
    {
        myorder.orderList = list;
        return list;
    }

*/


/*
    static int access$502(MyOrder myorder, int i)
    {
        myorder.listSize = i;
        return i;
    }

*/






/*
    static OrderVO access$902(MyOrder myorder, OrderVO ordervo)
    {
        myorder.orderVO = ordervo;
        return ordervo;
    }

*/
}
