// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.http;


public class BASE64Encoder
{

    public BASE64Encoder()
    {
    }

    public String encode(byte abyte0[])
    {
        StringBuffer stringbuffer;
        int i;
        char c;
        int j;
        stringbuffer = new StringBuffer(3 + (int)(1.3400000000000001D * abyte0.length));
        i = 0;
        c = '\0';
        j = 0;
_L10:
        if(i >= abyte0.length)
            break; /* Loop/switch isn't completed */
        j %= 8;
_L7:
        if(j >= 8)
            break MISSING_BLOCK_LABEL_214;
        j;
        JVM INSTR tableswitch 0 6: default 92
    //                   0 109
    //                   1 92
    //                   2 123
    //                   3 92
    //                   4 135
    //                   5 92
    //                   6 175;
           goto _L1 _L2 _L1 _L3 _L1 _L4 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_175;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        stringbuffer.append(encodeTable[c]);
        j += 6;
        if(true) goto _L7; else goto _L6
_L6:
        c = (abyte0[i] & lead6byte) >>> 2;
          goto _L8
_L3:
        c = abyte0[i] & last6byte;
          goto _L8
_L4:
        c = (abyte0[i] & last4byte) << 2;
        if(i + 1 < abyte0.length)
            c |= (abyte0[i + 1] & lead2byte) >>> 6;
          goto _L8
        c = (abyte0[i] & last2byte) << 4;
        if(i + 1 < abyte0.length)
            c |= (abyte0[i + 1] & lead4byte) >>> 4;
          goto _L8
        i++;
        if(true) goto _L10; else goto _L9
_L9:
        if(stringbuffer.length() % 4 != 0)
        {
            for(int k = 4 - stringbuffer.length() % 4; k > 0; k--)
                stringbuffer.append("=");

        }
        return stringbuffer.toString();
    }

    private static final char encodeTable[];
    private static final char last2byte = (char)Integer.parseInt("00000011", 2);
    private static final char last4byte = (char)Integer.parseInt("00001111", 2);
    private static final char last6byte = (char)Integer.parseInt("00111111", 2);
    private static final char lead2byte = (char)Integer.parseInt("11000000", 2);
    private static final char lead4byte = (char)Integer.parseInt("11110000", 2);
    private static final char lead6byte = (char)Integer.parseInt("11111100", 2);

    static 
    {
        char ac[] = new char[64];
        ac[0] = 'A';
        ac[1] = 'B';
        ac[2] = 'C';
        ac[3] = 'D';
        ac[4] = 'E';
        ac[5] = 'F';
        ac[6] = 'G';
        ac[7] = 'H';
        ac[8] = 'I';
        ac[9] = 'J';
        ac[10] = 'K';
        ac[11] = 'L';
        ac[12] = 'M';
        ac[13] = 'N';
        ac[14] = 'O';
        ac[15] = 'P';
        ac[16] = 'Q';
        ac[17] = 'R';
        ac[18] = 'S';
        ac[19] = 'T';
        ac[20] = 'U';
        ac[21] = 'V';
        ac[22] = 'W';
        ac[23] = 'X';
        ac[24] = 'Y';
        ac[25] = 'Z';
        ac[26] = 'a';
        ac[27] = 'b';
        ac[28] = 'c';
        ac[29] = 'd';
        ac[30] = 'e';
        ac[31] = 'f';
        ac[32] = 'g';
        ac[33] = 'h';
        ac[34] = 'i';
        ac[35] = 'j';
        ac[36] = 'k';
        ac[37] = 'l';
        ac[38] = 'm';
        ac[39] = 'n';
        ac[40] = 'o';
        ac[41] = 'p';
        ac[42] = 'q';
        ac[43] = 'r';
        ac[44] = 's';
        ac[45] = 't';
        ac[46] = 'u';
        ac[47] = 'v';
        ac[48] = 'w';
        ac[49] = 'x';
        ac[50] = 'y';
        ac[51] = 'z';
        ac[52] = '0';
        ac[53] = '1';
        ac[54] = '2';
        ac[55] = '3';
        ac[56] = '4';
        ac[57] = '5';
        ac[58] = '6';
        ac[59] = '7';
        ac[60] = '8';
        ac[61] = '9';
        ac[62] = '+';
        ac[63] = '/';
        encodeTable = ac;
    }
}
