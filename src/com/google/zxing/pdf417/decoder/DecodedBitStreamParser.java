// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;

final class DecodedBitStreamParser
{

    private DecodedBitStreamParser()
    {
    }

    private static StringBuffer add(String s, String s1)
    {
        StringBuffer stringbuffer;
        StringBuffer stringbuffer1;
        StringBuffer stringbuffer2;
        int i;
        stringbuffer = new StringBuffer(5);
        stringbuffer1 = new StringBuffer(5);
        stringbuffer2 = new StringBuffer(s.length());
        i = 0;
_L3:
        if(i < s.length()) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        j = 0;
        k = s.length() - 3;
_L4:
        if(k <= -1)
            return stringbuffer2;
        break MISSING_BLOCK_LABEL_78;
_L2:
        stringbuffer2.append('0');
        i++;
          goto _L3
        stringbuffer.setLength(0);
        stringbuffer.append(s.charAt(k));
        stringbuffer.append(s.charAt(k + 1));
        stringbuffer.append(s.charAt(k + 2));
        stringbuffer1.setLength(0);
        stringbuffer1.append(s1.charAt(k));
        stringbuffer1.append(s1.charAt(k + 1));
        stringbuffer1.append(s1.charAt(k + 2));
        int l = Integer.parseInt(stringbuffer.toString());
        int i1 = Integer.parseInt(stringbuffer1.toString());
        int j1 = (j + (l + i1)) % 1000;
        j = (j + (l + i1)) / 1000;
        stringbuffer2.setCharAt(k + 2, (char)(48 + j1 % 10));
        stringbuffer2.setCharAt(k + 1, (char)(48 + (j1 / 10) % 10));
        stringbuffer2.setCharAt(k, (char)(48 + j1 / 100));
        k -= 3;
          goto _L4
    }

    private static int byteCompaction(int i, int ai[], int j, StringBuffer stringbuffer)
    {
        if(i != 901) goto _L2; else goto _L1
_L1:
        char ac1[];
        int ai1[];
        int j2;
        long l2;
        boolean flag2;
        ac1 = new char[6];
        ai1 = new int[6];
        j2 = 0;
        l2 = 0L;
        flag2 = false;
_L15:
        if(j < ai[0] && !flag2) goto _L4; else goto _L3
_L3:
        int k2 = 5 * (j2 / 5);
_L7:
        if(k2 < j2) goto _L6; else goto _L5
_L5:
        return j;
_L6:
        stringbuffer.append((char)ai1[k2]);
        k2++;
          goto _L7
_L2:
        if(i != 924) goto _L5; else goto _L8
_L8:
        l = 0L;
        flag = false;
        k = 0;
_L12:
        if(j >= ai[0] || flag) goto _L5; else goto _L9
_L9:
        int i1 = j + 1;
        int j1 = ai[j];
        int k1;
        boolean flag1;
        char ac[];
        int l1;
        if(j1 < 900)
        {
            int i2 = k + 1;
            l = l * 900L + (long)j1;
            flag1 = flag;
            k1 = i2;
            j = i1;
        } else
        if(j1 == 900 || j1 == 901 || j1 == 902 || j1 == 924 || j1 == 928 || j1 == 923 || j1 == 922)
        {
            j = i1 - 1;
            k1 = k;
            flag1 = true;
        } else
        {
            k1 = k;
            j = i1;
            flag1 = flag;
        }
        if(k1 % 5 != 0 || k1 <= 0) goto _L11; else goto _L10
_L10:
        ac = new char[6];
        l1 = 0;
_L13:
        if(l1 < 6)
            break MISSING_BLOCK_LABEL_474;
        stringbuffer.append(ac);
_L11:
        flag = flag1;
        k = k1;
          goto _L12
        ac[5 - l1] = (char)(int)(255L & l);
        l >>= 8;
        l1++;
          goto _L13
_L4:
        int i3 = j + 1;
        int j3 = ai[j];
        long l;
        boolean flag;
        int k;
        int k3;
        boolean flag3;
        long l3;
        boolean flag4;
        if(j3 < 900)
        {
            ai1[j2] = j3;
            int j4 = j2 + 1;
            long l4 = l2 * 900L + (long)j3;
            j = i3;
            k3 = j4;
            flag3 = flag2;
            l3 = l4;
        } else
        if(j3 == 900 || j3 == 901 || j3 == 902 || j3 == 924 || j3 == 928 || j3 == 923 || j3 == 922)
        {
            j = i3 - 1;
            k3 = j2;
            flag3 = true;
            l3 = l2;
        } else
        {
            j = i3;
            k3 = j2;
            flag3 = flag2;
            l3 = l2;
        }
        if(k3 % 5 == 0 && k3 > 0)
        {
            int i4 = 0;
            do
            {
                if(i4 >= 6)
                {
                    stringbuffer.append(ac1);
                    boolean flag5 = flag3;
                    j2 = 0;
                    l2 = l3;
                    flag2 = flag5;
                    continue; /* Loop/switch isn't completed */
                }
                ac1[5 - i4] = (char)(int)(l3 % 256L);
                l3 >>= 8;
                i4++;
            } while(true);
        }
        flag4 = flag3;
        j2 = k3;
        l2 = l3;
        flag2 = flag4;
        if(true) goto _L15; else goto _L14
_L14:
    }

