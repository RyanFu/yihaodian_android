// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters;


// Referenced classes of package com.thoughtworks.xstream.converters:
//            Converter

public interface ConverterLookup
{

    public abstract Converter lookupConverterForType(Class class1);
}
