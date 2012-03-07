// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public final class EAN8Reader extends UPCEANReader
{

    public EAN8Reader()
    {
    }

    @Override
	protected int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException
    {
        int ai1[];
        int i;
        int j;
        int k;
        ai1 = decodeMiddleCounters;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        i = bitarray.getSize();
        j = ai[1];
        k = 0;
_L1:
        int l;
        int i1;
        if(k >= 4 || j >= i)
        {
            l = findGuardPattern(bitarray, j, true, MIDDLE_PATTERN)[1];
            i1 = 0;
            break MISSING_BLOCK_LABEL_70;
        }
        stringbuffer.append((char)(48 + decodeDigit(bitarray, ai1, j, L_PATTERNS)));
        k1 = 0;
_L2:
label0:
        {
            if(k1 < ai1.length)
                break label0;
            k++;
        }
          goto _L1
        j += ai1[k1];
        k1++;
          goto _L2
_L4:
        int j1;
        int k1;
        if(i1 >= 4 || l >= i)
            return l;
        stringbuffer.append((char)(48 + decodeDigit(bitarray, ai1, l, L_PATTERNS)));
        j1 = 0;
_L5:
label1:
        {
            if(j1 < ai1.length)
                break label1;
            i1++;
        }
        if(true) goto _L4; else goto _L3
_L3:
        l += ai1[j1];
        j1++;
          goto _L5
    }

    @Override
	BarcodeFormat getBarcodeFormat()
    {
        return BarcodeFormat.EAN_8;
    }

    private final int decodeMiddleCounters[] = new int[4];
}
