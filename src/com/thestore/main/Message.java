// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thestore.net.DBHelper;
import com.thestore.net.MessageAsyncTask;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.message.InnerMessageVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class Message extends ActivityMain
{

    public Message()
    {
        lists = new ArrayList();
        typeID = 1;
        pageSize = 10;
        currentPage = 1;
        handler = new Handler() {

            public void handleMessage(android.os.Message message)
            {
                message.what;
                JVM INSTR tableswitch 2131296281 2131296284: default 36
            //                           2131296281 37
            //                           2131296282 146
            //                           2131296283 487
            //                           2131296284 646;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                if(message.obj != null)
                {
                    unreadNum = ((Integer)message.obj).intValue();
                    MessageAsyncTask messageasynctask1 = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f09001a);
                    if(messageasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        messageasynctask1.cancel(true);
                        messageasynctask1.execute(null);
                    } else
                    {
                        messageasynctask1.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L3:
                messageLayout.removeAllViews();
                if(message.obj != null)
                {
                    messageBtnLayout.setVisibility(8);
                    page = (Page)message.obj;
                    innerMessageVO = page.getObjList();
                    currentPage = page.getCurrentPage().intValue();
                    innerMessageVO.size();
                    totalSize = page.getTotalSize().intValue();
                    if(typeID == 1)
                        messageCount = page.getTotalSize().intValue();
                    boxTextView.setText(Html.fromHtml((new StringBuilder()).append("\u6211\u7684\u6D88\u606F(<font color=#B21A1F>").append(unreadNum).append("/").append(messageCount).append("</font>)").toString()));
                    if(totalSize > 0)
                    {
                        if(!messageBtnLayout.isShown())
                        {
                            setMessageType();
                            addViewBtn();
                        } else
                        {
                            addMessageDetail((InnerMessageVO)innerMessageVO.get(currentLine));
                        }
                    } else
                    {
                        TextView textview = new TextView(Message.this);
                        textview.setText("\u60A8\u5171\u67090\u6761\u6D88\u606F");
                        textview.setGravity(17);
                        messageLayout.addView(textview);
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L4:
                if(message.obj != null)
                {
                    if(((Integer)message.obj).intValue() == 1)
                    {
                        lists.clear();
                        showToast(0x7f0a004e);
                        if(DBHelper.testNet())
                        {
                            MessageAsyncTask messageasynctask = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
                            if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                            {
                                messageasynctask.cancel(true);
                                messageasynctask.execute(null);
                            } else
                            {
                                messageasynctask.execute(null);
                            }
                        } else
                        {
                            util.showNetNull();
                        }
                    } else
                    {
                        showToast(0x7f0a004f);
                    }
                } else
                {
                    util.showNetNull();
                }
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L5:
                if(message.obj != null)
                {
                    messageLayout.removeAllViews();
                    InnerMessageVO innermessagevo = (InnerMessageVO)message.obj;
                    addMessageDetail(innermessagevo);
                    cancelProgress();
                } else
                {
                    util.showNetNull();
                }
                if(true) goto _L1; else goto _L6
_L6:
            }

            final Message this$0;

            
            {
                this$0 = Message.this;
                super();
            }
        }
;
    }

    private void addMessage(final int position, final InnerMessageVO innerMessageVO)
    {
        findViewById(0x7f0900da).setVisibility(0);
        messageBtnLayout.setVisibility(8);
        final View view = inflater.inflate(0x7f03001b, null);
        ImageView imageview = (ImageView)view.findViewById(0x7f0900ea);
        int i;
        ImageView imageview1;
        TextView textview;
        View view1;
        if(innerMessageVO.getIsNew().intValue() == 0)
            i = 0x7f020075;
        else
            i = 0;
        imageview.setBackgroundResource(i);
        imageview1 = (ImageView)view.findViewById(0x7f0900eb);
        if(innerMessageVO.getMessageType().intValue() == 0)
            imageview1.setBackgroundResource(0x7f020076);
        else
            imageview1.setBackgroundResource(0x7f020078);
        textview = (TextView)view.findViewById(0x7f0900ec);
        messageId = innerMessageVO.getId().longValue();
        this.position = position;
        textview.setText(Html.fromHtml(innerMessageVO.getContent()));
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        view.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view2)
            {
                messageLayout.removeAllViews();
                currentLine = position;
                if(DBHelper.testNet())
                {
                    showProgress();
                    MessageAsyncTask messageasynctask = new MessageAsyncTask(innerMessageVO.getId().longValue(), handler, 0x7f09001c);
                    if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        messageasynctask.cancel(true);
                        messageasynctask.execute(null);
                    } else
                    {
                        messageasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Message this$0;
            final InnerMessageVO val$innerMessageVO;
            final int val$position;

            
            {
                this$0 = Message.this;
                position = i;
                innerMessageVO = innermessagevo;
                super();
            }
        }
);
        view.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view2, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 38
            //                           1 50
            //                           2 36
            //                           3 50;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L2:
                view.setBackgroundResource(0x7f0200b9);
                continue; /* Loop/switch isn't completed */
_L3:
                view.setBackgroundColor(-1);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final Message this$0;
            final View val$view;

            
            {
                this$0 = Message.this;
                view = view1;
                super();
            }
        }
);
        ((TextView)view.findViewById(0x7f0900ed)).setText((new SimpleDateFormat("yyyy-MM-dd")).format(innerMessageVO.getCreTime()));
        messageLayout.addView(view);
        view1 = new View(this);
        view1.setBackgroundResource(0x7f020026);
        messageLayout.addView(view1);
    }

    private void addMessageDetail(final InnerMessageVO innerMessageVO)
    {
        messageLayout.removeAllViews();
        findViewById(0x7f0900da).setVisibility(8);
        messageBtnLayout.setVisibility(0);
        View view = inflater.inflate(0x7f03001a, null);
        ImageView imageview = (ImageView)view.findViewById(0x7f0900e5);
        TextView textview;
        if(innerMessageVO.getMessageType().intValue() == 0)
            imageview.setBackgroundResource(0x7f020076);
        else
            imageview.setBackgroundResource(0x7f020078);
        textview = (TextView)view.findViewById(0x7f0900e6);
        textview.setText(Html.fromHtml(innerMessageVO.getContent()));
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        ((Button)findViewById(0x7f0900e4)).setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view1)
            {
                if(DBHelper.testNet())
                {
                    showProgress();
                    MessageAsyncTask messageasynctask = new MessageAsyncTask(innerMessageVO.getId().longValue(), handler, 0x7f09001b);
                    if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        messageasynctask.cancel(true);
                        messageasynctask.execute(null);
                    } else
                    {
                        messageasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Message this$0;
            final InnerMessageVO val$innerMessageVO;

            
            {
                this$0 = Message.this;
                innerMessageVO = innermessagevo;
                super();
            }
        }
);
        ((Button)findViewById(0x7f0900e3)).setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view1)
            {
                util.sendSms(null, innerMessageVO);
            }

            final Message this$0;
            final InnerMessageVO val$innerMessageVO;

            
            {
                this$0 = Message.this;
                innerMessageVO = innermessagevo;
                super();
            }
        }
);
        ((ImageView)view.findViewById(0x7f0900e8)).setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view1)
            {
                if(currentLine != 0)
                {
                    if(DBHelper.testNet())
                    {
                        showProgress();
                        currentLine = currentLine - 1;
                        MessageAsyncTask messageasynctask = new MessageAsyncTask(((InnerMessageVO)lists.get(currentLine)).getId().longValue(), handler, 0x7f09001c);
                        if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            messageasynctask.cancel(true);
                            messageasynctask.execute(null);
                        } else
                        {
                            messageasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    showToast("\u8FD9\u662F\u7B2C\u4E00\u6761\u6D88\u606F");
                }
            }

            final Message this$0;

            
            {
                this$0 = Message.this;
                super();
            }
        }
);
        ((ImageView)view.findViewById(0x7f0900e9)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(currentLine != lists.size() - 1)
                {
                    if(DBHelper.testNet())
                    {
                        showProgress();
                        currentLine = 1 + currentLine;
                        MessageAsyncTask messageasynctask = new MessageAsyncTask(((InnerMessageVO)lists.get(currentLine)).getId().longValue(), handler, 0x7f09001c);
                        if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            messageasynctask.cancel(true);
                            messageasynctask.execute(null);
                        } else
                        {
                            messageasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                } else
                {
                    showToast("\u8FD9\u662F\u6700\u540E\u4E00\u6761\u6D88\u606F");
                }
            }

            final Message this$0;

            
            {
                this$0 = Message.this;
                super();
            }
        }
);
        messageLayout.addView(view);
    }

    private void addViewBtn()
    {
        final Button button = new Button(this);
        button.setBackgroundResource(0x7f02003d);
        String s;
        boolean flag;
        if(lists.size() == totalSize)
            s = "\u6D88\u606F\u52A0\u8F7D\u5B8C\u6BD5";
        else
            s = "\u52A0\u8F7D\u66F4\u591A";
        button.setText(s);
        if(lists.size() != totalSize)
            flag = true;
        else
            flag = false;
        button.setEnabled(flag);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    showProgress();
                    button.setText("\u52A0\u8F7D\u4E2D\uFF0C\u8BF7\u7A0D\u5019");
                    int i = 
// JavaClassFileOutputException: get_constant: invalid tag

            final Message this$0;
            final Button val$button;

            
            {
                this$0 = Message.this;
                button = button1;
                super();
            }
        }
);
        messageLayout.addView(button);
    }

    private void init()
    {
        inflater = LayoutInflater.from(this);
        messageLayout = (LinearLayout)findViewById(0x7f0900e1);
        messageBtnLayout = (LinearLayout)findViewById(0x7f0900e2);
        boxTextView = (TextView)findViewById(0x7f0900dd);
        final TextView allTextView = (TextView)findViewById(0x7f0900de);
        final TextView systemTextView = (TextView)findViewById(0x7f0900df);
        final TextView privateTextView = (TextView)findViewById(0x7f0900e0);
        allTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    lists.clear();
                    messageBtnLayout.setVisibility(8);
                    allTextView.setBackgroundResource(0x7f020094);
                    allTextView.setTextColor(Color.rgb(167, 32, 36));
                    systemTextView.setTextColor(Color.rgb(51, 51, 51));
                    privateTextView.setTextColor(Color.rgb(51, 51, 51));
                    systemTextView.setBackgroundResource(0);
                    privateTextView.setBackgroundResource(0);
                    typeID = 1;
                    showProgress();
                    MessageAsyncTask messageasynctask = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
                    if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        messageasynctask.cancel(true);
                        messageasynctask.execute(null);
                    } else
                    {
                        messageasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Message this$0;
            final TextView val$allTextView;
            final TextView val$privateTextView;
            final TextView val$systemTextView;

            
            {
                this$0 = Message.this;
                allTextView = textview;
                systemTextView = textview1;
                privateTextView = textview2;
                super();
            }
        }
);
        systemTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    lists.clear();
                    messageBtnLayout.setVisibility(8);
                    systemTextView.setTextColor(Color.rgb(167, 32, 36));
                    allTextView.setTextColor(Color.rgb(51, 51, 51));
                    privateTextView.setTextColor(Color.rgb(51, 51, 51));
                    systemTextView.setBackgroundResource(0x7f020094);
                    allTextView.setBackgroundResource(0);
                    privateTextView.setBackgroundResource(0);
                    typeID = -1;
                    showProgress();
                    MessageAsyncTask messageasynctask = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
                    if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        messageasynctask.cancel(true);
                        messageasynctask.execute(null);
                    } else
                    {
                        messageasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Message this$0;
            final TextView val$allTextView;
            final TextView val$privateTextView;
            final TextView val$systemTextView;

            
            {
                this$0 = Message.this;
                systemTextView = textview;
                allTextView = textview1;
                privateTextView = textview2;
                super();
            }
        }
);
        privateTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(DBHelper.testNet())
                {
                    lists.clear();
                    messageBtnLayout.setVisibility(8);
                    privateTextView.setTextColor(Color.rgb(167, 32, 36));
                    systemTextView.setTextColor(Color.rgb(51, 51, 51));
                    allTextView.setTextColor(Color.rgb(51, 51, 51));
                    privateTextView.setBackgroundResource(0x7f020094);
                    systemTextView.setBackgroundResource(0);
                    allTextView.setBackgroundResource(0);
                    typeID = 0;
                    showProgress();
                    MessageAsyncTask messageasynctask = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
                    if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        messageasynctask.cancel(true);
                        messageasynctask.execute(null);
                    } else
                    {
                        messageasynctask.execute(null);
                    }
                } else
                {
                    util.showNetNull();
                }
            }

            final Message this$0;
            final TextView val$allTextView;
            final TextView val$privateTextView;
            final TextView val$systemTextView;

            
            {
                this$0 = Message.this;
                privateTextView = textview;
                systemTextView = textview1;
                allTextView = textview2;
                super();
            }
        }
);
    }

    private void setMessageType()
    {
        int i = page.getObjList().size();
        for(int j = 0; j < i; j++)
            lists.add(j, innerMessageVO.get(i - 1 - j));

        messageLayout.removeAllViews();
        for(int k = lists.size() - 1; k >= 0; k--)
            addMessage(k, (InnerMessageVO)lists.get(k));

    }

    private void setSmsMenu(boolean flag)
    {
        menu.findItem(0x7f09020e).setVisible(flag);
        menu.findItem(0x7f09020f).setVisible(flag);
    }

    public void onCreate(Bundle bundle)
    {
        onCreate(bundle);
        setContentView(0x7f030019);
        util = new Util(this);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a0047);
        if(DBHelper.testNet())
        {
            showProgress();
            MessageAsyncTask messageasynctask = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
            if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                messageasynctask.cancel(true);
                messageasynctask.execute(null);
            } else
            {
                messageasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        init();
    }

    public boolean onCreateOptionsMenu(Menu menu1)
    {
        menu = menu1;
        (new MenuInflater(this)).inflate(0x7f0c0005, menu1);
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && messageBtnLayout.isShown())
        {
            messageBtnLayout.setVisibility(8);
            lists.clear();
            if(DBHelper.testNet())
            {
                showProgress();
                MessageAsyncTask messageasynctask = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
                if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    messageasynctask.cancel(true);
                    messageasynctask.execute(null);
                } else
                {
                    messageasynctask.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
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
    //                   2131296782: 160
    //                   2131296783: 187;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        if(messageBtnLayout.isShown())
        {
            messageBtnLayout.setVisibility(8);
            lists.clear();
            if(DBHelper.testNet())
            {
                showProgress();
                MessageAsyncTask messageasynctask1 = new MessageAsyncTask(typeID, 1, pageSize, handler, 0x7f090019);
                if(messageasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    messageasynctask1.cancel(true);
                    messageasynctask1.execute(null);
                } else
                {
                    messageasynctask1.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        } else
        {
            finish();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        util.sendSms(null, (InnerMessageVO)innerMessageVO.get(currentLine));
        continue; /* Loop/switch isn't completed */
_L4:
        messageLayout.removeAllViews();
        currentLine = position;
        if(DBHelper.testNet())
        {
            showProgress();
            MessageAsyncTask messageasynctask = new MessageAsyncTask(messageId, handler, 0x7f09001c);
            if(messageasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                messageasynctask.cancel(true);
                messageasynctask.execute(null);
            } else
            {
                messageasynctask.execute(null);
            }
        } else
        {
            util.showNetNull();
        }
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean onPrepareOptionsMenu(Menu menu1)
    {
        setSmsMenu(messageBtnLayout.isShown());
        return onPrepareOptionsMenu(menu1);
    }

    protected void onRestart()
    {
        onRestart();
        bottomOnClick(this);
    }

    private TextView boxTextView;
    private int currentLine;
    private int currentPage;
    private Handler handler;
    private LayoutInflater inflater;
    private List innerMessageVO;
    private List lists;
    private Menu menu;
    private LinearLayout messageBtnLayout;
    private int messageCount;
    private long messageId;
    private LinearLayout messageLayout;
    private Page page;
    private int pageSize;
    private int position;
    private int totalSize;
    private int typeID;
    private int unreadNum;
    private Util util;





/*
    static Page access$1002(Message message, Page page1)
    {
        message.page = page1;
        return page1;
    }

*/



/*
    static List access$1102(Message message, List list)
    {
        message.innerMessageVO = list;
        return list;
    }

*/



/*
    static int access$1202(Message message, int i)
    {
        message.totalSize = i;
        return i;
    }

*/



/*
    static int access$1302(Message message, int i)
    {
        message.messageCount = i;
        return i;
    }

*/







/*
    static int access$202(Message message, int i)
    {
        message.typeID = i;
        return i;
    }

*/







/*
    static int access$702(Message message, int i)
    {
        message.currentLine = i;
        return i;
    }

*/



/*
    static int access$802(Message message, int i)
    {
        message.currentPage = i;
        return i;
    }

*/


/*
    static int access$808(Message message)
    {
        int i = message.currentPage;
        message.currentPage = i + 1;
        return i;
    }

*/



/*
    static int access$902(Message message, int i)
    {
        message.unreadNum = i;
        return i;
    }

*/
}
