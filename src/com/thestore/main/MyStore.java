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
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.*;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.favorite.FavoriteVO;
import com.yihaodian.mobile.vo.order.OrderVO;
import com.yihaodian.mobile.vo.product.ProductVO;
import com.yihaodian.mobile.vo.user.UserVO;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class MyStore extends ActivityMain
    implements android.view.View.OnClickListener
{

    public MyStore()
    {
        currentSize = 10;
        messageNum = 0L;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                int i;
                int j;
                i = 3;
                j = 0;
                message.what;
                JVM INSTR lookupswitch 8: default 84
            //                           2131296291: 85
            //                           2131296297: 738
            //                           2131296307: 175
            //                           2131296309: 471
            //                           2131296310: 600
            //                           2131296319: 284
            //                           2131296320: 756
            //                           2131296321: 907;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
                return;
_L2:
                if(message.obj != null)
                    if(((Integer)message.obj).intValue() == 1)
                    {
                        showToast(0x7f0a005b);
                        User.token = null;
                        Cart.cartTotal = 0L;
                        setCartImage(Long.valueOf(0L));
                        util.changeMain("com.thestore.main.userland");
                        finish();
                    } else
                    {
                        showToast(0x7f0a005c);
                    }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    UserVO uservo = (UserVO)message.obj;
                    initMyStoreTitle(uservo);
                    OrderAsyncTask orderasynctask1 = new OrderAsyncTask(handler, 0x7f09003f, 1, currentSize);
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
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L7:
                orderLayout.setVisibility(0);
                orderLayout.removeAllViews();
                if(message.obj != null)
                {
                    MyStore.orderPage = (Page)message.obj;
                    List list1 = MyStore.orderPage.getObjList();
                    int l = list1.size();
                    Integer integer;
                    Intent intent;
                    Integer integer1;
                    OrderAsyncTask orderasynctask;
                    FavoriteAsyncTask favoriteasynctask;
                    List list;
                    int k;
                    ProductVO productvo;
                    FavoriteAsyncTask favoriteasynctask1;
                    if(l <= i)
                        i = l;
                    if(i != 0)
                        for(; j < i; j++)
                        {
                            OrderVO ordervo = (OrderVO)list1.get(j);
                            addViewOrder(j, ordervo);
                        }

                    else
                        initNullMyStoreOreder();
                } else
                {
                    initNullMyStoreOreder();
                }
                cancelProgress();
                favoriteasynctask1 = new FavoriteAsyncTask(1, currentSize, handler, 0x7f090035);
                if(favoriteasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    favoriteasynctask1.cancel(true);
                    favoriteasynctask1.execute(null);
                } else
                {
                    favoriteasynctask1.execute(null);
                }
                continue; /* Loop/switch isn't completed */
_L5:
                favoriteLayout.setVisibility(0);
                favoriteLayout.removeAllViews();
                if(message.obj != null)
                {
                    MyStore.favoritePage = (Page)message.obj;
                    list = MyStore.favoritePage.getObjList();
                    k = list.size();
                    if(k <= i)
                        i = k;
                    if(i != 0)
                        for(; j < i; j++)
                        {
                            productvo = ((FavoriteVO)list.get(j)).getProduct();
                            addViewFavorite(j, productvo);
                        }

                    else
                        initNullMyStoreFavorite();
                } else
                {
                    initNullMyStoreFavorite();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L6:
                if(message.obj == null) goto _L11; else goto _L10
_L10:
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch 0 1: default 640
            //                           0 704
            //                           1 716;
                   goto _L12 _L13 _L14
_L14:
                break MISSING_BLOCK_LABEL_716;
_L12:
                util.showNetNull();
_L11:
                favoriteasynctask = new FavoriteAsyncTask(1, currentSize, handler, 0x7f090035);
                if(favoriteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    favoriteasynctask.cancel(true);
                    favoriteasynctask.execute(null);
                } else
                {
                    favoriteasynctask.execute(null);
                }
                continue; /* Loop/switch isn't completed */
_L13:
                showToast(0x7f0a0101);
                  goto _L11
                showToast(0x7f0a0100);
                  goto _L11
_L3:
                cancelProgress();
                judgeBuySucess(message);
                continue; /* Loop/switch isn't completed */
_L8:
                if(message.obj != null)
                {
                    integer1 = (Integer)message.obj;
                    if(integer1.intValue() == 1)
                    {
                        showToast("\u8BA2\u5355\u53D6\u6D88\u6210\u529F\uFF01");
                        showProgress();
                        orderasynctask = new OrderAsyncTask(handler, 0x7f09003f, 1, currentSize);
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
                        showToast("\u8BA2\u5355\u53D6\u6D88\u5931\u8D25\uFF01");
                    else
                        util.showNetNull();
                } else
                {
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L9:
                if(message.obj != null)
                {
                    integer = (Integer)message.obj;
                    if(integer.intValue() == 1)
                    {
                        intent = new Intent("com.thestore.main.Cart");
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
                if(true) goto _L1; else goto _L15
_L15:
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
;
    }

    private void addViewFavorite(int i, final ProductVO list)
    {
        View view = inflater.inflate(0x7f030021, null);
        ((LinearLayout)view.findViewById(0x7f090111)).setVisibility(8);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f09010c);
        final LinearLayout layout;
        ViewHolder viewholder;
        String s;
        TextView textview;
        StringBuilder stringbuilder;
        Object obj;
        if(i == 0)
        {
            ((TextView)view.findViewById(0x7f09010f)).setVisibility(0);
            linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    util.changeMain("com.thestore.main.Favorite");
                }

                final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
            }
);
            ((ImageView)view.findViewById(0x7f090110)).setVisibility(0);
        } else
        {
            linearlayout.setVisibility(8);
        }
        layout = (LinearLayout)view.findViewById(0x7f090114);
        viewholder = new ViewHolder();
        viewholder.imageView = (ImageView)view.findViewById(0x7f090115);
        view.setTag(viewholder);
        s = list.getMiniDefaultProductUrl();
        layout.setVisibility(0);
        layout.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view1, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 38
            //                           1 50
            //                           2 36
            //                           3 50;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L2:
                layout.setBackgroundResource(0x7f0200b9);
                continue; /* Loop/switch isn't completed */
_L3:
                layout.setBackgroundColor(-1);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MyStore this$0;
            final LinearLayout val$layout;

            
            {
                this$0 = MyStore.this;
                layout = linearlayout;
                super();
            }
        }
);
        layout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(DBHelper.testNet())
                {
                    Intent intent = new Intent("com.thestore.main.ProductDetail");
                    intent.putExtra("PRODUCT_ID", list.getProductId());
                    startActivity(intent);
                } else
                {
                    util.showNetNull();
                }
            }

            final MyStore this$0;
            final ProductVO val$list;

            
            {
                this$0 = MyStore.this;
                list = productvo;
                super();
            }
        }
);
        ((TextView)view.findViewById(0x7f090116)).setText(list.getCnName());
        textview = (TextView)view.findViewById(0x7f090117);
        stringbuilder = (new StringBuilder()).append("\uFFE5");
        if(list.getPrice() != null)
            obj = list.getPrice();
        else
            obj = "0.00";
        textview.setText(stringbuilder.append(obj).toString());
        buyArray = getResources().getStringArray(0x7f060006);
        ((Button)view.findViewById(0x7f090118)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                (new android.app.AlertDialog.Builder(MyStore.this)).setTitle(0x7f0a0040).setMessage(0x7f0a0093).setPositiveButton(0x7f0a00b7, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        if(DBHelper.testNet())
                        {
                            showProgress();
                            FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(list.getProductId().longValue(), handler, 0x7f090036);
                            if(favoriteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                favoriteasynctask.cancel(true);
                                favoriteasynctask.execute(null);
                            } else
                            {
                                favoriteasynctask.execute(null);
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    }

                    final _cls14 this$1;

                    
                    {
                        this$1 = _cls14.this;
                        super();
                    }
                }
).setNegativeButton(0x7f0a00f7, null).create().show();
            }

            final MyStore this$0;
            final ProductVO val$list;

            
            {
                this$0 = MyStore.this;
                list = productvo;
                super();
            }
        }
);
        favoriteLayout.addView(view);
        viewholder.imageView.setTag(s);
        imageLoader.DisplayImage(s, this, viewholder.imageView);
    }

    private void addViewOrder(int i, final OrderVO orderVO)
    {
        View view = inflater.inflate(0x7f030022, null);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f090119);
        final LinearLayout layout;
        TextView textview;
        int j;
        SpannableString spannablestring;
        TextView textview1;
        String s;
        TextView textview2;
        SimpleDateFormat simpledateformat;
        String s1;
        if(i == 0)
        {
            ((TextView)view.findViewById(0x7f09011b)).setVisibility(0);
            ((ImageView)view.findViewById(0x7f090108)).setVisibility(0);
        } else
        {
            linearlayout.setVisibility(8);
        }
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                Intent intent = new Intent("com.thestore.main.MyOrder");
                intent.putExtra("FROM_ID", 0x7f09011a);
                startActivity(intent);
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
        layout = (LinearLayout)view.findViewById(0x7f09011e);
        layout.setVisibility(0);
        layout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(DBHelper.testNet())
                {
                    Intent intent = new Intent("com.thestore.main.OrderDetail");
                    intent.putExtra("ORDER_ID", orderVO.getOrderId());
                    intent.putExtra("DETAIL_FROM_TYPE", 0x7f09011a);
                    startActivity(intent);
                } else
                {
                    util.showNetNull();
                }
            }

            final MyStore this$0;
            final OrderVO val$orderVO;

            
            {
                this$0 = MyStore.this;
                orderVO = ordervo;
                super();
            }
        }
);
        layout.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view1, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 38
            //                           1 50
            //                           2 36
            //                           3 50;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L2:
                layout.setBackgroundResource(0x7f0200b9);
                continue; /* Loop/switch isn't completed */
