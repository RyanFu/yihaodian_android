// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thestore.net.CartAsyncTask;
import com.thestore.net.DBHelper;
import com.thestore.net.FavoriteAsyncTask;
import com.thestore.net.OrderAsyncTask;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.thestore.util.ViewHolder;
import com.yihaodian.mobile.vo.cart.CartItemVO;
import com.yihaodian.mobile.vo.cart.CartVO;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Home

public class Cart extends ActivityMain
{

    public Cart()
    {
        inputNumIntegers = new ArrayList();
        toCheckOutButton = null;
        result = 0;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 5: default 56
            //                           2131296299: 234
            //                           2131296301: 864
            //                           2131296302: 1004
            //                           2131296308: 748
            //                           2131296392: 57;
                   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
                return;
_L6:
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    String as[] = getResources().getStringArray(0x7f06000b);
                    if(result == 1)
                    {
                        Intent intent = new Intent("com.thestore.main.Order");
                        startActivity(intent);
                    } else
                    if(result == -21)
                        showToast("\u5E93\u5B58\u4E0D\u8DB3");
                    else
                    if(result == -22)
                        showToast("\u8D2D\u7269\u8F66\u4EA7\u54C1\u4E3A\u7A7A");
                    else
                        try
                        {
                            showToast(as[-result]);
                        }
                        catch(Exception exception1)
                        {
                            util.showNetNull();
                        }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L2:
                listLinearLayout.removeAllViews();
                if(DBHelper.getTimeOut())
                    (new android.app.AlertDialog.Builder(Cart.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                        public boolean onKey(DialogInterface dialoginterface, int l, KeyEvent keyevent)
                        {
                            if(l == 4)
                                finish();
                            return false;
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int l)
                        {
                            finish();
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int l)
                        {
                            if(DBHelper.testNet())
                            {
                                showProgress();
                                CartAsyncTask cartasynctask2 = new CartAsyncTask(handler, 0x7f09002b);
                                if(cartasynctask2.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                {
                                    cartasynctask2.cancel(true);
                                    cartasynctask2.execute(null);
                                } else
                                {
                                    cartasynctask2.execute(null);
                                }
                            } else
                            {
                                util.showNetNull();
                            }
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
).create().show();
                if(message.obj != null)
                {
                    cartVO = (CartVO)message.obj;
                    cartItemList = cartVO.getBuyItemList();
                    gifItemtList = cartVO.getGifItemtList();
                    totalBuy = (long)cartItemList.size();
                    totalGif = (long)gifItemtList.size();
                    totalCount = totalBuy + totalGif;
                    com.thestore.net.Cart.cartTotal = cartVO.getTotalquantity().longValue();
                    setCartImage(Long.valueOf(com.thestore.net.Cart.cartTotal));
                    if(totalCount > 0L)
                    {
                        findViewById(0x7f090074).setVisibility(0);
                        findViewById(0x7f090075).setVisibility(8);
                        totalprice = cartVO.getTotalprice().doubleValue();
                        for(int j = 0; (long)j < totalCount; j++)
                            inputNumIntegers.add(j, (new StringBuilder()).append(((CartItemVO)cartItemList.get(j)).getBuyQuantity()).append("").toString());

                        for(int k = 0; (long)k < totalCount; k++)
                            addViewShop(k);

                        showBottom();
                    } else
                    {
                        findViewById(0x7f090074).setVisibility(8);
                        findViewById(0x7f090075).setVisibility(0);
                        findViewById(0x7f090076).setOnClickListener(new android.view.View.OnClickListener() {

                            public void onClick(View view)
                            {
                                util.changeMain("com.thestore.main.Type");
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                    }
                } else
                {
                    findViewById(0x7f090074).setVisibility(8);
                    findViewById(0x7f090075).setVisibility(0);
                    findViewById(0x7f090076).setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            util.changeMain("com.thestore.main.Type");
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
);
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj == null) goto _L8; else goto _L7
_L7:
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch -1 1: default 792
            //                           -1 838
            //                           0 825
            //                           1 812;
                   goto _L9 _L10 _L11 _L12
_L9:
                util.showNetNull();
_L13:
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L12:
                showToast(0x7f0a0090);
                continue; /* Loop/switch isn't completed */
_L11:
                showToast(0x7f0a0091);
                continue; /* Loop/switch isn't completed */
_L10:
                showToast(0x7f0a0092);
                continue; /* Loop/switch isn't completed */
_L8:
                util.showNetNull();
                if(true) goto _L13; else goto _L3
_L3:
                if(message.obj != null)
                {
                    if(((Integer)message.obj).intValue() == 1)
                    {
                        showToast(0x7f0a0100);
                        if(DBHelper.testNet())
                        {
                            CartAsyncTask cartasynctask1 = new CartAsyncTask(handler, 0x7f09002b);
                            if(cartasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                cartasynctask1.cancel(true);
                                cartasynctask1.execute(null);
                            } else
                            {
                                cartasynctask1.execute(null);
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    } else
                    {
                        showToast(0x7f0a0101);
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    if(((Integer)message.obj).intValue() == 1)
                        showToast("\u66F4\u6539\u5546\u54C1\u6570\u91CF\u6210\u529F");
                    else
                    if(((Integer)message.obj).intValue() == -21)
                    {
                        showToast(0x7f0a0109);
                    } else
                    {
                        int i = ((Integer)message.obj).intValue();
                        try
                        {
                            showToast(buyArray[i]);
                        }
                        catch(Exception exception)
                        {
                            util.showNetNull();
                        }
                    }
                    if(DBHelper.testNet())
                    {
                        CartAsyncTask cartasynctask = new CartAsyncTask(handler, 0x7f09002b);
                        if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            cartasynctask.cancel(true);
                            cartasynctask.execute(null);
                        } else
                        {
                            cartasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                if(true) goto _L1; else goto _L14
_L14:
            }

            final Cart this$0;

            
            {
                this$0 = Cart.this;
                super();
            }
        }
;
    }

    private void addBottomShop(final int position, View view)
    {
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f090080);
        final TextView editText = (TextView)linearlayout.findViewById(0x7f090082);
        final long productId = ((CartItemVO)cartItemList.get(position)).getProduct().getProductId().longValue();
        final int updateType = ((CartItemVO)cartItemList.get(position)).getUpdateType().intValue();
        editText.setText((new StringBuilder()).append((String)inputNumIntegers.get(position)).append("").toString());
        editText.setEnabled(false);
        ((Button)linearlayout.findViewById(0x7f090081)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(((String)inputNumIntegers.get(position)).equals(""))
                    inputNumIntegers.set(position, "0");
                int i = Integer.parseInt((String)inputNumIntegers.get(position));
                if(i > 1)
                {
                    int j = i - 1;
                    editText.setText((new StringBuilder()).append(j).append("").toString());
                    inputNumIntegers.set(position, (new StringBuilder()).append(j).append("").toString());
                    if(DBHelper.testNet())
                    {
                        com.thestore.net.Cart.merchantId = ((CartItemVO)cartItemList.get(position)).getProduct().getMerchantId().longValue();
                        showProgress();
                        CartAsyncTask cartasynctask = new CartAsyncTask(productId, Long.valueOf(j), updateType, handler, 0x7f09002e);
                        if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            cartasynctask.cancel(true);
                            cartasynctask.execute(null);
                        } else
                        {
                            cartasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                }
            }

            final Cart this$0;
            final TextView val$editText;
            final int val$position;
            final long val$productId;
            final int val$updateType;

            
            {
                this$0 = Cart.this;
                position = i;
                editText = textview;
                productId = l;
                updateType = j;
                super();
            }
        }
);
        ((Button)linearlayout.findViewById(0x7f090083)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(((String)inputNumIntegers.get(position)).equals(""))
                    inputNumIntegers.set(position, "0");
                int i = Integer.parseInt((String)inputNumIntegers.get(position));
                if(i < 999)
                {
                    int j = i + 1;
                    editText.setText((new StringBuilder()).append(j).append("").toString());
                    inputNumIntegers.set(position, (new StringBuilder()).append(j).append("").toString());
                    if(DBHelper.testNet())
                    {
                        com.thestore.net.Cart.merchantId = ((CartItemVO)cartItemList.get(position)).getProduct().getMerchantId().longValue();
                        showProgress();
                        CartAsyncTask cartasynctask = new CartAsyncTask(productId, Long.valueOf(j), updateType, handler, 0x7f09002e);
                        if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            cartasynctask.cancel(true);
                            cartasynctask.execute(null);
                        } else
                        {
                            cartasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                }
            }

            final Cart this$0;
            final TextView val$editText;
            final int val$position;
            final long val$productId;
            final int val$updateType;

            
            {
                this$0 = Cart.this;
                position = i;
                editText = textview;
                productId = l;
                updateType = j;
                super();
            }
        }
);
        ((Button)view.findViewById(0x7f09007f)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                (new android.app.AlertDialog.Builder(Cart.this)).setTitle(0x7f0a0040).setMessage(0x7f0a0102).setPositiveButton(0x7f0a00b7, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if(DBHelper.testNet())
                        {
                            com.thestore.net.Cart.merchantId = ((CartItemVO)cartItemList.get(position)).getProduct().getMerchantId().longValue();
                            showProgress();
                            CartAsyncTask cartasynctask = new CartAsyncTask(productId, updateType, handler, 0x7f09002d);
                            if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                cartasynctask.cancel(true);
                                cartasynctask.execute(null);
                            } else
                            {
                                cartasynctask.execute(null);
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    }

                    final _cls6 this$1;

                    
                    {
                        this$1 = _cls6.this;
                        super();
                    }
                }
).setNegativeButton(0x7f0a00f7, null).create().show();
            }

            final Cart this$0;
            final int val$position;
            final long val$productId;
            final int val$updateType;

            
            {
                this$0 = Cart.this;
                position = i;
                productId = l;
                updateType = j;
                super();
            }
        }
);
        ((Button)view.findViewById(0x7f090084)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(DBHelper.testNet())
                {
                    showProgress();
                    FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(productId, handler, 0x7f090034);
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

            final Cart this$0;
            final long val$productId;

            
            {
                this$0 = Cart.this;
                productId = l;
                super();
            }
        }
);
        ((Button)view.findViewById(0x7f090085)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                util.sendSms(((CartItemVO)cartItemList.get(position)).getProduct(), null);
            }

            final Cart this$0;
            final int val$position;

            
            {
                this$0 = Cart.this;
                position = i;
                super();
            }
        }
);
    }

