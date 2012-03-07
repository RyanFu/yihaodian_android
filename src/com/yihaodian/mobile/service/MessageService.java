// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.service;

import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.message.InnerMessageVO;

public interface MessageService
{

    public abstract Integer deleteMessageById(String s, Long long1);

    public abstract InnerMessageVO getMessageDetailById(String s, Long long1);

    public abstract Page getMessageList(String s, Integer integer, Integer integer1, Integer integer2);

    public abstract Integer getUnreadMessageCount(String s);
}
