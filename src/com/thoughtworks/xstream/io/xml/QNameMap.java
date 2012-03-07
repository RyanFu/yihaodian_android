// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import java.util.*;
import javax.xml.namespace.QName;

public class QNameMap
{

    public QNameMap()
    {
        defaultPrefix = "";
        defaultNamespace = "";
    }

    public String getDefaultNamespace()
    {
        return defaultNamespace;
    }

    public String getDefaultPrefix()
    {
        return defaultPrefix;
    }

    public String getJavaClassName(QName qname)
    {
        if(qnameToJava == null) goto _L2; else goto _L1
_L1:
        String s1 = (String)qnameToJava.get(qname);
        if(s1 == null) goto _L2; else goto _L3
_L3:
        String s = s1;
_L5:
        return s;
_L2:
        s = qname.getLocalPart();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public QName getQName(String s)
    {
        if(javaToQName == null) goto _L2; else goto _L1
_L1:
        QName qname1 = (QName)javaToQName.get(s);
        if(qname1 == null) goto _L2; else goto _L3
_L3:
        QName qname = qname1;
_L5:
        return qname;
_L2:
        qname = new QName(defaultNamespace, s, defaultPrefix);
        if(true) goto _L5; else goto _L4
_L4:
    }

    /**
     * @deprecated Method registerMapping is deprecated
     */

    @Deprecated
	public void registerMapping(QName qname, Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        registerMapping(qname, class1.getName());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method registerMapping is deprecated
     */

    @Deprecated
	public void registerMapping(QName qname, String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(javaToQName == null)
            javaToQName = Collections.synchronizedMap(new HashMap());
        if(qnameToJava == null)
            qnameToJava = Collections.synchronizedMap(new HashMap());
        javaToQName.put(s, qname);
        qnameToJava.put(qname, s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setDefaultNamespace(String s)
    {
        defaultNamespace = s;
    }

    public void setDefaultPrefix(String s)
    {
        defaultPrefix = s;
    }

    private String defaultNamespace;
    private String defaultPrefix;
    private Map javaToQName;
    private Map qnameToJava;
}
