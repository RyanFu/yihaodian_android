// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.*;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            ExpandedPair, BitArrayBuilder

public final class RSSExpandedReader extends AbstractRSSReader
{

    public RSSExpandedReader()
    {
        currentSequence = new int[LONGEST_SEQUENCE_SIZE];
    }

    private void adjustOddEvenCounts(int i)
        throws NotFoundException
    {
        int j;
        int k;
        int l;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        j = count(oddCounts);
        k = count(evenCounts);
        l = (j + k) - i;
        if((j & 1) == 1)
            flag = true;
        else
            flag = false;
        if((k & 1) == 0)
            flag1 = true;
        else
            flag1 = false;
        flag2 = false;
        flag3 = false;
        if(j <= 13) goto _L2; else goto _L1
_L1:
        flag3 = true;
_L6:
        flag4 = false;
        flag5 = false;
        if(k > 13)
            flag5 = true;
        else
        if(k < 4)
            flag4 = true;
        if(l == 1)
        {
            if(flag)
            {
                if(flag1)
                    throw NotFoundException.getNotFoundInstance();
                flag3 = true;
            } else
            {
                if(!flag1)
                    throw NotFoundException.getNotFoundInstance();
                flag5 = true;
            }
        } else
        if(l == -1)
        {
            if(flag)
            {
                if(flag1)
                    throw NotFoundException.getNotFoundInstance();
                flag2 = true;
            } else
            {
                if(!flag1)
                    throw NotFoundException.getNotFoundInstance();
                flag4 = true;
            }
        } else
        if(l == 0)
        {
            if(!flag)
                continue; /* Loop/switch isn't completed */
            if(!flag1)
                throw NotFoundException.getNotFoundInstance();
            if(j < k)
            {
                flag2 = true;
                flag5 = true;
            } else
            {
                flag3 = true;
                flag4 = true;
            }
        } else
        {
            throw NotFoundException.getNotFoundInstance();
        }
          goto _L3
_L2:
        if(j < 4)
            flag2 = true;
        continue; /* Loop/switch isn't completed */
        if(!flag1) goto _L3; else goto _L4
_L4:
        throw NotFoundException.getNotFoundInstance();
_L3:
        if(flag2)
        {
            if(flag3)
                throw NotFoundException.getNotFoundInstance();
            increment(oddCounts, oddRoundingErrors);
        }
        if(flag3)
            decrement(oddCounts, oddRoundingErrors);
        if(flag4)
        {
            if(flag5)
                throw NotFoundException.getNotFoundInstance();
            increment(evenCounts, oddRoundingErrors);
        }
        if(flag5)
            decrement(evenCounts, evenRoundingErrors);
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean checkChecksum()
    {
        ExpandedPair expandedpair = (ExpandedPair)pairs.elementAt(0);
        DataCharacter datacharacter = expandedpair.getLeftChar();
        int i = expandedpair.getRightChar().getChecksumPortion();
        int j = 2;
        int k = 1;
        do
        {
            if(k >= pairs.size())
            {
                ExpandedPair expandedpair1;
                boolean flag;
                if(i % 211 + 211 * (j - 4) == datacharacter.getValue())
                    flag = true;
                else
                    flag = false;
                return flag;
            }
            expandedpair1 = (ExpandedPair)pairs.elementAt(k);
            i += expandedpair1.getLeftChar().getChecksumPortion();
            j++;
            if(expandedpair1.getRightChar() != null)
            {
                i += expandedpair1.getRightChar().getChecksumPortion();
                j++;
            }
            k++;
        } while(true);
    }

    private boolean checkPairSequence(Vector vector, FinderPattern finderpattern)
        throws NotFoundException
    {
        int i;
        int j;
        i = 1 + vector.size();
        if(i > currentSequence.length)
            throw NotFoundException.getNotFoundInstance();
        j = 0;
_L4:
        if(j < vector.size()) goto _L2; else goto _L1
_L1:
        int k;
        currentSequence[i - 1] = finderpattern.getValue();
        k = 0;
_L8:
        int ai[];
        if(k >= FINDER_PATTERN_SEQUENCES.length)
            throw NotFoundException.getNotFoundInstance();
        ai = FINDER_PATTERN_SEQUENCES[k];
          goto _L3
_L2:
        currentSequence[j] = ((ExpandedPair)vector.elementAt(j)).getFinderPattern().getValue();
        j++;
          goto _L4
_L3:
        if(ai.length < i) goto _L6; else goto _L5
_L5:
        boolean flag;
        int l;
        flag = true;
        l = 0;
_L7:
        if(l < i)
        {
label0:
            {
                if(currentSequence[l] == ai[l])
                    break label0;
                flag = false;
            }
        }
        if(flag)
        {
            boolean flag1;
            if(i == ai.length)
                flag1 = true;
            else
                flag1 = false;
            return flag1;
        }
        break; /* Loop/switch isn't completed */
        l++;
        if(true) goto _L7; else goto _L6
_L6:
        k++;
          goto _L8
    }

    private static Result constructResult(Vector vector)
        throws NotFoundException
    {
        String s = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(vector)).parseInformation();
        ResultPoint aresultpoint[] = ((ExpandedPair)vector.elementAt(0)).getFinderPattern().getResultPoints();
        ResultPoint aresultpoint1[] = ((ExpandedPair)vector.lastElement()).getFinderPattern().getResultPoints();
        ResultPoint aresultpoint2[] = new ResultPoint[4];
        aresultpoint2[0] = aresultpoint[0];
        aresultpoint2[1] = aresultpoint[1];
        aresultpoint2[2] = aresultpoint1[0];
        aresultpoint2[3] = aresultpoint1[1];
        return new Result(s, null, aresultpoint2, BarcodeFormat.RSS_EXPANDED);
    }

