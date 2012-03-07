// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Hashtable;

public final class QRCodeWriter
    implements Writer
{

    public QRCodeWriter()
    {
    }

    private static BitMatrix renderResult(QRCode qrcode, int i, int j)
    {
        ByteMatrix bytematrix;
        int k;
        int l;
        int i2;
        int j2;
        BitMatrix bitmatrix;
        int l2;
        int i3;
        bytematrix = qrcode.getMatrix();
        k = bytematrix.getWidth();
        l = bytematrix.getHeight();
        int i1 = k + 8;
        int j1 = l + 8;
        int k1 = Math.max(i, i1);
        int l1 = Math.max(j, j1);
        i2 = Math.min(k1 / i1, l1 / j1);
        j2 = (k1 - k * i2) / 2;
        int k2 = (l1 - l * i2) / 2;
        bitmatrix = new BitMatrix(k1, l1);
        l2 = k2;
        i3 = 0;
_L2:
        if(i3 >= l)
            return bitmatrix;
        int j3 = 0;
        int k3 = j2;
        do
        {
label0:
            {
                if(j3 < k)
                    break label0;
                int l3 = i3 + 1;
                l2 += i2;
                i3 = l3;
            }
            if(true)
                continue;
            if(bytematrix.get(j3, i3) == 1)
                bitmatrix.setRegion(k3, l2, i2, i2);
            j3++;
            k3 += i2;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException
    {
        return encode(s, barcodeformat, i, j, null);
    }

    @Override
	public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("Found empty contents");
        if(barcodeformat != BarcodeFormat.QR_CODE)
            throw new IllegalArgumentException((new StringBuilder("Can only encode QR_CODE, but got ")).append(barcodeformat).toString());
        if(i < 0 || j < 0)
            throw new IllegalArgumentException((new StringBuilder("Requested dimensions are too small: ")).append(i).append('x').append(j).toString());
        ErrorCorrectionLevel errorcorrectionlevel = ErrorCorrectionLevel.L;
        if(hashtable != null)
        {
            ErrorCorrectionLevel errorcorrectionlevel1 = (ErrorCorrectionLevel)hashtable.get(EncodeHintType.ERROR_CORRECTION);
            if(errorcorrectionlevel1 != null)
                errorcorrectionlevel = errorcorrectionlevel1;
        }
        QRCode qrcode = new QRCode();
        Encoder.encode(s, errorcorrectionlevel, hashtable, qrcode);
        return renderResult(qrcode, i, j);
    }

    private static final int QUIET_ZONE_SIZE = 4;
}
