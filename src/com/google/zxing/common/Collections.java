// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import java.util.Vector;

// Referenced classes of package com.google.zxing.common:
//            Comparator

public final class Collections
{

    private Collections()
    {
    }

    public static void insertionSort(Vector vector, Comparator comparator)
    {
        int i;
        int j;
        i = vector.size();
        j = 1;
_L2:
        if(j >= i)
            return;
        Object obj = vector.elementAt(j);
        int k = j - 1;
        do
        {
            Object obj1;
label0:
            {
                if(k >= 0)
                {
                    obj1 = vector.elementAt(k);
                    if(comparator.compare(obj1, obj) > 0)
                        break label0;
                }
                vector.setElementAt(obj, k + 1);
                j++;
            }
            if(true)
                continue;
            vector.setElementAt(obj1, k + 1);
            k--;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }
}
