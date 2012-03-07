// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.message.InnerMessageVO;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class MyMessage
{

    public MyMessage()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        messageCountMethod = "getUnreadMessageCount";
        messageListMethod = "getMessageList";
        messageDelMethod = "deleteMessageById";
        messageDetailMethod = "getMessageDetailById";
        messageType = 1;
    }

    public MyMessage(int i, int j, int k)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        messageCountMethod = "getUnreadMessageCount";
        messageListMethod = "getMessageList";
        messageDelMethod = "deleteMessageById";
        messageDetailMethod = "getMessageDetailById";
        messageType = 1;
        messageType = i;
        currentPage = j;
        pageSize = k;
    }

    public MyMessage(long l)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        messageCountMethod = "getUnreadMessageCount";
        messageListMethod = "getMessageList";
        messageDelMethod = "deleteMessageById";
        messageDetailMethod = "getMessageDetailById";
        messageType = 1;
        messageId = l;
    }

    private String messageCountBody()
    {
        Object aobj[] = new Object[1];
        aobj[0] = User.token;
        return xStream.toXML(((aobj)));
    }

    private String messageDelBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = Long.valueOf(messageId);
        return xStream.toXML(((aobj)));
    }

    private String messageDetailBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = Long.valueOf(messageId);
        return xStream.toXML(((aobj)));
    }

    private String messageListBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = User.token;
        aobj[1] = Integer.valueOf(messageType);
        aobj[2] = Integer.valueOf(currentPage);
        aobj[3] = Integer.valueOf(pageSize);
        return xStream.toXML(((aobj)));
    }

    public Integer getMessageCount()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(messageCountMethod, messageCountBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Integer getMessageDel()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(messageDelMethod, messageDelBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public InnerMessageVO getMessageDetail()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(messageDetailMethod, messageDetailBody());
        return (InnerMessageVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Page getMessageList()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(messageListMethod, messageListBody());
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    private int currentPage;
    private DBHelper dbHelper;
    private String messageCountMethod;
    private String messageDelMethod;
    private String messageDetailMethod;
    private long messageId;
    private String messageListMethod;
    private int messageType;
    private int pageSize;
    private XStream xStream;
}
