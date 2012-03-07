// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.methods:
//            ExpectContinueMethod

public class MultipartPostMethod extends ExpectContinueMethod
{

    public MultipartPostMethod()
    {
        parameters = new ArrayList();
    }

    public MultipartPostMethod(String s)
    {
        super(s);
        parameters = new ArrayList();
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

    protected void addContentLengthRequestHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter EntityEnclosingMethod.addContentLengthRequestHeader(HttpState, HttpConnection)");
        if(getRequestHeader("Content-Length") == null)
            addRequestHeader("Content-Length", String.valueOf(getRequestContentLength()));
        removeRequestHeader("Transfer-Encoding");
    }

    protected void addContentTypeRequestHeader(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter EntityEnclosingMethod.addContentTypeRequestHeader(HttpState, HttpConnection)");
        if(!parameters.isEmpty())
        {
            StringBuffer stringbuffer = new StringBuffer("multipart/form-data");
            if(Part.getBoundary() != null)
            {
                stringbuffer.append("; boundary=");
                stringbuffer.append(Part.getBoundary());
            }
            setRequestHeader("Content-Type", stringbuffer.toString());
        }
    }

    public void addParameter(String s, File file)
        throws FileNotFoundException
    {
        LOG.trace("enter MultipartPostMethod.addParameter(String parameterName, File parameterFile)");
        FilePart filepart = new FilePart(s, file);
        parameters.add(filepart);
    }

    public void addParameter(String s, String s1)
    {
        LOG.trace("enter addParameter(String parameterName, String parameterValue)");
        StringPart stringpart = new StringPart(s, s1);
        parameters.add(stringpart);
    }

    public void addParameter(String s, String s1, File file)
        throws FileNotFoundException
    {
        LOG.trace("enter MultipartPostMethod.addParameter(String parameterName, String fileName, File parameterFile)");
        FilePart filepart = new FilePart(s, s1, file);
        parameters.add(filepart);
    }

    public void addPart(Part part)
    {
        LOG.trace("enter addPart(Part part)");
        parameters.add(part);
    }

    @Override
	protected void addRequestHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter MultipartPostMethod.addRequestHeaders(HttpState state, HttpConnection conn)");
        super.addRequestHeaders(httpstate, httpconnection);
        addContentLengthRequestHeader(httpstate, httpconnection);
        addContentTypeRequestHeader(httpstate, httpconnection);
    }

    @Override
	public String getName()
    {
        return "POST";
    }

    public Part[] getParts()
    {
        return (Part[])parameters.toArray(new Part[parameters.size()]);
    }

    protected long getRequestContentLength()
        throws IOException
    {
        LOG.trace("enter MultipartPostMethod.getRequestContentLength()");
        return Part.getLengthOfParts(getParts());
    }

    @Override
	protected boolean hasRequestContent()
    {
        return true;
    }

    @Override
	public void recycle()
    {
        LOG.trace("enter MultipartPostMethod.recycle()");
        super.recycle();
        parameters.clear();
    }

    @Override
	protected boolean writeRequestBody(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter MultipartPostMethod.writeRequestBody(HttpState state, HttpConnection conn)");
        Part.sendParts(httpconnection.getRequestOutputStream(), getParts());
        return true;
    }

    private static final Log LOG;
    public static final String MULTIPART_FORM_CONTENT_TYPE = "multipart/form-data";
    static Class class$org$apache$commons$httpclient$methods$MultipartPostMethod;
    private final List parameters;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$MultipartPostMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.MultipartPostMethod");
            class$org$apache$commons$httpclient$methods$MultipartPostMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$MultipartPostMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
