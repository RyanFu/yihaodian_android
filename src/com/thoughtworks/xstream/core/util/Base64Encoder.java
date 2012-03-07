// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.io.*;

public class Base64Encoder
{

    public Base64Encoder()
    {
    }

    private int mapCharToInt(Reader reader)
        throws IOException
    {
_L2:
        int i = reader.read();
        int j;
        if(i != -1)
        {
            int k = REVERSE_MAPPING[i];
            if(k == 0)
                continue; /* Loop/switch isn't completed */
            j = k - 1;
        } else
        {
            j = -1;
        }
_L3:
        return j;
        if(i != 61) goto _L2; else goto _L1
_L1:
        j = -1;
          goto _L3
    }

    public byte[] decode(String s)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        StringReader stringreader;
        int i;
        bytearrayoutputstream = new ByteArrayOutputStream();
        stringreader = new StringReader(s);
        i = 0;
_L5:
        if(i >= s.length()) goto _L2; else goto _L1
_L1:
        int ai[];
        int j;
        int k;
        ai = new int[4];
        ai[0] = mapCharToInt(stringreader);
        ai[1] = mapCharToInt(stringreader);
        ai[2] = mapCharToInt(stringreader);
        ai[3] = mapCharToInt(stringreader);
        j = (0x3f & ai[0]) << 18 | (0x3f & ai[1]) << 12 | (0x3f & ai[2]) << 6 | 0x3f & ai[3];
        k = 0;
_L4:
        if(k >= 3)
            break MISSING_BLOCK_LABEL_205;
        if(ai[k + 1] >= 0)
            bytearrayoutputstream.write(0xff & j >> 8 * (2 - k));
          goto _L3
_L2:
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        return abyte0;
        IOException ioexception;
        ioexception;
        throw new Error(ioexception + ": " + ioexception.getMessage());
_L3:
        k++;
          goto _L4
        i += 4;
          goto _L5
    }

    public String encode(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        for(int j = 0; j < abyte0.length; j += 3)
        {
            int k = Math.min(3, abyte0.length - j);
            int l = (0xff & abyte0[j]) << 16;
            int i1;
            int j1;
            int k1;
            int l1;
            int i2;
            if(k <= 1)
                i1 = 0;
            else
                i1 = 0xff & abyte0[j + 1];
            j1 = l | i1 << 8;
            if(k <= 2)
                k1 = 0;
            else
                k1 = 0xff & abyte0[j + 2];
            l1 = j1 | k1;
            i2 = 0;
            while(i2 < 4) 
            {
                char c;
                if(k + 1 > i2)
                    c = SIXTY_FOUR_CHARS[0x3f & l1 >> 6 * (3 - i2)];
                else
                    c = '=';
                stringbuffer.append(c);
                i2++;
            }
            if((i += 4) % 76 == 0)
                stringbuffer.append('\n');
        }

        return stringbuffer.toString();
    }

    private static final int REVERSE_MAPPING[];
    private static final char SIXTY_FOUR_CHARS[];

    static 
    {
        SIXTY_FOUR_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        REVERSE_MAPPING = new int[123];
        for(int i = 0; i < SIXTY_FOUR_CHARS.length; i++)
            REVERSE_MAPPING[SIXTY_FOUR_CHARS[i]] = i + 1;

    }
}
