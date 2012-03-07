// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.logging.Log;

public class HttpURLConnection extends java.net.HttpURLConnection
{

    protected HttpURLConnection(URL url1)
    {
        super(url1);
        throw new RuntimeException("An HTTP URL connection can only be constructed from a HttpMethod class");
    }

    public HttpURLConnection(HttpMethod httpmethod, URL url1)
    {
        super(url1);
        method = httpmethod;
        url = url1;
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

    @Override
	public void connect()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.connect()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    @Override
	public void disconnect()
    {
        LOG.trace("enter HttpURLConnection.disconnect()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public boolean getAllowUserInteraction()
    {
        LOG.trace("enter HttpURLConnection.getAllowUserInteraction()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public Object getContent()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getContent()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public Object getContent(Class aclass[])
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getContent(Class[])");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public boolean getDefaultUseCaches()
    {
        LOG.trace("enter HttpURLConnection.getDefaultUseCaches()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public boolean getDoInput()
    {
        LOG.trace("enter HttpURLConnection.getDoInput()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public boolean getDoOutput()
    {
        LOG.trace("enter HttpURLConnection.getDoOutput()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public InputStream getErrorStream()
    {
        LOG.trace("enter HttpURLConnection.getErrorStream()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public String getHeaderField(int i)
    {
        LOG.trace("enter HttpURLConnection.getHeaderField(int)");
        String s;
        if(i == 0)
        {
            s = method.getStatusLine().toString();
        } else
        {
            org.apache.commons.httpclient.Header aheader[] = method.getResponseHeaders();
            if(i < 0 || i > aheader.length)
                s = null;
            else
                s = aheader[i - 1].getValue();
        }
        return s;
    }

    @Override
	public String getHeaderField(String s)
    {
        org.apache.commons.httpclient.Header aheader[];
        int i;
        LOG.trace("enter HttpURLConnection.getHeaderField(String)");
        aheader = method.getResponseHeaders();
        i = aheader.length - 1;
_L6:
        if(i >= 0) goto _L2; else goto _L1
_L1:
        String s1 = null;
_L4:
        return s1;
_L2:
        if(!aheader[i].getName().equalsIgnoreCase(s))
            break; /* Loop/switch isn't completed */
        s1 = aheader[i].getValue();
        if(true) goto _L4; else goto _L3
_L3:
        i--;
        if(true) goto _L6; else goto _L5
_L5:
    }

    @Override
	public String getHeaderFieldKey(int i)
    {
        LOG.trace("enter HttpURLConnection.getHeaderFieldKey(int)");
        String s;
        if(i == 0)
        {
            s = null;
        } else
        {
            org.apache.commons.httpclient.Header aheader[] = method.getResponseHeaders();
            if(i < 0 || i > aheader.length)
                s = null;
            else
                s = aheader[i - 1].getName();
        }
        return s;
    }

    @Override
	public long getIfModifiedSince()
    {
        LOG.trace("enter HttpURLConnection.getIfmodifiedSince()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public InputStream getInputStream()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getInputStream()");
        return method.getResponseBodyAsStream();
    }

    @Override
	public boolean getInstanceFollowRedirects()
    {
        LOG.trace("enter HttpURLConnection.getInstanceFollowRedirects()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public OutputStream getOutputStream()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getOutputStream()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    @Override
	public Permission getPermission()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getPermission()");
        throw new RuntimeException("Not implemented yet");
    }

    @Override
	public String getRequestMethod()
    {
        LOG.trace("enter HttpURLConnection.getRequestMethod()");
        return method.getName();
    }

    public String getRequestProperty(String s)
    {
        LOG.trace("enter HttpURLConnection.getRequestProperty()");
        throw new RuntimeException("Not implemented yet");
    }

    public int getResponseCode()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getResponseCode()");
        return method.getStatusCode();
    }

    public String getResponseMessage()
        throws IOException
    {
        LOG.trace("enter HttpURLConnection.getResponseMessage()");
        return method.getStatusText();
    }

    public URL getURL()
    {
        LOG.trace("enter HttpURLConnection.getURL()");
        return url;
    }

    public boolean getUseCaches()
    {
        LOG.trace("enter HttpURLConnection.getUseCaches()");
        throw new RuntimeException("Not implemented yet");
    }

    public void setAllowUserInteraction(boolean flag)
    {
        LOG.trace("enter HttpURLConnection.setAllowUserInteraction(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setDefaultUseCaches(boolean flag)
    {
        LOG.trace("enter HttpURLConnection.setDefaultUseCaches(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setDoInput(boolean flag)
    {
        LOG.trace("enter HttpURLConnection.setDoInput()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setDoOutput(boolean flag)
    {
        LOG.trace("enter HttpURLConnection.setDoOutput()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setIfModifiedSince(long l)
    {
        LOG.trace("enter HttpURLConnection.setIfModifiedSince(long)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setInstanceFollowRedirects(boolean flag)
    {
        LOG.trace("enter HttpURLConnection.setInstanceFollowRedirects(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setRequestMethod(String s)
        throws ProtocolException
    {
        LOG.trace("enter HttpURLConnection.setRequestMethod(String)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setRequestProperty(String s, String s1)
    {
        LOG.trace("enter HttpURLConnection.setRequestProperty()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public void setUseCaches(boolean flag)
    {
        LOG.trace("enter HttpURLConnection.setUseCaches(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }

    public boolean usingProxy()
    {
        LOG.trace("enter HttpURLConnection.usingProxy()");
        throw new RuntimeException("Not implemented yet");
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$util$HttpURLConnection;
    private HttpMethod method;
    private URL url;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$util$HttpURLConnection == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.util.HttpURLConnection");
            class$org$apache$commons$httpclient$util$HttpURLConnection = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$util$HttpURLConnection;
        }
        LOG = LogFactory.getLog(class1);
    }
}
