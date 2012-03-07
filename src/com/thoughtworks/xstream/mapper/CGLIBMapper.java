// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class CGLIBMapper extends MapperWrapper
{
    public static interface Marker
    {
    }


    public CGLIBMapper(Mapper mapper)
    {
        this(mapper, "CGLIB-enhanced-proxy");
    }

    public CGLIBMapper(Mapper mapper, String s)
    {
        super(mapper);
        alias = s;
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
	public Class realClass(String s)
    {
        Class class1;
        if(s.equals(alias))
        {
            if(class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker == null)
            {
                class1 = _mthclass$("com.thoughtworks.xstream.mapper.CGLIBMapper$Marker");
                class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker = class1;
            } else
            {
                class1 = class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker;
            }
        } else
        {
            class1 = super.realClass(s);
        }
        return class1;
    }

    @Override
	public String serializedClass(Class class1)
    {
        String s = super.serializedClass(class1);
        String s2;
        if(class1 == null)
        {
            s2 = s;
        } else
        {
            String s1 = class1.getName();
            if(s1.equals(s) && s1.indexOf(DEFAULT_NAMING_MARKER) > 0 && Enhancer.isEnhanced(class1))
                s2 = alias;
            else
                s2 = s;
        }
        return s2;
    }

    private static String DEFAULT_NAMING_MARKER = "$$EnhancerByCGLIB$$";
    static Class class$com$thoughtworks$xstream$mapper$CGLIBMapper$Marker;
    private final String alias;

}
