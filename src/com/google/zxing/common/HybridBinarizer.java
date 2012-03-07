// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.*;
import java.lang.reflect.Array;

// Referenced classes of package com.google.zxing.common:
//            GlobalHistogramBinarizer, BitMatrix

public final class HybridBinarizer extends GlobalHistogramBinarizer
{

    public HybridBinarizer(LuminanceSource luminancesource)
    {
        super(luminancesource);
        matrix = null;
    }

    private void binarizeEntireImage()
        throws NotFoundException
    {
        if(matrix == null)
        {
            LuminanceSource luminancesource = getLuminanceSource();
            if(luminancesource.getWidth() >= 40 && luminancesource.getHeight() >= 40)
            {
                byte abyte0[] = luminancesource.getMatrix();
                int i = luminancesource.getWidth();
                int j = luminancesource.getHeight();
                int k = i >> 3;
                if((i & 7) != 0)
                    k++;
                int l = j >> 3;
                if((j & 7) != 0)
                    l++;
                int ai[][] = calculateBlackPoints(abyte0, k, l, i, j);
                matrix = new BitMatrix(i, j);
                calculateThresholdForBlock(abyte0, k, l, i, j, ai, matrix);
            } else
            {
                matrix = super.getBlackMatrix();
            }
        }
    }

    private static int[][] calculateBlackPoints(byte abyte0[], int i, int j, int k, int l)
    {
        int ai1[][];
        int i1;
        int ai[] = new int[2];
        ai[0] = j;
        ai[1] = i;
        ai1 = (int[][])Array.newInstance(Integer.TYPE, ai);
        i1 = 0;
_L1:
        if(i1 >= j)
            return ai1;
        int j1 = i1 << 3;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        if(j1 + 8 >= l)
            k1 = l - 8;
        else
            k1 = j1;
        l1 = 0;
label0:
        {
            if(l1 < i)
                break label0;
            i1++;
        }
          goto _L1
        i2 = l1 << 3;
        if(i2 + 8 >= k)
            j2 = k - 8;
        else
            j2 = i2;
        k2 = 255;
        l2 = 0;
        i3 = 0;
        j3 = 0;
label1:
        {
            if(i3 < 8)
                break label1;
            if(l2 - k2 > 24)
                k4 = j3 >> 6;
            else
            if(l2 == 0)
                k4 = 1;
            else
                k4 = k2 >> 1;
            ai1[i1][l1] = k4;
            l1++;
        }
        break MISSING_BLOCK_LABEL_66;
        k3 = j2 + k * (k1 + i3);
        l3 = 0;
        i4 = j3;
_L3:
label2:
        {
            if(l3 < 8)
                break label2;
            i3++;
            j3 = i4;
        }
        break MISSING_BLOCK_LABEL_113;
        j4 = 0xff & abyte0[k3 + l3];
        i4 += j4;
        if(j4 < k2)
            k2 = j4;
        if(j4 > l2)
            l2 = j4;
        l3++;
        if(true) goto _L3; else goto _L2
_L2:
    }

    private static void calculateThresholdForBlock(byte abyte0[], int i, int j, int k, int l, int ai[][], BitMatrix bitmatrix)
    {
        int i1 = 0;
_L2:
        int j1;
        int k1;
        if(i1 >= j)
            return;
        j1 = i1 << 3;
        if(j1 + 8 >= l)
            j1 = l - 8;
        k1 = 0;
_L3:
label0:
        {
            if(k1 < i)
                break label0;
            i1++;
        }
        if(true) goto _L2; else goto _L1
_L1:
        int j2;
        int l2;
        int i3;
        int j3;
        int l1 = k1 << 3;
        if(l1 + 8 >= k)
            l1 = k - 8;
        int i2;
        int k2;
        if(k1 > 1)
            i2 = k1;
        else
            i2 = 2;
        if(i2 < i - 2)
            j2 = i2;
        else
            j2 = i - 3;
        if(i1 > 1)
            k2 = i1;
        else
            k2 = 2;
        if(k2 < j - 2)
            l2 = k2;
        else
            l2 = j - 3;
        i3 = -2;
        j3 = 0;
_L4:
label1:
        {
            if(i3 <= 2)
                break label1;
            threshold8x8Block(abyte0, l1, j1, j3 / 25, k, bitmatrix);
            k1++;
        }
          goto _L3
        int ai1[] = ai[l2 + i3];
        int k3 = j3 + ai1[j2 - 2] + ai1[j2 - 1] + ai1[j2] + ai1[j2 + 1] + ai1[j2 + 2];
        i3++;
        j3 = k3;
          goto _L4
    }

    private static void threshold8x8Block(byte abyte0[], int i, int j, int k, int l, BitMatrix bitmatrix)
    {
        int i1 = 0;
_L2:
        if(i1 >= 8)
            return;
        int j1 = i + l * (j + i1);
        int k1 = 0;
        do
        {
label0:
            {
                if(k1 < 8)
                    break label0;
                i1++;
            }
            if(true)
                continue;
            if((0xff & abyte0[j1 + k1]) < k)
                bitmatrix.set(i + k1, j + i1);
            k1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	public Binarizer createBinarizer(LuminanceSource luminancesource)
    {
        return new HybridBinarizer(luminancesource);
    }

    @Override
	public BitMatrix getBlackMatrix()
        throws NotFoundException
    {
        binarizeEntireImage();
        return matrix;
    }

    private static final int MINIMUM_DIMENSION = 40;
    private BitMatrix matrix;
}
