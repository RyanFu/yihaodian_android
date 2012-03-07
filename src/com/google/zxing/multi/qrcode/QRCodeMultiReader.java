// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;
import java.util.Vector;

public final class QRCodeMultiReader extends QRCodeReader
    implements MultipleBarcodeReader
{

    public QRCodeMultiReader()
    {
    }

    @Override
	public Result[] decodeMultiple(BinaryBitmap binarybitmap)
        throws NotFoundException
    {
        return decodeMultiple(binarybitmap, null);
    }

    @Override
	public Result[] decodeMultiple(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException
    {
        Vector vector;
        DetectorResult adetectorresult[];
        int i;
        vector = new Vector();
        adetectorresult = (new MultiDetector(binarybitmap.getBlackMatrix())).detectMulti(hashtable);
        i = 0;
_L5:
        if(i < adetectorresult.length) goto _L2; else goto _L1
_L1:
        if(!vector.isEmpty()) goto _L4; else goto _L3
_L3:
        Result aresult1[] = EMPTY_RESULT_ARRAY;
_L6:
        return aresult1;
_L2:
        Result aresult[];
        int j;
        try
        {
            DecoderResult decoderresult = getDecoder().decode(adetectorresult[i].getBits());
            com.google.zxing.ResultPoint aresultpoint[] = adetectorresult[i].getPoints();
            Result result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.QR_CODE);
            if(decoderresult.getByteSegments() != null)
                result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderresult.getByteSegments());
            if(decoderresult.getECLevel() != null)
                result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderresult.getECLevel().toString());
            vector.addElement(result);
        }
        catch(ReaderException readerexception) { }
        i++;
          goto _L5
_L4:
        aresult = new Result[vector.size()];
        j = 0;
_L7:
label0:
        {
            if(j < vector.size())
                break label0;
            aresult1 = aresult;
        }
          goto _L6
        aresult[j] = (Result)vector.elementAt(j);
        j++;
          goto _L7
    }

    private static final Result EMPTY_RESULT_ARRAY[] = new Result[0];

}
