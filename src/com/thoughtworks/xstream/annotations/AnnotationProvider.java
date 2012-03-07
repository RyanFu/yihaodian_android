// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationProvider
{

    public AnnotationProvider()
    {
    }

    public Annotation getAnnotation(Field field, Class class1)
    {
        return field.getAnnotation(class1);
    }
}
