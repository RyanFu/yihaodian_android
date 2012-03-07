// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient.cookie;

import java.text.*;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.*;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient.cookie:
//            CookieSpecBase, MalformedCookieException

public class NetscapeDraftSpec extends CookieSpecBase
{

    public NetscapeDraftSpec()
    {
    }

    private static boolean isSpecialDomain(String s)
    {
        String s1 = s.toUpperCase();
        boolean flag;
        if(s1.endsWith(".COM") || s1.endsWith(".EDU") || s1.endsWith(".NET") || s1.endsWith(".GOV") || s1.endsWith(".MIL") || s1.endsWith(".ORG") || s1.endsWith(".INT"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public boolean domainMatch(String s, String s1)
    {
        return s.endsWith(s1);
    }

    @Override
	public Cookie[] parse(String s, int i, String s1, boolean flag, String s2)
        throws MalformedCookieException
    {
        Cookie cookie;
        NameValuePair anamevaluepair[];
        CookieSpecBase.LOG.trace("enter NetscapeDraftSpec.parse(String, port, path, boolean, Header)");
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
        String s3 = s.toLowerCase();
        String s4 = s1;
        int j = s4.lastIndexOf("/");
        if(j >= 0)
        {
            if(j == 0)
                j = 1;
            s4 = s4.substring(0, j);
        }
        HeaderElement headerelement = new HeaderElement(s2.toCharArray());
        cookie = new Cookie(s3, headerelement.getName(), headerelement.getValue(), s4, null, false);
        anamevaluepair = headerelement.getParameters();
        if(anamevaluepair == null) goto _L2; else goto _L1
_L1:
        int k = 0;
_L5:
        if(k < anamevaluepair.length) goto _L3; else goto _L2
_L2:
        Cookie acookie[] = new Cookie[1];
        acookie[0] = cookie;
        return acookie;
_L3:
        parseAttribute(anamevaluepair[k], cookie);
        k++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    @Override
	public void parseAttribute(NameValuePair namevaluepair, Cookie cookie)
        throws MalformedCookieException
    {
        String s1;
        if(namevaluepair == null)
            throw new IllegalArgumentException("Attribute may not be null.");
        if(cookie == null)
            throw new IllegalArgumentException("Cookie may not be null.");
        String s = namevaluepair.getName().toLowerCase();
        s1 = namevaluepair.getValue();
        if(!s.equals("expires"))
            break MISSING_BLOCK_LABEL_121;
        if(s1 == null)
            throw new MalformedCookieException("Missing value for expires attribute");
        cookie.setExpiryDate((new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US)).parse(s1));
_L1:
        return;
        ParseException parseexception;
        parseexception;
        throw new MalformedCookieException("Invalid expires attribute: " + parseexception.getMessage());
        super.parseAttribute(namevaluepair, cookie);
          goto _L1
    }

    @Override
	public void validate(String s, int i, String s1, boolean flag, Cookie cookie)
        throws MalformedCookieException
    {
        CookieSpecBase.LOG.trace("enterNetscapeDraftCookieProcessor RCF2109CookieProcessor.validate(Cookie)");
        super.validate(s, i, s1, flag, cookie);
        if(s.indexOf(".") >= 0)
        {
            int j = (new StringTokenizer(cookie.getDomain(), ".")).countTokens();
            if(isSpecialDomain(cookie.getDomain()))
            {
                if(j < 2)
                    throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates the Netscape cookie specification for " + "special domains");
            } else
            if(j < 3)
                throw new MalformedCookieException("Domain attribute \"" + cookie.getDomain() + "\" violates the Netscape cookie specification");
        }
    }
}
