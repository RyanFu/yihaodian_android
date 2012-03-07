// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

public class Trend
    implements Serializable
{

    public Trend(JSONObject jsonobject)
        throws JSONException
    {
        url = null;
        query = null;
        name = jsonobject.getString("name");
        if(!jsonobject.isNull("url"))
            url = jsonobject.getString("url");
        if(!jsonobject.isNull("query"))
            query = jsonobject.getString("query");
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof Trend))
        {
            flag = false;
        } else
        {
            Trend trend = (Trend)obj;
            if(!name.equals(trend.name))
                flag = false;
            else
            if(query == null ? trend.query != null : !query.equals(trend.query))
                flag = false;
            else
            if(url == null ? trend.url != null : !url.equals(trend.url))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public String getName()
    {
        return name;
    }

    public String getQuery()
    {
        return query;
    }

    public String getUrl()
    {
        return url;
    }

    @Override
	public int hashCode()
    {
        int i = 31 * name.hashCode();
        int j;
        int k;
        int l;
        if(url != null)
            j = url.hashCode();
        else
            j = 0;
        k = 31 * (i + j);
        if(query != null)
            l = query.hashCode();
        else
            l = 0;
        return k + l;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("Trend{name='").append(name).append('\'').append(", url='").append(url).append('\'').append(", query='").append(query).append('\'').append('}').toString();
    }

    private static final long serialVersionUID = 0x1aba5f6b262a250aL;
    private String name;
    private String query;
    private String url;
}
