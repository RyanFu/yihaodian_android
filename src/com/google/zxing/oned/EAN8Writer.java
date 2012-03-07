// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, UPCEANReader

public final class EAN8Writer extends UPCEANWriter
{

    public EAN8Writer()
    {
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.EAN_8)
            throw new IllegalArgumentException((new StringBuilder("Can only encode EAN_8, but got ")).append(barcodeformat).toString());
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    @Override
	public byte[] encode(String s)
    {
        byte abyte0[];
        int i;
        int j;
        if(s.length() != 8)
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be 8 digits long, but got ")).append(s.length()).toString());
        abyte0 = new byte[67];
        i = 0 + appendPattern(abyte0, 0, UPCEANReader.START_END_PATTERN, 1);
        j = 0;
_L3:
        if(j <= 3) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        l = i + appendPattern(abyte0, i, UPCEANReader.MIDDLE_PATTERN, 0);
        i1 = 4;
_L4:
        if(i1 > 7)
        {
            l + appendPattern(abyte0, l, UPCEANReader.START_END_PATTERN, 1);
            return abyte0;
        }
        break MISSING_BLOCK_LABEL_137;
_L2:
        int k = Integer.parseInt(s.substring(j, j + 1));
        i += appendPattern(abyte0, i, UPCEANReader.L_PATTERNS[k], 0);
        j++;
          goto _L3
        int j1 = Integer.parseInt(s.substring(i1, i1 + 1));
        l += appendPattern(abyte0, l, UPCEANReader.L_PATTERNS[j1], 1);
        i1++;
          goto _L4
    }

    private static final int codeWidth = 67;
}
