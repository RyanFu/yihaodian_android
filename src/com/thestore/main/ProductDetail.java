// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.main;

import android.app.*;
import android.content.*;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.*;
import android.text.*;
import android.view.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import com.thestore.net.*;
import com.thestore.util.Const;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.product.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.thestore.main:
//            ActivityMain

public class ProductDetail extends ActivityMain
{
    private class ProductImageAdapter extends BaseAdapter
    {

        public int getCount()
        {
            int i;
            if(showImgs.size() == 0)
                i = 1 + showImgs.size();
            else
                i = showImgs.size();
            return i;
        }

        public Object getItem(int i)
        {
            return Integer.valueOf(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            inflater = LayoutInflater.from(context);
            View view1 = inflater.inflate(0x7f03002b, null);
            ImageView imageview = (ImageView)view1.findViewById(0x7f09015c);
            imageview.setBackgroundResource(0x7f020069);
            if(showImgs.size() < 1)
            {
                leftArrowImageView.setVisibility(4);
                rightArrowImageView.setVisibility(4);
            } else
            if(showImgs.size() == 1)
            {
                leftArrowImageView.setVisibility(4);
                rightArrowImageView.setVisibility(4);
                imageview.setBackgroundDrawable(null);
                imageview.setImageBitmap(ProductDetail.this.loadingImage.loadImageFromUrl((String)showImgs.get(i)));
                imageview.setLayoutParams(new android.widget.Gallery.LayoutParams(160, 160));
            } else
            {
                leftArrowImageView.setVisibility(0);
                rightArrowImageView.setVisibility(0);
                String s = (String)showImgs.get(i);
                if(s != null)
                {
                    imageview.setTag(s);
                    Bitmap bitmap = ProductDetail.this.loadingImage.loadImage(s, new ImageCallback() {

                        public void imageLoaded(Bitmap bitmap1, String s1)
                        {
                            ImageView imageview1 = (ImageView)productImgGallery.findViewWithTag(s1);
                            if(imageview1 != null)
                                imageview1.setImageBitmap(bitmap1);
                        }

                        final ProductImageAdapter this$1;

                
                {
                    this$1 = ProductImageAdapter.this;
                    super();
                }
                    }
);
                    if(bitmap != null)
                        imageview.setImageBitmap(bitmap);
                } else
                {
                    imageview.setBackgroundResource(0x7f020069);
                }
                imageview.setBackgroundResource(0x7f020036);
                imageview.setLayoutParams(new android.widget.Gallery.LayoutParams(160, 160));
            }
            return view1;
        }

        private Context context;
        private LayoutInflater inflater;
        final ProductDetail this$0;

        public ProductImageAdapter(Context context1)
        {
            this$0 = ProductDetail.this;
            super();
            context = context1;
        }
    }


