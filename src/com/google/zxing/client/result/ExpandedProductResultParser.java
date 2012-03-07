// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ExpandedProductParsedResult

final class ExpandedProductResultParser extends ResultParser
{

    private ExpandedProductResultParser()
    {
    }

    private static String findAIvalue(int i, String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(s.charAt(i) == '(') goto _L2; else goto _L1
_L1:
        String s2 = "ERROR";
_L4:
        return s2;
_L2:
        String s1 = s.substring(i + 1);
        int j = 0;
label0:
        do
        {
            if(j >= s1.length())
            {
                s2 = stringbuffer.toString();
                break;
            }
            char c = s1.charAt(j);
            switch(c)
            {
            case 42: // '*'
            case 43: // '+'
            case 44: // ','
            case 45: // '-'
            case 46: // '.'
            case 47: // '/'
            default:
                s2 = "ERROR";
                break label0;

            case 48: // '0'
            case 49: // '1'
            case 50: // '2'
            case 51: // '3'
            case 52: // '4'
            case 53: // '5'
            case 54: // '6'
            case 55: // '7'
            case 56: // '8'
            case 57: // '9'
                stringbuffer.append(c);
                j++;
                break;

            case 41: // ')'
                s2 = stringbuffer.toString();
                break label0;
            }
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String findValue(int i, String s)
    {
        StringBuffer stringbuffer;
        String s1;
        int j;
        stringbuffer = new StringBuffer();
        s1 = s.substring(i);
        j = 0;
_L5:
        if(j < s1.length()) goto _L2; else goto _L1
_L1:
        return stringbuffer.toString();
_L2:
        char c;
        c = s1.charAt(j);
        if(c != '(')
            break; /* Loop/switch isn't completed */
        if(!"ERROR".equals(findAIvalue(j, s1))) goto _L1; else goto _L3
_L3:
        stringbuffer.append('(');
_L6:
        j++;
        if(true) goto _L5; else goto _L4
_L4:
        stringbuffer.append(c);
          goto _L6
        if(true) goto _L5; else goto _L7
_L7:
    }

    public static ExpandedProductParsedResult parse(Result result)
    {
        String s;
        ExpandedProductParsedResult expandedproductparsedresult;
        BarcodeFormat barcodeformat = result.getBarcodeFormat();
        if(!BarcodeFormat.RSS_EXPANDED.equals(barcodeformat))
        {
            expandedproductparsedresult = null;
        } else
        {
label0:
            {
                s = result.getText();
                if(s != null)
                    break label0;
                expandedproductparsedresult = null;
            }
        }
_L1:
        return expandedproductparsedresult;
        String s1;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        String s7;
        String s8;
        String s9;
        String s10;
        String s11;
        String s12;
        String s13;
        Hashtable hashtable;
        int i;
        s1 = "-";
        s2 = "-";
        s3 = "-";
        s4 = "-";
        s5 = "-";
        s6 = "-";
        s7 = "-";
        s8 = "-";
        s9 = "-";
        s10 = "-";
        s11 = "-";
        s12 = "-";
        s13 = "-";
        hashtable = new Hashtable();
        i = 0;
_L2:
        String s14;
        int j = s.length();
        if(i >= j)
        {
            expandedproductparsedresult = new ExpandedProductParsedResult(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, hashtable);
        } else
        {
label1:
            {
                s14 = findAIvalue(i, s);
                if(!"ERROR".equals(s14))
                    break label1;
                expandedproductparsedresult = null;
            }
        }
          goto _L1
        String s15;
        int k = i + (2 + s14.length());
        s15 = findValue(k, s);
        i = k + s15.length();
        if("00".equals(s14))
            s2 = s15;
        else
        if("01".equals(s14))
            s1 = s15;
        else
        if("10".equals(s14))
            s3 = s15;
        else
        if("11".equals(s14))
            s4 = s15;
        else
        if("13".equals(s14))
            s5 = s15;
        else
        if("15".equals(s14))
            s6 = s15;
        else
        if("17".equals(s14))
            s7 = s15;
        else
        if("3100".equals(s14) || "3101".equals(s14) || "3102".equals(s14) || "3103".equals(s14) || "3104".equals(s14) || "3105".equals(s14) || "3106".equals(s14) || "3107".equals(s14) || "3108".equals(s14) || "3109".equals(s14))
        {
            s8 = s15;
            s9 = "KG";
            s10 = s14.substring(3);
        } else
        if("3200".equals(s14) || "3201".equals(s14) || "3202".equals(s14) || "3203".equals(s14) || "3204".equals(s14) || "3205".equals(s14) || "3206".equals(s14) || "3207".equals(s14) || "3208".equals(s14) || "3209".equals(s14))
        {
            s8 = s15;
            s9 = "LB";
            s10 = s14.substring(3);
        } else
        {
label2:
            {
                if(!"3920".equals(s14) && !"3921".equals(s14) && !"3922".equals(s14) && !"3923".equals(s14))
                    break label2;
                s11 = s15;
                s12 = s14.substring(3);
            }
        }
          goto _L2
label3:
        {
            if(!"3930".equals(s14) && !"3931".equals(s14) && !"3932".equals(s14) && !"3933".equals(s14))
                break MISSING_BLOCK_LABEL_699;
            if(s15.length() >= 4)
                break label3;
            expandedproductparsedresult = null;
        }
          goto _L1
        s11 = s15.substring(3);
        s13 = s15.substring(0, 3);
        s12 = s14.substring(3);
          goto _L2
        hashtable.put(s14, s15);
          goto _L2
    }
}