    private void addViewShop(int i)
    {
        ViewHolder viewholder = new ViewHolder();
        final LinearLayout layout = (LinearLayout)inflater.inflate(0x7f030006, null);
        layout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                LinearLayout linearlayout = (LinearLayout)layout.findViewById(0x7f090080);
                int j = listLinearLayout.getChildCount();
                for(int k = 0; k < j; k++)
                    if(listLinearLayout.getChildAt(k).findViewById(0x7f090080) != null)
                        listLinearLayout.getChildAt(k).findViewById(0x7f090080).setVisibility(8);

                listLinearLayout.postInvalidate();
                linearlayout.setVisibility(0);
            }

            final Cart this$0;
            final LinearLayout val$layout;

            
            {
                this$0 = Cart.this;
                layout = linearlayout;
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

            final Cart this$0;
            final LinearLayout val$layout;

            
            {
                this$0 = Cart.this;
                layout = linearlayout;
                super();
            }
        }
);
        viewholder.imageView = (ImageView)layout.findViewById(0x7f09007b);
        layout.setTag(viewholder);
        String s = ((CartItemVO)cartItemList.get(i)).getProduct().getMiniDefaultProductUrl();
        ((TextView)layout.findViewById(0x7f09007c)).setText(((CartItemVO)cartItemList.get(i)).getProduct().getCnName());
        ((TextView)layout.findViewById(0x7f09007d)).setText((new StringBuilder()).append("\u4EF6\u6570\uFF1A").append(String.valueOf(((CartItemVO)cartItemList.get(i)).getBuyQuantity())).toString());
        TextView textview = (TextView)layout.findViewById(0x7f09007e);
        String s1 = (new DecimalFormat("0.00")).format(((CartItemVO)cartItemList.get(i)).getProduct().getPrice().doubleValue() * (double)((CartItemVO)cartItemList.get(i)).getBuyQuantity().intValue());
        textview.setText((new StringBuilder()).append("\u5C0F\u8BA1\uFF1A\uFFE5").append(s1).toString());
        addBottomShop(i, layout);
        if(gifItemtList.size() > 0)
        {
            ((LinearLayout)layout.findViewById(0x7f090078)).setVisibility(0);
            ((TextView)layout.findViewById(0x7f09007c)).setText(((CartItemVO)gifItemtList.get(i)).getProduct().getCnName());
            ((TextView)layout.findViewById(0x7f09007d)).setText((new StringBuilder()).append("\u4EF6\u6570\uFF1A").append(String.valueOf(((CartItemVO)gifItemtList.get(i)).getBuyQuantity())).toString());
            ((TextView)layout.findViewById(0x7f09007e)).setText((new StringBuilder()).append("\u5C0F\u8BA1\uFF1A\uFFE5").append(((CartItemVO)gifItemtList.get(i)).getProduct().getPrice()).toString());
            ((Button)((LinearLayout)layout.findViewById(0x7f090080)).findViewById(0x7f09007f)).setVisibility(8);
        }
        listLinearLayout.addView(layout);
        View view = new View(this);
        view.setBackgroundResource(0x7f020026);
        listLinearLayout.addView(view);
        viewholder.imageView.setTag(s);
        imageLoader.DisplayImage(s, this, viewholder.imageView);
    }

    private void initView()
    {
        buyArray = getResources().getStringArray(0x7f060006);
    }

    private void showBottom()
    {
        View view = inflater.inflate(0x7f030007, null);
        ((TextView)view.findViewById(0x7f090086)).setText((new StringBuilder()).append(cartVO.getTotalquantity()).append("\u4EF6").toString());
        TextView textview = (TextView)view.findViewById(0x7f090087);
        String s = (new DecimalFormat("0.00")).format(totalprice);
        textview.setText((new StringBuilder()).append("\uFFE5").append(s).toString());
        ((Button)view.findViewById(0x7f090089)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                util.changeMain("com.thestore.main.Type");
            }

            final Cart this$0;

            
            {
                this$0 = Cart.this;
                super();
            }
        }
);
        toCheckOutButton = (Button)view.findViewById(0x7f090088);
        toCheckOutButton.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view1, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 38
            //                           1 53
            //                           2 36
            //                           3 53;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L2:
                toCheckOutButton.setBackgroundResource(0x7f020022);
                continue; /* Loop/switch isn't completed */
