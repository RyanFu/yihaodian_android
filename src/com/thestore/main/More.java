// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.*;
import android.text.TextPaint;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thestore.net.*;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.system.DownloadVO;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Home, HomeSet

public class More extends ActivityMain
    implements android.view.View.OnClickListener
{

    public More()
    {
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296507 2131296507: default 24
            //                           2131296507 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                DownloadVO downloadvo = (DownloadVO)message.obj;
                if(downloadvo != null)
                    util.showUpdataAlert(More.this, downloadvo);
                else
                    util.showNetNull();
                cancelProgress();
                if(true) goto _L1; else goto _L3
_L3:
            }

            final More this$0;

            
            {
                this$0 = More.this;
                Handler();
            }
        }
;
    }

    private void initLayout()
    {
        int ai[] = new int[10];
        ai[0] = 0x7f0900f1;
        ai[1] = 0x7f0900f2;
        ai[2] = 0x7f0900f3;
        ai[3] = 0x7f0900f4;
        ai[4] = 0x7f0900f5;
        ai[5] = 0x7f0900f7;
        ai[6] = 0x7f0900f8;
        ai[7] = 0x7f0900f9;
        ai[8] = 0x7f0900fa;
        ai[9] = 0x7f0900fb;
        String as[] = getResources().getStringArray(0x7f060000);
        int i = as.length;
        for(int j = 0; j < i; j++)
            setTextViewFromLayout(ai[j], as[j]);

        if("8151389".equals(DBHelper.getTrader().getUnionKey()))
            removeLayout(0x7f0900fb);
    }

    private void removeLayout(int i)
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0900f6);
        if(linearlayout != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        LinearLayout linearlayout1 = (LinearLayout)findViewById(i);
        if(linearlayout1 != null)
        {
            linearlayout.removeView(linearlayout1);
            linearlayout.requestLayout();
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void setTextViewFromLayout(int i, String s)
    {
        LinearLayout linearlayout = (LinearLayout)findViewById(i);
        if(linearlayout != null)
        {
            final TextView textView = (TextView)linearlayout.findViewById(0x7f0900fc);
            textView.setTextSize(16F);
            textView.setText(s);
            textView.getPaint().setFakeBoldText(true);
            textView.setOnClickListener(this);
            textView.setOnTouchListener(new android.view.View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    motionevent.getAction();
                    JVM INSTR tableswitch 0 3: default 36
                //                               0 38
                //                               1 59
                //                               2 36
                //                               3 59;
                       goto _L1 _L2 _L3 _L1 _L3
_L1:
                    return false;
_L2:
                    textView.setTextColor(Color.rgb(255, 140, 16));
                    continue; /* Loop/switch isn't completed */
_L3:
                    initLayout();
                    textView.setTextColor(Color.rgb(102, 102, 102));
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final More this$0;
                final TextView val$textView;

            
            {
                this$0 = More.this;
                textView = textview;
                Object();
            }
            }
);
        }
    }

    public void onClick(View view)
    {
        ((View)((LinearLayout)view.getParent()).getParent()).getId();
        JVM INSTR tableswitch 2131296497 2131296507: default 76
    //                   2131296497 93
    //                   2131296498 156
    //                   2131296499 213
    //                   2131296500 287
    //                   2131296501 300
    //                   2131296502 76
    //                   2131296503 331
    //                   2131296504 344
    //                   2131296505 381
    //                   2131296506 394
    //                   2131296507 407;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L7 _L8 _L9 _L10 _L11
_L1:
        Intent intent = null;
_L12:
        DownAsy downasy;
        if(intent != null)
            try
            {
                startActivity(intent);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        super.onClick(view);
        return;
_L2:
        setTitleColor(0x7f07000e);
        if(DBHelper.testNet())
        {
            if(User.token != null)
            {
                util.changeMain("com.thestore.main.Message");
                intent = null;
            } else
            {
                User.TYPE = 0x7f090002;
                util.changeMain("com.thestore.main.userland");
                intent = null;
            }
        } else
        {
            util.showNetNull();
            intent = null;
        }
          goto _L12
_L3:
        if(DBHelper.testNet())
        {
            if(User.token != null)
            {
                util.changeMain("com.thestore.main.Mystore");
                intent = null;
            } else
            {
                User.TYPE = 0x7f090000;
                util.changeMain("com.thestore.main.userland");
                intent = null;
            }
        } else
        {
            util.showNetNull();
            intent = null;
        }
          goto _L12
_L4:
        if(util.getHeightPixels(this) >= 480F && !util.isAuto())
        {
            util.changeMain("com.thestore.scan.CaptureActivity");
            intent = null;
        } else
        if(util.isG8())
        {
            util.changeMain("com.thestore.scan.CaptureActivity");
            intent = null;
        } else
        {
            util.displayFrameworkBugMessageAndExit();
            intent = null;
        }
          goto _L12
_L5:
        intent = new Intent("com.thestore.main.Note");
          goto _L12
_L6:
        if(DBHelper.testNet())
        {
            intent = new Intent("com.thestore.main.Promotion");
        } else
        {
            util.showNetNull();
            intent = null;
        }
          goto _L12
_L7:
        intent = new Intent("com.thestore.main.help");
          goto _L12
_L8:
        if(User.token != null)
        {
            intent = new Intent("com.thestore.main.feekback");
        } else
        {
            User.TYPE = 0x7f090003;
            intent = new Intent("com.thestore.main.userland");
        }
          goto _L12
_L9:
        intent = new Intent("com.thestore.main.About");
          goto _L12
_L10:
        intent = new Intent("com.thestore.main.Statement");
          goto _L12
_L11:
label0:
        {
            if(DBHelper.isUpdata)
                break label0;
            if(DBHelper.testNet())
            {
                showProgress();
                downasy = new DownAsy(myHandler, 0x7f0900fb);
                if(downasy.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    downasy.cancel(true);
                    downasy.execute(null);
                } else
                {
                    downasy.execute(null);
                }
                intent = null;
            } else
            {
                util.showNetNull();
                intent = null;
            }
        }
          goto _L12
        showToast("\u6B63\u5728\u4E0B\u8F7D\uFF0C\u8BF7\u7A0D\u5019...");
        if(true) goto _L1; else goto _L13
_L13:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03001c);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a00ef);
        initLayout();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0006, menu);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            Intent intent = new Intent();
            intent.setClass(this, com/thestore/main/Home);
            startActivity(intent);
            finish();
            flag = false;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 4: default 48
    //                   2131296770: 50
    //                   2131296779: 106
    //                   2131296780: 94
    //                   2131296784: 82;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return true;
_L2:
        Intent intent1 = new Intent();
        intent1.setClass(this, com/thestore/main/Home);
        startActivity(intent1);
        finish();
        continue; /* Loop/switch isn't completed */
_L5:
        util.changeMain("com.thestore.main.help");
        continue; /* Loop/switch isn't completed */
_L4:
        util.changeMain("com.thestore.main.About");
        continue; /* Loop/switch isn't completed */
_L3:
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/HomeSet);
        startActivity(intent);
        if(true) goto _L1; else goto _L6
_L6:
    }

    protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private Handler myHandler;
    private Util util;


}
