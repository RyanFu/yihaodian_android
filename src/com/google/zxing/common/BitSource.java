// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


public final class BitSource
{

    public BitSource(byte abyte0[])
    {
        bytes = abyte0;
    }

    public int available()
    {
        return 8 * (bytes.length - byteOffset) - bitOffset;
    }

    public int readBits(int i)
    {
        int j;
        if(i < 1 || i > 32)
            throw new IllegalArgumentException();
        j = 0;
        if(bitOffset > 0)
        {
            int i1 = 8 - bitOffset;
            int k;
            int l;
            int j1;
            int k1;
            if(i < i1)
                j1 = i;
            else
                j1 = i1;
            k1 = i1 - j1;
            j = ((255 >> 8 - j1) << k1 & bytes[byteOffset]) >> k1;
            i -= j1;
            bitOffset = j1 + bitOffset;
            if(bitOffset == 8)
            {
                bitOffset = 0;
                byteOffset = 1 + byteOffset;
            }
        }
        if(i <= 0) goto _L2; else goto _L1
_L1:
        if(i >= 8) goto _L4; else goto _L3
_L3:
        if(i > 0)
        {
            k = 8 - i;
            l = (255 >> k) << k;
            j = j << i | (l & bytes[byteOffset]) >> k;
            bitOffset = i + bitOffset;
        }
_L2:
        return j;
_L4:
        j = j << 8 | 0xff & bytes[byteOffset];
        byteOffset = 1 + byteOffset;
        i -= 8;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private int bitOffset;
    private int byteOffset;
    private final byte bytes[];
}
