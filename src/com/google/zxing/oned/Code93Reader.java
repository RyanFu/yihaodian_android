// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code93Reader extends OneDReader
{

    public Code93Reader()
    {
    }

    private static void checkChecksums(StringBuffer stringbuffer)
        throws ChecksumException
    {
        int i = stringbuffer.length();
        checkOneChecksum(stringbuffer, i - 2, 20);
        checkOneChecksum(stringbuffer, i - 1, 15);
    }

    private static void checkOneChecksum(StringBuffer stringbuffer, int i, int j)
        throws ChecksumException
    {
        int k = 1;
        int l = 0;
        int i1 = i - 1;
        do
        {
            if(i1 < 0)
                if(stringbuffer.charAt(i) != ALPHABET[l % 47])
                    throw ChecksumException.getChecksumInstance();
                else
                    return;
            l += k * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(stringbuffer.charAt(i1));
            if(++k > j)
                k = 1;
            i1--;
        } while(true);
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
        if(c < 'a' || c > 'd')
            break MISSING_BLOCK_LABEL_258;
        c1 = stringbuffer.charAt(j + 1);
        k = 0;
        c;
        JVM INSTR tableswitch 97 100: default 92
    //                   97 136
    //                   98 164
    //                   99 216
    //                   100 108;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        stringbuffer1.append(k);
        j++;
_L7:
        j++;
          goto _L6
_L5:
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
_L4:
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
        ai = new int[6];
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
            break MISSING_BLOCK_LABEL_207;
        if(toPattern(ai) == ASTERISK_ENCODING)
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

    private static int toPattern(int ai[])
    {
        int i;
        int j;
        int k;
        i = ai.length;
        j = 0;
        k = 0;
_L3:
        if(k < i) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        l = 0;
        i1 = 0;
_L6:
        int k1;
        int l1;
        if(i1 >= i)
        {
            l1 = l;
        } else
        {
label0:
            {
                int j1 = (9 * (ai[i1] << 8)) / j;
                k1 = j1 >> 8;
                if((j1 & 0xff) > 127)
                    k1++;
                if(k1 >= 1 && k1 <= 4)
                    break label0;
                l1 = -1;
            }
        }
        return l1;
_L2:
        j += ai[k];
        k++;
          goto _L3
        int i2;
        if((i1 & 1) != 0)
            break MISSING_BLOCK_LABEL_134;
        i2 = 0;
_L7:
        if(i2 < k1) goto _L5; else goto _L4
_L4:
        i1++;
          goto _L6
_L5:
        l = 1 | l << 1;
        i2++;
          goto _L7
        l <<= k1;
          goto _L4
    }

    @Override
	public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        int ai[];
        int j;
        int k;
        ai = findAsteriskPattern(bitarray);
        j = ai[1];
        k = bitarray.getSize();
_L5:
        StringBuffer stringbuffer;
        int ai1[];
        int l;
        if(j >= k || bitarray.get(j))
        {
            stringbuffer = new StringBuffer(20);
            ai1 = new int[6];
            break MISSING_BLOCK_LABEL_51;
        } else
        {
            j++;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        char c;
        int i1;
        int j1;
        recordPattern(bitarray, j, ai1);
        l = toPattern(ai1);
        if(l < 0)
            throw NotFoundException.getNotFoundInstance();
        c = patternToChar(l);
        stringbuffer.append(c);
        i1 = j;
        j1 = 0;
_L3:
label0:
        {
            int k1 = ai1.length;
            if(j1 < k1)
                break MISSING_BLOCK_LABEL_171;
            for(; j < k && !bitarray.get(j); j++)
                break MISSING_BLOCK_LABEL_187;

            if(c == '*')
            {
                stringbuffer.deleteCharAt(stringbuffer.length() - 1);
                if(j == k || !bitarray.get(j))
                    throw NotFoundException.getNotFoundInstance();
                break label0;
            }
        }
        if(true) goto _L2; else goto _L1
_L1:
        j += ai1[j1];
        j1++;
          goto _L3
        if(stringbuffer.length() < 2)
            throw NotFoundException.getNotFoundInstance();
        checkChecksums(stringbuffer);
        stringbuffer.setLength(stringbuffer.length() - 2);
        String s = decodeExtended(stringbuffer);
        float f = (ai[1] + ai[0]) / 2.0F;
        float f1 = (j + i1) / 2.0F;
        ResultPoint aresultpoint[] = new ResultPoint[2];
        ResultPoint resultpoint = new ResultPoint(f, i);
        aresultpoint[0] = resultpoint;
        ResultPoint resultpoint1 = new ResultPoint(f1, i);
        aresultpoint[1] = resultpoint1;
        Result result = new Result(s, null, aresultpoint, BarcodeFormat.CODE_93);
        return result;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static final char ALPHABET[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final int ASTERISK_ENCODING;
    private static final int CHARACTER_ENCODINGS[];

    static 
    {
        int ai[] = new int[48];
        ai[0] = 276;
        ai[1] = 328;
        ai[2] = 324;
        ai[3] = 322;
        ai[4] = 296;
        ai[5] = 292;
        ai[6] = 290;
        ai[7] = 336;
        ai[8] = 274;
        ai[9] = 266;
        ai[10] = 424;
        ai[11] = 420;
        ai[12] = 418;
        ai[13] = 404;
        ai[14] = 402;
        ai[15] = 394;
        ai[16] = 360;
        ai[17] = 356;
        ai[18] = 354;
        ai[19] = 308;
        ai[20] = 282;
        ai[21] = 344;
        ai[22] = 332;
        ai[23] = 326;
        ai[24] = 300;
        ai[25] = 278;
        ai[26] = 436;
        ai[27] = 434;
        ai[28] = 428;
        ai[29] = 422;
        ai[30] = 406;
        ai[31] = 410;
        ai[32] = 364;
        ai[33] = 358;
        ai[34] = 310;
        ai[35] = 314;
        ai[36] = 302;
        ai[37] = 468;
        ai[38] = 466;
        ai[39] = 458;
        ai[40] = 366;
        ai[41] = 374;
        ai[42] = 430;
        ai[43] = 294;
        ai[44] = 474;
        ai[45] = 470;
        ai[46] = 306;
        ai[47] = 350;
        CHARACTER_ENCODINGS = ai;
        ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
    }
}
