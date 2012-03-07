// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.util.Arrays;
import org.w3c.dom.*;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONArray;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            WeiboResponse, WeiboException, Weibo

public class IDs extends WeiboResponse
{

    IDs(Response response)
        throws WeiboException
    {
        super(response);
        Element element = response.asDocument().getDocumentElement();
        ensureRootNodeNameIs(ROOT_NODE_NAMES, element);
        NodeList nodelist = element.getElementsByTagName("id");
        ids = new int[nodelist.getLength()];
        int i = 0;
        while(i < nodelist.getLength()) 
        {
            try
            {
                ids[i] = Integer.parseInt(nodelist.item(i).getFirstChild().getNodeValue());
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new WeiboException((new StringBuilder()).append("Weibo API returned malformed response: ").append(element).toString(), numberformatexception);
            }
            i++;
        }
        previousCursor = getChildLong("previous_cursor", element);
        nextCursor = getChildLong("next_cursor", element);
    }

    IDs(Response response, Weibo weibo)
        throws WeiboException
    {
        JSONObject jsonobject;
        super(response);
        jsonobject = response.asJSONObject();
        JSONArray jsonarray;
        int i;
        int j;
        previousCursor = jsonobject.getLong("previous_cursor");
        nextCursor = jsonobject.getLong("next_cursor");
        if(jsonobject.isNull("ids"))
            break MISSING_BLOCK_LABEL_104;
        jsonarray = jsonobject.getJSONArray("ids");
        i = jsonarray.length();
        ids = new int[i];
        j = 0;
_L1:
        if(j >= i)
            break MISSING_BLOCK_LABEL_104;
        ids[j] = jsonarray.getInt(j);
        j++;
          goto _L1
        JSONException jsonexception;
        jsonexception;
        throw new WeiboException(jsonexception);
    }

    @Override
	public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(!(obj instanceof IDs))
        {
            flag = false;
        } else
        {
            IDs ids1 = (IDs)obj;
            if(!Arrays.equals(ids, ids1.ids))
                flag = false;
            else
                flag = true;
        }
        return flag;
    }

    public int[] getIDs()
    {
        return ids;
    }

    public long getNextCursor()
    {
        return nextCursor;
    }

    public long getPreviousCursor()
    {
        return previousCursor;
    }

    public boolean hasNext()
    {
        boolean flag;
        if(0L != nextCursor)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasPrevious()
    {
        boolean flag;
        if(0L != previousCursor)
            flag = true;
        else
            flag = false;
        return flag;
    }

    @Override
	public int hashCode()
    {
        int i;
        if(ids != null)
            i = Arrays.hashCode(ids);
        else
            i = 0;
        return i;
    }

    @Override
	public String toString()
    {
        return (new StringBuilder()).append("IDs{ids=").append(ids).append(", previousCursor=").append(previousCursor).append(", nextCursor=").append(nextCursor).append('}').toString();
    }

    private static String ROOT_NODE_NAMES[];
    private static final long serialVersionUID = 0xa49d493b84decd47L;
    private int ids[];
    private long nextCursor;
    private long previousCursor;

    static 
    {
        String as[] = new String[2];
        as[0] = "id_list";
        as[1] = "ids";
        ROOT_NODE_NAMES = as;
    }
}
