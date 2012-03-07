// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.util.*;
import org.w3c.dom.*;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, User, RetweetDetails, 
//            Weibo

public class Status extends WeiboResponse
    implements Serializable
{

    public Status(String s)
        throws WeiboException, JSONException
    {
        latitude = -1D;
        longitude = -1D;
        user = null;
        JSONObject jsonobject = new JSONObject(s);
        id = jsonobject.getLong("id");
        text = jsonobject.getString("text");
        source = jsonobject.getString("source");
        createdAt = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
        inReplyToStatusId = getLong("in_reply_to_status_id", jsonobject);
        inReplyToUserId = getInt("in_reply_to_user_id", jsonobject);
        isFavorited = getBoolean("favorited", jsonobject);
        thumbnail_pic = jsonobject.getString("thumbnail_pic");
        bmiddle_pic = jsonobject.getString("bmiddle_pic");
        original_pic = jsonobject.getString("original_pic");
        user = new User(jsonobject.getJSONObject("user"));
        if(!jsonobject.isNull("retweeted_status"))
            retweetDetails = new RetweetDetails(jsonobject.getJSONObject("retweeted_status"));
    }

    Status(Response response)
        throws WeiboException
    {
        super(response);
        latitude = -1D;
        longitude = -1D;
        user = null;
        JSONObject jsonobject = response.asJSONObject();
        try
        {
            id = jsonobject.getLong("id");
            text = jsonobject.getString("text");
            source = jsonobject.getString("source");
            createdAt = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
            inReplyToStatusId = getLong("in_reply_to_status_id", jsonobject);
            inReplyToUserId = getInt("in_reply_to_user_id", jsonobject);
            isFavorited = getBoolean("favorited", jsonobject);
            thumbnail_pic = jsonobject.getString("thumbnail_pic");
            bmiddle_pic = jsonobject.getString("bmiddle_pic");
            original_pic = jsonobject.getString("original_pic");
            if(!jsonobject.isNull("user"))
                user = new User(jsonobject.getJSONObject("user"));
            inReplyToScreenName = jsonobject.getString("inReplyToScreenName");
            if(!jsonobject.isNull("retweeted_status"))
                retweetDetails = new RetweetDetails(jsonobject.getJSONObject("retweeted_status"));
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    Status(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        super(response);
        latitude = -1D;
        longitude = -1D;
        user = null;
        init(response, element, weibo);
    }

    Status(Response response, Weibo weibo)
        throws WeiboException
    {
        super(response);
        latitude = -1D;
        longitude = -1D;
        user = null;
        init(response, response.asDocument().getDocumentElement(), weibo);
    }

    public Status(JSONObject jsonobject)
        throws WeiboException, JSONException
    {
        latitude = -1D;
        longitude = -1D;
        user = null;
        id = jsonobject.getLong("id");
        text = jsonobject.getString("text");
        source = jsonobject.getString("source");
        createdAt = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
        isFavorited = getBoolean("favorited", jsonobject);
        isTruncated = getBoolean("truncated", jsonobject);
        inReplyToStatusId = getLong("in_reply_to_status_id", jsonobject);
        inReplyToUserId = getInt("in_reply_to_user_id", jsonobject);
        inReplyToScreenName = jsonobject.getString("in_reply_to_screen_name");
        thumbnail_pic = jsonobject.getString("thumbnail_pic");
        bmiddle_pic = jsonobject.getString("bmiddle_pic");
        original_pic = jsonobject.getString("original_pic");
        user = new User(jsonobject.getJSONObject("user"));
        if(!jsonobject.isNull("retweeted_status"))
            retweetDetails = new RetweetDetails(jsonobject.getJSONObject("retweeted_status"));
    }

    static List constructStatuses(Response response)
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
        arraylist.add(new Status(jsonarray.getJSONObject(j)));
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

    static List constructStatuses(Response response, Weibo weibo)
        throws WeiboException
    {
        Document document = response.asDocument();
        if(!isRootNodeNilClasses(document)) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = new ArrayList(0);
_L5:
        return arraylist;
_L2:
        NodeList nodelist;
        int i;
        ArrayList arraylist1;
        int j;
        ensureRootNodeNameIs("statuses", document);
        nodelist = document.getDocumentElement().getElementsByTagName("status");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_109;
        arraylist1.add(new Status(response, (Element)nodelist.item(j), weibo));
        j++;
          goto _L3
        arraylist = arraylist1;
        continue; /* Loop/switch isn't completed */
        WeiboException weiboexception;
        weiboexception;
        ensureRootNodeNameIs("nil-classes", document);
        arraylist = new ArrayList(0);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void init(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        ensureRootNodeNameIs("status", element);
        user = new User(response, (Element)element.getElementsByTagName("user").item(0), weibo);
        id = getChildLong("id", element);
        text = getChildText("text", element);
        source = getChildText("source", element);
        createdAt = getChildDate("created_at", element);
        isTruncated = getChildBoolean("truncated", element);
        inReplyToStatusId = getChildLong("in_reply_to_status_id", element);
        inReplyToUserId = getChildInt("in_reply_to_user_id", element);
        isFavorited = getChildBoolean("favorited", element);
        inReplyToScreenName = getChildText("in_reply_to_screen_name", element);
        NodeList nodelist = element.getElementsByTagName("georss:point");
        if(1 == nodelist.getLength())
        {
            String as[] = nodelist.item(0).getFirstChild().getNodeValue().split(" ");
            if(!"null".equals(as[0]))
                latitude = Double.parseDouble(as[0]);
            if(!"null".equals(as[1]))
                longitude = Double.parseDouble(as[1]);
        }
        NodeList nodelist1 = element.getElementsByTagName("retweet_details");
        if(1 == nodelist1.getLength())
            retweetDetails = new RetweetDetails(response, (Element)nodelist1.item(0), weibo);
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj == null)
            flag = false;
        else
        if(this == obj)
            flag = true;
        else
        if((obj instanceof Status) && ((Status)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String getBmiddle_pic()
    {
        return bmiddle_pic;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public long getId()
    {
        return id;
    }

    public String getInReplyToScreenName()
    {
        return inReplyToScreenName;
    }

    public long getInReplyToStatusId()
    {
        return inReplyToStatusId;
    }

    public int getInReplyToUserId()
    {
        return inReplyToUserId;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public String getOriginal_pic()
    {
        return original_pic;
    }

    public RetweetDetails getRetweetDetails()
    {
        return retweetDetails;
    }

    public String getSource()
    {
        return source;
    }

    public String getText()
    {
        return text;
    }

    public String getThumbnail_pic()
    {
        return thumbnail_pic;
    }

    public User getUser()
    {
        return user;
    }

    public int hashCode()
    {
        return (int)id;
    }

    public boolean isFavorited()
    {
        return isFavorited;
    }

    public boolean isRetweet()
    {
        boolean flag;
        if(retweetDetails != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTruncated()
    {
        return isTruncated;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Status{createdAt=").append(createdAt).append(", id=").append(id).append(", text='").append(text).append('\'').append(", source='").append(source).append('\'').append(", isTruncated=").append(isTruncated).append(", inReplyToStatusId=").append(inReplyToStatusId).append(", inReplyToUserId=").append(inReplyToUserId).append(", isFavorited=").append(isFavorited).append(", thumbnail_pic=").append(thumbnail_pic).append(", bmiddle_pic=").append(bmiddle_pic).append(", original_pic=").append(original_pic).append(", inReplyToScreenName='").append(inReplyToScreenName).append('\'').append(", latitude=").append(latitude).append(", longitude=").append(longitude).append(", retweetDetails=").append(retweetDetails).append(", user=").append(user).append('}').toString();
    }

    private static final long serialVersionUID = 0x1650c3edbf9bb2a0L;
    private String bmiddle_pic;
    private Date createdAt;
    private long id;
    private String inReplyToScreenName;
    private long inReplyToStatusId;
    private int inReplyToUserId;
    private boolean isFavorited;
    private boolean isTruncated;
    private double latitude;
    private double longitude;
    private String original_pic;
    private RetweetDetails retweetDetails;
    private String source;
    private String text;
    private String thumbnail_pic;
    private User user;
}
