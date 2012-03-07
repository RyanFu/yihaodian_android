// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.*;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException

public class SavedSearch extends WeiboResponse
{

    SavedSearch(Response response)
        throws WeiboException
    {
        super(response);
        init(response.asJSONObject());
    }

    SavedSearch(Response response, JSONObject jsonobject)
        throws WeiboException
    {
        super(response);
        init(jsonobject);
    }

    SavedSearch(JSONObject jsonobject)
        throws WeiboException
    {
        init(jsonobject);
    }

    static List constructSavedSearches(Response response)
        throws WeiboException
    {
        JSONArray jsonarray = response.asJSONArray();
        ArrayList arraylist;
        int i;
        arraylist = new ArrayList(jsonarray.length());
        i = 0;
_L1:
        if(i >= jsonarray.length())
            break MISSING_BLOCK_LABEL_95;
        arraylist.add(new SavedSearch(response, jsonarray.getJSONObject(i)));
        i++;
          goto _L1
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(response.asString()).toString(), jsonexception);
        return arraylist;
    }

    private void init(JSONObject jsonobject)
        throws WeiboException
    {
        try
        {
            createdAt = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
            query = getString("query", jsonobject, true);
            position = getInt("position", jsonobject);
            name = getString("name", jsonobject, true);
            id = getInt("id", jsonobject);
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof SavedSearch))
        {
            flag = false;
        } else
        {
            SavedSearch savedsearch = (SavedSearch)obj;
            if(id != savedsearch.id)
                flag = false;
            else
            if(position != savedsearch.position)
                flag = false;
            else
            if(!createdAt.equals(savedsearch.createdAt))
                flag = false;
            else
            if(!name.equals(savedsearch.name))
                flag = false;
            else
            if(!query.equals(savedsearch.query))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getPosition()
    {
        return position;
    }

    public String getQuery()
    {
        return query;
    }

    @Override
	public int hashCode()
    {
        return 31 * (31 * (31 * (31 * createdAt.hashCode() + query.hashCode()) + position) + name.hashCode()) + id;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("SavedSearch{createdAt=").append(createdAt).append(", query='").append(query).append('\'').append(", position=").append(position).append(", name='").append(name).append('\'').append(", id=").append(id).append('}').toString();
    }

    private static final long serialVersionUID = 0x2acbedd301ef9884L;
    private Date createdAt;
    private int id;
    private String name;
    private int position;
    private String query;
}
