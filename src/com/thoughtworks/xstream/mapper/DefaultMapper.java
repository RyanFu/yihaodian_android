// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;

// Referenced classes of package com.thoughtworks.xstream.mapper:
//            Mapper, CannotResolveClassException

public class DefaultMapper
    implements Mapper
{

    public DefaultMapper(ClassLoader classloader)
    {
        classLoader = classloader;
        classAttributeIdentifier = "class";
    }

    public DefaultMapper(ClassLoader classloader, String s)
    {
        this(classloader);
        String s1;
        if(s == null)
            s1 = "class";
        else
            s1 = s;
        classAttributeIdentifier = s1;
    }

    private Object readResolve()
    {
        classAttributeIdentifier = "class";
        return this;
    }

    @Override
	public String aliasForAttribute(Class class1, String s)
    {
        return s;
    }

    @Override
	public String aliasForAttribute(String s)
    {
        return s;
    }

    @Override
	public String aliasForSystemAttribute(String s)
    {
        return s;
    }

    @Override
	public String attributeForAlias(Class class1, String s)
    {
        return s;
    }

    @Override
	public String attributeForAlias(String s)
    {
        return s;
    }

    @Override
	public String attributeForClassDefiningField()
    {
        return "defined-in";
    }

    @Override
	public String attributeForEnumType()
    {
        return "enum-type";
    }

    @Override
	public String attributeForImplementationClass()
    {
        return classAttributeIdentifier;
    }

    @Override
	public String attributeForReadResolveField()
    {
        return "resolves-to";
    }

    @Override
	public Class defaultImplementationOf(Class class1)
    {
        return class1;
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s)
    {
        return null;
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(Class class1, String s, Class class2)
    {
        return null;
    }

    @Override
	public SingleValueConverter getConverterFromAttribute(String s)
    {
        return null;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(Class class1)
    {
        return null;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1)
    {
        return null;
    }

    @Override
	public SingleValueConverter getConverterFromItemType(String s, Class class1, Class class2)
    {
        return null;
    }

    @Override
	public String getFieldNameForItemTypeAndName(Class class1, Class class2, String s)
    {
        return null;
    }

    @Override
	public Mapper.ImplicitCollectionMapping getImplicitCollectionDefForFieldName(Class class1, String s)
    {
        return null;
    }

    @Override
	public Class getItemTypeForItemFieldName(Class class1, String s)
    {
        return null;
    }

    @Override
	public Converter getLocalConverter(Class class1, String s)
    {
        return null;
    }

    @Override
	public boolean isImmutableValueType(Class class1)
    {
        return false;
    }

    @Override
	public Mapper lookupMapperOfType(Class class1)
    {
        return null;
    }

    public String lookupName(Class class1)
    {
        return serializedClass(class1);
    }

    public Class lookupType(String s)
    {
        return realClass(s);
    }

    @Override
	public Class realClass(String s)
    {
        Class class2;
label0:
        {
            Class class1;
            try
            {
                if(s.charAt(0) != '[')
                {
                    class2 = classLoader.loadClass(s);
                    break label0;
                }
                if(s.endsWith(";"))
                {
                    class2 = Class.forName(s.toString(), false, classLoader);
                    break label0;
                }
                class1 = Class.forName(s.toString());
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new CannotResolveClassException(s + " : " + classnotfoundexception.getMessage());
            }
            class2 = class1;
        }
        return class2;
    }

    @Override
	public String realMember(Class class1, String s)
    {
        return s;
    }

    @Override
	public String serializedClass(Class class1)
    {
        return class1.getName();
    }

    @Override
	public String serializedMember(Class class1, String s)
    {
        return s;
    }

    @Override
	public boolean shouldSerializeMember(Class class1, String s)
    {
        return true;
    }

    private transient String classAttributeIdentifier;
    private final ClassLoader classLoader;
}