_L3:
                toCheckOutButton.setBackgroundResource(0x7f020021);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Cart this$0;

            
            {
                this$0 = Cart.this;
                super();
            }
        }
);
        toCheckOutButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                view1.getId();
                JVM INSTR tableswitch 2131296392 2131296392: default 24
            //                           2131296392 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                if(DBHelper.testNet())
                {
                    showProgress();
                    OrderAsyncTask orderasynctask = new OrderAsyncTask(handler, 0x7f090088);
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
                if(true) goto _L1; else goto _L3
_L3:
            }

            final Cart this$0;

            
            {
                this$0 = Cart.this;
                super();
            }
        }
);
        listLinearLayout.addView(view);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030005);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a00f8);
        initView();
        if(DBHelper.testNet())
        {
            showProgress();
            CartAsyncTask cartasynctask = new CartAsyncTask(handler, 0x7f09002b);
            if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                cartasynctask.cancel(true);
                cartasynctask.execute(null);
            } else
            {
                cartasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        listLinearLayout = (LinearLayout)findViewById(0x7f090074);
        inflater = LayoutInflater.from(this);
        imageLoader = new ImageLoader(getApplicationContext());
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0002, menu);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            Intent intent = new Intent();
            intent.setClass(this, com/thestore/main/Home);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(i, keyevent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296770 2131296774: default 40
    //                   2131296770 42
    //                   2131296771 40
    //                   2131296772 40
    //                   2131296773 74
    //                   2131296774 143;
           goto _L1 _L2 _L1 _L1 _L3 _L4
_L1:
        return true;
_L2:
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/Home);
        startActivity(intent);
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        if(DBHelper.testNet())
        {
            showProgress();
            OrderAsyncTask orderasynctask = new OrderAsyncTask(handler, 0x7f090088);
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
        continue; /* Loop/switch isn't completed */
_L4:
        util.changeMain("com.thestore.main.Type");
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onRestart()
    {
        bottomOnClick(this);
        super.onRestart();
        if(DBHelper.testNet())
        {
            showProgress();
            CartAsyncTask cartasynctask = new CartAsyncTask(handler, 0x7f09002b);
            if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                cartasynctask.cancel(true);
                cartasynctask.execute(null);
            } else
            {
                cartasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
    }

    private String buyArray[];
    private List cartItemList;
    private CartVO cartVO;
    private List gifItemtList;
    private Handler handler;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private List inputNumIntegers;
    private LinearLayout listLinearLayout;
    private int result;
    private Button toCheckOutButton;
    private long totalBuy;
    private long totalCount;
    private long totalGif;
    private double totalprice;
    private Util util;



/*
    static int access$002(Cart cart, int i)
    {
        cart.result = i;
        return i;
    }

*/



/*
    static double access$1002(Cart cart, double d)
    {
        cart.totalprice = d;
        return d;
    }

*/










/*
    static CartVO access$402(Cart cart, CartVO cartvo)
    {
        cart.cartVO = cartvo;
        return cartvo;
    }

*/



/*
    static List access$502(Cart cart, List list)
    {
        cart.cartItemList = list;
        return list;
    }

*/



/*
    static List access$602(Cart cart, List list)
    {
        cart.gifItemtList = list;
        return list;
    }

*/



/*
    static long access$702(Cart cart, long l)
    {
        cart.totalBuy = l;
        return l;
    }

*/



/*
    static long access$802(Cart cart, long l)
    {
        cart.totalGif = l;
        return l;
    }

*/



/*
    static long access$902(Cart cart, long l)
    {
        cart.totalCount = l;
        return l;
    }

*/
}
