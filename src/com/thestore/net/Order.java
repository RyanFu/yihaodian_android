// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import com.yihaodian.mobile.vo.order.InvoiceVO;
import com.yihaodian.mobile.vo.order.OrderVO;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Order
{

    public Order()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = 99;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        currentPage = 1;
        pageSize = 1;
        couponNumber = "";
        invoiceVO = null;
        couponPage = null;
        createSessionOrder = "createSessionOrder";
        getMyOrderListByToken = "getMyOrderListByToken";
        getOrderDetailByOrderId = "getOrderDetailByOrderId";
        getSessionOrder = "getSessionOrder";
        removeSessionOrder = "removeSessionOrder";
        saveCouponToSessionOrder = "saveCouponToSessionOrder";
        saveGoodReceiverToSessionOrder = "saveGoodReceiverToSessionOrder";
        saveInvoiceToSessionOrder = "saveInvoiceToSessionOrder";
        submitOrder = "submitOrder";
        cancelOrder = "cancelOrder";
        rebuyOrder = "rebuyOrder";
        getMyCouponList = "getMyCouponList";
    }

    public Order(int i)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = 99;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        currentPage = 1;
        pageSize = 1;
        couponNumber = "";
        invoiceVO = null;
        couponPage = null;
        createSessionOrder = "createSessionOrder";
        getMyOrderListByToken = "getMyOrderListByToken";
        getOrderDetailByOrderId = "getOrderDetailByOrderId";
        getSessionOrder = "getSessionOrder";
        removeSessionOrder = "removeSessionOrder";
        saveCouponToSessionOrder = "saveCouponToSessionOrder";
        saveGoodReceiverToSessionOrder = "saveGoodReceiverToSessionOrder";
        saveInvoiceToSessionOrder = "saveInvoiceToSessionOrder";
        submitOrder = "submitOrder";
        cancelOrder = "cancelOrder";
        rebuyOrder = "rebuyOrder";
        getMyCouponList = "getMyCouponList";
        setCurrentPage(i);
    }

    public Order(int i, int j)
    {
        this(i);
        pageSize = j;
    }

    public Order(InvoiceVO invoicevo)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = 99;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        currentPage = 1;
        pageSize = 1;
        couponNumber = "";
        invoiceVO = null;
        couponPage = null;
        createSessionOrder = "createSessionOrder";
        getMyOrderListByToken = "getMyOrderListByToken";
        getOrderDetailByOrderId = "getOrderDetailByOrderId";
        getSessionOrder = "getSessionOrder";
        removeSessionOrder = "removeSessionOrder";
        saveCouponToSessionOrder = "saveCouponToSessionOrder";
        saveGoodReceiverToSessionOrder = "saveGoodReceiverToSessionOrder";
        saveInvoiceToSessionOrder = "saveInvoiceToSessionOrder";
        submitOrder = "submitOrder";
        cancelOrder = "cancelOrder";
        rebuyOrder = "rebuyOrder";
        getMyCouponList = "getMyCouponList";
        invoiceVO = invoicevo;
    }

    public Order(Long long1, int i)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = 99;
        goodReceiverId = Long.valueOf(0L);
        orderId = Long.valueOf(0L);
        currentPage = 1;
        pageSize = 1;
        couponNumber = "";
        invoiceVO = null;
        couponPage = null;
        createSessionOrder = "createSessionOrder";
        getMyOrderListByToken = "getMyOrderListByToken";
        getOrderDetailByOrderId = "getOrderDetailByOrderId";
        getSessionOrder = "getSessionOrder";
        removeSessionOrder = "removeSessionOrder";
        saveCouponToSessionOrder = "saveCouponToSessionOrder";
        saveGoodReceiverToSessionOrder = "saveGoodReceiverToSessionOrder";
        saveInvoiceToSessionOrder = "saveInvoiceToSessionOrder";
        submitOrder = "submitOrder";
        cancelOrder = "cancelOrder";
        rebuyOrder = "rebuyOrder";
        getMyCouponList = "getMyCouponList";
        i;
        JVM INSTR tableswitch 2131296313 2131296321: default 192
    //                   2131296313 193
    //                   2131296314 192
    //                   2131296315 192
    //                   2131296316 201
    //                   2131296317 192
    //                   2131296318 192
    //                   2131296319 192
    //                   2131296320 193
    //                   2131296321 193;
           goto _L1 _L2 _L1 _L1 _L3 _L1 _L1 _L1 _L2 _L2
_L1:
        return;
_L2:
        orderId = long1;
        continue; /* Loop/switch isn't completed */
