// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.converters.ErrorWriter;
import com.thoughtworks.xstream.core.util.FastStack;
import com.thoughtworks.xstream.io.AttributeNameIterator;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            AbstractXmlReader, DocumentReader, XmlFriendlyReplacer

public abstract class AbstractDocumentReader extends AbstractXmlReader
    implements DocumentReader
{
    private static class Pointer
    {

        public int v;

        private Pointer()
        {
        }

    }


    protected AbstractDocumentReader(Object obj)
    {
        this(obj, new XmlFriendlyReplacer());
    }

    protected AbstractDocumentReader(Object obj, XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        super(xmlfriendlyreplacer);
        pointers = new FastStack(16);
        current = obj;
        pointers.push(new Pointer());
        reassignCurrentElement(current);
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
    }

    @Override
	public void close()
    {
    }

    @Override
	public Iterator getAttributeNames()
    {
        return new AttributeNameIterator(this);
    }

    protected abstract Object getChild(int i);

    protected abstract int getChildCount();

    @Override
	public Object getCurrent()
    {
        return current;
    }

    protected abstract Object getParent();

    @Override
	public boolean hasMoreChildren()
    {
        boolean flag;
        if(((Pointer)pointers.peek()).v < getChildCount())
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void moveDown()
    {
        Pointer pointer = (Pointer)pointers.peek();
        pointers.push(new Pointer());
        current = getChild(pointer.v);
        pointer.v = 1 + pointer.v;
        reassignCurrentElement(current);
    }

    @Override
	public void moveUp()
    {
        current = getParent();
        pointers.popSilently();
        reassignCurrentElement(current);
    }

    public Object peekUnderlyingNode()
    {
        return current;
    }

    protected abstract void reassignCurrentElement(Object obj);

    @Override
	public HierarchicalStreamReader underlyingReader()
    {
        return this;
    }

    private Object current;
    private FastStack pointers;
}