    private void findNextPair(BitArray bitarray, Vector vector, int i)
        throws NotFoundException
    {
        int ai[];
        int k;
        boolean flag;
        boolean flag1;
        int l;
        int i1;
        int j1;
        ai = decodeFinderCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        int j = bitarray.getSize();
        if(i >= 0)
            k = i;
        else
        if(vector.isEmpty())
            k = 0;
        else
            k = ((ExpandedPair)vector.lastElement()).getFinderPattern().getStartEnd()[1];
        if(vector.size() % 2 != 0)
            flag = true;
        else
            flag = false;
        flag1 = false;
_L4:
        if(k < j) goto _L2; else goto _L1
_L1:
        l = 0;
        i1 = k;
        j1 = k;
_L5:
        if(j1 >= j)
            throw NotFoundException.getNotFoundInstance();
        break MISSING_BLOCK_LABEL_151;
_L2:
        if(bitarray.get(k))
            flag1 = false;
        else
            flag1 = true;
        if(!flag1) goto _L1; else goto _L3
_L3:
        k++;
          goto _L4
        if(flag1 ^ bitarray.get(j1))
        {
            ai[l] = 1 + ai[l];
        } else
        {
            if(l == 3)
            {
                if(flag)
                    reverseCounters(ai);
                if(isFinderPattern(ai))
                {
                    startEnd[0] = i1;
                    startEnd[1] = j1;
                    return;
                }
                if(flag)
                    reverseCounters(ai);
                i1 += ai[0] + ai[1];
                ai[0] = ai[2];
                ai[1] = ai[3];
                ai[2] = 0;
                ai[3] = 0;
                l--;
            } else
            {
                l++;
            }
            ai[l] = 1;
            if(flag1)
                flag1 = false;
            else
                flag1 = true;
        }
        j1++;
          goto _L5
    }

