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

public class DirectMessage extends WeiboResponse
    implements Serializable
{

    DirectMessage(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        super(response);
        init(response, element, weibo);
    }

    DirectMessage(Response response, Weibo weibo)
        throws WeiboException
    {
        super(response);
        init(response, response.asDocument().getDocumentElement(), weibo);
    }

    DirectMessage(JSONObject jsonobject)
        throws WeiboException
    {
        try
        {
            id = jsonobject.getInt("id");
            text = jsonobject.getString("text");
            sender_id = jsonobject.getInt("sender_id");
            recipient_id = jsonobject.getInt("recipient_id");
            created_at = parseDate(jsonobject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
            sender_screen_name = jsonobject.getString("sender_screen_name");
            recipient_screen_name = jsonobject.getString("recipient_screen_name");
            if(!jsonobject.isNull("sender"))
                sender = new User(jsonobject.getJSONObject("sender"));
            return;
        }
        catch(JSONException jsonexception)
        {
            throw new WeiboException((new StringBuilder()).append(jsonexception.getMessage()).append(":").append(jsonobject.toString()).toString(), jsonexception);
        }
    }

    static List constructDirectMessages(Response response)
        throws WeiboException
    {
        JSONArray jsonarray = response.asJSONArray();
        int i;
        ArrayList arraylist;
        int j;
        i = jsonarray.length();
        arraylist = new ArrayList(i);
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_66;
        arraylist.add(new DirectMessage(jsonarray.getJSONObject(j)));
        j++;
          goto _L1
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException(jsonexception);
        return arraylist;
    }

    static List constructDirectMessages(Response response, Weibo weibo)
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
        ensureRootNodeNameIs("direct-messages", document);
        nodelist = document.getDocumentElement().getElementsByTagName("direct_message");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_109;
        arraylist1.add(new DirectMessage(response, (Element)nodelist.item(j), weibo));
        j++;
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

    private void init(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        ensureRootNodeNameIs("direct_message", element);
        sender = new User(response, (Element)element.getElementsByTagName("sender").item(0), weibo);
        recipient = new User(response, (Element)element.getElementsByTagName("recipient").item(0), weibo);
        id = getChildInt("id", element);
        text = getChildText("text", element);
        sender_id = getChildInt("sender_id", element);
        recipient_id = getChildInt("recipient_id", element);
        created_at = getChildDate("created_at", element);
        sender_screen_name = getChildText("sender_screen_name", element);
        recipient_screen_name = getChildText("recipient_screen_name", element);
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
        if((obj instanceof DirectMessage) && ((DirectMessage)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Date getCreatedAt()
    {
        return created_at;
    }

    public int getId()
    {
        return id;
    }

    public User getRecipient()
    {
        return recipient;
    }

    public int getRecipientId()
    {
        return recipient_id;
    }

    public String getRecipientScreenName()
    {
        return recipient_screen_name;
    }

    public User getSender()
    {
        return sender;
    }

    public int getSenderId()
    {
        return sender_id;
    }

    public String getSenderScreenName()
    {
        return sender_screen_name;
    }

    public String getText()
    {
        return text;
    }

    @Override
	public int hashCode()
    {
        return id;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("DirectMessage{id=").append(id).append(", text='").append(text).append('\'').append(", sender_id=").append(sender_id).append(", recipient_id=").append(recipient_id).append(", created_at=").append(created_at).append(", sender_screen_name='").append(sender_screen_name).append('\'').append(", recipient_screen_name='").append(recipient_screen_name).append('\'').append(", sender=").append(sender).append(", recipient=").append(recipient).append('}').toString();
    }

    private static final long serialVersionUID = 0xd2daf1e275209457L;
    private Date created_at;
    private int id;
    private User recipient;
    private int recipient_id;
    private String recipient_screen_name;
    private User sender;
    private int sender_id;
    private String sender_screen_name;
    private String text;
}
