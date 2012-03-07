// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.reflection;

import java.lang.reflect.Field;

public interface ReflectionProvider
{
    public static interface Visitor
    {

        public abstract void visit(String s, Class class1, Class class2, Object obj);
    }


    public abstract boolean fieldDefinedInClass(String s, Class class1);

    public abstract Field getField(Class class1, String s);

    public abstract Class getFieldType(Object obj, String s, Class class1);

    public abstract Object newInstance(Class class1);

    public abstract void visitSerializableFields(Object obj, Visitor visitor);

    public abstract void writeField(Object obj, String s, Object obj1, Class class1);
}
