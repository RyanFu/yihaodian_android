// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.*;
import android.view.*;
import com.google.zxing.ResultPoint;
import java.io.IOException;
import java.util.regex.Pattern;

// Referenced classes of package com.thestore.scan:
//            PlanarYUVLuminanceSource

public final class CameraManager
{

    private CameraManager(Context context1)
    {
        MIN_FRAME_WIDTH = 240;
        MIN_FRAME_HEIGHT = 140;
        MAX_FRAME_WIDTH = 480;
        MAX_FRAME_HEIGHT = 160;
        context = context1;
        camera = null;
        initialized = false;
        previewing = false;
    }

    private static Point findBestPreviewSizeValue(String s, Point point)
    {
        String as[];
        int i;
        int j;
        int k;
        int l;
        int i1;
        as = COMMA_PATTERN.split(s);
        i = as.length;
        j = 0;
        k = 0;
        l = 0x7fffffff;
        i1 = 0;
_L2:
        String s1;
        int l1;
        if(i1 >= i)
            break MISSING_BLOCK_LABEL_181;
        s1 = as[i1].trim();
        l1 = s1.indexOf('x');
        if(l1 >= 0)
            break; /* Loop/switch isn't completed */
_L5:
        i1++;
        if(true) goto _L2; else goto _L1
_L1:
        int i2;
        int j2;
        i2 = Integer.parseInt(s1.substring(0, l1));
        j2 = Integer.parseInt(s1.substring(l1 + 1));
        int k2 = Math.abs(i2 - point.x) + Math.abs(j2 - point.y);
        if(k2 != 0) goto _L4; else goto _L3
_L3:
        int j1;
        int k1;
        j1 = j2;
        k1 = i2;
_L6:
        Point point1;
        if(k1 > 0 && j1 > 0)
            point1 = new Point(k1, j1);
        else
            point1 = null;
        return point1;
_L4:
        if(k2 < l)
        {
            l = k2;
            j = j2;
            k = i2;
        }
          goto _L5
        NumberFormatException numberformatexception;
        numberformatexception;
          goto _L5
        j1 = j;
        k1 = k;
          goto _L6
    }

    public static CameraManager get()
    {
        return cameraManager;
    }

    private Point getCameraResolution(android.hardware.Camera.Parameters parameters)
    {
        String s = parameters.get("preview-size-values");
        if(s == null)
            s = parameters.get("preview-size-value");
        Point point;
        if(s != null)
            point = findBestPreviewSizeValue(s, screenResolution);
        else
            point = null;
        if(point == null)
            point = new Point((screenResolution.x >> 3) << 3, (screenResolution.y >> 3) << 3);
        return point;
    }

    private Point getScreenResolution()
    {
        if(screenResolution == null)
        {
            Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            screenResolution = new Point(display.getWidth(), display.getHeight());
        }
        return screenResolution;
    }

    public static void init(Context context1)
    {
        if(cameraManager == null)
            cameraManager = new CameraManager(context1);
    }

    private void setCameraParameters()
    {
        android.hardware.Camera.Parameters parameters = camera.getParameters();
        previewFormat = parameters.getPreviewFormat();
        previewFormatString = parameters.get("preview-format");
        cameraResolution = getCameraResolution(parameters);
        parameters.setPreviewSize(cameraResolution.x, cameraResolution.y);
        parameters.set("flash-value", 2);
        parameters.set("flash-mode", "off");
        parameters.set("zoom", "4.0");
        parameters.set("taking-picture-zoom", "8");
        camera.setParameters(parameters);
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte abyte0[], int i, int j)
    {
        Rect rect = getFramingRect();
        previewFormat;
        JVM INSTR tableswitch 16 17: default 32
    //                   16 80
    //                   17 80;
           goto _L1 _L2 _L2
_L1:
        if(!"yuv420p".equals(previewFormatString)) goto _L4; else goto _L3
_L3:
        PlanarYUVLuminanceSource planaryuvluminancesource = new PlanarYUVLuminanceSource(abyte0, i, j, rect.left, rect.top, rect.width(), rect.height());
_L5:
        return planaryuvluminancesource;
_L2:
        planaryuvluminancesource = new PlanarYUVLuminanceSource(abyte0, i, j, rect.left, rect.top, rect.width(), rect.height());
        if(true) goto _L5; else goto _L4
_L4:
        throw new IllegalArgumentException((new StringBuilder()).append("Unsupported picture format: ").append(previewFormat).append('/').append(previewFormatString).toString());
    }

    public void closeDriver()
    {
        if(camera != null)
        {
            camera.release();
            camera = null;
        }
    }

    public Point[] convertResultPoints(ResultPoint aresultpoint[])
    {
        Rect rect = getFramingRect();
        int i = aresultpoint.length;
        Point apoint[] = new Point[i];
        for(int j = 0; j < i; j++)
        {
            apoint[j] = new Point();
            apoint[j].x = rect.left + (int)(0.5F + aresultpoint[j].getX());
            apoint[j].y = rect.top + (int)(0.5F + aresultpoint[j].getY());
        }

        return apoint;
    }

