// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters;

import java.util.Iterator;

public interface ErrorWriter
{

    public abstract void add(String s, String s1);

    public abstract String get(String s);

    public abstract Iterator keys();
}
