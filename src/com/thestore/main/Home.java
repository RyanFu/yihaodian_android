// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.*;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.*;
import android.text.*;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.scan.CameraManager;
import com.thestore.util.*;
import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.*;

// Referenced classes of package com.thestore.main:
//            ActivityMain, HomeSet, SearchResult

public class Home extends ActivityMain
    implements Runnable
{
    class ImageAsyn extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((List[])aobj);
        }

        protected transient Bitmap[] doInBackground(List alist[])
        {
            DBHelper dbhelper = new DBHelper();
            int i = alist[0].size();
            Bitmap abitmap[] = new Bitmap[i];
            for(int j = 0; j < i; j++)
                abitmap[j] = dbhelper.getBitMap(((ProductVO)alist[0].get(j)).getHotProductUrl());

            return abitmap;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Bitmap[])obj);
        }

        protected void onPostExecute(Bitmap abitmap[])
        {
            HotView.bitmap3 = abitmap;
        }

        final Home this$0;

        ImageAsyn()
        {
            this$0 = Home.this;
            super();
        }
    }

    class ChangeProvince extends AsyncTask
    {

        protected transient Integer doInBackground(Object aobj[])
        {
            Integer integer = null;
            Integer integer1;
            DBHelper dbhelper = new DBHelper();
            XStream xstream = DBHelper.getxStream();
            Object aobj1[] = new Object[2];
            aobj1[0] = User.token;
            aobj1[1] = Long.valueOf(tempProvince);
            integer1 = (Integer)xstream.fromXML(dbhelper.getResultString(dbhelper.getHttpResponse("changeProvince", xstream.toXML(((Object) (aobj1))))));
            integer = integer1;
_L2:
            return integer;
            Exception exception;
            exception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground(aobj);
        }

        protected void onPostExecute(Integer integer)
        {
            if(integer.intValue() != 1) goto _L2; else goto _L1
_L1:
            int i;
            province = tempprovince;
            showToast("\u66F4\u6539\u7701\u4EFD\u6210\u529F");
            Cart.cartTotal = 0L;
            setCartImage(Long.valueOf(Cart.cartTotal));
            i = 0;
_L10:
            if(i >= provinceArray.length) goto _L4; else goto _L3
_L3:
            if(provinceArray[i].equals(province))
            {
                User.province = Long.decode((new StringBuilder()).append(i + 1).append("").toString()).longValue();
                save(province);
            }
              goto _L5
_L4:
            popupWindow.dismiss();
            homeTopClick();
            tempProvince = User.province;
            if(!DBHelper.testNet()) goto _L7; else goto _L6
_L6:
            TypeAsyncTask typeasynctask = new TypeAsyncTask(handler, 0x7f090031);
            if(typeasynctask.getStatus() != android.os.AsyncTask.Status.RUNNING) goto _L9; else goto _L8
_L8:
            typeasynctask.cancel(true);
            typeasynctask.execute(null);
_L7:
            cancelProgress();
            return;
_L9:
            try
            {
                typeasynctask.execute(null);
            }
            catch(Exception exception)
            {
                util.showNetNull();
            }
            continue; /* Loop/switch isn't completed */
_L2:
            showToast("\u66F4\u6539\u7701\u4EFD\u5931\u8D25");
            if(true) goto _L7; else goto _L5
_L5:
            i++;
              goto _L10
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Integer)obj);
        }

        final Home this$0;

        ChangeProvince()
        {
            this$0 = Home.this;
            super();
        }
    }


    public Home()
    {
        util = new Util(this);
        String as[] = new String[32];
        as[0] = "\u4E0A\u6D77";
        as[1] = "\u5317\u4EAC";
        as[2] = "\u5929\u6D25";
        as[3] = "\u6CB3\u5317";
        as[4] = "\u6C5F\u82CF";
        as[5] = "\u6D59\u6C5F";
        as[6] = "\u91CD\u5E86";
        as[7] = "\u5185\u8499\u53E4";
        as[8] = "\u8FBD\u5B81";
        as[9] = "\u5409\u6797";
        as[10] = "\u9ED1\u9F99\u6C5F";
        as[11] = "\u56DB\u5DDD";
        as[12] = "\u5B89\u5FBD";
        as[13] = "\u798F\u5EFA";
        as[14] = "\u6C5F\u897F";
        as[15] = "\u5C71\u4E1C";
        as[16] = "\u6CB3\u5357";
        as[17] = "\u6E56\u5317";
        as[18] = "\u6E56\u5357";
        as[19] = "\u5E7F\u4E1C";
        as[20] = "\u5E7F\u897F";
        as[21] = "\u6D77\u5357";
        as[22] = "\u8D35\u5DDE";
        as[23] = "\u4E91\u5357";
        as[24] = "\u897F\u85CF";
        as[25] = "\u9655\u897F";
        as[26] = "\u7518\u8083";
        as[27] = "\u9752\u6D77";
        as[28] = "\u65B0\u7586";
        as[29] = "\u5B81\u590F";
        as[30] = "\u53F0\u6E7E";
        as[31] = "\u5C71\u897F";
        provinceArray = as;
        textWatcher = new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
                String s = searchEditText.getText().toString().trim();
                if(DBHelper.testNet())
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
                        searchListView.setVisibility(8);
                    }
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                int i = 0;
                message.what;
                JVM INSTR lookupswitch 4: default 48
            //                           6: 48
            //                           2131296304: 387
            //                           2131296305: 155
            //                           2131296340: 49;
                   goto _L1 _L1 _L2 _L3 _L4
