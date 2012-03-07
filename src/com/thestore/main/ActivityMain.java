// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Const;
import com.thestore.util.Util;

// Referenced classes of package com.thestore.main:
//            Home

public class ActivityMain extends Activity
    implements android.view.View.OnClickListener
{

    public ActivityMain()
    {
    }

    public void bottomOnClick(final Activity activity)
    {
        this.activity = activity;
        LinearLayout linearlayout = getView(activity);
        TextView textview = (TextView)linearlayout.findViewById(0x7f090067);
        TextView textview1 = (TextView)linearlayout.findViewById(0x7f090068);
        TextView textview2 = (TextView)linearlayout.findViewById(0x7f090069);
        TextView textview3 = (TextView)linearlayout.findViewById(0x7f09006c);
        textview.setOnClickListener(this);
        textview1.setOnClickListener(this);
        textview2.setOnClickListener(this);
        textview3.setOnClickListener(this);
        cartLayout = (LinearLayout)linearlayout.findViewById(0x7f09006a);
        cartLayout.setOnClickListener(this);
        cartTextView = (TextView)linearlayout.findViewById(0x7f09006b);
        setCartImage(Long.valueOf(Cart.cartTotal));
        cartLayout.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 38
            //                           1 71
            //                           2 36
            //                           3 71;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L2:
                if(!activity.getClass().getName().equals("com.thestore.main.Cart"))
                    cartLayout.setBackgroundResource(0x7f02000b);
                continue; /* Loop/switch isn't completed */
_L3:
                if(activity.getClass().getName().equals("com.thestore.main.Cart"))
                    cartLayout.setBackgroundResource(0x7f020008);
                else
                    cartLayout.setBackgroundColor(0);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final ActivityMain this$0;
            final Activity val$activity;

            
            {
                this$0 = ActivityMain.this;
                activity = activity1;
                super();
            }
        }
);
        if(activity.getClass().getName().equals("com.thestore.main.Home"))
            textview.setBackgroundResource(0x7f020005);
        if(activity.getClass().getName().equals("com.thestore.main.Search"))
            textview1.setBackgroundResource(0x7f020006);
        if(activity.getClass().getName().equals("com.thestore.main.Type"))
            textview2.setBackgroundResource(0x7f020007);
        if(activity.getClass().getName().equals("com.thestore.main.Cart"))
            cartLayout.setBackgroundResource(0x7f020008);
        if(activity.getClass().getName().equals("com.thestore.main.More"))
            textview3.setBackgroundResource(0x7f020009);
    }

    public void cancelProgress()
    {
        if(progressDialog != null)
        {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void cancelToast()
    {
        if(toast != null)
            toast.cancel();
    }

    public LinearLayout getView(Activity activity1)
    {
        return (LinearLayout)activity1.findViewById(0x7f090060);
    }

    protected void initTitle()
    {
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f090123);
        if(relativelayout != null)
        {
            Button button = (Button)findViewById(0x7f0901cf);
            if(button != null)
                relativelayout.removeView(button);
            Button button1 = (Button)findViewById(0x7f0901d0);
            if(button1 != null)
                relativelayout.removeView(button1);
        }
    }

    @Override
	public void onClick(View view)
    {
        Intent intent;
        if(Const.PRODUCT == 1)
        {
            finish();
            Const.PRODUCT = 0;
        }
        intent = null;
        view.getId();
        JVM INSTR tableswitch 2131296359 2131296364: default 60
    //                   2131296359 66
    //                   2131296360 106
    //                   2131296361 137
    //                   2131296362 168
    //                   2131296363 60
    //                   2131296364 239;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L1:
        startActivity(intent);
_L7:
        return;
_L2:
        if(!activity.getClass().getName().equals("com.thestore.main.Home"))
        {
            intent = new Intent();
            intent.setClass(activity, com/thestore/main/Home);
        }
          goto _L1
_L3:
        if(!activity.getClass().getName().equals("com.thestore.main.Search"))
            intent = new Intent("com.thestore.main.Search");
          goto _L1
_L4:
        if(!activity.getClass().getName().equals("com.thestore.main.Type"))
            intent = new Intent("com.thestore.main.Type");
          goto _L1
_L5:
        if(DBHelper.testNet())
        {
            if(!activity.getClass().getName().equals("com.thestore.main.Cart"))
                if(User.token != null)
                {
                    intent = new Intent("com.thestore.main.Cart");
                } else
                {
                    User.TYPE = 0x7f090001;
                    intent = new Intent("com.thestore.main.userland");
                }
        } else
        {
            util.showNetNull();
        }
          goto _L1
_L6:
        if(!activity.getClass().getName().equals("com.thestore.main.More"))
            intent = new Intent("com.thestore.main.More");
          goto _L1
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L7
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(1);
        toast = new Toast(this);
        util = new Util(this);
    }

    public void setCartImage(Long long1)
    {
        TextView textview = cartTextView;
        int i;
        if(Cart.cartTotal > 0L)
            i = 0;
        else
            i = 8;
        textview.setVisibility(i);
        if(long1.longValue() > 9L)
            cartTextView.setBackgroundResource(0x7f020028);
        else
            cartTextView.setBackgroundResource(0x7f020027);
        cartTextView.postInvalidate();
        cartTextView.setPadding(5, 0, 0, 0);
        cartTextView.setText((new StringBuilder()).append(long1).append("").toString());
    }

    @Override
	public void setContentView(int i)
    {
        super.setContentView(i);
        initTitle();
    }

    public void showProgress()
    {
        showProgress(0x7f0a000f);
    }

    public void showProgress(int i)
    {
        if(progressDialog != null)
            progressDialog.cancel();
        progressDialog = ProgressDialog.show(activity, "", getResources().getString(i));
        progressDialog.setCancelable(true);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
        progressDialog = ProgressDialog.show(activity, "", getResources().getString(i));
        progressDialog.setCancelable(true);
          goto _L1
    }

    public void showToast(int i)
    {
        cancelToast();
        View view = ((LayoutInflater)activity.getSystemService("layout_inflater")).inflate(0x7f030040, null);
        ((TextView)view.findViewById(0x7f0901d1)).setText(i);
        toast.setDuration(0);
        toast.setView(view);
        toast.show();
    }

    public void showToast(String s)
    {
        cancelToast();
        View view = ((LayoutInflater)activity.getSystemService("layout_inflater")).inflate(0x7f030040, null);
        ((TextView)view.findViewById(0x7f0901d1)).setText(s);
        toast.setDuration(0);
        toast.setView(view);
        toast.show();
    }

    public static ProgressDialog progressDialog;
    private Activity activity;
    private LinearLayout cartLayout;
    private TextView cartTextView;
    private Toast toast;
    private Util util;

}
