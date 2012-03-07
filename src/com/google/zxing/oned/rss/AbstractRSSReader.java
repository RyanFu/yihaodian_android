// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader
{

    protected AbstractRSSReader()
    {
        oddCounts = new int[dataCharacterCounters.length / 2];
        evenCounts = new int[dataCharacterCounters.length / 2];
    }

    protected static int count(int ai[])
    {
        int i = 0;
        int j = 0;
        do
        {
            if(j >= ai.length)
                return i;
            i += ai[j];
            j++;
        } while(true);
    }

    protected static void decrement(int ai[], float af[])
    {
        int i = 0;
        float f = af[0];
        int j = 1;
        do
        {
            if(j >= ai.length)
            {
                ai[i] = ai[i] - 1;
                return;
            }
            if(af[j] < f)
            {
                f = af[j];
                i = j;
            }
            j++;
        } while(true);
    }

    protected static void increment(int ai[], float af[])
    {
        int i = 0;
        float f = af[0];
        int j = 1;
        do
        {
            if(j >= ai.length)
            {
                ai[i] = 1 + ai[i];
                return;
            }
            if(af[j] > f)
            {
                f = af[j];
                i = j;
            }
            j++;
        } while(true);
    }

    protected static boolean isFinderPattern(int ai[])
    {
        int k;
        int l;
        int i1;
        int i = ai[0] + ai[1];
        int j = i + ai[2] + ai[3];
        float f = (float)i / (float)j;
        if(f < 0.7916667F || f > 0.8928571F)
            break MISSING_BLOCK_LABEL_112;
        k = 0x7fffffff;
        l = 0x80000000;
        i1 = 0;
_L3:
        if(i1 < ai.length) goto _L2; else goto _L1
_L1:
        boolean flag;
        if(l < k * 10)
            flag = true;
        else
            flag = false;
_L4:
        return flag;
_L2:
        int j1 = ai[i1];
        if(j1 > l)
            l = j1;
        if(j1 < k)
            k = j1;
        i1++;
          goto _L3
        flag = false;
          goto _L4
    }

    protected static int parseFinderValue(int ai[], int ai1[][])
        throws NotFoundException
    {
        int i = 0;
        do
        {
            if(i >= ai1.length)
                throw NotFoundException.getNotFoundInstance();
            if(patternMatchVariance(ai, ai1[i], 102) < 51)
                return i;
            i++;
        } while(true);
    }

    private static final int MAX_AVG_VARIANCE = 51;
    private static final float MAX_FINDER_PATTERN_RATIO = 0.8928571F;
    private static final int MAX_INDIVIDUAL_VARIANCE = 102;
    private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667F;
    protected final int dataCharacterCounters[] = new int[8];
    protected final int decodeFinderCounters[] = new int[4];
    protected final int evenCounts[];
    protected final float evenRoundingErrors[] = new float[4];
    protected final int oddCounts[];
    protected final float oddRoundingErrors[] = new float[4];
}
