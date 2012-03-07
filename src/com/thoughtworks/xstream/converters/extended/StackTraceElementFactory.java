// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.ConversionException;
import java.lang.reflect.Field;

public class StackTraceElementFactory
{

    public StackTraceElementFactory()
    {
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

    private StackTraceElement create(String s, String s1, String s2, int i)
    {
        StackTraceElement stacktraceelement = (new Throwable()).getStackTrace()[0];
        setField(stacktraceelement, "declaringClass", s);
        setField(stacktraceelement, "methodName", s1);
        setField(stacktraceelement, "fileName", s2);
        setField(stacktraceelement, "lineNumber", new Integer(i));
        return stacktraceelement;
    }

    private void setField(StackTraceElement stacktraceelement, String s, Object obj)
    {
        try
        {
            Class class1;
            Field field;
            if(class$java$lang$StackTraceElement == null)
            {
                class1 = _mthclass$("java.lang.StackTraceElement");
                class$java$lang$StackTraceElement = class1;
            } else
            {
                class1 = class$java$lang$StackTraceElement;
            }
            field = class1.getDeclaredField(s);
            field.setAccessible(true);
            field.set(stacktraceelement, obj);
            return;
        }
        catch(Exception exception)
        {
            throw new ConversionException(exception);
        }
    }

    public StackTraceElement element(String s, String s1, String s2)
    {
        return create(s, s1, s2, -1);
    }

    public StackTraceElement element(String s, String s1, String s2, int i)
    {
        return create(s, s1, s2, i);
    }

    public StackTraceElement nativeMethodElement(String s, String s1)
    {
        return create(s, s1, "Native Method", -2);
    }

    public StackTraceElement unknownSourceElement(String s, String s1)
    {
        return create(s, s1, "Unknown Source", -1);
    }

    static Class class$java$lang$StackTraceElement;
}
