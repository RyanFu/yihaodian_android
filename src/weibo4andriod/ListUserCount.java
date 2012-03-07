// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException

public class ListUserCount extends WeiboResponse
    implements Serializable
{

    public ListUserCount(Response response)
        throws WeiboException
    {
        org.w3c.dom.Element element = response.asDocument().getDocumentElement();
        ensureRootNodeNameIs("count", element);
        listCount = getChildInt("lists", element);
        subscriberCount = getChildInt("subscriptions", element);
        listedCount = getChildInt("listed", element);
    }

    public ListUserCount(JSONObject jsonobject)
        throws WeiboException, JSONException
    {
        listCount = jsonobject.getInt("lists");
        subscriberCount = jsonobject.getInt("subscriptions");
        listedCount = jsonobject.getInt("listed");
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(this == obj)
            flag = true;
        else
        if((obj instanceof ListUserCount) && ((ListUserCount)obj).hashCode() == hashCode())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getListCount()
    {
        return listCount;
    }

    public int getListedCount()
    {
        return listedCount;
    }

    public int getSubscriberCount()
    {
        return subscriberCount;
    }

    @Override
	public int hashCode()
    {
        return 100 * listCount + 10 * subscriberCount + listedCount;
    }

    public void setListCount(int i)
    {
        listCount = i;
    }

    public void setListedCount(int i)
    {
        listedCount = i;
    }

    public void setSubscriberCount(int i)
    {
        subscriberCount = i;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("ListUserCount{listCount=").append(listCount).append(", subscriberCount=").append(subscriberCount).append(", listedCount=").append(listedCount).append('}').toString();
    }

    private static final long serialVersionUID = 0x249e88f8d62e1119L;
    private int listCount;
    private int listedCount;
    private int subscriberCount;
}