    public Rect getFramingRect()
    {
        if(Build.BOARD.equals("buzz"))
        {
            MIN_FRAME_HEIGHT = 300;
            MAX_FRAME_HEIGHT = 400;
        }
        if(framingRect != null) goto _L2; else goto _L1
_L1:
        if(camera != null) goto _L4; else goto _L3
_L3:
        Rect rect = null;
_L6:
        return rect;
_L4:
        int i = (3 * cameraResolution.x) / 4;
        int j;
        int k;
        int l;
        if(i < MIN_FRAME_WIDTH)
        {
            if(Build.BOARD.equals("buzz"))
                i = MIN_FRAME_WIDTH;
            else
                i = 150 + MIN_FRAME_WIDTH;
        } else
        if(i > MAX_FRAME_WIDTH)
            if(Build.BOARD.equals("buzz"))
                i = MAX_FRAME_WIDTH;
            else
                i = 150 + MAX_FRAME_WIDTH;
        j = (3 * cameraResolution.y) / 4;
        if(j < MIN_FRAME_HEIGHT)
        {
            if(Build.BOARD.equals("buzz"))
            {
                k = MIN_FRAME_HEIGHT;
                l = j;
            } else
            {
                int j1 = 150 + MIN_FRAME_HEIGHT;
                k = i;
                l = j1;
            }
        } else
        if(j > MAX_FRAME_HEIGHT)
        {
            if(Build.BOARD.equals("buzz"))
            {
                k = MAX_FRAME_HEIGHT;
                l = j;
            } else
            {
                int i1 = 150 + MAX_FRAME_HEIGHT;
                k = i;
                l = i1;
            }
        } else
        {
            k = i;
            l = j;
        }
        (cameraResolution.x - k) / 2;
        (cameraResolution.y - l) / 2;
        framingRect = new Rect(0, 0, k + 0, l + 0);
_L2:
        rect = framingRect;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void openDriver(SurfaceHolder surfaceholder)
        throws IOException
    {
        if(camera == null)
        {
            camera = Camera.open();
            if(camera == null)
                throw new IOException();
            camera.setPreviewDisplay(surfaceholder);
            if(!initialized)
            {
                initialized = true;
                getScreenResolution();
            }
            setCameraParameters();
        }
    }

    public void requestAutoFocus(Handler handler, int i)
    {
        if(camera != null && previewing)
        {
            autoFocusHandler = handler;
            autoFocusMessage = i;
            camera.autoFocus(autoFocusCallback);
        }
    }

    public void requestPreviewFrame(Handler handler, int i)
    {
        if(camera != null && previewing)
        {
            previewHandler = handler;
            previewMessage = i;
            if(useOneShotPreviewCallback)
                camera.setOneShotPreviewCallback(previewCallback);
            else
                camera.setPreviewCallback(previewCallback);
        }
    }

    public void startPreview()
    {
        if(camera != null && !previewing)
        {
            camera.startPreview();
            previewing = true;
        }
    }

    public void stopPreview()
    {
        if(camera != null && previewing)
        {
            if(!useOneShotPreviewCallback)
                camera.setPreviewCallback(null);
            camera.stopPreview();
            previewHandler = null;
            autoFocusHandler = null;
            previewing = false;
        }
    }

    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static CameraManager cameraManager;
    private int MAX_FRAME_HEIGHT;
    private int MAX_FRAME_WIDTH;
    private int MIN_FRAME_HEIGHT;
    private int MIN_FRAME_WIDTH;
    private final android.hardware.Camera.AutoFocusCallback autoFocusCallback = new android.hardware.Camera.AutoFocusCallback() {

        public void onAutoFocus(boolean flag, Camera camera1)
        {
            if(autoFocusHandler != null)
            {
                Message message = autoFocusHandler.obtainMessage(autoFocusMessage, Boolean.valueOf(flag));
                autoFocusHandler.sendMessageDelayed(message, 500L);
                autoFocusHandler = null;
            }
        }

        final CameraManager this$0;

            
            {
                this$0 = CameraManager.this;
                super();
            }
    }
;
    private Handler autoFocusHandler;
    private int autoFocusMessage;
    private Camera camera;
    private Point cameraResolution;
    private final Context context;
    private Rect framingRect;
    private boolean initialized;
    private final android.hardware.Camera.PreviewCallback previewCallback = new android.hardware.Camera.PreviewCallback() {

        public void onPreviewFrame(byte abyte0[], Camera camera1)
        {
            if(!useOneShotPreviewCallback)
                camera1.setPreviewCallback(null);
            if(previewHandler != null)
            {
                previewHandler.obtainMessage(previewMessage, cameraResolution.x, cameraResolution.y, abyte0).sendToTarget();
                previewHandler = null;
            }
        }

        final CameraManager this$0;

            
            {
                this$0 = CameraManager.this;
                super();
            }
    }
;
    private int previewFormat;
    private String previewFormatString;
    private Handler previewHandler;
    private int previewMessage;
    private boolean previewing;
    private Point screenResolution;
    private boolean useOneShotPreviewCallback;





/*
    static Handler access$102(CameraManager cameramanager, Handler handler)
    {
        cameramanager.previewHandler = handler;
        return handler;
    }

*/





/*
    static Handler access$402(CameraManager cameramanager, Handler handler)
    {
        cameramanager.autoFocusHandler = handler;
        return handler;
    }

*/

}