    private static int getNextSecondBar(BitArray bitarray, int i)
    {
        int j;
        boolean flag;
        j = i;
        flag = bitarray.get(j);
_L3:
        if(j < bitarray.size && bitarray.get(j) == flag) goto _L2; else goto _L1
_L1:
        boolean flag1;
        if(flag)
            flag1 = false;
        else
            flag1 = true;
_L4:
        if(j >= bitarray.size || bitarray.get(j) != flag1)
            return j;
        break MISSING_BLOCK_LABEL_64;
_L2:
        j++;
          goto _L3
        j++;
          goto _L4
    }

    private static boolean isNotA1left(FinderPattern finderpattern, boolean flag, boolean flag1)
    {
        boolean flag2;
        if(finderpattern.getValue() == 0 && flag && flag1)
            flag2 = false;
        else
            flag2 = true;
        return flag2;
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitarray, int i, boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        int l1 = startEnd[0] - 1;
_L7:
        if(l1 >= 0 && !bitarray.get(l1)) goto _L4; else goto _L3
_L3:
        int j;
        int l;
        int i1;
        int i2 = l1 + 1;
        i1 = startEnd[0] - i2;
        j = i2;
        l = startEnd[1];
_L8:
        int ai[];
        int j1;
        ai = decodeFinderCounters;
        j1 = ai.length - 1;
_L9:
        if(j1 > 0) goto _L6; else goto _L5
_L5:
        ai[0] = i1;
        int k1 = parseFinderValue(ai, FINDER_PATTERNS);
        FinderPattern finderpattern;
        int ai1[] = new int[2];
        ai1[0] = j;
        ai1[1] = l;
        finderpattern = new FinderPattern(k1, ai1, j, l, i);
_L10:
        return finderpattern;
_L4:
        l1--;
          goto _L7
_L2:
label0:
        {
            j = startEnd[0];
            for(int k = 1 + startEnd[1]; bitarray.get(k) && k < bitarray.size; k++)
                break label0;

            l = k;
            i1 = l - startEnd[1];
        }
          goto _L8
_L6:
        ai[j1] = ai[j1 - 1];
        j1--;
          goto _L9
        NotFoundException notfoundexception;
        notfoundexception;
        finderpattern = null;
          goto _L10
    }

    private static void reverseCounters(int ai[])
    {
        int i = ai.length;
        int j = 0;
        do
        {
            if(j >= i / 2)
                return;
            int k = ai[j];
            ai[j] = ai[i - j - 1];
            ai[i - j - 1] = k;
            j++;
        } while(true);
    }

