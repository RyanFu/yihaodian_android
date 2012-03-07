// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import java.io.InputStream;
import java.io.OutputStream;

public class Utils
{

    public Utils()
    {
    }

    public static void CopyStream(InputStream inputstream, OutputStream outputstream)
    {
        try
        {
            byte abyte0[] = new byte[1024];
            do
            {
                int i = inputstream.read(abyte0, 0, 1024);
                if(i == -1)
                    break;
                outputstream.write(abyte0, 0, i);
            } while(true);
        }
        catch(Exception exception) { }
    }
}
