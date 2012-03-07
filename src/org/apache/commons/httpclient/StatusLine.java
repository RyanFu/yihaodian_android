// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            HttpException, ProtocolException

public class StatusLine
{

    public StatusLine(String s)
        throws HttpException
    {
        int i;
        int j;
        int k;
        i = s.length();
        j = 0;
        k = 0;
_L4:
        if(Character.isWhitespace(s.charAt(k))) goto _L2; else goto _L1
_L1:
        int i1 = k + 4;
        if(!"HTTP".equals(s.substring(k, i1)))
            throw new HttpException("Status-Line '" + s + "' does not start with HTTP");
          goto _L3
        StringIndexOutOfBoundsException stringindexoutofboundsexception1;
        stringindexoutofboundsexception1;
_L9:
        throw new HttpException("Status-Line '" + s + "' is not valid");
_L2:
        int l = k + 1;
        j++;
        k = l;
          goto _L4
_L3:
        int j1;
        j1 = s.indexOf(" ", i1);
        if(j1 <= 0)
            throw new ProtocolException("Unable to parse HTTP-Version from the status line: '" + s + "'");
        httpVersion = s.substring(j, j1).toUpperCase();
_L7:
        if(s.charAt(j1) == ' ') goto _L6; else goto _L5
_L5:
        int k1 = s.indexOf(" ", j1);
        int l1;
        l1 = k1;
        if(l1 < 0)
            l1 = i;
        statusCode = Integer.parseInt(s.substring(j1, l1));
        int i2;
        i2 = l1 + 1;
        if(i2 >= i)
            break MISSING_BLOCK_LABEL_310;
        reasonPhrase = s.substring(i2).trim();
_L8:
        statusLine = s;
        return;
_L6:
        j1++;
          goto _L7
        NumberFormatException numberformatexception;
        numberformatexception;
        throw new ProtocolException("Unable to parse status code from status line: '" + s + "'");
          goto _L8
        StringIndexOutOfBoundsException stringindexoutofboundsexception;
        stringindexoutofboundsexception;
        k;
          goto _L9
    }

    public static boolean startsWithHTTP(String s)
    {
        int i = 0;
_L3:
        if(Character.isWhitespace(s.charAt(i))) goto _L2; else goto _L1
_L1:
        boolean flag1 = "HTTP".equals(s.substring(i, i + 4));
        boolean flag = flag1;
_L4:
        return flag;
_L2:
        i++;
          goto _L3
        StringIndexOutOfBoundsException stringindexoutofboundsexception;
        stringindexoutofboundsexception;
        flag = false;
          goto _L4
    }

    public final String getHttpVersion()
    {
        return httpVersion;
    }

    public final String getReasonPhrase()
    {
        return reasonPhrase;
    }

    public final int getStatusCode()
    {
        return statusCode;
    }

    @Override
	public final String toString()
    {
        return statusLine;
    }

    private final String httpVersion;
    private final String reasonPhrase = "";
    private final int statusCode;
    private final String statusLine;
}
