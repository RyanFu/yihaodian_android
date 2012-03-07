// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

public final class DataMatrixReader
    implements Reader
{

    public DataMatrixReader()
    {
    }

    private static BitMatrix extractPureBits(BitMatrix bitmatrix)
        throws NotFoundException
    {
        int j1;
        int i2;
        int k2;
        int l2;
        BitMatrix bitmatrix1;
        int i3;
        int i = bitmatrix.getHeight();
        int j = bitmatrix.getWidth();
        int k = Math.min(i, j);
        int ai[] = bitmatrix.getTopLeftOnBit();
        if(ai == null)
            throw NotFoundException.getNotFoundInstance();
        int l = ai[0];
        int i1 = ai[1];
        do
        {
            if(l >= k || i1 >= k || !bitmatrix.get(l, i1))
            {
                if(l == k)
                    throw NotFoundException.getNotFoundInstance();
                break;
            }
            l++;
        } while(true);
        j1 = l - ai[0];
        int k1 = j - 1;
        do
        {
            if(k1 < 0 || bitmatrix.get(k1, i1))
            {
                if(k1 < 0)
                    throw NotFoundException.getNotFoundInstance();
                break;
            }
            k1--;
        } while(true);
        int l1 = k1 + 1;
        if((l1 - l) % j1 != 0)
            throw NotFoundException.getNotFoundInstance();
        i2 = 2 + (l1 - l) / j1;
        int j2 = i1 + j1;
        k2 = l - (j1 >> 1);
        l2 = j2 - (j1 >> 1);
        if(k2 + j1 * (i2 - 1) >= j || l2 + j1 * (i2 - 1) >= i)
            throw NotFoundException.getNotFoundInstance();
        bitmatrix1 = new BitMatrix(i2);
        i3 = 0;
_L2:
        if(i3 >= i2)
            return bitmatrix1;
        int j3 = l2 + i3 * j1;
        int k3 = 0;
        do
        {
label0:
            {
                if(k3 < i2)
                    break label0;
                i3++;
            }
            if(true)
                continue;
            if(bitmatrix.get(k2 + k3 * j1, j3))
                bitmatrix1.set(k3, i3);
            k3++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, ChecksumException, FormatException
    {
        return decode(binarybitmap, null);
    }

    @Override
	public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        DecoderResult decoderresult;
        ResultPoint aresultpoint[];
        Result result;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.PURE_BARCODE))
        {
            BitMatrix bitmatrix = extractPureBits(binarybitmap.getBlackMatrix());
            decoderresult = decoder.decode(bitmatrix);
            aresultpoint = NO_POINTS;
        } else
        {
            DetectorResult detectorresult = (new Detector(binarybitmap.getBlackMatrix())).detect();
            decoderresult = decoder.decode(detectorresult.getBits());
            aresultpoint = detectorresult.getPoints();
        }
        result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.DATA_MATRIX);
        if(decoderresult.getByteSegments() != null)
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderresult.getByteSegments());
        if(decoderresult.getECLevel() != null)
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderresult.getECLevel().toString());
        return result;
    }

    @Override
	public void reset()
    {
    }

    private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

}
