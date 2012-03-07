// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;


public class FieldKey
{

    public FieldKey(String s, Class class1, int i)
    {
        if(s == null || class1 == null)
            throw new IllegalArgumentException("fieldName or declaringClass is null");
        fieldName = s;
        declaringClass = class1;
        order = i;
        Class class2 = class1;
        int j = 0;
        for(; class2.getSuperclass() != null; class2 = class2.getSuperclass())
            j++;

        depth = j;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof FieldKey))
        {
            flag = false;
        } else
        {
            FieldKey fieldkey = (FieldKey)obj;
            if(!declaringClass.equals(fieldkey.declaringClass))
                flag = false;
            else
            if(!fieldName.equals(fieldkey.fieldName))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public Class getDeclaringClass()
    {
        return declaringClass;
    }

    public int getDepth()
    {
        return depth;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public int getOrder()
    {
        return order;
    }

    @Override
	public int hashCode()
    {
        return 29 * fieldName.hashCode() + declaringClass.hashCode();
    }

    @Override
	public String toString()
    {
        return "FieldKey{order=" + order + ", writer=" + depth + ", declaringClass=" + declaringClass + ", fieldName='" + fieldName + "'" + "}";
    }

    private final Class declaringClass;
    private final int depth;
    private final String fieldName;
    private final int order;
}
