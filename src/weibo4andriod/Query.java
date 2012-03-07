// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.ArrayList;
import java.util.List;
import weibo4andriod.http.PostParameter;

public class Query
{

    public Query()
    {
        query = null;
        lang = null;
        rpp = -1;
        page = -1;
        sinceId = -1L;
        geocode = null;
    }

    public Query(String s)
    {
        query = null;
        lang = null;
        rpp = -1;
        page = -1;
        sinceId = -1L;
        geocode = null;
        query = s;
    }

    private void appendParameter(String s, long l, List list)
    {
        if(0L <= l)
            list.add(new PostParameter(s, String.valueOf(l)));
    }

    private void appendParameter(String s, String s1, List list)
    {
        if(s1 != null)
            list.add(new PostParameter(s, s1));
    }

    public PostParameter[] asPostParameters()
    {
        ArrayList arraylist = new ArrayList();
        appendParameter("q", query, arraylist);
        appendParameter("lang", lang, arraylist);
        appendParameter("rpp", rpp, arraylist);
        appendParameter("page", page, arraylist);
        appendParameter("since_id", sinceId, arraylist);
        appendParameter("geocode", geocode, arraylist);
        return (PostParameter[])arraylist.toArray(new PostParameter[arraylist.size()]);
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(obj == null || getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            Query query1 = (Query)obj;
            if(page != query1.page)
                flag = false;
            else
            if(rpp != query1.rpp)
                flag = false;
            else
            if(sinceId != query1.sinceId)
                flag = false;
            else
            if(geocode == null ? query1.geocode != null : !geocode.equals(query1.geocode))
                flag = false;
            else
            if(lang == null ? query1.lang != null : !lang.equals(query1.lang))
                flag = false;
            else
            if(query == null ? query1.query != null : !query.equals(query1.query))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public String getGeocode()
    {
        return geocode;
    }

    public String getLang()
    {
        return lang;
    }

    public int getPage()
    {
        return page;
    }

    public String getQuery()
    {
        return query;
    }

    public int getRpp()
    {
        return rpp;
    }

    public long getSinceId()
    {
        return sinceId;
    }

    @Override
	public int hashCode()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        if(query != null)
            i = query.hashCode();
        else
            i = 0;
        j = i * 31;
        if(lang != null)
            k = lang.hashCode();
        else
            k = 0;
        l = 31 * (31 * (31 * (31 * (j + k) + rpp) + page) + (int)(sinceId ^ sinceId >>> 32));
        if(geocode != null)
            i1 = geocode.hashCode();
        else
            i1 = 0;
        return l + i1;
    }

    public void setGeoCode(double d, double d1, double d2, String s)
    {
        geocode = (new StringBuilder()).append(d).append(",").append(d1).append(",").append(d2).append(s).toString();
    }

    public void setLang(String s)
    {
        lang = s;
    }

    public void setPage(int i)
    {
        page = i;
    }

    public void setQuery(String s)
    {
        query = s;
    }

    public void setRpp(int i)
    {
        rpp = i;
    }

    public void setSinceId(long l)
    {
        sinceId = l;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("Query{query='").append(query).append('\'').append(", lang='").append(lang).append('\'').append(", rpp=").append(rpp).append(", page=").append(page).append(", sinceId=").append(sinceId).append(", geocode='").append(geocode).append('\'').append('}').toString();
    }

    public static final String KILOMETERS = "km";
    public static final String MILES = "mi";
    private String geocode;
    private String lang;
    private int page;
    private String query;
    private int rpp;
    private long sinceId;
}
