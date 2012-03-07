// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.*;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Mode, Version, ErrorCorrectionLevel

final class DecodedBitStreamParser
{

    private DecodedBitStreamParser()
    {
    }

    static DecoderResult decode(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel, Hashtable hashtable)
        throws FormatException
    {
        BitSource bitsource;
        StringBuffer stringbuffer;
        CharacterSetECI characterseteci;
        boolean flag;
        Vector vector;
        bitsource = new BitSource(abyte0);
        stringbuffer = new StringBuffer(50);
        characterseteci = null;
        flag = false;
        vector = new Vector(1);
_L5:
        Mode mode1;
        if(bitsource.available() < 4)
        {
            mode1 = Mode.TERMINATOR;
        } else
        {
            Mode mode;
            try
            {
                mode = Mode.forBits(bitsource.readBits(4));
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                throw FormatException.getFormatInstance();
            }
            mode1 = mode;
        }
        if(mode1.equals(Mode.TERMINATOR)) goto _L2; else goto _L1
_L1:
        if(!mode1.equals(Mode.FNC1_FIRST_POSITION) && !mode1.equals(Mode.FNC1_SECOND_POSITION)) goto _L4; else goto _L3
_L3:
        flag = true;
_L2:
        if(mode1.equals(Mode.TERMINATOR))
        {
            String s = stringbuffer.toString();
            Vector vector1;
            int i;
            if(vector.isEmpty())
                vector1 = null;
            else
                vector1 = vector;
            return new DecoderResult(abyte0, s, vector1, errorcorrectionlevel);
        }
        if(true) goto _L5; else goto _L4
_L4:
        if(mode1.equals(Mode.STRUCTURED_APPEND))
            bitsource.readBits(16);
        else
        if(mode1.equals(Mode.ECI))
        {
            characterseteci = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitsource));
            if(characterseteci == null)
                throw FormatException.getFormatInstance();
        } else
        {
            i = bitsource.readBits(mode1.getCharacterCountBits(version));
            if(mode1.equals(Mode.NUMERIC))
                decodeNumericSegment(bitsource, stringbuffer, i);
            else
            if(mode1.equals(Mode.ALPHANUMERIC))
                decodeAlphanumericSegment(bitsource, stringbuffer, i, flag);
            else
            if(mode1.equals(Mode.BYTE))
                decodeByteSegment(bitsource, stringbuffer, i, characterseteci, vector, hashtable);
            else
            if(mode1.equals(Mode.KANJI))
                decodeKanjiSegment(bitsource, stringbuffer, i);
            else
                throw FormatException.getFormatInstance();
        }
          goto _L2
    }

    private static void decodeAlphanumericSegment(BitSource bitsource, StringBuffer stringbuffer, int i, boolean flag)
        throws FormatException
    {
        int j = stringbuffer.length();
_L5:
        if(i > 1) goto _L2; else goto _L1
_L1:
        if(i == 1)
            stringbuffer.append(toAlphaNumericChar(bitsource.readBits(6)));
        if(!flag) goto _L4; else goto _L3
_L3:
        int l = j;
_L6:
        if(l < stringbuffer.length())
            break MISSING_BLOCK_LABEL_88;
_L4:
        return;
_L2:
        int k = bitsource.readBits(11);
        stringbuffer.append(toAlphaNumericChar(k / 45));
        stringbuffer.append(toAlphaNumericChar(k % 45));
        i -= 2;
          goto _L5
        if(stringbuffer.charAt(l) == '%')
            if(l < stringbuffer.length() - 1 && stringbuffer.charAt(l + 1) == '%')
                stringbuffer.deleteCharAt(l + 1);
            else
                stringbuffer.setCharAt(l, '\035');
        l++;
          goto _L6
    }

    private static void decodeByteSegment(BitSource bitsource, StringBuffer stringbuffer, int i, CharacterSetECI characterseteci, Vector vector, Hashtable hashtable)
        throws FormatException
    {
        byte abyte0[] = new byte[i];
        if(i << 3 > bitsource.available())
            throw FormatException.getFormatInstance();
        int j = 0;
        do
        {
            if(j >= i)
            {
                String s;
                if(characterseteci == null)
                    s = StringUtils.guessEncoding(abyte0, hashtable);
                else
                    s = characterseteci.getEncodingName();
                try
                {
                    stringbuffer.append(new String(abyte0, s));
                }
                catch(UnsupportedEncodingException unsupportedencodingexception)
                {
                    throw FormatException.getFormatInstance();
                }
                vector.addElement(abyte0);
                return;
            }
            abyte0[j] = (byte)bitsource.readBits(8);
            j++;
        } while(true);
    }

    private static void decodeKanjiSegment(BitSource bitsource, StringBuffer stringbuffer, int i)
        throws FormatException
    {
        byte abyte0[] = new byte[i * 2];
        int j = 0;
        do
        {
            int k;
            int l;
            int i1;
            if(i <= 0)
                try
                {
                    stringbuffer.append(new String(abyte0, "SJIS"));
                    return;
                }
                catch(UnsupportedEncodingException unsupportedencodingexception)
                {
                    throw FormatException.getFormatInstance();
                }
            k = bitsource.readBits(13);
            l = k / 192 << 8 | k % 192;
            if(l < 7936)
                i1 = l + 33088;
            else
                i1 = l + 49472;
            abyte0[j] = (byte)(i1 >> 8);
            abyte0[j + 1] = (byte)i1;
            j += 2;
            i--;
        } while(true);
    }

    private static void decodeNumericSegment(BitSource bitsource, StringBuffer stringbuffer, int i)
        throws FormatException
    {
_L4:
        if(i >= 3) goto _L2; else goto _L1
_L2:
        int j = bitsource.readBits(10);
        if(j >= 1000)
            throw FormatException.getFormatInstance();
        stringbuffer.append(toAlphaNumericChar(j / 100));
        stringbuffer.append(toAlphaNumericChar((j / 10) % 10));
        stringbuffer.append(toAlphaNumericChar(j % 10));
        i -= 3;
        if(true) goto _L4; else goto _L3
_L1:
        if(i != 2)
            break; /* Loop/switch isn't completed */
        int l = bitsource.readBits(7);
        if(l >= 100)
            throw FormatException.getFormatInstance();
        stringbuffer.append(toAlphaNumericChar(l / 10));
        stringbuffer.append(toAlphaNumericChar(l % 10));
_L6:
        return;
_L3:
        if(i == 1)
        {
            int k = bitsource.readBits(4);
            if(k >= 10)
                throw FormatException.getFormatInstance();
            stringbuffer.append(toAlphaNumericChar(k));
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static int parseECIValue(BitSource bitsource)
    {
        int i = bitsource.readBits(8);
        int j;
        if((i & 0x80) == 0)
            j = i & 0x7f;
        else
        if((i & 0xc0) == 128)
            j = bitsource.readBits(8) | (i & 0x3f) << 8;
        else
        if((i & 0xe0) == 192)
            j = bitsource.readBits(16) | (i & 0x1f) << 16;
        else
            throw new IllegalArgumentException((new StringBuilder("Bad ECI bits starting with byte ")).append(i).toString());
        return j;
    }

    private static char toAlphaNumericChar(int i)
        throws FormatException
    {
        if(i >= ALPHANUMERIC_CHARS.length)
            throw FormatException.getFormatInstance();
        else
            return ALPHANUMERIC_CHARS[i];
    }

    private static final char ALPHANUMERIC_CHARS[];

    static 
    {
        char ac[] = new char[45];
        ac[0] = '0';
        ac[1] = '1';
        ac[2] = '2';
        ac[3] = '3';
        ac[4] = '4';
        ac[5] = '5';
        ac[6] = '6';
        ac[7] = '7';
        ac[8] = '8';
        ac[9] = '9';
        ac[10] = 'A';
        ac[11] = 'B';
        ac[12] = 'C';
        ac[13] = 'D';
        ac[14] = 'E';
        ac[15] = 'F';
        ac[16] = 'G';
        ac[17] = 'H';
        ac[18] = 'I';
        ac[19] = 'J';
        ac[20] = 'K';
        ac[21] = 'L';
        ac[22] = 'M';
        ac[23] = 'N';
        ac[24] = 'O';
        ac[25] = 'P';
        ac[26] = 'Q';
        ac[27] = 'R';
        ac[28] = 'S';
        ac[29] = 'T';
        ac[30] = 'U';
        ac[31] = 'V';
        ac[32] = 'W';
        ac[33] = 'X';
        ac[34] = 'Y';
        ac[35] = 'Z';
        ac[36] = ' ';
        ac[37] = '$';
        ac[38] = '%';
        ac[39] = '*';
        ac[40] = '+';
        ac[41] = '-';
        ac[42] = '.';
        ac[43] = '/';
        ac[44] = ':';
        ALPHANUMERIC_CHARS = ac;
    }
}
