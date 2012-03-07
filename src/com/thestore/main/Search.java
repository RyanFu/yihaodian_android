// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.content.Intent;
import android.os.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.DBHelper;
import com.thestore.net.SearchLongTailDao;
import com.thestore.util.Util;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain, Home, SearchResult

public class Search extends ActivityMain
{

    public Search()
    {
        selectLongTaiListener = new android.widget.AdapterView.OnItemClickListener() {

            @Override
			public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                txtSearch.setText((CharSequence)longTailList.get(i));
                Editable editable = txtSearch.getText();
                Selection.setSelection(editable, editable.length());
            }

            final Search this$0;

            
            {
                this$0 = Search.this;
                super();
            }
        }
;
        watcher = new TextWatcher() {

            @Override
			public void afterTextChanged(Editable editable)
            {
            }

            @Override
			public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            @Override
			public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
                String s = txtSearch.getText().toString().trim();
                if(DBHelper.testNet())
                {
                    if(!s.equals(""))
                    {
                        SearchLongTailDao searchlongtaildao = new SearchLongTailDao(s, myHandler);
                        if(!isFinish || searchlongtaildao.getStatus() == android.os.AsyncTask.Status.FINISHED)
                        {
                            searchlongtaildao.execute(null);
                            isFinish = true;
                        }
                    } else
                    {
                        layout.setVisibility(8);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Search this$0;

            
            {
                this$0 = Search.this;
                super();
            }
        }
;
        btnSreachListener = new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view)
            {
                String s = txtSearch.getText().toString().trim();
                if(s.equals(""))
                    showToast(0x7f0a010a);
                else
                if(s.length() > 50)
                    showToast(0x7f0a010b);
                else
                if(DBHelper.testNet())
                {
                    Intent intent = new Intent();
                    intent.setClass(Search.this, com/thestore/main/SearchResult);
                    intent.putExtra("keyword", s);
                    startActivity(intent);
                } else
                {
                    util.showNetNull();
                }
            }

            final Search this$0;

            
            {
                this$0 = Search.this;
                super();
            }
        }
;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296340 2131296340: default 24
            //                           2131296340 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                isFinish = false;
                if(message.obj != null)
                {
                    longTailList = (List)message.obj;
                    if(longTailList != null)
                    {
                        layout.setVisibility(0);
                        lvLongTail.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
                        lvLongTail.setAdapter(new ArrayAdapter(Search.this, 0x7f030018, longTailList));
                    }
                }
                if(true) goto _L1; else goto _L3
_L3:
            }

            final Search this$0;

            
            {
                this$0 = Search.this;
                super();
            }
        }
;
    }

    private void initViews()
    {
        btnSearch = (Button)findViewById(0x7f0901ad);
        txtSearch = (EditText)findViewById(0x7f0901ac);
        lvLongTail = (ListView)findViewById(0x7f0901af);
        layout = (LinearLayout)findViewById(0x7f0901ae);
        btnSearch.setOnClickListener(btnSreachListener);
        txtSearch.addTextChangedListener(watcher);
        lvLongTail.setOnItemClickListener(selectLongTaiListener);
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        util = new Util(this);
        setContentView(0x7f030032);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0105);
        initViews();
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c000d, menu);
        return true;
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
        JVM INSTR tableswitch 2131296770 2131296771: default 28
    //                   2131296770 30
    //                   2131296771 58;
           goto _L1 _L2 _L3
_L1:
        return true;
_L2:
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/Home);
        startActivity(intent);
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        if(util.getHeightPixels(this) >= 480F && !util.isAuto())
            util.changeMain("com.thestore.scan.CaptureActivity");
        else
        if(util.isG8())
            util.changeMain("com.thestore.scan.CaptureActivity");
        else
            util.displayFrameworkBugMessageAndExit();
        if(true) goto _L1; else goto _L4
_L4:
    }

    @Override
	public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.findItem(0x7f09021b).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
	protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private Button btnSearch;
    private android.view.View.OnClickListener btnSreachListener;
    private boolean isFinish;
    private LinearLayout layout;
    private List longTailList;
    private ListView lvLongTail;
    private Handler myHandler;
    private android.widget.AdapterView.OnItemClickListener selectLongTaiListener;
    private EditText txtSearch;
    private Util util;
    private TextWatcher watcher;



/*
    static List access$002(Search search, List list)
    {
        search.longTailList = list;
        return list;
    }

*/





/*
    static boolean access$302(Search search, boolean flag)
    {
        search.isFinish = flag;
        return flag;
    }

*/



}
