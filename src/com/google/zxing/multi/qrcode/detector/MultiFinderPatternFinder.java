// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.detector.*;
import java.util.Hashtable;
import java.util.Vector;

final class MultiFinderPatternFinder extends FinderPatternFinder
{
    private static class ModuleSizeComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            float f = ((FinderPattern)obj1).getEstimatedModuleSize() - ((FinderPattern)obj).getEstimatedModuleSize();
            byte byte0;
            if((double)f < 0.0D)
                byte0 = -1;
            else
            if((double)f > 0.0D)
                byte0 = 1;
            else
                byte0 = 0;
            return byte0;
        }

        private ModuleSizeComparator()
        {
        }

        ModuleSizeComparator(ModuleSizeComparator modulesizecomparator)
        {
            this();
        }
    }


    MultiFinderPatternFinder(BitMatrix bitmatrix)
    {
        super(bitmatrix);
    }

    MultiFinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
    {
        super(bitmatrix, resultpointcallback);
    }

    private FinderPattern[][] selectBestPatterns()
        throws NotFoundException
    {
        Vector vector;
        int i;
        vector = getPossibleCenters();
        i = vector.size();
        if(i < 3)
            throw NotFoundException.getNotFoundInstance();
        if(i != 3) goto _L2; else goto _L1
_L1:
        FinderPattern afinderpattern2[][];
        afinderpattern2 = new FinderPattern[1][];
        FinderPattern afinderpattern3[] = new FinderPattern[3];
        afinderpattern3[0] = (FinderPattern)vector.elementAt(0);
        afinderpattern3[1] = (FinderPattern)vector.elementAt(1);
        afinderpattern3[2] = (FinderPattern)vector.elementAt(2);
        afinderpattern2[0] = afinderpattern3;
_L20:
        return afinderpattern2;
_L2:
        Vector vector1;
        int j;
        Collections.insertionSort(vector, new ModuleSizeComparator(null));
        vector1 = new Vector();
        j = 0;
_L7:
        int k = i - 2;
        if(j < k) goto _L4; else goto _L3
_L3:
        FinderPattern afinderpattern1[][];
        int k1;
        if(vector1.isEmpty())
            break MISSING_BLOCK_LABEL_568;
        afinderpattern1 = new FinderPattern[vector1.size()][];
        k1 = 0;
_L18:
        int l1 = vector1.size();
        if(k1 >= l1)
        {
            afinderpattern2 = afinderpattern1;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_548;
_L4:
        FinderPattern finderpattern = (FinderPattern)vector.elementAt(j);
        if(finderpattern != null) goto _L6; else goto _L5
_L5:
        j++;
          goto _L7
_L6:
        int l = j + 1;
_L11:
        int i1 = i - 1;
        if(l >= i1) goto _L5; else goto _L8
_L8:
        FinderPattern finderpattern1 = (FinderPattern)vector.elementAt(l);
        if(finderpattern1 != null) goto _L10; else goto _L9
_L9:
        l++;
          goto _L11
_L10:
        float f = (finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) / Math.min(finderpattern.getEstimatedModuleSize(), finderpattern1.getEstimatedModuleSize());
        if(Math.abs(finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) > 0.5F && f >= 0.05F) goto _L5; else goto _L12
_L12:
        int j1 = l + 1;
_L16:
        if(j1 >= i) goto _L9; else goto _L13
_L13:
        FinderPattern finderpattern2 = (FinderPattern)vector.elementAt(j1);
        if(finderpattern2 != null) goto _L15; else goto _L14
_L14:
        j1++;
          goto _L16
_L15:
        float f1 = (finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) / Math.min(finderpattern1.getEstimatedModuleSize(), finderpattern2.getEstimatedModuleSize());
        if(Math.abs(finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) > 0.5F && f1 >= 0.05F) goto _L9; else goto _L17
_L17:
        FinderPattern afinderpattern[] = new FinderPattern[3];
        afinderpattern[0] = finderpattern;
        afinderpattern[1] = finderpattern1;
        afinderpattern[2] = finderpattern2;
        ResultPoint.orderBestPatterns(afinderpattern);
        FinderPatternInfo finderpatterninfo = new FinderPatternInfo(afinderpattern);
        float f2 = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getBottomLeft());
        float f3 = ResultPoint.distance(finderpatterninfo.getTopRight(), finderpatterninfo.getBottomLeft());
        float f4 = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getTopRight());
        float f5 = (f2 + f4) / finderpattern.getEstimatedModuleSize() / 2.0F;
        if(f5 <= 180F && f5 >= 9F && Math.abs((f2 - f4) / Math.min(f2, f4)) < 0.1F)
        {
            float f6 = (float)Math.sqrt(f2 * f2 + f4 * f4);
            if(Math.abs((f3 - f6) / Math.min(f3, f6)) < 0.1F)
                vector1.addElement(afinderpattern);
        }
          goto _L14
        afinderpattern1[k1] = (FinderPattern[])vector1.elementAt(k1);
        k1++;
          goto _L18
        throw NotFoundException.getNotFoundInstance();
        if(true) goto _L20; else goto _L19
