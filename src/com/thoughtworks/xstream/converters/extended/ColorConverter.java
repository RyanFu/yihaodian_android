// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.*;
import java.util.HashMap;
import java.util.Map;

public class ColorConverter
    implements Converter
{

    public ColorConverter()
    {
    }

    private void write(String s, int i, HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        ExtendedHierarchicalStreamWriterHelper.startNode(hierarchicalstreamwriter, s, Integer.TYPE);
        hierarchicalstreamwriter.setValue(String.valueOf(i));
        hierarchicalstreamwriter.endNode();
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return class1.getName().equals("java.awt.Color");
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Color color = (Color)obj;
        write("red", color.getRed(), hierarchicalstreamwriter);
        write("green", color.getGreen(), hierarchicalstreamwriter);
        write("blue", color.getBlue(), hierarchicalstreamwriter);
        write("alpha", color.getAlpha(), hierarchicalstreamwriter);
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        HashMap hashmap = new HashMap();
        for(; hierarchicalstreamreader.hasMoreChildren(); hierarchicalstreamreader.moveUp())
        {
            hierarchicalstreamreader.moveDown();
            hashmap.put(hierarchicalstreamreader.getNodeName(), Integer.valueOf(hierarchicalstreamreader.getValue()));
        }

        return new Color(((Integer)hashmap.get("red")).intValue(), ((Integer)hashmap.get("green")).intValue(), ((Integer)hashmap.get("blue")).intValue(), ((Integer)hashmap.get("alpha")).intValue());
    }
}
