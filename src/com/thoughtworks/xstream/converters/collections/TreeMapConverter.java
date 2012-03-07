// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.collections;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.converters.collections:
//            MapConverter

public class TreeMapConverter extends MapConverter
{
    private static class PresortedMap
        implements SortedMap
    {
        private static class ArraySet extends ArrayList
            implements Set
        {

            private ArraySet()
            {
            }

        }


        @Override
		public void clear()
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Comparator comparator()
        {
            return comparator;
        }

        @Override
		public boolean containsKey(Object obj)
        {
            return false;
        }

        @Override
		public boolean containsValue(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Set entrySet()
        {
            return set;
        }

        @Override
		public Object firstKey()
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Object get(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public SortedMap headMap(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean isEmpty()
        {
            return set.isEmpty();
        }

        @Override
		public Set keySet()
        {
            ArraySet arrayset = new ArraySet();
            for(Iterator iterator = set.iterator(); iterator.hasNext(); arrayset.add(((java.util.Map.Entry)iterator.next()).getKey()));
            return arrayset;
        }

        @Override
		public Object lastKey()
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Object put(final Object key, final Object value)
        {
            class _cls1
                implements java.util.Map.Entry
            {

                @Override
				public Object getKey()
                {
                    return key;
                }

                @Override
				public Object getValue()
                {
                    return value;
                }

                @Override
				public Object setValue(Object obj)
                {
                    throw new UnsupportedOperationException();
                }

                private final PresortedMap this$0;
                private final Object val$key;
                private final Object val$value;

                _cls1()
                {
                    this$0 = PresortedMap.this;
                    key = obj;
                    value = obj1;
                }
            }

            set.add(new _cls1());
            return null;
        }

        @Override
		public void putAll(Map map)
        {
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); set.add(iterator.next()));
        }

        @Override
		public Object remove(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public int size()
        {
            return set.size();
        }

        @Override
		public SortedMap subMap(Object obj, Object obj1)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public SortedMap tailMap(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Collection values()
        {
            ArraySet arrayset = new ArraySet();
            for(Iterator iterator = set.iterator(); iterator.hasNext(); arrayset.add(((java.util.Map.Entry)iterator.next()).getValue()));
            return arrayset;
        }

        private final Comparator comparator;
        private final ArraySet set;

        PresortedMap()
        {
            this(null);
        }

        PresortedMap(Comparator comparator1)
        {
            set = new ArraySet();
            comparator = comparator1;
        }
    }


    public TreeMapConverter(Mapper mapper)
    {
        super(mapper);
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

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$util$TreeMap == null)
        {
            class2 = _mthclass$("java.util.TreeMap");
            class$java$util$TreeMap = class2;
        } else
        {
            class2 = class$java$util$TreeMap;
        }
        return class1.equals(class2);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Comparator comparator = ((TreeMap)obj).comparator();
        if(comparator == null)
        {
            hierarchicalstreamwriter.startNode("no-comparator");
            hierarchicalstreamwriter.endNode();
        } else
        {
            hierarchicalstreamwriter.startNode("comparator");
            hierarchicalstreamwriter.addAttribute("class", mapper().serializedClass(comparator.getClass()));
            marshallingcontext.convertAnother(comparator);
            hierarchicalstreamwriter.endNode();
        }
        super.marshal(obj, hierarchicalstreamwriter, marshallingcontext);
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        hierarchicalstreamreader.moveDown();
        PresortedMap presortedmap;
        TreeMap treemap;
        if(hierarchicalstreamreader.getNodeName().equals("comparator"))
        {
            String s = hierarchicalstreamreader.getAttribute("class");
            Comparator comparator = (Comparator)unmarshallingcontext.convertAnother(null, mapper().realClass(s));
            presortedmap = new PresortedMap(comparator);
            treemap = new TreeMap(comparator);
        } else
        if(hierarchicalstreamreader.getNodeName().equals("no-comparator"))
        {
            presortedmap = new PresortedMap();
            treemap = new TreeMap();
        } else
        {
            throw new ConversionException("TreeMap does not contain <comparator> element");
        }
        hierarchicalstreamreader.moveUp();
        super.populateMap(hierarchicalstreamreader, unmarshallingcontext, presortedmap);
        treemap.putAll(presortedmap);
        return treemap;
    }

    static Class class$java$util$TreeMap;
}
