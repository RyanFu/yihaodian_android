// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.w3c.dom.*;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, UserWapper, Weibo, 
//            DirectMessage

public class User extends WeiboResponse
    implements Serializable
{

    User(Response response, Element element, Weibo weibo1)
        throws WeiboException
    {
        super(response);
        statusId = -1L;
        statusText = null;
        statusSource = null;
        statusTruncated = false;
        statusInReplyToStatusId = -1L;
        statusInReplyToUserId = -1;
        statusFavorited = false;
        statusInReplyToScreenName = null;
        init(element, weibo1);
    }

    User(Response response, Weibo weibo1)
        throws WeiboException
    {
        super(response);
        statusId = -1L;
        statusText = null;
        statusSource = null;
        statusTruncated = false;
        statusInReplyToStatusId = -1L;
        statusInReplyToUserId = -1;
        statusFavorited = false;
        statusInReplyToScreenName = null;
        init(response.asDocument().getDocumentElement(), weibo1);
    }

    User(JSONObject jsonobject)
        throws WeiboException
    {
        statusId = -1L;
        statusText = null;
        statusSource = null;
        statusTruncated = false;
        statusInReplyToStatusId = -1L;
        statusInReplyToUserId = -1;
        statusFavorited = false;
        statusInReplyToScreenName = null;
        init(jsonobject);
    }

    static List constructResult(Response response)
        throws WeiboException
    {
        JSONArray jsonarray = response.asJSONArray();
        int i;
        ArrayList arraylist1;
        int j;
        i = jsonarray.length();
        arraylist1 = new ArrayList(i);
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_59;
        arraylist1.add(new User(jsonarray.getJSONObject(j)));
        j++;
          goto _L1
        ArrayList arraylist = arraylist1;
_L3:
        return arraylist;
        JSONException jsonexception;
        jsonexception;
        arraylist = null;
        if(true) goto _L3; else goto _L2
_L2:
    }

    public static List constructUsers(Response response)
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
        arraylist.add(new User(jsonarray.getJSONObject(j)));
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

    public static List constructUsers(Response response, Weibo weibo1)
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
        ArrayList arraylist1;
        int i;
        ensureRootNodeNameIs("users", document);
        nodelist = document.getDocumentElement().getChildNodes();
        arraylist1 = new ArrayList(nodelist.getLength());
        i = 0;
_L3:
        if(i >= nodelist.getLength())
            break MISSING_BLOCK_LABEL_127;
        Node node = nodelist.item(i);
        if(node.getNodeName().equals("user"))
            arraylist1.add(new User(response, (Element)node, weibo1));
        i++;
          goto _L3
        arraylist = arraylist1;
        continue; /* Loop/switch isn't completed */
        WeiboException weiboexception;
        weiboexception;
        if(isRootNodeNilClasses(document))
            arraylist = new ArrayList(0);
        else
            throw weiboexception;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static UserWapper constructWapperUsers(Response response)
        throws WeiboException
    {
        JSONObject jsonobject = response.asJSONObject();
        UserWapper userwapper;
        try
        {
            JSONArray jsonarray = jsonobject.getJSONArray("users");
            int i = jsonarray.length();
            ArrayList arraylist = new ArrayList(i);
            for(int j = 0; j < i; j++)
                arraylist.add(new User(jsonarray.getJSONObject(j)));

            long l = jsonobject.getLong("previous_curosr");
            long l1 = jsonobject.getLong("next_cursor");
            if(l1 == -1L)
                l1 = jsonobject.getLong("nextCursor");
            userwapper = new UserWapper(arraylist, l, l1);
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException(jsonexception);
        }
        return userwapper;
    }

    public static UserWapper constructWapperUsers(Response response, Weibo weibo1)
        throws WeiboException
    {
        Document document = response.asDocument();
        if(!isRootNodeNilClasses(document)) goto _L2; else goto _L1
_L1:
        UserWapper userwapper = new UserWapper(new ArrayList(0), 0L, 0L);
_L4:
        return userwapper;
_L2:
        Element element;
        NodeList nodelist1;
        ArrayList arraylist;
        ensureRootNodeNameIs("users_list", document);
        element = document.getDocumentElement();
        NodeList nodelist = element.getElementsByTagName("users");
        if(nodelist.getLength() == 0)
        {
            userwapper = new UserWapper(new ArrayList(0), 0L, 0L);
            continue; /* Loop/switch isn't completed */
        }
        nodelist1 = ((Element)nodelist.item(0)).getChildNodes();
        arraylist = new ArrayList(nodelist1.getLength());
        WeiboException weiboexception;
        long l;
        long l1;
        UserWapper userwapper1;
        for(int i = 0; i < nodelist1.getLength(); i++)
        {
            Node node = nodelist1.item(i);
            if(node.getNodeName().equals("user"))
                arraylist.add(new User(response, (Element)node, weibo1));
            break MISSING_BLOCK_LABEL_278;
        }

        l = getChildLong("previous_curosr", element);
        l1 = getChildLong("next_curosr", element);
        if(l1 == -1L)
            l1 = getChildLong("nextCurosr", element);
        userwapper1 = new UserWapper(arraylist, l, l1);
        userwapper = userwapper1;
        continue; /* Loop/switch isn't completed */
        weiboexception;
        if(isRootNodeNilClasses(document))
            userwapper = new UserWapper(new ArrayList(0), 0L, 0L);
        else
            throw weiboexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void init(Element element, Weibo weibo1)
        throws WeiboException
    {
        weibo = weibo1;
        ensureRootNodeNameIs(POSSIBLE_ROOT_NAMES, element);
        id = getChildInt("id", element);
        name = getChildText("name", element);
        screenName = getChildText("screen_name", element);
        location = getChildText("location", element);
        description = getChildText("description", element);
        profileImageUrl = getChildText("profile_image_url", element);
        url = getChildText("url", element);
        isProtected = getChildBoolean("protected", element);
        followersCount = getChildInt("followers_count", element);
        profileBackgroundColor = getChildText("profile_background_color", element);
        profileTextColor = getChildText("profile_text_color", element);
        profileLinkColor = getChildText("profile_link_color", element);
        profileSidebarFillColor = getChildText("profile_sidebar_fill_color", element);
        profileSidebarBorderColor = getChildText("profile_sidebar_border_color", element);
        friendsCount = getChildInt("friends_count", element);
        createdAt = getChildDate("created_at", element);
        favouritesCount = getChildInt("favourites_count", element);
        utcOffset = getChildInt("utc_offset", element);
        timeZone = getChildText("time_zone", element);
        profileBackgroundImageUrl = getChildText("profile_background_image_url", element);
        profileBackgroundTile = getChildText("profile_background_tile", element);
        following = getChildBoolean("following", element);
        notificationEnabled = getChildBoolean("notifications", element);
        statusesCount = getChildInt("statuses_count", element);
        geoEnabled = getChildBoolean("geo_enabled", element);
        verified = getChildBoolean("verified", element);
        NodeList nodelist = element.getElementsByTagName("status");
        if(nodelist.getLength() != 0)
        {
            Element element1 = (Element)nodelist.item(0);
            statusCreatedAt = getChildDate("created_at", element1);
            statusId = getChildLong("id", element1);
            statusText = getChildText("text", element1);
            statusSource = getChildText("source", element1);
            statusTruncated = getChildBoolean("truncated", element1);
            statusInReplyToStatusId = getChildLong("in_reply_to_status_id", element1);
            statusInReplyToUserId = getChildInt("in_reply_to_user_id", element1);
            statusFavorited = getChildBoolean("favorited", element1);
            statusInReplyToScreenName = getChildText("in_reply_to_screen_name", element1);
        }
    }

    private void init(JSONObject jsonobject)
        throws WeiboException
    {
        if(jsonobject == null)
            break MISSING_BLOCK_LABEL_391;
        id = jsonobject.getInt("id");
        name = jsonobject.getString("name");
        screenName = jsonobject.getString("screen_name");
        location = jsonobject.getString("location");
        description = jsonobject.getString("description");
        profileImageUrl = jsonobject.getString("profile_image_url");
        url = jsonobject.getString("url");
        isProtected = jsonobject.getBoolean("protected");
        followersCount = jsonobject.getInt("followers_count");
        profileBackgroundColor = jsonobject.getString("profile_background_color");
        profileTextColor = jsonobject.getString("profile_text_color");
        profileLinkColor = jsonobject.getString("profile_link_color");
        profileSidebarFillColor = jsonobject.getString("profile_sidebar_fill_color");
        profileSidebarBorderColor = jsonobject.getString("profile_sidebar_border_color");
        friendsCount = jsonobject.getInt("friends_count");
        createdAt = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
        favouritesCount = jsonobject.getInt("favourites_count");
        utcOffset = getInt("utc_offset", jsonobject);
        timeZone = jsonobject.getString("time_zone");
        profileBackgroundImageUrl = jsonobject.getString("profile_background_image_url");
        profileBackgroundTile = jsonobject.getString("profile_background_tile");
        following = getBoolean("following", jsonobject);
        notificationEnabled = getBoolean("notifications", jsonobject);
        statusesCount = jsonobject.getInt("statuses_count");
        if(!jsonobject.isNull("status"))
        {
            JSONObject jsonobject1 = jsonobject.getJSONObject("status");
            statusCreatedAt = parseDate(jsonobject1.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
            statusId = jsonobject1.getLong("id");
            statusText = jsonobject1.getString("text");
            statusSource = jsonobject1.getString("source");
            statusTruncated = jsonobject1.getBoolean("truncated");
            statusInReplyToStatusId = jsonobject1.getLong("in_reply_to_status_id");
            statusInReplyToUserId = jsonobject1.getInt("in_reply_to_user_id");
            statusFavorited = jsonobject1.getBoolean("favorited");
            statusInReplyToScreenName = jsonobject1.getString("in_reply_to_screen_name");
        }
        return;
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
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
        if((obj instanceof User) && ((User)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public String getDescription()
    {
        return description;
    }

    public int getFavouritesCount()
    {
        return favouritesCount;
    }

    public int getFollowersCount()
    {
        return followersCount;
    }

    public int getFriendsCount()
    {
        return friendsCount;
    }

    public int getId()
    {
        return id;
    }

    public String getLocation()
    {
        return location;
    }

    public String getName()
    {
        return name;
    }

    public String getProfileBackgroundColor()
    {
        return profileBackgroundColor;
    }

    public String getProfileBackgroundImageUrl()
    {
        return profileBackgroundImageUrl;
    }

    public String getProfileBackgroundTile()
    {
        return profileBackgroundTile;
    }

    public URL getProfileImageURL()
    {
        URL url1;
        try
        {
            url1 = new URL(profileImageUrl);
        }
        catch(MalformedURLException malformedurlexception)
        {
            url1 = null;
        }
        return url1;
    }

    public String getProfileLinkColor()
    {
        return profileLinkColor;
    }

    public String getProfileSidebarBorderColor()
    {
        return profileSidebarBorderColor;
    }

    public String getProfileSidebarFillColor()
    {
        return profileSidebarFillColor;
    }

    public String getProfileTextColor()
    {
        return profileTextColor;
    }

    public String getScreenName()
    {
        return screenName;
    }

    public Date getStatusCreatedAt()
    {
        return statusCreatedAt;
    }

    public long getStatusId()
    {
        return statusId;
    }

    public String getStatusInReplyToScreenName()
    {
        String s;
        if(-1 != statusInReplyToUserId)
            s = statusInReplyToScreenName;
        else
            s = null;
        return s;
    }

    public long getStatusInReplyToStatusId()
    {
        return statusInReplyToStatusId;
    }

    public int getStatusInReplyToUserId()
    {
        return statusInReplyToUserId;
    }

    public String getStatusSource()
    {
        return statusSource;
    }

    public String getStatusText()
    {
        return statusText;
    }

    public int getStatusesCount()
    {
        return statusesCount;
    }

    public String getTimeZone()
    {
        return timeZone;
    }

    public URL getURL()
    {
        URL url1;
        try
        {
            url1 = new URL(url);
        }
        catch(MalformedURLException malformedurlexception)
        {
            url1 = null;
        }
        return url1;
    }

    public int getUtcOffset()
    {
        return utcOffset;
    }

    public int hashCode()
    {
        return id;
    }

    public boolean isFollowing()
    {
        return following;
    }

    public boolean isGeoEnabled()
    {
        return geoEnabled;
    }

    public boolean isNotificationEnabled()
    {
        return notificationEnabled;
    }

    public boolean isNotifications()
    {
        return notificationEnabled;
    }

    public boolean isProtected()
    {
        return isProtected;
    }

    public boolean isStatusFavorited()
    {
        return statusFavorited;
    }

    public boolean isStatusTruncated()
    {
        return statusTruncated;
    }

    public boolean isVerified()
    {
        return verified;
    }

    public DirectMessage sendDirectMessage(String s)
        throws WeiboException
    {
        return weibo.sendDirectMessage(getName(), s);
    }

    public String toString()
    {
        return (new StringBuilder()).append("User{weibo=").append(weibo).append(", id=").append(id).append(", name='").append(name).append('\'').append(", screenName='").append(screenName).append('\'').append(", location='").append(location).append('\'').append(", description='").append(description).append('\'').append(", profileImageUrl='").append(profileImageUrl).append('\'').append(", url='").append(url).append('\'').append(", isProtected=").append(isProtected).append(", followersCount=").append(followersCount).append(", statusCreatedAt=").append(statusCreatedAt).append(", statusId=").append(statusId).append(", statusText='").append(statusText).append('\'').append(", statusSource='").append(statusSource).append('\'').append(", statusTruncated=").append(statusTruncated).append(", statusInReplyToStatusId=").append(statusInReplyToStatusId).append(", statusInReplyToUserId=").append(statusInReplyToUserId).append(", statusFavorited=").append(statusFavorited).append(", statusInReplyToScreenName='").append(statusInReplyToScreenName).append('\'').append(", profileBackgroundColor='").append(profileBackgroundColor).append('\'').append(", profileTextColor='").append(profileTextColor).append('\'').append(", profileLinkColor='").append(profileLinkColor).append('\'').append(", profileSidebarFillColor='").append(profileSidebarFillColor).append('\'').append(", profileSidebarBorderColor='").append(profileSidebarBorderColor).append('\'').append(", friendsCount=").append(friendsCount).append(", createdAt=").append(createdAt).append(", favouritesCount=").append(favouritesCount).append(", utcOffset=").append(utcOffset).append(", timeZone='").append(timeZone).append('\'').append(", profileBackgroundImageUrl='").append(profileBackgroundImageUrl).append('\'').append(", profileBackgroundTile='").append(profileBackgroundTile).append('\'').append(", following=").append(following).append(", notificationEnabled=").append(notificationEnabled).append(", statusesCount=").append(statusesCount).append(", geoEnabled=").append(geoEnabled).append(", verified=").append(verified).append('}').toString();
    }

    static final String POSSIBLE_ROOT_NAMES[];
    private static final long serialVersionUID = 0xa7eedbb8d7ed08daL;
    private Date createdAt;
    private String description;
    private int favouritesCount;
    private int followersCount;
    private boolean following;
    private int friendsCount;
    private boolean geoEnabled;
    private int id;
    private boolean isProtected;
    private String location;
    private String name;
    private boolean notificationEnabled;
    private String profileBackgroundColor;
    private String profileBackgroundImageUrl;
    private String profileBackgroundTile;
    private String profileImageUrl;
    private String profileLinkColor;
    private String profileSidebarBorderColor;
    private String profileSidebarFillColor;
    private String profileTextColor;
    private String screenName;
    private Date statusCreatedAt;
    private boolean statusFavorited;
    private long statusId;
    private String statusInReplyToScreenName;
    private long statusInReplyToStatusId;
    private int statusInReplyToUserId;
    private String statusSource;
    private String statusText;
    private boolean statusTruncated;
    private int statusesCount;
    private String timeZone;
    private String url;
    private int utcOffset;
    private boolean verified;
    private Weibo weibo;

    static 
    {
        String as[] = new String[4];
        as[0] = "user";
        as[1] = "sender";
        as[2] = "recipient";
        as[3] = "retweeting_user";
        POSSIBLE_ROOT_NAMES = as;
    }
}
