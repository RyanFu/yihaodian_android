// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResultType

public abstract class ParsedResult
{

    protected ParsedResult(ParsedResultType parsedresulttype)
    {
        type = parsedresulttype;
    }

    public static void maybeAppend(String s, StringBuffer stringbuffer)
    {
        if(s != null && s.length() > 0)
        {
            if(stringbuffer.length() > 0)
                stringbuffer.append('\n');
            stringbuffer.append(s);
        }
    }

    public static void maybeAppend(String as[], StringBuffer stringbuffer)
    {
        if(as == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i < as.length) goto _L3; else goto _L2
_L2:
        return;
_L3:
        if(as[i] != null && as[i].length() > 0)
        {
            if(stringbuffer.length() > 0)
                stringbuffer.append('\n');
            stringbuffer.append(as[i]);
        }
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public abstract String getDisplayResult();

    public ParsedResultType getType()
    {
        return type;
    }

    @Override
	public String toString()
    {
        return getDisplayResult();
    }

    private final ParsedResultType type;
}
