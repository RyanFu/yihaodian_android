// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.os.*;
import android.util.Log;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.thestore.scan:
//            CameraManager, CaptureActivity, PlanarYUVLuminanceSource

final class DecodeThread extends Thread
{

    DecodeThread(CaptureActivity captureactivity, Vector vector, String s, ResultPointCallback resultpointcallback)
    {
        activity = captureactivity;
        Hashtable hashtable = new Hashtable(3);
        if(vector != null && !vector.isEmpty())
            hashtable.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        if(s != null)
            hashtable.put(DecodeHintType.CHARACTER_SET, s);
        hashtable.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultpointcallback);
        multiFormatReader.setHints(hashtable);
    }

    private void decode(byte abyte0[], int i, int j)
    {
        com.google.zxing.Result result;
        PlanarYUVLuminanceSource planaryuvluminancesource;
        BinaryBitmap binarybitmap;
        result = null;
        planaryuvluminancesource = CameraManager.get().buildLuminanceSource(abyte0, i, j);
        binarybitmap = new BinaryBitmap(new HybridBinarizer(planaryuvluminancesource));
        com.google.zxing.Result result1 = multiFormatReader.decodeWithState(binarybitmap);
        result = result1;
        multiFormatReader.reset();
_L1:
        Exception exception;
        ReaderException readerexception;
        if(result != null)
        {
            Message message = Message.obtain(activity.getHandler(), 0x7f09000f, result);
            Bundle bundle = new Bundle();
            bundle.putParcelable("barcode_bitmap", planaryuvluminancesource.renderCroppedGreyscaleBitmap());
            message.setData(bundle);
            message.sendToTarget();
        } else
        {
            Message.obtain(activity.getHandler(), 0x7f09000e).sendToTarget();
        }
        return;
        readerexception;
        multiFormatReader.reset();
          goto _L1
        exception;
        multiFormatReader.reset();
        throw exception;
    }

    Handler getHandler()
    {
        return handler;
    }

    @Override
	public void run()
    {
        Looper.prepare();
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR lookupswitch 2: default 32
            //                           2131296269: 33
            //                           2131296275: 83;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                Log.d("key", "=======");
                try
                {
                    decode((byte[])(byte[])message.obj, message.arg1, message.arg2);
                }
                catch(Exception exception)
                {
                    Log.d("key", exception.getMessage());
                }
                continue; /* Loop/switch isn't completed */
_L3:
                Looper.myLooper().quit();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final DecodeThread this$0;

            
            {
                this$0 = DecodeThread.this;
                super();
            }
        }
;
        Looper.loop();
    }

    public static final String BARCODE_BITMAP = "barcode_bitmap";
    private final CaptureActivity activity;
    private Handler handler;
    private final MultiFormatReader multiFormatReader = new MultiFormatReader();

}
