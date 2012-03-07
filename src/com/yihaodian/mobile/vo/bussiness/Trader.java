// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.bussiness;

import java.io.Serializable;

public class Trader
    implements Serializable
{

    public Trader()
    {
        traderName = null;
        traderPassword = null;
        clientSystem = null;
        clientVersion = null;
        protocol = null;
        interfaceVersion = null;
        clientAppVersion = null;
        clientTelnetPhone = null;
        provinceId = null;
        userToken = null;
        unionKey = null;
    }

    public String getClientAppVersion()
    {
        return clientAppVersion;
    }

    public String getClientSystem()
    {
        return clientSystem;
    }

    public String getClientTelnetPhone()
    {
        return clientTelnetPhone;
    }

    public String getClientVersion()
    {
        return clientVersion;
    }

    public String getInterfaceVersion()
    {
        return interfaceVersion;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public String getProvinceId()
    {
        return provinceId;
    }

    public String getTraderName()
    {
        return traderName;
    }

    public String getTraderPassword()
    {
        return traderPassword;
    }

    public String getUnionKey()
    {
        return unionKey;
    }

    public String getUserToken()
    {
        return userToken;
    }

    public void setClientAppVersion(String s)
    {
        clientAppVersion = s;
    }

    public void setClientSystem(String s)
    {
        clientSystem = s;
    }

    public void setClientTelnetPhone(String s)
    {
        clientTelnetPhone = s;
    }

    public void setClientVersion(String s)
    {
        clientVersion = s;
    }

    public void setInterfaceVersion(String s)
    {
        interfaceVersion = s;
    }

    public void setProtocol(String s)
    {
        protocol = s;
    }

    public void setProvinceId(String s)
    {
        provinceId = s;
    }

    public void setTraderName(String s)
    {
        traderName = s;
    }

    public void setTraderPassword(String s)
    {
        traderPassword = s;
    }

    public void setUnionKey(String s)
    {
        unionKey = s;
    }

    public void setUserToken(String s)
    {
        userToken = s;
    }

    private static final long serialVersionUID = 0x9e48d54159a67a9L;
    private String clientAppVersion;
    private String clientSystem;
    private String clientTelnetPhone;
    private String clientVersion;
    private String interfaceVersion;
    private String protocol;
    private String provinceId;
    private String traderName;
    private String traderPassword;
    private String unionKey;
    private String userToken;
}
