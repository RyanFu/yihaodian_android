// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import java.util.*;
import javax.security.auth.Subject;

public class SubjectConverter extends AbstractCollectionConverter
{

    public SubjectConverter(Mapper mapper)
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
        if(class$javax$security$auth$Subject == null)
        {
            class2 = _mthclass$("javax.security.auth.Subject");
            class$javax$security$auth$Subject = class2;
        } else
        {
            class2 = class$javax$security$auth$Subject;
        }
        return class1.equals(class2);
    }

    @Override
	public void marshal(Object obj, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        Subject subject = (Subject)obj;
        marshalPrincipals(subject.getPrincipals(), hierarchicalstreamwriter, marshallingcontext);
        marshalPublicCredentials(subject.getPublicCredentials(), hierarchicalstreamwriter, marshallingcontext);
        marshalPrivateCredentials(subject.getPrivateCredentials(), hierarchicalstreamwriter, marshallingcontext);
        marshalReadOnly(subject.isReadOnly(), hierarchicalstreamwriter);
    }

    protected void marshalPrincipals(Set set, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
        hierarchicalstreamwriter.startNode("principals");
        for(Iterator iterator = set.iterator(); iterator.hasNext(); writeItem(iterator.next(), marshallingcontext, hierarchicalstreamwriter));
        hierarchicalstreamwriter.endNode();
    }

    protected void marshalPrivateCredentials(Set set, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
    }

    protected void marshalPublicCredentials(Set set, HierarchicalStreamWriter hierarchicalstreamwriter, MarshallingContext marshallingcontext)
    {
    }

    protected void marshalReadOnly(boolean flag, HierarchicalStreamWriter hierarchicalstreamwriter)
    {
        hierarchicalstreamwriter.startNode("readOnly");
        hierarchicalstreamwriter.setValue(String.valueOf(flag));
        hierarchicalstreamwriter.endNode();
    }

    protected Set populateSet(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        HashSet hashset = new HashSet();
        hierarchicalstreamreader.moveDown();
        Object obj;
        for(; hierarchicalstreamreader.hasMoreChildren(); hashset.add(obj))
        {
            hierarchicalstreamreader.moveDown();
            obj = readItem(hierarchicalstreamreader, unmarshallingcontext, hashset);
            hierarchicalstreamreader.moveUp();
        }

        hierarchicalstreamreader.moveUp();
        return hashset;
    }

    @Override
	public Object unmarshal(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        Set set = unmarshalPrincipals(hierarchicalstreamreader, unmarshallingcontext);
        Set set1 = unmarshalPublicCredentials(hierarchicalstreamreader, unmarshallingcontext);
        Set set2 = unmarshalPrivateCredentials(hierarchicalstreamreader, unmarshallingcontext);
        return new Subject(unmarshalReadOnly(hierarchicalstreamreader), set, set1, set2);
    }

    protected Set unmarshalPrincipals(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return populateSet(hierarchicalstreamreader, unmarshallingcontext);
    }

    protected Set unmarshalPrivateCredentials(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return Collections.EMPTY_SET;
    }

    protected Set unmarshalPublicCredentials(HierarchicalStreamReader hierarchicalstreamreader, UnmarshallingContext unmarshallingcontext)
    {
        return Collections.EMPTY_SET;
    }

    protected boolean unmarshalReadOnly(HierarchicalStreamReader hierarchicalstreamreader)
    {
        hierarchicalstreamreader.moveDown();
        boolean flag = Boolean.getBoolean(hierarchicalstreamreader.getValue());
        hierarchicalstreamreader.moveUp();
        return flag;
    }

    static Class class$javax$security$auth$Subject;
}
