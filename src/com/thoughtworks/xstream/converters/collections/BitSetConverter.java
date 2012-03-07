// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

public class BitSetConverter
    implements Converter
{

    public BitSetConverter()
    {
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$util$BitSet == null)
        {
            class2 = _mthclass$("java.util.BitSet");
            class$java$util$BitSet = class2;
        } else
        {
            class2 = class$java$util$BitSet;
        }
        return class1.equals(class2);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        BitSet bitset = (BitSet)obj;
        StringBuffer stringbuffer = new StringBuffer();
        boolean flag = false;
        int i = 0;
        while(i < bitset.length()) 
        {
            if(bitset.get(i))
            {
                if(flag)
                    stringbuffer.append(',');
                else
                    flag = true;
                stringbuffer.append(i);
            }
            i++;
        }
        hierarchicalstreamwriter.setValue(stringbuffer.toString());
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        BitSet bitset = new BitSet();
        for(StringTokenizer stringtokenizer = new StringTokenizer(hierarchicalstreamreader.getValue(), ",", false); stringtokenizer.hasMoreTokens(); bitset.set(Integer.parseInt(stringtokenizer.nextToken())));
        return bitset;
    }

    static Class class$java$util$BitSet;
}
