// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.multi.qrcode.detector:
//            MultiFinderPatternFinder

public final class MultiDetector extends Detector
{

    public MultiDetector(BitMatrix bitmatrix)
    {
        super(bitmatrix);
    }

    public DetectorResult[] detectMulti(Hashtable hashtable)
        throws NotFoundException
    {
        com.google.zxing.qrcode.detector.FinderPatternInfo afinderpatterninfo[];
        Vector vector;
        int i;
        afinderpatterninfo = (new MultiFinderPatternFinder(getImage())).findMulti(hashtable);
        if(afinderpatterninfo == null || afinderpatterninfo.length == 0)
            throw NotFoundException.getNotFoundInstance();
        vector = new Vector();
        i = 0;
_L5:
        if(i < afinderpatterninfo.length) goto _L2; else goto _L1
_L1:
        if(!vector.isEmpty()) goto _L4; else goto _L3
_L3:
        DetectorResult adetectorresult1[] = EMPTY_DETECTOR_RESULTS;
_L6:
        return adetectorresult1;
_L2:
        DetectorResult adetectorresult[];
        int j;
        try
        {
            vector.addElement(processFinderPatternInfo(afinderpatterninfo[i]));
        }
        catch(ReaderException readerexception) { }
        i++;
          goto _L5
_L4:
        adetectorresult = new DetectorResult[vector.size()];
        j = 0;
_L7:
label0:
        {
            if(j < vector.size())
                break label0;
            adetectorresult1 = adetectorresult;
        }
          goto _L6
        adetectorresult[j] = (DetectorResult)vector.elementAt(j);
        j++;
          goto _L7
    }

    private static final DetectorResult EMPTY_DETECTOR_RESULTS[] = new DetectorResult[0];

}
