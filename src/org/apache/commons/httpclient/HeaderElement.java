// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.util.ParameterParser;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            NameValuePair, HttpException

public class HeaderElement extends NameValuePair
{

    public HeaderElement()
    {
        this(((String) (null)), ((String) (null)), ((NameValuePair []) (null)));
    }

    public HeaderElement(String s, String s1)
    {
        this(s, s1, ((NameValuePair []) (null)));
    }

    public HeaderElement(String s, String s1, NameValuePair anamevaluepair[])
    {
        super(s, s1);
        parameters = null;
        parameters = anamevaluepair;
    }

    public HeaderElement(char ac[])
    {
        this(ac, 0, ac.length);
    }

    public HeaderElement(char ac[], int i, int j)
    {
        this();
        if(ac != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        List list = (new ParameterParser()).parse(ac, i, j, ';');
        if(list.size() > 0)
        {
            NameValuePair namevaluepair = (NameValuePair)list.remove(0);
            setName(namevaluepair.getName());
            setValue(namevaluepair.getValue());
            if(list.size() > 0)
                parameters = (NameValuePair[])list.toArray(new NameValuePair[list.size()]);
        }
        if(true) goto _L1; else goto _L3
_L3:
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

    public static final HeaderElement[] parse(String s)
        throws HttpException
    {
        LOG.trace("enter HeaderElement.parse(String)");
        HeaderElement aheaderelement[];
        if(s == null)
            aheaderelement = new HeaderElement[0];
        else
            aheaderelement = parseElements(s.toCharArray());
        return aheaderelement;
    }

    public static final HeaderElement[] parseElements(String s)
    {
        LOG.trace("enter HeaderElement.parseElements(String)");
        HeaderElement aheaderelement[];
        if(s == null)
            aheaderelement = new HeaderElement[0];
        else
            aheaderelement = parseElements(s.toCharArray());
        return aheaderelement;
    }

    public static final HeaderElement[] parseElements(char ac[])
    {
        LOG.trace("enter HeaderElement.parseElements(char[])");
        if(ac != null) goto _L2; else goto _L1
_L1:
        HeaderElement aheaderelement[] = new HeaderElement[0];
_L4:
        return aheaderelement;
_L2:
        ArrayList arraylist = new ArrayList();
        int i = 0;
        int j = 0;
        int k = ac.length;
        boolean flag = false;
        do
        {
label0:
            {
                if(i < k)
                    break label0;
                aheaderelement = (HeaderElement[])arraylist.toArray(new HeaderElement[arraylist.size()]);
            }
            if(true)
                continue;
            char c = ac[i];
            HeaderElement headerelement;
            if(c == '"')
                if(!flag)
                    flag = true;
                else
                    flag = false;
            headerelement = null;
            if(!flag && c == ',')
            {
                headerelement = new HeaderElement(ac, j, i);
                j = i + 1;
            } else
            if(i == k - 1)
                headerelement = new HeaderElement(ac, j, k);
            if(headerelement != null && headerelement.getName() != null)
                arraylist.add(headerelement);
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public NameValuePair getParameterByName(String s)
    {
        NameValuePair namevaluepair;
        NameValuePair anamevaluepair[];
        LOG.trace("enter HeaderElement.getParameterByName(String)");
        if(s == null)
            throw new IllegalArgumentException("Name may not be null");
        namevaluepair = null;
        anamevaluepair = getParameters();
        if(anamevaluepair == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L6:
        if(i < anamevaluepair.length) goto _L3; else goto _L2
_L2:
        return namevaluepair;
_L3:
        NameValuePair namevaluepair1 = anamevaluepair[i];
        if(!namevaluepair1.getName().equalsIgnoreCase(s))
            break; /* Loop/switch isn't completed */
        namevaluepair = namevaluepair1;
        if(true) goto _L2; else goto _L4
_L4:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public NameValuePair[] getParameters()
    {
        return parameters;
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HeaderElement;
    private NameValuePair parameters[];

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$HeaderElement == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HeaderElement");
            class$org$apache$commons$httpclient$HeaderElement = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HeaderElement;
        }
        LOG = LogFactory.getLog(class1);
    }
}
