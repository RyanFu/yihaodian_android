// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;


public class Log
{

    public Log()
    {
    }

    private static String changeToString(int i)
    {
        String s;
        if(i < 10)
            s = (new StringBuilder()).append("0").append(i).toString();
        else
            s = (new StringBuilder()).append("").append(i).toString();
        return s;
    }

    public static void debug(String s, String s1)
    {
        log("DEBUG", s, s1);
    }

    public static void error(String s, String s1)
    {
        log("ERROR", s, s1);
    }

    public static void info(String s, String s1)
    {
        log("INFO", s, s1);
    }

    private static void log(String s, String s1, String s2)
    {
    }
}