    DataCharacter decodeDataCharacter(BitArray bitarray, FinderPattern finderpattern, boolean flag, boolean flag1)
        throws NotFoundException
    {
        int ai[];
        float f;
        int ai1[];
        int ai2[];
        float af[];
        float af1[];
        int l;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        ai = dataCharacterCounters;
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        ai[5] = 0;
        ai[6] = 0;
        ai[7] = 0;
        int i1;
        int l1;
        int j2;
        if(flag1)
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
        f = (float)count(ai) / (float)17;
        ai1 = oddCounts;
        ai2 = evenCounts;
        af = oddRoundingErrors;
        af1 = evenRoundingErrors;
        l = 0;
_L5:
        i1 = ai.length;
        if(l < i1) goto _L2; else goto _L1
_L1:
        adjustOddEvenCounts(17);
        l1 = 4 * finderpattern.getValue();
        float f1;
        int j1;
        int k1;
        int i2;
        int k2;
        if(flag)
            i2 = 0;
        else
            i2 = 2;
        j2 = l1 + i2;
        if(flag1)
            k2 = 0;
        else
            k2 = 1;
        l2 = (j2 + k2) - 1;
        i3 = 0;
        j3 = 0;
        k3 = ai1.length - 1;
_L6:
        if(k3 >= 0) goto _L4; else goto _L3
_L3:
        l3 = 0;
        i4 = 0;
        j4 = ai2.length - 1;
_L7:
        if(j4 < 0)
        {
            int k4 = j3 + l3;
            if((i3 & 1) != 0 || i3 > 13 || i3 < 4)
            {
                throw NotFoundException.getNotFoundInstance();
            } else
            {
                int l4 = (13 - i3) / 2;
                int i5 = SYMBOL_WIDEST[l4];
                int j5 = 9 - i5;
                int k5 = RSSUtils.getRSSvalue(ai1, i5, true);
                int l5 = RSSUtils.getRSSvalue(ai2, j5, false);
                int i6 = EVEN_TOTAL_SUBSET[l4];
                int j6 = GSUM[l4] + (l5 + k5 * i6);
                DataCharacter datacharacter = new DataCharacter(j6, k4);
                return datacharacter;
            }
        }
        break MISSING_BLOCK_LABEL_454;
_L2:
        f1 = (1.0F * (float)ai[l]) / f;
        j1 = (int)(0.5F + f1);
        if(j1 < 1)
            j1 = 1;
        else
        if(j1 > 8)
            j1 = 8;
        k1 = l >> 1;
        if((l & 1) == 0)
        {
            ai1[k1] = j1;
            af[k1] = f1 - (float)j1;
        } else
        {
            ai2[k1] = j1;
            af1[k1] = f1 - (float)j1;
        }
        l++;
          goto _L5
_L4:
        if(isNotA1left(finderpattern, flag, flag1))
            j3 += WEIGHTS[l2][k3 * 2] * ai1[k3];
        i3 += ai1[k3];
        k3--;
          goto _L6
        if(isNotA1left(finderpattern, flag, flag1))
            l3 += WEIGHTS[l2][1 + j4 * 2] * ai2[j4];
        i4 += ai2[j4];
        j4--;
          goto _L7
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        reset();
        decodeRow2pairs(i, bitarray);
        return constructResult(pairs);
    }