_L1:
                return;
_L4:
                isFinish = false;
                if(message.obj != null)
                {
                    longTailList = (List)message.obj;
                    if(longTailList != null)
                    {
                        searchListView.setVisibility(0);
                        searchListView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
                        searchListView.setAdapter(new ArrayAdapter(Home.this, 0x7f030018, longTailList));
                    }
                }
                  goto _L1
_L3:
                if(message.obj == null) goto _L1; else goto _L5
_L5:
                Page page;
                page = (Page)message.obj;
                PromationView.list = page.getObjList();
                if(page.getTotalSize().intValue() >= 1) goto _L7; else goto _L6
_L6:
                PromationView.list = null;
                for(int i1 = 0; i1 < 10; i1++)
                    PromationView.arraString[i1] = "";

                  goto _L8
_L7:
                int l = PromationView.list.size();
_L20:
                if(i >= l) goto _L8; else goto _L9
_L9:
                PromationView.arraString[i] = ((ProductVO)page.getObjList().get(i)).getAdvertisement();
                if(PromationView.arraString[i].length() > 13)
                    PromationView.arraString[i] = (new StringBuilder()).append(PromationView.arraString[i].substring(0, 13)).append("...").toString();
                  goto _L10
_L8:
                ((PromationView)findViewById(0x7f0900c2)).invalidate();
_L19:
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
                  goto _L1
_L2:
                if(message.obj == null) goto _L1; else goto _L11
_L11:
                List list = (List)message.obj;
                if(list.size() <= 0) goto _L13; else goto _L12
_L12:
                ImageAsyn imageasyn;
                HotView.count = list.size();
                imageasyn = new ImageAsyn();
                if(imageasyn.getStatus() != android.os.AsyncTask.Status.RUNNING) goto _L15; else goto _L14
_L14:
                imageasyn.cancel(true);
                List alist1[] = new List[1];
                alist1[0] = list;
                imageasyn.execute(alist1);
_L16:
                ((HotView)findViewById(0x7f0900bb)).invalidate();
                  goto _L1
_L15:
                List alist[] = new List[1];
                alist[0] = list;
                imageasyn.execute(alist);
                  goto _L16
_L13:
                int j;
                int k;
                j = HotView.bitmap3.length;
                k = 0;
_L18:
                if(k >= j) goto _L16; else goto _L17
