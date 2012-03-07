// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, AddressBookParsedResult

final class VCardResultParser extends ResultParser
{

    private VCardResultParser()
    {
    }

    private static String decodeQuotedPrintable(String s, String s1)
    {
        int i;
        StringBuffer stringbuffer;
        ByteArrayOutputStream bytearrayoutputstream;
        int j;
        i = s.length();
        stringbuffer = new StringBuffer(i);
        bytearrayoutputstream = new ByteArrayOutputStream();
        j = 0;
_L2:
        if(j >= i)
        {
            maybeAppendFragment(bytearrayoutputstream, s1, stringbuffer);
            return stringbuffer.toString();
        }
        char c = s.charAt(j);
        switch(c)
        {
        default:
            maybeAppendFragment(bytearrayoutputstream, s1, stringbuffer);
            stringbuffer.append(c);
            break;

        case 10: // '\n'
        case 13: // '\r'
            break;

        case 61: // '='
            break; /* Loop/switch isn't completed */
        }
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        if(j < i - 2)
        {
            char c1 = s.charAt(j + 1);
            if(c1 != '\r' && c1 != '\n')
            {
                char c2 = s.charAt(j + 2);
                try
                {
                    bytearrayoutputstream.write(16 * toHexValue(c1) + toHexValue(c2));
                }
                catch(IllegalArgumentException illegalargumentexception) { }
                j += 2;
            }
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private static String formatAddress(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        String s1 = null;
_L4:
        return s1;
_L2:
        int i = s.length();
        StringBuffer stringbuffer = new StringBuffer(i);
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                    break label0;
                s1 = stringbuffer.toString().trim();
            }
            if(true)
                continue;
            char c = s.charAt(j);
            if(c == ';')
                stringbuffer.append(' ');
            else
                stringbuffer.append(c);
            j++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void formatNames(String as[])
    {
        if(as == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i < as.length) goto _L3; else goto _L2
_L2:
        return;
_L3:
        String s = as[i];
        String as1[] = new String[5];
        int j = 0;
        int k = 0;
        do
        {
            int l;
label0:
            {
                l = s.indexOf(';', j);
                if(l > 0)
                    break label0;
                as1[k] = s.substring(j);
                StringBuffer stringbuffer = new StringBuffer(100);
                maybeAppendComponent(as1, 3, stringbuffer);
                maybeAppendComponent(as1, 1, stringbuffer);
                maybeAppendComponent(as1, 2, stringbuffer);
                maybeAppendComponent(as1, 0, stringbuffer);
                maybeAppendComponent(as1, 4, stringbuffer);
                as[i] = stringbuffer.toString().trim();
                i++;
            }
            if(true)
                continue;
            as1[k] = s.substring(j, l);
            k++;
            j = l + 1;
        } while(true);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static boolean isLikeVCardDate(String s)
    {
        boolean flag;
        if(s == null)
            flag = true;
        else
        if(isStringOfDigits(s, 8))
            flag = true;
        else
        if(s.length() == 10 && s.charAt(4) == '-' && s.charAt(7) == '-' && isSubstringOfDigits(s, 0, 4) && isSubstringOfDigits(s, 5, 2) && isSubstringOfDigits(s, 8, 2))
            flag = true;
        else
            flag = false;
        return flag;
    }

    static String matchSingleVCardPrefixedField(String s, String s1, boolean flag)
    {
        String as[] = matchVCardPrefixedField(s, s1, flag);
        String s2;
        if(as == null)
            s2 = null;
        else
            s2 = as[0];
        return s2;
    }

    private static String[] matchVCardPrefixedField(String s, String s1, boolean flag)
    {
        int i;
        int j;
        Vector vector;
        i = 0;
        j = s1.length();
        vector = null;
_L5:
        if(i < j) goto _L2; else goto _L1
_L1:
        i;
_L3:
        String s2;
        boolean flag1;
        int l1;
        Object obj;
        boolean flag2;
        int k2;
        String s6;
        boolean flag3;
        Object obj1;
        boolean flag4;
        int k3;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        String s3;
        String s4;
        int j2;
        int l2;
        String s5;
        int i3;
        int j3;
        String s7;
        String s8;
        String as[];
        if(vector == null || vector.isEmpty())
            as = null;
        else
            as = toStringArray(vector);
        return as;
_L2:
        k = s1.indexOf(s, i);
        if(k >= 0) goto _L4; else goto _L3
_L4:
label0:
        {
            if(k <= 0 || s1.charAt(k - 1) == '\n')
                break label0;
            i = k + 1;
        }
          goto _L5
        i = k + s.length();
        if(s1.charAt(i) != ':' && s1.charAt(i) != ';') goto _L5; else goto _L6
_L6:
        l = i;
        i1 = i;
_L13:
        if(s1.charAt(i1) != ':') goto _L8; else goto _L7
_L7:
        if(i1 <= l)
            break MISSING_BLOCK_LABEL_609;
        j2 = l + 1;
        obj = null;
        flag2 = false;
        k2 = l;
        l2 = j2;
_L21:
        if(l2 <= i1) goto _L10; else goto _L9
_L9:
        flag1 = flag2;
        s2 = obj;
_L27:
        j1 = i1 + 1;
        k1 = j1;
_L24:
        l1 = s1.indexOf('\n', j1);
        if(l1 >= 0) goto _L12; else goto _L11
_L11:
        if(l1 < 0)
            i = j;
        else
        if(l1 > k1)
        {
            Vector vector1;
            int i2;
            if(vector == null)
                vector1 = new Vector(1);
            else
                vector1 = vector;
            if(s1.charAt(l1 - 1) == '\r')
                i2 = l1 - 1;
            else
                i2 = l1;
            s3 = s1.substring(k1, i2);
            if(flag)
                s3 = s3.trim();
            if(flag1)
                s4 = decodeQuotedPrintable(s3, s2);
            else
                s4 = stripContinuationCRLF(s3);
            vector1.addElement(s4);
            i = i2 + 1;
            vector = vector1;
        } else
        {
            i = l1 + 1;
        }
          goto _L5
_L8:
        i1++;
          goto _L13
_L10:
        if(s1.charAt(l2) != ';' && s1.charAt(l2) != ':') goto _L15; else goto _L14
_L14:
        s5 = s1.substring(k2 + 1, l2);
        i3 = s5.indexOf('=');
        if(i3 < 0) goto _L17; else goto _L16
_L16:
        s7 = s5.substring(0, i3);
        s8 = s5.substring(i3 + 1);
        if(!s7.equalsIgnoreCase("ENCODING")) goto _L19; else goto _L18
_L18:
        if(!s8.equalsIgnoreCase("QUOTED-PRINTABLE")) goto _L17; else goto _L20
_L20:
        s6 = obj;
        flag3 = true;
_L23:
        j3 = l2;
        obj1 = s6;
        flag4 = flag3;
        k3 = j3;
_L26:
        l2++;
        obj = obj1;
        flag2 = flag4;
        k2 = k3;
          goto _L21
_L19:
        if(!s7.equalsIgnoreCase("CHARSET")) goto _L17; else goto _L22
_L22:
        s6 = s8;
        flag3 = flag2;
          goto _L23
_L12:
        if(l1 >= s1.length() - 1 || s1.charAt(l1 + 1) != ' ' && s1.charAt(l1 + 1) != '\t')
            continue; /* Loop/switch isn't completed */
        j1 = l1 + 2;
          goto _L24
        if(!flag1 || s1.charAt(l1 - 1) != '=' && s1.charAt(l1 - 2) != '=') goto _L11; else goto _L25
_L25:
        j1 = l1 + 1;
          goto _L24
_L17:
        s6 = obj;
        flag3 = flag2;
          goto _L23
_L15:
        flag4 = flag2;
        k3 = k2;
        obj1 = obj;
          goto _L26
        s2 = null;
        flag1 = false;
          goto _L27
    }

    private static void maybeAppendComponent(String as[], int i, StringBuffer stringbuffer)
    {
        if(as[i] != null)
        {
            stringbuffer.append(' ');
            stringbuffer.append(as[i]);
        }
    }

    private static void maybeAppendFragment(ByteArrayOutputStream bytearrayoutputstream, String s, StringBuffer stringbuffer)
    {
        if(bytearrayoutputstream.size() > 0)
        {
            byte abyte0[] = bytearrayoutputstream.toByteArray();
            String s1;
            if(s == null)
                s1 = new String(abyte0);
            else
                try
                {
                    s1 = new String(abyte0, s);
                }
                catch(UnsupportedEncodingException unsupportedencodingexception)
                {
                    s1 = new String(abyte0);
                }
            bytearrayoutputstream.reset();
            stringbuffer.append(s1);
        }
    }

    public static AddressBookParsedResult parse(Result result)
    {
        String s = result.getText();
        if(s != null && s.startsWith("BEGIN:VCARD")) goto _L2; else goto _L1
_L1:
        AddressBookParsedResult addressbookparsedresult = null;
_L5:
        return addressbookparsedresult;
_L2:
        String as[];
        String as1[];
        String as2[];
        String s1;
        String as3[];
        as = matchVCardPrefixedField("FN", s, true);
        if(as == null)
        {
            as = matchVCardPrefixedField("N", s, true);
            formatNames(as);
        }
        as1 = matchVCardPrefixedField("TEL", s, true);
        as2 = matchVCardPrefixedField("EMAIL", s, true);
        s1 = matchSingleVCardPrefixedField("NOTE", s, false);
        as3 = matchVCardPrefixedField("ADR", s, true);
        if(as3 == null) goto _L4; else goto _L3
_L3:
        int i = 0;
_L6:
        if(i < as3.length)
            break MISSING_BLOCK_LABEL_166;
_L4:
        String s2 = matchSingleVCardPrefixedField("ORG", s, true);
        String s3 = matchSingleVCardPrefixedField("BDAY", s, true);
        if(!isLikeVCardDate(s3))
            s3 = null;
        addressbookparsedresult = new AddressBookParsedResult(as, null, as1, as2, s1, as3, s2, s3, matchSingleVCardPrefixedField("TITLE", s, true), matchSingleVCardPrefixedField("URL", s, true));
          goto _L5
        as3[i] = formatAddress(as3[i]);
        i++;
          goto _L6
    }

    private static String stripContinuationCRLF(String s)
    {
        int i;
        StringBuffer stringbuffer;
        boolean flag;
        int j;
        i = s.length();
        stringbuffer = new StringBuffer(i);
        flag = false;
        j = 0;
_L2:
        if(j >= i)
            return stringbuffer.toString();
        if(!flag)
            break; /* Loop/switch isn't completed */
        flag = false;
_L4:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        char c = s.charAt(j);
        flag = false;
        switch(c)
        {
        case 11: // '\013'
        case 12: // '\f'
        default:
            stringbuffer.append(c);
            break;

        case 10: // '\n'
            flag = true;
            break;

        case 13: // '\r'
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static int toHexValue(char c)
    {
        int i;
        if(c >= '0' && c <= '9')
            i = c - 48;
        else
        if(c >= 'A' && c <= 'F')
            i = 10 + (c - 65);
        else
        if(c >= 'a' && c <= 'f')
            i = 10 + (c - 97);
        else
            throw new IllegalArgumentException();
        return i;
    }
}
