// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.system;


public class DownloadVO
{

    public DownloadVO()
    {
        downloadUrl = null;
        canUpdate = null;
        forceUpdate = null;
        remark = null;
    }

    public String getCanUpdate()
    {
        return canUpdate;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public String getForceUpdate()
    {
        return forceUpdate;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setCanUpdate(String s)
    {
        canUpdate = s;
    }

    public void setDownloadUrl(String s)
    {
        downloadUrl = s;
    }

    public void setForceUpdate(String s)
    {
        forceUpdate = s;
    }

    public void setRemark(String s)
    {
        remark = s;
    }

    private String canUpdate;
    private String downloadUrl;
    private String forceUpdate;
    private String remark;
}
