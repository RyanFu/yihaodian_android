// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.cart;

import com.yihaodian.mobile.vo.product.ProductVO;

public class CartItemVO
{

    public CartItemVO()
    {
        product = null;
        buyQuantity = new Integer(0);
        updateType = null;
    }

    public Integer getBuyQuantity()
    {
        return buyQuantity;
    }

    public ProductVO getProduct()
    {
        return product;
    }

    public Integer getUpdateType()
    {
        return updateType;
    }

    public void setBuyQuantity(Integer integer)
    {
        buyQuantity = integer;
    }

    public void setProduct(ProductVO productvo)
    {
        product = productvo;
    }

    public void setUpdateType(Integer integer)
    {
        updateType = integer;
    }

    private Integer buyQuantity;
    private ProductVO product;
    private Integer updateType;
}
