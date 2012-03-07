// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;

public class EncodingUtil
{

    private EncodingUtil()
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

    private static String doFormUrlEncode(NameValuePair anamevaluepair[], String s)
        throws UnsupportedEncodingException
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if(i >= anamevaluepair.length)
                return stringbuffer.toString();
            URLCodec urlcodec = new URLCodec();
            NameValuePair namevaluepair = anamevaluepair[i];
            if(namevaluepair.getName() != null)
            {
                if(i > 0)
                    stringbuffer.append("&");
                stringbuffer.append(urlcodec.encode(namevaluepair.getName(), s));
                stringbuffer.append("=");
                if(namevaluepair.getValue() != null)
                    stringbuffer.append(urlcodec.encode(namevaluepair.getValue(), s));
            }
            i++;
        } while(true);
    }

    public static String formUrlEncode(NameValuePair anamevaluepair[], String s)
    {
        String s3 = doFormUrlEncode(anamevaluepair, s);
        String s2 = s3;
_L2:
        return s2;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        LOG.error("Encoding not supported: " + s);
        String s1;
        try
        {
            s1 = doFormUrlEncode(anamevaluepair, "ISO-8859-1");
        }
        catch(UnsupportedEncodingException unsupportedencodingexception1)
        {
            throw new HttpClientError("Encoding not supported: ISO-8859-1");
        }
        s2 = s1;
        if(true) goto _L2; else goto _L1
_L1:
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
            throw new HttpClientError("HttpClient requires ASCII support");
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
            throw new HttpClientError("HttpClient requires ASCII support");
        }
        return s;
    }

    public static byte[] getBytes(String s, String s1)
    {
        if(s == null)
            throw new IllegalArgumentException("data may not be null");
        if(s1 == null || s1.length() == 0)
            throw new IllegalArgumentException("charset may not be null or empty");
        byte abyte1[] = s.getBytes(s1);
        byte abyte0[] = abyte1;
_L2:
        return abyte0;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(LOG.isWarnEnabled())
            LOG.warn("Unsupported encoding: " + s1 + ". System encoding used.");
        abyte0 = s.getBytes();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String getString(byte abyte0[], int i, int j, String s)
    {
        if(abyte0 == null)
            throw new IllegalArgumentException("Parameter may not be null");
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("charset may not be null or empty");
        String s1;
        try
        {
            s1 = new String(abyte0, i, j, s);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            if(LOG.isWarnEnabled())
                LOG.warn("Unsupported encoding: " + s + ". System encoding used");
            s1 = new String(abyte0, i, j);
        }
        return s1;
    }

    public static String getString(byte abyte0[], String s)
    {
        return getString(abyte0, 0, abyte0.length, s);
    }

    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$util$EncodingUtil;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$util$EncodingUtil == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.util.EncodingUtil");
            class$org$apache$commons$httpclient$util$EncodingUtil = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$util$EncodingUtil;
        }
        LOG = LogFactory.getLog(class1);
    }
}
