// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result.optional;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result.optional:
//            AbstractNDEFResultParser, NDEFRecord, NDEFTextResultParser, NDEFURIResultParser, 
//            NDEFSmartPosterParsedResult

final class NDEFSmartPosterResultParser extends AbstractNDEFResultParser
{

    NDEFSmartPosterResultParser()
    {
    }

    public static NDEFSmartPosterParsedResult parse(Result result)
    {
        NDEFRecord ndefrecord;
        NDEFSmartPosterParsedResult ndefsmartposterparsedresult;
        byte abyte0[] = result.getRawBytes();
        if(abyte0 == null)
        {
            ndefsmartposterparsedresult = null;
        } else
        {
            ndefrecord = NDEFRecord.readRecord(abyte0, 0);
            if(ndefrecord == null || !ndefrecord.isMessageBegin() || !ndefrecord.isMessageEnd())
            {
                ndefsmartposterparsedresult = null;
            } else
            {
label0:
                {
                    if(ndefrecord.getType().equals("Sp"))
                        break label0;
                    ndefsmartposterparsedresult = null;
                }
            }
        }
_L1:
        return ndefsmartposterparsedresult;
        int i;
        int j;
        NDEFRecord ndefrecord1;
        byte abyte1[];
        byte byte0;
        String s;
        String s1;
        i = 0;
        j = 0;
        ndefrecord1 = null;
        abyte1 = ndefrecord.getPayload();
        byte0 = -1;
        s = null;
        s1 = null;
_L2:
label1:
        {
            if(i < abyte1.length)
            {
                ndefrecord1 = NDEFRecord.readRecord(abyte1, i);
                if(ndefrecord1 != null)
                    break label1;
            }
            String s2;
            if(j == 0 || ndefrecord1 != null && !ndefrecord1.isMessageEnd())
                ndefsmartposterparsedresult = null;
            else
                ndefsmartposterparsedresult = new NDEFSmartPosterParsedResult(byte0, s1, s);
        }
          goto _L1
label2:
        {
            if(j != 0 || ndefrecord1.isMessageBegin())
                break label2;
            ndefsmartposterparsedresult = null;
        }
          goto _L1
        s2 = ndefrecord1.getType();
        if("T".equals(s2))
            s = NDEFTextResultParser.decodeTextPayload(ndefrecord1.getPayload())[1];
        else
        if("U".equals(s2))
            s1 = NDEFURIResultParser.decodeURIPayload(ndefrecord1.getPayload());
        else
        if("act".equals(s2))
            byte0 = ndefrecord1.getPayload()[0];
        j++;
        i += ndefrecord1.getTotalRecordLength();
          goto _L2
    }
}
