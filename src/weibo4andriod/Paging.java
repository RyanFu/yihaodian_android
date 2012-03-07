// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;

public class Paging
    implements Serializable
{

    public Paging()
    {
        page = -1;
        count = -1;
        sinceId = -1L;
        maxId = -1L;
    }

    public Paging(int i)
    {
        page = -1;
        count = -1;
        sinceId = -1L;
        maxId = -1L;
        setPage(i);
    }

    public Paging(int i, int j)
    {
        this(i);
        setCount(j);
    }

    public Paging(int i, int j, long l)
    {
        this(i, j);
        setSinceId(l);
    }

    public Paging(int i, int j, long l, long l1)
    {
        this(i, j, l);
        setMaxId(l1);
    }

    public Paging(int i, long l)
    {
        this(i);
        setSinceId(l);
    }

    public Paging(long l)
    {
        page = -1;
        count = -1;
        sinceId = -1L;
        maxId = -1L;
        setSinceId(l);
    }

    public Paging count(int i)
    {
        setCount(i);
        return this;
    }

    public int getCount()
    {
        return count;
    }

    public long getMaxId()
    {
        return maxId;
    }

    public int getPage()
    {
        return page;
    }

    public long getSinceId()
    {
        return sinceId;
    }

    public Paging maxId(long l)
    {
        setMaxId(l);
        return this;
    }

    public void setCount(int i)
    {
        if(i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("count should be positive integer. passed:").append(i).toString());
        } else
        {
            count = i;
            return;
        }
    }

    public void setMaxId(long l)
    {
        if(l < 1L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("max_id should be positive integer. passed:").append(l).toString());
        } else
        {
            maxId = l;
            return;
        }
    }

    public void setPage(int i)
    {
        if(i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("page should be positive integer. passed:").append(i).toString());
        } else
        {
            page = i;
            return;
        }
    }

    public void setSinceId(int i)
    {
        if(i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("since_id should be positive integer. passed:").append(i).toString());
        } else
        {
            sinceId = i;
            return;
        }
    }

    public void setSinceId(long l)
    {
        if(l < 1L)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("since_id should be positive integer. passed:").append(l).toString());
        } else
        {
            sinceId = l;
            return;
        }
    }

    public Paging sinceId(int i)
    {
        setSinceId(i);
        return this;
    }

    public Paging sinceId(long l)
    {
        setSinceId(l);
        return this;
    }

    private static final long serialVersionUID = 0xd2664a1415d9dfc2L;
    private int count;
    private long maxId;
    private int page;
    private long sinceId;
}
