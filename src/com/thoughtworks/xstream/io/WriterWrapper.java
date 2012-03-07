// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;


// Referenced classes of package com.thoughtworks.xstream.io:
//            ExtendedHierarchicalStreamWriter, HierarchicalStreamWriter

public abstract class WriterWrapper
    implements ExtendedHierarchicalStreamWriter
{

    protected WriterWrapper(HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        wrapped = hierarchicalstreamwriter;
    }

    @Override
	public void addAttribute(String s, String s1)
    {
        wrapped.addAttribute(s, s1);
    }

    @Override
	public void close()
    {
        wrapped.close();
    }

    @Override
	public void endNode()
    {
        wrapped.endNode();
    }

    @Override
	public void flush()
    {
        wrapped.flush();
    }

    @Override
	public void setValue(String s)
    {
        wrapped.setValue(s);
    }

    @Override
	public void startNode(String s)
    {
        wrapped.startNode(s);
    }

    @Override
	public void startNode(String s, Class class1)
    {
        ((ExtendedHierarchicalStreamWriter)wrapped).startNode(s, class1);
    }

    @Override
	public HierarchicalStreamWriter underlyingWriter()
    {
        return wrapped.underlyingWriter();
    }

    protected HierarchicalStreamWriter wrapped;
}
