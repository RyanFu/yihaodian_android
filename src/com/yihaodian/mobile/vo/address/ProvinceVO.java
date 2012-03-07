// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.address;


public class ProvinceVO
{

    public ProvinceVO()
    {
        id = null;
        provinceName = null;
    }

    public Long getId()
    {
        return id;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setProvinceName(String s)
    {
        provinceName = s;
    }

    private Long id;
    private String provinceName;
}
