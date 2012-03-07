// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.*;
import com.google.zxing.Result;
import java.util.Vector;

// Referenced classes of package com.thestore.scan:
//            DecodeThread, ViewfinderResultPointCallback, CaptureActivity, CameraManager

public final class CaptureActivityHandler extends Handler
{
    private static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/thestore/scan/CaptureActivityHandler$State, s);
        }

        public static State[] values()
        {
            return $VALUES.clone();
        }

        private static final State $VALUES[];
        public static final State DONE;
        public static final State PREVIEW;
        public static final State SUCCESS;

        static 
        {
            PREVIEW = new State("PREVIEW", 0);
            SUCCESS = new State("SUCCESS", 1);
            DONE = new State("DONE", 2);
            State astate[] = new State[3];
            astate[0] = PREVIEW;
            astate[1] = SUCCESS;
            astate[2] = DONE;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    CaptureActivityHandler(CaptureActivity captureactivity, Vector vector, String s, boolean flag)
    {
        activity = captureactivity;
        decodeThread = new DecodeThread(captureactivity, vector, s, new ViewfinderResultPointCallback(captureactivity.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;
        CameraManager.get().startPreview();
        if(flag)
            restartPreviewAndDecode();
    }

    private void restartPreviewAndDecode()
    {
        if(state == State.SUCCESS)
        {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), 0x7f09000d);
            CameraManager.get().requestAutoFocus(this, 0x7f09000c);
            activity.drawViewfinder();
        }
    }

    @Override
	public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 2131296268 2131296277: default 60
    //                   2131296268 61
    //                   2131296269 60
    //                   2131296270 142
    //                   2131296271 90
    //                   2131296272 60
    //                   2131296273 60
    //                   2131296274 60
    //                   2131296275 60
    //                   2131296276 83
    //                   2131296277 171;
           goto _L1 _L2 _L1 _L3 _L4 _L1 _L1 _L1 _L1 _L5 _L6
_L1:
        return;
_L2:
        if(state == State.PREVIEW)
            CameraManager.get().requestAutoFocus(this, 0x7f09000c);
        continue; /* Loop/switch isn't completed */
_L5:
        restartPreviewAndDecode();
        continue; /* Loop/switch isn't completed */
_L4:
        state = State.SUCCESS;
        Bundle bundle = message.getData();
        Bitmap bitmap;
        if(bundle == null)
            bitmap = null;
        else
            bitmap = (Bitmap)bundle.getParcelable("barcode_bitmap");
        activity.handleDecode((Result)message.obj, bitmap);
        continue; /* Loop/switch isn't completed */
_L3:
        state = State.PREVIEW;
        try
        {
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), 0x7f09000d);
        }
        catch(Exception exception) { }
        continue; /* Loop/switch isn't completed */
_L6:
        activity.setResult(-1, (Intent)message.obj);
        activity.finish();
        if(true) goto _L1; else goto _L7
_L7:
    }

    public void quitSynchronously()
    {
        state = State.DONE;
        CameraManager.get().stopPreview();
        Message.obtain(decodeThread.getHandler(), 0x7f090013).sendToTarget();
        try
        {
            decodeThread.join();
        }
        catch(InterruptedException interruptedexception) { }
        removeMessages(0x7f09000f);
        removeMessages(0x7f09000e);
    }

    private final CaptureActivity activity;
    private final DecodeThread decodeThread;
    private State state;
}
