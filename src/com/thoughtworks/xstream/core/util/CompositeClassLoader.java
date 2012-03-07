// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;

import java.util.*;

public class CompositeClassLoader extends ClassLoader
{

    public CompositeClassLoader()
    {
        Class class1;
        if(class$java$lang$Object == null)
        {
            class1 = _mthclass$("java.lang.Object");
            class$java$lang$Object = class1;
        } else
        {
            class1 = class$java$lang$Object;
        }
        add(class1.getClassLoader());
        add(getClass().getClassLoader());
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    public void add(ClassLoader classloader)
    {
        if(classloader != null)
            classLoaders.add(0, classloader);
    }

    @Override
	public Class loadClass(String s)
        throws ClassNotFoundException
    {
        Iterator iterator = classLoaders.iterator();
_L6:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        ClassLoader classloader1 = (ClassLoader)iterator.next();
        Class class2 = classloader1.loadClass(s);
        Class class1 = class2;
_L4:
        return class1;
_L2:
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        if(classloader != null)
            class1 = classloader.loadClass(s);
        else
            throw new ClassNotFoundException(s);
        if(true) goto _L4; else goto _L3
_L3:
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    static Class class$java$lang$Object;
    private final List classLoaders = Collections.synchronizedList(new ArrayList());
}
