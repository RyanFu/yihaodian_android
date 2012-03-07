// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import org.apache.commons.httpclient.*;

// Referenced classes of package org.apache.commons.httpclient.cookie:
//            CookieSpec, MalformedCookieException

public class IgnoreCookiesSpec
    implements CookieSpec
{

    public IgnoreCookiesSpec()
    {
    }

    @Override
	public boolean domainMatch(String s, String s1)
    {
        return false;
    }

    @Override
	public String formatCookie(Cookie cookie)
    {
        return null;
    }

    @Override
	public Header formatCookieHeader(Cookie cookie)
        throws IllegalArgumentException
    {
        return null;
    }

    @Override
	public Header formatCookieHeader(Cookie acookie[])
        throws IllegalArgumentException
    {
        return null;
    }

    @Override
	public String formatCookies(Cookie acookie[])
        throws IllegalArgumentException
    {
        return null;
    }

    @Override
	public Collection getValidDateFormats()
    {
        return null;
    }

    @Override
	public boolean match(String s, int i, String s1, boolean flag, Cookie cookie)
    {
        return false;
    }

    @Override
	public Cookie[] match(String s, int i, String s1, boolean flag, Cookie acookie[])
    {
        return new Cookie[0];
    }

    @Override
	public Cookie[] parse(String s, int i, String s1, boolean flag, String s2)
        throws MalformedCookieException
    {
        return new Cookie[0];
    }

    @Override
	public Cookie[] parse(String s, int i, String s1, boolean flag, Header header)
        throws MalformedCookieException, IllegalArgumentException
    {
        return new Cookie[0];
    }

    @Override
	public void parseAttribute(NameValuePair namevaluepair, Cookie cookie)
        throws MalformedCookieException, IllegalArgumentException
    {
    }

    @Override
	public boolean pathMatch(String s, String s1)
    {
        return false;
    }

    @Override
	public void setValidDateFormats(Collection collection)
    {
    }

    @Override
	public void validate(String s, int i, String s1, boolean flag, Cookie cookie)
        throws MalformedCookieException, IllegalArgumentException
    {
    }
}
