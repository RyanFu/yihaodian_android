// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.core.util.FastStack;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlWriter, DocumentWriter, XmlFriendlyReplacer

public abstract class AbstractDocumentWriter extends AbstractXmlWriter
    implements DocumentWriter
{

    public AbstractDocumentWriter(Object obj, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        if(obj != null)
        {
            nodeStack.push(obj);
            result.add(obj);
        }
    }

    @Override
	public void close()
    {
    }

    protected abstract Object createNode(String s);

    @Override
	public final void endNode()
    {
        endNodeInternally();
        Object obj = nodeStack.pop();
        if(nodeStack.size() == 0)
            result.add(obj);
    }

    public void endNodeInternally()
    {
    }

    @Override
	public void flush()
    {
    }

    protected final Object getCurrent()
    {
        return nodeStack.peek();
    }

    @Override
	public List getTopLevelNodes()
    {
        return result;
    }

    @Override
	public final void startNode(String s)
    {
        Object obj = createNode(s);
        nodeStack.push(obj);
    }

    private final FastStack nodeStack = new FastStack(16);
    private final List result = new ArrayList();
}
