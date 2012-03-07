// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.product.ProductVO;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Product
{

    public Product(Long long1)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        searchBarcode = "getProductByBarcode";
        productIntro = "getProductDetail";
        productDesc = "getProductDetailDescription";
        productComment = "getProductDetailComment";
        searchByNetNoteMethod = "searchByNetNote";
        productId = long1;
    }

    public Product(String s)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        searchBarcode = "getProductByBarcode";
        productIntro = "getProductDetail";
        productDesc = "getProductDetailDescription";
        productComment = "getProductDetailComment";
        searchByNetNoteMethod = "searchByNetNote";
        barcode = s;
    }

    public Product(List list1)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        searchBarcode = "getProductByBarcode";
        productIntro = "getProductDetail";
        productDesc = "getProductDetailDescription";
        productComment = "getProductDetailComment";
        searchByNetNoteMethod = "searchByNetNote";
        list = list1;
    }

    private String barCodeBody()
    {
        Object aobj[] = new Object[3];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = barcode;
        aobj[2] = Long.valueOf(User.province);
        return xStream.toXML(((aobj)));
    }

    private String noteBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = dbHelper.getMCSITE_ID();
        aobj[2] = Long.valueOf(User.province);
        aobj[3] = list;
        return xStream.toXML(((aobj)));
    }

    private String productDetailBody()
    {
        Object aobj[] = new Object[3];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = productId;
        aobj[2] = Long.valueOf(User.province);
        return xStream.toXML(((aobj)));
    }

    public List getBarcode()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(searchBarcode, barCodeBody());
        List list2 = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        List list1 = list2;
_L2:
        return list1;
        Exception exception;
        exception;
        list1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public List getNote()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(searchByNetNoteMethod, noteBody());
        return (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public ProductVO getProductDetail()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(productIntro, productDetailBody());
        ProductVO productvo1 = (ProductVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
        ProductVO productvo = productvo1;
_L2:
        return productvo;
        Exception exception;
        exception;
        exception.printStackTrace();
        productvo = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ProductVO getProductDetailComment()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(productComment, productDetailBody());
        ProductVO productvo1 = (ProductVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
        ProductVO productvo = productvo1;
_L2:
        return productvo;
        Exception exception;
        exception;
        exception.printStackTrace();
        productvo = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public ProductVO getProductDetailDescription()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(productDesc, productDetailBody());
        ProductVO productvo1 = (ProductVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
        ProductVO productvo = productvo1;
_L2:
        return productvo;
        Exception exception;
        exception;
        exception.printStackTrace();
        productvo = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private String barcode;
    private DBHelper dbHelper;
    private List list;
    private String productComment;
    private String productDesc;
    private Long productId;
    private String productIntro;
    private String searchBarcode;
    private String searchByNetNoteMethod;
    private XStream xStream;
}
