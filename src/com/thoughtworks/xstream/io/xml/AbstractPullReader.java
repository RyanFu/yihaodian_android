// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.FastStack;
import com.thoughtworks.xstream.io.AttributeNameIterator;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlReader, XmlFriendlyReplacer

public abstract class AbstractPullReader extends AbstractXmlReader
{
    private static class Event
    {

        int type;
        String value;

        private Event()
        {
        }

    }


    protected AbstractPullReader(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
    }

    private void move()
    {
        readEvent().type;
        JVM INSTR tableswitch 1 2: default 28
    //                   1 29
    //                   2 44;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        elementStack.push(pullElementName());
        continue; /* Loop/switch isn't completed */
_L3:
        elementStack.pop();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private Event readEvent()
    {
        Event event;
        if(marked)
        {
            if(lookback.hasStuff())
                event = (Event)lookahead.push(lookback.pop());
            else
                event = (Event)lookahead.push(readRealEvent());
        } else
        if(lookback.hasStuff())
            event = (Event)lookback.pop();
        else
            event = readRealEvent();
        return event;
    }

    private Event readRealEvent()
    {
        Event event;
        event = new Event();
        event.type = pullNextEvent();
        if(event.type != 3) goto _L2; else goto _L1
_L1:
        event.value = pullText();
_L4:
        return event;
_L2:
        if(event.type == 1)
            event.value = pullElementName();
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public Iterator getAttributeNames()
    {
        return new AttributeNameIterator(this);
    }

    @Override
	public String getNodeName()
    {
        return unescapeXmlName((String)elementStack.peek());
    }

    @Override
	public String getValue()
    {
        String s;
        StringBuffer stringbuffer;
        Event event;
        s = null;
        stringbuffer = null;
        mark();
        event = readEvent();
_L2:
        if(event.type != 3)
            break; /* Loop/switch isn't completed */
        String s2 = event.value;
        if(s2 != null && s2.length() > 0)
            if(s == null)
            {
                s = s2;
            } else
            {
                if(stringbuffer == null)
                    stringbuffer = new StringBuffer(s);
                stringbuffer.append(s2);
            }
_L4:
        event = readEvent();
        if(true) goto _L2; else goto _L1
_L1:
        if(event.type == 4) goto _L4; else goto _L3
_L3:
        reset();
        String s1;
        if(stringbuffer != null)
            s1 = stringbuffer.toString();
        else
        if(s == null)
            s1 = "";
        else
            s1 = s;
        return s1;
    }

    @Override
	public boolean hasMoreChildren()
    {
        mark();
_L1:
        readEvent().type;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 35
    //                   2 43;
           goto _L1 _L2 _L3
_L2:
        boolean flag;
        reset();
        flag = true;
_L5:
        return flag;
_L3:
        reset();
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void mark()
    {
        marked = true;
    }

    @Override
	public void moveDown()
    {
        for(int i = elementStack.size(); elementStack.size() <= i;)
        {
            move();
            if(elementStack.size() < i)
                throw new RuntimeException();
        }

    }

    @Override
	public void moveUp()
    {
        for(int i = elementStack.size(); elementStack.size() >= i; move());
    }

    protected abstract String pullElementName();

    protected abstract int pullNextEvent();

    protected abstract String pullText();

    public void reset()
    {
        for(; lookahead.hasStuff(); lookback.push(lookahead.pop()));
        marked = false;
    }

    @Override
	public HierarchicalStreamReader underlyingReader()
    {
        return this;
    }

    protected static final int COMMENT = 4;
    protected static final int END_NODE = 2;
    protected static final int OTHER = 0;
    protected static final int START_NODE = 1;
    protected static final int TEXT = 3;
    private final FastStack elementStack = new FastStack(16);
    private final FastStack lookahead = new FastStack(4);
    private final FastStack lookback = new FastStack(4);
    private boolean marked;
}