    Vector decodeRow2pairs(int i, BitArray bitarray)
        throws NotFoundException
    {
        ExpandedPair expandedpair;
        do
        {
            do
            {
                expandedpair = retrieveNextPair(bitarray, pairs, i);
                pairs.addElement(expandedpair);
            } while(!expandedpair.mayBeLast());
            if(checkChecksum())
                return pairs;
        } while(!expandedpair.mustBeLast());
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset()
    {
        pairs.setSize(0);
    }

    ExpandedPair retrieveNextPair(BitArray bitarray, Vector vector, int i)
        throws NotFoundException
    {
        boolean flag2;
        DataCharacter datacharacter1;
        boolean flag;
        boolean flag1;
        int j;
        if(vector.size() % 2 == 0)
            flag = true;
        else
            flag = false;
        flag1 = true;
        j = -1;
        do
        {
            findNextPair(bitarray, vector, j);
            FinderPattern finderpattern = parseFoundFinderPattern(bitarray, i, flag);
            DataCharacter datacharacter;
            DataCharacter datacharacter2;
            if(finderpattern == null)
                j = getNextSecondBar(bitarray, startEnd[0]);
            else
                flag1 = false;
        } while(flag1);
        flag2 = checkPairSequence(vector, finderpattern);
        datacharacter = decodeDataCharacter(bitarray, finderpattern, flag, true);
        datacharacter2 = decodeDataCharacter(bitarray, finderpattern, flag, false);
        datacharacter1 = datacharacter2;
_L2:
        return new ExpandedPair(datacharacter, datacharacter1, finderpattern, flag2);
        NotFoundException notfoundexception;
        notfoundexception;
        if(flag2)
            datacharacter1 = null;
        else
            throw notfoundexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final int EVEN_TOTAL_SUBSET[];
    private static final int FINDER_PATTERNS[][];
    private static final int FINDER_PATTERN_SEQUENCES[][];
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int GSUM[];
    private static final int LONGEST_SEQUENCE_SIZE = 0;
    private static final int MAX_PAIRS = 11;
    private static final int SYMBOL_WIDEST[];
    private static final int WEIGHTS[][];
    private final int currentSequence[];
    private final Vector pairs = new Vector(11);
    private final int startEnd[] = new int[2];

    static 
    {
        int ai[] = new int[5];
        ai[0] = 7;
        ai[1] = 5;
        ai[2] = 4;
        ai[3] = 3;
        ai[4] = 1;
        SYMBOL_WIDEST = ai;
        int ai1[] = new int[5];
        ai1[0] = 4;
        ai1[1] = 20;
        ai1[2] = 52;
        ai1[3] = 104;
        ai1[4] = 204;
        EVEN_TOTAL_SUBSET = ai1;
        int ai2[] = new int[5];
        ai2[1] = 348;
        ai2[2] = 1388;
        ai2[3] = 2948;
        ai2[4] = 3988;
        GSUM = ai2;
        int ai3[][] = new int[6][];
        int ai4[] = new int[4];
        ai4[0] = 1;
        ai4[1] = 8;
        ai4[2] = 4;
        ai4[3] = 1;
        ai3[0] = ai4;
        int ai5[] = new int[4];
        ai5[0] = 3;
        ai5[1] = 6;
        ai5[2] = 4;
        ai5[3] = 1;
        ai3[1] = ai5;
        int ai6[] = new int[4];
        ai6[0] = 3;
        ai6[1] = 4;
        ai6[2] = 6;
        ai6[3] = 1;
        ai3[2] = ai6;
        int ai7[] = new int[4];
        ai7[0] = 3;
        ai7[1] = 2;
        ai7[2] = 8;
        ai7[3] = 1;
        ai3[3] = ai7;
        int ai8[] = new int[4];
        ai8[0] = 2;
        ai8[1] = 6;
        ai8[2] = 5;
        ai8[3] = 1;
        ai3[4] = ai8;
        int ai9[] = new int[4];
        ai9[0] = 2;
        ai9[1] = 2;
        ai9[2] = 9;
        ai9[3] = 1;
        ai3[5] = ai9;
        FINDER_PATTERNS = ai3;
        int ai10[][] = new int[23][];
        int ai11[] = new int[8];
        ai11[0] = 1;
        ai11[1] = 3;
        ai11[2] = 9;
        ai11[3] = 27;
        ai11[4] = 81;
        ai11[5] = 32;
        ai11[6] = 96;
        ai11[7] = 77;
        ai10[0] = ai11;
        int ai12[] = new int[8];
        ai12[0] = 20;
        ai12[1] = 60;
        ai12[2] = 180;
        ai12[3] = 118;
        ai12[4] = 143;
        ai12[5] = 7;
        ai12[6] = 21;
        ai12[7] = 63;
        ai10[1] = ai12;
        int ai13[] = new int[8];
        ai13[0] = 189;
        ai13[1] = 145;
        ai13[2] = 13;
        ai13[3] = 39;
        ai13[4] = 117;
        ai13[5] = 140;
        ai13[6] = 209;
        ai13[7] = 205;
        ai10[2] = ai13;
        int ai14[] = new int[8];
        ai14[0] = 193;
        ai14[1] = 157;
        ai14[2] = 49;
        ai14[3] = 147;
        ai14[4] = 19;
        ai14[5] = 57;
        ai14[6] = 171;
        ai14[7] = 91;
        ai10[3] = ai14;
        int ai15[] = new int[8];
        ai15[0] = 62;
        ai15[1] = 186;
        ai15[2] = 136;
        ai15[3] = 197;
        ai15[4] = 169;
        ai15[5] = 85;
        ai15[6] = 44;
        ai15[7] = 132;
        ai10[4] = ai15;
        int ai16[] = new int[8];
        ai16[0] = 185;
        ai16[1] = 133;
        ai16[2] = 188;
        ai16[3] = 142;
        ai16[4] = 4;
        ai16[5] = 12;
        ai16[6] = 36;
        ai16[7] = 108;
        ai10[5] = ai16;
        int ai17[] = new int[8];
        ai17[0] = 113;
        ai17[1] = 128;
        ai17[2] = 173;
        ai17[3] = 97;
        ai17[4] = 80;
        ai17[5] = 29;
        ai17[6] = 87;
        ai17[7] = 50;
        ai10[6] = ai17;
        int ai18[] = new int[8];
        ai18[0] = 150;
        ai18[1] = 28;
        ai18[2] = 84;
        ai18[3] = 41;
        ai18[4] = 123;
        ai18[5] = 158;
        ai18[6] = 52;
        ai18[7] = 156;
        ai10[7] = ai18;
        int ai19[] = new int[8];
        ai19[0] = 46;
        ai19[1] = 138;
        ai19[2] = 203;
        ai19[3] = 187;
        ai19[4] = 139;
        ai19[5] = 206;
        ai19[6] = 196;
        ai19[7] = 166;
        ai10[8] = ai19;
        int ai20[] = new int[8];
        ai20[0] = 76;
        ai20[1] = 17;
        ai20[2] = 51;
        ai20[3] = 153;
        ai20[4] = 37;
        ai20[5] = 111;
        ai20[6] = 122;
        ai20[7] = 155;
        ai10[9] = ai20;
        int ai21[] = new int[8];
        ai21[0] = 43;
        ai21[1] = 129;
        ai21[2] = 176;
        ai21[3] = 106;
        ai21[4] = 107;
        ai21[5] = 110;
        ai21[6] = 119;
        ai21[7] = 146;
        ai10[10] = ai21;
        int ai22[] = new int[8];
        ai22[0] = 16;
        ai22[1] = 48;
        ai22[2] = 144;
        ai22[3] = 10;
        ai22[4] = 30;
        ai22[5] = 90;
        ai22[6] = 59;
        ai22[7] = 177;
        ai10[11] = ai22;
        int ai23[] = new int[8];
        ai23[0] = 109;
        ai23[1] = 116;
        ai23[2] = 137;
        ai23[3] = 200;
        ai23[4] = 178;
        ai23[5] = 112;
        ai23[6] = 125;
        ai23[7] = 164;
        ai10[12] = ai23;
        int ai24[] = new int[8];
        ai24[0] = 70;
        ai24[1] = 210;
        ai24[2] = 208;
        ai24[3] = 202;
        ai24[4] = 184;
        ai24[5] = 130;
        ai24[6] = 179;
        ai24[7] = 115;
        ai10[13] = ai24;
        int ai25[] = new int[8];
        ai25[0] = 134;
        ai25[1] = 191;
        ai25[2] = 151;
        ai25[3] = 31;
        ai25[4] = 93;
        ai25[5] = 68;
        ai25[6] = 204;
        ai25[7] = 190;
        ai10[14] = ai25;
        int ai26[] = new int[8];
        ai26[0] = 148;
        ai26[1] = 22;
        ai26[2] = 66;
        ai26[3] = 198;
        ai26[4] = 172;
        ai26[5] = 94;
        ai26[6] = 71;
        ai26[7] = 2;
        ai10[15] = ai26;
        int ai27[] = new int[8];
        ai27[0] = 6;
        ai27[1] = 18;
        ai27[2] = 54;
        ai27[3] = 162;
        ai27[4] = 64;
        ai27[5] = 192;
        ai27[6] = 154;
        ai27[7] = 40;
        ai10[16] = ai27;
        int ai28[] = new int[8];
        ai28[0] = 120;
        ai28[1] = 149;
        ai28[2] = 25;
        ai28[3] = 75;
        ai28[4] = 14;
        ai28[5] = 42;
        ai28[6] = 126;
        ai28[7] = 167;
        ai10[17] = ai28;
        int ai29[] = new int[8];
        ai29[0] = 79;
        ai29[1] = 26;
        ai29[2] = 78;
        ai29[3] = 23;
        ai29[4] = 69;
        ai29[5] = 207;
        ai29[6] = 199;
        ai29[7] = 175;
        ai10[18] = ai29;
        int ai30[] = new int[8];
        ai30[0] = 103;
        ai30[1] = 98;
        ai30[2] = 83;
        ai30[3] = 38;
        ai30[4] = 114;
        ai30[5] = 131;
        ai30[6] = 182;
        ai30[7] = 124;
        ai10[19] = ai30;
        int ai31[] = new int[8];
        ai31[0] = 161;
        ai31[1] = 61;
        ai31[2] = 183;
        ai31[3] = 127;
        ai31[4] = 170;
        ai31[5] = 88;
        ai31[6] = 53;
        ai31[7] = 159;
        ai10[20] = ai31;
        int ai32[] = new int[8];
        ai32[0] = 55;
        ai32[1] = 165;
        ai32[2] = 73;
        ai32[3] = 8;
        ai32[4] = 24;
        ai32[5] = 72;
        ai32[6] = 5;
        ai32[7] = 15;
        ai10[21] = ai32;
        int ai33[] = new int[8];
        ai33[0] = 45;
        ai33[1] = 135;
        ai33[2] = 194;
        ai33[3] = 160;
        ai33[4] = 58;
        ai33[5] = 174;
        ai33[6] = 100;
        ai33[7] = 89;
        ai10[22] = ai33;
        WEIGHTS = ai10;
        int ai34[][] = new int[10][];
        ai34[0] = new int[2];
        int ai35[] = new int[3];
        ai35[1] = 1;
        ai35[2] = 1;
        ai34[1] = ai35;
        int ai36[] = new int[4];
        ai36[1] = 2;
        ai36[2] = 1;
        ai36[3] = 3;
        ai34[2] = ai36;
        int ai37[] = new int[5];
        ai37[1] = 4;
        ai37[2] = 1;
        ai37[3] = 3;
        ai37[4] = 2;
        ai34[3] = ai37;
        int ai38[] = new int[6];
        ai38[1] = 4;
        ai38[2] = 1;
        ai38[3] = 3;
        ai38[4] = 3;
        ai38[5] = 5;
        ai34[4] = ai38;
        int ai39[] = new int[7];
        ai39[1] = 4;
        ai39[2] = 1;
        ai39[3] = 3;
        ai39[4] = 4;
        ai39[5] = 5;
        ai39[6] = 5;
        ai34[5] = ai39;
        int ai40[] = new int[8];
        ai40[2] = 1;
        ai40[3] = 1;
        ai40[4] = 2;
        ai40[5] = 2;
        ai40[6] = 3;
        ai40[7] = 3;
        ai34[6] = ai40;
        int ai41[] = new int[9];
        ai41[2] = 1;
        ai41[3] = 1;
        ai41[4] = 2;
        ai41[5] = 2;
        ai41[6] = 3;
        ai41[7] = 4;
        ai41[8] = 4;
        ai34[7] = ai41;
        int ai42[] = new int[10];
        ai42[2] = 1;
        ai42[3] = 1;
        ai42[4] = 2;
        ai42[5] = 2;
        ai42[6] = 3;
        ai42[7] = 4;
        ai42[8] = 5;
        ai42[9] = 5;
        ai34[8] = ai42;
        int ai43[] = new int[11];
        ai43[2] = 1;
        ai43[3] = 1;
        ai43[4] = 2;
        ai43[5] = 3;
        ai43[6] = 3;
        ai43[7] = 4;
        ai43[8] = 4;
        ai43[9] = 5;
        ai43[10] = 5;
        ai34[9] = ai43;
        FINDER_PATTERN_SEQUENCES = ai34;
        LONGEST_SEQUENCE_SIZE = FINDER_PATTERN_SEQUENCES[FINDER_PATTERN_SEQUENCES.length - 1].length;
    }
}
