// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.path.*;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReader;
import com.thoughtworks.xstream.mapper.Mapper;

// Referenced classes of package com.thoughtworks.xstream.core:
//            AbstractReferenceUnmarshaller

public class ReferenceByXPathUnmarshaller extends AbstractReferenceUnmarshaller
{

    public ReferenceByXPathUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, ClassMapper classmapper)
    {
        this(obj, hierarchicalstreamreader, converterlookup, ((Mapper) (classmapper)));
    }

    public ReferenceByXPathUnmarshaller(Object obj, HierarchicalStreamReader hierarchicalstreamreader, ConverterLookup converterlookup, Mapper mapper)
    {
        super(obj, hierarchicalstreamreader, converterlookup, mapper);
        pathTracker = new PathTracker();
        reader = new PathTrackingReader(hierarchicalstreamreader, pathTracker);
        isXmlFriendly = hierarchicalstreamreader.underlyingReader() instanceof XmlFriendlyReader;
    }

    @Override
	protected Object getCurrentReferenceKey()
    {
        return pathTracker.getPath();
    }

    @Override
	protected Object getReferenceKey(String s)
    {
        String s1;
        Path path;
        Path path1;
        if(isXmlFriendly)
            s1 = ((XmlFriendlyReader)reader.underlyingReader()).unescapeXmlName(s);
        else
            s1 = s;
        path = new Path(s1);
        if(s.charAt(0) != '/')
            path1 = pathTracker.getPath().apply(path);
        else
            path1 = path;
        return path1;
    }

    protected boolean isXmlFriendly;
    private PathTracker pathTracker;
}
