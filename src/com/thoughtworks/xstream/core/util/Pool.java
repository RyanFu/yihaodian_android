// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;


public class Pool
{
    public static interface Factory
    {

        public abstract Object newInstance();
    }


    public Pool(int i, int j, Factory factory1)
    {
        mutex = new Object();
        initialPoolSize = i;
        maxPoolSize = j;
        factory = factory1;
    }

    private Object readResolve()
    {
        mutex = new Object();
        return this;
    }

    public Object fetchFromPool()
    {
        Object obj = mutex;
        obj;
        JVM INSTR monitorenter ;
        if(pool == null)
        {
            pool = new Object[maxPoolSize];
            for(nextAvailable = initialPoolSize; nextAvailable > 0;)
                putInPool(factory.newInstance());

        }
        break MISSING_BLOCK_LABEL_61;
        Exception exception;
        exception;
        throw exception;
_L2:
        int i;
        int j;
        i = nextAvailable;
        j = maxPoolSize;
        if(i != j)
            break MISSING_BLOCK_LABEL_121;
        mutex.wait();
        if(true) goto _L2; else goto _L1
_L1:
        InterruptedException interruptedexception;
        interruptedexception;
        throw new RuntimeException("Interrupted whilst waiting for a free item in the pool : " + interruptedexception.getMessage());
        Object obj1;
        Object aobj[] = pool;
        int k = nextAvailable;
        nextAvailable = k + 1;
        obj1 = aobj[k];
        if(obj1 == null)
        {
            obj1 = factory.newInstance();
            putInPool(obj1);
            nextAvailable = 1 + nextAvailable;
        }
        obj;
        JVM INSTR monitorexit ;
        return obj1;
    }

    protected void putInPool(Object obj)
    {
        Object obj1 = mutex;
        obj1;
        JVM INSTR monitorenter ;
        Object aobj[] = pool;
        int i = nextAvailable - 1;
        nextAvailable = i;
        aobj[i] = obj;
        mutex.notify();
        return;
    }

    private final Factory factory;
    private final int initialPoolSize;
    private final int maxPoolSize;
    private transient Object mutex;
    private transient int nextAvailable;
    private transient Object pool[];
}
