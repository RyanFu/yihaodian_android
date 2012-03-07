// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.DBHelper;
import com.thestore.net.OrderAsyncTask;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.order.InvoiceVO;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Invoice extends ActivityMain
{

    public Invoice()
    {
        result = 0;
        invoiceTitleEditText = null;
        invoiceContentSpinner = null;
        invoiceAmountEditText = null;
        invoiceOkButton = null;
        invoiceVO = null;
        invoiceTitle = "";
        invoiceContent = "";
        invoiceAmount = "";
        tmpTitle = "";
        tmpContent = "";
        tmpAmount = "";
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296471 2131296471: default 24
            //                           2131296471 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                if(message.obj != null)
                {
                    result = ((Integer)message.obj).intValue();
                    cancelProgress();
                    if(result == 1)
                    {
                        showToast("\u6DFB\u52A0\u6210\u529F");
                        finish();
                    } else
                    if(result == 0)
                        showToast("\u53D1\u7968\u51FA\u9519");
                    else
                    if(result == -1)
                    {
                        showToast("\u53D1\u7968\u91D1\u989D\u5927\u4E8E\u4EA7\u54C1\u603B\u4EF7");
                        invoiceAmountEditText.setText(tmpAmount);
                    } else
                    if(result == -2)
                        showToast("\u8BF7\u5148\u4FDD\u5B58\u6536\u8D27\u5730\u5740");
                    else
                    if(result == -19)
                        showToast("\u8BA2\u5355\u4E0D\u5B58\u5728");
                    else
                        util.showNetNull();
                } else
                {
                    cancelProgress();
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L3
_L3:
            }

            final Invoice this$0;

            
            {
                this$0 = Invoice.this;
                super();
            }
        }
;
    }

    private String checkInvoice(String s, String s1, String s2)
    {
        String s3;
        if(s.length() < 1)
            s3 = "\u53D1\u7968\u62AC\u5934\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        else
        if(s1.equals("\u8BF7\u9009\u62E9"))
            s3 = "\u8BF7\u9009\u62E9\u53D1\u7968\u5185\u5BB9\uFF01";
        else
        if(s2.length() < 1)
            s3 = "\u53D1\u7968\u91D1\u989D\u4E0D\u80FD\u4E3A\u7A7A\uFF01";
        else
            s3 = "";
        return s3;
    }

    private InvoiceVO createInvoice(String s, String s1, String s2)
    {
        InvoiceVO invoicevo = new InvoiceVO();
        invoicevo.setTitle(s);
        invoicevo.setContent(s1);
        invoicevo.setAmount(Double.valueOf(Double.parseDouble(s2)));
        return invoicevo;
    }

    private void initInvoiceView()
    {
        invoiceTitleEditText = (EditText)findViewById(0x7f0900d4);
        invoiceContentSpinner = (Spinner)findViewById(0x7f0900d5);
        invoiceAmountEditText = (EditText)findViewById(0x7f0900d6);
        invoiceOkButton = (Button)findViewById(0x7f0900d7);
        if(tmpTitle != null)
            invoiceTitleEditText.setText(tmpTitle);
        if(tmpContent == null) goto _L2; else goto _L1
_L1:
        String as[] = getResources().getStringArray(0x7f060007);
        ArrayList arraylist = new ArrayList();
        arraylist.add(tmpContent);
        int i = 0;
        do
        {
            if(i >= as.length)
                break;
            if(!as[i].equals("\u8BF7\u9009\u62E9") && !as[i].equals(tmpContent))
                arraylist.add(as[i]);
            i++;
        } while(true);
        ArrayAdapter arrayadapter1 = new ArrayAdapter(this, 0x1090008, arraylist);
        arrayadapter1.setDropDownViewResource(0x1090009);
        invoiceContentSpinner.setAdapter(arrayadapter1);
_L4:
        if(tmpAmount != null)
            invoiceAmountEditText.setText(tmpAmount);
        invoiceOkButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                invoiceTitle = invoiceTitleEditText.getText().toString();
                invoiceContent = invoiceContentSpinner.getSelectedItem().toString();
                invoiceAmount = invoiceAmountEditText.getText().toString();
                String s = checkInvoice(invoiceTitle, invoiceContent, invoiceAmount);
                if(s.equals(""))
                {
                    invoiceVO = createInvoice(invoiceTitle, invoiceContent, invoiceAmount);
                    if(DBHelper.testNet())
                    {
                        showProgress();
                        OrderAsyncTask orderasynctask = new OrderAsyncTask(invoiceVO, myHandler, 0x7f0900d7);
                        if(orderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            orderasynctask.cancel(true);
                            orderasynctask.execute(null);
                        } else
                        {
                            orderasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    showToast(s);
                }
            }

            final Invoice this$0;

            
            {
                this$0 = Invoice.this;
                super();
            }
        }
);
        return;
_L2:
        ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(this, 0x7f060007, 0x1090008);
        arrayadapter.setDropDownViewResource(0x1090009);
        invoiceContentSpinner.setAdapter(arrayadapter);
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030016);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0083);
        Intent intent = getIntent();
        tmpTitle = intent.getStringExtra("INVOICE_TITLE");
        tmpContent = intent.getStringExtra("INVOICE_CONTENT");
        tmpAmount = intent.getStringExtra("INVOICE_AMOUNT");
        initInvoiceView();
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

    private String invoiceAmount;
    private EditText invoiceAmountEditText;
    private String invoiceContent;
    private Spinner invoiceContentSpinner;
    private Button invoiceOkButton;
    private String invoiceTitle;
    private EditText invoiceTitleEditText;
    private InvoiceVO invoiceVO;
    private Handler myHandler;
    private int result;
    private String tmpAmount;
    private String tmpContent;
    private String tmpTitle;
    private Util util;



/*
    static String access$002(Invoice invoice, String s)
    {
        invoice.invoiceTitle = s;
        return s;
    }

*/





/*
    static int access$1102(Invoice invoice, int i)
    {
        invoice.result = i;
        return i;
    }

*/




/*
    static String access$202(Invoice invoice, String s)
    {
        invoice.invoiceContent = s;
        return s;
    }

*/




/*
    static String access$402(Invoice invoice, String s)
    {
        invoice.invoiceAmount = s;
        return s;
    }

*/





/*
    static InvoiceVO access$702(Invoice invoice, InvoiceVO invoicevo)
    {
        invoice.invoiceVO = invoicevo;
        return invoicevo;
    }

*/


}
