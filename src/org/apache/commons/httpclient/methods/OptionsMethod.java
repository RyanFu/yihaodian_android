// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.util.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.logging.Log;

public class OptionsMethod extends HttpMethodBase
{

    public OptionsMethod()
    {
        methodsAllowed = new Vector();
    }

    public OptionsMethod(String s)
    {
        super(s);
        methodsAllowed = new Vector();
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    public Enumeration getAllowedMethods()
    {
        checkUsed();
        return methodsAllowed.elements();
    }

    @Override
	public String getName()
    {
        return "OPTIONS";
    }

    public boolean isAllowed(String s)
    {
        checkUsed();
        return methodsAllowed.contains(s);
    }

    public boolean needContentLength()
    {
        return false;
    }

    @Override
	protected void processResponseHeaders(HttpState httpstate, HttpConnection httpconnection)
    {
        org.apache.commons.httpclient.Header header;
        LOG.trace("enter OptionsMethod.processResponseHeaders(HttpState, HttpConnection)");
        header = getResponseHeader("allow");
        if(header == null) goto _L2; else goto _L1
_L1:
        StringTokenizer stringtokenizer = new StringTokenizer(header.getValue(), ",");
_L5:
        if(stringtokenizer.hasMoreElements()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        String s = stringtokenizer.nextToken().trim().toUpperCase();
        methodsAllowed.addElement(s);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$OptionsMethod;
    private Vector methodsAllowed;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$OptionsMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.OptionsMethod");
            class$org$apache$commons$httpclient$methods$OptionsMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$OptionsMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
