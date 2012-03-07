// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.io.xml.xppdom;

import java.util.*;

public class Xpp3Dom
{

    public Xpp3Dom(String s)
    {
        name = s;
        childList = new ArrayList();
        childMap = new HashMap();
    }

    public void addChild(Xpp3Dom xpp3dom)
    {
        xpp3dom.setParent(this);
        childList.add(xpp3dom);
        childMap.put(xpp3dom.getName(), xpp3dom);
    }

    public String getAttribute(String s)
    {
        String s1;
        if(attributes != null)
            s1 = (String)attributes.get(s);
        else
            s1 = null;
        return s1;
    }

    public String[] getAttributeNames()
    {
        String as[];
        if(attributes == null)
            as = new String[0];
        else
            as = (String[])attributes.keySet().toArray(new String[0]);
        return as;
    }

    public Xpp3Dom getChild(int i)
    {
        return (Xpp3Dom)childList.get(i);
    }

    public Xpp3Dom getChild(String s)
    {
        return (Xpp3Dom)childMap.get(s);
    }

    public int getChildCount()
    {
        int i;
        if(childList == null)
            i = 0;
        else
            i = childList.size();
        return i;
    }

    public Xpp3Dom[] getChildren()
    {
        Xpp3Dom axpp3dom[];
        if(childList == null)
            axpp3dom = new Xpp3Dom[0];
        else
            axpp3dom = (Xpp3Dom[])childList.toArray(new Xpp3Dom[0]);
        return axpp3dom;
    }

    public Xpp3Dom[] getChildren(String s)
    {
        Xpp3Dom axpp3dom[];
        if(childList == null)
        {
            axpp3dom = new Xpp3Dom[0];
        } else
        {
            ArrayList arraylist = new ArrayList();
            int i = childList.size();
            for(int j = 0; j < i; j++)
            {
                Xpp3Dom xpp3dom = (Xpp3Dom)childList.get(j);
                if(s.equals(xpp3dom.getName()))
                    arraylist.add(xpp3dom);
            }

            axpp3dom = (Xpp3Dom[])arraylist.toArray(new Xpp3Dom[0]);
        }
        return axpp3dom;
    }

    public String getName()
    {
        return name;
    }

    public Xpp3Dom getParent()
    {
        return parent;
    }

    public String getValue()
    {
        return value;
    }

    public void setAttribute(String s, String s1)
    {
        if(attributes == null)
            attributes = new HashMap();
        attributes.put(s, s1);
    }

    public void setParent(Xpp3Dom xpp3dom)
    {
        parent = xpp3dom;
    }

    public void setValue(String s)
    {
        value = s;
    }

    protected Map attributes;
    protected List childList;
    protected Map childMap;
    protected String name;
    protected Xpp3Dom parent;
    protected String value;
}
