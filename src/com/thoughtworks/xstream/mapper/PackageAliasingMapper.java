// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import java.io.*;
import java.util.*;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            MapperWrapper, Mapper

public class PackageAliasingMapper extends MapperWrapper
    implements Serializable
{

    public PackageAliasingMapper(Mapper mapper)
    {
        super(mapper);
        packageToName = new TreeMap(REVERSE);
        nameToPackage = new HashMap();
    }

    private void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        packageToName = new TreeMap(REVERSE);
        packageToName.putAll((Map)objectinputstream.readObject());
        nameToPackage = new HashMap();
        Object obj;
        for(Iterator iterator = packageToName.keySet().iterator(); iterator.hasNext(); nameToPackage.put(packageToName.get(obj), obj))
            obj = iterator.next();

    }

    private void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.writeObject(new HashMap(packageToName));
    }

    public void addPackageAlias(String s, String s1)
    {
        if(s.length() > 0 && s.charAt(s.length() - 1) != '.')
            s = s + '.';
        if(s1.length() > 0 && s1.charAt(s1.length() - 1) != '.')
            s1 = s1 + '.';
        nameToPackage.put(s, s1);
        packageToName.put(s1, s);
    }

    @Override
	public Class realClass(String s)
    {
        int i = s.length();
_L4:
        int j;
        j = s.lastIndexOf('.', i);
        String s1;
        String s2;
        StringBuffer stringbuffer;
        if(j < 0)
            s1 = "";
        else
            s1 = s.substring(0, j) + '.';
        s2 = (String)nameToPackage.get(s1);
        if(s2 == null) goto _L2; else goto _L1
_L1:
        stringbuffer = (new StringBuffer()).append(s2);
        String s3;
        if(j < 0)
            s3 = s;
        else
            s3 = s.substring(j + 1);
        s = stringbuffer.append(s3).toString();
_L3:
        return super.realClass(s);
_L2:
        i = j - 1;
        if(j >= 0) goto _L4; else goto _L3
    }

    @Override
	public String serializedClass(Class class1)
    {
        String s;
        int i;
        s = class1.getName();
        i = s.length();
_L2:
        int j = s.lastIndexOf('.', i);
        String s1;
        String s2;
        String s3;
        if(j < 0)
            s1 = "";
        else
            s1 = s.substring(0, j + 1);
        s2 = (String)packageToName.get(s1);
        if(s2 != null)
        {
            StringBuffer stringbuffer = (new StringBuffer()).append(s2);
            String s4;
            if(j < 0)
                s4 = s;
            else
                s4 = s.substring(j + 1);
            s3 = stringbuffer.append(s4).toString();
        } else
        {
            i = j - 1;
            if(j >= 0)
                continue; /* Loop/switch isn't completed */
            s3 = super.serializedClass(class1);
        }
        return s3;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final Comparator REVERSE = new _cls1();
    protected transient Map nameToPackage;
    private Map packageToName;


    private class _cls1
        implements Comparator
    {

        @Override
		public int compare(Object obj, Object obj1)
        {
            return ((String)obj1).compareTo((String)obj);
        }

        _cls1()
        {
        }
    }

}
