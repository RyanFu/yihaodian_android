// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;

public class ParameterParser
{

    public ParameterParser()
    {
        chars = null;
        pos = 0;
        len = 0;
        i1 = 0;
        i2 = 0;
    }

    private String getToken(boolean flag)
    {
_L3:
        if(i1 < i2 && Character.isWhitespace(chars[i1])) goto _L2; else goto _L1
_L1:
        if(i2 <= i1 || !Character.isWhitespace(chars[i2 - 1]))
        {
            if(flag && i2 - i1 >= 2 && chars[i1] == '"' && chars[i2 - 1] == '"')
            {
                i1 = 1 + i1;
                i2 = i2 - 1;
            }
            String s = null;
            if(i2 >= i1)
                s = new String(chars, i1, i2 - i1);
            return s;
        }
        break MISSING_BLOCK_LABEL_174;
_L2:
        i1 = 1 + i1;
          goto _L3
        i2 = i2 - 1;
          goto _L1
    }

    private boolean hasChar()
    {
        boolean flag;
        if(pos < len)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isOneOf(char c, char ac[])
    {
        boolean flag;
        int i;
        flag = false;
        i = 0;
_L5:
        if(i < ac.length) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(c != ac[i])
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L1; else goto _L3
_L3:
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private String parseQuotedToken(char ac[])
    {
        boolean flag;
        boolean flag1;
        i1 = pos;
        i2 = pos;
        flag = false;
        flag1 = false;
_L5:
        if(hasChar()) goto _L2; else goto _L1
_L1:
        return getToken(true);
_L2:
        char c = chars[pos];
        if(!flag && isOneOf(c, ac)) goto _L1; else goto _L3
_L3:
        if(!flag1 && c == '"')
            if(!flag)
                flag = true;
            else
                flag = false;
        if(!flag1 && c == '\\')
            flag1 = true;
        else
            flag1 = false;
        i2 = 1 + i2;
        pos = 1 + pos;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private String parseToken(char ac[])
    {
        i1 = pos;
        do
        {
            for(i2 = pos; !hasChar() || isOneOf(chars[pos], ac);)
                return getToken(false);

            i2 = 1 + i2;
            pos = 1 + pos;
        } while(true);
    }

    public List parse(String s, char c)
    {
        Object obj;
        if(s == null)
            obj = new ArrayList();
        else
            obj = parse(s.toCharArray(), c);
        return ((List) (obj));
    }

    public List parse(char ac[], char c)
    {
        Object obj;
        if(ac == null)
            obj = new ArrayList();
        else
            obj = parse(ac, 0, ac.length, c);
        return ((List) (obj));
    }

    public List parse(char ac[], int i, int j, char c)
    {
        if(ac != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = new ArrayList();
_L4:
        return arraylist;
_L2:
        ArrayList arraylist1 = new ArrayList();
        chars = ac;
        pos = i;
        len = j;
        do
        {
label0:
            {
                if(hasChar())
                    break label0;
                arraylist = arraylist1;
            }
            if(true)
                continue;
            char ac1[] = new char[2];
            ac1[0] = '=';
            ac1[1] = c;
            String s = parseToken(ac1);
            String s1 = null;
            if(hasChar() && ac[pos] == '=')
            {
                pos = 1 + pos;
                char ac2[] = new char[1];
                ac2[0] = c;
                s1 = parseQuotedToken(ac2);
            }
            if(hasChar() && ac[pos] == c)
                pos = 1 + pos;
            if(s != null && (!s.equals("") || s1 != null))
                arraylist1.add(new NameValuePair(s, s1));
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private char chars[];
    private int i1;
    private int i2;
    private int len;
    private int pos;
}
