// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.Date;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, Weibo

public class RateLimitStatus extends WeiboResponse
{

    RateLimitStatus(Response response)
        throws WeiboException
    {
        super(response);
        org.w3c.dom.Element element = response.asDocument().getDocumentElement();
        remainingHits = getChildInt("remaining-hits", element);
        hourlyLimit = getChildInt("hourly-limit", element);
        resetTimeInSeconds = getChildInt("reset-time-in-seconds", element);
        resetTime = getChildDate("reset-time", element, "EEE MMM d HH:mm:ss z yyyy");
    }

    RateLimitStatus(Response response, Weibo weibo)
        throws WeiboException
    {
        super(response);
        JSONObject jsonobject = response.asJSONObject();
        try
        {
            remainingHits = jsonobject.getInt("remaining_hits");
            hourlyLimit = jsonobject.getInt("hourly_limit");
            resetTimeInSeconds = jsonobject.getInt("reset_time_in_seconds");
            resetTime = parseDate(jsonobject.getString("reset_time"), "EEE MMM dd HH:mm:ss z yyyy");
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    public Date getDateTime()
    {
        return resetTime;
    }

    public int getHourlyLimit()
    {
        return hourlyLimit;
    }

    public int getRemainingHits()
    {
        return remainingHits;
    }

    public Date getResetTime()
    {
        return resetTime;
    }

    public int getResetTimeInSeconds()
    {
        return resetTimeInSeconds;
    }

    @Override
	public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("RateLimitStatus{remainingHits:");
        stringbuilder.append(remainingHits);
        stringbuilder.append(";hourlyLimit:");
        stringbuilder.append(hourlyLimit);
        stringbuilder.append(";resetTimeInSeconds:");
        stringbuilder.append(resetTimeInSeconds);
        stringbuilder.append(";resetTime:");
        stringbuilder.append(resetTime);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private static final long serialVersionUID = 0xcf6392515d72b83L;
    private int hourlyLimit;
    private int remainingHits;
    private Date resetTime;
    private int resetTimeInSeconds;
}
