// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.os.Bundle;
import com.thestore.util.Util;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class ShowOrderInfo extends ActivityMain
{

    public ShowOrderInfo()
    {
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030037);
        util = new Util(this);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a00ee);
    }

    Util util;
}
