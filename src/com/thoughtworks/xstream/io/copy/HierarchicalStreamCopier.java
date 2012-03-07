// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.copy;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class HierarchicalStreamCopier
{

    public HierarchicalStreamCopier()
    {
    }

    public void copy(HierarchicalStreamReader hierarchicalstreamreader, HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        hierarchicalstreamwriter.startNode(hierarchicalstreamreader.getNodeName());
        int i = hierarchicalstreamreader.getAttributeCount();
        for(int j = 0; j < i; j++)
            hierarchicalstreamwriter.addAttribute(hierarchicalstreamreader.getAttributeName(j), hierarchicalstreamreader.getAttribute(j));

        String s = hierarchicalstreamreader.getValue();
        if(s != null && s.length() > 0)
            hierarchicalstreamwriter.setValue(s);
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            copy(hierarchicalstreamreader, hierarchicalstreamwriter);
        }

        hierarchicalstreamwriter.endNode();
    }
}
