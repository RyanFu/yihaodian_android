// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;


// Referenced classes of package weibo4andriod.org.json:
//            JSONException, JSONTokener, JSONArray, JSONObject

public class CDL
{

    public CDL()
    {
    }

    private static String getValue(JSONTokener jsontokener)
        throws JSONException
    {
        char c;
        do
            c = jsontokener.next();
        while(c == ' ' || c == '\t');
        c;
        JVM INSTR lookupswitch 4: default 60
    //                   0: 73
    //                   34: 78
    //                   39: 78
    //                   44: 87;
           goto _L1 _L2 _L3 _L3 _L4
_L1:
        String s;
        jsontokener.back();
        s = jsontokener.nextTo(',');
_L6:
        return s;
_L2:
        s = null;
        continue; /* Loop/switch isn't completed */
_L3:
        s = jsontokener.nextString(c);
        continue; /* Loop/switch isn't completed */
_L4:
        jsontokener.back();
        s = "";
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static JSONArray rowToJSONArray(JSONTokener jsontokener)
        throws JSONException
    {
        JSONArray jsonarray = new JSONArray();
_L4:
        String s = getValue(jsontokener);
        if(s != null && (jsonarray.length() != 0 || s.length() != 0)) goto _L2; else goto _L1
_L1:
        jsonarray = null;
_L7:
        return jsonarray;
_L2:
        jsonarray.put(s);
_L6:
        char c = jsontokener.next();
        if(c == ',') goto _L4; else goto _L3
_L3:
        if(c == ' ') goto _L6; else goto _L5
_L5:
        if(c != '\n' && c != '\r' && c != 0)
            throw jsontokener.syntaxError((new StringBuilder()).append("Bad character '").append(c).append("' (").append(c).append(").").toString());
          goto _L7
    }

    public static JSONObject rowToJSONObject(JSONArray jsonarray, JSONTokener jsontokener)
        throws JSONException
    {
        JSONArray jsonarray1 = rowToJSONArray(jsontokener);
        JSONObject jsonobject;
        if(jsonarray1 != null)
            jsonobject = jsonarray1.toJSONObject(jsonarray);
        else
            jsonobject = null;
        return jsonobject;
    }

    public static String rowToString(JSONArray jsonarray)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        while(i < jsonarray.length()) 
        {
            if(i > 0)
                stringbuffer.append(',');
            Object obj = jsonarray.opt(i);
            if(obj != null)
            {
                String s = obj.toString();
                if(s.indexOf(',') >= 0)
                {
                    if(s.indexOf('"') >= 0)
                    {
                        stringbuffer.append('\'');
                        stringbuffer.append(s);
                        stringbuffer.append('\'');
                    } else
                    {
                        stringbuffer.append('"');
                        stringbuffer.append(s);
                        stringbuffer.append('"');
                    }
                } else
                {
                    stringbuffer.append(s);
                }
            }
            i++;
        }
        stringbuffer.append('\n');
        return stringbuffer.toString();
    }

    public static JSONArray toJSONArray(String s)
        throws JSONException
    {
        return toJSONArray(new JSONTokener(s));
    }

    public static JSONArray toJSONArray(JSONArray jsonarray, String s)
        throws JSONException
    {
        return toJSONArray(jsonarray, new JSONTokener(s));
    }

    public static JSONArray toJSONArray(JSONArray jsonarray, JSONTokener jsontokener)
        throws JSONException
    {
        if(jsonarray != null && jsonarray.length() != 0) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray1 = null;
_L4:
        return jsonarray1;
_L2:
        jsonarray1 = new JSONArray();
        do
        {
            JSONObject jsonobject;
label0:
            {
                jsonobject = rowToJSONObject(jsonarray, jsontokener);
                if(jsonobject != null)
                    break label0;
                if(jsonarray1.length() == 0)
                    jsonarray1 = null;
            }
            if(true)
                continue;
            jsonarray1.put(jsonobject);
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static JSONArray toJSONArray(JSONTokener jsontokener)
        throws JSONException
    {
        return toJSONArray(rowToJSONArray(jsontokener), jsontokener);
    }

    public static String toString(JSONArray jsonarray)
        throws JSONException
    {
        JSONObject jsonobject = jsonarray.optJSONObject(0);
        if(jsonobject == null) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray1 = jsonobject.names();
        if(jsonarray1 == null) goto _L2; else goto _L3
_L3:
        String s = (new StringBuilder()).append(rowToString(jsonarray1)).append(toString(jsonarray1, jsonarray)).toString();
_L5:
        return s;
_L2:
        s = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static String toString(JSONArray jsonarray, JSONArray jsonarray1)
        throws JSONException
    {
        String s;
        if(jsonarray == null || jsonarray.length() == 0)
        {
            s = null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            for(int i = 0; i < jsonarray1.length(); i++)
            {
                JSONObject jsonobject = jsonarray1.optJSONObject(i);
                if(jsonobject != null)
                    stringbuffer.append(rowToString(jsonobject.toJSONArray(jsonarray)));
            }

            s = stringbuffer.toString();
        }
        return s;
    }
}
