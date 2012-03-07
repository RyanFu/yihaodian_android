// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.persistence;

import java.util.Iterator;

public interface PersistenceStrategy
{

    public abstract Object get(Object obj);

    public abstract Iterator iterator();

    public abstract Object put(Object obj, Object obj1);

    public abstract Object remove(Object obj);

    public abstract int size();
}
