// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.*;
import java.util.*;
import org.apache.commons.httpclient.util.EncodingUtil;

// Referenced classes of package org.apache.commons.httpclient:
//            URIException

public class URI
    implements Cloneable, Comparable, Serializable
{
    public static class LocaleToCharsetMap
    {

        public static String getCharset(Locale locale)
        {
            String s = (String)LOCALE_TO_CHARSET_MAP.get(locale.toString());
            String s1;
            if(s != null)
                s1 = s;
            else
                s1 = (String)LOCALE_TO_CHARSET_MAP.get(locale.getLanguage());
            return s1;
        }

        private static final Hashtable LOCALE_TO_CHARSET_MAP;

        static 
        {
            LOCALE_TO_CHARSET_MAP = new Hashtable();
            LOCALE_TO_CHARSET_MAP.put("ar", "ISO-8859-6");
            LOCALE_TO_CHARSET_MAP.put("be", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("bg", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("ca", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("cs", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("da", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("de", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("el", "ISO-8859-7");
            LOCALE_TO_CHARSET_MAP.put("en", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("es", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("et", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("fi", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("fr", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("hr", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("hu", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("is", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("it", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("iw", "ISO-8859-8");
            LOCALE_TO_CHARSET_MAP.put("ja", "Shift_JIS");
            LOCALE_TO_CHARSET_MAP.put("ko", "EUC-KR");
            LOCALE_TO_CHARSET_MAP.put("lt", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("lv", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("mk", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("nl", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("no", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("pl", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("pt", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("ro", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("ru", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sh", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sk", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sl", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sq", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sr", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sv", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("tr", "ISO-8859-9");
            LOCALE_TO_CHARSET_MAP.put("uk", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("zh", "GB2312");
            LOCALE_TO_CHARSET_MAP.put("zh_TW", "Big5");
        }

        public LocaleToCharsetMap()
        {
        }
    }

    public static class DefaultCharsetChanged extends RuntimeException
    {

        public String getReason()
        {
            return reason;
        }

        public int getReasonCode()
        {
            return reasonCode;
        }

        public static final int DOCUMENT_CHARSET = 2;
        public static final int PROTOCOL_CHARSET = 1;
        public static final int UNKNOWN;
        private String reason;
        private int reasonCode;

        public DefaultCharsetChanged(int i, String s)
        {
            super(s);
            reason = s;
            reasonCode = i;
        }
    }


    protected URI()
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
    }

    public URI(String s)
        throws URIException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        parseUriReference(s, false);
    }

    public URI(String s, String s1)
        throws URIException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        protocolCharset = s1;
        parseUriReference(s, false);
    }

    public URI(String s, String s1, String s2)
        throws URIException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        if(s == null)
            throw new URIException(1, "scheme required");
        char ac[] = s.toLowerCase().toCharArray();
        if(validate(ac, scheme))
        {
            _scheme = ac;
            _opaque = encode(s1, allowed_opaque_part, getProtocolCharset());
            _is_opaque_part = true;
            _fragment = s2.toCharArray();
            setURI();
            return;
        } else
        {
            throw new URIException(1, "incorrect scheme");
        }
    }

    public URI(String s, String s1, String s2, int i)
        throws URIException
    {
        this(s, s1, s2, i, null, null, null);
    }

    public URI(String s, String s1, String s2, int i, String s3)
        throws URIException
    {
        this(s, s1, s2, i, s3, null, null);
    }

    public URI(String s, String s1, String s2, int i, String s3, String s4)
        throws URIException
    {
        this(s, s1, s2, i, s3, s4, null);
    }

    public URI(String s, String s1, String s2, int i, String s3, String s4, String s5)
        throws URIException
    {
        String s8;
        if(s2 == null)
        {
            s8 = null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            String s6;
            StringBuffer stringbuffer1;
            String s7;
            if(s1 != null)
                s6 = s1 + '@';
            else
                s6 = "";
            stringbuffer1 = stringbuffer.append(s6).append(s2);
            if(i != -1)
                s7 = ":" + i;
            else
                s7 = "";
            s8 = stringbuffer1.append(s7).toString();
        }
        this(s, s8, s3, s4, s5);
    }

    public URI(String s, String s1, String s2, String s3)
        throws URIException
    {
        this(s, s1, s2, ((String) (null)), s3);
    }

    public URI(String s, String s1, String s2, String s3, String s4)
        throws URIException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        StringBuffer stringbuffer = new StringBuffer();
        if(s != null)
        {
            stringbuffer.append(s);
            stringbuffer.append(':');
        }
        if(s1 != null)
        {
            stringbuffer.append("//");
            stringbuffer.append(s1);
        }
        if(s2 != null)
        {
            if((s != null || s1 != null) && !s2.startsWith("/"))
                throw new URIException(1, "abs_path requested");
            stringbuffer.append(s2);
        }
        if(s3 != null)
        {
            stringbuffer.append('?');
            stringbuffer.append(s3);
        }
        if(s4 != null)
        {
            stringbuffer.append('#');
            stringbuffer.append(s4);
        }
        parseUriReference(stringbuffer.toString(), false);
    }

    public URI(String s, boolean flag)
        throws URIException, NullPointerException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        parseUriReference(s, flag);
    }

    public URI(String s, boolean flag, String s1)
        throws URIException, NullPointerException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        protocolCharset = s1;
        parseUriReference(s, flag);
    }

    public URI(URI uri, String s)
        throws URIException
    {
        this(uri, new URI(s));
    }

    public URI(URI uri, String s, boolean flag)
        throws URIException
    {
        this(uri, new URI(s, flag));
    }

    public URI(URI uri, URI uri1)
        throws URIException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        if(uri._scheme == null)
            throw new URIException(1, "base URI required");
        if(uri._scheme != null)
        {
            _scheme = uri._scheme;
            _authority = uri._authority;
        }
        if(uri._is_opaque_part || uri1._is_opaque_part)
        {
            _scheme = uri._scheme;
            boolean flag;
            if(uri._is_opaque_part || uri1._is_opaque_part)
                flag = true;
            else
                flag = false;
            _is_opaque_part = flag;
            _opaque = uri1._opaque;
            _fragment = uri1._fragment;
            setURI();
        } else
        {
            if(uri1._scheme != null)
            {
                _scheme = uri1._scheme;
                _is_net_path = uri1._is_net_path;
                _authority = uri1._authority;
                if(uri1._is_server)
                {
                    _is_server = uri1._is_server;
                    _userinfo = uri1._userinfo;
                    _host = uri1._host;
                    _port = uri1._port;
                } else
                if(uri1._is_reg_name)
                    _is_reg_name = uri1._is_reg_name;
                _is_abs_path = uri1._is_abs_path;
                _is_rel_path = uri1._is_rel_path;
                _path = uri1._path;
            } else
            if(uri._authority != null && uri1._scheme == null)
            {
                _is_net_path = uri._is_net_path;
                _authority = uri._authority;
                if(uri._is_server)
                {
                    _is_server = uri._is_server;
                    _userinfo = uri._userinfo;
                    _host = uri._host;
                    _port = uri._port;
                } else
                if(uri._is_reg_name)
                    _is_reg_name = uri._is_reg_name;
            }
            if(uri1._authority != null)
            {
                _is_net_path = uri1._is_net_path;
                _authority = uri1._authority;
                if(uri1._is_server)
                {
                    _is_server = uri1._is_server;
                    _userinfo = uri1._userinfo;
                    _host = uri1._host;
                    _port = uri1._port;
                } else
                if(uri1._is_reg_name)
                    _is_reg_name = uri1._is_reg_name;
                _is_abs_path = uri1._is_abs_path;
                _is_rel_path = uri1._is_rel_path;
                _path = uri1._path;
            }
            if(uri1._scheme == null && uri1._authority == null)
                if((uri1._path == null || uri1._path.length == 0) && uri1._query == null)
                {
                    _path = uri._path;
                    _query = uri._query;
                } else
                {
                    _path = resolvePath(uri._path, uri1._path);
                }
            if(uri1._query != null)
                _query = uri1._query;
            if(uri1._fragment != null)
                _fragment = uri1._fragment;
            setURI();
            parseUriReference(new String(_uri), true);
        }
    }

    public URI(char ac[])
        throws URIException, NullPointerException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        parseUriReference(new String(ac), true);
    }

    public URI(char ac[], String s)
        throws URIException, NullPointerException
    {
        hash = 0;
        _uri = null;
        protocolCharset = null;
        _scheme = null;
        _opaque = null;
        _authority = null;
        _userinfo = null;
        _host = null;
        _port = -1;
        _path = null;
        _query = null;
        _fragment = null;
        protocolCharset = s;
        parseUriReference(new String(ac), true);
    }

    protected static String decode(String s, String s1)
        throws URIException
    {
        if(s == null)
            throw new IllegalArgumentException("Component array of chars may not be null");
        byte abyte0[];
        try
        {
            abyte0 = URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(s));
        }
        catch(DecoderException decoderexception)
        {
            throw new URIException(decoderexception.getMessage());
        }
        return EncodingUtil.getString(abyte0, s1);
    }

    protected static String decode(char ac[], String s)
        throws URIException
    {
        if(ac == null)
            throw new IllegalArgumentException("Component array of chars may not be null");
        else
            return decode(new String(ac), s);
    }

    protected static char[] encode(String s, BitSet bitset, String s1)
        throws URIException
    {
        if(s == null)
            throw new IllegalArgumentException("Original string may not be null");
        if(bitset == null)
            throw new IllegalArgumentException("Allowed bitset may not be null");
        else
            return EncodingUtil.getAsciiString(URLCodec.encodeUrl(bitset, EncodingUtil.getBytes(s, s1))).toCharArray();
    }

    public static String getDefaultDocumentCharset()
    {
        return defaultDocumentCharset;
    }

    public static String getDefaultDocumentCharsetByLocale()
    {
        return defaultDocumentCharsetByLocale;
    }

    public static String getDefaultDocumentCharsetByPlatform()
    {
        return defaultDocumentCharsetByPlatform;
    }

    public static String getDefaultProtocolCharset()
    {
        return defaultProtocolCharset;
    }

    public static void setDefaultDocumentCharset(String s)
        throws DefaultCharsetChanged
    {
        defaultDocumentCharset = s;
        throw new DefaultCharsetChanged(2, "the default document charset changed");
    }

    public static void setDefaultProtocolCharset(String s)
        throws DefaultCharsetChanged
    {
        defaultProtocolCharset = s;
        throw new DefaultCharsetChanged(1, "the default protocol charset changed");
    }

    /**
     * @deprecated Method clone is deprecated
     */

    public Object clone()
    {
        this;
        JVM INSTR monitorenter ;
        URI uri;
        uri = new URI();
        uri._uri = _uri;
        uri._scheme = _scheme;
        uri._opaque = _opaque;
        uri._authority = _authority;
        uri._userinfo = _userinfo;
        uri._host = _host;
        uri._port = _port;
        uri._path = _path;
        uri._query = _query;
        uri._fragment = _fragment;
        uri.protocolCharset = protocolCharset;
        uri._is_hier_part = _is_hier_part;
        uri._is_opaque_part = _is_opaque_part;
        uri._is_net_path = _is_net_path;
        uri._is_abs_path = _is_abs_path;
        uri._is_rel_path = _is_rel_path;
        uri._is_reg_name = _is_reg_name;
        uri._is_server = _is_server;
        uri._is_hostname = _is_hostname;
        uri._is_IPv4address = _is_IPv4address;
        uri._is_IPv6reference = _is_IPv6reference;
        this;
        JVM INSTR monitorexit ;
        return uri;
        Exception exception;
        exception;
        throw exception;
    }

    public int compareTo(Object obj)
        throws ClassCastException
    {
        URI uri = (URI)obj;
        int i;
        if(!equals(_authority, uri.getRawAuthority()))
            i = -1;
        else
            i = toString().compareTo(uri.toString());
        return i;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == this)
            flag = true;
        else
        if(!(obj instanceof URI))
        {
            flag = false;
        } else
        {
            URI uri = (URI)obj;
            if(!equals(_scheme, uri._scheme))
                flag = false;
            else
            if(!equals(_opaque, uri._opaque))
                flag = false;
            else
            if(!equals(_authority, uri._authority))
                flag = false;
            else
            if(!equals(_path, uri._path))
                flag = false;
            else
            if(!equals(_query, uri._query))
                flag = false;
            else
            if(!equals(_fragment, uri._fragment))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    protected boolean equals(char ac[], char ac1[])
    {
        if(ac != null || ac1 != null) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        int i;
        if(ac == null || ac1 == null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(ac.length != ac1.length)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        i = 0;
_L5:
        if(i >= ac.length)
        {
            flag = true;
        } else
        {
label0:
            {
                if(ac[i] == ac1[i])
                    break label0;
                flag = false;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        i++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public String getAboveHierPath()
        throws URIException
    {
        char ac[] = getRawAboveHierPath();
        String s;
        if(ac == null)
            s = null;
        else
            s = decode(ac, getProtocolCharset());
        return s;
    }

    public String getAuthority()
        throws URIException
    {
        String s;
        if(_authority == null)
            s = null;
        else
            s = decode(_authority, getProtocolCharset());
        return s;
    }

    public String getCurrentHierPath()
        throws URIException
    {
        char ac[] = getRawCurrentHierPath();
        String s;
        if(ac == null)
            s = null;
        else
            s = decode(ac, getProtocolCharset());
        return s;
    }

    public String getEscapedAboveHierPath()
        throws URIException
    {
        char ac[] = getRawAboveHierPath();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedAuthority()
    {
        String s;
        if(_authority == null)
            s = null;
        else
            s = new String(_authority);
        return s;
    }

    public String getEscapedCurrentHierPath()
        throws URIException
    {
        char ac[] = getRawCurrentHierPath();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedFragment()
    {
        String s;
        if(_fragment == null)
            s = null;
        else
            s = new String(_fragment);
        return s;
    }

    public String getEscapedName()
    {
        char ac[] = getRawName();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedPath()
    {
        char ac[] = getRawPath();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedPathQuery()
    {
        char ac[] = getRawPathQuery();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedQuery()
    {
        String s;
        if(_query == null)
            s = null;
        else
            s = new String(_query);
        return s;
    }

    public String getEscapedURI()
    {
        String s;
        if(_uri == null)
            s = null;
        else
            s = new String(_uri);
        return s;
    }

    public String getEscapedURIReference()
    {
        char ac[] = getRawURIReference();
        String s;
        if(ac == null)
            s = null;
        else
            s = new String(ac);
        return s;
    }

    public String getEscapedUserinfo()
    {
        String s;
        if(_userinfo == null)
            s = null;
        else
            s = new String(_userinfo);
        return s;
    }

    public String getFragment()
        throws URIException
    {
        String s;
        if(_fragment == null)
            s = null;
        else
            s = decode(_fragment, getProtocolCharset());
        return s;
    }

    public String getHost()
        throws URIException
    {
        String s;
        if(_host != null)
            s = decode(_host, getProtocolCharset());
        else
            s = null;
        return s;
    }

    public String getName()
        throws URIException
    {
        String s;
        if(getRawName() == null)
            s = null;
        else
            s = decode(getRawName(), getProtocolCharset());
        return s;
    }

    public String getPath()
        throws URIException
    {
        char ac[] = getRawPath();
        String s;
        if(ac == null)
            s = null;
        else
            s = decode(ac, getProtocolCharset());
        return s;
    }

    public String getPathQuery()
        throws URIException
    {
        char ac[] = getRawPathQuery();
        String s;
        if(ac == null)
            s = null;
        else
            s = decode(ac, getProtocolCharset());
        return s;
    }

    public int getPort()
    {
        return _port;
    }

    public String getProtocolCharset()
    {
        String s;
        if(protocolCharset != null)
            s = protocolCharset;
        else
            s = defaultProtocolCharset;
        return s;
    }

    public String getQuery()
        throws URIException
    {
        String s;
        if(_query == null)
            s = null;
        else
            s = decode(_query, getProtocolCharset());
        return s;
    }

    public char[] getRawAboveHierPath()
        throws URIException
    {
        char ac[] = getRawCurrentHierPath();
        char ac1[];
        if(ac == null)
            ac1 = null;
        else
            ac1 = getRawCurrentHierPath(ac);
        return ac1;
    }

    public char[] getRawAuthority()
    {
        return _authority;
    }

    public char[] getRawCurrentHierPath()
        throws URIException
    {
        char ac[];
        if(_path == null)
            ac = null;
        else
            ac = getRawCurrentHierPath(_path);
        return ac;
    }

    protected char[] getRawCurrentHierPath(char ac[])
        throws URIException
    {
        if(_is_opaque_part)
            throw new URIException(1, "no hierarchy level");
        if(ac == null)
            throw new URIException(1, "empty path");
        String s = new String(ac);
        int i = s.indexOf('/');
        int j = s.lastIndexOf('/');
        char ac1[];
        if(j == 0)
            ac1 = rootPath;
        else
        if(i != j && j != -1)
            ac1 = s.substring(0, j).toCharArray();
        else
            ac1 = ac;
        return ac1;
    }

    public char[] getRawFragment()
    {
        return _fragment;
    }

    public char[] getRawHost()
    {
        return _host;
    }

    public char[] getRawName()
    {
        if(_path != null) goto _L2; else goto _L1
_L1:
        char ac1[] = null;
_L4:
        return ac1;
_L2:
        int i = 0;
        int j = _path.length - 1;
        do
        {
            int k;
            char ac[];
            if(j >= 0)
            {
label0:
                {
                    if(_path[j] != '/')
                        break label0;
                    i = j + 1;
                }
            }
            k = _path.length - i;
            ac = new char[k];
            System.arraycopy(_path, i, ac, 0, k);
            ac1 = ac;
            if(true)
                continue;
            j--;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public char[] getRawPath()
    {
        char ac[];
        if(_is_opaque_part)
            ac = _opaque;
        else
            ac = _path;
        return ac;
    }

    public char[] getRawPathQuery()
    {
        char ac[];
        if(_path == null && _query == null)
        {
            ac = null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            if(_path != null)
                stringbuffer.append(_path);
            if(_query != null)
            {
                stringbuffer.append('?');
                stringbuffer.append(_query);
            }
            ac = stringbuffer.toString().toCharArray();
        }
        return ac;
    }

    public char[] getRawQuery()
    {
        return _query;
    }

    public char[] getRawScheme()
    {
        return _scheme;
    }

    public char[] getRawURI()
    {
        return _uri;
    }

    public char[] getRawURIReference()
    {
        char ac[];
        if(_fragment == null)
            ac = _uri;
        else
        if(_uri == null)
            ac = _fragment;
        else
            ac = (new String(_uri) + "#" + new String(_fragment)).toCharArray();
        return ac;
    }

    public char[] getRawUserinfo()
    {
        return _userinfo;
    }

    public String getScheme()
    {
        String s;
        if(_scheme == null)
            s = null;
        else
            s = new String(_scheme);
        return s;
    }

    public String getURI()
        throws URIException
    {
        String s;
        if(_uri == null)
            s = null;
        else
            s = decode(_uri, getProtocolCharset());
        return s;
    }

    public String getURIReference()
        throws URIException
    {
        char ac[] = getRawURIReference();
        String s;
        if(ac == null)
            s = null;
        else
            s = decode(ac, getProtocolCharset());
        return s;
    }

    public String getUserinfo()
        throws URIException
    {
        String s;
        if(_userinfo == null)
            s = null;
        else
            s = decode(_userinfo, getProtocolCharset());
        return s;
    }

    public boolean hasAuthority()
    {
        boolean flag;
        if(_authority != null || _is_net_path)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasFragment()
    {
        boolean flag;
        if(_fragment != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasQuery()
    {
        boolean flag;
        if(_query != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasUserinfo()
    {
        boolean flag;
        if(_userinfo != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        if(hash != 0) goto _L2; else goto _L1
_L1:
        char ac[] = _uri;
        if(ac == null) goto _L4; else goto _L3
_L3:
        int k;
        int l;
        k = 0;
        l = ac.length;
_L7:
        if(k < l) goto _L5; else goto _L4
_L4:
        char ac1[] = _fragment;
        if(ac1 == null) goto _L2; else goto _L6
_L6:
        int i;
        int j;
        i = 0;
        j = ac1.length;
_L8:
        if(i < j)
            break MISSING_BLOCK_LABEL_78;
_L2:
        return hash;
_L5:
        hash = 31 * hash + ac[k];
        k++;
          goto _L7
        hash = 31 * hash + ac1[i];
        i++;
          goto _L8
    }

    protected int indexFirstOf(String s, String s1)
    {
        return indexFirstOf(s, s1, -1);
    }

    protected int indexFirstOf(String s, String s1, int i)
    {
        int j;
        if(s == null || s.length() == 0)
        {
            j = -1;
        } else
        {
label0:
            {
                if(s1 != null && s1.length() != 0)
                    break label0;
                j = -1;
            }
        }
_L3:
        return j;
        if(i >= 0) goto _L2; else goto _L1
_L1:
        i = 0;
_L5:
        int k;
        char ac[];
        int l;
        k = s.length();
        ac = s1.toCharArray();
        l = 0;
_L6:
        if(l < ac.length)
            break MISSING_BLOCK_LABEL_96;
        int i1;
        if(k == s.length())
            j = -1;
        else
            j = k;
          goto _L3
_L2:
        if(i <= s.length()) goto _L5; else goto _L4
_L4:
        j = -1;
          goto _L3
        i1 = s.indexOf(ac[l], i);
        if(i1 >= 0 && i1 < k)
            k = i1;
        l++;
          goto _L6
    }

    protected int indexFirstOf(char ac[], char c)
    {
        return indexFirstOf(ac, c, 0);
    }

    protected int indexFirstOf(char ac[], char c, int i)
    {
        if(ac != null && ac.length != 0) goto _L2; else goto _L1
_L1:
        int j = -1;
_L5:
        return j;
_L2:
        if(i >= 0) goto _L4; else goto _L3
_L3:
        i = 0;
_L7:
        int k = i;
_L8:
        if(k >= ac.length)
        {
            j = -1;
        } else
        {
label0:
            {
                if(ac[k] != c)
                    break label0;
                j = k;
            }
        }
          goto _L5
_L4:
        if(i <= ac.length) goto _L7; else goto _L6
_L6:
        j = -1;
          goto _L5
        k++;
          goto _L8
    }

    public boolean isAbsPath()
    {
        return _is_abs_path;
    }

    public boolean isAbsoluteURI()
    {
        boolean flag;
        if(_scheme != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isHierPart()
    {
        return _is_hier_part;
    }

    public boolean isHostname()
    {
        return _is_hostname;
    }

    public boolean isIPv4address()
    {
        return _is_IPv4address;
    }

    public boolean isIPv6reference()
    {
        return _is_IPv6reference;
    }

    public boolean isNetPath()
    {
        boolean flag;
        if(_is_net_path || _authority != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isOpaquePart()
    {
        return _is_opaque_part;
    }

    public boolean isRegName()
    {
        return _is_reg_name;
    }

    public boolean isRelPath()
    {
        return _is_rel_path;
    }

    public boolean isRelativeURI()
    {
        boolean flag;
        if(_scheme == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isServer()
    {
        return _is_server;
    }

    public void normalize()
        throws URIException
    {
        if(isAbsPath())
        {
            _path = normalize(_path);
            setURI();
        }
    }

    protected char[] normalize(char ac[])
        throws URIException
    {
        if(ac != null) goto _L2; else goto _L1
_L1:
        char ac1[] = null;
_L8:
        return ac1;
_L2:
        String s;
        int i;
        int j;
        int k;
        int i1;
        s = new String(ac);
        int j1;
        if(s.startsWith("./"))
            s = s.substring(1);
        else
        if(s.startsWith("../"))
            s = s.substring(2);
        else
        if(s.startsWith(".."))
            s = s.substring(2);
_L9:
        i = s.indexOf("/./");
        if(i != -1) goto _L4; else goto _L3
_L3:
        if(s.endsWith("/."))
            s = s.substring(0, s.length() - 1);
        j = 0;
_L10:
        k = s.indexOf("/../", j);
        if(k != -1) goto _L6; else goto _L5
_L5:
        if(s.endsWith("/.."))
        {
            j1 = s.lastIndexOf('/', s.length() - 4);
            if(j1 >= 0)
                s = s.substring(0, j1 + 1);
        }
_L13:
        i1 = s.indexOf("/../");
          goto _L7
_L12:
        if(s.endsWith("/..") && s.lastIndexOf('/', s.length() - 4) < 0)
            s = "/";
        ac1 = s.toCharArray();
          goto _L8
_L4:
        s = s.substring(0, i) + s.substring(i + 2);
          goto _L9
_L6:
        int l = s.lastIndexOf('/', k - 1);
        if(l >= 0)
            s = s.substring(0, l) + s.substring(k + 3);
        else
            j = k + 3;
          goto _L10
_L7:
        if(i1 == -1 || s.lastIndexOf('/', i1 - 1) >= 0) goto _L12; else goto _L11
_L11:
        s = s.substring(i1 + 3);
          goto _L13
    }

    protected void parseAuthority(String s, boolean flag)
        throws URIException
    {
        String s1;
        boolean flag1;
        int k;
        _is_IPv6reference = false;
        _is_IPv4address = false;
        _is_hostname = false;
        _is_server = false;
        _is_reg_name = false;
        s1 = getProtocolCharset();
        flag1 = true;
        int i = 0;
        int j = s.indexOf('@');
        if(j != -1)
        {
            char ac2[];
            if(flag)
                ac2 = s.substring(0, j).toCharArray();
            else
                ac2 = encode(s.substring(0, j), allowed_userinfo, s1);
            _userinfo = ac2;
            i = j + 1;
        }
        if(s.indexOf('[', i) >= i)
        {
            int i1 = s.indexOf(']', i);
            if(i1 == -1)
                throw new URIException(1, "IPv6reference");
            k = i1 + 1;
            char ac1[];
            if(flag)
                ac1 = s.substring(i, k).toCharArray();
            else
                ac1 = encode(s.substring(i, k), allowed_IPv6reference, s1);
            _host = ac1;
            _is_IPv6reference = true;
        } else
        {
            k = s.indexOf(':', i);
            if(k == -1)
            {
                k = s.length();
                flag1 = false;
            }
            _host = s.substring(i, k).toCharArray();
            if(validate(_host, IPv4address))
                _is_IPv4address = true;
            else
            if(validate(_host, hostname))
                _is_hostname = true;
            else
                _is_reg_name = true;
        }
_L6:
        if(!_is_reg_name) goto _L2; else goto _L1
_L1:
        _is_IPv6reference = false;
        _is_IPv4address = false;
        _is_hostname = false;
        _is_server = false;
        char ac[];
        if(flag)
            ac = s.toString().toCharArray();
        else
            ac = encode(s.toString(), allowed_reg_name, s1);
        _authority = ac;
_L4:
        return;
_L2:
        int l;
        if(s.length() - 1 <= k || !flag1 || s.charAt(k) != ':')
            break MISSING_BLOCK_LABEL_396;
        l = k + 1;
        _port = Integer.parseInt(s.substring(l));
        StringBuffer stringbuffer = new StringBuffer();
        if(_userinfo != null)
        {
            stringbuffer.append(_userinfo);
            stringbuffer.append('@');
        }
        if(_host != null)
        {
            stringbuffer.append(_host);
            if(_port != -1)
            {
                stringbuffer.append(':');
                stringbuffer.append(_port);
            }
        }
        _authority = stringbuffer.toString().toCharArray();
        _is_server = true;
        if(true) goto _L4; else goto _L3
_L3:
        NumberFormatException numberformatexception;
        numberformatexception;
        throw new URIException(1, "invalid port number");
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void parseUriReference(String s, boolean flag)
        throws URIException
    {
label0:
        {
            if(s == null)
                throw new URIException("URI-Reference required");
            String s1 = s.trim();
            int i = s1.length();
            if(i > 0)
            {
                char ac3[] = new char[1];
                ac3[0] = s1.charAt(0);
                if(validate(ac3, delims) && i >= 2)
                {
                    char ac4[] = new char[1];
                    int i3 = i - 1;
                    ac4[0] = s1.charAt(i3);
                    if(validate(ac4, delims))
                    {
                        s1 = s1.substring(1, i - 1);
                        i -= 2;
                    }
                }
            }
            int j = 0;
            boolean flag1 = false;
            int k = s1.indexOf(':');
            int l = s1.indexOf('/');
            if(k <= 0 || l >= 0 && l < k)
                flag1 = true;
            String s2;
            int i1;
            String s3;
            if(flag1)
                s2 = "/?#";
            else
                s2 = ":/?#";
            i1 = indexFirstOf(s1, s2, 0);
            if(i1 == -1)
                i1 = 0;
            if(i1 > 0 && i1 < i && s1.charAt(i1) == ':')
            {
                char ac2[] = s1.substring(0, i1).toLowerCase().toCharArray();
                if(!validate(ac2, scheme))
                    break label0;
                _scheme = ac2;
                j = ++i1;
            }
            _is_hier_part = false;
            _is_rel_path = false;
            _is_abs_path = false;
            _is_net_path = false;
            if(i1 >= 0 && i1 < i && s1.charAt(i1) == '/')
            {
                _is_hier_part = true;
                if(i1 + 2 < i && s1.charAt(i1 + 1) == '/')
                {
                    int j2 = i1 + 2;
                    int k2 = indexFirstOf(s1, "/?#", j2);
                    int l1;
                    int l2;
                    if(k2 == -1)
                        if(s1.substring(i1 + 2).length() == 0)
                            k2 = i1 + 2;
                        else
                            k2 = s1.length();
                    l2 = i1 + 2;
                    parseAuthority(s1.substring(l2, k2), flag);
                    i1 = k2;
                    j = k2;
                    _is_net_path = true;
                }
                if(j == i1)
                    _is_abs_path = true;
            }
            if(j < i)
            {
                int i2 = indexFirstOf(s1, "?#", j);
                if(i2 == -1)
                    i2 = s1.length();
                if(!_is_abs_path)
                    if(!flag && prevalidate(s1.substring(j, i2), disallowed_rel_path) || flag && validate(s1.substring(j, i2).toCharArray(), rel_path))
                        _is_rel_path = true;
                    else
                    if(!flag && prevalidate(s1.substring(j, i2), disallowed_opaque_part) || flag && validate(s1.substring(j, i2).toCharArray(), opaque_part))
                        _is_opaque_part = true;
                    else
                        _path = null;
                if(flag)
                    setRawPath(s1.substring(j, i2).toCharArray());
                else
                    setPath(s1.substring(j, i2));
                i1 = i2;
            }
            s3 = getProtocolCharset();
            if(i1 >= 0 && i1 + 1 < i && s1.charAt(i1) == '?')
            {
                int j1 = s1.indexOf('#', i1 + 1);
                if(j1 == -1)
                    j1 = s1.length();
                char ac1[];
                if(flag)
                {
                    l1 = i1 + 1;
                    ac1 = s1.substring(l1, j1).toCharArray();
                } else
                {
                    int k1 = i1 + 1;
                    ac1 = encode(s1.substring(k1, j1), allowed_query, s3);
                }
                _query = ac1;
                i1 = j1;
            }
            if(i1 >= 0 && i1 + 1 <= i && s1.charAt(i1) == '#')
                if(i1 + 1 == i)
                {
                    _fragment = "".toCharArray();
                } else
                {
                    char ac[];
                    if(flag)
                        ac = s1.substring(i1 + 1).toCharArray();
                    else
                        ac = encode(s1.substring(i1 + 1), allowed_fragment, s3);
                    _fragment = ac;
                }
            setURI();
            return;
        }
        throw new URIException("incorrect scheme");
    }

    protected boolean prevalidate(String s, BitSet bitset)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        char ac[] = s.toCharArray();
        int i = 0;
        do
        {
            if(i >= ac.length)
            {
                flag = true;
            } else
            {
label0:
                {
                    if(!bitset.get(ac[i]))
                        break label0;
                    flag = false;
                }
            }
            if(true)
                continue;
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void readObject(ObjectInputStream objectinputstream)
        throws ClassNotFoundException, IOException
    {
        objectinputstream.defaultReadObject();
    }

    protected char[] removeFragmentIdentifier(char ac[])
    {
        char ac1[];
        if(ac == null)
        {
            ac1 = null;
        } else
        {
            int i = (new String(ac)).indexOf('#');
            if(i != -1)
                ac = (new String(ac)).substring(0, i).toCharArray();
            ac1 = ac;
        }
        return ac1;
    }

    protected char[] resolvePath(char ac[], char ac1[])
        throws URIException
    {
        String s;
        int i;
        char ac2[];
        if(ac == null)
            s = "";
        else
            s = new String(ac);
        i = s.lastIndexOf('/');
        if(i != -1)
            ac = s.substring(0, i + 1).toCharArray();
        if(ac1 == null || ac1.length == 0)
            ac2 = normalize(ac);
        else
        if(ac1[0] == '/')
        {
            ac2 = normalize(ac1);
        } else
        {
            StringBuffer stringbuffer = new StringBuffer(s.length() + ac1.length);
            String s1;
            if(i != -1)
                s1 = s.substring(0, i + 1);
            else
                s1 = "/";
            stringbuffer.append(s1);
            stringbuffer.append(ac1);
            ac2 = normalize(stringbuffer.toString().toCharArray());
        }
        return ac2;
    }

    public void setEscapedAuthority(String s)
        throws URIException
    {
        parseAuthority(s, true);
        setURI();
    }

    public void setEscapedFragment(String s)
        throws URIException
    {
        if(s == null)
        {
            _fragment = null;
            hash = 0;
        } else
        {
            setRawFragment(s.toCharArray());
        }
    }

    public void setEscapedPath(String s)
        throws URIException
    {
        if(s == null)
        {
            _opaque = null;
            _path = null;
            setURI();
        } else
        {
            setRawPath(s.toCharArray());
        }
    }

    public void setEscapedQuery(String s)
        throws URIException
    {
        if(s == null)
        {
            _query = null;
            setURI();
        } else
        {
            setRawQuery(s.toCharArray());
        }
    }

    public void setFragment(String s)
        throws URIException
    {
        if(s == null || s.length() == 0)
        {
            char ac[];
            if(s == null)
                ac = null;
            else
                ac = s.toCharArray();
            _fragment = ac;
            hash = 0;
        } else
        {
            _fragment = encode(s, allowed_fragment, getProtocolCharset());
            hash = 0;
        }
    }

    public void setPath(String s)
        throws URIException
    {
        if(s != null && s.length() != 0) goto _L2; else goto _L1
_L1:
        char ac[];
        if(s == null)
            ac = null;
        else
            ac = s.toCharArray();
        _opaque = ac;
        _path = ac;
        setURI();
_L4:
        return;
_L2:
        String s1;
        s1 = getProtocolCharset();
        if(!_is_net_path && !_is_abs_path)
            break; /* Loop/switch isn't completed */
        _path = encode(s, allowed_abs_path, s1);
_L5:
        setURI();
        if(true) goto _L4; else goto _L3
_L3:
        if(_is_rel_path)
        {
            StringBuffer stringbuffer = new StringBuffer(s.length());
            int i = s.indexOf('/');
            if(i == 0)
                throw new URIException(1, "incorrect relative path");
            if(i > 0)
            {
                stringbuffer.append(encode(s.substring(0, i), allowed_rel_path, s1));
                stringbuffer.append(encode(s.substring(i), allowed_abs_path, s1));
            } else
            {
                stringbuffer.append(encode(s, allowed_rel_path, s1));
            }
            _path = stringbuffer.toString().toCharArray();
        } else
        if(_is_opaque_part)
        {
            StringBuffer stringbuffer1 = new StringBuffer();
            stringbuffer1.insert(0, encode(s.substring(0, 1), uric_no_slash, s1));
            stringbuffer1.insert(1, encode(s.substring(1), uric, s1));
            _opaque = stringbuffer1.toString().toCharArray();
        } else
        {
            throw new URIException(1, "incorrect path");
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void setQuery(String s)
        throws URIException
    {
        if(s == null || s.length() == 0)
        {
            char ac[];
            if(s == null)
                ac = null;
            else
                ac = s.toCharArray();
            _query = ac;
            setURI();
        } else
        {
            setRawQuery(encode(s, allowed_query, getProtocolCharset()));
        }
    }

    public void setRawAuthority(char ac[])
        throws URIException, NullPointerException
    {
        parseAuthority(new String(ac), true);
        setURI();
    }

    public void setRawFragment(char ac[])
        throws URIException
    {
        if(ac == null || ac.length == 0)
        {
            _fragment = ac;
            hash = 0;
        } else
        {
            if(!validate(ac, fragment))
                throw new URIException(3, "escaped fragment not valid");
            _fragment = ac;
            hash = 0;
        }
    }

    public void setRawPath(char ac[])
        throws URIException
    {
        if(ac != null && ac.length != 0) goto _L2; else goto _L1
_L1:
        _opaque = ac;
        _path = ac;
        setURI();
_L4:
        return;
_L2:
        char ac1[];
        ac1 = removeFragmentIdentifier(ac);
        if(!_is_net_path && !_is_abs_path)
            break; /* Loop/switch isn't completed */
        if(ac1[0] != '/')
            throw new URIException(1, "not absolute path");
        if(!validate(ac1, abs_path))
            throw new URIException(3, "escaped absolute path not valid");
        _path = ac1;
_L5:
        setURI();
        if(true) goto _L4; else goto _L3
_L3:
        if(_is_rel_path)
        {
            int i = indexFirstOf(ac1, '/');
            if(i == 0)
                throw new URIException(1, "incorrect path");
            if(i > 0 && !validate(ac1, 0, i - 1, rel_segment) && !validate(ac1, i, -1, abs_path) || i < 0 && !validate(ac1, 0, -1, rel_segment))
                throw new URIException(3, "escaped relative path not valid");
            _path = ac1;
        } else
        if(_is_opaque_part)
        {
            if(!uric_no_slash.get(ac1[0]) && !validate(ac1, 1, -1, uric))
                throw new URIException(3, "escaped opaque part not valid");
            _opaque = ac1;
        } else
        {
            throw new URIException(1, "incorrect path");
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void setRawQuery(char ac[])
        throws URIException
    {
        if(ac == null || ac.length == 0)
        {
            _query = ac;
            setURI();
        } else
        {
            char ac1[] = removeFragmentIdentifier(ac);
            if(!validate(ac1, query))
                throw new URIException(3, "escaped query not valid");
            _query = ac1;
            setURI();
        }
    }

    protected void setURI()
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        if(_scheme != null)
        {
            stringbuffer.append(_scheme);
            stringbuffer.append(':');
        }
        if(_is_net_path)
        {
            stringbuffer.append("//");
            if(_authority != null)
                if(_userinfo != null)
                {
                    if(_host != null)
                    {
                        stringbuffer.append(_host);
                        if(_port != -1)
                        {
                            stringbuffer.append(':');
                            stringbuffer.append(_port);
                        }
                    }
                } else
                {
                    stringbuffer.append(_authority);
                }
        }
        if(_opaque == null || !_is_opaque_part) goto _L2; else goto _L1
_L1:
        stringbuffer.append(_opaque);
_L4:
        if(_query != null)
        {
            stringbuffer.append('?');
            stringbuffer.append(_query);
        }
        _uri = stringbuffer.toString().toCharArray();
        hash = 0;
        return;
_L2:
        if(_path != null && _path.length != 0)
            stringbuffer.append(_path);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String toString()
    {
        return getEscapedURI();
    }

    protected boolean validate(char ac[], int i, int j, BitSet bitset)
    {
        int k;
        if(j == -1)
            j = ac.length - 1;
        k = i;
_L6:
        if(k <= j) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        if(bitset.get(ac[k]))
            break; /* Loop/switch isn't completed */
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        k++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected boolean validate(char ac[], BitSet bitset)
    {
        return validate(ac, 0, -1, bitset);
    }

    protected void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
    }

    protected static final BitSet IPv4address;
    protected static final BitSet IPv6address;
    protected static final BitSet IPv6reference;
    protected static final BitSet URI_reference;
    protected static final BitSet abs_path;
    protected static final BitSet absoluteURI;
    public static final BitSet allowed_IPv6reference;
    public static final BitSet allowed_abs_path;
    public static final BitSet allowed_authority;
    public static final BitSet allowed_fragment;
    public static final BitSet allowed_host;
    public static final BitSet allowed_opaque_part;
    public static final BitSet allowed_query;
    public static final BitSet allowed_reg_name;
    public static final BitSet allowed_rel_path;
    public static final BitSet allowed_userinfo;
    public static final BitSet allowed_within_authority;
    public static final BitSet allowed_within_path;
    public static final BitSet allowed_within_query;
    public static final BitSet allowed_within_userinfo;
    protected static final BitSet alpha;
    protected static final BitSet alphanum;
    protected static final BitSet authority;
    public static final BitSet control;
    protected static String defaultDocumentCharset;
    protected static String defaultDocumentCharsetByLocale;
    protected static String defaultDocumentCharsetByPlatform;
    protected static String defaultProtocolCharset;
    public static final BitSet delims;
    protected static final BitSet digit;
    public static final BitSet disallowed_opaque_part;
    public static final BitSet disallowed_rel_path;
    protected static final BitSet domainlabel;
    protected static final BitSet escaped;
    protected static final BitSet fragment;
    protected static final BitSet hex;
    protected static final BitSet hier_part;
    protected static final BitSet host;
    protected static final BitSet hostname;
    protected static final BitSet hostport;
    protected static final BitSet mark;
    protected static final BitSet net_path;
    protected static final BitSet opaque_part;
    protected static final BitSet param;
    protected static final BitSet path;
    protected static final BitSet path_segments;
    protected static final BitSet pchar;
    protected static final BitSet percent;
    protected static final BitSet port;
    protected static final BitSet query;
    protected static final BitSet reg_name;
    protected static final BitSet rel_path;
    protected static final BitSet rel_segment;
    protected static final BitSet relativeURI;
    protected static final BitSet reserved;
    protected static char rootPath[];
    protected static final BitSet scheme;
    protected static final BitSet segment;
    static final long serialVersionUID = 0x864831aad836c36L;
    protected static final BitSet server;
    public static final BitSet space;
    protected static final BitSet toplabel;
    protected static final BitSet unreserved;
    public static final BitSet unwise;
    protected static final BitSet uric;
    protected static final BitSet uric_no_slash;
    protected static final BitSet userinfo;
    public static final BitSet within_userinfo;
    protected char _authority[];
    protected char _fragment[];
    protected char _host[];
    protected boolean _is_IPv4address;
    protected boolean _is_IPv6reference;
    protected boolean _is_abs_path;
    protected boolean _is_hier_part;
    protected boolean _is_hostname;
    protected boolean _is_net_path;
    protected boolean _is_opaque_part;
    protected boolean _is_reg_name;
    protected boolean _is_rel_path;
    protected boolean _is_server;
    protected char _opaque[];
    protected char _path[];
    protected int _port;
    protected char _query[];
    protected char _scheme[];
    protected char _uri[];
    protected char _userinfo[];
    protected int hash;
    protected String protocolCharset;

    static 
    {
        defaultProtocolCharset = "UTF-8";
        defaultDocumentCharset = null;
        defaultDocumentCharsetByLocale = null;
        defaultDocumentCharsetByPlatform = null;
        Locale locale = Locale.getDefault();
        if(locale != null)
        {
            defaultDocumentCharsetByLocale = LocaleToCharsetMap.getCharset(locale);
            defaultDocumentCharset = defaultDocumentCharsetByLocale;
        }
        char ac[];
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        try
        {
            defaultDocumentCharsetByPlatform = System.getProperty("file.encoding");
        }
        catch(SecurityException securityexception) { }
        if(defaultDocumentCharset == null)
            defaultDocumentCharset = defaultDocumentCharsetByPlatform;
        ac = new char[1];
        ac[0] = '/';
        rootPath = ac;
        percent = new BitSet(256);
        percent.set(37);
        digit = new BitSet(256);
        i = 48;
        if(i <= 57) goto _L2; else goto _L1
_L1:
        alpha = new BitSet(256);
        j = 97;
_L11:
        if(j <= 122) goto _L4; else goto _L3
_L3:
        k = 65;
_L12:
        if(k <= 90) goto _L6; else goto _L5
_L5:
        alphanum = new BitSet(256);
        alphanum.or(alpha);
        alphanum.or(digit);
        hex = new BitSet(256);
        hex.or(digit);
        l = 97;
_L13:
        if(l <= 102) goto _L8; else goto _L7
_L7:
        i1 = 65;
_L14:
        if(i1 <= 70) goto _L10; else goto _L9
_L9:
        escaped = new BitSet(256);
        escaped.or(percent);
        escaped.or(hex);
        mark = new BitSet(256);
        mark.set(45);
        mark.set(95);
        mark.set(46);
        mark.set(33);
        mark.set(126);
        mark.set(42);
        mark.set(39);
        mark.set(40);
        mark.set(41);
        unreserved = new BitSet(256);
        unreserved.or(alphanum);
        unreserved.or(mark);
        reserved = new BitSet(256);
        reserved.set(59);
        reserved.set(47);
        reserved.set(63);
        reserved.set(58);
        reserved.set(64);
        reserved.set(38);
        reserved.set(61);
        reserved.set(43);
        reserved.set(36);
        reserved.set(44);
        uric = new BitSet(256);
        uric.or(reserved);
        uric.or(unreserved);
        uric.or(escaped);
        fragment = uric;
        query = uric;
        pchar = new BitSet(256);
        pchar.or(unreserved);
        pchar.or(escaped);
        pchar.set(58);
        pchar.set(64);
        pchar.set(38);
        pchar.set(61);
        pchar.set(43);
        pchar.set(36);
        pchar.set(44);
        param = pchar;
        segment = new BitSet(256);
        segment.or(pchar);
        segment.set(59);
        segment.or(param);
        path_segments = new BitSet(256);
        path_segments.set(47);
        path_segments.or(segment);
        abs_path = new BitSet(256);
        abs_path.set(47);
        abs_path.or(path_segments);
        uric_no_slash = new BitSet(256);
        uric_no_slash.or(unreserved);
        uric_no_slash.or(escaped);
        uric_no_slash.set(59);
        uric_no_slash.set(63);
        uric_no_slash.set(59);
        uric_no_slash.set(64);
        uric_no_slash.set(38);
        uric_no_slash.set(61);
        uric_no_slash.set(43);
        uric_no_slash.set(36);
        uric_no_slash.set(44);
        opaque_part = new BitSet(256);
        opaque_part.or(uric_no_slash);
        opaque_part.or(uric);
        path = new BitSet(256);
        path.or(abs_path);
        path.or(opaque_part);
        port = digit;
        IPv4address = new BitSet(256);
        IPv4address.or(digit);
        IPv4address.set(46);
        IPv6address = new BitSet(256);
        IPv6address.or(hex);
        IPv6address.set(58);
        IPv6address.or(IPv4address);
        IPv6reference = new BitSet(256);
        IPv6reference.set(91);
        IPv6reference.or(IPv6address);
        IPv6reference.set(93);
        toplabel = new BitSet(256);
        toplabel.or(alphanum);
        toplabel.set(45);
        domainlabel = toplabel;
        hostname = new BitSet(256);
        hostname.or(toplabel);
        hostname.set(46);
        host = new BitSet(256);
        host.or(hostname);
        host.or(IPv6reference);
        hostport = new BitSet(256);
        hostport.or(host);
        hostport.set(58);
        hostport.or(port);
        userinfo = new BitSet(256);
        userinfo.or(unreserved);
        userinfo.or(escaped);
        userinfo.set(59);
        userinfo.set(58);
        userinfo.set(38);
        userinfo.set(61);
        userinfo.set(43);
        userinfo.set(36);
        userinfo.set(44);
        within_userinfo = new BitSet(256);
        within_userinfo.or(userinfo);
        within_userinfo.clear(59);
        within_userinfo.clear(58);
        within_userinfo.clear(64);
        within_userinfo.clear(63);
        within_userinfo.clear(47);
        server = new BitSet(256);
        server.or(userinfo);
        server.set(64);
        server.or(hostport);
        reg_name = new BitSet(256);
        reg_name.or(unreserved);
        reg_name.or(escaped);
        reg_name.set(36);
        reg_name.set(44);
        reg_name.set(59);
        reg_name.set(58);
        reg_name.set(64);
        reg_name.set(38);
        reg_name.set(61);
        reg_name.set(43);
        authority = new BitSet(256);
        authority.or(server);
        authority.or(reg_name);
        scheme = new BitSet(256);
        scheme.or(alpha);
        scheme.or(digit);
        scheme.set(43);
        scheme.set(45);
        scheme.set(46);
        rel_segment = new BitSet(256);
        rel_segment.or(unreserved);
        rel_segment.or(escaped);
        rel_segment.set(59);
        rel_segment.set(64);
        rel_segment.set(38);
        rel_segment.set(61);
        rel_segment.set(43);
        rel_segment.set(36);
        rel_segment.set(44);
        rel_path = new BitSet(256);
        rel_path.or(rel_segment);
        rel_path.or(abs_path);
        net_path = new BitSet(256);
        net_path.set(47);
        net_path.or(authority);
        net_path.or(abs_path);
        hier_part = new BitSet(256);
        hier_part.or(net_path);
        hier_part.or(abs_path);
        hier_part.or(query);
        relativeURI = new BitSet(256);
        relativeURI.or(net_path);
        relativeURI.or(abs_path);
        relativeURI.or(rel_path);
        relativeURI.or(query);
        absoluteURI = new BitSet(256);
        absoluteURI.or(scheme);
        absoluteURI.set(58);
        absoluteURI.or(hier_part);
        absoluteURI.or(opaque_part);
        URI_reference = new BitSet(256);
        URI_reference.or(absoluteURI);
        URI_reference.or(relativeURI);
        URI_reference.set(35);
        URI_reference.or(fragment);
        control = new BitSet(256);
        j1 = 0;
_L15:
        if(j1 > 31)
        {
            control.set(127);
            space = new BitSet(256);
            space.set(32);
            delims = new BitSet(256);
            delims.set(60);
            delims.set(62);
            delims.set(35);
            delims.set(37);
            delims.set(34);
            unwise = new BitSet(256);
            unwise.set(123);
            unwise.set(125);
            unwise.set(124);
            unwise.set(92);
            unwise.set(94);
            unwise.set(91);
            unwise.set(93);
            unwise.set(96);
            disallowed_rel_path = new BitSet(256);
            disallowed_rel_path.or(uric);
            disallowed_rel_path.andNot(rel_path);
            disallowed_opaque_part = new BitSet(256);
            disallowed_opaque_part.or(uric);
            disallowed_opaque_part.andNot(opaque_part);
            allowed_authority = new BitSet(256);
            allowed_authority.or(authority);
            allowed_authority.clear(37);
            allowed_opaque_part = new BitSet(256);
            allowed_opaque_part.or(opaque_part);
            allowed_opaque_part.clear(37);
            allowed_reg_name = new BitSet(256);
            allowed_reg_name.or(reg_name);
            allowed_reg_name.clear(37);
            allowed_userinfo = new BitSet(256);
            allowed_userinfo.or(userinfo);
            allowed_userinfo.clear(37);
            allowed_within_userinfo = new BitSet(256);
            allowed_within_userinfo.or(within_userinfo);
            allowed_within_userinfo.clear(37);
            allowed_IPv6reference = new BitSet(256);
            allowed_IPv6reference.or(IPv6reference);
            allowed_IPv6reference.clear(91);
            allowed_IPv6reference.clear(93);
            allowed_host = new BitSet(256);
            allowed_host.or(hostname);
            allowed_host.or(allowed_IPv6reference);
            allowed_within_authority = new BitSet(256);
            allowed_within_authority.or(server);
            allowed_within_authority.or(reg_name);
            allowed_within_authority.clear(59);
            allowed_within_authority.clear(58);
            allowed_within_authority.clear(64);
            allowed_within_authority.clear(63);
            allowed_within_authority.clear(47);
            allowed_abs_path = new BitSet(256);
            allowed_abs_path.or(abs_path);
            allowed_abs_path.andNot(percent);
            allowed_rel_path = new BitSet(256);
            allowed_rel_path.or(rel_path);
            allowed_rel_path.clear(37);
            allowed_within_path = new BitSet(256);
            allowed_within_path.or(abs_path);
            allowed_within_path.clear(47);
            allowed_within_path.clear(59);
            allowed_within_path.clear(61);
            allowed_within_path.clear(63);
            allowed_query = new BitSet(256);
            allowed_query.or(uric);
            allowed_query.clear(37);
            allowed_within_query = new BitSet(256);
            allowed_within_query.or(allowed_query);
            allowed_within_query.andNot(reserved);
            allowed_fragment = new BitSet(256);
            allowed_fragment.or(uric);
            allowed_fragment.clear(37);
            return;
        }
        break MISSING_BLOCK_LABEL_2643;
_L2:
        digit.set(i);
        i++;
        break MISSING_BLOCK_LABEL_108;
_L4:
        alpha.set(j);
        j++;
          goto _L11
_L6:
        alpha.set(k);
        k++;
          goto _L12
_L8:
        hex.set(l);
        l++;
          goto _L13
_L10:
        hex.set(i1);
        i1++;
          goto _L14
        control.set(j1);
        j1++;
          goto _L15
    }
}
