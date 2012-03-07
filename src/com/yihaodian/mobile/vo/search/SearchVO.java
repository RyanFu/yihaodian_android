// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.search;


public class SearchVO
{

    public SearchVO()
    {
        keyword = null;
        minPrice = null;
        searchCount = null;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public Double getMinPrice()
    {
        return minPrice;
    }

    public Long getSearchCount()
    {
        return searchCount;
    }

    public void setKeyword(String s)
    {
        keyword = s;
    }

    public void setMinPrice(Double double1)
    {
        minPrice = double1;
    }

    public void setSearchCount(Long long1)
    {
        searchCount = long1;
    }

    private String keyword;
    private Double minPrice;
    private Long searchCount;
}
