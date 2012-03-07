// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GF256

final class GF256Poly
{

    GF256Poly(GF256 gf256, int ai[])
    {
        int i;
        int j;
        if(ai == null || ai.length == 0)
            throw new IllegalArgumentException();
        field = gf256;
        i = ai.length;
        if(i <= 1 || ai[0] != 0)
            break MISSING_BLOCK_LABEL_109;
        j = 1;
_L3:
        if(j < i && ai[j] == 0) goto _L2; else goto _L1
_L1:
        if(j == i)
        {
            coefficients = gf256.getZero().coefficients;
        } else
        {
            coefficients = new int[i - j];
            System.arraycopy(ai, j, coefficients, 0, coefficients.length);
        }
_L4:
        return;
_L2:
        j++;
          goto _L3
        coefficients = ai;
          goto _L4
    }

    GF256Poly addOrSubtract(GF256Poly gf256poly)
    {
        if(!field.equals(gf256poly.field))
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        if(!isZero()) goto _L2; else goto _L1
_L1:
        GF256Poly gf256poly1 = gf256poly;
_L4:
        return gf256poly1;
_L2:
        int ai[];
        int ai1[];
        int ai2[];
        int i;
        int j;
        if(gf256poly.isZero())
        {
            gf256poly1 = this;
            continue; /* Loop/switch isn't completed */
        }
        ai = coefficients;
        ai1 = gf256poly.coefficients;
        if(ai.length > ai1.length)
        {
            int ai3[] = ai;
            ai = ai1;
            ai1 = ai3;
        }
        ai2 = new int[ai1.length];
        i = ai1.length - ai.length;
        System.arraycopy(ai1, 0, ai2, 0, i);
        j = i;
_L5:
label0:
        {
            if(j < ai1.length)
                break label0;
            gf256poly1 = new GF256Poly(field, ai2);
        }
        if(true) goto _L4; else goto _L3
_L3:
        ai2[j] = GF256.addOrSubtract(ai[j - i], ai1[j]);
        j++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    GF256Poly[] divide(GF256Poly gf256poly)
    {
        if(!field.equals(gf256poly.field))
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        if(gf256poly.isZero())
            throw new IllegalArgumentException("Divide by 0");
        GF256Poly gf256poly1 = field.getZero();
        GF256Poly gf256poly2 = this;
        int i = gf256poly.getCoefficient(gf256poly.getDegree());
        int j = field.inverse(i);
        do
        {
            if(gf256poly2.getDegree() < gf256poly.getDegree() || gf256poly2.isZero())
            {
                GF256Poly agf256poly[] = new GF256Poly[2];
                agf256poly[0] = gf256poly1;
                agf256poly[1] = gf256poly2;
                return agf256poly;
            }
            int k = gf256poly2.getDegree() - gf256poly.getDegree();
            int l = field.multiply(gf256poly2.getCoefficient(gf256poly2.getDegree()), j);
            GF256Poly gf256poly3 = gf256poly.multiplyByMonomial(k, l);
            gf256poly1 = gf256poly1.addOrSubtract(field.buildMonomial(k, l));
            gf256poly2 = gf256poly2.addOrSubtract(gf256poly3);
        } while(true);
    }

    int evaluateAt(int i)
    {
        if(i != 0) goto _L2; else goto _L1
_L1:
        int i1 = getCoefficient(0);
_L4:
        return i1;
_L2:
        int j;
        int k;
        int l;
        j = coefficients.length;
        if(i == 1)
        {
            int j1 = 0;
            int k1 = 0;
            do
            {
                if(k1 >= j)
                {
                    i1 = j1;
                    continue; /* Loop/switch isn't completed */
                }
                j1 = GF256.addOrSubtract(j1, coefficients[k1]);
                k1++;
            } while(true);
        }
        k = coefficients[0];
        l = 1;
_L5:
label0:
        {
            if(l < j)
                break label0;
            i1 = k;
        }
        if(true) goto _L4; else goto _L3
_L3:
        k = GF256.addOrSubtract(field.multiply(i, k), coefficients[l]);
        l++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    int getCoefficient(int i)
    {
        return coefficients[coefficients.length - 1 - i];
    }

    int[] getCoefficients()
    {
        return coefficients;
    }

    int getDegree()
    {
        return coefficients.length - 1;
    }

    boolean isZero()
    {
        boolean flag;
        if(coefficients[0] == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    GF256Poly multiply(int i)
    {
        if(i != 0) goto _L2; else goto _L1
_L1:
        GF256Poly gf256poly = field.getZero();
_L4:
        return gf256poly;
_L2:
        int j;
        int ai[];
        int k;
        if(i == 1)
        {
            gf256poly = this;
            continue; /* Loop/switch isn't completed */
        }
        j = coefficients.length;
        ai = new int[j];
        k = 0;
_L5:
label0:
        {
            if(k < j)
                break label0;
            gf256poly = new GF256Poly(field, ai);
        }
        if(true) goto _L4; else goto _L3
_L3:
        ai[k] = field.multiply(coefficients[k], i);
        k++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    GF256Poly multiply(GF256Poly gf256poly)
    {
        if(!field.equals(gf256poly.field))
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        if(!isZero() && !gf256poly.isZero()) goto _L2; else goto _L1
_L1:
        GF256Poly gf256poly1 = field.getZero();
_L4:
        return gf256poly1;
_L2:
        int ai[];
        int i;
        int ai1[];
        int j;
        int ai2[];
        int k;
        ai = coefficients;
        i = ai.length;
        ai1 = gf256poly.coefficients;
        j = ai1.length;
        ai2 = new int[(i + j) - 1];
        k = 0;
_L5:
label0:
        {
            if(k < i)
                break label0;
            gf256poly1 = new GF256Poly(field, ai2);
        }
        if(true) goto _L4; else goto _L3
_L3:
        int l;
        int i1;
        l = ai[k];
        i1 = 0;
_L6:
label1:
        {
            if(i1 < j)
                break label1;
            k++;
        }
          goto _L5
        ai2[k + i1] = GF256.addOrSubtract(ai2[k + i1], field.multiply(l, ai1[i1]));
        i1++;
          goto _L6
    }

    GF256Poly multiplyByMonomial(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException();
        if(j != 0) goto _L2; else goto _L1
_L1:
        GF256Poly gf256poly = field.getZero();
_L4:
        return gf256poly;
_L2:
        int k = coefficients.length;
        int ai[] = new int[k + i];
        int l = 0;
        do
        {
label0:
            {
                if(l < k)
                    break label0;
                gf256poly = new GF256Poly(field, ai);
            }
            if(true)
                continue;
            ai[l] = field.multiply(coefficients[l], j);
            l++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(8 * getDegree());
        int i = getDegree();
        do
        {
            if(i < 0)
                return stringbuffer.toString();
            int j = getCoefficient(i);
            if(j != 0)
            {
                if(j < 0)
                {
                    stringbuffer.append(" - ");
                    j = -j;
                } else
                if(stringbuffer.length() > 0)
                    stringbuffer.append(" + ");
                if(i == 0 || j != 1)
                {
                    int k = field.log(j);
                    if(k == 0)
                        stringbuffer.append('1');
                    else
                    if(k == 1)
                    {
                        stringbuffer.append('a');
                    } else
                    {
                        stringbuffer.append("a^");
                        stringbuffer.append(k);
                    }
                }
                if(i != 0)
                    if(i == 1)
                    {
                        stringbuffer.append('x');
                    } else
                    {
                        stringbuffer.append("x^");
                        stringbuffer.append(i);
                    }
            }
            i--;
        } while(true);
    }

    private final int coefficients[];
    private final GF256 field;
}
