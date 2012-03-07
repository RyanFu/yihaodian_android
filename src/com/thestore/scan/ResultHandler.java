// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.app.Activity;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;

public abstract class ResultHandler
{

    ResultHandler(Activity activity, ParsedResult parsedresult)
    {
        result = parsedresult;
    }

    public CharSequence getDisplayContents()
    {
        return result.getDisplayResult().replace("\r", "");
    }

    public abstract int getDisplayTitle();

    ParsedResult getResult()
    {
        return result;
    }

    public final ParsedResultType getType()
    {
        return result.getType();
    }

    private final ParsedResult result;
}
