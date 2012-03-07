// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.coupon.CouponVO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Coupon extends ActivityMain
{

    public Coupon()
    {
        result = 99;
        couponListIndex = -1;
        couponId = -1L;
        couponCodEditText = null;
        couponUseButton = null;
        couponListTitleTextView = null;
        couponListLayout = null;
        couponPage = null;
        couponList = null;
        inflater = null;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296334 2131296335: default 28
            //                           2131296334 29
            //                           2131296335 184;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    if(result == 1)
                    {
                        showToast("\u64CD\u4F5C\u6B63\u786E");
                        finish();
                    } else
                    if(result == -19)
                    {
                        cancelProgress();
                        showToast("\u8BA2\u5355\u4E0D\u5B58\u5728");
                    } else
                    if(result == 0)
                    {
                        cancelProgress();
                        showToast("\u62B5\u7528\u5238\u51FA\u9519");
                    } else
                    {
                        cancelProgress();
                        util.showNetNull();
                    }
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L3:
                if(message.obj != null)
                {
                    couponPage = (Page)message.obj;
                    cancelProgress();
                    if(couponPage != null || couponPage.getObjList() != null)
                    {
                        couponList = couponPage.getObjList();
                        if(couponList.size() > 0)
                        {
                            couponListLayout.setVisibility(0);
                            int i = 0;
                            while(i < couponList.size()) 
                            {
                                createCouponList((CouponVO)couponList.get(i), i);
                                i++;
                            }
                        } else
                        {
                            couponListTitleTextView.setText("\u6682\u65E0\u53EF\u4F7F\u7528\u7684\u62B5\u7528\u5238");
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Coupon this$0;

            
            {
                this$0 = Coupon.this;
                super();
            }
        }
;
    }

    private void createCouponList(CouponVO couponvo, final int index)
    {
        LinearLayout linearlayout = (LinearLayout)inflater.inflate(0x7f03000a, null);
        final TextView textView = (TextView)linearlayout.findViewById(0x7f090095);
        View view = linearlayout.findViewById(0x7f090096);
        String as[] = getResources().getStringArray(0x7f060008);
        textView.setText((new StringBuilder()).append("").append("\u7F16\u7801\uFF1A").append(couponvo.getNumber()).append("\n").append("\u7C7B\u578B\uFF1A").append(as[couponvo.getDefineType().intValue()]).append("\n").append("\u6709\u6548\u671F:").append((new SimpleDateFormat("yyyy-MM-dd")).format(couponvo.getBeginTime())).append("\uFF5E").append((new SimpleDateFormat("yyyy-MM-dd")).format(couponvo.getExpiredTime())).append("\n").append("\u9762\u503C\uFF1A").append(String.valueOf(couponvo.getAmount())).append("\u5143\n").append("\u4F7F\u7528\u8BF4\u660E\uFF1A").append(as[couponvo.getDefineType().intValue()]).append("\u6EE1").append(couponvo.getThreshOld()).append("\u5143\u4F7F\u7528").toString());
        if(couponvo.getId().longValue() == couponId)
        {
            textView.setTextColor(Color.rgb(115, 77, 1));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(0x7f020035), null);
        } else
        {
            textView.setTextColor(Color.rgb(51, 51, 51));
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        if(index == couponList.size() - 1)
            view.setVisibility(8);
        textView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view1, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 49
            //                           1 38
            //                           2 36
            //                           3 38;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L3:
                textView.setBackgroundColor(0);
                continue; /* Loop/switch isn't completed */
_L2:
                textView.setBackgroundColor(Color.rgb(255, 198, 0));
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Coupon this$0;
            final TextView val$textView;

            
            {
                this$0 = Coupon.this;
                textView = textview;
                super();
            }
        }
);
        textView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view1)
            {
                couponListIndex = index;
                return false;
            }

            final Coupon this$0;
            final int val$index;

            
            {
                this$0 = Coupon.this;
                index = i;
                super();
            }
        }
);
        textView.setOnCreateContextMenuListener(new android.view.View.OnCreateContextMenuListener() {

            public void onCreateContextMenu(ContextMenu contextmenu, View view1, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
            {
                contextmenu.add(0, 0x7f090050, 0, "\u4F7F\u7528\u6B64\u62B5\u7528\u5238");
            }

            final Coupon this$0;

            
            {
                this$0 = Coupon.this;
                super();
            }
        }
);
        couponListLayout.addView(linearlayout);
    }

    private void initView()
    {
        couponListLayout = (LinearLayout)findViewById(0x7f090093);
        inflater = LayoutInflater.from(this);
        couponCodEditText = (EditText)findViewById(0x7f090090);
        couponUseButton = (Button)findViewById(0x7f090091);
        couponListTitleTextView = (TextView)findViewById(0x7f090092);
        couponUseButton.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view)
            {
                if(couponCodEditText.getText().toString().trim().length() == 0)
                    showToast("\u8BF7\u8F93\u5165\u60A8\u7684\u62B5\u7528\u5238\u7F16\u7801\uFF01");
                else
                if(User.token != null && DBHelper.testNet())
                {
                    showProgress();
                    CouponAsyncTask couponasynctask1 = new CouponAsyncTask(couponCodEditText.getText().toString().trim(), myHandler, 0x7f09004e);
                    if(couponasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        couponasynctask1.cancel(true);
                        couponasynctask1.execute(null);
                    } else
                    {
                        couponasynctask1.execute(null);
                    }
                } else
                {
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final Coupon this$0;

            
            {
                this$0 = Coupon.this;
                super();
            }
        }
);
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            CouponAsyncTask couponasynctask = new CouponAsyncTask(myHandler, 0x7f09004f);
            if(couponasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                couponasynctask.cancel(true);
                couponasynctask.execute(null);
            } else
            {
                couponasynctask.execute(null);
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
    }

    @Override
	public boolean onContextItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296336 2131296336: default 24
    //                   2131296336 30;
           goto _L1 _L2
