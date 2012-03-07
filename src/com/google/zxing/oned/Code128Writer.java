// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, Code128Reader

public final class Code128Writer extends UPCEANWriter
{

    public Code128Writer()
    {
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.CODE_128)
            throw new IllegalArgumentException((new StringBuilder("Can only encode CODE_128, but got ")).append(barcodeformat).toString());
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    @Override
	public byte[] encode(String s)
    {
        int i;
        int j;
        int k;
        i = s.length();
        if(i > 80)
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
        j = 35;
        k = 0;
_L3:
        if(k < i) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int i1;
        int j1;
        int k1;
        abyte0 = new byte[j];
        i1 = appendPattern(abyte0, 0, Code128Reader.CODE_PATTERNS[104], 1);
        j1 = 104;
        k1 = 0;
_L5:
        if(k1 >= i)
        {
            int l1 = j1 % 103;
            int i2 = i1 + appendPattern(abyte0, i1, Code128Reader.CODE_PATTERNS[l1], 1);
            i2 + appendPattern(abyte0, i2, Code128Reader.CODE_PATTERNS[106], 1);
            return abyte0;
        }
        break MISSING_BLOCK_LABEL_176;
_L2:
        int ai[];
        int l;
        ai = Code128Reader.CODE_PATTERNS[s.charAt(k) - 32];
        l = 0;
_L4:
label0:
        {
            if(l < ai.length)
                break label0;
            k++;
        }
          goto _L3
        j += ai[l];
        l++;
          goto _L4
        j1 += (s.charAt(k1) - 32) * (k1 + 1);
        i1 += appendPattern(abyte0, i1, Code128Reader.CODE_PATTERNS[s.charAt(k1) - 32], 1);
        k1++;
          goto _L5
    }
}
