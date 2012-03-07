// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;

import java.util.Iterator;

// Referenced classes of package weibo4andriod.org.json:
//            JSONException, XMLTokener, JSONObject, JSONArray

public class XML
{

    public XML()
    {
    }

    public static String escape(String s)
    {
        StringBuffer stringbuffer;
        int i;
        int j;
        stringbuffer = new StringBuffer();
        i = 0;
        j = s.length();
_L7:
        char c;
        if(i >= j)
            break MISSING_BLOCK_LABEL_125;
        c = s.charAt(i);
        c;
        JVM INSTR lookupswitch 4: default 72
    //                   34: 115
    //                   38: 85
    //                   60: 95
    //                   62: 105;
           goto _L1 _L2 _L3 _L4 _L5
_L2:
        break MISSING_BLOCK_LABEL_115;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuffer.append(c);
_L8:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        stringbuffer.append("&amp;");
          goto _L8
_L4:
        stringbuffer.append("&lt;");
          goto _L8
_L5:
        stringbuffer.append("&gt;");
          goto _L8
        stringbuffer.append("&quot;");
          goto _L8
        return stringbuffer.toString();
    }

    public static void noSpace(String s)
        throws JSONException
    {
        int i = s.length();
        if(i == 0)
            throw new JSONException("Empty string.");
        for(int j = 0; j < i; j++)
            if(Character.isWhitespace(s.charAt(j)))
                throw new JSONException((new StringBuilder()).append("'").append(s).append("' contains a space character.").toString());

    }

