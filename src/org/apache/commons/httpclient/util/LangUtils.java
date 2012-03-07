// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;


public class LangUtils
{

    private LangUtils()
    {
    }

    public static boolean equals(Object obj, Object obj1)
    {
        boolean flag;
        if(obj == null)
        {
            if(obj1 == null)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = obj.equals(obj1);
        }
        return flag;
    }

    public static int hashCode(int i, int j)
    {
        return j + i * 37;
    }

    public static int hashCode(int i, Object obj)
    {
        int j;
        if(obj != null)
            j = obj.hashCode();
        else
            j = 0;
        return hashCode(i, j);
    }

    public static int hashCode(int i, boolean flag)
    {
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        return hashCode(i, j);
    }

    public static final int HASH_OFFSET = 37;
    public static final int HASH_SEED = 17;
}
