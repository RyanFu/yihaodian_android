// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.*;
import android.util.AttributeSet;
import android.view.*;
import com.thestore.net.DBHelper;
import com.thestore.net.TypeAsyncTask;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;

// Referenced classes of package com.thestore.util:
//            Util

public class HotView extends View
    implements android.view.GestureDetector.OnGestureListener, android.view.View.OnClickListener, Runnable
{
    class ImageAsyn extends AsyncTask
    {

        @Override
		protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Bitmap[] doInBackground(String as[])
        {
            int i = list.size();
            Bitmap abitmap[] = new Bitmap[i];
            for(int j = 0; j < i; j++)
                abitmap[j] = dbHelper.getBitMap(((ProductVO)list.get(j)).getHotProductUrl());

            return abitmap;
        }

        @Override
		protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Bitmap[])obj);
        }

        protected void onPostExecute(Bitmap abitmap[])
        {
            HotView.bitmap3 = abitmap;
            for(int i = 0; i < HotView.count; i++)
                if(HotView.bitmap3[i] != null)
                {
                    int j = HotView.bitmap3[i].getWidth();
                    int k = HotView.bitmap3[i].getHeight();
                    float f = (float)distan_X / (float)j;
                    float f1 = 130F / k;
                    Matrix matrix = new Matrix();
                    matrix.postScale(f, f1);
                    bitmap[i] = Bitmap.createBitmap(HotView.bitmap3[i], 0, 0, j, k, matrix, true);
                }

            postInvalidate();
        }

        final HotView this$0;

        ImageAsyn()
        {
            this$0 = HotView.this;
            super();
        }
    }


    public HotView(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        bitmap = new Bitmap[10];
        bitmap2 = BitmapFactory.decodeResource(getResources(), 0x7f02004e);
        dbHelper = new DBHelper();
        distan_X = 300;
        handler = new Handler() {

            @Override
			public void handleMessage(Message message)
            {
                if(message.obj != null)
                    try
                    {
                        list = (List)message.obj;
                        if(list.size() > 0)
                        {
                            HotView.count = list.size();
                            ImageAsyn imageasyn = new ImageAsyn();
                            if(imageasyn.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                imageasyn.cancel(true);
                                imageasyn.execute(null);
                            } else
                            {
                                imageasyn.execute(null);
                            }
                        }
                    }
                    catch(Exception exception) { }
            }

            final HotView this$0;

            
            {
                this$0 = HotView.this;
                super();
            }
        }
;
        context = context1;
        paint = new Paint();
        paint.setColor(0xff0000ff);
        tempBitmap = BitmapFactory.decodeResource(getResources(), 0x7f020069);
        bitmapW = distan_X;
        gestureDetector = new GestureDetector(this);
        setClickable(true);
        setOnClickListener(this);
        setLongClickable(false);
        if(DBHelper.testNet())
        {
            TypeAsyncTask typeasynctask = new TypeAsyncTask(handler, 10L, 0x7f090030);
            if(typeasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                typeasynctask.cancel(true);
                typeasynctask.execute(null);
            } else
            {
                typeasynctask.execute(null);
            }
        }
        (new Thread(this)).start();
    }

    public void handleScroll(float f)
    {
        if(f <= 6D) goto _L2; else goto _L1
_L1:
        if(scrollX < 150 + (count - 1) * distan_X)
            scrollX = 20 + scrollX;
_L4:
        postInvalidate();
        return;
_L2:
        if(f < -6D && scrollX >= -60)
            scrollX = scrollX - 20;
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public void onClick(View view)
    {
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
        int i = 0;
        while(i < count) 
        {
            if(bitmap[i] != null)
                canvas.drawBitmap(bitmap[i], -scrollX + i * bitmapW, 0.0F, paint);
            else
                canvas.drawBitmap(tempBitmap, 130 + (-scrollX + i * bitmapW), 50F, paint);
            i++;
        }
        canvas.drawBitmap(bitmap2, 0.0F, 0.0F, paint);
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
        return false;
    }

    @Override
	public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 325
    //                   1 47
    //                   2 337
    //                   3 158;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        boolean flag = gestureDetector.onTouchEvent(motionevent);
_L7:
        return flag;
_L3:
        if(Math.abs((int)motionevent.getRawX() - temp) < 10)
        {
            invalidate();
            try
            {
                if(bitmap3[index] != null)
                    if(DBHelper.testNet())
                    {
                        Intent intent = new Intent("com.thestore.main.ProductDetail");
                        intent.putExtra("PRODUCT_ID", ((ProductVO)list.get(index)).getProductId());
                        context.startActivity(intent);
                    } else
                    {
                        (new Util(context)).showNetNull();
                    }
            }
            catch(Exception exception) { }
            break MISSING_BLOCK_LABEL_344;
        }
_L5:
        int i;
        if(scrollX <= 0)
        {
            scrollX = 0;
            index = 0;
        }
        if(scrollX >= (count - 1) * distan_X)
        {
            scrollX = (count - 1) * distan_X;
            index = count - 1;
        }
        invalidate();
        i = 0;
_L8:
        if(i >= count - 1) goto _L1; else goto _L6
_L6:
label0:
        {
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
          goto _L7
        i++;
          goto _L8
_L2:
        temp = (int)motionevent.getRawX();
          goto _L1
_L4:
        postInvalidate();
          goto _L1
        flag = true;
          goto _L7
    }

    @Override
	public void run()
    {
        do
            try
            {
                Thread.sleep(100L);
                postInvalidate();
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
        while(true);
    }

    public static Bitmap bitmap3[] = new Bitmap[10];
    public static int count;
    private Bitmap bitmap[];
    private Bitmap bitmap2;
    private int bitmapW;
    private Context context;
    private DBHelper dbHelper;
    private int distan_X;
    private GestureDetector gestureDetector;
    Handler handler;
    private int index;
    private List list;
    private Paint paint;
    private int scrollX;
    private int temp;
    private Bitmap tempBitmap;




/*
    static List access$002(HotView hotview, List list1)
    {
        hotview.list = list1;
        return list1;
    }

*/



}
