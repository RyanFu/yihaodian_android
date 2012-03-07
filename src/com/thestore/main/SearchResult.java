// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.*;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class SearchResult extends ActivityMain
    implements android.widget.AbsListView.OnScrollListener
{
    class DownImage extends AsyncTask
    {

        protected transient Bitmap doInBackground(String as[])
        {
            return null;
        }

        @Override
		protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        final SearchResult this$0;

        DownImage()
        {
            this$0 = SearchResult.this;
            super();
        }
    }

    public class ProductListAdapter extends BaseAdapter
    {
        private class ViewHolder
        {

            ImageView imageView;
            final ProductListAdapter this$1;

            private ViewHolder()
            {
                this$1 = ProductListAdapter.this;
                super();
            }

        }


        @Override
		public int getCount()
        {
            return 1 + showCount;
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
		public View getView(final int position, View view, ViewGroup viewgroup)
        {
            Object obj;
            if(position != showCount)
            {
                inflater = LayoutInflater.from(context);
                View view1 = inflater.inflate(0x7f030033, null);
                ViewHolder viewholder = new ViewHolder();
                viewholder.imageView = (ImageView)view1.findViewById(0x7f0901b1);
                view1.setTag(viewholder);
                String s1 = ((ProductVO)productList.get(position)).getMiniDefaultProductUrl();
                TextView textview = (TextView)view1.findViewById(0x7f0901b2);
                TextView textview1 = (TextView)view1.findViewById(0x7f0901b3);
                TextView textview2 = (TextView)view1.findViewById(0x7f0901b4);
                textview.setText(((ProductVO)productList.get(position)).getCnName());
                StringBuilder stringbuilder = (new StringBuilder()).append("\uFFE5");
                Object obj1;
                String s2;
                LinearLayout linearlayout;
                int i;
                Button button2;
                final long productID;
                LinearLayout linearlayout1;
                if(((ProductVO)productList.get(position)).getPrice() != null)
                    obj1 = ((ProductVO)productList.get(position)).getPrice();
                else
                    obj1 = "0.00";
                textview1.setText(stringbuilder.append(obj1).toString());
                if(((ProductVO)productList.get(position)).getCanBuy().booleanValue())
                    s2 = "\u73B0\u8D27";
                else
                    s2 = "\u7F3A\u8D27";
                textview2.setText(s2);
                linearlayout = (LinearLayout)view1.findViewById(0x7f0901b5);
                if(((Boolean)isShow.get(position)).booleanValue())
                    i = 0;
                else
                    i = 8;
                linearlayout.setVisibility(i);
                button2 = (Button)linearlayout.findViewById(0x7f0901b6);
                productID = ((ProductVO)productList.get(position)).getProductId().longValue();
                button2.setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
					public void onClick(View view2)
                    {
                        if(DBHelper.testNet())
                        {
                            if(User.token != null)
                            {
                                showProgress();
                                Cart.merchantId = ((ProductVO)productList.get(position)).getMerchantId().longValue();
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
                                User.TYPE = 0x7f090006;
                                util.changeMain("com.thestore.main.userland");
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    }

                    final ProductListAdapter this$1;
                    final int val$position;
                    final long val$productID;

                
                {
                    this$1 = ProductListAdapter.this;
                    position = i;
                    productID = l;
                    super();
                }
                }
);
                ((Button)linearlayout.findViewById(0x7f0901b7)).setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
					public void onClick(View view2)
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
                                User.TYPE = 0x7f090006;
                                util.changeMain("com.thestore.main.userland");
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    }

                    final ProductListAdapter this$1;
                    final long val$productID;

                
                {
                    this$1 = ProductListAdapter.this;
                    productID = l;
                    super();
                }
                }
);
                ((Button)linearlayout.findViewById(0x7f0901b8)).setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
					public void onClick(View view2)
                    {
                        if(DBHelper.testNet())
                        {
                            Intent intent = new Intent("com.thestore.main.ProductDetail");
                            intent.putExtra("PRODUCT_ID", ((ProductVO)productList.get(position)).getProductId());
                            startActivity(intent);
                        } else
                        {
                            util.showNetNull();
                        }
                    }

                    final ProductListAdapter this$1;
                    final int val$position;

                
                {
                    this$1 = ProductListAdapter.this;
                    position = i;
                    super();
                }
                }
);
                linearlayout1 = (LinearLayout)view1.findViewById(0x7f090073);
                if(!((Boolean)isShow.get(position)).booleanValue());
                linearlayout1.setBackgroundResource(0);
                viewholder.imageView.setTag(s1);
                imageLoader.DisplayImage(s1, context, viewholder.imageView);
                obj = view1;
            } else
            if(productCount != 0)
            {
                loadingbButton = new Button(SearchResult.this);
                Button button = loadingbButton;
                String s;
                Button button1;
                boolean flag;
                if(showCount == productCount)
                    s = "\u5546\u54C1\u52A0\u8F7D\u5B8C\u6BD5";
                else
                    s = "\u52A0\u8F7D\u66F4\u591A";
                button.setText(s);
                button1 = loadingbButton;
                if(showCount != productCount)
                    flag = true;
                else
                    flag = false;
                button1.setEnabled(flag);
                obj = loadingbButton;
                ((View) (obj)).setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
					public void onClick(View view2)
                    {
                        if(DBHelper.testNet())
                        {
                            loadingbButton.getScrollY();
                            loadingbButton.setText(0x7f0a0107);
                            Object aobj[] = new Object[9];
                            aobj[7] = Integer.valueOf(1 + currentPage);
                            SearchProductDao searchproductdao = new SearchProductDao(keyword, myHandler);
                            if(searchproductdao.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                searchproductdao.cancel(true);
                                searchproductdao.execute(aobj);
                            } else
                            {
                                searchproductdao.execute(aobj);
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    }

                    final ProductListAdapter this$1;

                
                {
                    this$1 = ProductListAdapter.this;
                    super();
                }
                }
);
            } else
            {
                obj = new Button(SearchResult.this);
                ((Button) (obj)).setBackgroundColor(-1);
                ((Button) (obj)).setText(0x7f0a010d);
                ((Button) (obj)).setTextSize(16F);
                ((Button) (obj)).setEnabled(false);
                ((Button) (obj)).setPadding(10, 10, 10, 10);
                ((Button) (obj)).setTextColor(Color.rgb(189, 0, 0));
            }
            return ((View) (obj));
        }

        private Activity context;
        private ImageLoader imageLoader;
        private LayoutInflater inflater;
        final SearchResult this$0;

        public ProductListAdapter(Activity activity)
        {
            this$0 = SearchResult.this;
            super();
            context = activity;
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }
    }


    public SearchResult()
    {
        productCount = 0;
        currentPage = 0;
        keyword = "";
        resultInfo = "";
        isShow = new ArrayList();
        isLoad = new ArrayList();
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 3: default 40
            //                           2131296297: 41
            //                           2131296298: 173
            //                           2131296308: 59;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                cancelProgress();
                judgeBuySucess(message);
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj == null) goto _L6; else goto _L5
_L5:
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch -1 1: default 104
            //                           -1 148
            //                           0 136
            //                           1 124;
                   goto _L7 _L8 _L9 _L10
