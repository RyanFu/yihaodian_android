// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;


public class JSONException extends Exception
{

    public JSONException(String s)
    {
        super(s);
    }

    public JSONException(Throwable throwable)
    {
        super(throwable.getMessage());
        cause = throwable;
    }

    @Override
	public Throwable getCause()
    {
        return cause;
    }

    private Throwable cause;
}
