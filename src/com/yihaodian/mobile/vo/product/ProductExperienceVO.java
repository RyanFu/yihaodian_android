// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.product;

import java.util.Date;

public class ProductExperienceVO
{

    public ProductExperienceVO()
    {
        userName = null;
        content = null;
        contentGood = null;
        contentFail = null;
        createtime = new Date();
        ratingLog = new Integer(5);
    }

    public String getContent()
    {
        return content;
    }

    public String getContentFail()
    {
        return contentFail;
    }

    public String getContentGood()
    {
        return contentGood;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public Integer getRatingLog()
    {
        return ratingLog;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setContentFail(String s)
    {
        contentFail = s;
    }

    public void setContentGood(String s)
    {
        contentGood = s;
    }

    public void setCreatetime(Date date)
    {
        createtime = date;
    }

    public void setRatingLog(Integer integer)
    {
        ratingLog = integer;
    }

    public void setUserName(String s)
    {
        userName = s;
    }

    private String content;
    private String contentFail;
    private String contentGood;
    private Date createtime;
    private Integer ratingLog;
    private String userName;
}
