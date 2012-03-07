// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import weibo4andriod.http.AccessToken;
import weibo4andriod.http.HttpClient;
import weibo4andriod.http.ImageItem;
import weibo4andriod.http.PostParameter;
import weibo4andriod.http.RequestToken;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboSupport, Configuration, WeiboException, ListObject, 
//            User, Status, SavedSearch, Tag, 
//            Comment, DirectMessage, Paging, IDs, 
//            Count, Trends, ListUserCount, RateLimitStatus, 
//            RetweetDetails, QueryResult, Query, UserWapper, 
//            ListObjectWapper

public class Weibo extends WeiboSupport
    implements Serializable
{
    static class Device
    {

        final String DEVICE;

        public Device(String s)
        {
            DEVICE = s;
        }
    }


    public Weibo()
    {
        baseURL = (new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/").toString();
        searchBaseURL = (new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/").toString();
        format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        http.setRequestTokenURL((new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/request_token").toString());
        http.setAuthorizationURL((new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/authorize").toString());
        http.setAccessTokenURL((new StringBuilder()).append(Configuration.getScheme()).append("api.t.sina.com.cn/oauth/access_token").toString());
    }

    public Weibo(String s)
    {
        this();
        baseURL = s;
    }

    public Weibo(String s, String s1)
    {
        this();
        setUserId(s);
        setPassword(s1);
    }

    public Weibo(String s, String s1, String s2)
    {
        this();
        setUserId(s);
        setPassword(s1);
        baseURL = s2;
    }

    private void addParameterToList(List list, String s, String s1)
    {
        if(s1 != null)
            list.add(new PostParameter(s, s1));
    }

    private Response get(String s, boolean flag)
        throws WeiboException
    {
        return get(s, null, flag);
    }

    private String toDateStr(Date date)
    {
        Date date1;
        if(date == null)
            date1 = new Date();
        else
            date1 = date;
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date1);
    }

    public ListObject addListMember(String s, String s1, String s2, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/members.xml");
        String s3 = stringbuilder.toString();
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(new PostParameter("id", String.valueOf(s2)));
        linkedlist.add(new PostParameter("source", CONSUMER_KEY));
        PostParameter apostparameter[] = new PostParameter[linkedlist.size()];
        int i = 0;
        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext();)
        {
            PostParameter postparameter = (PostParameter)iterator.next();
            int j = i + 1;
            apostparameter[i] = postparameter;
            i = j;
        }

        return new ListObject(http.httpRequest(s3, apostparameter, flag, "POST"), this);
    }

    public ListObject addListSubscriber(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/subscribers.xml");
        String s2 = stringbuilder.toString();
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(new PostParameter("source", CONSUMER_KEY));
        PostParameter apostparameter[] = new PostParameter[linkedlist.size()];
        int i = 0;
        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext();)
        {
            PostParameter postparameter = (PostParameter)iterator.next();
            int j = i + 1;
            apostparameter[i] = postparameter;
            i = j;
        }

        return new ListObject(http.httpRequest(s2, apostparameter, flag, "POST"), this);
    }

    public User block(String s)
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("blocks/create/").append(s).append(".xml").toString(), true), this);
    }

    public User create(String s)
        throws WeiboException
    {
        return createFriendship(s);
    }

    public User createBlock(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("blocks/create.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("id", s);
        return new User(httpclient.post(s1, apostparameter, true).asJSONObject());
    }

    public Status createFavorite(long l)
        throws WeiboException
    {
        return new Status(http.post((new StringBuilder()).append(getBaseURL()).append("favorites/create/").append(l).append(".json").toString(), true));
    }

    public User createFriendship(String s)
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("friendships/create/").append(s).append(".json").toString(), new PostParameter[0], true).asJSONObject());
    }

    public User createFriendship(String s, boolean flag)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("friendships/create/").append(s).append(".json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("follow", String.valueOf(flag));
        return new User(httpclient.post(s1, apostparameter, true).asJSONObject());
    }

    public SavedSearch createSavedSearch(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("saved_searches/create.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("query", s);
        return new SavedSearch(httpclient.post(s1, apostparameter, true));
    }

    public List createTags(String s)
        throws WeiboException, JSONException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("tags/create.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("tags", s);
        return Tag.constructTags(httpclient.post(s1, apostparameter, true));
    }

    public DirectMessage deleteDirectMessage(int i)
        throws WeiboException
    {
        return destroyDirectMessage(i);
    }

    public JSONObject destoryTag(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("tags/destroy.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("tag_id", s);
        return httpclient.post(s1, apostparameter, true).asJSONObject();
    }

    public List destory_batchTags(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("tags/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", s);
        return Tag.constructTags(httpclient.post(s1, apostparameter, true));
    }

    public User destroy(String s)
        throws WeiboException
    {
        return destroyFriendship(s);
    }

    public User destroyBlock(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("blocks/destroy.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("id", s);
        return new User(httpclient.post(s1, apostparameter, true).asJSONObject());
    }

    public Comment destroyComment(long l)
        throws WeiboException
    {
        return new Comment(http.delete((new StringBuilder()).append(getBaseURL()).append("statuses/comment_destroy/").append(l).append(".json?source=").append(CONSUMER_KEY).toString(), true));
    }

    public List destroyComments(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/comment/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", s);
        return Comment.constructComments(httpclient.post(s1, apostparameter, true));
    }

    public List destroyComments(String as[])
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = as.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(as[j]).append(',');

        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(getBaseURL()).append("statuses/comment/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", stringbuilder.toString());
        return Comment.constructComments(httpclient.post(s, apostparameter, true));
    }

    public DirectMessage destroyDirectMessage(int i)
        throws WeiboException
    {
        return new DirectMessage(http.post((new StringBuilder()).append(getBaseURL()).append("direct_messages/destroy/").append(i).append(".json").toString(), new PostParameter[0], true).asJSONObject());
    }

    public List destroyDirectMessages(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("direct_messages/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", s);
        return DirectMessage.constructDirectMessages(httpclient.post(s1, apostparameter, true));
    }

    public List destroyDirectMessages(String as[])
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = as.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(as[j]).append(',');

        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(getBaseURL()).append("direct_messages/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", stringbuilder.toString());
        return DirectMessage.constructDirectMessages(httpclient.post(s, apostparameter, true));
    }

    public Status destroyFavorite(long l)
        throws WeiboException
    {
        return new Status(http.post((new StringBuilder()).append(getBaseURL()).append("favorites/destroy/").append(l).append(".json").toString(), true));
    }

    public List destroyFavorites(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("favorites/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", s);
        return Status.constructStatuses(httpclient.post(s1, apostparameter, true));
    }

    public List destroyFavorites(String as[])
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = as.length;
        for(int j = 0; j < i; j++)
            stringbuilder.append(as[j]).append(',');

        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(getBaseURL()).append("favorites/destroy_batch.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("ids", stringbuilder.toString());
        return Status.constructStatuses(httpclient.post(s, apostparameter, true));
    }

    public User destroyFriendship(String s)
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("friendships/destroy/").append(s).append(".json").toString(), new PostParameter[0], true).asJSONObject());
    }

    public SavedSearch destroySavedSearch(int i)
        throws WeiboException
    {
        return new SavedSearch(http.post((new StringBuilder()).append(getBaseURL()).append("saved_searches/destroy/").append(i).append(".json").toString(), true));
    }

    public Status destroyStatus(long l)
        throws WeiboException
    {
        return new Status(http.post((new StringBuilder()).append(getBaseURL()).append("statuses/destroy/").append(l).append(".json").toString(), new PostParameter[0], true));
    }

    public User disableNotification(String s)
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("notifications/leave/").append(s).append(".json").toString(), true).asJSONObject());
    }

    public User enableNotification(String s)
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("notifications/follow/").append(s).append(".json").toString(), true).asJSONObject());
    }

    public User endSession()
        throws WeiboException
    {
        return new User(get((new StringBuilder()).append(getBaseURL()).append("account/end_session.json").toString(), true).asJSONObject());
    }

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
            Weibo weibo = (Weibo)obj;
            if(!baseURL.equals(weibo.baseURL))
                flag = false;
            else
            if(!format.equals(weibo.format))
                flag = false;
            else
            if(!http.equals(weibo.http))
                flag = false;
            else
            if(!searchBaseURL.equals(weibo.searchBaseURL))
                flag = false;
            else
            if(!source.equals(weibo.source))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public boolean exists(String s, String s1)
        throws WeiboException
    {
        return existsFriendship(s, s1);
    }

    public boolean existsBlock(String s)
        throws WeiboException
    {
        int i = get((new StringBuilder()).append(getBaseURL()).append("blocks/exists.json?user_id=").append(s).toString(), true).asString().indexOf("<error>You are not blocking this user.</error>");
        boolean flag;
        if(-1 == i)
            flag = true;
        else
            flag = false;
_L2:
        return flag;
        WeiboException weiboexception;
        weiboexception;
        if(weiboexception.getStatusCode() == 404)
            flag = false;
        else
            throw weiboexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean existsFriendship(String s, String s1)
        throws WeiboException
    {
        boolean flag;
        if(-1 != get((new StringBuilder()).append(getBaseURL()).append("friendships/exists.json").toString(), "user_a", s, "user_b", s1, true).asString().indexOf("true"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public List favorites()
        throws WeiboException
    {
        return getFavorites();
    }

    public List favorites(int i)
        throws WeiboException
    {
        return getFavorites(i);
    }

    public List favorites(String s)
        throws WeiboException
    {
        return getFavorites(s);
    }

    public List favorites(String s, int i)
        throws WeiboException
    {
        return getFavorites(s, i);
    }

    public User follow(String s)
        throws WeiboException
    {
        return enableNotification(s);
    }

    public volatile void forceUsePost(boolean flag)
    {
        super.forceUsePost(flag);
    }

    protected Response get(String s, String s1, String s2, String s3, String s4, boolean flag)
        throws WeiboException
    {
        PostParameter apostparameter[] = new PostParameter[2];
        apostparameter[0] = new PostParameter(s1, s2);
        apostparameter[1] = new PostParameter(s3, s4);
        return get(s, apostparameter, flag);
    }

    protected Response get(String s, String s1, String s2, boolean flag)
        throws WeiboException
    {
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter(s1, s2);
        return get(s, apostparameter, flag);
    }

    protected Response get(String s, PostParameter apostparameter[], Paging paging, boolean flag)
        throws WeiboException
    {
        if(paging != null)
        {
            ArrayList arraylist = new ArrayList(4);
            if(-1L != paging.getMaxId())
                arraylist.add(new PostParameter("max_id", String.valueOf(paging.getMaxId())));
            if(-1L != paging.getSinceId())
                arraylist.add(new PostParameter("since_id", String.valueOf(paging.getSinceId())));
            if(-1 != paging.getPage())
                arraylist.add(new PostParameter("page", String.valueOf(paging.getPage())));
            PostParameter apostparameter1[];
            PostParameter apostparameter2[];
            String s1;
            Response response;
            if(-1 != paging.getCount())
                if(-1 != s.indexOf("search"))
                    arraylist.add(new PostParameter("rpp", String.valueOf(paging.getCount())));
                else
                    arraylist.add(new PostParameter("count", String.valueOf(paging.getCount())));
            apostparameter1 = (PostParameter[])arraylist.toArray(new PostParameter[arraylist.size()]);
            if(apostparameter != null)
            {
                PostParameter apostparameter3[] = new PostParameter[apostparameter.length + arraylist.size()];
                System.arraycopy(apostparameter, 0, apostparameter3, 0, apostparameter.length);
                System.arraycopy(apostparameter1, 0, apostparameter3, apostparameter.length, arraylist.size());
                apostparameter2 = apostparameter3;
                s1 = s;
            } else
            if(apostparameter1.length != 0)
            {
                String s2 = HttpClient.encodeParameters(apostparameter1);
                if(-1 != s.indexOf("?"))
                {
                    s1 = (new StringBuilder()).append(s).append("&source=").append(CONSUMER_KEY).append("&").append(s2).toString();
                    apostparameter2 = null;
                } else
                {
                    s1 = (new StringBuilder()).append(s).append("?source=").append(CONSUMER_KEY).append("&").append(s2).toString();
                    apostparameter2 = null;
                }
            } else
            {
                apostparameter2 = null;
                s1 = s;
            }
            response = get(s1, apostparameter2, flag);
        } else
        {
            response = get(s, apostparameter, flag);
        }
        return response;
    }

    protected Response get(String s, PostParameter apostparameter[], boolean flag)
        throws WeiboException
    {
        String s1;
        if(s.indexOf("?") == -1)
            s1 = (new StringBuilder()).append(s).append("?source=").append(CONSUMER_KEY).toString();
        else
        if(s.indexOf("source") == -1)
            s1 = (new StringBuilder()).append(s).append("&source=").append(CONSUMER_KEY).toString();
        else
            s1 = s;
        if(apostparameter != null && apostparameter.length > 0)
            s1 = (new StringBuilder()).append(s1).append("&").append(HttpClient.encodeParameters(apostparameter)).toString();
        return http.get(s1, flag);
    }

    public User getAuthenticatedUser()
        throws WeiboException
    {
        return new User(get((new StringBuilder()).append(getBaseURL()).append("account/verify_credentials.xml").toString(), true), this);
    }

    public String getBaseURL()
    {
        return baseURL;
    }

    public List getBlockingUsers()
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("blocks/blocking.json").toString(), true));
    }

    public List getBlockingUsers(int i)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("blocks/blocking.json?page=").append(i).toString(), true));
    }

    public IDs getBlockingUsersIDs()
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("blocks/blocking/ids.json").toString(), true), this);
    }

    public volatile String getClientURL()
    {
        return super.getClientURL();
    }

    public volatile String getClientVersion()
    {
        return super.getClientVersion();
    }

    public List getComments(String s)
        throws WeiboException
    {
        return Comment.constructComments(get((new StringBuilder()).append(getBaseURL()).append("statuses/comments.json?id=").append(s).toString(), true));
    }

    public List getCommentsByMe()
        throws WeiboException
    {
        return Comment.constructComments(get((new StringBuilder()).append(getBaseURL()).append("statuses/comments_by_me.json").toString(), true));
    }

    public List getCommentsTimeline()
        throws WeiboException
    {
        return Comment.constructComments(get((new StringBuilder()).append(getBaseURL()).append("statuses/comments_timeline.json").toString(), true));
    }

    public List getCommentsToMe()
        throws WeiboException
    {
        return Comment.constructComments(get((new StringBuilder()).append(getBaseURL()).append("statuses/comments_to_me.json").toString(), true));
    }

    public List getCounts(String s)
        throws WeiboException
    {
        return Count.constructCounts(get((new StringBuilder()).append(getBaseURL()).append("statuses/counts.json?ids=").append(s).toString(), true));
    }

    public Trends getCurrentTrends()
        throws WeiboException
    {
        return (Trends)Trends.constructTrendsList(get((new StringBuilder()).append(searchBaseURL).append("trends/current.json").toString(), false)).get(0);
    }

    public Trends getCurrentTrends(boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(searchBaseURL).append("trends/current.json");
        String s;
        if(flag)
            s = "?exclude=hashtags";
        else
            s = "";
        return (Trends)Trends.constructTrendsList(get(stringbuilder.append(s).toString(), false)).get(0);
    }

    public List getDailyTrends()
        throws WeiboException
    {
        return Trends.constructTrendsList(get((new StringBuilder()).append(searchBaseURL).append("trends/daily.json").toString(), false));
    }

    public List getDailyTrends(Date date, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(searchBaseURL).append("trends/daily.json?date=").append(toDateStr(date));
        String s;
        if(flag)
            s = "&exclude=hashtags";
        else
            s = "";
        return Trends.constructTrendsList(get(stringbuilder.append(s).toString(), false));
    }

    public List getDirectMessages()
        throws WeiboException
    {
        return DirectMessage.constructDirectMessages(get((new StringBuilder()).append(getBaseURL()).append("direct_messages.json").toString(), true));
    }

    public List getDirectMessages(int i)
        throws WeiboException
    {
        return getDirectMessages(new Paging(i));
    }

    public List getDirectMessages(int i, int j)
        throws WeiboException
    {
        return getDirectMessages((new Paging(i)).sinceId(j));
    }

    public List getDirectMessages(Date date)
        throws WeiboException
    {
        return DirectMessage.constructDirectMessages(get((new StringBuilder()).append(getBaseURL()).append("direct_messages.xml").toString(), "since", format.format(date), true), this);
    }

    public List getDirectMessages(Paging paging)
        throws WeiboException
    {
        return DirectMessage.constructDirectMessages(get((new StringBuilder()).append(getBaseURL()).append("direct_messages.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getDirectMessagesByPage(int i)
        throws WeiboException
    {
        return getDirectMessages(new Paging(i));
    }

    public String getDowntimeSchedule()
        throws WeiboException
    {
        throw new WeiboException("this method is not supported by the Weibo API anymore", new NoSuchMethodException("this method is not supported by the Weibo API anymore"));
    }

    public List getFavorites()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("favorites.json").toString(), new PostParameter[0], true));
    }

    public List getFavorites(int i)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("favorites.json").toString(), "page", String.valueOf(i), true));
    }

    public List getFavorites(String s)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("favorites/").append(s).append(".json").toString(), new PostParameter[0], true));
    }

    public List getFavorites(String s, int i)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("favorites/").append(s).append(".json").toString(), "page", String.valueOf(i), true));
    }

    public List getFeatured()
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/featured.json").toString(), true));
    }

    public List getFollowers()
        throws WeiboException
    {
        return getFollowersStatuses();
    }

    public List getFollowers(int i)
        throws WeiboException
    {
        return getFollowersStatuses(new Paging(i));
    }

    public List getFollowers(String s)
        throws WeiboException
    {
        return getFollowersStatuses(s);
    }

    public List getFollowers(String s, int i)
        throws WeiboException
    {
        return getFollowersStatuses(s, new Paging(i));
    }

    public List getFollowers(String s, Paging paging)
        throws WeiboException
    {
        return getFollowersStatuses(s, paging);
    }

    public List getFollowers(Paging paging)
        throws WeiboException
    {
        return getFollowersStatuses(paging);
    }

    public IDs getFollowersIDs()
        throws WeiboException
    {
        return getFollowersIDs(-1L);
    }

    public IDs getFollowersIDs(int i)
        throws WeiboException
    {
        return getFollowersIDs(i, -1L);
    }

    public IDs getFollowersIDs(int i, long l)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("followers/ids.xml?user_id=").append(i).append("&cursor=").append(l).toString(), true));
    }

    public IDs getFollowersIDs(int i, Paging paging)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("followers/ids.xml?user_id=").append(i).toString(), ((PostParameter []) (null)), paging, true));
    }

    public IDs getFollowersIDs(long l)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("followers/ids.json?cursor=").append(l).toString(), true), this);
    }

    public IDs getFollowersIDs(String s)
        throws WeiboException
    {
        return getFollowersIDs(s, -1L);
    }

    public IDs getFollowersIDs(String s, long l)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("followers/ids.json?screen_name=").append(s).append("&cursor=").append(l).toString(), true), this);
    }

    public IDs getFollowersIDs(String s, Paging paging)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("followers/ids.xml?screen_name=").append(s).toString(), ((PostParameter []) (null)), paging, true));
    }

    public IDs getFollowersIDs(Paging paging)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("followers/ids.xml").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getFollowersStatuses()
        throws WeiboException
    {
        return User.constructResult(get((new StringBuilder()).append(getBaseURL()).append("statuses/followers.json").toString(), true));
    }

    public List getFollowersStatuses(String s)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/followers/").append(s).append(".json").toString(), true));
    }

    public List getFollowersStatuses(String s, Paging paging)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/followers/").append(s).append(".json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getFollowersStatuses(Paging paging)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/followers.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getFriends()
        throws WeiboException
    {
        return getFriendsStatuses();
    }

    public List getFriends(int i)
        throws WeiboException
    {
        return getFriendsStatuses(new Paging(i));
    }

    public List getFriends(String s)
        throws WeiboException
    {
        return getFriendsStatuses(s);
    }

    public List getFriends(String s, int i)
        throws WeiboException
    {
        return getFriendsStatuses(s, new Paging(i));
    }

    public List getFriends(String s, Paging paging)
        throws WeiboException
    {
        return getFriendsStatuses(s, paging);
    }

    public List getFriends(Paging paging)
        throws WeiboException
    {
        return getFriendsStatuses(paging);
    }

    public IDs getFriendsIDs()
        throws WeiboException
    {
        return getFriendsIDs(-1L);
    }

    public IDs getFriendsIDs(int i)
        throws WeiboException
    {
        return getFriendsIDs(i, -1L);
    }

    public IDs getFriendsIDs(int i, long l)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("friends/ids.json?user_id=").append(i).append("&cursor=").append(l).toString(), true), this);
    }

    public IDs getFriendsIDs(int i, Paging paging)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("friends/ids.xml?user_id=").append(i).toString(), ((PostParameter []) (null)), paging, true));
    }

    public IDs getFriendsIDs(long l)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("friends/ids.xml?cursor=").append(l).toString(), true));
    }

    public IDs getFriendsIDs(String s)
        throws WeiboException
    {
        return getFriendsIDs(s, -1L);
    }

    public IDs getFriendsIDs(String s, long l)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("friends/ids.json?screen_name=").append(s).append("&cursor=").append(l).toString(), true), this);
    }

    public IDs getFriendsIDs(String s, Paging paging)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("friends/ids.xml?screen_name=").append(s).toString(), ((PostParameter []) (null)), paging, true));
    }

    public IDs getFriendsIDs(Paging paging)
        throws WeiboException
    {
        return new IDs(get((new StringBuilder()).append(getBaseURL()).append("friends/ids.xml").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getFriendsStatuses()
        throws WeiboException
    {
        return User.constructResult(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends.json").toString(), true));
    }

    public List getFriendsStatuses(String s)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends/").append(s).append(".json").toString(), false));
    }

    public List getFriendsStatuses(String s, Paging paging)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends/").append(s).append(".json").toString(), false));
    }

    public List getFriendsStatuses(Paging paging)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getFriendsTimeline()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends_timeline.json").toString(), true));
    }

    public List getFriendsTimeline(int i)
        throws WeiboException
    {
        return getFriendsTimeline(new Paging(i));
    }

    public List getFriendsTimeline(long l)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends_timeline.xml").toString(), "since_id", String.valueOf(l), true), this);
    }

    public List getFriendsTimeline(long l, int i)
        throws WeiboException
    {
        return getFriendsTimeline((new Paging(i)).sinceId(l));
    }

    public List getFriendsTimeline(long l, String s, int i)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getFriendsTimeline(String s)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getFriendsTimeline(String s, int i)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getFriendsTimeline(String s, long l)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getFriendsTimeline(String s, Date date)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getFriendsTimeline(String s, Paging paging)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getFriendsTimeline(Date date)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends_timeline.xml").toString(), "since", format.format(date), true), this);
    }

    public List getFriendsTimeline(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/friends_timeline.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getFriendsTimelineByPage(int i)
        throws WeiboException
    {
        return getFriendsTimeline(new Paging(i));
    }

    public List getFriendsTimelineByPage(String s, int i)
        throws WeiboException
    {
        throw new IllegalStateException("The Weibo API is not supporting this method anymore");
    }

    public List getHomeTimeline()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/home_timeline.json").toString(), true));
    }

    public List getHomeTimeline(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/home_timeline.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getHotUsers(String s)
        throws WeiboException
    {
        return User.constructUsers(get((new StringBuilder()).append(getBaseURL()).append("users/hot.json").toString(), "category", s, true));
    }

    public ListObject getList(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists/").append(s1).append(".xml").append("?source=").append(CONSUMER_KEY);
        String s2 = stringbuilder.toString();
        return new ListObject(http.httpRequest(s2, null, flag, "GET"), this);
    }

    public UserWapper getListMembers(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/members.xml").append("?source=").append(CONSUMER_KEY);
        String s2 = stringbuilder.toString();
        return User.constructWapperUsers(http.httpRequest(s2, null, flag, "GET"), this);
    }

    public List getListStatuses(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists/").append(s1).append("/statuses.xml").append("?source=").append(CONSUMER_KEY);
        String s2 = stringbuilder.toString();
        return Status.constructStatuses(http.httpRequest(s2, null, flag, "GET"), this);
    }

    public UserWapper getListSubscribers(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/subscribers.xml").append("?source=").append(CONSUMER_KEY);
        String s2 = stringbuilder.toString();
        return User.constructWapperUsers(http.httpRequest(s2, null, flag, "GET"), this);
    }

    public ListUserCount getListUserCount(String s, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(String.valueOf(s)).append("/lists").append("/counts.xml").append("?source=").append(CONSUMER_KEY);
        String s1 = stringbuilder.toString();
        return new ListUserCount(http.httpRequest(s1, null, flag, "GET"));
    }

    public List getMentions()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/mentions.json").toString(), null, true));
    }

    public List getMentions(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/mentions.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    /**
     * @deprecated Method getOAuthAccessToken is deprecated
     */

    public AccessToken getOAuthAccessToken(String s, String s1)
        throws WeiboException
    {
        this;
        JVM INSTR monitorenter ;
        AccessToken accesstoken;
        accesstoken = http.getOAuthAccessToken(s, s1);
        setUserId(accesstoken.getScreenName());
        this;
        JVM INSTR monitorexit ;
        return accesstoken;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getOAuthAccessToken is deprecated
     */

    public AccessToken getOAuthAccessToken(String s, String s1, String s2)
        throws WeiboException
    {
        this;
        JVM INSTR monitorenter ;
        AccessToken accesstoken = http.getOAuthAccessToken(s, s1, s2);
        this;
        JVM INSTR monitorexit ;
        return accesstoken;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getOAuthAccessToken is deprecated
     */

    public AccessToken getOAuthAccessToken(RequestToken requesttoken)
        throws WeiboException
    {
        this;
        JVM INSTR monitorenter ;
        AccessToken accesstoken = http.getOAuthAccessToken(requesttoken);
        this;
        JVM INSTR monitorexit ;
        return accesstoken;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getOAuthAccessToken is deprecated
     */

    public AccessToken getOAuthAccessToken(RequestToken requesttoken, String s)
        throws WeiboException
    {
        this;
        JVM INSTR monitorenter ;
        AccessToken accesstoken;
        accesstoken = http.getOAuthAccessToken(requesttoken, s);
        setUserId(accesstoken.getScreenName());
        this;
        JVM INSTR monitorexit ;
        return accesstoken;
        Exception exception;
        exception;
        throw exception;
    }

    public RequestToken getOAuthRequestToken()
        throws WeiboException
    {
        return http.getOAuthRequestToken();
    }

    public RequestToken getOAuthRequestToken(String s)
        throws WeiboException
    {
        return http.getOauthRequestToken(s);
    }

    public volatile String getPassword()
    {
        return super.getPassword();
    }

    public User getPrivacy()
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("account/get_privacy.json").toString(), true).asJSONObject());
    }

    public List getPublicTimeline()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/public_timeline.json").toString(), true));
    }

    public List getPublicTimeline(int i)
        throws WeiboException
    {
        return getPublicTimeline(i);
    }

    public List getPublicTimeline(long l)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/public_timeline.json").toString(), ((PostParameter []) (null)), new Paging(l), false));
    }

    public RateLimitStatus getRateLimitStatus()
        throws WeiboException
    {
        return new RateLimitStatus(get((new StringBuilder()).append(getBaseURL()).append("account/rate_limit_status.json").toString(), true), this);
    }

    public List getReplies()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/replies.xml").toString(), true), this);
    }

    public List getReplies(int i)
        throws WeiboException
    {
        if(i < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("page should be positive integer. passed:").append(i).toString());
        else
            return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/replies.xml").toString(), "page", String.valueOf(i), true), this);
    }

    public List getReplies(long l)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/replies.xml").toString(), "since_id", String.valueOf(l), true), this);
    }

    public List getReplies(long l, int i)
        throws WeiboException
    {
        if(i < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("page should be positive integer. passed:").append(i).toString());
        else
            return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/replies.xml").toString(), "since_id", String.valueOf(l), "page", String.valueOf(i), true), this);
    }

    public List getRepliesByPage(int i)
        throws WeiboException
    {
        if(i < 1)
            throw new IllegalArgumentException((new StringBuilder()).append("page should be positive integer. passed:").append(i).toString());
        else
            return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/replies.xml").toString(), "page", String.valueOf(i), true), this);
    }

    public List getRetweetedByMe()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweeted_by_me.json").toString(), null, true));
    }

    public List getRetweetedByMe(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweeted_by_me.json").toString(), null, true));
    }

    public List getRetweetedToMe()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweeted_to_me.json").toString(), null, true));
    }

    public List getRetweetedToMe(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweeted_to_me.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getRetweets(long l)
        throws WeiboException
    {
        return RetweetDetails.createRetweetDetails(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweets/").append(l).append(".json").toString(), true));
    }

    public List getRetweetsOfMe()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweets_of_me.json").toString(), null, true));
    }

    public List getRetweetsOfMe(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/retweets_of_me.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getSavedSearches()
        throws WeiboException
    {
        return SavedSearch.constructSavedSearches(get((new StringBuilder()).append(getBaseURL()).append("saved_searches.json").toString(), true));
    }

    public String getSearchBaseURL()
    {
        return searchBaseURL;
    }

    public List getSentDirectMessages()
        throws WeiboException
    {
        return DirectMessage.constructDirectMessages(get((new StringBuilder()).append(getBaseURL()).append("direct_messages/sent.json").toString(), new PostParameter[0], true));
    }

    public List getSentDirectMessages(int i)
        throws WeiboException
    {
        return getSentDirectMessages(new Paging(i));
    }

    public List getSentDirectMessages(int i, int j)
        throws WeiboException
    {
        return getSentDirectMessages(new Paging(i, j));
    }

    public List getSentDirectMessages(Date date)
        throws WeiboException
    {
        return DirectMessage.constructDirectMessages(get((new StringBuilder()).append(getBaseURL()).append("direct_messages/sent.xml").toString(), "since", format.format(date), true), this);
    }

    public List getSentDirectMessages(Paging paging)
        throws WeiboException
    {
        return DirectMessage.constructDirectMessages(get((new StringBuilder()).append(getBaseURL()).append("direct_messages/sent.json").toString(), new PostParameter[0], paging, true));
    }

    public volatile String getSource()
    {
        return super.getSource();
    }

    public List getSuggestions()
        throws WeiboException
    {
        return Tag.constructTags(get((new StringBuilder()).append(getBaseURL()).append("tags/suggestions.json").toString(), true));
    }

    public Trends getTrends()
        throws WeiboException
    {
        return Trends.constructTrends(get((new StringBuilder()).append(searchBaseURL).append("trends.json").toString(), false));
    }

    public Count getUnread()
        throws WeiboException, JSONException
    {
        return new Count(get((new StringBuilder()).append(getBaseURL()).append("statuses/unread.json").toString(), true).asJSONObject());
    }

    public volatile String getUserAgent()
    {
        return super.getUserAgent();
    }

    public User getUserDetail(String s)
        throws WeiboException
    {
        return showUser(s);
    }

    public volatile String getUserId()
    {
        return super.getUserId();
    }

    public ListObjectWapper getUserListedLists(String s, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists/memberships.xml").append("?source=").append(CONSUMER_KEY);
        String s1 = stringbuilder.toString();
        return ListObject.constructListObjects(http.httpRequest(s1, null, flag, "GET"), this);
    }

    public ListObjectWapper getUserLists(String s, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists.xml").append("?source=").append(CONSUMER_KEY);
        String s1 = stringbuilder.toString();
        return ListObject.constructListObjects(http.httpRequest(s1, null, flag, "GET"), this);
    }

    public ListObjectWapper getUserSubscriberLists(String s, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists/subscriptions.xml").append("?source=").append(CONSUMER_KEY);
        String s1 = stringbuilder.toString();
        return ListObject.constructListObjects(http.httpRequest(s1, null, flag, "GET"), this);
    }

    public List getUserTimeline()
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline.json").toString(), true));
    }

    public List getUserTimeline(int i, long l)
        throws WeiboException
    {
        return getUserTimeline((new Paging(l)).count(i));
    }

    public List getUserTimeline(int i, Date date)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline.xml").toString(), "since", format.format(date), "count", String.valueOf(i), true), this);
    }

    public List getUserTimeline(long l)
        throws WeiboException
    {
        return getUserTimeline(new Paging(l));
    }

    public List getUserTimeline(String s)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline/").append(s).append(".json").toString(), http.isAuthenticationEnabled()));
    }

    public List getUserTimeline(String s, int i)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline/").append(s).append(".xml").toString(), "count", String.valueOf(i), http.isAuthenticationEnabled()), this);
    }

    public List getUserTimeline(String s, int i, long l)
        throws WeiboException
    {
        return getUserTimeline(s, (new Paging(l)).count(i));
    }

    public List getUserTimeline(String s, int i, Date date)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline/").append(s).append(".xml").toString(), "since", format.format(date), "count", String.valueOf(i), http.isAuthenticationEnabled()), this);
    }

    public List getUserTimeline(String s, long l)
        throws WeiboException
    {
        return getUserTimeline(s, new Paging(l));
    }

    public List getUserTimeline(String s, Date date)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline/").append(s).append(".xml").toString(), "since", format.format(date), http.isAuthenticationEnabled()), this);
    }

    public List getUserTimeline(String s, Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline/").append(s).append(".xml").toString(), ((PostParameter []) (null)), paging, http.isAuthenticationEnabled()), this);
    }

    public List getUserTimeline(Paging paging)
        throws WeiboException
    {
        return Status.constructStatuses(get((new StringBuilder()).append(getBaseURL()).append("statuses/user_timeline.json").toString(), ((PostParameter []) (null)), paging, true));
    }

    public List getWeeklyTrends()
        throws WeiboException
    {
        return Trends.constructTrendsList(get((new StringBuilder()).append(searchBaseURL).append("trends/weekly.json").toString(), false));
    }

    public List getWeeklyTrends(Date date, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(searchBaseURL).append("trends/weekly.json?date=").append(toDateStr(date));
        String s;
        if(flag)
            s = "&exclude=hashtags";
        else
            s = "";
        return Trends.constructTrendsList(get(stringbuilder.append(s).toString(), false));
    }

    /**
     * @deprecated Method getXAuthAccessToken is deprecated
     */

    public AccessToken getXAuthAccessToken(String s, String s1, String s2)
        throws WeiboException
    {
        this;
        JVM INSTR monitorenter ;
        AccessToken accesstoken = http.getXAuthAccessToken(s, s1, s2);
        this;
        JVM INSTR monitorexit ;
        return accesstoken;
        Exception exception;
        exception;
        throw exception;
    }

    public List getrepostbyme(String s)
        throws WeiboException
    {
        return Status.constructStatuses(http.get((new StringBuilder()).append(getBaseURL()).append("statuses/repost_by_me.json").append("?source=").append(CONSUMER_KEY).append("&id=").append(s).toString(), true));
    }

    public List getreposttimeline(String s)
        throws WeiboException
    {
        return Status.constructStatuses(http.get((new StringBuilder()).append(getBaseURL()).append("statuses/repost_timeline.json").append("?source=").append(CONSUMER_KEY).append("&id=").append(s).toString(), true));
    }

    public List gettags(String s)
        throws WeiboException
    {
        return Tag.constructTags(http.get((new StringBuilder()).append(getBaseURL()).append("tags.json?").append("user_id=").append(s).toString(), true));
    }

    public int hashCode()
    {
        return 31 * (31 * (31 * (31 * http.hashCode() + baseURL.hashCode()) + searchBaseURL.hashCode()) + source.hashCode()) + format.hashCode();
    }

    public ListObject insertList(String s, String s1, boolean flag, String s2, boolean flag1)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists.xml");
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(new PostParameter("name", s1));
        linkedlist.add(new PostParameter("description", s2));
        String s3;
        PostParameter apostparameter[];
        int i;
        if(flag)
            s3 = "public";
        else
            s3 = "private";
        linkedlist.add(new PostParameter("mode", s3));
        linkedlist.add(new PostParameter("source", CONSUMER_KEY));
        apostparameter = new PostParameter[linkedlist.size()];
        i = 0;
        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext();)
        {
            PostParameter postparameter = (PostParameter)iterator.next();
            int j = i + 1;
            apostparameter[i] = postparameter;
            i = j;
        }

        String s4 = stringbuilder.toString();
        return new ListObject(http.httpRequest(s4, apostparameter, flag1, "POST"), this);
    }

    public boolean isListMember(String s, String s1, String s2, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/members/").append(s2).append(".xml").append("?source=").append(CONSUMER_KEY);
        String s3 = stringbuilder.toString();
        return "true".equals(http.httpRequest(s3, null, flag, "GET").asDocument().getDocumentElement().getNodeValue());
    }

    public boolean isListSubscriber(String s, String s1, String s2, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/subscribers/").append(s2).append(".xml").append("?source=").append(CONSUMER_KEY);
        String s3 = stringbuilder.toString();
        return "true".equals(http.httpRequest(s3, null, flag, "GET").asDocument().getDocumentElement().getNodeValue());
    }

    public volatile boolean isUsePostForced()
    {
        return super.isUsePostForced();
    }

    public User leave(String s)
        throws WeiboException
    {
        return disableNotification(s);
    }

    public RateLimitStatus rateLimitStatus()
        throws WeiboException
    {
        return new RateLimitStatus(http.get((new StringBuilder()).append(getBaseURL()).append("account/rate_limit_status.json?").append("source=").append(CONSUMER_KEY).toString(), true), this);
    }

    public transient JSONObject register(String s, String as[])
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("account/register.json").toString();
        PostParameter apostparameter[] = new PostParameter[5];
        apostparameter[0] = new PostParameter("nick", as[2]);
        apostparameter[1] = new PostParameter("gender", as[3]);
        apostparameter[2] = new PostParameter("password", as[4]);
        apostparameter[3] = new PostParameter("email", as[5]);
        apostparameter[4] = new PostParameter("ip", s);
        return httpclient.post(s1, apostparameter, true).asJSONObject();
    }

    public ListObject removeList(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists/").append(s1).append(".xml").append("?source=").append(CONSUMER_KEY);
        String s2 = stringbuilder.toString();
        return new ListObject(http.httpRequest(s2, null, flag, "DELETE"), this);
    }

    public ListObject removeListMember(String s, String s1, String s2, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/members.xml").append("?source=").append(CONSUMER_KEY).append("&id=").append(String.valueOf(s2));
        String s3 = stringbuilder.toString();
        return new ListObject(http.httpRequest(s3, null, flag, "DELETE"), this);
    }

    public ListObject removeListSubscriber(String s, String s1, boolean flag)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/").append(s1).append("/subscribers.xml").append("?source=").append(CONSUMER_KEY);
        String s2 = stringbuilder.toString();
        return new ListObject(http.httpRequest(s2, null, flag, "DELETE"), this);
    }

    public Status reply(String s, String s1, String s2)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s3 = (new StringBuilder()).append(getBaseURL()).append("statuses/reply.json").toString();
        PostParameter apostparameter[] = new PostParameter[3];
        apostparameter[0] = new PostParameter("id", s);
        apostparameter[1] = new PostParameter("cid", s1);
        apostparameter[2] = new PostParameter("comment", s2);
        return new Status(httpclient.post(s3, apostparameter, true));
    }

    public Status repost(String s, String s1)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s2 = (new StringBuilder()).append(getBaseURL()).append("statuses/repost.json").toString();
        PostParameter apostparameter[] = new PostParameter[2];
        apostparameter[0] = new PostParameter("id", s);
        apostparameter[1] = new PostParameter("status", s1);
        return new Status(httpclient.post(s2, apostparameter, true));
    }

    public Status resetCount(int i)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(getBaseURL()).append("statuses/reset_count.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("type", i);
        return new Status(httpclient.post(s, apostparameter, true));
    }

    public Status retweetStatus(long l)
        throws WeiboException
    {
        return new Status(http.post((new StringBuilder()).append(getBaseURL()).append("statuses/retweet/").append(l).append(".json").toString(), new PostParameter[0], true));
    }

    public List search(String s)
        throws WeiboException
    {
        return Status.constructStatuses(http.get((new StringBuilder()).append(getBaseURL()).append("statuses/search.json?source=").append(CONSUMER_KEY).append("&q=").append(s).toString(), true));
    }

    public QueryResult search(Query query)
        throws WeiboException
    {
        QueryResult queryresult;
        try
        {
            queryresult = new QueryResult(get((new StringBuilder()).append(searchBaseURL).append("search.json").toString(), query.asPostParameters(), false), this);
        }
        catch(WeiboException weiboexception)
        {
            if(404 == weiboexception.getStatusCode())
                queryresult = new QueryResult(query);
            else
                throw weiboexception;
        }
        return queryresult;
    }

    public DirectMessage sendDirectMessage(String s, String s1)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s2 = (new StringBuilder()).append(getBaseURL()).append("direct_messages/new.json").toString();
        PostParameter apostparameter[] = new PostParameter[3];
        apostparameter[0] = new PostParameter("user_id", s);
        apostparameter[1] = new PostParameter("text", s1);
        apostparameter[2] = new PostParameter("source", source);
        return new DirectMessage(httpclient.post(s2, apostparameter, true).asJSONObject());
    }

    public void setBaseURL(String s)
    {
        baseURL = s;
    }

    public volatile void setClientURL(String s)
    {
        super.setClientURL(s);
    }

    public volatile void setClientVersion(String s)
    {
        super.setClientVersion(s);
    }

    public volatile void setHttpConnectionTimeout(int i)
    {
        super.setHttpConnectionTimeout(i);
    }

    public volatile void setHttpProxy(String s, int i)
    {
        super.setHttpProxy(s, i);
    }

    public volatile void setHttpProxyAuth(String s, String s1)
    {
        super.setHttpProxyAuth(s, s1);
    }

    public volatile void setHttpReadTimeout(int i)
    {
        super.setHttpReadTimeout(i);
    }

    public void setOAuthAccessToken(String s, String s1)
    {
        setOAuthAccessToken(new AccessToken(s, s1));
    }

    public void setOAuthAccessToken(AccessToken accesstoken)
    {
        http.setOAuthAccessToken(accesstoken);
    }

    /**
     * @deprecated Method setOAuthConsumer is deprecated
     */

    public void setOAuthConsumer(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        http.setOAuthConsumer(s, s1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public volatile void setPassword(String s)
    {
        super.setPassword(s);
    }

    public volatile void setRequestHeader(String s, String s1)
    {
        super.setRequestHeader(s, s1);
    }

    public volatile void setRetryCount(int i)
    {
        super.setRetryCount(i);
    }

    public volatile void setRetryIntervalSecs(int i)
    {
        super.setRetryIntervalSecs(i);
    }

    public void setSearchBaseURL(String s)
    {
        searchBaseURL = s;
    }

    public volatile void setSource(String s)
    {
        super.setSource(s);
    }

    public void setToken(String s, String s1)
    {
        http.setToken(s, s1);
    }

    public volatile void setUserAgent(String s)
    {
        super.setUserAgent(s);
    }

    public volatile void setUserId(String s)
    {
        super.setUserId(s);
    }

    public Status show(int i)
        throws WeiboException
    {
        return showStatus(i);
    }

    public Status show(long l)
        throws WeiboException
    {
        return new Status(get((new StringBuilder()).append(getBaseURL()).append("statuses/show/").append(l).append(".xml").toString(), false), this);
    }

    public JSONObject showFriendships(String s)
        throws WeiboException
    {
        return get((new StringBuilder()).append(getBaseURL()).append("friendships/show.json?target_id=").append(s).toString(), true).asJSONObject();
    }

    public JSONObject showFriendships(String s, String s1)
        throws WeiboException
    {
        return get((new StringBuilder()).append(getBaseURL()).append("friendships/show.json?target_id=").append(s1).append("&source_id=").append(s).append("&source=").append(CONSUMER_KEY).toString(), true).asJSONObject();
    }

    public SavedSearch showSavedSearch(int i)
        throws WeiboException
    {
        return new SavedSearch(get((new StringBuilder()).append(getBaseURL()).append("saved_searches/show/").append(i).append(".json").toString(), true));
    }

    public Status showStatus(long l)
        throws WeiboException
    {
        return new Status(get((new StringBuilder()).append(getBaseURL()).append("statuses/show/").append(l).append(".json").toString(), true));
    }

    public User showUser(String s)
        throws WeiboException
    {
        return new User(get((new StringBuilder()).append(getBaseURL()).append("users/show/").append(s).append(".json").toString(), http.isAuthenticationEnabled()).asJSONObject());
    }

    public boolean test()
        throws WeiboException
    {
        boolean flag;
        if(-1 != get((new StringBuilder()).append(getBaseURL()).append("help/test.json").toString(), false).asString().indexOf("ok"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Weibo{http=").append(http).append(", baseURL='").append(baseURL).append('\'').append(", searchBaseURL='").append(searchBaseURL).append('\'').append(", source='").append(source).append('\'').append(", format=").append(format).append('}').toString();
    }

    public User unblock(String s)
        throws WeiboException
    {
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("blocks/destroy/").append(s).append(".xml").toString(), true), this);
    }

    public Status update(String s)
        throws WeiboException
    {
        return updateStatus(s);
    }

    public Status update(String s, long l)
        throws WeiboException
    {
        return updateStatus(s, l);
    }

    public Comment updateComment(String s, String s1, String s2)
        throws WeiboException
    {
        PostParameter apostparameter[];
        if(s2 == null)
        {
            apostparameter = new PostParameter[2];
            apostparameter[0] = new PostParameter("comment", s);
            apostparameter[1] = new PostParameter("id", s1);
        } else
        {
            apostparameter = new PostParameter[3];
            apostparameter[0] = new PostParameter("comment", s);
            apostparameter[1] = new PostParameter("cid", s2);
            apostparameter[2] = new PostParameter("id", s1);
        }
        return new Comment(http.post((new StringBuilder()).append(getBaseURL()).append("statuses/comment.json").toString(), apostparameter, true));
    }

    public User updateDeliverlyDevice(Device device)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s = (new StringBuilder()).append(getBaseURL()).append("account/update_delivery_device.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("device", device.DEVICE);
        return new User(httpclient.post(s, apostparameter, true).asJSONObject());
    }

    public ListObject updateList(String s, String s1, String s2, boolean flag, String s3, boolean flag1)
        throws WeiboException
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getBaseURL()).append(s).append("/lists/").append(s1).append(".xml");
        LinkedList linkedlist = new LinkedList();
        linkedlist.add(new PostParameter("name", s2));
        String s4;
        PostParameter apostparameter[];
        int i;
        if(flag)
            s4 = "public";
        else
            s4 = "private";
        linkedlist.add(new PostParameter("mode", s4));
        linkedlist.add(new PostParameter("description", s3));
        linkedlist.add(new PostParameter("source", CONSUMER_KEY));
        apostparameter = new PostParameter[linkedlist.size()];
        i = 0;
        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext();)
        {
            PostParameter postparameter = (PostParameter)iterator.next();
            int j = i + 1;
            apostparameter[i] = postparameter;
            i = j;
        }

        String s5 = stringbuilder.toString();
        return new ListObject(http.httpRequest(s5, apostparameter, flag1, "POST"), this);
    }

    public User updateLocation(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("account/update_location.xml").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("location", s);
        return new User(httpclient.post(s1, apostparameter, true), this);
    }

    public User updatePrivacy(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("account/update_privacy.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("comment", s);
        return new User(httpclient.post(s1, apostparameter, true).asJSONObject());
    }

    public User updateProfile(String s, String s1, String s2, String s3, String s4)
        throws WeiboException
    {
        ArrayList arraylist = new ArrayList(5);
        addParameterToList(arraylist, "name", s);
        addParameterToList(arraylist, "email", s1);
        addParameterToList(arraylist, "url", s2);
        addParameterToList(arraylist, "location", s3);
        addParameterToList(arraylist, "description", s4);
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("account/update_profile.json").toString(), (PostParameter[])arraylist.toArray(new PostParameter[arraylist.size()]), true).asJSONObject());
    }

    public User updateProfileColors(String s, String s1, String s2, String s3, String s4)
        throws WeiboException
    {
        ArrayList arraylist = new ArrayList(5);
        addParameterToList(arraylist, "profile_background_color", s);
        addParameterToList(arraylist, "profile_text_color", s1);
        addParameterToList(arraylist, "profile_link_color", s2);
        addParameterToList(arraylist, "profile_sidebar_fill_color", s3);
        addParameterToList(arraylist, "profile_sidebar_border_color", s4);
        return new User(http.post((new StringBuilder()).append(getBaseURL()).append("account/update_profile_colors.json").toString(), (PostParameter[])arraylist.toArray(new PostParameter[arraylist.size()]), true).asJSONObject());
    }

    public User updateProfileImage(File file)
        throws WeiboException
    {
        return new User(http.multPartURL("image", (new StringBuilder()).append(getBaseURL()).append("account/update_profile_image.json").toString(), null, file, true).asJSONObject());
    }

    public Status updateStatus(String s)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/update.json").toString();
        PostParameter apostparameter[] = new PostParameter[1];
        apostparameter[0] = new PostParameter("status", s);
        return new Status(httpclient.post(s1, apostparameter, true));
    }

    public Status updateStatus(String s, double d, double d1)
        throws WeiboException, JSONException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/update.json").toString();
        PostParameter apostparameter[] = new PostParameter[3];
        apostparameter[0] = new PostParameter("status", s);
        apostparameter[1] = new PostParameter("lat", d);
        apostparameter[2] = new PostParameter("long", d1);
        return new Status(httpclient.post(s1, apostparameter, true));
    }

    public Status updateStatus(String s, long l)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/update.json").toString();
        PostParameter apostparameter[] = new PostParameter[3];
        apostparameter[0] = new PostParameter("status", s);
        apostparameter[1] = new PostParameter("in_reply_to_status_id", String.valueOf(l));
        apostparameter[2] = new PostParameter("source", source);
        return new Status(httpclient.post(s1, apostparameter, true));
    }

    public Status updateStatus(String s, long l, double d, double d1)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/update.json").toString();
        PostParameter apostparameter[] = new PostParameter[5];
        apostparameter[0] = new PostParameter("status", s);
        apostparameter[1] = new PostParameter("lat", d);
        apostparameter[2] = new PostParameter("long", d1);
        apostparameter[3] = new PostParameter("in_reply_to_status_id", String.valueOf(l));
        apostparameter[4] = new PostParameter("source", source);
        return new Status(httpclient.post(s1, apostparameter, true));
    }

    public Status uploadStatus(String s, File file)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/upload.json").toString();
        PostParameter apostparameter[] = new PostParameter[2];
        apostparameter[0] = new PostParameter("status", s);
        apostparameter[1] = new PostParameter("source", source);
        return new Status(httpclient.multPartURL("pic", s1, apostparameter, file, true));
    }

    public Status uploadStatus(String s, ImageItem imageitem)
        throws WeiboException
    {
        HttpClient httpclient = http;
        String s1 = (new StringBuilder()).append(getBaseURL()).append("statuses/upload.json").toString();
        PostParameter apostparameter[] = new PostParameter[2];
        apostparameter[0] = new PostParameter("status", s);
        apostparameter[1] = new PostParameter("source", source);
        return new Status(httpclient.multPartURL(s1, apostparameter, imageitem, true));
    }

    public User verifyCredentials()
        throws WeiboException
    {
        return new User(get((new StringBuilder()).append(getBaseURL()).append("account/verify_credentials.json").toString(), true).asJSONObject());
    }

    public static String CONSUMER_KEY = "1552112022";
    public static String CONSUMER_SECRET = "bd9e3b147ece106f9cee159758a28ec1";
    public static final Device IM = new Device("im");
    public static final Device NONE = new Device("none");
    public static final Device SMS = new Device("sms");
    private static final long serialVersionUID = 0xeb5f6361d798c8fcL;
    private String baseURL;
    private SimpleDateFormat format;
    private String searchBaseURL;

}
