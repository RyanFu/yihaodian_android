// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class CodaBarReader extends OneDReader
{

    public CodaBarReader()
    {
    }

    private static boolean arrayContains(char ac[], char c)
    {
        if(ac == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        if(i < ac.length) goto _L3; else goto _L2
_L2:
        boolean flag = false;
_L5:
        return flag;
_L3:
        if(ac[i] != c)
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L5; else goto _L4
_L4:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static int[] findAsteriskPattern(BitArray bitarray)
        throws NotFoundException
    {
        int k;
        int ai[];
        int l;
        boolean flag;
        int i1;
        int j1;
        int i = bitarray.getSize();
        int j = 0;
        while(j < i && !bitarray.get(j)) 
            j++;
        k = 0;
        ai = new int[7];
        l = j;
        flag = false;
        i1 = ai.length;
        j1 = j;
_L2:
        if(j1 >= i)
            throw NotFoundException.getNotFoundInstance();
        if(!(flag ^ bitarray.get(j1)))
            break; /* Loop/switch isn't completed */
        ai[k] = 1 + ai[k];
_L5:
        j1++;
        if(true) goto _L2; else goto _L1
_L1:
        if(k != i1 - 1)
            break MISSING_BLOCK_LABEL_229;
        int ai1[];
        if(!arrayContains(STARTEND_ENCODING, toNarrowWidePattern(ai)) || !bitarray.isRange(Math.max(0, l - (j1 - l) / 2), l, false))
            break MISSING_BLOCK_LABEL_154;
        ai1 = new int[2];
        ai1[0] = l;
        ai1[1] = j1;
        return ai1;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        int k1;
        l += ai[0] + ai[1];
        k1 = 2;
_L6:
        if(k1 < i1) goto _L4; else goto _L3
_L3:
        ai[i1 - 2] = 0;
        ai[i1 - 1] = 0;
        k--;
_L7:
        ai[k] = 1;
        flag ^= true;
          goto _L5
_L4:
        ai[k1 - 2] = ai[k1];
        k1++;
          goto _L6
        k++;
          goto _L7
    }

    private static char toNarrowWidePattern(int ai[])
    {
        int i;
        int j;
        int k;
        int l;
        i = ai.length;
        j = 0;
        k = 0x7fffffff;
        l = 0;
_L8:
        if(l < i) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        int k1;
        i1 = 0;
        j1 = 0;
        k1 = 0;
_L9:
        if(k1 < i) goto _L4; else goto _L3
_L3:
        int l1;
        if(i1 != 2 && i1 != 3)
            continue; /* Loop/switch isn't completed */
        l1 = 0;
_L11:
        if(l1 < CHARACTER_ENCODINGS.length) goto _L6; else goto _L5
_L5:
        if(--j > k) goto _L1; else goto _L7
_L7:
        char c = '!';
_L10:
        return c;
_L2:
        if(ai[l] < k)
            k = ai[l];
        if(ai[l] > j)
            j = ai[l];
        l++;
          goto _L8
_L4:
        if(ai[k1] > j)
        {
            j1 |= 1 << i - 1 - k1;
            i1++;
        }
        k1++;
          goto _L9
_L6:
label0:
        {
            if(CHARACTER_ENCODINGS[l1] != j1)
                break label0;
            c = ALPHABET[l1];
        }
          goto _L10
        l1++;
          goto _L11
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        int ai[];
        int j;
        int k;
        ai = findAsteriskPattern(bitarray);
        ai[1] = 0;
        j = ai[1];
        k = bitarray.getSize();
_L10:
        StringBuffer stringbuffer;
        int ai1[];
        char c;
        if(j >= k || bitarray.get(j))
        {
            stringbuffer = new StringBuffer();
            break MISSING_BLOCK_LABEL_48;
        } else
        {
            j++;
            continue; /* Loop/switch isn't completed */
        }
_L6:
        int l;
        int i1;
        ai1 = new int[7];
        recordPattern(bitarray, j, ai1);
        c = toNarrowWidePattern(ai1);
        if(c == '!')
            throw NotFoundException.getNotFoundInstance();
        stringbuffer.append(c);
        l = j;
        i1 = 0;
_L7:
        int j1 = ai1.length;
        if(i1 < j1) goto _L2; else goto _L1
_L1:
        if(j < k && !bitarray.get(j)) goto _L4; else goto _L3
_L3:
        if(j < k) goto _L6; else goto _L5
_L5:
        int k1;
        int l1;
        k1 = 0;
        l1 = 0;
_L8:
        int i2 = ai1.length;
        if(l1 >= i2)
        {
            int j2 = j - l - k1;
            if(j != k && j2 / 2 < k1)
                throw NotFoundException.getNotFoundInstance();
            break MISSING_BLOCK_LABEL_222;
        }
        break MISSING_BLOCK_LABEL_206;
_L2:
        j += ai1[i1];
        i1++;
          goto _L7
_L4:
        j++;
          goto _L1
        k1 += ai1[l1];
        l1++;
          goto _L8
        if(stringbuffer.length() < 2)
            throw NotFoundException.getNotFoundInstance();
        char c1 = stringbuffer.charAt(0);
        if(!arrayContains(STARTEND_ENCODING, c1))
            throw NotFoundException.getNotFoundInstance();
        int k2 = 1;
        do
        {
            int l2 = stringbuffer.length();
            if(k2 >= l2)
            {
                if(stringbuffer.length() > 6)
                {
                    stringbuffer.deleteCharAt(stringbuffer.length() - 1);
                    stringbuffer.deleteCharAt(0);
                    float f = (float)(ai[1] + ai[0]) / 2.0F;
                    float f1 = (float)(j + l) / 2.0F;
                    String s = stringbuffer.toString();
                    ResultPoint aresultpoint[] = new ResultPoint[2];
                    ResultPoint resultpoint = new ResultPoint(f, i);
                    aresultpoint[0] = resultpoint;
                    ResultPoint resultpoint1 = new ResultPoint(f1, i);
                    aresultpoint[1] = resultpoint1;
                    return new Result(s, null, aresultpoint, BarcodeFormat.CODABAR);
                }
                break;
            }
            if(stringbuffer.charAt(k2) == c1 && k2 + 1 != stringbuffer.length())
            {
                stringbuffer.delete(k2 + 1, stringbuffer.length() - 1);
                k2 = stringbuffer.length();
            }
            k2++;
        } while(true);
        throw NotFoundException.getNotFoundInstance();
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static final char ALPHABET[] = "0123456789-$:/.+ABCDTN".toCharArray();
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCDTN";
    private static final int CHARACTER_ENCODINGS[];
    private static final char STARTEND_ENCODING[];
    private static final int minCharacterLength = 6;

    static 
    {
        int ai[] = new int[22];
        ai[0] = 3;
        ai[1] = 6;
        ai[2] = 9;
        ai[3] = 96;
        ai[4] = 18;
        ai[5] = 66;
        ai[6] = 33;
        ai[7] = 36;
        ai[8] = 48;
        ai[9] = 72;
        ai[10] = 12;
        ai[11] = 24;
        ai[12] = 37;
        ai[13] = 81;
        ai[14] = 84;
        ai[15] = 21;
        ai[16] = 26;
        ai[17] = 41;
        ai[18] = 11;
        ai[19] = 14;
        ai[20] = 26;
        ai[21] = 41;
        CHARACTER_ENCODINGS = ai;
        char ac[] = new char[8];
        ac[0] = 'E';
        ac[1] = '*';
        ac[2] = 'A';
        ac[3] = 'B';
        ac[4] = 'C';
        ac[5] = 'D';
        ac[6] = 'T';
        ac[7] = 'N';
        STARTEND_ENCODING = ac;
    }
}
