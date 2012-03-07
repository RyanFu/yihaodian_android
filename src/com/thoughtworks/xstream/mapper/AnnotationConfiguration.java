// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.mapper;


public interface AnnotationConfiguration
{

    public abstract void autodetectAnnotations(boolean flag);

    public abstract void processAnnotations(Class aclass[]);
}
