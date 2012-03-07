// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GF256Poly

public final class GF256
{

    private GF256(int i)
    {
        int j;
        int k;
        expTable = new int[256];
        logTable = new int[256];
        j = 1;
        k = 0;
_L3:
        if(k < 256) goto _L2; else goto _L1
_L1:
        int l = 0;
_L4:
        if(l >= 255)
        {
            zero = new GF256Poly(this, new int[1]);
            int ai[] = new int[1];
            ai[0] = 1;
            one = new GF256Poly(this, ai);
            return;
        }
        break MISSING_BLOCK_LABEL_112;
_L2:
        expTable[k] = j;
        j <<= 1;
        if(j >= 256)
            j ^= i;
        k++;
          goto _L3
        logTable[expTable[l]] = l;
        l++;
          goto _L4
    }

    static int addOrSubtract(int i, int j)
    {
        return i ^ j;
    }

    GF256Poly buildMonomial(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException();
        GF256Poly gf256poly;
        if(j == 0)
        {
            gf256poly = zero;
        } else
        {
            int ai[] = new int[i + 1];
            ai[0] = j;
            gf256poly = new GF256Poly(this, ai);
        }
        return gf256poly;
    }

    int exp(int i)
    {
        return expTable[i];
    }

    GF256Poly getOne()
    {
        return one;
    }

    GF256Poly getZero()
    {
        return zero;
    }

    int inverse(int i)
    {
        if(i == 0)
            throw new ArithmeticException();
        else
            return expTable[255 - logTable[i]];
    }

    int log(int i)
    {
        if(i == 0)
            throw new IllegalArgumentException();
        else
            return logTable[i];
    }

    int multiply(int i, int j)
    {
        int k;
        if(i == 0 || j == 0)
        {
            k = 0;
        } else
        {
            int l = logTable[i] + logTable[j];
            k = expTable[(l & 0xff) + (l >>> 8)];
        }
        return k;
    }

    public static final GF256 DATA_MATRIX_FIELD = new GF256(301);
    public static final GF256 QR_CODE_FIELD = new GF256(285);
    private final int expTable[];
    private final int logTable[];
    private final GF256Poly one;
    private final GF256Poly zero;

}
