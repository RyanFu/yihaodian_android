// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.ImageLoader;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class ShowOrder extends ActivityMain
    implements android.widget.AdapterView.OnItemClickListener
{
    public class ShowOrderAdapter extends BaseAdapter
    {

        @Override
		public int getCount()
        {
            return products.size();
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
            ProductVO productvo;
            TextView textview;
            StringBuilder stringbuilder;
            Object obj;
            ImageView imageview;
            ImageView imageview1;
            if(view == null)
                view1 = inflater.inflate(0x7f030036, null);
            else
                view1 = view;
            productvo = (ProductVO)products.get(i);
            ((TextView)view1.findViewById(0x7f0901c3)).setText(productvo.getCnName());
            textview = (TextView)view1.findViewById(0x7f0901c4);
            stringbuilder = (new StringBuilder()).append("\uFFE5");
            if(productvo.getPrice() != null)
                obj = productvo.getPrice();
            else
                obj = "0.00";
            textview.setText(stringbuilder.append(obj).toString());
            imageview = (ImageView)view1.findViewById(0x7f0901c2);
            imageLoader.DisplayImage(productvo.getMidleDefaultProductUrl(), context, imageview);
            imageview1 = (ImageView)view1.findViewById(0x7f0901c5);
            if(i == selectItem)
                imageview1.setImageResource(0x7f0200ba);
            else
                imageview1.setImageResource(0x7f0200d0);
            return view1;
        }

        private Activity context;
        private ImageLoader imageLoader;
        private LayoutInflater inflater;
        final ShowOrder this$0;

        public ShowOrderAdapter(Activity activity)
        {
            this$0 = ShowOrder.this;
            super();
            context = activity;
            imageLoader = new ImageLoader(activity.getApplicationContext());
            inflater = LayoutInflater.from(activity);
        }
    }


    public ShowOrder()
    {
        selectItem = 0;
        products = new ArrayList();
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 5: default 56
            //                           1: 57
            //                           2: 251
            //                           4: 289
            //                           5: 176
            //                           2131296343: 306;
                   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
                return;
_L2:
                if(((Boolean)message.obj).booleanValue())
                {
                    getSharedPreferences("saidan", 0).edit().putString("userAccount", user).commit();
                    ShowOrderAsyncTask showorderasynctask = new ShowOrderAsyncTask(handler, 5, 1, 1);
                    if(showorderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        showorderasynctask.cancel(true);
                        showorderasynctask.execute(null);
                    } else
                    {
                        showorderasynctask.execute(null);
                    }
                } else
                {
                    cancelProgress();
                    showToast("\u90AE\u7BB1\u6216\u5BC6\u7801\u9519\u8BEF");
                }
                continue; /* Loop/switch isn't completed */
_L5:
                cancelProgress();
                orderid = (String)message.obj;
                (new android.app.AlertDialog.Builder(ShowOrder.this)).setTitle("\u5206\u4EAB\u5230\u65B0\u6D6A\u5FAE\u535A").setPositiveButton("\u5173\u95ED", null).setNegativeButton("\u5206\u4EAB", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        showProgress();
                        ProductVO productvo = (ProductVO)products.get(selectItem);
                        ShowOrderAsyncTask showorderasynctask1 = new ShowOrderAsyncTask(handler, 2, user, pass, weiboInfo.getText().toString(), User.userAccount, orderid, (new StringBuilder()).append(productvo.getProductId()).append("").toString(), (new StringBuilder()).append(User.province).append("").toString());
                        ShowProcessTask showprocesstask;
                        if(showorderasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            showorderasynctask1.cancel(true);
                            showorderasynctask1.execute(null);
                        } else
                        {
                            showorderasynctask1.execute(null);
                        }
                        showprocesstask = new ShowProcessTask(handler, 0x7f090057);
                        if(showprocesstask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            showprocesstask.cancel(true);
                            showprocesstask.execute(null);
                        } else
                        {
                            showprocesstask.execute(null);
                        }
                    }

                    final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                }
).setView(linearLayout2).create().show();
                continue; /* Loop/switch isn't completed */
_L3:
                cancelProgress();
                showToast("\u6652\u5355\u5B8C\u6210");
                finish();
                util.changeMain("com.thestore.main.Home");
                continue; /* Loop/switch isn't completed */
_L4:
                cancelProgress();
                finish();
                continue; /* Loop/switch isn't completed */
_L6:
                showProgress();
                if(true) goto _L1; else goto _L7
_L7:
            }

            final ShowOrder this$0;

            
            {
                this$0 = ShowOrder.this;
                super();
            }
        }
