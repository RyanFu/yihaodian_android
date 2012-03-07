// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.Date;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, WeiboSupport

public class Tweet extends WeiboResponse
{

    Tweet(JSONObject jsonobject, WeiboSupport weibosupport)
        throws WeiboException
    {
        toUserId = -1;
        toUser = null;
        isoLanguageCode = null;
        try
        {
            text = getString("text", jsonobject, false);
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
        JSONException jsonexception2;
        try
        {
            toUserId = jsonobject.getInt("to_user_id");
            toUser = jsonobject.getString("to_user");
        }
        catch(JSONException jsonexception1) { }
        fromUser = jsonobject.getString("from_user");
        id = jsonobject.getLong("id");
        fromUserId = jsonobject.getInt("from_user_id");
        try
        {
            isoLanguageCode = jsonobject.getString("iso_language_code");
        }
        // Misplaced declaration of an exception variable
        catch(JSONException jsonexception2) { }
        source = getString("source", jsonobject, true);
        profileImageUrl = getString("profile_image_url", jsonobject, true);
        createdAt = parseDate(jsonobject.getString("created_at"), "E MMM dd hh:mm:ss z yyyy");
        return;
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
            Tweet tweet = (Tweet)obj;
            if(fromUserId != tweet.fromUserId)
                flag = false;
            else
            if(id != tweet.id)
                flag = false;
            else
            if(toUserId != tweet.toUserId)
                flag = false;
            else
            if(createdAt == null ? tweet.createdAt != null : !createdAt.equals(tweet.createdAt))
                flag = false;
            else
            if(fromUser == null ? tweet.fromUser != null : !fromUser.equals(tweet.fromUser))
                flag = false;
            else
            if(isoLanguageCode == null ? tweet.isoLanguageCode != null : !isoLanguageCode.equals(tweet.isoLanguageCode))
                flag = false;
            else
            if(profileImageUrl == null ? tweet.profileImageUrl != null : !profileImageUrl.equals(tweet.profileImageUrl))
                flag = false;
            else
            if(source == null ? tweet.source != null : !source.equals(tweet.source))
                flag = false;
            else
            if(text == null ? tweet.text != null : !text.equals(tweet.text))
                flag = false;
            else
            if(toUser == null ? tweet.toUser != null : !toUser.equals(tweet.toUser))
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

    public String getFromUser()
    {
        return fromUser;
    }

    public int getFromUserId()
    {
        return fromUserId;
    }

    public long getId()
    {
        return id;
    }

    public String getIsoLanguageCode()
    {
        return isoLanguageCode;
    }

    public String getProfileImageUrl()
    {
        return profileImageUrl;
    }

    public String getSource()
    {
        return source;
    }

    public String getText()
    {
        return text;
    }

    public String getToUser()
    {
        return toUser;
    }

    public int getToUserId()
    {
        return toUserId;
    }

    @Override
	public int hashCode()
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        if(text != null)
            i = text.hashCode();
        else
            i = 0;
        j = 31 * (i * 31 + (toUserId ^ toUserId >>> 32));
        if(toUser != null)
            k = toUser.hashCode();
        else
            k = 0;
        l = 31 * (j + k);
        if(fromUser != null)
            i1 = fromUser.hashCode();
        else
            i1 = 0;
        j1 = 31 * (31 * (31 * (l + i1) + (int)(id ^ id >>> 32)) + (fromUserId ^ fromUserId >>> 32));
        if(isoLanguageCode != null)
            k1 = isoLanguageCode.hashCode();
        else
            k1 = 0;
        l1 = 31 * (j1 + k1);
        if(source != null)
            i2 = source.hashCode();
        else
            i2 = 0;
        j2 = 31 * (l1 + i2);
        if(profileImageUrl != null)
            k2 = profileImageUrl.hashCode();
        else
            k2 = 0;
        l2 = 31 * (j2 + k2);
        if(createdAt != null)
            i3 = createdAt.hashCode();
        else
            i3 = 0;
        return l2 + i3;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("Tweet{text='").append(text).append('\'').append(", toUserId=").append(toUserId).append(", toUser='").append(toUser).append('\'').append(", fromUser='").append(fromUser).append('\'').append(", id=").append(id).append(", fromUserId=").append(fromUserId).append(", isoLanguageCode='").append(isoLanguageCode).append('\'').append(", source='").append(source).append('\'').append(", profileImageUrl='").append(profileImageUrl).append('\'').append(", createdAt=").append(createdAt).append('}').toString();
    }

    private static final long serialVersionUID = 0x3babbbc7515d16c3L;
    private Date createdAt;
    private String fromUser;
    private int fromUserId;
    private long id;
    private String isoLanguageCode;
    private String profileImageUrl;
    private String source;
    private String text;
    private String toUser;
    private int toUserId;
}
