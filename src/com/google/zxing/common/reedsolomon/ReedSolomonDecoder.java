// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            ReedSolomonException, GF256Poly, GF256

public final class ReedSolomonDecoder
{

    public ReedSolomonDecoder(GF256 gf256)
    {
        field = gf256;
    }

    private int[] findErrorLocations(GF256Poly gf256poly)
        throws ReedSolomonException
    {
        int i = gf256poly.getDegree();
        int ai1[];
        if(i == 1)
        {
            ai1 = new int[1];
            ai1[0] = gf256poly.getCoefficient(1);
        } else
        {
            int ai[] = new int[i];
            int j = 0;
            int k = 1;
            do
            {
                if(k >= 256 || j >= i)
                {
                    if(j != i)
                        throw new ReedSolomonException("Error locator degree does not match number of roots");
                    break;
                }
                if(gf256poly.evaluateAt(k) == 0)
                {
                    ai[j] = field.inverse(k);
                    j++;
                }
                k++;
            } while(true);
            ai1 = ai;
        }
        return ai1;
    }

    private int[] findErrorMagnitudes(GF256Poly gf256poly, int ai[], boolean flag)
    {
        int i;
        int ai1[];
        int j;
        i = ai.length;
        ai1 = new int[i];
        j = 0;
_L2:
        if(j >= i)
            return ai1;
        int k = field.inverse(ai[j]);
        int l = 1;
        int i1 = 0;
        do
        {
label0:
            {
                if(i1 < i)
                    break label0;
                ai1[j] = field.multiply(gf256poly.evaluateAt(k), field.inverse(l));
                if(flag)
                    ai1[j] = field.multiply(ai1[j], k);
                j++;
            }
            if(true)
                continue;
            if(j != i1)
            {
                int j1 = field.multiply(ai[i1], k);
                int k1;
                if((j1 & 1) == 0)
                    k1 = j1 | 1;
                else
                    k1 = j1 & -2;
                l = field.multiply(l, k1);
            }
            i1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private GF256Poly[] runEuclideanAlgorithm(GF256Poly gf256poly, GF256Poly gf256poly1, int i)
        throws ReedSolomonException
    {
        GF256Poly gf256poly2;
        GF256Poly gf256poly3;
        GF256Poly gf256poly4;
        GF256Poly gf256poly5;
        GF256Poly gf256poly6;
        GF256Poly gf256poly7;
        if(gf256poly.getDegree() < gf256poly1.getDegree())
        {
            GF256Poly gf256poly16 = gf256poly;
            gf256poly = gf256poly1;
            gf256poly1 = gf256poly16;
        }
        gf256poly2 = gf256poly;
        gf256poly3 = gf256poly1;
        gf256poly4 = field.getOne();
        gf256poly5 = field.getZero();
        gf256poly6 = field.getZero();
        gf256poly7 = field.getOne();
_L1:
        if(gf256poly3.getDegree() < i / 2)
        {
            int l1 = gf256poly7.getCoefficient(0);
            GF256Poly gf256poly8;
            GF256Poly gf256poly9;
            GF256Poly gf256poly10;
            GF256Poly gf256poly11;
            int j;
            int k;
            int l;
            int i1;
            GF256 gf256;
            int j1;
            int k1;
            GF256Poly gf256poly12;
            GF256Poly gf256poly13;
            if(l1 == 0)
            {
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            } else
            {
                int i2 = field.inverse(l1);
                GF256Poly gf256poly14 = gf256poly7.multiply(i2);
                GF256Poly gf256poly15 = gf256poly3.multiply(i2);
                GF256Poly agf256poly[] = new GF256Poly[2];
                agf256poly[0] = gf256poly14;
                agf256poly[1] = gf256poly15;
                return agf256poly;
            }
        }
        gf256poly8 = gf256poly2;
        gf256poly9 = gf256poly4;
        gf256poly10 = gf256poly6;
        gf256poly2 = gf256poly3;
        gf256poly4 = gf256poly5;
        gf256poly6 = gf256poly7;
        if(gf256poly2.isZero())
            throw new ReedSolomonException("r_{i-1} was zero");
        gf256poly3 = gf256poly8;
        gf256poly11 = field.getZero();
        j = gf256poly2.getDegree();
        k = gf256poly2.getCoefficient(j);
        l = field.inverse(k);
label0:
        {
            if(gf256poly3.getDegree() >= gf256poly2.getDegree() && !gf256poly3.isZero())
                break label0;
            gf256poly5 = gf256poly11.multiply(gf256poly4).addOrSubtract(gf256poly9);
            gf256poly7 = gf256poly11.multiply(gf256poly6).addOrSubtract(gf256poly10);
        }
          goto _L1
        i1 = gf256poly3.getDegree() - gf256poly2.getDegree();
        gf256 = field;
        j1 = gf256poly3.getDegree();
        k1 = gf256.multiply(gf256poly3.getCoefficient(j1), l);
        gf256poly12 = field.buildMonomial(i1, k1);
        gf256poly11 = gf256poly11.addOrSubtract(gf256poly12);
        gf256poly13 = gf256poly2.multiplyByMonomial(i1, k1);
        gf256poly3 = gf256poly3.addOrSubtract(gf256poly13);
        break MISSING_BLOCK_LABEL_177;
    }

    public void decode(int ai[], int i)
        throws ReedSolomonException
    {
        GF256Poly gf256poly;
        int ai1[];
        boolean flag;
        boolean flag1;
        int j;
        gf256poly = new GF256Poly(field, ai);
        ai1 = new int[i];
        flag = field.equals(GF256.DATA_MATRIX_FIELD);
        flag1 = true;
        j = 0;
_L5:
        if(j < i) goto _L2; else goto _L1
_L1:
        if(!flag1) goto _L4; else goto _L3
_L3:
        return;
_L2:
        GF256 gf256 = field;
        int k;
        int l;
        if(flag)
            k = j + 1;
        else
            k = j;
        l = gf256poly.evaluateAt(gf256.exp(k));
        ai1[ai1.length - 1 - j] = l;
        if(l != 0)
            flag1 = false;
        j++;
          goto _L5
_L4:
        int ai2[];
        int ai3[];
        int i1;
        GF256Poly gf256poly1 = new GF256Poly(field, ai1);
        GF256Poly agf256poly[] = runEuclideanAlgorithm(field.buildMonomial(i, 1), gf256poly1, i);
        GF256Poly gf256poly2 = agf256poly[0];
        GF256Poly gf256poly3 = agf256poly[1];
        ai2 = findErrorLocations(gf256poly2);
        ai3 = findErrorMagnitudes(gf256poly3, ai2, flag);
        i1 = 0;
_L7:
        int j1 = ai2.length;
        if(i1 >= j1) goto _L3; else goto _L6
_L6:
        int k1 = ai.length - 1 - field.log(ai2[i1]);
        if(k1 < 0)
            throw new ReedSolomonException("Bad error location");
        ai[k1] = GF256.addOrSubtract(ai[k1], ai3[i1]);
        i1++;
          goto _L7
    }

    private final GF256 field;
}
