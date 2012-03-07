// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Type
{

    public Type()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        homeHotTopMethod = "getHomeHotProductTop5List";
        homeHotTopPageMethod = "getPromotionProductPage";
        typeHotTopPageMethod = "getHotProductPageByCategoryId";
        currentPage = -1;
        pageSize = -1;
        categoryId = Long.valueOf(-1L);
    }

    public Type(int i, int j, Long long1, Long long2)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        homeHotTopMethod = "getHomeHotProductTop5List";
        homeHotTopPageMethod = "getPromotionProductPage";
        typeHotTopPageMethod = "getHotProductPageByCategoryId";
        currentPage = -1;
        pageSize = -1;
        categoryId = Long.valueOf(-1L);
        currentPage = i;
        pageSize = j;
        categoryId = long2;
    }

    public Type(long l)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        homeHotTopMethod = "getHomeHotProductTop5List";
        homeHotTopPageMethod = "getPromotionProductPage";
        typeHotTopPageMethod = "getHotProductPageByCategoryId";
        currentPage = -1;
        pageSize = -1;
        categoryId = Long.valueOf(-1L);
        categoryId = Long.valueOf(l);
    }

    private String TypeHotPageBody()
    {
        Object aobj[] = new Object[5];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(User.province);
        aobj[2] = categoryId;
        aobj[3] = Integer.valueOf(1);
        aobj[4] = Integer.valueOf(10);
        return xStream.toXML(((aobj)));
    }

    private String getPromotionProductPageBody()
    {
        Object aobj[] = new Object[5];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(User.province);
        aobj[2] = categoryId;
        aobj[3] = Integer.valueOf(currentPage);
        aobj[4] = Integer.valueOf(pageSize);
        return xStream.toXML(((aobj)));
    }

    private String homeHotPageBody()
    {
        Object aobj[] = new Object[5];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(User.province);
        aobj[2] = null;
        aobj[3] = Integer.valueOf(1);
        aobj[4] = Integer.valueOf(10);
        return xStream.toXML(((aobj)));
    }

    private String homeHotTopBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(User.province);
        return xStream.toXML(((aobj)));
    }

    public Page getHomeHotPage()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(homeHotTopPageMethod, homeHotPageBody());
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public List getHomeHotTop()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(homeHotTopMethod, homeHotTopBody());
        return (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Page getPromotionProductPage()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(homeHotTopPageMethod, getPromotionProductPageBody());
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Page getTypeHotPage()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(typeHotTopPageMethod, TypeHotPageBody());
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    private Long categoryId;
    private int currentPage;
    private DBHelper dbHelper;
    private String homeHotTopMethod;
    private String homeHotTopPageMethod;
    private int pageSize;
    private String typeHotTopPageMethod;
    private XStream xStream;
}
