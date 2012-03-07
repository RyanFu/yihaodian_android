// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;


public final class RSSUtils
{

    private RSSUtils()
    {
    }

    static int combins(int i, int j)
    {
        int k;
        int i1;
        int j1;
        int k1;
        int l;
        if(i - j > j)
        {
            k = j;
            l = i - j;
        } else
        {
            k = i - j;
            l = j;
        }
        i1 = 1;
        j1 = 1;
        k1 = i;
_L3:
        if(k1 > l) goto _L2; else goto _L1
_L1:
        if(j1 > k)
            return i1;
        break MISSING_BLOCK_LABEL_75;
_L2:
        i1 *= k1;
        if(j1 <= k)
        {
            i1 /= j1;
            j1++;
        }
        k1--;
          goto _L3
        i1 /= j1;
        j1++;
          goto _L1
    }

    static int[] elements(int ai[], int i, int j)
    {
        int ai1[];
        int k;
        int l;
        int i1;
        int j1;
        ai1 = new int[2 + ai.length];
        k = j << 1;
        ai1[0] = 1;
        l = 10;
        i1 = 1;
        j1 = 1;
_L5:
        if(j1 < k - 2) goto _L2; else goto _L1
_L1:
        ai1[k - 1] = i - i1;
        if(ai1[k - 1] < l)
            l = ai1[k - 1];
        if(l <= 1) goto _L4; else goto _L3
_L3:
        int k1 = 0;
_L6:
        if(k1 < k)
            break MISSING_BLOCK_LABEL_151;
_L4:
        return ai1;
_L2:
        ai1[j1] = ai[j1 - 1] - ai1[j1 - 1];
        ai1[j1 + 1] = ai[j1] - ai1[j1];
        i1 += ai1[j1] + ai1[j1 + 1];
        if(ai1[j1] < l)
            l = ai1[j1];
        j1 += 2;
          goto _L5
        ai1[k1] = ai1[k1] + (l - 1);
        int l1 = k1 + 1;
        ai1[l1] = ai1[l1] - (l - 1);
        k1 += 2;
          goto _L6
    }

    public static int getRSSvalue(int ai[], int i, boolean flag)
    {
        int j;
        int i1;
        int j1;
        int k1;
        int l1;
        j = ai.length;
        int k = 0;
        int l = 0;
        do
        {
            if(l >= j)
            {
                i1 = 0;
                j1 = 0;
                k1 = 0;
                l1 = k;
                break MISSING_BLOCK_LABEL_28;
            }
            k += ai[l];
            l++;
        } while(true);
_L2:
        int i2;
        int j2;
        int k2;
        if(k1 >= j - 1)
            return i1;
        i2 = 1;
        j2 = j1 | 1 << k1;
        k2 = i1;
_L5:
label0:
        {
            if(i2 < ai[k1])
                break label0;
            int j4 = l1 - i2;
            k1++;
            j1 = j2;
            i1 = k2;
            l1 = j4;
        }
        if(true) goto _L2; else goto _L1
_L1:
        int l2 = combins(l1 - i2 - 1, j - k1 - 2);
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        if(flag && j2 == 0 && l1 - i2 - (j - k1 - 1) >= j - k1 - 1)
            i3 = l2 - combins(l1 - i2 - (j - k1), j - k1 - 2);
        else
            i3 = l2;
        if(j - k1 - 1 <= 1)
            break MISSING_BLOCK_LABEL_282;
        l3 = 0;
        i4 = l1 - i2 - (j - k1 - 2);
        if(i4 > i) goto _L4; else goto _L3
_L3:
        j3 = i3 - l3 * (j - 1 - k1);
_L6:
        k3 = k2 + j3;
        i2++;
        j2 &= -1 ^ 1 << k1;
        k2 = k3;
          goto _L5
_L4:
        l3 += combins(l1 - i2 - i4 - 1, j - k1 - 3);
        i4--;
        break MISSING_BLOCK_LABEL_203;
        if(l1 - i2 > i)
            j3 = i3 - 1;
        else
            j3 = i3;
          goto _L6
    }

    static int[] getRSSwidths(int i, int j, int k, int l, boolean flag)
    {
        int ai[];
        int i1;
        int j1;
        ai = new int[k];
        i1 = 0;
        j1 = 0;
_L3:
        int l1;
        int i2;
        if(j1 >= k - 1)
        {
            ai[j1] = j;
            return ai;
        }
        int k1 = i1 | 1 << j1;
        l1 = 1;
        i2 = k1;
_L6:
        int j2 = combins(j - l1 - 1, k - j1 - 2);
        int k2;
        int l2;
        int i3;
        int j3;
        if(flag && i2 == 0 && j - l1 - (k - j1 - 1) >= k - j1 - 1)
            k2 = j2 - combins(j - l1 - (k - j1), k - j1 - 2);
        else
            k2 = j2;
        if(k - j1 - 1 <= 1)
            break MISSING_BLOCK_LABEL_225;
        i3 = 0;
        j3 = j - l1 - (k - j1 - 2);
_L4:
        if(j3 > l) goto _L2; else goto _L1
_L1:
        l2 = k2 - i3 * (k - 1 - j1);
_L5:
        i -= l2;
        if(i >= 0)
            break MISSING_BLOCK_LABEL_242;
        i += l2;
        j -= l1;
        ai[j1] = l1;
        j1++;
        i1 = i2;
          goto _L3
_L2:
        i3 += combins(j - l1 - j3 - 1, k - j1 - 3);
        j3--;
          goto _L4
        if(j - l1 > l)
            l2 = k2 - 1;
        else
            l2 = k2;
          goto _L5
        l1++;
        i2 &= -1 ^ 1 << j1;
          goto _L6
    }
}
