// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.user;


public class UserVO
{

    public UserVO()
    {
        userId = null;
        username = "";
        email = "";
        userMobile = "";
        userPhone = "";
        boughtTimes = new Long(0L);
        cocode = null;
        pictureUrl = "";
        messageCount = new Long(0L);
        couponCount = new Long(0L);
        amount = new Double(0.0D);
        points = new Double(0.0D);
        vip = new Boolean(false);
    }

    public Double getAmount()
    {
        return amount;
    }

    public Long getBoughtTimes()
    {
        return boughtTimes;
    }

    public String getCocode()
    {
        return cocode;
    }

    public Long getCouponCount()
    {
        return couponCount;
    }

    public String getEmail()
    {
        return email;
    }

    public Long getMessageCount()
    {
        return messageCount;
    }

    public String getPictureUrl()
    {
        return pictureUrl;
    }

    public Double getPoints()
    {
        return points;
    }

    public Long getUserId()
    {
        return userId;
    }

    public String getUserMobile()
    {
        return userMobile;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public String getUsername()
    {
        return username;
    }

    public Boolean getVip()
    {
        return vip;
    }

    public void setAmount(Double double1)
    {
        amount = double1;
    }

    public void setBoughtTimes(Long long1)
    {
        boughtTimes = long1;
    }

    public void setCocode(String s)
    {
        cocode = s;
    }

    public void setCouponCount(Long long1)
    {
        couponCount = long1;
    }

    public void setEmail(String s)
    {
        email = s;
    }

    public void setMessageCount(Long long1)
    {
        messageCount = long1;
    }

    public void setPictureUrl(String s)
    {
        pictureUrl = s;
    }

    public void setPoints(Double double1)
    {
        points = double1;
    }

    public void setUserId(Long long1)
    {
        userId = long1;
    }

    public void setUserMobile(String s)
    {
        userMobile = s;
    }

    public void setUserPhone(String s)
    {
        userPhone = s;
    }

    public void setUsername(String s)
    {
        username = s;
    }

    public void setVip(Boolean boolean1)
    {
        vip = boolean1;
    }

    private Double amount;
    private Long boughtTimes;
    private String cocode;
    private Long couponCount;
    private String email;
    private Long messageCount;
    private String pictureUrl;
    private Double points;
    private Long userId;
    private String userMobile;
    private String userPhone;
    private String username;
    private Boolean vip;
}
