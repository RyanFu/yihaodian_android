// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, AddressBookParsedResult

final class AddressBookAUResultParser extends ResultParser
{

    AddressBookAUResultParser()
    {
    }

    private static String[] matchMultipleValuePrefix(String s, int i, String s1, boolean flag)
    {
        Vector vector;
        int j;
        vector = null;
        j = 1;
_L5:
        if(j <= i) goto _L2; else goto _L1
_L1:
        String s2;
        String as[];
        if(vector == null)
            as = null;
        else
            as = toStringArray(vector);
        return as;
_L2:
        if((s2 = matchSinglePrefixedField((new StringBuilder(String.valueOf(s))).append(j).append(':').toString(), s1, '\r', flag)) == null) goto _L1; else goto _L3
_L3:
        if(vector == null)
            vector = new Vector(i);
        vector.addElement(s2);
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static AddressBookParsedResult parse(Result result)
    {
        String s = result.getText();
        AddressBookParsedResult addressbookparsedresult;
        if(s == null || s.indexOf("MEMORY") < 0 || s.indexOf("\r\n") < 0)
        {
            addressbookparsedresult = null;
        } else
        {
            String s1 = matchSinglePrefixedField("NAME1:", s, '\r', true);
            String s2 = matchSinglePrefixedField("NAME2:", s, '\r', true);
            String as[] = matchMultipleValuePrefix("TEL", 3, s, true);
            String as1[] = matchMultipleValuePrefix("MAIL", 3, s, true);
            String s3 = matchSinglePrefixedField("MEMORY:", s, '\r', false);
            String s4 = matchSinglePrefixedField("ADD:", s, '\r', true);
            String as3[];
            if(s4 == null)
            {
                as3 = null;
            } else
            {
                String as2[] = new String[1];
                as2[0] = s4;
                as3 = as2;
            }
            addressbookparsedresult = new AddressBookParsedResult(maybeWrap(s1), s2, as, as1, s3, as3, null, null, null, null);
        }
        return addressbookparsedresult;
    }
}
