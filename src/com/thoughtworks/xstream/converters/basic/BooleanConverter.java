// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;


// Referenced classes of package com.thoughtworks.xstream.converters.basic:
//            AbstractSingleValueConverter

public class BooleanConverter extends AbstractSingleValueConverter
{

    public BooleanConverter()
    {
        this("true", "false", false);
    }

    public BooleanConverter(String s, String s1, boolean flag)
    {
        positive = s;
        negative = s1;
        caseSensitive = flag;
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
        if(class1.equals(Boolean.TYPE)) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class2;
        if(class$java$lang$Boolean == null)
        {
            class2 = _mthclass$("java.lang.Boolean");
            class$java$lang$Boolean = class2;
        } else
        {
            class2 = class$java$lang$Boolean;
        }
        if(!class1.equals(class2)) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public Object fromString(String s)
    {
        Boolean boolean1;
        if(caseSensitive)
        {
            if(positive.equals(s))
                boolean1 = Boolean.TRUE;
            else
                boolean1 = Boolean.FALSE;
        } else
        if(positive.equalsIgnoreCase(s))
            boolean1 = Boolean.TRUE;
        else
            boolean1 = Boolean.FALSE;
        return boolean1;
    }

    public boolean shouldConvert(Class class1, Object obj)
    {
        return true;
    }

    @Override
	public String toString(Object obj)
    {
        Boolean boolean1 = (Boolean)obj;
        String s;
        if(obj == null)
            s = null;
        else
        if(boolean1.booleanValue())
            s = positive;
        else
            s = negative;
        return s;
    }

    public static final BooleanConverter BINARY = new BooleanConverter("1", "0", true);
    public static final BooleanConverter TRUE_FALSE = new BooleanConverter("true", "false", false);
    public static final BooleanConverter YES_NO = new BooleanConverter("yes", "no", false);
    static Class class$java$lang$Boolean;
    private final boolean caseSensitive;
    private final String negative;
    private final String positive;

}
