// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, EAN13Reader, UPCEANReader

public final class EAN13Writer extends UPCEANWriter
{

    public EAN13Writer()
    {
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.EAN_13)
            throw new IllegalArgumentException((new StringBuilder("Can only encode EAN_13, but got ")).append(barcodeformat).toString());
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    @Override
	public byte[] encode(String s)
    {
        int j;
        byte abyte0[];
        int k;
        int l;
        if(s.length() != 13)
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 13 digits long, but got ")).append(s.length()).toString());
        int i = Integer.parseInt(s.substring(0, 1));
        j = EAN13Reader.FIRST_DIGIT_ENCODINGS[i];
        abyte0 = new byte[95];
        k = 0 + appendPattern(abyte0, 0, UPCEANReader.START_END_PATTERN, 1);
        l = 1;
_L3:
        if(l <= 6) goto _L2; else goto _L1
_L1:
        int j1;
        int k1;
        j1 = k + appendPattern(abyte0, k, UPCEANReader.MIDDLE_PATTERN, 0);
        k1 = 7;
_L4:
        if(k1 > 12)
        {
            j1 + appendPattern(abyte0, j1, UPCEANReader.START_END_PATTERN, 1);
            return abyte0;
        }
        break MISSING_BLOCK_LABEL_183;
_L2:
        int i1 = Integer.parseInt(s.substring(l, l + 1));
        if((1 & j >> 6 - l) == 1)
            i1 += 10;
        k += appendPattern(abyte0, k, UPCEANReader.L_AND_G_PATTERNS[i1], 0);
        l++;
          goto _L3
        int l1 = Integer.parseInt(s.substring(k1, k1 + 1));
        j1 += appendPattern(abyte0, j1, UPCEANReader.L_PATTERNS[l1], 1);
        k1++;
          goto _L4
    }

    private static final int codeWidth = 95;
}
