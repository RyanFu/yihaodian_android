// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.converters.basic.ByteConverter;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.*;

public class EncodedByteArrayConverter
    implements Converter
{

    public EncodedByteArrayConverter()
    {
    }

    private Object unmarshalIndividualByteElements(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        ArrayList arraylist = new ArrayList();
        for(boolean flag = true; flag || hierarchicalstreamreader.hasMoreChildren(); flag = false)
        {
            hierarchicalstreamreader.moveDown();
            arraylist.add(byteConverter.fromString(hierarchicalstreamreader.getValue()));
            hierarchicalstreamreader.moveUp();
        }

        byte abyte0[] = new byte[arraylist.size()];
        int i = 0;
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
        {
            abyte0[i] = ((Byte)iterator.next()).byteValue();
            i++;
        }

        return abyte0;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(class1.isArray() && class1.getComponentType().equals(Byte.TYPE))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        hierarchicalstreamwriter.setValue(base64.encode((byte[])obj));
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        String s = hierarchicalstreamreader.getValue();
        byte abyte0[];
        if(!hierarchicalstreamreader.hasMoreChildren())
            abyte0 = base64.decode(s);
        else
            abyte0 = ((byte []) (unmarshalIndividualByteElements(hierarchicalstreamreader, unmarshallingcontext)));
        return abyte0;
    }

    private static final Base64Encoder base64 = new Base64Encoder();
    private static final ByteConverter byteConverter = new ByteConverter();

}
