// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.cart;

import java.util.ArrayList;
import java.util.List;

public class CartVO
{

    public CartVO()
    {
        totalquantity = new Long(0L);
        totalprice = new Double(0.0D);
        totalsavedprice = new Double(0.0D);
        buyItemList = new ArrayList();
        gifItemtList = new ArrayList();
    }

    public List getBuyItemList()
    {
        return buyItemList;
    }

    public List getGifItemtList()
    {
        return gifItemtList;
    }

    public Double getTotalprice()
    {
        return totalprice;
    }

    public Long getTotalquantity()
    {
        return totalquantity;
    }

    public Double getTotalsavedprice()
    {
        return totalsavedprice;
    }

    public void setBuyItemList(List list)
    {
        buyItemList = list;
    }

    public void setGifItemtList(List list)
    {
        gifItemtList = list;
    }

    public void setTotalprice(Double double1)
    {
        totalprice = double1;
    }

    public void setTotalquantity(Long long1)
    {
        totalquantity = long1;
    }

    public void setTotalsavedprice(Double double1)
    {
        totalsavedprice = double1;
    }

    private List buyItemList;
    private List gifItemtList;
    private Double totalprice;
    private Long totalquantity;
    private Double totalsavedprice;
}