    static DecoderResult decode(int ai[])
        throws FormatException
    {
        StringBuffer stringbuffer;
        int j;
        int k;
        stringbuffer = new StringBuffer(100);
        int i = 1 + 1;
        j = ai[1];
        k = i;
_L7:
        if(k >= ai[0])
            return new DecoderResult(null, stringbuffer.toString(), null, null);
        j;
        JVM INSTR lookupswitch 5: default 96
    //                   900: 132
    //                   901: 144
    //                   902: 157
    //                   913: 169
    //                   924: 182;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        int l = textCompaction(ai, k - 1, stringbuffer);
_L8:
        if(l < ai.length)
        {
            int i1 = l + 1;
            j = ai[l];
            k = i1;
        } else
        {
            throw FormatException.getFormatInstance();
        }
        if(true) goto _L7; else goto _L2
_L2:
        l = textCompaction(ai, k, stringbuffer);
          goto _L8
_L3:
        l = byteCompaction(j, ai, k, stringbuffer);
          goto _L8
_L4:
        l = numericCompaction(ai, k, stringbuffer);
          goto _L8
_L5:
        l = byteCompaction(j, ai, k, stringbuffer);
          goto _L8
_L6:
        l = byteCompaction(j, ai, k, stringbuffer);
          goto _L8
    }

    private static String decodeBase900toBase10(int ai[], int i)
    {
        StringBuffer stringbuffer;
        int j;
        stringbuffer = null;
        j = 0;
_L3:
        if(j < i) goto _L2; else goto _L1
_L1:
        String s;
        int k;
        s = null;
        k = 0;
_L4:
        StringBuffer stringbuffer1;
        if(k < stringbuffer.length())
        {
label0:
            {
                if(stringbuffer.charAt(k) != '1')
                    break label0;
                s = stringbuffer.toString().substring(k + 1);
            }
        }
        if(s == null)
            s = stringbuffer.toString();
        return s;
_L2:
        stringbuffer1 = multiply(EXP900[i - j - 1], ai[j]);
        if(stringbuffer == null)
            stringbuffer = stringbuffer1;
        else
            stringbuffer = add(stringbuffer.toString(), stringbuffer1.toString());
        j++;
          goto _L3
        k++;
          goto _L4
    }

