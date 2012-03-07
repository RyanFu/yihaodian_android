// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

// Referenced classes of package com.thoughtworks.xstream.converters:
//            Converter, SingleValueConverter, MarshallingContext, UnmarshallingContext

public class SingleValueConverterWrapper
    implements Converter, SingleValueConverter
{

    public SingleValueConverterWrapper(SingleValueConverter singlevalueconverter)
    {
        wrapped = singlevalueconverter;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        return wrapped.canConvert(class1);
    }

    @Override
	public Object fromString(String s)
    {
        return wrapped.fromString(s);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        hierarchicalstreamwriter.setValue(toString(obj));
    }

    @Override
	public String toString(Object obj)
    {
        return wrapped.toString(obj);
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return fromString(hierarchicalstreamreader.getValue());
    }

    private final SingleValueConverter wrapped;
}
