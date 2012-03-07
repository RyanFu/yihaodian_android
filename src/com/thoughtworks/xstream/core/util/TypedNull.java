// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;


public class TypedNull
{

    public TypedNull(Class class1)
    {
        type = class1;
    }

    public Class getType()
    {
        return type;
    }

    private final Class type;
}
