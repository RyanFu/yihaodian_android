// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRatingVO
{

    public ProductRatingVO()
    {
        totalExperiencesCount = new Long(0L);
        goodExperiencesCount = new Long(0L);
        middleExperiencesCount = new Long(0L);
        badExperiencesCount = new Long(0L);
        goodRating = new Double(0.0D);
        middleRating = new Double(0.0D);
        badRating = new Double(0.0D);
        top5Experience = new ArrayList();
    }

    public Long getBadExperiencesCount()
    {
        return badExperiencesCount;
    }

    public Double getBadRating()
    {
        return badRating;
    }

    public Long getGoodExperiencesCount()
    {
        return goodExperiencesCount;
    }

    public Double getGoodRating()
    {
        return goodRating;
    }

    public Long getMiddleExperiencesCount()
    {
        return middleExperiencesCount;
    }

    public Double getMiddleRating()
    {
        return middleRating;
    }

    public List getTop5Experience()
    {
        return top5Experience;
    }

    public Long getTotalExperiencesCount()
    {
        return totalExperiencesCount;
    }

    public void setBadExperiencesCount(Long long1)
    {
        badExperiencesCount = long1;
    }

    public void setBadRating(Double double1)
    {
        badRating = double1;
    }

    public void setGoodExperiencesCount(Long long1)
    {
        goodExperiencesCount = long1;
    }

    public void setGoodRating(Double double1)
    {
        goodRating = double1;
    }

    public void setMiddleExperiencesCount(Long long1)
    {
        middleExperiencesCount = long1;
    }

    public void setMiddleRating(Double double1)
    {
        middleRating = double1;
    }

    public void setTop5Experience(List list)
    {
        top5Experience = list;
    }

    public void setTotalExperiencesCount(Long long1)
    {
        totalExperiencesCount = long1;
    }

    private Long badExperiencesCount;
    private Double badRating;
    private Long goodExperiencesCount;
    private Double goodRating;
    private Long middleExperiencesCount;
    private Double middleRating;
    private List top5Experience;
    private Long totalExperiencesCount;
}
