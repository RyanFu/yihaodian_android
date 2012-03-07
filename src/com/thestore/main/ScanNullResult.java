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
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, BarCode

public class ScanNullResult extends ActivityMain
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

        final ScanNullResult this$0;

        DownImage()
        {
            this$0 = ScanNullResult.this;
            super();
        }
    }

    private class HotListAdapter extends BaseAdapter
    {
        private class ViewHolder
        {

            ImageView imageView;
            final HotListAdapter this$1;

            private ViewHolder()
            {
                this$1 = HotListAdapter.this;
                super();
            }

        }


        @Override
		public int getCount()
        {
            return hotCount;
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
            inflater = LayoutInflater.from(context);
            View view1 = inflater.inflate(0x7f030015, null);
            ViewHolder viewholder = new ViewHolder();
            viewholder.imageView = (ImageView)view1.findViewById(0x7f0900cc);
            view1.setTag(viewholder);
            String s = ((ProductVO)hotList.get(position)).getMiniDefaultProductUrl();
            TextView textview = (TextView)view1.findViewById(0x7f0900cd);
            String s1;
            TextView textview1;
            StringBuilder stringbuilder;
            Object obj;
            LinearLayout linearlayout;
            int i;
            Button button;
            final long productID;
            LinearLayout linearlayout1;
            if(((ProductVO)hotList.get(position)).getCnName() != null)
                s1 = ((ProductVO)hotList.get(position)).getCnName();
            else
                s1 = "";
            textview.setText(s1);
            textview1 = (TextView)view1.findViewById(0x7f0900ce);
            stringbuilder = (new StringBuilder()).append("\uFFE5");
            if(((ProductVO)hotList.get(position)).getPrice() != null)
                obj = ((ProductVO)hotList.get(position)).getPrice();
            else
                obj = "0.00";
            textview1.setText(stringbuilder.append(obj).toString());
            linearlayout = (LinearLayout)view1.findViewById(0x7f0900cf);
            if(((Boolean)isShow.get(position)).booleanValue())
                i = 0;
            else
                i = 8;
            linearlayout.setVisibility(i);
            button = (Button)linearlayout.findViewById(0x7f0900d0);
            productID = ((ProductVO)hotList.get(position)).getProductId().longValue();
            button.setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view2)
                {
                    if(DBHelper.testNet())
                    {
                        if(User.token != null)
                        {
                            showProgress();
                            Cart.merchantId = ((ProductVO)hotList.get(position)).getMerchantId().longValue();
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

                final HotListAdapter this$1;
                final int val$position;
                final long val$productID;

                
                {
                    this$1 = HotListAdapter.this;
                    position = i;
                    productID = l;
                    super();
                }
            }
);
            ((Button)linearlayout.findViewById(0x7f0900d1)).setOnClickListener(new android.view.View.OnClickListener() {

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

                final HotListAdapter this$1;
                final long val$productID;

                
                {
                    this$1 = HotListAdapter.this;
                    productID = l;
                    super();
                }
            }
);
            ((Button)linearlayout.findViewById(0x7f0900d2)).setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view2)
                {
                    if(DBHelper.testNet())
                    {
                        Intent intent = new Intent("com.thestore.main.ProductDetail");
                        intent.putExtra("PRODUCT_ID", ((ProductVO)hotList.get(position)).getProductId());
                        startActivity(intent);
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final HotListAdapter this$1;
                final int val$position;

                
                {
                    this$1 = HotListAdapter.this;
                    position = i;
                    super();
                }
            }
);
            linearlayout1 = (LinearLayout)view1.findViewById(0x7f0900ca);
            if(!((Boolean)isShow.get(position)).booleanValue());
            linearlayout1.setBackgroundResource(0);
            viewholder.imageView.setTag(s);
            imageLoader.DisplayImage(s, context, viewholder.imageView);
            return view1;
        }

        private Activity context;
        private ImageLoader imageLoader;
        private LayoutInflater inflater;
        final ScanNullResult this$0;

        public HotListAdapter(Activity activity)
        {
            this$0 = ScanNullResult.this;
            super();
            context = activity;
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }
    }


    public ScanNullResult()
    {
        msgTextView = null;
        rescanButton = null;
        manualButton = null;
        moreTextView = null;
        hotListView = null;
        operateLayout = null;
        hotCount = 0;
        adapter = null;
        isShow = null;
        isLoad = null;
        hotList = null;
        productClick = new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                operateLayout = (LinearLayout)view.findViewById(0x7f0900cf);
                if(((Boolean)isShow.get(i)).booleanValue())
                    operateLayout.setVisibility(8);
                else
                    operateLayout.setVisibility(0);
                operateLayout.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view1)
                    {
                        operateLayout.setVisibility(8);
                    }

                    final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                }
);
                for(int j = 0; j < hotCount; j++)
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

            final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
        }
