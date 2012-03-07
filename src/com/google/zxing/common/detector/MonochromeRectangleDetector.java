// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector
{

    public MonochromeRectangleDetector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
    }

    private int[] blackWhiteRange(int i, int j, int k, int l, boolean flag)
    {
        int i1;
        int j1;
        i1 = k + l >> 1;
        j1 = i1;
_L5:
        if(j1 >= k) goto _L2; else goto _L1
_L1:
        int i2;
        int j2;
        i2 = j1 + 1;
        j2 = i1;
_L7:
        if(j2 < l) goto _L4; else goto _L3
_L3:
        int i3 = j2 - 1;
        int k1;
        int l1;
        int k2;
        int l2;
        int ai[];
        if(i3 > i2)
        {
            ai = new int[2];
            ai[0] = i2;
            ai[1] = i3;
        } else
        {
            ai = null;
        }
        return ai;
_L2:
        if(flag ? image.get(j1, i) : image.get(i, j1))
            j1--;
        else
            break MISSING_BLOCK_LABEL_105;
          goto _L5
        k1 = j1;
        while(--j1 >= k && (flag ? !image.get(j1, i) : !image.get(i, j1))) ;
        l1 = k1 - j1;
        if(j1 >= k && l1 <= j) goto _L5; else goto _L6
_L6:
        j1 = k1;
          goto _L1
_L4:
        if(flag ? image.get(j2, i) : image.get(i, j2))
            j2++;
        else
            break MISSING_BLOCK_LABEL_215;
          goto _L7
        k2 = j2;
        while(++j2 < l && (flag ? !image.get(j2, i) : !image.get(i, j2))) ;
        l2 = j2 - k2;
        if(j2 < l && l2 <= j) goto _L7; else goto _L8
_L8:
        j2 = k2;
          goto _L3
    }

    private ResultPoint findCornerFromCenter(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2)
        throws NotFoundException
    {
        int ai[] = null;
        int j2 = i1;
        int k2 = i;
        do
        {
            if(j2 >= l1 || j2 < k1 || k2 >= l || k2 < k)
                throw NotFoundException.getNotFoundInstance();
            int ai1[];
            if(j == 0)
                ai1 = blackWhiteRange(j2, i2, k, l, true);
            else
                ai1 = blackWhiteRange(k2, i2, k1, l1, false);
            if(ai1 == null)
            {
                if(ai == null)
                    throw NotFoundException.getNotFoundInstance();
                ResultPoint resultpoint;
                if(j == 0)
                {
                    int j3 = j2 - j1;
                    if(ai[0] < i)
                    {
                        if(ai[1] > i)
                        {
                            int k3;
                            if(j1 > 0)
                                k3 = ai[0];
                            else
                                k3 = ai[1];
                            resultpoint = new ResultPoint(k3, j3);
                        } else
                        {
                            resultpoint = new ResultPoint(ai[0], j3);
                        }
                    } else
                    {
                        resultpoint = new ResultPoint(ai[1], j3);
                    }
                } else
                {
                    int l2 = k2 - j;
                    if(ai[0] < i1)
                    {
                        if(ai[1] > i1)
                        {
                            float f = l2;
                            int i3;
                            if(j < 0)
                                i3 = ai[0];
                            else
                                i3 = ai[1];
                            resultpoint = new ResultPoint(f, i3);
                        } else
                        {
                            resultpoint = new ResultPoint(l2, ai[0]);
                        }
                    } else
                    {
                        resultpoint = new ResultPoint(l2, ai[1]);
                    }
                }
                return resultpoint;
            }
            ai = ai1;
            j2 += j1;
            k2 += j;
        } while(true);
    }

    public ResultPoint[] detect()
        throws NotFoundException
    {
        int i = image.getHeight();
        int j = image.getWidth();
        int k = i >> 1;
        int l = j >> 1;
        int i1 = Math.max(1, i / 256);
        int j1 = Math.max(1, j / 256);
        int k1 = (int)findCornerFromCenter(l, 0, 0, j, k, -i1, 0, i, l >> 1).getY() - 1;
        ResultPoint resultpoint = findCornerFromCenter(l, -j1, 0, j, k, 0, k1, i, k >> 1);
        int l1 = (int)resultpoint.getX() - 1;
        ResultPoint resultpoint1 = findCornerFromCenter(l, j1, l1, j, k, 0, k1, i, k >> 1);
        int i2 = 1 + (int)resultpoint1.getX();
        ResultPoint resultpoint2 = findCornerFromCenter(l, 0, l1, i2, k, i1, k1, i, l >> 1);
        int j2 = 1 + (int)resultpoint2.getY();
        ResultPoint resultpoint3 = findCornerFromCenter(l, 0, l1, i2, k, -i1, k1, j2, l >> 2);
        ResultPoint aresultpoint[] = new ResultPoint[4];
        aresultpoint[0] = resultpoint3;
        aresultpoint[1] = resultpoint;
        aresultpoint[2] = resultpoint1;
        aresultpoint[3] = resultpoint2;
        return aresultpoint;
    }

    private static final int MAX_MODULES = 32;
    private final BitMatrix image;
}
