// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.address.GoodReceiverVO;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Receiver
{

    public Receiver()
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        goodReceiverVO = null;
        goodReceiverList = null;
        provinceList = null;
        cityList = null;
        countyList = null;
        result = -1;
        provinceId = Long.valueOf(-1L);
        cityId = Long.valueOf(-1L);
        getGoodReceiverListByToken = "getGoodReceiverListByToken";
        insertGoodReceiverByToken = "insertGoodReceiverByToken";
        updateGoodReceiverByToken = "updateGoodReceiverByToken";
        deleteGoodReceiverByToken = "deleteGoodReceiverByToken";
        getAllProvince = "getAllProvince";
        getCityByProvinceId = "getCityByProvinceId";
        getCountyByCityId = "getCountyByCityId";
    }

    public Receiver(GoodReceiverVO goodreceivervo)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        goodReceiverVO = null;
        goodReceiverList = null;
        provinceList = null;
        cityList = null;
        countyList = null;
        result = -1;
        provinceId = Long.valueOf(-1L);
        cityId = Long.valueOf(-1L);
        getGoodReceiverListByToken = "getGoodReceiverListByToken";
        insertGoodReceiverByToken = "insertGoodReceiverByToken";
        updateGoodReceiverByToken = "updateGoodReceiverByToken";
        deleteGoodReceiverByToken = "deleteGoodReceiverByToken";
        getAllProvince = "getAllProvince";
        getCityByProvinceId = "getCityByProvinceId";
        getCountyByCityId = "getCountyByCityId";
        goodReceiverVO = goodreceivervo;
    }

    public Receiver(Long long1, int i)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        goodReceiverVO = null;
        goodReceiverList = null;
        provinceList = null;
        cityList = null;
        countyList = null;
        result = -1;
        provinceId = Long.valueOf(-1L);
        cityId = Long.valueOf(-1L);
        getGoodReceiverListByToken = "getGoodReceiverListByToken";
        insertGoodReceiverByToken = "insertGoodReceiverByToken";
        updateGoodReceiverByToken = "updateGoodReceiverByToken";
        deleteGoodReceiverByToken = "deleteGoodReceiverByToken";
        getAllProvince = "getAllProvince";
        getCityByProvinceId = "getCityByProvinceId";
        getCountyByCityId = "getCountyByCityId";
        i;
        JVM INSTR tableswitch 2131296329 2131296330: default 140
    //                   2131296329 141
    //                   2131296330 149;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        provinceId = long1;
        continue; /* Loop/switch isn't completed */
_L3:
        cityId = long1;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private String deleteGoodReceiverByTokenBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = goodReceiverVO.getId();
        return xStream.toXML(((aobj)));
    }

    private String getAllProvinceBody()
    {
        Object aobj[] = new Object[1];
        aobj[0] = DBHelper.getTrader();
        return xStream.toXML(((aobj)));
    }

    private String getCityByProvinceIdBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = provinceId;
        return xStream.toXML(((aobj)));
    }

    private String getCountyByCityIdBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = cityId;
        return xStream.toXML(((aobj)));
    }

    private String getTokenBody()
    {
        Object aobj[] = new Object[1];
        aobj[0] = User.token;
        return xStream.toXML(((aobj)));
    }

    private String getTwoParamBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = goodReceiverVO;
        return xStream.toXML(((aobj)));
    }

    public int deleteGoodReceiverByToken()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(deleteGoodReceiverByToken, deleteGoodReceiverByTokenBody());
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

    public List getAllProvince()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getAllProvince, getAllProvinceBody());
        try
        {
            provinceList = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return provinceList;
    }

    public List getCityByProvinceId()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getCityByProvinceId, getCityByProvinceIdBody());
        try
        {
            cityList = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return cityList;
    }

    public List getCountyByCityId()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getCountyByCityId, getCountyByCityIdBody());
        try
        {
            countyList = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return countyList;
    }

    public List getGoodReceiverListByToken()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(getGoodReceiverListByToken, getTokenBody());
        try
        {
            goodReceiverList = (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return goodReceiverList;
    }

    public int insertGoodReceiverByToken()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(insertGoodReceiverByToken, getTwoParamBody());
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

    public int updateGoodReceiverByToken()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(updateGoodReceiverByToken, getTwoParamBody());
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

    private Long cityId;
    private List cityList;
    private List countyList;
    private DBHelper dbHelper;
    private String deleteGoodReceiverByToken;
    private String getAllProvince;
    private String getCityByProvinceId;
    private String getCountyByCityId;
    private String getGoodReceiverListByToken;
    private List goodReceiverList;
    private GoodReceiverVO goodReceiverVO;
    private String insertGoodReceiverByToken;
    private Long provinceId;
    private List provinceList;
    private int result;
    private String updateGoodReceiverByToken;
    private XStream xStream;
}
