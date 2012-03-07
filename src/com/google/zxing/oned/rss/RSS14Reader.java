// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.oned.rss:
//            AbstractRSSReader, Pair, FinderPattern, RSSUtils, 
//            DataCharacter

public final class RSS14Reader extends AbstractRSSReader
{

    public RSS14Reader()
    {
    }

    private static void addOrTally(Vector vector, Pair pair)
    {
        if(pair != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Enumeration enumeration;
        boolean flag;
        enumeration = vector.elements();
        flag = false;
_L6:
        if(enumeration.hasMoreElements()) goto _L4; else goto _L3
_L3:
        if(!flag)
            vector.addElement(pair);
          goto _L1
_L4:
        Pair pair1 = (Pair)enumeration.nextElement();
        if(pair1.getValue() != pair.getValue()) goto _L6; else goto _L5
_L5:
        pair1.incrementCount();
        flag = true;
          goto _L3
    }

    private void adjustOddEvenCounts(boolean flag, int i)
        throws NotFoundException
    {
        int j;
        int k;
        int l;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        j = count(oddCounts);
        k = count(evenCounts);
        l = (j + k) - i;
        int i1 = j & 1;
        int j1;
        if(flag)
            j1 = 1;
        else
            j1 = 0;
        if(i1 == j1)
            flag1 = true;
        else
            flag1 = false;
        if((k & 1) == 1)
            flag2 = true;
        else
            flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        flag6 = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(j <= 12) goto _L4; else goto _L3
_L3:
        flag4 = true;
_L9:
        if(k > 12)
            flag6 = true;
        else
        if(k < 4)
            flag5 = true;
_L6:
        if(l == 1)
        {
            if(flag1)
            {
                if(flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag4 = true;
            } else
            {
                if(!flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag6 = true;
            }
        } else
        if(l == -1)
        {
            if(flag1)
            {
                if(flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag3 = true;
            } else
            {
                if(!flag2)
                    throw NotFoundException.getNotFoundInstance();
                flag5 = true;
            }
        } else
        if(l == 0)
        {
            if(!flag1)
                continue; /* Loop/switch isn't completed */
            if(!flag2)
                throw NotFoundException.getNotFoundInstance();
            if(j < k)
            {
                flag3 = true;
                flag6 = true;
            } else
            {
                flag4 = true;
                flag5 = true;
            }
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
          goto _L5
_L4:
        if(j < 4)
            flag3 = true;
        continue; /* Loop/switch isn't completed */
_L2:
        if(j > 11)
            flag4 = true;
        else
        if(j < 5)
            flag3 = true;
        if(k > 10)
            flag6 = true;
        else
        if(k < 4)
            flag5 = true;
          goto _L6
        if(!flag2) goto _L5; else goto _L7
_L7:
        throw NotFoundException.getNotFoundInstance();
_L5:
        if(flag3)
        {
            if(flag4)
                throw NotFoundException.getNotFoundInstance();
            increment(oddCounts, oddRoundingErrors);
        }
        if(flag4)
            decrement(oddCounts, oddRoundingErrors);
        if(flag5)
        {
            if(flag6)
                throw NotFoundException.getNotFoundInstance();
            increment(evenCounts, oddRoundingErrors);
        }
        if(flag6)
            decrement(evenCounts, evenRoundingErrors);
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static boolean checkChecksum(Pair pair, Pair pair1)
    {
        int i = pair.getFinderPattern().getValue();
        int j = pair1.getFinderPattern().getValue();
        if(i != 0 || j != 8)
            if(i != 8);
        int k = (pair.getChecksumPortion() + 16 * pair1.getChecksumPortion()) % 79;
        int l = 9 * pair.getFinderPattern().getValue() + pair1.getFinderPattern().getValue();
        if(l > 72)
            l--;
        if(l > 8)
            l--;
        boolean flag;
        if(k == l)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static Result constructResult(Pair pair, Pair pair1)
    {
        String s;
        StringBuffer stringbuffer;
        int i;
        s = String.valueOf(0x453af5L * (long)pair.getValue() + (long)pair1.getValue());
        stringbuffer = new StringBuffer(14);
        i = 13 - s.length();
_L3:
        if(i > 0) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        stringbuffer.append(s);
        j = 0;
        k = 0;
_L4:
        if(k >= 13)
        {
            int i1 = 10 - j % 10;
            if(i1 == 10)
                i1 = 0;
            stringbuffer.append(i1);
            ResultPoint aresultpoint[] = pair.getFinderPattern().getResultPoints();
            ResultPoint aresultpoint1[] = pair1.getFinderPattern().getResultPoints();
            String s1 = String.valueOf(stringbuffer.toString());
            ResultPoint aresultpoint2[] = new ResultPoint[4];
            aresultpoint2[0] = aresultpoint[0];
            aresultpoint2[1] = aresultpoint[1];
            aresultpoint2[2] = aresultpoint1[0];
            aresultpoint2[3] = aresultpoint1[1];
            return new Result(s1, null, aresultpoint2, BarcodeFormat.RSS14);
        }
        break MISSING_BLOCK_LABEL_183;
_L2:
        stringbuffer.append('0');
        i--;
          goto _L3
        int l = stringbuffer.charAt(k) - 48;
        if((k & 1) == 0)
            l *= 3;
        j += l;
        k++;
          goto _L4
    }

    private DataCharacter decodeDataCharacter(BitArray bitarray, FinderPattern finderpattern, boolean flag)
        throws NotFoundException
    {
        int ai[];
        float f;
        int ai1[];
        int ai2[];
        float af[];
        float af1[];
        int i1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        ai = dataCharacterCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        ai[5] = 0;
        ai[6] = 0;
        ai[7] = 0;
        int l;
        int j1;
        if(flag)
        {
            recordPatternInReverse(bitarray, finderpattern.getStartEnd()[0], ai);
        } else
        {
            recordPattern(bitarray, 1 + finderpattern.getStartEnd()[1], ai);
            int i = 0;
            int j = ai.length - 1;
            while(i < j) 
            {
                int k = ai[i];
                ai[i] = ai[j];
                ai[j] = k;
                i++;
                j--;
            }
        }
        if(flag)
            l = 16;
        else
            l = 15;
        f = (float)count(ai) / (float)l;
        ai1 = oddCounts;
        ai2 = evenCounts;
        af = oddRoundingErrors;
        af1 = evenRoundingErrors;
        i1 = 0;
_L14:
        j1 = ai.length;
        if(i1 < j1) goto _L2; else goto _L1
_L1:
        adjustOddEvenCounts(flag, l);
        i2 = 0;
        j2 = 0;
        k2 = ai1.length - 1;
_L9:
        if(k2 >= 0) goto _L4; else goto _L3
_L3:
        l2 = 0;
        i3 = 0;
        j3 = ai2.length - 1;
_L10:
        if(j3 >= 0) goto _L6; else goto _L5
_L5:
        k3 = j2 + l2 * 3;
        if(!flag) goto _L8; else goto _L7
_L2:
        float f1 = (float)ai[i1] / f;
        int k1 = (int)(0.5F + f1);
        int l1;
        if(k1 < 1)
            k1 = 1;
        else
        if(k1 > 8)
            k1 = 8;
        l1 = i1 >> 1;
        if((i1 & 1) == 0)
        {
            ai1[l1] = k1;
            af[l1] = f1 - (float)k1;
        } else
        {
            ai2[l1] = k1;
            af1[l1] = f1 - (float)k1;
        }
        i1++;
        continue; /* Loop/switch isn't completed */
_L4:
        j2 = j2 * 9 + ai1[k2];
        i2 += ai1[k2];
        k2--;
          goto _L9
_L6:
        l2 = l2 * 9 + ai2[j3];
        i3 += ai2[j3];
        j3--;
          goto _L10
_L7:
        DataCharacter datacharacter;
        if((i2 & 1) != 0 || i2 > 12 || i2 < 4)
            throw NotFoundException.getNotFoundInstance();
        int l5 = (12 - i2) / 2;
        int i6 = OUTSIDE_ODD_WIDEST[l5];
        int j6 = 9 - i6;
        int k6 = RSSUtils.getRSSvalue(ai1, i6, false);
        int l6 = RSSUtils.getRSSvalue(ai2, j6, true);
        int i7 = OUTSIDE_EVEN_TOTAL_SUBSET[l5];
        int j7 = OUTSIDE_GSUM[l5];
        int k7 = j7 + (l6 + k6 * i7);
        datacharacter = new DataCharacter(k7, k3);
_L12:
        return datacharacter;
_L8:
        if((i3 & 1) != 0 || i3 > 10 || i3 < 4)
            throw NotFoundException.getNotFoundInstance();
        int l3 = (10 - i3) / 2;
        int i4 = INSIDE_ODD_WIDEST[l3];
        int j4 = 9 - i4;
        int k4 = RSSUtils.getRSSvalue(ai1, i4, true);
        int l4 = RSSUtils.getRSSvalue(ai2, j4, false);
        int i5 = INSIDE_ODD_TOTAL_SUBSET[l3];
        int j5 = INSIDE_GSUM[l3];
        int k5 = j5 + (k4 + l4 * i5);
        datacharacter = new DataCharacter(k5, k3);
        if(true) goto _L12; else goto _L11
_L11:
        if(true) goto _L14; else goto _L13
_L13:
    }

    private Pair decodePair(BitArray bitarray, boolean flag, int i, Hashtable hashtable)
    {
        int ai[];
        FinderPattern finderpattern;
        ai = findFinderPattern(bitarray, 0, flag);
        finderpattern = parseFoundFinderPattern(bitarray, i, flag, ai);
        if(hashtable != null) goto _L2; else goto _L1
_L1:
        ResultPointCallback resultpointcallback1 = null;
_L3:
        Pair pair;
        if(resultpointcallback1 != null)
        {
            float f = (float)(ai[0] + ai[1]) / 2.0F;
            if(flag)
                f = (float)(bitarray.getSize() - 1) - f;
            resultpointcallback1.foundPossibleResultPoint(new ResultPoint(f, i));
        }
        DataCharacter datacharacter = decodeDataCharacter(bitarray, finderpattern, true);
        DataCharacter datacharacter1 = decodeDataCharacter(bitarray, finderpattern, false);
        pair = new Pair(1597 * datacharacter.getValue() + datacharacter1.getValue(), datacharacter.getChecksumPortion() + 4 * datacharacter1.getChecksumPortion(), finderpattern);
        break MISSING_BLOCK_LABEL_168;
_L2:
        ResultPointCallback resultpointcallback = (ResultPointCallback)hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        resultpointcallback1 = resultpointcallback;
          goto _L3
        NotFoundException notfoundexception;
        notfoundexception;
        pair = null;
        return pair;
    }

    private int[] findFinderPattern(BitArray bitarray, int i, boolean flag)
        throws NotFoundException
    {
        int ai[];
        int j;
        boolean flag1;
        ai = decodeFinderCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        j = bitarray.getSize();
        flag1 = false;
_L4:
        if(i < j) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        int i1;
        k = 0;
        l = i;
        i1 = i;
_L5:
        if(i1 >= j)
            throw NotFoundException.getNotFoundInstance();
        break MISSING_BLOCK_LABEL_90;
_L2:
        if(bitarray.get(i))
            flag1 = false;
        else
            flag1 = true;
        if(flag == flag1) goto _L1; else goto _L3
_L3:
        i++;
          goto _L4
        if(flag1 ^ bitarray.get(i1))
        {
            ai[k] = 1 + ai[k];
        } else
        {
            if(k == 3)
            {
                if(isFinderPattern(ai))
                {
                    int ai1[] = new int[2];
                    ai1[0] = l;
                    ai1[1] = i1;
                    return ai1;
                }
                l += ai[0] + ai[1];
                ai[0] = ai[2];
                ai[1] = ai[3];
                ai[2] = 0;
                ai[3] = 0;
                k--;
            } else
            {
                k++;
            }
            ai[k] = 1;
            if(flag1)
                flag1 = false;
            else
                flag1 = true;
        }
        i1++;
          goto _L5
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitarray, int i, boolean flag, int ai[])
        throws NotFoundException
    {
        boolean flag1;
        int j;
        flag1 = bitarray.get(ai[0]);
        j = ai[0] - 1;
_L3:
        if(j >= 0 && flag1 ^ bitarray.get(j)) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        int ai1[];
        int i1;
        k = j + 1;
        l = ai[0] - k;
        ai1 = decodeFinderCounters;
        i1 = ai1.length - 1;
_L4:
        if(i1 <= 0)
        {
            ai1[0] = l;
            int j1 = parseFinderValue(ai1, FINDER_PATTERNS);
            int k1 = k;
            int l1 = ai[1];
            if(flag)
            {
                k1 = bitarray.getSize() - 1 - k1;
                l1 = bitarray.getSize() - 1 - l1;
            }
            int ai2[] = new int[2];
            ai2[0] = k;
            ai2[1] = ai[1];
            return new FinderPattern(j1, ai2, k1, l1, i);
        }
        break MISSING_BLOCK_LABEL_162;
_L2:
        j--;
          goto _L3
        ai1[i1] = ai1[i1 - 1];
        i1--;
          goto _L4
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        int j;
        int k;
        int l;
        Pair pair = decodePair(bitarray, false, i, hashtable);
        addOrTally(possibleLeftPairs, pair);
        bitarray.reverse();
        Pair pair1 = decodePair(bitarray, true, i, hashtable);
        addOrTally(possibleRightPairs, pair1);
        bitarray.reverse();
        j = possibleLeftPairs.size();
        k = possibleRightPairs.size();
        l = 0;
_L3:
        Pair pair2;
        if(l >= j)
            throw NotFoundException.getNotFoundInstance();
        pair2 = (Pair)possibleLeftPairs.elementAt(l);
        if(pair2.getCount() <= 1) goto _L2; else goto _L1
_L1:
        int i1 = 0;
_L4:
        if(i1 < k)
            break MISSING_BLOCK_LABEL_117;
_L2:
        l++;
          goto _L3
        Pair pair3 = (Pair)possibleRightPairs.elementAt(i1);
        if(pair3.getCount() > 1 && checkChecksum(pair2, pair3))
            return constructResult(pair2, pair3);
        i1++;
          goto _L4
    }

    public void reset()
    {
        possibleLeftPairs.setSize(0);
        possibleRightPairs.setSize(0);
    }

    private static final int FINDER_PATTERNS[][];
    private static final int INSIDE_GSUM[];
    private static final int INSIDE_ODD_TOTAL_SUBSET[];
    private static final int INSIDE_ODD_WIDEST[];
    private static final int OUTSIDE_EVEN_TOTAL_SUBSET[];
    private static final int OUTSIDE_GSUM[];
    private static final int OUTSIDE_ODD_WIDEST[];
    private final Vector possibleLeftPairs = new Vector();
    private final Vector possibleRightPairs = new Vector();

    static 
    {
        int ai[] = new int[5];
        ai[0] = 1;
        ai[1] = 10;
        ai[2] = 34;
        ai[3] = 70;
        ai[4] = 126;
        OUTSIDE_EVEN_TOTAL_SUBSET = ai;
        int ai1[] = new int[4];
        ai1[0] = 4;
        ai1[1] = 20;
        ai1[2] = 48;
        ai1[3] = 81;
        INSIDE_ODD_TOTAL_SUBSET = ai1;
        int ai2[] = new int[5];
        ai2[1] = 161;
        ai2[2] = 961;
        ai2[3] = 2015;
        ai2[4] = 2715;
        OUTSIDE_GSUM = ai2;
        int ai3[] = new int[4];
        ai3[1] = 336;
        ai3[2] = 1036;
        ai3[3] = 1516;
        INSIDE_GSUM = ai3;
        int ai4[] = new int[5];
        ai4[0] = 8;
        ai4[1] = 6;
        ai4[2] = 4;
        ai4[3] = 3;
        ai4[4] = 1;
        OUTSIDE_ODD_WIDEST = ai4;
        int ai5[] = new int[4];
        ai5[0] = 2;
        ai5[1] = 4;
        ai5[2] = 6;
        ai5[3] = 8;
        INSIDE_ODD_WIDEST = ai5;
        int ai6[][] = new int[9][];
        int ai7[] = new int[4];
        ai7[0] = 3;
        ai7[1] = 8;
        ai7[2] = 2;
        ai7[3] = 1;
        ai6[0] = ai7;
        int ai8[] = new int[4];
        ai8[0] = 3;
        ai8[1] = 5;
        ai8[2] = 5;
        ai8[3] = 1;
        ai6[1] = ai8;
        int ai9[] = new int[4];
        ai9[0] = 3;
        ai9[1] = 3;
        ai9[2] = 7;
        ai9[3] = 1;
        ai6[2] = ai9;
        int ai10[] = new int[4];
        ai10[0] = 3;
        ai10[1] = 1;
        ai10[2] = 9;
        ai10[3] = 1;
        ai6[3] = ai10;
        int ai11[] = new int[4];
        ai11[0] = 2;
        ai11[1] = 7;
        ai11[2] = 4;
        ai11[3] = 1;
        ai6[4] = ai11;
        int ai12[] = new int[4];
        ai12[0] = 2;
        ai12[1] = 5;
        ai12[2] = 6;
        ai12[3] = 1;
        ai6[5] = ai12;
        int ai13[] = new int[4];
        ai13[0] = 2;
        ai13[1] = 3;
        ai13[2] = 8;
        ai13[3] = 1;
        ai6[6] = ai13;
        int ai14[] = new int[4];
        ai14[0] = 1;
        ai14[1] = 5;
        ai14[2] = 7;
        ai14[3] = 1;
        ai6[7] = ai14;
        int ai15[] = new int[4];
        ai15[0] = 1;
        ai15[1] = 3;
        ai15[2] = 9;
        ai15[3] = 1;
        ai6[8] = ai15;
        FINDER_PATTERNS = ai6;
    }
}
