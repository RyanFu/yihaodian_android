// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import java.util.ArrayList;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            HttpException, Header, ProtocolException

public class HttpParser
{

    private HttpParser()
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

    public static Header[] parseHeaders(InputStream inputstream)
        throws IOException, HttpException
    {
        LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
        return parseHeaders(inputstream, "US-ASCII");
    }

    public static Header[] parseHeaders(InputStream inputstream, String s)
        throws IOException, HttpException
    {
        LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
        ArrayList arraylist = new ArrayList();
        String s1 = null;
        StringBuffer stringbuffer = null;
        do
        {
            String s2 = readLine(inputstream, s);
            if(s2 == null || s2.trim().length() < 1)
            {
                if(s1 != null)
                    arraylist.add(new Header(s1, stringbuffer.toString()));
                return (Header[])arraylist.toArray(new Header[arraylist.size()]);
            }
            if(s2.charAt(0) == ' ' || s2.charAt(0) == '\t')
            {
                if(stringbuffer != null)
                {
                    stringbuffer.append(' ');
                    stringbuffer.append(s2.trim());
                }
            } else
            {
                if(s1 != null)
                    arraylist.add(new Header(s1, stringbuffer.toString()));
                int i = s2.indexOf(":");
                if(i < 0)
                    throw new ProtocolException("Unable to parse header: " + s2);
                s1 = s2.substring(0, i).trim();
                stringbuffer = new StringBuffer(s2.substring(i + 1).trim());
            }
        } while(true);
    }

    public static String readLine(InputStream inputstream)
        throws IOException
    {
        LOG.trace("enter HttpParser.readLine(InputStream)");
        return readLine(inputstream, "US-ASCII");
    }

    public static String readLine(InputStream inputstream, String s)
        throws IOException
    {
        LOG.trace("enter HttpParser.readLine(InputStream, String)");
        byte abyte0[] = readRawLine(inputstream);
        String s1;
        if(abyte0 == null)
        {
            s1 = null;
        } else
        {
            int i = abyte0.length;
            int j = 0;
            if(i > 0 && abyte0[i - 1] == 10)
            {
                j = 0 + 1;
                if(i > 1 && abyte0[i - 2] == 13)
                    j++;
            }
            s1 = EncodingUtil.getString(abyte0, 0, i - j, s);
        }
        return s1;
    }

    public static byte[] readRawLine(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream;
        LOG.trace("enter HttpParser.readRawLine()");
        bytearrayoutputstream = new ByteArrayOutputStream();
_L3:
        int i = inputstream.read();
        if(i >= 0) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        if(bytearrayoutputstream.size() == 0)
            abyte0 = null;
        else
            abyte0 = bytearrayoutputstream.toByteArray();
        return abyte0;
_L2:
        bytearrayoutputstream.write(i);
        if(i != 10) goto _L3; else goto _L1
    }

    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpParser;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$HttpParser == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.HttpParser");
            class$org$apache$commons$httpclient$HttpParser = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$HttpParser;
        }
        LOG = LogFactory.getLog(class1);
    }
}
