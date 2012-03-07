// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.product;


public class CategoryVO
{

    public CategoryVO()
    {
        id = null;
        categoryName = null;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public Long getId()
    {
        return id;
    }

    public void setCategoryName(String s)
    {
        categoryName = s;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    private String categoryName;
    private Long id;
}
