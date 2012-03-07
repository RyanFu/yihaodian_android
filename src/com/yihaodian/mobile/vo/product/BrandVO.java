// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.product;


public class BrandVO
{

    public BrandVO()
    {
        id = null;
        brandName = null;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public Long getId()
    {
        return id;
    }

    public void setBrandName(String s)
    {
        brandName = s;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    private String brandName;
    private Long id;
}
