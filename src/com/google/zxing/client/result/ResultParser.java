// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.client.result:
//            BookmarkDoCoMoResultParser, AddressBookDoCoMoResultParser, EmailDoCoMoResultParser, AddressBookAUResultParser, 
//            VCardResultParser, BizcardResultParser, VEventResultParser, EmailAddressResultParser, 
//            TelResultParser, SMSMMSResultParser, SMSTOMMSTOResultParser, GeoResultParser, 
//            WifiResultParser, URLTOResultParser, URIResultParser, ISBNResultParser, 
//            ProductResultParser, ExpandedProductResultParser, TextParsedResult, ParsedResult

public abstract class ResultParser
{

    public ResultParser()
    {
    }

    private static void appendKeyValue(String s, int i, int j, Hashtable hashtable)
    {
        int k = s.indexOf('=', i);
        if(k >= 0)
            hashtable.put(s.substring(i, k), urlDecode(s.substring(k + 1, j)));
    }

    private static int findFirstEscape(char ac[])
    {
        int i;
        int j;
        i = ac.length;
        j = 0;
_L6:
        if(j < i) goto _L2; else goto _L1
_L1:
        int k = -1;
_L4:
        return k;
_L2:
        char c = ac[j];
        if(c != '+' && c != '%')
            break; /* Loop/switch isn't completed */
        k = j;
        if(true) goto _L4; else goto _L3
_L3:
        j++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected static boolean isStringOfDigits(String s, int i)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int j;
        if(i != s.length())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        j = 0;
_L5:
        if(j >= i)
        {
            flag = true;
        } else
        {
label0:
            {
                char c = s.charAt(j);
                if(c >= '0' && c <= '9')
                    break label0;
                flag = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    protected static boolean isSubstringOfDigits(String s, int i, int j)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int l;
        int i1;
        int k = s.length();
        l = i + j;
        if(k < l)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        i1 = i;
_L5:
        if(i1 >= l)
        {
            flag = true;
        } else
        {
label0:
            {
                char c = s.charAt(i1);
                if(c >= '0' && c <= '9')
                    break label0;
                flag = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        i1++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    static String[] matchPrefixedField(String s, String s1, char c, boolean flag)
    {
        Vector vector;
        int i;
        int j;
        vector = null;
        i = 0;
        j = s1.length();
_L5:
        if(i < j) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        boolean flag1;
        int i1;
        String s2;
        String as[];
        if(vector == null || vector.isEmpty())
            as = null;
        else
            as = toStringArray(vector);
        return as;
_L2:
        if((k = s1.indexOf(s, i)) < 0) goto _L1; else goto _L3
_L3:
        i = k + s.length();
        l = i;
        flag1 = false;
        while(!flag1) 
        {
            i1 = s1.indexOf(c, i);
            if(i1 < 0)
            {
                i = s1.length();
                flag1 = true;
            } else
            if(s1.charAt(i1 - 1) == '\\')
            {
                i = i1 + 1;
            } else
            {
                if(vector == null)
                    vector = new Vector(3);
                s2 = unescapeBackslash(s1.substring(l, i1));
                if(flag)
                    s2 = s2.trim();
                vector.addElement(s2);
                i = i1 + 1;
                flag1 = true;
            }
        }
        if(true) goto _L5; else goto _L4
_L4:
    }

    static String matchSinglePrefixedField(String s, String s1, char c, boolean flag)
    {
        String as[] = matchPrefixedField(s, s1, c, flag);
        String s2;
        if(as == null)
            s2 = null;
        else
            s2 = as[0];
        return s2;
    }

    protected static void maybeAppend(String s, StringBuffer stringbuffer)
    {
        if(s != null)
        {
            stringbuffer.append('\n');
            stringbuffer.append(s);
        }
    }

    protected static void maybeAppend(String as[], StringBuffer stringbuffer)
    {
        if(as == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i < as.length) goto _L3; else goto _L2
_L2:
        return;
_L3:
        stringbuffer.append('\n');
        stringbuffer.append(as[i]);
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected static String[] maybeWrap(String s)
    {
        String as[];
        if(s == null)
        {
            as = null;
        } else
        {
            as = new String[1];
            as[0] = s;
        }
        return as;
    }

    private static int parseHexDigit(char c)
    {
        if(c < 'a') goto _L2; else goto _L1
_L1:
        if(c > 'f') goto _L4; else goto _L3
_L3:
        int i = 10 + (c - 97);
_L6:
        return i;
_L2:
        if(c >= 'A')
        {
            if(c <= 'F')
            {
                i = 10 + (c - 65);
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(c >= '0' && c <= '9')
        {
            i = c - 48;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = -1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    static Hashtable parseNameValuePairs(String s)
    {
        int i = s.indexOf('?');
        if(i >= 0) goto _L2; else goto _L1
_L1:
        Hashtable hashtable1 = null;
_L4:
        return hashtable1;
_L2:
        Hashtable hashtable = new Hashtable(3);
        int j = i + 1;
        do
        {
            int k;
label0:
            {
                k = s.indexOf('&', j);
                if(k >= 0)
                    break label0;
                appendKeyValue(s, j, s.length(), hashtable);
                hashtable1 = hashtable;
            }
            if(true)
                continue;
            appendKeyValue(s, j, k, hashtable);
            j = k + 1;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static ParsedResult parseResult(Result result)
    {
        URIParsedResult uriparsedresult = BookmarkDoCoMoResultParser.parse(result);
        Object obj;
        if(uriparsedresult != null)
        {
            obj = uriparsedresult;
        } else
        {
            AddressBookParsedResult addressbookparsedresult = AddressBookDoCoMoResultParser.parse(result);
            if(addressbookparsedresult != null)
            {
                obj = addressbookparsedresult;
            } else
            {
                EmailAddressParsedResult emailaddressparsedresult = EmailDoCoMoResultParser.parse(result);
                if(emailaddressparsedresult != null)
                {
                    obj = emailaddressparsedresult;
                } else
                {
                    AddressBookParsedResult addressbookparsedresult1 = AddressBookAUResultParser.parse(result);
                    if(addressbookparsedresult1 != null)
                    {
                        obj = addressbookparsedresult1;
                    } else
                    {
                        AddressBookParsedResult addressbookparsedresult2 = VCardResultParser.parse(result);
                        if(addressbookparsedresult2 != null)
                        {
                            obj = addressbookparsedresult2;
                        } else
                        {
                            AddressBookParsedResult addressbookparsedresult3 = BizcardResultParser.parse(result);
                            if(addressbookparsedresult3 != null)
                            {
                                obj = addressbookparsedresult3;
                            } else
                            {
                                CalendarParsedResult calendarparsedresult = VEventResultParser.parse(result);
                                if(calendarparsedresult != null)
                                {
                                    obj = calendarparsedresult;
                                } else
                                {
                                    EmailAddressParsedResult emailaddressparsedresult1 = EmailAddressResultParser.parse(result);
                                    if(emailaddressparsedresult1 != null)
                                    {
                                        obj = emailaddressparsedresult1;
                                    } else
                                    {
                                        TelParsedResult telparsedresult = TelResultParser.parse(result);
                                        if(telparsedresult != null)
                                        {
                                            obj = telparsedresult;
                                        } else
                                        {
                                            SMSParsedResult smsparsedresult = SMSMMSResultParser.parse(result);
                                            if(smsparsedresult != null)
                                            {
                                                obj = smsparsedresult;
                                            } else
                                            {
                                                SMSParsedResult smsparsedresult1 = SMSTOMMSTOResultParser.parse(result);
                                                if(smsparsedresult1 != null)
                                                {
                                                    obj = smsparsedresult1;
                                                } else
                                                {
                                                    GeoParsedResult geoparsedresult = GeoResultParser.parse(result);
                                                    if(geoparsedresult != null)
                                                    {
                                                        obj = geoparsedresult;
                                                    } else
                                                    {
                                                        WifiParsedResult wifiparsedresult = WifiResultParser.parse(result);
                                                        if(wifiparsedresult != null)
                                                        {
                                                            obj = wifiparsedresult;
                                                        } else
                                                        {
                                                            URIParsedResult uriparsedresult1 = URLTOResultParser.parse(result);
                                                            if(uriparsedresult1 != null)
                                                            {
                                                                obj = uriparsedresult1;
                                                            } else
                                                            {
                                                                URIParsedResult uriparsedresult2 = URIResultParser.parse(result);
                                                                if(uriparsedresult2 != null)
                                                                {
                                                                    obj = uriparsedresult2;
                                                                } else
                                                                {
                                                                    ISBNParsedResult isbnparsedresult = ISBNResultParser.parse(result);
                                                                    if(isbnparsedresult != null)
                                                                    {
                                                                        obj = isbnparsedresult;
                                                                    } else
                                                                    {
                                                                        ProductParsedResult productparsedresult = ProductResultParser.parse(result);
                                                                        if(productparsedresult != null)
                                                                        {
                                                                            obj = productparsedresult;
                                                                        } else
                                                                        {
                                                                            ExpandedProductParsedResult expandedproductparsedresult = ExpandedProductResultParser.parse(result);
                                                                            if(expandedproductparsedresult != null)
                                                                                obj = expandedproductparsedresult;
                                                                            else
                                                                                obj = new TextParsedResult(result.getText(), null);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ((ParsedResult) (obj));
    }

    static String[] toStringArray(Vector vector)
    {
        int i = vector.size();
        String as[] = new String[i];
        int j = 0;
        do
        {
            if(j >= i)
                return as;
            as[j] = (String)vector.elementAt(j);
            j++;
        } while(true);
    }

    protected static String unescapeBackslash(String s)
    {
        int j;
        StringBuffer stringbuffer;
        boolean flag;
        int k;
        if(s == null)
            break MISSING_BLOCK_LABEL_107;
        int i = s.indexOf('\\');
        if(i < 0)
            break MISSING_BLOCK_LABEL_107;
        j = s.length();
        stringbuffer = new StringBuffer(j - 1);
        stringbuffer.append(s.toCharArray(), 0, i);
        flag = false;
        k = i;
_L3:
        if(k < j) goto _L2; else goto _L1
_L1:
        String s1 = stringbuffer.toString();
_L4:
        return s1;
_L2:
        char c = s.charAt(k);
        if(flag || c != '\\')
        {
            stringbuffer.append(c);
            flag = false;
        } else
        {
            flag = true;
        }
        k++;
          goto _L3
        s1 = s;
          goto _L4
    }

    private static String urlDecode(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        String s1 = null;
_L4:
        return s1;
_L2:
        char ac[];
        int j;
        StringBuffer stringbuffer;
        int k;
        ac = s.toCharArray();
        int i = findFirstEscape(ac);
        if(i < 0)
        {
            s1 = s;
            continue; /* Loop/switch isn't completed */
        }
        j = ac.length;
        stringbuffer = new StringBuffer(j - 2);
        stringbuffer.append(ac, 0, i);
        k = i;
_L5:
label0:
        {
            if(k < j)
                break label0;
            s1 = stringbuffer.toString();
        }
        if(true) goto _L4; else goto _L3
_L3:
        char c = ac[k];
        if(c == '+')
            stringbuffer.append(' ');
        else
        if(c == '%')
        {
            if(k >= j - 2)
            {
                stringbuffer.append('%');
            } else
            {
                int l = k + 1;
                int i1 = parseHexDigit(ac[l]);
                k = l + 1;
                int j1 = parseHexDigit(ac[k]);
                if(i1 < 0 || j1 < 0)
                {
                    stringbuffer.append('%');
                    stringbuffer.append(ac[k - 1]);
                    stringbuffer.append(ac[k]);
                }
                stringbuffer.append((char)(j1 + (i1 << 4)));
            }
        } else
        {
            stringbuffer.append(c);
        }
        k++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }
}
