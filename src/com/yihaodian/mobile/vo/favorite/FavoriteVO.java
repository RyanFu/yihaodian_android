// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.favorite;

import com.yihaodian.mobile.vo.product.ProductVO;

public class FavoriteVO
{

    public FavoriteVO()
    {
        id = null;
        mcsiteid = null;
        product = null;
    }

    public Long getId()
    {
        return id;
    }

    public Long getMcsiteid()
    {
        return mcsiteid;
    }

    public ProductVO getProduct()
    {
        return product;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setMcsiteid(Long long1)
    {
        mcsiteid = long1;
    }

    public void setProduct(ProductVO productvo)
    {
        product = productvo;
    }

    private Long id;
    private Long mcsiteid;
    private ProductVO product;
}
