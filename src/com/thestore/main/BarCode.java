// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import com.thestore.net.DBHelper;
import com.thestore.net.ProductAsyncTask;
import com.thestore.scan.CaptureActivity;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class BarCode extends ActivityMain
    implements android.view.View.OnClickListener
{

    public BarCode()
    {
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                cancelProgress();
                if(message.obj != null)
                {
                    List list = (List)message.obj;
                    if(list != null && list.size() > 0)
                    {
                        CaptureActivity.productVOs = list;
                        if(CaptureActivity.productVOs.size() == 1)
                        {
                            Intent intent = new Intent("com.thestore.main.ProductDetail");
                            intent.putExtra("TYPE", 3);
                            intent.putExtra("PRODUCT_ID", ((ProductVO)CaptureActivity.productVOs.get(0)).getProductId());
                            startActivity(intent);
                        } else
                        {
                            Intent intent1 = new Intent("com.thestore.main.BarCodeResult");
                            startActivity(intent1);
                        }
                    } else
                    {
                        BarCode.isNull = true;
                        util.changeMain("com.thestore.main.ScanNullResult");
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final BarCode this$0;

            
            {
                this$0 = BarCode.this;
                super();
            }
        }
;
    }

    private void init()
    {
        Button button = (Button)findViewById(0x7f090063);
        codEditText = (EditText)findViewById(0x7f090062);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            CharSequence charsequence = bundle.getCharSequence("code");
            codEditText.setText(charsequence);
        }
        button.setOnClickListener(this);
    }

    @Override
	public void onClick(View view)
    {
        super.onClick(view);
        view.getId();
        JVM INSTR tableswitch 2131296355 2131296355: default 28
    //                   2131296355 29;
           goto _L1 _L2
_L1:
        return;
_L2:
        if(util.getEditString(codEditText).length() > 0)
        {
            util.hindInputMethod();
            if(DBHelper.testNet())
            {
                showProgress();
                ProductAsyncTask productasynctask = new ProductAsyncTask(util.getEditString(codEditText), handler, 0x7f090016);
                if(productasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    productasynctask.cancel(true);
                    productasynctask.execute(null);
                } else
                {
                    productasynctask.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        } else
        {
            showToast(0x7f0a011a);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        requestWindowFeature(1);
        setContentView(0x7f030001);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a010e);
        init();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0001, menu);
        return true;
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296770 2131296772: default 32
    //                   2131296770 34
    //                   2131296771 53
    //                   2131296772 41;
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

    public static boolean isNull;
    private EditText codEditText;
    Handler handler;
    private Util util;

}
