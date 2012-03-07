// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters;

import java.util.Iterator;

public interface DataHolder
{

    public abstract Object get(Object obj);

    public abstract Iterator keys();

    public abstract void put(Object obj, Object obj1);
}
