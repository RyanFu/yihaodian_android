// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboException

public class Count
    implements Serializable
{

    public Count(JSONObject jsonobject)
        throws WeiboException, JSONException
    {
        id = jsonobject.getLong("id");
        comments = jsonobject.getLong("comments");
        rt = jsonobject.getLong("rt");
        dm = jsonobject.getLong("dm");
        mentions = jsonobject.getLong("mentions");
        followers = jsonobject.getLong("followers");
    }

    static List constructCounts(Response response)
        throws WeiboException
    {
        JSONArray jsonarray;
        int i;
        ArrayList arraylist;
        int j;
        jsonarray = response.asJSONArray();
        i = jsonarray.length();
        arraylist = new ArrayList(i);
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_72;
        arraylist.add(new Count(jsonarray.getJSONObject(j)));
        j++;
          goto _L1
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException(jsonexception);
        WeiboException weiboexception;
        weiboexception;
        throw weiboexception;
        return arraylist;
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
        if((obj instanceof Count) && ((Count)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public long getComments()
    {
        return comments;
    }

    public long getDm()
    {
        return dm;
    }

    public long getFollowers()
    {
        return followers;
    }

    public long getMentions()
    {
        return mentions;
    }

    public long getRt()
    {
        return rt;
    }

    @Override
	public int hashCode()
    {
        return (int)id;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("Count{ id=").append(id).append(", comments=").append(comments).append(", rt=").append(rt).append(", dm=").append(dm).append(", mentions=").append(mentions).append(", followers=").append(followers).append('}').toString();
    }

    private static final long serialVersionUID = 0x7df5effd39a1e485L;
    private long comments;
    private long dm;
    private long followers;
    private long id;
    private long mentions;
    private long rt;
}
