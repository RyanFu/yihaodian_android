// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import java.io.Serializable;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.util.LangUtils;
import org.apache.commons.logging.Log;

// Referenced classes of package org.apache.commons.httpclient:
//            NameValuePair

public class Cookie extends NameValuePair
    implements Serializable, Comparator
{

    public Cookie()
    {
        this(null, "noname", null, null, ((Date) (null)), false);
    }

    public Cookie(String s, String s1, String s2)
    {
        this(s, s1, s2, null, ((Date) (null)), false);
    }

    public Cookie(String s, String s1, String s2, String s3, int i, boolean flag)
    {
        this(s, s1, s2, s3, ((Date) (null)), flag);
        if(i < -1)
            throw new IllegalArgumentException("Invalid max age:  " + Integer.toString(i));
        if(i >= 0)
            setExpiryDate(new Date(System.currentTimeMillis() + 1000L * i));
    }

    public Cookie(String s, String s1, String s2, String s3, Date date, boolean flag)
    {
        super(s1, s2);
        hasPathAttribute = false;
        hasDomainAttribute = false;
        cookieVersion = 0;
        LOG.trace("enter Cookie(String, String, String, String, Date, boolean)");
        if(s1 == null)
            throw new IllegalArgumentException("Cookie name may not be null");
        if(s1.trim().equals(""))
        {
            throw new IllegalArgumentException("Cookie name may not be blank");
        } else
        {
            setPath(s3);
            setDomain(s);
            setExpiryDate(date);
            setSecure(flag);
            return;
        }
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

    @Override
	public int compare(Object obj, Object obj1)
    {
        LOG.trace("enter Cookie.compare(Object, Object)");
        if(!(obj instanceof Cookie))
            throw new ClassCastException(obj.getClass().getName());
        if(!(obj1 instanceof Cookie))
            throw new ClassCastException(obj1.getClass().getName());
        Cookie cookie = (Cookie)obj;
        Cookie cookie1 = (Cookie)obj1;
        int i;
        if(cookie.getPath() == null && cookie1.getPath() == null)
            i = 0;
        else
        if(cookie.getPath() == null)
        {
            if(cookie1.getPath().equals("/"))
                i = 0;
            else
                i = -1;
        } else
        if(cookie1.getPath() == null)
        {
            if(cookie.getPath().equals("/"))
                i = 0;
            else
                i = 1;
        } else
        {
            i = STRING_COLLATOR.compare(cookie.getPath(), cookie1.getPath());
        }
        return i;
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(this == obj)
            flag = true;
        else
        if(obj instanceof Cookie)
        {
            Cookie cookie = (Cookie)obj;
            if(LangUtils.equals(getName(), cookie.getName()) && LangUtils.equals(cookieDomain, cookie.cookieDomain) && LangUtils.equals(cookiePath, cookie.cookiePath))
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String getComment()
    {
        return cookieComment;
    }

    public String getDomain()
    {
        return cookieDomain;
    }

    public Date getExpiryDate()
    {
        return cookieExpiryDate;
    }

    public String getPath()
    {
        return cookiePath;
    }

    public boolean getSecure()
    {
        return isSecure;
    }

    public int getVersion()
    {
        return cookieVersion;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, getName()), cookieDomain), cookiePath);
    }

    public boolean isDomainAttributeSpecified()
    {
        return hasDomainAttribute;
    }

    public boolean isExpired()
    {
        boolean flag;
        if(cookieExpiryDate != null && cookieExpiryDate.getTime() <= System.currentTimeMillis())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isExpired(Date date)
    {
        boolean flag;
        if(cookieExpiryDate != null && cookieExpiryDate.getTime() <= date.getTime())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPathAttributeSpecified()
    {
        return hasPathAttribute;
    }

    public boolean isPersistent()
    {
        boolean flag;
        if(cookieExpiryDate != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setComment(String s)
    {
        cookieComment = s;
    }

    public void setDomain(String s)
    {
        if(s != null)
        {
            int i = s.indexOf(":");
            if(i != -1)
                s = s.substring(0, i);
            cookieDomain = s.toLowerCase();
        }
    }

    public void setDomainAttributeSpecified(boolean flag)
    {
        hasDomainAttribute = flag;
    }

    public void setExpiryDate(Date date)
    {
        cookieExpiryDate = date;
    }

    public void setPath(String s)
    {
        cookiePath = s;
    }

    public void setPathAttributeSpecified(boolean flag)
    {
        hasPathAttribute = flag;
    }

    public void setSecure(boolean flag)
    {
        isSecure = flag;
    }

    public void setVersion(int i)
    {
        cookieVersion = i;
    }

    public String toExternalForm()
    {
        CookieSpec cookiespec;
        if(getVersion() > 0)
            cookiespec = CookiePolicy.getDefaultSpec();
        else
            cookiespec = CookiePolicy.getCookieSpec("netscape");
        return cookiespec.formatCookie(this);
    }

    @Override
	public String toString()
    {
        return toExternalForm();
    }

    private static final Log LOG;
    private static final RuleBasedCollator STRING_COLLATOR = (RuleBasedCollator)Collator.getInstance(new Locale("en", "US", ""));
    static Class class$org$apache$commons$httpclient$Cookie;
    private String cookieComment;
    private String cookieDomain;
    private Date cookieExpiryDate;
    private String cookiePath;
    private int cookieVersion;
    private boolean hasDomainAttribute;
    private boolean hasPathAttribute;
    private boolean isSecure;

    static 
    {
        Class class1;
        if(class$org$apache$commons$httpclient$Cookie == null)
        {
            class1 = _mthclass$("org.apache.commons.httpclient.Cookie");
            class$org$apache$commons$httpclient$Cookie = class1;
        } else
        {
            class1 = class$org$apache$commons$httpclient$Cookie;
        }
        LOG = LogFactory.getLog(class1);
    }
}
