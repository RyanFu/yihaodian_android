// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.reedsolomon;

import java.util.Vector;

// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GF256, GF256Poly

public final class ReedSolomonEncoder
{

    public ReedSolomonEncoder(GF256 gf256)
    {
        if(!GF256.QR_CODE_FIELD.equals(gf256))
        {
            throw new IllegalArgumentException("Only QR Code is supported at this time");
        } else
        {
            field = gf256;
            cachedGenerators = new Vector();
            Vector vector = cachedGenerators;
            int ai[] = new int[1];
            ai[0] = 1;
            vector.addElement(new GF256Poly(gf256, ai));
            return;
        }
    }

    private GF256Poly buildGenerator(int i)
    {
        if(i < cachedGenerators.size()) goto _L2; else goto _L1
_L1:
        GF256Poly gf256poly;
        int j;
        gf256poly = (GF256Poly)cachedGenerators.elementAt(cachedGenerators.size() - 1);
        j = cachedGenerators.size();
_L5:
        if(j <= i) goto _L3; else goto _L2
_L2:
        return (GF256Poly)cachedGenerators.elementAt(i);
_L3:
        GF256 gf256 = field;
        int ai[] = new int[2];
        ai[0] = 1;
        ai[1] = field.exp(j - 1);
        GF256Poly gf256poly1 = gf256poly.multiply(new GF256Poly(gf256, ai));
        cachedGenerators.addElement(gf256poly1);
        gf256poly = gf256poly1;
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void encode(int ai[], int i)
    {
        if(i == 0)
            throw new IllegalArgumentException("No error correction bytes");
        int j = ai.length - i;
        if(j <= 0)
            throw new IllegalArgumentException("No data bytes provided");
        GF256Poly gf256poly = buildGenerator(i);
        int ai1[] = new int[j];
        System.arraycopy(ai, 0, ai1, 0, j);
        int ai2[] = (new GF256Poly(field, ai1)).multiplyByMonomial(i, 1).divide(gf256poly)[1].getCoefficients();
        int k = i - ai2.length;
        int l = 0;
        do
        {
            if(l >= k)
            {
                System.arraycopy(ai2, 0, ai, j + k, ai2.length);
                return;
            }
            ai[j + l] = 0;
            l++;
        } while(true);
    }

    private final Vector cachedGenerators;
    private final GF256 field;
}
