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

public class Comment extends WeiboResponse
    implements Serializable
{

    public Comment(String s)
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
        user = new User(jsonobject.getJSONObject("user"));
    }

    Comment(Response response)
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
            if(!jsonobject.isNull("user"))
                user = new User(jsonobject.getJSONObject("user"));
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    Comment(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        super(response);
        latitude = -1D;
        longitude = -1D;
        user = null;
        init(response, element, weibo);
    }

    Comment(Response response, Weibo weibo)
        throws WeiboException
    {
        super(response);
        latitude = -1D;
        longitude = -1D;
        user = null;
        init(response, response.asDocument().getDocumentElement(), weibo);
    }

    public Comment(JSONObject jsonobject)
        throws WeiboException, JSONException
    {
        latitude = -1D;
        longitude = -1D;
        user = null;
        id = jsonobject.getLong("id");
        text = jsonobject.getString("text");
        source = jsonobject.getString("source");
        createdAt = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
        if(!jsonobject.isNull("user"))
            user = new User(jsonobject.getJSONObject("user"));
    }

    static List constructComments(Response response)
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
        arraylist.add(new Comment(jsonarray.getJSONObject(j)));
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

    static List constructComments(Response response, Weibo weibo)
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
        ensureRootNodeNameIs("comments", document);
        nodelist = document.getDocumentElement().getElementsByTagName("comment");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_109;
        arraylist1.add(new Comment(response, (Element)nodelist.item(j), weibo));
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
        ensureRootNodeNameIs("comment", element);
        user = new User(response, (Element)element.getElementsByTagName("user").item(0), weibo);
        id = getChildLong("id", element);
        text = getChildText("text", element);
        source = getChildText("source", element);
        createdAt = getChildDate("created_at", element);
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
        if((obj instanceof Comment) && ((Comment)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
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

    public User getUser()
    {
        return user;
    }

    @Override
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

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("Comment{createdAt=").append(createdAt).append(", id=").append(id).append(", text='").append(text).append('\'').append(", source='").append(source).append('\'').append(", isTruncated=").append(isTruncated).append(", inReplyToStatusId=").append(inReplyToStatusId).append(", inReplyToUserId=").append(inReplyToUserId).append(", isFavorited=").append(isFavorited).append(", inReplyToScreenName='").append(inReplyToScreenName).append('\'').append(", latitude=").append(latitude).append(", longitude=").append(longitude).append(", retweetDetails=").append(retweetDetails).append(", user=").append(user).append('}').toString();
    }

    private static final long serialVersionUID = 0x1650c3edbf9bb2a0L;
    private Date createdAt;
    private long id;
    private String inReplyToScreenName;
    private long inReplyToStatusId;
    private int inReplyToUserId;
    private boolean isFavorited;
    private boolean isTruncated;
    private double latitude;
    private double longitude;
    private RetweetDetails retweetDetails;
    private String source;
    private String text;
    private User user;
}
