// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class UPCEReader extends UPCEANReader
{

    public UPCEReader()
    {
    }

    public static String convertUPCEtoUPCA(String s)
    {
        char ac[];
        StringBuffer stringbuffer;
        char c;
        ac = new char[6];
        s.getChars(1, 7, ac, 0);
        stringbuffer = new StringBuffer(12);
        stringbuffer.append(s.charAt(0));
        c = ac[5];
        c;
        JVM INSTR tableswitch 48 52: default 76
    //                   48 114
    //                   49 114
    //                   50 114
    //                   51 147
    //                   52 173;
           goto _L1 _L2 _L2 _L2 _L3 _L4
_L1:
        stringbuffer.append(ac, 0, 5);
        stringbuffer.append("0000");
        stringbuffer.append(c);
_L6:
        stringbuffer.append(s.charAt(7));
        return stringbuffer.toString();
_L2:
        stringbuffer.append(ac, 0, 2);
        stringbuffer.append(c);
        stringbuffer.append("0000");
        stringbuffer.append(ac, 2, 3);
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuffer.append(ac, 0, 3);
        stringbuffer.append("00000");
        stringbuffer.append(ac, 3, 2);
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuffer.append(ac, 0, 4);
        stringbuffer.append("00000");
        stringbuffer.append(ac[4]);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static void determineNumSysAndCheckDigit(StringBuffer stringbuffer, int i)
        throws NotFoundException
    {
        int j = 0;
_L2:
        if(j > 1)
            throw NotFoundException.getNotFoundInstance();
        int k = 0;
        do
        {
label0:
            {
                if(k < 10)
                    break label0;
                j++;
            }
            if(true)
                continue;
            if(i == NUMSYS_AND_CHECK_DIGIT_PATTERNS[j][k])
            {
                stringbuffer.insert(0, (char)(j + 48));
                stringbuffer.append((char)(k + 48));
                return;
            }
            k++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	protected boolean checkChecksum(String s)
        throws FormatException, ChecksumException
    {
        return super.checkChecksum(convertUPCEtoUPCA(s));
    }

    @Override
	protected int[] decodeEnd(BitArray bitarray, int i)
        throws NotFoundException
    {
        return findGuardPattern(bitarray, i, true, MIDDLE_END_PATTERN);
    }

    @Override
	protected int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException
    {
        int ai1[];
        int i;
        int j;
        int k;
        int l;
        ai1 = decodeMiddleCounters;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        i = bitarray.getSize();
        j = ai[1];
        k = 0;
        l = 0;
_L2:
        if(l >= 6 || j >= i)
        {
            determineNumSysAndCheckDigit(stringbuffer, k);
            return j;
        }
        int i1 = decodeDigit(bitarray, ai1, j, L_AND_G_PATTERNS);
        stringbuffer.append((char)(48 + i1 % 10));
        int j1 = 0;
        do
        {
label0:
            {
                if(j1 < ai1.length)
                    break label0;
                if(i1 >= 10)
                    k |= 1 << 5 - l;
                l++;
            }
            if(true)
                continue;
            j += ai1[j1];
            j1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	BarcodeFormat getBarcodeFormat()
    {
        return BarcodeFormat.UPC_E;
    }

    private static final int MIDDLE_END_PATTERN[];
    private static final int NUMSYS_AND_CHECK_DIGIT_PATTERNS[][];
    private final int decodeMiddleCounters[] = new int[4];

    static 
    {
        int ai[] = new int[6];
        ai[0] = 1;
        ai[1] = 1;
        ai[2] = 1;
        ai[3] = 1;
        ai[4] = 1;
        ai[5] = 1;
        MIDDLE_END_PATTERN = ai;
        int ai1[][] = new int[2][];
        int ai2[] = new int[10];
        ai2[0] = 56;
        ai2[1] = 52;
        ai2[2] = 50;
        ai2[3] = 49;
        ai2[4] = 44;
        ai2[5] = 38;
        ai2[6] = 35;
        ai2[7] = 42;
        ai2[8] = 41;
        ai2[9] = 37;
        ai1[0] = ai2;
        int ai3[] = new int[10];
        ai3[0] = 7;
        ai3[1] = 11;
        ai3[2] = 13;
        ai3[3] = 14;
        ai3[4] = 19;
        ai3[5] = 25;
        ai3[6] = 28;
        ai3[7] = 21;
        ai3[8] = 22;
        ai3[9] = 26;
        ai1[1] = ai3;
        NUMSYS_AND_CHECK_DIGIT_PATTERNS = ai1;
    }
}
