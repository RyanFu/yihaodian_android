// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.io.InterruptedIOException;
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;

public class ExceptionUtil
{

    public ExceptionUtil()
    {
    }

    private static Class SocketTimeoutExceptionClass()
    {
        Class class2 = Class.forName("java.net.SocketTimeoutException");
        Class class1 = class2;
_L2:
        return class1;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        class1 = null;
        if(true) goto _L2; else goto _L1
_L1:
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

    private static Method getInitCauseMethod()
    {
        Method method;
        try
        {
            Class aclass[] = new Class[1];
            Class class1;
            Class class2;
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
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            method = null;
        }
        return method;
    }

    public static void initCause(Throwable throwable, Throwable throwable1)
    {
        if(INIT_CAUSE_METHOD == null)
            break MISSING_BLOCK_LABEL_29;
        Method method = INIT_CAUSE_METHOD;
        Object aobj[] = new Object[1];
        aobj[0] = throwable1;
        method.invoke(throwable, aobj);
_L1:
        return;
        Exception exception;
        exception;
        LOG.warn("Exception invoking Throwable.initCause", exception);
          goto _L1
    }

    public static boolean isSocketTimeoutException(InterruptedIOException interruptedioexception)
    {
        boolean flag;
        if(SOCKET_TIMEOUT_CLASS != null)
            flag = SOCKET_TIMEOUT_CLASS.isInstance(interruptedioexception);
        else
            flag = true;
        return flag;
    }

    private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();
    private static final Log LOG;
    private static final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
    static Class class$java$lang$Throwable;
    static Class class$org$apache$commons$httpclient$util$ExceptionUtil;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$util$ExceptionUtil == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.util.ExceptionUtil");
            class$org$apache$commons$httpclient$util$ExceptionUtil = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$util$ExceptionUtil;
        }
        LOG = LogFactory.getLog(class1);
    }
}
