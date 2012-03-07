// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Version, ErrorCorrectionLevel

final class DataBlock
{

    private DataBlock(int i, byte abyte0[])
    {
        numDataCodewords = i;
        codewords = abyte0;
    }

    static DataBlock[] getDataBlocks(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel)
    {
        Version.ECBlocks ecblocks;
        int i;
        Version.ECB aecb[];
        int j;
        if(abyte0.length != version.getTotalCodewords())
            throw new IllegalArgumentException();
        ecblocks = version.getECBlocksForLevel(errorcorrectionlevel);
        i = 0;
        aecb = ecblocks.getECBlocks();
        j = 0;
_L6:
        if(j < aecb.length) goto _L2; else goto _L1
_L1:
        DataBlock adatablock[];
        int k;
        int l;
        adatablock = new DataBlock[i];
        k = 0;
        l = 0;
_L7:
        if(k < aecb.length) goto _L4; else goto _L3
_L3:
        int l2;
        int i3;
        int j3;
        int k3;
        int k4;
        int j5;
        int j2 = adatablock[0].codewords.length;
        int k2 = adatablock.length - 1;
        Version.ECB ecb;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int i5;
        while(k2 >= 0 && adatablock[k2].codewords.length != j2) 
            k2--;
        l2 = k2 + 1;
        i3 = j2 - ecblocks.getECCodewordsPerBlock();
        j3 = 0;
        k3 = 0;
          goto _L5
_L2:
        i += aecb[j].getCount();
        j++;
          goto _L6
_L4:
        ecb = aecb[k];
        i1 = 0;
        j1 = l;
_L8:
label0:
        {
            if(i1 < ecb.getCount())
                break label0;
            k++;
            l = j1;
        }
          goto _L7
        k1 = ecb.getDataCodewords();
        l1 = k1 + ecblocks.getECCodewordsPerBlock();
        i2 = j1 + 1;
        adatablock[j1] = new DataBlock(k1, new byte[l1]);
        i1++;
        j1 = i2;
          goto _L8
_L5:
        if(j3 >= i3)
        {
            k4 = l2;
            break MISSING_BLOCK_LABEL_118;
        }
        l3 = 0;
        i4 = k3;
_L10:
label1:
        {
            if(l3 < l)
                break label1;
            j3++;
            k3 = i4;
        }
        continue; /* Loop/switch isn't completed */
        byte abyte1[] = adatablock[l3].codewords;
        int j4 = i4 + 1;
        abyte1[j3] = abyte0[i4];
        l3++;
        i4 = j4;
        if(true) goto _L10; else goto _L9
_L9:
        do
        {
            int l3;
            int i4;
            if(k4 >= l)
            {
                i5 = adatablock[0].codewords.length;
                j5 = i3;
                break MISSING_BLOCK_LABEL_139;
            }
            byte abyte2[] = adatablock[k4].codewords;
            int l4 = k3 + 1;
            abyte2[i3] = abyte0[k3];
            k4++;
            k3 = l4;
        } while(true);
        if(true) goto _L5; else goto _L11
_L11:
        int k5;
        int l5;
        if(j5 >= i5)
            return adatablock;
        k5 = k3;
        l5 = 0;
_L13:
label2:
        {
            if(l5 < l)
                break label2;
            j5++;
            k3 = k5;
        }
        if(true) goto _L11; else goto _L12
_L12:
        int i6;
        byte abyte3[];
        int j6;
        if(l5 < l2)
            i6 = j5;
        else
            i6 = j5 + 1;
        abyte3 = adatablock[l5].codewords;
        j6 = k5 + 1;
        abyte3[i6] = abyte0[k5];
        l5++;
        k5 = j6;
          goto _L13
    }

    byte[] getCodewords()
    {
        return codewords;
    }

    int getNumDataCodewords()
    {
        return numDataCodewords;
    }

    private final byte codewords[];
    private final int numDataCodewords;
}