    public ProductDetail()
    {
        loadingImage = new loadingImage();
        introTextView = null;
        descTextView = null;
        cmtTextView = null;
        scanLayout = null;
        scanResultTextView = null;
        productNameTextView = null;
        productImgGallery = null;
        leftArrowImageView = null;
        rightArrowImageView = null;
        priceTextView = null;
        canBuyTextView = null;
        quantityEditText = null;
        buyLayout = null;
        favoriteButton = null;
        shareButton = null;
        addButton = null;
        subButton = null;
        descWebView = null;
        totalExperienceTextView = null;
        experienceStrTextView = null;
        goodRatingTextView = null;
        badRatingTextView = null;
        middleRatingTextView = null;
        goodBar = null;
        middleBar = null;
        badBar = null;
        commentLayout = null;
        productDetailLayout = null;
        introLayout = null;
        descLayout = null;
        cmtLayout = null;
        type = 0;
        productQuantity = Long.valueOf(0L);
        firstDesc = true;
        firstComm = true;
        goodRate = 0.0D;
        middleRate = 0.0D;
        badRate = 0.0D;
        productVO = null;
        productDescVO = null;
        productCommentVO = null;
        productRating = null;
        productImgUrls = null;
        productDefaultImg = null;
        showImgs = null;
        inflater = null;
        notificationManager = null;
        myHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 5: default 56
            //                           2131296292: 271
            //                           2131296293: 570
            //                           2131296294: 709
            //                           2131296297: 233
            //                           2131296308: 121;
                   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
                productVO = (ProductVO)message.obj;
                int i;
                if(productVO != null)
                {
                    cancelProgress();
                    productRating = productVO.getRating();
                    initViews();
                    initIntroViews();
                } else
                {
                    util.showNetNull();
                }
_L15:
                return;
_L6:
                if(message.obj == null) goto _L8; else goto _L7
_L7:
                ((Integer)message.obj).intValue();
                JVM INSTR tableswitch -1 1: default 164
            //                           -1 208
            //                           0 196
            //                           1 184;
                   goto _L9 _L10 _L11 _L12
_L9:
                util.showNetNull();
_L13:
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L12:
                showToast(0x7f0a0090);
                continue; /* Loop/switch isn't completed */
_L11:
                showToast(0x7f0a0091);
                continue; /* Loop/switch isn't completed */
_L10:
                showToast(0x7f0a0092);
                continue; /* Loop/switch isn't completed */
_L8:
                util.showNetNull();
                if(true) goto _L13; else goto _L5
_L5:
                if(message.obj != null)
                    judgeBuySucess(message);
                else
                    util.showNetNull();
                cancelProgress();
                continue; /* Loop/switch isn't completed */
_L2:
                productVO = (ProductVO)message.obj;
                cancelProgress();
                if(productVO != null)
                {
                    cancelProgress();
                    productDefaultImg = productVO.getMidleDefaultProductUrl();
                    productImgUrls = productVO.getMidleProductUrl();
                    showImgs = new ArrayList();
                    if(productDefaultImg != null)
                        showImgs.add(productDefaultImg);
                    if(productImgUrls.length > 0)
                        for(i = 0; i < productImgUrls.length; i++)
                            showImgs.add(productImgUrls[i]);

                    initViews();
                    initIntroViews();
                } else
                {
                    cancelProgress();
                    if(DBHelper.getTimeOut())
                    {
                        (new android.app.AlertDialog.Builder(ProductDetail.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                            public boolean onKey(DialogInterface dialoginterface, int j, KeyEvent keyevent)
                            {
                                if(j == 4)
                                    finish();
                                return false;
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int j)
                            {
                                finish();
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int j)
                            {
                                if(DBHelper.testNet())
                                {
                                    showProgress();
                                    ProductAsyncTask productasynctask = new ProductAsyncTask(productId, myHandler, 0x7f090024);
                                    if(productasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                    {
                                        productasynctask.cancel(true);
                                        productasynctask.execute(null);
                                    } else
                                    {
                                        productasynctask.execute(null);
                                    }
                                } else
                                {
                                    util.showNetNull();
                                    finish();
                                }
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).create().show();
                    } else
                    {
                        initNotify(0x7f0200c0, 0x7f0a00dd);
                        notificationManager.cancelAll();
                        finish();
                    }
                }
                continue; /* Loop/switch isn't completed */
_L3:
                productDescVO = (ProductVO)message.obj;
                if(productDescVO != null)
                {
                    cancelProgress();
                    firstDesc = false;
                    initDescViews();
                } else
                {
                    cancelProgress();
                    if(DBHelper.getTimeOut())
                        (new android.app.AlertDialog.Builder(ProductDetail.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                            public boolean onKey(DialogInterface dialoginterface, int j, KeyEvent keyevent)
                            {
                                if(j == 4)
                                    finish();
                                return false;
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int j)
                            {
                                finish();
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int j)
                            {
                                if(DBHelper.testNet())
                                {
                                    showProgress();
                                    ProductAsyncTask productasynctask = new ProductAsyncTask(productId, myHandler, 0x7f090025);
                                    if(productasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                    {
                                        productasynctask.cancel(true);
                                        productasynctask.execute(null);
                                    } else
                                    {
                                        productasynctask.execute(null);
                                    }
                                } else
                                {
                                    finish();
                                    util.showNetNull();
                                }
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).create().show();
                    else
                        util.showNetNull();
                }
                continue; /* Loop/switch isn't completed */
_L4:
                productCommentVO = (ProductVO)message.obj;
                if(productCommentVO != null)
                {
                    productRating = productCommentVO.getRating();
                    if(productRating != null)
                    {
                        experienceList = productRating.getTop5Experience();
                        if(experienceList != null)
                        {
                            cancelProgress();
                            firstComm = false;
                            initCommentViews();
                        } else
                        {
                            cancelProgress();
                            showToast("\u6B64\u5546\u54C1\u65E0\u8BC4\u4EF7\u4FE1\u606F!");
                        }
                    } else
                    {
                        cancelProgress();
                        showToast("\u6B64\u5546\u54C1\u65E0\u8BC4\u4EF7\u4FE1\u606F!");
                    }
                } else
                {
                    cancelProgress();
                    if(DBHelper.getTimeOut())
                        (new android.app.AlertDialog.Builder(ProductDetail.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                            public boolean onKey(DialogInterface dialoginterface, int j, KeyEvent keyevent)
                            {
                                if(j == 4)
                                    finish();
                                return false;
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int j)
                            {
                                finish();
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNeutralButton(0x7f0a001b, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int j)
                            {
                                if(DBHelper.testNet())
                                {
                                    showProgress();
                                    ProductAsyncTask productasynctask = new ProductAsyncTask(productId, myHandler, 0x7f090026);
                                    if(productasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                                    {
                                        productasynctask.cancel(true);
                                        productasynctask.execute(null);
                                    } else
                                    {
                                        productasynctask.execute(null);
                                    }
                                } else
                                {
                                    finish();
                                    util.showNetNull();
                                }
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).create().show();
                    else
                        util.showNetNull();
                }
                if(true) goto _L15; else goto _L14
_L14:
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
;
        viewSelectListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view.getId();
                JVM INSTR tableswitch 2131296608 2131296610: default 32
            //                           2131296608 33
            //                           2131296609 170
            //                           2131296610 403;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                initIntroViews();
                introTextView.setBackgroundResource(0x7f020094);
                descTextView.setBackgroundColor(0);
                cmtTextView.setBackgroundColor(0);
                introTextView.setTextColor(Color.rgb(167, 32, 36));
                descTextView.setTextColor(Color.rgb(51, 51, 51));
                cmtTextView.setTextColor(Color.rgb(51, 51, 51));
                introLayout.setVisibility(0);
                descLayout.setVisibility(8);
                cmtLayout.setVisibility(8);
                continue; /* Loop/switch isn't completed */
_L3:
                if(firstDesc)
                    if(DBHelper.testNet())
                    {
                        showProgress();
                        ProductAsyncTask productasynctask1 = new ProductAsyncTask(productId, myHandler, 0x7f090025);
                        if(productasynctask1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            productasynctask1.cancel(true);
                            productasynctask1.execute(null);
                        } else
                        {
                            productasynctask1.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                initDescViews();
                introTextView.setBackgroundColor(0);
                descTextView.setBackgroundResource(0x7f020094);
                cmtTextView.setBackgroundColor(0);
                introTextView.setTextColor(Color.rgb(51, 51, 51));
                descTextView.setTextColor(Color.rgb(167, 32, 36));
                cmtTextView.setTextColor(Color.rgb(51, 51, 51));
                introLayout.setVisibility(8);
                descLayout.setVisibility(0);
                cmtLayout.setVisibility(8);
                continue; /* Loop/switch isn't completed */
_L4:
                if(firstComm)
                    if(DBHelper.testNet())
                    {
                        showProgress();
                        ProductAsyncTask productasynctask = new ProductAsyncTask(productId, myHandler, 0x7f090026);
                        if(productasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            productasynctask.cancel(true);
                            productasynctask.execute(null);
                        } else
                        {
                            productasynctask.execute(null);
                        }
                    } else
                    {
                        util.showNetNull();
                    }
                introTextView.setBackgroundColor(0);
                descTextView.setBackgroundColor(0);
                cmtTextView.setBackgroundResource(0x7f020094);
                introTextView.setTextColor(Color.rgb(51, 51, 51));
                descTextView.setTextColor(Color.rgb(51, 51, 51));
                cmtTextView.setTextColor(Color.rgb(167, 32, 36));
                introLayout.setVisibility(8);
                descLayout.setVisibility(8);
                cmtLayout.setVisibility(0);
                if(true) goto _L1; else goto _L5
_L5:
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
;
    }

    private void createCommentView(ProductExperienceVO productexperiencevo)
    {
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        TextView textview;
        TextView textview1;
        RatingBar ratingbar;
        TextView textview2;
        linearlayout = (LinearLayout)inflater.inflate(0x7f030008, null);
        linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f09008a);
        textview = (TextView)linearlayout.findViewById(0x7f09008b);
        textview1 = (TextView)linearlayout.findViewById(0x7f09008c);
        ratingbar = (RatingBar)linearlayout.findViewById(0x7f09008d);
        textview2 = (TextView)linearlayout.findViewById(0x7f09008e);
        if(experienceList.size() != 0) goto _L2; else goto _L1
_L1:
        textview2.setText("\u5F88\u62B1\u6B49\uFF01\u6682\u65E0\u8BC4\u4EF7\u4FE1\u606F\uFF01");
        textview2.setTextColor(0xff000000);
        textview2.setTextSize(16F);
        textview2.setGravity(17);
_L5:
        commentLayout.addView(linearlayout);
        return;
_L2:
        String s;
        linearlayout1.setVisibility(0);
        s = productexperiencevo.getUserName();
        if(s != null) goto _L4; else goto _L3
_L3:
        s = "\u533F\u540D";
_L6:
        textview.setText(s);
        int i;
        String s1;
        String s2;
        String s3;
        String s4;
        if(productexperiencevo.getCreatetime() != null)
            s1 = (new SimpleDateFormat("yyyy-MM-dd")).format(productexperiencevo.getCreatetime());
        else
            s1 = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        textview1.setText(s1);
        ratingbar.setRating(productexperiencevo.getRatingLog().intValue());
        ratingbar.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                return true;
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
        s2 = productexperiencevo.getContentGood();
        if(s2 == null)
            s2 = "\u6682\u65E0\u8BC4\u4EF7";
        s3 = productexperiencevo.getContentFail();
        if(s3 == null)
            s3 = "\u6682\u65E0\u8BC4\u4EF7";
        s4 = productexperiencevo.getContent();
        if(s4 == null)
            s4 = "\u6682\u65E0\u8BC4\u4EF7";
        textview2.setText(Html.fromHtml((new StringBuilder()).append("<font color=#333333>\u4F18\u70B9 \uFF1A </font><font color=#666666>").append(s2).append("</font><br />").append("<font color=#333333>\u7F3A\u70B9 \uFF1A </font><font color=#666666>").append(s3).append("</font><br />").append("<font color=#333333>\u603B\u7ED3 \uFF1A </font><font color=#666666>").append(s4).append("</font>").toString()));
        if(true) goto _L5; else goto _L4
_L4:
        i = s.indexOf("@");
        if(i > 0)
            s = s.substring(0, i);
          goto _L6
    }

    private void initCommentViews()
    {
        if(productRating.getGoodRating() != null)
            goodRate = productRating.getGoodRating().doubleValue();
        if(productRating.getMiddleRating() != null)
            middleRate = productRating.getMiddleRating().doubleValue();
        if(productRating.getBadRating() != null)
            badRate = productRating.getBadRating().doubleValue();
        totalExperienceTextView = (TextView)findViewById(0x7f090175);
        goodBar = (ProgressBar)findViewById(0x7f090177);
        goodRatingTextView = (TextView)findViewById(0x7f090178);
        middleBar = (ProgressBar)findViewById(0x7f090179);
        middleRatingTextView = (TextView)findViewById(0x7f09017a);
        badBar = (ProgressBar)findViewById(0x7f09017b);
        badRatingTextView = (TextView)findViewById(0x7f09017c);
        experienceStrTextView = (TextView)findViewById(0x7f090176);
        TextView textview;
        String s;
        TextView textview1;
        String s1;
        TextView textview2;
        String s2;
        if(goodRate == 0.0D && middleRate == 0.0D && badRate == 0.0D)
        {
            experienceStrTextView.setText(0x7f0a00e5);
            totalExperienceTextView.setText("0.0%");
        } else
        if(goodRate > middleRate)
        {
            experienceStrTextView.setText(0x7f0a00e2);
            if(100D * goodRate < 10D)
                totalExperienceTextView.setText((new StringBuilder()).append((new DecimalFormat("0.0")).format(100D * goodRate)).append("%").toString());
            else
                totalExperienceTextView.setText((new StringBuilder()).append((new DecimalFormat("00.0")).format(100D * goodRate)).append("%").toString());
        } else
        if(middleRate > badRate)
        {
            experienceStrTextView.setText(0x7f0a00e3);
            if(100D * goodRate < 10D)
                totalExperienceTextView.setText((new StringBuilder()).append((new DecimalFormat("0.0")).format(100D * middleRate)).append("%").toString());
            else
                totalExperienceTextView.setText((new StringBuilder()).append((new DecimalFormat("00.0")).format(100D * middleRate)).append("%").toString());
        } else
        {
            experienceStrTextView.setText(0x7f0a00e4);
            if(100D * badRate < 10D)
                totalExperienceTextView.setText((new StringBuilder()).append((new DecimalFormat("0.0")).format(100D * badRate)).append("%").toString());
            else
                totalExperienceTextView.setText((new StringBuilder()).append((new DecimalFormat("00.0")).format(100D * badRate)).append("%").toString());
        }
        goodBar.setProgress((int)(100D * goodRate));
        middleBar.setProgress((int)(100D * middleRate));
        badBar.setProgress((int)(100D * badRate));
        textview = goodRatingTextView;
        if(100D * goodRate < 10D)
            s = (new StringBuilder()).append((new DecimalFormat("0.0")).format(100D * goodRate)).append("%").toString();
        else
            s = (new StringBuilder()).append((new DecimalFormat("00.0")).format(100D * goodRate)).append("%").toString();
        textview.setText(s);
        textview1 = middleRatingTextView;
        if(100D * middleRate < 10D)
            s1 = (new StringBuilder()).append((new DecimalFormat("0.0")).format(100D * middleRate)).append("%").toString();
        else
            s1 = (new StringBuilder()).append((new DecimalFormat("00.0")).format(100D * middleRate)).append("%").toString();
        textview1.setText(s1);
        textview2 = badRatingTextView;
        if(100D * badRate < 10D)
            s2 = (new StringBuilder()).append((new DecimalFormat("0.0")).format(100D * badRate)).append("%").toString();
        else
            s2 = (new StringBuilder()).append((new DecimalFormat("00.0")).format(100D * badRate)).append("%").toString();
        textview2.setText(s2);
        commentLayout = (LinearLayout)findViewById(0x7f09017d);
        if(experienceList.size() > 0)
        {
            for(int i = 0; i < experienceList.size(); i++)
                createCommentView((ProductExperienceVO)experienceList.get(i));

        } else
        {
            createCommentView(null);
        }
    }

    private void initDescViews()
    {
        descWebView = (WebView)findViewById(0x7f090173);
        descWebView.getSettings().setJavaScriptEnabled(true);
        descWebView.loadDataWithBaseURL("", productDescVO.getDescription(), "text/html", "utf-8", "");
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    private void initIntroViews()
    {
        productNameTextView = (TextView)findViewById(0x7f090166);
        productImgGallery = (Gallery)findViewById(0x7f090168);
        leftArrowImageView = (ImageView)findViewById(0x7f090167);
        rightArrowImageView = (ImageView)findViewById(0x7f090169);
        priceTextView = (TextView)findViewById(0x7f09016a);
        canBuyTextView = (TextView)findViewById(0x7f09016b);
        quantityEditText = (EditText)findViewById(0x7f09016d);
        buyLayout = (LinearLayout)findViewById(0x7f09016f);
        favoriteButton = (Button)findViewById(0x7f090170);
        shareButton = (Button)findViewById(0x7f090171);
        addButton = (Button)findViewById(0x7f09016e);
        subButton = (Button)findViewById(0x7f09016c);
        if(type > 0)
        {
            scanLayout = (LinearLayout)findViewById(0x7f09015e);
            scanLayout.setVisibility(0);
            scanResultTextView = (TextView)findViewById(0x7f09015f);
            scanResultTextView.setText(productVO.getCnName());
        }
        productNameTextView.setText(productVO.getCnName());
        productImgGallery.setAdapter(new ProductImageAdapter(this));
        productImgGallery.setGravity(16);
        priceTextView.setText((new StringBuilder()).append("\uFFE5").append(String.valueOf(productVO.getPrice())).toString());
        String s = getResources().getString(0x7f0a00d0);
        String s1 = getResources().getString(0x7f0a00d1);
        TextView textview = canBuyTextView;
        if(!productVO.getCanBuy().booleanValue())
            s = s1;
        textview.setText(s);
        quantityEditText.setText("1");
        buyLayout.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                motionevent.getAction();
                JVM INSTR tableswitch 0 3: default 36
            //                           0 53
            //                           1 38
            //                           2 36
            //                           3 38;
                   goto _L1 _L2 _L3 _L1 _L3
_L1:
                return false;
_L3:
                buyLayout.setBackgroundResource(0x7f020037);
                continue; /* Loop/switch isn't completed */
_L2:
                buyLayout.setBackgroundResource(0x7f020016);
                if(true) goto _L1; else goto _L4
_L4:
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
        buyLayout.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(quantityEditText.getText().toString() != null && quantityEditText.getText().toString().length() > 0)
                    productQuantity = Long.valueOf(Long.parseLong(quantityEditText.getText().toString()));
                if(productQuantity.longValue() > 0L)
                {
                    if(User.token != null && DBHelper.testNet())
                    {
                        showProgress();
                        Cart.merchantId = productVO.getMerchantId().longValue();
                        CartAsyncTask cartasynctask = new CartAsyncTask(productId.longValue(), productQuantity, myHandler, 0x7f090029);
                        if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                        {
                            cartasynctask.cancel(true);
                            cartasynctask.execute(null);
                        } else
                        {
                            cartasynctask.execute(null);
                        }
                    } else
                    {
                        User.TYPE = 0x7f090005;
                        util.changeMain("com.thestore.main.userland");
                    }
                } else
                {
                    showToast("\u5546\u54C1\u8D2D\u4E70\u6570\u91CF\u81F3\u5C11\u4E3A1!");
                }
_L2:
                return;
                NumberFormatException numberformatexception;
                numberformatexception;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
        favoriteButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(User.token != null && DBHelper.testNet())
                {
                    showProgress();
                    FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(productId.longValue(), myHandler, 0x7f090034);
                    if(favoriteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                    {
                        favoriteasynctask.cancel(true);
                        favoriteasynctask.execute(null);
                    } else
                    {
                        favoriteasynctask.execute(null);
                    }
                } else
                {
                    User.TYPE = 0x7f090005;
                    util.changeMain("com.thestore.main.userland");
                }
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
        shareButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                util.sendSms(productVO, null);
            }

            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
        addButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(quantityEditText.getText().toString().trim().length() > 0)
                {
                    num = 1L + Long.parseLong(quantityEditText.getText().toString());
                    if(num < 1000L)
                    {
                        quantityEditText.setText(String.valueOf(num));
                        productQuantity = Long.valueOf(num);
                    }
                    et = quantityEditText.getText();
                    Selection.setSelection(et, et.length());
                } else
                {
                    showToast("\u5546\u54C1\u8D2D\u4E70\u6570\u91CF\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
                }
            }

            private Editable et;
            private long num;
            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
        subButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(quantityEditText.getText().toString().trim().length() > 0)
                {
                    num = Long.parseLong(quantityEditText.getText().toString()) - 1L;
                    if(num > 0L)
                    {
                        quantityEditText.setText(String.valueOf(num));
                        productQuantity = Long.valueOf(num);
                    }
                    et = quantityEditText.getText();
                    Selection.setSelection(et, et.length());
                } else
                {
                    showToast("\u5546\u54C1\u8D2D\u4E70\u6570\u91CF\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
                }
            }

            private Editable et;
            private long num;
            final ProductDetail this$0;

            
            {
                this$0 = ProductDetail.this;
                super();
            }
        }
);
    }

    private void initNotify(int i, int j)
    {
        notificationManager = (NotificationManager)getSystemService("notification");
        Notification notification = new Notification(i, getResources().getString(j), System.currentTimeMillis());
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        notification.setLatestEventInfo(this, getResources().getString(i), getResources().getString(j), pendingintent);
        notificationManager.notify(i, notification);
    }

    private void initViews()
    {
        introTextView = (TextView)findViewById(0x7f090160);
        descTextView = (TextView)findViewById(0x7f090161);
        cmtTextView = (TextView)findViewById(0x7f090162);
        introTextView.setOnClickListener(viewSelectListener);
        descTextView.setOnClickListener(viewSelectListener);
        cmtTextView.setOnClickListener(viewSelectListener);
        productDetailLayout.setVisibility(0);
        introLayout = (LinearLayout)findViewById(0x7f090165);
        descLayout = (LinearLayout)findViewById(0x7f090172);
        cmtLayout = (LinearLayout)findViewById(0x7f090174);
    }

    private void judgeBuySucess(Message message)
    {
        if(((Integer)message.obj).intValue() == 1)
        {
            if(quantityEditText.getText().toString() != null && quantityEditText.getText().toString().length() > 0)
            {
                Cart.cartTotal += Integer.parseInt(util.getEditString(quantityEditText));
                setCartImage(Long.valueOf(Cart.cartTotal));
                showToast(0x7f0a0108);
            } else
            {
                showToast("\u5546\u54C1\u8D2D\u4E70\u6570\u91CF\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
            }
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
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002c);
        bottomOnClick(this);
        util = new Util(this);
        util.setDefaultTitle(0x7f0a00d8);
        productDetailLayout = (LinearLayout)findViewById(0x7f090164);
        inflater = LayoutInflater.from(this);
        Intent intent = getIntent();
        productId = Long.valueOf(intent.getLongExtra("PRODUCT_ID", 0L));
        type = intent.getIntExtra("TYPE", 0);
        url = intent.getStringExtra("PRODUCT_URL");
        if(url != null)
        {
            int i = url.lastIndexOf("/");
            String as[] = url.substring(i + 1, url.length()).split("_");
            productId = Long.valueOf(Long.parseLong(as[0]));
            User.province = Long.parseLong(as[1]);
            if(as.length > 2)
                DBHelper.getTrader().setUnionKey(as[2]);
            Const.PRODUCT = 1;
        }
        if(productId.longValue() > 0L)
            if(DBHelper.testNet())
            {
                showProgress();
                ProductAsyncTask productasynctask = new ProductAsyncTask(productId, myHandler, 0x7f090024);
                if(productasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    productasynctask.cancel(true);
                    productasynctask.execute(null);
                } else
                {
                    productasynctask.execute(null);
                }
            } else
            {
                util.showNetNull();
            }
        buyArray = getResources().getStringArray(0x7f060006);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        (new MenuInflater(this)).inflate(0x7f0c0009, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR lookupswitch 5: default 56
    //                   2131296770: 58
    //                   2131296771: 65
    //                   2131296786: 236
    //                   2131296790: 78
    //                   2131296791: 327;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return true;
_L2:
        finish();
        continue; /* Loop/switch isn't completed */
_L3:
        util.changeMain("com.thestore.scan.CaptureActivity");
        continue; /* Loop/switch isn't completed */
_L5:
        if(quantityEditText.getText().toString().length() > 0)
            productQuantity = Long.valueOf(Long.parseLong(quantityEditText.getText().toString()));
        if(productQuantity.longValue() > 0L)
        {
            if(User.token != null && DBHelper.testNet())
            {
                showProgress();
                CartAsyncTask cartasynctask = new CartAsyncTask(productId.longValue(), productQuantity, myHandler, 0x7f090029);
                if(cartasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    cartasynctask.cancel(true);
                    cartasynctask.execute(null);
                } else
                {
                    cartasynctask.execute(null);
                }
            } else
            {
                User.TYPE = 0x7f090005;
                util.changeMain("com.thestore.main.userland");
            }
        } else
        {
            showToast("\u5546\u54C1\u8D2D\u4E70\u6570\u91CF\u81F3\u5C11\u4E3A1!");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(User.token != null && DBHelper.testNet())
        {
            showProgress();
            FavoriteAsyncTask favoriteasynctask = new FavoriteAsyncTask(productId.longValue(), myHandler, 0x7f090034);
            if(favoriteasynctask.getStatus() == android.os.AsyncTask.Status.RUNNING)
            {
                favoriteasynctask.cancel(true);
                favoriteasynctask.execute(null);
            } else
            {
                favoriteasynctask.execute(null);
            }
        } else
        {
            User.TYPE = 0x7f090005;
            util.changeMain("com.thestore.main.userland");
        }
        continue; /* Loop/switch isn't completed */
_L6:
        util.sendSms(productVO, null);
        if(true) goto _L1; else goto _L7
_L7:
    }

    protected void onRestart()
    {
        super.onRestart();
        bottomOnClick(this);
    }

    private Button addButton;
    private ProgressBar badBar;
    private double badRate;
    private TextView badRatingTextView;
    private String buyArray[];
    private LinearLayout buyLayout;
    private TextView canBuyTextView;
    private LinearLayout cmtLayout;
    private TextView cmtTextView;
    private LinearLayout commentLayout;
    private LinearLayout descLayout;
    private TextView descTextView;
    private WebView descWebView;
    private List experienceList;
    private TextView experienceStrTextView;
    private Button favoriteButton;
    private boolean firstComm;
    private boolean firstDesc;
    private ProgressBar goodBar;
    private double goodRate;
    private TextView goodRatingTextView;
    private LayoutInflater inflater;
    private LinearLayout introLayout;
    private TextView introTextView;
    private ImageView leftArrowImageView;
    private loadingImage loadingImage;
    private ProgressBar middleBar;
    private double middleRate;
    private TextView middleRatingTextView;
    private Handler myHandler;
    private NotificationManager notificationManager;
    private TextView priceTextView;
    private ProductVO productCommentVO;
    private String productDefaultImg;
    private ProductVO productDescVO;
    private LinearLayout productDetailLayout;
    private Long productId;
    private Gallery productImgGallery;
    private String productImgUrls[];
    private TextView productNameTextView;
    private Long productQuantity;
    private ProductRatingVO productRating;
    private ProductVO productVO;
    private EditText quantityEditText;
    private ImageView rightArrowImageView;
    private LinearLayout scanLayout;
    private TextView scanResultTextView;
    private Button shareButton;
    private List showImgs;
    private Button subButton;
    private TextView totalExperienceTextView;
    private int type;
    private String url;
    private Util util;
    private android.view.View.OnClickListener viewSelectListener;







/*
    static ProductVO access$1202(ProductDetail productdetail, ProductVO productvo)
    {
        productdetail.productDescVO = productvo;
        return productvo;
    }

*/



/*
    static boolean access$1302(ProductDetail productdetail, boolean flag)
    {
        productdetail.firstDesc = flag;
        return flag;
    }

*/




/*
    static ProductVO access$1502(ProductDetail productdetail, ProductVO productvo)
    {
        productdetail.productCommentVO = productvo;
        return productvo;
    }

*/



/*
    static ProductRatingVO access$1602(ProductDetail productdetail, ProductRatingVO productratingvo)
    {
        productdetail.productRating = productratingvo;
        return productratingvo;
    }

*/



/*
    static List access$1702(ProductDetail productdetail, List list)
    {
        productdetail.experienceList = list;
        return list;
    }

*/



/*
    static boolean access$1802(ProductDetail productdetail, boolean flag)
    {
        productdetail.firstComm = flag;
        return flag;
    }

*/





/*
    static ProductVO access$202(ProductDetail productdetail, ProductVO productvo)
    {
        productdetail.productVO = productvo;
        return productvo;
    }

*/




/*
    static Long access$2202(ProductDetail productdetail, Long long1)
    {
        productdetail.productQuantity = long1;
        return long1;
    }

*/











/*
    static String access$302(ProductDetail productdetail, String s)
    {
        productdetail.productDefaultImg = s;
        return s;
    }

*/





/*
    static String[] access$402(ProductDetail productdetail, String as[])
    {
        productdetail.productImgUrls = as;
        return as;
    }

*/



/*
    static List access$502(ProductDetail productdetail, List list)
    {
        productdetail.showImgs = list;
        return list;
    }

*/




}
