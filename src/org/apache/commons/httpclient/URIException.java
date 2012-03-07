// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;


// Referenced classes of package org.apache.commons.httpclient:
//            HttpException

public class URIException extends HttpException
{

    public URIException()
    {
    }

    public URIException(int i)
    {
        reasonCode = i;
    }

    public URIException(int i, String s)
    {
        super(s);
        reason = s;
        reasonCode = i;
    }

    public URIException(String s)
    {
        super(s);
        reason = s;
        reasonCode = 0;
    }

    @Override
	public String getReason()
    {
        return reason;
    }

    @Override
	public int getReasonCode()
    {
        return reasonCode;
    }

    @Override
	public void setReason(String s)
    {
        reason = s;
    }

    @Override
	public void setReasonCode(int i)
    {
        reasonCode = i;
    }

    public static final int ESCAPING = 3;
    public static final int PARSING = 1;
    public static final int PUNYCODE = 4;
    public static final int UNKNOWN = 0;
    public static final int UNSUPPORTED_ENCODING = 2;
    protected String reason;
    protected int reasonCode;
}
