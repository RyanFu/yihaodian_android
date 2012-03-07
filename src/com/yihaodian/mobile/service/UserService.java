// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.user.UserVO;
import com.yihaodian.mobile.vo.user.VerifyCodeVO;

public interface UserService
{

    public abstract Integer changeProvince(String s, Long long1);

    public abstract Integer findPasswordByEmail(Trader trader, String s, String s1, String s2);

    public abstract UserVO getMyYihaodianSessionUser(String s);

    public abstract UserVO getSessionUser(String s);

    public abstract VerifyCodeVO getVerifyCodeUrl(Trader trader);

    public abstract String login(Trader trader, Long long1, String s, String s1);

    public abstract Integer logout(String s);

    public abstract Integer modifyPassword(String s, String s1, String s2, String s3);

    public abstract Integer register(Trader trader, String s, String s1, String s2, String s3);
}