;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 3: default 40
            //                           2131296279: 41
            //                           2131296297: 193
            //                           2131296308: 211;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                if(message.obj != null)
                {
                    hotList = (List)message.obj;
                    if(hotList != null && hotList.size() > 0)
                        initHotList(hotList);
                } else
                if(DBHelper.getTimeOut())
                    (new android.app.AlertDialog.Builder(ScanNullResult.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                        public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                        {
                            if(i == 4)
                                finish();
                            return false;
                        }

                        final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                    }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            finish();
                        }

                        final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                    }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            if(DBHelper.testNet())
                            {
                                HotSaleAsyncTask hotsaleasynctask = new HotSaleAsyncTask(myHandler, 0x7f090017);
                                if(hotsaleasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                {
                                    hotsaleasynctask.cancel(true);
                                    hotsaleasynctask.execute(null);
                                } else
                                {
                                    hotsaleasynctask.execute(null);
                                }
                            } else
                            {
                                util.showNetNull();
                            }
                        }

                        final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                    }
).create().show();
                else
                    util.showNetNull();
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L3:
                cancelProgress();
                judgeBuySucess(message);
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj == null)
                    break MISSING_BLOCK_LABEL_312;
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch -1 1: default 256
            //                           -1 300
            //                           0 288
            //                           1 276;
                   goto _L5 _L6 _L7 _L8
_L8:
                break; /* Loop/switch isn't completed */
_L5:
                util.showNetNull();
_L10:
                cancelProgress();
                if(true) goto _L1; else goto _L9
_L9:
                showToast(0x7f0a0090);
                  goto _L10
_L7:
                showToast(0x7f0a0091);
                  goto _L10
_L6:
                showToast(0x7f0a0092);
                  goto _L10
                util.showNetNull();
                  goto _L10
            }

            final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
        }
;
    }

    private void initHotList(List list)
    {
        hotCount = list.size();
        isShow = new ArrayList();
        isLoad = new ArrayList();
        for(int i = 0; i < hotCount; i++)
        {
            isShow.add(i, Boolean.valueOf(false));
            isLoad.add(i, Boolean.valueOf(false));
        }

        adapter = new HotListAdapter(this);
        hotListView.setAdapter(adapter);
    }

    private void initViews()
    {
        msgTextView = (TextView)findViewById(0x7f0901a5);
        rescanButton = (Button)findViewById(0x7f0901a6);
        manualButton = (Button)findViewById(0x7f0901a7);
        moreTextView = (TextView)findViewById(0x7f0901a8);
        hotListView = (ListView)findViewById(0x7f0901a9);
        buyArray = getResources().getStringArray(0x7f060006);
        if(BarCode.isNull)
        {
            util.setDefaultTitle(0x7f0a011e);
            msgTextView.setText(0x7f0a0120);
            rescanButton.setText(0x7f0a0124);
            manualButton.setText(0x7f0a0123);
            rescanButton.setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view)
                {
                    util.changeMain("com.thestore.main.BarCode");
                }

                final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
            }
);
            manualButton.setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view)
                {
                    util.changeMain("com.thestore.main.Search");
                }

                final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
            }
);
            BarCode.isNull = false;
        } else
        {
            util.setDefaultTitle(0x7f0a011d);
            msgTextView.setText(0x7f0a011f);
            rescanButton.setText(0x7f0a0121);
            manualButton.setText(0x7f0a0122);
            rescanButton.setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view)
                {
                    if(util.getHeightPixels(ScanNullResult.this) >= 480F && !util.isAuto())
                        util.changeMain("com.thestore.scan.CaptureActivity");
                    else
                        util.displayFrameworkBugMessageAndExit();
                }

                final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
            }
);
            manualButton.setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view)
                {
                    util.changeMain("com.thestore.main.BarCode");
                }

                final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
            }
);
        }
        moreTextView.setOnTouchListener(new android.view.View.OnTouchListener() {

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
                moreTextView.setTextColor(Color.rgb(51, 51, 51));
                continue; /* Loop/switch isn't completed */
_L2:
                moreTextView.setTextColor(Color.rgb(255, 140, 16));
                if(true) goto _L1; else goto _L4
_L4:
            }

            final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
        }
);
        moreTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                util.changeMain("com.thestore.main.Type");
            }

            final ScanNullResult this$0;

            
            {
                this$0 = ScanNullResult.this;
                super();
            }
        }
);
        hotListView.setOnItemClickListener(productClick);
        if(DBHelper.testNet())
        {
            HotSaleAsyncTask hotsaleasynctask = new HotSaleAsyncTask(myHandler, 0x7f090017);
            showProgress();
            if(hotsaleasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                hotsaleasynctask.cancel(true);
                hotsaleasynctask.execute(null);
            } else
            {
                hotsaleasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
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
        setContentView(0x7f030031);
        bottomOnClick(this);
        initViews();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c000c, menu);
        return true;
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 3: default 40
    //                   2131296770: 42
    //                   2131296771: 62
    //                   2131296794: 49;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L4:
        util.changeMain("com.thestore.main.BarCode");
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
        initViews();
    }

    @Override
	public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    @Override
	public void onScrollStateChanged(AbsListView abslistview, int i)
    {
    }

    private HotListAdapter adapter;
    private String buyArray[];
    private int hotCount;
    private List hotList;
    private ListView hotListView;
    private List isLoad;
    private List isShow;
    private Button manualButton;
    private TextView moreTextView;
    private TextView msgTextView;
    private Handler myHandler;
    private LinearLayout operateLayout;
    private android.widget.AdapterView.OnItemClickListener productClick;
    private Button rescanButton;
    private Util util;





/*
    static LinearLayout access$202(ScanNullResult scannullresult, LinearLayout linearlayout)
    {
        scannullresult.operateLayout = linearlayout;
        return linearlayout;
    }

*/






/*
    static List access$602(ScanNullResult scannullresult, List list)
    {
        scannullresult.hotList = list;
        return list;
    }

*/



}