    private static boolean parse(XMLTokener xmltokener, JSONObject jsonobject, String s)
        throws JSONException
    {
        Object obj = xmltokener.nextToken();
        if(obj != BANG) goto _L2; else goto _L1
_L1:
        char c = xmltokener.next();
        if(c != '-') goto _L4; else goto _L3
_L3:
        if(xmltokener.next() != '-') goto _L6; else goto _L5
_L5:
        boolean flag;
        xmltokener.skipPast("-->");
        flag = false;
_L8:
        return flag;
_L6:
        xmltokener.back();
        break MISSING_BLOCK_LABEL_51;
_L4:
        if(c == '[')
        {
            if(xmltokener.nextToken().equals("CDATA") && xmltokener.next() == '[')
            {
                String s4 = xmltokener.nextCDATA();
                if(s4.length() > 0)
                    jsonobject.accumulate("content", s4);
                flag = false;
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
            if(obj6 == LT)
                i++;
            else
            if(obj6 == GT)
                i--;
        } while(i > 0);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L2:
        if(obj == QUEST)
        {
            xmltokener.skipPast("?>");
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(obj == SLASH)
        {
            Object obj5 = xmltokener.nextToken();
            if(s == null)
                throw xmltokener.syntaxError((new StringBuilder()).append("Mismatched close tag").append(obj5).toString());
            if(!obj5.equals(s))
                throw xmltokener.syntaxError((new StringBuilder()).append("Mismatched ").append(s).append(" and ").append(obj5).toString());
            if(xmltokener.nextToken() != GT)
                throw xmltokener.syntaxError("Misshaped close tag");
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof Character)
            throw xmltokener.syntaxError("Misshaped tag");
        String s1 = (String)obj;
        Object obj1 = null;
        JSONObject jsonobject1 = new JSONObject();
        do
        {
            if(obj1 == null)
                obj1 = xmltokener.nextToken();
            if(!(obj1 instanceof String))
                break;
            String s3 = (String)obj1;
            Object obj3 = xmltokener.nextToken();
            if(obj3 == EQ)
            {
                Object obj4 = xmltokener.nextToken();
                if(!(obj4 instanceof String))
                    throw xmltokener.syntaxError("Missing value");
                jsonobject1.accumulate(s3, JSONObject.stringToValue((String)obj4));
                obj1 = null;
            } else
            {
                jsonobject1.accumulate(s3, "");
                obj1 = obj3;
            }
        } while(true);
        if(obj1 == SLASH)
        {
            if(xmltokener.nextToken() != GT)
                throw xmltokener.syntaxError("Misshaped tag");
            jsonobject.accumulate(s1, jsonobject1);
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(obj1 != GT)
            break; /* Loop/switch isn't completed */
        Object obj2;
label0:
        do
            do
            {
                obj2 = xmltokener.nextContent();
                if(obj2 == null)
                {
                    if(s1 != null)
                        throw xmltokener.syntaxError((new StringBuilder()).append("Unclosed tag ").append(s1).toString());
                    flag = false;
                    continue; /* Loop/switch isn't completed */
                }
                if(!(obj2 instanceof String))
                    continue label0;
                String s2 = (String)obj2;
                if(s2.length() > 0)
                    jsonobject1.accumulate("content", JSONObject.stringToValue(s2));
            } while(true);
        while(obj2 != LT || !parse(xmltokener, jsonobject1, s1));
        if(jsonobject1.length() == 0)
            jsonobject.accumulate(s1, "");
        else
        if(jsonobject1.length() == 1 && jsonobject1.opt("content") != null)
            jsonobject.accumulate(s1, jsonobject1.opt("content"));
        else
            jsonobject.accumulate(s1, jsonobject1);
        flag = false;
        if(true) goto _L8; else goto _L7
_L7:
        throw xmltokener.syntaxError("Misshaped tag");
    }

    public static JSONObject toJSONObject(String s)
        throws JSONException
    {
        JSONObject jsonobject = new JSONObject();
        for(XMLTokener xmltokener = new XMLTokener(s); xmltokener.more() && xmltokener.skipPast("<"); parse(xmltokener, jsonobject, null));
        return jsonobject;
    }

    public static String toString(Object obj)
        throws JSONException
    {
        return toString(obj, null);
    }

    public static String toString(Object obj, String s)
        throws JSONException
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(obj instanceof JSONObject)
        {
            if(s != null)
            {
                stringbuffer.append('<');
                stringbuffer.append(s);
                stringbuffer.append('>');
            }
            JSONObject jsonobject = (JSONObject)obj;
            for(Iterator iterator = jsonobject.keys(); iterator.hasNext();)
            {
                String s4 = iterator.next().toString();
                Object obj2 = jsonobject.opt(s4);
                String s1;
                String s2;
                JSONArray jsonarray;
                int i;
                int j;
                Object obj1;
                String s3;
                Object obj3;
                if(obj2 == null)
                    obj3 = "";
                else
                    obj3 = obj2;
                if(obj3 instanceof String)
                {
                    String _tmp = (String)obj3;
                }
                if(s4.equals("content"))
                {
                    if(obj3 instanceof JSONArray)
                    {
                        JSONArray jsonarray2 = (JSONArray)obj3;
                        int i1 = jsonarray2.length();
                        int j1 = 0;
                        while(j1 < i1) 
                        {
                            if(j1 > 0)
                                stringbuffer.append('\n');
                            stringbuffer.append(escape(jsonarray2.get(j1).toString()));
                            j1++;
                        }
                    } else
                    {
                        stringbuffer.append(escape(obj3.toString()));
                    }
                } else
                if(obj3 instanceof JSONArray)
                {
                    JSONArray jsonarray1 = (JSONArray)obj3;
                    int k = jsonarray1.length();
                    int l = 0;
                    while(l < k) 
                    {
                        Object obj4 = jsonarray1.get(l);
                        if(obj4 instanceof JSONArray)
                        {
                            stringbuffer.append('<');
                            stringbuffer.append(s4);
                            stringbuffer.append('>');
                            stringbuffer.append(toString(obj4));
                            stringbuffer.append("</");
                            stringbuffer.append(s4);
                            stringbuffer.append('>');
                        } else
                        {
                            stringbuffer.append(toString(obj4, s4));
                        }
                        l++;
                    }
                } else
                if(obj3.equals(""))
                {
                    stringbuffer.append('<');
                    stringbuffer.append(s4);
                    stringbuffer.append("/>");
                } else
                {
                    stringbuffer.append(toString(obj3, s4));
                }
            }

            if(s != null)
            {
                stringbuffer.append("</");
                stringbuffer.append(s);
                stringbuffer.append('>');
            }
            s2 = stringbuffer.toString();
        } else
        if(obj instanceof JSONArray)
        {
            jsonarray = (JSONArray)obj;
            i = jsonarray.length();
            j = 0;
            while(j < i) 
            {
                obj1 = jsonarray.opt(j);
                if(s == null)
                    s3 = "array";
                else
                    s3 = s;
                stringbuffer.append(toString(obj1, s3));
                j++;
            }
            s2 = stringbuffer.toString();
        } else
        {
            if(obj == null)
                s1 = "null";
            else
                s1 = escape(obj.toString());
            if(s == null)
                s2 = (new StringBuilder()).append("\"").append(s1).append("\"").toString();
            else
            if(s1.length() == 0)
                s2 = (new StringBuilder()).append("<").append(s).append("/>").toString();
            else
                s2 = (new StringBuilder()).append("<").append(s).append(">").append(s1).append("</").append(s).append(">").toString();
        }
        return s2;
    }

    public static final Character AMP = new Character('&');
    public static final Character APOS = new Character('\'');
    public static final Character BANG = new Character('!');
    public static final Character EQ = new Character('=');
    public static final Character GT = new Character('>');
    public static final Character LT = new Character('<');
    public static final Character QUEST = new Character('?');
    public static final Character QUOT = new Character('"');
    public static final Character SLASH = new Character('/');

}
