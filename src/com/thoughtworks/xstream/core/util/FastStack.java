// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;


public final class FastStack
{

    public FastStack(int i)
    {
        stack = new Object[i];
    }

    private void resizeStack(int i)
    {
        Object aobj[] = new Object[i];
        System.arraycopy(((stack)), 0, ((aobj)), 0, Math.min(pointer, i));
        stack = aobj;
    }

    public Object get(int i)
    {
        return stack[i];
    }

    public boolean hasStuff()
    {
        boolean flag;
        if(pointer > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Object peek()
    {
        Object obj;
        if(pointer == 0)
            obj = null;
        else
            obj = stack[pointer - 1];
        return obj;
    }

    public Object pop()
    {
        Object aobj[] = stack;
        int i = pointer - 1;
        pointer = i;
        Object obj = aobj[i];
        stack[pointer] = null;
        return obj;
    }

    public void popSilently()
    {
        Object aobj[] = stack;
        int i = pointer - 1;
        pointer = i;
        aobj[i] = null;
    }

    public Object push(Object obj)
    {
        if(1 + pointer >= stack.length)
            resizeStack(2 * stack.length);
        Object aobj[] = stack;
        int i = pointer;
        pointer = i + 1;
        aobj[i] = obj;
        return obj;
    }

    public int size()
    {
        return pointer;
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer("[");
        for(int i = 0; i < pointer; i++)
        {
            if(i > 0)
                stringbuffer.append(", ");
            stringbuffer.append(stack[i]);
        }

        stringbuffer.append(']');
        return stringbuffer.toString();
    }

    private int pointer;
    private Object stack[];
}
