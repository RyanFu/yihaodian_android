// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package weibo4andriod.org.json;


// Referenced classes of package weibo4andriod.org.json:
//            JSONException, JSONObject, JSONTokener

public class Cookie
{

    public Cookie()
    {
    }

    public static String escape(String s)
    {
        String s1 = s.trim();
        StringBuffer stringbuffer = new StringBuffer();
        int i = s1.length();
        int j = 0;
        while(j < i) 
        {
            char c = s1.charAt(j);
            if(c < ' ' || c == '+' || c == '%' || c == '=' || c == ';')
            {
                stringbuffer.append('%');
                stringbuffer.append(Character.forDigit(0xf & c >>> 4, 16));
                stringbuffer.append(Character.forDigit(c & 0xf, 16));
            } else
            {
                stringbuffer.append(c);
            }
            j++;
        }
        return stringbuffer.toString();
    }

    public static JSONObject toJSONObject(String s)
        throws JSONException
    {
        JSONObject jsonobject;
        JSONTokener jsontokener;
        jsonobject = new JSONObject();
        jsontokener = new JSONTokener(s);
        jsonobject.put("name", jsontokener.nextTo('='));
        jsontokener.next('=');
        jsonobject.put("value", jsontokener.nextTo(';'));
        jsontokener.next();
_L2:
        String s1;
        Object obj;
        if(!jsontokener.more())
            break MISSING_BLOCK_LABEL_135;
        s1 = unescape(jsontokener.nextTo("=;"));
        if(jsontokener.next() == '=')
            break MISSING_BLOCK_LABEL_116;
        if(!s1.equals("secure"))
            break; /* Loop/switch isn't completed */
        obj = Boolean.TRUE;
_L3:
        jsonobject.put(s1, obj);
        if(true) goto _L2; else goto _L1
_L1:
        throw jsontokener.syntaxError("Missing '=' in cookie parameter.");
        obj = unescape(jsontokener.nextTo(';'));
        jsontokener.next();
          goto _L3
        return jsonobject;
    }

    public static String toString(JSONObject jsonobject)
        throws JSONException
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(escape(jsonobject.getString("name")));
        stringbuffer.append("=");
        stringbuffer.append(escape(jsonobject.getString("value")));
        if(jsonobject.has("expires"))
        {
            stringbuffer.append(";expires=");
            stringbuffer.append(jsonobject.getString("expires"));
        }
        if(jsonobject.has("domain"))
        {
            stringbuffer.append(";domain=");
            stringbuffer.append(escape(jsonobject.getString("domain")));
        }
        if(jsonobject.has("path"))
        {
            stringbuffer.append(";path=");
            stringbuffer.append(escape(jsonobject.getString("path")));
        }
        if(jsonobject.optBoolean("secure"))
            stringbuffer.append(";secure");
        return stringbuffer.toString();
    }

    public static String unescape(String s)
    {
        int i;
        StringBuffer stringbuffer;
        int j;
        i = s.length();
        stringbuffer = new StringBuffer();
        j = 0;
_L2:
        char c;
        int k;
        char c1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        c = s.charAt(j);
        if(c == '+')
        {
            k = j;
            c1 = ' ';
        } else
        {
            if(c != '%' || j + 2 >= i)
                break MISSING_BLOCK_LABEL_132;
            int l = JSONTokener.dehexchar(s.charAt(j + 1));
            int i1 = JSONTokener.dehexchar(s.charAt(j + 2));
            if(l < 0 || i1 < 0)
                break MISSING_BLOCK_LABEL_132;
            char c2 = (char)(i1 + l * 16);
            k = j + 2;
            c1 = c2;
        }
_L3:
        stringbuffer.append(c1);
        j = k + 1;
        if(true) goto _L2; else goto _L1
_L1:
        return stringbuffer.toString();
        k = j;
        c1 = c;
          goto _L3
    }
}
