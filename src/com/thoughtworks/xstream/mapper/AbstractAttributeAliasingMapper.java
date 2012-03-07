// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public abstract class AbstractAttributeAliasingMapper extends MapperWrapper
{

    public AbstractAttributeAliasingMapper(Mapper mapper)
    {
        super(mapper);
        nameToAlias = new HashMap();
    }

    private Object readResolve()
    {
        nameToAlias = new HashMap();
        Object obj;
        for(Iterator iterator = aliasToName.keySet().iterator(); iterator.hasNext(); nameToAlias.put(aliasToName.get(obj), obj))
            obj = iterator.next();

        return this;
    }

    public void addAliasFor(String s, String s1)
    {
        aliasToName.put(s1, s);
        nameToAlias.put(s, s1);
    }

    protected final Map aliasToName = new HashMap();
    protected transient Map nameToAlias;
}
