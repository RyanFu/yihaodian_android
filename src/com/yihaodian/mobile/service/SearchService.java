// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.core.Page;
import java.util.List;

public interface SearchService
{

    public abstract List getSearchKeyWord(Trader trader, Long long1, String s);

    public abstract List searchByNetNote(Trader trader, Long long1, Long long2, List list);

    public abstract Page searchProduct(Trader trader, Long long1, Long long2, String s, Long long3, Long long4, Integer integer, 
            Integer integer1, Integer integer2);

    public abstract List searchProductForList(Trader trader, List list, List list1, List list2, List list3, List list4, List list5, 
            List list6, List list7);

    public static final int ST_AGE = 6;
    public static final int ST_APPRAISAL = 5;
    public static final int ST_NONE = 0;
    public static final int ST_PRICE_ASC = 3;
    public static final int ST_PRICE_DESC = 4;
    public static final int ST_RELATED = 1;
    public static final int ST_SALENUMBER = 2;
}
