// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            FormatInformation, DataMask, Version

final class BitMatrixParser
{

    BitMatrixParser(BitMatrix bitmatrix)
        throws FormatException
    {
        int i = bitmatrix.getHeight();
        if(i < 21 || (i & 3) != 1)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            bitMatrix = bitmatrix;
            return;
        }
    }

    private int copyBit(int i, int j, int k)
    {
        int l;
        if(bitMatrix.get(i, j))
            l = 1 | k << 1;
        else
            l = k << 1;
        return l;
    }

    byte[] readCodewords()
        throws FormatException
    {
        Version version;
        int i;
        BitMatrix bitmatrix;
        boolean flag;
        byte abyte0[];
        int j;
        int k;
        int l;
        int i1;
        FormatInformation formatinformation = readFormatInformation();
        version = readVersion();
        DataMask datamask = DataMask.forReference(formatinformation.getDataMask());
        i = bitMatrix.getHeight();
        datamask.unmaskBitMatrix(bitMatrix, i);
        bitmatrix = version.buildFunctionPattern();
        flag = true;
        abyte0 = new byte[version.getTotalCodewords()];
        j = 0;
        k = 0;
        l = 0;
        i1 = i - 1;
_L1:
        int i2;
        int j2;
        if(i1 <= 0)
        {
            int k2 = version.getTotalCodewords();
            int j1;
            int k1;
            int l1;
            if(j != k2)
                throw FormatException.getFormatInstance();
            else
                return abyte0;
        }
        if(i1 == 6)
            i1--;
        j1 = 0;
label0:
        {
            if(j1 < i)
                break label0;
            flag ^= true;
            i1 -= 2;
        }
          goto _L1
        if(flag)
            k1 = i - 1 - j1;
        else
            k1 = j1;
        l1 = 0;
        i2 = j;
_L5:
label1:
        {
            if(l1 < 2)
                break label1;
            j1++;
            j = i2;
        }
        break MISSING_BLOCK_LABEL_104;
        if(bitmatrix.get(i1 - l1, k1))
            break MISSING_BLOCK_LABEL_254;
        l++;
        k <<= 1;
        if(bitMatrix.get(i1 - l1, k1))
            k |= 1;
        if(l != 8)
            break MISSING_BLOCK_LABEL_254;
        j2 = i2 + 1;
        abyte0[i2] = (byte)k;
        l = 0;
        k = 0;
_L3:
        l1++;
        i2 = j2;
        if(true)
            continue; /* Loop/switch isn't completed */
        j2 = i2;
        if(true) goto _L3; else goto _L2
_L2:
        if(true) goto _L5; else goto _L4
_L4:
    }

    FormatInformation readFormatInformation()
        throws FormatException
    {
        if(parsedFormatInfo == null) goto _L2; else goto _L1
_L1:
        FormatInformation formatinformation = parsedFormatInfo;
_L9:
        return formatinformation;
_L2:
        int i;
        int j;
        i = 0;
        j = 0;
_L10:
        if(j < 6) goto _L4; else goto _L3
_L3:
        int k;
        int l;
        k = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, i)));
        l = 5;
_L11:
        if(l >= 0) goto _L6; else goto _L5
_L5:
        int i1;
        int j1;
        int k1;
        int l1;
        i1 = bitMatrix.getHeight();
        j1 = 0;
        k1 = i1 - 8;
        l1 = i1 - 1;
_L12:
        if(l1 >= k1) goto _L8; else goto _L7
_L7:
        int i2 = i1 - 7;
_L13:
        if(i2 < i1)
            break MISSING_BLOCK_LABEL_181;
        parsedFormatInfo = FormatInformation.decodeFormatInformation(k, j1);
        if(parsedFormatInfo != null)
            formatinformation = parsedFormatInfo;
        else
            throw FormatException.getFormatInstance();
          goto _L9
_L4:
        i = copyBit(j, 8, i);
        j++;
          goto _L10
_L6:
        k = copyBit(8, l, k);
        l--;
          goto _L11
_L8:
        j1 = copyBit(l1, 8, j1);
        l1--;
          goto _L12
        j1 = copyBit(8, i2, j1);
        i2++;
          goto _L13
    }

    Version readVersion()
        throws FormatException
    {
        if(parsedVersion == null) goto _L2; else goto _L1
_L1:
        Version version = parsedVersion;
_L12:
        return version;
_L2:
        int i;
        int j;
        i = bitMatrix.getHeight();
        j = i - 17 >> 2;
        if(j > 6) goto _L4; else goto _L3
_L3:
        version = Version.getVersionForNumber(j);
          goto _L5
_L4:
        int k;
        int l;
        int i1;
        k = 0;
        l = i - 11;
        i1 = 5;
_L9:
        if(i1 >= 0) goto _L7; else goto _L6
_L6:
        parsedVersion = Version.decodeVersionInformation(k);
        if(parsedVersion == null || parsedVersion.getDimensionForVersion() != i)
            break; /* Loop/switch isn't completed */
        version = parsedVersion;
          goto _L5
_L7:
        int j1 = i - 9;
_L10:
label0:
        {
            if(j1 >= l)
                break label0;
            i1--;
        }
        if(true) goto _L9; else goto _L8
        k = copyBit(j1, i1, k);
        j1--;
          goto _L10
_L8:
        int k1;
        int l1;
        k1 = 0;
        l1 = 5;
_L13:
label1:
        {
            if(l1 >= 0)
                break label1;
            parsedVersion = Version.decodeVersionInformation(k1);
            int i2;
            if(parsedVersion != null && parsedVersion.getDimensionForVersion() == i)
                version = parsedVersion;
            else
                throw FormatException.getFormatInstance();
        }
_L5:
        if(true) goto _L12; else goto _L11
_L11:
        i2 = i - 9;
_L14:
label2:
        {
            if(i2 >= l)
                break label2;
            l1--;
        }
          goto _L13
        k1 = copyBit(l1, i2, k1);
        i2--;
          goto _L14
    }

    private final BitMatrix bitMatrix;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;
}