    private static void decodeTextCompaction(int ai[], int ai1[], int i, StringBuffer stringbuffer)
    {
        int j;
        int k;
        int l;
        j = 0;
        k = 0;
        l = 0;
_L8:
        int i1;
        char c;
        if(l >= i)
            return;
        i1 = ai[l];
        c = '\0';
        j;
        JVM INSTR tableswitch 0 4: default 60
    //                   0 78
    //                   1 174
    //                   2 270
    //                   3 373
    //                   4 425;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_425;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L9:
        if(c != 0)
            stringbuffer.append(c);
        l++;
        if(true) goto _L8; else goto _L7
_L7:
        if(i1 < 26)
            c = (char)(i1 + 65);
        else
        if(i1 == 26)
            c = ' ';
        else
        if(i1 == 27)
            j = 1;
        else
        if(i1 == 28)
            j = 2;
        else
        if(i1 == 29)
        {
            k = j;
            j = 4;
        } else
        if(i1 == 913)
            stringbuffer.append((char)ai1[l]);
          goto _L9
_L3:
        if(i1 < 26)
            c = (char)(i1 + 97);
        else
        if(i1 == 26)
            c = ' ';
        else
        if(i1 == 28)
            j = 0;
        else
        if(i1 == 28)
            j = 2;
        else
        if(i1 == 29)
        {
            k = j;
            j = 4;
        } else
        if(i1 == 913)
            stringbuffer.append((char)ai1[l]);
          goto _L9
_L4:
        if(i1 < 25)
            c = MIXED_CHARS[i1];
        else
        if(i1 == 25)
            j = 3;
        else
        if(i1 == 26)
            c = ' ';
        else
        if(i1 != 27)
            if(i1 == 28)
                j = 0;
            else
            if(i1 == 29)
            {
                k = j;
                j = 4;
            } else
            if(i1 == 913)
                stringbuffer.append((char)ai1[l]);
          goto _L9
_L5:
        if(i1 < 29)
            c = PUNCT_CHARS[i1];
        else
        if(i1 == 29)
            j = 0;
        else
        if(i1 == 913)
            stringbuffer.append((char)ai1[l]);
          goto _L9
        j = k;
        if(i1 < 29)
            c = PUNCT_CHARS[i1];
        else
        if(i1 == 29)
            j = 0;
          goto _L9
    }

    private static StringBuffer multiply(String s, int i)
    {
        StringBuffer stringbuffer;
        int j;
        stringbuffer = new StringBuffer(s.length());
        j = 0;
_L7:
        if(j < s.length()) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        int i1;
        int j1;
        k = i / 100;
        l = (i / 10) % 10;
        i1 = i % 10;
        j1 = 0;
_L8:
        if(j1 < i1) goto _L4; else goto _L3
_L3:
        int k1 = 0;
_L9:
        if(k1 < l) goto _L6; else goto _L5
_L5:
        int l1 = 0;
_L10:
        if(l1 >= k)
            return stringbuffer;
        break MISSING_BLOCK_LABEL_140;
_L2:
        stringbuffer.append('0');
        j++;
          goto _L7
_L4:
        stringbuffer = add(stringbuffer.toString(), s);
        j1++;
          goto _L8
_L6:
        stringbuffer = add(stringbuffer.toString(), (new StringBuilder(String.valueOf(s))).append('0').toString().substring(1));
        k1++;
          goto _L9
        stringbuffer = add(stringbuffer.toString(), (new StringBuilder(String.valueOf(s))).append("00").toString().substring(2));
        l1++;
          goto _L10
    }

    private static int numericCompaction(int ai[], int i, StringBuffer stringbuffer)
    {
        int j = 0;
        boolean flag = false;
        int ai1[] = new int[15];
        do
        {
            do
            {
                if(i >= ai[0] || flag)
                    return i;
                int k = i + 1;
                int l = ai[i];
                if(k == ai[0])
                    flag = true;
                if(l < 900)
                {
                    ai1[j] = l;
                    j++;
                    i = k;
                } else
                if(l == 900 || l == 901 || l == 924 || l == 928 || l == 923 || l == 922)
                {
                    i = k - 1;
                    flag = true;
                } else
                {
                    i = k;
                }
            } while(j % 15 != 0 && l != 902 && !flag);
            stringbuffer.append(decodeBase900toBase10(ai1, j));
            j = 0;
        } while(true);
    }

