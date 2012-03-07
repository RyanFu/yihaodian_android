// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.preference.*;
import android.text.format.Formatter;
import com.thestore.net.DBHelper;
import com.thestore.net.DownAsy;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.system.DownloadVO;
import java.io.File;

public class HomeSet extends PreferenceActivity
{
    class GetSizeAsy extends AsyncTask
    {

        @Override
		protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient String doInBackground(String as[])
        {
            return getSize();
        }

        @Override
		protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            String s1 = getResources().getString(0x7f0a0009);
            clearPreference.setSummary((new StringBuilder()).append(s1).append(getSize()).toString());
            clearPreference.setEnabled(true);
        }

        final HomeSet this$0;

        GetSizeAsy()
        {
            this$0 = HomeSet.this;
            super();
        }
    }


    public HomeSet()
    {
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296264 2131296264: default 24
            //                           2131296264 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                if(message.obj != null)
                {
                    DownloadVO downloadvo = (DownloadVO)message.obj;
                    if(downloadvo != null)
                        util.showUpdataAlert(HomeSet.this, downloadvo);
                    else
                        util.showNetNull();
                } else
                {
                    util.showNetNull();
                }
                updatePreferenceScreen.setEnabled(true);
                if(true) goto _L1; else goto _L3
_L3:
            }

            final HomeSet this$0;

            
            {
                this$0 = HomeSet.this;
                super();
            }
        }
;
    }

    private String getSdCardPath()
    {
        return (new StringBuilder()).append(Environment.getExternalStorageDirectory().getPath()).append("/").toString();
    }

    private String getSize()
    {
        File file = new File((new StringBuilder()).append(getSdCardPath()).append("thestore").append("/").toString());
        long l;
        if(file.exists())
        {
            int i = file.list().length;
            long l1 = 0L;
            for(int j = 0; j < i; j++)
                l1 += (new File((new StringBuilder()).append(getSdCardPath()).append("thestore").append("/").append(file.list()[j]).toString())).length();

            l = l1;
        } else
        {
            l = 0L;
        }
        return Formatter.formatFileSize(getApplicationContext(), l);
    }

    private void setChangeListener()
    {
        clearPreference = findPreference("history");
        clearPreference.setSummary(getResources().getString(0x7f0a000f));
        clearPreference.setEnabled(false);
        size = getResources().getString(0x7f0a0009);
        clearPreference.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

            @Override
			public boolean onPreferenceClick(Preference preference)
            {
                (new android.app.AlertDialog.Builder(HomeSet.this)).setTitle(0x7f0a0040).setMessage(0x7f0a000d).setPositiveButton(0x7f0a00c2, new android.content.DialogInterface.OnClickListener() {

                    @Override
					public void onClick(DialogInterface dialoginterface, int i)
                    {
                        (new ImageLoader(_fld0)).clearCache();
                        clearPreference.setSummary((new StringBuilder()).append(size).append(getSize()).toString());
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
).setNegativeButton(0x7f0a00c3, null).create().show();
                return false;
            }

            final HomeSet this$0;

            
            {
                this$0 = HomeSet.this;
                super();
            }
        }
);
        findPreference("net").setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

            @Override
			public boolean onPreferenceClick(Preference preference)
            {
                startActivityForResult(new Intent("android.settings.WIRELESS_SETTINGS"), 0);
                return false;
            }

            final HomeSet this$0;

            
            {
                this$0 = HomeSet.this;
                super();
            }
        }
);
        final String num = getResources().getString(0x7f0a0006);
        final String array[] = getResources().getStringArray(0x7f060001);
        final ListPreference listPreference = (ListPreference)findPreference("search");
        listPreference.setSummary((new StringBuilder()).append(num).append(Util.pageSize).append("\u6761").toString());
        listPreference.setOnPreferenceChangeListener(new android.preference.Preference.OnPreferenceChangeListener() {

            @Override
			public boolean onPreferenceChange(Preference preference, Object obj)
            {
                int i = listPreference.findIndexOfValue(obj.toString());
                (new Util(HomeSet.this)).setPageSize(Integer.parseInt(array[i]));
                listPreference.setSummary((new StringBuilder()).append(num).append(array[i]).append("\u6761").toString());
                listPreference.setValueIndex(i);
                return false;
            }

            final HomeSet this$0;
            final String val$array[];
            final ListPreference val$listPreference;
            final String val$num;

            
            {
                this$0 = HomeSet.this;
                listPreference = listpreference;
                array = as;
                num = s;
                super();
            }
        }
);
        updatePreferenceScreen = (PreferenceScreen)findPreference("update");
        if(DBHelper.isUpdata)
        {
            updatePreferenceScreen.setSummary("\u6B63\u5728\u4E0B\u8F7D\uFF0C\u8BF7\u7A0D\u5019..");
            updatePreferenceScreen.setEnabled(false);
        } else
        {
            updatePreferenceScreen.setSummary(0x7f0a0002);
        }
        updatePreferenceScreen.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {

            @Override
			public boolean onPreferenceClick(Preference preference)
            {
                if(DBHelper.testNet())
                {
                    updatePreferenceScreen.setEnabled(false);
                    DownAsy downasy = new DownAsy(handler, 0x7f090008);
                    if(downasy.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        downasy.cancel(true);
                        downasy.execute(null);
                    } else
                    {
                        downasy.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
                return false;
            }

            final HomeSet this$0;

            
            {
                this$0 = HomeSet.this;
                super();
            }
        }
);
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        requestWindowFeature(1);
        super.onCreate(bundle);
        addPreferencesFromResource(0x7f040000);
        setChangeListener();
        getSizeAsy = new GetSizeAsy();
        GetSizeAsy getsizeasy = getSizeAsy;
        String as[] = new String[1];
        as[0] = "";
        getsizeasy.execute(as);
        util = new Util(this);
    }

    @Override
	protected void onDestroy()
    {
        if(getSizeAsy.getStatus() == android.os.AsyncTask.Status.RUNNING)
            getSizeAsy.cancel(true);
        finish();
        super.onDestroy();
    }

    @Override
	protected void onStop()
    {
        if(getSizeAsy.getStatus() == android.os.AsyncTask.Status.RUNNING)
            getSizeAsy.cancel(true);
        finish();
        super.onStop();
    }

    private Preference clearPreference;
    private GetSizeAsy getSizeAsy;
    Handler handler;
    private String size;
    private PreferenceScreen updatePreferenceScreen;
    private Util util;





}
