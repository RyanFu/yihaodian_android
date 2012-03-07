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
//            WeiboResponse, WeiboException, Trend

public class Trends extends WeiboResponse
    implements Comparable
{

    Trends(Response response, Date date, Date date1, Trend atrend[])
        throws WeiboException
    {
        super(response);
        asOf = date;
        trendAt = date1;
        trends = atrend;
    }

    static Trends constructTrends(Response response)
        throws WeiboException
    {
        JSONObject jsonobject = response.asJSONObject();
        Trends trends1;
        try
        {
            Date date = parseDate(jsonobject.getString("as_of"));
            trends1 = new Trends(response, date, date, jsonArrayToTrendArray(jsonobject.getJSONArray("trends")));
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(response.asString()).toString(), jsonexception);
        }
        return trends1;
    }

    static List constructTrendsList(Response response)
        throws WeiboException
    {
        JSONObject jsonobject = response.asJSONObject();
        Date date;
        JSONObject jsonobject1;
        ArrayList arraylist;
        Iterator iterator;
        date = parseDate(jsonobject.getString("as_of"));
        jsonobject1 = jsonobject.getJSONObject("trends");
        arraylist = new ArrayList(jsonobject1.length());
        iterator = jsonobject1.keys();
_L1:
        String s;
        Trend atrend[];
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_234;
            s = (String)iterator.next();
            atrend = jsonArrayToTrendArray(jsonobject1.getJSONArray(s));
            if(s.length() != 19)
                break MISSING_BLOCK_LABEL_156;
            arraylist.add(new Trends(response, date, parseDate(s, "yyyy-MM-dd HH:mm:ss"), atrend));
        } while(true);
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(response.asString()).toString(), jsonexception);
        if(s.length() == 16)
            arraylist.add(new Trends(response, date, parseDate(s, "yyyy-MM-dd HH:mm"), atrend));
        else
        if(s.length() == 10)
            arraylist.add(new Trends(response, date, parseDate(s, "yyyy-MM-dd"), atrend));
          goto _L1
        Collections.sort(arraylist);
        return arraylist;
    }

    private static Trend[] jsonArrayToTrendArray(JSONArray jsonarray)
        throws JSONException
    {
        Trend atrend[] = new Trend[jsonarray.length()];
        for(int i = 0; i < jsonarray.length(); i++)
            atrend[i] = new Trend(jsonarray.getJSONObject(i));

        return atrend;
    }

    private static Date parseDate(String s)
        throws WeiboException
    {
        Date date;
        if(s.length() == 10)
            date = new Date(1000L * Long.parseLong(s));
        else
            date = WeiboResponse.parseDate(s, "EEE, d MMM yyyy HH:mm:ss z");
        return date;
    }

    @Override
	public volatile int compareTo(Object obj)
    {
        return compareTo((Trends)obj);
    }

    public int compareTo(Trends trends1)
    {
        return trendAt.compareTo(trends1.trendAt);
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof Trends))
        {
            flag = false;
        } else
        {
            Trends trends1 = (Trends)obj;
            if(asOf == null ? trends1.asOf != null : !asOf.equals(trends1.asOf))
                flag = false;
            else
            if(trendAt == null ? trends1.trendAt != null : !trendAt.equals(trends1.trendAt))
                flag = false;
            else
            if(!Arrays.equals(trends, trends1.trends))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public Date getAsOf()
    {
        return asOf;
    }

    public Date getTrendAt()
    {
        return trendAt;
    }

    public Trend[] getTrends()
    {
        return trends;
    }

    @Override
	public int hashCode()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        if(asOf != null)
            i = asOf.hashCode();
        else
            i = 0;
        j = i * 31;
        if(trendAt != null)
            k = trendAt.hashCode();
        else
            k = 0;
        l = 31 * (j + k);
        if(trends != null)
            i1 = Arrays.hashCode(trends);
        else
            i1 = 0;
        return l + i1;
    }

    @Override
	public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("Trends{asOf=").append(asOf).append(", trendAt=").append(trendAt).append(", trends=");
        List list;
        if(trends == null)
            list = null;
        else
            list = Arrays.asList(trends);
        return stringbuilder.append(list).append('}').toString();
    }

    private static final long serialVersionUID = 0x9cc0d79bdc9acd4bL;
    private Date asOf;
    private Date trendAt;
    private Trend trends[];
}
