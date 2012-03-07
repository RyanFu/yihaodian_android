// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;

public interface ProductService
{

    public abstract Page getBrandByCategoryId(Trader trader, Long long1, Integer integer, Integer integer1);

    public abstract Page getCategoryByRootCategoryId(Trader trader, Long long1, Long long2, Integer integer, Integer integer1);

    public abstract List getHomeHotProductTop5List(Trader trader, Long long1);

    public abstract Page getHotProductPageByCategoryId(Trader trader, Long long1, Long long2, Integer integer, Integer integer1);

    public abstract List getHotRandomProducts(Trader trader, Long long1);

    public abstract List getProductByBarcode(Trader trader, String s, Long long1);

    public abstract ProductVO getProductDetail(Trader trader, Long long1, Long long2);

    public abstract ProductVO getProductDetailComment(Trader trader, Long long1, Long long2);

    public abstract ProductVO getProductDetailDescription(Trader trader, Long long1, Long long2);

    public abstract Page getPromotionProductPage(Trader trader, Long long1, Long long2, Integer integer, Integer integer1);
}
