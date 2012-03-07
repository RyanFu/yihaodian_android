// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;


// Referenced classes of package weibo4andriod:
//            Dispatcher

class ExecuteThread extends Thread
{

    ExecuteThread(String s, Dispatcher dispatcher, int i)
    {
        super((new StringBuilder()).append(s).append("[").append(i).append("]").toString());
        alive = true;
        q = dispatcher;
    }

    @Override
	public void run()
    {
        do
        {
            if(!alive)
                break;
            Runnable runnable = q.poll();
            if(runnable != null)
                try
                {
                    runnable.run();
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
        } while(true);
    }

    public void shutdown()
    {
        alive = false;
    }

    private boolean alive;
    Dispatcher q;
}
