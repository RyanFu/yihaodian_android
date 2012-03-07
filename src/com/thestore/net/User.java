// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.user.UserVO;
import com.yihaodian.mobile.vo.user.VerifyCodeVO;

// Referenced classes of package com.thestore.net:
//            DBHelper

public class User
{

    public User(String s, String s1)
    {
        dbHelper = new DBHelper();
        loginMethod = "login";
        verifyMethod = "getVerifyCodeUrl";
        registMethod = "register";
        forgetMethod = "findPasswordByEmail";
        myStoreMethod = "getMyYihaodianSessionUser";
        modifyPassword = "modifyPassword";
        serverTimeStamp = "getServerTimeStamp";
        logoutMethod = "logout";
        xStream = DBHelper.getxStream();
        username = s;
        tempPassword = s1;
    }

    public User(String s, String s1, String s2)
    {
        dbHelper = new DBHelper();
        loginMethod = "login";
        verifyMethod = "getVerifyCodeUrl";
        registMethod = "register";
        forgetMethod = "findPasswordByEmail";
        myStoreMethod = "getMyYihaodianSessionUser";
        modifyPassword = "modifyPassword";
        serverTimeStamp = "getServerTimeStamp";
        logoutMethod = "logout";
        xStream = DBHelper.getxStream();
        username = s;
        tempPassword = s1;
        newPwd = s2;
    }

    private String loginBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(province);
        aobj[2] = username;
        aobj[3] = tempPassword;
        return xStream.toXML(((aobj)));
    }

    private String registBody()
    {
        Object aobj[] = new Object[5];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = username;
        aobj[2] = tempPassword;
        aobj[3] = code;
        aobj[4] = tempToken;
        return xStream.toXML(((aobj)));
    }

    public Integer findPassword()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(forgetMethod, forgetBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public String forgetBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = username;
        aobj[2] = code;
        aobj[3] = tempToken;
        return xStream.toXML(((aobj)));
    }

    public Integer getLogout()
    {
        Object aobj[] = new Object[1];
        aobj[0] = token;
        String s = xStream.toXML(((aobj)));
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(logoutMethod, s);
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public String getModifyPasswordBody()
    {
        Object aobj[] = new Object[4];
        aobj[0] = token;
        aobj[1] = username;
        aobj[2] = tempPassword;
        aobj[3] = newPwd;
        return xStream.toXML(((aobj)));
    }

    public UserVO getMyStore()
    {
        Object aobj[] = new Object[1];
        aobj[0] = token;
        String s = xStream.toXML(((aobj)));
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(myStoreMethod, s);
        return (UserVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public Long getServerTimeStamp()
    {
        Object aobj[] = new Object[1];
        aobj[0] = DBHelper.getTrader();
        String s = xStream.toXML(((aobj)));
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(serverTimeStamp, s);
        return (Long)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public String getTempToken()
    {
        Object aobj[] = new Object[1];
        aobj[0] = DBHelper.getTrader();
        String s = xStream.toXML(((aobj)));
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(verifyMethod, s);
        return ((VerifyCodeVO)xStream.fromXML(dbHelper.getResultString(httpresponse))).getTempToken();
    }

    public VerifyCodeVO getVerify()
    {
        Object aobj[] = new Object[1];
        aobj[0] = DBHelper.getTrader();
        String s = xStream.toXML(((aobj)));
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(verifyMethod, s);
        return (VerifyCodeVO)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public String login()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(loginMethod, loginBody());
        return (String)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public int modifyPassword()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(modifyPassword, getModifyPasswordBody());
        return ((Integer)xStream.fromXML(dbHelper.getResultString(httpresponse))).intValue();
    }

    public Integer regist()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(registMethod, registBody());
        return (Integer)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public static int TYPE;
    public static String code;
    public static String password;
    public static long province = 1L;
    public static String tempToken;
    public static String token = null;
    public static String userAccount;
    public static String userName;
    private DBHelper dbHelper;
    private String forgetMethod;
    private String loginMethod;
    private String logoutMethod;
    private String modifyPassword;
    private String myStoreMethod;
    private String newPwd;
    private String registMethod;
    private String serverTimeStamp;
    private String tempPassword;
    private String username;
    private String verifyMethod;
    private XStream xStream;

}
