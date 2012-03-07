// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core;


public class SequenceGenerator
    implements ReferenceByIdMarshaller.IDGenerator
{

    public SequenceGenerator(int i)
    {
        counter = i;
    }

    @Override
	public String next(Object obj)
    {
        int i = counter;
        counter = i + 1;
        return String.valueOf(i);
    }

    private int counter;
}
