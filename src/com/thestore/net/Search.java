// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.net;

import com.thoughtworks.xstream.XStream;
import com.yihaodian.mobile.vo.core.Page;
import java.util.List;

// Referenced classes of package com.thestore.net:
//            DBHelper, User

public class Search
{

    public Search(String s)
    {
        xStream = DBHelper.getxStream();
        mcsite = 1L;
        searchKeywordMethod = "getSearchKeyWord";
        searchResultMethod = "searchProduct";
        keyword = s;
    }

    private String keywordBody()
    {
        Object aobj[] = new Object[3];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = Long.valueOf(mcsite);
        aobj[2] = keyword;
        return xStream.toXML(((aobj)));
    }

    private String searchResultBody()
    {
        Object aobj[] = new Object[9];
        aobj[0] = DBHelper.getTrader();
        aobj[1] = dbHelper.getMCSITE_ID();
        aobj[2] = Long.valueOf(User.province);
        aobj[3] = keyword;
        aobj[4] = Long.valueOf(categoryId);
        aobj[5] = Long.valueOf(brandId);
        aobj[6] = Integer.valueOf(sortType);
        aobj[7] = Integer.valueOf(currentPage);
        aobj[8] = Integer.valueOf(pageSize);
        return xStream.toXML(((aobj)));
    }

    public List getKeyword()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(searchKeywordMethod, keywordBody());
        return (List)xStream.fromXML(dbHelper.getResultString(httpresponse));
    }

    public List getSearchResult()
    {
        org.apache.http.HttpResponse httpresponse = dbHelper.getHttpResponse(searchResultMethod, searchResultBody());
        return ((Page)xStream.fromXML(dbHelper.getResultString(httpresponse))).getObjList();
    }

    public void setCategoryBrandSort(long l, long l1, int i, int j)
    {
        categoryId = l;
        brandId = l1;
        sortType = i;
        pageSize = j;
    }

    public void setSortType(int i)
    {
        currentPage = i;
    }

    private static DBHelper dbHelper = new DBHelper();
    private long brandId;
    private long categoryId;
    private int currentPage;
    private String keyword;
    private long mcsite;
    private int pageSize;
    private String searchKeywordMethod;
    private String searchResultMethod;
    private int sortType;
    private XStream xStream;

}
