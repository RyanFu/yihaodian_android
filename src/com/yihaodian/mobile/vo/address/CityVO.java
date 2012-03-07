// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.address;


public class CityVO
{

    public CityVO()
    {
        id = null;
        cityName = null;
    }

    public String getCityName()
    {
        return cityName;
    }

    public Long getId()
    {
        return id;
    }

    public void setCityName(String s)
    {
        cityName = s;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    private String cityName;
    private Long id;
}
