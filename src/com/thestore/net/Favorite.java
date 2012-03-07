// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Favorite
{

    public Favorite(int i, int j)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        addFavoriteMethod = "addFavorite";
        delFavoriteMethod = "delFavorite";
        getFavoriteMethod = "getMyFavoriteList";
        currentPage = i;
        pageSize = j;
    }

    public Favorite(long l)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        addFavoriteMethod = "addFavorite";
        delFavoriteMethod = "delFavorite";
        getFavoriteMethod = "getMyFavoriteList";
        productId = l;
    }

    private String addFavoriteBody()
    {
        Object aobj[] = new Object[3];
        aobj[0] = User.token;
        aobj[1] = null;
        aobj[2] = Long.valueOf(productId);
        return xStream.toXML(((aobj)));
    }

    private String delFavoriteBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = Long.valueOf(productId);
        return xStream.toXML(((aobj)));
    }

    private String getFavoriteBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = User.token;
        aobj[1] = null;
        aobj[2] = Integer.valueOf(currentPage);
        aobj[3] = Integer.valueOf(pageSize);
        return xStream.toXML(((aobj)));
    }

    public Integer getAddFavorite()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(addFavoriteMethod, addFavoriteBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Integer getDelFavorite()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(delFavoriteMethod, delFavoriteBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Page getListFavorite()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getFavoriteMethod, getFavoriteBody());
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    private String addFavoriteMethod;
    private int currentPage;
    private DBHelper dbHelper;
    private String delFavoriteMethod;
    private String getFavoriteMethod;
    private int pageSize;
    private long productId;
    private XStream xStream;
}
