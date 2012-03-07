// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, Code39Reader

public final class Code39Writer extends UPCEANWriter
{

    public Code39Writer()
    {
    }

    private static void toIntArray(int i, int ai[])
    {
        int j = 0;
        do
        {
            if(j >= 9)
                return;
            int k;
            if((i & 1 << j) == 0)
                k = 1;
            else
                k = 2;
            ai[j] = k;
            j++;
        } while(true);
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.CODE_39)
            throw new IllegalArgumentException((new StringBuilder("Can only encode CODE_39, but got ")).append(barcodeformat).toString());
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    @Override
	public byte[] encode(String s)
    {
        int i;
        int ai[];
        int j;
        int k;
        i = s.length();
        if(i > 80)
            throw new IllegalArgumentException((new StringBuilder("Requested contents should be less than 80 digits long, but got ")).append(i).toString());
        ai = new int[9];
        j = i + 25;
        k = 0;
_L3:
        if(k < i) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int ai1[];
        int k1;
        int l1;
        abyte0 = new byte[j];
        toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], ai);
        int j1 = appendPattern(abyte0, 0, ai, 1);
        ai1 = new int[1];
        ai1[0] = 1;
        k1 = j1 + appendPattern(abyte0, j1, ai1, 0);
        l1 = i - 1;
_L5:
        if(l1 < 0)
        {
            toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], ai);
            k1 + appendPattern(abyte0, k1, ai, 1);
            return abyte0;
        }
        break MISSING_BLOCK_LABEL_196;
_L2:
        int i1;
        int l = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(s.charAt(k));
        toIntArray(Code39Reader.CHARACTER_ENCODINGS[l], ai);
        i1 = 0;
_L4:
label0:
        {
            if(i1 < ai.length)
                break label0;
            k++;
        }
          goto _L3
        j += ai[i1];
        i1++;
          goto _L4
        int i2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(s.charAt(l1));
        toIntArray(Code39Reader.CHARACTER_ENCODINGS[i2], ai);
        int j2 = k1 + appendPattern(abyte0, k1, ai, 1);
        k1 = j2 + appendPattern(abyte0, j2, ai1, 0);
        l1--;
          goto _L5
    }
}
