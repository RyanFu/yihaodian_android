// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.Map;

public class FontConverter
    implements Converter
{

    public FontConverter()
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
        boolean flag;
        if(class1.getName().equals("java.awt.Font") || class1.getName().equals("javax.swing.plaf.FontUIResource"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Map map = ((Font)obj).getAttributes();
        hierarchicalstreamwriter.startNode("attributes");
        marshallingcontext.convertAnother(map);
        hierarchicalstreamwriter.endNode();
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        hierarchicalstreamreader.moveDown();
        Class class1;
        Map map;
        Font font;
        Class class2;
        Class class3;
        Object obj;
        if(class$java$util$Map == null)
        {
            class1 = _mthclass$("java.util.Map");
            class$java$util$Map = class1;
        } else
        {
            class1 = class$java$util$Map;
        }
        map = (Map)unmarshallingcontext.convertAnother(null, class1);
        hierarchicalstreamreader.moveUp();
        font = Font.getFont(map);
        class2 = unmarshallingcontext.getRequiredType();
        if(class$javax$swing$plaf$FontUIResource == null)
        {
            class3 = _mthclass$("javax.swing.plaf.FontUIResource");
            class$javax$swing$plaf$FontUIResource = class3;
        } else
        {
            class3 = class$javax$swing$plaf$FontUIResource;
        }
        if(class2 == class3)
            obj = new FontUIResource(font);
        else
            obj = font;
        return obj;
    }

    static Class class$java$util$Map;
    static Class class$javax$swing$plaf$FontUIResource;
}
