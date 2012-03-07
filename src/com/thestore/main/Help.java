// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.thestore.util.Util;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Help extends ActivityMain
{
    private class MyExpandAdapter extends BaseExpandableListAdapter
    {

        @Override
		public Object getChild(int i, int j)
        {
            return ((List)child.get(i)).get(j);
        }

        @Override
		public long getChildId(int i, int j)
        {
            return 0L;
        }

        @Override
		public View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
        {
            TextView textview = new TextView(activity);
            textview.setBackgroundColor(-1);
            textview.setTextSize(16F);
            textview.setPadding(17, 10, 17, 10);
            textview.setText((CharSequence)((List)child.get(i)).get(j));
            textview.setTextColor(Color.rgb(115, 77, 1));
            textview.setEnabled(false);
            return textview;
        }

        @Override
		public int getChildrenCount(int i)
        {
            return ((List)child.get(i)).size();
        }

        @Override
		public Object getGroup(int i)
        {
            return group.get(i);
        }

        @Override
		public int getGroupCount()
        {
            return group.size();
        }

        @Override
		public long getGroupId(int i)
        {
            return i;
        }

        @Override
		public View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
        {
            View view1;
            ImageView imageview;
            if(view == null)
                view1 = LayoutInflater.from(activity).inflate(0x7f030010, null);
            else
                view1 = view;
            imageview = (ImageView)view1.findViewById(0x7f0900b2);
            if(flag)
                imageview.setBackgroundResource(0x7f02003b);
            else
                imageview.setBackgroundResource(0x7f0200a8);
            ((TextView)view1.findViewById(0x7f0900b1)).setText(getGroup(i).toString());
            return view1;
        }

        @Override
		public boolean hasStableIds()
        {
            return false;
        }

        @Override
		public boolean isChildSelectable(int i, int j)
        {
            return true;
        }

        @Override
		public void onGroupCollapsed(int i)
        {
        }

        @Override
		public void onGroupExpanded(int i)
        {
            for(int j = 0; j < group.size(); j++)
                if(i != j)
                    listview.collapseGroup(j);

        }

        Activity activity;
        public List child;
        public List group;
        final Help this$0;

        public MyExpandAdapter(Activity activity1, List list, List list1)
        {
            this$0 = Help.this;
            super();
            activity = activity1;
            group = list;
            child = list1;
        }
    }


    public Help()
    {
        questions = null;
        answers = null;
    }

    private void addValue(String s, String s1)
    {
        group.add(s);
        ArrayList arraylist = new ArrayList();
        arraylist.add(s1);
        child.add(arraylist);
    }

    private void init()
    {
        group = new ArrayList();
        child = new ArrayList();
        for(int i = 0; i < questions.length; i++)
            addValue(questions[i], answers[i]);

    }

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f03000f);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a013f);
        questions = getResources().getStringArray(0x7f060009);
        answers = getResources().getStringArray(0x7f06000a);
        init();
        adapter = new MyExpandAdapter(this, group, child);
        listview = (ExpandableListView)findViewById(0x7f0900b0);
        listview.setSelection(2);
        listview.setAdapter(adapter);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0000, menu);
        return true;
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296770 2131296770: default 24
    //                   2131296770 26;
           goto _L1 _L2
_L1:
        return true;
_L2:
        finish();
        if(true) goto _L1; else goto _L3
_L3:
    }

    @Override
	protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    MyExpandAdapter adapter;
    private String answers[];
    List child;
    List group;
    ExpandableListView listview;
    private String questions[];
    private Util util;
}
