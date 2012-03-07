// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

final class DataBlock
{

    private DataBlock(int i, byte abyte0[])
    {
        numDataCodewords = i;
        codewords = abyte0;
    }

    static DataBlock[] getDataBlocks(byte abyte0[], Version version)
    {
        Version.ECBlocks ecblocks;
        Version.ECB aecb[];
        DataBlock adatablock[];
        int k;
        int l;
        int j2;
        int l2;
        int i3;
        boolean flag;
        int j4;
        int k4;
        int k5;
        int l5;
        ecblocks = version.getECBlocks();
        int i = 0;
        aecb = ecblocks.getECBlocks();
        int j = 0;
        do
        {
            int k2;
            int j5;
            if(j >= aecb.length)
            {
                adatablock = new DataBlock[i];
                k = 0;
                l = 0;
                break MISSING_BLOCK_LABEL_36;
            }
            i += aecb[j].getCount();
            j++;
        } while(true);
_L1:
        if(k >= aecb.length)
        {
            j2 = adatablock[0].codewords.length - ecblocks.getECCodewords();
            k2 = j2 - 1;
            l2 = 0;
            i3 = 0;
            break MISSING_BLOCK_LABEL_71;
        }
        ecb = aecb[k];
        i1 = 0;
        j1 = l;
_L2:
label0:
        {
            if(i1 < ecb.getCount())
                break label0;
            k++;
            l = j1;
        }
          goto _L1
        int k1 = ecb.getDataCodewords();
        int l1 = k1 + ecblocks.getECCodewords();
        int i2 = j1 + 1;
        adatablock[j1] = new DataBlock(k1, new byte[l1]);
        i1++;
        j1 = i2;
          goto _L2
_L7:
        if(l2 >= k2)
        {
            Version.ECB ecb;
            int i1;
            int j1;
            int j3;
            int k3;
            byte abyte1[];
            int l3;
            int i4;
            if(version.getVersionNumber() == 24)
                flag = true;
            else
                flag = false;
            if(flag)
                i4 = 8;
            else
                i4 = l;
            j4 = 0;
            k4 = i3;
            break MISSING_BLOCK_LABEL_106;
        }
        j3 = 0;
        k3 = i3;
_L5:
label1:
        {
            if(j3 < l)
                break label1;
            l2++;
            i3 = k3;
        }
          goto _L3
        abyte1 = adatablock[j3].codewords;
        l3 = k3 + 1;
        abyte1[l2] = abyte0[k3];
        j3++;
        k3 = l3;
        if(true) goto _L5; else goto _L4
_L4:
        do
        {
            if(j4 >= i4)
            {
                j5 = adatablock[0].codewords.length;
                k5 = j2;
                l5 = k4;
                break MISSING_BLOCK_LABEL_131;
            }
            byte abyte2[] = adatablock[j4].codewords;
            int l4 = j2 - 1;
            int i5 = k4 + 1;
            abyte2[l4] = abyte0[k4];
            j4++;
            k4 = i5;
        } while(true);
_L3:
        if(true) goto _L7; else goto _L6
_L6:
        int i6;
        int j6;
        int k6;
        byte abyte3[];
        int l6;
        if(k5 >= j5)
            if(l5 != abyte0.length)
                throw new IllegalArgumentException();
            else
                return adatablock;
        i6 = l5;
        j6 = 0;
_L8:
label2:
        {
            if(j6 < l)
                break label2;
            k5++;
            l5 = i6;
        }
          goto _L6
        if(flag && j6 > 7)
            k6 = k5 - 1;
        else
            k6 = k5;
        abyte3 = adatablock[j6].codewords;
        l6 = i6 + 1;
        abyte3[k6] = abyte0[i6];
        j6++;
        i6 = l6;
          goto _L8
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
