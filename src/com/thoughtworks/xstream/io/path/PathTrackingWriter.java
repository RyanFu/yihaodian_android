// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.path;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.WriterWrapper;
import com.thoughtworks.xstream.io.xml.XmlFriendlyWriter;

// Referenced classes of package com.thoughtworks.xstream.io.path:
//            PathTracker

public class PathTrackingWriter extends WriterWrapper
{

    public PathTrackingWriter(HierarchicalStreamWriter hierarchicalstreamwriter, PathTracker pathtracker)
    {
        super(hierarchicalstreamwriter);
        isXmlFriendly = hierarchicalstreamwriter.underlyingWriter() instanceof XmlFriendlyWriter;
        pathTracker = pathtracker;
    }

    @Override
	public void endNode()
    {
        super.endNode();
        pathTracker.popElement();
    }

    @Override
	public void startNode(String s)
    {
        PathTracker pathtracker = pathTracker;
        String s1;
        if(isXmlFriendly)
            s1 = ((XmlFriendlyWriter)wrapped.underlyingWriter()).escapeXmlName(s);
        else
            s1 = s;
        pathtracker.pushElement(s1);
        super.startNode(s);
    }

    @Override
	public void startNode(String s, Class class1)
    {
        PathTracker pathtracker = pathTracker;
        String s1;
        if(isXmlFriendly)
            s1 = ((XmlFriendlyWriter)wrapped.underlyingWriter()).escapeXmlName(s);
        else
            s1 = s;
        pathtracker.pushElement(s1);
        super.startNode(s, class1);
    }

    private final boolean isXmlFriendly;
    private final PathTracker pathTracker;
}
