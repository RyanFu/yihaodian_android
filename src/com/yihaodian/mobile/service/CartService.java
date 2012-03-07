// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.cart.CartVO;

public interface CartService
{

    public abstract Integer addProduct(String s, Long long1, Long long2, Long long3);

    public abstract Integer delAllProduct(String s);

    public abstract Integer delProduct(String s, Long long1, Long long2, Integer integer);

    public abstract CartVO getSessionCart(String s);

    public abstract Integer updateCartItemQuantity(String s, Long long1, Long long2, Long long3, Integer integer);
}
