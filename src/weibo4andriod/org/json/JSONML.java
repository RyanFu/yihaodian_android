// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;

import java.util.Iterator;

// Referenced classes of package weibo4andriod.org.json:
//            JSONException, XMLTokener, XML, JSONArray, 
//            JSONObject

public class JSONML
{

    public JSONML()
    {
    }

    private static Object parse(XMLTokener xmltokener, boolean flag, JSONArray jsonarray)
        throws JSONException
    {
_L11:
        Object obj = xmltokener.nextContent();
        if(obj != XML.LT) goto _L2; else goto _L1
_L1:
        Object obj1 = xmltokener.nextToken();
        if(!(obj1 instanceof Character)) goto _L4; else goto _L3
_L3:
        Object obj5;
        if(obj1 == XML.SLASH)
        {
            obj5 = xmltokener.nextToken();
            if(!(obj5 instanceof String))
                throw new JSONException((new StringBuilder()).append("Expected a closing name instead of '").append(obj5).append("'.").toString());
            if(xmltokener.nextToken() != XML.GT)
                throw xmltokener.syntaxError("Misshaped close tag");
        } else
        if(obj1 == XML.BANG)
        {
            char c = xmltokener.next();
            if(c == '-')
            {
                if(xmltokener.next() == '-')
                    xmltokener.skipPast("-->");
                xmltokener.back();
                continue; /* Loop/switch isn't completed */
            }
            if(c == '[')
            {
                if(xmltokener.nextToken().equals("CDATA") && xmltokener.next() == '[')
                {
                    if(jsonarray != null)
                        jsonarray.put(xmltokener.nextCDATA());
                } else
                {
                    throw xmltokener.syntaxError("Expected 'CDATA['");
                }
                continue; /* Loop/switch isn't completed */
            }
            int i = 1;
            do
            {
                Object obj6 = xmltokener.nextMeta();
                if(obj6 == null)
                    throw xmltokener.syntaxError("Missing '>' after '<!'.");
                if(obj6 == XML.LT)
                    i++;
                else
                if(obj6 == XML.GT)
                    i--;
            } while(i > 0);
            continue; /* Loop/switch isn't completed */
        } else
        {
            if(obj1 == XML.QUEST)
                xmltokener.skipPast("?>");
            else
                throw xmltokener.syntaxError("Misshaped tag");
            continue; /* Loop/switch isn't completed */
        }
          goto _L5
_L4:
        String s;
        JSONArray jsonarray1;
        JSONObject jsonobject;
        Object obj2;
        if(!(obj1 instanceof String))
            throw xmltokener.syntaxError((new StringBuilder()).append("Bad tagName '").append(obj1).append("'.").toString());
        s = (String)obj1;
        jsonarray1 = new JSONArray();
        jsonobject = new JSONObject();
        if(flag)
        {
            jsonarray1.put(s);
            if(jsonarray != null)
                jsonarray.put(jsonarray1);
        } else
        {
            jsonobject.put("tagName", s);
            if(jsonarray != null)
                jsonarray.put(jsonobject);
        }
        obj2 = null;
_L9:
        if(obj2 == null)
            obj2 = xmltokener.nextToken();
        if(obj2 == null)
            throw xmltokener.syntaxError("Misshaped tag");
        if(obj2 instanceof String) goto _L7; else goto _L6
_L7:
        String s1 = (String)obj2;
        if(!flag && (s1 == "tagName" || s1 == "childNode"))
            throw xmltokener.syntaxError("Reserved attribute.");
        Object obj3 = xmltokener.nextToken();
        if(obj3 == XML.EQ)
        {
            Object obj4 = xmltokener.nextToken();
            if(!(obj4 instanceof String))
                throw xmltokener.syntaxError("Missing value");
            jsonobject.accumulate(s1, JSONObject.stringToValue((String)obj4));
            obj2 = null;
        } else
        {
            jsonobject.accumulate(s1, "");
            obj2 = obj3;
        }
        if(true) goto _L9; else goto _L8
_L6:
        if(flag && jsonobject.length() > 0)
            jsonarray1.put(jsonobject);
        if(obj2 != XML.SLASH)
            break; /* Loop/switch isn't completed */
        if(xmltokener.nextToken() != XML.GT)
            throw xmltokener.syntaxError("Misshaped tag");
        if(jsonarray != null)
            continue; /* Loop/switch isn't completed */
        if(flag)
            obj5 = jsonarray1;
        else
            obj5 = jsonobject;
_L5:
        return obj5;
_L8:
        if(obj2 != XML.GT)
            throw xmltokener.syntaxError("Misshaped tag");
        String s2 = (String)parse(xmltokener, flag, jsonarray1);
        if(s2 == null)
            continue; /* Loop/switch isn't completed */
        if(!s2.equals(s))
            throw xmltokener.syntaxError((new StringBuilder()).append("Mismatched '").append(s).append("' and '").append(s2).append("'").toString());
        if(!flag && jsonarray1.length() > 0)
            jsonobject.put("childNodes", jsonarray1);
        if(jsonarray != null)
            continue; /* Loop/switch isn't completed */
        if(flag)
            obj5 = jsonarray1;
        else
            obj5 = jsonobject;
        if(true) goto _L5; else goto _L2
_L2:
        if(jsonarray != null)
        {
            if(obj instanceof String)
                obj = JSONObject.stringToValue((String)obj);
            jsonarray.put(obj);
        }
        if(true) goto _L11; else goto _L10
_L10:
    }

