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
//            WeiboResponse, WeiboException, Weibo

public class Tag extends WeiboResponse
    implements Serializable
{

    public Tag(Response response, Element element)
        throws WeiboException
    {
        ensureRootNodeNameIs("tag", element);
        id = getChildText("id", element);
        value = getChildText("value", element);
    }

    public Tag(Response response, Element element, Weibo weibo)
        throws WeiboException
    {
        ensureRootNodeNameIs("tagid", element);
        id = element.getNodeName();
        value = element.getNodeValue();
    }

    public Tag(Response response, Element element, Weibo weibo, String s)
        throws WeiboException
    {
        ensureRootNodeNameIs("tagid", element);
        id = element.getNodeName();
        value = element.getNodeValue();
    }

    public Tag(JSONObject jsonobject)
        throws WeiboException, JSONException
    {
        if(jsonobject.getString("id").length() == 0)
        {
            for(Iterator iterator = jsonobject.keys(); iterator.hasNext();)
            {
                id = (String)iterator.next();
                value = jsonobject.getString(id);
            }

        } else
        {
            id = jsonobject.getString("id");
            value = jsonobject.getString("value");
        }
    }

    static List constructTags(Response response)
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
        arraylist.add(new Tag(jsonarray.getJSONObject(j)));
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

    public static List constructTags(Response response, Weibo weibo)
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
        ensureRootNodeNameIs("tags", document);
        nodelist = document.getDocumentElement().getElementsByTagName("tag");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_108;
        arraylist1.add(new Tag(response, (Element)nodelist.item(j)));
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

    public static List createTags(Response response, Weibo weibo)
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
        ensureRootNodeNameIs("tagids", document);
        nodelist = document.getDocumentElement().getElementsByTagName("tagid");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_109;
        arraylist1.add(new Tag(response, (Element)nodelist.item(j), null));
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

    public static List destroyTags(Response response, Weibo weibo)
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
        ensureRootNodeNameIs("tags", document);
        nodelist = document.getDocumentElement().getElementsByTagName("tagid");
        i = nodelist.getLength();
        arraylist1 = new ArrayList(i);
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_110;
        arraylist1.add(new Tag(response, (Element)nodelist.item(j), null, null));
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
        if((obj instanceof Tag) && ((Tag)obj).id == id)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String getId()
    {
        return id;
    }

    public String getValue()
    {
        return value;
    }

    @Override
	public int hashCode()
    {
        return Integer.parseInt(id);
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("tags{ ").append(id).append(",").append(value).append('}').toString();
    }

    private static final long serialVersionUID = 1L;
    private String id;
    private String value;
}
