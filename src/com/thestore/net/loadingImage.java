// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.thestore.net:
//            ImageCallback

public class loadingImage
{

    public loadingImage()
    {
        imageCache = new HashMap();
    }

    public Bitmap loadImage(final String imageUrl, final ImageCallback imageCallback)
    {
        Bitmap bitmap = (Bitmap)imageCache.get(imageUrl);
        if(bitmap == null)
        {
            (new Thread(new Runnable() {

                @Override
				public void run()
                {
                    Bitmap bitmap1 = loadImageFromUrl(imageUrl);
                    imageCache.put(imageUrl, bitmap1);
                    Message message = handler.obtainMessage(0, bitmap1);
                    handler.sendMessage(message);
                }

                final loadingImage this$0;
                final Handler val$handler;
                final String val$imageUrl;

            
            {
                this$0 = loadingImage.this;
                imageUrl = s;
                handler = handler1;
                super();
            }
            }
)).start();
            bitmap = null;
        }
        return bitmap;
    }

    public Bitmap loadImageFromUrl(String s)
    {
        Bitmap bitmap;
        Bitmap bitmap1;
        bitmap = null;
        URL url;
        HttpURLConnection httpurlconnection;
        InputStream inputstream;
        try
        {
            url = new URL(s);
        }
        catch(MalformedURLException malformedurlexception)
        {
            malformedurlexception.printStackTrace();
            url = null;
        }
        httpurlconnection = (HttpURLConnection)url.openConnection();
        httpurlconnection.setDoInput(true);
        httpurlconnection.connect();
        inputstream = httpurlconnection.getInputStream();
        bitmap = BitmapFactory.decodeStream(inputstream);
        inputstream.close();
        bitmap1 = bitmap;
_L2:
        return bitmap1;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        bitmap1 = bitmap;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Map imageCache;


    // Unreferenced inner class com/thestore/net/loadingImage$1

/* anonymous class */
    class _cls1 extends Handler
    {

        @Override
		public void handleMessage(Message message)
        {
            imageCallback.imageLoaded((Bitmap)message.obj, imageUrl);
        }

        final loadingImage this$0;
        final ImageCallback val$imageCallback;
        final String val$imageUrl;

            
            {
                this$0 = loadingImage.this;
                imageCallback = imagecallback;
                imageUrl = s;
                super();
            }
    }

}
