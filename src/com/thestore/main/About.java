// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import com.thestore.net.DBHelper;
import com.thestore.util.Util;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class About extends ActivityMain
{

    public About()
    {
        versionTextView = null;
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030000);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0137);
        String s;
        Object aobj[];
        String s1;
        try
        {
            if(DBHelper.version == null)
                DBHelper.version = getPackageManager().getPackageInfo("com.thestore.main", 0).versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) { }
        versionTextView = (TextView)findViewById(0x7f09005e);
        s = getResources().getString(0x7f0a013b);
        aobj = new Object[1];
        aobj[0] = DBHelper.version;
        s1 = String.format(s, aobj);
        versionTextView.setText(s1);
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

    private Util util;
    private TextView versionTextView;
}
