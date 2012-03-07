// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.scan.CaptureActivity;
import com.thestore.util.*;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Home

public class BarCodeResult extends ActivityMain
    implements android.widget.AdapterView.OnItemClickListener
{
    private class BarAdapter extends BaseAdapter
    {

        @Override
		public int getCount()
        {
            return showCount;
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
            ViewHolder viewholder = new ViewHolder();
            View view1 = inflater.inflate(0x7f030033, null);
            ((TextView)view1.findViewById(0x7f0901b2)).setText(((ProductVO)productVO.get(position)).getCnName());
            TextView textview = (TextView)view1.findViewById(0x7f0901b3);
            StringBuilder stringbuilder = (new StringBuilder()).append("\uFFE5");
            Object obj;
            TextView textview1;
            String s;
            String s1;
            LinearLayout linearlayout;
            int i;
            Button button;
            final long productID;
            LinearLayout linearlayout1;
            if(((ProductVO)productVO.get(position)).getPrice() != null)
                obj = ((ProductVO)productVO.get(position)).getPrice();
            else
                obj = "0.00";
            textview.setText(stringbuilder.append(obj).toString());
            textview1 = (TextView)view1.findViewById(0x7f0901b4);
            if(((ProductVO)productVO.get(position)).getCanBuy().booleanValue())
                s = "\u73B0\u8D27";
            else
                s = "\u7F3A\u8D27";
            textview1.setText(s);
            viewholder.imageView = (ImageView)view1.findViewById(0x7f0901b1);
            view1.setTag(viewholder);
            s1 = ((ProductVO)productVO.get(position)).getMiniDefaultProductUrl();
            linearlayout = (LinearLayout)view1.findViewById(0x7f0901b5);
            if(((Boolean)isShow.get(position)).booleanValue())
                i = 0;
            else
                i = 8;
            linearlayout.setVisibility(i);
            button = (Button)linearlayout.findViewById(0x7f0901b6);
            productID = ((ProductVO)productVO.get(position)).getProductId().longValue();
            button.setOnClickListener(new android.view.View.OnClickListener() {

                @Override
				public void onClick(View view2)
                {
                    if(User.token != null)
                    {
                        if(DBHelper.testNet())
                        {
                            showProgress();
                            Cart.merchantId = ((ProductVO)productVO.get(position)).getMerchantId().longValue();
                            CartAsyncTask cartasynctask = new CartAsyncTask(productID, handler, 0x7f090029);
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
                        User.TYPE = 0x7f090006;
                        util.changeMain("com.thestore.main.userland");
                    }
                }

                final BarAdapter this$1;
                final int val$position;
                final long val$productID;

                
                {
                    this$1 = BarAdapter.this;
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
                    if(User.token != null)
                    {
                        if(DBHelper.testNet())
                        {
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
                            util.showNetNull();
                        }
                    } else
                    {
                        User.TYPE = 0x7f090006;
                        util.changeMain("com.thestore.main.userland");
                    }
                }

                final BarAdapter this$1;
                final long val$productID;

                
                {
                    this$1 = BarAdapter.this;
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
                        intent.putExtra("PRODUCT_ID", ((ProductVO)productVO.get(position)).getProductId());
                        startActivity(intent);
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final BarAdapter this$1;
                final int val$position;

                
                {
                    this$1 = BarAdapter.this;
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
            return view1;
        }

        private Activity context;
        private LayoutInflater inflater;
        final BarCodeResult this$0;

        public BarAdapter(Activity activity, ListView listview)
        {
            this$0 = BarCodeResult.this;
            super();
            inflater = LayoutInflater.from(activity);
            context = activity;
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }
    }


    public BarCodeResult()
    {
        isShow = new ArrayList();
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                switch(message.what)
                {
                case 2131296297: 
                    cancelProgress();
                    judgeBuySucess(message);
                    continue;

                case 2131296308: 
                    if(message.obj != null)
                        switch(((Integer)message.obj).intValue())
                        {
                        default:
                            util.showNetNull();
                            break;

                        case 1: // '\001'
                            showToast(0x7f0a0090);
                            break;

                        case 0: // '\0'
                            showToast(0x7f0a0091);
                            break;

                        case -1: 
                            showToast(0x7f0a0092);
                            break;
                        }
                    else
                        util.showNetNull();
                    break;
                }
                while(true) 
                    return;
            }

            final BarCodeResult this$0;

            
            {
                this$0 = BarCodeResult.this;
                super();
            }
        }
;
    }

    private void init()
    {
        productVO = CaptureActivity.productVOs;
        showCount = productVO.size();
        for(int i = 0; i < showCount; i++)
            isShow.add(i, Boolean.valueOf(false));

        buyArray = getResources().getStringArray(0x7f060006);
        ((TextView)findViewById(0x7f090064)).setText((new StringBuilder()).append("\"").append(((ProductVO)productVO.get(0)).getCnName()).append("\"\u3002").toString());
        ListView listview = (ListView)findViewById(0x7f090065);
        barAdapter = new BarAdapter(this, listview);
        listview.setAdapter(barAdapter);
        listview.setOnItemClickListener(this);
    }

    private void judgeBuySucess(Message message)
    {
        if(message.obj != null)
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
    }

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030002);
        bottomOnClick(this);
        util = new Util(this);
        util.setDefaultTitle(0x7f0a011b);
        init();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c000d, menu);
        return true;
    }

    @Override
	public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0901b5);
        byte byte0;
        if(((Boolean)isShow.get(i)).booleanValue())
            byte0 = 8;
        else
            byte0 = 0;
        linearlayout.setVisibility(byte0);
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
        barAdapter.notifyDataSetChanged();
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296770 2131296771: default 28
    //                   2131296770 30
    //                   2131296771 59;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/Home);
        startActivity(intent);
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        if(util.getHeightPixels(this) >= 480F && !util.isAuto())
            util.changeMain("com.thestore.scan.CaptureActivity");
        else
        if(util.isG8())
            util.changeMain("com.thestore.scan.CaptureActivity");
        else
            util.displayFrameworkBugMessageAndExit();
        if(true) goto _L1; else goto _L4
_L4:
    }

    @Override
	public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.findItem(0x7f09021b).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
	protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private BarAdapter barAdapter;
    private String buyArray[];
    Handler handler;
    private ImageLoader imageLoader;
    private List isShow;
    private List productVO;
    private int showCount;
    private Util util;





/*
    static ImageLoader access$202(BarCodeResult barcoderesult, ImageLoader imageloader)
    {
        barcoderesult.imageLoader = imageloader;
        return imageloader;
    }

*/



}
