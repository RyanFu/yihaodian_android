// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;


public final class TimeoutController
{
    public static class TimeoutException extends Exception
    {

        public TimeoutException()
        {
        }
    }


    private TimeoutController()
    {
    }

    public static void execute(Runnable runnable, long l)
        throws TimeoutException
    {
        Thread thread = new Thread(runnable, "Timeout guard");
        thread.setDaemon(true);
        execute(thread, l);
    }

    public static void execute(Thread thread, long l)
        throws TimeoutException
    {
        thread.start();
        try
        {
            thread.join(l);
        }
        catch(InterruptedException interruptedexception) { }
        if(thread.isAlive())
        {
            thread.interrupt();
            throw new TimeoutException();
        } else
        {
            return;
        }
    }
}
