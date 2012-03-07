// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.*;
import android.text.TextPaint;
import android.view.*;
import android.widget.*;
import com.thestore.net.CategoryAsyncTask;
import com.thestore.net.DBHelper;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.CategoryVO;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Home

public class Type extends ActivityMain
    implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemClickListener
{
    public class TypeAdapter extends BaseAdapter
    {

        @Override
		public int getCount()
        {
            int i;
            if(pages == null)
                i = 0;
            else
                i = pages.getObjList().size();
            return i;
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
            View view1;
            CategoryVO categoryvo;
            if(view == null)
                view1 = inflater.inflate(0x7f030046, null);
            else
                view1 = view;
            categoryvo = (CategoryVO)pages.getObjList().get(i);
            ((TextView)view1.findViewById(0x7f0901de)).setText(categoryvo.getCategoryName());
            return view1;
        }

        private Activity context;
        private ImageLoader imageLoader;
        private LayoutInflater inflater;
        final Type this$0;

        public TypeAdapter(Activity activity)
        {
            this$0 = Type.this;
            super();
            context = activity;
            imageLoader = new ImageLoader(activity.getApplicationContext());
            inflater = LayoutInflater.from(activity);
        }
    }


    public Type()
    {
        pages = null;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296344 2131296344: default 24
            //                           2131296344 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                cancelProgress();
                if(message.obj != null)
                {
                    pages = (Page)message.obj;
                    typeAdapter.notifyDataSetChanged();
                } else
                if(!DBHelper.testNet())
                    util.showNetNull();
                if(true) goto _L1; else goto _L3
_L3:
            }

            final Type this$0;

            
            {
                this$0 = Type.this;
                super();
            }
        }
;
    }

    private void initLayout()
    {
        showProgress();
        CategoryAsyncTask categoryasynctask = new CategoryAsyncTask(0L, handler, 0x7f090058);
        if(categoryasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
        {
            categoryasynctask.cancel(true);
            categoryasynctask.execute(null);
        } else
        {
            categoryasynctask.execute(null);
        }
    }

    private void setTextViewFromLayout(int i, String s, int j)
    {
        final TextView textView = (TextView)((LinearLayout)findViewById(i)).findViewById(0x7f0901de);
        textView.setTextSize(16F);
        textView.setText(s);
        textView.getPaint().setFakeBoldText(true);
        textView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    View _tmp = (View)((LinearLayout)view.getParent()).getParent();
                    util.changeMain("com.thestore.main.TypeDetail");
                } else
                {
                    util.showNetNull();
                }
            }

            final Type this$0;

            
            {
                this$0 = Type.this;
                super();
            }
        }
);
        textView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 38
            //                           1 59
            //                           2 36
            //                           3 59;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L2:
                textView.setTextColor(Color.rgb(255, 140, 16));
                continue; /* Loop/switch isn't completed */
_L3:
                initLayout();
                textView.setTextColor(Color.rgb(102, 102, 102));
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Type this$0;
            final TextView val$textView;

            
            {
                this$0 = Type.this;
                textView = textview;
                super();
            }
        }
);
    }

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030041);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a00eb);
        listView = (ListView)findViewById(0x7f0901d3);
        typeAdapter = new TypeAdapter(this);
        listView.setAdapter(typeAdapter);
        listView.setOnItemClickListener(this);
        initLayout();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0000, menu);
        return true;
    }

    @Override
	public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent("com.thestore.main.TypeDetail");
        CategoryVO categoryvo = (CategoryVO)pages.getObjList().get(i);
        intent.putExtra("CategoryID", categoryvo.getId());
        intent.putExtra("CategoryName", categoryvo.getCategoryName());
        startActivity(intent);
    }

    @Override
	public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            Intent intent = new Intent();
            intent.setClass(this, com/thestore/main/Home);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(i, keyevent);
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
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/Home);
        startActivity(intent);
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

    public static int typeId = 0;
    private Handler handler;
    private ListView listView;
    private Page pages;
    private TypeAdapter typeAdapter;
    private Util util;




/*
    static Page access$002(Type type, Page page)
    {
        type.pages = page;
        return page;
    }

*/



}
