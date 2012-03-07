// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.util.List;

public class ListObjectWapper
    implements Serializable
{

    public ListObjectWapper(List list, long l, long l1)
    {
        listObjects = list;
        previousCursor = l;
        nextCursor = l1;
    }

    public List getListObjects()
    {
        return listObjects;
    }

    public long getNextCursor()
    {
        return nextCursor;
    }

    public long getPreviousCursor()
    {
        return previousCursor;
    }

    public void setListObjects(List list)
    {
        listObjects = list;
    }

    public void setNextCursor(long l)
    {
        nextCursor = l;
    }

    public void setPreviousCursor(long l)
    {
        previousCursor = l;
    }

    private static final long serialVersionUID = 0xd4b67c979c436964L;
    private List listObjects;
    private long nextCursor;
    private long previousCursor;
}
