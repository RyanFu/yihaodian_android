// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, User, ListObjectWapper, 
//            Weibo

public class ListObject extends WeiboResponse
    implements Serializable
{

    ListObject(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        super(response);
        init(response, element, weibo);
    }

    ListObject(Response response, Weibo weibo)
        throws WeiboException
    {
        super(response);
        init(response, response.asDocument().getDocumentElement(), weibo);
    }

    ListObject(JSONObject jsonobject)
        throws WeiboException
    {
        try
        {
            id = jsonobject.getLong("id");
            name = jsonobject.getString("name");
            fullName = jsonobject.getString("full_name");
            slug = jsonobject.getString("slug");
            description = jsonobject.getString("description");
            subscriberCount = jsonobject.getInt("subscriber_count");
            memberCount = jsonobject.getInt("member_count");
            uri = jsonobject.getString("uri");
            mode = jsonobject.getString("mode");
            if(!jsonobject.isNull("user"))
                user = new User(jsonobject.getJSONObject("user"));
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    static ListObjectWapper constructListObjects(Response response)
        throws WeiboException
    {
        JSONObject jsonobject = response.asJSONObject();
        ListObjectWapper listobjectwapper;
        try
        {
            JSONArray jsonarray = jsonobject.getJSONArray("lists");
            int i = jsonarray.length();
            ArrayList arraylist = new ArrayList(i);
            for(int j = 0; j < i; j++)
                arraylist.add(new ListObject(jsonarray.getJSONObject(j)));

            long l = jsonobject.getLong("previous_curosr");
            long l1 = jsonobject.getLong("next_cursor");
            if(l1 == -1L)
                l1 = jsonobject.getLong("nextCursor");
            listobjectwapper = new ListObjectWapper(arraylist, l, l1);
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException(jsonexception);
        }
        return listobjectwapper;
    }

    static ListObjectWapper constructListObjects(Response response, Weibo weibo)
        throws WeiboException
    {
        Document document = response.asDocument();
        if(!isRootNodeNilClasses(document)) goto _L2; else goto _L1
_L1:
        ListObjectWapper listobjectwapper = new ListObjectWapper(new ArrayList(0), 0L, 0L);
_L4:
        return listobjectwapper;
_L2:
        ListObjectWapper listobjectwapper1;
        ensureRootNodeNameIs("lists_list", document);
        Element element = document.getDocumentElement();
        NodeList nodelist = element.getElementsByTagName("lists");
        if(nodelist.getLength() == 0)
        {
            listobjectwapper = new ListObjectWapper(new ArrayList(0), 0L, 0L);
            continue; /* Loop/switch isn't completed */
        }
        NodeList nodelist1 = ((Element)nodelist.item(0)).getElementsByTagName("list");
        int i = nodelist1.getLength();
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add(new ListObject(response, (Element)nodelist1.item(j), weibo));

        long l = getChildLong("previous_curosr", element);
        long l1 = getChildLong("next_curosr", element);
        if(l1 == -1L)
            l1 = getChildLong("nextCurosr", element);
        listobjectwapper1 = new ListObjectWapper(arraylist, l, l1);
        listobjectwapper = listobjectwapper1;
        continue; /* Loop/switch isn't completed */
        WeiboException weiboexception;
        weiboexception;
        if(isRootNodeNilClasses(document))
            listobjectwapper = new ListObjectWapper(new ArrayList(0), 0L, 0L);
        else
            throw weiboexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void init(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        ensureRootNodeNameIs("list", element);
        id = getChildLong("id", element);
        name = getChildText("name", element);
        fullName = getChildText("full_name", element);
        slug = getChildText("slug", element);
        description = getChildText("description", element);
        subscriberCount = getChildInt("subscriber_count", element);
        memberCount = getChildInt("member_count", element);
        uri = getChildText("uri", element);
        mode = getChildText("mode", element);
        NodeList nodelist = element.getElementsByTagName("user");
        if(nodelist.getLength() != 0)
            user = new User(response, (Element)nodelist.item(0), weibo);
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
        if((obj instanceof ListObject) && ((ListObject)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String getDescription()
    {
        return description;
    }

    public String getFullName()
    {
        return fullName;
    }

    public long getId()
    {
        return id;
    }

    public int getMemberCount()
    {
        return memberCount;
    }

    public String getMode()
    {
        return mode;
    }

    public String getName()
    {
        return name;
    }

    public String getSlug()
    {
        return slug;
    }

    public int getSubscriberCount()
    {
        return subscriberCount;
    }

    public String getUri()
    {
        return uri;
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

    public void setDescription(String s)
    {
        description = s;
    }

    public void setFullName(String s)
    {
        fullName = s;
    }

    public void setId(long l)
    {
        id = l;
    }

    public void setMemberCount(int i)
    {
        memberCount = i;
    }

    public void setMode(String s)
    {
        mode = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setSlug(String s)
    {
        slug = s;
    }

    public void setSubscriberCount(int i)
    {
        subscriberCount = i;
    }

    public void setUri(String s)
    {
        uri = s;
    }

    public void setUser(User user1)
    {
        user = user1;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("ListObject{id=").append(id).append(", name='").append(name).append('\'').append(", fullName='").append(fullName).append('\'').append(", slug='").append(slug).append('\'').append(", description='").append(description).append('\'').append(", subscriberCount=").append(subscriberCount).append(", memberCount=").append(memberCount).append(", mode='").append(mode).append("', uri='").append(uri).append('\'').append(", user='").append(user.toString()).append("'}").toString();
    }

    private static final long serialVersionUID = 0x3a66a4e50e177790L;
    private String description;
    private String fullName;
    private long id;
    private int memberCount;
    private String mode;
    private String name;
    private String slug;
    private int subscriberCount;
    private String uri;
    private User user;
}
