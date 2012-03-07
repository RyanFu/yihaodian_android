// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.coupon;

import java.util.Date;

public class CouponVO
{

    public CouponVO()
    {
        id = null;
        mcsiteid = null;
        number = null;
        amount = null;
        beginTime = null;
        expiredTime = null;
        defineType = null;
        threshOld = null;
    }

    public Double getAmount()
    {
        return amount;
    }

    public Date getBeginTime()
    {
        return beginTime;
    }

    public Integer getDefineType()
    {
        return defineType;
    }

    public Date getExpiredTime()
    {
        return expiredTime;
    }

    public Long getId()
    {
        return id;
    }

    public Long getMcsiteid()
    {
        return mcsiteid;
    }

    public String getNumber()
    {
        return number;
    }

    public Double getThreshOld()
    {
        return threshOld;
    }

    public void setAmount(Double double1)
    {
        amount = double1;
    }

    public void setBeginTime(Date date)
    {
        beginTime = date;
    }

    public void setDefineType(Integer integer)
    {
        defineType = integer;
    }

    public void setExpiredTime(Date date)
    {
        expiredTime = date;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setMcsiteid(Long long1)
    {
        mcsiteid = long1;
    }

    public void setNumber(String s)
    {
        number = s;
    }

    public void setThreshOld(Double double1)
    {
        threshOld = double1;
    }

    private Double amount;
    private Date beginTime;
    private Integer defineType;
    private Date expiredTime;
    private Long id;
    private Long mcsiteid;
    private String number;
    private Double threshOld;
}
