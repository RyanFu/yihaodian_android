// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import android.os.*;
import com.thestore.util.Util;
import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import java.util.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class SearchProductDao extends AsyncTask
{

    public SearchProductDao(String s, Handler handler1)
    {
        productList = null;
        page = null;
        keyword = s;
        handler = handler1;
    }

    @Override
	protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    protected transient Map doInBackground(Object aobj[])
    {
        HashMap hashmap = new HashMap();
        try
        {
            DBHelper dbhelper = new DBHelper();
            HttpPost httppost = dbhelper.getHttpPost();
            aobj[0] = DBHelper.getTrader();
            aobj[1] = dbhelper.getMCSITE_ID();
            aobj[2] = Long.valueOf(User.province);
            aobj[3] = keyword;
            aobj[4] = Long.valueOf(0L);
            aobj[5] = Long.valueOf(0L);
            aobj[6] = Integer.valueOf(6);
            aobj[8] = Integer.valueOf(Util.pageSize);
            httppost.setEntity(new UrlEncodedFormEntity(dbhelper.setMethodParamList("searchProduct", dbhelper.getMethodBody(aobj)), "UTF-8"));
            String s = dbhelper.getResultString(dbhelper.getHttpResponse(httppost));
            page = (Page)DBHelper.getxStream().fromXML(s);
            productList = page.getObjList();
            hashmap.put("pageObject", page);
            hashmap.put("productList", productList);
        }
        catch(Exception exception) { }
        return hashmap;
    }

    @Override
	protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Map)obj);
    }

    protected void onPostExecute(Map map)
    {
        Message message = handler.obtainMessage();
        message.what = 0x7f09002a;
        message.obj = map;
        handler.sendMessage(message);
    }

    private Handler handler;
    private String keyword;
    private Page page;
    private List productList;
}
