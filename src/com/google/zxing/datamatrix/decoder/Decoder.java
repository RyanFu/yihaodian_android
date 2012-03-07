// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.*;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            BitMatrixParser, DataBlock, DecodedBitStreamParser

public final class Decoder
{

    public Decoder()
    {
        rsDecoder = new ReedSolomonDecoder(GF256.DATA_MATRIX_FIELD);
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
        throws FormatException, ChecksumException
    {
        DataBlock adatablock[];
        byte abyte0[];
        int k;
        int l;
        BitMatrixParser bitmatrixparser = new BitMatrixParser(bitmatrix);
        Version version = bitmatrixparser.readVersion(bitmatrix);
        adatablock = DataBlock.getDataBlocks(bitmatrixparser.readCodewords(), version);
        int i = 0;
        int j = 0;
        do
        {
            if(j >= adatablock.length)
            {
                abyte0 = new byte[i];
                k = 0;
                l = 0;
                break MISSING_BLOCK_LABEL_51;
            }
            i += adatablock[j].getNumDataCodewords();
            j++;
        } while(true);
_L2:
        byte abyte1[];
        int i1;
        int j1;
        int k1;
        if(l >= adatablock.length)
            return DecodedBitStreamParser.decode(abyte0);
        DataBlock datablock = adatablock[l];
        abyte1 = datablock.getCodewords();
        i1 = datablock.getNumDataCodewords();
        correctErrors(abyte1, i1);
        j1 = 0;
        k1 = k;
_L3:
label0:
        {
            if(j1 < i1)
                break label0;
            l++;
            k = k1;
        }
        if(true) goto _L2; else goto _L1
_L1:
        int l1 = k1 + 1;
        abyte0[k1] = abyte1[j1];
        j1++;
        k1 = l1;
          goto _L3
    }

    public DecoderResult decode(boolean aflag[][])
        throws FormatException, ChecksumException
    {
        int i;
        BitMatrix bitmatrix;
        int j;
        i = aflag.length;
        bitmatrix = new BitMatrix(i);
        j = 0;
_L2:
        if(j >= i)
            return decode(bitmatrix);
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
