// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.core.Page;

public interface FavoriteService
{

    public abstract Integer addFavorite(String s, String s1, Long long1);

    public abstract Integer delFavorite(String s, Long long1);

    public abstract Page getMyFavoriteList(String s, String s1, Integer integer, Integer integer1);
}
