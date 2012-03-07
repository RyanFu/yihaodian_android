// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.thestore.net.DBHelper;
import com.thestore.net.User;
import com.yihaodian.mobile.vo.message.InnerMessageVO;
import com.yihaodian.mobile.vo.product.ProductVO;
import com.yihaodian.mobile.vo.system.DownloadVO;

// Referenced classes of package com.thestore.util:
//            Store

public class Util
{

    public Util(Activity activity1)
    {
        activity = activity1;
    }

    public Util(Context context)
    {
        activity = (Activity)context;
    }

    public void changeMain(String s)
    {
        Intent intent = new Intent(s);
        activity.startActivity(intent);
    }

    public void displayFrameworkBugMessageAndExit()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(0x7f0a000e));
        builder.setMessage(activity.getString(0x7f0a0117));
        builder.setPositiveButton(0x7f0a0111, new android.content.DialogInterface.OnClickListener() {

            @Override
			public void onClick(DialogInterface dialoginterface, int i)
            {
                Intent intent = new Intent("com.thestore.main.BarCode");
                activity.startActivity(intent);
            }

            final Util this$0;

            
            {
                this$0 = Util.this;
                super();
            }
        }
);
        builder.setNegativeButton(0x7f0a0110, null);
        builder.show();
    }

    public String getEditString(EditText edittext)
    {
        return edittext.getText().toString().trim();
    }

    public float getHeightPixels(Activity activity1)
    {
        new DisplayMetrics();
        return activity1.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }

    public int getPageSize()
    {
        pageSize = (new Store(activity)).getInt("pageSize", 10);
        return pageSize;
    }

    public void hindInputMethod()
    {
        ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public boolean isAuto()
    {
        return Build.BRAND.toLowerCase().equals("qcom");
    }

    public boolean isEmpty(EditText edittext)
    {
        boolean flag;
        if(edittext.getText().toString().trim().equals("") && edittext.getText().toString().trim().length() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isExistSdCard()
    {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public boolean isG8()
    {
        return Build.BOARD.equals("buzz");
    }

    public void sendSms(ProductVO productvo, InnerMessageVO innermessagevo)
    {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("sms:"));
        String s;
        if(productvo != null)
        {
            String s1 = productvo.getCnName();
            if(s1.length() > 15)
            {
                String s2 = s1.substring(0, 15);
                s1 = (new StringBuilder()).append(s2).append("...").toString();
            }
            s = (new StringBuilder()).append("\u6211\u57281\u53F7\u5E97\u53D1\u73B0\u4E86").append(s1).append("\uFF0C").append(productvo.getPrice()).append("\u5143\uFF0C").append("http://m.yihaodian.com/p/").append(productvo.getProductId()).append("_").append(User.province).append("_sms").toString();
        } else
        {
            s = innermessagevo.getContent();
        }
        intent.putExtra("address", "");
        intent.putExtra("sms_body", s);
        activity.startActivity(intent);
    }

    public void setDefaultTitle(int i)
    {
        TextView textview = (TextView)activity.findViewById(0x7f090124);
        textview.setTextColor(-1);
        textview.setText(i);
    }

    public void setEmptyError(EditText edittext, int i)
    {
        if(isEmpty(edittext))
            setErrorMessage(edittext, i);
    }

    public void setErrorMessage(EditText edittext, int i)
    {
        edittext.setError(activity.getString(i));
    }

    public void setPageSize(int i)
    {
        (new Store(activity)).storeInt("pageSize", i);
        pageSize = i;
    }

    public void setTitle(int i, int j)
    {
        activity.requestWindowFeature(7);
        activity.setContentView(i);
        activity.getWindow().setFeatureInt(7, j);
    }

    public void showInputMethod(View view)
    {
        view.requestFocus();
        ((InputMethodManager)activity.getSystemService("input_method")).showSoftInput(view, 2);
    }

    public void showNetNull()
    {
        showToast(0x7f0a0010);
    }

    public void showToast(int i)
    {
        Toast.makeText(activity, activity.getString(i), 0).show();
    }

    public void showToast(String s)
    {
        Toast.makeText(activity, s, 0).show();
    }

    public void showUpdataAlert(final Activity context, final DownloadVO downloadVO)
    {
        AlertDialog alertdialog;
        if(downloadVO.getCanUpdate().equals("true"))
        {
            if(downloadVO.getForceUpdate().equals("true"))
                alertdialog = (new android.app.AlertDialog.Builder(context)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(downloadVO.getRemark()).setPositiveButton(context.getResources().getString(0x7f0a0014), new android.content.DialogInterface.OnClickListener() {

                    @Override
					public void onClick(DialogInterface dialoginterface, int i)
                    {
                        DBHelper.url = downloadVO.getDownloadUrl();
                        context.startService(new Intent("com.thestore.util.DownService"));
                    }

                    final Util this$0;
                    final Activity val$context;
                    final DownloadVO val$downloadVO;

            
            {
                this$0 = Util.this;
                downloadVO = downloadvo;
                context = activity1;
                super();
            }
                }
).setNegativeButton(context.getResources().getString(0x7f0a0015), new android.content.DialogInterface.OnClickListener() {

                    @Override
					public void onClick(DialogInterface dialoginterface, int i)
                    {
                        activity.finish();
                    }

                    final Util this$0;

            
            {
                this$0 = Util.this;
                super();
            }
                }
).create();
            else
                alertdialog = (new android.app.AlertDialog.Builder(context)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(downloadVO.getRemark()).setPositiveButton(context.getResources().getString(0x7f0a0014), new android.content.DialogInterface.OnClickListener() {

                    @Override
					public void onClick(DialogInterface dialoginterface, int i)
                    {
                        DBHelper.url = downloadVO.getDownloadUrl();
                        context.startService(new Intent("com.thestore.util.DownService"));
                    }

                    final Util this$0;
                    final Activity val$context;
                    final DownloadVO val$downloadVO;

            
            {
                this$0 = Util.this;
                downloadVO = downloadvo;
                context = activity1;
                super();
            }
                }
).setNegativeButton(context.getResources().getString(0x7f0a0016), null).create();
        } else
        {
            alertdialog = (new android.app.AlertDialog.Builder(context)).setTitle(0x7f0a0040).setIcon(0x7f020068).setMessage(context.getResources().getString(0x7f0a0012)).setPositiveButton(context.getResources().getString(0x7f0a0116), null).create();
        }
        alertdialog.show();
    }

    public static int pageSize = 10;
    private Activity activity;


}
