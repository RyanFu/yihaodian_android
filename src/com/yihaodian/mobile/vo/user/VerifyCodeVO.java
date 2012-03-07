// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.user;


public class VerifyCodeVO
{

    public VerifyCodeVO()
    {
        codeUrl = null;
        tempToken = null;
    }

    public String getCodeUrl()
    {
        return codeUrl;
    }

    public String getTempToken()
    {
        return tempToken;
    }

    public void setCodeUrl(String s)
    {
        codeUrl = s;
    }

    public void setTempToken(String s)
    {
        tempToken = s;
    }

    private String codeUrl;
    private String tempToken;
}
