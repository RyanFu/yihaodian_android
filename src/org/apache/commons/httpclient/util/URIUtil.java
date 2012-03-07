// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.util;

import java.util.BitSet;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

// Referenced classes of package org.apache.commons.httpclient.util:
//            EncodingUtil

public class URIUtil
{
    protected static class Coder extends URI
    {

        public static String decode(char ac[], String s)
            throws URIException
        {
            return URI.decode(ac, s);
        }

        public static char[] encode(String s, BitSet bitset, String s1)
            throws URIException
        {
            return URI.encode(s, bitset, s1);
        }

        public static String replace(String s, char c, char c1)
        {
            StringBuffer stringbuffer = new StringBuffer(s.length());
            int i = 0;
            do
            {
                int j = s.indexOf(c);
                if(j >= 0)
                {
                    stringbuffer.append(s.substring(0, j));
                    stringbuffer.append(c1);
                } else
                {
                    stringbuffer.append(s.substring(i));
                }
                i = j;
            } while(j >= 0);
            return stringbuffer.toString();
        }

        public static String replace(String s, char ac[], char ac1[])
        {
            int i = ac.length;
            do
            {
                if(i <= 0)
                    return s.toString();
                s = replace(s, ac[i], ac1[i]);
                i--;
            } while(true);
        }

        public static boolean verifyEscaped(char ac[])
        {
            int i = 0;
_L6:
            if(i < ac.length) goto _L2; else goto _L1
_L1:
            boolean flag = true;
_L4:
            return flag;
_L2:
            char c = ac[i];
            if(c > '\200')
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(c != '%')
                break; /* Loop/switch isn't completed */
            int j = i + 1;
            if(Character.digit(ac[j], 16) != -1)
            {
                i = j + 1;
                if(Character.digit(ac[i], 16) != -1)
                    break; /* Loop/switch isn't completed */
            }
            flag = false;
            if(true) goto _L4; else goto _L3
_L3:
            i++;
            if(true) goto _L6; else goto _L5
_L5:
        }

        protected Coder()
        {
        }
    }


    public URIUtil()
    {
    }

