// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, ITFReader

public final class ITFWriter extends UPCEANWriter
{

    public ITFWriter()
    {
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.ITF)
            throw new IllegalArgumentException((new StringBuilder("Can only encode ITF, but got ")).append(barcodeformat).toString());
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    @Override
	public byte[] encode(String s)
    {
        int i;
        byte abyte0[];
        int j;
        int k;
        i = s.length();
        if(i > 80)
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
        abyte0 = new byte[9 + i * 9];
        int ai[] = new int[4];
        ai[0] = 1;
        ai[1] = 1;
        ai[2] = 1;
        ai[3] = 1;
        j = appendPattern(abyte0, 0, ai, 1);
        k = 0;
_L2:
        if(k >= i)
        {
            int ai2[] = new int[3];
            ai2[0] = 3;
            ai2[1] = 1;
            ai2[2] = 1;
            j + appendPattern(abyte0, j, ai2, 1);
            return abyte0;
        }
        int l = Character.digit(s.charAt(k), 10);
        int i1 = Character.digit(s.charAt(k + 1), 10);
        int ai1[] = new int[18];
        int j1 = 0;
        do
        {
label0:
            {
                if(j1 < 5)
                    break label0;
                j += appendPattern(abyte0, j, ai1, 1);
                k += 2;
            }
            if(true)
                continue;
            ai1[j1 << 1] = ITFReader.PATTERNS[l][j1];
            ai1[1 + (j1 << 1)] = ITFReader.PATTERNS[i1][j1];
            j1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }
}
