// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Category
{

    public Category(long l)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        rootid = 0L;
        mcsite = 1L;
        getCategoryByRootCategoryId = "getCategoryByRootCategoryId";
        getCategoryProducts = "searchProduct";
        rootid = l;
    }

    public Page getCategoryByRootCategoryId()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getCategoryByRootCategoryId, getCategoryByRootCategoryIdBody());
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    protected String getCategoryByRootCategoryIdBody()
    {
        Object aobj[] = new Object[5];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(mcsite);
        aobj[2] = Long.valueOf(rootid);
        aobj[3] = Integer.valueOf(1);
        aobj[4] = Integer.valueOf(50);
        return xStream.toXML(((aobj)));
    }

    public Page getCategoryProduct(long l, int i, int j)
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getCategoryProducts, getCategoryProductsBody(l, i, j));
        return (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    protected String getCategoryProductsBody(long l, int i, int j)
    {
        Object aobj[] = new Object[9];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(mcsite);
        aobj[2] = Long.valueOf(User.province);
        aobj[3] = "";
        aobj[4] = Long.valueOf(l);
        aobj[5] = Long.valueOf(0L);
        aobj[6] = Integer.valueOf(i);
        aobj[7] = Integer.valueOf(j);
        aobj[8] = Integer.valueOf(10);
        return xStream.toXML(((aobj)));
    }

    private DBHelper dbHelper;
    private String getCategoryByRootCategoryId;
    private String getCategoryProducts;
    private long mcsite;
    private long rootid;
    private XStream xStream;
}
