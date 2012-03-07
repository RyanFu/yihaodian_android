// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class MyButton extends Button
{

    public MyButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.thestore.main.R.styleable.mybutton_style);
        normalDrawable = typedarray.getDrawable(0);
        pressDrawable = typedarray.getDrawable(1);
        typedarray.recycle();
        setBackgroundDrawable(normalDrawable);
    }

    @Override
	public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 36
    //                   0 42
    //                   1 53
    //                   2 36
    //                   3 53;
           goto _L1 _L2 _L3 _L1 _L3
_L1:
        return super.onTouchEvent(motionevent);
_L2:
        setBackgroundDrawable(pressDrawable);
        continue; /* Loop/switch isn't completed */
_L3:
        setBackgroundDrawable(normalDrawable);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private Drawable normalDrawable;
    private Drawable pressDrawable;
}
