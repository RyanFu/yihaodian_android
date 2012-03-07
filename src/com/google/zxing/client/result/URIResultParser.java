// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, URIParsedResult

final class URIResultParser extends ResultParser
{

    private URIResultParser()
    {
    }

    static boolean isBasicallyValidURI(String s)
    {
        boolean flag;
        int i;
        int j;
        if(s == null || s.indexOf(' ') >= 0 || s.indexOf('\n') >= 0)
        {
            flag = false;
        } else
        {
            i = s.indexOf('.');
            if(i >= s.length() - 2)
            {
                flag = false;
            } else
            {
label0:
                {
                    j = s.indexOf(':');
                    if(i >= 0 || j >= 0)
                        break label0;
                    flag = false;
                }
            }
        }
_L6:
        return flag;
        if(j < 0) goto _L2; else goto _L1
_L1:
        if(i >= 0 && i <= j) goto _L4; else goto _L3
_L3:
        int k = 0;
_L7:
        if(k < j) goto _L5; else goto _L2
_L2:
        flag = true;
          goto _L6
_L5:
label1:
        {
            char c = s.charAt(k);
            if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')
                break label1;
            flag = false;
        }
          goto _L6
        k++;
          goto _L7
_L4:
label2:
        {
            if(j < s.length() - 2)
                break label2;
            flag = false;
        }
          goto _L6
        int l = j + 1;
_L9:
        if(l >= j + 3) goto _L2; else goto _L8
_L8:
label3:
        {
            char c1 = s.charAt(l);
            if(c1 >= '0' && c1 <= '9')
                break label3;
            flag = false;
        }
          goto _L6
        l++;
          goto _L9
    }

    public static URIParsedResult parse(Result result)
    {
        String s = result.getText();
        if(s != null && s.startsWith("URL:"))
            s = s.substring(4);
        URIParsedResult uriparsedresult;
        if(!isBasicallyValidURI(s))
            uriparsedresult = null;
        else
            uriparsedresult = new URIParsedResult(s, null);
        return uriparsedresult;
    }
}