_L17:
                HotView.bitmap3[k] = null;
                k++;
                  goto _L18
                Exception exception1;
                exception1;
                  goto _L19
_L10:
                i++;
                  goto _L20
                Exception exception;
                exception;
                  goto _L16
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
;
    }

    private void exit()
    {
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0a00c0).setIcon(0x7f020068).setMessage(0x7f0a00c1).setNegativeButton(0x7f0a00c3, null).setPositiveButton(0x7f0a00c2, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                ((ActivityManager)getSystemService("activity")).restartPackage(getPackageName());
                if(CameraManager.get() != null)
                    CameraManager.get().closeDriver();
                stopService(new Intent("com.thestore.util.MyService"));
                stopService(new Intent("com.thestore.util.DownService"));
                finish();
                System.gc();
                System.exit(0);
                finish();
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
).create().show();
    }

    private void homeTopClick()
    {
        final Button myStoreButton = (Button)findViewById(0x7f0900b5);
        myStoreButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    if(User.token != null)
                    {
                        util.changeMain("com.thestore.main.Mystore");
                    } else
                    {
                        User.TYPE = 0x7f090000;
                        util.changeMain("com.thestore.main.userland");
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
        if(User.token != null)
        {
            myStoreButton.setBackgroundResource(0x7f0200d2);
            myStoreButton.setOnTouchListener(new android.view.View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionevent)
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
                    myStoreButton.setBackgroundResource(0x7f0200d4);
                    continue; /* Loop/switch isn't completed */
_L3:
                    myStoreButton.setBackgroundResource(0x7f0200d2);
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final Home this$0;
                final Button val$myStoreButton;

            
            {
                this$0 = Home.this;
                myStoreButton = button;
                super();
            }
            }
);
        } else
        {
            myStoreButton.setBackgroundResource(0x7f0200d3);
            myStoreButton.setOnTouchListener(new android.view.View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionevent)
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
                    myStoreButton.setBackgroundResource(0x7f0200d4);
                    continue; /* Loop/switch isn't completed */
_L3:
                    myStoreButton.setBackgroundResource(0x7f0200d3);
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final Home this$0;
                final Button val$myStoreButton;

            
            {
                this$0 = Home.this;
                myStoreButton = button;
                super();
            }
            }
);
        }
        provinceButton = (Button)findViewById(0x7f0900b4);
        if(province.length() < 1)
            province = "\u4E0A\u6D77";
        provinceButton.setBackgroundResource(0x7f02006f);
        provinceButton.setTextColor(0xffff0000);
        provinceButton.setText(province);
        provinceButton.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 1: default 28
            //                           0 30
            //                           1 80;
                   goto _L1 _L2 _L3
_L1:
                return false;
_L2:
                provinceButton.setBackgroundResource(0x7f02006f);
                provinceButton.setText(province);
                provinceButton.setTextColor(-1);
                view.setBackgroundResource(0x7f02009d);
                continue; /* Loop/switch isn't completed */
