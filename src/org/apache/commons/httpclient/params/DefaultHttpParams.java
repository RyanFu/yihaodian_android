// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;

import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.params:
//            HttpParams, DefaultHttpParamsFactory, HttpParamsFactory

public class DefaultHttpParams
    implements HttpParams, Serializable, Cloneable
{

    public DefaultHttpParams()
    {
        this(getDefaultParams());
    }

    public DefaultHttpParams(HttpParams httpparams)
    {
        defaults = null;
        parameters = null;
        defaults = httpparams;
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

    public static HttpParams getDefaultParams()
    {
        return httpParamsFactory.getDefaultParams();
    }

    public static void setHttpParamsFactory(HttpParamsFactory httpparamsfactory)
    {
        if(httpparamsfactory == null)
        {
            throw new IllegalArgumentException("httpParamsFactory may not be null");
        } else
        {
            httpParamsFactory = httpparamsfactory;
            return;
        }
    }

    public void clear()
    {
        parameters = null;
    }

    @Override
	public Object clone()
        throws CloneNotSupportedException
    {
        DefaultHttpParams defaulthttpparams = (DefaultHttpParams)super.clone();
        if(parameters != null)
            defaulthttpparams.parameters = (HashMap)parameters.clone();
        defaulthttpparams.setDefaults(defaults);
        return defaulthttpparams;
    }

    @Override
	public boolean getBooleanParameter(String s, boolean flag)
    {
        Object obj = getParameter(s);
        boolean flag1;
        if(obj == null)
            flag1 = flag;
        else
            flag1 = ((Boolean)obj).booleanValue();
        return flag1;
    }

    /**
     * @deprecated Method getDefaults is deprecated
     */

    @Deprecated
	@Override
	public HttpParams getDefaults()
    {
        this;
        JVM INSTR monitorenter ;
        HttpParams httpparams = defaults;
        this;
        JVM INSTR monitorexit ;
        return httpparams;
        Exception exception;
        exception;
        throw exception;
    }

    @Override
	public double getDoubleParameter(String s, double d)
    {
        Object obj = getParameter(s);
        double d1;
        if(obj == null)
            d1 = d;
        else
            d1 = ((Double)obj).doubleValue();
        return d1;
    }

    @Override
	public int getIntParameter(String s, int i)
    {
        Object obj = getParameter(s);
        int j;
        if(obj == null)
            j = i;
        else
            j = ((Integer)obj).intValue();
        return j;
    }

    @Override
	public long getLongParameter(String s, long l)
    {
        Object obj = getParameter(s);
        long l1;
        if(obj == null)
            l1 = l;
        else
            l1 = ((Long)obj).longValue();
        return l1;
    }

    /**
     * @deprecated Method getParameter is deprecated
     */

    @Deprecated
	@Override
	public Object getParameter(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = null;
        Object obj3;
        if(parameters == null)
            break MISSING_BLOCK_LABEL_24;
        obj3 = parameters.get(s);
        obj = obj3;
        if(obj == null) goto _L2; else goto _L1
_L1:
        Object obj1 = obj;
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj1;
_L2:
        Object obj2;
        if(defaults == null)
            break MISSING_BLOCK_LABEL_62;
        obj2 = defaults.getParameter(s);
        obj1 = obj2;
        continue; /* Loop/switch isn't completed */
        obj1 = null;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    @Override
	public boolean isParameterFalse(String s)
    {
        boolean flag;
        if(!getBooleanParameter(s, false))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public boolean isParameterSet(String s)
    {
        boolean flag;
        if(getParameter(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public boolean isParameterSetLocally(String s)
    {
        boolean flag;
        if(parameters != null && parameters.get(s) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public boolean isParameterTrue(String s)
    {
        return getBooleanParameter(s, false);
    }

    @Override
	public void setBooleanParameter(String s, boolean flag)
    {
        setParameter(s, new Boolean(flag));
    }

    /**
     * @deprecated Method setDefaults is deprecated
     */

    @Deprecated
	@Override
	public void setDefaults(HttpParams httpparams)
    {
        this;
        JVM INSTR monitorenter ;
        defaults = httpparams;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    @Override
	public void setDoubleParameter(String s, double d)
    {
        setParameter(s, new Double(d));
    }

    @Override
	public void setIntParameter(String s, int i)
    {
        setParameter(s, new Integer(i));
    }

    public void setLongParameter(String s, long l)
    {
        setParameter(s, new Long(l));
    }

    /**
     * @deprecated Method setParameter is deprecated
     */

    public void setParameter(String s, Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if(parameters == null)
            parameters = new HashMap();
        parameters.put(s, obj);
        if(LOG.isDebugEnabled())
            LOG.debug("Set parameter " + s + " = " + obj);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method setParameters is deprecated
     */

    public void setParameters(String as[], Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        int j = as.length;
        if(i < j)
            break MISSING_BLOCK_LABEL_17;
        this;
        JVM INSTR monitorexit ;
        return;
        setParameter(as[i], obj);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$params$DefaultHttpParams;
    private static HttpParamsFactory httpParamsFactory = new DefaultHttpParamsFactory();
    private HttpParams defaults;
    private HashMap parameters;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$params$DefaultHttpParams == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.params.DefaultHttpParams");
            class$org$apache$commons$httpclient$params$DefaultHttpParams = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$params$DefaultHttpParams;
        }
        LOG = LogFactory.getLog(class1);
    }
}
