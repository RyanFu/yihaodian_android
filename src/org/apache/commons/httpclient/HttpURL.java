// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.URIUtil;

// Referenced classes of package org.apache.commons.httpclient:
//            URI, URIException

public class HttpURL extends URI
{

    protected HttpURL()
    {
    }

    public HttpURL(String s)
        throws URIException
    {
        parseUriReference(s, false);
        checkValid();
    }

    public HttpURL(String s, int i, String s1)
        throws URIException
    {
        this(null, null, s, i, s1, null, null);
    }

    public HttpURL(String s, int i, String s1, String s2)
        throws URIException
    {
        this(null, null, s, i, s1, s2, null);
    }

    public HttpURL(String s, String s1)
        throws URIException
    {
        super.protocolCharset = s1;
        parseUriReference(s, false);
        checkValid();
    }

    public HttpURL(String s, String s1, int i, String s2)
        throws URIException
    {
        this(s, s1, i, s2, null, null);
    }

    public HttpURL(String s, String s1, int i, String s2, String s3)
        throws URIException
    {
        this(s, s1, i, s2, s3, null);
    }

    public HttpURL(String s, String s1, int i, String s2, String s3, String s4)
        throws URIException
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(s != null || s1 != null || i != -1)
        {
            super._scheme = DEFAULT_SCHEME;
            stringbuffer.append(_default_scheme);
            stringbuffer.append("://");
            if(s != null)
            {
                stringbuffer.append(s);
                stringbuffer.append('@');
            }
            if(s1 != null)
            {
                stringbuffer.append(URIUtil.encode(s1, URI.allowed_host));
                if(i != -1 || i != 80)
                {
                    stringbuffer.append(':');
                    stringbuffer.append(i);
                }
            }
        }
        if(s2 != null)
        {
            if(URI.scheme != null && !s2.startsWith("/"))
                throw new URIException(1, "abs_path requested");
            stringbuffer.append(URIUtil.encode(s2, URI.allowed_abs_path));
        }
        if(s3 != null)
        {
            stringbuffer.append('?');
            stringbuffer.append(URIUtil.encode(s3, URI.allowed_query));
        }
        if(s4 != null)
        {
            stringbuffer.append('#');
            stringbuffer.append(URIUtil.encode(s4, URI.allowed_fragment));
        }
        parseUriReference(stringbuffer.toString(), true);
        checkValid();
    }

    public HttpURL(String s, String s1, String s2)
        throws URIException
    {
        this(s, s1, s2, -1, null, null, null);
    }

    public HttpURL(String s, String s1, String s2, int i)
        throws URIException
    {
        this(s, s1, s2, i, null, null, null);
    }

    public HttpURL(String s, String s1, String s2, int i, String s3)
        throws URIException
    {
        this(s, s1, s2, i, s3, null, null);
    }

    public HttpURL(String s, String s1, String s2, int i, String s3, String s4)
        throws URIException
    {
        this(s, s1, s2, i, s3, s4, null);
    }

    public HttpURL(String s, String s1, String s2, int i, String s3, String s4, String s5)
        throws URIException
    {
        this(toUserinfo(s, s1), s2, i, s3, s4, s5);
    }

    public HttpURL(String s, String s1, String s2, String s3)
        throws URIException
    {
        this(null, null, s, -1, s1, s2, s3);
    }

    public HttpURL(String s, String s1, String s2, String s3, String s4)
        throws URIException
    {
        this(s, s1, -1, s2, s3, s4);
    }

    public HttpURL(HttpURL httpurl, String s)
        throws URIException
    {
        this(httpurl, new HttpURL(s));
    }

    public HttpURL(HttpURL httpurl, HttpURL httpurl1)
        throws URIException
    {
        super(httpurl, httpurl1);
        checkValid();
    }

    public HttpURL(char ac[])
        throws URIException, NullPointerException
    {
        parseUriReference(new String(ac), true);
        checkValid();
    }

    public HttpURL(char ac[], String s)
        throws URIException, NullPointerException
    {
        super.protocolCharset = s;
        parseUriReference(new String(ac), true);
        checkValid();
    }

    protected static String toUserinfo(String s, String s1)
        throws URIException
    {
        String s2;
        if(s == null)
        {
            s2 = null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer(20);
            stringbuffer.append(URIUtil.encode(s, URI.allowed_within_userinfo));
            if(s1 == null)
            {
                s2 = stringbuffer.toString();
            } else
            {
                stringbuffer.append(':');
                stringbuffer.append(URIUtil.encode(s1, URI.allowed_within_userinfo));
                s2 = stringbuffer.toString();
            }
        }
        return s2;
    }

    protected void checkValid()
        throws URIException
    {
        if(!equals(super._scheme, DEFAULT_SCHEME) && super._scheme != null)
            throw new URIException(1, "wrong class use");
        else
            return;
    }

    public String getEscapedPassword()
    {
        char ac[] = getRawPassword();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedUser()
    {
        char ac[] = getRawUser();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getPassword()
        throws URIException
    {
        char ac[] = getRawPassword();
        String s;
        if(ac == null)
            s = null;
        else
            s = URI.decode(ac, getProtocolCharset());
        return s;
    }

    @Override
	public int getPort()
    {
        int i;
        if(super._port == -1)
            i = 80;
        else
            i = super._port;
        return i;
    }

    @Override
	public char[] getRawAboveHierPath()
        throws URIException
    {
        char ac[] = getRawCurrentHierPath();
        char ac1[];
        if(ac == null || ac.length == 0)
            ac1 = URI.rootPath;
        else
            ac1 = getRawCurrentHierPath(ac);
        return ac1;
    }

    @Override
	public char[] getRawCurrentHierPath()
        throws URIException
    {
        char ac[];
        if(super._path == null || super._path.length == 0)
            ac = URI.rootPath;
        else
            ac = super.getRawCurrentHierPath(super._path);
        return ac;
    }

    public char[] getRawPassword()
    {
        int i = indexFirstOf(super._userinfo, ':');
        char ac1[];
        if(i == -1)
        {
            ac1 = null;
        } else
        {
            int j = super._userinfo.length - i - 1;
            char ac[] = new char[j];
            System.arraycopy(super._userinfo, i + 1, ac, 0, j);
            ac1 = ac;
        }
        return ac1;
    }

    @Override
	public char[] getRawPath()
    {
        char ac[] = super.getRawPath();
        char ac1[];
        if(ac == null || ac.length == 0)
            ac1 = URI.rootPath;
        else
            ac1 = ac;
        return ac1;
    }

    @Override
	public char[] getRawScheme()
    {
        char ac[];
        if(super._scheme == null)
            ac = null;
        else
            ac = DEFAULT_SCHEME;
        return ac;
    }

    public char[] getRawUser()
    {
        char ac[];
        if(super._userinfo == null || super._userinfo.length == 0)
        {
            ac = null;
        } else
        {
            int i = indexFirstOf(super._userinfo, ':');
            if(i == -1)
            {
                ac = super._userinfo;
            } else
            {
                char ac1[] = new char[i];
                System.arraycopy(super._userinfo, 0, ac1, 0, i);
                ac = ac1;
            }
        }
        return ac;
    }

    @Override
	public String getScheme()
    {
        String s;
        if(super._scheme == null)
            s = null;
        else
            s = new String(DEFAULT_SCHEME);
        return s;
    }

    public String getUser()
        throws URIException
    {
        char ac[] = getRawUser();
        String s;
        if(ac == null)
            s = null;
        else
            s = URI.decode(ac, getProtocolCharset());
        return s;
    }

    public void setEscapedPassword(String s)
        throws URIException
    {
        char ac[];
        if(s == null)
            ac = null;
        else
            ac = s.toCharArray();
        setRawPassword(ac);
    }

    public void setEscapedUser(String s)
        throws URIException, NullPointerException
    {
        setRawUser(s.toCharArray());
    }

    public void setEscapedUserinfo(String s, String s1)
        throws URIException, NullPointerException
    {
        char ac[] = s.toCharArray();
        char ac1[];
        if(s1 == null)
            ac1 = null;
        else
            ac1 = s1.toCharArray();
        setRawUserinfo(ac, ac1);
    }

    public void setPassword(String s)
        throws URIException
    {
        char ac[];
        if(s == null)
            ac = null;
        else
            ac = URI.encode(s, URI.allowed_within_userinfo, getProtocolCharset());
        setRawPassword(ac);
    }

    public void setQuery(String s, String s1)
        throws URIException, NullPointerException
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s2 = getProtocolCharset();
        stringbuffer.append(URI.encode(s, URI.allowed_within_query, s2));
        stringbuffer.append('=');
        stringbuffer.append(URI.encode(s1, URI.allowed_within_query, s2));
        super._query = stringbuffer.toString().toCharArray();
        setURI();
    }

    public void setQuery(String as[], String as1[])
        throws URIException, NullPointerException
    {
        int i = as.length;
        if(i != as1.length)
            throw new URIException("wrong array size of query");
        StringBuffer stringbuffer = new StringBuffer();
        String s = getProtocolCharset();
        int j = 0;
        do
        {
            if(j >= i)
            {
                super._query = stringbuffer.toString().toCharArray();
                setURI();
                return;
            }
            stringbuffer.append(URI.encode(as[j], URI.allowed_within_query, s));
            stringbuffer.append('=');
            stringbuffer.append(URI.encode(as1[j], URI.allowed_within_query, s));
            if(j + 1 < i)
                stringbuffer.append('&');
            j++;
        } while(true);
    }

    public void setRawPassword(char ac[])
        throws URIException
    {
        if(ac != null && !validate(ac, URI.within_userinfo))
            throw new URIException(3, "escaped password not valid");
        if(getRawUser() == null || getRawUser().length == 0)
            throw new URIException(1, "username required");
        String s = new String(getRawUser());
        String s1 = new String(ac);
        StringBuffer stringbuffer = (new StringBuffer()).append(s);
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        if(s1 == null)
            s2 = "";
        else
            s2 = ":" + s1;
        s3 = stringbuffer.append(s2).toString();
        s4 = new String(getRawHost());
        if(super._port == -1)
            s5 = s4;
        else
            s5 = s4 + ":" + super._port;
        s6 = s3 + "@" + s5;
        super._userinfo = s3.toCharArray();
        super._authority = s6.toCharArray();
        setURI();
    }

    public void setRawUser(char ac[])
        throws URIException
    {
        if(ac == null || ac.length == 0)
            throw new URIException(1, "user required");
        if(!validate(ac, URI.within_userinfo))
            throw new URIException(3, "escaped user not valid");
        String s = new String(ac);
        String s1 = new String(getRawPassword());
        StringBuffer stringbuffer = (new StringBuffer()).append(s);
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        if(s1 == null)
            s2 = "";
        else
            s2 = ":" + s1;
        s3 = stringbuffer.append(s2).toString();
        s4 = new String(getRawHost());
        if(super._port == -1)
            s5 = s4;
        else
            s5 = s4 + ":" + super._port;
        s6 = s3 + "@" + s5;
        super._userinfo = s3.toCharArray();
        super._authority = s6.toCharArray();
        setURI();
    }

    public void setRawUserinfo(char ac[], char ac1[])
        throws URIException
    {
        if(ac == null || ac.length == 0)
            throw new URIException(1, "user required");
        if(!validate(ac, URI.within_userinfo) || ac1 != null && !validate(ac1, URI.within_userinfo))
            throw new URIException(3, "escaped userinfo not valid");
        String s = new String(ac);
        String s1;
        StringBuffer stringbuffer;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        if(ac1 == null)
            s1 = null;
        else
            s1 = new String(ac1);
        stringbuffer = (new StringBuffer()).append(s);
        if(s1 == null)
            s2 = "";
        else
            s2 = ":" + s1;
        s3 = stringbuffer.append(s2).toString();
        s4 = new String(getRawHost());
        if(super._port == -1)
            s5 = s4;
        else
            s5 = s4 + ":" + super._port;
        s6 = s3 + "@" + s5;
        super._userinfo = s3.toCharArray();
        super._authority = s6.toCharArray();
        setURI();
    }

    public void setUser(String s)
        throws URIException, NullPointerException
    {
        setRawUser(URI.encode(s, URI.allowed_within_userinfo, getProtocolCharset()));
    }

    public void setUserinfo(String s, String s1)
        throws URIException, NullPointerException
    {
        String s2 = getProtocolCharset();
        char ac[] = URI.encode(s, URI.within_userinfo, s2);
        char ac1[];
        if(s1 == null)
            ac1 = null;
        else
            ac1 = URI.encode(s1, URI.within_userinfo, s2);
        setRawUserinfo(ac, ac1);
    }

    public static final int DEFAULT_PORT = 80;
    public static final char DEFAULT_SCHEME[];
    public static final int _default_port = 80;
    public static final char _default_scheme[];
    static final long serialVersionUID = 0x9ca990a4050b271dL;

    static 
    {
        char ac[] = new char[4];
        ac[0] = 'h';
        ac[1] = 't';
        ac[2] = 't';
        ac[3] = 'p';
        DEFAULT_SCHEME = ac;
        _default_scheme = DEFAULT_SCHEME;
    }
}
