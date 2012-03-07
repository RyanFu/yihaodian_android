// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.order;

import com.yihaodian.mobile.vo.address.GoodReceiverVO;
import com.yihaodian.mobile.vo.coupon.CouponVO;
import java.io.Serializable;
import java.util.*;

public class OrderVO
    implements Serializable
{

    public OrderVO()
    {
        orderId = null;
        orderCode = null;
        orderAmount = new Double(0.0D);
        productAmount = new Double(0.0D);
        deliveryAmount = new Double(0.0D);
        accountAmount = new Double(0.0D);
        couponAmount = new Double(0.0D);
        paymentAccount = new Double(0.0D);
        productCount = new Long(0L);
        orderCreateTime = new Date();
        orderItemList = new ArrayList();
        orderStatus = new Integer(0);
        orderStatusForString = "";
        goodReceiver = null;
        expectReceiveDateTo = new Date();
        invoiceList = new ArrayList();
        coupon = null;
        deliveryMethodForString = null;
        paymentMethodForString = null;
    }

    public static long getSerialVersionUID()
    {
        return 0x27639080d6f6393cL;
    }

    public Double getAccountAmount()
    {
        return accountAmount;
    }

    public CouponVO getCoupon()
    {
        return coupon;
    }

    public Double getCouponAmount()
    {
        return couponAmount;
    }

    public Double getDeliveryAmount()
    {
        return deliveryAmount;
    }

    public String getDeliveryMethodForString()
    {
        return deliveryMethodForString;
    }

    public Date getExpectReceiveDateTo()
    {
        return expectReceiveDateTo;
    }

    public GoodReceiverVO getGoodReceiver()
    {
        return goodReceiver;
    }

    public List getInvoiceList()
    {
        return invoiceList;
    }

    public Double getOrderAmount()
    {
        return orderAmount;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public Date getOrderCreateTime()
    {
        return orderCreateTime;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public List getOrderItemList()
    {
        return orderItemList;
    }

    public Integer getOrderStatus()
    {
        return orderStatus;
    }

    public String getOrderStatusForString()
    {
        return orderStatusForString;
    }

    public Double getPaymentAccount()
    {
        return paymentAccount;
    }

    public String getPaymentMethodForString()
    {
        return paymentMethodForString;
    }

    public Double getProductAmount()
    {
        return productAmount;
    }

    public Long getProductCount()
    {
        return productCount;
    }

    public void setAccountAmount(Double double1)
    {
        accountAmount = double1;
    }

    public void setCoupon(CouponVO couponvo)
    {
        coupon = couponvo;
    }

    public void setCouponAmount(Double double1)
    {
        couponAmount = double1;
    }

    public void setDeliveryAmount(Double double1)
    {
        deliveryAmount = double1;
    }

    public void setDeliveryMethodForString(String s)
    {
        deliveryMethodForString = s;
    }

    public void setExpectReceiveDateTo(Date date)
    {
        expectReceiveDateTo = date;
    }

    public void setGoodReceiver(GoodReceiverVO goodreceivervo)
    {
        goodReceiver = goodreceivervo;
    }

    public void setInvoiceList(List list)
    {
        invoiceList = list;
    }

    public void setOrderAmount(Double double1)
    {
        orderAmount = double1;
    }

    public void setOrderCode(String s)
    {
        orderCode = s;
    }

    public void setOrderCreateTime(Date date)
    {
        orderCreateTime = date;
    }

    public void setOrderId(Long long1)
    {
        orderId = long1;
    }

    public void setOrderItemList(List list)
    {
        orderItemList = list;
    }

    public void setOrderStatus(Integer integer)
    {
        orderStatus = integer;
    }

    public void setOrderStatusForString(String s)
    {
        orderStatusForString = s;
    }

    public void setPaymentAccount(Double double1)
    {
        paymentAccount = double1;
    }

    public void setPaymentMethodForString(String s)
    {
        paymentMethodForString = s;
    }

    public void setProductAmount(Double double1)
    {
        productAmount = double1;
    }

    public void setProductCount(Long long1)
    {
        productCount = long1;
    }

    private static final long serialVersionUID = 0x27639080d6f6393cL;
    private Double accountAmount;
    private CouponVO coupon;
    private Double couponAmount;
    private Double deliveryAmount;
    private String deliveryMethodForString;
    private Date expectReceiveDateTo;
    private GoodReceiverVO goodReceiver;
    private List invoiceList;
    private Double orderAmount;
    private String orderCode;
    private Date orderCreateTime;
    private Long orderId;
    private List orderItemList;
    private Integer orderStatus;
    private String orderStatusForString;
    private Double paymentAccount;
    private String paymentMethodForString;
    private Double productAmount;
    private Long productCount;
}
