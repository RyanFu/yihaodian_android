// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;


public interface HierarchicalStreamWriter
{

    public abstract void addAttribute(String s, String s1);

    public abstract void close();

    public abstract void endNode();

    public abstract void flush();

    public abstract void setValue(String s);

    public abstract void startNode(String s);

    public abstract HierarchicalStreamWriter underlyingWriter();
}