_L3:
                provinceButton.setTextColor(0xffff0000);
                view.setBackgroundResource(0x7f02006f);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
        provinceButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                View view1 = LayoutInflater.from(Home.this).inflate(0x7f03002f, null);
                popupWindow = new PopupWindow(view1, screen_w, screen_h);
                popupWindow.showAtLocation(view.getRootView(), 3, 0, 0);
                initProvince(view1, popupWindow);
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
    }

    private void initBarCode()
    {
        ((ImageView)findViewById(0x7f0900c0)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(util.getHeightPixels(Home.this) >= 480F && !util.isAuto())
                    util.changeMain("com.thestore.scan.CaptureActivity");
                else
                if(util.isG8())
                    util.changeMain("com.thestore.scan.CaptureActivity");
                else
                    util.displayFrameworkBugMessageAndExit();
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
    }

    private void initNote()
    {
        ((ImageView)findViewById(0x7f0900bf)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                util.changeMain("com.thestore.main.Note");
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
    }

    private void initProvince(View view, final PopupWindow popupWindow)
    {
        final String array[] = getResources().getStringArray(0x7f060002);
        for(int i = 0; i < array.length; i++)
            ((TextView)view.findViewById(0x7f090182 + i)).setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    tempprovince = array[view1.getId() - 0x7f090182];
                    if(DBHelper.testNet())
                    {
                        if(User.token != null && !tempprovince.contains(provinceButton.getText()))
                        {
                            province = tempprovince;
                            for(int k = 0; k < provinceArray.length; k++)
                                if(provinceArray[k].equals(tempprovince))
                                {
                                    User.province = Long.decode((new StringBuilder()).append(k + 1).append("").toString()).longValue();
                                    save(province);
                                }

                            tempProvince = User.province;
                            showProgress();
                            ChangeProvince changeprovince = new ChangeProvince();
                            int j;
                            if(changeprovince.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                changeprovince.cancel(true);
                                changeprovince.execute(null);
                            } else
                            {
                                changeprovince.execute(null);
                            }
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                    if(User.token == null)
                    {
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
                        } else
                        {
                            util.showNetNull();
                        }
                        province = tempprovince;
                        for(j = 0; j < provinceArray.length; j++)
                            if(provinceArray[j].equals(province))
                            {
                                User.province = Long.decode((new StringBuilder()).append(j + 1).append("").toString()).longValue();
                                save(province);
                            }

                        save(province);
                        tempProvince = User.province;
                        homeTopClick();
                    }
                    popupWindow.dismiss();
                }

                final Home this$0;
                final String val$array[];
                final PopupWindow val$popupWindow;

            
            {
                this$0 = Home.this;
                array = as;
                popupWindow = popupwindow;
                super();
            }
            }
);

        ((TextView)view.findViewById(0x7f090181)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                popupWindow.dismiss();
            }

            final Home this$0;
            final PopupWindow val$popupWindow;

            
            {
                this$0 = Home.this;
                popupWindow = popupwindow;
                super();
            }
        }
);
    }

    private void load()
    {
        province = (new Store(this)).getString("province", "");
    }

    private void save(String s)
    {
        Store store = new Store(this);
        store.storeString("province", s);
        store.storeInt("provinceID", (int)User.province);
    }

    private void setHomeSearch()
    {
        searchButton = (Button)findViewById(0x7f0900b9);
        searchEditText = (EditText)findViewById(0x7f0900b8);
        searchListView = (ListView)findViewById(0x7f0900c5);
        searchEditText.addTextChangedListener(textWatcher);
        searchButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                String s = searchEditText.getText().toString().trim();
                if(s.equals(""))
                    showToast(0x7f0a010a);
                else
                if(s.length() > 50)
                    showToast(0x7f0a010b);
                else
                if(DBHelper.testNet())
                {
                    searchListView.setVisibility(8);
                    Intent intent = new Intent();
                    intent.setClass(Home.this, com/thestore/main/SearchResult);
                    intent.putExtra("keyword", s);
                    startActivity(intent);
                    searchEditText.setText("");
                } else
                {
                    util.showNetNull();
                }
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
        searchListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                searchEditText.setText((CharSequence)longTailList.get(i));
                Editable editable = searchEditText.getText();
                Selection.setSelection(editable, editable.length());
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
    }

    private void setHomeTop()
    {
        initBarCode();
        initNote();
    }

    private void startTimer()
    {
        task = new TimerTask() {

            public void run()
            {
                Message message = new Message();
                handler.sendMessage(message);
            }

            final Home this$0;
            final Handler val$handler;

            
            {
                this$0 = Home.this;
                handler = handler1;
                super();
            }
        }
;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        DBHelper.context = this;
        load();
        setContentView(0x7f030011);
        bottomOnClick(this);
        setHomeTop();
        setHomeSearch();
        timer = new Timer();
        startTimer();
        timer.schedule(task, 0L, 100L);
        ((RelativeLayout)findViewById(0x7f0900b3)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                util.hindInputMethod();
            }

            final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
        }
);
        util.getPageSize();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0004, menu);
        return true;
    }

    protected void onDestroy()
    {
        save(province);
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i != 4) goto _L2; else goto _L1
_L1:
        if(popupWindow == null || !popupWindow.isShowing()) goto _L4; else goto _L3
