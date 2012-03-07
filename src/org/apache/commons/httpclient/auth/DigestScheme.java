// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.auth:
//            RFC2617Scheme, MalformedChallengeException, AuthenticationException, InvalidCredentialsException

public class DigestScheme extends RFC2617Scheme
{

    public DigestScheme()
    {
        qopVariant = 0;
        complete = false;
        formatter = new ParameterFormatter();
    }

    public DigestScheme(String s)
        throws MalformedChallengeException
    {
        this();
        processChallenge(s);
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

    public static String createCnonce()
    {
        LOG.trace("enter DigestScheme.createCnonce()");
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new HttpClientError("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
        return encode(messagedigest.digest(EncodingUtil.getAsciiBytes(Long.toString(System.currentTimeMillis()))));
    }

    private String createDigest(String s, String s1)
        throws AuthenticationException
    {
        LOG.trace("enter DigestScheme.createDigest(String, String, Map)");
        String s2 = getParameter("uri");
        String s3 = getParameter("realm");
        String s4 = getParameter("nonce");
        String s5 = getParameter("qop");
        String s6 = getParameter("methodname");
        String s7 = getParameter("algorithm");
        if(s7 == null)
            s7 = "MD5";
        String s8 = getParameter("charset");
        if(s8 == null)
            s8 = "ISO-8859-1";
        if(qopVariant == 1)
        {
            LOG.warn("qop=auth-int is not supported");
            throw new AuthenticationException("Unsupported qop in HTTP Digest authentication");
        }
        MessageDigest messagedigest;
        StringBuffer stringbuffer;
        String s9;
        String s10;
        String s11;
        String s12;
        String s14;
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
        }
        catch(Exception exception)
        {
            throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
        stringbuffer = new StringBuffer(2 + (s.length() + s3.length() + s1.length()));
        stringbuffer.append(s);
        stringbuffer.append(':');
        stringbuffer.append(s3);
        stringbuffer.append(':');
        stringbuffer.append(s1);
        s9 = stringbuffer.toString();
        if(s7.equals("MD5-sess"))
        {
            String s15 = encode(messagedigest.digest(EncodingUtil.getBytes(s9, s8)));
            StringBuffer stringbuffer3 = new StringBuffer(2 + (s15.length() + s4.length() + cnonce.length()));
            stringbuffer3.append(s15);
            stringbuffer3.append(':');
            stringbuffer3.append(s4);
            stringbuffer3.append(':');
            stringbuffer3.append(cnonce);
            s9 = stringbuffer3.toString();
        } else
        if(!s7.equals("MD5"))
            LOG.warn("Unhandled algorithm " + s7 + " requested");
        s10 = encode(messagedigest.digest(EncodingUtil.getBytes(s9, s8)));
        s11 = null;
        if(qopVariant == 1)
            LOG.error("Unhandled qop auth-int");
        else
            s11 = s6 + ":" + s2;
        s12 = encode(messagedigest.digest(EncodingUtil.getAsciiBytes(s11)));
        if(qopVariant == 0)
        {
            LOG.debug("Using null qop method");
            StringBuffer stringbuffer2 = new StringBuffer(s10.length() + s4.length() + s12.length());
            stringbuffer2.append(s10);
            stringbuffer2.append(':');
            stringbuffer2.append(s4);
            stringbuffer2.append(':');
            stringbuffer2.append(s12);
            s14 = stringbuffer2.toString();
        } else
        {
            if(LOG.isDebugEnabled())
                LOG.debug("Using qop method " + s5);
            String s13 = getQopVariantString();
            StringBuffer stringbuffer1 = new StringBuffer(5 + (s10.length() + s4.length() + "00000001".length() + cnonce.length() + s13.length() + s12.length()));
            stringbuffer1.append(s10);
            stringbuffer1.append(':');
            stringbuffer1.append(s4);
            stringbuffer1.append(':');
            stringbuffer1.append("00000001");
            stringbuffer1.append(':');
            stringbuffer1.append(cnonce);
            stringbuffer1.append(':');
            stringbuffer1.append(s13);
            stringbuffer1.append(':');
            stringbuffer1.append(s12);
            s14 = stringbuffer1.toString();
        }
        return encode(messagedigest.digest(EncodingUtil.getAsciiBytes(s14)));
    }

    private String createDigestHeader(String s, String s1)
        throws AuthenticationException
    {
        LOG.trace("enter DigestScheme.createDigestHeader(String, Map, String)");
        String s2 = getParameter("uri");
        String s3 = getParameter("realm");
        String s4 = getParameter("nonce");
        String s5 = getParameter("opaque");
        String s6 = getParameter("algorithm");
        ArrayList arraylist = new ArrayList(20);
        arraylist.add(new NameValuePair("username", s));
        arraylist.add(new NameValuePair("realm", s3));
        arraylist.add(new NameValuePair("nonce", s4));
        arraylist.add(new NameValuePair("uri", s2));
        arraylist.add(new NameValuePair("response", s1));
        if(qopVariant != 0)
        {
            arraylist.add(new NameValuePair("qop", getQopVariantString()));
            arraylist.add(new NameValuePair("nc", "00000001"));
            arraylist.add(new NameValuePair("cnonce", cnonce));
        }
        if(s6 != null)
            arraylist.add(new NameValuePair("algorithm", s6));
        if(s5 != null)
            arraylist.add(new NameValuePair("opaque", s5));
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                return stringbuffer.toString();
            NameValuePair namevaluepair = (NameValuePair)arraylist.get(i);
            if(i > 0)
                stringbuffer.append(", ");
            boolean flag;
            ParameterFormatter parameterformatter;
            boolean flag1;
            if("nc".equals(namevaluepair.getName()) || "qop".equals(namevaluepair.getName()))
                flag = true;
            else
                flag = false;
            parameterformatter = formatter;
            if(!flag)
                flag1 = true;
            else
                flag1 = false;
            parameterformatter.setAlwaysUseQuotes(flag1);
            formatter.format(stringbuffer, namevaluepair);
            i++;
        } while(true);
    }

    private static String encode(byte abyte0[])
    {
        LOG.trace("enter DigestScheme.encode(byte[])");
        if(abyte0.length == 16) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        char ac[] = new char[32];
        int i = 0;
        do
        {
label0:
            {
                if(i < 16)
                    break label0;
                s = new String(ac);
            }
            if(true)
                continue;
            int j = 0xf & abyte0[i];
            int k = (0xf0 & abyte0[i]) >> 4;
            ac[i * 2] = HEXADECIMAL[k];
            ac[1 + i * 2] = HEXADECIMAL[j];
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String getQopVariantString()
    {
        String s;
        if(qopVariant == 1)
            s = "auth-int";
        else
            s = "auth";
        return s;
    }

    public String authenticate(Credentials credentials, String s, String s1)
        throws AuthenticationException
    {
        LOG.trace("enter DigestScheme.authenticate(Credentials, String, String)");
        UsernamePasswordCredentials usernamepasswordcredentials;
        String s2;
        try
        {
            usernamepasswordcredentials = (UsernamePasswordCredentials)credentials;
        }
        catch(ClassCastException classcastexception)
        {
            throw new InvalidCredentialsException("Credentials cannot be used for digest authentication: " + credentials.getClass().getName());
        }
        getParameters().put("methodname", s);
        getParameters().put("uri", s1);
        s2 = createDigest(usernamepasswordcredentials.getUserName(), usernamepasswordcredentials.getPassword());
        return "Digest " + createDigestHeader(usernamepasswordcredentials.getUserName(), s2);
    }

    public String authenticate(Credentials credentials, HttpMethod httpmethod)
        throws AuthenticationException
    {
        LOG.trace("enter DigestScheme.authenticate(Credentials, HttpMethod)");
        UsernamePasswordCredentials usernamepasswordcredentials;
        String s;
        try
        {
            usernamepasswordcredentials = (UsernamePasswordCredentials)credentials;
        }
        catch(ClassCastException classcastexception)
        {
            throw new InvalidCredentialsException("Credentials cannot be used for digest authentication: " + credentials.getClass().getName());
        }
        getParameters().put("methodname", httpmethod.getName());
        getParameters().put("uri", httpmethod.getPath());
        if(getParameter("charset") == null)
            getParameters().put("charset", httpmethod.getParams().getCredentialCharset());
        s = createDigest(usernamepasswordcredentials.getUserName(), usernamepasswordcredentials.getPassword());
        return "Digest " + createDigestHeader(usernamepasswordcredentials.getUserName(), s);
    }

    public String getID()
    {
        String s = getRealm();
        String s1 = getParameter("nonce");
        if(s1 != null)
            s = s + "-" + s1;
        return s;
    }

    public String getSchemeName()
    {
        return "digest";
    }

    public boolean isComplete()
    {
        boolean flag;
        if("true".equalsIgnoreCase(getParameter("stale")))
            flag = false;
        else
            flag = complete;
        return flag;
    }

    public boolean isConnectionBased()
    {
        return false;
    }

    public void processChallenge(String s)
        throws MalformedChallengeException
    {
        boolean flag;
        String s1;
        super.processChallenge(s);
        if(getParameter("realm") == null)
            throw new MalformedChallengeException("missing realm in challange");
        if(getParameter("nonce") == null)
            throw new MalformedChallengeException("missing nonce in challange");
        flag = false;
        s1 = getParameter("qop");
        if(s1 == null) goto _L2; else goto _L1
_L1:
        StringTokenizer stringtokenizer = new StringTokenizer(s1, ",");
_L4:
        String s2;
        if(stringtokenizer.hasMoreTokens())
        {
label0:
            {
                s2 = stringtokenizer.nextToken().trim();
                if(!s2.equals("auth"))
                    break label0;
                qopVariant = 2;
            }
        }
_L2:
        if(flag && qopVariant == 0)
        {
            throw new MalformedChallengeException("None of the qop methods is supported");
        } else
        {
            cnonce = createCnonce();
            complete = true;
            return;
        }
        if(s2.equals("auth-int"))
        {
            qopVariant = 1;
        } else
        {
            flag = true;
            LOG.warn("Unsupported qop detected: " + s2);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final char HEXADECIMAL[];
    private static final Log LOG;
    private static final String NC = "00000001";
    private static final int QOP_AUTH = 2;
    private static final int QOP_AUTH_INT = 1;
    private static final int QOP_MISSING;
    static Class class$org$apache$commons$httpclient$auth$DigestScheme;
    private String cnonce;
    private boolean complete;
    private final ParameterFormatter formatter;
    private int qopVariant;

    static 
    {
        Class class1;
        char ac[];
        if(class$org$apache$commons$httpclient$auth$DigestScheme == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.auth.DigestScheme");
            class$org$apache$commons$httpclient$auth$DigestScheme = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$auth$DigestScheme;
        }
        LOG = LogFactory.getLog(class1);
        ac = new char[16];
        ac[0] = '0';
        ac[1] = '1';
        ac[2] = '2';
        ac[3] = '3';
        ac[4] = '4';
        ac[5] = '5';
        ac[6] = '6';
        ac[7] = '7';
        ac[8] = '8';
        ac[9] = '9';
        ac[10] = 'a';
        ac[11] = 'b';
        ac[12] = 'c';
        ac[13] = 'd';
        ac[14] = 'e';
        ac[15] = 'f';
        HEXADECIMAL = ac;
    }
}
