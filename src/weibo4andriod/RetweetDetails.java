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
//            WeiboResponse, WeiboException, User, Weibo

public class RetweetDetails extends WeiboResponse
    implements Serializable
{

    RetweetDetails(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        super(response);
        init(response, element, weibo);
    }

    RetweetDetails(Response response, Weibo weibo)
        throws WeiboException
    {
        super(response);
        init(response, response.asDocument().getDocumentElement(), weibo);
    }

    RetweetDetails(JSONObject jsonobject)
        throws WeiboException
    {
        init(jsonobject);
    }

    static List createRetweetDetails(Response response)
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
        arraylist.add(new RetweetDetails(jsonarray.getJSONObject(j)));
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

    static List createRetweetDetails(Response response, Weibo weibo)
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
        ensureRootNodeNameIs("retweets", document);
        nodelist = document.getDocumentElement().getElementsByTagName("retweet_details");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_109;
        arraylist1.add(new RetweetDetails(response, (Element)nodelist.item(j), weibo));
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
        ensureRootNodeNameIs("retweet_details", element);
        retweetId = getChildLong("retweet_id", element);
        retweetedAt = getChildDate("retweeted_at", element);
        retweetingUser = new User(response, (Element)element.getElementsByTagName("retweeting_user").item(0), weibo);
    }

    private void init(JSONObject jsonobject)
        throws WeiboException
    {
        try
        {
            retweetId = jsonobject.getInt("retweetId");
            retweetedAt = parseDate(jsonobject.getString("retweetedAt"), "EEE MMM dd HH:mm:ss z yyyy");
            retweetingUser = new User(jsonobject.getJSONObject("retweetingUser"));
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof RetweetDetails))
        {
            flag = false;
        } else
        {
            RetweetDetails retweetdetails = (RetweetDetails)obj;
            if(retweetId == retweetdetails.retweetId)
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public long getRetweetId()
    {
        return retweetId;
    }

    public Date getRetweetedAt()
    {
        return retweetedAt;
    }

    public User getRetweetingUser()
    {
        return retweetingUser;
    }

    @Override
	public int hashCode()
    {
        return 31 * (31 * (int)(retweetId ^ retweetId >>> 32) + retweetedAt.hashCode()) + retweetingUser.hashCode();
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("RetweetDetails{retweetId=").append(retweetId).append(", retweetedAt=").append(retweetedAt).append(", retweetingUser=").append(retweetingUser).append('}').toString();
    }

    static final long serialVersionUID = 0x1b2c267fe22c03d6L;
    private long retweetId;
    private Date retweetedAt;
    private User retweetingUser;
}
