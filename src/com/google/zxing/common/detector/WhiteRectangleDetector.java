// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector
{

    public WhiteRectangleDetector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
        height = bitmatrix.getHeight();
        width = bitmatrix.getWidth();
    }

    private ResultPoint[] centerEdges(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
    {
        float f = resultpoint.getX();
        float f1 = resultpoint.getY();
        float f2 = resultpoint1.getX();
        float f3 = resultpoint1.getY();
        float f4 = resultpoint2.getX();
        float f5 = resultpoint2.getY();
        float f6 = resultpoint3.getX();
        float f7 = resultpoint3.getY();
        ResultPoint aresultpoint[];
        if(f < (float)(width / 2))
        {
            aresultpoint = new ResultPoint[4];
            aresultpoint[0] = new ResultPoint(f6 - 1.0F, 1.0F + f7);
            aresultpoint[1] = new ResultPoint(1.0F + f2, 1.0F + f3);
            aresultpoint[2] = new ResultPoint(f4 - 1.0F, f5 - 1.0F);
            aresultpoint[3] = new ResultPoint(1.0F + f, f1 - 1.0F);
        } else
        {
            aresultpoint = new ResultPoint[4];
            aresultpoint[0] = new ResultPoint(1.0F + f6, 1.0F + f7);
            aresultpoint[1] = new ResultPoint(1.0F + f2, f3 - 1.0F);
            aresultpoint[2] = new ResultPoint(f4 - 1.0F, 1.0F + f5);
            aresultpoint[3] = new ResultPoint(f - 1.0F, f1 - 1.0F);
        }
        return aresultpoint;
    }

    private boolean containsBlackPoint(int i, int j, int k, boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        int i1 = i;
_L6:
        if(i1 <= j) goto _L4; else goto _L3
_L3:
        boolean flag1 = false;
_L5:
        return flag1;
_L4:
label0:
        {
            if(!image.get(i1, k))
                break label0;
            flag1 = true;
        }
          goto _L5
        i1++;
          goto _L6
_L2:
        int l = i;
_L8:
        if(l > j) goto _L3; else goto _L7
_L7:
label1:
        {
            if(!image.get(k, l))
                break label1;
            flag1 = true;
        }
          goto _L5
        l++;
          goto _L8
    }

    private static int distanceL2(float f, float f1, float f2, float f3)
    {
        float f4 = f - f2;
        float f5 = f1 - f3;
        return round((float)Math.sqrt(f4 * f4 + f5 * f5));
    }

    private ResultPoint getBlackPointOnSegment(float f, float f1, float f2, float f3)
    {
        int i;
        float f4;
        float f5;
        int j;
        i = distanceL2(f, f1, f2, f3);
        f4 = (f2 - f) / (float)i;
        f5 = (f3 - f1) / (float)i;
        j = 0;
_L6:
        if(j < i) goto _L2; else goto _L1
_L1:
        ResultPoint resultpoint = null;
_L4:
        return resultpoint;
_L2:
        int k = round(f + f4 * (float)j);
        int l = round(f1 + f5 * (float)j);
        if(!image.get(k, l))
            break; /* Loop/switch isn't completed */
        resultpoint = new ResultPoint(k, l);
        if(true) goto _L4; else goto _L3
_L3:
        j++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static int round(float f)
    {
        return (int)(0.5F + f);
    }

    public ResultPoint[] detect()
        throws NotFoundException
    {
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        boolean flag1;
        boolean flag2;
        i = width - 40 >> 1;
        j = 40 + width >> 1;
        k = height - 40 >> 1;
        l = 40 + height >> 1;
        flag = false;
        flag1 = true;
        flag2 = false;
_L28:
        if(flag1) goto _L2; else goto _L1
_L1:
        if(flag || !flag2) goto _L4; else goto _L3
_L3:
        int k1;
        ResultPoint resultpoint;
        int l1;
        k1 = j - i;
        resultpoint = null;
        l1 = 1;
_L13:
        if(l1 < k1) goto _L6; else goto _L5
_L5:
        ResultPoint resultpoint1;
        int i2;
        boolean flag3;
        int i1;
        boolean flag4;
        int j1;
        boolean flag5;
        boolean flag6;
        int l2;
        int i3;
        if(resultpoint == null)
            throw NotFoundException.getNotFoundInstance();
        resultpoint1 = null;
        i2 = 1;
          goto _L7
_L2:
        flag1 = false;
        flag3 = true;
_L8:
label0:
        {
            if(flag3)
            {
                i3 = width;
                if(j < i3)
                    break label0;
            }
            i1 = width;
            if(j < i1)
                break MISSING_BLOCK_LABEL_158;
            flag = true;
        }
          goto _L1
        flag3 = containsBlackPoint(k, l, j, false);
        if(flag3)
        {
            j++;
            flag1 = true;
        }
          goto _L8
        flag4 = true;
_L9:
label1:
        {
            if(flag4)
            {
                l2 = height;
                if(l < l2)
                    break label1;
            }
            j1 = height;
            if(l < j1)
                break MISSING_BLOCK_LABEL_223;
            flag = true;
        }
          goto _L1
        flag4 = containsBlackPoint(i, j, l, true);
        if(flag4)
        {
            l++;
            flag1 = true;
        }
          goto _L9
        flag5 = true;
_L10:
label2:
        {
            if(flag5 && i >= 0)
                break label2;
            if(i >= 0)
                break MISSING_BLOCK_LABEL_270;
            flag = true;
        }
          goto _L1
        flag5 = containsBlackPoint(k, l, i, false);
        if(flag5)
        {
            i--;
            flag1 = true;
        }
          goto _L10
        flag6 = true;
_L11:
label3:
        {
            if(flag6 && k >= 0)
                break label3;
            if(k < 0)
            {
                flag = true;
            } else
            {
                if(flag1)
                    flag2 = true;
                continue; /* Loop/switch isn't completed */
            }
        }
          goto _L1
        flag6 = containsBlackPoint(i, j, k, true);
        if(flag6)
        {
            k--;
            flag1 = true;
        }
          goto _L11
_L6:
        if((resultpoint = getBlackPointOnSegment(i, l - l1, i + l1, l)) != null) goto _L5; else goto _L12
_L12:
        l1++;
          goto _L13
_L7:
        if(i2 < k1) goto _L15; else goto _L14
_L14:
        if(resultpoint1 == null)
            throw NotFoundException.getNotFoundInstance();
        break; /* Loop/switch isn't completed */
_L15:
        if((resultpoint1 = getBlackPointOnSegment(i, k + i2, i + i2, k)) != null) goto _L14; else goto _L16
_L16:
        i2++;
        if(true) goto _L7; else goto _L17
_L17:
        ResultPoint resultpoint2;
        int j2;
        resultpoint2 = null;
        j2 = 1;
_L22:
        if(j2 < k1) goto _L19; else goto _L18
_L18:
        if(resultpoint2 == null)
            throw NotFoundException.getNotFoundInstance();
        break; /* Loop/switch isn't completed */
_L19:
        if((resultpoint2 = getBlackPointOnSegment(j, k + j2, j - j2, k)) != null) goto _L18; else goto _L20
_L20:
        j2++;
        if(true) goto _L22; else goto _L21
_L21:
        ResultPoint resultpoint3;
        int k2;
        resultpoint3 = null;
        k2 = 1;
_L26:
        if(k2 < k1) goto _L24; else goto _L23
_L23:
        if(resultpoint3 == null)
            throw NotFoundException.getNotFoundInstance();
        else
            return centerEdges(resultpoint3, resultpoint, resultpoint2, resultpoint1);
_L24:
        if((resultpoint3 = getBlackPointOnSegment(j, l - k2, j - k2, l)) != null) goto _L23; else goto _L25
_L25:
        k2++;
        if(true) goto _L26; else goto _L4
_L4:
        throw NotFoundException.getNotFoundInstance();
        if(true) goto _L28; else goto _L27
_L27:
    }

    private static final int CORR = 1;
    private static final int INIT_SIZE = 40;
    private final int height;
    private final BitMatrix image;
    private final int width;
}
