// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;

public class InstallApp extends Activity
{

    public InstallApp()
    {
    }

    public void installApp(String s)
    {
        String s1;
        Intent intent;
        if(Environment.getDataDirectory().exists())
            s1 = (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append(s).toString();
        else
            s1 = s;
        intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(s1)), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        installApp(getIntent().getStringExtra("path"));
        stopService(new Intent("com.thestore.util.DownService"));
    }
}
