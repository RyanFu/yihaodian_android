// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.*;

// Referenced classes of package com.thestore.main:
//            StartService

public class BootReceiver extends BroadcastReceiver
{

    public BootReceiver()
    {
    }

    @Override
	public void onReceive(Context context, Intent intent)
    {
        context.startService(new Intent(context, com/thestore/main/StartService));
    }
}
