// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;

import com.thoughtworks.xstream.core.util.FastStack;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.thoughtworks.xstream.io:
//            WriterWrapper, StreamException, HierarchicalStreamWriter

public class StatefulWriter extends WriterWrapper
{

    public StatefulWriter(HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        super(hierarchicalstreamwriter);
        state = STATE_OPEN;
        attributes = new FastStack(16);
    }

    private void checkClosed()
    {
        if(state == STATE_CLOSED)
            throw new StreamException(new IOException("Writing on a closed stream"));
        else
            return;
    }

    private Object readResolve()
    {
        attributes = new FastStack(16);
        return this;
    }

    private void startNodeCommon()
    {
        checkClosed();
        if(state == STATE_VALUE)
        {
            throw new StreamException(new IllegalStateException("Opening node after writing text"));
        } else
        {
            state = STATE_NODE_START;
            balance = 1 + balance;
            attributes.push(new HashSet());
            return;
        }
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        checkClosed();
        if(state != STATE_NODE_START)
            throw new StreamException(new IllegalStateException("Writing attribute '" + s + "' without an opened node"));
        Set set = (Set)attributes.peek();
        if(set.contains(s))
        {
            throw new StreamException(new IllegalStateException("Writing attribute '" + s + "' twice"));
        } else
        {
            set.add(s);
            super.addAttribute(s, s1);
            return;
        }
    }

    @Override
	public void close()
    {
        if(state != STATE_NODE_END)
            if(state == STATE_OPEN);
        state = STATE_CLOSED;
        super.close();
    }

    @Override
	public void endNode()
    {
        checkClosed();
        int i = balance;
        balance = i - 1;
        if(i == 0)
        {
            throw new StreamException(new IllegalStateException("Unbalanced node"));
        } else
        {
            attributes.popSilently();
            state = STATE_NODE_END;
            super.endNode();
            return;
        }
    }

    @Override
	public void flush()
    {
        checkClosed();
        super.flush();
    }

    @Override
	public void setValue(String s)
    {
        checkClosed();
        if(state != STATE_NODE_START)
        {
            throw new StreamException(new IllegalStateException("Writing text without an opened node"));
        } else
        {
            state = STATE_VALUE;
            super.setValue(s);
            return;
        }
    }

    @Override
	public void startNode(String s)
    {
        startNodeCommon();
        super.startNode(s);
    }

    @Override
	public void startNode(String s, Class class1)
    {
        startNodeCommon();
        super.startNode(s, class1);
    }

    public int state()
    {
        return state;
    }

    public static int STATE_CLOSED = 4;
    public static int STATE_NODE_END = 3;
    public static int STATE_NODE_START = 1;
    public static int STATE_OPEN = 0;
    public static int STATE_VALUE = 2;
    private transient FastStack attributes;
    private transient int balance;
    private transient int state;

}