_L3:
        goodReceiverId = long1;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private String getCancelOrderBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = orderId;
        return xStream.toXML(((Object) (aobj)));
    }

    private String getOrderDetailByOrderIdBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = orderId;
        return xStream.toXML(((Object) (aobj)));
    }

    private String getOrderTokenBody()
    {
        Object aobj[] = new Object[1];
        aobj[0] = User.token;
        return xStream.toXML(((Object) (aobj)));
    }

    private String getRebuyOrderBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = orderId;
        return xStream.toXML(((Object) (aobj)));
    }

    private String getSaveCouponToSessionOrderBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = getCouponNumber();
        return xStream.toXML(((Object) (aobj)));
    }

    private String getSaveGoodReceiverToSessionOrderBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = goodReceiverId;
        return xStream.toXML(((Object) (aobj)));
    }

    private String getSaveInvoiceToSessionOrderBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = User.token;
        aobj[1] = getInvoiceVO().getTitle();
        aobj[2] = getInvoiceVO().getContent();
        aobj[3] = getInvoiceVO().getAmount();
        return xStream.toXML(((Object) (aobj)));
    }

    private String getThreeParamsBody()
    {
        Object aobj[] = new Object[3];
        aobj[0] = User.token;
        aobj[1] = Integer.valueOf(getCurrentPage());
        aobj[2] = Integer.valueOf(pageSize);
        return xStream.toXML(((Object) (aobj)));
    }

    public int cancelOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(cancelOrder, getCancelOrderBody());
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

    public int createSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(createSessionOrder, getOrderTokenBody());
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

    public String getCouponNumber()
    {
        return couponNumber;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public List getGoodReceiverListByToken()
    {
        List list;
        org.apache.http.HttpResponse httpresponse;
        list = null;
        httpresponse = dbHelper.getHttpResponse(getMyCouponList, getOrderTokenBody());
        List list1 = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        list = list1;
_L2:
        return list;
        Exception exception;
        exception;
        exception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public InvoiceVO getInvoiceVO()
    {
        return invoiceVO;
    }

    public Page getMyCouponList()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getMyCouponList, getThreeParamsBody());
        try
        {
            couponPage = (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return couponPage;
    }

    public Page getMyOrderListByToken()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getMyOrderListByToken, getThreeParamsBody());
        Page page1 = (Page)xStream.fromXML(dbHelper.getResultString(httpresponse));
        Page page = page1;
_L2:
        return page;
        Exception exception;
        exception;
        exception.printStackTrace();
        page = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public OrderVO getOrderDetailByOrderId()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getOrderDetailByOrderId, getOrderDetailByOrderIdBody());
        OrderVO ordervo1 = (OrderVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
        OrderVO ordervo = ordervo1;
_L2:
        return ordervo;
        Exception exception;
        exception;
        exception.printStackTrace();
        ordervo = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public OrderVO getSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getSessionOrder, getOrderTokenBody());
        OrderVO ordervo1 = (OrderVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
        OrderVO ordervo = ordervo1;
_L2:
        return ordervo;
        Exception exception;
        exception;
        exception.printStackTrace();
        ordervo = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int rebuyOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(rebuyOrder, getRebuyOrderBody());
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

    public int removeSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(removeSessionOrder, getOrderTokenBody());
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

    public int saveCouponToSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(saveCouponToSessionOrder, getSaveCouponToSessionOrderBody());
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

    public int saveGoodReceiverToSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(saveGoodReceiverToSessionOrder, getSaveGoodReceiverToSessionOrderBody());
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

    public int saveInvoiceToSessionOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(saveInvoiceToSessionOrder, getSaveInvoiceToSessionOrderBody());
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

    public void setCouponNumber(String s)
    {
        couponNumber = s;
    }

    public void setCurrentPage(int i)
    {
        currentPage = i;
    }

    public void setInvoiceVO(InvoiceVO invoicevo)
    {
        invoiceVO = invoicevo;
    }

    public int submitOrder()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(submitOrder, getOrderTokenBody());
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

    private String cancelOrder;
    private String couponNumber;
    private Page couponPage;
    private String createSessionOrder;
    private int currentPage;
    private DBHelper dbHelper;
    private String getMyCouponList;
    private String getMyOrderListByToken;
    private String getOrderDetailByOrderId;
    private String getSessionOrder;
    private Long goodReceiverId;
    private InvoiceVO invoiceVO;
    private Long orderId;
    private int pageSize;
    private String rebuyOrder;
    private String removeSessionOrder;
    private int result;
    private String saveCouponToSessionOrder;
    private String saveGoodReceiverToSessionOrder;
    private String saveInvoiceToSessionOrder;
    private String submitOrder;
    private XStream xStream;
}
