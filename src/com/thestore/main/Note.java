// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.*;
import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Referenced classes of package com.thestore.main:
//            ActivityMain, SearchResult

public class Note extends ActivityMain
{
    class NoteAsyncTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground(aobj);
        }

        protected transient List doInBackground(Object aobj[])
        {
            List list1 = null;
            List list2;
            XStream xstream = DBHelper.getxStream();
            DBHelper dbhelper = new DBHelper();
            Object aobj1[] = new Object[9];
            aobj1[0] = DBHelper.getTrader();
            aobj1[1] = mcsiteidList;
            aobj1[2] = provinceIdList;
            aobj1[3] = list;
            aobj1[4] = categoryIdList;
            aobj1[5] = brandIdList;
            aobj1[6] = sortTypeList;
            aobj1[7] = currentPageList;
            aobj1[8] = pageSizeList;
            list2 = (List)xstream.fromXML(dbhelper.getResultString(dbhelper.getHttpResponse("searchProductForList", xstream.toXML(((Object) (aobj1))))));
            list1 = list2;
_L2:
            return list1;
            Exception exception;
            exception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((List)obj);
        }

        protected void onPostExecute(List list1)
        {
            int k;
            int l;
            int i1;
            int j1;
            searchLayout.removeAllViews();
            cancelProgress();
            int i = vector.size();
            int j;
            Exception exception;
            if(i > pageSize)
                j = pageSize;
            else
                j = i;
            if(!DBHelper.getTimeOut()) goto _L2; else goto _L1
_L1:
            (new android.app.AlertDialog.Builder(Note.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                public boolean onKey(DialogInterface dialoginterface, int k1, KeyEvent keyevent)
                {
                    if(k1 == 4)
                    {
                        findViewById(0x7f090129).setVisibility(0);
                        searchScrollView.setVisibility(8);
                        noteListView.setVisibility(8);
                        textView.setText(0x7f0a0130);
                        util.setDefaultTitle(0x7f0a0127);
                    }
                    return false;
                }

                final NoteAsyncTask this$1;

                
                {
                    this$1 = NoteAsyncTask.this;
                    super();
                }
            }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int k1)
                {
                    findViewById(0x7f090129).setVisibility(0);
                    searchScrollView.setVisibility(8);
                    noteListView.setVisibility(8);
                    textView.setText(0x7f0a0130);
                    util.setDefaultTitle(0x7f0a0127);
                }

                final NoteAsyncTask this$1;

                
                {
                    this$1 = NoteAsyncTask.this;
                    super();
                }
            }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int k1)
                {
                    if(DBHelper.testNet())
                    {
                        showProgress();
                        NoteAsyncTask noteasynctask = new NoteAsyncTask();
                        if(noteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            noteasynctask.cancel(true);
                            noteasynctask.execute(null);
                        } else
                        {
                            noteasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final NoteAsyncTask this$1;

                
                {
                    this$1 = NoteAsyncTask.this;
                    super();
                }
            }
).create().show();
_L4:
            return;
_L2:
            if(list1 == null) goto _L4; else goto _L3
_L3:
            if(list1.size() <= 0) goto _L4; else goto _L5
_L5:
            k = 0;
_L12:
            if(k >= j) goto _L4; else goto _L6
_L6:
            l = ((Page)list1.get(k)).getObjList().size();
            if(l != 0) goto _L8; else goto _L7
_L7:
            showViewResult(k, 0, ((Page)list1.get(k)).getObjList(), list1);
              goto _L9
_L11:
            if(j1 >= i1) goto _L9; else goto _L10
_L10:
            showViewResult(k, j1, ((Page)list1.get(k)).getObjList(), list1);
            j1++;
              goto _L11
            exception;
              goto _L4
_L14:
            i1 = l;
            break; /* Loop/switch isn't completed */
_L9:
            k++;
              goto _L12
_L8:
            if(l <= 3)
                continue; /* Loop/switch isn't completed */
            i1 = 3;
            break; /* Loop/switch isn't completed */
            if(true) goto _L14; else goto _L13
_L13:
            j1 = 0;
              goto _L11
        }

        final Note this$0;

        NoteAsyncTask()
        {
            this$0 = Note.this;
            super();
        }
    }


    public Note()
    {
        vector = new Vector();
        list = new ArrayList();
        pageSize = 10;
        mcsiteidList = new ArrayList();
        provinceIdList = new ArrayList();
        categoryIdList = new ArrayList();
        brandIdList = new ArrayList();
        sortTypeList = new ArrayList();
        currentPageList = new ArrayList();
        pageSizeList = new ArrayList();
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 2: default 32
            //                           2131296297: 33
            //                           2131296340: 51;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                cancelProgress();
                judgeBuySucess(message);
                continue; /* Loop/switch isn't completed */
_L3:
                isFinish = false;
                if(message.obj != null)
                {
                    netList = (List)message.obj;
                    if(netList != null)
                    {
                        noteListView.setVisibility(0);
                        noteListView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
                        noteListView.setAdapter(new ArrayAdapter(Note.this, 0x7f030018, netList));
                    }
                }
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Note this$0;

            
            {
                this$0 = Note.this;
                super();
            }
        }
