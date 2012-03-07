// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.methods.multipart;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.methods.multipart:
//            Part

public class MultipartRequestEntity
    implements RequestEntity
{

    public MultipartRequestEntity(Part apart[], HttpMethodParams httpmethodparams)
    {
        if(apart == null)
            throw new IllegalArgumentException("parts cannot be null");
        if(httpmethodparams == null)
        {
            throw new IllegalArgumentException("params cannot be null");
        } else
        {
            parts = apart;
            params = httpmethodparams;
            return;
        }
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

    private static byte[] generateMultipartBoundary()
    {
        Random random = new Random();
        byte abyte0[] = new byte[30 + random.nextInt(11)];
        int i = 0;
        do
        {
            if(i >= abyte0.length)
                return abyte0;
            abyte0[i] = MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)];
            i++;
        } while(true);
    }

    @Override
	public long getContentLength()
    {
        long l1 = Part.getLengthOfParts(parts, getMultipartBoundary());
        long l = l1;
_L2:
        return l;
        Exception exception;
        exception;
        log.error("An exception occurred while getting the length of the parts", exception);
        l = 0L;
        if(true) goto _L2; else goto _L1
_L1:
    }

    @Override
	public String getContentType()
    {
        StringBuffer stringbuffer = new StringBuffer("multipart/form-data");
        stringbuffer.append("; boundary=");
        stringbuffer.append(EncodingUtil.getAsciiString(getMultipartBoundary()));
        return stringbuffer.toString();
    }

    protected byte[] getMultipartBoundary()
    {
        if(multipartBoundary == null)
        {
            String s = (String)params.getParameter("http.method.multipart.boundary");
            if(s != null)
                multipartBoundary = EncodingUtil.getAsciiBytes(s);
            else
                multipartBoundary = generateMultipartBoundary();
        }
        return multipartBoundary;
    }

    @Override
	public boolean isRepeatable()
    {
        int i = 0;
_L6:
        if(i < parts.length) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        if(parts[i].isRepeatable())
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    @Override
	public void writeRequest(OutputStream outputstream)
        throws IOException
    {
        Part.sendParts(outputstream, parts, getMultipartBoundary());
    }

    private static byte MULTIPART_CHARS[] = EncodingUtil.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private static final String MULTIPART_FORM_CONTENT_TYPE = "multipart/form-data";
    static Class class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity;
    private static final Log log;
    private byte multipartBoundary[];
    private HttpMethodParams params;
    protected Part parts[];

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity");
            class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$methods$multipart$MultipartRequestEntity;
        }
        log = LogFactory.getLog(class1);
    }
}
