// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.multi:
//            MultipleBarcodeReader

public final class GenericMultipleBarcodeReader
    implements MultipleBarcodeReader
{

    public GenericMultipleBarcodeReader(Reader reader)
    {
        _flddelegate = reader;
    }

    private void doDecodeMultiple(BinaryBitmap binarybitmap, Hashtable hashtable, Vector vector, int i, int j)
    {
        Result result = _flddelegate.decode(binarybitmap, hashtable);
        boolean flag;
        int k;
        flag = false;
        k = 0;
_L3:
        ReaderException readerexception;
        if(k < vector.size())
        {
label0:
            {
                if(!((Result)vector.elementAt(k)).getText().equals(result.getText()))
                    break label0;
                flag = true;
            }
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        return;
        readerexception;
          goto _L1
        k++;
          goto _L3
_L2:
        ResultPoint aresultpoint[];
        vector.addElement(translateResultPoints(result, i, j));
        aresultpoint = result.getResultPoints();
        if(aresultpoint == null || aresultpoint.length == 0) goto _L1; else goto _L4
_L4:
        int l;
        int i1;
        float f;
        float f1;
        float f2;
        float f3;
        int j1;
        l = binarybitmap.getWidth();
        i1 = binarybitmap.getHeight();
        f = l;
        f1 = i1;
        f2 = 0.0F;
        f3 = 0.0F;
        j1 = 0;
_L5:
label1:
        {
            if(j1 < aresultpoint.length)
                break label1;
            if(f > 100F)
                doDecodeMultiple(binarybitmap.crop(0, 0, (int)f, i1), hashtable, vector, i, j);
            if(f1 > 100F)
                doDecodeMultiple(binarybitmap.crop(0, 0, l, (int)f1), hashtable, vector, i, j);
            if(f2 < (l - 100))
                doDecodeMultiple(binarybitmap.crop((int)f2, 0, l - (int)f2, i1), hashtable, vector, i + (int)f2, j);
            if(f3 < (i1 - 100))
                doDecodeMultiple(binarybitmap.crop(0, (int)f3, l, i1 - (int)f3), hashtable, vector, i, j + (int)f3);
        }
          goto _L1
        ResultPoint resultpoint = aresultpoint[j1];
        float f4 = resultpoint.getX();
        float f5 = resultpoint.getY();
        if(f4 < f)
            f = f4;
        if(f5 < f1)
            f1 = f5;
        if(f4 > f2)
            f2 = f4;
        if(f5 > f3)
            f3 = f5;
        j1++;
          goto _L5
    }

    private static Result translateResultPoints(Result result, int i, int j)
    {
        ResultPoint aresultpoint[] = result.getResultPoints();
        ResultPoint aresultpoint1[] = new ResultPoint[aresultpoint.length];
        int k = 0;
        do
        {
            if(k >= aresultpoint.length)
                return new Result(result.getText(), result.getRawBytes(), aresultpoint1, result.getBarcodeFormat());
            ResultPoint resultpoint = aresultpoint[k];
            aresultpoint1[k] = new ResultPoint(resultpoint.getX() + i, resultpoint.getY() + j);
            k++;
        } while(true);
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
        Vector vector = new Vector();
        doDecodeMultiple(binarybitmap, hashtable, vector, 0, 0);
        if(vector.isEmpty())
            throw NotFoundException.getNotFoundInstance();
        int i = vector.size();
        Result aresult[] = new Result[i];
        int j = 0;
        do
        {
            if(j >= i)
                return aresult;
            aresult[j] = (Result)vector.elementAt(j);
            j++;
        } while(true);
    }

    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader _flddelegate;
}
