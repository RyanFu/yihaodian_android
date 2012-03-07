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
//            CollectionConverter

public class TreeSetConverter extends CollectionConverter
{
    private static class PresortedSet
        implements SortedSet
    {

        @Override
		public boolean add(Object obj)
        {
            return list.add(obj);
        }

        @Override
		public boolean addAll(Collection collection)
        {
            return list.addAll(collection);
        }

        @Override
		public void clear()
        {
            list.clear();
        }

        @Override
		public Comparator comparator()
        {
            return comparator;
        }

        @Override
		public boolean contains(Object obj)
        {
            return list.contains(obj);
        }

        @Override
		public boolean containsAll(Collection collection)
        {
            return list.containsAll(collection);
        }

        @Override
		public boolean equals(Object obj)
        {
            return list.equals(obj);
        }

        @Override
		public Object first()
        {
            Object obj;
            if(list.isEmpty())
                obj = null;
            else
                obj = list.get(0);
            return obj;
        }

        @Override
		public int hashCode()
        {
            return list.hashCode();
        }

        @Override
		public SortedSet headSet(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public boolean isEmpty()
        {
            return list.isEmpty();
        }

        @Override
		public Iterator iterator()
        {
            return list.iterator();
        }

        @Override
		public Object last()
        {
            Object obj;
            if(list.isEmpty())
                obj = null;
            else
                obj = list.get(list.size() - 1);
            return obj;
        }

        @Override
		public boolean remove(Object obj)
        {
            return list.remove(obj);
        }

        @Override
		public boolean removeAll(Collection collection)
        {
            return list.removeAll(collection);
        }

        @Override
		public boolean retainAll(Collection collection)
        {
            return list.retainAll(collection);
        }

        @Override
		public int size()
        {
            return list.size();
        }

        public List subList(int i, int j)
        {
            return list.subList(i, j);
        }

        @Override
		public SortedSet subSet(Object obj, Object obj1)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public SortedSet tailSet(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        @Override
		public Object[] toArray()
        {
            return list.toArray();
        }

        @Override
		public Object[] toArray(Object aobj[])
        {
            return list.toArray(aobj);
        }

        private final Comparator comparator;
        private final List list;

        PresortedSet()
        {
            this(null);
        }

        PresortedSet(Comparator comparator1)
        {
            list = new ArrayList();
            comparator = comparator1;
        }
    }


    public TreeSetConverter(Mapper mapper)
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
        if(class$java$util$TreeSet == null)
        {
            class2 = _mthclass$("java.util.TreeSet");
            class$java$util$TreeSet = class2;
        } else
        {
            class2 = class$java$util$TreeSet;
        }
        return class1.equals(class2);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Comparator comparator = ((TreeSet)obj).comparator();
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
        PresortedSet presortedset;
        TreeSet treeset;
        if(hierarchicalstreamreader.getNodeName().equals("comparator"))
        {
            String s = hierarchicalstreamreader.getAttribute("class");
            Comparator comparator = (Comparator)unmarshallingcontext.convertAnother(null, mapper().realClass(s));
            presortedset = new PresortedSet(comparator);
            treeset = new TreeSet(comparator);
        } else
        if(hierarchicalstreamreader.getNodeName().equals("no-comparator"))
        {
            presortedset = new PresortedSet();
            treeset = new TreeSet();
        } else
        {
            throw new ConversionException("TreeSet does not contain <comparator> element");
        }
        hierarchicalstreamreader.moveUp();
        super.populateCollection(hierarchicalstreamreader, unmarshallingcontext, presortedset);
        treeset.addAll(presortedset);
        return treeset;
    }

    static Class class$java$util$TreeSet;
}
