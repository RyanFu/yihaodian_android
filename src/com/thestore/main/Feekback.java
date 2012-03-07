// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.os.*;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import com.thestore.net.*;
import com.thestore.util.Util;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Feekback extends ActivityMain
    implements android.view.View.OnClickListener
{

    public Feekback()
    {
        result = 0;
        feekback = "";
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                if(message.what == 0x7f0900a1)
                    if(message.obj != null)
                    {
                        result = ((Integer)message.obj).intValue();
                        if(result == 1)
                        {
                            showToast("\u63D0\u4EA4\u6210\u529F!");
                            fbText.setText("");
                        } else
                        if(result == 0)
                            showToast("\u63D0\u4EA4\u5931\u8D25!");
                        else
                            util.showNetNull();
                        cancelProgress();
                    } else
                    {
                        cancelProgress();
                        util.showNetNull();
                    }
            }

            final Feekback this$0;

            
            {
                this$0 = Feekback.this;
                super();
            }
        }
;
    }

    private void initView()
    {
        fbButton = (Button)findViewById(0x7f0900a1);
        fbText = (EditText)findViewById(0x7f0900a0);
        fbText.setHint("\u6700\u591A\u53EA\u80FD\u8F93\u5165800\u5B57");
        fbButton.setOnClickListener(this);
    }

    @Override
	public void onClick(View view)
    {
        super.onClick(view);
        if(view.getId() == 0x7f0900a1)
        {
            feekback = fbText.getText().toString().trim();
            if(feekback != null && feekback.length() > 0)
            {
                showProgress();
                if(User.token != null && DBHelper.testNet())
                {
                    FeekbackAsyncTask feekbackasynctask = new FeekbackAsyncTask(fbText.getText().toString(), myHandler, 0x7f0900a1);
                    if(feekbackasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        feekbackasynctask.cancel(true);
                        feekbackasynctask.execute(null);
                    } else
                    {
                        feekbackasynctask.execute(null);
                    }
                } else
                {
                    util.changeMain("com.thestore.main.userland");
                }
            } else
            {
                showToast("\u63D0\u4EA4\u610F\u89C1\u5185\u5BB9\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
            }
        }
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03000d);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0133);
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

    private Button fbButton;
    private EditText fbText;
    private String feekback;
    private Handler myHandler;
    private int result;
    private Util util;



/*
    static int access$002(Feekback feekback1, int i)
    {
        feekback1.result = i;
        return i;
    }

*/


}
