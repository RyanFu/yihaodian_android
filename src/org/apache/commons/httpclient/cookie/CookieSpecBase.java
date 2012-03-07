// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.cookie;

import java.util.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.cookie:
//            CookieSpec, MalformedCookieException

public class CookieSpecBase
    implements CookieSpec
{

    public CookieSpecBase()
    {
        datepatterns = null;
    }

    private static void addInPathOrder(List list, Cookie cookie)
    {
        do
        {
            int i;
            for(i = 0; i >= list.size() || cookie.compare(cookie, (Cookie)list.get(i)) > 0;)
            {
                list.add(i, cookie);
                return;
            }

            i++;
        } while(true);
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

    public boolean domainMatch(String s, String s1)
    {
        boolean flag;
        if(s.equals(s1))
        {
            flag = true;
        } else
        {
            if(!s1.startsWith("."))
                s1 = "." + s1;
            if(s.endsWith(s1) || s.equals(s1.substring(1)))
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public String formatCookie(Cookie cookie)
    {
        LOG.trace("enter CookieSpecBase.formatCookie(Cookie)");
        if(cookie == null)
            throw new IllegalArgumentException("Cookie may not be null");
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(cookie.getName());
        stringbuffer.append("=");
        String s = cookie.getValue();
        if(s != null)
            stringbuffer.append(s);
        return stringbuffer.toString();
    }

    public Header formatCookieHeader(Cookie cookie)
    {
        LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie)");
        return new Header("Cookie", formatCookie(cookie));
    }

    public Header formatCookieHeader(Cookie acookie[])
    {
        LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie[])");
        return new Header("Cookie", formatCookies(acookie));
    }

    public String formatCookies(Cookie acookie[])
        throws IllegalArgumentException
    {
        LOG.trace("enter CookieSpecBase.formatCookies(Cookie[])");
        if(acookie == null)
            throw new IllegalArgumentException("Cookie array may not be null");
        if(acookie.length == 0)
            throw new IllegalArgumentException("Cookie array may not be empty");
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if(i >= acookie.length)
                return stringbuffer.toString();
            if(i > 0)
                stringbuffer.append("; ");
            stringbuffer.append(formatCookie(acookie[i]));
            i++;
        } while(true);
    }

    public Collection getValidDateFormats()
    {
        return datepatterns;
    }

    public boolean match(String s, int i, String s1, boolean flag, Cookie cookie)
    {
        String s2;
        LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie");
        if(s == null)
            throw new IllegalArgumentException("Host of origin may not be null");
        if(s.trim().equals(""))
            throw new IllegalArgumentException("Host of origin may not be blank");
        if(i < 0)
            throw new IllegalArgumentException("Invalid port: " + i);
        if(s1 == null)
            throw new IllegalArgumentException("Path of origin may not be null.");
        if(cookie == null)
            throw new IllegalArgumentException("Cookie may not be null");
        if(s1.trim().equals(""))
            s1 = "/";
        s2 = s.toLowerCase();
        if(cookie.getDomain() != null) goto _L2; else goto _L1
_L1:
        boolean flag1;
        LOG.warn("Invalid cookie state: domain not specified");
        flag1 = false;
_L4:
        return flag1;
_L2:
        if(cookie.getPath() == null)
        {
            LOG.warn("Invalid cookie state: path not specified");
            flag1 = false;
            continue; /* Loop/switch isn't completed */
        }
        if((cookie.getExpiryDate() == null || cookie.getExpiryDate().after(new Date())) && domainMatch(s2, cookie.getDomain()) && pathMatch(s1, cookie.getPath()))
        {
            boolean flag2;
            if(cookie.getSecure())
                flag2 = flag;
            else
                flag2 = true;
            if(flag2)
            {
                flag1 = true;
                continue; /* Loop/switch isn't completed */
            }
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Cookie[] match(String s, int i, String s1, boolean flag, Cookie acookie[])
    {
        LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie[])");
        if(acookie != null) goto _L2; else goto _L1
_L1:
        Cookie acookie1[] = null;
_L4:
        return acookie1;
_L2:
        LinkedList linkedlist = new LinkedList();
        int j = 0;
        do
        {
label0:
            {
                if(j < acookie.length)
                    break label0;
                acookie1 = (Cookie[])linkedlist.toArray(new Cookie[linkedlist.size()]);
            }
            if(true)
                continue;
            if(match(s, i, s1, flag, acookie[j]))
                addInPathOrder(linkedlist, acookie[j]);
            j++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Cookie[] parse(String s, int i, String s1, boolean flag, String s2)
        throws MalformedCookieException
    {
        String s3;
        String s4;
        boolean flag1;
        int k1;
        int l1;
        LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, Header)");
        if(s == null)
            throw new IllegalArgumentException("Host of origin may not be null");
        if(s.trim().equals(""))
            throw new IllegalArgumentException("Host of origin may not be blank");
        if(i < 0)
            throw new IllegalArgumentException("Invalid port: " + i);
        if(s1 == null)
            throw new IllegalArgumentException("Path of origin may not be null.");
        if(s2 == null)
            throw new IllegalArgumentException("Header may not be null.");
        if(s1.trim().equals(""))
            s1 = "/";
        s3 = s.toLowerCase();
        s4 = s1;
        int j = s4.lastIndexOf("/");
        if(j >= 0)
        {
            if(j == 0)
                j = 1;
            s4 = s4.substring(0, j);
        }
        flag1 = false;
        int k = s2.toLowerCase().indexOf("expires=");
        if(k == -1)
            break MISSING_BLOCK_LABEL_240;
        k1 = k + "expires=".length();
        l1 = s2.indexOf(";", k1);
        if(l1 == -1)
            l1 = s2.length();
        DateUtil.parseDate(s2.substring(k1, l1), datepatterns);
        flag1 = true;
_L6:
        HeaderElement aheaderelement[];
        Cookie acookie[];
        int l;
        if(flag1)
        {
            aheaderelement = new HeaderElement[1];
            aheaderelement[0] = new HeaderElement(s2.toCharArray());
        } else
        {
            aheaderelement = HeaderElement.parseElements(s2.toCharArray());
        }
        acookie = new Cookie[aheaderelement.length];
        l = 0;
_L3:
        Cookie cookie;
        NameValuePair anamevaluepair[];
        int i1;
        if(l >= aheaderelement.length)
            return acookie;
        HeaderElement headerelement = aheaderelement[l];
        int j1;
        try
        {
            cookie = new Cookie(s3, headerelement.getName(), headerelement.getValue(), s4, null, false);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw new MalformedCookieException(illegalargumentexception.getMessage());
        }
        anamevaluepair = headerelement.getParameters();
        if(anamevaluepair == null) goto _L2; else goto _L1
_L1:
        i1 = 0;
_L4:
        j1 = anamevaluepair.length;
        if(i1 < j1)
            break MISSING_BLOCK_LABEL_389;
_L2:
        acookie[l] = cookie;
        l++;
          goto _L3
        parseAttribute(anamevaluepair[i1], cookie);
        i1++;
          goto _L4
        DateParseException dateparseexception;
        dateparseexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public Cookie[] parse(String s, int i, String s1, boolean flag, Header header)
        throws MalformedCookieException
    {
        LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, String)");
        if(header == null)
            throw new IllegalArgumentException("Header may not be null.");
        else
            return parse(s, i, s1, flag, header.getValue());
    }

    public void parseAttribute(NameValuePair namevaluepair, Cookie cookie)
        throws MalformedCookieException
    {
        String s;
        String s1;
        if(namevaluepair == null)
            throw new IllegalArgumentException("Attribute may not be null.");
        if(cookie == null)
            throw new IllegalArgumentException("Cookie may not be null.");
        s = namevaluepair.getName().toLowerCase();
        s1 = namevaluepair.getValue();
        if(!s.equals("path")) goto _L2; else goto _L1
_L1:
        if(s1 == null || s1.trim().equals(""))
            s1 = "/";
        cookie.setPath(s1);
        cookie.setPathAttributeSpecified(true);
_L4:
        return;
_L2:
        if(s.equals("domain"))
        {
            if(s1 == null)
                throw new MalformedCookieException("Missing value for domain attribute");
            if(s1.trim().equals(""))
                throw new MalformedCookieException("Blank value for domain attribute");
            cookie.setDomain(s1);
            cookie.setDomainAttributeSpecified(true);
        } else
        if(s.equals("max-age"))
        {
            if(s1 == null)
                throw new MalformedCookieException("Missing value for max-age attribute");
            int i;
            try
            {
                i = Integer.parseInt(s1);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new MalformedCookieException("Invalid max-age attribute: " + numberformatexception.getMessage());
            }
            cookie.setExpiryDate(new Date(System.currentTimeMillis() + 1000L * (long)i));
        } else
        if(s.equals("secure"))
            cookie.setSecure(true);
        else
        if(s.equals("comment"))
            cookie.setComment(s1);
        else
        if(s.equals("expires"))
        {
            if(s1 == null)
                throw new MalformedCookieException("Missing value for expires attribute");
            try
            {
                cookie.setExpiryDate(DateUtil.parseDate(s1, datepatterns));
            }
            catch(DateParseException dateparseexception)
            {
                LOG.debug("Error parsing cookie date", dateparseexception);
                throw new MalformedCookieException("Unable to parse expiration date parameter: " + s1);
            }
        } else
        if(LOG.isDebugEnabled())
            LOG.debug("Unrecognized cookie attribute: " + namevaluepair.toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean pathMatch(String s, String s1)
    {
        boolean flag = s.startsWith(s1);
        if(flag && s.length() != s1.length() && !s1.endsWith("/"))
            if(s.charAt(s1.length()) == CookieSpec.PATH_DELIM_CHAR)
                flag = true;
            else
                flag = false;
        return flag;
    }

    public void setValidDateFormats(Collection collection)
    {
        datepatterns = collection;
    }

    public void validate(String s, int i, String s1, boolean flag, Cookie cookie)
        throws MalformedCookieException
    {
        LOG.trace("enter CookieSpecBase.validate(String, port, path, boolean, Cookie)");
        if(s == null)
            throw new IllegalArgumentException("Host of origin may not be null");
        if(s.trim().equals(""))
            throw new IllegalArgumentException("Host of origin may not be blank");
        if(i < 0)
            throw new IllegalArgumentException("Invalid port: " + i);
        if(s1 == null)
            throw new IllegalArgumentException("Path of origin may not be null.");
        if(s1.trim().equals(""))
            s1 = "/";
        String s2 = s.toLowerCase();
        if(cookie.getVersion() < 0)
            throw new MalformedCookieException("Illegal version number " + cookie.getValue());
        if(s2.indexOf(".") >= 0)
        {
            if(!s2.endsWith(cookie.getDomain()))
            {
                String s3 = cookie.getDomain();
                if(s3.startsWith("."))
                    s3 = s3.substring(1, s3.length());
                if(!s2.equals(s3))
                    throw new MalformedCookieException("Illegal domain attribute \"" + cookie.getDomain() + "\". Domain of origin: \"" + s2 + "\"");
            }
        } else
        if(!s2.equals(cookie.getDomain()))
            throw new MalformedCookieException("Illegal domain attribute \"" + cookie.getDomain() + "\". Domain of origin: \"" + s2 + "\"");
        if(!s1.startsWith(cookie.getPath()))
            throw new MalformedCookieException("Illegal path attribute \"" + cookie.getPath() + "\". Path of origin: \"" + s1 + "\"");
        else
            return;
    }

    protected static final Log LOG;
    static Class class$org$apache$commons$httpclient$cookie$CookieSpec;
    private Collection datepatterns;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$cookie$CookieSpec == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.cookie.CookieSpec");
            class$org$apache$commons$httpclient$cookie$CookieSpec = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$cookie$CookieSpec;
        }
        LOG = LogFactory.getLog(class1);
    }
}
