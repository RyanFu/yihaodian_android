// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import com.thestore.util.Util;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Statement extends ActivityMain
{

    public Statement()
    {
    }

    private void initView()
    {
        util.setDefaultTitle(0x7f0a013c);
        title = (TextView)findViewById(0x7f0901cb);
        title.setText(0x7f0a013d);
        content = (TextView)findViewById(0x7f0901cc);
        content.setText(0x7f0a013e);
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03003b);
        bottomOnClick(this);
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

    private TextView content;
    private TextView title;
    private Util util;
}
