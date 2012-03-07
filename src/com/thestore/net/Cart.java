// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.cart.CartVO;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Cart
{

    public Cart()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        cartMethod = "getSessionCart";
        addCartMethod = "addProduct";
        delCartMethod = "delProduct";
        updateCartMethod = "updateCartItemQuantity";
        delAllProduct = "delAllProduct";
        quantity = 1L;
    }

    public Cart(long l)
    {
        this(l, 1L);
    }

    public Cart(long l, int i)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        cartMethod = "getSessionCart";
        addCartMethod = "addProduct";
        delCartMethod = "delProduct";
        updateCartMethod = "updateCartItemQuantity";
        delAllProduct = "delAllProduct";
        quantity = 1L;
        productId = l;
        updateType = i;
    }

    public Cart(long l, int i, long l1)
    {
        this(l, i);
        quantity = l1;
    }

    public Cart(long l, long l1)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        cartMethod = "getSessionCart";
        addCartMethod = "addProduct";
        delCartMethod = "delProduct";
        updateCartMethod = "updateCartItemQuantity";
        delAllProduct = "delAllProduct";
        quantity = 1L;
        productId = l;
        quantity = l1;
    }

    private String CartBody()
    {
        Object aobj[] = new Object[1];
        aobj[0] = User.token;
        return xStream.toXML(((aobj)));
    }

    private String addCartBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = User.token;
        aobj[1] = Long.valueOf(productId);
        aobj[2] = Long.valueOf(merchantId);
        aobj[3] = Long.valueOf(quantity);
        return xStream.toXML(((aobj)));
    }

    private String delCartBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = User.token;
        aobj[1] = Long.valueOf(productId);
        aobj[2] = Long.valueOf(merchantId);
        aobj[3] = Integer.valueOf(updateType);
        return xStream.toXML(((aobj)));
    }

    private String updateCartBody()
    {
        Object aobj[] = new Object[5];
        aobj[0] = User.token;
        aobj[1] = Long.valueOf(productId);
        aobj[2] = Long.valueOf(merchantId);
        aobj[3] = Long.valueOf(quantity);
        aobj[4] = Integer.valueOf(updateType);
        return xStream.toXML(((aobj)));
    }

    public Integer delAllProduct()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(delAllProduct, CartBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Integer getAddCart()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(addCartMethod, addCartBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public CartVO getCart()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(cartMethod, CartBody());
        return (CartVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Integer getDelCart()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(delCartMethod, delCartBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Integer getUpdateCart()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(updateCartMethod, updateCartBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public static long cartTotal;
    public static long merchantId;
    private String addCartMethod;
    private String cartMethod;
    private DBHelper dbHelper;
    private String delAllProduct;
    private String delCartMethod;
    private long productId;
    private long quantity;
    private String updateCartMethod;
    private int updateType;
    private XStream xStream;
}
