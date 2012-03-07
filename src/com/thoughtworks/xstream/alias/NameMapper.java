// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.alias;


public interface NameMapper
{

    public abstract String fromXML(String s);

    public abstract String toXML(String s);
}
