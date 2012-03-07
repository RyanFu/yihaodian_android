// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.HierarchicalStreamDriver;

// Referenced classes of package com.thoughtworks.xstream.io.xml:
//            XmlFriendlyReplacer

public abstract class AbstractXmlDriver
    implements HierarchicalStreamDriver
{

    public AbstractXmlDriver()
    {
        this(new XmlFriendlyReplacer());
    }

    public AbstractXmlDriver(XmlFriendlyReplacer xmlfriendlyreplacer)
    {
        replacer = xmlfriendlyreplacer;
    }

    protected XmlFriendlyReplacer xmlFriendlyReplacer()
    {
        return replacer;
    }

    private XmlFriendlyReplacer replacer;
}
