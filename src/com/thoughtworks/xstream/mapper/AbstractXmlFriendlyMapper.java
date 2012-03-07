// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;


// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class AbstractXmlFriendlyMapper extends MapperWrapper
{

    protected AbstractXmlFriendlyMapper(Mapper mapper)
    {
        super(mapper);
        dollarReplacementInClass = '-';
        dollarReplacementInField = "_DOLLAR_";
        underscoreReplacementInField = "__";
        noPackagePrefix = "default";
    }

    private boolean stringFoundAt(String s, int i, String s1)
    {
        boolean flag;
        if(s.length() >= i + s1.length() && s.substring(i, i + s1.length()).equals(s1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected String escapeClassName(String s)
    {
        String s1 = s.replace('$', dollarReplacementInClass);
        if(s1.charAt(0) == dollarReplacementInClass)
            s1 = noPackagePrefix + s1;
        return s1;
    }

    protected String escapeFieldName(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = s.length();
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            if(c == '$')
                stringbuffer.append(dollarReplacementInField);
            else
            if(c == '_')
                stringbuffer.append(underscoreReplacementInField);
            else
                stringbuffer.append(c);
            j++;
        }
        return stringbuffer.toString();
    }

    protected String unescapeClassName(String s)
    {
        if(s.startsWith(noPackagePrefix + dollarReplacementInClass))
            s = s.substring(noPackagePrefix.length());
        return s.replace(dollarReplacementInClass, '$');
    }

    protected String unescapeFieldName(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = s.length();
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            if(stringFoundAt(s, j, underscoreReplacementInField))
            {
                j += underscoreReplacementInField.length() - 1;
                stringbuffer.append('_');
            } else
            if(stringFoundAt(s, j, dollarReplacementInField))
            {
                j += dollarReplacementInField.length() - 1;
                stringbuffer.append('$');
            } else
            {
                stringbuffer.append(c);
            }
            j++;
        }
        return stringbuffer.toString();
    }

    private char dollarReplacementInClass;
    private String dollarReplacementInField;
    private String noPackagePrefix;
    private String underscoreReplacementInField;
}
