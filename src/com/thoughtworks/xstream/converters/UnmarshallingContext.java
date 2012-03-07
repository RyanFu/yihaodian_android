// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters;


// Referenced classes of package com.thoughtworks.xstream.converters:
//            DataHolder, Converter

public interface UnmarshallingContext
    extends DataHolder
{

    public abstract void addCompletionCallback(Runnable runnable, int i);

    public abstract Object convertAnother(Object obj, Class class1);

    public abstract Object convertAnother(Object obj, Class class1, Converter converter);

    public abstract Object currentObject();

    public abstract Class getRequiredType();
}
