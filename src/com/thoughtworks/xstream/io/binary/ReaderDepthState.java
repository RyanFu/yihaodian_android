// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.binary;

import java.util.*;

class ReaderDepthState
{
    private static class Attribute
    {

        String name;
        String value;

        private Attribute()
        {
        }

        Attribute(_cls1 _pcls1)
        {
            this();
        }
    }

    private static class State
    {

        List attributes;
        boolean hasMoreChildren;
        String name;
        State parent;
        String value;

        private State()
        {
        }

        State(_cls1 _pcls1)
        {
            this();
        }
    }


    ReaderDepthState()
    {
    }

    public void addAttribute(String s, String s1)
    {
        Attribute attribute = new Attribute(null);
        attribute.name = s;
        attribute.value = s1;
        if(current.attributes == null)
            current.attributes = new ArrayList();
        current.attributes.add(attribute);
    }

    public String getAttribute(int i)
    {
        String s;
        if(current.attributes == null)
            s = null;
        else
            s = ((Attribute)current.attributes.get(i)).value;
        return s;
    }

    public String getAttribute(String s)
    {
        if(current.attributes != null) goto _L2; else goto _L1
_L1:
        String s1 = null;
_L4:
        return s1;
_L2:
        for(Iterator iterator = current.attributes.iterator(); iterator.hasNext();)
        {
            Attribute attribute = (Attribute)iterator.next();
            if(attribute.name.equals(s))
            {
                s1 = attribute.value;
                continue; /* Loop/switch isn't completed */
            }
        }

        s1 = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getAttributeCount()
    {
        int i;
        if(current.attributes == null)
            i = 0;
        else
            i = current.attributes.size();
        return i;
    }

    public String getAttributeName(int i)
    {
        String s;
        if(current.attributes == null)
            s = null;
        else
            s = ((Attribute)current.attributes.get(i)).name;
        return s;
    }

    public Iterator getAttributeNames()
    {
        Object obj;
        if(current.attributes == null)
            obj = Collections.EMPTY_SET.iterator();
        else
            obj = new _cls1();
        return ((Iterator) (obj));
    }

    public String getName()
    {
        return current.name;
    }

    public String getValue()
    {
        String s;
        if(current.value == null)
            s = "";
        else
            s = current.value;
        return s;
    }

    public boolean hasMoreChildren()
    {
        return current.hasMoreChildren;
    }

    public void pop()
    {
        current = current.parent;
    }

    public void push()
    {
        State state = new State(null);
        state.parent = current;
        current = state;
    }

    public void setHasMoreChildren(boolean flag)
    {
        current.hasMoreChildren = flag;
    }

    public void setName(String s)
    {
        current.name = s;
    }

    public void setValue(String s)
    {
        current.value = s;
    }

    private static final String EMPTY_STRING = "";
    private State current;

    private class _cls1
        implements Iterator
    {

        @Override
		public boolean hasNext()
        {
            return attributeIterator.hasNext();
        }

        @Override
		public Object next()
        {
            return ((Attribute)attributeIterator.next()).name;
        }

        @Override
		public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private final ReaderDepthState this$0;
        private final Iterator val$attributeIterator;

        _cls1()
        {
            this$0 = ReaderDepthState.this;
            attributeIterator = iterator;
        }
    }

}
