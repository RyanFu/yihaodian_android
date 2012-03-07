// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.util.List;

public class UserWapper
    implements Serializable
{

    public UserWapper(List list, long l, long l1)
    {
        users = list;
        previousCursor = l;
        nextCursor = l1;
    }

    public long getNextCursor()
    {
        return nextCursor;
    }

    public long getPreviousCursor()
    {
        return previousCursor;
    }

    public List getUsers()
    {
        return users;
    }

    public void setNextCursor(long l)
    {
        nextCursor = l;
    }

    public void setPreviousCursor(long l)
    {
        previousCursor = l;
    }

    public void setUsers(List list)
    {
        users = list;
    }

    private static final long serialVersionUID = 0xd4b6b41247983964L;
    private long nextCursor;
    private long previousCursor;
    private List users;
}
