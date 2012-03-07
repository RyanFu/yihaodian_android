// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.DataCharacter;
import java.util.Vector;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            ExpandedPair

final class BitArrayBuilder
{

    private BitArrayBuilder()
    {
    }

    static BitArray buildBitArray(Vector vector)
    {
        BitArray bitarray;
        int j;
        int k;
        int l;
        int i = (vector.size() << 1) - 1;
        if(((ExpandedPair)vector.lastElement()).getRightChar() == null)
            i--;
        bitarray = new BitArray(i * 12);
        j = 0;
        k = ((ExpandedPair)vector.elementAt(0)).getRightChar().getValue();
        l = 11;
_L4:
        if(l >= 0) goto _L2; else goto _L1
_L1:
        int i1 = 1;
_L9:
        ExpandedPair expandedpair;
        int j1;
        int k1;
        if(i1 >= vector.size())
            return bitarray;
        expandedpair = (ExpandedPair)vector.elementAt(i1);
        j1 = expandedpair.getLeftChar().getValue();
        k1 = 11;
          goto _L3
_L2:
        if((k & 1 << l) != 0)
            bitarray.set(j);
        j++;
        l--;
          goto _L4
_L3:
        if(k1 >= 0) goto _L6; else goto _L5
_L5:
        if(expandedpair.getRightChar() == null) goto _L8; else goto _L7
_L7:
        int l1;
        int i2;
        l1 = expandedpair.getRightChar().getValue();
        i2 = 11;
_L10:
        if(i2 >= 0)
            break MISSING_BLOCK_LABEL_189;
_L8:
        i1++;
          goto _L9
_L6:
        if((j1 & 1 << k1) != 0)
            bitarray.set(j);
        j++;
        k1--;
          goto _L3
        if((l1 & 1 << i2) != 0)
            bitarray.set(j);
        j++;
        i2--;
          goto _L10
    }
}
