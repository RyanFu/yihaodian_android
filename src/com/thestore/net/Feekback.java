// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Feekback
{

    public Feekback(String s)
    {
        dbHelper = new DBHelper();
        xStream = DBHelper.getxStream();
        result = -1;
        addFeedback = "addFeedback";
        feekback = s;
    }

    private String addFeedbackBody()
    {
        Object aobj[] = new Object[2];
        aobj[0] = User.token;
        aobj[1] = feekback;
        return xStream.toXML(((aobj)));
    }

    public int addFeedback()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(addFeedback, addFeedbackBody());
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

    private String addFeedback;
    private DBHelper dbHelper;
    private String feekback;
    private int result;
    private XStream xStream;
}
