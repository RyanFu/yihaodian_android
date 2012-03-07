// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io;

import com.thoughtworks.xstream.converters.ErrorWriter;
import java.util.Iterator;

public interface HierarchicalStreamReader
{

    public abstract void appendErrors(ErrorWriter errorwriter);

    public abstract void close();

    public abstract String getAttribute(int i);

    public abstract String getAttribute(String s);

    public abstract int getAttributeCount();

    public abstract String getAttributeName(int i);

    public abstract Iterator getAttributeNames();

    public abstract String getNodeName();

    public abstract String getValue();

    public abstract boolean hasMoreChildren();

    public abstract void moveDown();

    public abstract void moveUp();

    public abstract HierarchicalStreamReader underlyingReader();
}
