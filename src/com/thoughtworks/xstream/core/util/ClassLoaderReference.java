// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.core.util;


// Referenced classes of package com.thoughtworks.xstream.core.util:
//            CompositeClassLoader

public class ClassLoaderReference extends ClassLoader
{
    static class Replacement
    {

        private Object readResolve()
        {
            return new ClassLoaderReference(new CompositeClassLoader());
        }

        Replacement()
        {
        }
    }


    public ClassLoaderReference(ClassLoader classloader)
    {
        reference = classloader;
    }

    private Object writeReplace()
    {
        return new Replacement();
    }

    public ClassLoader getReference()
    {
        return reference;
    }

    @Override
	public Class loadClass(String s)
        throws ClassNotFoundException
    {
        return reference.loadClass(s);
    }

    public void setReference(ClassLoader classloader)
    {
        reference = classloader;
    }

    private transient ClassLoader reference;
}
