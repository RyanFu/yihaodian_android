// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.LangUtils;

// Referenced classes of package org.apache.commons.httpclient:
//            Credentials

public class UsernamePasswordCredentials
    implements Credentials
{

    public UsernamePasswordCredentials()
    {
    }

    public UsernamePasswordCredentials(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("Username:password string may not be null");
        int i = s.indexOf(':');
        if(i >= 0)
        {
            userName = s.substring(0, i);
            password = s.substring(i + 1);
        } else
        {
            userName = s;
        }
    }

    public UsernamePasswordCredentials(String s, String s1)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Username may not be null");
        } else
        {
            userName = s;
            password = s1;
            return;
        }
    }

    @Override
	public boolean equals(Object obj)
    {
        if(obj != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        if(this == obj)
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        if(getClass().equals(obj.getClass()))
        {
            UsernamePasswordCredentials usernamepasswordcredentials = (UsernamePasswordCredentials)obj;
            if(LangUtils.equals(userName, usernamepasswordcredentials.userName) && LangUtils.equals(password, usernamepasswordcredentials.password))
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
        }
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    @Override
	public int hashCode()
    {
        return LangUtils.hashCode(LangUtils.hashCode(17, userName), password);
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setUserName(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Username may not be null");
        } else
        {
            userName = s;
            return;
        }
    }

    @Override
	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(userName);
        stringbuffer.append(":");
        String s;
        if(password == null)
            s = "null";
        else
            s = password;
        stringbuffer.append(s);
        return stringbuffer.toString();
    }

    private String password;
    private String userName;
}
