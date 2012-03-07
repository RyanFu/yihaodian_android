// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.scan;

import android.graphics.Bitmap;
import com.google.zxing.LuminanceSource;

public final class PlanarYUVLuminanceSource extends LuminanceSource
{

    public PlanarYUVLuminanceSource(byte abyte0[], int i, int j, int k, int l, int i1, int j1)
    {
        super(i1, j1);
        if(k + i1 > i || l + j1 > j)
        {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        } else
        {
            yuvData = abyte0;
            dataWidth = i;
            dataHeight = j;
            left = k;
            top = l;
            return;
        }
    }

    public int getDataHeight()
    {
        return dataHeight;
    }

    public int getDataWidth()
    {
        return dataWidth;
    }

    @Override
	public byte[] getMatrix()
    {
        int i = getWidth();
        int j = getHeight();
        byte abyte2[];
        if(i == dataWidth && j == dataHeight)
        {
            abyte2 = yuvData;
        } else
        {
            int k = i * j;
            byte abyte0[] = new byte[k];
            int l = top * dataWidth + left;
            if(i == dataWidth)
            {
                System.arraycopy(yuvData, l, abyte0, 0, k);
                abyte2 = abyte0;
            } else
            {
                byte abyte1[] = yuvData;
                int i1 = l;
                for(int j1 = 0; j1 < j; j1++)
                {
                    System.arraycopy(abyte1, i1, abyte0, j1 * i, i);
                    i1 += dataWidth;
                }

                abyte2 = abyte0;
            }
        }
        return abyte2;
    }

    @Override
	public byte[] getRow(int i, byte abyte0[])
    {
        if(i < 0 || i >= getHeight())
            throw new IllegalArgumentException((new StringBuilder()).append("Requested row is outside the image: ").append(i).toString());
        int j = getWidth();
        byte abyte1[];
        int k;
        if(abyte0 == null || abyte0.length < j)
            abyte1 = new byte[j];
        else
            abyte1 = abyte0;
        k = (i + top) * dataWidth + left;
        System.arraycopy(yuvData, k, abyte1, 0, j);
        return abyte1;
    }

    @Override
	public boolean isCropSupported()
    {
        return true;
    }

    public Bitmap renderCroppedGreyscaleBitmap()
    {
        int i = getWidth();
        int j = getHeight();
        int ai[] = new int[i * j];
        byte abyte0[] = yuvData;
        int k = top * dataWidth + left;
        for(int l = 0; l < j; l++)
        {
            int i1 = l * i;
            for(int j1 = 0; j1 < i; j1++)
            {
                int k1 = 0xff & abyte0[k + j1];
                ai[i1 + j1] = 0xff000000 | k1 * 0x10101;
            }

            k += dataWidth;
        }

        Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        bitmap.setPixels(ai, 0, i, 0, 0, i, j);
        return bitmap;
    }

    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte yuvData[];
}
