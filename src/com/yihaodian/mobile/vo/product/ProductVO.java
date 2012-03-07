// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.product;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.yihaodian.mobile.vo.product:
//            ProductRatingVO

public class ProductVO
{

    public ProductVO()
    {
        productId = null;
        merchantIds = new ArrayList(1);
        merchantId = null;
        code = null;
        cnName = null;
        brandName = null;
        enName = null;
        hotProductUrl = null;
        hotProductUrlForWinSys = null;
        midleDefaultProductUrl = null;
        midleProductUrl = null;
        miniDefaultProductUrl = null;
        maketPrice = new Double(0.0D);
        price = new Double(0.0D);
        canBuy = new Boolean(true);
        description = "";
        rating = null;
        advertisement = "";
    }

    public String getAdvertisement()
    {
        return advertisement;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public Boolean getCanBuy()
    {
        return canBuy;
    }

    public String getCnName()
    {
        return cnName;
    }

    public String getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

    public String getEnName()
    {
        return enName;
    }

    public String getHotProductUrl()
    {
        return hotProductUrl;
    }

    public String getHotProductUrlForWinSys()
    {
        return hotProductUrlForWinSys;
    }

    public Double getMaketPrice()
    {
        return maketPrice;
    }

    public Long getMerchantId()
    {
        return merchantId;
    }

    public List getMerchantIds()
    {
        return merchantIds;
    }

    public String getMidleDefaultProductUrl()
    {
        return midleDefaultProductUrl;
    }

    public String[] getMidleProductUrl()
    {
        return midleProductUrl;
    }

    public String getMiniDefaultProductUrl()
    {
        return miniDefaultProductUrl;
    }

    public Double getPrice()
    {
        return price;
    }

    public Long getProductId()
    {
        return productId;
    }

    public ProductRatingVO getRating()
    {
        return rating;
    }

    public void setAdvertisement(String s)
    {
        advertisement = s;
    }

    public void setBrandName(String s)
    {
        brandName = s;
    }

    public void setCanBuy(Boolean boolean1)
    {
        canBuy = boolean1;
    }

    public void setCnName(String s)
    {
        cnName = s;
    }

    public void setCode(String s)
    {
        code = s;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setEnName(String s)
    {
        enName = s;
    }

    public void setHotProductUrl(String s)
    {
        hotProductUrl = s;
    }

    public void setHotProductUrlForWinSys(String s)
    {
        hotProductUrlForWinSys = s;
    }

    public void setMaketPrice(Double double1)
    {
        maketPrice = double1;
    }

    public void setMerchantId(Long long1)
    {
        merchantId = long1;
    }

    public void setMerchantIds(List list)
    {
        merchantIds = list;
    }

    public void setMidleDefaultProductUrl(String s)
    {
        midleDefaultProductUrl = s;
    }

    public void setMidleProductUrl(String as[])
    {
        midleProductUrl = as;
    }

    public void setMiniDefaultProductUrl(String s)
    {
        miniDefaultProductUrl = s;
    }

    public void setPrice(Double double1)
    {
        price = double1;
    }

    public void setProductId(Long long1)
    {
        productId = long1;
    }

    public void setRating(ProductRatingVO productratingvo)
    {
        rating = productratingvo;
    }

    private String advertisement;
    private String brandName;
    private Boolean canBuy;
    private String cnName;
    private String code;
    private String description;
    private String enName;
    private String hotProductUrl;
    private String hotProductUrlForWinSys;
    private Double maketPrice;
    private Long merchantId;
    private List merchantIds;
    private String midleDefaultProductUrl;
    private String midleProductUrl[];
    private String miniDefaultProductUrl;
    private Double price;
    private Long productId;
    private ProductRatingVO rating;
}
