// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.*;

// Referenced classes of package com.google.zxing.common:
//            BitMatrix, BitArray

public class GlobalHistogramBinarizer extends Binarizer
{

    public GlobalHistogramBinarizer(LuminanceSource luminancesource)
    {
        super(luminancesource);
        luminances = null;
        buckets = null;
    }

    private static int estimateBlackPoint(int ai[])
        throws NotFoundException
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        i = ai.length;
        j = 0;
        k = 0;
        l = 0;
        i1 = 0;
_L8:
        if(j < i) goto _L2; else goto _L1
_L1:
        int k2;
        int l2;
        int i3;
        k2 = 0;
        l2 = 0;
        i3 = 0;
_L6:
        if(k2 < i) goto _L4; else goto _L3
_L3:
        int k1;
        int l1;
        int i2;
        int l3;
        int i4;
        int j2;
        int j3;
        int k3;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int l5;
        if(i1 > i3)
        {
            int i6 = i1;
            int j6 = i3;
            j4 = i6;
            k4 = j6;
        } else
        {
            j4 = i3;
            k4 = i1;
        }
        if(j4 - k4 <= i >> 4)
            throw NotFoundException.getNotFoundInstance();
          goto _L5
_L2:
        if(ai[j] > l)
        {
            k1 = j;
            l1 = ai[j];
        } else
        {
            int j1 = l;
            k1 = i1;
            l1 = j1;
        }
        if(ai[j] > k)
            i2 = ai[j];
        else
            i2 = k;
        j++;
        k = i2;
        j2 = k1;
        l = l1;
        i1 = j2;
        continue; /* Loop/switch isn't completed */
_L4:
        j3 = k2 - i1;
        k3 = j3 * (j3 * ai[k2]);
        if(k3 > l2)
        {
            l3 = k2;
            i4 = k3;
        } else
        {
            l3 = i3;
            i4 = l2;
        }
        k2++;
        l2 = i4;
        i3 = l3;
        if(true) goto _L6; else goto _L5
_L5:
        l4 = j4 - 1;
        i5 = -1;
        j5 = j4 - 1;
        do
        {
            if(j5 <= k4)
                return l4 << 3;
            k5 = j5 - k4;
            l5 = k5 * k5 * (j4 - j5) * (k - ai[j5]);
            if(l5 > i5)
            {
                l4 = j5;
                i5 = l5;
            }
            j5--;
        } while(true);
        if(true) goto _L8; else goto _L7
_L7:
    }

    private void initArrays(int i)
    {
        if(luminances == null || luminances.length < i)
            luminances = new byte[i];
        if(buckets == null)
        {
            buckets = new int[32];
        } else
        {
            int j = 0;
            while(j < 32) 
            {
                buckets[j] = 0;
                j++;
            }
        }
    }

    @Override
	public Binarizer createBinarizer(LuminanceSource luminancesource)
    {
        return new GlobalHistogramBinarizer(luminancesource);
    }

    @Override
	public BitMatrix getBlackMatrix()
        throws NotFoundException
    {
        LuminanceSource luminancesource;
        int i;
        int j;
        BitMatrix bitmatrix;
        int ai[];
        int k;
        luminancesource = getLuminanceSource();
        i = luminancesource.getWidth();
        j = luminancesource.getHeight();
        bitmatrix = new BitMatrix(i, j);
        initArrays(i);
        ai = buckets;
        k = 1;
_L1:
        int k1;
        byte abyte1[];
        int l1;
        if(k >= 5)
        {
            k1 = estimateBlackPoint(ai);
            abyte1 = luminancesource.getMatrix();
            l1 = 0;
            break MISSING_BLOCK_LABEL_62;
        }
        abyte0 = luminancesource.getRow((j * k) / 5, luminances);
        l = (i << 2) / 5;
        i1 = i / 5;
_L2:
label0:
        {
            if(i1 < l)
                break label0;
            k++;
        }
          goto _L1
        int j1 = (0xff & abyte0[i1]) >> 3;
        ai[j1] = 1 + ai[j1];
        i1++;
          goto _L2
_L4:
        int i2;
        int j2;
        byte abyte0[];
        int l;
        int i1;
        if(l1 >= j)
            return bitmatrix;
        i2 = l1 * i;
        j2 = 0;
_L5:
label1:
        {
            if(j2 < i)
                break label1;
            l1++;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if((0xff & abyte1[i2 + j2]) < k1)
            bitmatrix.set(j2, l1);
        j2++;
          goto _L5
    }

    @Override
	public BitArray getBlackRow(int i, BitArray bitarray)
        throws NotFoundException
    {
        byte abyte0[];
        int ai[];
        int k;
        int i1;
        int j1;
        int k1;
        int l1;
        LuminanceSource luminancesource = getLuminanceSource();
        int j = luminancesource.getWidth();
        if(bitarray == null || bitarray.getSize() < j)
            bitarray = new BitArray(j);
        else
            bitarray.clear();
        initArrays(j);
        abyte0 = luminancesource.getRow(i, luminances);
        ai = buckets;
        k = 0;
_L3:
        if(k < j) goto _L2; else goto _L1
_L1:
        i1 = estimateBlackPoint(ai);
        j1 = 0xff & abyte0[0];
        k1 = 0xff & abyte0[1];
        l1 = 1;
_L4:
        if(l1 >= j - 1)
            return bitarray;
        break MISSING_BLOCK_LABEL_146;
_L2:
        int l = (0xff & abyte0[k]) >> 3;
        ai[l] = 1 + ai[l];
        k++;
          goto _L3
        int i2 = 0xff & abyte0[l1 + 1];
        if((k1 << 2) - j1 - i2 >> 1 < i1)
            bitarray.set(l1);
        j1 = k1;
        k1 = i2;
        l1++;
          goto _L4
    }

    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private int buckets[];
    private byte luminances[];
}
