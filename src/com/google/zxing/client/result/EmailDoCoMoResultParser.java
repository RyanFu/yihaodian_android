// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, EmailAddressParsedResult

final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser
{

    EmailDoCoMoResultParser()
    {
    }

    private static boolean isAtextSymbol(char c)
    {
        int i = 0;
_L6:
        if(i < ATEXT_SYMBOLS.length) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        if(c != ATEXT_SYMBOLS[i])
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    static boolean isBasicallyValidEmailAddress(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag1 = false;
_L4:
        return flag1;
_L2:
        boolean flag;
        int i;
        flag = false;
        i = 0;
_L5:
        if(i >= s.length())
        {
            flag1 = flag;
        } else
        {
            char c = s.charAt(i);
            if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && !isAtextSymbol(c))
            {
                flag1 = false;
            } else
            {
label0:
                {
                    if(c != '@')
                        break MISSING_BLOCK_LABEL_101;
                    if(!flag)
                        break label0;
                    flag1 = false;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        flag = true;
        i++;
          goto _L5
    }

    public static EmailAddressParsedResult parse(Result result)
    {
        String s = result.getText();
        EmailAddressParsedResult emailaddressparsedresult;
        if(s == null || !s.startsWith("MATMSG:"))
        {
            emailaddressparsedresult = null;
        } else
        {
            String as[] = matchDoCoMoPrefixedField("TO:", s, true);
            if(as == null)
            {
                emailaddressparsedresult = null;
            } else
            {
                String s1 = as[0];
                if(!isBasicallyValidEmailAddress(s1))
                    emailaddressparsedresult = null;
                else
                    emailaddressparsedresult = new EmailAddressParsedResult(s1, matchSingleDoCoMoPrefixedField("SUB:", s, false), matchSingleDoCoMoPrefixedField("BODY:", s, false), (new StringBuilder("mailto:")).append(s1).toString());
            }
        }
        return emailaddressparsedresult;
    }

    private static final char ATEXT_SYMBOLS[];

    static 
    {
        char ac[] = new char[21];
        ac[0] = '@';
        ac[1] = '.';
        ac[2] = '!';
        ac[3] = '#';
        ac[4] = '$';
        ac[5] = '%';
        ac[6] = '&';
        ac[7] = '\'';
        ac[8] = '*';
        ac[9] = '+';
        ac[10] = '-';
        ac[11] = '/';
        ac[12] = '=';
        ac[13] = '?';
        ac[14] = '^';
        ac[15] = '_';
        ac[16] = '`';
        ac[17] = '{';
        ac[18] = '|';
        ac[19] = '}';
        ac[20] = '~';
        ATEXT_SYMBOLS = ac;
    }
}