_L19:
    }

    public FinderPatternInfo[] findMulti(Hashtable hashtable)
        throws NotFoundException
    {
        BitMatrix bitmatrix;
        int j;
        int k;
        int ai[];
        int l;
        FinderPattern afinderpattern[][];
        Vector vector;
        int k1;
        FinderPatternInfo afinderpatterninfo1[];
        boolean flag;
        int i;
        int l1;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        bitmatrix = getImage();
        i = bitmatrix.getHeight();
        j = bitmatrix.getWidth();
        k = (int)(3F * ((float)i / 228F));
        if(k < 3 || flag)
            k = 3;
        ai = new int[5];
        l = k - 1;
_L7:
        if(l < i) goto _L2; else goto _L1
_L1:
        afinderpattern = selectBestPatterns();
        vector = new Vector();
        k1 = 0;
_L9:
        l1 = afinderpattern.length;
        if(k1 < l1) goto _L4; else goto _L3
_L3:
        if(!vector.isEmpty()) goto _L6; else goto _L5
_L5:
        afinderpatterninfo1 = EMPTY_RESULT_ARRAY;
_L10:
        return afinderpatterninfo1;
_L2:
        int i1;
        int j1;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        i1 = 0;
        j1 = 0;
_L8:
label0:
        {
            if(j1 < j)
                break label0;
            if(foundPatternCross(ai))
                handlePossibleCenter(ai, l, j);
            l += k;
        }
          goto _L7
        if(bitmatrix.get(j1, l))
        {
            if((i1 & 1) == 1)
                i1++;
            ai[i1] = 1 + ai[i1];
        } else
        if((i1 & 1) == 0)
        {
            if(i1 == 4)
            {
                if(foundPatternCross(ai))
                {
                    if(!handlePossibleCenter(ai, l, j1))
                    {
                        while(++j1 < j && !bitmatrix.get(j1, l)) ;
                        j1--;
                    }
                    i1 = 0;
                    ai[0] = 0;
                    ai[1] = 0;
                    ai[2] = 0;
                    ai[3] = 0;
                    ai[4] = 0;
                } else
                {
                    ai[0] = ai[2];
                    ai[1] = ai[3];
                    ai[2] = ai[4];
                    ai[3] = 1;
                    ai[4] = 0;
                    i1 = 3;
                }
            } else
            {
                i1++;
                ai[i1] = 1 + ai[i1];
            }
        } else
        {
            ai[i1] = 1 + ai[i1];
        }
        j1++;
          goto _L8
_L4:
        FinderPattern afinderpattern1[] = afinderpattern[k1];
        ResultPoint.orderBestPatterns(afinderpattern1);
        FinderPatternInfo finderpatterninfo = new FinderPatternInfo(afinderpattern1);
        vector.addElement(finderpatterninfo);
        k1++;
          goto _L9
_L6:
        FinderPatternInfo afinderpatterninfo[];
        int i2;
        afinderpatterninfo = new FinderPatternInfo[vector.size()];
        i2 = 0;
_L11:
label1:
        {
            int j2 = vector.size();
            if(i2 < j2)
                break label1;
            afinderpatterninfo1 = afinderpatterninfo;
        }
          goto _L10
        afinderpatterninfo[i2] = (FinderPatternInfo)vector.elementAt(i2);
        i2++;
          goto _L11
    }

    private static final float DIFF_MODSIZE_CUTOFF = 0.5F;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05F;
    private static final FinderPatternInfo EMPTY_RESULT_ARRAY[] = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180F;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9F;

}
