// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Promotion extends ActivityMain
    implements android.widget.AdapterView.OnItemClickListener
{
    public class TypeAdapter extends BaseAdapter
    {
        private class ViewHolder
        {

            ImageView imageView;
            final TypeAdapter this$1;

            private ViewHolder()
            {
                this$1 = TypeAdapter.this;
                super();
            }

        }


        @Override
		public int getCount()
        {
            int i;
            if(showCount > 0)
            {
                if(showCount == promotionPage.getTotalSize().intValue())
                    i = showCount;
                else
                    i = 1 + showCount;
            } else
            {
                i = showCount;
            }
            return i;
        }

        @Override
		public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        @Override
		public long getItemId(int i)
        {
            return i;
        }

        @Override
		public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(i <= showCount) goto _L2; else goto _L1
_L1:
            Object obj2 = null;
_L8:
            return ((View) (obj2));
_L2:
            if(i != showCount) goto _L4; else goto _L3
_L3:
            loadingButton = new Button(Promotion.this);
            loadingButton.setText("\u52A0\u8F7D\u66F4\u591A");
            loadingButton.setEnabled(true);
            loadingButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view4)
                {
                    if(DBHelper.testNet())
                    {
                        loadingButton.getScrollY();
                        loadingButton.setText(0x7f0a0107);
                        loadingButton.setEnabled(false);
                        TypeAsyncTask typeasynctask = new TypeAsyncTask(myHandler, 0x7f090052, 1 + pageCount, 0);
                        if(typeasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            typeasynctask.cancel(true);
                            typeasynctask.execute(null);
                        } else
                        {
                            typeasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final TypeAdapter this$1;

                
                {
                    this$1 = TypeAdapter.this;
                    super();
                }
            }
);
            obj2 = loadingButton;
              goto _L5
_L4:
            final ProductVO product = (ProductVO)promotionList.get(i);
            if(view != null) goto _L7; else goto _L6
_L6:
            ViewHolder viewholder1;
            View view1;
            View view3 = inflater.inflate(0x7f030045, null);
            ViewHolder viewholder3 = new ViewHolder();
            viewholder3.imageView = (ImageView)view3.findViewById(0x7f0901c2);
            view3.setTag(viewholder3);
            view1 = view3;
            viewholder1 = viewholder3;
_L9:
            ((TextView)view1.findViewById(0x7f0901c3)).setText(product.getCnName());
            TextView textview = (TextView)view1.findViewById(0x7f0901c4);
            StringBuilder stringbuilder = (new StringBuilder()).append("\uFFE5");
            Object obj;
            TextView textview1;
            StringBuilder stringbuilder1;
            Object obj1;
            TextView textview2;
            LinearLayout linearlayout;
            int j;
            Button button;
            final long productID;
            LinearLayout linearlayout1;
            View view2;
            ViewHolder viewholder2;
            if(product.getPrice() != null)
                obj = product.getPrice();
            else
                obj = "0.00";
            textview.setText(stringbuilder.append(obj).toString());
            textview1 = (TextView)view1.findViewById(0x7f0901dd);
            stringbuilder1 = (new StringBuilder()).append("\uFFE5");
            if(product.getMaketPrice() != null)
                obj1 = product.getMaketPrice();
            else
                obj1 = "0.00";
            textview1.setText(stringbuilder1.append(obj1).toString());
            textview2 = (TextView)view1.findViewById(0x7f0901d8);
            if(product.getCanBuy().booleanValue())
                textview2.setText("\u73B0\u8D27");
            else
                textview2.setText("\u5DF2\u552E\u5B8C");
            viewholder1.imageView.setTag(product.getMiniDefaultProductUrl());
            imageLoader.DisplayImage(product.getMiniDefaultProductUrl(), context, viewholder1.imageView);
            linearlayout = (LinearLayout)view1.findViewById(0x7f0901d9);
            if(((Boolean)showList.get(i)).booleanValue())
                j = 0;
            else
                j = 8;
            linearlayout.setVisibility(j);
            linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view4)
                {
                }

                final TypeAdapter this$1;

                
                {
                    this$1 = TypeAdapter.this;
                    super();
                }
            }
);
            button = (Button)linearlayout.findViewById(0x7f0901da);
            productID = product.getProductId().longValue();
            button.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view4)
                {
                    if(DBHelper.testNet())
                    {
                        if(User.token != null)
                        {
                            showProgress();
                            Cart.merchantId = product.getMerchantId().longValue();
                            CartAsyncTask cartasynctask = new CartAsyncTask(productID, Long.valueOf(1L), myHandler, 0x7f090029);
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
                            User.TYPE = 0x7f090007;
                            util.changeMain("com.thestore.main.userland");
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final TypeAdapter this$1;
                final ProductVO val$product;
                final long val$productID;

                
                {
                    this$1 = TypeAdapter.this;
                    product = productvo;
                    productID = l;
                    super();
                }
            }
);
            ((Button)linearlayout.findViewById(0x7f0901db)).setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view4)
                {
                    if(DBHelper.testNet())
                    {
                        if(User.token != null)
                        {
                            showProgress();
                            FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(productID, myHandler, 0x7f090034);
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
                            User.TYPE = 0x7f090007;
                            util.changeMain("com.thestore.main.userland");
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final TypeAdapter this$1;
                final long val$productID;

                
                {
                    this$1 = TypeAdapter.this;
                    productID = l;
                    super();
                }
            }
);
            ((Button)linearlayout.findViewById(0x7f0901dc)).setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view4)
                {
                    if(DBHelper.testNet())
                    {
                        Intent intent = new Intent("com.thestore.main.ProductDetail");
                        intent.putExtra("PRODUCT_ID", product.getProductId());
                        startActivity(intent);
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final TypeAdapter this$1;
                final ProductVO val$product;

                
                {
                    this$1 = TypeAdapter.this;
                    product = productvo;
                    super();
                }
            }
);
            linearlayout1 = (LinearLayout)view1.findViewById(0x7f0901c0);
            if(!((Boolean)showList.get(i)).booleanValue());
            linearlayout1.setBackgroundResource(0);
            obj2 = view1;
