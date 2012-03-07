// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.order.OrderVO;

public interface OrderService
{

    public abstract Integer cancelOrder(String s, Long long1);

    public abstract Integer createSessionOrder(String s);

    public abstract Page getMyOrderListByToken(String s, Integer integer, Integer integer1);

    public abstract OrderVO getOrderDetailByOrderId(String s, Long long1);

    public abstract OrderVO getSessionOrder(String s);

    public abstract Integer rebuyOrder(String s, Long long1);

    public abstract Integer removeSessionOrder(String s);

    public abstract Integer saveCouponToSessionOrder(String s, String s1);

    public abstract Integer saveGoodReceiverToSessionOrder(String s, Long long1);

    public abstract Integer saveInvoiceToSessionOrder(String s, String s1, String s2, Double double1);

    public abstract Integer submitOrder(String s);
}