    private static int textCompaction(int ai[], int i, StringBuffer stringbuffer)
    {
        int ai1[] = new int[ai[0] << 1];
        int ai2[] = new int[ai[0] << 1];
        int j = 0;
        boolean flag = false;
        do
        {
            int k;
            int l;
            do
            {
                if(i >= ai[0] || flag)
                {
                    decodeTextCompaction(ai1, ai2, j, stringbuffer);
                    return i;
                }
                k = i + 1;
                l = ai[i];
                if(l >= 900)
                    break;
                ai1[j] = l / 30;
                ai1[j + 1] = l % 30;
                j += 2;
                i = k;
            } while(true);
            switch(l)
            {
            default:
                i = k;
                break;

            case 900: 
                i = k - 1;
                flag = true;
                break;

            case 901: 
                i = k - 1;
                flag = true;
                break;

            case 902: 
                i = k - 1;
                flag = true;
                break;

            case 913: 
                ai1[j] = 913;
                ai2[j] = l;
                j++;
                i = k;
                break;

            case 924: 
                i = k - 1;
                flag = true;
                break;
            }
        } while(true);
    }

    private static final int AL = 28;
    private static final int ALPHA = 0;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final String EXP900[];
    private static final int LL = 27;
    private static final int LOWER = 1;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int MIXED = 2;
    private static final char MIXED_CHARS[];
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int PUNCT = 3;
    private static final char PUNCT_CHARS[];
    private static final int PUNCT_SHIFT = 4;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    static 
    {
        char ac[] = new char[29];
        ac[0] = ';';
        ac[1] = '<';
        ac[2] = '>';
        ac[3] = '@';
        ac[4] = '[';
        ac[5] = '\\';
        ac[6] = '}';
        ac[7] = '_';
        ac[8] = '`';
        ac[9] = '~';
        ac[10] = '!';
        ac[11] = '\r';
        ac[12] = '\t';
        ac[13] = ',';
        ac[14] = ':';
        ac[15] = '\n';
        ac[16] = '-';
        ac[17] = '.';
        ac[18] = '$';
        ac[19] = '/';
        ac[20] = '"';
        ac[21] = '|';
        ac[22] = '*';
        ac[23] = '(';
        ac[24] = ')';
        ac[25] = '?';
        ac[26] = '{';
        ac[27] = '}';
        ac[28] = '\'';
        PUNCT_CHARS = ac;
        char ac1[] = new char[25];
        ac1[0] = '0';
        ac1[1] = '1';
        ac1[2] = '2';
        ac1[3] = '3';
        ac1[4] = '4';
        ac1[5] = '5';
        ac1[6] = '6';
        ac1[7] = '7';
        ac1[8] = '8';
        ac1[9] = '9';
        ac1[10] = '&';
        ac1[11] = '\r';
        ac1[12] = '\t';
        ac1[13] = ',';
        ac1[14] = ':';
        ac1[15] = '#';
        ac1[16] = '-';
        ac1[17] = '.';
        ac1[18] = '$';
        ac1[19] = '/';
        ac1[20] = '+';
        ac1[21] = '%';
        ac1[22] = '*';
        ac1[23] = '=';
        ac1[24] = '^';
        MIXED_CHARS = ac1;
        String as[] = new String[16];
        as[0] = "000000000000000000000000000000000000000000001";
        as[1] = "000000000000000000000000000000000000000000900";
        as[2] = "000000000000000000000000000000000000000810000";
        as[3] = "000000000000000000000000000000000000729000000";
        as[4] = "000000000000000000000000000000000656100000000";
        as[5] = "000000000000000000000000000000590490000000000";
        as[6] = "000000000000000000000000000531441000000000000";
        as[7] = "000000000000000000000000478296900000000000000";
        as[8] = "000000000000000000000430467210000000000000000";
        as[9] = "000000000000000000387420489000000000000000000";
        as[10] = "000000000000000348678440100000000000000000000";
        as[11] = "000000000000313810596090000000000000000000000";
        as[12] = "000000000282429536481000000000000000000000000";
        as[13] = "000000254186582832900000000000000000000000000";
        as[14] = "000228767924549610000000000000000000000000000";
        as[15] = "205891132094649000000000000000000000000000000";
        EXP900 = as;
    }
}