;
    }

    private void SinaLogin()
    {
        if(selectItem >= 0)
        {
            LayoutInflater layoutinflater = LayoutInflater.from(this);
            linearLayout1 = (LinearLayout)layoutinflater.inflate(0x7f030039, null);
            userName = (EditText)linearLayout1.findViewById(0x7f0901c8);
            password = (EditText)linearLayout1.findViewById(0x7f0901c9);
            String s = getSharedPreferences("saidan", 0).getString("userAccount", "");
            userName.setText(s);
            linearLayout2 = (LinearLayout)layoutinflater.inflate(0x7f03003a, null);
            weiboInfo = (EditText)linearLayout2.findViewById(0x7f0901ca);
            ProductVO productvo = (ProductVO)products.get(selectItem);
            weiboInfo.setText((new StringBuilder()).append("\u6211\u5728 @\u638C\u4E0A1\u53F7\u5E97 \u53D1\u73B0\u4E86").append(productvo.getCnName()).append("\uFF0C\uFFE5").append(productvo.getPrice()).append("\u5143\uFF0C\u5FEB\u6765\u62A2\u8D2D\u5427\uFF01http://m.yihaodian.com/product/").append(productvo.getProductId()).append("_").append(cityid).append("_").append(DBHelper.getTrader().getUnionKey()).append(" \uFF08\u4E00\u7AD9\u5F0F\u62C7\u6307\u8D2D\u7269\uFF09").toString());
            dialogCreate();
        }
    }

    public void dialogCreate()
    {
        (new android.app.AlertDialog.Builder(this)).setTitle("\u767B\u5F55\u5230\u65B0\u6D6A\u5FAE\u535A\u4EE5\u5206\u4EAB").setPositiveButton("\u5173\u95ED", new android.content.DialogInterface.OnClickListener() {

            @Override
			public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final ShowOrder this$0;

            
            {
                this$0 = ShowOrder.this;
                super();
            }
        }
).setNegativeButton("\u767B\u5F55", new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                user = userName.getText().toString();
                pass = password.getText().toString();
                if(user != null && user.length() != 0) goto _L2; else goto _L1
_L1:
                (new android.app.AlertDialog.Builder(ShowOrder.this)).setTitle("\u9519\u8BEF").setMessage("\u8BF7\u8F93\u5165\u65B0\u6D6A\u90AE\u7BB1").setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface1, int j)
                    {
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
).create().show();
_L4:
                return;
_L2:
                if(pass == null || pass.length() == 0)
                    (new android.app.AlertDialog.Builder(ShowOrder.this)).setTitle("\u9519\u8BEF").setMessage("\u8BF7\u8F93\u5165\u90AE\u7BB1\u5BC6\u7801").setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface1, int j)
                        {
                        }

                        final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                    }
).create().show();
                else
                if(User.token != null && DBHelper.testNet())
                {
                    ShowOrderAsyncTask showorderasynctask = new ShowOrderAsyncTask(handler, 1, user, pass);
                    ShowProcessTask showprocesstask;
                    if(showorderasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        showorderasynctask.cancel(true);
                        showorderasynctask.execute(null);
                    } else
                    {
                        showorderasynctask.execute(null);
                    }
                    showprocesstask = new ShowProcessTask(handler, 0x7f090057);
                    if(showprocesstask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        showprocesstask.cancel(true);
                        showprocesstask.execute(null);
                    } else
                    {
                        showprocesstask.execute(null);
                    }
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ShowOrder this$0;

            
            {
                this$0 = ShowOrder.this;
                super();
            }
        }
).setView(linearLayout1).create().show();
    }

    @Override
	protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030035);
        util = new Util(this);
        bottomOnClick(this);
        util.setDefaultTitle(0x7f0a00ed);
        Intent intent = getIntent();
        int i = intent.getIntExtra("ORDER_COUNT", 0);
        orderid = intent.getStringExtra("SHOWORDER_ID");
        cityid = intent.getLongExtra("ORDER_AREA", 0L);
        products.clear();
        for(int j = 0; j < i; j++)
        {
            ProductVO productvo = new ProductVO();
            productvo.setProductId(Long.valueOf(intent.getLongExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_ID").toString(), 0L)));
            productvo.setCnName(intent.getStringExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_NAME").toString()));
            productvo.setMidleDefaultProductUrl(intent.getStringExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_IMAGE").toString()));
            productvo.setPrice(Double.valueOf(intent.getDoubleExtra((new StringBuilder()).append("ORDER_ITEM_").append(j).append("_PRICE").toString(), 0.0D)));
            products.add(productvo);
        }

        typeAdapter = new ShowOrderAdapter(this);
        listView = (ListView)findViewById(0x7f0901be);
        listView.setAdapter(typeAdapter);
        listView.setOnItemClickListener(this);
        showOrderButton = (Button)findViewById(0x7f0901bf);
        showOrderButton.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
			public void onClick(View view)
            {
                SinaLogin();
            }

            final ShowOrder this$0;

            
            {
                this$0 = ShowOrder.this;
                super();
            }
        }
);
    }

    @Override
	public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        selectItem = i;
        typeAdapter.notifyDataSetChanged();
    }

    long cityid;
    private Handler handler;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    ListView listView;
    String orderid;
    String pass;
    EditText password;
    List products;
    int selectItem;
    Button showOrderButton;
    ImageView titleImage;
    ShowOrderAdapter typeAdapter;
    String user;
    EditText userName;
    Util util;
    EditText weiboInfo;


}
