// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestEntity
{

    public abstract long getContentLength();

    public abstract String getContentType();

    public abstract boolean isRepeatable();

    public abstract void writeRequest(OutputStream outputstream)
        throws IOException;
}
