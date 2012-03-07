// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.basic;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CharConverter
    implements Converter, SingleValueConverter
{

    public CharConverter()
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
        if(class1.equals(Character.TYPE)) goto _L2; else goto _L1
_L1:
        boolean flag;
        Class class2;
        if(class$java$lang$Character == null)
        {
            class2 = _mthclass$("java.lang.Character");
            class$java$lang$Character = class2;
        } else
        {
            class2 = class$java$lang$Character;
        }
        if(!class1.equals(class2)) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public Object fromString(String s)
    {
        Character character;
        if(s.length() == 0)
            character = new Character('\0');
        else
            character = new Character(s.charAt(0));
        return character;
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        hierarchicalstreamwriter.setValue(toString(obj));
    }

    @Override
	public String toString(Object obj)
    {
        String s;
        if(((Character)obj).charValue() == 0)
            s = "";
        else
            s = obj.toString();
        return s;
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        String s = hierarchicalstreamreader.getAttribute("null");
        Object obj;
        if(s != null && s.equals("true"))
            obj = new Character('\0');
        else
            obj = fromString(hierarchicalstreamreader.getValue());
        return obj;
    }

    static Class class$java$lang$Character;
}