_L7:
                util.showNetNull();
_L11:
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L10:
                showToast(0x7f0a0090);
                continue; /* Loop/switch isn't completed */
_L9:
                showToast(0x7f0a0091);
                continue; /* Loop/switch isn't completed */
_L8:
                showToast(0x7f0a0092);
                continue; /* Loop/switch isn't completed */
_L6:
                util.showNetNull();
                if(true) goto _L11; else goto _L3
_L3:
                HashMap hashmap = (HashMap)message.obj;
                if(hashmap != null)
                {
                    pageObject = (Page)hashmap.get("pageObject");
                    if(currentPage < 1)
                        productList = (List)hashmap.get("productList");
                    else
                        try
                        {
                            productList.addAll((List)hashmap.get("productList"));
                        }
                        catch(Exception exception) { }
                    if(pageObject != null)
                    {
                        productCount = pageObject.getTotalSize().intValue();
                        currentPage = pageObject.getCurrentPage().intValue();
                        showCount = productList.size();
                        for(int i = 0; i < productCount; i++)
                        {
                            isShow.add(i, Boolean.valueOf(false));
                            isLoad.add(i, Boolean.valueOf(false));
                        }

                    }
                    resultInfo = (new StringBuilder()).append("\u5171").append(productCount).append("\u641C\u7D22\u7ED3\u679C").toString();
                    resultTextView.setText(resultInfo);
                    loadingTextView.setVisibility(8);
                    productListView.setVisibility(0);
                    if(currentPage == 1)
                        productListView.setAdapter(adapter);
                    else
                        adapter.notifyDataSetChanged();
                } else
                if(DBHelper.getTimeOut())
                    (new android.app.AlertDialog.Builder(SearchResult.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                        public boolean onKey(DialogInterface dialoginterface, int j, KeyEvent keyevent)
                        {
                            if(j == 4)
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

                        public void onClick(DialogInterface dialoginterface, int j)
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

                        public void onClick(DialogInterface dialoginterface, int j)
                        {
                            Object aobj[] = new Object[9];
                            aobj[7] = Integer.valueOf(1 + currentPage);
                            if(DBHelper.testNet())
                            {
                                SearchProductDao searchproductdao = new SearchProductDao(keyword, myHandler);
                                if(searchproductdao.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                {
                                    searchproductdao.cancel(true);
                                    searchproductdao.execute(aobj);
                                } else
                                {
                                    searchproductdao.execute(aobj);
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
                if(true) goto _L1; else goto _L12
_L12:
            }

            final SearchResult this$0;

            
            {
                this$0 = SearchResult.this;
                super();
            }
        }
;
        productClick = new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                operateLayout = (LinearLayout)view.findViewById(0x7f0901b5);
                if(((Boolean)isShow.get(i)).booleanValue())
                    operateLayout.setVisibility(8);
                else
                    operateLayout.setVisibility(0);
                operateLayout.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view1)
                    {
                        operateLayout.setVisibility(8);
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                for(int j = 0; j < showCount; j++)
                    if(j != i)
                        isShow.set(j, Boolean.valueOf(false));

                List list = isShow;
                boolean flag;
                if(!((Boolean)isShow.get(i)).booleanValue())
                    flag = true;
                else
                    flag = false;
                list.set(i, Boolean.valueOf(flag));
                adapter.notifyDataSetChanged();
_L2:
                return;
                Exception exception;
                exception;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final SearchResult this$0;

            
            {
                this$0 = SearchResult.this;
                super();
            }
        }
;
    }

    private void initViews()
    {
        resultTextView = (TextView)findViewById(0x7f0901ba);
        resultInfo = (new StringBuilder()).append("\u5171").append(productCount).append("\u641C\u7D22\u7ED3\u679C").toString();
        resultTextView.setText(resultInfo);
        loadingTextView = (TextView)findViewById(0x7f0901bc);
        productListView = (ListView)findViewById(0x7f0901bd);
        productListView.setVisibility(8);
        productListView.setOnItemClickListener(productClick);
        buyArray = getResources().getStringArray(0x7f060006);
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

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        showCount = util.getPageSize();
        setContentView(0x7f030034);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0106);
        keyword = getIntent().getStringExtra("keyword");
        initViews();
        adapter = new ProductListAdapter(this);
        setProgressBarIndeterminateVisibility(true);
        Object aobj[] = new Object[9];
        aobj[7] = Integer.valueOf(1 + currentPage);
        if(DBHelper.testNet())
        {
            SearchProductDao searchproductdao = new SearchProductDao(keyword, myHandler);
            if(searchproductdao.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                searchproductdao.cancel(true);
                searchproductdao.execute(aobj);
            } else
            {
                searchproductdao.execute(aobj);
            }
        } else
        {
            util.showNetNull();
        }
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c000d, menu);
        return true;
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 3: default 40
    //                   2131296770: 42
    //                   2131296771: 62
    //                   2131296795: 49;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L4:
        util.changeMain("com.thestore.main.Search");
        continue; /* Loop/switch isn't completed */
_L3:
        if(util.getHeightPixels(this) >= 480F && !util.isAuto())
            util.changeMain("com.thestore.scan.CaptureActivity");
        else
        if(util.isG8())
            util.changeMain("com.thestore.scan.CaptureActivity");
        else
            util.displayFrameworkBugMessageAndExit();
        if(true) goto _L1; else goto _L5
_L5:
    }

    @Override
	protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    @Override
	public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    @Override
	public void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    private ProductListAdapter adapter;
    private String buyArray[];
    private int currentPage;
    private List isLoad;
    private List isShow;
    private String keyword;
    private TextView loadingTextView;
    private Button loadingbButton;
    private Handler myHandler;
    private LinearLayout operateLayout;
    private Page pageObject;
    private android.widget.AdapterView.OnItemClickListener productClick;
    private int productCount;
    private List productList;
    private ListView productListView;
    private String resultInfo;
    private TextView resultTextView;
    private int showCount;
    private Util util;











/*
    static LinearLayout access$1602(SearchResult searchresult, LinearLayout linearlayout)
    {
        searchresult.operateLayout = linearlayout;
        return linearlayout;
    }

*/



/*
    static Button access$1802(SearchResult searchresult, Button button)
    {
        searchresult.loadingbButton = button;
        return button;
    }

*/



/*
    static Page access$202(SearchResult searchresult, Page page)
    {
        searchresult.pageObject = page;
        return page;
    }

*/



/*
    static int access$302(SearchResult searchresult, int i)
    {
        searchresult.currentPage = i;
        return i;
    }

*/



/*
    static List access$402(SearchResult searchresult, List list)
    {
        searchresult.productList = list;
        return list;
    }

*/



/*
    static int access$502(SearchResult searchresult, int i)
    {
        searchresult.productCount = i;
        return i;
    }

*/



/*
    static int access$602(SearchResult searchresult, int i)
    {
        searchresult.showCount = i;
        return i;
    }

*/





/*
    static String access$902(SearchResult searchresult, String s)
    {
        searchresult.resultInfo = s;
        return s;
    }

*/
}
