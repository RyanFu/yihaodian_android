// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, SMSParsedResult

final class SMSMMSResultParser extends ResultParser
{

    private SMSMMSResultParser()
    {
    }

    private static void addNumberVia(Vector vector, Vector vector1, String s)
    {
        int i = s.indexOf(';');
        if(i < 0)
        {
            vector.addElement(s);
            vector1.addElement(null);
        } else
        {
            vector.addElement(s.substring(0, i));
            String s1 = s.substring(i + 1);
            String s2;
            if(s1.startsWith("via="))
                s2 = s1.substring(4);
            else
                s2 = null;
            vector1.addElement(s2);
        }
    }

    public static SMSParsedResult parse(Result result)
    {
        String s = result.getText();
        if(s != null) goto _L2; else goto _L1
_L1:
        SMSParsedResult smsparsedresult = null;
_L4:
        return smsparsedresult;
_L2:
        if(!s.startsWith("sms:") && !s.startsWith("SMS:") && !s.startsWith("mms:") && !s.startsWith("MMS:"))
        {
            smsparsedresult = null;
            continue; /* Loop/switch isn't completed */
        }
        Hashtable hashtable = parseNameValuePairs(s);
        String s1 = null;
        boolean flag = false;
        String s2;
        int i;
        String s3;
        Vector vector;
        Vector vector1;
        int j;
        int k;
        if(hashtable != null && !hashtable.isEmpty())
        {
            String s4 = (String)hashtable.get("subject");
            s1 = (String)hashtable.get("body");
            s2 = s4;
            flag = true;
        } else
        {
            s2 = null;
        }
        i = s.indexOf('?', 4);
        if(i < 0 || !flag)
            s3 = s.substring(4);
        else
            s3 = s.substring(4, i);
        vector = new Vector(1);
        vector1 = new Vector(1);
        j = -1;
label0:
        {
            k = s3.indexOf(',', j + 1);
            if(k > j)
                break label0;
            addNumberVia(vector, vector1, s3.substring(j + 1));
            smsparsedresult = new SMSParsedResult(toStringArray(vector), toStringArray(vector1), s2, s1);
        }
        if(true) goto _L4; else goto _L3
_L3:
        addNumberVia(vector, vector1, s3.substring(j + 1, k));
        j = k;
        break MISSING_BLOCK_LABEL_156;
        if(true) goto _L4; else goto _L5
_L5:
    }
}
