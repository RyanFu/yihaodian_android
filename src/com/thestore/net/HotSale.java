// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class HotSale
{

    public HotSale()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        productList = null;
        getHotRandomProducts = "getHotRandomProducts";
    }

    private String getHotRandomProductsBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(User.province);
        return xStream.toXML(((aobj)));
    }

    public List getHotRandomProducts()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getHotRandomProducts, getHotRandomProductsBody());
        try
        {
            productList = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return productList;
    }

    private DBHelper dbHelper;
    private String getHotRandomProducts;
    private List productList;
    private XStream xStream;
}
