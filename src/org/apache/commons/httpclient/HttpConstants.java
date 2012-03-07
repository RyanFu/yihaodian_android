// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;

public class HttpConstants
{

    public HttpConstants()
    {
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

    public static byte[] getAsciiBytes(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Parameter may not be null");
        byte abyte0[];
        try
        {
            abyte0 = s.getBytes("US-ASCII");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("HttpClient requires ASCII support");
        }
        return abyte0;
    }

    public static String getAsciiString(byte abyte0[])
    {
        return getAsciiString(abyte0, 0, abyte0.length);
    }

    public static String getAsciiString(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("Parameter may not be null");
        String s;
        try
        {
            s = new String(abyte0, i, j, "US-ASCII");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("HttpClient requires ASCII support");
        }
        return s;
    }

    public static byte[] getBytes(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Parameter may not be null");
        byte abyte1[] = s.getBytes("US-ASCII");
        byte abyte0[] = abyte1;
_L2:
        return abyte0;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(LOG.isWarnEnabled())
            LOG.warn("Unsupported encoding: US-ASCII. System default encoding used");
        abyte0 = s.getBytes();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static byte[] getContentBytes(String s)
    {
        return getContentBytes(s, null);
    }

    public static byte[] getContentBytes(String s, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("Parameter may not be null");
        if(s1 == null || s1.equals(""))
            s1 = "ISO-8859-1";
        byte abyte2[] = s.getBytes(s1);
        byte abyte0[] = abyte2;
_L2:
        return abyte0;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(LOG.isWarnEnabled())
            LOG.warn("Unsupported encoding: " + s1 + ". HTTP default encoding used");
        byte abyte1[] = s.getBytes("ISO-8859-1");
        abyte0 = abyte1;
        continue; /* Loop/switch isn't completed */
        UnsupportedEncodingException unsupportedencodingexception1;
        unsupportedencodingexception1;
        if(LOG.isWarnEnabled())
            LOG.warn("Unsupported encoding: ISO-8859-1. System encoding used");
        abyte0 = s.getBytes();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getContentString(byte abyte0[])
    {
        return getContentString(abyte0, null);
    }

    public static String getContentString(byte abyte0[], int i, int j)
    {
        return getContentString(abyte0, i, j, null);
    }

    public static String getContentString(byte abyte0[], int i, int j, String s)
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("Parameter may not be null");
        if(s == null || s.equals(""))
            s = "ISO-8859-1";
        String s1;
        try
        {
            s1 = new String(abyte0, i, j, s);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            if(LOG.isWarnEnabled())
                LOG.warn("Unsupported encoding: " + s + ". Default HTTP encoding used");
            try
            {
                s1 = new String(abyte0, i, j, "ISO-8859-1");
            }
            catch(UnsupportedEncodingException unsupportedencodingexception1)
            {
                if(LOG.isWarnEnabled())
                    LOG.warn("Unsupported encoding: ISO-8859-1. System encoding used");
                s1 = new String(abyte0, i, j);
            }
        }
        return s1;
    }

    public static String getContentString(byte abyte0[], String s)
    {
        return getContentString(abyte0, 0, abyte0.length, s);
    }

    public static String getString(byte abyte0[])
    {
        return getString(abyte0, 0, abyte0.length);
    }

    public static String getString(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("Parameter may not be null");
        String s;
        try
        {
            s = new String(abyte0, i, j, "US-ASCII");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            if(LOG.isWarnEnabled())
                LOG.warn("Unsupported encoding: US-ASCII. System default encoding used");
            s = new String(abyte0, i, j);
        }
        return s;
    }

    public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    public static final String HTTP_ELEMENT_CHARSET = "US-ASCII";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpConstants;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$HttpConstants == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpConstants");
            class$org$apache$commons$httpclient$HttpConstants = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpConstants;
        }
        LOG = LogFactory.getLog(class1);
    }
}
