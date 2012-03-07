// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.w3c.dom.*;
import weibo4andriod.http.HTMLEntity;
import weibo4andriod.http.Response;
import weibo4andriod.org.json.JSONException;
import weibo4andriod.org.json.JSONObject;

// Referenced classes of package weibo4andriod:
//            Configuration, WeiboException

public class WeiboResponse
    implements Serializable
{

    public WeiboResponse()
    {
        rateLimitLimit = -1;
        rateLimitRemaining = -1;
        rateLimitReset = -1L;
    }

    public WeiboResponse(Response response)
    {
        rateLimitLimit = -1;
        rateLimitRemaining = -1;
        rateLimitReset = -1L;
        String s = response.getResponseHeader("X-RateLimit-Limit");
        if(s != null)
            rateLimitLimit = Integer.parseInt(s);
        String s1 = response.getResponseHeader("X-RateLimit-Remaining");
        if(s1 != null)
            rateLimitRemaining = Integer.parseInt(s1);
        String s2 = response.getResponseHeader("X-RateLimit-Reset");
        if(s2 != null)
            rateLimitReset = Long.parseLong(s2);
    }

    protected static void ensureRootNodeNameIs(String s, Document document)
        throws WeiboException
    {
        Element element = document.getDocumentElement();
        if(!s.equals(element.getNodeName()))
            throw new WeiboException((new StringBuilder()).append("Unexpected root node name:").append(element.getNodeName()).append(". Expected:").append(s).append(". Check the availability of the Weibo API at http://open.t.sina.com.cn/").toString());
        else
            return;
    }

    protected static void ensureRootNodeNameIs(String s, Element element)
        throws WeiboException
    {
        if(!s.equals(element.getNodeName()))
            throw new WeiboException((new StringBuilder()).append("Unexpected root node name:").append(element.getNodeName()).append(". Expected:").append(s).append(". Check the availability of the Weibo API at http://open.t.sina.com.cn/.").toString());
        else
            return;
    }

    protected static void ensureRootNodeNameIs(String as[], Element element)
        throws WeiboException
    {
        String s = element.getNodeName();
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(as[j].equals(s))
                return;

        String s1 = "";
        for(int k = 0; k < as.length; k++)
        {
            if(k != 0)
                s1 = (new StringBuilder()).append(s1).append(" or ").toString();
            s1 = (new StringBuilder()).append(s1).append(as[k]).toString();
        }

        throw new WeiboException((new StringBuilder()).append("Unexpected root node name:").append(element.getNodeName()).append(". Expected:").append(s1).append(". Check the availability of the Weibo API at http://open.t.sina.com.cn/.").toString());
    }

    protected static boolean getBoolean(String s, JSONObject jsonobject)
        throws JSONException
    {
        String s1 = jsonobject.getString(s);
        boolean flag;
        if(s1 == null || "".equals(s1) || "null".equals(s1))
            flag = false;
        else
            flag = Boolean.valueOf(s1).booleanValue();
        return flag;
    }

    protected static boolean getChildBoolean(String s, Element element)
    {
        return Boolean.valueOf(getTextContent(s, element)).booleanValue();
    }

    protected static Date getChildDate(String s, Element element)
        throws WeiboException
    {
        return getChildDate(s, element, "EEE MMM d HH:mm:ss z yyyy");
    }

    protected static Date getChildDate(String s, Element element, String s1)
        throws WeiboException
    {
        return parseDate(getChildText(s, element), s1);
    }

    protected static int getChildInt(String s, Element element)
    {
        String s1 = getTextContent(s, element);
        int i;
        if(s1 == null || "".equals(s1) || "null".equals(s))
            i = -1;
        else
            i = Integer.valueOf(s1).intValue();
        return i;
    }

    protected static long getChildLong(String s, Element element)
    {
        String s1 = getTextContent(s, element);
        long l;
        if(s1 == null || "".equals(s1) || "null".equals(s))
            l = -1L;
        else
            l = Long.valueOf(s1).longValue();
        return l;
    }

    protected static String getChildText(String s, Element element)
    {
        return HTMLEntity.unescape(getTextContent(s, element));
    }

    protected static int getInt(String s, JSONObject jsonobject)
        throws JSONException
    {
        String s1 = jsonobject.getString(s);
        int i;
        if(s1 == null || "".equals(s1) || "null".equals(s1))
            i = -1;
        else
            i = Integer.parseInt(s1);
        return i;
    }

    protected static long getLong(String s, JSONObject jsonobject)
        throws JSONException
    {
        String s1 = jsonobject.getString(s);
        long l;
        if(s1 == null || "".equals(s1) || "null".equals(s1))
            l = -1L;
        else
            l = Long.parseLong(s1);
        return l;
    }

    protected static String getString(String s, JSONObject jsonobject, boolean flag)
    {
        String s1 = null;
        String s2;
        String s3;
        try
        {
            s2 = jsonobject.getString(s);
        }
        catch(JSONException jsonexception)
        {
            continue; /* Loop/switch isn't completed */
        }
        s1 = s2;
        if(!flag)
            break MISSING_BLOCK_LABEL_27;
        s3 = URLDecoder.decode(s1, "UTF-8");
        s1 = s3;
_L2:
        return s1;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected static String getTextContent(String s, Element element)
    {
        NodeList nodelist = element.getElementsByTagName(s);
        if(nodelist.getLength() <= 0) goto _L2; else goto _L1
_L1:
        Node node = nodelist.item(0).getFirstChild();
        if(node == null) goto _L2; else goto _L3
_L3:
        String s1;
        s1 = node.getNodeValue();
        if(s1 == null)
            s1 = "";
_L5:
        return s1;
_L2:
        s1 = "";
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected static boolean isRootNodeNilClasses(Document document)
    {
        String s = document.getDocumentElement().getNodeName();
        boolean flag;
        if("nil-classes".equals(s) || "nilclasses".equals(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected static Date parseDate(String s, String s1)
        throws WeiboException
    {
        if(s != null && !"".equals(s)) goto _L2; else goto _L1
_L1:
        Date date = null;
_L4:
        return date;
_L2:
        SimpleDateFormat simpledateformat;
        simpledateformat = (SimpleDateFormat)formatMap.get(s1);
        if(simpledateformat == null)
        {
            simpledateformat = new SimpleDateFormat(s1, Locale.ENGLISH);
            simpledateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
            formatMap.put(s1, simpledateformat);
        }
        simpledateformat;
        JVM INSTR monitorenter ;
        Date date1 = simpledateformat.parse(s);
        date = date1;
        if(true) goto _L4; else goto _L3
_L3:
        ParseException parseexception;
        parseexception;
        throw new WeiboException((new StringBuilder()).append("Unexpected format(").append(s).append(") returned from sina.com.cn").toString());
    }

    public int getRateLimitLimit()
    {
        return rateLimitLimit;
    }

    public int getRateLimitRemaining()
    {
        return rateLimitRemaining;
    }

    public long getRateLimitReset()
    {
        return rateLimitReset;
    }

    private static final boolean IS_DALVIK = Configuration.isDalvik();
    private static Map formatMap = new HashMap();
    private static final long serialVersionUID = 0x30d96af8300e975aL;
    private transient int rateLimitLimit;
    private transient int rateLimitRemaining;
    private transient long rateLimitReset;

}
