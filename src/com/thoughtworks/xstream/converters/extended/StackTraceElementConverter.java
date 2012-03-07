// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.thoughtworks.xstream.converters.extended:
//            StackTraceElementFactory

public class StackTraceElementConverter extends AbstractSingleValueConverter
{

    public StackTraceElementConverter()
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

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$lang$StackTraceElement == null)
        {
            class2 = _mthclass$("java.lang.StackTraceElement");
            class$java$lang$StackTraceElement = class2;
        } else
        {
            class2 = class$java$lang$StackTraceElement;
        }
        return class2.equals(class1);
    }

    @Override
	public Object fromString(String s)
    {
        Matcher matcher = PATTERN.matcher(s);
        if(matcher.matches())
        {
            String s1 = matcher.group(1);
            String s2 = matcher.group(2);
            String s3 = matcher.group(3);
            StackTraceElement stacktraceelement;
            if(s3.equals("Unknown Source"))
                stacktraceelement = FACTORY.unknownSourceElement(s1, s2);
            else
            if(s3.equals("Native Method"))
                stacktraceelement = FACTORY.nativeMethodElement(s1, s2);
            else
            if(matcher.group(4) != null)
            {
                int i = Integer.parseInt(matcher.group(5));
                stacktraceelement = FACTORY.element(s1, s2, s3, i);
            } else
            {
                stacktraceelement = FACTORY.element(s1, s2, s3);
            }
            return stacktraceelement;
        } else
        {
            throw new ConversionException("Could not parse StackTraceElement : " + s);
        }
    }

    @Override
	public String toString(Object obj)
    {
        return super.toString(obj).replaceFirst(":\\?\\?\\?", "");
    }

    private static final StackTraceElementFactory FACTORY = new StackTraceElementFactory();
    private static final Pattern PATTERN = Pattern.compile("^(.+)\\.([^\\(]+)\\(([^:]*)(:(\\d+))?\\)$");
    static Class class$java$lang$StackTraceElement;

}
