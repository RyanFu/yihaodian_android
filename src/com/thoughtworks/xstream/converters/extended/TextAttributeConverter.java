// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.reflection.AbstractAttributedCharacterIteratorAttributeConverter;

public class TextAttributeConverter extends AbstractAttributedCharacterIteratorAttributeConverter
{

    public TextAttributeConverter()
    {
        Class class1;
        if(class$java$awt$font$TextAttribute == null)
        {
            class1 = _mthclass$("java.awt.font.TextAttribute");
            class$java$awt$font$TextAttribute = class1;
        } else
        {
            class1 = class$java$awt$font$TextAttribute;
        }
        super(class1);
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

    static Class class$java$awt$font$TextAttribute;
}
