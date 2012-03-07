// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;


public class WeiboException extends Exception
{

    public WeiboException(Exception exception)
    {
        super(exception);
        statusCode = -1;
    }

    public WeiboException(String s)
    {
        super(s);
        statusCode = -1;
    }

    public WeiboException(String s, int i)
    {
        super(s);
        statusCode = -1;
        statusCode = i;
    }

    public WeiboException(String s, Exception exception)
    {
        super(s, exception);
        statusCode = -1;
    }

    public WeiboException(String s, Exception exception, int i)
    {
        super(s, exception);
        statusCode = -1;
        statusCode = i;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    private static final long serialVersionUID = 0xdb9822202e937df9L;
    private int statusCode;
}
