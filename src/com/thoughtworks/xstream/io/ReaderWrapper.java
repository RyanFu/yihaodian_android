// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;

import com.thoughtworks.xstream.converters.ErrorWriter;
import java.util.Iterator;

// Referenced classes of package com.thoughtworks.xstream.io:
//            HierarchicalStreamReader

public abstract class ReaderWrapper
    implements HierarchicalStreamReader
{

    protected ReaderWrapper(HierarchicalStreamReader hierarchicalstreamreader)
    {
        wrapped = hierarchicalstreamreader;
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
        wrapped.appendErrors(errorwriter);
    }

    @Override
	public void close()
    {
        wrapped.close();
    }

    @Override
	public String getAttribute(int i)
    {
        return wrapped.getAttribute(i);
    }

    @Override
	public String getAttribute(String s)
    {
        return wrapped.getAttribute(s);
    }

    @Override
	public int getAttributeCount()
    {
        return wrapped.getAttributeCount();
    }

    @Override
	public String getAttributeName(int i)
    {
        return wrapped.getAttributeName(i);
    }

    @Override
	public Iterator getAttributeNames()
    {
        return wrapped.getAttributeNames();
    }

    @Override
	public String getNodeName()
    {
        return wrapped.getNodeName();
    }

    @Override
	public String getValue()
    {
        return wrapped.getValue();
    }

    @Override
	public boolean hasMoreChildren()
    {
        return wrapped.hasMoreChildren();
    }

    @Override
	public void moveDown()
    {
        wrapped.moveDown();
    }

    @Override
	public void moveUp()
    {
        wrapped.moveUp();
    }

    @Override
	public HierarchicalStreamReader underlyingReader()
    {
        return wrapped.underlyingReader();
    }

    protected HierarchicalStreamReader wrapped;
}