_L5:
            if(true) goto _L8; else goto _L7
_L7:
            ViewHolder viewholder = (ViewHolder)view.getTag();
            if(viewholder == null)
            {
                view2 = inflater.inflate(0x7f030045, null);
                viewholder2 = new ViewHolder();
                viewholder2.imageView = (ImageView)view2.findViewById(0x7f0901c2);
                view2.setTag(viewholder2);
                view1 = view2;
                viewholder1 = viewholder2;
            } else
            {
                viewholder1 = viewholder;
                view1 = view;
            }
              goto _L9
        }

        private Activity context;
        private ImageLoader imageLoader;
        private LayoutInflater inflater;
        final Promotion this$0;

        public TypeAdapter(Activity activity)
        {
            this$0 = Promotion.this;
            super();
            context = activity;
            inflater = LayoutInflater.from(activity);
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }
    }


    public Promotion()
    {
        promotionPage = null;
        promotionList = null;
        showList = new ArrayList();
        showCount = 0;
        inflater = null;
        pageCount = 1;
        flag = false;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 4: default 48
            //                           2131296297: 629
            //                           2131296308: 777
            //                           2131296337: 278
            //                           2131296338: 49;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L5:
                if(message.obj != null)
                {
                    promotionPage = (Page)message.obj;
                    List list = promotionPage.getObjList();
                    if(pageCount != promotionPage.getCurrentPage().intValue() && list.size() > 0)
                    {
                        pageCount = promotionPage.getCurrentPage().intValue();
                        for(int k = 0; k < list.size(); k++)
                        {
                            showList.add(k, Boolean.valueOf(false));
                            promotionList.add(list.get(k));
                        }

                        int j = message + this.access$100;
                        typeAdapter.notifyDataSetChanged();
                    } else
                    {
                        loadingButton.setText("\u52A0\u8F7D\u66F4\u591A");
                        loadingButton.setEnabled(true);
                    }
                } else
                {
                    loadingButton.setText("\u52A0\u8F7D\u66F4\u591A");
                    loadingButton.setEnabled(true);
                }
                continue; /* Loop/switch isn't completed */
_L4:
                System.out.println((new StringBuilder()).append(message.obj).append("==").toString());
                if(message.obj != null)
                {
                    cancelProgress();
                    promotionPage = (Page)message.obj;
                    if(promotionPage.getObjList().size() > 0)
                    {
                        promotionList = promotionPage.getObjList();
                        if(promotionList.size() <= 10)
                            showCount = promotionList.size();
                        else
                            showCount = 10;
                        showList.clear();
                        for(int j = 0; j < showCount; j++)
                            showList.add(j, Boolean.valueOf(false));

                        if(!flag)
                        {
                            typeAdapter = new TypeAdapter(Promotion.this);
                            flag = true;
                        }
                        listView.setAdapter(typeAdapter);
                    }
                    cancelProgress();
                } else
                {
                    cancelProgress();
                    if(DBHelper.getTimeOut())
                        (new android.app.AlertDialog.Builder(Promotion.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

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
                                    TypeAsyncTask typeasynctask = new TypeAsyncTask(myHandler, 0x7f090051);
                                    if(typeasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                    {
                                        typeasynctask.cancel(true);
                                        typeasynctask.execute(null);
                                    } else
                                    {
                                        typeasynctask.execute(null);
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
                    else
                        util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L2:
                cancelProgress();
                if(message.obj != null)
                {
                    if(((Integer)message.obj).intValue() == 1)
                    {
                        Cart.cartTotal = 1L + Cart.cartTotal;
                        setCartImage(Long.valueOf(Cart.cartTotal));
                        showToast(0x7f0a0108);
                    } else
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
                } else
                {
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L3:
                if(message.obj == null)
                    break MISSING_BLOCK_LABEL_879;
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch -1 1: default 820
            //                           -1 866
            //                           0 853
            //                           1 840;
                   goto _L6 _L7 _L8 _L9
_L9:
                break; /* Loop/switch isn't completed */
_L6:
                util.showNetNull();
_L11:
                cancelProgress();
                if(true) goto _L1; else goto _L10
_L10:
                showToast(0x7f0a0090);
                  goto _L11
_L8:
                showToast(0x7f0a0091);
                  goto _L11
_L7:
                showToast(0x7f0a0092);
                  goto _L11
                util.showNetNull();
                  goto _L11
            }

            final Promotion this$0;

            
            {
                this$0 = Promotion.this;
                super();
            }
        }
;
    }

    private void initView()
    {
        inflater = LayoutInflater.from(this);
        listView = (ListView)findViewById(0x7f09017e);
        listView.setOnItemClickListener(this);
        buyArray = getResources().getStringArray(0x7f060006);
        if(DBHelper.testNet())
        {
            showProgress();
            TypeAsyncTask typeasynctask = new TypeAsyncTask(myHandler, 0x7f090051);
            if(typeasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                typeasynctask.cancel(true);
                typeasynctask.execute(null);
            } else
            {
                typeasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03002d);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a00cb);
        initView();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0000, menu);
        return true;
    }

    @Override
	public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        if(i < showCount)
        {
            LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0901d9);
            byte byte0;
            if(((Boolean)showList.get(i)).booleanValue())
                byte0 = 8;
            else
                byte0 = 0;
            linearlayout.setVisibility(byte0);
            for(int j = 0; j < showCount; j++)
                if(j != i)
                    showList.set(j, Boolean.valueOf(false));

            List list = showList;
            boolean flag1;
            if(!((Boolean)showList.get(i)).booleanValue())
                flag1 = true;
            else
                flag1 = false;
            list.set(i, Boolean.valueOf(flag1));
            typeAdapter.notifyDataSetChanged();
        }
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

    private String buyArray[];
    private boolean flag;
    private LayoutInflater inflater;
    private ListView listView;
    private Button loadingButton;
    private Handler myHandler;
    private int pageCount;
    private List promotionList;
    private Page promotionPage;
    private int showCount;
    private List showList;
    private TypeAdapter typeAdapter;
    private Util util;



/*
    static Page access$002(Promotion promotion, Page page)
    {
        promotion.promotionPage = page;
        return page;
    }

*/




/*
    static int access$102(Promotion promotion, int i)
    {
        promotion.pageCount = i;
        return i;
    }

*/





/*
    static List access$302(Promotion promotion, List list)
    {
        promotion.promotionList = list;
        return list;
    }

*/



/*
    static int access$402(Promotion promotion, int i)
    {
        promotion.showCount = i;
        return i;
    }

*/


/*
    static int access$412(Promotion promotion, int i)
    {
        int j = i + promotion.showCount;
        promotion.showCount = j;
        return j;
    }

*/



/*
    static TypeAdapter access$502(Promotion promotion, TypeAdapter typeadapter)
    {
        promotion.typeAdapter = typeadapter;
        return typeadapter;
    }

*/



/*
    static Button access$602(Promotion promotion, Button button)
    {
        promotion.loadingButton = button;
        return button;
    }

*/



/*
    static boolean access$702(Promotion promotion, boolean flag1)
    {
        promotion.flag = flag1;
        return flag1;
    }

*/


}
