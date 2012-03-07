// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;

// Referenced classes of package com.thestore.scan:
//            TextResultHandler, ResultHandler

public final class ResultHandlerFactory
{

    private ResultHandlerFactory()
    {
    }

    public static ResultHandler makeResultHandler(Activity activity, Result result)
    {
        return new TextResultHandler(activity, parseResult(result));
    }

    private static ParsedResult parseResult(Result result)
    {
        return ResultParser.parseResult(result);
    }
}
