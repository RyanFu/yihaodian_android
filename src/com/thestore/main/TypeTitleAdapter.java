// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TypeTitleAdapter extends BaseAdapter
{

    public TypeTitleAdapter(Context context, int i)
    {
        type = i;
        inflater = LayoutInflater.from(context);
        array = context.getResources().getStringArray(0x7f060005);
    }

    @Override
	public int getCount()
    {
        return array.length;
    }

    @Override
	public Object getItem(int i)
    {
        return Integer.valueOf(i);
    }

    @Override
	public long getItemId(int i)
    {
        return i;
    }

    @Override
	public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = inflater.inflate(0x7f030047, null);
        TextView textview = (TextView)view1.findViewById(0x7f0901e0);
        textview.setText(array[i]);
        if(type == i)
        {
            textview.requestFocus();
            textview.setBackgroundResource(0x7f020094);
            textview.setTextColor(Color.rgb(167, 32, 36));
        }
        return view1;
    }

    public static int type = 0;
    private String array[];
    private LayoutInflater inflater;

}
