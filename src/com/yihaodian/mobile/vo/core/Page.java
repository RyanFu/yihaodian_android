// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.yihaodian.mobile.vo.core;

import java.util.ArrayList;
import java.util.List;

public class Page
{

    public Page()
    {
        currentPage = new Integer(1);
        pageSize = new Integer(10);
        totalSize = new Integer(0);
        objList = new ArrayList();
    }

    public Integer getCurrentPage()
    {
        return currentPage;
    }

    public List getObjList()
    {
        return objList;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public Integer getTotalSize()
    {
        return totalSize;
    }

    public void setCurrentPage(Integer integer)
    {
        currentPage = integer;
    }

    public void setObjList(List list)
    {
        objList = list;
    }

    public void setPageSize(Integer integer)
    {
        pageSize = integer;
    }

    public void setTotalSize(Integer integer)
    {
        totalSize = integer;
    }

    private Integer currentPage;
    private List objList;
    private Integer pageSize;
    private Integer totalSize;
}
