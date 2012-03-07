// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.path;

import com.thoughtworks.xstream.converters.ErrorWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.ReaderWrapper;

// Referenced classes of package com.thoughtworks.xstream.io.path:
//            PathTracker, Path

public class PathTrackingReader extends ReaderWrapper
{

    public PathTrackingReader(HierarchicalStreamReader hierarchicalstreamreader, PathTracker pathtracker)
    {
        super(hierarchicalstreamreader);
        pathTracker = pathtracker;
        pathtracker.pushElement(getNodeName());
    }

    @Override
	public void appendErrors(ErrorWriter errorwriter)
    {
        errorwriter.add("path", pathTracker.getPath().toString());
        super.appendErrors(errorwriter);
    }

    @Override
	public void moveDown()
    {
        super.moveDown();
        pathTracker.pushElement(getNodeName());
    }

    @Override
	public void moveUp()
    {
        super.moveUp();
        pathTracker.popElement();
    }

    private final PathTracker pathTracker;
}