    public static JSONArray toJSONArray(String s)
        throws JSONException
    {
        return toJSONArray(new XMLTokener(s));
    }

    public static JSONArray toJSONArray(XMLTokener xmltokener)
        throws JSONException
    {
        return (JSONArray)parse(xmltokener, true, null);
    }

    public static JSONObject toJSONObject(String s)
        throws JSONException
    {
        return toJSONObject(new XMLTokener(s));
    }

    public static JSONObject toJSONObject(XMLTokener xmltokener)
        throws JSONException
    {
        return (JSONObject)parse(xmltokener, false, null);
    }

    public static String toString(JSONArray jsonarray)
        throws JSONException
    {
        StringBuffer stringbuffer;
        String s1;
        Object obj;
        stringbuffer = new StringBuffer();
        String s = jsonarray.getString(0);
        XML.noSpace(s);
        s1 = XML.escape(s);
        stringbuffer.append('<');
        stringbuffer.append(s1);
        obj = jsonarray.opt(1);
        if(!(obj instanceof JSONObject)) goto _L2; else goto _L1
_L1:
        JSONObject jsonobject = (JSONObject)obj;
        Iterator iterator = jsonobject.keys();
        int i;
        int j;
        int k;
        Object obj1;
        do
        {
            if(!iterator.hasNext())
                break;
            String s2 = iterator.next().toString();
            XML.noSpace(s2);
            String s3 = jsonobject.optString(s2);
            if(s3 != null)
            {
                stringbuffer.append(' ');
                stringbuffer.append(XML.escape(s2));
                stringbuffer.append('=');
                stringbuffer.append('"');
                stringbuffer.append(XML.escape(s3));
                stringbuffer.append('"');
            }
        } while(true);
        i = 2;
          goto _L3
_L2:
        i = 1;
_L5:
        j = jsonarray.length();
        if(i >= j)
        {
            stringbuffer.append('/');
            stringbuffer.append('>');
        } else
        {
            stringbuffer.append('>');
            k = i;
            do
            {
                obj1 = jsonarray.get(k);
                k++;
                if(obj1 != null)
                    if(obj1 instanceof String)
                        stringbuffer.append(XML.escape(obj1.toString()));
                    else
                    if(obj1 instanceof JSONObject)
                        stringbuffer.append(toString((JSONObject)obj1));
                    else
                    if(obj1 instanceof JSONArray)
                        stringbuffer.append(toString((JSONArray)obj1));
            } while(k < j);
            stringbuffer.append('<');
            stringbuffer.append('/');
            stringbuffer.append(s1);
            stringbuffer.append('>');
        }
        return stringbuffer.toString();
_L3:
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static String toString(JSONObject jsonobject)
        throws JSONException
    {
        StringBuffer stringbuffer;
        String s;
        stringbuffer = new StringBuffer();
        s = jsonobject.optString("tagName");
        if(s != null) goto _L2; else goto _L1
_L1:
        String s2 = XML.escape(jsonobject.toString());
_L4:
        return s2;
_L2:
        String s1;
        JSONArray jsonarray;
        XML.noSpace(s);
        s1 = XML.escape(s);
        stringbuffer.append('<');
        stringbuffer.append(s1);
        Iterator iterator = jsonobject.keys();
        do
        {
            if(!iterator.hasNext())
                break;
            String s3 = iterator.next().toString();
            if(!s3.equals("tagName") && !s3.equals("childNodes"))
            {
                XML.noSpace(s3);
                String s4 = jsonobject.optString(s3);
                if(s4 != null)
                {
                    stringbuffer.append(' ');
                    stringbuffer.append(XML.escape(s3));
                    stringbuffer.append('=');
                    stringbuffer.append('"');
                    stringbuffer.append(XML.escape(s4));
                    stringbuffer.append('"');
                }
            }
        } while(true);
        jsonarray = jsonobject.optJSONArray("childNodes");
        if(jsonarray != null)
            break; /* Loop/switch isn't completed */
        stringbuffer.append('/');
        stringbuffer.append('>');
_L5:
        s2 = stringbuffer.toString();
        if(true) goto _L4; else goto _L3
_L3:
        stringbuffer.append('>');
        int i = jsonarray.length();
        int j = 0;
        while(j < i) 
        {
            Object obj = jsonarray.get(j);
            if(obj != null)
                if(obj instanceof String)
                    stringbuffer.append(XML.escape(obj.toString()));
                else
                if(obj instanceof JSONObject)
                    stringbuffer.append(toString((JSONObject)obj));
                else
                if(obj instanceof JSONArray)
                    stringbuffer.append(toString((JSONArray)obj));
            j++;
        }
        stringbuffer.append('<');
        stringbuffer.append('/');
        stringbuffer.append(s1);
        stringbuffer.append('>');
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }
}
