// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, EAN13Reader, EAN8Reader, UPCEReader, 
//            UPCAReader, UPCEANReader

public final class MultiFormatUPCEANReader extends OneDReader
{

    public MultiFormatUPCEANReader(Hashtable hashtable)
    {
        Vector vector;
        if(hashtable == null)
            vector = null;
        else
            vector = (Vector)hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        readers = new Vector();
        if(vector == null) goto _L2; else goto _L1
_L1:
        if(!vector.contains(BarcodeFormat.EAN_13)) goto _L4; else goto _L3
_L3:
        readers.addElement(new EAN13Reader());
_L6:
        if(vector.contains(BarcodeFormat.EAN_8))
            readers.addElement(new EAN8Reader());
        if(vector.contains(BarcodeFormat.UPC_E))
            readers.addElement(new UPCEReader());
_L2:
        if(readers.isEmpty())
        {
            readers.addElement(new EAN13Reader());
            readers.addElement(new EAN8Reader());
            readers.addElement(new UPCEReader());
        }
        return;
_L4:
        if(vector.contains(BarcodeFormat.UPC_A))
            readers.addElement(new UPCAReader());
        if(true) goto _L6; else goto _L5
_L5:
    }

    @Override
	public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        int ai[];
        int j;
        int k;
        ai = UPCEANReader.findStartGuardPattern(bitarray);
        j = readers.size();
        k = 0;
_L2:
        UPCEANReader upceanreader;
        if(k >= j)
            throw NotFoundException.getNotFoundInstance();
        upceanreader = (UPCEANReader)readers.elementAt(k);
        Result result = upceanreader.decodeRow(i, bitarray, ai, hashtable);
        ReaderException readerexception;
        boolean flag;
        Vector vector;
        boolean flag1;
        Result result1;
        if(BarcodeFormat.EAN_13.equals(result.getBarcodeFormat()) && result.getText().charAt(0) == '0')
            flag = true;
        else
            flag = false;
        if(hashtable == null)
            vector = null;
        else
            vector = (Vector)hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        if(vector != null && !vector.contains(BarcodeFormat.UPC_A))
            flag1 = false;
        else
            flag1 = true;
        if(flag && flag1)
            result1 = new Result(result.getText().substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
        else
            result1 = result;
        return result1;
        readerexception;
        k++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	public void reset()
    {
        int i = readers.size();
        int j = 0;
        do
        {
            if(j >= i)
                return;
            ((Reader)readers.elementAt(j)).reset();
            j++;
        } while(true);
    }

    private final Vector readers;
}
