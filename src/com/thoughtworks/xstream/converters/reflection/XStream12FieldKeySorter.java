// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.thoughtworks.xstream.converters.reflection:
//            FieldKeySorter

public class XStream12FieldKeySorter
    implements FieldKeySorter
{

    public XStream12FieldKeySorter()
    {
    }

    @Override
	public Map sort(Class class1, Map map)
    {
        TreeMap treemap = new TreeMap(new _cls1());
        treemap.putAll(map);
        return treemap;
    }

    private class _cls1
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            FieldKey fieldkey = (FieldKey)obj;
            FieldKey fieldkey1 = (FieldKey)obj1;
            int i = fieldkey1.getDepth() - fieldkey.getDepth();
            if(i == 0)
                i = fieldkey.getOrder() - fieldkey1.getOrder();
            return i;
        }

        private final XStream12FieldKeySorter this$0;

        _cls1()
        {
            this$0 = XStream12FieldKeySorter.this;
        }
    }

}
