// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.core.DefaultConverterLookup;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public interface MarshallingStrategy
{

    public abstract void marshal(HierarchicalStreamWriter hierarchicalstreamwriter, Object obj, ConverterLookup converterlookup, Mapper mapper, DataHolder dataholder);

    public abstract void marshal(HierarchicalStreamWriter hierarchicalstreamwriter, Object obj, DefaultConverterLookup defaultconverterlookup, ClassMapper classmapper, DataHolder dataholder);

    public abstract Object unmarshal(Object obj, HierarchicalStreamReader hierarchicalstreamreader, DataHolder dataholder, ConverterLookup converterlookup, Mapper mapper);

    public abstract Object unmarshal(Object obj, HierarchicalStreamReader hierarchicalstreamreader, DataHolder dataholder, DefaultConverterLookup defaultconverterlookup, ClassMapper classmapper);
}
