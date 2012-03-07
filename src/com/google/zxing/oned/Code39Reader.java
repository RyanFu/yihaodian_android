// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code39Reader extends OneDReader
{

    public Code39Reader()
    {
        usingCheckDigit = false;
        extendedMode = false;
    }

    public Code39Reader(boolean flag)
    {
        usingCheckDigit = flag;
        extendedMode = false;
    }

    public Code39Reader(boolean flag, boolean flag1)
    {
        usingCheckDigit = flag;
        extendedMode = flag1;
    }

    private static String decodeExtended(StringBuffer stringbuffer)
        throws FormatException
    {
        int i;
        StringBuffer stringbuffer1;
        int j;
        i = stringbuffer.length();
        stringbuffer1 = new StringBuffer(i);
        j = 0;
_L6:
        char c;
        char c1;
        int k;
        if(j >= i)
            return stringbuffer1.toString();
        c = stringbuffer.charAt(j);
        if(c != '+' && c != '$' && c != '%' && c != '/')
            break MISSING_BLOCK_LABEL_282;
        c1 = stringbuffer.charAt(j + 1);
        k = 0;
        c;
        JVM INSTR lookupswitch 4: default 116
    //                   36: 160
    //                   37: 188
    //                   43: 132
    //                   47: 240;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        stringbuffer1.append(k);
        j++;
_L7:
        j++;
          goto _L6
_L4:
        if(c1 >= 'A' && c1 <= 'Z')
            k = c1 + 32;
        else
            throw FormatException.getFormatInstance();
          goto _L1
_L2:
        if(c1 >= 'A' && c1 <= 'Z')
            k = c1 - 64;
        else
            throw FormatException.getFormatInstance();
          goto _L1
_L3:
        if(c1 >= 'A' && c1 <= 'E')
            k = c1 - 38;
        else
        if(c1 >= 'F' && c1 <= 'W')
            k = c1 - 11;
        else
            throw FormatException.getFormatInstance();
          goto _L1
_L5:
        if(c1 >= 'A' && c1 <= 'O')
            k = c1 - 32;
        else
        if(c1 == 'Z')
            k = 58;
        else
            throw FormatException.getFormatInstance();
          goto _L1
        stringbuffer1.append(c);
          goto _L7
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
        ai = new int[9];
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
        int k1;
        if(k != i1 - 1)
            break MISSING_BLOCK_LABEL_231;
        if(toNarrowWidePattern(ai) == ASTERISK_ENCODING && bitarray.isRange(Math.max(0, l - (j1 - l) / 2), l, false))
        {
            int ai1[] = new int[2];
            ai1[0] = l;
            ai1[1] = j1;
            return ai1;
        }
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
        if(flag)
            flag = false;
        else
            flag = true;
          goto _L5
_L4:
        ai[k1 - 2] = ai[k1];
        k1++;
          goto _L6
        k++;
          goto _L7
    }

    private static char patternToChar(int i)
        throws NotFoundException
    {
        int j = 0;
        do
        {
            if(j >= CHARACTER_ENCODINGS.length)
                throw NotFoundException.getNotFoundInstance();
            if(CHARACTER_ENCODINGS[j] == i)
                return ALPHABET[j];
            j++;
        } while(true);
    }

    private static int toNarrowWidePattern(int ai[])
    {
        int i;
        int j;
        i = ai.length;
        j = 0;
_L9:
        int k;
        int l;
        k = 0x7fffffff;
        l = 0;
_L5:
        if(l < i) goto _L2; else goto _L1
_L1:
        int j1;
        int k1;
        int l1;
        int i2;
        j = k;
        j1 = 0;
        k1 = 0;
        l1 = 0;
        i2 = 0;
_L6:
        if(i2 < i) goto _L4; else goto _L3
_L3:
        int l2;
        if(j1 != 3)
            continue; /* Loop/switch isn't completed */
        l2 = 0;
_L7:
        int k2;
        if(l2 >= i || j1 <= 0)
        {
            k2 = l1;
        } else
        {
label0:
            {
                int i3 = ai[l2];
                if(ai[l2] <= j)
                    break label0;
                j1--;
                if(i3 << 1 < k1)
                    break label0;
                k2 = -1;
            }
        }
_L10:
        return k2;
_L2:
        int i1 = ai[l];
        if(i1 < k && i1 > j)
            k = i1;
        l++;
          goto _L5
_L4:
        int j2 = ai[i2];
        if(ai[i2] > j)
        {
            l1 |= 1 << i - 1 - i2;
            j1++;
            k1 += j2;
        }
        i2++;
          goto _L6
        l2++;
          goto _L7
        if(j1 > 3) goto _L9; else goto _L8
_L8:
        k2 = -1;
          goto _L10
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        int ai[];
        int j;
        int k;
        ai = findAsteriskPattern(bitarray);
        j = ai[1];
        k = bitarray.getSize();
_L10:
        StringBuffer stringbuffer;
        int ai1[];
        int l;
        if(j >= k || bitarray.get(j))
        {
            stringbuffer = new StringBuffer(20);
            ai1 = new int[9];
            break MISSING_BLOCK_LABEL_51;
        } else
        {
            j++;
            continue; /* Loop/switch isn't completed */
        }
_L6:
        char c;
        int i1;
        int j1;
        recordPattern(bitarray, j, ai1);
        l = toNarrowWidePattern(ai1);
        if(l < 0)
            throw NotFoundException.getNotFoundInstance();
        c = patternToChar(l);
        stringbuffer.append(c);
        i1 = j;
        j1 = 0;
_L7:
        int k1 = ai1.length;
        if(j1 < k1) goto _L2; else goto _L1
_L1:
        if(j < k && !bitarray.get(j)) goto _L4; else goto _L3
_L3:
        if(c != '*') goto _L6; else goto _L5
_L5:
        int l1;
        int i2;
        stringbuffer.deleteCharAt(stringbuffer.length() - 1);
        l1 = 0;
        i2 = 0;
_L8:
        int j2 = ai1.length;
        if(i2 >= j2)
        {
            int k2 = j - i1 - l1;
            if(j != k && k2 / 2 < l1)
                throw NotFoundException.getNotFoundInstance();
            break MISSING_BLOCK_LABEL_237;
        }
        break MISSING_BLOCK_LABEL_221;
_L2:
        j += ai1[j1];
        j1++;
          goto _L7
_L4:
        j++;
          goto _L1
        l1 += ai1[i2];
        i2++;
          goto _L8
        if(usingCheckDigit)
        {
            int l2 = stringbuffer.length() - 1;
            int i3 = 0;
            int j3 = 0;
            do
            {
                if(j3 >= l2)
                {
                    if(stringbuffer.charAt(l2) != ALPHABET[i3 % 43])
                        throw ChecksumException.getChecksumInstance();
                    break;
                }
                i3 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(stringbuffer.charAt(j3));
                j3++;
            } while(true);
            stringbuffer.deleteCharAt(l2);
        }
        if(stringbuffer.length() == 0)
            throw NotFoundException.getNotFoundInstance();
        String s;
        float f;
        float f1;
        ResultPoint aresultpoint[];
        ResultPoint resultpoint;
        ResultPoint resultpoint1;
        BarcodeFormat barcodeformat;
        Result result;
        if(extendedMode)
            s = decodeExtended(stringbuffer);
        else
            s = stringbuffer.toString();
        f = (float)(ai[1] + ai[0]) / 2.0F;
        f1 = (float)(j + i1) / 2.0F;
        aresultpoint = new ResultPoint[2];
        resultpoint = new ResultPoint(f, i);
        aresultpoint[0] = resultpoint;
        resultpoint1 = new ResultPoint(f1, i);
        aresultpoint[1] = resultpoint1;
        barcodeformat = BarcodeFormat.CODE_39;
        result = new Result(s, null, aresultpoint, barcodeformat);
        return result;
        if(true) goto _L10; else goto _L9
_L9:
    }

    private static final char ALPHABET[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    private static final int ASTERISK_ENCODING;
    static final int CHARACTER_ENCODINGS[];
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static 
    {
        int ai[] = new int[44];
        ai[0] = 52;
        ai[1] = 289;
        ai[2] = 97;
        ai[3] = 352;
        ai[4] = 49;
        ai[5] = 304;
        ai[6] = 112;
        ai[7] = 37;
        ai[8] = 292;
        ai[9] = 100;
        ai[10] = 265;
        ai[11] = 73;
        ai[12] = 328;
        ai[13] = 25;
        ai[14] = 280;
        ai[15] = 88;
        ai[16] = 13;
        ai[17] = 268;
        ai[18] = 76;
        ai[19] = 28;
        ai[20] = 259;
        ai[21] = 67;
        ai[22] = 322;
        ai[23] = 19;
        ai[24] = 274;
        ai[25] = 82;
        ai[26] = 7;
        ai[27] = 262;
        ai[28] = 70;
        ai[29] = 22;
        ai[30] = 385;
        ai[31] = 193;
        ai[32] = 448;
        ai[33] = 145;
        ai[34] = 400;
        ai[35] = 208;
        ai[36] = 133;
        ai[37] = 388;
        ai[38] = 196;
        ai[39] = 148;
        ai[40] = 168;
        ai[41] = 162;
        ai[42] = 138;
        ai[43] = 42;
        CHARACTER_ENCODINGS = ai;
        ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
    }
}
