// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.params;


public interface HttpParams
{

    public abstract boolean getBooleanParameter(String s, boolean flag);

    public abstract HttpParams getDefaults();

    public abstract double getDoubleParameter(String s, double d);

    public abstract int getIntParameter(String s, int i);

    public abstract long getLongParameter(String s, long l);

    public abstract Object getParameter(String s);

    public abstract boolean isParameterFalse(String s);

    public abstract boolean isParameterSet(String s);

    public abstract boolean isParameterSetLocally(String s);

    public abstract boolean isParameterTrue(String s);

    public abstract void setBooleanParameter(String s, boolean flag);

    public abstract void setDefaults(HttpParams httpparams);

    public abstract void setDoubleParameter(String s, double d);

    public abstract void setIntParameter(String s, int i);

    public abstract void setLongParameter(String s, long l);

    public abstract void setParameter(String s, Object obj);
}
