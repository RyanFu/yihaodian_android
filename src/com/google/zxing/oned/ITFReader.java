// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class ITFReader extends OneDReader
{

    public ITFReader()
    {
        narrowLineWidth = -1;
    }

    private static int decodeDigit(int ai[])
        throws NotFoundException
    {
        int i = 107;
        int j = -1;
        int k = PATTERNS.length;
        int l = 0;
        do
        {
            int i1;
            if(l >= k)
                if(j >= 0)
                    return j;
                else
                    throw NotFoundException.getNotFoundInstance();
            i1 = patternMatchVariance(ai, PATTERNS[l], 204);
            if(i1 < i)
            {
                i = i1;
                j = l;
            }
            l++;
        } while(true);
    }

    private static void decodeMiddle(BitArray bitarray, int i, int j, StringBuffer stringbuffer)
        throws NotFoundException
    {
        int ai[];
        int ai1[];
        int ai2[];
        ai = new int[10];
        ai1 = new int[5];
        ai2 = new int[5];
_L2:
        if(i >= j)
            return;
        recordPattern(bitarray, i, ai);
        int k = 0;
        do
        {
label0:
            {
                if(k < 5)
                    break label0;
                stringbuffer.append((char)(48 + decodeDigit(ai1)));
                stringbuffer.append((char)(48 + decodeDigit(ai2)));
                int i1 = 0;
                while(i1 < ai.length) 
                {
                    i += ai[i1];
                    i1++;
                }
            }
            if(true)
                continue;
            int l = k << 1;
            ai1[k] = ai[l];
            ai2[k] = ai[l + 1];
            k++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static int[] findGuardPattern(BitArray bitarray, int i, int ai[])
        throws NotFoundException
    {
        int j;
        int ai1[];
        int k;
        int l;
        int i1;
        boolean flag;
        int j1;
        j = ai.length;
        ai1 = new int[j];
        k = bitarray.getSize();
        l = i;
        i1 = 0;
        flag = false;
        j1 = i;
_L2:
        if(l >= k)
            throw NotFoundException.getNotFoundInstance();
        if(!(flag ^ bitarray.get(l)))
            break; /* Loop/switch isn't completed */
        ai1[i1] = 1 + ai1[i1];
_L5:
        l++;
        if(true) goto _L2; else goto _L1
_L1:
        int k1;
        if(i1 != j - 1)
            break MISSING_BLOCK_LABEL_184;
        if(patternMatchVariance(ai1, ai, 204) < 107)
        {
            int ai2[] = new int[2];
            ai2[0] = j1;
            ai2[1] = l;
            return ai2;
        }
        j1 += ai1[0] + ai1[1];
        k1 = 2;
_L6:
        if(k1 < j) goto _L4; else goto _L3
_L3:
        ai1[j - 2] = 0;
        ai1[j - 1] = 0;
        i1--;
_L7:
        ai1[i1] = 1;
        if(flag)
            flag = false;
        else
            flag = true;
          goto _L5
_L4:
        ai1[k1 - 2] = ai1[k1];
        k1++;
          goto _L6
        i1++;
          goto _L7
    }

    private static int skipWhiteSpace(BitArray bitarray)
        throws NotFoundException
    {
        int i = bitarray.getSize();
        do
        {
            for(int j = 0; j >= i || bitarray.get(j);)
                if(j == i)
                    throw NotFoundException.getNotFoundInstance();
                else
                    return j;

            j++;
        } while(true);
    }

    private void validateQuietZone(BitArray bitarray, int i)
        throws NotFoundException
    {
        int j = 10 * narrowLineWidth;
        do
        {
            for(int k = i - 1; j <= 0 || k < 0 || bitarray.get(k);)
                if(j != 0)
                    throw NotFoundException.getNotFoundInstance();
                else
                    return;

            j--;
            k--;
        } while(true);
    }

    int[] decodeEnd(BitArray bitarray)
        throws NotFoundException
    {
        bitarray.reverse();
        int ai[];
        ai = findGuardPattern(bitarray, skipWhiteSpace(bitarray), END_PATTERN_REVERSED);
        validateQuietZone(bitarray, ai[0]);
        int i = ai[0];
        ai[0] = bitarray.getSize() - ai[1];
        ai[1] = bitarray.getSize() - i;
        bitarray.reverse();
        return ai;
        Exception exception;
        exception;
        bitarray.reverse();
        throw exception;
    }

    @Override
	public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws FormatException, NotFoundException
    {
        int ai[];
        int ai1[];
        String s;
        int ai2[];
        int j;
        boolean flag;
        int k;
        ai = decodeStart(bitarray);
        ai1 = decodeEnd(bitarray);
        StringBuffer stringbuffer = new StringBuffer(20);
        decodeMiddle(bitarray, ai[1], ai1[0], stringbuffer);
        s = stringbuffer.toString();
        ai2 = null;
        if(hashtable != null)
            ai2 = (int[])hashtable.get(DecodeHintType.ALLOWED_LENGTHS);
        if(ai2 == null)
            ai2 = DEFAULT_ALLOWED_LENGTHS;
        j = s.length();
        flag = false;
        k = 0;
_L2:
        if(k < ai2.length)
        {
label0:
            {
                if(j != ai2[k])
                    break label0;
                flag = true;
            }
        }
        if(!flag)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            ResultPoint aresultpoint[] = new ResultPoint[2];
            aresultpoint[0] = new ResultPoint(ai[1], i);
            aresultpoint[1] = new ResultPoint(ai1[0], i);
            return new Result(s, null, aresultpoint, BarcodeFormat.ITF);
        }
        k++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    int[] decodeStart(BitArray bitarray)
        throws NotFoundException
    {
        int ai[] = findGuardPattern(bitarray, skipWhiteSpace(bitarray), START_PATTERN);
        narrowLineWidth = ai[1] - ai[0] >> 2;
        validateQuietZone(bitarray, ai[0]);
        return ai;
    }

    private static final int DEFAULT_ALLOWED_LENGTHS[];
    private static final int END_PATTERN_REVERSED[];
    private static final int MAX_AVG_VARIANCE = 107;
    private static final int MAX_INDIVIDUAL_VARIANCE = 204;
    private static final int N = 1;
    static final int PATTERNS[][];
    private static final int START_PATTERN[];
    private static final int W = 3;
    private int narrowLineWidth;

    static 
    {
        int ai[] = new int[5];
        ai[0] = 6;
        ai[1] = 10;
        ai[2] = 12;
        ai[3] = 14;
        ai[4] = 44;
        DEFAULT_ALLOWED_LENGTHS = ai;
        int ai1[] = new int[4];
        ai1[0] = 1;
        ai1[1] = 1;
        ai1[2] = 1;
        ai1[3] = 1;
        START_PATTERN = ai1;
        int ai2[] = new int[3];
        ai2[0] = 1;
        ai2[1] = 1;
        ai2[2] = 3;
        END_PATTERN_REVERSED = ai2;
        int ai3[][] = new int[10][];
        int ai4[] = new int[5];
        ai4[0] = 1;
        ai4[1] = 1;
        ai4[2] = 3;
        ai4[3] = 3;
        ai4[4] = 1;
        ai3[0] = ai4;
        int ai5[] = new int[5];
        ai5[0] = 3;
        ai5[1] = 1;
        ai5[2] = 1;
        ai5[3] = 1;
        ai5[4] = 3;
        ai3[1] = ai5;
        int ai6[] = new int[5];
        ai6[0] = 1;
        ai6[1] = 3;
        ai6[2] = 1;
        ai6[3] = 1;
        ai6[4] = 3;
        ai3[2] = ai6;
        int ai7[] = new int[5];
        ai7[0] = 3;
        ai7[1] = 3;
        ai7[2] = 1;
        ai7[3] = 1;
        ai7[4] = 1;
        ai3[3] = ai7;
        int ai8[] = new int[5];
        ai8[0] = 1;
        ai8[1] = 1;
        ai8[2] = 3;
        ai8[3] = 1;
        ai8[4] = 3;
        ai3[4] = ai8;
        int ai9[] = new int[5];
        ai9[0] = 3;
        ai9[1] = 1;
        ai9[2] = 3;
        ai9[3] = 1;
        ai9[4] = 1;
        ai3[5] = ai9;
        int ai10[] = new int[5];
        ai10[0] = 1;
        ai10[1] = 3;
        ai10[2] = 3;
        ai10[3] = 1;
        ai10[4] = 1;
        ai3[6] = ai10;
        int ai11[] = new int[5];
        ai11[0] = 1;
        ai11[1] = 1;
        ai11[2] = 1;
        ai11[3] = 3;
        ai11[4] = 3;
        ai3[7] = ai11;
        int ai12[] = new int[5];
        ai12[0] = 3;
        ai12[1] = 1;
        ai12[2] = 1;
        ai12[3] = 3;
        ai12[4] = 1;
        ai3[8] = ai12;
        int ai13[] = new int[5];
        ai13[0] = 1;
        ai13[1] = 3;
        ai13[2] = 1;
        ai13[3] = 3;
        ai13[4] = 1;
        ai3[9] = ai13;
        PATTERNS = ai3;
    }
}
