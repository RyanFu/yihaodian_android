// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.order;

import com.yihaodian.mobile.vo.product.ProductVO;

public class OrderItemVO
{

    public OrderItemVO()
    {
        product = null;
        buyQuantity = null;
    }

    public Integer getBuyQuantity()
    {
        return buyQuantity;
    }

    public ProductVO getProduct()
    {
        return product;
    }

    public void setBuyQuantity(Integer integer)
    {
        buyQuantity = integer;
    }

    public void setProduct(ProductVO productvo)
    {
        product = productvo;
    }

    private Integer buyQuantity;
    private ProductVO product;
}
