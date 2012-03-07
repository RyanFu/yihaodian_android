// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class XmlFriendlyReplacer
{

    public XmlFriendlyReplacer()
    {
        this("_-", "__");
    }

    public XmlFriendlyReplacer(String s, String s1)
    {
        dollarReplacement = s;
        underscoreReplacement = s1;
        escapeCache = new WeakHashMap();
        unescapeCache = new WeakHashMap();
    }

    private Object readResolve()
    {
        escapeCache = new WeakHashMap();
        unescapeCache = new WeakHashMap();
        return this;
    }

    public String escapeName(String s)
    {
        String s1;
        String s2;
        int i;
        int j;
        WeakReference weakreference = (WeakReference)escapeCache.get(s);
        Object obj;
        char c1;
        if(weakreference == null)
            obj = null;
        else
            obj = weakreference.get();
        s1 = (String)obj;
        if(s1 != null)
            break MISSING_BLOCK_LABEL_224;
        i = s.length();
        j = 0;
_L6:
        if(j >= i) goto _L2; else goto _L1
_L1:
        c1 = s.charAt(j);
        if(c1 != '$' && c1 != '_') goto _L3; else goto _L2
_L2:
        if(j != i) goto _L5; else goto _L4
_L4:
        s2 = s;
_L7:
        return s2;
_L3:
        j++;
          goto _L6
_L5:
        StringBuffer stringbuffer = new StringBuffer(i + 8);
        if(j > 0)
            stringbuffer.append(s.substring(0, j));
        while(j < i) 
        {
            char c = s.charAt(j);
            if(c == '$')
                stringbuffer.append(dollarReplacement);
            else
            if(c == '_')
                stringbuffer.append(underscoreReplacement);
            else
                stringbuffer.append(c);
            j++;
        }
        s1 = stringbuffer.toString();
        escapeCache.put(s, new WeakReference(s1));
        s2 = s1;
          goto _L7
    }

    public String unescapeName(String s)
    {
        String s1;
        String s2;
        char c;
        char c1;
        int i;
        int j;
        WeakReference weakreference = (WeakReference)unescapeCache.get(s);
        Object obj;
        char c3;
        if(weakreference == null)
            obj = null;
        else
            obj = weakreference.get();
        s1 = (String)obj;
        if(s1 != null)
            break MISSING_BLOCK_LABEL_294;
        c = dollarReplacement.charAt(0);
        c1 = underscoreReplacement.charAt(0);
        i = s.length();
        j = 0;
_L6:
        if(j >= i) goto _L2; else goto _L1
_L1:
        c3 = s.charAt(j);
        if(c3 != c && c3 != c1) goto _L3; else goto _L2
_L2:
        if(j != i) goto _L5; else goto _L4
_L4:
        s2 = s;
_L7:
        return s2;
_L3:
        j++;
          goto _L6
_L5:
        StringBuffer stringbuffer = new StringBuffer(i + 8);
        if(j > 0)
            stringbuffer.append(s.substring(0, j));
        while(j < i) 
        {
            char c2 = s.charAt(j);
            if(c2 == c && s.startsWith(dollarReplacement, j))
            {
                j += dollarReplacement.length() - 1;
                stringbuffer.append('$');
            } else
            if(c2 == c1 && s.startsWith(underscoreReplacement, j))
            {
                j += underscoreReplacement.length() - 1;
                stringbuffer.append('_');
            } else
            {
                stringbuffer.append(c2);
            }
            j++;
        }
        s1 = stringbuffer.toString();
        unescapeCache.put(s, new WeakReference(s1));
        s2 = s1;
          goto _L7
    }

    private String dollarReplacement;
    private transient Map escapeCache;
    private String underscoreReplacement;
    private transient Map unescapeCache;
}
