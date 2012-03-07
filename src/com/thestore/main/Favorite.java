// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.DBHelper;
import com.thestore.net.FavoriteAsyncTask;
import com.thestore.util.*;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.favorite.FavoriteVO;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, MyStore

public class Favorite extends ActivityMain
{

    public Favorite()
    {
        currentPage = 1;
        pageSize = 10;
        lists = new ArrayList();
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                switch(message.what)
                {
                case 2131296309: 
                    listLayout.removeAllViews();
                    if(message.obj != null)
                    {
                        Page page = (Page)message.obj;
                        list = page.getObjList();
                        totalSize = page.getTotalSize().intValue();
                        currentPage = page.getCurrentPage().intValue();
                        showCount = list.size();
                        if(showCount != 0)
                        {
                            findViewById(0x7f090097).setVisibility(0);
                            findViewById(0x7f090098).setVisibility(8);
                            setFavorite();
                        } else
                        {
                            addViewNullFavorite();
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                    cancelProgress();
                    continue;

                case 2131296310: 
                    if(message.obj != null)
                        switch(((Integer)message.obj).intValue())
                        {
                        default:
                            util.showNetNull();
                            break;

                        case 1: // '\001'
                            if(DBHelper.testNet())
                            {
                                showToast(0x7f0a0100);
                                FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(0, pageSize, handler, 0x7f090035);
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
                            break;

                        case 0: // '\0'
                            showToast(0x7f0a0101);
                            cancelProgress();
                            break;
                        }
                    else
                        util.showNetNull();
                    break;
                }
                while(true) 
                    return;
            }

            final Favorite this$0;

            
            {
                this$0 = Favorite.this;
                super();
            }
        }
;
    }

    private void addViewBtn()
    {
        final Button button = new Button(this);
        button.setBackgroundResource(0x7f02003d);
        String s;
        boolean flag;
        if(lists.size() == totalSize)
            s = "\u5546\u54C1\u52A0\u8F7D\u5B8C\u6BD5";
        else
            s = "\u52A0\u8F7D\u66F4\u591A";
        button.setText(s);
        if(lists.size() != totalSize)
            flag = true;
        else
            flag = false;
        button.setEnabled(flag);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    showProgress();
                    button.setText("\u52A0\u8F7D\u4E2D\uFF0C\u8BF7\u7A0D\u5019");
                    int i = ((setText) (this)).setText;
                    FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(currentPage, pageSize, handler, 0x7f090035);
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

            final Favorite this$0;
            final Button val$button;

            
            {
                this$0 = Favorite.this;
                button = button1;
                super();
            }
        }
);
        listLayout.addView(button);
    }

    private void addViewFavorite(final int position)
    {
        LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f03000c, null);
        ViewHolder viewholder = new ViewHolder();
        String s = ((FavoriteVO)lists.get(position)).getProduct().getMiniDefaultProductUrl();
        viewholder.imageView = (ImageView)linearlayout.findViewById(0x7f09009b);
        linearlayout.setTag(viewholder);
        ((TextView)linearlayout.findViewById(0x7f09009c)).setText(((FavoriteVO)lists.get(position)).getProduct().getCnName());
        TextView textview = (TextView)linearlayout.findViewById(0x7f09009d);
        StringBuilder stringbuilder = (new StringBuilder()).append("\uFFE5");
        Object obj;
        final long productId;
        final LinearLayout linearLayout;
        View view;
        if(((FavoriteVO)lists.get(position)).getProduct().getPrice() != null)
            obj = ((FavoriteVO)lists.get(position)).getProduct().getPrice();
        else
            obj = "0.00";
        textview.setText(stringbuilder.append(obj).toString());
        productId = ((FavoriteVO)lists.get(position)).getProduct().getProductId().longValue();
        linearLayout = (LinearLayout)linearlayout.findViewById(0x7f09009a);
        linearLayout.setOnTouchListener(new android.view.View.OnTouchListener() {

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
                linearLayout.setBackgroundResource(0x7f0200b9);
                continue; /* Loop/switch isn't completed */
_L3:
                linearLayout.setBackgroundColor(-1);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Favorite this$0;
            final LinearLayout val$linearLayout;

            
            {
                this$0 = Favorite.this;
                linearLayout = linearlayout;
                super();
            }
        }
);
        linearLayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(DBHelper.testNet())
                {
                    Intent intent = new Intent("com.thestore.main.ProductDetail");
                    intent.putExtra("PRODUCT_ID", ((FavoriteVO)lists.get(position)).getProduct().getProductId());
                    startActivity(intent);
                } else
                {
                    util.showNetNull();
                }
            }

            final Favorite this$0;
            final int val$position;

            
            {
                this$0 = Favorite.this;
                position = i;
                super();
            }
        }
);
        ((Button)linearlayout.findViewById(0x7f09009e)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                (new android.app.AlertDialog.Builder(Favorite.this)).setTitle(0x7f0a0040).setMessage(0x7f0a0093).setPositiveButton(0x7f0a00b7, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if(DBHelper.testNet())
                        {
                            showProgress();
                            FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(productId, handler, 0x7f090036);
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

                    final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                }
).setNegativeButton(0x7f0a00f7, null).create().show();
            }

            final Favorite this$0;
            final long val$productId;

            
            {
                this$0 = Favorite.this;
                productId = l;
                super();
            }
        }
);
        listLayout.addView(linearlayout);
        view = new View(this);
        view.setBackgroundResource(0x7f020026);
        listLayout.addView(view);
        viewholder.imageView.setTag(s);
        imageLoader.DisplayImage(s, this, viewholder.imageView);
    }

    private void addViewNullFavorite()
    {
        findViewById(0x7f090097).setVisibility(8);
        findViewById(0x7f090098).setVisibility(0);
        findViewById(0x7f090099).setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view)
            {
                util.changeMain("com.thestore.main.Type");
            }

            final Favorite this$0;

            
            {
                this$0 = Favorite.this;
                super();
            }
        }
);
    }

    private void init()
    {
        list = MyStore.favoritePage.getObjList();
        if(list.size() == 10)
            totalSize = 0;
        else
            totalSize = list.size();
        setFavorite();
    }

    private void setFavorite()
    {
        int i = list.size();
        for(int j = 0; j < i; j++)
            lists.add(j, list.get(j));

        int k = lists.size();
        for(int l = 0; l < k; l++)
            addViewFavorite(l);

        addViewBtn();
    }

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000b);
        util = new Util(this);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a008f);
        inflater = LayoutInflater.from(this);
        listLayout = (LinearLayout)findViewById(0x7f090097);
        imageLoader = new ImageLoader(getApplicationContext());
        init();
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
    private Handler handler;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private List list;
    private LinearLayout listLayout;
    private List lists;
    private int pageSize;
    private int showCount;
    private int totalSize;
    private Util util;





/*
    static List access$102(Favorite favorite, List list1)
    {
        favorite.list = list1;
        return list1;
    }

*/


/*
    static int access$202(Favorite favorite, int i)
    {
        favorite.totalSize = i;
        return i;
    }

*/



/*
    static int access$302(Favorite favorite, int i)
    {
        favorite.currentPage = i;
        return i;
    }

*/


/*
    static int access$308(Favorite favorite)
    {
        int i = favorite.currentPage;
        favorite.currentPage = i + 1;
        return i;
    }

*/



/*
    static int access$402(Favorite favorite, int i)
    {
        favorite.showCount = i;
        return i;
    }

*/





}
