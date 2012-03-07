// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.core.util.ThreadSafePropertyEditor;

public class PropertyEditorCapableConverter
    implements SingleValueConverter
{

    public PropertyEditorCapableConverter(Class class1, Class class2)
    {
        type = class2;
        editor = new ThreadSafePropertyEditor(class1, 2, 5);
    }

    @Override
	public boolean canConvert(Class class1)
    {
        boolean flag;
        if(type == class1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public Object fromString(String s)
    {
        return editor.setAsText(s);
    }

    @Override
	public String toString(Object obj)
    {
        return editor.getAsText(obj);
    }

    private final ThreadSafePropertyEditor editor;
    private final Class type;
}
