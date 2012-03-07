// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.bussiness.Trader;
import com.yihaodian.mobile.vo.system.DownloadVO;

public interface SystemService
{

    public abstract DownloadVO getClientApplicationDownloadUrl(Trader trader);

    public abstract Long getServerTimeStamp(Trader trader);
}
