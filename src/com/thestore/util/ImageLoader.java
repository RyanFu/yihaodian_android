// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

// Referenced classes of package com.thestore.util:
//            Utils

public class ImageLoader
{
    class BitmapDisplayer
        implements Runnable
    {

        @Override
		public void run()
        {
            if(bitmap != null)
                imageView.setImageBitmap(bitmap);
            else
                imageView.setImageResource(0x7f020069);
        }

        Bitmap bitmap;
        ImageView imageView;
        final ImageLoader this$0;

        public BitmapDisplayer(Bitmap bitmap1, ImageView imageview)
        {
            this$0 = ImageLoader.this;
            super();
            bitmap = bitmap1;
            imageView = imageview;
        }
    }

    class PhotosLoader extends Thread
    {

        @Override
		public void run()
        {
_L2:
            if(photosQueue.photosToLoad.size() == 0)
                synchronized(photosQueue.photosToLoad)
                {
                    photosQueue.photosToLoad.wait();
                }
            if(photosQueue.photosToLoad.size() == 0)
                break MISSING_BLOCK_LABEL_195;
            PhotoToLoad phototoload;
            synchronized(photosQueue.photosToLoad)
            {
                phototoload = (PhotoToLoad)photosQueue.photosToLoad.pop();
            }
            Bitmap bitmap = getBitmap(phototoload.url);
            cache.put(phototoload.url, bitmap);
            Object obj = phototoload.imageView.getTag();
            if(obj != null && ((String)obj).equals(phototoload.url))
            {
                BitmapDisplayer bitmapdisplayer = new BitmapDisplayer(bitmap, phototoload.imageView);
                ((Activity)phototoload.imageView.getContext()).runOnUiThread(bitmapdisplayer);
            }
            boolean flag = Thread.interrupted();
            if(!flag) goto _L2; else goto _L1
_L1:
            return;
            exception2;
            stack1;
            JVM INSTR monitorexit ;
            try
            {
                throw exception2;
            }
            catch(Exception exception) { }
            if(true) goto _L1; else goto _L3
_L3:
            exception1;
            stack;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        final ImageLoader this$0;

        PhotosLoader()
        {
            this$0 = ImageLoader.this;
            super();
        }
    }

    class PhotosQueue
    {

        public void Clean(ImageView imageview)
        {
            int i = 0;
            do
            {
label0:
                {
                    try
                    {
                        if(i < photosToLoad.size())
                        {
                            if(((PhotoToLoad)photosToLoad.get(i)).imageView == imageview)
                            {
                                photosToLoad.remove(i);
                                continue;
                            }
                            break label0;
                        }
                    }
                    catch(Exception exception) { }
                    return;
                }
                i++;
            } while(true);
        }

        private Stack photosToLoad;
        final ImageLoader this$0;


        PhotosQueue()
        {
            this$0 = ImageLoader.this;
            super();
            photosToLoad = new Stack();
        }
    }

    private class PhotoToLoad
    {

        public ImageView imageView;
        final ImageLoader this$0;
        public String url;

        public PhotoToLoad(String s, ImageView imageview)
        {
            this$0 = ImageLoader.this;
            super();
            url = s;
            imageView = imageview;
        }
    }


    public ImageLoader(Context context)
    {
        cache = new HashMap();
        photosQueue = new PhotosQueue();
        photoLoaderThread = new PhotosLoader();
        photoLoaderThread.setPriority(4);
        if(Environment.getExternalStorageState().equals("mounted"))
            cacheDir = new File(Environment.getExternalStorageDirectory(), "thestore");
        else
            cacheDir = context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }

    private Bitmap decodeFile(File file)
    {
        int k;
        int l;
        int i1;
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(file), null, options);
        int i = options.outWidth;
        int j = options.outHeight;
        k = i;
        l = j;
        i1 = 1;
_L1:
        Bitmap bitmap;
        if(k / 2 < 70 || l / 2 < 70)
        {
            android.graphics.BitmapFactory.Options options1 = new android.graphics.BitmapFactory.Options();
            options1.inSampleSize = i1;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, options1);
            break MISSING_BLOCK_LABEL_128;
        }
        k /= 2;
        l /= 2;
        i1 *= 2;
          goto _L1
        Exception exception;
        exception;
        bitmap = null;
        return bitmap;
    }

    private Bitmap getBitmap(String s)
    {
        File file;
        Bitmap bitmap;
        String s1 = String.valueOf(s.hashCode());
        file = new File(cacheDir, s1);
        bitmap = decodeFile(file);
        if(bitmap == null) goto _L2; else goto _L1
_L1:
        return bitmap;
_L2:
        Bitmap bitmap1;
        java.io.InputStream inputstream = (new URL(s)).openStream();
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        Utils.CopyStream(inputstream, fileoutputstream);
        fileoutputstream.close();
        bitmap1 = decodeFile(file);
        bitmap = bitmap1;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        bitmap = null;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void queuePhoto(String s, Activity activity, ImageView imageview)
    {
        photosQueue.Clean(imageview);
        PhotoToLoad phototoload = new PhotoToLoad(s, imageview);
        synchronized(photosQueue.photosToLoad)
        {
            photosQueue.photosToLoad.push(phototoload);
            photosQueue.photosToLoad.notifyAll();
        }
        if(photoLoaderThread.getState() == Thread.State.NEW)
            photoLoaderThread.start();
        return;
        exception;
        stack;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void DisplayImage(String s, Activity activity, ImageView imageview)
    {
        if(s != null)
        {
            if(cache.containsKey(s))
            {
                imageview.setImageBitmap((Bitmap)cache.get(s));
            } else
            {
                queuePhoto(s, activity, imageview);
                imageview.setImageResource(0x7f020069);
            }
        } else
        {
            try
            {
                queuePhoto("", activity, imageview);
                imageview.setImageResource(0x7f020069);
            }
            catch(Exception exception) { }
        }
    }

    public void clearCache()
    {
        cache.clear();
        File afile[] = cacheDir.listFiles();
        int i = afile.length;
        for(int j = 0; j < i; j++)
            afile[j].delete();

    }

    public void stopThread()
    {
        photoLoaderThread.interrupt();
    }

    private HashMap cache;
    private File cacheDir;
    final int img_default = 0x7f020069;
    PhotosLoader photoLoaderThread;
    PhotosQueue photosQueue;


}
