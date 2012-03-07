// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Hashtable;

public final class StringUtils
{

    private StringUtils()
    {
    }

    public static String guessEncoding(byte abyte0[], Hashtable hashtable)
    {
        if(hashtable == null) goto _L2; else goto _L1
_L1:
        String s1 = (String)hashtable.get(DecodeHintType.CHARACTER_SET);
        if(s1 == null) goto _L2; else goto _L3
_L3:
        String s = s1;
_L8:
        return s;
_L2:
        if(abyte0.length <= 3 || abyte0[0] != -17 || abyte0[1] != -69 || abyte0[2] != -65) goto _L5; else goto _L4
_L4:
        s = "UTF8";
          goto _L6
_L5:
        int i;
        boolean flag;
        boolean flag1;
        boolean flag2;
        int j;
        int k;
        int l;
        boolean flag3;
        int i1;
        boolean flag4;
        boolean flag5;
        i = abyte0.length;
        flag = true;
        flag1 = true;
        flag2 = true;
        j = 0;
        k = 0;
        l = 0;
        flag3 = false;
        i1 = 0;
        flag4 = false;
        flag5 = false;
_L13:
label0:
        {
            if(i1 < i && (flag || flag1 || flag2))
                break label0;
            boolean flag6;
            int j1;
            int k1;
            boolean flag7;
            int l1;
            int i2;
            int j2;
            if(j > 0)
                flag6 = false;
            else
                flag6 = flag2;
            if(flag1 && ASSUME_SHIFT_JIS)
                s = "SJIS";
            else
            if(flag6 && flag5)
                s = "UTF8";
            else
            if(flag1 && (k >= 3 || l * 20 > i))
                s = "SJIS";
            else
            if(!flag4 && flag)
                s = "ISO8859_1";
            else
                s = PLATFORM_DEFAULT_ENCODING;
        }
_L6:
        if(true) goto _L8; else goto _L7
_L7:
        j1 = 0xff & abyte0[i1];
        if(j1 < 128 || j1 > 191) goto _L10; else goto _L9
_L9:
        if(j <= 0) goto _L12; else goto _L11
_L11:
        k1 = j - 1;
        flag7 = flag5;
_L15:
        if((j1 == 194 || j1 == 195) && i1 < i - 1)
        {
            i2 = 0xff & abyte0[i1 + 1];
            if(i2 <= 191 && (j1 == 194 && i2 >= 160 || j1 == 195 && i2 >= 128))
                flag4 = true;
        }
        if(j1 >= 127 && j1 <= 159)
            flag = false;
        if(j1 >= 161 && j1 <= 223 && !flag3)
            l++;
        if(!flag3 && (j1 >= 240 && j1 <= 255 || j1 == 128 || j1 == 160))
            flag1 = false;
        if(j1 >= 129 && j1 <= 159 || j1 >= 224 && j1 <= 239)
        {
            if(flag3)
            {
                flag3 = false;
            } else
            {
                flag3 = true;
                if(i1 >= abyte0.length - 1)
                {
                    flag1 = false;
                } else
                {
                    l1 = 0xff & abyte0[i1 + 1];
                    if(l1 < 64 || l1 > 252)
                        flag1 = false;
                    else
                        k++;
                }
            }
        } else
        {
            flag3 = false;
        }
        i1++;
        flag5 = flag7;
        j = k1;
          goto _L13
_L10:
        if(j > 0)
            flag2 = false;
        if(j1 < 192 || j1 > 253) goto _L12; else goto _L14
_L14:
        flag5 = true;
        j2 = j1;
_L16:
        if((j2 & 0x40) != 0)
            break MISSING_BLOCK_LABEL_448;
_L12:
        k1 = j;
        flag7 = flag5;
          goto _L15
        j++;
        j2 <<= 1;
          goto _L16
    }

    private static final boolean ASSUME_SHIFT_JIS = false;
    private static final String EUC_JP = "EUC_JP";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING = System.getProperty("file.encoding");
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static 
    {
        boolean flag;
        if(!"SJIS".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING) && !"EUC_JP".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING))
            flag = false;
        else
            flag = true;
        ASSUME_SHIFT_JIS = flag;
    }
}
