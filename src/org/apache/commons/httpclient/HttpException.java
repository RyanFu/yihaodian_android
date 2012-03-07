// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import java.lang.reflect.Method;

public class HttpException extends IOException
{

    public HttpException()
    {
        reasonCode = 200;
        cause = null;
    }

    public HttpException(String s)
    {
        super(s);
        reasonCode = 200;
        cause = null;
    }

    public HttpException(String s, Throwable throwable)
    {
        super(s);
        reasonCode = 200;
        cause = throwable;
        try
        {
            Class aclass[] = new Class[1];
            Class class1;
            Class class2;
            Method method;
            Object aobj[];
            if(class$java$lang$Throwable == null)
            {
                class1 = _mthclass$("java.lang.Throwable");
                class$java$lang$Throwable = class1;
            } else
            {
                class1 = class$java$lang$Throwable;
            }
            aclass[0] = class1;
            if(class$java$lang$Throwable == null)
            {
                class2 = _mthclass$("java.lang.Throwable");
                class$java$lang$Throwable = class2;
            } else
            {
                class2 = class$java$lang$Throwable;
            }
            method = class2.getMethod("initCause", aclass);
            aobj = new Object[1];
            aobj[0] = throwable;
            method.invoke(this, aobj);
        }
        catch(Exception exception) { }
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    @Override
	public Throwable getCause()
    {
        return cause;
    }

    public String getReason()
    {
        return reason;
    }

    public int getReasonCode()
    {
        return reasonCode;
    }

    @Override
	public void printStackTrace()
    {
        printStackTrace(System.err);
    }

    @Override
	public void printStackTrace(PrintStream printstream)
    {
        Class aclass[] = new Class[0];
        getClass().getMethod("getStackTrace", aclass);
        super.printStackTrace(printstream);
_L1:
        return;
        Exception exception;
        exception;
        super.printStackTrace(printstream);
        if(cause != null)
        {
            printstream.print("Caused by: ");
            cause.printStackTrace(printstream);
        }
          goto _L1
    }

    @Override
	public void printStackTrace(PrintWriter printwriter)
    {
        Class aclass[] = new Class[0];
        getClass().getMethod("getStackTrace", aclass);
        super.printStackTrace(printwriter);
_L1:
        return;
        Exception exception;
        exception;
        super.printStackTrace(printwriter);
        if(cause != null)
        {
            printwriter.print("Caused by: ");
            cause.printStackTrace(printwriter);
        }
          goto _L1
    }

    public void setReason(String s)
    {
        reason = s;
    }

    public void setReasonCode(int i)
    {
        reasonCode = i;
    }

    static Class class$java$lang$Throwable;
    private final Throwable cause;
    private String reason;
    private int reasonCode;
}
