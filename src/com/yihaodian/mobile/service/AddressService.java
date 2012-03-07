// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.address.GoodReceiverVO;
import com.yihaodian.mobile.vo.bussiness.Trader;
import java.util.List;

public interface AddressService
{

    public abstract Integer deleteGoodReceiverByToken(String s, Long long1);

    public abstract List getAllProvince(Trader trader);

    public abstract List getCityByProvinceId(Trader trader, Long long1);

    public abstract List getCountyByCityId(Trader trader, Long long1);

    public abstract List getGoodReceiverListByToken(String s);

    public abstract Integer insertGoodReceiverByToken(String s, GoodReceiverVO goodreceivervo);

    public abstract Integer updateGoodReceiverByToken(String s, GoodReceiverVO goodreceivervo);
}
