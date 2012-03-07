// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.NotFoundException;

// Referenced classes of package com.google.zxing.common:
//            GridSampler, PerspectiveTransform, BitMatrix

public final class DefaultGridSampler extends GridSampler
{

    public DefaultGridSampler()
    {
    }

    @Override
	public BitMatrix sampleGrid(BitMatrix bitmatrix, int i, float f, float f1, float f2, float f3, float f4, 
            float f5, float f6, float f7, float f8, float f9, float f10, float f11, 
            float f12, float f13, float f14, float f15)
        throws NotFoundException
    {
        return sampleGrid(bitmatrix, i, PerspectiveTransform.quadrilateralToQuadrilateral(f, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15));
    }

    @Override
	public BitMatrix sampleGrid(BitMatrix bitmatrix, int i, PerspectiveTransform perspectivetransform)
        throws NotFoundException
    {
        BitMatrix bitmatrix1;
        float af[];
        int j;
        bitmatrix1 = new BitMatrix(i);
        af = new float[i << 1];
        j = 0;
_L7:
        int k;
        float f;
        int l;
        if(j >= i)
            return bitmatrix1;
        k = af.length;
        f = 0.5F + j;
        l = 0;
_L4:
        if(l < k) goto _L2; else goto _L1
_L1:
        int i1;
        perspectivetransform.transformPoints(af);
        checkAndNudgePoints(bitmatrix, af);
        i1 = 0;
_L5:
        if(i1 >= k)
        {
            j++;
            continue; /* Loop/switch isn't completed */
        }
          goto _L3
_L2:
        af[l] = 0.5F + (l >> 1);
        af[l + 1] = f;
        l += 2;
          goto _L4
_L3:
        if(bitmatrix.get((int)af[i1], (int)af[i1 + 1]))
            bitmatrix1.set(i1 >> 1, j);
        i1 += 2;
          goto _L5
        ArrayIndexOutOfBoundsException arrayindexoutofboundsexception;
        arrayindexoutofboundsexception;
        throw NotFoundException.getNotFoundInstance();
        if(true) goto _L7; else goto _L6
_L6:
    }
}