_L3:
                layout.setBackgroundColor(-1);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final MyStore this$0;
            final LinearLayout val$layout;

            
            {
                this$0 = MyStore.this;
                layout = linearlayout;
                super();
            }
        }
);
        textview = (TextView)view.findViewById(0x7f09011f);
        j = orderVO.getOrderCode().length();
        spannablestring = new SpannableString(orderVO.getOrderCode());
        spannablestring.setSpan(new ForegroundColorSpan(Color.rgb(115, 77, 1)), 0, j, 33);
        textview.append(spannablestring);
        textview1 = (TextView)view.findViewById(0x7f090120);
        s = (new DecimalFormat("0.00")).format(orderVO.getOrderAmount());
        textview1.setText((new StringBuilder()).append("\uFFE5").append(s).toString());
        textview1.append((new StringBuilder()).append("   ").append(orderVO.getOrderStatusForString()).toString());
        textview2 = (TextView)view.findViewById(0x7f090121);
        simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        simpledateformat.setTimeZone(TimeZone.getTimeZone("EST"));
        s1 = simpledateformat.format(orderVO.getOrderCreateTime());
        textview2.setText((new StringBuilder()).append(s1).append(" \u4E0B\u5355").toString());
        if(orderVO.getOrderStatusForString().equals("\u5F85\u7ED3\u7B97"))
        {
            Button button1 = (Button)view.findViewById(0x7f090122);
            button1.setBackgroundResource(0x7f020023);
            button1.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    (new android.app.AlertDialog.Builder(MyStore.this)).setTitle("\u53D6\u6D88\u8BA2\u5355\u63D0\u793A").setMessage("\u786E\u5B9A\u8981\u53D6\u6D88\u8BA2\u5355\u5417\uFF1F").setPositiveButton(0x7f0a00b7, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int k)
                        {
                            if(User.token != null && DBHelper.testNet())
                            {
                                showProgress();
                                OrderAsyncTask orderasynctask = new OrderAsyncTask(orderVO.getOrderId(), handler, 0x7f090040);
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

                        final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super();
                    }
                    }
).setNegativeButton(0x7f0a00f7, null).create().show();
                }

                final MyStore this$0;
                final OrderVO val$orderVO;

            
            {
                this$0 = MyStore.this;
                orderVO = ordervo;
                super();
            }
            }
);
        } else
        {
            try
            {
                Button button = (Button)view.findViewById(0x7f090122);
                button.setBackgroundResource(0x7f020020);
                button.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view1)
                    {
                        (new android.app.AlertDialog.Builder(MyStore.this)).setTitle("\u91CD\u65B0\u8D2D\u4E70\u63D0\u793A").setIcon(0x7f020068).setMessage("\u786E\u5B9A\u8981\u91CD\u65B0\u8D2D\u4E70\u8BE5\u8BA2\u5355\u4E2D\u7684\u5546\u54C1\u5417\uFF1F").setPositiveButton(0x7f0a00b7, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int k)
                            {
                                if(User.token != null && DBHelper.testNet())
                                {
                                    showProgress();
                                    OrderAsyncTask orderasynctask = new OrderAsyncTask(orderVO.getOrderId(), handler, 0x7f090041);
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

                            final _cls10 this$1;

                    
                    {
                        this$1 = _cls10.this;
                        super();
                    }
                        }
).setNegativeButton(0x7f0a00f7, null).create().show();
                    }

                    final MyStore this$0;
                    final OrderVO val$orderVO;

            
            {
                this$0 = MyStore.this;
                orderVO = ordervo;
                super();
            }
                }
);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        orderLayout.addView(view);
    }

    private void init()
    {
        mailTextView = (TextView)findViewById(0x7f090103);
        messageTextView = (TextView)findViewById(0x7f090107);
        couponTextView = (TextView)findViewById(0x7f090104);
        numTextView = (TextView)findViewById(0x7f090105);
        modifyPwdButton = (Button)findViewById(0x7f090109);
        receiverButton = (Button)findViewById(0x7f09010a);
        modifyPwdButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("com.thestore.main.userland");
                intent.putExtra("USER_NAME", mailTextView.getText().toString().trim());
                intent.putExtra("VIEW_ID", 0x7f0901fc);
                startActivity(intent);
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
        receiverButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("com.thestore.main.GoodReceiver");
                intent.putExtra("SET_GOODRECEIVER", 0x7f090043);
                startActivity(intent);
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
        ((Button)findViewById(0x7f09010d)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new android.app.AlertDialog.Builder(MyStore.this)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(getResources().getString(0x7f0a005d)).setPositiveButton(0x7f0a0116, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if(DBHelper.testNet())
                        {
                            showProgress();
                            UserAsyncTask userasynctask = new UserAsyncTask(0x7f090023, handler);
                            if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                userasynctask.cancel(true);
                                userasynctask.execute(null);
                            } else
                            {
                                userasynctask.execute(null);
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
).setNegativeButton(0x7f0a0065, null).create().show();
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
    }

    private void initMyStoreTitle(UserVO uservo)
    {
        userName = uservo.getEmail();
        mailTextView.setText(userName);
        messageNum = uservo.getMessageCount().longValue();
        String s = (new StringBuilder()).append(messageNum).append("\u6761").toString();
        android.text.Spanned spanned = Html.fromHtml((new StringBuilder()).append("<font color=#9E0000>").append(s).append("</font>\u65B0\u4FE1\u606F").toString());
        messageTextView.setText(spanned);
        android.text.Spanned spanned1 = Html.fromHtml((new StringBuilder()).append("1\u53F7\u5E97\u79EF\u5206\uFF1A<font color=#9E0000>").append(uservo.getPoints()).append("</font>").toString());
        couponTextView.setText(spanned1);
        StringBuilder stringbuilder = (new StringBuilder()).append("  \u62B5\u7528\u5238\uFF1A<font color=#9E0000>");
        Object obj;
        android.text.Spanned spanned2;
        if(uservo.getCouponCount().longValue() == 0L)
            obj = "\u6682\u65E0";
        else
            obj = uservo.getCouponCount();
        spanned2 = Html.fromHtml(stringbuilder.append(obj).append("</font>").toString());
        numTextView.setText(spanned2);
        findViewById(0x7f090106).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(User.token != null)
                {
                    util.changeMain("com.thestore.main.Message");
                } else
                {
                    User.TYPE = 0x7f090002;
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
    }

    private void initNet()
    {
        if(User.token != null)
        {
            if(DBHelper.testNet())
            {
                showProgress();
                UserAsyncTask userasynctask = new UserAsyncTask(0x7f090033, handler);
                if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    userasynctask.cancel(true);
                    userasynctask.execute(null);
                } else
                {
                    userasynctask.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
    }

    private void initNullMyStoreFavorite()
    {
        View view = inflater.inflate(0x7f030021, null);
        ((TextView)view.findViewById(0x7f09010f)).setVisibility(8);
        ((ImageView)view.findViewById(0x7f090110)).setVisibility(8);
        ((LinearLayout)view.findViewById(0x7f090114)).setVisibility(8);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f090111);
        linearlayout.setVisibility(0);
        TextView textview = (TextView)view.findViewById(0x7f090112);
        SpannableString spannablestring = new SpannableString(getResources().getString(0x7f0a0059));
        spannablestring.setSpan(new ForegroundColorSpan(Color.rgb(102, 102, 102)), 0, 5, 33);
        textview.setText(spannablestring);
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                util.changeMain("com.thestore.main.Type");
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
        favoriteLayout.addView(view);
    }

    private void initNullMyStoreOreder()
    {
        View view = inflater.inflate(0x7f030022, null);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f09011c);
        linearlayout.setVisibility(0);
        TextView textview = (TextView)view.findViewById(0x7f09011d);
        SpannableString spannablestring = new SpannableString(getResources().getString(0x7f0a0058));
        spannablestring.setSpan(new ForegroundColorSpan(Color.rgb(102, 102, 102)), 0, 5, 33);
        textview.setText(spannablestring);
        linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                util.changeMain("com.thestore.main.Type");
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
);
        orderLayout.addView(view);
    }

    private void judgeBuySucess(Message message)
    {
        if(message.obj != null)
            if(((Integer)message.obj).intValue() == 1)
                showToast(0x7f0a0108);
            else
            if(((Integer)message.obj).intValue() == -21)
            {
                showToast(0x7f0a0109);
            } else
            {
                int i = ((Integer)message.obj).intValue();
                try
                {
                    showToast(buyArray[-i]);
                }
                catch(Exception exception)
                {
                    util.showNetNull();
                }
            }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030020);
        util = new Util(this);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0050);
        init();
        initNet();
        inflater = LayoutInflater.from(this);
        favoriteLayout = (LinearLayout)findViewById(0x7f09010c);
        orderLayout = (LinearLayout)findViewById(0x7f09010b);
        imageLoader = new ImageLoader(getApplicationContext());
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0007, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 5: default 56
    //                   2131296770: 58
    //                   2131296778: 65
    //                   2131296785: 103
    //                   2131296786: 179
    //                   2131296787: 116;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return true;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        if(User.token != null)
        {
            util.changeMain("com.thestore.main.userland");
        } else
        {
            User.TYPE = 0x7f090002;
            util.changeMain("com.thestore.main.Message");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        util.changeMain("com.thestore.main.MyOrder");
        continue; /* Loop/switch isn't completed */
_L6:
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(getResources().getString(0x7f0a005d)).setPositiveButton(0x7f0a0116, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if(DBHelper.testNet())
                {
                    showProgress();
                    UserAsyncTask userasynctask = new UserAsyncTask(0x7f090023, handler);
                    if(userasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        userasynctask.cancel(true);
                        userasynctask.execute(null);
                    } else
                    {
                        userasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final MyStore this$0;

            
            {
                this$0 = MyStore.this;
                super();
            }
        }
).setNegativeButton(0x7f0a0065, null).create().show();
        continue; /* Loop/switch isn't completed */
_L5:
        util.changeMain("com.thestore.main.Favorite");
        if(true) goto _L1; else goto _L7
_L7:
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(orderPage != null && orderPage.getTotalSize().intValue() < 1)
            menu.findItem(0x7f090211).setEnabled(false);
        if(favoritePage != null && favoritePage.getTotalSize().intValue() < 1)
            menu.findItem(0x7f090212).setEnabled(false);
        return super.onPrepareOptionsMenu(menu);
    }

    protected void onRestart()
    {
        super.onRestart();
        initNet();
        bottomOnClick(this);
    }

    public static Page favoritePage;
    public static Page orderPage;
    public static String userName;
    private String buyArray[];
    private TextView couponTextView;
    private int currentSize;
    private LinearLayout favoriteLayout;
    private Handler handler;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private TextView mailTextView;
    private long messageNum;
    private TextView messageTextView;
    private Button modifyPwdButton;
    private TextView numTextView;
    private LinearLayout orderLayout;
    private Button receiverButton;
    private Util util;












}
