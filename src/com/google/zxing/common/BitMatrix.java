// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


// Referenced classes of package com.google.zxing.common:
//            BitArray

public final class BitMatrix
{

    public BitMatrix(int i)
    {
        this(i, i);
    }

    public BitMatrix(int i, int j)
    {
        if(i < 1 || j < 1)
        {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        } else
        {
            width = i;
            height = j;
            rowSize = i + 31 >> 5;
            bits = new int[j * rowSize];
            return;
        }
    }

    public void clear()
    {
        int i = bits.length;
        int j = 0;
        do
        {
            if(j >= i)
                return;
            bits[j] = 0;
            j++;
        } while(true);
    }

    @Override
	public boolean equals(Object obj)
    {
        if(obj instanceof BitMatrix) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        BitMatrix bitmatrix;
        int i;
        bitmatrix = (BitMatrix)obj;
        if(width != bitmatrix.width || height != bitmatrix.height || rowSize != bitmatrix.rowSize || bits.length != bitmatrix.bits.length)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        i = 0;
_L5:
        if(i >= bits.length)
        {
            flag = true;
        } else
        {
label0:
            {
                if(bits[i] == bitmatrix.bits[i])
                    break label0;
                flag = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void flip(int i, int j)
    {
        int k = j * rowSize + (i >> 5);
        int ai[] = bits;
        ai[k] = ai[k] ^ 1 << (i & 0x1f);
    }

    public boolean get(int i, int j)
    {
        int k = j * rowSize + (i >> 5);
        boolean flag;
        if((1 & bits[k] >>> (i & 0x1f)) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getHeight()
    {
        return height;
    }

    public BitArray getRow(int i, BitArray bitarray)
    {
        if(bitarray == null || bitarray.getSize() < width)
            bitarray = new BitArray(width);
        int j = i * rowSize;
        int k = 0;
        do
        {
            if(k >= rowSize)
                return bitarray;
            bitarray.setBulk(k << 5, bits[j + k]);
            k++;
        } while(true);
    }

    public int[] getTopLeftOnBit()
    {
        int i = 0;
        do
        {
            if(i >= bits.length || bits[i] != 0)
            {
                int ai[];
                if(i == bits.length)
                {
                    ai = null;
                } else
                {
label0:
                    {
                        int j = i / rowSize;
                        int k = i % rowSize << 5;
                        int l = bits[i];
                        int j1;
                        for(int i1 = 0; l << 31 - i1 == 0; i1++)
                            break label0;

                        j1 = k + i1;
                        ai = new int[2];
                        ai[0] = j1;
                        ai[1] = j;
                    }
                }
                return ai;
            }
            i++;
        } while(true);
    }

    public int getWidth()
    {
        return width;
    }

    @Override
	public int hashCode()
    {
        int i = 31 * (31 * (31 * width + width) + height) + rowSize;
        int j = 0;
        do
        {
            if(j >= bits.length)
                return i;
            i = i * 31 + bits[j];
            j++;
        } while(true);
    }

    public void set(int i, int j)
    {
        int k = j * rowSize + (i >> 5);
        int ai[] = bits;
        ai[k] = ai[k] | 1 << (i & 0x1f);
    }

    public void setRegion(int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int k1;
        if(j < 0 || i < 0)
            throw new IllegalArgumentException("Left and top must be nonnegative");
        if(l < 1 || k < 1)
            throw new IllegalArgumentException("Height and width must be at least 1");
        i1 = i + k;
        j1 = j + l;
        if(j1 > height || i1 > width)
            throw new IllegalArgumentException("The region must fit inside the matrix");
        k1 = j;
_L2:
        if(k1 >= j1)
            return;
        int l1 = k1 * rowSize;
        int i2 = i;
        do
        {
label0:
            {
                if(i2 < i1)
                    break label0;
                k1++;
            }
            if(true)
                continue;
            int ai[] = bits;
            int j2 = l1 + (i2 >> 5);
            ai[j2] = ai[j2] | 1 << (i2 & 0x1f);
            i2++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer;
        int i;
        stringbuffer = new StringBuffer(height * (1 + width));
        i = 0;
_L2:
        if(i >= height)
            return stringbuffer.toString();
        int j = 0;
        do
        {
label0:
            {
                if(j < width)
                    break label0;
                stringbuffer.append('\n');
                i++;
            }
            if(true)
                continue;
            String s;
            if(get(j, i))
                s = "X ";
            else
                s = "  ";
            stringbuffer.append(s);
            j++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final int bits[];
    public final int height;
    public final int rowSize;
    public final int width;
}
