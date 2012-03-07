// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.zxing.ResultPoint;
import java.util.*;

// Referenced classes of package com.thestore.scan:
//            CameraManager

public final class ViewfinderView extends View
{

    public ViewfinderView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        left = 120;
        top = 60;
        Resources resources = getResources();
        laserColor = resources.getColor(0x7f07000c);
        resultPointColor = resources.getColor(0x7f070003);
        scannerAlpha = 0;
        possibleResultPoints = new HashSet(5);
        new DisplayMetrics();
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        screen_w = displaymetrics.widthPixels;
        screen_h = displaymetrics.heightPixels;
    }

    public void addPossibleResultPoint(ResultPoint resultpoint)
    {
        possibleResultPoints.add(resultpoint);
    }

    public void drawResultBitmap(Bitmap bitmap)
    {
        resultBitmap = bitmap;
        invalidate();
    }

    public void drawViewfinder()
    {
        resultBitmap = null;
        invalidate();
    }

    @Override
	public void onDraw(Canvas canvas)
    {
        Rect rect;
        if(Build.BOARD.equals("buzz"))
            left = 30;
        rect = CameraManager.get().getFramingRect();
        break MISSING_BLOCK_LABEL_24;
        if(rect != null && resultBitmap == null)
        {
            paint.setColor(getResources().getColor(0x7f070008));
            canvas.drawRect(0.0F, 0.0F, screen_w, top, paint);
            canvas.drawRect(0.0F, top, left, screen_h, paint);
            canvas.drawRect(left, screen_h - top, screen_w, screen_h, paint);
            canvas.drawRect(screen_w - left, top, screen_w, screen_h - top, paint);
            paint.setColor(laserColor);
            paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
            scannerAlpha = (1 + scannerAlpha) % SCANNER_ALPHA.length;
            canvas.drawRect(left, (screen_h >> 1) - 1, screen_w - left, 2 + screen_h >> 1, paint);
            Collection collection = possibleResultPoints;
            Collection collection1 = lastPossibleResultPoints;
            if(collection.isEmpty())
            {
                lastPossibleResultPoints = null;
            } else
            {
                possibleResultPoints = new HashSet(5);
                lastPossibleResultPoints = collection;
                paint.setAlpha(255);
                paint.setColor(resultPointColor);
                Iterator iterator = collection.iterator();
                while(iterator.hasNext()) 
                {
                    ResultPoint resultpoint = (ResultPoint)iterator.next();
                    canvas.drawCircle(left + resultpoint.getX(), rect.top + resultpoint.getY(), 6F, paint);
                }
            }
            if(collection1 != null)
            {
                paint.setAlpha(127);
                paint.setColor(resultPointColor);
                ResultPoint resultpoint1;
                for(Iterator iterator1 = collection1.iterator(); iterator1.hasNext(); canvas.drawCircle(left + resultpoint1.getX(), rect.top + resultpoint1.getY(), 3F, paint))
                    resultpoint1 = (ResultPoint)iterator1.next();

            }
            postInvalidateDelayed(100L, left, rect.top, screen_w - left, rect.bottom);
        }
        return;
    }

    private static final long ANIMATION_DELAY = 100L;
    private static final int OPAQUE = 255;
    private static final int SCANNER_ALPHA[];
    private final int laserColor;
    private Collection lastPossibleResultPoints;
    private int left;
    private final Paint paint = new Paint();
    private Collection possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultPointColor;
    private int scannerAlpha;
    private int screen_h;
    private int screen_w;
    private int top;

    static 
    {
        int ai[] = new int[8];
        ai[0] = 0;
        ai[1] = 64;
        ai[2] = 128;
        ai[3] = 192;
        ai[4] = 255;
        ai[5] = 192;
        ai[6] = 128;
        ai[7] = 64;
        SCANNER_ALPHA = ai;
    }
}
