// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Coupon
{

    public Coupon()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = 99;
        couponNumber = "";
        couponList = null;
        saveCouponToSessionOrder = "saveCouponToSessionOrder";
        getMyCouponList = "getMyCouponList";
    }

    public Coupon(String s)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = 99;
        couponNumber = "";
        couponList = null;
        saveCouponToSessionOrder = "saveCouponToSessionOrder";
        getMyCouponList = "getMyCouponList";
        couponNumber = s;
    }

    private String getMyCouponListBody()
    {
        Object aobj[] = new Object[3];
        aobj[0] = User.token;
        aobj[1] = Integer.valueOf(1);
        aobj[2] = Integer.valueOf(50);
        return xStream.toXML(((aobj)));
    }

    private String getSaveCouponBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = couponNumber;
        return xStream.toXML(((aobj)));
    }

    public Page getMyCouponList()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getMyCouponList, getMyCouponListBody());
        try
        {
            couponList = (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return couponList;
    }

    public int saveCouponToSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(saveCouponToSessionOrder, getSaveCouponBody());
        try
        {
            result = ((Integer)xStream.fromXML(dbHelper.getResultString(httpresponse))).intValue();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return result;
    }

    private Page couponList;
    private String couponNumber;
    private DBHelper dbHelper;
    private String getMyCouponList;
    private int result;
    private String saveCouponToSessionOrder;
    private XStream xStream;
}
