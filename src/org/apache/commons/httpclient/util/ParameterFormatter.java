// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import org.apache.commons.httpclient.NameValuePair;

public class ParameterFormatter
{

    public ParameterFormatter()
    {
        alwaysUseQuotes = true;
    }

    public static void formatValue(StringBuffer stringbuffer, String s, boolean flag)
    {
        if(stringbuffer == null)
            throw new IllegalArgumentException("String buffer may not be null");
        if(s == null)
            throw new IllegalArgumentException("Value buffer may not be null");
        if(!flag) goto _L2; else goto _L1
_L1:
        int k;
        stringbuffer.append('"');
        k = 0;
_L5:
        if(k < s.length()) goto _L4; else goto _L3
_L3:
        stringbuffer.append('"');
_L6:
        return;
_L4:
        char c1 = s.charAt(k);
        if(isUnsafeChar(c1))
            stringbuffer.append('\\');
        stringbuffer.append(c1);
        k++;
          goto _L5
_L2:
        int i;
        boolean flag1;
        int j;
        i = stringbuffer.length();
        flag1 = false;
        j = 0;
_L7:
label0:
        {
            if(j < s.length())
                break label0;
            if(flag1)
            {
                stringbuffer.insert(i, '"');
                stringbuffer.append('"');
            }
        }
          goto _L6
        char c = s.charAt(j);
        if(isSeparator(c))
            flag1 = true;
        if(isUnsafeChar(c))
            stringbuffer.append('\\');
        stringbuffer.append(c);
        j++;
          goto _L7
    }

    private static boolean isOneOf(char ac[], char c)
    {
        int i = 0;
_L6:
        if(i < ac.length) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        if(c != ac[i])
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static boolean isSeparator(char c)
    {
        return isOneOf(SEPARATORS, c);
    }

    private static boolean isUnsafeChar(char c)
    {
        return isOneOf(UNSAFE_CHARS, c);
    }

    public String format(NameValuePair namevaluepair)
    {
        StringBuffer stringbuffer = new StringBuffer();
        format(stringbuffer, namevaluepair);
        return stringbuffer.toString();
    }

    public void format(StringBuffer stringbuffer, NameValuePair namevaluepair)
    {
        if(stringbuffer == null)
            throw new IllegalArgumentException("String buffer may not be null");
        if(namevaluepair == null)
            throw new IllegalArgumentException("Parameter may not be null");
        stringbuffer.append(namevaluepair.getName());
        String s = namevaluepair.getValue();
        if(s != null)
        {
            stringbuffer.append("=");
            formatValue(stringbuffer, s, alwaysUseQuotes);
        }
    }

    public boolean isAlwaysUseQuotes()
    {
        return alwaysUseQuotes;
    }

    public void setAlwaysUseQuotes(boolean flag)
    {
        alwaysUseQuotes = flag;
    }

    private static final char SEPARATORS[];
    private static final char UNSAFE_CHARS[];
    private boolean alwaysUseQuotes;

    static 
    {
        char ac[] = new char[19];
        ac[0] = '(';
        ac[1] = ')';
        ac[2] = '<';
        ac[3] = '>';
        ac[4] = '@';
        ac[5] = ',';
        ac[6] = ';';
        ac[7] = ':';
        ac[8] = '\\';
        ac[9] = '"';
        ac[10] = '/';
        ac[11] = '[';
        ac[12] = ']';
        ac[13] = '?';
        ac[14] = '=';
        ac[15] = '{';
        ac[16] = '}';
        ac[17] = ' ';
        ac[18] = '\t';
        SEPARATORS = ac;
        char ac1[] = new char[2];
        ac1[0] = '"';
        ac1[1] = '\\';
        UNSAFE_CHARS = ac1;
    }
}
