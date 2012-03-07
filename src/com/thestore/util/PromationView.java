// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.*;
import com.thestore.net.DBHelper;
import com.thestore.net.TypeAsyncTask;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;

// Referenced classes of package com.thestore.util:
//            Util

public class PromationView extends View
    implements android.view.GestureDetector.OnGestureListener
{

    public PromationView(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        distan_X = 300;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                int i;
                i = 0;
                if(message.obj == null)
                    break MISSING_BLOCK_LABEL_127;
                Page page;
                page = (Page)message.obj;
                PromationView.list = page.getObjList();
                PromationView.count = PromationView.list.size();
_L1:
                if(i >= PromationView.count)
                    break MISSING_BLOCK_LABEL_127;
                PromationView.arraString[i] = ((ProductVO)page.getObjList().get(i)).getAdvertisement();
                if(PromationView.arraString[i].length() > 11)
                    PromationView.arraString[i] = (new StringBuilder()).append(PromationView.arraString[i].substring(0, 11)).append("...").toString();
                postInvalidate();
                i++;
                  goto _L1
                Exception exception;
                exception;
            }

            final PromationView this$0;

            
            {
                this$0 = PromationView.this;
                super();
            }
        }
;
        context = context1;
        paint = new Paint();
        paint.setColor(Color.rgb(102, 102, 102));
        paint.setAntiAlias(true);
        paint.setTextSize(16F);
        paint.setFakeBoldText(true);
        gestureDetector = new GestureDetector(this);
        if(DBHelper.testNet())
        {
            TypeAsyncTask typeasynctask = new TypeAsyncTask(handler, 0x7f090031);
            if(typeasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                typeasynctask.cancel(true);
                typeasynctask.execute(null);
            } else
            {
                typeasynctask.execute(null);
            }
        }
    }

    public void handleScroll(float f)
    {
        if(f <= 6D) goto _L2; else goto _L1
_L1:
        if(scrollX < 150 + distan_X * (count - 1))
            scrollX = 20 + scrollX;
_L4:
        invalidate();
        return;
_L2:
        if(f < -6D && scrollX >= -60)
            scrollX = scrollX - 20;
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public boolean onDown(MotionEvent motionevent)
    {
        return true;
    }

    @Override
	protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.clipRect(20, 10, 250, 50);
        for(int i = 0; i < count; i++)
            if(arraString[i] != null)
                canvas.drawText(arraString[i], 30 + (-scrollX + i * distan_X), 33F, paint);

    }

    @Override
	public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return true;
    }

    @Override
	public void onLongPress(MotionEvent motionevent)
    {
    }

    @Override
	public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        handleScroll(f);
        return true;
    }

    @Override
	public void onShowPress(MotionEvent motionevent)
    {
    }

    @Override
	public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return true;
    }

    @Override
	public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 1: default 28
    //                   0 313
    //                   1 39;
           goto _L1 _L2 _L3
_L1:
        boolean flag = gestureDetector.onTouchEvent(motionevent);
_L5:
        return flag;
_L3:
        int i;
        if(Math.abs((int)motionevent.getRawX() - temp) < 10)
        {
            if(arraString[index] != null)
                if(DBHelper.testNet())
                {
                    Intent intent = new Intent("com.thestore.main.ProductDetail");
                    intent.putExtra("PRODUCT_ID", ((ProductVO)list.get(index)).getProductId());
                    context.startActivity(intent);
                } else
                {
                    (new Util(context)).showNetNull();
                }
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        if(scrollX <= 0)
        {
            scrollX = 0;
            index = 0;
        }
        if(scrollX >= (count - 1) * distan_X - 150)
        {
            scrollX = (count - 1) * distan_X - 150;
            index = count - 1;
        }
        i = 0;
_L6:
label0:
        {
            if(i >= count - 1)
                continue; /* Loop/switch isn't completed */
            if(scrollX < i * distan_X || scrollX >= distan_X * (i + 1))
                break label0;
            if(motionevent.getRawX() - (float)temp > 0.0F)
            {
                scrollX = i * distan_X;
                index = i;
            } else
            {
                scrollX = distan_X * (i + 1);
                index = i + 1;
            }
            invalidate();
            flag = true;
        }
        if(true) goto _L5; else goto _L4
_L4:
        i++;
          goto _L6
        if(true) goto _L5; else goto _L2
_L2:
        temp = (int)motionevent.getRawX();
        if(true) goto _L1; else goto _L7
_L7:
    }

    public static String arraString[] = new String[10];
    public static int count;
    public static List list;
    private Context context;
    private int distan_X;
    private GestureDetector gestureDetector;
    Handler handler;
    private int index;
    private Paint paint;
    private int scrollX;
    private int temp;

}
