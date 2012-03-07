// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BareBonesBrowserLaunch
{

    public BareBonesBrowserLaunch()
    {
    }

    private static void browse(String s)
        throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InterruptedException, InvocationTargetException, IOException, NoSuchMethodException
    {
        String s1 = System.getProperty("os.name", "");
        if(s1.startsWith("Mac OS"))
        {
            Class class1 = Class.forName("com.apple.eio.FileManager");
            Class aclass[] = new Class[1];
            aclass[0] = java/lang/String;
            Method method = class1.getDeclaredMethod("openURL", aclass);
            Object aobj[] = new Object[1];
            aobj[0] = s;
            method.invoke(null, aobj);
        } else
        if(s1.startsWith("Windows"))
        {
            Runtime.getRuntime().exec((new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(s).toString());
        } else
        {
            String as[] = new String[6];
            as[0] = "firefox";
            as[1] = "opera";
            as[2] = "konqueror";
            as[3] = "epiphany";
            as[4] = "mozilla";
            as[5] = "netscape";
            int i = 0;
            String s2;
            for(s2 = null; i < as.length && s2 == null; i++)
            {
                Runtime runtime1 = Runtime.getRuntime();
                String as2[] = new String[2];
                as2[0] = "which";
                as2[1] = as[i];
                if(runtime1.exec(as2).waitFor() == 0)
                    s2 = as[i];
            }

            if(s2 == null)
                throw new NoSuchMethodException("Could not find web browser");
            Runtime runtime = Runtime.getRuntime();
            String as1[] = new String[2];
            as1[0] = s2;
            as1[1] = s;
            runtime.exec(as1);
        }
    }

    public static void openURL(String s)
    {
        browse(s);
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
