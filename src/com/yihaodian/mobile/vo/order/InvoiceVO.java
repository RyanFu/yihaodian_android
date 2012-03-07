// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.order;


public class InvoiceVO
{

    public InvoiceVO()
    {
        id = null;
        mcsiteid = null;
        title = null;
        content = null;
        amount = new Double(0.0D);
    }

    public Double getAmount()
    {
        return amount;
    }

    public String getContent()
    {
        return content;
    }

    public Long getId()
    {
        return id;
    }

    public Long getMcsiteid()
    {
        return mcsiteid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAmount(Double double1)
    {
        amount = double1;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setMcsiteid(Long long1)
    {
        mcsiteid = long1;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    private Double amount;
    private String content;
    private Long id;
    private Long mcsiteid;
    private String title;
}