_L3:
        boolean flag;
        popupWindow.dismiss();
        popupWindow = null;
        flag = true;
_L6:
        return flag;
_L4:
        if(popupWindow == null || popupWindow != null && !popupWindow.isShowing())
            exit();
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131296777 2131296781: default 40
    //                   2131296777 49
    //                   2131296778 87
    //                   2131296779 138
    //                   2131296780 125
    //                   2131296781 42;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return true;
_L6:
        exit();
        continue; /* Loop/switch isn't completed */
_L2:
        if(User.token != null)
        {
            util.changeMain("com.thestore.main.Mystore");
        } else
        {
            User.TYPE = 0x7f090000;
            util.changeMain("com.thestore.main.userland");
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(User.token != null)
        {
            util.changeMain("com.thestore.main.Message");
        } else
        {
            User.TYPE = 0x7f090002;
            util.changeMain("com.thestore.main.userland");
        }
        continue; /* Loop/switch isn't completed */
_L5:
        util.changeMain("com.thestore.main.About");
        continue; /* Loop/switch isn't completed */
_L4:
        Intent intent = new Intent();
        intent.setClass(this, com/thestore/main/HomeSet);
        startActivity(intent);
        if(true) goto _L1; else goto _L7
_L7:
    }

    protected void onPause()
    {
        save(province);
        super.onPause();
    }

    protected void onRestart()
    {
        super.onRestart();
        DBHelper.context = this;
        bottomOnClick(this);
        homeTopClick();
        searchEditText.setText("");
    }

    protected void onStart()
    {
        new DisplayMetrics();
        DisplayMetrics displaymetrics = getApplicationContext().getResources().getDisplayMetrics();
        screen_w = displaymetrics.widthPixels;
        screen_h = displaymetrics.heightPixels;
        homeTopClick();
        super.onStart();
    }

    public void run()
    {
        do
        {
            if(util.getEditString(searchEditText).length() < 1)
                searchListView.setVisibility(8);
            try
            {
                Thread.sleep(100L);
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
        } while(true);
    }

    Handler handler;
    private boolean isFinish;
    private List longTailList;
    private PopupWindow popupWindow;
    private String province;
    String provinceArray[];
    private Button provinceButton;
    private int screen_h;
    private int screen_w;
    private Button searchButton;
    private EditText searchEditText;
    private ListView searchListView;
    private TimerTask task;
    private long tempProvince;
    private String tempprovince;
    TextWatcher textWatcher;
    private Timer timer;
    private Util util;






/*
    static String access$1102(Home home, String s)
    {
        home.tempprovince = s;
        return s;
    }

*/




/*
    static long access$1302(Home home, long l)
    {
        home.tempProvince = l;
        return l;
    }

*/





/*
    static List access$302(Home home, List list)
    {
        home.longTailList = list;
        return list;
    }

*/



/*
    static boolean access$402(Home home, boolean flag)
    {
        home.isFinish = flag;
        return flag;
    }

*/




/*
    static String access$602(Home home, String s)
    {
        home.province = s;
        return s;
    }

*/



/*
    static PopupWindow access$702(Home home, PopupWindow popupwindow)
    {
        home.popupWindow = popupwindow;
        return popupwindow;
    }

*/



    // Unreferenced inner class com/thestore/main/Home$2

/* anonymous class */
    class _cls2 extends Handler
    {

        public void handleMessage(Message message)
        {
            if(util.getEditString(searchEditText).length() < 1)
                searchListView.setVisibility(8);
        }

        final Home this$0;

            
            {
                this$0 = Home.this;
                super();
            }
    }

}
