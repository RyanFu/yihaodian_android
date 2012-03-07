// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.app.Activity;
import com.google.zxing.client.result.ParsedResult;

// Referenced classes of package com.thestore.scan:
//            ResultHandler

public final class TextResultHandler extends ResultHandler
{

    public TextResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    @Override
	public int getDisplayTitle()
    {
        return 0;
    }
}
