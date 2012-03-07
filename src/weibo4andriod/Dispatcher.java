// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.LinkedList;
import java.util.List;

// Referenced classes of package weibo4andriod:
//            ExecuteThread

class Dispatcher
{

    public Dispatcher(String s)
    {
        this(s, 1);
    }

    public Dispatcher(String s, int i)
    {
        q = new LinkedList();
        ticket = new Object();
        active = true;
        threads = new ExecuteThread[i];
        for(int j = 0; j < threads.length; j++)
        {
            threads[j] = new ExecuteThread(s, this, j);
            threads[j].setDaemon(true);
            threads[j].start();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
			public void run()
            {
                if(active)
                    shutdown();
            }

            final Dispatcher this$0;

            
            {
                this$0 = Dispatcher.this;
                super();
            }
        }
);
    }

    /**
     * @deprecated Method invokeLater is deprecated
     */

    @Deprecated
	public void invokeLater(Runnable runnable)
    {
        this;
        JVM INSTR monitorenter ;
        synchronized(q)
        {
            q.add(runnable);
        }
        synchronized(ticket)
        {
            ticket.notify();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        list;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception2;
        obj;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public Runnable poll()
    {
_L2:
        Runnable runnable;
        if(!active)
            break; /* Loop/switch isn't completed */
        synchronized(q)
        {
            if(q.size() > 0)
            {
                runnable = (Runnable)q.remove(0);
                if(runnable != null)
                    break MISSING_BLOCK_LABEL_96;
            }
        }
        Object obj = ticket;
        obj;
        JVM INSTR monitorenter ;
        Exception exception1;
        try
        {
            ticket.wait();
        }
        catch(InterruptedException interruptedexception) { }
        obj;
        JVM INSTR monitorexit ;
        if(true) goto _L2; else goto _L1
        exception1;
        throw exception1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        runnable = null;
        return runnable;
    }

    /**
     * @deprecated Method shutdown is deprecated
     */

    @Deprecated
	public void shutdown()
    {
        int i = 0;
        this;
        JVM INSTR monitorenter ;
        if(!active)
            break MISSING_BLOCK_LABEL_78;
        active = false;
        ExecuteThread aexecutethread[] = threads;
        for(int j = aexecutethread.length; i < j; i++)
            aexecutethread[i].shutdown();

        synchronized(ticket)
        {
            ticket.notify();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        throw new IllegalStateException("Already shutdown");
    }

    private boolean active;
    private List q;
    private ExecuteThread threads[];
    Object ticket;

}
