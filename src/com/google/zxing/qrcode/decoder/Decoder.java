// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.*;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            BitMatrixParser, FormatInformation, DataBlock, DecodedBitStreamParser

public final class Decoder
{

    public Decoder()
    {
        rsDecoder = new ReedSolomonDecoder(GF256.QR_CODE_FIELD);
    }

    private void correctErrors(byte abyte0[], int i)
        throws ChecksumException
    {
        int j;
        int ai[];
        int k;
        j = abyte0.length;
        ai = new int[j];
        k = 0;
_L3:
        if(k < j) goto _L2; else goto _L1
_L1:
        int i1;
        int l = abyte0.length - i;
        try
        {
            rsDecoder.decode(ai, l);
        }
        catch(ReedSolomonException reedsolomonexception)
        {
            throw ChecksumException.getChecksumInstance();
        }
        i1 = 0;
_L4:
        if(i1 >= i)
            return;
        break MISSING_BLOCK_LABEL_69;
_L2:
        ai[k] = 0xff & abyte0[k];
        k++;
          goto _L3
        abyte0[i1] = (byte)ai[i1];
        i1++;
          goto _L4
    }

    public DecoderResult decode(BitMatrix bitmatrix)
        throws ChecksumException, FormatException, NotFoundException
    {
        return decode(bitmatrix, null);
    }

    public DecoderResult decode(BitMatrix bitmatrix, Hashtable hashtable)
        throws FormatException, ChecksumException
    {
        DataBlock adatablock[];
        byte abyte0[];
        int l;
        int i1;
        BitMatrixParser bitmatrixparser = new BitMatrixParser(bitmatrix);
        Version version = bitmatrixparser.readVersion();
        ErrorCorrectionLevel errorcorrectionlevel = bitmatrixparser.readFormatInformation().getErrorCorrectionLevel();
        adatablock = DataBlock.getDataBlocks(bitmatrixparser.readCodewords(), version, errorcorrectionlevel);
        int i = 0;
        int j = 0;
        do
        {
            int k = adatablock.length;
            int j1;
            if(j >= k)
            {
                abyte0 = new byte[i];
                l = 0;
                i1 = 0;
                break MISSING_BLOCK_LABEL_67;
            }
            i += adatablock[j].getNumDataCodewords();
            j++;
        } while(true);
_L2:
        byte abyte1[];
        int k1;
        int l1;
        int i2;
        j1 = adatablock.length;
        if(i1 >= j1)
            return DecodedBitStreamParser.decode(abyte0, version, errorcorrectionlevel, hashtable);
        DataBlock datablock = adatablock[i1];
        abyte1 = datablock.getCodewords();
        k1 = datablock.getNumDataCodewords();
        correctErrors(abyte1, k1);
        l1 = 0;
        i2 = l;
_L3:
label0:
        {
            if(l1 < k1)
                break label0;
            i1++;
            l = i2;
        }
        if(true) goto _L2; else goto _L1
_L1:
        int j2 = i2 + 1;
        abyte0[i2] = abyte1[l1];
        l1++;
        i2 = j2;
          goto _L3
    }

    public DecoderResult decode(boolean aflag[][])
        throws ChecksumException, FormatException, NotFoundException
    {
        return decode(aflag, null);
    }

    public DecoderResult decode(boolean aflag[][], Hashtable hashtable)
        throws ChecksumException, FormatException, NotFoundException
    {
        int i;
        BitMatrix bitmatrix;
        int j;
        i = aflag.length;
        bitmatrix = new BitMatrix(i);
        j = 0;
_L2:
        if(j >= i)
            return decode(bitmatrix, hashtable);
        int k = 0;
        do
        {
label0:
            {
                if(k < i)
                    break label0;
                j++;
            }
            if(true)
                continue;
            if(aflag[j][k])
                bitmatrix.set(k, j);
            k++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final ReedSolomonDecoder rsDecoder;
}
