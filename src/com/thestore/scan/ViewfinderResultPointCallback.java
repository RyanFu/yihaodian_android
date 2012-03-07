// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

// Referenced classes of package com.thestore.scan:
//            ViewfinderView

final class ViewfinderResultPointCallback
    implements ResultPointCallback
{

    ViewfinderResultPointCallback(ViewfinderView viewfinderview)
    {
        viewfinderView = viewfinderview;
    }

    @Override
	public void foundPossibleResultPoint(ResultPoint resultpoint)
    {
        viewfinderView.addPossibleResultPoint(resultpoint);
    }

    private final ViewfinderView viewfinderView;
}
