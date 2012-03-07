// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import java.util.Map;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            FieldKeySorter

public class ImmutableFieldKeySorter
    implements FieldKeySorter
{

    public ImmutableFieldKeySorter()
    {
    }

    @Override
	public Map sort(Class class1, Map map)
    {
        return map;
    }
}