;
    }

    private void addViewNote(final int position)
    {
        View view = inflater.inflate(0x7f030024, null);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f090131);
        int i;
        if(position % 2 == 0)
            i = 0;
        else
            i = 0x7f020083;
        linearlayout.setBackgroundResource(i);
        ((TextView)view.findViewById(0x7f090132)).setText((String)vector.get(showCount - position - 1));
        ((Button)view.findViewById(0x7f090133)).setOnClickListener(new android.view.View.OnClickListener() );
        noteLayout.addView(view);
    }

    private void clearKeyBoard()
    {
        task = new TimerTask() {

            public void run()
            {
                Message message = new Message();
                handler.sendMessage(message);
            }

            final Note this$0;
            final Handler val$handler;

            
            {
                this$0 = Note.this;
                handler = handler1;
                super();
            }
        }
;
    }

    private void init()
    {
        inflater = LayoutInflater.from(this);
        textView = (TextView)findViewById(0x7f090125);
        noteLayout = (LinearLayout)findViewById(0x7f09012d);
        buyArray = getResources().getStringArray(0x7f060006);
        searchScrollView = (ScrollView)findViewById(0x7f090127);
        searchLayout = (LinearLayout)findViewById(0x7f090128);
        imageLoader = new ImageLoader(getApplicationContext());
        ((Button)findViewById(0x7f09012f)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                int j = vector.size();
                if(j != 0)
                {
                    util.setDefaultTitle(0x7f0a012d);
                    if(DBHelper.testNet())
                    {
                        for(int k = 0; k < j; k++)
                            list.set(k, vector.get(j - k - 1));

                        showProgress();
                        textView.setText(0x7f0a0131);
                        findViewById(0x7f090129).setVisibility(8);
                        searchScrollView.setVisibility(0);
                        NoteAsyncTask noteasynctask = new NoteAsyncTask();
                        if(noteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            noteasynctask.cancel(true);
                            noteasynctask.execute(null);
                        } else
                        {
                            noteasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    showToast(0x7f0a012f);
                }
            }

            final Note this$0;

            
            {
                this$0 = Note.this;
                super();
            }
        }
);
        for(int i = 0; i < showCount; i++)
            addViewNote(i);

        editText = (EditText)findViewById(0x7f09012b);
        noteListView = (ListView)findViewById(0x7f090130);
        noteListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int j, long l)
            {
                editText.setText((CharSequence)netList.get(j));
                Editable editable = editText.getText();
                Selection.setSelection(editable, editable.length());
            }

            final Note this$0;

            
            {
                this$0 = Note.this;
                super();
            }
        }
);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int j, int k, int l)
            {
            }

            public void onTextChanged(CharSequence charsequence, int j, int k, int l)
            {
                String s = editText.getText().toString().trim();
                if(DBHelper.testNet())
                {
                    if(s.length() > 0)
                    {
                        SearchLongTailDao searchlongtaildao = new SearchLongTailDao(s, handler);
                        if(!isFinish || searchlongtaildao.getStatus() == android.os.AsyncTask.Status.FINISHED)
                        {
                            searchlongtaildao.execute(null);
                            isFinish = true;
                        }
                    } else
                    {
                        noteListView.setVisibility(8);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Note this$0;

            
            {
                this$0 = Note.this;
                super();
            }
        }
);
        ((Button)findViewById(0x7f09012c)).setOnClickListener(new android.view.View.OnClickListener() );
        timer = new Timer();
        clearKeyBoard();
        timer.schedule(task, 0L, 500L);
    }

    private void initList(int i)
    {
        mcsiteidList.add(i, Long.valueOf(1L));
        provinceIdList.add(i, Long.valueOf(User.province));
        categoryIdList.add(i, Long.valueOf(0L));
        brandIdList.add(i, Long.valueOf(0L));
        sortTypeList.add(i, Integer.valueOf(2));
        currentPageList.add(i, Integer.valueOf(1));
        pageSizeList.add(i, Integer.valueOf(pageSize));
    }

    private void judgeBuySucess(Message message)
    {
        if(message.obj != null)
        {
            if(((Integer)message.obj).intValue() == 1)
            {
                Store store = new Store(this);
                Cart.cartTotal = 1L + Cart.cartTotal;
                store.storeString("cartTotal", (new StringBuilder()).append(Cart.cartTotal).append("").toString());
                setCartImage(Long.valueOf(Cart.cartTotal));
                showToast(0x7f0a0108);
            } else
            if(((Integer)message.obj).intValue() == -21)
            {
                showToast(0x7f0a0109);
            } else
            {
                int i = ((Integer)message.obj).intValue();
                try
                {
                    showToast(buyArray[-i]);
                }
                catch(Exception exception)
                {
                    util.showNetNull();
                }
            }
        } else
        {
            util.showNetNull();
        }
    }

    private void loadFileData()
    {
        Properties properties = new Properties();
        int i;
        int j;
        properties.load(openFileInput("thestore.properties"));
        i = Integer.parseInt(properties.getProperty("note_count"));
        showCount = i;
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_131;
        vector.add(j, properties.getProperty((new StringBuilder()).append("note").append(j).toString()));
        list.add(j, properties.getProperty((new StringBuilder()).append("note").append(j).toString()));
        initList(j);
        j++;
          goto _L1
        Exception exception;
        exception;
        exception.printStackTrace();
    }

    private void saveFileData()
    {
        Properties properties;
        properties = new Properties();
        properties.clear();
        int i = vector.size();
        properties.put("note_count", (new StringBuilder()).append(i).append("").toString());
        for(int j = 0; j < i; j++)
            properties.put((new StringBuilder()).append("note").append(j).toString(), vector.elementAt(j));

        properties.store(openFileOutput("thestore.properties", 0), "");
_L1:
        return;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception.printStackTrace();
          goto _L1
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
          goto _L1
    }

    private void setNoteMenu(boolean flag)
    {
        menu.findItem(0x7f090214).setVisible(flag);
        menu.findItem(0x7f090215).setVisible(flag);
    }

    private void showViewResult(final int position, int i, final List productVO, List list1)
    {
        View view = inflater.inflate(0x7f030025, null);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f090134);
        if(i == 0)
        {
            linearlayout.setVisibility(0);
            ((TextView)view.findViewById(0x7f090135)).setText((CharSequence)list.get(position));
        } else
        {
            view.findViewById(0x7f090134).setVisibility(8);
        }
        if(productVO.size() != 0)
        {
            ViewHolder viewholder = new ViewHolder();
            final LinearLayout layout = (LinearLayout)view.findViewById(0x7f090136);
            layout.setVisibility(0);
            viewholder.imageView = (ImageView)view.findViewById(0x7f090137);
            view.setTag(viewholder);
            String s = ((ProductVO)((Page)list1.get(position)).getObjList().get(i)).getMiniDefaultProductUrl();
            ((TextView)view.findViewById(0x7f090138)).setText(((ProductVO)((Page)list1.get(position)).getObjList().get(i)).getCnName());
            ((TextView)view.findViewById(0x7f090139)).setText((new StringBuilder()).append(((ProductVO)((Page)list1.get(position)).getObjList().get(i)).getPrice()).append("").toString());
            final long produceId = ((ProductVO)((Page)list1.get(position)).getObjList().get(i)).getProductId().longValue();
            layout.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    if(DBHelper.testNet())
                    {
                        Intent intent = new Intent("com.thestore.main.ProductDetail");
                        intent.putExtra("PRODUCT_ID", produceId);
                        startActivity(intent);
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final Note this$0;
                final long val$produceId;

            
            {
                this$0 = Note.this;
                produceId = l;
                super();
            }
            }
);
            layout.setOnTouchListener(new android.view.View.OnTouchListener() {

                public boolean onTouch(View view1, MotionEvent motionevent)
                {
                    motionevent.getAction();
                    JVM INSTR tableswitch 0 3: default 36
                //                               0 38
                //                               1 50
                //                               2 36
                //                               3 50;
                       goto _L1 _L2 _L3 _L1 _L3
_L1:
                    return false;
_L2:
                    layout.setBackgroundResource(0x7f0200b9);
                    continue; /* Loop/switch isn't completed */
_L3:
                    layout.setBackgroundResource(0x7f020084);
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final Note this$0;
                final LinearLayout val$layout;

            
            {
                this$0 = Note.this;
                layout = linearlayout;
                super();
            }
            }
);
            ((Button)view.findViewById(0x7f09013a)).setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    if(User.token != null)
                    {
                        showProgress();
                        Cart.merchantId = ((ProductVO)productVO.get(position)).getMerchantId().longValue();
                        CartAsyncTask cartasynctask = new CartAsyncTask(produceId, handler, 0x7f090029);
                        if(cartasynctask.getStatus() != android.os.AsyncTask.Status.RUNNING)
                            cartasynctask.execute(null);
                    } else
                    {
                        util.changeMain("com.thestore.main.userland");
                    }
                }

                final Note this$0;
                final int val$position;
                final long val$produceId;
                final List val$productVO;

            
            {
                this$0 = Note.this;
                productVO = list1;
                position = i;
                produceId = l;
                super();
            }
            }
);
            linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    if(DBHelper.testNet())
                    {
                        Intent intent = new Intent();
                        intent.setClass(Note.this, com/thestore/main/SearchResult);
                        intent.putExtra("keyword", (String)list.get(position));
                        startActivity(intent);
                    } else
                    {
                        util.showNetNull();
                    }
                }

                final Note this$0;
                final int val$position;

            
            {
                this$0 = Note.this;
                position = i;
                super();
            }
            }
);
            viewholder.imageView.setTag(s);
            imageLoader.DisplayImage(s, this, viewholder.imageView);
        } else
        {
            ((LinearLayout)view.findViewById(0x7f090136)).setVisibility(8);
            linearlayout.setVisibility(0);
            ((TextView)view.findViewById(0x7f090135)).setText((CharSequence)list.get(position));
        }
        searchLayout.addView(view);
    }

    public void onCreate(Bundle bundle)
    {
        onCreate(bundle);
        setContentView(0x7f030023);
        bottomOnClick(this);
        util = new Util(this);
        util.setDefaultTitle(0x7f0a0127);
        loadFileData();
        init();
    }

    public boolean onCreateOptionsMenu(Menu menu1)
    {
        menu = menu1;
        (new MenuInflater(this)).inflate(0x7f0c0008, menu1);
        return true;
    }

    protected void onDestroy()
    {
        saveFileData();
        list = null;
        onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && searchLayout.isShown())
        {
            findViewById(0x7f090129).setVisibility(0);
            searchScrollView.setVisibility(8);
            noteListView.setVisibility(8);
            textView.setText(0x7f0a0130);
            util.setDefaultTitle(0x7f0a0127);
            flag = false;
        } else
        {
            flag = onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 3: default 40
    //                   2131296770: 42
    //                   2131296788: 101
    //                   2131296789: 204;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        if(searchLayout.isShown())
        {
            textView.setText(0x7f0a0130);
            findViewById(0x7f090129).setVisibility(0);
            searchScrollView.setVisibility(8);
            noteListView.setVisibility(8);
        } else
        {
            finish();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(vector.size() != 0)
        {
            if(DBHelper.testNet())
            {
                showProgress();
                findViewById(0x7f090129).setVisibility(8);
                searchScrollView.setVisibility(0);
                NoteAsyncTask noteasynctask = new NoteAsyncTask();
                if(noteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    noteasynctask.cancel(true);
                    noteasynctask.execute(null);
                } else
                {
                    noteasynctask.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        } else
        {
            showToast(0x7f0a012f);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        (new Builder(this)).setTitle(0x7f0a00c0).setMessage(0x7f0a012e).setPositiveButton(0x7f0a00c2, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                vector.removeAllElements();
                showCount = 0;
                noteLayout.removeAllViews();
            }

            final Note this$0;

            
            {
                this$0 = Note.this;
                super();
            }
        }
).setNegativeButton(0x7f0a00c3, null).create().show();
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected void onPause()
    {
        saveFileData();
        onPause();
    }

    public boolean onPrepareOptionsMenu(Menu menu1)
    {
        boolean flag;
        MenuItem menuitem;
        boolean flag1;
        if(!searchLayout.isShown())
            flag = true;
        else
            flag = false;
        setNoteMenu(flag);
        menuitem = menu1.findItem(0x7f090215);
        if(!vector.isEmpty())
            flag1 = true;
        else
            flag1 = false;
        menuitem.setEnabled(flag1);
        return onPrepareOptionsMenu(menu1);
    }

    protected void onRestart()
    {
        onRestart();
        bottomOnClick(this);
    }

    List brandIdList;
    private String buyArray[];
    List categoryIdList;
    List currentPageList;
    EditText editText;
    private Handler handler;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private boolean isFinish;
    private List list;
    List mcsiteidList;
    private Menu menu;
    private List netList;
    private LinearLayout noteLayout;
    private ListView noteListView;
    private int pageSize;
    List pageSizeList;
    List provinceIdList;
    private LinearLayout searchLayout;
    private ScrollView searchScrollView;
    private int showCount;
    List sortTypeList;
    private TimerTask task;
    private TextView textView;
    private Timer timer;
    private Util util;
    private Vector vector;















/*
    static List access$502(Note note, List list1)
    {
        note.netList = list1;
        return list1;
    }

*/




/*
    static boolean access$702(Note note, boolean flag)
    {
        note.isFinish = flag;
        return flag;
    }

*/




/*
    static int access$902(Note note, int i)
    {
        note.showCount = i;
        return i;
    }

*/


/*
    static int access$908(Note note)
    {
        int i = note.showCount;
        note.showCount = i + 1;
        return i;
    }

*/


/*
    static int access$910(Note note)
    {
        int i = note.showCount;
        note.showCount = i - 1;
        return i;
    }

*/

    // Unreferenced inner class com/thestore/main/Note$5

/* anonymous class */
    class _cls5 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(util.getEditString(editText).length() < 1)
                noteListView.setVisibility(8);
        }

        final Note this$0;

            
            {
                this$0 = Note.this;
                super();
            }
    }

}
