// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            AbstractExpandedDecoder, GeneralAppIdDecoder

abstract class AI01decoder extends AbstractExpandedDecoder
{

    AI01decoder(BitArray bitarray)
    {
        super(bitarray);
    }

    private static void appendCheckDigit(StringBuffer stringbuffer, int i)
    {
        int j = 0;
        int k = 0;
        do
        {
            if(k >= 13)
            {
                int j1 = 10 - j % 10;
                if(j1 == 10)
                    j1 = 0;
                stringbuffer.append(j1);
                return;
            }
            int l = stringbuffer.charAt(k + i) - 48;
            int i1;
            if((k & 1) == 0)
                i1 = l * 3;
            else
                i1 = l;
            j += i1;
            k++;
        } while(true);
    }

    protected void encodeCompressedGtin(StringBuffer stringbuffer, int i)
    {
        stringbuffer.append("(01)");
        int j = stringbuffer.length();
        stringbuffer.append('9');
        encodeCompressedGtinWithoutAI(stringbuffer, i, j);
    }

    protected void encodeCompressedGtinWithoutAI(StringBuffer stringbuffer, int i, int j)
    {
        int k = 0;
        do
        {
            if(k >= 4)
            {
                appendCheckDigit(stringbuffer, j);
                return;
            }
            int l = generalDecoder.extractNumericValueFromBitArray(i + k * 10, 10);
            if(l / 100 == 0)
                stringbuffer.append('0');
            if(l / 10 == 0)
                stringbuffer.append('0');
            stringbuffer.append(l);
            k++;
        } while(true);
    }

    protected static final int gtinSize = 40;
}
