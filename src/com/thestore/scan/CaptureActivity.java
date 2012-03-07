// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.Button;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.thestore.net.DBHelper;
import com.thestore.net.ProductAsyncTask;
import com.thestore.util.Util;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

// Referenced classes of package com.thestore.scan:
//            CameraManager, CaptureActivityHandler, ViewfinderView, ResultHandlerFactory, 
//            ResultHandler

public final class CaptureActivity extends Activity
    implements android.view.SurfaceHolder.Callback, android.view.View.OnClickListener
{
    private static final class Source extends Enum
    {

        public static Source valueOf(String s)
        {
            return (Source)Enum.valueOf(com/thestore/scan/CaptureActivity$Source, s);
        }

        public static Source[] values()
        {
            return $VALUES.clone();
        }

        private static final Source $VALUES[];
        public static final Source NATIVE_APP_INTENT;
        public static final Source NONE;

        static 
        {
            NATIVE_APP_INTENT = new Source("NATIVE_APP_INTENT", 0);
            NONE = new Source("NONE", 1);
            Source asource[] = new Source[2];
            asource[0] = NATIVE_APP_INTENT;
            asource[1] = NONE;
            $VALUES = asource;
        }

        private Source(String s, int i)
        {
            super(s, i);
        }
    }


    public CaptureActivity()
    {
        barcodHandler = new Handler() {

            @Override
			public void handleMessage(Message message)
            {
                cancelProgress();
                if(message.obj == null || ((List)message.obj).size() == 0)
                {
                    if(DBHelper.getTimeOut())
                    {
                        (new android.app.AlertDialog.Builder(CaptureActivity.this)).setMessage(0x7f0a001c).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                            @Override
							public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                            {
                                if(i == 4)
                                {
                                    CameraManager.get().stopPreview();
                                    finish();
                                }
                                return false;
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNegativeButton(0x7f0a00f7, new android.content.DialogInterface.OnClickListener() {

                            @Override
							public void onClick(DialogInterface dialoginterface, int i)
                            {
                                finish();
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
).setNeutralButton("\u91CD\u65B0\u626B\u63CF", new android.content.DialogInterface.OnClickListener() {

                            @Override
							public void onClick(DialogInterface dialoginterface, int i)
                            {
                                finish();
                                util.changeMain("com.thestore.scan.CaptureActivity");
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
                        CameraManager.get().stopPreview();
                        util.changeMain("com.thestore.main.ScanNullResult");
                        finish();
                    }
                } else
                {
                    CaptureActivity.productVOs = (List)message.obj;
                    CameraManager.get().stopPreview();
                    if(CaptureActivity.productVOs.size() == 1)
                    {
                        Intent intent = new Intent("com.thestore.main.ProductDetail");
                        intent.putExtra("TYPE", 3);
                        intent.putExtra("PRODUCT_ID", ((ProductVO)CaptureActivity.productVOs.get(0)).getProductId());
                        startActivity(intent);
                    } else
                    {
                        Intent intent1 = new Intent("com.thestore.main.BarCodeResult");
                        startActivity(intent1);
                    }
                    finish();
                }
            }

            final CaptureActivity this$0;

            
            {
                this$0 = CaptureActivity.this;
                super();
            }
        }
;
    }

    private void drawResultPoints(Bitmap bitmap, Result result)
    {
        com.google.zxing.ResultPoint aresultpoint[] = result.getResultPoints();
        if(aresultpoint != null && aresultpoint.length > 0)
        {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(0x7f070002));
            paint.setStrokeWidth(3F);
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            canvas.drawRect(new Rect(2, 2, bitmap.getWidth() - 2, bitmap.getHeight() - 2), paint);
            paint.setColor(getResources().getColor(0x7f070005));
            if(aresultpoint.length == 2)
                paint.setStrokeWidth(4F);
        }
    }

    private void init()
    {
        util = new Util(this);
        SurfaceHolder surfaceholder = ((SurfaceView)findViewById(0x7f09006d)).getHolder();
        Intent intent;
        String s;
        if(hasSurface)
        {
            initCamera(surfaceholder);
        } else
        {
            surfaceholder.addCallback(this);
            surfaceholder.setType(3);
        }
        intent = getIntent();
        if(intent == null)
            s = null;
        else
            s = intent.getAction();
        if(intent != null && s != null)
        {
            if(s.equals("com.google.zxing.client.android.SCAN"))
            {
                source = Source.NATIVE_APP_INTENT;
                decodeFormats = parseDecodeFormats(intent);
            } else
            {
                source = Source.NONE;
                decodeFormats = null;
            }
            characterSet = intent.getStringExtra("CHARACTER_SET");
        } else
        {
            source = Source.NONE;
            decodeFormats = null;
            characterSet = null;
        }
    }

    private void initCamera(SurfaceHolder surfaceholder)
    {
        try
        {
            CameraManager.get().openDriver(surfaceholder);
        }
        catch(Exception exception)
        {
            util.displayFrameworkBugMessageAndExit();
        }
        if(handler == null)
        {
            boolean flag;
            if(lastResult == null)
                flag = true;
            else
                flag = false;
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet, flag);
        }
    }

    private static Vector parseDecodeFormats(Intent intent)
    {
        String s;
        Vector vector;
        s = intent.getStringExtra("SCAN_FORMATS");
        if(s == null)
            break MISSING_BLOCK_LABEL_64;
        vector = new Vector();
        String as[];
        int i;
        int j;
        as = COMMA_PATTERN.split(s);
        i = as.length;
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_64;
        vector.add(BarcodeFormat.valueOf(as[j]));
        j++;
          goto _L1
        Exception exception;
        exception;
        String s1 = intent.getStringExtra("SCAN_MODE");
        Vector vector1;
        if(s1 != null && "PRODUCT_MODE".equals(s1))
            vector1 = PRODUCT_FORMATS;
        else
            vector1 = null;
        return vector1;
    }

    public void cancelProgress()
    {
        if(progressDialog != null)
        {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
    }

    public void drawViewfinder()
    {
        viewfinderView.drawViewfinder();
    }

    public Handler getHandler()
    {
        return handler;
    }

    ViewfinderView getViewfinderView()
    {
        return viewfinderView;
    }

    public void handleDecode(Result result, Bitmap bitmap)
    {
        lastResult = result;
        if(bitmap != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        drawResultPoints(bitmap, result);
        class _cls2
        {

            static final int $SwitchMap$com$thestore$scan$CaptureActivity$Source[];

            static 
            {
                $SwitchMap$com$thestore$scan$CaptureActivity$Source = new int[Source.values().length];
                $SwitchMap$com$thestore$scan$CaptureActivity$Source[Source.NONE.ordinal()] = 1;
_L2:
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        switch(_cls2..SwitchMap.com.thestore.scan.CaptureActivity.Source[source.ordinal()])
        {
        case 1: // '\001'
            String s = (String)ResultHandlerFactory.makeResultHandler(this, result).getDisplayContents();
            if(DBHelper.testNet())
            {
                showProgress();
                (new ProductAsyncTask(s, barcodHandler, 0x7f090016)).execute(null);
            } else
            {
                (new Util(this)).showNetNull();
            }
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    @Override
	public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131296369 2131296370: default 28
    //                   2131296369 29
    //                   2131296370 56;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        finish();
        CameraManager.get().stopPreview();
        startActivity(new Intent("com.thestore.main.BarCode"));
        continue; /* Loop/switch isn't completed */
_L3:
        CameraManager.get().stopPreview();
        finish();
        if(true) goto _L1; else goto _L4
_L4:
    }

    @Override
	public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    @Override
	public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        requestWindowFeature(1);
        setContentView(0x7f030004);
        scanButton = (Button)findViewById(0x7f090071);
        backButton = (Button)findViewById(0x7f090072);
        scanButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        getWindow().setFlags(1024, 1024);
        viewfinderView = (ViewfinderView)findViewById(0x7f09006e);
        handler = null;
        lastResult = null;
        hasSurface = false;
        init();
        CameraManager.init(getApplication());
    }

    @Override
	public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        finish();
        return super.onKeyDown(i, keyevent);
    }

    @Override
	protected void onPause()
    {
        super.onPause();
        if(handler != null)
        {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
	protected void onResume()
    {
        super.onResume();
    }

    public void showProgress()
    {
        progressDialog = ProgressDialog.show(this, "", getResources().getString(0x7f0a000f));
        progressDialog.setCancelable(true);
    }

    @Override
	public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
    }

    @Override
	public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        if(!hasSurface)
        {
            hasSurface = true;
            initCamera(surfaceholder);
        }
    }

    @Override
	public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        hasSurface = false;
    }

    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Vector PRODUCT_FORMATS;
    public static List productVOs;
    private Button backButton;
    Handler barcodHandler;
    private String characterSet;
    private Vector decodeFormats;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private Result lastResult;
    private ProgressDialog progressDialog;
    private Button scanButton;
    private Source source;
    private Util util;
    private ViewfinderView viewfinderView;

    static 
    {
        PRODUCT_FORMATS = new Vector(5);
        PRODUCT_FORMATS.add(BarcodeFormat.UPC_A);
        PRODUCT_FORMATS.add(BarcodeFormat.UPC_E);
        PRODUCT_FORMATS.add(BarcodeFormat.EAN_13);
        PRODUCT_FORMATS.add(BarcodeFormat.EAN_8);
    }

}
