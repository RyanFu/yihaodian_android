// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.cookie;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.cookie:
//            CookieSpecBase, MalformedCookieException

public class RFC2109Spec extends CookieSpecBase
{

    public RFC2109Spec()
    {
        formatter.setAlwaysUseQuotes(true);
    }

    private void formatCookieAsVer(StringBuffer stringbuffer, Cookie cookie, int i)
    {
        String s = cookie.getValue();
        if(s == null)
            s = "";
        formatParam(stringbuffer, new NameValuePair(cookie.getName(), s), i);
        if(cookie.getPath() != null && cookie.isPathAttributeSpecified())
        {
            stringbuffer.append("; ");
            formatParam(stringbuffer, new NameValuePair("$Path", cookie.getPath()), i);
        }
        if(cookie.getDomain() != null && cookie.isDomainAttributeSpecified())
        {
            stringbuffer.append("; ");
            formatParam(stringbuffer, new NameValuePair("$Domain", cookie.getDomain()), i);
        }
    }

    private void formatParam(StringBuffer stringbuffer, NameValuePair namevaluepair, int i)
    {
        if(i < 1)
        {
            stringbuffer.append(namevaluepair.getName());
            stringbuffer.append("=");
            if(namevaluepair.getValue() != null)
                stringbuffer.append(namevaluepair.getValue());
        } else
        {
            formatter.format(stringbuffer, namevaluepair);
        }
    }

    @Override
	public boolean domainMatch(String s, String s1)
    {
        boolean flag;
        if(s.equals(s1) || s1.startsWith(".") && s.endsWith(s1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public String formatCookie(Cookie cookie)
    {
        CookieSpecBase.LOG.trace("enter RFC2109Spec.formatCookie(Cookie)");
        if(cookie == null)
        {
            throw new IllegalArgumentException("Cookie may not be null");
        } else
        {
            int i = cookie.getVersion();
            StringBuffer stringbuffer = new StringBuffer();
            formatParam(stringbuffer, new NameValuePair("$Version", Integer.toString(i)), i);
            stringbuffer.append("; ");
            formatCookieAsVer(stringbuffer, cookie, i);
            return stringbuffer.toString();
        }
    }

    @Override
	public String formatCookies(Cookie acookie[])
    {
        int i;
        int j;
        CookieSpecBase.LOG.trace("enter RFC2109Spec.formatCookieHeader(Cookie[])");
        i = 0x7fffffff;
        j = 0;
_L3:
        if(j < acookie.length) goto _L2; else goto _L1
_L1:
        StringBuffer stringbuffer;
        int k;
        stringbuffer = new StringBuffer();
        formatParam(stringbuffer, new NameValuePair("$Version", Integer.toString(i)), i);
        k = 0;
_L4:
        if(k >= acookie.length)
            return stringbuffer.toString();
        break MISSING_BLOCK_LABEL_92;
_L2:
        Cookie cookie = acookie[j];
        if(cookie.getVersion() < i)
            i = cookie.getVersion();
        j++;
          goto _L3
        stringbuffer.append("; ");
        formatCookieAsVer(stringbuffer, acookie[k], i);
        k++;
          goto _L4
    }

    @Override
	public void parseAttribute(NameValuePair namevaluepair, Cookie cookie)
        throws MalformedCookieException
    {
        if(namevaluepair == null)
            throw new IllegalArgumentException("Attribute may not be null.");
        if(cookie == null)
            throw new IllegalArgumentException("Cookie may not be null.");
        String s = namevaluepair.getName().toLowerCase();
        String s1 = namevaluepair.getValue();
        if(s.equals("path"))
        {
            if(s1 == null)
                throw new MalformedCookieException("Missing value for path attribute");
            if(s1.trim().equals(""))
                throw new MalformedCookieException("Blank value for path attribute");
            cookie.setPath(s1);
            cookie.setPathAttributeSpecified(true);
        } else
        if(s.equals("version"))
        {
            if(s1 == null)
                throw new MalformedCookieException("Missing value for version attribute");
            try
            {
                cookie.setVersion(Integer.parseInt(s1));
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new MalformedCookieException("Invalid version: " + numberformatexception.getMessage());
            }
        } else
        {
            super.parseAttribute(namevaluepair, cookie);
        }
    }

    @Override
	public void validate(String s, int i, String s1, boolean flag, Cookie cookie)
        throws MalformedCookieException
    {
        CookieSpecBase.LOG.trace("enter RFC2109Spec.validate(String, int, String, boolean, Cookie)");
        super.validate(s, i, s1, flag, cookie);
        if(cookie.getName().indexOf(' ') != -1)
            throw new MalformedCookieException("Cookie name may not contain blanks");
        if(cookie.getName().startsWith("$"))
            throw new MalformedCookieException("Cookie name may not start with $");
        if(cookie.isDomainAttributeSpecified() && !cookie.getDomain().equals(s))
        {
            if(!cookie.getDomain().startsWith("."))
                throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2109: domain must start with a dot");
            int j = cookie.getDomain().indexOf('.', 1);
            if(j < 0 || j == cookie.getDomain().length() - 1)
                throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2109: domain must contain an embedded dot");
            String s2 = s.toLowerCase();
            if(!s2.endsWith(cookie.getDomain()))
                throw new MalformedCookieException("Illegal domain attribute \"" + cookie.getDomain() + "\". Domain of origin: \"" + s2 + "\"");
            if(s2.substring(0, s2.length() - cookie.getDomain().length()).indexOf('.') != -1)
                throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates RFC 2109: host minus domain may not contain any dots");
        }
    }

    private final ParameterFormatter formatter = new ParameterFormatter();
}
