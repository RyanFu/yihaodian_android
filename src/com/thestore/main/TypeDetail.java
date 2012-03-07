// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.CategoryVO;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Type

public class TypeDetail extends ActivityMain
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


        public int getCount()
        {
            int i;
            if(showCount > 0)
                i = 1 + showCount;
            else
                i = showCount;
            return i;
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(i <= showCount) goto _L2; else goto _L1
_L1:
            Object obj1 = null;
_L8:
            return ((View) (obj1));
_L2:
            if(i != showCount) goto _L4; else goto _L3
_L3:
            loadingbButton = new Button(TypeDetail.this);
            loadingbButton.setText("\u52A0\u8F7D\u66F4\u591A");
            loadingbButton.setEnabled(true);
            loadingbButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view4)
                {
                    if(DBHelper.testNet())
                    {
                        loadingbButton.getScrollY();
                        loadingbButton.setText(0x7f0a0107);
                        loadingbButton.setEnabled(false);
                        CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(filterCatelogID, sortType, 1 + pageCount, handler, 0x7f09005a);
                        if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            categoryasynctask.cancel(true);
                            categoryasynctask.execute(null);
                        } else
                        {
                            categoryasynctask.execute(null);
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
            obj1 = loadingbButton;
              goto _L5
_L4:
            final ProductVO product = (ProductVO)listProducts.get(i);
            if(view != null) goto _L7; else goto _L6
_L6:
            ViewHolder viewholder1;
            View view1;
            View view3 = inflater.inflate(0x7f030044, null);
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
            textview1 = (TextView)view1.findViewById(0x7f0901d8);
            if(product.getCanBuy().booleanValue())
                textview1.setText("\u73B0\u8D27");
            else
                textview1.setText("\u5DF2\u552E\u5B8C");
            viewholder1.imageView.setTag(product.getMiniDefaultProductUrl());
            imageLoader.DisplayImage(product.getMiniDefaultProductUrl(), context, viewholder1.imageView);
            linearlayout = (LinearLayout)view1.findViewById(0x7f0901d9);
            if(((Boolean)TypeDetail.showList.get(i)).booleanValue())
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
                            CartAsyncTask cartasynctask = new CartAsyncTask(productID, Long.valueOf(1L), handler, 0x7f090029);
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
                            FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(productID, handler, 0x7f090034);
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
            if(!((Boolean)TypeDetail.showList.get(i)).booleanValue());
            linearlayout1.setBackgroundResource(0);
            obj1 = view1;
_L5:
            if(true) goto _L8; else goto _L7
_L7:
            ViewHolder viewholder = (ViewHolder)view.getTag();
            if(viewholder == null)
            {
                view2 = inflater.inflate(0x7f030044, null);
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
        final TypeDetail this$0;

        public TypeAdapter(Activity activity)
        {
            this$0 = TypeDetail.this;
            super();
            context = activity;
            inflater = LayoutInflater.from(activity);
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }
    }


    public TypeDetail()
    {
        pageCount = 1;
        sortType = 2;
        subCategory = null;
        salesTextView = null;
        timeTextView = null;
        priceTextView = null;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 6: default 64
            //                           2131296297: 905
            //                           2131296306: 618
            //                           2131296308: 923
            //                           2131296344: 65
            //                           2131296345: 283
            //                           2131296346: 145;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
                return;
_L5:
                if(message.obj != null)
                {
                    cancelProgress();
                    subCategory = (Page)message.obj;
                    chooseSubCategory();
                } else
                {
                    cancelProgress();
                    if(DBHelper.getTimeOut())
                        util.showToast("\u67E5\u8BE2\u8D85\u65F6");
                    else
                        util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L7:
                if(message.obj != null)
                {
                    List list = ((Page)message.obj).getObjList();
                    for(int k = 0; k < list.size(); k++)
                    {
                        listProducts.add(list.get(k));
                        TypeDetail.showList.add(k, Boolean.valueOf(false));
                    }

                    int j = message + ((android.widget) (this)).;
                    typeAdapter.notifyDataSetChanged();
                } else
                if(DBHelper.getTimeOut())
                    util.showToast("\u67E5\u8BE2\u8D85\u65F6");
                else
                    util.showNetNull();
                continue; /* Loop/switch isn't completed */
_L6:
                if(message.obj != null)
                {
                    Page page1 = (Page)message.obj;
                    if(page1.getObjList().size() > 0)
                    {
                        TextView textview = (TextView)findViewById(0x7f090124);
                        if(textview != null)
                        {
                            textview.setTextColor(-1);
                            textview.setText(lastTitle);
                        }
                        listProducts = page1.getObjList();
                        if(listProducts.size() <= 10)
                            showCount = listProducts.size();
                        else
                            showCount = 10;
                        TypeDetail.showList.clear();
                        for(int j = 0; j < showCount; j++)
                            TypeDetail.showList.add(j, Boolean.valueOf(false));

                        if(!flag)
                        {
                            typeAdapter = new TypeAdapter(TypeDetail.this);
                            flag = true;
                        }
                        listView.setAdapter(typeAdapter);
                    } else
                    {
                        showToast("\u6CA1\u6709\u6307\u5B9A\u5206\u7C7B\u7684\u5546\u54C1");
                    }
                } else
                if(DBHelper.getTimeOut())
                    (new android.app.AlertDialog.Builder(TypeDetail.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                        public boolean onKey(DialogInterface dialoginterface, int l, KeyEvent keyevent)
                        {
                            if(l == 4)
                                finish();
                            return false;
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int l)
                        {
                            finish();
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int l)
                        {
                            if(DBHelper.testNet())
                            {
                                showProgress();
                                CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(filterCatelogID, sortType, pageCount, handler, 0x7f090059);
                                if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                {
                                    categoryasynctask.cancel(true);
                                    categoryasynctask.execute(null);
                                } else
                                {
                                    categoryasynctask.execute(null);
                                }
                            } else
                            {
                                util.showNetNull();
                            }
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
).create().show();
                else
                    util.showNetNull();
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L3:
                if(message.obj != null)
                {
                    Page page = (Page)message.obj;
                    listProducts = page.getObjList();
                    if(listProducts.size() > 0)
                    {
                        int i;
                        if(listProducts.size() <= 10)
                            showCount = listProducts.size();
                        else
                            showCount = 10;
                        for(i = 0; i < showCount; i++)
                            TypeDetail.showList.add(i, Boolean.valueOf(false));

                        if(!flag)
                        {
                            typeAdapter = new TypeAdapter(TypeDetail.this);
                            flag = true;
                        }
                        listView.setAdapter(typeAdapter);
                    } else
                    {
                        finish();
                    }
                } else
                if(DBHelper.getTimeOut())
                    (new android.app.AlertDialog.Builder(TypeDetail.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                        public boolean onKey(DialogInterface dialoginterface, int l, KeyEvent keyevent)
                        {
                            if(l == 4)
                                finish();
                            return false;
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int l)
                        {
                            finish();
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int l)
                        {
                            if(DBHelper.testNet())
                            {
                                showProgress();
                                TypeAsyncTask typeasynctask = new TypeAsyncTask(handler, idArray[Type.typeId], 0x7f090032);
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

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
).create().show();
                else
                    util.showNetNull();
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L2:
                cancelProgress();
                judgeBuySucess(message);
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj == null)
                    break MISSING_BLOCK_LABEL_1024;
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch -1 1: default 968
            //                           -1 1012
            //                           0 1000
            //                           1 988;
                   goto _L8 _L9 _L10 _L11
_L11:
                break; /* Loop/switch isn't completed */
_L8:
                util.showNetNull();
_L13:
                cancelProgress();
                if(true) goto _L1; else goto _L12
_L12:
                showToast(0x7f0a0090);
                  goto _L13
_L10:
                showToast(0x7f0a0091);
                  goto _L13
_L9:
                showToast(0x7f0a0092);
                  goto _L13
                util.showNetNull();
                  goto _L13
            }

            final TypeDetail this$0;

            
            {
                this$0 = TypeDetail.this;
                super();
            }
        }
;
    }

    private void init()
    {
        listView = (ListView)findViewById(0x7f09017e);
        idArray = getResources().getIntArray(0x7f060004);
        listView.setOnItemClickListener(this);
        buyArray = getResources().getStringArray(0x7f060006);
        salesTextView = (TextView)findViewById(0x7f0901d4);
        salesTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                searchProduct(2);
            }

            final TypeDetail this$0;

            
            {
                this$0 = TypeDetail.this;
                super();
            }
        }
);
        timeTextView = (TextView)findViewById(0x7f0901d5);
        timeTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                searchProduct(6);
            }

            final TypeDetail this$0;

            
            {
                this$0 = TypeDetail.this;
                super();
            }
        }
);
        priceTextView = (TextView)findViewById(0x7f0901d6);
        priceTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                searchProduct(3);
            }

            final TypeDetail this$0;

            
            {
                this$0 = TypeDetail.this;
                super();
            }
        }
);
    }

    private void judgeBuySucess(Message message)
    {
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
    }

    protected void chooseSubCategory()
    {
        final List list = subCategory.getObjList();
        final String items[] = new String[list.size()];
        for(int i = 0; i < list.size(); i++)
            items[i] = ((CategoryVO)list.get(i)).getCategoryName();

        (new android.app.AlertDialog.Builder(this)).setTitle("\u5B50\u5206\u7C7B").setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.dismiss();
                CategoryVO categoryvo = (CategoryVO)list.get(j);
                filterCatelogID = categoryvo.getId().longValue();
                ((Button)findViewById(0x7f0901d0)).setText("\u5207\u6362\u5206\u7C7B");
                lastTitle = items[j];
                pageCount = 1;
                if(DBHelper.testNet())
                {
                    showProgress();
                    CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(filterCatelogID, sortType, pageCount, handler, 0x7f090059);
                    if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        categoryasynctask.cancel(true);
                        categoryasynctask.execute(null);
                    } else
                    {
                        categoryasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final TypeDetail this$0;
            final String val$items[];
            final List val$list;

            
            {
                this$0 = TypeDetail.this;
                list = list1;
                items = as;
                super();
            }
        }
).setNegativeButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
            }

            final TypeDetail this$0;

            
            {
                this$0 = TypeDetail.this;
                super();
            }
        }
).create().show();
    }

    protected void initTitle()
    {
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f090123);
        Button button = (Button)findViewById(0x7f0901cf);
        if(button != null)
            relativelayout.removeView(button);
        Button button1 = (Button)findViewById(0x7f0901d0);
        button1.setText("\u5B50\u5206\u7C7B");
        button1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(subCategory == null || subCategory.getTotalSize().intValue() == 0)
                {
                    showProgress();
                    CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(filterCatelogID, handler, 0x7f090058);
                    if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        categoryasynctask.cancel(true);
                        categoryasynctask.execute(null);
                    } else
                    {
                        categoryasynctask.execute(null);
                    }
                } else
                {
                    chooseSubCategory();
                }
            }

            final TypeDetail this$0;

            
            {
                this$0 = TypeDetail.this;
                super();
            }
        }
);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030042);
        util = new Util(this);
        bottomOnClick(this);
        Intent intent = getIntent();
        thisCatelogID = intent.getLongExtra("CategoryID", 0L);
        filterCatelogID = thisCatelogID;
        lastTitle = intent.getStringExtra("CategoryName");
        ((TextView)findViewById(0x7f090124)).setText(lastTitle);
        init();
        if(DBHelper.testNet())
        {
            showProgress();
            CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(filterCatelogID, sortType, pageCount, handler, 0x7f090059);
            if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                categoryasynctask.cancel(true);
                categoryasynctask.execute(null);
            } else
            {
                categoryasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0000, menu);
        return true;
    }

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

    protected void searchProduct(int i)
    {
        if(sortType != i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        sortType = i;
        i;
        JVM INSTR tableswitch 2 6: default 48
    //                   2 112
    //                   3 268
    //                   4 48
    //                   5 48
    //                   6 190;
           goto _L3 _L4 _L5 _L3 _L3 _L6
_L3:
        if(DBHelper.testNet())
        {
            pageCount = 1;
            showProgress();
            CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(filterCatelogID, i, pageCount, handler, 0x7f090059);
            if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                categoryasynctask.cancel(true);
                categoryasynctask.execute(null);
            } else
            {
                categoryasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        if(true) goto _L1; else goto _L4
_L4:
        salesTextView.setBackgroundResource(0x7f020094);
        timeTextView.setBackgroundColor(0);
        priceTextView.setBackgroundColor(0);
        salesTextView.setTextColor(Color.rgb(167, 32, 36));
        timeTextView.setTextColor(Color.rgb(51, 51, 51));
        priceTextView.setTextColor(Color.rgb(51, 51, 51));
          goto _L3
_L6:
        salesTextView.setBackgroundColor(0);
        timeTextView.setBackgroundResource(0x7f020094);
        priceTextView.setBackgroundColor(0);
        salesTextView.setTextColor(Color.rgb(51, 51, 51));
        timeTextView.setTextColor(Color.rgb(167, 32, 36));
        priceTextView.setTextColor(Color.rgb(51, 51, 51));
          goto _L3
_L5:
        salesTextView.setBackgroundColor(0);
        timeTextView.setBackgroundColor(0);
        priceTextView.setBackgroundResource(0x7f020094);
        salesTextView.setTextColor(Color.rgb(51, 51, 51));
        timeTextView.setTextColor(Color.rgb(51, 51, 51));
        priceTextView.setTextColor(Color.rgb(167, 32, 36));
          goto _L3
    }

    public void showList(int i)
    {
        if(DBHelper.testNet())
        {
            TypeAsyncTask typeasynctask = new TypeAsyncTask(handler, idArray[i], 0x7f090032);
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

    public static List showList = new ArrayList();
    private String buyArray[];
    private long filterCatelogID;
    private boolean flag;
    public Handler handler;
    public int idArray[];
    String lastTitle;
    private List listProducts;
    private ListView listView;
    private Button loadingbButton;
    private int pageCount;
    private TextView priceTextView;
    private TextView salesTextView;
    private int showCount;
    private int sortType;
    private Page subCategory;
    private long thisCatelogID;
    private TextView timeTextView;
    private TypeAdapter typeAdapter;
    private Util util;




/*
    static Page access$002(TypeDetail typedetail, Page page)
    {
        typedetail.subCategory = page;
        return page;
    }

*/




/*
    static long access$102(TypeDetail typedetail, long l)
    {
        typedetail.filterCatelogID = l;
        return l;
    }

*/



/*
    static Button access$1102(TypeDetail typedetail, Button button)
    {
        typedetail.loadingbButton = button;
        return button;
    }

*/



/*
    static int access$202(TypeDetail typedetail, int i)
    {
        typedetail.pageCount = i;
        return i;
    }

*/





/*
    static List access$502(TypeDetail typedetail, List list)
    {
        typedetail.listProducts = list;
        return list;
    }

*/



/*
    static int access$602(TypeDetail typedetail, int i)
    {
        typedetail.showCount = i;
        return i;
    }

*/


/*
    static int access$612(TypeDetail typedetail, int i)
    {
        int j = i + typedetail.showCount;
        typedetail.showCount = j;
        return j;
    }

*/



/*
    static TypeAdapter access$702(TypeDetail typedetail, TypeAdapter typeadapter)
    {
        typedetail.typeAdapter = typeadapter;
        return typeadapter;
    }

*/



/*
    static boolean access$802(TypeDetail typedetail, boolean flag1)
    {
        typedetail.flag = flag1;
        return flag1;
    }

*/

}
