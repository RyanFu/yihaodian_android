// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.Vector;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            AlignmentPattern

final class AlignmentPatternFinder
{

    AlignmentPatternFinder(BitMatrix bitmatrix, int i, int j, int k, int l, float f, ResultPointCallback resultpointcallback)
    {
        image = bitmatrix;
        startX = i;
        startY = j;
        width = k;
        height = l;
        moduleSize = f;
        resultPointCallback = resultpointcallback;
    }

    private static float centerFromEnd(int ai[], int i)
    {
        return (float)(i - ai[2]) - (float)ai[1] / 2.0F;
    }

    private float crossCheckVertical(int i, int j, int k, int l)
    {
        BitMatrix bitmatrix;
        int i1;
        int ai[];
        int j1;
        bitmatrix = image;
        i1 = bitmatrix.getHeight();
        ai = crossCheckStateCount;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        j1 = i;
_L3:
        if(j1 >= 0 && bitmatrix.get(j, j1) && ai[1] <= k) goto _L2; else goto _L1
_L1:
        float f;
        if(j1 < 0 || ai[1] > k)
        {
            f = (0.0F / 0.0F);
        } else
        {
label0:
            {
                for(; j1 >= 0 && !bitmatrix.get(j, j1) && ai[0] <= k; j1--)
                    ai[0] = 1 + ai[0];

                if(ai[0] <= k)
                    break label0;
                f = (0.0F / 0.0F);
            }
        }
_L4:
        return f;
_L2:
        ai[1] = 1 + ai[1];
        j1--;
          goto _L3
        int k1 = i + 1;
_L5:
label1:
        {
            if(k1 < i1 && bitmatrix.get(j, k1) && ai[1] <= k)
                break label1;
            if(k1 == i1 || ai[1] > k)
            {
                f = (0.0F / 0.0F);
            } else
            {
                for(; k1 < i1 && !bitmatrix.get(j, k1) && ai[2] <= k; k1++)
                    ai[2] = 1 + ai[2];

                if(ai[2] > k)
                    f = (0.0F / 0.0F);
                else
                if(5 * Math.abs((ai[0] + ai[1] + ai[2]) - l) >= l * 2)
                    f = (0.0F / 0.0F);
                else
                if(foundPatternCross(ai))
                    f = centerFromEnd(ai, k1);
                else
                    f = (0.0F / 0.0F);
            }
        }
          goto _L4
        ai[1] = 1 + ai[1];
        k1++;
          goto _L5
    }

    private boolean foundPatternCross(int ai[])
    {
        float f;
        float f1;
        int i;
        f = moduleSize;
        f1 = f / 2.0F;
        i = 0;
_L6:
        if(i < 3) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        if(Math.abs(f - (float)ai[i]) < f1)
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private AlignmentPattern handlePossibleCenter(int ai[], int i, int j)
    {
        float f;
        float f1;
        int k = ai[0] + ai[1] + ai[2];
        f = centerFromEnd(ai, j);
        f1 = crossCheckVertical(i, (int)f, 2 * ai[1], k);
        if(Float.isNaN(f1)) goto _L2; else goto _L1
_L1:
        float f2;
        int l;
        int i1;
        f2 = (float)(ai[0] + ai[1] + ai[2]) / 3F;
        l = possibleCenters.size();
        i1 = 0;
_L8:
        if(i1 < l) goto _L4; else goto _L3
_L3:
        AlignmentPattern alignmentpattern1 = new AlignmentPattern(f, f1, f2);
        possibleCenters.addElement(alignmentpattern1);
        if(resultPointCallback != null)
            resultPointCallback.foundPossibleResultPoint(alignmentpattern1);
_L2:
        AlignmentPattern alignmentpattern = null;
_L6:
        return alignmentpattern;
_L4:
        if(!((AlignmentPattern)possibleCenters.elementAt(i1)).aboutEquals(f2, f1, f))
            break; /* Loop/switch isn't completed */
        alignmentpattern = new AlignmentPattern(f, f1, f2);
        if(true) goto _L6; else goto _L5
_L5:
        i1++;
        if(true) goto _L8; else goto _L7
_L7:
    }

    AlignmentPattern find()
        throws NotFoundException
    {
        int i;
        int j;
        int k;
        int l;
        int ai[];
        int i1;
        i = startX;
        j = height;
        k = i + width;
        l = startY + (j >> 1);
        ai = new int[3];
        i1 = 0;
_L14:
        if(i1 < j) goto _L2; else goto _L1
_L1:
        AlignmentPattern alignmentpattern1;
        if(possibleCenters.isEmpty())
            break; /* Loop/switch isn't completed */
        alignmentpattern1 = (AlignmentPattern)possibleCenters.elementAt(0);
_L7:
        return alignmentpattern1;
_L2:
        int k1;
        int l1;
        int i2;
        int j1;
        AlignmentPattern alignmentpattern2;
        if((i1 & 1) == 0)
            j1 = i1 + 1 >> 1;
        else
            j1 = -(i1 + 1 >> 1);
        k1 = l + j1;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        l1 = i;
_L8:
        if(l1 < k && !image.get(l1, k1)) goto _L4; else goto _L3
_L3:
        i2 = 0;
_L11:
        if(l1 < k) goto _L6; else goto _L5
_L5:
        if(!foundPatternCross(ai))
            break MISSING_BLOCK_LABEL_329;
        alignmentpattern2 = handlePossibleCenter(ai, k1, k);
        if(alignmentpattern2 == null)
            break MISSING_BLOCK_LABEL_329;
        alignmentpattern1 = alignmentpattern2;
          goto _L7
_L4:
        l1++;
          goto _L8
_L6:
        if(!image.get(l1, k1))
            break MISSING_BLOCK_LABEL_305;
        if(i2 != 1) goto _L10; else goto _L9
_L9:
        ai[i2] = 1 + ai[i2];
_L12:
        l1++;
          goto _L11
_L10:
label0:
        {
            if(i2 != 2)
                break MISSING_BLOCK_LABEL_287;
            if(!foundPatternCross(ai))
                break label0;
            AlignmentPattern alignmentpattern = handlePossibleCenter(ai, k1, l1);
            if(alignmentpattern == null)
                break label0;
            alignmentpattern1 = alignmentpattern;
        }
          goto _L7
        ai[0] = ai[2];
        ai[1] = 1;
        ai[2] = 0;
        i2 = 1;
          goto _L12
        i2++;
        ai[i2] = 1 + ai[i2];
          goto _L12
        if(i2 == 1)
            i2++;
        ai[i2] = 1 + ai[i2];
          goto _L12
        i1++;
        if(true) goto _L14; else goto _L13
_L13:
        throw NotFoundException.getNotFoundInstance();
    }

    private final int crossCheckStateCount[] = new int[3];
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final Vector possibleCenters = new Vector(5);
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;
}
