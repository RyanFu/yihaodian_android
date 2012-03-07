// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;

public final class ByteMatrix
{

    public ByteMatrix(int i, int j)
    {
        int ai[] = new int[2];
        ai[0] = j;
        ai[1] = i;
        bytes = (byte[][])Array.newInstance(Byte.TYPE, ai);
        width = i;
        height = j;
    }

    public void clear(byte byte0)
    {
        int i = 0;
_L2:
        if(i >= height)
            return;
        int j = 0;
        do
        {
label0:
            {
                if(j < width)
                    break label0;
                i++;
            }
            if(true)
                continue;
            bytes[i][j] = byte0;
            j++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public byte get(int i, int j)
    {
        return bytes[j][i];
    }

    public byte[][] getArray()
    {
        return bytes;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void set(int i, int j, byte byte0)
    {
        bytes[j][i] = byte0;
    }

    public void set(int i, int j, int k)
    {
        bytes[j][i] = (byte)k;
    }

    public void set(int i, int j, boolean flag)
    {
        byte abyte0[] = bytes[j];
        int k;
        if(flag)
            k = 1;
        else
            k = 0;
        abyte0[i] = (byte)k;
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer(2 + 2 * width * height);
        i = 0;
_L2:
        int j;
        if(i >= height)
            return stringbuffer.toString();
        j = 0;
_L6:
label0:
        {
            if(j < width)
                break label0;
            stringbuffer.append('\n');
            i++;
        }
        if(true) goto _L2; else goto _L1
_L1:
        bytes[i][j];
        JVM INSTR tableswitch 0 1: default 88
    //                   0 101
    //                   1 111;
           goto _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_111;
_L3:
        stringbuffer.append("  ");
_L7:
        j++;
          goto _L6
_L4:
        stringbuffer.append(" 0");
          goto _L7
        stringbuffer.append(" 1");
          goto _L7
    }

    private final byte bytes[][];
    private final int height;
    private final int width;
}
