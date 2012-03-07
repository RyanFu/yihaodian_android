// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;


// Referenced classes of package com.thoughtworks.xstream.io:
//            ExtendedHierarchicalStreamWriter, HierarchicalStreamWriter

public class ExtendedHierarchicalStreamWriterHelper
{

    public ExtendedHierarchicalStreamWriterHelper()
    {
    }

    public static void startNode(HierarchicalStreamWriter hierarchicalstreamwriter, String s, Class class1)
    {
        if(hierarchicalstreamwriter instanceof ExtendedHierarchicalStreamWriter)
            ((ExtendedHierarchicalStreamWriter)hierarchicalstreamwriter).startNode(s, class1);
        else
            hierarchicalstreamwriter.startNode(s);
    }
}