    public static String decode(String s)
        throws URIException
    {
        String s1;
        try
        {
            s1 = EncodingUtil.getString(URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(s)), URI.getDefaultProtocolCharset());
        }
        catch(DecoderException decoderexception)
        {
            throw new URIException(decoderexception.getMessage());
        }
        return s1;
    }

    public static String decode(String s, String s1)
        throws URIException
    {
        return Coder.decode(s.toCharArray(), s1);
    }

    public static String encode(String s, BitSet bitset)
        throws URIException
    {
        return encode(s, bitset, URI.getDefaultProtocolCharset());
    }

    public static String encode(String s, BitSet bitset, String s1)
        throws URIException
    {
        return EncodingUtil.getAsciiString(URLCodec.encodeUrl(bitset, EncodingUtil.getBytes(s, s1)));
    }

    public static String encodeAll(String s)
        throws URIException
    {
        return encodeAll(s, URI.getDefaultProtocolCharset());
    }

    public static String encodeAll(String s, String s1)
        throws URIException
    {
        return encode(s, empty, s1);
    }

    public static String encodePath(String s)
        throws URIException
    {
        return encodePath(s, URI.getDefaultProtocolCharset());
    }

    public static String encodePath(String s, String s1)
        throws URIException
    {
        return encode(s, URI.allowed_abs_path, s1);
    }

    public static String encodePathQuery(String s)
        throws URIException
    {
        return encodePathQuery(s, URI.getDefaultProtocolCharset());
    }

    public static String encodePathQuery(String s, String s1)
        throws URIException
    {
        int i = s.indexOf('?');
        String s2;
        if(i < 0)
            s2 = encode(s, URI.allowed_abs_path, s1);
        else
            s2 = encode(s.substring(0, i), URI.allowed_abs_path, s1) + '?' + encode(s.substring(i + 1), URI.allowed_query, s1);
        return s2;
    }

    public static String encodeQuery(String s)
        throws URIException
    {
        return encodeQuery(s, URI.getDefaultProtocolCharset());
    }

    public static String encodeQuery(String s, String s1)
        throws URIException
    {
        return encode(s, URI.allowed_query, s1);
    }

    public static String encodeWithinAuthority(String s)
        throws URIException
    {
        return encodeWithinAuthority(s, URI.getDefaultProtocolCharset());
    }

    public static String encodeWithinAuthority(String s, String s1)
        throws URIException
    {
        return encode(s, URI.allowed_within_authority, s1);
    }

    public static String encodeWithinPath(String s)
        throws URIException
    {
        return encodeWithinPath(s, URI.getDefaultProtocolCharset());
    }

    public static String encodeWithinPath(String s, String s1)
        throws URIException
    {
        return encode(s, URI.allowed_within_path, s1);
    }

    public static String encodeWithinQuery(String s)
        throws URIException
    {
        return encodeWithinQuery(s, URI.getDefaultProtocolCharset());
    }

    public static String encodeWithinQuery(String s, String s1)
        throws URIException
    {
        return encode(s, URI.allowed_within_query, s1);
    }

    public static String getFromPath(String s)
    {
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            int i = s.indexOf("//");
            int j;
            int k;
            if(i >= 0)
            {
                if(s.lastIndexOf("/", i - 1) >= 0)
                    j = 0;
                else
                    j = i + 2;
            } else
            {
                j = 0;
            }
            k = s.indexOf("/", j);
            if(k < 0)
            {
                if(i >= 0)
                    s1 = "/";
                else
                    s1 = s;
            } else
            {
                s1 = s.substring(k);
            }
        }
        return s1;
    }

    public static String getName(String s)
    {
        String s1;
        if(s == null || s.length() == 0)
        {
            s1 = s;
        } else
        {
            String s2 = getPath(s);
            int i = s2.lastIndexOf("/");
            int j = s2.length();
            if(i >= 0)
                s1 = s2.substring(i + 1, j);
            else
                s1 = s2;
        }
        return s1;
    }

    public static String getPath(String s)
    {
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            int i = s.indexOf("//");
            int j;
            int k;
            int l;
            if(i >= 0)
            {
                if(s.lastIndexOf("/", i - 1) >= 0)
                    j = 0;
                else
                    j = i + 2;
            } else
            {
                j = 0;
            }
            k = s.indexOf("/", j);
            l = s.length();
            if(s.indexOf('?', k) != -1)
                l = s.indexOf('?', k);
            if(s.lastIndexOf("#") > k && s.lastIndexOf("#") < l)
                l = s.lastIndexOf("#");
            if(k < 0)
            {
                if(i >= 0)
                    s1 = "/";
                else
                    s1 = s;
            } else
            {
                s1 = s.substring(k, l);
            }
        }
        return s1;
    }

    public static String getPathQuery(String s)
    {
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            int i = s.indexOf("//");
            int j;
            int k;
            int l;
            if(i >= 0)
            {
                if(s.lastIndexOf("/", i - 1) >= 0)
                    j = 0;
                else
                    j = i + 2;
            } else
            {
                j = 0;
            }
            k = s.indexOf("/", j);
            l = s.length();
            if(s.lastIndexOf("#") > k)
                l = s.lastIndexOf("#");
            if(k < 0)
            {
                if(i >= 0)
                    s1 = "/";
                else
                    s1 = s;
            } else
            {
                s1 = s.substring(k, l);
            }
        }
        return s1;
    }

    public static String getQuery(String s)
    {
        String s1;
        if(s == null || s.length() == 0)
        {
            s1 = null;
        } else
        {
            int i = s.indexOf("//");
            int j;
            int k;
            int l;
            int i1;
            if(i >= 0)
            {
                if(s.lastIndexOf("/", i - 1) >= 0)
                    j = 0;
                else
                    j = i + 2;
            } else
            {
                j = 0;
            }
            k = s.indexOf("/", j);
            l = s.length();
            i1 = s.indexOf("?", k);
            if(i1 >= 0)
            {
                int j1 = i1 + 1;
                if(s.lastIndexOf("#") > j1)
                    l = s.lastIndexOf("#");
                if(j1 < 0 || j1 == l)
                    s1 = null;
                else
                    s1 = s.substring(j1, l);
            } else
            {
                s1 = null;
            }
        }
        return s1;
    }

    protected static final BitSet empty = new BitSet(1);

}
