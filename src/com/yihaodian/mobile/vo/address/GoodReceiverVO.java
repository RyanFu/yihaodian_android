// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.address;


public class GoodReceiverVO
{

    public GoodReceiverVO()
    {
        id = null;
        mcsiteid = null;
        receiveName = null;
        address1 = null;
        receiverPhone = null;
        receiverMobile = null;
        receiverEmail = null;
        postCode = null;
        countryId = new Long(0L);
        countryName = "\u6D93\uE15E\u6D57";
        provinceId = null;
        provinceName = "";
        cityId = null;
        cityName = "";
        countyId = null;
        countyName = null;
        defaultAddressId = null;
    }

    public String getAddress1()
    {
        return address1;
    }

    public Long getCityId()
    {
        return cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public Long getCountryId()
    {
        return countryId;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public Long getCountyId()
    {
        return countyId;
    }

    public String getCountyName()
    {
        return countyName;
    }

    public Long getDefaultAddressId()
    {
        return defaultAddressId;
    }

    public Long getId()
    {
        return id;
    }

    public Long getMcsiteid()
    {
        return mcsiteid;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public Long getProvinceId()
    {
        return provinceId;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public String getReceiveName()
    {
        return receiveName;
    }

    public String getReceiverEmail()
    {
        return receiverEmail;
    }

    public String getReceiverMobile()
    {
        return receiverMobile;
    }

    public String getReceiverPhone()
    {
        return receiverPhone;
    }

    public String getRecordName()
    {
        return recordName;
    }

    public void setAddress1(String s)
    {
        address1 = s;
    }

    public void setCityId(Long long1)
    {
        cityId = long1;
    }

    public void setCityName(String s)
    {
        cityName = s;
    }

    public void setCountryId(Long long1)
    {
        countryId = long1;
    }

    public void setCountryName(String s)
    {
        countryName = s;
    }

    public void setCountyId(Long long1)
    {
        countyId = long1;
    }

    public void setCountyName(String s)
    {
        countyName = s;
    }

    public void setDefaultAddressId(Long long1)
    {
        defaultAddressId = long1;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setMcsiteid(Long long1)
    {
        mcsiteid = long1;
    }

    public void setPostCode(String s)
    {
        postCode = s;
    }

    public void setProvinceId(Long long1)
    {
        provinceId = long1;
    }

    public void setProvinceName(String s)
    {
        provinceName = s;
    }

    public void setReceiveName(String s)
    {
        receiveName = s;
    }

    public void setReceiverEmail(String s)
    {
        receiverEmail = s;
    }

    public void setReceiverMobile(String s)
    {
        receiverMobile = s;
    }

    public void setReceiverPhone(String s)
    {
        receiverPhone = s;
    }

    public void setRecordName(String s)
    {
        recordName = s;
    }

    private String address1;
    private Long cityId;
    private String cityName;
    private Long countryId;
    private String countryName;
    private Long countyId;
    private String countyName;
    private Long defaultAddressId;
    private Long id;
    private Long mcsiteid;
    private String postCode;
    private Long provinceId;
    private String provinceName;
    private String receiveName;
    private String receiverEmail;
    private String receiverMobile;
    private String receiverPhone;
    private String recordName;
}
