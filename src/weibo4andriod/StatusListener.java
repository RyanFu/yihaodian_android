// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;


// Referenced classes of package weibo4andriod:
//            Status

public interface StatusListener
{

    public abstract void onException(Exception exception);

    public abstract void onStatus(Status status);
}
