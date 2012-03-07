// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN13Reader extends UPCEANReader
{

    public EAN13Reader()
    {
    }

    private static void determineFirstDigit(StringBuffer stringbuffer, int i)
        throws NotFoundException
    {
        int j = 0;
        do
        {
            if(j >= 10)
                throw NotFoundException.getNotFoundInstance();
            if(i == FIRST_DIGIT_ENCODINGS[j])
            {
                stringbuffer.insert(0, (char)(j + 48));
                return;
            }
            j++;
        } while(true);
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
_L1:
        int i1;
        int j1;
        if(l >= 6 || j >= i)
        {
            determineFirstDigit(stringbuffer, k);
            i1 = findGuardPattern(bitarray, j, true, MIDDLE_PATTERN)[1];
            j1 = 0;
            break MISSING_BLOCK_LABEL_80;
        }
        l1 = decodeDigit(bitarray, ai1, j, L_AND_G_PATTERNS);
        stringbuffer.append((char)(48 + l1 % 10));
        i2 = 0;
_L2:
label0:
        {
            if(i2 < ai1.length)
                break label0;
            if(l1 >= 10)
                k |= 1 << 5 - l;
            l++;
        }
          goto _L1
        j += ai1[i2];
        i2++;
          goto _L2
_L4:
        int k1;
        int l1;
        int i2;
        if(j1 >= 6 || i1 >= i)
            return i1;
        stringbuffer.append((char)(48 + decodeDigit(bitarray, ai1, i1, L_PATTERNS)));
        k1 = 0;
_L5:
label1:
        {
            if(k1 < ai1.length)
                break label1;
            j1++;
        }
        if(true) goto _L4; else goto _L3
_L3:
        i1 += ai1[k1];
        k1++;
          goto _L5
    }

    @Override
	BarcodeFormat getBarcodeFormat()
    {
        return BarcodeFormat.EAN_13;
    }

    static final int FIRST_DIGIT_ENCODINGS[];
    private final int decodeMiddleCounters[] = new int[4];

    static 
    {
        int ai[] = new int[10];
        ai[1] = 11;
        ai[2] = 13;
        ai[3] = 14;
        ai[4] = 19;
        ai[5] = 25;
        ai[6] = 28;
        ai[7] = 21;
        ai[8] = 22;
        ai[9] = 26;
        FIRST_DIGIT_ENCODINGS = ai;
    }
}
