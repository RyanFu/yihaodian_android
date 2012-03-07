// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.address;


public class CountyVO
{

    public CountyVO()
    {
        id = null;
        countyName = null;
        postcode = null;
    }

    public String getCountyName()
    {
        return countyName;
    }

    public Long getId()
    {
        return id;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setCountyName(String s)
    {
        countyName = s;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setPostcode(String s)
    {
        postcode = s;
    }

    private String countyName;
    private Long id;
    private String postcode;
}
