// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.message;

import com.yihaodian.mobile.vo.user.UserVO;
import java.util.Date;

public class InnerMessageVO
{

    public InnerMessageVO()
    {
        id = null;
        mcsiteid = null;
        sender = null;
        content = null;
        messageType = null;
        messageTypeString = null;
        creTime = null;
        isNew = null;
    }

    public String getContent()
    {
        return content;
    }

    public Date getCreTime()
    {
        return creTime;
    }

    public Long getId()
    {
        return id;
    }

    public Integer getIsNew()
    {
        return isNew;
    }

    public Long getMcsiteid()
    {
        return mcsiteid;
    }

    public Integer getMessageType()
    {
        return messageType;
    }

    public String getMessageTypeString()
    {
        return messageTypeString;
    }

    public UserVO getSender()
    {
        return sender;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setCreTime(Date date)
    {
        creTime = date;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setIsNew(Integer integer)
    {
        isNew = integer;
    }

    public void setMcsiteid(Long long1)
    {
        mcsiteid = long1;
    }

    public void setMessageType(Integer integer)
    {
        messageType = integer;
    }

    public void setMessageTypeString(String s)
    {
        messageTypeString = s;
    }

    public void setSender(UserVO uservo)
    {
        sender = uservo;
    }

    private String content;
    private Date creTime;
    private Long id;
    private Integer isNew;
    private Long mcsiteid;
    private Integer messageType;
    private String messageTypeString;
    private UserVO sender;
}
