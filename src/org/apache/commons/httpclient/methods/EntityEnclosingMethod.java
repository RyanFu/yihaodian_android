// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods;

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.methods:
//            ExpectContinueMethod, RequestEntity, ByteArrayRequestEntity, InputStreamRequestEntity, 
//            StringRequestEntity

public abstract class EntityEnclosingMethod extends ExpectContinueMethod
{

    public EntityEnclosingMethod()
    {
        requestStream = null;
        requestString = null;
        repeatCount = 0;
        requestContentLength = -2L;
        chunked = false;
        setFollowRedirects(false);
    }

    public EntityEnclosingMethod(String s)
    {
        super(s);
        requestStream = null;
        requestString = null;
        repeatCount = 0;
        requestContentLength = -2L;
        chunked = false;
        setFollowRedirects(false);
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
        if(getRequestHeader("content-length") != null || getRequestHeader("Transfer-Encoding") != null) goto _L2; else goto _L1
_L1:
        long l = getRequestContentLength();
        if(l >= 0L) goto _L4; else goto _L3
_L3:
        if(!getEffectiveVersion().greaterEquals(HttpVersion.HTTP_1_1)) goto _L6; else goto _L5
_L5:
        addRequestHeader("Transfer-Encoding", "chunked");
_L2:
        return;
_L6:
        throw new ProtocolException(getEffectiveVersion() + " does not support chunk encoding");
_L4:
        addRequestHeader("Content-Length", String.valueOf(l));
        if(true) goto _L2; else goto _L7
_L7:
    }

    @Override
	protected void addRequestHeaders(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter EntityEnclosingMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(httpstate, httpconnection);
        addContentLengthRequestHeader(httpstate, httpconnection);
        if(getRequestHeader("Content-Type") == null)
        {
            RequestEntity requestentity = getRequestEntity();
            if(requestentity != null && requestentity.getContentType() != null)
                setRequestHeader("Content-Type", requestentity.getContentType());
        }
    }

    protected void clearRequestBody()
    {
        LOG.trace("enter EntityEnclosingMethod.clearRequestBody()");
        requestStream = null;
        requestString = null;
        requestEntity = null;
    }

    protected byte[] generateRequestBody()
    {
        LOG.trace("enter EntityEnclosingMethod.renerateRequestBody()");
        return null;
    }

    protected RequestEntity generateRequestEntity()
    {
        byte abyte0[] = generateRequestBody();
        if(abyte0 == null) goto _L2; else goto _L1
_L1:
        requestEntity = new ByteArrayRequestEntity(abyte0);
_L4:
        return requestEntity;
_L2:
        if(requestStream != null)
        {
            requestEntity = new InputStreamRequestEntity(requestStream, requestContentLength);
            requestStream = null;
        } else
        if(requestString != null)
        {
            String s = getRequestCharSet();
            try
            {
                requestEntity = new StringRequestEntity(requestString, null, s);
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                if(LOG.isWarnEnabled())
                    LOG.warn(s + " not supported");
                requestEntity = new StringRequestEntity(requestString);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    @Override
	public boolean getFollowRedirects()
    {
        return false;
    }

    @Override
	public String getRequestCharSet()
    {
        String s;
        if(getRequestHeader("Content-Type") == null)
        {
            if(requestEntity != null)
                s = getContentCharSet(new Header("Content-Type", requestEntity.getContentType()));
            else
                s = super.getRequestCharSet();
        } else
        {
            s = super.getRequestCharSet();
        }
        return s;
    }

    protected long getRequestContentLength()
    {
        LOG.trace("enter EntityEnclosingMethod.getRequestContentLength()");
        long l;
        if(!hasRequestContent())
            l = 0L;
        else
        if(chunked)
        {
            l = -1L;
        } else
        {
            if(requestEntity == null)
                requestEntity = generateRequestEntity();
            if(requestEntity == null)
                l = 0L;
            else
                l = requestEntity.getContentLength();
        }
        return l;
    }

    public RequestEntity getRequestEntity()
    {
        return generateRequestEntity();
    }

    @Override
	protected boolean hasRequestContent()
    {
        LOG.trace("enter EntityEnclosingMethod.hasRequestContent()");
        boolean flag;
        if(requestEntity != null || requestStream != null || requestString != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public void recycle()
    {
        LOG.trace("enter EntityEnclosingMethod.recycle()");
        clearRequestBody();
        requestContentLength = -2L;
        repeatCount = 0;
        chunked = false;
        super.recycle();
    }

    public void setContentChunked(boolean flag)
    {
        chunked = flag;
    }

    @Override
	public void setFollowRedirects(boolean flag)
    {
        if(flag)
        {
            throw new IllegalArgumentException("Entity enclosing requests cannot be redirected without user intervention");
        } else
        {
            super.setFollowRedirects(false);
            return;
        }
    }

    public void setRequestBody(InputStream inputstream)
    {
        LOG.trace("enter EntityEnclosingMethod.setRequestBody(InputStream)");
        clearRequestBody();
        requestStream = inputstream;
    }

    public void setRequestBody(String s)
    {
        LOG.trace("enter EntityEnclosingMethod.setRequestBody(String)");
        clearRequestBody();
        requestString = s;
    }

    public void setRequestContentLength(int i)
    {
        LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        requestContentLength = i;
    }

    public void setRequestContentLength(long l)
    {
        LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        requestContentLength = l;
    }

    public void setRequestEntity(RequestEntity requestentity)
    {
        clearRequestBody();
        requestEntity = requestentity;
    }

    @Override
	protected boolean writeRequestBody(HttpState httpstate, HttpConnection httpconnection)
        throws IOException, HttpException
    {
        LOG.trace("enter EntityEnclosingMethod.writeRequestBody(HttpState, HttpConnection)");
        boolean flag;
        if(!hasRequestContent())
        {
            LOG.debug("Request body has not been specified");
            flag = true;
        } else
        {
            if(requestEntity == null)
                requestEntity = generateRequestEntity();
            if(requestEntity == null)
            {
                LOG.debug("Request body is empty");
                flag = true;
            } else
            {
                long l = getRequestContentLength();
                if(repeatCount > 0 && !requestEntity.isRepeatable())
                    throw new ProtocolException("Unbuffered entity enclosing request can not be repeated.");
                repeatCount = 1 + repeatCount;
                Object obj = httpconnection.getRequestOutputStream();
                if(l < 0L)
                    obj = new ChunkedOutputStream(((OutputStream) (obj)));
                requestEntity.writeRequest(((OutputStream) (obj)));
                if(obj instanceof ChunkedOutputStream)
                    ((ChunkedOutputStream)obj).finish();
                ((OutputStream) (obj)).flush();
                LOG.debug("Request body sent");
                flag = true;
            }
        }
        return flag;
    }

    public static final long CONTENT_LENGTH_AUTO = -2L;
    public static final long CONTENT_LENGTH_CHUNKED = -1L;
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
    private boolean chunked;
    private int repeatCount;
    private long requestContentLength;
    private RequestEntity requestEntity;
    private InputStream requestStream;
    private String requestString;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$EntityEnclosingMethod == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.EntityEnclosingMethod");
            class$org$apache$commons$httpclient$methods$EntityEnclosingMethod = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
        }
        LOG = LogFactory.getLog(class1);
    }
}