_L1:
        return super.onContextItemSelected(menuitem);
_L2:
        if(User.token != null && DBHelper.testNet())
        {
            if((new Date()).getTime() > ((CouponVO)couponList.get(couponListIndex)).getExpiredTime().getTime())
            {
                showToast("\u8BE5\u62B5\u7528\u5238\u5DF2\u7ECF\u8FC7\u671F\uFF01");
            } else
            {
                showProgress();
                CouponAsyncTask couponasynctask = new CouponAsyncTask(((CouponVO)couponList.get(couponListIndex)).getNumber(), myHandler, 0x7f09004e);
                if(couponasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    couponasynctask.cancel(true);
                    couponasynctask.execute(null);
                } else
                {
                    couponasynctask.execute(null);
                }
            }
        } else
        {
            util.changeMain("com.thestore.main.userland");
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030009);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0089);
        couponId = getIntent().getLongExtra("COUPON_ID", 0L);
        initView();
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

    private EditText couponCodEditText;
    private long couponId;
    private List couponList;
    private int couponListIndex;
    private LinearLayout couponListLayout;
    private TextView couponListTitleTextView;
    private Page couponPage;
    private Button couponUseButton;
    private LayoutInflater inflater;
    private Handler myHandler;
    private int result;
    private Util util;






/*
    static int access$302(Coupon coupon, int i)
    {
        coupon.result = i;
        return i;
    }

*/



/*
    static Page access$402(Coupon coupon, Page page)
    {
        coupon.couponPage = page;
        return page;
    }

*/



/*
    static List access$502(Coupon coupon, List list)
    {
        coupon.couponList = list;
        return list;
    }

*/





/*
    static int access$902(Coupon coupon, int i)
    {
        coupon.couponListIndex = i;
        return i;
    }

*/
}
