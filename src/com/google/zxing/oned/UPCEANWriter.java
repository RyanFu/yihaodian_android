// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public abstract class UPCEANWriter
    implements Writer
{

    public UPCEANWriter()
    {
    }

    protected static int appendPattern(byte abyte0[], int i, int ai[], int j)
    {
        int k;
        int l;
        int i1;
        if(j != 0 && j != 1)
            throw new IllegalArgumentException((new StringBuilder("startColor must be either 0 or 1, but got: ")).append(j).toString());
        k = (byte)j;
        l = 0;
        i1 = 0;
_L2:
        if(i1 >= ai.length)
            return l;
        int j1 = 0;
        do
        {
label0:
            {
                if(j1 < ai[i1])
                    break label0;
                k ^= 1;
                i1++;
            }
            if(true)
                continue;
            abyte0[i] = k;
            i++;
            l++;
            j1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static BitMatrix renderResult(byte abyte0[], int i, int j)
    {
        int k = abyte0.length;
        int l = k + (UPCEANReader.START_END_PATTERN.length << 1);
        int i1 = Math.max(i, l);
        int j1 = Math.max(1, j);
        int k1 = i1 / l;
        int l1 = (i1 - k * k1) / 2;
        BitMatrix bitmatrix = new BitMatrix(i1, j1);
        int i2 = 0;
        int j2 = l1;
        do
        {
            if(i2 >= k)
                return bitmatrix;
            if(abyte0[i2] == 1)
                bitmatrix.setRegion(j2, 0, k1, j1);
            i2++;
            j2 += k1;
        } while(true);
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException
    {
        return encode(s, barcodeformat, i, j, null);
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("Found empty contents");
        if(i < 0 || j < 0)
            throw new IllegalArgumentException((new StringBuilder("Requested dimensions are too small: ")).append(i).append('x').append(j).toString());
        else
            return renderResult(encode(s), i, j);
    }

    public abstract byte[] encode(String s);
}
