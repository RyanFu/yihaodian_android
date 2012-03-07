// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.alias.ClassMapper;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class CachingMapper extends MapperWrapper
{

    public CachingMapper(ClassMapper classmapper)
    {
        this(((Mapper) (classmapper)));
        readResolve();
    }

    public CachingMapper(Mapper mapper)
    {
        super(mapper);
        readResolve();
    }

    private Object readResolve()
    {
        realClassCache = Collections.synchronizedMap(new HashMap(128));
        return this;
    }

    @Override
	public Class realClass(String s)
    {
        WeakReference weakreference = (WeakReference)realClassCache.get(s);
        if(weakreference == null) goto _L2; else goto _L1
_L1:
        Class class3 = (Class)weakreference.get();
        if(class3 == null) goto _L2; else goto _L3
_L3:
        Class class2 = class3;
_L5:
        return class2;
_L2:
        Class class1 = super.realClass(s);
        realClassCache.put(s, new WeakReference(class1));
        class2 = class1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private transient Map realClassCache;
}
