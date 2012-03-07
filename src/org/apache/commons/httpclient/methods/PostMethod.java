// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.util.*;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.methods:
//            EntityEnclosingMethod, ByteArrayRequestEntity, RequestEntity

public class PostMethod extends EntityEnclosingMethod
{

    public PostMethod()
    {
        params = new Vector();
    }

    public PostMethod(String s)
    {
        super(s);
        params = new Vector();
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

    public void addParameter(String s, String s1)
        throws IllegalArgumentException
    {
        LOG.trace("enter PostMethod.addParameter(String, String)");
        if(s == null || s1 == null)
        {
            throw new IllegalArgumentException("Arguments to addParameter(String, String) cannot be null");
        } else
        {
            super.clearRequestBody();
            params.add(new NameValuePair(s, s1));
            return;
        }
    }

    public void addParameter(NameValuePair namevaluepair)
        throws IllegalArgumentException
    {
        LOG.trace("enter PostMethod.addParameter(NameValuePair)");
        if(namevaluepair == null)
        {
            throw new IllegalArgumentException("NameValuePair may not be null");
        } else
        {
            addParameter(namevaluepair.getName(), namevaluepair.getValue());
            return;
        }
    }

    public void addParameters(NameValuePair anamevaluepair[])
    {
        LOG.trace("enter PostMethod.addParameters(NameValuePair[])");
        if(anamevaluepair == null)
        {
            LOG.warn("Attempt to addParameters(null) ignored");
        } else
        {
            super.clearRequestBody();
            int i = 0;
            while(i < anamevaluepair.length) 
            {
                params.add(anamevaluepair[i]);
                i++;
            }
        }
    }

    @Override
	protected void clearRequestBody()
    {
        LOG.trace("enter PostMethod.clearRequestBody()");
        params.clear();
        super.clearRequestBody();
    }

    @Override
	protected RequestEntity generateRequestEntity()
    {
        Object obj;
        if(!params.isEmpty())
            obj = new ByteArrayRequestEntity(EncodingUtil.getAsciiBytes(EncodingUtil.formUrlEncode(getParameters(), getRequestCharSet())), "application/x-www-form-urlencoded");
        else
            obj = super.generateRequestEntity();
        return ((RequestEntity) (obj));
    }

    @Override
	public String getName()
    {
        return "POST";
    }

    public NameValuePair getParameter(String s)
    {
        LOG.trace("enter PostMethod.getParameter(String)");
        if(s != null) goto _L2; else goto _L1
_L1:
        NameValuePair namevaluepair1 = null;
_L4:
        return namevaluepair1;
_L2:
        Iterator iterator = params.iterator();
        NameValuePair namevaluepair;
        do
        {
            if(!iterator.hasNext())
            {
                namevaluepair1 = null;
                continue; /* Loop/switch isn't completed */
            }
            namevaluepair = (NameValuePair)iterator.next();
        } while(!s.equals(namevaluepair.getName()));
        namevaluepair1 = namevaluepair;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public NameValuePair[] getParameters()
    {
        LOG.trace("enter PostMethod.getParameters()");
        int i = params.size();
        Object aobj[] = params.toArray();
        NameValuePair anamevaluepair[] = new NameValuePair[i];
        int j = 0;
        do
        {
            if(j >= i)
                return anamevaluepair;
            anamevaluepair[j] = (NameValuePair)aobj[j];
            j++;
        } while(true);
    }

    @Override
	protected boolean hasRequestContent()
    {
        LOG.trace("enter PostMethod.hasRequestContent()");
        boolean flag;
        if(!params.isEmpty())
            flag = true;
        else
            flag = super.hasRequestContent();
        return flag;
    }

    public boolean removeParameter(String s)
        throws IllegalArgumentException
    {
        LOG.trace("enter PostMethod.removeParameter(String)");
        if(s == null)
            throw new IllegalArgumentException("Argument passed to removeParameter(String) cannot be null");
        boolean flag = false;
        Iterator iterator = params.iterator();
        do
        {
            do
                if(!iterator.hasNext())
                    return flag;
            while(!s.equals(((NameValuePair)iterator.next()).getName()));
            iterator.remove();
            flag = true;
        } while(true);
    }

    public boolean removeParameter(String s, String s1)
        throws IllegalArgumentException
    {
        Iterator iterator;
        LOG.trace("enter PostMethod.removeParameter(String, String)");
        if(s == null)
            throw new IllegalArgumentException("Parameter name may not be null");
        if(s1 == null)
            throw new IllegalArgumentException("Parameter value may not be null");
        iterator = params.iterator();
_L2:
        boolean flag;
        if(!iterator.hasNext())
        {
            flag = false;
        } else
        {
            NameValuePair namevaluepair = (NameValuePair)iterator.next();
            if(!s.equals(namevaluepair.getName()) || !s1.equals(namevaluepair.getValue()))
                continue; /* Loop/switch isn't completed */
            iterator.remove();
            flag = true;
        }
        return flag;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setParameter(String s, String s1)
    {
        LOG.trace("enter PostMethod.setParameter(String, String)");
        removeParameter(s);
        addParameter(s, s1);
    }

    public void setRequestBody(NameValuePair anamevaluepair[])
        throws IllegalArgumentException
    {
        LOG.trace("enter PostMethod.setRequestBody(NameValuePair[])");
        if(anamevaluepair == null)
        {
            throw new IllegalArgumentException("Array of parameters may not be null");
        } else
        {
            clearRequestBody();
            addParameters(anamevaluepair);
            return;
        }
    }

    public static final String FORM_URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$PostMethod;
    private Vector params;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$PostMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.PostMethod");
            class$org$apache$commons$httpclient$methods$PostMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$PostMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
