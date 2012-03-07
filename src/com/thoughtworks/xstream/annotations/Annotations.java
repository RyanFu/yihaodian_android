// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.annotations;

import com.thoughtworks.xstream.XStream;

public class Annotations
{

    private Annotations()
    {
    }

    /**
     * @deprecated Method configureAliases is deprecated
     */

    @Deprecated
	public static transient void configureAliases(XStream xstream, Class aclass[])
    {
        com/thoughtworks/xstream/annotations/Annotations;
        JVM INSTR monitorenter ;
        xstream.processAnnotations(aclass);
        com/thoughtworks/xstream/annotations/Annotations;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
