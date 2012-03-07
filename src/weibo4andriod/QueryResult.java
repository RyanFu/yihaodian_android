// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.ArrayList;
import java.util.List;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, Query, Tweet, 
//            WeiboSupport

public class QueryResult extends WeiboResponse
{

    QueryResult(Query query1)
        throws WeiboException
    {
        total = -1;
        sinceId = query1.getSinceId();
        resultsPerPage = query1.getRpp();
        page = query1.getPage();
        tweets = new ArrayList(0);
    }

    QueryResult(Response response, WeiboSupport weibosupport)
        throws WeiboException
    {
        JSONObject jsonobject;
        super(response);
        total = -1;
        jsonobject = response.asJSONObject();
        JSONArray jsonarray;
        int i;
        sinceId = jsonobject.getLong("since_id");
        maxId = jsonobject.getLong("max_id");
        refreshUrl = getString("refresh_url", jsonobject, true);
        resultsPerPage = jsonobject.getInt("results_per_page");
        warning = getString("warning", jsonobject, false);
        completedIn = jsonobject.getDouble("completed_in");
        page = jsonobject.getInt("page");
        query = getString("query", jsonobject, true);
        jsonarray = jsonobject.getJSONArray("results");
        tweets = new ArrayList(jsonarray.length());
        i = 0;
_L1:
        if(i >= jsonarray.length())
            break MISSING_BLOCK_LABEL_213;
        JSONObject jsonobject1 = jsonarray.getJSONObject(i);
        tweets.add(new Tweet(jsonobject1, weibosupport));
        i++;
          goto _L1
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
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
            QueryResult queryresult = (QueryResult)obj;
            if(Double.compare(queryresult.completedIn, completedIn) != 0)
                flag = false;
            else
            if(maxId != queryresult.maxId)
                flag = false;
            else
            if(page != queryresult.page)
                flag = false;
            else
            if(resultsPerPage != queryresult.resultsPerPage)
                flag = false;
            else
            if(sinceId != queryresult.sinceId)
                flag = false;
            else
            if(total != queryresult.total)
                flag = false;
            else
            if(!query.equals(queryresult.query))
                flag = false;
            else
            if(refreshUrl == null ? queryresult.refreshUrl != null : !refreshUrl.equals(queryresult.refreshUrl))
                flag = false;
            else
            if(tweets == null ? queryresult.tweets != null : !tweets.equals(queryresult.tweets))
                flag = false;
            else
            if(warning == null ? queryresult.warning != null : !warning.equals(queryresult.warning))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public double getCompletedIn()
    {
        return completedIn;
    }

    public long getMaxId()
    {
        return maxId;
    }

    public int getPage()
    {
        return page;
    }

    public String getQuery()
    {
        return query;
    }

    public String getRefreshUrl()
    {
        return refreshUrl;
    }

    public int getResultsPerPage()
    {
        return resultsPerPage;
    }

    public long getSinceId()
    {
        return sinceId;
    }

    public int getTotal()
    {
        return total;
    }

    public List getTweets()
    {
        return tweets;
    }

    public String getWarning()
    {
        return warning;
    }

    @Override
	public int hashCode()
    {
        int i = 31 * (31 * (int)(sinceId ^ sinceId >>> 32) + (int)(maxId ^ maxId >>> 32));
        int j;
        int k;
        int l;
        int i1;
        long l1;
        int j1;
        int k1;
        if(refreshUrl != null)
            j = refreshUrl.hashCode();
        else
            j = 0;
        k = 31 * (31 * (31 * (i + j) + resultsPerPage) + total);
        if(warning != null)
            l = warning.hashCode();
        else
            l = 0;
        i1 = k + l;
        if(completedIn != 0.0D)
            l1 = Double.doubleToLongBits(completedIn);
        else
            l1 = 0L;
        j1 = 31 * (31 * (31 * (i1 * 31 + (int)(l1 ^ l1 >>> 32)) + page) + query.hashCode());
        if(tweets != null)
            k1 = tweets.hashCode();
        else
            k1 = 0;
        return j1 + k1;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("QueryResult{sinceId=").append(sinceId).append(", maxId=").append(maxId).append(", refreshUrl='").append(refreshUrl).append('\'').append(", resultsPerPage=").append(resultsPerPage).append(", total=").append(total).append(", warning='").append(warning).append('\'').append(", completedIn=").append(completedIn).append(", page=").append(page).append(", query='").append(query).append('\'').append(", tweets=").append(tweets).append('}').toString();
    }

    private static final long serialVersionUID = 0x82477b4a917043daL;
    private double completedIn;
    private long maxId;
    private int page;
    private String query;
    private String refreshUrl;
    private int resultsPerPage;
    private long sinceId;
    private int total;
    private List tweets;
    private String warning;
}
